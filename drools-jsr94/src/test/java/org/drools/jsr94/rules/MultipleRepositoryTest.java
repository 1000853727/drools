package org.drools.jsr94.rules;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.StatelessRuleSession;
import javax.rules.admin.RuleAdministrator;
import javax.rules.admin.RuleExecutionSet;

import junit.framework.TestCase;

/**
 * A test for independent repository instances for different runtimes.
 *
 * @author jgilbert
 * @author <a href="mailto:michael.frandsen@syngenio.de">michael frandsen </a>
 */
public class MultipleRepositoryTest extends TestCase {

    public MultipleRepositoryTest(final String name) {
        super( name );
    }

    /**
     * Do the test.
     *
     * @throws Exception
     */
    public void testMultipleInstances() throws Exception {
        // create 2 different runtimes with different rulesets
        final RuleRuntime ruleRuntime1 = getServiceProvider( "engine1",
                                                             "multiple-engine1.drl" ).getRuleRuntime();
        final RuleRuntime ruleRuntime2 = getServiceProvider( "engine2",
                                                             "multiple-engine2.drl" ).getRuleRuntime();

        // there should be only 1
        System.out.println( ruleRuntime1.getRegistrations().size() );
        assertTrue( ruleRuntime1.getRegistrations().size() == 1 );

        // there should be only 1
        System.out.println( ruleRuntime2.getRegistrations().size() );
        assertTrue( ruleRuntime2.getRegistrations().size() == 1 );

        // execute them both for good measure...
        execute( ruleRuntime1,
                 "Engine1",
                 new Object[]{"value1"} );
        execute( ruleRuntime2,
                 "Engine2",
                 new Object[]{"value2"} );

    }

    /**
     * Create a Provider.
     *
     * @param url
     * @param rulesets
     * @return
     * @throws Exception
     */
    public RuleServiceProvider getServiceProvider(final String url,
                                                  final String ruleset) throws Exception {
        // create the provider
        final Class clazz = this.getClass().getClassLoader().loadClass( "org.drools.jsr94.rules.RuleServiceProviderImpl" );
        RuleServiceProviderManager.registerRuleServiceProvider( url,
                                                                clazz );
        final RuleServiceProvider serviceProvider = RuleServiceProviderManager.getRuleServiceProvider( url );
        final RuleAdministrator ruleAdministrator = serviceProvider.getRuleAdministrator();

        // register the ruleset
        final InputStream inStream = this.getClass().getResourceAsStream( ruleset );
        final RuleExecutionSet res1 = ruleAdministrator.getLocalRuleExecutionSetProvider( null ).createRuleExecutionSet( inStream,
                                                                                                                         null );

        inStream.close();
        final String uri = res1.getName();
        System.out.println( uri );
        ruleAdministrator.registerRuleExecutionSet( uri,
                                                    res1,
                                                    null );
        return serviceProvider;
    }

    /**
     * Execute a ruleset for the input.
     *
     * @param rt
     * @param ruleset
     * @param input
     * @throws Exception
     */
    public void execute(final RuleRuntime rt,
                        final String ruleset,
                        final Object[] input) throws Exception {
        final StatelessRuleSession srs = (StatelessRuleSession) rt.createRuleSession( ruleset,
                                                                                      null,
                                                                                      RuleRuntime.STATELESS_SESSION_TYPE );
        final List output = srs.executeRules( Arrays.asList( input ) );
        System.out.println( output );
    }
}