package org.drools.builder.impl;

import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.ResourceType;
import org.drools.builder.ResourceConfiguration;
import org.drools.compiler.PackageBuilder;
import org.drools.definition.KnowledgePackage;
import org.drools.definitions.impl.KnowledgePackageImp;
import org.drools.io.Resource;
import org.drools.io.impl.ReaderResource;
import org.drools.io.impl.UrlResource;
import org.drools.rule.Package;

public class KnowledgeBuilderImpl implements KnowledgeBuilder {
	public PackageBuilder pkgBuilder;

	public KnowledgeBuilderImpl(PackageBuilder pkgBuilder) {
		this.pkgBuilder = pkgBuilder;
	}
	
	public void add(Resource resource, ResourceType type) {
        pkgBuilder.addKnowledgeResource( resource, type, null )  ;
	}	

    public void add(Resource resource,
                            ResourceType type,
                            ResourceConfiguration configuration) {
        pkgBuilder.addKnowledgeResource( resource, type, configuration );
    }	
	
	public Collection<KnowledgePackage> getKnowledgePackages() {
	    if ( pkgBuilder.hasErrors() ) {
	        return new ArrayList<KnowledgePackage>( 0 );
	    }
	    
		Package[] pkgs = pkgBuilder.getPackages();
		List<KnowledgePackage> list = new ArrayList<KnowledgePackage>( pkgs.length );
		
		for ( Package pkg : pkgs ) {
			list.add( new KnowledgePackageImp( pkg ) );
		}
		
		return list;
	}	
	
	
	public boolean hasErrors() {
	    return this.pkgBuilder.hasErrors();
	}

    public KnowledgeBuilderErrors getErrors() {
        return this.pkgBuilder.getErrors();
    }
    
    public PackageBuilder getPackageBuilder() {
        return this.pkgBuilder;
    }
}
