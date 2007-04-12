package org.drools.rule;

import java.util.Calendar;

import junit.framework.TestCase;

/**
 * @author Michael Neale
 */
public class RuleTest extends TestCase {

    public void testDateEffective() {
        final Rule rule = new Rule( "myrule" );

        assertTrue( rule.isEffective() );

        final Calendar earlier = Calendar.getInstance();
        earlier.setTimeInMillis( 10 );

        rule.setDateEffective( earlier );

        assertTrue( rule.isEffective() );

        final Calendar later = Calendar.getInstance();
        later.setTimeInMillis( later.getTimeInMillis() + 100000000 );

        assertTrue( later.after( Calendar.getInstance() ) );

        rule.setDateEffective( later );
        assertFalse( rule.isEffective() );

    }

    public void testDateExpires() throws Exception {
        final Rule rule = new Rule( "myrule" );

        assertTrue( rule.isEffective() );

        final Calendar earlier = Calendar.getInstance();
        earlier.setTimeInMillis( 10 );

        rule.setDateExpires( earlier );

        assertFalse( rule.isEffective() );

        final Calendar later = Calendar.getInstance();
        later.setTimeInMillis( later.getTimeInMillis() + 100000000 );

        rule.setDateExpires( later );
        assertTrue( rule.isEffective() );

    }

    public void testDateEffectiveExpires() {
        final Rule rule = new Rule( "myrule" );

        final Calendar past = Calendar.getInstance();
        past.setTimeInMillis( 10 );

        final Calendar future = Calendar.getInstance();
        future.setTimeInMillis( future.getTimeInMillis() + 100000000 );

        rule.setDateEffective( past );
        rule.setDateExpires( future );

        assertTrue( rule.isEffective() );

        rule.setDateExpires( past );
        assertFalse( rule.isEffective() );

        rule.setDateExpires( future );
        rule.setDateEffective( future );

        assertFalse( rule.isEffective() );

    }

    public void testRuleEnabled() {
        final Rule rule = new Rule( "myrule" );
        rule.setEnabled( false );
        assertFalse( rule.isEffective() );

        final Calendar past = Calendar.getInstance();
        past.setTimeInMillis( 10 );

        rule.setDateEffective( past );
        assertFalse( rule.isEffective() );
        rule.setEnabled( true );

        assertTrue( rule.isEffective() );
    }

}
