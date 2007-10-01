package org.drools.decisiontable.parser;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.drools.rule.GroupElement;
import org.drools.rule.LiteralConstraint;
import org.drools.rule.Package;
import org.drools.rule.Rule;
import org.drools.rule.VariableConstraint;

public class DefaultTemplateRuleBaseTest extends TestCase {

	public void testSimpleTemplate() throws Exception
	{
		TemplateContainer tc = new TemplateContainer() {
			private Column[] columns = new Column[] {
					new LongColumn("column1"),
					new LongColumn("column2"),
					new StringColumn("column3")
			};
			
			public Column[] getColumns() {
				return columns;
			}

			public String getHeader() {
				return null;
			}

			public Map getTemplates() {
				Map templates = new HashMap();
				RuleTemplate ruleTemplate = new RuleTemplate("template1", this);
				ruleTemplate.addColumn("column1 == 10");
				ruleTemplate.addColumn("column2 < 5 || > 20");
				ruleTemplate.addColumn("column3 == \"xyz\"");
				templates.put("template1", ruleTemplate);
				return templates;
			}

			public Column getColumn(String name) {
				return columns[Integer.parseInt(name.substring(6)) - 1];
			}
			
		};
		DefaultTemplateRuleBase ruleBase = new DefaultTemplateRuleBase(tc);
		Package[] packages = ruleBase.newStatefulSession().getRuleBase().getPackages();
		assertEquals(1, packages.length);
		Map globals = packages[0].getGlobals();
		assertEquals(DefaultGenerator.class, globals.get("generator"));
		Rule[] rules = packages[0].getRules();
		assertEquals(1, rules.length);
		assertEquals("template1", rules[0].getName());
		GroupElement lhs = rules[0].getLhs();
		//when
		//  r : Row()
		//  column1 : Column(name == "column1")
		//  exists LongCell(row == r, column == column1, value == 10)
		//  column2 : Column(name == "column2")
		//  exists LongCell(row == r, column == column2, value < 5 | > 20)
		//  column3 : Column(name == "column3")
		//  exists StringCell(row == r, column == column3, value == "xyz")
		assertEquals(7, lhs.getChildren().size());
		org.drools.rule.Pattern pattern = (org.drools.rule.Pattern) lhs.getChildren().get(1);
		assertEquals(1, pattern.getConstraints().size());
		LiteralConstraint constraint = (LiteralConstraint) pattern.getConstraints().get(0);
		assertEquals("column1", constraint.getField().getValue());
		GroupElement exists = (GroupElement) lhs.getChildren().get(2);
		pattern = (org.drools.rule.Pattern) exists.getChildren().get(0);
		assertEquals(3, pattern.getConstraints().size());
		VariableConstraint vconstraint = (VariableConstraint) pattern.getConstraints().get(1);
		assertEquals(Column.class, vconstraint.getFieldExtractor().getExtractToClass());
		assertEquals("column1", vconstraint.getRequiredDeclarations()[0].getIdentifier());
		pattern = (org.drools.rule.Pattern) lhs.getChildren().get(3);
		assertEquals(1, pattern.getConstraints().size());
		constraint = (LiteralConstraint) pattern.getConstraints().get(0);
		assertEquals("column2", constraint.getField().getValue());
		exists = (GroupElement) lhs.getChildren().get(4);
		pattern = (org.drools.rule.Pattern) exists.getChildren().get(0);
		assertEquals(3, pattern.getConstraints().size());
		vconstraint = (VariableConstraint) pattern.getConstraints().get(1);
		assertEquals(Column.class, vconstraint.getFieldExtractor().getExtractToClass());
		assertEquals("column2", vconstraint.getRequiredDeclarations()[0].getIdentifier());
		pattern = (org.drools.rule.Pattern) lhs.getChildren().get(5);
		assertEquals(1, pattern.getConstraints().size());
		constraint = (LiteralConstraint) pattern.getConstraints().get(0);
		assertEquals("column3", constraint.getField().getValue());
		exists = (GroupElement) lhs.getChildren().get(6);
		pattern = (org.drools.rule.Pattern) exists.getChildren().get(0);
		assertEquals(3, pattern.getConstraints().size());
		vconstraint = (VariableConstraint) pattern.getConstraints().get(1);
		assertEquals(Column.class, vconstraint.getFieldExtractor().getExtractToClass());
		assertEquals("column3", vconstraint.getRequiredDeclarations()[0].getIdentifier());
	}
}
