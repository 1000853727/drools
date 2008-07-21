package org.drools.lang;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.drools.base.evaluators.MatchesEvaluatorsDefinition;
import org.drools.base.evaluators.SetEvaluatorsDefinition;
import org.drools.lang.descr.FieldConstraintDescr;
import org.drools.lang.descr.PatternDescr;

public class MVELDumperTest extends TestCase {

    private MVELDumper dumper;
    
    protected void setUp() throws Exception {
        super.setUp();
        
        // configure operators
        new SetEvaluatorsDefinition();
        new MatchesEvaluatorsDefinition();
        
        dumper = new MVELDumper();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDump() throws Exception {
        String input = "Cheese( price > 10 && < 20 || == $val || == 30 )";
        String expected = "( ( price > 10 && price < 20 ) || price == $val || price == 30 )" ;
        PatternDescr pattern = (PatternDescr) parse("lhs_pattern", "lhs_pattern", input);
        
        FieldConstraintDescr fieldDescr = (FieldConstraintDescr) pattern.getConstraint().getDescrs().get( 0 );
        String result = dumper.dump( fieldDescr );
        
        assertEquals( expected, result );
    }
    
    public void testDumpMatches() throws Exception {
        String input = "Cheese( type.toString matches \"something\\swith\\tsingle escapes\" )";
        String expected = "type.toString ~= \"something\\\\swith\\\\tsingle escapes\"" ;
        PatternDescr pattern = (PatternDescr) parse("lhs_pattern", "lhs_pattern", input);
        
        FieldConstraintDescr fieldDescr = (FieldConstraintDescr) pattern.getConstraint().getDescrs().get( 0 );
        String result = dumper.dump( fieldDescr );
        
        assertEquals( expected, result );
    }

    public void testDumpMatches2() throws Exception {
        String input = "Cheese( type.toString matches 'something\\swith\\tsingle escapes' )";
        String expected = "type.toString ~= \"something\\\\swith\\\\tsingle escapes\"" ;
        PatternDescr pattern = (PatternDescr) parse("lhs_pattern", "lhs_pattern", input);

        FieldConstraintDescr fieldDescr = (FieldConstraintDescr) pattern.getConstraint().getDescrs().get( 0 );
        String result = dumper.dump( fieldDescr );
        
        assertEquals( expected, result );
    }

    public void testDumpMatches3() throws Exception {
        String input = "Map( this[\"content\"] matches \"hello ;=\" )";
        String expected = "this[\"content\"] ~= \"hello ;=\"" ;
        PatternDescr pattern = (PatternDescr) parse("lhs_pattern", "lhs_pattern", input);
        
        FieldConstraintDescr fieldDescr = (FieldConstraintDescr) pattern.getConstraint().getDescrs().get( 0 );
        String result = dumper.dump( fieldDescr );
        
        assertEquals( expected, result );
    }

    public void testDumpWithDateAttr() throws Exception {
        String input = "Person( son.birthDate == \"01-jan-2000\" )";
        String expected = "son.birthDate == org.drools.util.DateUtils.parseDate( \"01-jan-2000\" )" ;
        PatternDescr pattern = (PatternDescr) parse("lhs_pattern", "lhs_pattern", input);
        
        FieldConstraintDescr fieldDescr = (FieldConstraintDescr) pattern.getConstraint().getDescrs().get( 0 );
        String result = dumper.dump( fieldDescr, true );
        
        assertEquals( expected, result );
    }

	private Object parse(String parserRuleName, String treeRuleName,
			final String text) throws Exception {
		return newParser(parserRuleName, treeRuleName, newCharStream(text));
	}

	private CharStream newCharStream(final String text) {
		return new ANTLRStringStream(text);
	}

	private Object newParser(String parserRuleName, String treeRuleName,
			final CharStream charStream) {
		return execTreeParser(parserRuleName, treeRuleName, charStream);
	}

	public Object execTreeParser(String testRuleName, String testTreeRuleName,
			CharStream charStream) {
		Object treeRuleReturn = null;
		try {
			DRLLexer lexer = new DRLLexer(charStream);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			DRLParser parser = new DRLParser(tokens);
			parser.setTreeAdaptor(new DroolsTreeAdaptor());
			/** Use Reflection to get rule method from parser */
			Method ruleName = Class.forName("org.drools.lang.DRLParser")
					.getMethod(testRuleName);

			/** Invoke grammar rule, and get the return value */
			Object ruleReturn = ruleName.invoke(parser);
			
			if (!parser.hasErrors()){
				Class _return = Class.forName("org.drools.lang.DRLParser" + "$"
						+ testRuleName + "_return");
				Method returnName = _return.getMethod("getTree");
				DroolsTree tree = (DroolsTree) returnName.invoke(ruleReturn);

				// Walk resulting tree; create tree nodes stream first
				CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
				// AST nodes have payload that point into token stream
				nodes.setTokenStream(tokens);
				// Create a tree walker attached to the nodes stream
				DescrBuilderTree walker = new DescrBuilderTree(nodes);
				/** Invoke the tree rule, and store the return value if there is */
				Method treeRuleName = Class.forName(
						"org.drools.lang.DescrBuilderTree").getMethod(
						testTreeRuleName);
				treeRuleReturn = treeRuleName.invoke(walker);				
			}

			if (treeRuleReturn != null) {
				/** If return object is instanceof AST, get the toStringTree */
				if (treeRuleReturn.toString().indexOf(
						testTreeRuleName + "_return") > 0) {
					try { // NullPointerException may happen here...
						Class _treeReturn = Class
								.forName("org.drools.lang.DescrBuilderTree"
										+ "$" + testTreeRuleName + "_return");
						Field[] fields = _treeReturn.getDeclaredFields();
						for (Field field : fields) {
							if (field.getType().getName().contains(
									"org.drools.lang.descr.")) {
								return field.get(treeRuleReturn);
							}
						}
					} catch (Exception e) {
						System.err.println(e);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return treeRuleReturn;
	}
}
