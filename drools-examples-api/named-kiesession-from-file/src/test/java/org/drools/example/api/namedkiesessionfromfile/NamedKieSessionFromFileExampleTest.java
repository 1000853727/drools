package org.drools.example.api.namedkiesessionfromfile;

import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class NamedKieSessionFromFileExampleTest {

    @Test
    @Ignore
    public void testGo() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        new NamedKieSessionFromFileExample().go(ps);
        ps.close();

        String actual = new String(baos.toByteArray());
        String expected = "" +
                          "Dave: Hello, HAL. Do you read me, HAL?\n" +
                          "HAL: Dave. I read you.\n";
        assertEquals(expected, actual);
    }
}
