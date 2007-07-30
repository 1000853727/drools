package org.drools.base.evaluators;

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

import org.drools.base.BaseEvaluator;
import org.drools.base.ValueType;
import org.drools.common.InternalWorkingMemory;
import org.drools.rule.VariableRestriction.DoubleVariableContextEntry;
import org.drools.rule.VariableRestriction.VariableContextEntry;
import org.drools.spi.Evaluator;
import org.drools.spi.Extractor;
import org.drools.spi.FieldValue;

public class DoubleFactory
    implements
    EvaluatorFactory {

    private static final long       serialVersionUID = 400L;
    private static EvaluatorFactory INSTANCE         = new DoubleFactory();

    private DoubleFactory() {

    }

    public static EvaluatorFactory getInstance() {
        if ( DoubleFactory.INSTANCE == null ) {
            DoubleFactory.INSTANCE = new DoubleFactory();
        }
        return DoubleFactory.INSTANCE;
    }

    public Evaluator getEvaluator(final Operator operator) {
        if ( operator == Operator.EQUAL ) {
            return DoubleEqualEvaluator.INSTANCE;
        } else if ( operator == Operator.NOT_EQUAL ) {
            return DoubleNotEqualEvaluator.INSTANCE;
        } else if ( operator == Operator.LESS ) {
            return DoubleLessEvaluator.INSTANCE;
        } else if ( operator == Operator.LESS_OR_EQUAL ) {
            return DoubleLessOrEqualEvaluator.INSTANCE;
        } else if ( operator == Operator.GREATER ) {
            return DoubleGreaterEvaluator.INSTANCE;
        } else if ( operator == Operator.GREATER_OR_EQUAL ) {
            return DoubleGreaterOrEqualEvaluator.INSTANCE;
        } else if ( operator == Operator.MEMBEROF ) {
            return DoubleMemberOfEvaluator.INSTANCE;
        } else if ( operator == Operator.NOTMEMBEROF ) {
            return DoubleNotMemberOfEvaluator.INSTANCE;
        } else {
            throw new RuntimeException( "Operator '" + operator + "' does not exist for DoubleEvaluator" );
        }
    }

    static class DoubleEqualEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new DoubleEqualEvaluator();

        private DoubleEqualEvaluator() {
            super( ValueType.PDOUBLE_TYPE,
                   Operator.EQUAL );
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor,
                                final Object object1, final FieldValue object2) {
            if ( extractor.isNullValue( workingMemory, object1 ) ) {
                return object2.isNull();
            } else if ( object2.isNull() ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return extractor.getDoubleValue( workingMemory, object1 ) == object2.getDoubleValue();
        }

        public boolean evaluateCachedRight(InternalWorkingMemory workingMemory,
                                           final VariableContextEntry context, final Object left) {
            if ( context.declaration.getExtractor().isNullValue( workingMemory, left ) ) {
                return context.isRightNull();
            } else if ( context.isRightNull() ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return context.declaration.getExtractor().getDoubleValue( workingMemory, left ) == ((DoubleVariableContextEntry) context).right;
        }

        public boolean evaluateCachedLeft(InternalWorkingMemory workingMemory,
                                          final VariableContextEntry context, final Object right) {
            if ( context.extractor.isNullValue( workingMemory, right )) {
                return context.isLeftNull();
            } else if ( context.isLeftNull() ) {
                return false;
            }
            
            // TODO: we are not handling delta right now... maybe we should
            return ((DoubleVariableContextEntry) context).left == context.extractor.getDoubleValue( workingMemory, right );
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor1,
                                final Object object1,
                                final Extractor extractor2, final Object object2) {
            if (extractor1.isNullValue( workingMemory, object1 )) {
                return extractor2.isNullValue( workingMemory, object2 );
            } else if (extractor2.isNullValue( workingMemory, object2 )) {
                return false;
            }
            
            // TODO: we are not handling delta right now... maybe we should
            return extractor1.getDoubleValue( workingMemory, object1 ) == extractor2.getDoubleValue( workingMemory, object2 );
        }

        public String toString() {
            return "Double ==";
        }
    }

    static class DoubleNotEqualEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new DoubleNotEqualEvaluator();

        private DoubleNotEqualEvaluator() {
            super( ValueType.PDOUBLE_TYPE,
                   Operator.NOT_EQUAL );
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor,
                                final Object object1, final FieldValue object2) {
            if ( extractor.isNullValue( workingMemory, object1 ) ) {
                return !object2.isNull();
            } else if ( object2.isNull() ) {
                return true;
            }
            
            // TODO: we are not handling delta right now... maybe we should
            return extractor.getDoubleValue( workingMemory, object1 ) != object2.getDoubleValue();
        }

        public boolean evaluateCachedRight(InternalWorkingMemory workingMemory,
                                           final VariableContextEntry context, final Object left) {
            if ( context.declaration.getExtractor().isNullValue( workingMemory, left ) ) {
                return !context.isRightNull();
            } else if ( context.isRightNull() ) {
                return true;
            }
            
            // TODO: we are not handling delta right now... maybe we should
            return context.declaration.getExtractor().getDoubleValue( workingMemory, left ) != ((DoubleVariableContextEntry) context).right;
        }

        public boolean evaluateCachedLeft(InternalWorkingMemory workingMemory,
                                          final VariableContextEntry context, final Object right) {
            if ( context.extractor.isNullValue( workingMemory, right )) {
                return !context.isLeftNull();
            } else if ( context.isLeftNull() ) {
                return true;
            }
            
            // TODO: we are not handling delta right now... maybe we should
            return ((DoubleVariableContextEntry) context).left != context.extractor.getDoubleValue( workingMemory, right );
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor1,
                                final Object object1,
                                final Extractor extractor2, final Object object2) {
            if (extractor1.isNullValue( workingMemory, object1 )) {
                return !extractor2.isNullValue( workingMemory, object2 );
            } else if (extractor2.isNullValue( workingMemory, object2 )) {
                return true;
            }
            
            // TODO: we are not handling delta right now... maybe we should
            return extractor1.getDoubleValue( workingMemory, object1 ) != extractor2.getDoubleValue( workingMemory, object2 );
        }

        public String toString() {
            return "Double !=";
        }
    }

    static class DoubleLessEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new DoubleLessEvaluator();

        private DoubleLessEvaluator() {
            super( ValueType.PDOUBLE_TYPE,
                   Operator.LESS );
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor,
                                final Object object1, final FieldValue object2) {
            if( extractor.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return extractor.getDoubleValue( workingMemory, object1 ) < object2.getDoubleValue();
        }

        public boolean evaluateCachedRight(InternalWorkingMemory workingMemory,
                                           final VariableContextEntry context, final Object left) {
            if( context.rightNull ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return ((DoubleVariableContextEntry) context).right < context.declaration.getExtractor().getDoubleValue( workingMemory, left );
        }

        public boolean evaluateCachedLeft(InternalWorkingMemory workingMemory,
                                          final VariableContextEntry context, final Object right) {
            if( context.extractor.isNullValue( workingMemory, right ) ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return context.extractor.getDoubleValue( workingMemory, right ) < ((DoubleVariableContextEntry) context).left;
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor1,
                                final Object object1,
                                final Extractor extractor2, final Object object2) {
            if( extractor1.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return extractor1.getDoubleValue( workingMemory, object1 ) < extractor2.getDoubleValue( workingMemory, object2 );
        }

        public String toString() {
            return "Double <";
        }
    }

    static class DoubleLessOrEqualEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new DoubleLessOrEqualEvaluator();

        private DoubleLessOrEqualEvaluator() {
            super( ValueType.PDOUBLE_TYPE,
                   Operator.LESS_OR_EQUAL );
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor,
                                final Object object1, final FieldValue object2) {
            if( extractor.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return extractor.getDoubleValue( workingMemory, object1 ) <= object2.getDoubleValue();
        }

        public boolean evaluateCachedRight(InternalWorkingMemory workingMemory,
                                           final VariableContextEntry context, final Object left) {
            if( context.rightNull ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return ((DoubleVariableContextEntry) context).right <= context.declaration.getExtractor().getDoubleValue( workingMemory, left );
        }

        public boolean evaluateCachedLeft(InternalWorkingMemory workingMemory,
                                          final VariableContextEntry context, final Object right) {
            if( context.extractor.isNullValue( workingMemory, right ) ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return context.extractor.getDoubleValue( workingMemory, right ) <= ((DoubleVariableContextEntry) context).left;
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor1,
                                final Object object1,
                                final Extractor extractor2, final Object object2) {
            if( extractor1.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return extractor1.getDoubleValue( workingMemory, object1 ) <= extractor2.getDoubleValue( workingMemory, object2 );
        }

        public String toString() {
            return "Double <=";
        }
    }

    static class DoubleGreaterEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new DoubleGreaterEvaluator();

        private DoubleGreaterEvaluator() {
            super( ValueType.PDOUBLE_TYPE,
                   Operator.GREATER );
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor,
                                final Object object1, final FieldValue object2) {
            if( extractor.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return extractor.getDoubleValue( workingMemory, object1 ) > object2.getDoubleValue();
        }

        public boolean evaluateCachedRight(InternalWorkingMemory workingMemory,
                                           final VariableContextEntry context, final Object left) {
            if( context.rightNull ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return ((DoubleVariableContextEntry) context).right > context.declaration.getExtractor().getDoubleValue( workingMemory, left );
        }

        public boolean evaluateCachedLeft(InternalWorkingMemory workingMemory,
                                          final VariableContextEntry context, final Object right) {
            if( context.extractor.isNullValue( workingMemory, right ) ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return context.extractor.getDoubleValue( workingMemory, right ) > ((DoubleVariableContextEntry) context).left;
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor1,
                                final Object object1,
                                final Extractor extractor2, final Object object2) {
            if( extractor1.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return extractor1.getDoubleValue( workingMemory, object1 ) > extractor2.getDoubleValue( workingMemory, object2 );
        }

        public String toString() {
            return "Double >";
        }
    }

    static class DoubleGreaterOrEqualEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long      serialVersionUID = 400L;
        private final static Evaluator INSTANCE         = new DoubleGreaterOrEqualEvaluator();

        private DoubleGreaterOrEqualEvaluator() {
            super( ValueType.PDOUBLE_TYPE,
                   Operator.GREATER_OR_EQUAL );
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor,
                                final Object object1, final FieldValue object2) {
            if( extractor.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return extractor.getDoubleValue( workingMemory, object1 ) >= object2.getDoubleValue();
        }

        public boolean evaluateCachedRight(InternalWorkingMemory workingMemory,
                                           final VariableContextEntry context, final Object left) {
            if( context.rightNull ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return ((DoubleVariableContextEntry) context).right >= context.declaration.getExtractor().getDoubleValue( workingMemory, left );
        }

        public boolean evaluateCachedLeft(InternalWorkingMemory workingMemory,
                                          final VariableContextEntry context, final Object right) {
            if( context.extractor.isNullValue( workingMemory, right ) ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return context.extractor.getDoubleValue( workingMemory, right ) >= ((DoubleVariableContextEntry) context).left;
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor1,
                                final Object object1,
                                final Extractor extractor2, final Object object2) {
            if( extractor1.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            // TODO: we are not handling delta right now... maybe we should
            return extractor1.getDoubleValue( workingMemory, object1 ) >= extractor2.getDoubleValue( workingMemory, object2 );
        }

        public String toString() {
            return "Double >=";
        }
    }
    
    static class DoubleMemberOfEvaluator extends BaseMemberOfEvaluator {

        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new DoubleMemberOfEvaluator();

        private DoubleMemberOfEvaluator() {
            super( ValueType.PDOUBLE_TYPE,
                   Operator.MEMBEROF );
        }

        public String toString() {
            return "Double memberOf";
        }
    }

    static class DoubleNotMemberOfEvaluator extends BaseNotMemberOfEvaluator {

        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new DoubleNotMemberOfEvaluator();

        private DoubleNotMemberOfEvaluator() {
            super( ValueType.PDOUBLE_TYPE,
                   Operator.NOTMEMBEROF );
        }

        public String toString() {
            return "Double not memberOf";
        }
    }
    
}