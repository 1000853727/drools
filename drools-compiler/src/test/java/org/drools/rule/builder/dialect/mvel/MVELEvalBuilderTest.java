package org.drools.rule.builder.dialect.mvel;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.drools.Cheese;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.base.ClassFieldAccessorCache;
import org.drools.base.ClassObjectType;
import org.drools.common.InternalFactHandle;
import org.drools.compiler.DialectCompiletimeRegistry;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderConfiguration;
import org.drools.lang.descr.EvalDescr;
import org.drools.lang.descr.RuleDescr;
import org.drools.reteoo.LeftTuple;
import org.drools.reteoo.MockLeftTupleSink;
import org.drools.rule.Declaration;
import org.drools.rule.EvalCondition;
import org.drools.rule.Package;
import org.drools.rule.Pattern;
import org.drools.spi.InternalReadAccessor;

public class MVELEvalBuilderTest extends TestCase {

    private ClassFieldAccessorCache cache;

    public void setUp() {
        cache = ClassFieldAccessorCache.getInstance();
    }

    public void testSimpleExpression() {
        final Package pkg = new Package( "pkg1" );
        final RuleDescr ruleDescr = new RuleDescr( "rule 1" );

        PackageBuilder pkgBuilder = new PackageBuilder( pkg );
        final PackageBuilderConfiguration conf = pkgBuilder.getPackageBuilderConfiguration();
        DialectCompiletimeRegistry dialectRegistry = pkgBuilder.getPackageRegistry( pkg.getName() ).getDialectCompiletimeRegistry();
        MVELDialect mvelDialect = ( MVELDialect ) dialectRegistry.getDialect( "mvel" );

        final InstrumentedBuildContent context = new InstrumentedBuildContent( conf,
                                                                               ruleDescr,
                                                                               dialectRegistry,
                                                                               pkg,                                                                               
                                                                               mvelDialect );

        final InstrumentedDeclarationScopeResolver declarationResolver = new InstrumentedDeclarationScopeResolver();

        final InternalReadAccessor extractor = cache.getReader( Cheese.class,
                                                             "price",
                                                             getClass().getClassLoader() );

        final Pattern pattern = new Pattern( 0,
                                             new ClassObjectType( int.class ) );
        final Declaration declaration = new Declaration( "a",
                                                         extractor,
                                                         pattern );
        final Map map = new HashMap();
        map.put( "a",
                 declaration );
        declarationResolver.setDeclarations( map );
        context.setDeclarationResolver( declarationResolver );

        final EvalDescr evalDescr = new EvalDescr();
        evalDescr.setContent( "a == 10" );

        final MVELEvalBuilder builder = new MVELEvalBuilder();
        final EvalCondition eval = (EvalCondition) builder.build( context,
                                                                  evalDescr );

        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        final WorkingMemory wm = ruleBase.newStatefulSession();

        MockLeftTupleSink sink = new MockLeftTupleSink();
        final Cheese cheddar = new Cheese( "cheddar",
                                           10 );
        final InternalFactHandle f0 = (InternalFactHandle) wm.insert( cheddar );
        final LeftTuple tuple = new LeftTuple( f0, sink, true );
        
        Object evalContext = eval.createContext();

        assertTrue( eval.isAllowed( tuple,
                                    wm,
                                    evalContext ) );

        cheddar.setPrice( 9 );
        wm.update( f0,
                   cheddar );
        assertFalse( eval.isAllowed( tuple,
                                     wm,
                                     evalContext ) );
    }

}
