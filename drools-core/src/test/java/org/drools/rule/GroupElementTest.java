package org.drools.rule;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.drools.RuntimeDroolsException;

public class GroupElementTest extends TestCase {

    public void testPackNestedAnd() {
        final GroupElement and1 = GroupElementFactory.newAndInstance();
        final Pattern pattern1 = new Pattern( 0,
                                     null );
        and1.addChild( pattern1 );

        final Pattern pattern2 = new Pattern( 0,
                                     null );
        and1.addChild( pattern2 );

        assertEquals( 2,
                      and1.getChildren().size() );
        assertSame( pattern1,
                    and1.getChildren().get( 0 ) );
        assertSame( pattern2,
                    and1.getChildren().get( 1 ) );

        final GroupElement and2 = GroupElementFactory.newAndInstance();
        and2.addChild( and1 );

        and2.pack();
        assertEquals( 2,
                      and2.getChildren().size() );
        assertSame( pattern1,
                    and2.getChildren().get( 0 ) );
        assertSame( pattern2,
                    and2.getChildren().get( 1 ) );
    }

    public void testPackNestedOr() {
        final GroupElement or1 = GroupElementFactory.newOrInstance();
        final Pattern pattern1 = new Pattern( 0,
                                     null );
        or1.addChild( pattern1 );

        final Pattern pattern2 = new Pattern( 0,
                                     null );
        or1.addChild( pattern2 );

        assertEquals( 2,
                      or1.getChildren().size() );
        assertSame( pattern1,
                    or1.getChildren().get( 0 ) );
        assertSame( pattern2,
                    or1.getChildren().get( 1 ) );

        final GroupElement or2 = GroupElementFactory.newOrInstance();
        or2.addChild( or1 );

        or2.pack();

        assertEquals( 2,
                      or2.getChildren().size() );
        assertSame( pattern1,
                    or2.getChildren().get( 0 ) );
        assertSame( pattern2,
                    or2.getChildren().get( 1 ) );
    }

    public void testPackNestedExists() {
        final GroupElement exists1 = GroupElementFactory.newExistsInstance();
        final Pattern pattern1 = new Pattern( 0,
                                     null );
        exists1.addChild( pattern1 );

        assertEquals( 1,
                      exists1.getChildren().size() );
        assertSame( pattern1,
                    exists1.getChildren().get( 0 ) );

        final GroupElement exists2 = GroupElementFactory.newExistsInstance();
        exists2.addChild( exists1 );

        exists2.pack();

        assertEquals( 1,
                      exists2.getChildren().size() );
        assertSame( pattern1,
                    exists2.getChildren().get( 0 ) );
    }

    public void testAddMultipleChildsIntoNot() {
        final GroupElement not = GroupElementFactory.newNotInstance();

        final Pattern pattern1 = new Pattern( 0,
                                     null );
        try {
            not.addChild( pattern1 );
        } catch ( final RuntimeDroolsException rde ) {
            Assert.fail( "Adding a single child is not supposed to throw Exception for NOT GE: " + rde.getMessage() );
        }

        final Pattern pattern2 = new Pattern( 0,
                                     null );
        try {
            not.addChild( pattern2 );
            Assert.fail( "Adding a second child into a NOT GE should throw Exception" );
        } catch ( final RuntimeDroolsException rde ) {
            // everything is fine
        }
    }

    public void testAddSingleBranchAnd() {
        final GroupElement and1 = GroupElementFactory.newAndInstance();
        final Pattern pattern = new Pattern( 0,
                                    null );
        and1.addChild( pattern );
        assertEquals( 1,
                      and1.getChildren().size() );
        assertSame( pattern,
                    and1.getChildren().get( 0 ) );

        final GroupElement or1 = GroupElementFactory.newOrInstance();
        or1.addChild( and1 );

        or1.pack();
        assertEquals( 1,
                      or1.getChildren().size() );
        assertSame( pattern,
                    or1.getChildren().get( 0 ) );
    }

    public void testAddSingleBranchOr() {
        final GroupElement or1 = GroupElementFactory.newOrInstance();
        final Pattern pattern = new Pattern( 0,
                                    null );
        or1.addChild( pattern );
        assertEquals( 1,
                      or1.getChildren().size() );
        assertSame( pattern,
                    or1.getChildren().get( 0 ) );

        final GroupElement and1 = GroupElementFactory.newAndInstance();
        and1.addChild( or1 );

        and1.pack();
        assertEquals( 1,
                      and1.getChildren().size() );
        assertSame( pattern,
                    and1.getChildren().get( 0 ) );
    }

