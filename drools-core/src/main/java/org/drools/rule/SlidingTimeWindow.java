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
 * Created on Apr 26, 2008
 */

package org.drools.rule;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;

import org.drools.common.EventFactHandle;
import org.drools.common.InternalWorkingMemory;
import org.drools.common.PropagationContextImpl;
import org.drools.common.WorkingMemoryAction;
import org.drools.marshalling.MarshallerWriteContext;
import org.drools.reteoo.RightTuple;
import org.drools.spi.PropagationContext;
import org.drools.time.Job;
import org.drools.time.JobContext;
import org.drools.time.JobHandle;
import org.drools.time.TimerService;
import org.drools.time.Trigger;

/**
 * @author etirelli
 *
 */
public class SlidingTimeWindow
    implements
    Behavior {

    private long size;
    
    // FIXME: THIS IS SO WRONG!!! HOW DID I MADE THAT???? 
    private volatile transient RightTuple expiringTuple; 

    public SlidingTimeWindow() {
        this( 0 );
    }

    /**
     * @param size
     */
    public SlidingTimeWindow(final long size) {
        super();
        this.size = size;
    }

    /**
     * @inheritDoc
     *
     * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
     */
    public void readExternal(final ObjectInput in) throws IOException,
                                                  ClassNotFoundException {
        this.size = in.readLong();
    }

    /**
     * @inheritDoc
     *
     * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
     */
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeLong( this.size );
    }

    public BehaviorType getType() {
        return BehaviorType.TIME_WINDOW;
    }

    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(final long size) {
        this.size = size;
    }

    public Object createContext() {
        return new PriorityQueue<RightTuple>( 16, // arbitrary size... can we improve it?
                                              new SlidingTimeWindowComparator() );
    }

    /**
     * @inheritDoc
     *
     * @see org.drools.rule.Behavior#assertRightTuple(java.lang.Object, org.drools.reteoo.RightTuple, org.drools.common.InternalWorkingMemory)
     */
    public void assertRightTuple(final Object context,
                                 final RightTuple rightTuple,
                                 final InternalWorkingMemory workingMemory) {
        PriorityQueue<RightTuple> queue = (PriorityQueue<RightTuple>) context;
        queue.add( rightTuple );
        if ( queue.peek() == rightTuple ) {
            // update next expiration time 
            updateNextExpiration( rightTuple,
                                  workingMemory,
                                  queue );
        }
    }

    /**
     * @inheritDoc
     *
     * @see org.drools.rule.Behavior#retractRightTuple(java.lang.Object, org.drools.reteoo.RightTuple, org.drools.common.InternalWorkingMemory)
     */
    public void retractRightTuple(final Object context,
                                  final RightTuple rightTuple,
                                  final InternalWorkingMemory workingMemory) {
        // it may be a call back to expire the tuple that is already being expired
        if( this.expiringTuple != rightTuple ) {
            PriorityQueue<RightTuple> queue = (PriorityQueue<RightTuple>) context;
            if ( queue.peek() == rightTuple ) {
                // it was the head of the queue
                queue.poll();
                // update next expiration time 
                updateNextExpiration( queue.peek(),
                                      workingMemory,
                                      queue );
            } else {
                queue.remove( rightTuple );
            }
        }
    }

    public void expireTuples(final Object context,
                             final InternalWorkingMemory workingMemory) {
        TimerService clock = workingMemory.getTimerService();
        long currentTime = clock.getCurrentTime();
        PriorityQueue<RightTuple> queue = (PriorityQueue<RightTuple>) context;
        RightTuple tuple = queue.peek();
        while ( tuple != null && isExpired( currentTime,
                                            tuple ) ) {
            this.expiringTuple = tuple;
            queue.remove();
            final PropagationContext propagationContext = new PropagationContextImpl( workingMemory.getNextPropagationIdCounter(),
                                                                                      PropagationContext.RETRACTION,
                                                                                      null,
                                                                                      null,
                                                                                      tuple.getFactHandle() );
            tuple.getRightTupleSink().retractRightTuple( tuple,
                                                         propagationContext,
                                                         workingMemory );
            tuple.unlinkFromRightParent();
            this.expiringTuple = null;
            tuple = queue.peek();
        }
        
        // update next expiration time 
        updateNextExpiration( tuple,
                              workingMemory,
                              queue );
    }

    private boolean isExpired(final long currentTime,
                              final RightTuple rightTuple) {
        return ((EventFactHandle) rightTuple.getFactHandle()).getStartTimestamp() + this.size <= currentTime;
    }

    /**
     * @param rightTuple
     * @param workingMemory
     */
    private void updateNextExpiration(final RightTuple rightTuple,
                                      final InternalWorkingMemory workingMemory,
                                      final Object context) {
        TimerService clock = workingMemory.getTimerService();
        if ( rightTuple != null ) {
            long nextTimestamp = ((EventFactHandle) rightTuple.getFactHandle()).getStartTimestamp() + this.size;
            JobContext jobctx = new BehaviorJobContext( workingMemory, this, context );
            BehaviorJob job = new BehaviorJob();
            JobHandle handle = clock.scheduleJob( job, jobctx, new PointInTimeTrigger( nextTimestamp ));
            jobctx.setJobHandle( handle );
        }
    }
    
    public String toString() {
        return "SlidingTimeWindow( size="+size+" )";
    }

    /**
     * A Comparator<RightTuple> implementation for the fact queue
     * 
     * @author etirelli
     */
    private static class SlidingTimeWindowComparator
        implements
        Comparator<RightTuple> {
        public int compare(RightTuple t1,
                           RightTuple t2) {
            final EventFactHandle e1 = (EventFactHandle) t1.getFactHandle();
            final EventFactHandle e2 = (EventFactHandle) t2.getFactHandle();
            return (e1.getStartTimestamp() < e2.getStartTimestamp()) ? -1 : (e1.getStartTimestamp() == e2.getStartTimestamp() ? 0 : 1);
        }
    }
    
    private static class PointInTimeTrigger implements Trigger {
        private Date timestamp;
        
        public PointInTimeTrigger() {}
        
        public PointInTimeTrigger( long timestamp ) {
            this.timestamp = new Date( timestamp );
        }

        public Date getNextFireTime() {
            return this.timestamp;
        }

        public void readExternal(ObjectInput in) throws IOException,
                                                ClassNotFoundException {
            this.timestamp = (Date) in.readObject();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject( this.timestamp );
        }
    }
    
    private static class BehaviorJobContext implements JobContext {
        public InternalWorkingMemory workingMemory;
        public Behavior behavior;
        public Object behaviorContext;
        public JobHandle handle;
        
        /**
         * @param workingMemory
         * @param behavior
         * @param behaviorContext
         */
        public BehaviorJobContext(InternalWorkingMemory workingMemory,
                                  Behavior behavior,
                                  Object behaviorContext) {
            super();
            this.workingMemory = workingMemory;
            this.behavior = behavior;
            this.behaviorContext = behaviorContext;
        }

        public JobHandle getJobHandle() {
            return this.handle;
        }

        public void setJobHandle(JobHandle jobHandle) {
            this.handle = jobHandle;
        }
        
    }
    
    private static class BehaviorJob implements Job {

        public void execute(JobContext ctx) {
            BehaviorJobContext context = (BehaviorJobContext) ctx;
            context.workingMemory.queueWorkingMemoryAction( new BehaviorExpireWMAction( context.behavior, context.behaviorContext ) );
        }

    }
    
    private static class BehaviorExpireWMAction implements WorkingMemoryAction {
        private final Behavior behavior;
        private final Object context;

        /**
         * @param behavior
         * @param context
         */
        public BehaviorExpireWMAction(Behavior behavior,
                                      Object context) {
            super();
            this.behavior = behavior;
            this.context = context;
        }

        public void execute(InternalWorkingMemory workingMemory) {
            this.behavior.expireTuples( context, workingMemory );
        }

        public void write(MarshallerWriteContext context) throws IOException {
            // TODO Auto-generated method stub
            
        }

        public void readExternal(ObjectInput in) throws IOException,
                                                ClassNotFoundException {
            // TODO Auto-generated method stub
            
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            // TODO Auto-generated method stub
            
        }
        
    }

}
