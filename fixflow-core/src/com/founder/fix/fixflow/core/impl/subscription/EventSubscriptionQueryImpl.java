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

import java.util.List;

import com.founder.fix.fixflow.core.impl.AbstractQuery;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;
import com.founder.fix.fixflow.core.subscription.EventSubscription;
import com.founder.fix.fixflow.core.subscription.EventSubscriptionQuery;
import com.founder.fix.fixflow.core.subscription.EventSubscriptionType;

public class EventSubscriptionQueryImpl  extends AbstractQuery< EventSubscriptionQuery, EventSubscription> implements EventSubscriptionQuery {
	
	
	protected String subscriptionId;

	protected String processDefinitionId;

	protected String tokenId;

	protected EventSubscriptionType subscriptionType;

	protected String messageId;

	
	protected String signalId;
	
	
	public EventSubscriptionQueryImpl() {
	}

	public EventSubscriptionQueryImpl(CommandContext commandContext) {
		super(commandContext);
	}

	public EventSubscriptionQueryImpl(CommandExecutor commandExecutor) {
		super(commandExecutor);
	}
	





	public EventSubscriptionQuery subscriptionId(String subscriptionId) {
		this.subscriptionId=subscriptionId;
		return this;
	}

	public EventSubscriptionQuery processDefinitionId(String processDefinitionId) {
		this.processDefinitionId=processDefinitionId;
		return this;
	}

	public EventSubscriptionQuery tokenId(String tokenId) {
		this.tokenId=tokenId;
		return this;
	}

	public EventSubscriptionQuery subscriptionType(
			EventSubscriptionType subscriptionType) {
		this.subscriptionType=subscriptionType;
		return this;
	}

	public EventSubscriptionQuery messageId(String messageId) {
		this.messageId=messageId;
		return this;
	}

	public EventSubscriptionQuery signalId(String signalId) {
		this.signalId=signalId;
		return this;
	}
	

	
	@Override
	public long executeCount(CommandContext commandContext) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<EventSubscription> executeList(CommandContext commandContext,
			Page page) {
		return (List)commandContext.getEventSubscriptionManager().findEventSubscriptionByQueryCriteria(this, page);
	}
	
	public String getSubscriptionId() {
		return subscriptionId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public EventSubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public String getMessageId() {
		return messageId;
	}

	public String getSignalId() {
		return signalId;
	}


}
