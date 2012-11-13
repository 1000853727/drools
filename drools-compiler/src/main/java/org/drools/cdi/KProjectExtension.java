package org.drools.cdi;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.ProcessInjectionTarget;
import javax.enterprise.util.AnnotationLiteral;

import org.drools.kproject.KBaseImpl;
import org.drools.kproject.KProject;
import org.drools.kproject.KSessionImpl;
import org.kie.KnowledgeBase;
import org.kie.KnowledgeBaseFactory;
import org.kie.builder.CompositeKnowledgeBuilder;
import org.kie.builder.KnowledgeBuilder;
import org.kie.builder.KnowledgeBuilderFactory;
import org.kie.builder.ResourceType;
import org.kie.io.ResourceFactory;
import org.kie.runtime.StatefulKnowledgeSession;
import org.kie.runtime.StatelessKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

public class KProjectExtension
        implements
        Extension {

    private static final Logger                       log = LoggerFactory.getLogger( KProjectExtension.class );

    private Set<String>                               kBaseQNames;
    private Set<String>                               kSessionQNames;

    private Map<String, String>                       kBaseURLs;
    private Map<String, KProject>                     kProjects;
    private Map<String, org.drools.kproject.KBase>    kBases;
    private Map<String, org.drools.kproject.KSession> kSessions;

    public KProjectExtension() {}

    public void init() {
        kBaseURLs = new HashMap<String, String>();
        kProjects = new HashMap<String, KProject>();
        kBases = new HashMap<String, org.drools.kproject.KBase>();
        kSessions = new HashMap<String, org.drools.kproject.KSession>();
        buildKProjects();
    }

    <Object> void processInjectionTarget(@Observes ProcessInjectionTarget<Object> pit) {
        if ( kProjects == null ) {
            init();
        }

        // Find all uses of KBase and KSession and add to Set index
        if ( !pit.getInjectionTarget().getInjectionPoints().isEmpty() ) {
            for ( InjectionPoint ip : pit.getInjectionTarget().getInjectionPoints() ) {
                KBase kBase = ip.getAnnotated().getAnnotation( KBase.class );
                if ( kBase != null ) {
                    if ( kBaseQNames == null ) {
                        kBaseQNames = new HashSet<String>();
                    }
                    kBaseQNames.add( kBase.value() );
                }

                KSession kSession = ip.getAnnotated().getAnnotation( KSession.class );
                if ( kSession != null ) {
                    if ( kSessionQNames == null ) {
                        kSessionQNames = new HashSet<String>();
                    }
                    kSessionQNames.add( kSession.value() );
                }
            }
        }

    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd,
                            BeanManager bm) {
        if ( kProjects != null ) {
            // if kProjects null, processInjectionTarget was not called, so beans to create

            Map<String, KBaseBean> kBaseBeans = new HashMap<String, KProjectExtension.KBaseBean>();
            if ( kBaseQNames != null ) {
                for ( String kBaseQName : kBaseQNames ) {
                    KBaseBean bean = new KBaseBean( kBases.get( kBaseQName ),
                                                    kBaseURLs.get( kBaseQName ),
                                                    kBaseBeans );
                    kBaseBeans.put( kBaseQName, bean );
                    abd.addBean( bean );
                }
            }
            kBaseQNames = null;

            if ( kSessionQNames != null ) {
                for ( String kSessionQName : kSessionQNames ) {
                    org.drools.kproject.KSession kSession = kSessions.get( kSessionQName );
                    KBaseBean bean = kBaseBeans.get( ((KSessionImpl)kSession).getKBase().getQName() );
                    if ( "stateless".equals( kSession.getType() ) ) {
                        abd.addBean( new StatelessKSessionBean( kSession, bean ) );
                    } else {
                        abd.addBean( new StatefulKSessionBean( kSession, bean ) );
                    }
                }
            }
            kSessionQNames = null;
            
            kBaseURLs = null;
            kProjects = null;
            kBases = null;
            kSessions = null;            
        }
    }

    public static class KBaseBean
            implements
            Bean<KnowledgeBase> {
        static final Set<Type>            types = Collections.unmodifiableSet( new HashSet<Type>( Arrays.asList( KnowledgeBase.class, Object.class ) ) );

        private Set<Annotation>           qualifiers;

        private String                    urlPath;
        private org.drools.kproject.KBase kBaseModel;

        private KnowledgeBase             kBase;
        
        private Map<String, KBaseBean>    kBaseBeans;

        public KBaseBean(final org.drools.kproject.KBase kBaseModel,
                         String urlPath, 
                         Map<String, KBaseBean> kBaseBeans) {
            this.kBaseModel = kBaseModel;            
            this.urlPath = urlPath;
            this.kBaseBeans = kBaseBeans;
            this.qualifiers = Collections.unmodifiableSet( new HashSet<Annotation>( Arrays.asList( new AnnotationLiteral<Default>() {},
                                                                                                   new AnnotationLiteral<Any>() {},
                                                                                                   new KBase() {
                                                                                                       public Class< ? extends Annotation> annotationType() {
                                                                                                           return KBase.class;
                                                                                                       }

                                                                                                       public String value() {
                                                                                                           return kBaseModel.getQName();
                                                                                                       }
                                                                                                   }
                    ) ) );
        }

        public KnowledgeBase create(CreationalContext ctx) {            
            KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
            CompositeKnowledgeBuilder ckbuilder = kbuilder.batch();
            
            Set<String> includes = kBaseModel.getIncludes();
            if ( includes != null && !includes.isEmpty() ) {
                for ( String include : includes ) {
                    KBaseBean includeBean = kBaseBeans.get( include );
                    addFiles(ckbuilder, includeBean.getkBaseModel(), includeBean.getUrlPath() );
                }
            }
            addFiles(ckbuilder, kBaseModel, urlPath);

            ckbuilder.build();

            this.kBase = KnowledgeBaseFactory.newKnowledgeBase();
            this.kBase.addKnowledgePackages( kbuilder.getKnowledgePackages() );

            return this.kBase;
        }

        private void addFiles(CompositeKnowledgeBuilder ckbuilder, org.drools.kproject.KBase modelToAdd, String urlPathToAdd) {
            List<String> files = modelToAdd.getFiles();

            String rootPath = urlPathToAdd;
            if ( rootPath.lastIndexOf( ':' ) > 0 ) {
                rootPath = urlPathToAdd.substring( rootPath.lastIndexOf( ':' ) + 1 );
            }

            if ( urlPathToAdd.endsWith( ".jar" ) ) {  
                File actualZipFile = new File( rootPath );
                if ( !actualZipFile.exists() ) {
                    log.error( "Unable to build KBase:" + modelToAdd.getName() + " as jarPath cannot be found\n" + rootPath );
                   // return KnowledgeBaseFactory.newKnowledgeBase();
                }
                
                ZipFile zipFile = null;
                try {
                    zipFile = new ZipFile( actualZipFile );
                } catch ( Exception e ) {
                    log.error( "Unable to build KBase:" + modelToAdd.getName() + " as jar cannot be opened\n" + e.getMessage() );
                    // return KnowledgeBaseFactory.newKnowledgeBase();
                }  
       
                try {
                    for ( String file : files ) {
                        ZipEntry zipEntry = zipFile.getEntry( file );
                        ckbuilder.add( ResourceFactory.newInputStreamResource( zipFile.getInputStream( zipEntry ) ), ResourceType.DRL );
                    }
                } catch ( Exception e ) {
                    try {
                        zipFile.close();
                    } catch ( IOException e1 ) {
    
                    }
                    log.error( "Unable to build KBase:" + modelToAdd.getName() + " as jar cannot be read\n" + e.getMessage() );
                    // return KnowledgeBaseFactory.newKnowledgeBase();
                }
            } else {
                try {
                    for ( String file : files ) {
                        ckbuilder.add( ResourceFactory.newFileResource( new File(rootPath, file) ), ResourceType.DRL );
                    }
                } catch ( Exception e) {
                    log.error( "Unable to build KBase:" + modelToAdd.getName() + "\n" + e.getMessage() );
                }
            }
        }

        public org.drools.kproject.KBase getkBaseModel() {
            return kBaseModel;
        }

        public String getUrlPath() {
            return urlPath;
        }

        public KnowledgeBase getKnowledgeBase() {
            if ( this.kBase == null ) {
                create( null );
            }
            return this.kBase;
        }

        public void destroy(KnowledgeBase kBase,
                            CreationalContext ctx) {
            this.kBase = null;

            ctx.release();
        }

        public Class getBeanClass() {
            return KnowledgeBase.class;
        }

        public Set<InjectionPoint> getInjectionPoints() {
            return Collections.emptySet();
        }

        public String getName() {
            return null;
        }

        public Set<Annotation> getQualifiers() {
            return qualifiers;
        }

        public Class< ? extends Annotation> getScope() {
            return ApplicationScoped.class;
        }

        public Set<Class< ? extends Annotation>> getStereotypes() {
            return Collections.emptySet();
        }

        public Set<Type> getTypes() {
            return types;
        }

        public boolean isAlternative() {
            return false;
        }

        public boolean isNullable() {
            return false;
        }
    }

    public static class StatelessKSessionBean
            implements
            Bean<StatelessKnowledgeSession> {
        static final Set<Type>               types = Collections.unmodifiableSet( new HashSet<Type>( Arrays.asList( StatelessKnowledgeSession.class, Object.class ) ) );

        private Set<Annotation>              qualifiers;

        private org.drools.kproject.KBase    kBaseModel;
        private org.drools.kproject.KSession kSessionModel;
        
        private KBaseBean                    kBaseBean;

        public StatelessKSessionBean(final org.drools.kproject.KSession kSessionModel, KBaseBean kBaseBean) {
            this.kSessionModel = kSessionModel;
            this.kBaseBean = kBaseBean;
            this.kBaseModel = ((KSessionImpl) kSessionModel).getKBase();

            this.qualifiers = Collections.unmodifiableSet( new HashSet<Annotation>( Arrays.asList( new AnnotationLiteral<Default>() {},
                                                                                                   new AnnotationLiteral<Any>() {},
                                                                                                   new KSession() {
                                                                                                       public Class< ? extends Annotation> annotationType() {
                                                                                                           return KSession.class;
                                                                                                       }

                                                                                                       public String value() {
                                                                                                           return kSessionModel.getQName();
                                                                                                       }
                                                                                                   }
                    ) ) );
        }

        public StatelessKnowledgeSession create(CreationalContext ctx) {
            KnowledgeBase kBase = kBaseBean.getKnowledgeBase();
            return kBase.newStatelessKnowledgeSession();
        }

        public void destroy(StatelessKnowledgeSession kSession,
                            CreationalContext ctx) {
            ctx.release();
        }

        public Class getBeanClass() {
            return StatelessKnowledgeSession.class;
        }

        public Set<InjectionPoint> getInjectionPoints() {
            return Collections.emptySet();
        }

        public String getName() {
            return null;
        }

        public Set<Annotation> getQualifiers() {
            return qualifiers;
        }

        public Class< ? extends Annotation> getScope() {
            return ApplicationScoped.class;
        }

        public Set<Class< ? extends Annotation>> getStereotypes() {
            return Collections.emptySet();
        }

        public Set<Type> getTypes() {
            return types;
        }

        public boolean isAlternative() {
            return false;
        }

        public boolean isNullable() {
            return false;
        }
    }

    public static class StatefulKSessionBean
            implements
            Bean<StatefulKnowledgeSession> {
        static final Set<Type>               types = Collections.unmodifiableSet( new HashSet<Type>( Arrays.asList( StatefulKnowledgeSession.class, Object.class ) ) );

        private Set<Annotation>              qualifiers;

        private org.drools.kproject.KBase    kBaseModel;
        private org.drools.kproject.KSession kSessionModel;
        
        private KBaseBean                    kBaseBean;

        public StatefulKSessionBean(final org.drools.kproject.KSession kSessionModel, KBaseBean kBaseBean) {
            this.kSessionModel = kSessionModel;
            this.kBaseModel = ((KSessionImpl) kSessionModel).getKBase();
            this.kBaseBean = kBaseBean;

            this.qualifiers = Collections.unmodifiableSet( new HashSet<Annotation>( Arrays.asList( new AnnotationLiteral<Default>() {},
                                                                                                   new AnnotationLiteral<Any>() {},
                                                                                                   new KSession() {
                                                                                                       public Class< ? extends Annotation> annotationType() {
                                                                                                           return KSession.class;
                                                                                                       }

                                                                                                       public String value() {
                                                                                                           return kSessionModel.getQName();
                                                                                                       }
                                                                                                   }
                    ) ) );
        }

        public StatefulKnowledgeSession create(CreationalContext ctx) {
            KnowledgeBase kBase = kBaseBean.getKnowledgeBase();
            return kBase.newStatefulKnowledgeSession();
        }

        public void destroy(StatefulKnowledgeSession kBase,
                            CreationalContext ctx) {
            ctx.release();
        }

        public Class getBeanClass() {
            return StatefulKnowledgeSession.class;
        }

        public Set<InjectionPoint> getInjectionPoints() {
            return Collections.emptySet();
        }

        public String getName() {
            return null;
        }

        public Set<Annotation> getQualifiers() {
            return qualifiers;
        }

        public Class< ? extends Annotation> getScope() {
            return ApplicationScoped.class;
        }

        public Set<Class< ? extends Annotation>> getStereotypes() {
            return Collections.emptySet();
        }

        public Set<Type> getTypes() {
            return types;
        }

        public boolean isAlternative() {
            return false;
        }

        public boolean isNullable() {
            return false;
        }
    }

    public void buildKProjects() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        final Enumeration<URL> e;
        try {
            e = classLoader.getResources( "META-INF/kproject.xml" );
        } catch ( IOException exc ) {
            log.error( "Unable to find and build index of kproject.xml \n" + exc.getMessage() );
            return;
        }

        // Map of kproject urls
        Map<String, String> urls = new HashMap<String, String>();
        while ( e.hasMoreElements() ) {
            URL url = e.nextElement();;
            try {
                XStream xstream = new XStream();
                KProject kProject = (KProject) xstream.fromXML( url );
                String kProjectId = kProject.getGroupArtifactVersion().getGroupId() + ":" + kProject.getGroupArtifactVersion().getArtifactId();
                urls.put( kProjectId, fixURL( url ) );
                kProjects.put( kProjectId, kProject );
            } catch ( Exception exc ) {
                log.error( "Unable to build and build index of kproject.xml url=" + url.toExternalForm() + "\n" + exc.getMessage() );
            }
        }

        for ( KProject kProject : kProjects.values() ) {
            for ( org.drools.kproject.KBase kBase : kProject.getKBases().values() ) {
                kBases.put( kBase.getQName(), kBase );
                ((KBaseImpl) kBase).setKProject( kProject ); // should already be set, but just in case

                String kProjectId = kProject.getGroupArtifactVersion().getGroupId() + ":" + kProject.getGroupArtifactVersion().getArtifactId();
                kBaseURLs.put( kBase.getQName(), urls.get( kProjectId ) );
                for ( org.drools.kproject.KSession kSession : kBase.getKSessions().values() ) {
                    ((KSessionImpl) kSession).setKBase( kBase ); // should already be set, but just in case
                    kSessions.put( kSession.getQName(),
                                   kSession );
                }
            }
        }
    }

    private String fixURL(URL url) {
        String urlPath = url.toExternalForm();

        // determine resource type (eg: jar, file, bundle)
        String urlType = "file";
        int colonIndex = urlPath.indexOf( ":" );
        if ( colonIndex != -1 ) {
            urlType = urlPath.substring( 0, colonIndex );
        }

        urlPath = url.getPath();
        

        if ( "jar".equals( urlType ) ) {
            // switch to using getPath() instead of toExternalForm()
           
            if ( urlPath.indexOf( '!' ) > 0 ) {
                urlPath = urlPath.substring( 0, urlPath.indexOf( '!' ) );
            }
        } else {
            urlPath = urlPath.substring( 0, urlPath.length() - "/META-INF/kproject.xml".length() );
        }

        
        // remove any remaining protocols, normally only if it was a jar
        colonIndex = urlPath.lastIndexOf( ":" );
        if ( colonIndex >= 0 ) {
            urlPath = urlPath.substring( colonIndex +  1  );
        }
        
        try {
            urlPath = URLDecoder.decode( urlPath, "UTF-8" );
        } catch ( UnsupportedEncodingException e ) {
            throw new IllegalArgumentException( "Error decoding URL (" + url + ") using UTF-8", e );
        }

        log.debug( "KProject URL Type + URL: " + urlType + ":" + urlPath );

        return urlPath;
    }
}