    /**
     * This test tests deep nested structures, and shall transform this:
     * 
     *    AND2
     *     |
     *    OR3
     *     |
     *    OR2
     *     |
     *    AND1
     *     |
     *    OR1
     *    / \
     *   C1  C2
     *   
     * Into this:
     * 
     *   OR1
     *   / \
     *  C1 C2
     *
     */
    public void testDeepNestedStructure() {
        final GroupElement or1 = GroupElementFactory.newOrInstance();
        final Pattern pattern1 = new Pattern( 0,
                                     null );
        or1.addChild( pattern1 );

        final Pattern pattern2 = new Pattern( 0,
                                     null );
        or1.addChild( pattern2 );

        final GroupElement and1 = GroupElementFactory.newAndInstance();
        and1.addChild( or1 );
        assertEquals( 1,
                      and1.getChildren().size() );
        assertSame( or1,
                    and1.getChildren().get( 0 ) );

        assertSame( pattern1,
                    or1.getChildren().get( 0 ) );
        assertSame( pattern2,
                    or1.getChildren().get( 1 ) );

        final GroupElement or2 = GroupElementFactory.newOrInstance();
        or2.addChild( and1 );

        assertEquals( 1,
                      or2.getChildren().size() );
        assertSame( and1,
                    or2.getChildren().get( 0 ) );

        final GroupElement or3 = GroupElementFactory.newOrInstance();
        or3.addChild( or2 );

        assertEquals( 1,
                      or2.getChildren().size() );
        assertSame( or2,
                    or3.getChildren().get( 0 ) );

        final GroupElement and2 = GroupElementFactory.newAndInstance();
        and2.addChild( or3 );

        assertEquals( 1,
                      and2.getChildren().size() );
        assertSame( or3,
                    and2.getChildren().get( 0 ) );

        // Now pack the structure
        and2.pack();

        // and2 now is in fact transformed into an OR
        assertEquals( GroupElement.Type.OR,
                      and2.getType() );

        assertEquals( 2,
                      and2.getChildren().size() );

        assertSame( pattern1,
                    and2.getChildren().get( 0 ) );
        assertSame( pattern2,
                    and2.getChildren().get( 1 ) );

    }

    /**
     * This test tests deep nested structures, and shall transform this:
     * 
     *      AND2
     *      / \ 
     *    OR3  C3
     *     |
     *    OR2
     *     |
     *    AND1
     *     |
     *    OR1
     *    / \
     *   C1  C2
     *   
     * Into this:
     * 
     *     AND2
     *     /  \
     *   OR1  C3
     *   / \
     *  C1 C2
     *
     */
    public void testDeepNestedStructureWithMultipleElementsInRoot() {
        final GroupElement or1 = GroupElementFactory.newOrInstance();
        final Pattern pattern1 = new Pattern( 0,
                                     null );
        or1.addChild( pattern1 );

        final Pattern pattern2 = new Pattern( 0,
                                     null );
        or1.addChild( pattern2 );

        final GroupElement and1 = GroupElementFactory.newAndInstance();
        and1.addChild( or1 );
        assertEquals( 1,
                      and1.getChildren().size() );
        assertSame( or1,
                    and1.getChildren().get( 0 ) );

        assertSame( pattern1,
                    or1.getChildren().get( 0 ) );
        assertSame( pattern2,
                    or1.getChildren().get( 1 ) );

        final GroupElement or2 = GroupElementFactory.newOrInstance();
        or2.addChild( and1 );

        assertEquals( 1,
                      or2.getChildren().size() );
        assertSame( and1,
                    or2.getChildren().get( 0 ) );

        final GroupElement or3 = GroupElementFactory.newOrInstance();
        or3.addChild( or2 );

        assertEquals( 1,
                      or2.getChildren().size() );
        assertSame( or2,
                    or3.getChildren().get( 0 ) );

        final GroupElement and2 = GroupElementFactory.newAndInstance();
        and2.addChild( or3 );

        final Pattern pattern3 = new Pattern( 0,
                                     null );
        and2.addChild( pattern3 );

        assertEquals( 2,
                      and2.getChildren().size() );
        assertSame( or3,
                    and2.getChildren().get( 0 ) );
        assertSame( pattern3,
                    and2.getChildren().get( 1 ) );

        // Now pack the structure
        and2.pack();

        // and2 now is in fact transformed into an OR
        assertTrue( and2.isAnd() );

        assertEquals( 2,
                      and2.getChildren().size() );

        // order must be the same
        assertSame( or1,
                    and2.getChildren().get( 0 ) );
        assertSame( pattern3,
                    and2.getChildren().get( 1 ) );

        assertEquals( 2,
                      or1.getChildren().size() );
        assertSame( pattern1,
                    or1.getChildren().get( 0 ) );
        assertSame( pattern2,
                    or1.getChildren().get( 1 ) );

    }

}
