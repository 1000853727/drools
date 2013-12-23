/*
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

package org.drools.core.util;

import java.io.IOException;
import java.io.InputStream;

import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.RuntimeDroolsException;
import org.drools.core.rule.Package;

/**
 * This loads up rulebases from binary packages.
 * Can work with an existing or a new rulebase.
 * This is useful for deployment.
 */
public class BinaryRuleBaseLoader {

    private RuleBase    ruleBase;
    private ClassLoader classLoader;

    /**
     * This will create a new default rulebase (which is initially empty).
     * Optional parent classLoader for the Package's internal ClassLoader
     * is Thread.currentThread.getContextClassLoader()
     */
    public BinaryRuleBaseLoader() {
        this( RuleBaseFactory.newRuleBase(), null );
    }

    /**
     * This will add any binary packages to the rulebase.
     * Optional parent classLoader for the Package's internal ClassLoader
     * is Thread.currentThread.getContextClassLoader()
     */
    public BinaryRuleBaseLoader(RuleBase rb) {
        this( rb, null);
    }

    /**
     * This will add any binary packages to the rulebase.
     * Optional classLoader to be used as the parent ClassLoader
     * for the Package's internal ClassLoader, is Thread.currentThread.getContextClassLoader()
     * if not user specified.
     */
    public BinaryRuleBaseLoader(RuleBase rb, ClassLoader classLoader) {
        if ( classLoader == null ) {
            classLoader = Thread.currentThread().getContextClassLoader();
            if ( classLoader == null ) {
                classLoader = Thread.currentThread().getContextClassLoader();
            }
        }
        this.ruleBase = rb;
        this.classLoader = classLoader;
    }

    /**
     * This will add the BINARY package to the rulebase.
     * Uses the member ClassLoader as the Package's internal parent classLoader
     * which is Thread.currentThread.getContextClassLoader if not user specified
     * @param in An input stream to the serialized package.
     */
    public void addPackage(InputStream in) {
        addPackage(in, this.classLoader);
    }

    /**
     * This will add the BINARY package to the rulebase.
     * 
     * @param in
     *            An input stream to the serialized package.
     * @param classLoader
     *            used as the parent ClassLoader for the Package's internal
     *            ClassLaoder
     */
    public void addPackage(InputStream in,
                           ClassLoader classLoader) {
        if ( classLoader == null ) {
            classLoader = this.classLoader;
        }

        try {
            
            Object opkg = DroolsStreamUtils.streamIn( in,
                                                      classLoader );

            if ( opkg instanceof Package ) {
                Package pkg = (Package) opkg;
                addPackage( pkg );
            } else if ( opkg instanceof Package[] ) {
                Package[] pkgs = (Package[]) opkg;
                for ( Package pkg : pkgs ) {
                    addPackage( pkg );
                }
            } else {
                throw new IllegalArgumentException( "Can only add instances of org.kie.rule.Package to a rulebase instance." );
            }

        } catch ( IOException e ) {
            throw new RuntimeDroolsException( e );
        } catch ( ClassNotFoundException e ) {
            throw new RuntimeDroolsException( e );
        } finally {
            try {
                in.close();
            } catch ( IOException e ) {
                throw new RuntimeException( e );
            }
        }

    }
    
    private void addPackage(Package pkg) {
        if ( !pkg.isValid() ) {
            throw new IllegalArgumentException( "Can't add a non valid package to a rulebase." );
        }
        try {
            this.ruleBase.addPackage( pkg );
        } catch ( Exception e ) {
            throw new RuntimeDroolsException( "Unable to add package to the rulebase.",
                                              e );
        }
    }

    public RuleBase getRuleBase() {
        return this.ruleBase;
    }

}
