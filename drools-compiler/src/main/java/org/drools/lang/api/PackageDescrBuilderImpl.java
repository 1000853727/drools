/**
 * 
 */
package org.drools.lang.api;

import org.drools.lang.descr.FunctionImportDescr;
import org.drools.lang.descr.PackageDescr;

/**
 * A builder implementation for PackageDescrs using a fluent API.
 */
public class PackageDescrBuilderImpl extends BaseDescrBuilderImpl
    implements
    PackageDescrBuilder {
    private PackageDescr pkg;

    private PackageDescrBuilderImpl() {
        super( new PackageDescr() );
        pkg = (PackageDescr) descr;
    }

    public static PackageDescrBuilder newPackage() {
        return new PackageDescrBuilderImpl();
    }

    /**
     * {@inheritDoc}
     */
    public PackageDescr getDescr() {
        return pkg;
    }

    /**
     * {@inheritDoc}
     */
    public PackageDescrBuilder name( String name ) {
        pkg.setNamespace( name );
        return this;
    }

    public ImportDescrBuilder newImportDescr() {
        ImportDescrBuilder impl = new ImportDescrBuilderImpl( false );
        pkg.addImport( impl.getDescr() );
        return impl;
    }

    public ImportDescrBuilder newFunctionImportDescr() {
        ImportDescrBuilder impl = new ImportDescrBuilderImpl( true );
        pkg.addFunctionImport( (FunctionImportDescr) impl.getDescr() );
        return impl;
    }

}
