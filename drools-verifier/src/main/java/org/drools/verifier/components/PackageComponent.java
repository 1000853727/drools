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

package org.drools.verifier.components;

import org.drools.verifier.data.VerifierComponent;

/**
 * 
 * @author Toni Rikkola
 */
public abstract class PackageComponent extends VerifierComponent {

    private String packageName;

    public PackageComponent(RulePackage rulePackage) {
        setPackageName( rulePackage.getName() );
    }

    protected PackageComponent(String packageName) {
        setPackageName( packageName );
    }

    public String getPackageName() {
        return packageName;
    }

    protected void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackagePath() {
        return String.format( "package[@name='%s']",
                              getPackageName() );
    }

}
