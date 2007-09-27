package org.drools.brms.modeldriven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import junit.framework.TestCase;

import org.drools.brms.server.util.DataEnumLoader;
import org.mvel.MVEL;

public class DataEnumLoaderTest extends TestCase {

	public void testEnumGeneration() throws Exception {



		Object result = MVEL.eval("[2, 3, 4, ]", new HashMap());
		assertTrue(result instanceof List);
		List l = (List) result;
		assertEquals(3, l.size());

		result = MVEL.eval("['Person.age' : [2, 3]\n 'Person.name' : ['qqq', \n'ccc']]", new HashMap());




		DataEnumLoader loader = new DataEnumLoader(readLines().toString());

        assertFalse(loader.getErrors().toString(), loader.hasErrors());

		Map enumeration = (Map) loader.getData();
		assertEquals(loader.getErrors().toString(), 0, loader.getErrors().size());
		assertEquals(3, enumeration.size());

		String[] list = (String[]) enumeration.get("Person.age");
		assertEquals(4, list.length);
		assertEquals("1", list[0]);
		assertEquals("2", list[1]);

		list = (String[]) enumeration.get("Person.rating");
		assertEquals(2, list.length);
		assertEquals("High", list[0]);
		assertEquals("Low", list[1]);




		loader = new DataEnumLoader("goober poo error");
		assertEquals(0, loader.getData().size());
		assertFalse(loader.getErrors().size() == 0);
		assertTrue(loader.hasErrors());

	}



    public void testNoOp() {
        DataEnumLoader loader = new DataEnumLoader(" ");
        assertFalse(loader.hasErrors());
        assertEquals(0, loader.getData().size());

        loader = new DataEnumLoader("");
        assertFalse(loader.hasErrors());
        assertEquals(0, loader.getData().size());

    }

    public void testLiteralHelperUtilityClass() {
    	//this shows how you can load it up with a class (which should return a map of keys to List.
    	DataEnumLoader loader = new DataEnumLoader("=(new org.drools.brms.modeldriven.SampleDataSource2()).loadData()");

    	assertFalse(loader.hasErrors());

        assertEquals(1, loader.getData().size());
        String[] res = (String[]) loader.getData().get("whee");
    	assertEquals(2, res.length);
    	assertEquals("hey", res[0]);
    	assertEquals("ho", res[1]);

    }


    public void testNewLines() {
        String s = "yeah yeah, \nyeah \nyeah";
        assertEquals("yeah yeah,\nyeah,\nyeah", DataEnumLoader.addCommasForNewLines( s ));
    }

	private StringBuffer readLines() throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("Some.enumeration")));
		String line = "";
		StringBuffer buf = new StringBuffer();
		while ((line = r.readLine()) != null) {
			buf.append(line); buf.append('\n');
		}
		return buf;
	}


}
