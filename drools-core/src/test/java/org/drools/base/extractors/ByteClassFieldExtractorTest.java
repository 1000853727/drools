package org.drools.base.extractors;

import junit.framework.Assert;

import org.drools.base.ClassFieldAccessorCache;
import org.drools.base.TestBean;
import org.drools.spi.InternalReadAccessor;

public class ByteClassFieldExtractorTest extends BaseClassFieldExtractorsTest {
    InternalReadAccessor extractor = ClassFieldAccessorCache.getInstance().getReader( TestBean.class,
                                                                               "byteAttr",
                                                                               getClass().getClassLoader() );
    TestBean  bean      = new TestBean();

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGetBooleanValue() {
        try {
            this.extractor.getBooleanValue( null,
                                            this.bean );
            fail( "Should have throw an exception" );
        } catch ( final Exception e ) {
            // success
        }
    }

    public void testGetByteValue() {
        try {
            Assert.assertEquals( 1,
                                 this.extractor.getByteValue( null,
                                                              this.bean ) );
        } catch ( final Exception e ) {
            fail( "Should not throw an exception" );
        }
    }

    public void testGetCharValue() {
        try {
            this.extractor.getCharValue( null,
                                         this.bean );
            fail( "Should have throw an exception" );
        } catch ( final Exception e ) {
            // success
        }
    }

    public void testGetDoubleValue() {
        try {
            Assert.assertEquals( 1.0,
                                 this.extractor.getDoubleValue( null,
                                                                this.bean ),
                                 0.01 );
        } catch ( final Exception e ) {
            fail( "Should not throw an exception" );
        }
    }

    public void testGetFloatValue() {
        try {
            Assert.assertEquals( 1.0f,
                                 this.extractor.getFloatValue( null,
                                                               this.bean ),
                                 0.01 );
        } catch ( final Exception e ) {
            fail( "Should not throw an exception" );
        }
    }

    public void testGetIntValue() {
        try {
            Assert.assertEquals( 1,
                                 this.extractor.getIntValue( null,
                                                             this.bean ) );
        } catch ( final Exception e ) {
            fail( "Should not throw an exception" );
        }
    }

    public void testGetLongValue() {
        try {
            Assert.assertEquals( 1,
                                 this.extractor.getLongValue( null,
                                                              this.bean ) );
        } catch ( final Exception e ) {
            fail( "Should not throw an exception" );
        }
    }

    public void testGetShortValue() {
        try {
            Assert.assertEquals( 1,
                                 this.extractor.getShortValue( null,
                                                               this.bean ) );
        } catch ( final Exception e ) {
            fail( "Should not throw an exception" );
        }
    }

    public void testGetValue() {
        try {
            Assert.assertEquals( 1,
                                 ((Number) this.extractor.getValue( null,
                                                                    this.bean )).byteValue() );
        } catch ( final Exception e ) {
            fail( "Should not throw an exception" );
        }
    }

    public void testIsNullValue() {
        try {
            Assert.assertFalse( this.extractor.isNullValue( null,
                                                            this.bean ) );
        } catch ( final Exception e ) {
            fail( "Should not throw an exception" );
        }
    }
}
