package org.drools.kproject;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.commons.jci.compilers.CompilationResult;
import org.drools.commons.jci.compilers.EclipseJavaCompiler;
import org.drools.commons.jci.compilers.EclipseJavaCompilerSettings;
import org.drools.conf.AssertBehaviorOption;
import org.drools.conf.EventProcessingOption;
import org.drools.kproject.memory.MemoryFile;
import org.drools.kproject.memory.MemoryFileSystem;
import org.drools.kproject.memory.MemorytURLStreamHandler;
import org.drools.rule.JavaDialectRuntimeData;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.jboss.weld.bootstrap.api.Bootstrap;
import org.jboss.weld.bootstrap.spi.BeanDeploymentArchive;
import org.jboss.weld.bootstrap.spi.Deployment;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.discovery.AbstractWeldSEDeployment;
import org.jboss.weld.environment.se.discovery.ImmutableBeanDeploymentArchive;
import org.jboss.weld.resources.spi.ResourceLoader;
import org.junit.Test;

public class KProjectTest {

    private static final ProtectionDomain PROTECTION_DOMAIN;

    static {
        PROTECTION_DOMAIN = (ProtectionDomain) AccessController.doPrivileged(new PrivilegedAction() {

            public Object run() {
                return JavaDialectRuntimeData.class.getProtectionDomain();
            }
        });
    }

    @Test
    public void testAddModifyRemoveKBase() throws IOException,
            ClassNotFoundException,
            InterruptedException,
            InstantiationException,
            IllegalAccessException,
            SecurityException,
            NoSuchMethodException,
            IllegalArgumentException,
            InvocationTargetException {
        KProject kproj = new KProjectImpl();

        KProjectChangeLog klog = new KProjectChangeLog();
        kproj.setListener(klog);

        kproj.setKProjectPath("src/main/resources/");
        kproj.setKBasesPath("src/kbases");

        List<String> files = asList(new String[]{});

        // Create and add the KBase
        KBase kBase1 = kproj.newKBase("org.test1", "KBase1")
                            .setFiles(files)
                            .setAnnotations(asList("@ApplicationScoped; @Inject"))
                            .setEqualsBehavior(AssertBehaviorOption.EQUALITY)
                            .setEventProcessingMode(EventProcessingOption.STREAM);

        MemoryFileSystem mfs = new MemoryFileSystem();
        KProjectChangeLogCommiter.commit(kproj, klog, mfs);

        Folder fld = mfs.getFolder("src/kbases/org.test1.KBase1");
        assertTrue(fld.exists());

        // Modify the KBase
        MemoryFile mf = (MemoryFile) mfs.getFile("src/kbases/org.test1.KBase1/org/test1/KBase1Producer.java");
        String s = new String(mfs.getBytes(mf.getPath().toPortableString()));
        assertTrue(s.contains("EventProcessingOption.STREAM"));

        kBase1.setEventProcessingMode(EventProcessingOption.CLOUD);
        KProjectChangeLogCommiter.commit(kproj, klog, mfs);
        mf = (MemoryFile) mfs.getFile("src/kbases/org.test1.KBase1/org/test1/KBase1Producer.java");
        s = new String(mfs.getBytes(mf.getPath().toPortableString()));
        assertTrue(s.contains("EventProcessingOption.CLOUD"));

        // Remove the KBase
        kproj.removeKBase(kBase1.getQName());
        KProjectChangeLogCommiter.commit(kproj, klog, mfs);

        fld = mfs.getFolder("src/kbases/org.test1.KBase1");
        assertFalse(fld.exists());
        mf = (MemoryFile) mfs.getFile("src/kbases/org.test1.KBase1/org/test1/KBase1Producer.java");
        assertFalse(mf.exists());
    }

