package org.drools.event;

import java.io.Externalizable;
import java.util.EventListener;

import org.drools.event.AfterFunctionRemovedEvent;
import org.drools.event.AfterRuleAddedEvent;
import org.drools.event.AfterRuleRemovedEvent;
import org.drools.event.BeforeFunctionRemovedEvent;
import org.drools.event.BeforeRuleAddedEvent;
import org.drools.event.BeforeRuleRemovedEvent;

public interface RuleBaseEventListener
    extends
    EventListener, Externalizable {

    /**
     * Method called before a new package is added to the rule base
     * @param event
     */
    void beforePackageAdded(BeforePackageAddedEvent event);

    /**
     * Method called after a new package is added to the rule base
     * @param event
     */
    void afterPackageAdded(AfterPackageAddedEvent event);

    /**
     * Method called before a package is removed from the rule base
     * @param event
     */
    void beforePackageRemoved(BeforePackageRemovedEvent event);

    /**
     * Method called after a package is removed from the rule base
     * @param event
     */
    void afterPackageRemoved(AfterPackageRemovedEvent event);

    /**
     * Method called before a rule base is locked
     * @param event
     */
    void beforeRuleBaseLocked(BeforeRuleBaseLockedEvent event);

    /**
     * Method called after a rule base is locked
     * @param event
     */
    void afterRuleBaseLocked(AfterRuleBaseLockedEvent event);

    /**
     * Method called before a rule base is unlocked
     * @param event
     */
    void beforeRuleBaseUnlocked(BeforeRuleBaseUnlockedEvent event);

    /**
     * Method called after a rule base is unlocked
     * @param event
     */
    void afterRuleBaseUnlocked(AfterRuleBaseUnlockedEvent event);

    /**
     * Method called before a new rule is added to the rule base
     * @param event
     */
    void beforeRuleAdded(BeforeRuleAddedEvent event);

    /**
     * Method called after a new rule is added to the rule base
     * @param event
     */
    void afterRuleAdded(AfterRuleAddedEvent event);

    /**
     * Method called before a rule is removed from the rule base
     * @param event
     */
    void beforeRuleRemoved(BeforeRuleRemovedEvent event);

    /**
     * Method called after a rule is removed from the rule base
     * @param event
     */
    void afterRuleRemoved(AfterRuleRemovedEvent event);

    /**
     * Method called before a function is removed from the rule base
     * @param event
     */
    void beforeFunctionRemoved(BeforeFunctionRemovedEvent event);

    /**
     * Method called after a function is removed from the rule base
     * @param event
     */
    void afterFunctionRemoved(AfterFunctionRemovedEvent event);

}
