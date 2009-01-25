package org.drools.lang;

import java.util.LinkedList;

import junit.framework.TestCase;

import org.antlr.runtime.RecognitionException;
import org.drools.compiler.DrlParser;
import org.drools.compiler.DroolsParserException;
import org.drools.lang.descr.ImportDescr;
import org.drools.lang.descr.PackageDescr;
import org.drools.lang.descr.RuleDescr;

public class DRLIncompleteCodeTest extends TestCase {

	public void testIncompleteCode1() throws DroolsParserException,
			RecognitionException {
		String input = "package a.b.c import a.b.c.* query MyQuery rule MyRule when Class ( property memberOf collexction ";
		DrlParser parser = new DrlParser();
		PackageDescr descr = parser.parse(true, input);
		System.out.println(parser.getErrors());

		assertNotNull(descr);
		assertEquals("a.b.c", descr.getNamespace());
		assertEquals("a.b.c.*", ((ImportDescr) descr.getImports().get(0))
				.getTarget());

		assertEquals(Location.LOCATION_LHS_INSIDE_CONDITION_END,
				getLastIntegerValue(parser.getEditorSentences().get(2)
						.getContent()));
	}

	public void testIncompleteCode2() throws DroolsParserException,
			RecognitionException {
		String input = "rule MyRule when Class ( property memberOf collection ";
		DrlParser parser = new DrlParser();
		PackageDescr descr = parser.parse(true, input);

		assertNotNull(descr);
		assertEquals(Location.LOCATION_LHS_INSIDE_CONDITION_END,
				getLastIntegerValue(parser.getEditorSentences().get(0)
						.getContent()));
	}

	public void testIncompleteCode3() throws DroolsParserException,
			RecognitionException {
		String input = "rule MyRule when Class ( property memberOf collection ) then end query MyQuery Class ( property memberOf collection ) end ";
		DrlParser parser = new DrlParser();
		PackageDescr descr = parser.parse(true, input);

		assertNotNull(descr);
		assertEquals("MyRule", ((RuleDescr) descr.getRules().get(0)).getName());

		assertNotNull(descr);
		assertEquals("MyQuery", ((RuleDescr) descr.getRules().get(1)).getName());

		assertEquals(Location.LOCATION_RHS, getLastIntegerValue(parser
				.getEditorSentences().get(0).getContent()));
	}

	public void testIncompleteCode4() throws DroolsParserException,
			RecognitionException {
		String input = "package a.b.c import a.b.c.*"
				+ " rule MyRule when Class ( property memberOf collection ) then end "
				+ " query MyQuery Class ( property memberOf collection ) end ";
		DrlParser parser = new DrlParser();
		PackageDescr descr = parser.parse(true, input);

		assertEquals("a.b.c", descr.getNamespace());
		assertEquals("a.b.c.*", ((ImportDescr) descr.getImports().get(0))
				.getTarget());

		assertNotNull(descr);
		assertEquals("MyRule", ((RuleDescr) descr.getRules().get(0)).getName());

		assertNotNull(descr);
		assertEquals("MyQuery", ((RuleDescr) descr.getRules().get(1)).getName());
	}

	public void testIncompleteCode5() throws DroolsParserException,
			RecognitionException {
		String input = "packe a.b.c import a.b.c.*"
				+ " rule MyRule when Class ( property memberOf collection ) then end "
				+ " query MyQuery Class ( property memberOf collection ) end ";
		DrlParser parser = new DrlParser();
		PackageDescr descr = parser.parse(true, input);

		assertNotNull(descr);
	}

	public void testIncompleteCode6() throws DroolsParserException,
			RecognitionException {
		String input = "packe 1111.111 import a.b.c.*"
				+ " rule MyRule when Class ( property memberOf collection ) then end "
				+ " query MyQuery Class ( property memberOf collection ) end ";
		DrlParser parser = new DrlParser();
		PackageDescr descr = parser.parse(true, input);

		// here is null, 'cos parser emits an error on predict dfa on "packe
		// 1111.111" and stops the parsing
		assertNull(descr);
	}

