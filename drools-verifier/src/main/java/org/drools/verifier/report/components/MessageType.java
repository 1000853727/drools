package org.drools.verifier.report.components;

public class MessageType {
	public static final MessageType NOT_SPECIFIED = new MessageType(0);
	public static final MessageType RANGE_CHECK = new MessageType(1);
	public static final MessageType MISSING_EQUALITY = new MessageType(2);
	public static final MessageType REDUNDANCY = new MessageType(3);
	public static final MessageType SUBSUMPTION = new MessageType(4);
	public static final MessageType MISSING_COMPONENT = new MessageType(5);
	public static final MessageType OPTIMISATION = new MessageType(6);
	public static final MessageType INCOHERENCE = new MessageType(7);
	public static final MessageType OVERLAP = new MessageType(8);
	public static final MessageType ALWAYS_FALSE = new MessageType(9);
	public static final MessageType ALWAYS_TRUE = new MessageType(10);

	public final int index;

	public MessageType(int i) {
		index = i;
	}

}
