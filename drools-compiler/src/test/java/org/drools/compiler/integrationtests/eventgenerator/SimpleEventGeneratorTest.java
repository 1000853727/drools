package org.drools.compiler.integrationtests.eventgenerator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.core.RuleBase;
import org.drools.core.WorkingMemory;
import org.junit.Test;

import org.drools.compiler.CommonTestMethodBase;
import org.drools.compiler.integrationtests.eventgenerator.Event.EventType;


public class SimpleEventGeneratorTest extends CommonTestMethodBase {

    private final static String TEST_RULE_FILE = "test_eventGenerator.drl";

    @Test
    public void testEventGenerationMaxItems() throws DroolsParserException, IOException, Exception{
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( TEST_RULE_FILE ) );
        final RuleBase ruleBase = loadRuleBase( reader );
        final WorkingMemory wm = ruleBase.newStatefulSession();
        final SimpleEventGenerator myGenerator;

        // create unrestricted event generator
        myGenerator = new SimpleEventGenerator(wm , new SimpleEventListener(wm));
        // generate 10 events, starting from the session clock
        myGenerator.addEventSource("Conveyor1", new Event(EventType.CUSTOM, null), PseudoSessionClock.timeInSeconds(4), PseudoSessionClock.timeInSeconds(6), 0, 10);
        myGenerator.generate();
        assertEquals(wm.getQueryResults("all inserted events").size(), 10);
    }

    @Test
    public void testEventGenerationMaxTime() throws DroolsParserException, IOException, Exception{
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( TEST_RULE_FILE ) );
        final RuleBase ruleBase = loadRuleBase( reader );
        final WorkingMemory wm = ruleBase.newStatefulSession();
        final SimpleEventGenerator myGenerator;

        // create unrestricted event generator
        myGenerator = new SimpleEventGenerator(wm , new SimpleEventListener(wm));
        // generate events for 1 min, starting from the session clock
        myGenerator.addEventSource("Conveyor1", new Event(EventType.CUSTOM, null), PseudoSessionClock.timeInSeconds(4), PseudoSessionClock.timeInSeconds(6), PseudoSessionClock.timeInMinutes(1), 0);
        myGenerator.generate();
        assertEquals(wm.getQueryResults("all inserted events").size(), wm.getQueryResults("all inserted events with generation time < 1 min").size());
    }

    @Test
    public void testEventGenerationMaxTimeAndMaxItems() throws DroolsParserException, IOException, Exception{
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( TEST_RULE_FILE ) );
        final RuleBase ruleBase = loadRuleBase( reader );
        final WorkingMemory wm = ruleBase.newStatefulSession();
        final SimpleEventGenerator myGenerator;

        // create unrestricted event generator
        myGenerator = new SimpleEventGenerator(wm , new SimpleEventListener(wm));
        // generate at most 10 events not exceeding 1 min, starting from the session clock
        myGenerator.addEventSource("Conveyor1", new Event(EventType.CUSTOM, null), PseudoSessionClock.timeInSeconds(4), PseudoSessionClock.timeInSeconds(6), PseudoSessionClock.timeInMinutes(1), 10);
        myGenerator.generate();
        assertEquals(wm.getQueryResults("all inserted events").size(), wm.getQueryResults("all inserted events with generation time < 1 min").size());
        assertTrue(wm.getQueryResults("all inserted events with generation time < 1 min").size()<=10);
    }

    @Test
    public void testEventGenerationDelayedMaxItems() throws DroolsParserException, IOException, Exception{
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( TEST_RULE_FILE ) );
        final RuleBase ruleBase = loadRuleBase( reader );
        final WorkingMemory wm = ruleBase.newStatefulSession();
        final SimpleEventGenerator myGenerator;

        // create unrestricted event generator
        myGenerator = new SimpleEventGenerator(wm , new SimpleEventListener(wm));
        // generate 10 events, delayed by 2 minutes from start session clock
        myGenerator.addDelayedEventSource("Conveyor1", new Event(EventType.CUSTOM, null), PseudoSessionClock.timeInSeconds(4), PseudoSessionClock.timeInSeconds(6), PseudoSessionClock.timeInMinutes(2), 0, 10);
        myGenerator.generate();
        assertEquals(wm.getQueryResults("all inserted events").size(), 10);
    }

    @Test
    public void testEventGenerationDelayedMaxTime() throws DroolsParserException, IOException, Exception{
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( TEST_RULE_FILE ) );
        final RuleBase ruleBase = loadRuleBase( reader );
        final WorkingMemory wm = ruleBase.newStatefulSession();
        final SimpleEventGenerator myGenerator;

        // create unrestricted event generator
        myGenerator = new SimpleEventGenerator(wm , new SimpleEventListener(wm));
        // generate events for 1 min, delayed by 2 minutes from start session clock
        myGenerator.addDelayedEventSource("Conveyor1", new Event(EventType.CUSTOM, null), PseudoSessionClock.timeInSeconds(4), PseudoSessionClock.timeInSeconds(6), PseudoSessionClock.timeInMinutes(2), PseudoSessionClock.timeInMinutes(1), 0);
        myGenerator.generate();
        assertEquals(wm.getQueryResults("all inserted events").size(), wm.getQueryResults("all inserted events with 2 min < generation time < 3 min").size());
    }

    @Test
    public void testEventGenerationDelayedMaxTimeAndMaxItems() throws DroolsParserException, IOException, Exception{
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( TEST_RULE_FILE ) );
        final RuleBase ruleBase = loadRuleBase( reader );
        final WorkingMemory wm = ruleBase.newStatefulSession();
        final SimpleEventGenerator myGenerator;

        // create unrestricted event generator
        myGenerator = new SimpleEventGenerator(wm , new SimpleEventListener(wm));
        // generate at most 10 events not exceeding 1 min, delayed by 2 minutes from start session clock
        myGenerator.addDelayedEventSource("Conveyor1", new Event(EventType.CUSTOM, null), PseudoSessionClock.timeInSeconds(4), PseudoSessionClock.timeInSeconds(6), PseudoSessionClock.timeInMinutes(2), PseudoSessionClock.timeInMinutes(1), 10);
        myGenerator.generate();
        assertEquals(wm.getQueryResults("all inserted events").size(), wm.getQueryResults("all inserted events with 2 min < generation time < 3 min").size());
        assertTrue(wm.getQueryResults("all inserted events with 2 min < generation time < 3 min").size()<=10);
    }

    @Test
    public void testEventGenerationGlobalMaxTime() throws DroolsParserException, IOException, Exception{
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( TEST_RULE_FILE ) );
        final RuleBase ruleBase = loadRuleBase( reader );
        final WorkingMemory wm = ruleBase.newStatefulSession();
        final SimpleEventGenerator myGenerator;

        // create unrestricted event generator
        myGenerator = new SimpleEventGenerator(wm , new SimpleEventListener(wm), PseudoSessionClock.timeInMinutes(1));
        // generate events for 1 min, starting from the session clock
        myGenerator.addEventSource("Conveyor1", new Event(EventType.CUSTOM, null), PseudoSessionClock.timeInSeconds(4), PseudoSessionClock.timeInSeconds(6), PseudoSessionClock.timeInMinutes(3), 0);
        myGenerator.generate();
        assertEquals(wm.getQueryResults("all inserted events").size(), wm.getQueryResults("all inserted events with generation time < 1 min").size());
    }

    @Test
    public void testEventGenerationMultipleSources() throws DroolsParserException, IOException, Exception{
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( TEST_RULE_FILE ) );
        final RuleBase ruleBase = loadRuleBase( reader );
        final WorkingMemory wm = ruleBase.newStatefulSession();
        final SimpleEventGenerator myGenerator;

        // create unrestricted event generator
        myGenerator = new SimpleEventGenerator(wm , new SimpleEventListener(wm));
        // generate 15 events with parent resource A and 20 events with parent resource B
        myGenerator.addEventSource("Conveyor1", new Event(EventType.CUSTOM, "resA"), PseudoSessionClock.timeInSeconds(4), PseudoSessionClock.timeInSeconds(6), 0, 15);
        myGenerator.addEventSource("Conveyor2", new Event(EventType.CUSTOM, "resB"), PseudoSessionClock.timeInSeconds(3), PseudoSessionClock.timeInSeconds(5), 0, 20);
        myGenerator.generate();
        assertEquals(wm.getQueryResults("all inserted events with parent resource A").size(), 15);
        assertEquals(wm.getQueryResults("all inserted events with parent resource B").size(), 20);
    }

}
