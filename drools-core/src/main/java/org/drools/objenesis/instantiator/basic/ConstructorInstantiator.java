package org.drools.objenesis.instantiator.basic;

import java.lang.reflect.Constructor;

import org.drools.objenesis.ObjenesisException;
import org.drools.objenesis.instantiator.ObjectInstantiator;

/**
 * Instantiates a class by grabbing the no args constructor and calling Constructor.newInstance().
 * This can deal with default public constructors, but that's about it.
 * 
 * @see ObjectInstantiator
 */
public class ConstructorInstantiator
    implements
    ObjectInstantiator {

    protected Constructor constructor;

    public ConstructorInstantiator(final Class type) {
        try {
            this.constructor = type.getDeclaredConstructor( (Class[]) null );
        } catch ( final Exception e ) {
            throw new ObjenesisException( e );
        }
    }

    public Object newInstance() {
        try {
            return this.constructor.newInstance( (Object[]) null );
        } catch ( final Exception e ) {
            throw new ObjenesisException( e );
        }
    }

}
