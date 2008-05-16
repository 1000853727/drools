package org.drools.base.extractors;

import junit.framework.Assert;

import org.drools.base.ClassFieldAccessorCache;
import org.drools.base.TestBean;
import org.drools.spi.InternalReadAccessor;

public class CharClassFieldExtractorTest extends BaseClassFieldExtractorsTest {
    InternalReadAccessor extractor = ClassFieldAccessorCache.getInstance().getReader( TestBean.class,
                                                                               "charAttr",
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
            Assert.assertEquals( 'a',
                                 this.extractor.getByteValue( null,
                                                              this.bean ) );
        } catch ( final Exception e ) {
            fail( "Should not throw exception" );
        }
    }

    public void testGetCharValue() {
        try {
            Assert.assertEquals( 'a',
                                 this.extractor.getCharValue( null,
                                                              this.bean ) );
        } catch ( final Exception e ) {
            fail( "Should not throw exception" );
        }
    }

    public void testGetDoubleValue() {
        try {
            Assert.assertEquals( 'a',
                                 (int) this.extractor.getDoubleValue( null,
                                                                      this.bean ) );
        } catch ( final Exception e ) {
            fail( "Should not throw exception" );
        }
    }

    public void testGetFloatValue() {
        try {
            Assert.assertEquals( 'a',
                                 (int) this.extractor.getFloatValue( null,
                                                                     this.bean ) );
        } catch ( final Exception e ) {
            fail( "Should not throw exception" );
        }
    }

    public void testGetIntValue() {
        try {
            Assert.assertEquals( 'a',
                                 this.extractor.getIntValue( null,
                                                             this.bean ) );
        } catch ( final Exception e ) {
            fail( "Should not throw exception" );
        }
    }

    public void testGetLongValue() {
        try {
            Assert.assertEquals( 'a',
                                 (int) this.extractor.getLongValue( null,
                                                                    this.bean ) );
        } catch ( final Exception e ) {
            fail( "Should not throw exception" );
        }
    }

    public void testGetShortValue() {
        try {
            Assert.assertEquals( 'a',
                                 this.extractor.getShortValue( null,
                                                               this.bean ) );
        } catch ( final Exception e ) {
            fail( "Should not throw exception" );
        }
    }

    public void testGetValue() {
        try {
            Assert.assertEquals( 'a',
                                 ((Character) this.extractor.getValue( null,
                                                                       this.bean )).charValue() );
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
