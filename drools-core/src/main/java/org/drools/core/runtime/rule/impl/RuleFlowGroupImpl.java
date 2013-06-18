/*
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

package org.drools.core.runtime.rule.impl;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.drools.core.common.InternalAgenda;
import org.drools.core.common.InternalAgendaGroup;
import org.kie.api.runtime.rule.RuleFlowGroup;

public class RuleFlowGroupImpl implements RuleFlowGroup, Externalizable {
    
    private String name;
    
    private InternalAgenda agenda;
    
    RuleFlowGroupImpl() {
        
    }
    
    RuleFlowGroupImpl(org.drools.core.spi.RuleFlowGroup ruleflowGroup, InternalAgenda agenda) {
        this.name = ruleflowGroup.getName();
        this.agenda = agenda;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF( this.name );
    }
    
    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
        this.name = in.readUTF();
    }
    
    public String getName() {
        return this.name;
    }

    public void clear() {
        this.agenda.clearAndCancelRuleFlowGroup( this.name );
    }
    
    public void setAutoDeactivate( boolean deactivate ) {
        ((InternalAgendaGroup)this.agenda.getRuleFlowGroup( this.name )).setAutoDeactivate( deactivate );
    }

}
