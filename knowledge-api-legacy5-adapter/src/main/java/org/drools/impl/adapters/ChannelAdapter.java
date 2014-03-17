package org.drools.impl.adapters;

import org.drools.runtime.Channel;

public class ChannelAdapter implements org.kie.api.runtime.Channel {

    private final Channel delegate;

    public ChannelAdapter(Channel delegate) {
        this.delegate = delegate;
    }

    public void send(Object object) {
        delegate.send(object);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ChannelAdapter && delegate.equals(((ChannelAdapter)obj).delegate);
    }
}
