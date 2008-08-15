package org.drools.rule.builder.dialect.mvel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.drools.Cheese;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.base.ClassFieldAccessorCache;
import org.drools.base.ClassFieldAccessorStore;
import org.drools.base.ClassObjectType;
import org.drools.base.mvel.MVELPredicateExpression;
import org.drools.common.InternalFactHandle;
import org.drools.common.InternalWorkingMemory;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderConfiguration;
import org.drools.compiler.PackageRegistry;
import org.drools.lang.descr.PredicateDescr;
import org.drools.lang.descr.RuleDescr;
import org.drools.reteoo.LeftTuple;
import org.drools.reteoo.MockLeftTupleSink;
import org.drools.rule.Declaration;
import org.drools.rule.Package;
import org.drools.rule.Pattern;
import org.drools.rule.PredicateConstraint;
import org.drools.rule.PredicateConstraint.PredicateContextEntry;
import org.drools.spi.InternalReadAccessor;

public class MVELPredicateBuilderTest extends TestCase {


    ClassFieldAccessorStore store = new ClassFieldAccessorStore();

    protected void setUp() throws Exception {
        store.setClassFieldAccessorCache( new ClassFieldAccessorCache( Thread.currentThread().getContextClassLoader() ) );
        store.setEagerWire( true );
    }

    public void testSimpleExpression() {
        final Package pkg = new Package( "pkg1" );
        final RuleDescr ruleDescr = new RuleDescr( "rule 1" );

        PackageBuilder pkgBuilder = new PackageBuilder( pkg );
        final PackageBuilderConfiguration conf = pkgBuilder.getPackageBuilderConfiguration();
        PackageRegistry pkgRegistry = pkgBuilder.getPackageRegistry( pkg.getName() );
        MVELDialect mvelDialect = ( MVELDialect ) pkgRegistry.getDialectCompiletimeRegistry().getDialect( "mvel" );

        final InstrumentedBuildContent context = new InstrumentedBuildContent( pkgBuilder,
                                                                               ruleDescr,
                                                                               pkgRegistry.getDialectCompiletimeRegistry(),
                                                                               pkg,                                                                               
                                                                               mvelDialect );

        final InstrumentedDeclarationScopeResolver declarationResolver = new InstrumentedDeclarationScopeResolver();
        final InternalReadAccessor extractor = store.getReader( Cheese.class,
                                                             "price",
                                                             getClass().getClassLoader() );

        final Pattern patternA = new Pattern( 0,
                                              new ClassObjectType( Cheese.class ) );

        final Pattern patternB = new Pattern( 1,
                                              new ClassObjectType( Cheese.class ) );

        final Declaration a = new Declaration( "a",
                                               extractor,
                                               patternA );
        final Declaration b = new Declaration( "b",
                                               extractor,
                                               patternB );

        final Map map = new HashMap();
        map.put( "a",
                 a );
        map.put( "b",
                 b );
        declarationResolver.setDeclarations( map );
        context.setDeclarationResolver( declarationResolver );

        final PredicateDescr predicateDescr = new PredicateDescr();
        predicateDescr.setContent( "a == b" );

        final MVELPredicateBuilder builder = new MVELPredicateBuilder();

        final List[] usedIdentifiers = new ArrayList[2];
        final List list = new ArrayList();
        usedIdentifiers[1] = list;

        final Declaration[] previousDeclarations = new Declaration[]{a};
        final Declaration[] localDeclarations = new Declaration[]{b};

        final PredicateConstraint predicate = new PredicateConstraint( null,
                                                                       localDeclarations );

        builder.build( context,
                       usedIdentifiers,
                       previousDeclarations,
                       localDeclarations,
                       predicate,
                       predicateDescr );
        
        ( (MVELPredicateExpression) predicate.getPredicateExpression()).compile( Thread.currentThread().getContextClassLoader() );

        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        final InternalWorkingMemory wm = (InternalWorkingMemory) ruleBase.newStatefulSession();

        final Cheese stilton = new Cheese( "stilton",
                                           10 );

        final Cheese cheddar = new Cheese( "cheddar",
                                           10 );
        
        MockLeftTupleSink sink = new MockLeftTupleSink();
        
        final InternalFactHandle f0 = (InternalFactHandle) wm.insert( cheddar );
        final InternalFactHandle f1 = (InternalFactHandle) wm.insert( stilton );
        final LeftTuple tuple = new LeftTuple( f0, sink, true );

        final PredicateContextEntry predicateContext = (PredicateContextEntry) predicate.createContextEntry();
        predicateContext.leftTuple = tuple;
        predicateContext.workingMemory = wm;

        assertTrue( predicate.isAllowedCachedLeft( predicateContext,
                                                   f1 ) );

        cheddar.setPrice( 9 );
        wm.update( f0,
                   cheddar );

        assertFalse( predicate.isAllowedCachedLeft( predicateContext,
                                                    f1 ) );
    }

}
