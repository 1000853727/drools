package org.drools.verifier.visitor;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.Collections;

import org.drools.compiler.DrlParser;
import org.drools.compiler.DroolsParserException;
import org.drools.lang.descr.PackageDescr;
import org.drools.verifier.components.Pattern;
import org.drools.verifier.components.Restriction;
import org.drools.verifier.components.VerifierComponentType;
import org.drools.verifier.data.VerifierComponent;
import org.drools.verifier.data.VerifierData;
import org.drools.verifier.data.VerifierReportFactory;
import org.junit.Assert;
import org.junit.Test;

public class NestedPatternsTest {

    @Test
    public void runVisitor() throws DroolsParserException,
                            UnknownDescriptionException {
        VerifierData data = VerifierReportFactory.newVerifierData();
        PackageDescrVisitor visitor = new PackageDescrVisitor( data,
                                                               Collections.EMPTY_LIST );

        Assert.assertNotNull( data );

        Reader drlReader = new InputStreamReader( getClass().getResourceAsStream( "NestedPatterns.drl" ) );
        PackageDescr packageDescr = new DrlParser().parse( drlReader );

        Assert.assertNotNull( packageDescr );

        visitor.visitPackageDescr( packageDescr );

        Collection<VerifierComponent> all = data.getAll();
        int patternCount = 0;
        for ( VerifierComponent verifierComponent : all ) {

            if ( verifierComponent.getVerifierComponentType().equals( VerifierComponentType.PATTERN ) ) {
                patternCount++;
            }
        }
        Assert.assertEquals( 4,
                             patternCount );

        Collection<Pattern> patterns = data.getAll( VerifierComponentType.PATTERN );

//        for ( Pattern pattern : patterns ) {
//            System.out.println( pattern.getPath() + " " + pattern );
//        }

        Assert.assertNotNull( patterns );
        Assert.assertEquals( 4,
                             patterns.size() );

        Collection<Restriction> restrictions = data.getAll( VerifierComponentType.RESTRICTION );

//        for ( Restriction restriction : restrictions ) {
//            System.out.println( restriction.getPath() + " " + restriction );
//        }

        Assert.assertNotNull( restrictions );
        Assert.assertEquals( 3,
                             restrictions.size() );

    }
}
