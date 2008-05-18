package org.drools.marshalling;

public interface PersisterKey {
   public boolean equal(Object object1, Object object2);
   
   public int hashCode(Object object);
}
