package org.drools.workbench.models.commons.backend.packages;

import org.drools.workbench.models.datamodel.packages.HasPackageName;

/**
 * Writes Package details to a String
 */
public class PackageNameWriter {

    public static void write( final StringBuilder sb,
                              final HasPackageName model ) {
        final String packageName = model.getPackageName();
        if ( !( packageName == null || packageName.isEmpty() ) ) {
            sb.append( "package " ).append( packageName ).append( ";\n\n" );
        }
    }

}
