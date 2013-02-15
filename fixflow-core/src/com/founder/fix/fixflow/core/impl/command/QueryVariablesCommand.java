package com.founder.fix.fixflow.core.impl.command;

import java.util.Collection;

public class QueryVariablesCommand {
	
	protected String processInstanceId;
	protected String tokenId;
	protected String taskInstanceId;
	protected String nodeId;
	


	Collection<String> variableNames;
	
	
	public QueryVariablesCommand(){
		
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


	public Collection<String> getVariableNames() {
		return variableNames;
	}


	public void setVariableNames(Collection<String> variableNames) {
		this.variableNames = variableNames;
	}
}
