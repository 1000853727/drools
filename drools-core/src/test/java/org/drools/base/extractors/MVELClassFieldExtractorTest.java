package org.drools.base.extractors;

import java.util.Vector;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.drools.Address;
import org.drools.Person;
import org.drools.base.ClassFieldAccessorCache;
import org.drools.spi.InternalReadAccessor;

public class MVELClassFieldExtractorTest extends TestCase {

    InternalReadAccessor               extractor = ClassFieldAccessorCache.getInstance().getReader( Person.class,
                                                                                             "addresses['home'].street",
                                                                                             getClass().getClassLoader() );
    private final Person[]  person    = new Person[2];
    private final Address[] business  = new Address[2];
    private final Address[] home      = new Address[2];

    protected void setUp() throws Exception {
        super.setUp();
        person[0] = new Person( "bob",
                                30 );
        business[0] = new Address( "Business Street",
                                   "999",
                                   null );
        home[0] = new Address( "Home Street",
                               "555",
                               "55555555" );
        person[0].getAddresses().put( "business",
                                      business[0] );
        person[0].getAddresses().put( "home",
                                      home[0] );

        person[1] = new Person( "mark",
                                35 );
        business[1] = new Address( "Another Business Street",
                                   "999",
                                   null );
        home[1] = new Address( "Another Home Street",
                               "555",
                               "55555555" );
        person[1].getAddresses().put( "business",
                                      business[1] );
        person[1].getAddresses().put( "home",
                                      home[1] );

    }

    public void testGetBooleanValue() {
        try {
            this.extractor.getBooleanValue( null,
                                            this.person[0] );
            fail( "Should have throw an exception" );
        } catch ( final Exception e ) {
            // success
        }
    }

    public void testGetByteValue() {
        try {
            this.extractor.getByteValue( null,
                                         this.person[0] );
            fail( "Should have throw an exception" );
        } catch ( final Exception e ) {
            // success
        }
    }

    public void testGetCharValue() {
        try {
            this.extractor.getCharValue( null,
                                         this.person[0] );
            fail( "Should have throw an exception" );
        } catch ( final Exception e ) {
            // success
        }
    }

    public void testGetDoubleValue() {
        try {
            this.extractor.getDoubleValue( null,
                                           this.person[0] );
            fail( "Should have throw an exception" );
        } catch ( final Exception e ) {
            // success
        }
    }

    public void testGetFloatValue() {
        try {
            this.extractor.getFloatValue( null,
                                          this.person[0] );
            fail( "Should have throw an exception" );
        } catch ( final Exception e ) {
            // success
        }
    }

    public void testGetIntValue() {
        try {
            this.extractor.getIntValue( null,
                                        this.person[0] );
            fail( "Should have throw an exception" );
        } catch ( final Exception e ) {
            // success
        }
    }

    public void testGetLongValue() {
        try {
            this.extractor.getLongValue( null,
                                         this.person[0] );
            fail( "Should have throw an exception" );
        } catch ( final Exception e ) {
            // success
        }
    }

    public void testGetShortValue() {
        try {
            this.extractor.getShortValue( null,
                                          this.person[0] );
            fail( "Should have throw an exception" );
        } catch ( final Exception e ) {
            // success
        }
    }

    public void testGetValue() {
        try {
            Assert.assertEquals( home[0].getStreet(),
                                 this.extractor.getValue( null,
                                                          this.person[0] ) );
            Assert.assertTrue( this.extractor.getValue( null,
                                                        this.person[0] ) instanceof String );
        } catch ( final Exception e ) {
            fail( "Should not throw an exception" );
        }
    }

    public void testIsNullValue() {
        try {
            Assert.assertFalse( this.extractor.isNullValue( null,
                                                            this.person[0] ) );

            InternalReadAccessor nullExtractor = ClassFieldAccessorCache.getInstance().getReader( Person.class,
                                                                                           "addresses['business'].phone",
                                                                                           getClass().getClassLoader() );
            Assert.assertTrue( nullExtractor.isNullValue( null,
                                                          this.person[0] ) );
        } catch ( final Exception e ) {
            fail( "Should not throw an exception" );
        }
    }

    public void testMultithreads() {
        final int THREAD_COUNT = 30;

        try {
            final Vector errors = new Vector();

            final Thread t[] = new Thread[THREAD_COUNT];
            for ( int j = 0; j < 10; j++ ) {
                for ( int i = 0; i < t.length; i++ ) {
                    final int ID = i;
                    t[i] = new Thread() {
                        public void run() {
                            try {
                                final int ITERATIONS = 300;
                                for ( int k = 0; k < ITERATIONS; k++ ) {
                                    String value = (String) extractor.getValue( null,
                                                                                person[ID % 2] );
                                    if ( !home[ID % 2].getStreet().equals( value ) ) {
                                        errors.add( "THREAD(" + ID + "): Wrong value at iteration " + k + ". Value='" + value + "'\n" );
                                    }
                                }
                            } catch ( Exception ex ) {
                                ex.printStackTrace();
                                errors.add( ex );
                            }
                        }

                    };
                    t[i].start();
                }
                for ( int i = 0; i < t.length; i++ ) {
                    t[i].join();
                }
            }
            if ( !errors.isEmpty() ) {
                System.out.println(errors.toString());
                fail( " Errors occured during execution " );
            }
        } catch ( InterruptedException e ) {
            e.printStackTrace();
            fail( "Unexpected exception running test: " + e.getMessage() );
        }
    }

}
