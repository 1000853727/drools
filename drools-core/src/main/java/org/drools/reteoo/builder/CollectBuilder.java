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

package org.drools.reteoo.builder;

import java.util.ArrayList;
import java.util.List;

import org.drools.core.base.accumulators.CollectAccumulator;
import org.drools.common.BetaConstraints;
import org.drools.common.TupleStartEqualsConstraint;
import org.drools.reteoo.AccumulateNode;
import org.drools.reteoo.LeftTupleSource;
import org.drools.reteoo.ObjectSource;
import org.drools.reteoo.RightInputAdapterNode;
import org.drools.rule.Accumulate;
import org.drools.rule.Collect;
import org.drools.rule.Pattern;
import org.drools.rule.RuleConditionElement;
import org.drools.spi.Accumulator;
import org.drools.spi.AlphaNodeFieldConstraint;

public class CollectBuilder
    implements
    ReteooComponentBuilder {

    /**
     * @inheritDoc
     */
    public void build(final BuildContext context,
                      final BuildUtils utils,
                      final RuleConditionElement rce) {

        boolean existSubNetwort = false;
        final Collect collect = (Collect) rce;
        context.pushRuleComponent( collect );

        final List resultBetaConstraints = context.getBetaconstraints();
        final List resultAlphaConstraints = context.getAlphaConstraints();

        final Pattern sourcePattern = collect.getSourcePattern();
        final Pattern resultPattern = collect.getResultPattern();

        // get builder for the pattern
        final ReteooComponentBuilder builder = utils.getBuilderFor( sourcePattern );

        // save tuple source and pattern offset for later if needed
        final LeftTupleSource tupleSource = context.getTupleSource();
        final int currentPatternIndex = context.getCurrentPatternOffset();

        // builds the source pattern
        builder.build( context,
                       utils,
                       sourcePattern );

        // if object source is null, then we need to adapt tuple source into a subnetwork
        if ( context.getObjectSource() == null ) {

            // attach right input adapter node to convert tuple source into an object source
            context.setObjectSource( (ObjectSource) utils.attachNode( context,
                                                                      new RightInputAdapterNode( context.getNextId(),
                                                                                                 context.getTupleSource(),
                                                                                                 tupleSource,
                                                                                                 context ) ) );

            // restore tuple source from before the start of the sub network
            context.setTupleSource( tupleSource );

            // create a tuple start equals constraint and set it in the context
            final TupleStartEqualsConstraint constraint = TupleStartEqualsConstraint.getInstance();
            final List betaConstraints = new ArrayList();
            betaConstraints.add( constraint );
            context.setBetaconstraints( betaConstraints );
            existSubNetwort = true;
        }

        BetaConstraints binder = utils.createBetaNodeConstraint( context,
                                                                 context.getBetaconstraints(),
                                                                 false );
        // indexing for the results should be always disabled
        BetaConstraints resultBinder = utils.createBetaNodeConstraint( context,
                                                                       resultBetaConstraints,
                                                                       true );

        CollectAccumulator accumulator = new CollectAccumulator( collect, 
                                                                 existSubNetwort );
        Accumulate accumulate = new Accumulate( sourcePattern,
                                                sourcePattern.getRequiredDeclarations(),
                                                new Accumulator[] { accumulator },
                                                false );
        context.setTupleSource( (LeftTupleSource) utils.attachNode( context,
                                                                    new AccumulateNode( context.getNextId(),
                                                                                        context.getTupleSource(),
                                                                                        context.getObjectSource(),
                                                                                        (AlphaNodeFieldConstraint[]) resultAlphaConstraints.toArray( new AlphaNodeFieldConstraint[resultAlphaConstraints.size()] ),
                                                                                        binder, // source binder
                                                                                        resultBinder,
                                                                                        accumulate,
                                                                                        existSubNetwort,
                                                                                        context ) ) );
        // source pattern was bound, so nulling context
        context.setObjectSource( null );
        context.setCurrentPatternOffset( currentPatternIndex );
        context.popRuleComponent();
    }

    /**
     * @inheritDoc
     */
    public boolean requiresLeftActivation(final BuildUtils utils,
                                          final RuleConditionElement rce) {
        return true;
    }

}
