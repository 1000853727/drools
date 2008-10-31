/**
 *
 */
package org.drools.util;

import java.io.Externalizable;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ChainedProperties
    implements
    Externalizable {
    private List props;
    private List defaultProps;

    public ChainedProperties() {

    }

    public ChainedProperties(String confFileName) {
        this( null,
              confFileName );
    }

    public ChainedProperties(ClassLoader classLoader,
                             String confFileName) {
        this( classLoader,
              confFileName,
              true );
    }

    public ChainedProperties(ClassLoader classLoader,
                             String confFileName,
                             boolean populateDefaults) {
        if ( classLoader == null ) {
            classLoader = Thread.currentThread().getContextClassLoader();
            if ( classLoader == null ) {
                classLoader = this.getClass().getClassLoader();
            }
        }

        this.props = new ArrayList();
        this.defaultProps = new ArrayList();

        // Properties added in precedence order

        // System defined properties always get precedence
        addProperties( System.getProperties() );

        // System property defined properties file
        loadProperties( System.getProperty( "drools." + confFileName ),
                        this.props );

        // User home properties file
        loadProperties( System.getProperty( "user.home" ) + "/drools." + confFileName,
                        this.props );

        // Working directory properties file
        loadProperties( "drools." + confFileName,
                        this.props );

        // check META-INF directories for all known ClassLoaders
        ClassLoader confClassLoader = classLoader;
        if ( confClassLoader != null ) {
            loadProperties( getResources( "META-INF/drools." + confFileName,
                                          confClassLoader ),
                            this.props );
        }

        confClassLoader = getClass().getClassLoader();
        if ( confClassLoader != null && confClassLoader != classLoader ) {
            loadProperties( getResources( "META-INF/drools." + confFileName,
                                          confClassLoader ),
                            this.props );
        }

        confClassLoader = Thread.currentThread().getContextClassLoader();
        if ( confClassLoader != null && confClassLoader != classLoader ) {
            loadProperties( getResources( "META-INF/drools." + confFileName,
                                          confClassLoader ),
                            this.props );
        }

        confClassLoader = ClassLoader.getSystemClassLoader();
        if ( confClassLoader != null && confClassLoader != classLoader ) {
            loadProperties( getResources( "META-INF/drools." + confFileName,
                                          confClassLoader ),
                            this.props );
        }

        if ( !populateDefaults ) {
            return;
        }

        // load defaults
        confClassLoader = classLoader;
        if ( confClassLoader != null ) {
            loadProperties( getResources( "META-INF/drools.default." + confFileName,
                                          confClassLoader ),
                            this.defaultProps );
        }

        confClassLoader = getClass().getClassLoader();
        if ( confClassLoader != null && confClassLoader != classLoader ) {
            loadProperties( getResources( "META-INF/drools.default." + confFileName,
                                          confClassLoader ),
                            this.defaultProps );
        }

        confClassLoader = Thread.currentThread().getContextClassLoader();
        if ( confClassLoader != null && confClassLoader != classLoader ) {
            loadProperties( getResources( "META-INF/drools.default." + confFileName,
                                          confClassLoader ),
                            this.defaultProps );
        }

        confClassLoader = ClassLoader.getSystemClassLoader();
        if ( confClassLoader != null && confClassLoader != classLoader ) {
            loadProperties( getResources( "META-INF/drools.default." + confFileName,
                                          confClassLoader ),
                            this.defaultProps );
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        props   = (List)in.readObject();
        defaultProps    = (List)in.readObject();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(props);
        out.writeObject(defaultProps);
    }

    private Enumeration getResources(String name,
                                     ClassLoader classLoader) {
        Enumeration enumeration = null;
        try {
            enumeration = classLoader.getResources( name );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return enumeration;
    }

    public void addProperties(Properties properties) {
        this.props.add( properties );
    }

    public String getProperty(String key,
                              String defaultValue) {
        String value = null;
        for ( Iterator it = this.props.iterator(); it.hasNext(); ) {
            Properties props = (Properties) it.next();
            value = props.getProperty( key );
            if ( value != null ) {
                break;
            }
        }
        if ( value == null ) {
            for ( Iterator it = this.defaultProps.iterator(); it.hasNext(); ) {
                Properties props = (Properties) it.next();
                value = props.getProperty( key );
                if ( value != null ) {
                    break;
                }
            }
        }
        return (value != null) ? value : defaultValue;
    }

    public void mapStartsWith(Map map,
                              String startsWith,
                              boolean includeSubProperties) {
        for ( Iterator it = this.props.iterator(); it.hasNext(); ) {
            Properties props = (Properties) it.next();
            mapStartsWith( map,
                           props,
                           startsWith,
                           includeSubProperties );
        }

        for ( Iterator it = this.defaultProps.iterator(); it.hasNext(); ) {
            Properties props = (Properties) it.next();
            mapStartsWith( map,
                           props,
                           startsWith,
                           includeSubProperties );
        }
    }

    private void mapStartsWith(Map map,
                               Properties properties,
                               String startsWith,
                               boolean includeSubProperties) {
        Enumeration enumeration = properties.propertyNames();
        while ( enumeration.hasMoreElements() ) {
            String key = (String) enumeration.nextElement();
            if ( key.startsWith( startsWith ) ) {
                if ( !includeSubProperties && key.substring( startsWith.length() + 1 ).indexOf( '.' ) > 0 ) {
                    // +1 to the length, as we do allow the direct property, just not ones below it
                    // This key has sub properties beyond the given startsWith, so skip
                    continue;
                }
                if ( !map.containsKey( key ) ) {
                    map.put( key,
                             properties.getProperty( key ) );
                }

            }
        }
    }

    private void loadProperties(Enumeration enumeration,
                                List chain) {
        if ( enumeration == null ) {
            return;
        }

        while ( enumeration.hasMoreElements() ) {
            URL url = (URL) enumeration.nextElement();
            loadProperties( url,
                            chain );
        }
    }

    private void loadProperties(String fileName,
                                List chain) {
        if ( fileName != null ) {
            File file = new File( fileName );
            if ( file != null && file.exists() ) {
                try {
                    loadProperties( file.toURL(),
                                    chain );
                } catch ( MalformedURLException e ) {
                    throw new IllegalArgumentException( "file.toURL() failed for drools.packagebuilder.conf properties value '" + file + "'" );
                }
            } else {
                //throw new IllegalArgumentException( "drools.packagebuilder.conf is specified but cannot be found '" + file + "'" );
            }
        }
    }

    private void loadProperties(URL confURL,
                                List chain) {
        if ( confURL == null ) {
            return;
        }
        Properties properties = new Properties();
        try {
            properties.load( confURL.openStream() );
            chain.add( properties );
        } catch ( IOException e ) {
            //throw new IllegalArgumentException( "Invalid URL to properties file '" + confURL.toExternalForm() + "'" );
        }
    }
}