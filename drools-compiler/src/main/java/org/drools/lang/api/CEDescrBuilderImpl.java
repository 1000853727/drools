/**
 * Copyright 2011 JBoss Inc
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

package org.drools.lang.api;

import org.drools.lang.descr.AndDescr;
import org.drools.lang.descr.BaseDescr;
import org.drools.lang.descr.ConditionalElementDescr;
import org.drools.lang.descr.ExistsDescr;
import org.drools.lang.descr.ForallDescr;
import org.drools.lang.descr.NotDescr;
import org.drools.lang.descr.OrDescr;

/**
 * An implementation for the CEDescrBuilder
 */
public class CEDescrBuilderImpl<P extends DescrBuilder<?>, T extends BaseDescr> extends BaseDescrBuilderImpl<T>
    implements
    CEDescrBuilder<P, T> {

    private P parent;

    public CEDescrBuilderImpl(P parent, T descr) {
        super( descr );
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     */
    public CEDescrBuilder<CEDescrBuilder<P, T>, AndDescr> and() {
        CEDescrBuilder<CEDescrBuilder<P, T>, AndDescr> and = new CEDescrBuilderImpl<CEDescrBuilder<P, T>, AndDescr>( this, new AndDescr() );
        ((ConditionalElementDescr) descr).addDescr( and.getDescr() );
        return and;
    }

    /**
     * {@inheritDoc}
     */
    public CEDescrBuilder<CEDescrBuilder<P, T>, OrDescr> or() {
        CEDescrBuilder<CEDescrBuilder<P, T>, OrDescr> or = new CEDescrBuilderImpl<CEDescrBuilder<P, T>, OrDescr>( this, new OrDescr() );
        ((ConditionalElementDescr) descr).addDescr( or.getDescr() );
        return or;
    }

    /**
     * {@inheritDoc}
     */
    public CEDescrBuilder<CEDescrBuilder<P, T>, NotDescr> not() {
        CEDescrBuilder<CEDescrBuilder<P, T>, NotDescr> not = new CEDescrBuilderImpl<CEDescrBuilder<P, T>, NotDescr>( this, new NotDescr() );
        ((ConditionalElementDescr) descr).addDescr( not.getDescr() );
        return not;
    }

    /**
     * {@inheritDoc}
     */
    public CEDescrBuilder<CEDescrBuilder<P, T>, ExistsDescr> exists() {
        CEDescrBuilder<CEDescrBuilder<P, T>, ExistsDescr> exists = new CEDescrBuilderImpl<CEDescrBuilder<P, T>, ExistsDescr>( this, new ExistsDescr() );
        ((ConditionalElementDescr) descr).addDescr( exists.getDescr() );
        return exists;
    }

    /**
     * {@inheritDoc}
     */
    public CEDescrBuilder<CEDescrBuilder<P, T>, ForallDescr> forall() {
        CEDescrBuilder<CEDescrBuilder<P, T>, ForallDescr> forall = new CEDescrBuilderImpl<CEDescrBuilder<P, T>, ForallDescr>( this, new ForallDescr() );
        ((ConditionalElementDescr) descr).addDescr( forall.getDescr() );
        return forall;
    }

    /**
     * {@inheritDoc}
     */
    public EvalDescrBuilder<CEDescrBuilder<P, T>> eval() {
        EvalDescrBuilder<CEDescrBuilder<P, T>> eval = new EvalDescrBuilderImpl<CEDescrBuilder<P,T>>();
        ((ConditionalElementDescr) descr).addDescr( eval.getDescr() );
        return eval;
    }

    /**
     * {@inheritDoc}
     */
    public PatternDescrBuilder<CEDescrBuilder<P, T>> pattern( String type ) {
        PatternDescrBuilder<CEDescrBuilder<P, T>> pattern = new PatternDescrBuilderImpl<CEDescrBuilder<P, T>>( this, type );
        ((ConditionalElementDescr) descr).addDescr( pattern.getDescr() );
        return pattern;
    }

    public P end() {
        return parent;
    }

}
