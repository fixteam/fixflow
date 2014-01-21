package com.founder.fix.fixflow.core.impl.event;

import java.util.Date;

import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;

public class EventSubscriptionEntity extends AbstractPersistentObject<EventSubscriptionEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// persistent state ///////////////////////////
	
	
	protected String id;
	protected int revision = 1;
	protected String eventType;
	protected String eventName;
	protected String tokenId;
	protected String processInstanceId;
	protected String nodeId;
	protected String processDefinitionId;
	protected String configuration;
	protected Date created;
	
	
	

	public String getId() {
		return this.id;
	}

	@Override
	public String getCloneRuleId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPersistentDbMapRuleId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPersistentStateRuleId() {
		// TODO Auto-generated method stub
		return null;
	}

}
