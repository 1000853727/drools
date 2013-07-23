package org.drools.reteoo.common;

import org.drools.core.common.InternalFactHandle;
import org.drools.core.common.PropagationContextFactory;
import org.drools.core.marshalling.impl.MarshallerReaderContext;
import org.drools.core.reteoo.LeftTuple;
import org.drools.core.rule.EntryPointId;
import org.drools.core.rule.Rule;
import org.drools.core.spi.PropagationContext;

import java.io.Serializable;

public class RetePropagationContextFactory implements PropagationContextFactory, Serializable  {
    public PropagationContext createPropagationContext(final long number,
                                                       final int type,
                                                       final Rule rule,
                                                       final LeftTuple leftTuple,
                                                       final InternalFactHandle factHandle,
                                                       final EntryPointId entryPoint,
                                                       final long modificationMask,
                                                       final Class<?> modifiedClass,
                                                       final MarshallerReaderContext readerContext) {
        return new RetePropagationContext(number, type, rule, leftTuple, factHandle, entryPoint, modificationMask, modifiedClass, readerContext);
    }

    public PropagationContext createPropagationContext(final long number,
                                                       final int type,
                                                       final Rule rule,
                                                       final LeftTuple leftTuple,
                                                       final InternalFactHandle factHandle,
                                                       final EntryPointId entryPoint,
                                                       final MarshallerReaderContext readerContext) {
        return new RetePropagationContext(number, type, rule, leftTuple, factHandle, entryPoint, readerContext);
    }

    public PropagationContext createPropagationContext(final long number,
                                                       final int type,
                                                       final Rule rule,
                                                       final LeftTuple leftTuple,
                                                       final InternalFactHandle factHandle,
                                                       final int activeActivations,
                                                       final int dormantActivations,
                                                       final EntryPointId entryPoint,
                                                       final long modificationMask) {
        return new RetePropagationContext(number, type, rule, leftTuple, factHandle, activeActivations, dormantActivations, entryPoint, modificationMask);
    }

    public PropagationContext createPropagationContext(final long number,
                                                       final int type,
                                                       final Rule rule,
                                                       final LeftTuple leftTuple,
                                                       final InternalFactHandle factHandle,
                                                       final EntryPointId entryPoint) {
        return new RetePropagationContext(number, type, rule, leftTuple, factHandle, entryPoint);
    }

    public PropagationContext createPropagationContext(final long number,
                                                       final int type,
                                                       final Rule rule,
                                                       final LeftTuple leftTuple,
                                                       final InternalFactHandle factHandle) {
        return new RetePropagationContext(number, type, rule, leftTuple, factHandle);
    }
}
