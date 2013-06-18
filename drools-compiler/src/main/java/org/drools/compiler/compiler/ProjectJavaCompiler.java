package org.drools.compiler.compiler;

import org.drools.compiler.commons.jci.compilers.CompilationResult;
import org.drools.compiler.commons.jci.compilers.JavaCompiler;
import org.drools.compiler.commons.jci.compilers.JavaCompilerFactory;
import org.drools.compiler.commons.jci.compilers.JavaCompilerSettings;
import org.drools.compiler.commons.jci.problems.CompilationProblem;
import org.drools.compiler.commons.jci.readers.MemoryResourceReader;
import org.drools.compiler.commons.jci.stores.ResourceStore;
import org.drools.compiler.rule.builder.dialect.java.JavaDialectConfiguration;
import org.drools.core.common.ProjectClassLoader;
import org.kie.internal.builder.KnowledgeBuilderResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.drools.core.util.ClassUtils.convertResourceToClassName;

public class ProjectJavaCompiler {

    private final JavaCompiler compiler;

    public ProjectJavaCompiler(PackageBuilder pkgBuilder) {
        this((JavaDialectConfiguration) pkgBuilder.getPackageBuilderConfiguration().getDialectConfiguration( "java" ));
    }

    public ProjectJavaCompiler(JavaDialectConfiguration configuration) {
        compiler = JavaCompilerFactory.getInstance().loadCompiler(configuration);
    }

    public List<KnowledgeBuilderResult> compileAll(ProjectClassLoader projectClassLoader,
                                                   List<String> classList,
                                                   MemoryResourceReader src) {

        List<KnowledgeBuilderResult> results = new ArrayList<KnowledgeBuilderResult>();

        if ( classList.isEmpty() ) {
            return results;
        }
        final String[] classes = new String[classList.size()];
        classList.toArray( classes );

        CompilationResult result = compiler.compile( classes,
                                                     src,
                                                     new ProjectResourceStore(projectClassLoader),
                                                     projectClassLoader );

        if ( result.getErrors().length > 0 ) {
            Map<String, PackageBuilder.ErrorHandler> errorHandlerMap = new HashMap<String, PackageBuilder.ErrorHandler>();

            for ( int i = 0; i < result.getErrors().length; i++ ) {
                final CompilationProblem err = result.getErrors()[i];
                PackageBuilder.ErrorHandler handler = errorHandlerMap.get( err.getFileName() );
                if (handler == null) {
                    handler = new PackageBuilder.SrcErrorHandler("Src compile error");
                    errorHandlerMap.put(err.getFileName(), handler);
                }
                handler.addError( err );
            }

            for (PackageBuilder.ErrorHandler handler : errorHandlerMap.values()) {
                if (handler.isInError()) {
                    results.add(handler.getError());
                }
            }
        }

        return results;
    }

    private static class ProjectResourceStore implements ResourceStore {

        private final ProjectClassLoader projectClassLoader;

        private ProjectResourceStore(ProjectClassLoader projectClassLoader) {
            this.projectClassLoader = projectClassLoader;
        }

        @Override
        public void write(String pResourceName, byte[] pResourceData) {
            projectClassLoader.defineClass(convertResourceToClassName(pResourceName), pResourceName, pResourceData);
        }

        @Override
        public byte[] read(String pResourceName) {
            return projectClassLoader.getBytecode(pResourceName);
        }

        @Override
        public void remove(String pResourceName) {
            throw new UnsupportedOperationException("org.drools.compiler.compiler.ProjectJavaCompiler.ProjectResourceStore.remove -> TODO");
        }
    }
}
