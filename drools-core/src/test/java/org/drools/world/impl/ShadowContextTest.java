package org.drools.world.impl;


import org.junit.Ignore;
import org.junit.Test;
import org.kie.command.Context;
import org.kie.command.ShadowWorld;
import org.kie.command.World;

import static org.junit.Assert.*;

public class ShadowContextTest {

    @Test @Ignore
    public void test1() {
        World world = new WorldImpl();
        
        world.set( "k1", "v1" );
        assertEquals( "v1", world.get( "k1" ) );
/*        
        Context ctx1 = null; //world.createContext( "p1" );
        
        ctx1.set( "k2", "v2" );
                
        assertEquals( "v2", ctx1.get( "k2" ) );
        assertEquals( "v1", ctx1.get( "k1" ) );
        
        //Context ctx2 = world.createContext( "p1" );
*/
    }
    
    @Test
    public void test2() {
        World world = new WorldImpl();
        
        ShadowWorld shadowWorld = new ShadowWorldImpl( world );
    }
}
