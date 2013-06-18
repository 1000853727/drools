package org.drools.compiler.compiler;

import org.drools.core.base.ClassTypeResolver;
import org.drools.core.base.TypeResolver;
import org.drools.core.util.ClassUtils;
import org.drools.core.factmodel.traits.TraitRegistry;
import org.drools.compiler.lang.descr.ImportDescr;
import org.drools.core.rule.DialectRuntimeRegistry;
import org.drools.core.rule.ImportDeclaration;
import org.drools.core.rule.Package;
import org.drools.core.rule.Rule;
import org.drools.core.spi.Consequence;
import org.kie.api.io.Resource;

import java.util.HashSet;
import java.util.Map;

public class PackageRegistry {
    private final Package                    pkg;
    private String                           dialect;

    private final DialectRuntimeRegistry     dialectRuntimeRegistry;
    private final DialectCompiletimeRegistry dialectCompiletimeRegistry;

    private final TypeResolver               typeResolver;

    public PackageRegistry(PackageBuilder packageBuilder, Package pkg) {
        this.pkg = pkg;
        this.dialectCompiletimeRegistry = packageBuilder.getPackageBuilderConfiguration().buildDialectRegistry( packageBuilder,
                                                                                                                this,
                                                                                                                pkg );
        this.dialectRuntimeRegistry = pkg.getDialectRuntimeRegistry();

        this.typeResolver = new ClassTypeResolver( new HashSet<String>( this.pkg.getImports().keySet() ),
                                                   packageBuilder.getRootClassLoader(),
                                                   this.pkg.getName() );

        this.typeResolver.addImport( pkg.getName() + ".*" );
        pkg.setTypeResolver(typeResolver);
    }

    private PackageRegistry(Package pkg, DialectRuntimeRegistry runtimeRegistry, DialectCompiletimeRegistry compiletimeRegistry, TypeResolver typeResolver) {
        this.pkg = pkg;
        this.dialectRuntimeRegistry = runtimeRegistry;
        this.dialectCompiletimeRegistry = compiletimeRegistry;
        this.typeResolver = typeResolver;
    }

    PackageRegistry clonePackage(ClassLoader classLoader) {
        Package clonedPkg = ClassUtils.deepClone(pkg, classLoader);
        clonedPkg.setDialectRuntimeRegistry(pkg.getDialectRuntimeRegistry());
        for (Rule rule : pkg.getRules()) {
            Rule clonedRule = clonedPkg.getRule(rule.getName());
            clonedRule.setConsequence(rule.getConsequence());
            if (rule.hasNamedConsequences()) {
                for (Map.Entry<String, Consequence> namedConsequence : rule.getNamedConsequences().entrySet()) {
                    clonedRule.addNamedConsequence(namedConsequence.getKey(), namedConsequence.getValue());
                }
            }
        }

        PackageRegistry clone = new PackageRegistry(clonedPkg, dialectRuntimeRegistry, dialectCompiletimeRegistry, typeResolver);
        clone.setDialect(dialect);
        return clone;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public Package getPackage() {
        return pkg;
    }

    public ClassLoader getPackageClassLoader() {
        return getPackage().getPackageClassLoader();
    }

    public DialectRuntimeRegistry getDialectRuntimeRegistry() {
        return dialectRuntimeRegistry;
    }

    public DialectCompiletimeRegistry getDialectCompiletimeRegistry() {
        return dialectCompiletimeRegistry;
    }

    public void addImport(ImportDescr importDescr) {
        String importEntry = importDescr.getTarget();
        this.pkg.addImport( new ImportDeclaration( importEntry ) );
        this.typeResolver.addImport( importEntry );
        this.dialectCompiletimeRegistry.addImport( importDescr );
    }

    public void addStaticImport(ImportDescr importDescr) {
        this.dialectCompiletimeRegistry.addStaticImport( importDescr );
    }

    public TypeResolver getTypeResolver() {
        return this.typeResolver;
    }

    public void compileAll() {
        this.dialectCompiletimeRegistry.compileAll();
    }

    public boolean removeObjectsGeneratedFromResource(Resource resource) {
        return pkg.removeObjectsGeneratedFromResource(resource);
    }

    public TraitRegistry getTraitRegistry() {
        return pkg.getTraitRegistry();
    }
}
