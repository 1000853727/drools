/**
 * 
 */
package org.codehaus.jfdi.interpreter;

import java.util.HashSet;

import junit.framework.TestCase;

import org.drools.Cheese;
import org.drools.FirstClass;
import org.drools.SecondClass;
import org.drools.base.ClassTypeResolver;

/**
 * @author fburlet
 *
 */
public class ClassTypeResolverTest extends TestCase {

    public void testResolvePrimtiveTypes() throws Exception {
        final ClassTypeResolver resolver = new ClassTypeResolver(new HashSet(), Thread.currentThread().getContextClassLoader());
        assertEquals( boolean.class,
                      resolver.resolveType( "boolean" ) );
        assertEquals( double.class,
                      resolver.resolveType( "double" ) );
        assertEquals( float.class,
                      resolver.resolveType( "float" ) );
        assertEquals( int.class,
                      resolver.resolveType( "int" ) );
        assertEquals( char.class,
                      resolver.resolveType( "char" ) );
        assertEquals( long.class,
                      resolver.resolveType( "long" ) );
        assertEquals( byte.class,
                      resolver.resolveType( "byte" ) );
        assertEquals( short.class,
                      resolver.resolveType( "short" ) );
    }

    public void testResolveArrayOfPrimitiveTypes() throws Exception {
        final ClassTypeResolver resolver =  new ClassTypeResolver(new HashSet(), Thread.currentThread().getContextClassLoader());
        assertEquals( boolean[].class,
                      resolver.resolveType( "boolean[]" ) );
        assertEquals( double[].class,
                      resolver.resolveType( "double[]" ) );
        assertEquals( float[].class,
                      resolver.resolveType( "float[]" ) );
        assertEquals( int[].class,
                      resolver.resolveType( "int[]" ) );
        assertEquals( char[].class,
                      resolver.resolveType( "char[]" ) );
        assertEquals( long[].class,
                      resolver.resolveType( "long[]" ) );
        assertEquals( byte[].class,
                      resolver.resolveType( "byte[]" ) );
        assertEquals( short[].class,
                      resolver.resolveType( "short[]" ) );
    }

    public void testResolveMultidimensionnalArrayOfPrimitiveTypes() throws Exception {
        final ClassTypeResolver resolver =  new ClassTypeResolver(new HashSet(), Thread.currentThread().getContextClassLoader());
        assertEquals( int[][].class,
                      resolver.resolveType( "int[][]" ) );
        assertEquals( int[][][].class,
                      resolver.resolveType( "int[][][]" ) );
        assertEquals( int[][][][].class,
                      resolver.resolveType( "int[][][][]" ) );
    }

    public void testResolveObjectNotFromImport() throws Exception {
        final ClassTypeResolver resolver =  new ClassTypeResolver(new HashSet(), Thread.currentThread().getContextClassLoader());
        assertEquals( String.class,
                      resolver.resolveType( "String" ) );
        assertEquals( String.class,
                      resolver.resolveType( "java.lang.String" ) );
        try {
            assertEquals( Cheese.class,
                          resolver.resolveType( "Cheese" ) );
            fail( "Should raise a ClassNotFoundException" );
        } catch ( final ClassNotFoundException e ) {
            // success
        }
        assertEquals( Cheese.class,
                      resolver.resolveType( "org.drools.Cheese" ) );
    }

    public void testResolveObjectFromImport() throws Exception {
        final ClassTypeResolver resolver = new ClassTypeResolver( new HashSet(), Thread.currentThread().getContextClassLoader() );
        resolver.addImport( "org.drools.Cheese" );
        resolver.addImport( "org.drools.FirstClass" );
        resolver.addImport( "org.drools.FirstClass.AlternativeKey" );
        resolver.addImport( "org.drools.SecondClass" );
        resolver.addImport( "org.drools.SecondClass.AlternativeKey" );        
        
        assertEquals( String.class,
                      resolver.resolveType( "String" ) );
        assertEquals( String.class,
                      resolver.resolveType( "java.lang.String" ) );
        assertEquals( Cheese.class,
                      resolver.resolveType( "Cheese" ) );
        assertEquals( Cheese.class,
                      resolver.resolveType( "org.drools.Cheese" ) );
        assertEquals( FirstClass.class,
                      resolver.resolveType( "org.drools.FirstClass" ) );
        assertEquals( FirstClass.AlternativeKey.class,
                      resolver.resolveType( "org.drools.FirstClass.AlternativeKey" ) );

        assertEquals( SecondClass.class,
                      resolver.resolveType( "org.drools.SecondClass" ) );
        assertEquals( SecondClass.AlternativeKey.class,
                      resolver.resolveType( "org.drools.SecondClass.AlternativeKey" ) );
    }
    
