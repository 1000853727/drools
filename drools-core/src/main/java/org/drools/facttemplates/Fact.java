/*
 * Copyright 2002-2005 Peter Lin & RuleML.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://ruleml-dev.sourceforge.net/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.drools.facttemplates;

public interface Fact {

    /**
     * Return the value at the given field  index
     * @param id
     * @return
     */
    Object getFieldValue(int index);

    Object getFieldValue(String name);

    void setFieldValue(String name,
                       Object value);

    void setFieldValue(int index,
                       Object value);

    /**
     * Return the unique ID for the fact
     * @return
     */
    long getFactId();

    /**
     * Return the Deftemplate for the fact
     * @return
     */
    FactTemplate getFactTemplate();
}
