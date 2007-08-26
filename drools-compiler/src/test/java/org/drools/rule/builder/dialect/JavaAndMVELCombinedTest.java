package org.drools.rule.builder.dialect;

import java.io.InputStreamReader;
import java.io.Reader;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.compiler.PackageBuilder;
import org.drools.rule.Package;

public class JavaAndMVELCombinedTest extends TestCase {

    private final static String FN1 = "mveljavarules.drl";
    private final static String FN2 = "mvelonly.drl";
    private final static String FN3 = "javaonly.drl";

    public void testMixed() {
        timing( FN1, "mveljava: ");
    }
    
    public void testMVEL() {
        timing( FN2, "    mvel: ");
    }
    
    public void testJAVA() {
        timing( FN3, "    java: ");
    }
    
//    public void testJavaMVELCombination() throws Exception {
//        long time1 = timing( new Runnable() {
//            public void run() {
//                readDRL( FN1 );
//            }
//        } );
//        long time2 = timing( new Runnable() {
//            public void run() {
//                readDRL( FN2 );
//            }
//        } );
//        long time3 = timing( new Runnable() {
//            public void run() {
//                readDRL( FN3 );
//            }
//        } );
//        
//        System.out.println("mveljava: "+time1/1000.);
//        System.out.println("    mvel: "+time2/1000.);
//        System.out.println("    java: "+time3/1000.);
//        
//    }

    private void timing( String name, String msg ) {
        long start = System.currentTimeMillis();
        readDRL( name );
        long time = System.currentTimeMillis()-start;
        System.out.println(msg+time/1000.);
    }

    private void readDRL(String fn) {
        try {
            Reader source = new InputStreamReader( JavaAndMVELCombinedTest.class.getResourceAsStream( fn ) );

            PackageBuilder builder = new PackageBuilder();
            builder.addPackageFromDrl( source );
            Assert.assertEquals( 0,
                                 builder.getErrors().getErrors().length );

            Package pkg = builder.getPackage();
            RuleBase ruleBase = RuleBaseFactory.newRuleBase();
            ruleBase.addPackage( pkg );

            Assert.assertEquals( 2,
                                 pkg.getRules().length );
        } catch ( Throwable t ) {
            throw new RuntimeException(t);
        }

    }

}
