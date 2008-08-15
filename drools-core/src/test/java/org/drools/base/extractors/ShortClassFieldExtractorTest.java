package org.drools.base.extractors;

import junit.framework.Assert;

import org.drools.base.ClassFieldAccessorCache;
import org.drools.base.ClassFieldAccessorStore;
import org.drools.base.TestBean;
import org.drools.spi.InternalReadAccessor;

public class ShortClassFieldExtractorTest extends BaseClassFieldExtractorsTest {
    private static final short VALUE = 3;

    InternalReadAccessor       reader;
    TestBean                   bean  = new TestBean();

    protected void setUp() throws Exception {
        ClassFieldAccessorStore store = new ClassFieldAccessorStore();
        store.setClassFieldAccessorCache( new ClassFieldAccessorCache( Thread.currentThread().getContextClassLoader() ) );
        store.setEagerWire( true );
        this.reader = store.getReader( TestBean.class,
                                              "shortAttr",
                                              getClass().getClassLoader() );
    }

    public void testGetBooleanValue() {
        try {
            this.reader.getBooleanValue( null,
                                            this.bean );
            fail( "Should have throw an exception" );
        } catch ( final Exception e ) {
            // success
        }
    }

    public void testGetByteValue() {
        try {
            Assert.assertEquals( ShortClassFieldExtractorTest.VALUE,
                                 this.reader.getByteValue( null,
                                                              this.bean ) );
        } catch ( final Exception e ) {
            fail( "Should not throw an exception" );
        }
    }

    public void testGetCharValue() {
        try {
            this.reader.getCharValue( null,
                                         this.bean );
            fail( "Should have throw an exception" );
        } catch ( final Exception e ) {
            // success
        }
    }

    public void testGetDoubleValue() {
            Assert.assertEquals( ShortClassFieldExtractorTest.VALUE,
                                 this.reader.getDoubleValue( null,
                                                                this.bean ),
                                 0.01 );
    }

    public void testGetFloatValue() {
            Assert.assertEquals( ShortClassFieldExtractorTest.VALUE,
                                 this.reader.getFloatValue( null,
                                                               this.bean ),
                                 0.01 );
    }

    public void testGetIntValue() {
        Assert.assertEquals( ShortClassFieldExtractorTest.VALUE,
                             this.reader.getIntValue( null,
                                                         this.bean ) );
    }

    public void testGetLongValue() {
        Assert.assertEquals( ShortClassFieldExtractorTest.VALUE,
                             this.reader.getLongValue( null,
                                                          this.bean ) );
    }

    public void testGetShortValue() {
        Assert.assertEquals( ShortClassFieldExtractorTest.VALUE,
                             this.reader.getShortValue( null,
                                                           this.bean ) );
    }

    public void testGetValue() {
        Assert.assertEquals( ShortClassFieldExtractorTest.VALUE,
                             ((Number) this.reader.getValue( null,
                                                                this.bean )).shortValue() );
    }

    public void testIsNullValue() {
        Assert.assertFalse( this.reader.isNullValue( null,
                                                        this.bean ) );
    }
}
