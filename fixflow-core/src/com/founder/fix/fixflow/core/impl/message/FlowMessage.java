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
package com.founder.fix.fixflow.core.impl.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.subscription.EventSubscriptionType;



public class FlowMessage  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6167854337909863372L;
	
	protected String id;
	
	protected String name;
	
	protected String targetProcess;
	
	protected String targetNode;
	
	protected String tokenId;
	
	protected String processInstanceId;
	
	protected EventSubscriptionType messageType;
	
	

	protected String documentation;
	
	protected Map<String, Object> dataVariableMap=new HashMap<String, Object>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTargetProcess() {
		return targetProcess;
	}

	public void setTargetProcess(String targetProcess) {
		this.targetProcess = targetProcess;
	}

	public String getTargetNode() {
		return targetNode;
	}

	public void setTargetNode(String targetNode) {
		this.targetNode = targetNode;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public Map<String, Object> getDataVariableMap() {
		return dataVariableMap;
	}

	public void setDataVariableMap(Map<String, Object> dataVariableMap) {
		this.dataVariableMap = dataVariableMap;
	}
	
	
	public EventSubscriptionType getMessageType() {
		return messageType;
	}

	public void setMessageType(EventSubscriptionType messageType) {
		this.messageType = messageType;
	}
	
	

}
