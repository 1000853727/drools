package org.drools.verifier.data;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.drools.verifier.components.VerifierComponentType;
import org.drools.verifier.report.components.Cause;

public abstract class VerifierComponent
    implements
    Comparable<VerifierComponent>,
    Cause {

    static class GuidFactory {

        private static Map<VerifierComponentType, Integer> guids = new HashMap<VerifierComponentType, Integer>();

        static String getGuid(VerifierComponentType type) {
            Integer guid = guids.get( type );
            if ( guid == null ) {
                guid = 0;
            }

            Integer result = guid;
            guids.put( type,
                       (guid + 1) );

            return result.toString();
        }
    }

    private String guid = GuidFactory.getGuid( getVerifierComponentType() );

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public abstract VerifierComponentType getVerifierComponentType();

    public int compareTo(VerifierComponent another) {
        return this.guid.compareTo( another.getGuid() );
    }

    public Collection<Cause> getCauses() {
        return Collections.emptyList();
    }
}
