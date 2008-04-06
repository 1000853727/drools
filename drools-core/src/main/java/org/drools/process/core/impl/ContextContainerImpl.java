package org.drools.process.core.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.process.core.Context;
import org.drools.process.core.ContextContainer;

/**
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class ContextContainerImpl implements Serializable, ContextContainer {

    private static final long serialVersionUID = 400L;

    private Map<String, Context> defaultContexts = new HashMap<String, Context>();
    private Map<String, List<Context>> subContexts = new HashMap<String, List<Context>>();
    private long lastContextId;

    public List<Context> getContexts(String contextType) {
        return this.subContexts.get(contextType);
    }
    
    public void addContext(Context context) {
        List<Context> list = this.subContexts.get(context.getType());
        if (list == null) {
            list = new ArrayList<Context>();
            this.subContexts.put(context.getType(), list);
        }
        if (!list.contains(context)) {
            list.add(context);
            context.setId(++lastContextId);
        }
    }
    
    public Context getContext(String contextType, long id) {
        List<Context> list = this.subContexts.get(contextType);
        if (list != null) {
            for (Context context: list) {
                if (context.getId() == id) {
                    return context;
                }
            }
        }
        return null;
    }

    public void setDefaultContext(Context context) {
        this.defaultContexts.put(context.getType(), context);
    }
    
    public Context getDefaultContext(String contextType) {
        return defaultContexts.get(contextType);
    }

}
