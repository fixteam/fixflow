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
package com.founder.fix.fixflow.core.impl.subscription;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.subscription.EventSubscription;
import com.founder.fix.fixflow.core.subscription.EventSubscriptionType;

/**
 * 事件订阅对象
 * 
 * @author jiang_nan
 * 
 */
public class EventSubscriptionEntity implements EventSubscription {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1816144024386606136L;

	protected String id;

	protected String processDefinitionId;

	protected String tokenId;

	protected EventSubscriptionType subscriptionType;

	protected String messageId;

	protected String signalId;
	
	protected String nodeId;


	public EventSubscriptionEntity() {
		this.id = GuidUtil.CreateGuid();
	}

	public EventSubscriptionEntity(String id) {
		this.id = id;
	}

	public String getId() {

		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public EventSubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(EventSubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getSignalId() {
		return signalId;
	}

	public void setSignalId(String signalId) {
		this.signalId = signalId;
	}
	

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Map<String, Object> getPersistentState() {

		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("id", this.id);
		persistentState.put("processDefinitionId", this.processDefinitionId);
		persistentState.put("tokenId", this.processDefinitionId);
		persistentState.put("subscriptionType", this.subscriptionType);
		persistentState.put("messageId", this.messageId);
		persistentState.put("signalId", this.signalId);
		persistentState.put("nodeId", this.getNodeId());

		return persistentState;
	}

	/**
	 * 从数据库读取任务
	 */
	public EventSubscriptionEntity(Map<String, Object> entityMap) {

		for (String dataKey : entityMap.keySet()) {

			if (dataKey.equals("SUBSCRIPTION_ID")) {
				this.setId(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			if (dataKey.equals("PROCESSDEFINITION_ID")) {
				this.setProcessDefinitionId(StringUtil.getString(entityMap
						.get(dataKey)));
				continue;
			}
			if (dataKey.equals("TOKEN_ID")) {
				this.setTokenId(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			if (dataKey.equals("SUBSCRIPTION_TYPE")) {
				this.setSubscriptionType(EventSubscriptionType
						.valueOf(entityMap.get(dataKey).toString()));
				continue;
			}
			if (dataKey.equals("MESSAGE_ID")) {
				this.setMessageId(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			if (dataKey.equals("SIGNAL_ID")) {
				this.setSignalId(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			if (dataKey.equals("NODE_ID")) {
				this.setSignalId(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
		}
	}
	
	public Map<String, Object> getEventSubscriptionDbMap() {

		Map<String, Object> objectParam = new HashMap<String, Object>();

		objectParam.put("SUBSCRIPTION_ID",this.getId());
		objectParam.put("PROCESSDEFINITION_ID",this.getProcessDefinitionId());
		objectParam.put("TOKEN_ID",this.getTokenId());
		objectParam.put("SUBSCRIPTION_TYPE",StringUtil.getString(this.getSubscriptionType().toString()));
		objectParam.put("MESSAGE_ID",this.getMessageId());
		objectParam.put("SIGNAL_ID",this.getSignalId());
		
		objectParam.put("NODE_ID",this.getNodeId());
		return objectParam;
	}

}
