package org.drools.compiler.kie.builder.impl;

import org.drools.compiler.compiler.io.memory.MemoryFileSystem;
import org.drools.compiler.kproject.models.KieModuleModelImpl;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.KieFileSystem;
import org.kie.internal.io.ResourceTypeImpl;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import static org.drools.core.util.IoUtils.readBytesFromInputStream;

import static org.drools.compiler.kie.builder.impl.KieBuilderImpl.RESOURCES_ROOT;

public class KieFileSystemImpl
        implements
        KieFileSystem {
    
    private final MemoryFileSystem mfs;

    public KieFileSystemImpl() {
        this(new MemoryFileSystem());
    }

    public KieFileSystemImpl(MemoryFileSystem mfs) {
        this.mfs = mfs;
    }

    public KieFileSystem write(String path,
                               byte[] content) {
        mfs.write( path, content, true );
        return this;
    }

    public KieFileSystem write(String path,
                               String text) {
        return write( path, text.getBytes() );
    }

    public KieFileSystem write(String path,
                               Resource resource) {
        try {
            return write( path, readBytesFromInputStream(resource.getInputStream()) );
        } catch (IOException e) {
            throw new RuntimeException("Unable to write Resource: " + resource.toString(), e);
        }
    }

    public KieFileSystem write(Resource resource) {
        try {
            String target = resource.getTargetPath() != null ? resource.getTargetPath() : resource.getSourcePath();
            if( target != null ) {
                write( RESOURCES_ROOT+target, readBytesFromInputStream(resource.getInputStream()) );
                ResourceConfiguration conf = resource.getConfiguration();
                if( conf != null ) {
                    Properties prop = ResourceTypeImpl.toProperties(conf);
                    ByteArrayOutputStream buff = new ByteArrayOutputStream();
                    prop.store( buff, "Configuration properties for resource: "+target );
                    write( RESOURCES_ROOT+target+".properties", buff.toByteArray() );
                }
                return this;
            } else {
                throw new RuntimeException( "Resource does not have neither a source nor a target path. Impossible to add it to the bundle. Please set either the source or target name of the resource before adding it." + resource.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to write Resource: " + resource.toString(), e);
        }
    }
    
    public void delete(String... paths) {
        for ( String path : paths ) {
            mfs.remove(path);
        }
    }

    public byte[] read(String path) {
        return mfs.read( path );
    }

    public MemoryFileSystem asMemoryFileSystem() {
        return mfs;
    }

    public KieFileSystem generateAndWritePomXML(ReleaseId releaseId) {
        write("pom.xml", KieBuilderImpl.generatePomXml(releaseId) );
        return this;
    }

    public KieFileSystem writePomXML(byte[] content) {
        write("pom.xml", content);
        return this;
    }

    public KieFileSystem writePomXML(String content) {
        write("pom.xml", content);
        return this;
    }

    public KieFileSystem writeKModuleXML(byte[] content) {
        write(KieModuleModelImpl.KMODULE_SRC_PATH, content);
        return this;
    }

    public KieFileSystem writeKModuleXML(String content) {
        write(KieModuleModelImpl.KMODULE_SRC_PATH, content);
        return this;
    }
    
}
