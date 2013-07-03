package org.drools.example.api.defaultkiesession;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.runtime.KieSession;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class DefaultKieSessionFromFSExampleTest {

    @Test
    public void testGo() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        go(ps);
        ps.close();

        String actual = new String(baos.toByteArray());
        if (File.separatorChar == '\\') {
            actual = actual.replaceAll("\r\n", "\n");
        }

        String expected = "" +
                          "Dave: Hello, HAL. Do you read me, HAL?\n" +
                          "HAL: Dave. I read you.\n";

        assertEquals(expected, actual);
    }

    public void go(PrintStream out) {
        String currentFolder = null;
        try {
            currentFolder = new File(".").getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File rootFolder = null;
        if (currentFolder.endsWith("drools")) {
            rootFolder = new File("drools-examples-api/default-kiesession");
        } else if (currentFolder.endsWith("drools-examples-api")) {
            rootFolder = new File("default-kiesession");
        } else {
            rootFolder = new File(".");
        }

        KieServices ks = KieServices.Factory.get();
        KieBuilder kieBuilder = ks.newKieBuilder(rootFolder).buildAll();

        KieSession kSession = ks.newKieContainer(kieBuilder.getKieModule().getReleaseId()).newKieSession();
        kSession.setGlobal("out", out);
        kSession.insert(new Message("Dave", "Hello, HAL. Do you read me, HAL?"));
        kSession.fireAllRules();
    }
}
