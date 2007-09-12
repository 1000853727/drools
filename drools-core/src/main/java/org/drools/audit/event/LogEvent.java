package org.drools.audit.event;

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

/**
 * An event logged by the WorkingMemoryLogger.
 * It is a snapshot of the event as it was thrown by the working memory.
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen </a>
 */
public class LogEvent {

    public static final int INSERTED            = 1;
    public static final int UPDATED            = 2;
    public static final int RETRACTED           = 3;

    public static final int ACTIVATION_CREATED         = 4;
    public static final int ACTIVATION_CANCELLED       = 5;
    public static final int BEFORE_ACTIVATION_FIRE     = 6;
    public static final int AFTER_ACTIVATION_FIRE      = 7;

    public static final int RULEFLOW_CREATED           = 8;
    public static final int RULEFLOW_COMPLETED         = 9;
    public static final int RULEFLOW_GROUP_ACTIVATED   = 10;
    public static final int RULEFLOW_GROUP_DEACTIVATED = 11;

    public static final int BEFORE_PACKAGE_ADDED       = 12;
    public static final int AFTER_PACKAGE_ADDED        = 13;
    public static final int BEFORE_PACKAGE_REMOVED     = 14;
    public static final int AFTER_PACKAGE_REMOVED      = 15;
    public static final int BEFORE_RULE_ADDED          = 16;
    public static final int AFTER_RULE_ADDED           = 17;
    public static final int BEFORE_RULE_REMOVED        = 18;
    public static final int AFTER_RULE_REMOVED         = 19;

    private int             type;

    /**
     * Creates a new log event.
     * 
     * @param type The type of the log event.
     */
    public LogEvent(final int type) {
        this.type = type;
    }

    /**
     * Returns the type of the log event as defined in this class.
     * 
     * @return The type of the log event.
     */
    public int getType() {
        return this.type;
    }

}
