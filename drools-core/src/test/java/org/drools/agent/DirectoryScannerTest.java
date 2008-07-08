package org.drools.agent;

import java.io.File;
import java.util.Properties;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.rule.Package;

import junit.framework.TestCase;

public class DirectoryScannerTest extends TestCase {

	public void testScan() throws Exception {
		File dir = RuleBaseAssemblerTest.getTempDirectory();

		Package p1 = new Package("p1");
		Package p2 = new Package("p2");
		File p1f = new File(dir, "p1.pkg");
		File p2f = new File(dir, "p2.pkg");

		RuleBaseAssemblerTest.writePackage(p1, p1f);
		RuleBaseAssemblerTest.writePackage(p2, p2f);

		DirectoryScanner scan = new DirectoryScanner();
		scan.listener = new MockListener();
		Properties props = new Properties();
		props.setProperty(RuleAgent.DIRECTORY, dir.getPath());

		scan.configure(props);

		RuleBase rb = RuleBaseFactory.newRuleBase();
		PackageProvider.applyChanges(rb, true, scan.loadPackageChanges().getChangedPackages(),
				new MockListener());

		assertEquals(2, rb.getPackages().length);

		Package p3 = new Package("p3");
		File p3f = new File(dir, "p3.pkg");

		RuleBaseAssemblerTest.writePackage(p3, p3f);

		PackageProvider.applyChanges(rb, true, scan.loadPackageChanges().getChangedPackages(),
				new MockListener());

		assertEquals(3, rb.getPackages().length);
	}

	public void testScanDRLFileReplace() throws Exception {
		File dir = RuleBaseAssemblerTest.getTempDirectory();

		Package p1 = new Package("p1");
		Package p2 = new Package("p2");
		File p1f = new File(dir, "p1.pkg");
		File p2f = new File(dir, "p2.pkg");

		RuleBaseAssemblerTest.writePackage(p1, p1f);
		RuleBaseAssemblerTest.writePackage(p2, p2f);

		DirectoryScanner scan = new DirectoryScanner();
		scan.listener = new MockListener();
		Properties props = new Properties();
		props.setProperty(RuleAgent.DIRECTORY, dir.getPath());

		scan.configure(props);

		RuleBase rb = RuleBaseFactory.newRuleBase();

		PackageChangeInfo info1 = scan.loadPackageChanges();

		assertEquals(2, info1.getChangedPackages().size());
		assertEquals(0, info1.getRemovedPackages().size());

		PackageProvider.applyChanges(rb, true, info1.getChangedPackages(), info1.getRemovedPackages(), new MockListener());

		assertEquals(2, rb.getPackages().length);

		// Delete file p2.pkg and create file p3.pkg.
		boolean fileDeleted = p2f.delete();
		assertTrue(fileDeleted);

		Package p3 = new Package("p3");
		File p3f = new File(dir, "p3.pkg");

		RuleBaseAssemblerTest.writePackage(p3, p3f);

		PackageChangeInfo info2 = scan.loadPackageChanges();

		assertEquals(1, info2.getChangedPackages().size());
		assertEquals(1, info2.getRemovedPackages().size());

		PackageProvider.applyChanges(rb, true, info2.getChangedPackages(), info2.getRemovedPackages(),  new MockListener());

		assertEquals(2, rb.getPackages().length);
	}

}
