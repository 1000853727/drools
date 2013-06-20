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

package org.drools.runtime;

public class EnvironmentName {
    public static final String TRANSACTION_MANAGER                  = "drools.transaction.TransactionManager";
    public static final String TRANSACTION_SYNCHRONIZATION_REGISTRY = "drools.transaction.TransactionSynchronizationRegistry";
    public static final String TRANSACTION                          = "drools.transaction.Transaction";

    public static final String ENTITY_MANAGER_FACTORY               = "drools.persistence.jpa.EntityManagerFactory";
    public static final String CMD_SCOPED_ENTITY_MANAGER            = "drools.persistence.jpa.CmdScopedEntityManager";
    public static final String APP_SCOPED_ENTITY_MANAGER            = "drools.persistence.jpa.AppScopedEntityManager";
    public static final String PERSISTENCE_CONTEXT_MANAGER          = "drools.persistence.PersistenceContextManager";

    public static final String OBJECT_MARSHALLING_STRATEGIES        = "drools.marshalling.ObjectMarshallingStrategies";
    public static final String GLOBALS                              = "drools.Globals";
    public static final String CALENDARS                            = "org.drools.core.time.Calendars";
    public static final String DATE_FORMATS                         = "org.drools.build.DateFormats";
}
