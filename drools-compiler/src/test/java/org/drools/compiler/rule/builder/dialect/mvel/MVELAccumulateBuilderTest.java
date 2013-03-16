package org.drools.compiler.rule.builder.dialect.mvel;

import org.drools.compiler.Cheese;
import org.drools.compiler.compiler.DialectCompiletimeRegistry;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.compiler.compiler.PackageBuilderConfiguration;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;
import org.junit.Test;
import static org.junit.Assert.*;

import org.drools.core.RuleBase;
import org.drools.core.base.mvel.MVELCompileable;
import org.drools.core.common.InternalFactHandle;
import org.drools.compiler.lang.descr.AccumulateDescr;
import org.drools.compiler.lang.descr.PackageDescr;
import org.drools.compiler.lang.descr.PatternDescr;
import org.drools.compiler.lang.descr.RuleDescr;
import org.drools.core.reteoo.InitialFactImpl;
import org.drools.core.reteoo.LeftTupleImpl;
import org.drools.compiler.reteoo.MockLeftTupleSink;
import org.drools.core.rule.Accumulate;
import org.drools.core.rule.MVELDialectRuntimeData;
import org.drools.core.rule.Package;
import org.drools.compiler.rule.builder.RuleBuildContext;

public class MVELAccumulateBuilderTest {

    @Test
    public void testSimpleExpression() {
        PackageBuilder pkgBuilder = new PackageBuilder();
        pkgBuilder.addPackage( new PackageDescr( "pkg1" ) );

        final Package pkg = pkgBuilder.getPackage();
        final RuleDescr ruleDescr = new RuleDescr( "rule 1" );

        final PackageBuilderConfiguration conf = pkgBuilder.getPackageBuilderConfiguration();
        DialectCompiletimeRegistry dialectRegistry = pkgBuilder.getPackageRegistry( pkg.getName() ).getDialectCompiletimeRegistry();
        MVELDialect mvelDialect = (MVELDialect) dialectRegistry.getDialect( "mvel" );

        final RuleBuildContext context = new RuleBuildContext( pkgBuilder,
                                                               ruleDescr,
                                                               dialectRegistry,
                                                               pkg,
                                                               mvelDialect );

        final AccumulateDescr accDescr = new AccumulateDescr();
        final PatternDescr inputPattern = new PatternDescr( "org.drools.compiler.Cheese",
                                                            "$cheese" );
        accDescr.setInputPattern( inputPattern );
        accDescr.setInitCode( "total = 0;" );
        accDescr.setActionCode( "total += $cheese.price;" );
        accDescr.setReverseCode( "total -= $cheese.price;" );
        accDescr.setResultCode( "new Integer(total)" );

        final MVELAccumulateBuilder builder = new MVELAccumulateBuilder();
        final Accumulate acc = (Accumulate) builder.build( context,
                                                           accDescr );

        ((MVELCompileable) acc.getAccumulators()[0]).compile( (MVELDialectRuntimeData) pkgBuilder.getPackageRegistry( pkg.getName() ).getDialectRuntimeRegistry().getDialectData( "mvel" ) );

        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        final WorkingMemory wm = ruleBase.newStatefulSession();

        MockLeftTupleSink sink = new MockLeftTupleSink();
        final Cheese cheddar1 = new Cheese( "cheddar",
                                            10 );
        final Cheese cheddar2 = new Cheese( "cheddar",
                                            8 );
        final InternalFactHandle f0 = (InternalFactHandle) wm.insert( new InitialFactImpl() );
        final InternalFactHandle f1 = (InternalFactHandle) wm.insert( cheddar1 );
        final InternalFactHandle f2 = (InternalFactHandle) wm.insert( cheddar2 );
        final LeftTupleImpl tuple = new LeftTupleImpl( f0,
                                               sink,
                                               true );

        Object[] wmContext = acc.createWorkingMemoryContext();
        Object[] accContext = acc.createContext();
        acc.init( wmContext,
                  accContext,
                  tuple,
                  wm );

        acc.accumulate( wmContext,
                        accContext,
                        tuple,
                        f1,
                        wm );
        acc.accumulate( wmContext,
                        accContext,
                        tuple,
                        f2,
                        wm );

        assertEquals( new Integer( 18 ),
                      acc.getResult( wmContext,
                                     accContext,
                                     tuple,
                                     wm )[0] );

        acc.reverse( wmContext,
                     accContext,
                     tuple,
                     f1,
                     wm );

        assertEquals( new Integer( 8 ),
                      acc.getResult( wmContext,
                                     accContext,
                                     tuple,
                                     wm )[0] );
    }

}
