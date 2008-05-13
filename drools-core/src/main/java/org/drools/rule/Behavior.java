/*
 * Copyright 2008 JBoss Inc
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
 *
 * Created on Apr 25, 2008
 */

package org.drools.rule;

import org.drools.common.InternalWorkingMemory;
import org.drools.reteoo.RightTuple;
import org.drools.spi.RuleComponent;

/**
 * An interface for all behavior implementations
 * 
 * @author etirelli
 *
 */
public interface Behavior extends RuleComponent, Cloneable {
    
    public static final Behavior[] EMPTY_BEHAVIOR_LIST = new Behavior[0];
    
    public enum BehaviorType {
        TIME_WINDOW( "time" ),
        LENGTH_WINDOW( "length" );
        
        private final String id;
        
        private BehaviorType( String id ) {
            this.id = id;
        }
        
        public boolean matches( String id ) {
            return this.id.equalsIgnoreCase( id );
        }
    }
    
    /**
     * Returns the type of the behavior
     * 
     * @return
     */
    public BehaviorType getType();

    /**
     * Creates the context object associated with this behavior.
     * The object is given as a parameter in all behavior call backs.
     * 
     * @return
     */
    public Object createContext();

    /**
     * Makes the behavior aware of the new fact entering behavior's scope
     * 
     * @param context The behavior context object
     * @param tuple The new fact entering behavior's scope
     * @param workingMemory The working memory session reference
     */
    public void assertRightTuple(Object context,
                                 RightTuple tuple,
                                 InternalWorkingMemory workingMemory);

    /**
     * Removes a right tuple from the behavior's scope
     * 
     * @param context The behavior context object
     * @param rightTuple The tuple leaving the behavior's scope
     * @param workingMemory The working memory session reference
     */
    public void retractRightTuple(Object context,
                                  RightTuple rightTuple,
                                  InternalWorkingMemory workingMemory);

    /**
     * A callback method that allows behaviors to expire tuples
     * 
     * @param context The behavior context object
     * @param workingMemory The working memory session reference
     */
    public void expireTuples(Object context, 
                             InternalWorkingMemory workingMemory);
    
}
