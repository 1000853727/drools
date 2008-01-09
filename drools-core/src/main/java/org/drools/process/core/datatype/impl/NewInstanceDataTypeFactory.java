package org.drools.process.core.datatype.impl;

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

import org.drools.process.core.datatype.DataType;
import org.drools.process.core.datatype.DataTypeFactory;

/**
 * A data type factory that always returns a new instance of a given class.
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class NewInstanceDataTypeFactory implements DataTypeFactory {

    private static final long serialVersionUID = 400L;
    
    private Class<? extends DataType> dataTypeClass;

    public NewInstanceDataTypeFactory(final Class<? extends DataType> dataTypeClass) {
        this.dataTypeClass = dataTypeClass;
    }

    public DataType createDataType() {
        try {
            return this.dataTypeClass.newInstance();
        } catch (final IllegalAccessException e) {
            throw new RuntimeException("Could not create data type for class "
                    + this.dataTypeClass, e);
        } catch (final InstantiationException e) {
            throw new RuntimeException("Could not create data type for class "
                    + this.dataTypeClass, e);
        }
    }

}
