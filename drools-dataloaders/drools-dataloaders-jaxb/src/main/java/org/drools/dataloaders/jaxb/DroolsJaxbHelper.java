package org.drools.dataloaders.jaxb;

/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.drools.RuleBase;
import org.drools.common.InternalRuleBase;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageRegistry;
import org.drools.lang.descr.PackageDescr;
import org.drools.rule.builder.dialect.java.JavaDialect;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JPackage;
import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.ErrorReceiver;
import com.sun.tools.xjc.ModelLoader;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.Outline;

public class DroolsJaxbHelper {
    public static String[] addModel(Reader reader,
                                    PackageBuilder pkgBuilder,
                                    Options xjcOpts,
                                    String systemId) throws IOException {
        InputSource source = new InputSource( new CachingRewindableReader( reader ) );
        source.setSystemId( systemId.trim().startsWith( "." ) ? systemId : "." + systemId );

        xjcOpts.addGrammar( source );
        
        try {
            xjcOpts.parseArguments( new String[] { "-npa" } );
        } catch ( BadCommandLineException e ) {
            throw new IllegalArgumentException("Unable to parse arguments", e);
        }

        ErrorReceiver errorReceiver = new JaxbErrorReceiver4Drools();

        Model model = ModelLoader.load( xjcOpts,
                                        new JCodeModel(),
                                        errorReceiver );

        final Outline outline = model.generateCode( xjcOpts,
                                                    errorReceiver );

        MapVfsCodeWriter codeWriter = new MapVfsCodeWriter();
        model.codeModel.build( xjcOpts.createCodeWriter( codeWriter ) );

        Set<JavaDialect> dialects = new HashSet<JavaDialect>();
        List<String> classNames = new ArrayList<String>();
        for ( Entry<String, byte[]> entry : codeWriter.getMap().entrySet() ) {
            String name = entry.getKey();

            String pkgName = null;
            int dotPos = name.lastIndexOf( '.' );
            pkgName = name.substring( 0,
                                      dotPos );

            if ( !name.endsWith( "package-info.java" ) ) {
                classNames.add( pkgName );
            }
            
            dotPos = pkgName.lastIndexOf( '.' );
            if ( dotPos != -1 ) {
                pkgName = pkgName.substring( 0,
                                             dotPos );
            }

            PackageRegistry pkgReg = pkgBuilder.getPackageRegistry( pkgName );
            if ( pkgReg == null ) {
                pkgBuilder.addPackage( new PackageDescr( pkgName ) );
                pkgReg = pkgBuilder.getPackageRegistry( pkgName );
            }

            JavaDialect dialect = (JavaDialect) pkgReg.getDialectCompiletimeRegistry().getDialect( "java" );
            dialects.add( dialect );
            dialect.addSrc( convertToResource( entry.getKey() ),
                            entry.getValue() );
        }

        pkgBuilder.compileAll();
        pkgBuilder.updateResults();

        return (String[]) classNames.toArray( new String[classNames.size()] );
    }

    public static JAXBContext newInstance(String[] classNames,
                                          RuleBase rb) throws JAXBException {
        return newInstance( classNames,
                            Collections.<String, Object> emptyMap(),
                            rb );
    }

    public static JAXBContext newInstance(String[] classNames,
                                          Map<String, ? > properties,
                                          RuleBase rb) throws JAXBException {
        ClassLoader classLoader = ((InternalRuleBase) rb).getRootClassLoader();

        Class[] classes = new Class[classNames.length];
        int i = 0;
        try {
            for ( i = 0; i < classNames.length; i++ ) {
                classes[i] = classLoader.loadClass( classNames[i] );
            }
        } catch ( ClassNotFoundException e ) {
            throw new JAXBException( "Unable to resolve class '" + classNames[i] + "'",
                                     e );
        }        

        return JAXBContext.newInstance( classes,
                                        properties );
    }

    private static String convertToResource(String string) {
        int lastDot = string.lastIndexOf( '.' );
        return string.substring( 0,
                                 lastDot ).replace( '.',
                                                    '/' ) + string.substring( lastDot,
                                                                              string.length() );
    }

    public static class MapVfsCodeWriter extends CodeWriter {

        private final Map<String, byte[]> map;

        private ByteArrayOutputStream     currentBaos;
        private String                    currentPath;

        public MapVfsCodeWriter() {
            this.map = new LinkedHashMap<String, byte[]>();
        }

        public OutputStream openBinary(JPackage pkg,
                                       String fileName) throws IOException {
            String pkgName = pkg.name();

            if ( pkgName.length() != 0 ) {
                pkgName += '.';
            }

            if ( this.currentBaos != null ) {
                this.currentBaos.close();
                this.map.put( this.currentPath,
                              this.currentBaos.toByteArray() );
            }

            this.currentPath = pkgName + fileName;
            this.currentBaos = new ByteArrayOutputStream();

            return new FilterOutputStream( this.currentBaos ) {
                public void close() {
                    // don't let this stream close
                }
            };
        }

        public void close() throws IOException {
            if ( this.currentBaos != null ) {
                this.currentBaos.close();
                this.map.put( this.currentPath,
                              this.currentBaos.toByteArray() );
            }
        }

        public Map<String, byte[]> getMap() {
            return this.map;
        }

    }

    public static class JaxbErrorReceiver4Drools extends ErrorReceiver {

        public String stage = "processing";

        public void warning(SAXParseException e) {
            e.printStackTrace();
        }

        public void error(SAXParseException e) {
            e.printStackTrace();
        }

        public void fatalError(SAXParseException e) {
            e.printStackTrace();
        }

        public void info(SAXParseException e) {
            e.printStackTrace();
        }
    }

    public static class CachingRewindableReader extends Reader {
        private Reader                 source;
        private boolean                sourceClosed;
        private RewindableStringReader cache;
        private StringBuilder          strBuilder;

        public CachingRewindableReader(Reader source) {
            this.source = source;
            this.strBuilder = new StringBuilder();
        }

        public int read(char[] cbuf,
                        int off,
                        int len) throws IOException {
            int value = 0;
            if ( this.cache == null ) {
                value = this.source.read( cbuf,
                                          off,
                                          len );
                if ( value != -1 ) {
                    // keep appening to the stringBuilder until we are at the end
                    this.strBuilder.append( cbuf,
                                            off,
                                            value );
                } else {
                    // we are at the end, so switch to cache
                    this.cache = new RewindableStringReader( strBuilder.toString() );
                }
            } else {
                value = this.cache.read( cbuf,
                                         off,
                                         len );
            }
            return value;
        }

        public void close() throws IOException {
            if ( !sourceClosed ) {
                // close the source, we only do this once.
                this.source.close();
                this.sourceClosed = true;
            }

            if ( cache == null ) {
                // switch to cache if we haven't already
                this.cache = new RewindableStringReader( strBuilder.toString() );
            } else {
                // reset the cache, so it can be read again.
                this.cache.reset();
            }
        }
    }

    public static class RewindableStringReader extends StringReader {
        public RewindableStringReader(String s) {
            super( s );
        }

        public void close() {
            try {
                reset();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }
}
