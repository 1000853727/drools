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
 */
package org.drools.reteoo;

import org.drools.common.InternalFactHandle;
import org.drools.common.InternalWorkingMemory;
import org.drools.common.RuleBasePartitionId;
import org.drools.spi.PropagationContext;

/**
 * This is an asynchronous implementation of the SingleObjectSinkAdapter
 * that is used to propagate facts between different partitions in the
 * rulebase
 *
 * @author: <a href="mailto:tirelli@post.com">Edson Tirelli</a>
 */
public class AsyncSingleObjectSinkAdapter extends SingleObjectSinkAdapter {

    public AsyncSingleObjectSinkAdapter() {
        super();
    }

    public AsyncSingleObjectSinkAdapter( RuleBasePartitionId partitionId, ObjectSink objectSink ) {
        super( partitionId, objectSink );
    }

    public void propagateAssertObject( InternalFactHandle factHandle, PropagationContext context, InternalWorkingMemory workingMemory ) {
        PartitionTaskManager manager = workingMemory.getPartitionManager( this.sink.getPartitionId() );
        manager.enqueue( new PartitionTaskManager.FactAssertAction(factHandle, context, this.sink ) );
    }
}
