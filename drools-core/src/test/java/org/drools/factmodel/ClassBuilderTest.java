package org.drools.factmodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.drools.base.ClassFieldAccessorCache;
import org.drools.base.ClassFieldAccessorStore;

public class ClassBuilderTest extends TestCase {

    ClassFieldAccessorStore store = new ClassFieldAccessorStore();
    private ClassLoader     classLoader;

    protected void setUp() throws Exception {
        super.setUp();
        classLoader = this.getClass().getClassLoader();
        store.setClassFieldAccessorCache( new ClassFieldAccessorCache( classLoader ) );
        store.setEagerWire( true );
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'org.drools.common.asm.ClassBuilder.buildClass(ClassDefinition)'
     */
    public void testBuildClass() {
        try {
            ClassBuilder builder = new ClassBuilder();

            ClassDefinition classDef = new ClassDefinition( "org.drools.TestClass1",
                                                            null,
                                                            new String[]{"java.io.Serializable"} );
            FieldDefinition intDef = new FieldDefinition( "intAttr",
                                                          "int" );

            FieldDefinition stringDef = new FieldDefinition( "stringAttr",
                                                             "java.lang.String" );//"java.lang.String" );
            classDef.addField( intDef );
            classDef.addField( stringDef );

            Class clazz = builder.buildAndLoadClass( classDef );
            intDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                        intDef.getName(),
                                                        classLoader ) );
            stringDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                           stringDef.getName(),
                                                           classLoader ) );

            byte[] data = builder.buildClass( classDef );

            Assert.assertSame( "Returned class should be the same",
                               clazz,
                               classDef.getDefinedClass() );
            Assert.assertEquals( "Class name should be equal",
                                 classDef.getClassName(),
                                 clazz.getName() );

            Serializable instance = (Serializable) clazz.newInstance();

            String stringValue = "Atributo String ok";
            stringDef.setValue( instance,
                                stringValue );
            Assert.assertEquals( "Attribute should have been correctly set",
                                 stringValue,
                                 stringDef.getValue( instance ) );

            int intValue = 50;
            intDef.setValue( instance,
                             new Integer( intValue ) );
            Assert.assertEquals( "Attribute should have been correctly set",
                                 intValue,
                                 ((Integer) intDef.getValue( instance )).intValue() );

            // testing class rebuilding
            clazz = builder.buildAndLoadClass( classDef );

        } catch ( Exception e ) {
            e.printStackTrace();
            Assert.fail( "Error creating class" );
        }
    }

    /**
     * how to write to a jar.
     * @param data
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void writeJar(byte[] data) throws FileNotFoundException,
                                      IOException {
        FileOutputStream out = new FileOutputStream( new File( "/Users/michaelneale/edson.jar" ) );
        JarOutputStream jout = new JarOutputStream( out );
        JarEntry je = new JarEntry( "br/com/auster/TestClass1.class" );
        jout.putNextEntry( je );
        jout.write( data );
        jout.closeEntry();
        jout.close();
    }

    public void testEquals() {
        try {
            ClassBuilder builder = new ClassBuilder();

            ClassDefinition classDef = new ClassDefinition( "org.drools.TestClass2",
                                                            null,
                                                            new String[]{} );
            FieldDefinition long1Def = new FieldDefinition( "longAttr1",
                                                            "long",
                                                            true );
            FieldDefinition long2Def = new FieldDefinition( "longAttr2",
                                                            "long",
                                                            true );
            FieldDefinition doubleDef = new FieldDefinition( "doubleAttr",
                                                             "double",
                                                             true );
            FieldDefinition intDef = new FieldDefinition( "intAttr",
                                                          "int",
                                                          true );
            FieldDefinition strDef = new FieldDefinition( "stringAttr",
                                                          "java.lang.String",
                                                          true );
            FieldDefinition dateDef = new FieldDefinition( "dateAttr",
                                                           "java.util.Date",
                                                           true );
            FieldDefinition str2Def = new FieldDefinition( "stringAttr2",
                                                           "java.lang.String" );
            classDef.addField( long1Def );
            classDef.addField( long2Def );
            classDef.addField( doubleDef );
            classDef.addField( intDef );
            classDef.addField( strDef );
            classDef.addField( dateDef );
            classDef.addField( str2Def );

            Class clazz = builder.buildAndLoadClass( classDef );
            long1Def.setReadWriteAccessor( store.getAccessor( clazz,
                                                          long1Def.getName(),
                                                          classLoader ) );
            long2Def.setReadWriteAccessor( store.getAccessor( clazz,
                                                          long2Def.getName(),
                                                          classLoader ) );
            doubleDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                           doubleDef.getName(),
                                                           classLoader ) );
            intDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                        intDef.getName(),
                                                        classLoader ) );
            strDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                        strDef.getName(),
                                                        classLoader ) );
            dateDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                         dateDef.getName(),
                                                         classLoader ) );
            str2Def.setReadWriteAccessor( store.getAccessor( clazz,
                                                         str2Def.getName(),
                                                         classLoader ) );

            Object x = clazz.newInstance();
            Object y = clazz.newInstance();

            long1Def.setValue( x,
                               new Long( 20 ) );
            long2Def.setValue( x,
                               new Long( 30 ) );
            doubleDef.setValue( x,
                                new Double( 50.0 ) );
            intDef.setValue( x,
                             new Integer( 10 ) );
            strDef.setValue( x,
                             "abc" );
            dateDef.setValue( x,
                              new Date( 1000 ) );
            str2Def.setValue( x,
                              "instance1" );

            long1Def.setValue( y,
                               new Long( 20 ) );
            long2Def.setValue( y,
                               new Long( 30 ) );
            doubleDef.setValue( y,
                                new Double( 50.0 ) );
            intDef.setValue( y,
                             new Integer( 10 ) );
            strDef.setValue( y,
                             "abc" );
            dateDef.setValue( y,
                              new Date( 1000 ) );
            str2Def.setValue( y,
                              "instance2" );

            Object o = new Object();

            Assert.assertTrue( x.equals( x ) );
            Assert.assertFalse( x.equals( null ) );
            Assert.assertFalse( x.equals( o ) );

            Assert.assertTrue( x.equals( y ) );

            intDef.setValue( y,
                             new Integer( 1 ) );
            Assert.assertFalse( x.equals( y ) );

            intDef.setValue( y,
                             new Integer( 10 ) );
            strDef.setValue( y,
                             "xyz" );
            Assert.assertFalse( x.equals( y ) );

            strDef.setValue( y,
                             null );
            Assert.assertFalse( x.equals( y ) );

            strDef.setValue( y,
                             "abc" );
            dateDef.setValue( y,
                              new Date( 1 ) );
            Assert.assertFalse( x.equals( y ) );

            dateDef.setValue( y,
                              null );
            Assert.assertFalse( x.equals( y ) );

        } catch ( Exception e ) {
            e.printStackTrace();
            Assert.fail( "Exception not expected" );
        }
    }

    public void testHashCode() {
        try {
            ClassBuilder builder = new ClassBuilder();

            ClassDefinition classDef = new ClassDefinition( "org.drools.TestClass3",
                                                            null,
                                                            new String[]{} );
            FieldDefinition intDef = new FieldDefinition( "intAttr",
                                                          "int",
                                                          true );
            FieldDefinition strDef = new FieldDefinition( "stringAttr",
                                                          "java.lang.String",
                                                          false );
            classDef.addField( intDef );
            classDef.addField( strDef );

            Class clazz = builder.buildAndLoadClass( classDef );
            intDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                        intDef.getName(),
                                                        classLoader ) );
            strDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                        strDef.getName(),
                                                        classLoader ) );

            Object x = clazz.newInstance();

            intDef.setValue( x,
                             new Integer( 10 ) );
            strDef.setValue( x,
                             "abc" );

            Assert.assertEquals( "Wrong hashcode calculation",
                                 31 + 10,
                                 x.hashCode() );
            Assert.assertEquals( "Wrong hashcode calculation",
                                 x.hashCode(),
                                 x.hashCode() );

        } catch ( Exception e ) {
            e.printStackTrace();
            Assert.fail( "Exception not expected" );
        }
    }

    public void testToString() {
        try {
            ClassBuilder builder = new ClassBuilder();

            ClassDefinition classDef = new ClassDefinition( "org.drools.TestClass4",
                                                            null,
                                                            new String[]{} );
            FieldDefinition long1Def = new FieldDefinition( "longAttr1",
                                                            "long",
                                                            true );
            FieldDefinition long2Def = new FieldDefinition( "longAttr2",
                                                            "long",
                                                            true );
            FieldDefinition doubleDef = new FieldDefinition( "doubleAttr",
                                                             "double",
                                                             true );
            FieldDefinition intDef = new FieldDefinition( "intAttr",
                                                          "int",
                                                          true );
            FieldDefinition strDef = new FieldDefinition( "stringAttr",
                                                          "java.lang.String",
                                                          true );
            FieldDefinition dateDef = new FieldDefinition( "dateAttr",
                                                           "java.util.Date",
                                                           true );
            FieldDefinition str2Def = new FieldDefinition( "stringAttr2",
                                                           "java.lang.String" );
            classDef.addField( long1Def );
            classDef.addField( long2Def );
            classDef.addField( doubleDef );
            classDef.addField( intDef );
            classDef.addField( strDef );
            classDef.addField( dateDef );
            classDef.addField( str2Def );

            Class clazz = builder.buildAndLoadClass( classDef );
            long1Def.setReadWriteAccessor( store.getAccessor( clazz,
                                                          long1Def.getName(),
                                                          classLoader ) );
            long2Def.setReadWriteAccessor( store.getAccessor( clazz,
                                                          long2Def.getName(),
                                                          classLoader ) );
            doubleDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                           doubleDef.getName(),
                                                           classLoader ) );
            intDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                        intDef.getName(),
                                                        classLoader ) );
            strDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                        strDef.getName(),
                                                        classLoader ) );
            dateDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                         dateDef.getName(),
                                                         classLoader ) );
            str2Def.setReadWriteAccessor( store.getAccessor( clazz,
                                                         str2Def.getName(),
                                                         classLoader ) );

            Object x = clazz.newInstance();

            long1Def.setValue( x,
                               new Long( 20 ) );
            long2Def.setValue( x,
                               new Long( 30 ) );
            doubleDef.setValue( x,
                                new Double( 50.0 ) );
            intDef.setValue( x,
                             new Integer( 10 ) );
            strDef.setValue( x,
                             "abc" );
            dateDef.setValue( x,
                              new Date( 1000 ) );
            str2Def.setValue( x,
                              "instance1" );

            String result = x.toString();

            Assert.assertTrue( result.contains( long1Def.getName() ) );
            Assert.assertTrue( result.contains( long2Def.getName() ) );
            Assert.assertTrue( result.contains( doubleDef.getName() ) );
            Assert.assertTrue( result.contains( intDef.getName() ) );
            Assert.assertTrue( result.contains( strDef.getName() ) );
            Assert.assertTrue( result.contains( dateDef.getName() ) );
            Assert.assertTrue( result.contains( str2Def.getName() ) );

        } catch ( Exception e ) {
            e.printStackTrace();
            Assert.fail( "Exception not expected" );
        }

    }

}
