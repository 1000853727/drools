/**
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.assistant.refactor;

import java.util.ArrayList;
import java.util.List;

import org.drools.assistant.info.RuleRefactorInfo;
import org.drools.assistant.info.drl.RuleBasicContentInfo;
import org.drools.assistant.option.AssistantOption;
import org.drools.assistant.option.ReplaceAssistantOption;

public class DSLRuleRefactor extends AbstractRuleRefactor {
	
	private List<AssistantOption> options;
	
	public DSLRuleRefactor(RuleRefactorInfo ruleRefactorInfo) {
		this.ruleRefactorInfo = ruleRefactorInfo;
		this.options = new ArrayList<AssistantOption>();
	}
	
	@Override
	public List<AssistantOption> execute(int offset) {
		if ((option = this.bindVariable(null))!=null)
			this.options.add(option);
		if ((option = this.fixImports(null))!=null)
			this.options.add(option);
		return this.options;
	}

	@Override
	protected ReplaceAssistantOption bindVariable(RuleBasicContentInfo contentInfo) {
		return null;
	}

	@Override
	protected ReplaceAssistantOption fixImports(RuleBasicContentInfo contentInfo) {
		return null;
	}

	@Override
	protected ReplaceAssistantOption renameVariable(RuleBasicContentInfo contentInfo) {
		return null;
	}

}
