package com.founder.fix.fixflow.core.subscription;

import com.founder.fix.fixflow.core.impl.db.PersistentObject;

public interface EventSubscription extends PersistentObject {
	
	public String getId();

	public String getProcessDefinitionId();

	public String getTokenId();

	public EventSubscriptionType getSubscriptionType();


	public String getMessageId();



	public String getSignalId();


	public String getNodeId();

	

}
