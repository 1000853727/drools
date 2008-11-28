package org.drools.io.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.drools.io.Resource;

public class EncodedResource implements Resource {
    private final Resource resource;

    private final String encoding;


    /**
     * Create a new EncodedResource for the given Resource,
     * not specifying a specific encoding.
     * @param resource the Resource to hold
     */
    public EncodedResource(Resource resource) {
        this(resource, null);
    }

    /**
     * Create a new EncodedResource for the given Resource,
     * using the specified encoding.
     * @param resource the Resource to hold
     * @param encoding the encoding to use for reading from the resource
     */
    public EncodedResource(Resource resource, String encoding) {
        if ( resource == null ) {
            throw new IllegalArgumentException( "resource cannot be null" );
        }
        this.resource = resource;
        this.encoding = encoding;
    }

    public URL getURL() throws IOException {
        return this.resource.getURL();
    }

    public boolean hasURL() {
        return this.resource.hasURL();
    }    
    
    /**
     * Return the Resource held.
     */
    public final Resource getResource() {
        return this.resource;
    }

    /**
     * Return the encoding to use for reading from the resource,
     * or <code>null</code> if none specified.
     */
    public final String getEncoding() {
        return this.encoding;
    }
    
    /**
     * Open a <code>java.io.Reader</code> for the specified resource,
     * using the specified encoding (if any).
     * @throws IOException if opening the Reader failed
     */
    public Reader getReader() throws IOException {
        if (this.encoding != null) {
            return new InputStreamReader(this.resource.getInputStream(), this.encoding);
        }
        else {
            return new InputStreamReader(this.resource.getInputStream());
        }
    }    
    
    public InputStream getInputStream() throws IOException {
        return this.resource.getInputStream();
    }    
    
    public String toString() {
        return "[EncodedResource resource=" + this.resource + " encoding='" + this.encoding + "']";
    }    

}
