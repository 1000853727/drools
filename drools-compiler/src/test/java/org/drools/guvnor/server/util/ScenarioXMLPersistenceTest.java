package org.drools.guvnor.server.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.drools.guvnor.client.modeldriven.testing.ExecutionTrace;
import org.drools.guvnor.client.modeldriven.testing.FactData;
import org.drools.guvnor.client.modeldriven.testing.FieldData;
import org.drools.guvnor.client.modeldriven.testing.Scenario;
import org.drools.guvnor.client.modeldriven.testing.VerifyFact;
import org.drools.guvnor.client.modeldriven.testing.VerifyField;
import org.drools.guvnor.client.modeldriven.testing.VerifyRuleFired;

public class ScenarioXMLPersistenceTest extends TestCase {

	public void testToXML() {
		ScenarioXMLPersistence p = ScenarioXMLPersistence.getInstance();

		Scenario sc = new Scenario();

		String s = p.marshal(sc);
		assertNotNull(s);

		sc = getDemo();

		s = p.marshal(sc);

	    assertTrue(s.indexOf("<ruleName>Life unverse and everything</ruleName>") > 0);

	    Scenario sc_ = p.unmarshal(s);
	    assertEquals(sc.globals.size(), sc_.globals.size());
	    assertEquals(sc.fixtures.size(), sc_.fixtures.size());
	    assertTrue(s.indexOf("org.drools") == -1); //check we have aliased all

	}

	public void testTrimUneededSection() {
		Scenario sc = getDemo();
		Scenario orig = getDemo();
		sc.fixtures.add(new ExecutionTrace());

		int origSize = orig.fixtures.size();

		assertEquals(origSize + 1, sc.fixtures.size());
		String xml = ScenarioXMLPersistence.getInstance().marshal(sc);
		Scenario sc_ = ScenarioXMLPersistence.getInstance().unmarshal(xml);

		assertEquals(origSize, sc_.fixtures.size());






	}


	public void testNewScenario() {
        FactData d1 = new FactData("Driver", "d1", ls(new FieldData[] {new FieldData("age", "42"), new FieldData("name", "david")}), false);
        Scenario sc = new Scenario();
        sc.fixtures.add(d1);
        sc.fixtures.add(new ExecutionTrace());

        int size = sc.fixtures.size();

		String xml = ScenarioXMLPersistence.getInstance().marshal(sc);
		Scenario sc_ = ScenarioXMLPersistence.getInstance().unmarshal(xml);

		assertEquals(size, sc_.fixtures.size());

		sc = new Scenario();
		sc.fixtures.add(new ExecutionTrace());
		xml = ScenarioXMLPersistence.getInstance().marshal(sc);
		sc_ = ScenarioXMLPersistence.getInstance().unmarshal(xml);
		assertEquals(1, sc_.fixtures.size());
	}

	private Scenario getDemo() {
        //Sample data
        FactData d1 = new FactData("Driver", "d1", ls(new FieldData[] {new FieldData("age", "42"), new FieldData("name", "david")}), false);
        FactData d2 = new FactData("Driver", "d2", ls(new FieldData[] {new FieldData("name", "michael")}), false);
        FactData d3 = new FactData("Driver", "d3", ls(new FieldData[] {new FieldData("name", "michael2")}), false);
        FactData d4 = new FactData("Accident", "a1", ls(new FieldData[] {new FieldData("name", "michael2")}), false);
        Scenario sc = new Scenario();
        sc.fixtures.add(d1);
        sc.fixtures.add(d2);
        sc.globals.add(d3);
        sc.globals.add(d4);
        sc.rules.add("rule1");
        sc.rules.add("rule2");

        sc.fixtures.add(new ExecutionTrace());

        List fields = new ArrayList();
        VerifyField vfl = new VerifyField("age", "42", "==");
        vfl.actualResult = "43";
        vfl.successResult = new Boolean(false);
        vfl.explanation = "Not cool jimmy.";

        fields.add(vfl);

        vfl = new VerifyField("name", "michael", "!=");
        vfl.actualResult = "bob";
        vfl.successResult = new Boolean(true);
        vfl.explanation = "Yeah !";
        fields.add(vfl);

        VerifyFact vf = new VerifyFact("d1", fields);

        sc.fixtures.add(vf);

        VerifyRuleFired vf1 = new VerifyRuleFired("Life unverse and everything", new Integer(42), null);
        vf1.actualResult = new Integer(42);
        vf1.successResult = new Boolean(true);
        vf1.explanation = "All good here.";

        VerifyRuleFired vf2 = new VerifyRuleFired("Everything else", null, new Boolean(true));
        vf2.actualResult = new Integer(0);
        vf2.successResult = new Boolean(false);
        vf2.explanation = "Not so good here.";
        sc.fixtures.add(vf1);
        sc.fixtures.add(vf2);

		return sc;
	}

	private List ls(FieldData[] fieldDatas) {
		List ls = new ArrayList();
		for (int i = 0; i < fieldDatas.length; i++) {
			ls.add(fieldDatas[i]);
		}
		return ls;
	}


}
