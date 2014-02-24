package org.kie.scanner;

import org.kie.api.Service;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.KieScannerFactoryService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;
import java.util.concurrent.Callable;

public class Activator implements BundleActivator {

    protected static final transient Logger logger = LoggerFactory.getLogger(Activator.class);

    private ServiceRegistration scannerReg;

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info( "registering kiescanner services" );
        this.scannerReg = context.registerService( new String[]{ KieScannerFactoryService.class.getName(), Service.class.getName() },
                                                   new KieScannerFactoryServiceImpl(),
                                                   new Hashtable() );
        logger.info( "kiescanner services registered" );
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        this.scannerReg.unregister();
    }
}
