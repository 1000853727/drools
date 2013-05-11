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

package org.drools.core.time.impl;

import java.io.Serializable;

import org.drools.core.WorkingMemory;
import org.drools.core.common.InternalWorkingMemory;
import org.drools.core.reteoo.LeftTuple;
import org.drools.core.rule.Declaration;
import org.drools.core.rule.RuleConditionElement;
import org.drools.core.spi.Activation;
import org.drools.core.spi.RuleComponent;
import org.drools.core.time.Trigger;
import org.kie.api.runtime.Calendars;

public interface Timer extends Serializable, RuleComponent, RuleConditionElement {

    Trigger createTrigger( long timestamp, String[] calendarNames, Calendars calendars);

    public Trigger createTrigger(long timestamp,
                                 LeftTuple leftTuple,
                                 DefaultJobHandle jh,
                                 String[] calendarNames,
                                 Calendars calendars,
                                 Declaration[][] declrs,
                                 InternalWorkingMemory wm);

    Trigger createTrigger( Activation schedulableActivation, InternalWorkingMemory workingMemory );
}
