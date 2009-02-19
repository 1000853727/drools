package org.drools.io.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import org.drools.ChangeSet;
import org.drools.SystemEventListener;
import org.drools.SystemEventListenerFactory;
import org.drools.event.io.ResourceChangeListener;
import org.drools.io.Resource;
import org.drools.io.ResourceChangeMonitor;
import org.drools.io.ResourceChangeNotifier;

public class ResourceChangeNotifierImpl
    implements
    ResourceChangeNotifier {
    private Map<Resource, Set<ResourceChangeListener>> subscriptions;
    private List<ResourceChangeMonitor>                monitors;
    private SystemEventListener                        listener;

    private LinkedBlockingQueue<ChangeSet>             queue;

    public ResourceChangeNotifierImpl() {
        this.listener = SystemEventListenerFactory.getSystemEventListener();
        this.subscriptions = new HashMap<Resource, Set<ResourceChangeListener>>();
        this.monitors = new CopyOnWriteArrayList<ResourceChangeMonitor>();
        this.queue = new LinkedBlockingQueue<ChangeSet>();
        this.listener.info( "ResourceChangeNotification created" );
    }
    
    public void setSystemEventListener(SystemEventListener listener) {
        this.listener = listener;
    }

    public void addResourceChangeMonitor(ResourceChangeMonitor monitor) {
        if ( !this.monitors.contains( monitor ) ) {
            this.listener.debug( "ResourceChangeNotification monitor added monitor=" + monitor );
            this.monitors.add( monitor );
        }
    }

    public void removeResourceChangeMonitor(ResourceChangeMonitor monitor) {
        this.listener.debug( "ResourceChangeNotification monitor removed monitor=" + monitor );
        this.monitors.remove( monitor );
    }

    public Collection<ResourceChangeMonitor> getResourceChangeMonitors() {
        return Collections.unmodifiableCollection( this.monitors );
    }

    public void subscribeResourceChangeListener(ResourceChangeListener listener,
                                                Resource resource) {
        this.listener.debug( "ResourceChangeNotification subscribing listener=" + listener + " to resource=" + resource );
        synchronized ( this.subscriptions ) {
            Set<ResourceChangeListener> listeners = this.subscriptions.get( resource );
            if ( listeners == null ) {
                listeners = new HashSet<ResourceChangeListener>();
                this.subscriptions.put( resource,
                                        listeners );
                for ( ResourceChangeMonitor monitor : this.monitors ) {
                    monitor.subscribeNotifier( this,
                                               resource );
                }
            }
            listeners.add( listener );
        }
    }

    public void unsubscribeResourceChangeListener(ResourceChangeListener listener,
                                                  Resource resource) {
        this.listener.debug( "ResourceChangeNotification unsubscribing listener=" + listener + " to resource=" + resource );
        synchronized ( this.subscriptions ) {
            Set<ResourceChangeListener> listeners = this.subscriptions.get( resource );
            if ( listeners == null ) {
                return;
            }

            listeners.remove( listeners );

            if ( listeners.isEmpty() ) {
                this.subscriptions.remove( resource );
                for ( ResourceChangeMonitor monitor : this.monitors ) {
                    monitor.unsubscribeNotifier( this,
                                                 resource );
                }
            }
        }
    }

    public void subscribeChildResource(Resource directory,
                                       Resource child) {
        this.listener.debug( "ResourceChangeNotification subscribing directory=" + directory + " content resource=" + child );
        for ( ResourceChangeListener listener : this.subscriptions.get( directory ) ) {
            subscribeResourceChangeListener( listener,
                                             child );
        }
    }

    public void publishChangeSet(ChangeSet changeSet) {
        try {
            this.listener.debug( "ResourceChangeNotification received ChangeSet notification" );
            this.queue.put( changeSet );
        } catch ( InterruptedException e ) {
            this.listener.exception( new RuntimeException( "ResourceChangeNotification Exception while adding to notification queue",
                                                           e ) );
        }

    }

    public void processChangeSet(ChangeSet changeSet) {
        // this provides the complete published change set for this notifier.
        // however different listeners might be listening to different resources, so provide
        // listener change specified change sets.

        Map<ResourceChangeListener, ChangeSetImpl> localChangeSets = new HashMap<ResourceChangeListener, ChangeSetImpl>();

        this.listener.debug( "ResourceChangeNotification processing ChangeSet" );
        for ( Resource resource : changeSet.getResourcesAdded() ) {
            Set<ResourceChangeListener> listeners = this.subscriptions.get( resource );
            for ( ResourceChangeListener listener : listeners ) {
                ChangeSetImpl localChangeSet = localChangeSets.get( listener );

                if ( localChangeSet == null ) {
                    // lazy initialise changeSet
                    localChangeSet = new ChangeSetImpl();
                    localChangeSets.put( listener,
                                         localChangeSet );
                }
                if ( localChangeSet.getResourcesAdded().isEmpty() ) {
                    localChangeSet.setResourcesAdded( new ArrayList<Resource>() );
                }
                localChangeSet.getResourcesAdded().add( resource );
                this.listener.debug( "ResourceChangeNotification ChangeSet added resource=" + resource + " for listener=" + listener );

            }
        }

        for ( Resource resource : changeSet.getResourcesRemoved() ) {
            Set<ResourceChangeListener> listeners = this.subscriptions.remove( resource );
            for ( ResourceChangeListener listener : listeners ) {
                ChangeSetImpl localChangeSet = localChangeSets.get( listener );
                if ( localChangeSet == null ) {
                    // lazy initialise changeSet
                    localChangeSet = new ChangeSetImpl();
                    localChangeSets.put( listener,
                                         localChangeSet );
                }
                if ( localChangeSet.getResourcesRemoved().isEmpty() ) {
                    localChangeSet.setResourcesRemoved( new ArrayList<Resource>() );
                }
                localChangeSet.getResourcesRemoved().add( resource );
                this.listener.debug( "ResourceChangeNotification ChangeSet removed resource=" + resource + " for listener=" + listener );
            }
        }

        for ( Resource resource : changeSet.getResourcesModified() ) {
            Set<ResourceChangeListener> listeners = this.subscriptions.get( resource );
            for ( ResourceChangeListener listener : listeners ) {
                ChangeSetImpl localChangeSet = localChangeSets.get( listener );
                if ( localChangeSet == null ) {
                    // lazy initialise changeSet
                    localChangeSet = new ChangeSetImpl();
                    localChangeSets.put( listener,
                                         localChangeSet );
                }
                if ( localChangeSet.getResourcesModified().isEmpty() ) {
                    localChangeSet.setResourcesModified( new ArrayList<Resource>() );
                }
                localChangeSet.getResourcesModified().add( resource );
                this.listener.debug( "ResourceChangeNotification ChangeSet modified resource=" + resource + " for listener=" + listener );
            }
        }

        for ( Entry<ResourceChangeListener, ChangeSetImpl> entry : localChangeSets.entrySet() ) {
            ResourceChangeListener listener = entry.getKey();
            ChangeSetImpl localChangeSet = entry.getValue();
            listener.resourcesChanged( localChangeSet );
        }

        //        ResourceModifiedEvent event = new ResourceModifiedEventImpl( resource,
        //                                                                     resource.getLastModified() );
        //        Set<ResourceChangeListener> listeners = this.subscriptions.get( resource );
        //        
        //        if ( listeners != null ) {
        //            for ( ResourceChangeListener listener : listeners ) {
        //                listener.resourceModified( event );
        //            }
        //        }        
    }

    public void start() {
        if ( this.processChangeSet == null ) {
            this.processChangeSet = new ProcessChangeSet( this.queue,
                                                          this,
                                                          this.listener );
        }

        if ( !this.processChangeSet.isRunning() ) {
            this.processChangeSet.setNotify( true );
            this.thread = new Thread( this.processChangeSet );
            this.thread.start();
        }
    }

    public void stop() {
        this.processChangeSet.setNotify( false );
        this.thread.interrupt();
    }

    private Thread           thread;
    private ProcessChangeSet processChangeSet;

    public static class ProcessChangeSet
        implements
        Runnable {
        private volatile boolean               notify;
        private LinkedBlockingQueue<ChangeSet> queue;
        private ResourceChangeNotifierImpl     notifier;
        private SystemEventListener            listener;

        ProcessChangeSet(LinkedBlockingQueue<ChangeSet> queue,
                         ResourceChangeNotifierImpl notifier,
                         SystemEventListener listener) {
            this.queue = queue;
            this.notifier = notifier;
            this.listener = listener;
        }

        public void setNotify(boolean notify) {
            this.notify = notify;
        }

        public boolean isRunning() {
            return this.notify;
        }

        public void run() {
            if ( this.notify ) {
                this.listener.info( "ResourceChangeNotification has started listening for ChangeSet publications" );
            }
            while ( this.notify ) {
                Exception exception = null;
                try {
                    this.listener.debug( "ResourceChangeNotification thread is waiting for queue update" );
                    this.notifier.processChangeSet( this.queue.take() );
                } catch ( InterruptedException e ) {
                    exception = e;
                }
                Thread.yield();
                if ( this.notify && exception != null) {
                    this.listener.exception( new RuntimeException( "ResourceChangeNotification ChangeSet publication thread was interrupted, but shutdown was not scheduled",
                                                                   exception ) );
                }
            }
            this.listener.info( "ResourceChangeNotification has stopped listening for ChangeSet publications" );
        }
    }

}
