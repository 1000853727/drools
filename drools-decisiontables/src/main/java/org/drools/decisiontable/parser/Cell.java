package org.drools.decisiontable.parser;

import java.util.Map;

import org.drools.StatefulSession;

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
 * @author <a href="mailto:stevearoonie@gmail.com">Steven Williams</a>
 * 
 * A cell in a decision table
 */
public interface Cell {
	public Row getRow();

	public Column getColumn();
	
	public void setValue(String value);

	public void addValue(Map vars);
	
	public void insert(StatefulSession session);

	public void setIndex(int i);
	
	public int getIndex();

	public boolean isEmpty();
}