package org.drools.rule.builder.dialect.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.drools.RuntimeDroolsException;
import org.drools.base.TypeResolver;
import org.drools.compiler.FunctionError;
import org.drools.lang.descr.FunctionDescr;
import org.drools.rule.LineMappings;
import org.drools.rule.Package;
import org.drools.rule.builder.FunctionBuilder;
import org.drools.util.StringUtils;
import org.mvel.templates.TemplateRuntime;
import org.mvel.integration.impl.MapVariableResolverFactory;


public class JavaFunctionBuilder
    implements
    FunctionBuilder {

    //    private static final StringTemplateGroup functionGroup = new StringTemplateGroup( new InputStreamReader( JavaFunctionBuilder.class.getResourceAsStream( "javaFunction.stg" ) ),
    //                                                                                      AngleBracketTemplateLexer.class );

    private static final String template = StringUtils.readFileAsString( new InputStreamReader( JavaFunctionBuilder.class.getResourceAsStream( "javaFunction.mvel" ) ) );

    public JavaFunctionBuilder() {

    }

    /* (non-Javadoc)
     * @see org.drools.rule.builder.dialect.java.JavaFunctionBuilder#build(org.drools.rule.Package, org.drools.lang.descr.FunctionDescr, org.codehaus.jfdi.interpreter.TypeResolver, java.util.Map)
     */
    public String build(final Package pkg,
                        final FunctionDescr functionDescr,
                        final TypeResolver typeResolver,
                        final Map lineMappings,
                        final List errors) {

        final Map vars = new HashMap();

        vars.put( "package",
                  pkg.getName() );

        vars.put( "imports",
                  pkg.getImports().keySet() );

        final List staticImports = new LinkedList();
        for( Iterator it = pkg.getStaticImports().iterator(); it.hasNext(); ) {
            final String staticImport = (String) it.next();
            if( ! staticImport.startsWith( functionDescr.getClassName() ) ) {
                staticImports.add( staticImport );
            }
        }
        vars.put( "staticImports",
                  staticImports );

        vars.put( "className",
                  StringUtils.ucFirst( functionDescr.getName() ) );

        vars.put( "methodName",
                  functionDescr.getName() );

        vars.put( "returnType",
                  functionDescr.getReturnType() );

        vars.put( "parameterTypes",
                  functionDescr.getParameterTypes() );

        vars.put( "parameterNames",
                  functionDescr.getParameterNames() );

        // Check that all the parameters are resolvable
        final Map params = new HashMap();
        final List names = functionDescr.getParameterNames();
        final List types = functionDescr.getParameterTypes();
        try {
            for ( int i = 0, size = names.size(); i < size; i++ ) {
                params.put( names.get( i ),
                            typeResolver.resolveType( (String) types.get( i ) ) );
            }
        } catch ( final ClassNotFoundException e ) {
            errors.add( new FunctionError( functionDescr,
                                           e,
                                           "unable to resolve type while building function" ) );
        }

        vars.put( "text",
                  functionDescr.getText() );

        final String text = String.valueOf(TemplateRuntime.eval( template, null, new MapVariableResolverFactory(vars)));

        //System.out.println( text );

        final BufferedReader reader = new BufferedReader( new StringReader( text ) );
        String line = null;
        final String lineStartsWith = "    public static " + functionDescr.getReturnType() + " " + functionDescr.getName();
        int offset = 0;
        try {
            while ( (line = reader.readLine()) != null ) {
                offset++;
                if ( line.startsWith( lineStartsWith ) ) {
                    break;
                }
            }
            functionDescr.setOffset( offset );
        } catch ( final IOException e ) {
            // won't ever happen, it's just reading over a string.
            throw new RuntimeDroolsException( "Error determining start offset with function" );
        }

        final String name = pkg.getName() + "." + StringUtils.ucFirst( functionDescr.getName() );
        final LineMappings mapping = new LineMappings( name );
        mapping.setStartLine( functionDescr.getLine() );
        mapping.setOffset( functionDescr.getOffset() );
        lineMappings.put( name,
                          mapping );

        return text;

    }

}