    @Test
    public void testAddModifyRemoveKSession() throws IOException,
            ClassNotFoundException,
            InterruptedException,
            InstantiationException,
            IllegalAccessException,
            SecurityException,
            NoSuchMethodException,
            IllegalArgumentException,
            InvocationTargetException {
        KProject kproj = new KProjectImpl();

        KProjectChangeLog klog = new KProjectChangeLog();
        kproj.setListener(klog);

        kproj.setKProjectPath("src/main/resources/");
        kproj.setKBasesPath("src/kbases");

        List<String> files = asList(new String[]{});

        // create and add the KBase
        KBase kBase1 = kproj.newKBase("org.test1", "KBase1")
                            .setFiles(files)
                            .setAnnotations(asList("@ApplicationScoped; @Inject"))
                            .setEqualsBehavior(AssertBehaviorOption.EQUALITY)
                            .setEventProcessingMode(EventProcessingOption.STREAM);

        MemoryFileSystem mfs = new MemoryFileSystem();
        KProjectChangeLogCommiter.commit(kproj, klog, mfs);
        Folder fld = mfs.getFolder("src/kbases/org.test1.KBase1");
        assertTrue(fld.exists());

        // Creat and add KSession
        KSession kSession1 = kBase1.newKSession("org.test1", "KSession1")
                                   .setType("stateful")
                                   .setClockType(ClockTypeOption.get("realtime"));

        KProjectChangeLogCommiter.commit(kproj, klog, mfs);

        MemoryFile mf = (MemoryFile) mfs.getFile("src/kbases/org.test1.KBase1/org/test1/KSession1Producer.java");
        String s = new String(mfs.getBytes(mf.getPath().toPortableString()));
        assertTrue(s.contains("newStatefulKnowledgeSession"));
        assertTrue(s.contains("realtime"));

        // Modify KSession
        kSession1.setType("stateless")
                 .setClockType(ClockTypeOption.get("pseudo"));
        KProjectChangeLogCommiter.commit(kproj, klog, mfs);

        mf = (MemoryFile) mfs.getFile("src/kbases/org.test1.KBase1/org/test1/KSession1Producer.java");
        s = new String(mfs.getBytes(mf.getPath().toPortableString()));
        assertTrue(s.contains("newStatelessKnowledgeSession"));
        assertTrue(s.contains("pseudo"));

        // Remove KSession
        kBase1.removeKSession("org.test1.KSession1");
        KProjectChangeLogCommiter.commit(kproj, klog, mfs);

        mf = (MemoryFile) mfs.getFile("src/kbases/org.test1.KBase1/org/test1/KSession1Producer.java");
        assertFalse(mf.exists());
    }

