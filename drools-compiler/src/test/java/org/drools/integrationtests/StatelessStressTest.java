package org.drools.integrationtests;

import junit.framework.TestCase;
import org.drools.Address;
import org.drools.Person;
import org.drools.rule.Package;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatelessSession;
import org.drools.compiler.PackageBuilder;

import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is for testing possible PermSpace issues (leaking) when spawning lots of sessions in concurrent threads.
 * Normally this test will be XXX'ed out, as when running it will not terminate.
 * @author Michael Neale
 */
public class StatelessStressTest extends TestCase {

    public void testDummy() {
        
    }

    private static RuleBase getRuleBase(Package pkg) throws IOException, ClassNotFoundException {
        RuleBase ruleBase    = RuleBaseFactory.newRuleBase();

        ruleBase.addPackage( pkg );
        return SerializationHelper.serializeObject(ruleBase);
    }
    
    
    public void XXXtestLotsOfStateless() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "thread_class_test.drl" ) ) );
        assertFalse(builder.hasErrors());

        
        

        final RuleBase rb  = getRuleBase( builder.getPackage() );

        int numThreads = 100;
        Thread[] ts = new Thread[numThreads];
        
        
            for (int i=0; i<numThreads; i++) {
                Runnable run = new Runnable() {
    
                    public void run() {
                        
                        long start = 0;
                        long previous = 0;
                        
                        while (true) {
                            start = System.currentTimeMillis();
                            StatelessSession sess = rb.newStatelessSession();
                            try {
                                sess    = SerializationHelper.serializeObject(sess);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                            Person p = new Person();
                            p.setName( "Michael" ); 
                            Address add1 = new Address();
                            add1.setStreet( "High" );
                            Address add2 = new Address();
                            add2.setStreet( "Low" );
                            List l = new ArrayList();
                            l.add( add1 ); l.add( add2 );
                            p.setAddresses( l );        
                            sess.execute( p );
                            
                            long current = System.currentTimeMillis() - start;
                            if (previous != 0) {
                                float f = current/previous;
                                if (f > 1) {
                                    System.err.println("SLOWDOWN");
                                }
                            }
                            
                            previous = current;
                        }
                    }
                    
                };
                
                Thread t = new Thread(run);
                t.start();
                ts[i] = t;
            }
            
            for ( int i = 0; i < ts.length; i++ ) {
                ts[i].join();
            }
        
        
        
        
    }
    
    
}
