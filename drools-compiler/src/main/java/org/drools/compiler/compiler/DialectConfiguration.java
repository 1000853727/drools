package org.drools.compiler.compiler;

import org.drools.core.rule.Package;

/**
 * Each Dialect can have its own configuration. Implementations of this class are typically
 * loaded via reflection in PackageBuilderConfiguration during the call to buildDialectRegistry().
 * This Class api is subject to change.
 */
public interface DialectConfiguration {
    
    public void init(PackageBuilderConfiguration  configuration);
    
    public Dialect newDialect(PackageBuilder packageBuilder, PackageRegistry pkgRegistry, Package pkg);
    
    public PackageBuilderConfiguration getPackageBuilderConfiguration();
}
