package com.founder.fix.fixflow.core.impl.command;

import java.util.Map;



public class SaveVariablesCommand {

	protected String processInstanceId;
	protected String tokenId;
	protected String taskInstanceId;
	protected String nodeId;

	protected Map<String, ? extends Object> variables;


	

	public SaveVariablesCommand() {
		
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getTaskInstanceId() {
		return taskInstanceId;
	}

	public void setTaskInstanceId(String taskInstanceId) {
		this.taskInstanceId = taskInstanceId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	public Map<String, ? extends Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, ? extends Object> variables) {
		this.variables = variables;
	}

}