    public void testResolveObjectFromImportMultipleClassesDifferentPackages() throws Exception {
        final ClassTypeResolver resolver = new ClassTypeResolver( new HashSet(), Thread.currentThread().getContextClassLoader() );
        resolver.addImport( "org.drools.Cheese" );
        assertEquals( String.class,
                      resolver.resolveType( "String" ) );
        assertEquals( String.class,
                      resolver.resolveType( "java.lang.String" ) );
        assertEquals( Cheese.class,
                      resolver.resolveType( "Cheese" ) );
        assertEquals( Cheese.class,
                      resolver.resolveType( "org.drools.Cheese" ) );
    }

    public void testResolveArrayOfObjectsNotFromImport() throws Exception {
        final ClassTypeResolver resolver =  new ClassTypeResolver(new HashSet(), Thread.currentThread().getContextClassLoader());
        assertEquals( String[].class,
                      resolver.resolveType( "String[]" ) );
        assertEquals( String[].class,
                      resolver.resolveType( "java.lang.String[]" ) );
        try {
            assertEquals( Cheese[].class,
                          resolver.resolveType( "Cheese[]" ) );
            fail( "Should raise a ClassNotFoundException" );
        } catch ( final ClassNotFoundException e ) {
            // success
        }
        assertEquals( Cheese[].class,
                      resolver.resolveType( "org.drools.Cheese[]" ) );
    }

    public void testResolveArrayOfObjectsFromImport() throws Exception {
        final ClassTypeResolver resolver = new ClassTypeResolver( new HashSet(), Thread.currentThread().getContextClassLoader() );
        resolver.addImport( "org.drools.Cheese" );
        assertEquals( String[].class,
                      resolver.resolveType( "String[]" ) );
        assertEquals( String[].class,
                      resolver.resolveType( "java.lang.String[]" ) );
        assertEquals( Cheese[].class,
                      resolver.resolveType( "Cheese[]" ) );
        assertEquals( Cheese[].class,
                      resolver.resolveType( "org.drools.Cheese[]" ) );
    }

    public void testResolveMultidimensionnalArrayOfObjectsNotFromImport() throws Exception {
        final ClassTypeResolver resolver =  new ClassTypeResolver(new HashSet(), Thread.currentThread().getContextClassLoader());
        assertEquals( String[][].class,
                      resolver.resolveType( "String[][]" ) );
        assertEquals( String[][].class,
                      resolver.resolveType( "java.lang.String[][]" ) );
        try {
            assertEquals( Cheese[][].class,
                          resolver.resolveType( "Cheese[][]" ) );
            fail( "Should raise a ClassNotFoundException" );
        } catch ( final ClassNotFoundException e ) {
            // success
        }
        assertEquals( Cheese[][].class,
                      resolver.resolveType( "org.drools.Cheese[][]" ) );
    }

    public void testResolveMultidimensionnalArrayOfObjectsFromImport() throws Exception {
        final ClassTypeResolver resolver = new ClassTypeResolver( new HashSet(), Thread.currentThread().getContextClassLoader() );
        resolver.addImport( "org.drools.Cheese" );
        assertEquals( String[][].class,
                      resolver.resolveType( "String[][]" ) );
        assertEquals( String[][].class,
                      resolver.resolveType( "java.lang.String[][]" ) );
        assertEquals( Cheese[][].class,
                      resolver.resolveType( "Cheese[][]" ) );
        assertEquals( Cheese[][].class,
                      resolver.resolveType( "org.drools.Cheese[][]" ) );
    }
}
