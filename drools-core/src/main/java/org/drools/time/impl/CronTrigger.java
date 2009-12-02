package org.drools.time.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.drools.time.Trigger;

public class CronTrigger implements Trigger {
    
    protected static final int YEAR_TO_GIVEUP_SCHEDULING_AT = 2299;
    
    private CronExpression cronEx = null;
    private Date startTime = null;
    private Date endTime = null;
    private Date nextFireTime = null;
    private Date previousFireTime = null;
    private transient TimeZone timeZone = null;   
    
    
    public CronTrigger(long timestamp, Date startTime, Date endTime, String cronExpression) {
        this( timestamp, startTime, endTime, determineCronExpression( cronExpression ));
    }
    
    public CronTrigger(long timestamp, Date startTime, Date endTime, CronExpression cronExpression) {
        setCronExpression(cronExpression);

        if (startTime == null) {
            startTime = new Date(timestamp);
        }
        setStartTime(startTime);
        if (endTime != null) {
            setEndTime(endTime);
        }
        setTimeZone(TimeZone.getDefault());        
        
        this.nextFireTime = getFireTimeAfter(new Date(getStartTime().getTime() - 1000l));        
    }
    
    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        if (startTime == null) {
            throw new IllegalArgumentException("Start time cannot be null");
        }

        Date eTime = getEndTime();
        if (eTime != null && startTime != null && eTime.before(startTime)) {
            throw new IllegalArgumentException(
                "End time cannot be before start time");
        }
        
        // round off millisecond...
        // Note timeZone is not needed here as parameter for
        // Calendar.getInstance(),
        // since time zone is implicit when using a Date in the setTime method.
        Calendar cl = Calendar.getInstance();
        cl.setTime(startTime);
        cl.set(Calendar.MILLISECOND, 0);

        this.startTime = cl.getTime();
    }

    /**
     * <p>
     * Get the time at which the <code>CronTrigger</code> should quit
     * repeating - even if repeastCount isn't yet satisfied.
     * </p>
     * 
     * @see #getFinalFireTime()
     */
    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        Date sTime = getStartTime();
        if (sTime != null && endTime != null && sTime.after(endTime)) {
            throw new IllegalArgumentException(
                    "End time cannot be before start time");
        }

        this.endTime = endTime;
    }

    /**
     * <p>
     * Returns the next time at which the <code>Trigger</code> is scheduled to fire. If
     * the trigger will not fire again, <code>null</code> will be returned.  Note that
     * the time returned can possibly be in the past, if the time that was computed
     * for the trigger to next fire has already arrived, but the scheduler has not yet
     * been able to fire the trigger (which would likely be due to lack of resources
     * e.g. threads).
     * </p>
     *
     * <p>The value returned is not guaranteed to be valid until after the <code>Trigger</code>
     * has been added to the scheduler.
     * </p>
     *
     * @see TriggerUtils#computeFireTimesBetween(Trigger, org.quartz.Calendar , Date, Date)
     */
    public Date getNextFireTime() {
        return this.nextFireTime;
    }

    /**
     * <p>
     * Returns the previous time at which the <code>CronTrigger</code> 
     * fired. If the trigger has not yet fired, <code>null</code> will be
     * returned.
     */
    public Date getPreviousFireTime() {
        return this.previousFireTime;
    }

    /**
     * <p>
     * Sets the next time at which the <code>CronTrigger</code> will fire.
     * <b>This method should not be invoked by client code.</b>
     * </p>
     */
    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    /**
     * <p>
     * Set the previous time at which the <code>CronTrigger</code> fired.
     * </p>
     * 
     * <p>
     * <b>This method should not be invoked by client code.</b>
     * </p>
     */
    public void setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    /**
     * <p>
     * Returns the time zone for which the <code>cronExpression</code> of
     * this <code>CronTrigger</code> will be resolved.
     * </p>
     */
    public TimeZone getTimeZone() {
        
        if(cronEx != null) {
            return cronEx.getTimeZone();
        }
        
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        return timeZone;
    }

    /**
     * <p>
     * Sets the time zone for which the <code>cronExpression</code> of this
     * <code>CronTrigger</code> will be resolved.
     * </p>
     * 
     * <p>If {@link #setCronExpression(CronExpression)} is called after this
     * method, the TimeZon setting on the CronExpression will "win".  However
     * if {@link #setCronExpression(String)} is called after this method, the
     * time zone applied by this method will remain in effect, since the 
     * String cron expression does not carry a time zone!
     */
    public void setTimeZone(TimeZone timeZone) {
        if(cronEx != null) {
            cronEx.setTimeZone(timeZone);
        }
        this.timeZone = timeZone;
    }
    
    public void setCronExpression(String cronExpression) {
        setCronExpression( determineCronExpression(cronExpression) );
    }    
    
    public void setCronExpression(CronExpression cronExpression) {
        TimeZone origTz = getTimeZone();
        this.cronEx = cronExpression;
        this.cronEx.setTimeZone(origTz);
    }  
    
    public static CronExpression determineCronExpression(String cronExpression) {
        try {
            return new CronExpression(cronExpression);
        } catch ( Exception e ) {
            throw new RuntimeException("Unable to parse cron expression '" + cronExpression + "'", e);
        }        
    }

    public Date hasNextFireTime() {
        return this.nextFireTime;
    }

    public Date nextFireTime() {
        Date date = this.nextFireTime;
        this.nextFireTime = getFireTimeAfter(this.nextFireTime);
        return date;
    }
    
    /**
     * <p>
     * Returns the next time at which the <code>CronTrigger</code> will fire,
     * after the given time. If the trigger will not fire after the given time,
     * <code>null</code> will be returned.
     * </p>
     * 
     * <p>
     * Note that the date returned is NOT validated against the related
     * org.quartz.Calendar (if any)
     * </p>
     */
    public Date getFireTimeAfter(Date afterTime) {
        if (getStartTime().after(afterTime)) {
            afterTime = new Date(getStartTime().getTime() - 1000l);
        }

        if (getEndTime() != null && (afterTime.compareTo(getEndTime()) >= 0)) {
            return null;
        }
        
        Date pot = getTimeAfter(afterTime);
        if (getEndTime() != null && pot != null && pot.after(getEndTime())) {
            return null;
        }

        return pot;
    }
    
    protected Date getTimeAfter(Date afterTime) {
        return (cronEx == null) ? null : cronEx.getTimeAfter(afterTime);
    }    

}