    @Test
    public void testCompileAndCDI() throws IOException,
            ClassNotFoundException,
            InterruptedException {
        KProject kproj = new KProjectImpl();

        kproj.setKProjectPath("src/main/resources/");
        kproj.setKBasesPath("src/kbases");

        List<String> files = asList(new String[]{"org/test1/rule1.drl", "org/test1/rule2.drl"});

        KBase kBase1 = kproj.newKBase("org.test1", "KBase1")
                            .setFiles(files)
                            .setAnnotations(asList("@ApplicationScoped; @Inject"))
                            .setEqualsBehavior(AssertBehaviorOption.EQUALITY)
                            .setEventProcessingMode(EventProcessingOption.STREAM);

        KSession ksession1 = kBase1.newKSession("org.test1", "KSession1")
                                   .setType("stateless")
                                   .setAnnotations(asList("@ApplicationScoped; @Inject"))
                                   .setClockType(ClockTypeOption.get("realtime"));

        KSession ksession2 = kBase1.newKSession("org.test1", "KSession2")
                                   .setType("stateful")
                                   .setAnnotations(asList("@ApplicationScoped; @Inject"))
                                   .setClockType(ClockTypeOption.get("pseudo"));

        files = asList(new String[]{"org/test2/rule1.drl", "org/test2/rule2.drl"});
        KBase kBase2 = kproj.newKBase("org.test2", "KBase2")
                            .setFiles(files)
                            .setAnnotations(asList("@ApplicationScoped"))
                            .setEqualsBehavior(AssertBehaviorOption.IDENTITY)
                            .setEventProcessingMode(EventProcessingOption.CLOUD);

        KSession ksession3 = kBase2.newKSession("org.test2", "KSession3")
                                   .setType("stateful")
                                   .setAnnotations(asList("@ApplicationScoped"))
                                   .setClockType(ClockTypeOption.get("pseudo"));

        KBase kBase3 = kproj.newKBase("org.test3", "KBase3")
                            .setFiles(asList(new String[]{}))
                            .addInclude(kBase1.getQName())
                            .addInclude(kBase2.getQName())
                            .setAnnotations(asList("@ApplicationScoped"))
                            .setEqualsBehavior(AssertBehaviorOption.IDENTITY)
                            .setEventProcessingMode(EventProcessingOption.CLOUD);

        KSession ksession4 = kBase3.newKSession("org.test3", "KSession4")
                                   .setType("stateless")
                                   .setAnnotations(asList("@ApplicationScoped"))
                                   .setClockType(ClockTypeOption.get("pseudo"));

        //        
        //        XStream xstream = new XStream();
        //        String string = xstream.toXML( kproj );
        //        System.out.println( string );
        //      

        //                
        MemoryFileSystem mfs = new MemoryFileSystem();
        KProjectChangeLogCommiter.commit(kproj, mfs);

        printFs(mfs, mfs.getProjectFolder());

        String kBase1R1 = getRule("org.test1", "rule1");
        String kBase1R2 = getRule("org.test1", "rule2");

        String kbase2R1 = getRule("org.test2", "rule1");
        String kbase2R2 = getRule("org.test2", "rule2");

        String fldKB1 = kproj.getKBasesPath() + "/" + kBase1.getQName() + "/" + kBase1.getNamespace().replace('.', '/');
        String fldKB2 = kproj.getKBasesPath() + "/" + kBase2.getQName() + "/" + kBase2.getNamespace().replace('.', '/');

        mfs.getFolder(fldKB1).create();
        mfs.getFolder(fldKB2).create();

        mfs.getFile(fldKB1 + "/rule1.drl").create(new ByteArrayInputStream(kBase1R1.getBytes()));
        mfs.getFile(fldKB1 + "/rule2.drl").create(new ByteArrayInputStream(kBase1R2.getBytes()));
        mfs.getFile(fldKB2 + "/rule1.drl").create(new ByteArrayInputStream(kbase2R1.getBytes()));
        mfs.getFile(fldKB2 + "/rule2.drl").create(new ByteArrayInputStream(kbase2R2.getBytes()));

        MemoryFileSystem trgMfs = new MemoryFileSystem();
        MemoryFileSystem srcMfs = mfs;

        Folder fld1 = trgMfs.getFolder("org/drools/cdi/test");
        fld1.create();
        File fle1 = fld1.getFile("KProjectTestClassImpl.java");
        fle1.create(new ByteArrayInputStream(generateKProjectTestClassImpl(kproj).getBytes()));

        Folder fld2 = trgMfs.getFolder( "META-INF" );
        fld2.create();
        File fle2 = fld2.getFile( "beans.xml" );
        fle2.create( new ByteArrayInputStream( generateBeansXML( kproj ).getBytes() ) );

        
        List<String> inputClasses = new ArrayList<String>();
        inputClasses.add("org/drools/cdi/test/KProjectTestClassImpl.java");

        final List<String> classes = compile(kproj, srcMfs, trgMfs, inputClasses);

        MemoryFileSystemClassLoader classLoader = new MemoryFileSystemClassLoader(trgMfs);

        ClassLoader origCl = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(classLoader);

            Weld weldContainer = new Weld() {
                @Override
                protected Deployment createDeployment(ResourceLoader resourceLoader,
                                                      Bootstrap bootstrap) {
                    return new TestWeldSEDeployment(resourceLoader, bootstrap, classes);
                }
            };
            WeldContainer weld = weldContainer.initialize();
            KProjectTestClass bean = weld.instance().select(KProjectTestClass.class).get();

            assertNotNull(bean.getKBase1());
            assertNotNull(bean.getKBase2());

            List<String> list = new ArrayList<String>();

            StatelessKnowledgeSession stlsKsession = bean.getKBase1KSession1();
            stlsKsession.setGlobal("list", list);
            stlsKsession.execute("dummy");
            assertEquals(2, list.size());
            assertTrue(list.contains("org.test1:rule1"));
            assertTrue(list.contains("org.test1:rule2"));

            list.clear();
            StatefulKnowledgeSession stflKsession = bean.getKBase1KSession2();
            stflKsession.setGlobal("list", list);
            stflKsession.fireAllRules();
            assertEquals(2, list.size());
            assertTrue(list.contains("org.test1:rule1"));
            assertTrue(list.contains("org.test1:rule2"));

            list.clear();
            stflKsession = bean.getKBase2KSession3();
            stflKsession.setGlobal("list", list);
            stflKsession.fireAllRules();
            assertEquals(2, list.size());

            assertTrue(list.contains("org.test2:rule1"));
            assertTrue(list.contains("org.test2:rule2"));

            list.clear();
            stlsKsession = bean.getKBase3KSession4();
            stlsKsession.setGlobal("list", list);
            stlsKsession.execute("dummy");
            assertEquals(4, list.size());
            assertTrue(list.contains("org.test1:rule1"));
            assertTrue(list.contains("org.test1:rule2"));
            assertTrue(list.contains("org.test2:rule1"));
            assertTrue(list.contains("org.test2:rule2"));

            weldContainer.shutdown();
        } finally {
            Thread.currentThread().setContextClassLoader(origCl);
        }
    }


    public List<String> compile(KProject kproj,
                                MemoryFileSystem srcMfs,
                                MemoryFileSystem trgMfs,
                                List<String> classes) {
        for (KBase kbase : kproj.getKBases().values()) {
            Folder srcFolder = srcMfs.getFolder(kproj.getKBasesPath() + "/" + kbase.getQName());
            Folder trgFolder = trgMfs.getProjectFolder();

            copyFolder(srcMfs, srcFolder, trgMfs, trgFolder, kproj);
        }

        populateClasses(kproj, classes);

        System.out.println(classes);

        EclipseJavaCompilerSettings settings = new EclipseJavaCompilerSettings();
        settings.setSourceVersion("1.5");
        settings.setTargetVersion("1.5");
        EclipseJavaCompiler compiler = new EclipseJavaCompiler(settings);
        CompilationResult res = compiler.compile(classes.toArray(new String[classes.size()]), trgMfs, trgMfs);

        if (res.getErrors().length > 0) {
            fail(res.getErrors()[0].getMessage());
            //fail(res.getErrors().toString());
        }

        List<String> classes2 = new ArrayList<String>(classes.size());
        for (String str : classes) {
            classes2.add(filenameToClassname(str));
        }

        return classes2;
    }

    public String getRule(String packageName,
                          String ruleName) {
        String s = "package " + packageName + "\n" +
                "global java.util.List list;\n" +
                "rule " + ruleName + " when \n" +
                "then \n" +
                "  list.add(\"" + packageName + ":" + ruleName + "\"); " +
                "end \n" +
                "";
        return s;
    }

    public void copyFolder(MemoryFileSystem srcMfs,
                           Folder srcFolder,
                           MemoryFileSystem trgMfs,
                           Folder trgFolder,
                           KProject kproj) {
        if (!trgFolder.exists()) {
            trgMfs.getFolder(trgFolder.getPath()).create();
        }

        Collection<Resource> col = (Collection<Resource>) srcFolder.getMembers();

        for (Resource rs : srcFolder.getMembers()) {
            if (rs instanceof Folder) {
                copyFolder(srcMfs, (Folder) rs, trgMfs, trgFolder.getFolder(((Folder) rs).getName()), kproj);
            } else {
                MemoryFile trgFile = (MemoryFile) trgFolder.getFile(((File) rs).getName());

                try {
                    trgMfs.setFileContents(trgFile, srcMfs.getFileContents((MemoryFile) rs));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void populateClasses(KProject kproject,
                                List<String> classes) {
        for (KBase kBase : kproject.getKBases().values()) {
            classes.add(kBase.getNamespace().replace('.', '/') + "/" + kBase.getName() + "Producer.java");
            classes.add(kBase.getNamespace().replace('.', '/') + "/" + kBase.getName() + ".java");
            for (KSession kSession : kBase.getKSessions().values()) {
                classes.add(kSession.getNamespace().replace('.', '/') + "/" + kSession.getName() + "Producer.java");
                classes.add(kSession.getNamespace().replace('.', '/') + "/" + kSession.getName() + ".java");
            }
        }
    }

    public void printFs(MemoryFileSystem mfs,
                        Folder f) {
        for (Resource rs : f.getMembers()) {
            System.out.println(rs);
            if (rs instanceof Folder) {
                printFs(mfs, (Folder) rs);
            } else {
                System.out.println(new String(mfs.getFileContents((MemoryFile) rs)));
            }
        }
    }

    public String generateKProjectTestClassImpl(KProject kproject) {
        String s = "package org.drools.cdi.test;\n" +
                "import javax.inject.Named;\n" +
                "import javax.inject.Inject;\n" +
                "import javax.inject.Inject;\n" +
                "import javax.enterprise.event.Observes;\n" +
                "import org.jboss.weld.environment.se.events.ContainerInitialized;\n" +
                "import " + KnowledgeBase.class.getName() + ";\n" +
                "import " + StatefulKnowledgeSession.class.getName() + ";\n" +
                "import " + StatelessKnowledgeSession.class.getName() + ";\n" +
                "import org.test1.KBase1;\n" +
                "import org.test2.KBase2;\n" +
                "import org.test3.KBase3;\n" +
                "import org.test1.KSession1;\n" +
                "import org.test1.KSession2;\n" +
                "import org.test2.KSession3;\n" +
                "import org.test3.KSession4;\n" +

                "public class KProjectTestClassImpl implements org.drools.kproject.KProjectTestClass {\n" +
                "    private @Inject @KBase1 KnowledgeBase kBase1; \n" +
                "    public KnowledgeBase getKBase1() {\n" +
                "        return kBase1;\n" +
                "    }\n" +
                "    private @Inject @KBase2 KnowledgeBase kBase2; \n" +
                "    public KnowledgeBase getKBase2() {\n" +
                "        return kBase2;\n" +
                "    }\n" +
                "    private @Inject @KBase3 KnowledgeBase kBase3; \n" +
                "    public KnowledgeBase getKBase3() {\n" +
                "        return kBase3;\n" +
                "    }\n" +
                "    private @Inject @KSession1 StatelessKnowledgeSession kBase1kSession1; \n" +
                "    public StatelessKnowledgeSession getKBase1KSession1() {\n" +
                "        return kBase1kSession1;\n" +
                "    }\n" +
                "    private @Inject @KSession2 StatefulKnowledgeSession kBase1kSession2; \n" +
                "    public StatefulKnowledgeSession getKBase1KSession2() {\n" +
                "        return kBase1kSession2;\n" +
                "    }\n" +
                "    private @Inject @KSession3 StatefulKnowledgeSession kBase2kSession3; \n" +
                "    public StatefulKnowledgeSession getKBase2KSession3() {\n" +
                "        return kBase2kSession3;\n" +
                "    }\n" +
                "    private @Inject @KSession4 StatelessKnowledgeSession kBase3kSession4; \n" +
                "    public StatelessKnowledgeSession getKBase3KSession4() {\n" +
                "        return kBase3kSession4;\n" +
                "    }\n" +
                "}\n";

        return s;
    }

    public String generateBeansXML(KProject kproject) {
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
                   "<beans xmlns=\"http://java.sun.com/xml/ns/javaee\"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/beans_1_0.xsd\">\n" + 
                   "</beans>";
        
        return s;
    }
        
    public static String filenameToClassname(String filename) {
        return filename.substring(0, filename.lastIndexOf(".java")).replace('/', '.').replace('\\', '.');
    }

    /**
     * This is an Internal Drools Class
     */
    public static class MemoryFileSystemClassLoader extends ClassLoader {

        MemoryFileSystem mfs;

        public MemoryFileSystemClassLoader(MemoryFileSystem mfs) {
            super(MemoryFileSystemClassLoader.class.getClassLoader());
            this.mfs = mfs;
        }

        public Class<?> loadClass(final String name,
                                  final boolean resolve) throws ClassNotFoundException {
            Class<?> cls = fastFindClass(name);

            if (cls == null) {
                cls = getParent().loadClass(name);
            }

            if (cls == null) {
                throw new ClassNotFoundException("Unable to load class: " + name);
            }

            return cls;
        }

        public Class<?> fastFindClass(final String name) {
            Class<?> cls = findLoadedClass(name);

            if (cls == null) {
                final byte[] clazzBytes = this.mfs.read(convertClassToResourcePath(name));
                if (clazzBytes != null) {
                    String pkgName = name.substring(0,
                                                    name.lastIndexOf('.'));
                    if (getPackage(pkgName) == null) {
                        definePackage(pkgName,
                                      "",
                                      "",
                                      "",
                                      "",
                                      "",
                                      "",
                                      null);
                    }

                    cls = defineClass(name,
                                      clazzBytes,
                                      0,
                                      clazzBytes.length,
                                      PROTECTION_DOMAIN);
                }

                if (cls != null) {
                    resolveClass(cls);
                }
            }

            return cls;
        }

        public InputStream getResourceAsStream(final String name) {
            final byte[] clsBytes = this.mfs.read(name);
            if (clsBytes != null) {
                return new ByteArrayInputStream(clsBytes);
            }

            return getParent().getResourceAsStream(name);
        }

        public URL getResource(String name) {
            final byte[] clsBytes = this.mfs.read(name);
            if (clsBytes != null) {
                try {
                    return new URL(null, "memory://" + name, new MemorytURLStreamHandler(clsBytes));
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Unable to create URL for: " + name);
                }
            }
            return getParent().getResource(name);
        }

        public Enumeration<URL> getResources(String name) throws IOException {
            return getParent().getResources(name);
        }

        public static String convertClassToResourcePath(final String pName) {
            return pName.replace('.',
                                 '/') + ".class";
        }

    }

    public static class TestWeldSEDeployment extends AbstractWeldSEDeployment {
        private final BeanDeploymentArchive beanDeploymentArchive;

        public TestWeldSEDeployment(ResourceLoader resourceLoader,
                                    Bootstrap bootstrap,
                                    List<String> classes) {
            super(bootstrap);
            beanDeploymentArchive = new ImmutableBeanDeploymentArchive("classpath", classes, null);

        }

        public Collection<BeanDeploymentArchive> getBeanDeploymentArchives() {
            return Collections.singletonList(beanDeploymentArchive);
        }

        public BeanDeploymentArchive loadBeanDeploymentArchive(Class<?> beanClass) {
            return beanDeploymentArchive;
        }

    }
}
