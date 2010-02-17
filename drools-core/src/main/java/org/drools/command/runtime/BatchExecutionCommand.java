package org.drools.command.runtime;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;
import org.drools.command.runtime.process.AbortWorkItemCommand;
import org.drools.command.runtime.process.CompleteWorkItemCommand;
import org.drools.command.runtime.process.SignalEventCommand;
import org.drools.command.runtime.process.StartProcessCommand;
import org.drools.command.runtime.rule.FireAllRulesCommand;
import org.drools.command.runtime.rule.GetObjectCommand;
import org.drools.command.runtime.rule.InsertElementsCommand;
import org.drools.command.runtime.rule.InsertObjectCommand;
import org.drools.command.runtime.rule.ModifyCommand;
import org.drools.command.runtime.rule.QueryCommand;
import org.drools.command.runtime.rule.RetractCommand;


/**
 * <p>Java class for BatchExecutionCommand complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BatchExecutionCommand">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="abort-work-item" type="{http://drools.org/drools-5.0/knowledge-session}AbortWorkItemCommand"/>
 *         &lt;element name="complete-work-item" type="{http://drools.org/drools-5.0/knowledge-session}CompleteWorkItemCommand"/>
 *         &lt;element name="fire-all-rules" type="{http://drools.org/drools-5.0/knowledge-session}FireAllRulesCommand"/>
 *         &lt;element name="get-global" type="{http://drools.org/drools-5.0/knowledge-session}GetGlobalCommand"/>
 *         &lt;element name="insert" type="{http://drools.org/drools-5.0/knowledge-session}InsertObjectCommand"/>
 *         &lt;element name="insert-elements" type="{http://drools.org/drools-5.0/knowledge-session}InsertElementsCommand"/>
 *         &lt;element name="query" type="{http://drools.org/drools-5.0/knowledge-session}QueryCommand"/>
 *         &lt;element name="set-global" type="{http://drools.org/drools-5.0/knowledge-session}SetGlobalCommand"/>
 *         &lt;element name="signal-event" type="{http://drools.org/drools-5.0/knowledge-session}SignalEventCommand"/>
 *         &lt;element name="start-process" type="{http://drools.org/drools-5.0/knowledge-session}StartProcessCommand"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name="batch-execution")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batch-execution", propOrder = {
    "lookup", "commands"
})
public class BatchExecutionCommand implements GenericCommand<Void> {
	
	private static final long serialVersionUID = 1L;
	
	@XmlAttribute(required=true)
	private String lookup;

	public BatchExecutionCommand(){
	}
	
	public BatchExecutionCommand( List<GenericCommand<?>> commands ) {
		this.commands = commands;
	}
	
	public BatchExecutionCommand( List<GenericCommand<?>> commands, String lookup ) {
		this.commands = commands;
		this.lookup = lookup;
	}
	
    @XmlElements({
//        @XmlElement(name = "complete-work-item", type = CompleteWorkItemCommand.class),
        @XmlElement(name = "abort-work-item", type = AbortWorkItemCommand.class),
        @XmlElement(name = "signal-event", type = SignalEventCommand.class),
        @XmlElement(name = "start-process", type = StartProcessCommand.class),
    	@XmlElement(name = "retract", type = RetractCommand.class),
        @XmlElement(name = "get-global", type = GetGlobalCommand.class),
        @XmlElement(name = "set-global", type = SetGlobalCommand.class),
        @XmlElement(name = "insert-elements", type = InsertElementsCommand.class),
        @XmlElement(name = "query", type = QueryCommand.class),
        @XmlElement(name = "insert", type = InsertObjectCommand.class),
        @XmlElement(name = "modify", type = ModifyCommand.class),
        @XmlElement(name = "get-object", type = GetObjectCommand.class),
        @XmlElement(name = "fire-all-rules", type = FireAllRulesCommand.class)
    })
    protected List<GenericCommand<?>> commands;

    /**
     * Gets the value of the abortWorkItemOrCompleteWorkItemOrFireAllRules property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the abortWorkItemOrCompleteWorkItemOrFireAllRules property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommand().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SetGlobalCommand }
     * {@link CompleteWorkItemCommand }
     * {@link AbortWorkItemCommand }
     * {@link SignalEventCommand }
     * {@link FireAllRulesCommand }
     * {@link StartProcessCommand }
     * {@link GetGlobalCommand }
     * {@link InsertElementsCommand }
     * {@link QueryCommand }
     * {@link InsertObjectCommand }
     * 
     * 
     */
    public List<GenericCommand<?>> getCommands() {
        if (commands == null) {
            commands = new ArrayList<GenericCommand<?>>();
        }
        return this.commands;
    }

    public Void execute(Context context) {
        for ( GenericCommand<?> command : commands ) {
            ((GenericCommand<?>)command).execute( context );
        }
        return null;
    }

	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

	public String getLookup() {
		return lookup;
	}	
}
