package org.drools.integrationtests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.drools.compiler.PackageBuilderConfiguration;
import org.drools.lang.descr.FunctionDescr;
import org.drools.lang.descr.ImportDescr;
import org.drools.lang.descr.PackageDescr;
import org.drools.rule.builder.dialect.java.JavaDialectConfiguration;

public class PackageBuilderThreadSafetyTest extends TestCase {

    private static final int _NUMBER_OF_THREADS = 100;
    private static final int _SLEEP_TIME_MS     = 100;

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testDummy() {
    }

    public void testThreadSafetyEclipse() {
        execute( JavaDialectConfiguration.ECLIPSE );        
    }

    public void testThreadSafetyJanino() {
        execute( JavaDialectConfiguration.JANINO  );
    }

    public void execute(int compiler) {
        final PackageBuilderConfiguration packageBuilderConfig = new PackageBuilderConfiguration();
        ((JavaDialectConfiguration) packageBuilderConfig.getDialectConfiguration( "java" )).setCompiler( compiler );        	
        
        final List errors = new ArrayList();
        final List exceptions = new ArrayList();
        Thread[] threads = new Thread[_NUMBER_OF_THREADS];
        for ( int i = 0; i < _NUMBER_OF_THREADS; i++ ) {
            final int ID = i;
            Thread testThread = new Thread() {
                public void run() {
                    try {
                        this.setName( "Thread[" + ID + "]" );
                        org.drools.compiler.PackageBuilder builder = null;
                        try {
                            builder = new org.drools.compiler.PackageBuilder( packageBuilderConfig );
                        } catch ( Throwable t ) {
                            t.printStackTrace();
                            throw new RuntimeException( t );
                        }
                        PackageDescr packageDescr = new PackageDescr( "MyRulebase" );
                        addImports( packageDescr );
                        addFunctions( packageDescr );
                        // added some arbitrary sleep statements to encourage 
                        // context switching and hope this provokes exceptions 
                        sleep( _SLEEP_TIME_MS );
                        builder.addPackage( packageDescr );
                        sleep( _SLEEP_TIME_MS );
                        builder.getPackage();
                        sleep( _SLEEP_TIME_MS );
                        if ( builder.hasErrors() ) {
                            System.out.println( "ERROR in thread: " + ID );
                            System.out.println( builder.getErrors().toString() );
                            errors.add( builder.getErrors() );
                        }
                    } catch ( Exception e ) {
                        e.printStackTrace();
                        exceptions.add( e );
                    }
                }
            };
            threads[i] = testThread;
            try {
                testThread.start();
            } catch ( Exception e ) {
                assertTrue( false );
            }
        }
        for ( int i = 0; i < _NUMBER_OF_THREADS; i++ ) {
            try {
                threads[i].join();
            } catch ( InterruptedException e ) {
                threads[i].interrupt();
            }
        }
        assertTrue( "Exceptions during package compilation (number=" + exceptions.size() + ")",
                    exceptions.isEmpty() );
        assertTrue( "PackageBuilderErrors during package compilation (number=" + errors.size() + ")",
                    errors.isEmpty() );
    }

    private static void addImports(PackageDescr packageDescr) {
        packageDescr.addImport( new ImportDescr( "java.util.List" ) );
        packageDescr.addImport( new ImportDescr( "java.util.ArrayList" ) );
        packageDescr.addImport( new ImportDescr( "java.util.LinkedList" ) );
        packageDescr.addImport( new ImportDescr( "java.util.Set" ) );
        packageDescr.addImport( new ImportDescr( "java.util.HashSet" ) );
        packageDescr.addImport( new ImportDescr( "java.util.SortedSet" ) );
        packageDescr.addImport( new ImportDescr( "java.util.TreeSet" ) );
    }

    private static void addFunctions(PackageDescr packageDescr) {
        FunctionDescr functionDescr = new FunctionDescr( "foo",
                                                         "void" );
        functionDescr.addParameter( "String",
                                    "arg1" );
        String body = "Set myHashSet = new HashSet();" + "myHashSet.add(arg1);" + "List myArrayList = new ArrayList();" + "myArrayList.add(arg1);" + "List myLinkedList = new LinkedList();" + "myLinkedList.add(arg1);"
                      + "Set myTreeSet = new TreeSet();" + "myTreeSet.add(arg1);";
        functionDescr.setText( body );
        packageDescr.addFunction( functionDescr );
    }

}
