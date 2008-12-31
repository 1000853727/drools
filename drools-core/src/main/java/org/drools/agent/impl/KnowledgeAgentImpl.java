package org.drools.agent.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import org.drools.ChangeSet;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.SystemEventListener;
import org.drools.RuleBase;
import org.drools.SystemEventListenerFactory;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentConfiguration;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.common.AbstractRuleBase;
import org.drools.definition.KnowledgeDefinition;
import org.drools.definition.process.Process;
import org.drools.event.io.ResourceChangeListener;
import org.drools.impl.KnowledgeBaseImpl;
import org.drools.io.InternalResource;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.io.impl.ClassPathResource;
import org.drools.io.impl.ResourceChangeNotifierImpl;
import org.drools.rule.Function;
import org.drools.rule.Package;
import org.drools.rule.Rule;
import org.drools.rule.TypeDeclaration;
import org.drools.util.DroolsStreamUtils;
import org.drools.xml.ChangeSetSemanticModule;
import org.drools.xml.SemanticModules;
import org.drools.xml.XmlChangeSetReader;

public class KnowledgeAgentImpl
    implements
    KnowledgeAgent,
    ResourceChangeListener {
    private String                         name;
    private Map<Resource, ResourceMapping> resources;
    private Set<Resource>                  resourceDirectories;
    private KnowledgeBase                  kbase;
    private ResourceChangeNotifierImpl     notifier;
    private boolean                        newInstance;
    private SystemEventListener            listener;
    private boolean                        scanDirectories;
    private LinkedBlockingQueue<ChangeSet> queue;
    private Thread                         thread;
    private ChangeSetNotificationDetector  changeSetNotificationDetector;
    private SemanticModules                semanticModules;

    public KnowledgeAgentImpl(String name,
                              KnowledgeBase kbase,
                              KnowledgeAgentConfiguration configuration) {
        this.kbase = kbase;
        this.resources = new HashMap<Resource, ResourceMapping>();
        this.resourceDirectories = new HashSet<Resource>();
        //this.listener = listener;
        this.listener = SystemEventListenerFactory.getSystemEventListener();
        this.newInstance = true; // we hard code this for now as incremental kbase changes don't work.
        this.queue = new LinkedBlockingQueue<ChangeSet>();
        boolean scanResources = false;
        boolean monitor = false;
        if ( configuration != null ) {
            //this.newInstance = ((KnowledgeAgentConfigurationImpl) configuration).isNewInstance();
            this.notifier = (ResourceChangeNotifierImpl) ResourceFactory.getResourceChangeNotifierService();
            if ( ((KnowledgeAgentConfigurationImpl) configuration).isMonitorChangeSetEvents() ) {
                monitor = true;
            }

            if ( ((KnowledgeAgentConfigurationImpl) configuration).isScanDirectories() ) {
                this.scanDirectories = true;
            }

            scanResources = ((KnowledgeAgentConfigurationImpl) configuration).isScanResources();
            if ( scanResources ) {
                this.notifier.addResourceChangeMonitor( ResourceFactory.getResourceChangeScannerService() );
                monitor = true; // if scanning, monitor must be true;
            }
        }

        monitorResourceChangeEvents( monitor );

        buildResourceMapping( kbase );

        this.listener.info( "KnowledgAgent created, with configuration:\nmonitorChangeSetEvents=" + monitor + " scanResources=" + scanResources + " scanDirectories=" + this.scanDirectories );
    }

    public void setSystemEventListener(SystemEventListener listener) {
        this.listener = listener;
    }

    public void applyChangeSet(Resource resource) {
        applyChangeSet( getChangeSet( resource ) );
    }

    public void applyChangeSet(ChangeSet changeSet) {
        this.listener.info( "KnowledgAgent applying ChangeSet" );
        ChangeSetState changeSetState = new ChangeSetState();
        changeSetState.scanDirectories = this.scanDirectories;
        processChangeSet( changeSet,
                          changeSetState );

        rebuildResources( changeSetState );
        buildResourceMapping( this.kbase );
    }

    public void processChangeSet(Resource resource,
                                 ChangeSetState changeSetState) {
        processChangeSet( getChangeSet( resource ),
                          changeSetState );
    }

    public void processChangeSet(ChangeSet changeSet,
                                 ChangeSetState changeSetState) {
        for ( Resource resource : changeSet.getResourcesAdded() ) {
            if ( ((InternalResource) resource).isDirectory() ) {
                this.resourceDirectories.add( resource );
                this.listener.debug( "KnowledgeAgent subscribing to directory=" + resource );
                this.notifier.subscribeResourceChangeListener( this,
                                                               resource );
                // if it's a dir, subscribe it's children first
                for ( Resource child : ((InternalResource) resource).listResources() ) {
                    if ( ((InternalResource) child).isDirectory() ) {
                        continue; // ignore sub directories
                    }
                    ((InternalResource) child).setResourceType( ((InternalResource) resource).getResourceType() );
                    ResourceMapping mapping = this.resources.get( child );
                    if ( mapping == null ) {
                        this.listener.debug( "KnowledgeAgent subscribing to directory content resource=" + child );
                        this.notifier.subscribeResourceChangeListener( this,
                                                                       child );
                        mapping = new ResourceMapping( child );
                        this.resources.put( child,
                                            mapping );
                    }
                }
            } else {
                if ( ((InternalResource) resource).getResourceType() == ResourceType.PKG ) {
                    changeSetState.pkgs.add( resource );
                } else if ( ((InternalResource) resource).getResourceType() == ResourceType.ChangeSet ) {
                    // @TODO
                    continue;
                }

                ResourceMapping mapping = this.resources.get( resource );
                if ( mapping == null ) {
                    this.listener.debug( "KnowledgeAgent subscribing to resource=" + resource );
                    this.notifier.subscribeResourceChangeListener( this,
                                                                   resource );
                    mapping = new ResourceMapping( resource );
                    this.resources.put( resource,
                                        mapping );
                }
            }
        }

        for ( Resource resource : changeSet.getResourcesRemoved() ) {
            if ( ((InternalResource) resource).getResourceType() == ResourceType.ChangeSet ) {
                processChangeSet( resource,
                                  changeSetState );
            } else if ( changeSetState.scanDirectories && ((InternalResource) resource).isDirectory() ) {
                this.listener.debug( "KnowledgeAgent unsubscribing from directory resource=" + resource );
                this.resourceDirectories.remove( resource );
                this.notifier.unsubscribeResourceChangeListener( this,
                                                                 resource );
            } else {
                this.listener.debug( "KnowledgeAgent unsubscribing from resource=" + resource );
                this.resources.remove( resource );
                this.notifier.unsubscribeResourceChangeListener( this,
                                                                 resource );
            }
        }

        // are we going to need kbuilder to build these resources?
        for ( Resource resource : this.resources.keySet() ) {
            this.listener.debug( "KnowledgeAgent ChangeSet requires KnowledgeBuilder" );
            if ( ((InternalResource) resource).getResourceType() != ResourceType.ChangeSet && ((InternalResource) resource).getResourceType() != ResourceType.PKG ) {
                changeSetState.needsKnowledgeBuilder = true;
                break;
            }
        }

    }

    public ChangeSet getChangeSet(Resource resource) {
        if ( this.semanticModules == null ) {
            this.semanticModules = new SemanticModules();
            this.semanticModules.addSemanticModule( new ChangeSetSemanticModule() );
        }

        XmlChangeSetReader reader = new XmlChangeSetReader( this.semanticModules );
        if ( resource instanceof ClassPathResource ) {
            reader.setClassLoader( ((ClassPathResource) resource).getClassLoader() );
        } else {
            reader.setClassLoader( ((AbstractRuleBase) (((KnowledgeBaseImpl) this.kbase).ruleBase)).getConfiguration().getClassLoader() );
        }

        ChangeSet changeSet = null;
        try {
            changeSet = reader.read( resource.getReader() );
        } catch ( Exception e ) {
            this.listener.exception( new RuntimeException( "Unable to parse ChangeSet",
                                                           e ) );
        }
        if ( changeSet == null ) {
            this.listener.exception( new RuntimeException( "Unable to parse ChangeSet" ) );
        }
        return changeSet;
    }

    public static class ChangeSetState {
        List<Resource> pkgs = new ArrayList<Resource>();
        boolean        scanDirectories;
        boolean        needsKnowledgeBuilder;
    }

    public void buildResourceMapping(KnowledgeBase kbase) {
        RuleBase rbase = ((KnowledgeBaseImpl) kbase).ruleBase;
        this.listener.debug( "KnowledgeAgent building resource map" );
        synchronized ( this.resources ) {

            for ( Package pkg : rbase.getPackages() ) {
                for ( Rule rule : pkg.getRules() ) {
                    Resource resource = rule.getResource();
                    if ( resource == null || !((InternalResource) resource).hasURL() ) {
                        continue;
                    }
                    ResourceMapping mapping = this.resources.get( resource );
                    if ( mapping == null ) {
                        this.notifier.subscribeResourceChangeListener( this,
                                                                       resource );
                        mapping = new ResourceMapping( resource );
                        this.resources.put( resource,
                                            mapping );
                    }
                    this.listener.debug( "KnowledgeAgent mapping resource=" + resource + " to rule=" + rule );
                    mapping.getKnowledgeDefinitions().add( rule );
                }

                for ( Process process : pkg.getRuleFlows().values() ) {
                    Resource resource = ((org.drools.process.core.Process) process).getResource();
                    if ( resource == null || !((InternalResource) resource).hasURL() ) {
                        continue;
                    }
                    ResourceMapping mapping = this.resources.get( resource );
                    if ( mapping == null ) {
                        this.notifier.subscribeResourceChangeListener( this,
                                                                       resource );
                        mapping = new ResourceMapping( resource );
                        this.resources.put( resource,
                                            mapping );
                    }
                    this.listener.debug( "KnowledgeAgent mapping resource=" + resource + " to process=" + process );
                    mapping.getKnowledgeDefinitions().add( process );
                }

                for ( TypeDeclaration typeDeclaration : pkg.getTypeDeclarations().values() ) {
                    Resource resource = typeDeclaration.getResource();
                    if ( resource == null || !((InternalResource) resource).hasURL() ) {
                        continue;
                    }
                    ResourceMapping mapping = this.resources.get( resource );
                    if ( mapping == null ) {
                        this.notifier.subscribeResourceChangeListener( this,
                                                                       resource );
                        mapping = new ResourceMapping( resource );
                        this.resources.put( resource,
                                            mapping );
                    }
                    this.listener.debug( "KnowledgeAgent mapping resource=" + resource + " to TypeDeclaration=" + typeDeclaration );
                    mapping.getKnowledgeDefinitions().add( typeDeclaration );
                }

                for ( Function function : pkg.getFunctions().values() ) {
                    Resource resource = function.getResource();
                    if ( resource == null || !((InternalResource) resource).hasURL() ) {
                        continue;
                    }
                    ResourceMapping mapping = this.resources.get( resource );
                    if ( mapping == null ) {
                        this.notifier.subscribeResourceChangeListener( this,
                                                                       resource );
                        mapping = new ResourceMapping( resource );
                        this.resources.put( resource,
                                            mapping );
                    }
                    this.listener.debug( "KnowledgeAgent mapping resource=" + resource + " to function=" + function );
                    mapping.getKnowledgeDefinitions().add( function );
                }
            }
        }
    }

    public KnowledgeBase getKnowledgeBase() {
        synchronized ( this.resources ) {
            return this.kbase;
        }
    }

    //    public void resourceModified(ResourceModifiedEvent event) {
    //        ResourceMapping mapping = this.resources.get( event.getResource() );
    //        System.out.println( "modified : " + event.getResource() );
    //        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    //        synchronized ( this.resources ) {
    //            for ( Resource resource : this.resources.keySet() ) {
    //                System.out.println( "building : " + resource );
    //                kbuilder.add( resource,
    //                              KnowledgeType.DRL );
    //            }
    //
    //            this.kbase = KnowledgeBaseFactory.newKnowledgeBase();
    //            this.kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
    //        }
    //    }

    public void resourcesChanged(ChangeSet changeSet) {
        try {
            this.listener.debug( "KnowledgeAgent received ChangeSet changed notification" );
            this.queue.put( changeSet );
        } catch ( InterruptedException e ) {
            this.listener.exception( new RuntimeException( "KnowledgeAgent error while adding ChangeSet notification to queue",
                                                           e ) );
        }
    }

    private void rebuildResources(ChangeSetState changeSetState) {
        this.listener.debug( "KnowledgeAgent rebuilding KnowledgeBase using ChangeSet" );
        synchronized ( this.resources ) {
            for ( Resource child : changeSetState.pkgs ) {

                try {
                    InputStream is = child.getInputStream();
                    Package pkg = (Package) DroolsStreamUtils.streamIn( is );
                    this.listener.debug( "KnowledgeAgent adding KnowledgeDefinitionsPackage " + pkg.getName() );
                    ((KnowledgeBaseImpl) this.kbase).ruleBase.addPackage( pkg );
                    is.close();
                } catch ( Exception e ) {
                    this.listener.exception( new RuntimeException( "KnowledgeAgent exception while trying to serialize and KnowledgeDefinitionsPackage  " ) );
                }
            }

            if ( changeSetState.needsKnowledgeBuilder ) {
                // modified we already know is in the map, so no need to process those

                // now make a copy of the resource keys, as we are about to reset it, but need the keys to rebuild the kbase
                Resource[] resourcesClone = this.resources.keySet().toArray( new Resource[this.resources.size()] );

                // reset the resources map, so it can now be rebuilt
                this.resources.clear();

                // rebuild the kbase
                KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

                for ( Resource resource : resourcesClone ) {
                    this.listener.debug( "KnowledgeAgent building resource=" + resource );
                    if ( ((InternalResource) resource).getResourceType() != ResourceType.PKG ) {
                        // .pks are handled as a special case.
                        kbuilder.add( resource,
                                      ((InternalResource) resource).getResourceType() );
                    }
                }

                if ( kbuilder.hasErrors() ) {
                    this.listener.warning( "KnowledgeAgent has KnowledgeBuilder errors ",
                                           kbuilder.getErrors() );
                }

                this.kbase = KnowledgeBaseFactory.newKnowledgeBase();
                this.kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
                this.listener.info( "KnowledgeAgent new KnowledgeBase now built and in use" );
            }
        }

        // code commented out to try and do incremental kbase changes
        // @TODO get this working for incremental changes
        //        synchronized ( this.resources ) {
        //            // first deal with removals
        //            for ( Resource resource : changeSet.getResourcesRemoved() ) {
        //                ResourceMapping mapping = this.resources.remove(resource );
        //                if ( !this.newInstance  ) {
        //                    // we are keeping the current instance, so we need remove the individual knowledge definitions           
        //                    for ( KnowledgeDefinition kd : mapping.getKnowledgeDefinitions() ) {
        //                        if ( kd instanceof Rule ) {
        //                            Rule rule = ( Rule ) kd;
        //                            this.kbase.removeRule( rule.getPackageName(), rule.getName() );
        //                        } else if ( kd instanceof Process ) {
        //                            Process process = ( Process ) kd;
        //                            this.kbase.removeProcess( process.getId() );
        //                        }
        //                        // @TODO functions and type declarations
        //                    }
        //                }
        //            }
        //            
        //            // now deal with additions
        //            for ( Resource resource : changeSet.getResourcesAdded() ) {
        //                
        //            }
        //            
        //            // final deal with modifies
        //        }        
    }

    public String getName() {
        return this.name;
    }

    public void monitorResourceChangeEvents(boolean monitor) {
        if ( this.changeSetNotificationDetector == null ) {
            if ( monitor ) {
                // we are going to start the monitor, so initialise it
                this.changeSetNotificationDetector = new ChangeSetNotificationDetector( this,
                                                                                        this.queue,
                                                                                        this.listener );
            } else if ( this.changeSetNotificationDetector == null ) {
                // do nothing, we aren't starting the monitor and the monitorResourceChangeEvents field is null
                return;
            }
        }

        if ( !this.changeSetNotificationDetector.monitor && monitor ) {
            // If the thread is not running and we are trying to start it, we must create a new Thread
            this.thread = new Thread( this.changeSetNotificationDetector );
            this.changeSetNotificationDetector.monitor = true;
            this.thread.start();
        } else {
            // this will stop the thread
            this.changeSetNotificationDetector.monitor = false;
            this.thread.interrupt();
        }
    }

    public static class ChangeSetNotificationDetector
        implements
        Runnable {
        private LinkedBlockingQueue<ChangeSet> queue;
        public volatile boolean                monitor;
        private KnowledgeAgentImpl             kagent;
        private SystemEventListener            listener;

        public ChangeSetNotificationDetector(KnowledgeAgentImpl kagent,
                                             LinkedBlockingQueue<ChangeSet> queue,
                                             SystemEventListener listener) {
            this.queue = queue;
            this.kagent = kagent;
            this.listener = listener;
        }

        public void run() {
            if ( this.monitor ) {
                this.listener.info( "KnowledegAgent has started listening for ChangeSet notifications" );
            }
            while ( this.monitor ) {
                try {
                    kagent.applyChangeSet( this.queue.take() );
                } catch ( InterruptedException e ) {
                    this.listener.exception( new RuntimeException( "KnowledgeAgent ChangeSet notification thread has been interrupted",
                                                                   e ) );
                }
                Thread.yield();
            }

            this.listener.info( "KnowledegAgent has stopped listening for ChangeSet notifications" );
        }
    }

    public static class ResourceMapping {
        private Resource                 resource;
        private Set<KnowledgeDefinition> knowledgeDefinitions;

        public ResourceMapping(Resource resource) {
            this.knowledgeDefinitions = new HashSet<KnowledgeDefinition>();
        }

        public Resource getResource() {
            return resource;
        }

        public Set<KnowledgeDefinition> getKnowledgeDefinitions() {
            return knowledgeDefinitions;
        }

    }

    @Override
    protected void finalize() throws Throwable {
        // users should turn off monitoring, but just in case when this class is GC'd we turn off the thread
        if ( this.changeSetNotificationDetector != null ) {
            this.changeSetNotificationDetector.monitor = false;
        }
    }
}
