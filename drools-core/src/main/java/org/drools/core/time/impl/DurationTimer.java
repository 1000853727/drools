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

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.drools.core.WorkingMemory;
import org.drools.core.common.EventFactHandle;
import org.drools.core.common.InternalWorkingMemory;
import org.drools.core.reteoo.LeftTuple;
import org.drools.core.rule.ConditionalElement;
import org.drools.core.rule.Declaration;
import org.drools.core.util.NumberUtils;
import org.drools.core.spi.Activation;
import org.drools.core.time.Trigger;
import org.kie.api.runtime.Calendars;

public class DurationTimer extends BaseTimer
    implements
    Timer,
    Externalizable {

    private long duration;
    private Declaration eventFactHandle;

    public DurationTimer() {

    }

    public DurationTimer(long duration) {
        this.duration = duration;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(duration);
        out.writeObject(eventFactHandle);
    }

    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        duration = in.readLong();
        eventFactHandle = (Declaration ) in.readObject();
    }

    public long getDuration() {
        return duration;
    }

    public Trigger createTrigger(Activation item, InternalWorkingMemory wm) {
        long timestamp = wm.getTimerService().getCurrentTime();
        String[] calendarNames = item.getRule().getCalendars();
        Calendars calendars = wm.getCalendars();
        return createTrigger(timestamp, calendarNames, calendars);
    }

    public Trigger createTrigger(long timestamp,
                                 LeftTuple leftTuple,
                                 DefaultJobHandle jh,
                                 String[] calendarNames,
                                 Calendars calendars,
                                 Declaration[][] declrs,
                                 InternalWorkingMemory wm) {
        if ( eventFactHandle != null ) {
            EventFactHandle  fh = (EventFactHandle) leftTuple.get(declrs[0][0]);
            timestamp = fh.getStartTimestamp();
        }
        return createTrigger(timestamp, calendarNames, calendars);
    }

    public Trigger createTrigger(long timestamp,
                                 String[] calendarNames,
                                 Calendars calendars) {
        long offset = timestamp + duration;
        if( NumberUtils.isAddOverflow( timestamp, duration, offset ) ) {
            // this should not happen, but possible in some odd simulation scenarios, so creating a trigger for immediate execution instead
            return new PointInTimeTrigger( timestamp,
                                           calendarNames,
                                           calendars );
        } else {
            return new PointInTimeTrigger( offset,
                                           calendarNames,
                                           calendars );
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (duration ^ (duration >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        DurationTimer other = (DurationTimer) obj;
        if ( duration != other.duration ) return false;
        return true;
    }

    @Override
    public ConditionalElement clone() {
        return new DurationTimer( duration );
    }

    public void setEventFactHandle(Declaration eventFactHandle) {
        this.eventFactHandle = eventFactHandle;
    }

    public Declaration getEventFactHandleDeclaration() {
        return eventFactHandle;
    }
}
