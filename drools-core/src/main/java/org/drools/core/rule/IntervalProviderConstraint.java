package org.drools.core.rule;

import org.drools.core.spi.Constraint;
import org.drools.core.time.Interval;

public interface IntervalProviderConstraint extends Constraint {
    Interval getInterval();
}
