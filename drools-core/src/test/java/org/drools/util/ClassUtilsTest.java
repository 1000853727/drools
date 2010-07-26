/**
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

package org.drools.util;

import org.drools.core.util.ClassUtils;

import junit.framework.TestCase;

public class ClassUtilsTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCanonicalNameSimpleClass() {
        String name = ClassUtils.canonicalName( ClassUtilsTest.class );
        assertEquals( "org.drools.util.ClassUtilsTest",
                      name );
    }

    public void testCanonicalNameInnerClass() {
        String name = ClassUtils.canonicalName( A.class );
        assertEquals( "org.drools.util.ClassUtilsTest.A",
                      name );
    }
    
    public void testCanonicalNameInnerInnerClass() {
        String name = ClassUtils.canonicalName( A.B.class );
        assertEquals( "org.drools.util.ClassUtilsTest.A.B",
                      name );
    }
    
    public void testCanonicalNameArray() {
        String name = ClassUtils.canonicalName( Object[].class );
        assertEquals( "java.lang.Object[]",
                      name );
    }
    
    public void testCanonicalNameMultiIndexArray() {
        String name = ClassUtils.canonicalName( Object[][][].class );
        assertEquals( "java.lang.Object[][][]",
                      name );
    }
    
    public void testCanonicalNameMultiIndexArrayInnerClass() {
        String name = ClassUtils.canonicalName( A.B[][][].class );
        assertEquals( "org.drools.util.ClassUtilsTest.A.B[][][]",
                      name );
    }
    
    public void testCanonicalNameMultiIndexArrayPrimitives() {
        String name = ClassUtils.canonicalName( long[][][].class );
        assertEquals( "long[][][]",
                      name );
    }
    
    public static class A {
        public static class B {
        }
    }
}
