package org.drools.api;

import java.io.StringReader;
import java.util.Collection;

import junit.framework.TestCase;

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.KnowledgeType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;

public class KnowledgeSessionTest extends TestCase {
	public void testKnowledgeProviderWithRules() {
		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		
		String str = "";
		str += "package org.test1\n";
		str += "rule rule1\n";
		str += "when\n";
		str += "then\n";
		str += "end\n\n";
		str += "rule rule2\n";
		str += "when\n";
		str += "then\n";
		str += "end\n";				
		builder.add( ResourceFactory.newByteArrayResource( str.getBytes() ), KnowledgeType.DRL );
		
		str = "package org.test2\n";
		str += "rule rule3\n";
		str += "when\n";
		str += "then\n";
		str += "end\n\n";
		str += "rule rule4\n";
		str += "when\n";
		str += "then\n";
		str += "end\n";			
		builder.add( ResourceFactory.newByteArrayResource( str.getBytes() ), KnowledgeType.DRL );
		
		Collection<KnowledgePackage> pkgs = builder.getKnowledgePackages();

		
	}
}
