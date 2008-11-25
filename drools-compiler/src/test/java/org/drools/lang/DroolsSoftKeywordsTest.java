/**
 * 
 */
package org.drools.lang;

import org.drools.base.evaluators.EvaluatorRegistry;

import junit.framework.TestCase;

/**
 * @author admin
 *
 */
public class DroolsSoftKeywordsTest extends TestCase {

	/**
	 * Test method for {@link org.drools.lang.DroolsSoftKeywords#isOperator(java.lang.String, boolean)}.
	 */
	public void testIsOperator() {
		// initializes the registry
		new EvaluatorRegistry();
		
		// test the registry
		assertTrue( DroolsSoftKeywords.isOperator("matches", false) );
		assertTrue( DroolsSoftKeywords.isOperator("matches", true) );
		assertTrue( DroolsSoftKeywords.isOperator("contains", false) );
		assertTrue( DroolsSoftKeywords.isOperator("contains", true) );
		assertTrue( DroolsSoftKeywords.isOperator("after", false) );
		assertTrue( DroolsSoftKeywords.isOperator("after", true) );
		assertTrue( DroolsSoftKeywords.isOperator("before", false) );
		assertTrue( DroolsSoftKeywords.isOperator("before", true) );
		assertTrue( DroolsSoftKeywords.isOperator("finishes", false) );
		assertTrue( DroolsSoftKeywords.isOperator("finishes", true) );
		assertTrue( DroolsSoftKeywords.isOperator("overlappedby", false) );
		assertTrue( DroolsSoftKeywords.isOperator("overlappedby", true) );
		
		assertFalse( DroolsSoftKeywords.isOperator("xyz", false) );
		assertFalse( DroolsSoftKeywords.isOperator("xyz", true) );
		
	}

}
