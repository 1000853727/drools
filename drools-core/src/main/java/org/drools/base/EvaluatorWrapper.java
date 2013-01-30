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

package org.drools.base;

import org.drools.base.extractors.SelfReferenceClassFieldReader;
import org.drools.common.InternalFactHandle;
import org.drools.common.InternalWorkingMemory;
import org.drools.rule.Declaration;
import org.drools.rule.VariableRestriction.VariableContextEntry;
import org.drools.spi.Evaluator;
import org.drools.spi.FieldValue;
import org.drools.spi.InternalReadAccessor;
import org.drools.time.Interval;

import static org.drools.base.mvel.MVELCompilationUnit.getFactHandle;

/**
 * An EvaluatorWrapper is used when executing MVEL expressions
 * that have operator calls rewritten as:
 * 
 * operator.evaluate( leftArg, rightArg )
 * 
 */
public class EvaluatorWrapper
    implements
    Evaluator {

    private static final long                          serialVersionUID = 520L;

    private static final SelfReferenceClassFieldReader extractor        = new SelfReferenceClassFieldReader( Object.class,
                                                                                                             "dummy" );

    private Evaluator                                  evaluator;
    private transient InternalWorkingMemory            workingMemory;

    private Declaration                                leftBinding;
    private Declaration                                rightBinding;

    private InternalFactHandle                         leftHandle;
    private InternalFactHandle                         rightHandle;

    private InternalReadAccessor                       leftExtractor;
    private InternalReadAccessor                       rightExtractor;

    private boolean                                    selfLeft;
    private boolean                                    selfRight;

    public EvaluatorWrapper(Evaluator evaluator,
                            Declaration leftBinding,
                            Declaration rightBinding) {
        this.evaluator = evaluator;
        this.leftBinding = leftBinding;
        this.rightBinding = rightBinding;
        init();
    }

    private void init() {
        leftExtractor = leftBinding == null || leftBinding.isPatternDeclaration() ? extractor : leftBinding.getExtractor();
        rightExtractor = rightBinding == null || rightBinding.isPatternDeclaration() ? extractor : rightBinding.getExtractor();
        selfLeft = leftBinding == null || leftBinding.getIdentifier().equals("this");
        selfRight = rightBinding == null || rightBinding.getIdentifier().equals("this");
    }

    /**
     * This method is called when operators are rewritten as function calls. For instance,
     * 
     * x after y
     * 
     * Is rewritten as
     * 
     * after.evaluate( x, y )
     * 
     * @return
     */
    public boolean evaluate(Object left,
                            Object right) {
        return evaluator.evaluate( workingMemory,
                                   leftExtractor,
                                   leftHandle,
                                   rightExtractor,
                                   rightHandle );
    }

    /**
     * @return
     * @see org.kie.spi.Evaluator#getValueType()
     */
    public ValueType getValueType() {
        return evaluator.getValueType();
    }

    /**
     * @return
     * @see org.kie.spi.Evaluator#getOperator()
     */
    public org.kie.runtime.rule.Operator getOperator() {
        return evaluator.getOperator();
    }

    /**
     * @return
     * @see org.kie.spi.Evaluator#getCoercedValueType()
     */
    public ValueType getCoercedValueType() {
        return evaluator.getCoercedValueType();
    }

    /**
     * @param workingMemory
     * @param extractor
     * @param factHandle
     * @param value
     * @return
     * @see org.kie.spi.Evaluator#evaluate(org.kie.common.InternalWorkingMemory, org.kie.spi.InternalReadAccessor, InternalFactHandle, org.kie.spi.FieldValue)
     */
    public boolean evaluate(InternalWorkingMemory workingMemory,
                            InternalReadAccessor extractor,
                            InternalFactHandle factHandle,
                            FieldValue value) {
        return evaluator.evaluate( workingMemory,
                                   extractor,
                                   factHandle,
                                   value );
    }

    /**
     * @param workingMemory
     * @param leftExtractor
     * @param left
     * @param rightExtractor
     * @param right
     * @return
     * @see org.kie.spi.Evaluator#evaluate(org.kie.common.InternalWorkingMemory, org.kie.spi.InternalReadAccessor, InternalFactHandle, org.kie.spi.InternalReadAccessor, InternalFactHandle)
     */
    public boolean evaluate(InternalWorkingMemory workingMemory,
                            InternalReadAccessor leftExtractor,
                            InternalFactHandle left,
                            InternalReadAccessor rightExtractor,
                            InternalFactHandle right) {
        return evaluator.evaluate( workingMemory,
                                   leftExtractor,
                                   left,
                                   rightExtractor,
                                   right );
    }

    /**
     * @param workingMemory
     * @param context
     * @param right
     * @return
     * @see org.kie.spi.Evaluator#evaluateCachedLeft(org.kie.common.InternalWorkingMemory, org.kie.rule.VariableRestriction.VariableContextEntry, InternalFactHandle)
     */
    public boolean evaluateCachedLeft(InternalWorkingMemory workingMemory,
                                      VariableContextEntry context,
                                      InternalFactHandle right) {
        return evaluator.evaluateCachedLeft( workingMemory,
                                             context,
                                             right );
    }

    /**
     * @param workingMemory
     * @param context
     * @param left
     * @return
     * @see org.kie.spi.Evaluator#evaluateCachedRight(org.kie.common.InternalWorkingMemory, org.kie.rule.VariableRestriction.VariableContextEntry, InternalFactHandle)
     */
    public boolean evaluateCachedRight(InternalWorkingMemory workingMemory,
                                       VariableContextEntry context,
                                       InternalFactHandle left) {
        return evaluator.evaluateCachedRight( workingMemory,
                                              context,
                                              left );
    }

    /**
     * @return
     * @see org.kie.spi.Evaluator#isTemporal()
     */
    public boolean isTemporal() {
        return evaluator.isTemporal();
    }

    /**
     * @return
     * @see org.kie.spi.Evaluator#getInterval()
     */
    public Interval getInterval() {
        return evaluator.getInterval();
    }

    public void loadHandles(InternalWorkingMemory workingMemory, InternalFactHandle[] handles, Object rightObject) {
        this.workingMemory = workingMemory;
        leftHandle = selfLeft ? (InternalFactHandle) workingMemory.getFactHandle(rightObject) : getFactHandle(leftBinding, handles);
        rightHandle = selfRight ? (InternalFactHandle) workingMemory.getFactHandle(rightObject) : getFactHandle(rightBinding, handles);
    }

    @Override
    public String toString() {
        return this.evaluator.toString();
    }
}
