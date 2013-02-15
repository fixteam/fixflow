package com.founder.fix.fixflow.core.impl.command;


public class MessageStartProcessInstanceCommand  extends StartProcessInstanceCommand{
	
	protected String messageId;
	
	protected String nodeId;
	
	
	
	

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

}
