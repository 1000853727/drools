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
import org.drools.rule.VariableRestriction.LongVariableContextEntry;
import org.drools.rule.VariableRestriction.VariableContextEntry;
import org.drools.spi.Evaluator;
import org.drools.spi.Extractor;
import org.drools.spi.FieldValue;

public class ByteFactory
    implements
    EvaluatorFactory {

    private static final long       serialVersionUID = 400L;
    private static EvaluatorFactory INSTANCE         = new ByteFactory();

    private ByteFactory() {

    }

    public static EvaluatorFactory getInstance() {
        if ( ByteFactory.INSTANCE == null ) {
            ByteFactory.INSTANCE = new ByteFactory();
        }
        return ByteFactory.INSTANCE;
    }

    public Evaluator getEvaluator(final Operator operator) {
        if ( operator == Operator.EQUAL ) {
            return ByteEqualEvaluator.INSTANCE;
        } else if ( operator == Operator.NOT_EQUAL ) {
            return ByteNotEqualEvaluator.INSTANCE;
        } else if ( operator == Operator.LESS ) {
            return ByteLessEvaluator.INSTANCE;
        } else if ( operator == Operator.LESS_OR_EQUAL ) {
            return ByteLessOrEqualEvaluator.INSTANCE;
        } else if ( operator == Operator.GREATER ) {
            return ByteGreaterEvaluator.INSTANCE;
        } else if ( operator == Operator.GREATER_OR_EQUAL ) {
            return ByteGreaterOrEqualEvaluator.INSTANCE;
        } else if ( operator == Operator.MEMBEROF ) {
            return ByteMemberOfEvaluator.INSTANCE;
        } else if ( operator == Operator.NOTMEMBEROF ) {
            return ByteNotMemberOfEvaluator.INSTANCE;
        } else {
            throw new RuntimeException( "Operator '" + operator + "' does not exist for ByteEvaluator" );
        }
    }

    static class ByteEqualEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new ByteEqualEvaluator();

        private ByteEqualEvaluator() {
            super( ValueType.PBYTE_TYPE,
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
            
            return extractor.getByteValue( workingMemory, object1 ) == object2.getByteValue();
        }

        public boolean evaluateCachedRight(InternalWorkingMemory workingMemory,
                                           final VariableContextEntry context, final Object left) {
            if ( context.declaration.getExtractor().isNullValue( workingMemory, left ) ) {
                return context.isRightNull();
            } else if ( context.isRightNull() ) {
                return false;
            }
            
            return context.declaration.getExtractor().getByteValue( workingMemory, left ) == ((LongVariableContextEntry) context).right;
        }

        public boolean evaluateCachedLeft(InternalWorkingMemory workingMemory,
                                          final VariableContextEntry context, final Object right) {
            if ( context.extractor.isNullValue( workingMemory, right )) {
                return context.isLeftNull();
            } else if ( context.isLeftNull() ) {
                return false;
            }
            
            return ((LongVariableContextEntry) context).left == context.extractor.getByteValue( workingMemory, right );
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
            
            return extractor1.getByteValue( workingMemory, object1 ) == extractor2.getByteValue( workingMemory, object2 );
        }

        public String toString() {
            return "Byte ==";
        }

    }

    static class ByteNotEqualEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new ByteNotEqualEvaluator();

        private ByteNotEqualEvaluator() {
            super( ValueType.PBYTE_TYPE,
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
            
            return extractor.getByteValue( workingMemory, object1 ) != object2.getByteValue();
        }

        public boolean evaluateCachedRight(InternalWorkingMemory workingMemory,
                                           final VariableContextEntry context, final Object left) {
            if ( context.declaration.getExtractor().isNullValue( workingMemory, left ) ) {
                return !context.isRightNull();
            } else if ( context.isRightNull() ) {
                return true;
            }
            
            return context.declaration.getExtractor().getByteValue( workingMemory, left ) != ((LongVariableContextEntry) context).right;
        }

        public boolean evaluateCachedLeft(InternalWorkingMemory workingMemory,
                                          final VariableContextEntry context, final Object object2) {
            if ( context.extractor.isNullValue( workingMemory, object2 ) ) {
                return !context.isLeftNull();
            } else if ( context.isLeftNull() ) {
                return true;
            }
            
            return ((LongVariableContextEntry) context).left != context.extractor.getByteValue( workingMemory, object2 );
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
            
            return extractor1.getByteValue( workingMemory, object1 ) != extractor2.getByteValue( workingMemory, object2 );
        }

        public String toString() {
            return "Byte !=";
        }
    }

    static class ByteLessEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new ByteLessEvaluator();

        private ByteLessEvaluator() {
            super( ValueType.PBYTE_TYPE,
                   Operator.LESS );
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor,
                                final Object object1, final FieldValue object2) {
            if( extractor.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            return extractor.getByteValue( workingMemory, object1 ) < object2.getByteValue();
        }

        public boolean evaluateCachedRight(InternalWorkingMemory workingMemory,
                                           final VariableContextEntry context, final Object left) {
            if( context.rightNull ) {
                return false;
            }
            return ((LongVariableContextEntry) context).right < context.declaration.getExtractor().getByteValue( workingMemory, left );
        }

        public boolean evaluateCachedLeft(InternalWorkingMemory workingMemory,
                                          final VariableContextEntry context, final Object right) {
            if( context.extractor.isNullValue( workingMemory, right ) ) {
                return false;
            }
            return context.extractor.getByteValue( workingMemory, right ) < ((LongVariableContextEntry) context).left;
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor1,
                                final Object object1,
                                final Extractor extractor2, final Object object2) {
            if( extractor1.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            return extractor1.getByteValue( workingMemory, object1 ) < extractor2.getByteValue( workingMemory, object2 );
        }

        public String toString() {
            return "Byte <";
        }
    }

    static class ByteLessOrEqualEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new ByteLessOrEqualEvaluator();

        private ByteLessOrEqualEvaluator() {
            super( ValueType.PBYTE_TYPE,
                   Operator.LESS_OR_EQUAL );
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor,
                                final Object object1, final FieldValue object2) {
            if( extractor.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            return extractor.getByteValue( workingMemory, object1 ) <= object2.getByteValue();
        }

        public boolean evaluateCachedRight(InternalWorkingMemory workingMemory,
                                           final VariableContextEntry context, final Object left) {
            if( context.rightNull ) {
                return false;
            }
            return ((LongVariableContextEntry) context).right <= context.declaration.getExtractor().getByteValue( workingMemory, left );
        }

        public boolean evaluateCachedLeft(InternalWorkingMemory workingMemory,
                                          final VariableContextEntry context, final Object right) {
            if( context.extractor.isNullValue( workingMemory, right ) ) {
                return false;
            }
            return context.extractor.getByteValue( workingMemory, right ) <= ((LongVariableContextEntry) context).left;
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor1,
                                final Object object1,
                                final Extractor extractor2, final Object object2) {
            if( extractor1.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            return extractor1.getByteValue( workingMemory, object1 ) <= extractor2.getByteValue( workingMemory, object2 );
        }

        public String toString() {
            return "Byte <=";
        }
    }

    static class ByteGreaterEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new ByteGreaterEvaluator();

        private ByteGreaterEvaluator() {
            super( ValueType.PBYTE_TYPE,
                   Operator.GREATER );
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor,
                                final Object object1, final FieldValue object2) {
            if( extractor.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            return extractor.getByteValue( workingMemory, object1 ) > object2.getByteValue();
        }

        public boolean evaluateCachedRight(InternalWorkingMemory workingMemory,
                                           final VariableContextEntry context, final Object left) {
            if( context.rightNull ) {
                return false;
            }
            return ((LongVariableContextEntry) context).right > context.declaration.getExtractor().getByteValue( workingMemory, left );
        }

        public boolean evaluateCachedLeft(InternalWorkingMemory workingMemory,
                                          final VariableContextEntry context, final Object right) {
            if( context.extractor.isNullValue( workingMemory, right ) ) {
                return false;
            }
            return context.extractor.getByteValue( workingMemory, right ) > ((LongVariableContextEntry) context).left;
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor1,
                                final Object object1,
                                final Extractor extractor2, final Object object2) {
            if( extractor1.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            return extractor1.getByteValue( workingMemory, object1 ) > extractor2.getByteValue( workingMemory, object2 );
        }

        public String toString() {
            return "Byte >";
        }
    }

    static class ByteGreaterOrEqualEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long      serialVersionUID = 400L;
        private final static Evaluator INSTANCE         = new ByteGreaterOrEqualEvaluator();

        private ByteGreaterOrEqualEvaluator() {
            super( ValueType.PBYTE_TYPE,
                   Operator.GREATER_OR_EQUAL );
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor,
                                final Object object1, final FieldValue object2) {
            if( extractor.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            return extractor.getByteValue( workingMemory, object1 ) >= object2.getByteValue();
        }

        public boolean evaluateCachedRight(InternalWorkingMemory workingMemory,
                                           final VariableContextEntry context, final Object left) {
            if( context.rightNull ) {
                return false;
            }
            return ((LongVariableContextEntry) context).right >= context.declaration.getExtractor().getByteValue( workingMemory, left );
        }

        public boolean evaluateCachedLeft(InternalWorkingMemory workingMemory,
                                          final VariableContextEntry context, final Object right) {
            if( context.extractor.isNullValue( workingMemory, right ) ) {
                return false;
            }
            return context.extractor.getByteValue( workingMemory, right ) >= ((LongVariableContextEntry) context).left;
        }

        public boolean evaluate(InternalWorkingMemory workingMemory,
                                final Extractor extractor1,
                                final Object object1,
                                final Extractor extractor2, final Object object2) {
            if( extractor1.isNullValue( workingMemory, object1 ) ) {
                return false;
            }
            return extractor1.getByteValue( workingMemory, object1 ) >= extractor2.getByteValue( workingMemory, object2 );
        }

        public String toString() {
            return "Byte >=";
        }
    }

    static class ByteMemberOfEvaluator extends BaseMemberOfEvaluator {

        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new ByteMemberOfEvaluator();

        private ByteMemberOfEvaluator() {
            super( ValueType.PBYTE_TYPE,
                   Operator.MEMBEROF );
        }

        public String toString() {
            return "Byte memberOf";
        }
    }

    static class ByteNotMemberOfEvaluator extends BaseNotMemberOfEvaluator {

        private static final long     serialVersionUID = 400L;
        public final static Evaluator INSTANCE         = new ByteNotMemberOfEvaluator();

        private ByteNotMemberOfEvaluator() {
            super( ValueType.PBYTE_TYPE,
                   Operator.NOTMEMBEROF );
        }

        public String toString() {
            return "Byte not memberOf";
        }
    }
    
}