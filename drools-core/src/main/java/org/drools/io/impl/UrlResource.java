package org.drools.io.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.drools.io.Resource;
import org.drools.util.StringUtils;

/**
 * Borrowed gratuitously from Spring under ASL2.0.
 *
 */
public class UrlResource implements Resource {
    private URL url;

    public UrlResource(URL url) {
        this.url = getCleanedUrl( url,
                                  url.toString() );
    }

    public UrlResource(String path) {
        try {
            this.url = getCleanedUrl( new URL( path ),
                                      path );
        } catch ( MalformedURLException e ) {
            throw new IllegalArgumentException( "'" + path + "' path is malformed", e);
        }
    }
    
    /**
     * This implementation opens an InputStream for the given URL.
     * It sets the "UseCaches" flag to <code>false</code>,
     * mainly to avoid jar file locking on Windows.
     * @see java.net.URL#openConnection()
     * @see java.net.URLConnection#setUseCaches(boolean)
     * @see java.net.URLConnection#getInputStream()
     */
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        con.setUseCaches(false);
        return con.getInputStream();
    }
    
    public Reader getReader() throws IOException {
        return new InputStreamReader( getInputStream() );
    }
    

    /**
     * Determine a cleaned URL for the given original URL.
     * @param originalUrl the original URL
     * @param originalPath the original URL path
     * @return the cleaned URL
     * @see org.springframework.util.StringUtils#cleanPath
     */
    private URL getCleanedUrl(URL originalUrl,
                              String originalPath) {
        try {
            return new URL( StringUtils.cleanPath( originalPath ) );
        } catch ( MalformedURLException ex ) {
            // Cleaned URL path cannot be converted to URL
            // -> take original URL.
            return originalUrl;
        }
    }
    
    public URL getURL() throws IOException {
        return this.url;
    }
    
    public boolean hasURL() {
        return true;
    }        

    /**
     * This implementation compares the underlying URL references.
     */
    public boolean equals(Object obj) {
        return (obj == this || (obj instanceof UrlResource && this.url.equals( ((UrlResource) obj).url )));
    }

    /**
     * This implementation returns the hash code of the underlying URL reference.
     */
    public int hashCode() {
        return this.url.hashCode();
    }
    
    public String toString() {
        return "[UrlResource path='" + this.url.toExternalForm() + "']";
    }

}
