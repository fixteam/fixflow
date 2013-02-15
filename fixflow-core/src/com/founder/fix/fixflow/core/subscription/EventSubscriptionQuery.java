package com.founder.fix.fixflow.core.subscription;

import com.founder.fix.fixflow.core.query.Query;

public interface EventSubscriptionQuery extends Query<EventSubscriptionQuery, EventSubscription>{
	
	
	EventSubscriptionQuery subscriptionId(String subscriptionId);

	EventSubscriptionQuery processDefinitionId(String processDefinitionId);

	EventSubscriptionQuery tokenId(String tokenId);

	EventSubscriptionQuery subscriptionType(EventSubscriptionType subscriptionType);

	EventSubscriptionQuery messageId(String messageId);

	EventSubscriptionQuery signalId(String signalId);

}
