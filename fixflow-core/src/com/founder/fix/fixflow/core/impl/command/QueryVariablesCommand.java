/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl.command;

import java.util.List;



public class QueryVariablesCommand {
	

	
	protected String processInstanceId;
	protected String tokenId;
	protected String taskInstanceId;
	protected String nodeId;

	


	List<String> variableNames;
	
	
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


	public List<String> getVariableNames() {
		return variableNames;
	}


	public void setVariableNames(List<String> variableNames) {
		this.variableNames = variableNames;
	}




}
