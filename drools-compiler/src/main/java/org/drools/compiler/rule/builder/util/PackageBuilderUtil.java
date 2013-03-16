package org.drools.compiler.rule.builder.util;

import org.drools.compiler.lang.descr.AccumulateDescr;
import org.drools.compiler.lang.descr.EntryPointDescr;
import org.drools.compiler.lang.descr.WindowReferenceDescr;
import org.drools.core.rule.QueryElement;
import org.drools.core.rule.RuleConditionElement;

public class PackageBuilderUtil {

    /**
     * This method checks for the conditions when local declarations should be read from a tuple instead
     * of the right object when resolving declarations in an accumulate
     * 
     * @param accumDescr
     * @param source
     * @return
     */
    public static boolean isReadLocalsFromTuple(final AccumulateDescr accumDescr,
                                                final RuleConditionElement source) {
        final boolean readLocalsFromTuple = accumDescr.isMultiPattern() || 
                ( accumDescr.getInputPattern().getSource() != null && 
                  !( accumDescr.getInputPattern().getSource() instanceof WindowReferenceDescr ) &&
                  !( accumDescr.getInputPattern().getSource() instanceof EntryPointDescr ) ) ||
                  source instanceof QueryElement ||
                  ( source.getNestedElements().size() == 1 && source.getNestedElements().get( 0 ) instanceof QueryElement );
        return readLocalsFromTuple;
    }
}
