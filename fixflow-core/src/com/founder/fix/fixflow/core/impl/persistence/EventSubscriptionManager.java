package com.founder.fix.fixflow.core.impl.persistence;

import java.util.List;

import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.subscription.EventSubscriptionEntity;
import com.founder.fix.fixflow.core.impl.subscription.EventSubscriptionQueryImpl;

public class EventSubscriptionManager extends AbstractManager {
	
	public void saveEventSubscriptionEntity(EventSubscriptionEntity eventSubscriptionEntity){
		getDbSqlSession().save("saveEventSubscriptionEntity", eventSubscriptionEntity);
	}
	
	
	public void deleteEventSubscriptionEntity(String subscriptionId){
		getDbSqlSession().delete("deleteEventSubscriptionEntity", subscriptionId);
	}
	
	@SuppressWarnings("unchecked")
	public List<EventSubscriptionEntity> findEventSubscriptionByQueryCriteria(EventSubscriptionQueryImpl eventSubscriptionQueryImpl,Page page){
		return getDbSqlSession().selectList("findEventSubscriptionByQueryCriteria", eventSubscriptionQueryImpl,page);
	}
	
	
	
}
