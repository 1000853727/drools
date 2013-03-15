package org.drools.example.api.kiebaseinclusion;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class KieBaseInclusionExampleTest {

    @Test
    public void testGo() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        new KieBaseInclusionExample().go(ps);
        ps.close();

        String actual = new String(baos.toByteArray());
        String expected = "" +
                          "Dave: Hello, HAL. Do you read me, HAL?\n" +
                          "HAL: Dave. I read you.\n" +
                          "Dave: Open the pod bay doors, HAL.\n" +
                          "HAL: I'm sorry, Dave. I'm afraid I can't do that.\n";
        assertEquals(expected, actual);
    }
}
