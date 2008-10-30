package org.drools.event.rule.impl;

/*
 * Copyright 2005 JBoss Inc
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

import org.drools.event.rule.ActivationCancelledEvent;
import org.drools.event.rule.ActivationCreatedEvent;
import org.drools.event.rule.ActivationEvent;
import org.drools.runtime.rule.Activation;


public class ActivationEventImpl implements ActivationEvent, ActivationCreatedEvent, ActivationCancelledEvent {
    private Activation activation;
    
    public ActivationEventImpl(Activation activation) {
        this.activation = activation;
    }
    
    public Activation getActivation() {
        return this.activation;
    }

}