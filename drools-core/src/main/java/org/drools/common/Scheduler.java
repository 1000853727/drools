package org.drools.common;

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

import org.drools.Agenda;
import org.drools.process.instance.timer.TimerManager.TimerTrigger;
import org.drools.time.Job;
import org.drools.time.JobContext;
import org.drools.time.JobHandle;
import org.drools.time.Trigger;
import org.drools.time.impl.PointInTimeTrigger;

/**
 * Scheduler for rules requiring truth duration.
 * 
 * @author <a href="mailto:bob@werken.com">bob mcwhirter </a>
 */
final class Scheduler {
    // ------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------
    /**
     * Construct.
     */
    private Scheduler() {
    }

    /**
     * Schedule an agenda item.
     * 
     * @param item
     *            The item to schedule.
     * @param workingMemory
     *            The working memory session.
     */
    public static void scheduleAgendaItem(final ScheduledAgendaItem item, InternalAgenda agenda) {
        DuractionJob job = new DuractionJob();        
        DuractionJobContext ctx = new DuractionJobContext( item, agenda );
        Trigger trigger = new PointInTimeTrigger( item.getRule().getDuration().getDuration( item.getTuple() ) +
                                                       ((InternalWorkingMemory)agenda.getWorkingMemory()).getTimerService().getCurrentTime());
        
        
        JobHandle jobHandle = ((InternalWorkingMemory)agenda.getWorkingMemory()).getTimerService().scheduleJob( job, ctx, trigger );
        item.setJobHandle( jobHandle );
    }
    
    public static void removeAgendaItem(final ScheduledAgendaItem item, final InternalAgenda agenda) {
        ((InternalWorkingMemory)agenda.getWorkingMemory()).getTimerService().removeJob( item.getJobHandle() );
    }    
    
    public static class DuractionJob implements Job {
        public void execute(JobContext ctx) {
            InternalAgenda agenda = ( InternalAgenda ) ((DuractionJobContext)ctx).getAgenda();
            ScheduledAgendaItem item  = ((DuractionJobContext)ctx).getScheduledAgendaItem();
            
            agenda.fireActivation( item );
            agenda.getScheduledActivationsLinkedList().remove( item );
            agenda.getWorkingMemory().fireAllRules();            
        }        
    }
    
    public static class DuractionJobContext implements JobContext {
        private JobHandle jobHandle;
        private ScheduledAgendaItem scheduledAgendaItem;
        private Agenda agenda;                
        
        public DuractionJobContext(ScheduledAgendaItem scheduledAgendaItem,
                                   Agenda agenda) {
            this.scheduledAgendaItem = scheduledAgendaItem;
            this.agenda = agenda;
        }

        public DuractionJobContext(ScheduledAgendaItem scheduledAgendaItem) {
            this.scheduledAgendaItem = scheduledAgendaItem;
        }
        
        public Agenda getAgenda() {
            return this.agenda;
        }
        
        public ScheduledAgendaItem getScheduledAgendaItem() {
            return this.scheduledAgendaItem;
        }

        public JobHandle getJobHandle() {
            return this.jobHandle;
        }

        public void setJobHandle(JobHandle jobHandle) {
            this.jobHandle = jobHandle;
        }        
    }    
}
