/**
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

package org.drools.xml.jaxb.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.drools.FactHandle;
import org.drools.common.DisconnectedFactHandle;
import org.drools.runtime.rule.impl.FlatQueryResults;
import org.drools.runtime.rule.impl.NativeQueryResults;


public class JaxbMapAdapter extends XmlAdapter<JaxbPair[], Map<String,Object>> {

	@Override
	public JaxbPair[] marshal(Map<String, Object> value) throws Exception {
		if (value == null || value.isEmpty()) {
			return new JaxbPair[0];
		}
		
		List<JaxbPair> ret = new ArrayList<JaxbPair>(value.size());
		for (Map.Entry<String, Object> entry : value.entrySet()) {
		    Object obj = entry.getValue();
		    Class<? extends Object> vClass = obj.getClass();
		    
		    if ( obj instanceof NativeQueryResults ) {
		        obj = new FlatQueryResults( ((NativeQueryResults )obj).getResults() );
		    } else if (obj instanceof FactHandle && !(obj instanceof DisconnectedFactHandle)) {
		        obj = new DisconnectedFactHandle(((FactHandle) obj).toExternalForm());
	        } else if (List.class.isAssignableFrom(vClass) && !JaxbListWrapper.class.equals(vClass)) {    
	                obj = new JaxbListWrapper( ((List<?>) obj).toArray( new Object[((List<?>) obj).size()]) );;
	        }
			ret.add(new JaxbPair(entry.getKey(), obj));
		}
		
		return ret.toArray(new JaxbPair[value.size()]);
	}

	@Override
	public Map<String, Object> unmarshal(JaxbPair[] value) throws Exception {
		Map<String, Object> r = new HashMap<String, Object>();
		for( JaxbPair p : value ) {
		    if ( p.getValue() instanceof JaxbListWrapper) {
		        r.put(p.getKey(), Arrays.asList( ((JaxbListWrapper)p.getValue()).getElements() ) );
		    } else {
		        r.put(p.getKey(), p.getValue());
		    }
		}
		return r;
	}

}
