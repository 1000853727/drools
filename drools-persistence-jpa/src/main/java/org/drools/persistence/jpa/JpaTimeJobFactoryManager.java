package org.drools.persistence.jpa;

import org.drools.command.CommandService;
import org.drools.time.*;
import org.drools.time.impl.TimerJobFactoryManager;
import org.drools.time.impl.TimerJobInstance;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JpaTimeJobFactoryManager
        implements
        TimerJobFactoryManager {

    private CommandService commandService;

    private Map<Long, TimerJobInstance> timerInstances;

    public void setCommandService(CommandService commandService) {
        this.commandService = commandService;
    }

    public JpaTimeJobFactoryManager() {
        timerInstances = new ConcurrentHashMap<Long, TimerJobInstance>();
    }

    public TimerJobInstance createTimerJobInstance(Job job,
                                                   JobContext ctx,
                                                   Trigger trigger,
                                                   JobHandle handle,
                                                   InternalSchedulerService scheduler) {
        ctx.setJobHandle(handle);
        JpaTimerJobInstance jobInstance = new JpaTimerJobInstance(new SelfRemovalJob(job),
                                                                  new SelfRemovalJobContext(ctx,
                                                                                            timerInstances),
                                                                  trigger,
                                                                  handle,
                                                                  scheduler);

        return jobInstance;
    }

    public void addTimerJobInstance(TimerJobInstance instance) {

        this.timerInstances.put(instance.getJobHandle().getId(),
                                instance);
    }

    public void removeTimerJobInstance(TimerJobInstance instance) {

        this.timerInstances.remove(instance.getJobHandle().getId());
    }


    public Collection<TimerJobInstance> getTimerJobInstances() {
        return timerInstances.values();
    }

    public CommandService getCommandService() {
        return commandService;
    }

}