	public void testIncompleteCode7() throws DroolsParserException,
			RecognitionException {
		String input = "package a.b.c imrt a.b.c.*"
				+ " rule MyRule when Class ( property memberOf collection ) then end "
				+ " query MyQuery Class ( property memberOf collection ) end ";
		DrlParser parser = new DrlParser();
		PackageDescr descr = parser.parse(true, input);

		assertNotNull(descr);
	}

	public void testIncompleteCode8() throws DroolsParserException,
			RecognitionException {
		String input = "package a.b.c import a.1111.c.*"
				+ " rule MyRule when Class ( property memberOf collection ) then end "
				+ " query MyQuery Class ( property memberOf collection ) end ";
		DrlParser parser = new DrlParser();
		PackageDescr descr = parser.parse(true, input);

		assertEquals("a.b.c", descr.getNamespace());
		assertEquals(2, descr.getRules().size());
		assertEquals(true, parser.hasErrors());
	}

	public void testIncompleteCode9() throws DroolsParserException,
			RecognitionException {
		String input = "package a.b.c import a.b.c.*"
				+ " rule MyRule xxxxx Class ( property memberOf collection ) then end "
				+ " query MyQuery Class ( property memberOf collection ) end ";
		DrlParser parser = new DrlParser();
		PackageDescr descr = parser.parse(true, input);

		assertEquals("a.b.c", descr.getNamespace());
		assertEquals("a.b.c.*", ((ImportDescr) descr.getImports().get(0))
				.getTarget());

		assertEquals(1, descr.getRules().size());
		assertEquals("MyQuery", ((RuleDescr) descr.getRules().get(0)).getName());
	}

	public void testIncompleteCode10() throws DroolsParserException,
			RecognitionException {
		String input = "package a.b.c import a.b.c.*"
				+ " rule MyRule xxxxx Class ( property memberOf "
				+ " query MyQuery Class ( property memberOf collection ) end ";
		DrlParser parser = new DrlParser();
		PackageDescr descr = parser.parse(true, input);

		assertEquals("a.b.c", descr.getNamespace());
		assertEquals("a.b.c.*", ((ImportDescr) descr.getImports().get(0))
				.getTarget());

		assertEquals(0, descr.getRules().size());
	}

	public void testIncompleteCode11() throws DroolsParserException,
			RecognitionException {
		String input = "package a.b.c import a.b.c.*"
				+ " rule MyRule when Class ( property memberOf collection ) then end "
				+ " qzzzzuery MyQuery Class ( property ";
		DrlParser parser = new DrlParser();
		PackageDescr descr = parser.parse(true, input);

		assertEquals("a.b.c", descr.getNamespace());
		assertEquals("a.b.c.*", ((ImportDescr) descr.getImports().get(0))
				.getTarget());

		assertNotNull(descr);
		assertEquals("MyRule", ((RuleDescr) descr.getRules().get(0)).getName());
	}

	public void testIncompleteCode12() throws DroolsParserException,
			RecognitionException {
		String input = "package a.b.c " + "import a.b.c.* " + "rule MyRule"
				+ "  when " + "    m: Message(  ) " + "    " + "  then"
				+ "end ";
		DrlParser parser = new DrlParser();
		PackageDescr descr = parser.parse(true, input);
		assertNotNull(descr);

		assertEquals("a.b.c", descr.getNamespace());
		assertEquals("a.b.c.*", ((ImportDescr) descr.getImports().get(0))
				.getTarget());
	}

	public void testIncompleteCode13() throws DroolsParserException,
			RecognitionException {
		String input = "package com.sample "
				+ "import com.sample.DroolsTest.Message; "
				+ "rule \"Hello World\"" + "  when " + "  then" + "     \\\" "
				+ "end ";
		DrlParser parser = new DrlParser();
		PackageDescr descr = parser.parse(true, input);
		assertNotNull(descr);
	}

	@SuppressWarnings("unchecked")
	private int getLastIntegerValue(LinkedList list) {
		// System.out.println(list.toString());
		int lastIntergerValue = -1;
		for (Object object : list) {
			if (object instanceof Integer) {
				lastIntergerValue = (Integer) object;
			}
		}
		return lastIntergerValue;
	}
}