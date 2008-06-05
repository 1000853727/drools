package org.drools.base.mvel;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mvel.integration.VariableResolver;

public class DroolsMVELShadowFactory extends DroolsMVELFactory {

    private static final long   serialVersionUID = 400L;

    private Set<String>         shadowVariables;
    private Map<String, Object> shadowValues;

    public DroolsMVELShadowFactory() {
    }

    public DroolsMVELShadowFactory(final Map previousDeclarations,
                                   final Map localDeclarations,
                                   final Map globals,
                                   final Set<String> shadowVariables) {
        this( previousDeclarations,
              localDeclarations,
              globals,
              null,
              shadowVariables );
    }

    public DroolsMVELShadowFactory(final Map previousDeclarations,
                                   final Map localDeclarations,
                                   final Map globals,
                                   final List[] externals,
                                   final Set<String> shadowVariables) {
        super( previousDeclarations,
               localDeclarations,
               globals,
               externals );
        this.shadowVariables = shadowVariables;
    }

    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
        super.readExternal( in );
        shadowVariables = (Set<String>) in.readObject();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal( out );
        out.writeObject( shadowVariables );
    }

    @Override
    public VariableResolver getVariableResolver(String name) {
        if ( this.shadowValues != null && this.shadowVariables.contains( name ) ) {
            return new ShadowVariableResolver( super.getVariableResolver( name ),
                                               this.shadowValues,
                                               name );
        }
        return super.getVariableResolver( name );
    }

    public Object clone() {
        return new DroolsMVELShadowFactory( this.getPreviousDeclarations(),
                                            this.getLocalDeclarations(),
                                            this.getGlobals(),
                                            this.shadowVariables );
    }

    /**
     * @return the shadowValues
     */
    public Map<String, Object> getShadowValues() {
        return shadowValues;
    }

    /**
     * @param shadowValues the shadowValues to set
     */
    public void setShadowValues(Map<String, Object> shadowValues) {
        this.shadowValues = shadowValues;
    }

    /**
     * @return the shadowVariables
     */
    public Set<String> getShadowVariables() {
        return shadowVariables;
    }

    private static class ShadowVariableResolver
        implements
        VariableResolver {
        private final VariableResolver    delegate;
        private final Map<String, Object> shadowValues;
        private final String              identifier;

        public ShadowVariableResolver(final VariableResolver delegate,
                                      final Map<String, Object> shadowValues,
                                      final String identifier) {
            super();
            this.delegate = delegate;
            this.shadowValues = shadowValues;
            this.identifier = identifier;
        }

        /**
         * @return
         * @see org.mvel.integration.VariableResolver#getFlags()
         */
        public int getFlags() {
            return delegate.getFlags();
        }

        /**
         * @return
         * @see org.mvel.integration.VariableResolver#getName()
         */
        public String getName() {
            return delegate.getName();
        }

        /**
         * @return
         * @see org.mvel.integration.VariableResolver#getType()
         */
        public Class getType() {
            return delegate.getType();
        }

        /**
         * @return
         * @see org.mvel.integration.VariableResolver#getValue()
         */
        public Object getValue() {
            return this.shadowValues.get( this.identifier );
        }

        /**
         * @param type
         * @see org.mvel.integration.VariableResolver#setStaticType(java.lang.Class)
         */
        public void setStaticType(Class type) {
            delegate.setStaticType( type );
        }

        /**
         * @param value
         * @see org.mvel.integration.VariableResolver#setValue(java.lang.Object)
         */
        public void setValue(Object value) {
            delegate.setValue( value );
        }

    }
}
