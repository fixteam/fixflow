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
package com.founder.fix.fixflow.core.impl.persistence.instance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.subscription.EventSubscriptionEntity;
import com.founder.fix.fixflow.core.impl.subscription.EventSubscriptionQueryImpl;

public class EventSubscriptionPersistence {
	protected Connection connection;
	protected SqlCommand sqlCommand;

	public EventSubscriptionPersistence(Connection connection) {
		this.connection = connection;
		sqlCommand = new SqlCommand(connection);
	}
	
	
	
	
	public List<EventSubscriptionEntity> findEventSubscriptionByQueryCriteria(EventSubscriptionQueryImpl eventSubscriptionQuery, Page page) {

		String sqlString="select "+Context.getProcessEngineConfiguration().getDbConfig().getDbSqlMap().get("topOrderBy")+" * ";
		List<Object> objectParamWhere = new ArrayList<Object>();
		sqlString=findEventSubscriptionByQueryCriteriaSql(sqlString,eventSubscriptionQuery,page,objectParamWhere);
		
		
		if (eventSubscriptionQuery.getOrderBy() != null) {

			sqlString = sqlString + " order by "+eventSubscriptionQuery.getOrderBy().toString();
		}
		
		if(page!=null)
		{
			Pagination pagination=Context.getProcessEngineConfiguration().getDbConfig().getPagination();
			sqlString=pagination.getPaginationSql(sqlString, page.getFirstResult(), page.getMaxResults(), "*");
		}
	
		
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlString, objectParamWhere);

		List<EventSubscriptionEntity> eventSubscriptionEntityList = new ArrayList<EventSubscriptionEntity>();

		for (Map<String, Object> dataMap : dataObj) {
			
			EventSubscriptionEntity eventSubscriptionEntity=new EventSubscriptionEntity(dataMap);

			eventSubscriptionEntityList.add(eventSubscriptionEntity);
		}
		
		return eventSubscriptionEntityList;
		//select distinct T.* from
	}
	
	private String findEventSubscriptionByQueryCriteriaSql(String sqlString,EventSubscriptionQueryImpl eventSubscriptionQuery, Page page,List<Object> objectParamWhere){
		
		
		sqlString = sqlString+" FROM FIXFLOW_RUN_EVENT_SUBSCRIPTION  ";



		sqlString = sqlString + " WHERE 1=1";
		

		
		
		
		if (eventSubscriptionQuery.getSubscriptionId()!= null) {
			sqlString = sqlString + " and SUBSCRIPTION_ID=? ";
			objectParamWhere.add(eventSubscriptionQuery.getSubscriptionId());
		}
		
		if (eventSubscriptionQuery.getProcessDefinitionId() != null) {
			sqlString = sqlString + " and PROCESSDEFINITION_ID=? ";
			objectParamWhere.add(eventSubscriptionQuery.getProcessDefinitionId());
		}
		
		if (eventSubscriptionQuery.getTokenId() != null) {
			sqlString = sqlString + " and TOKEN_ID "+eventSubscriptionQuery.getTokenId() ;
		}
		
		if (eventSubscriptionQuery.getSubscriptionType() != null) {
			sqlString = sqlString + " and SUBSCRIPTION_TYPE "+eventSubscriptionQuery.getSubscriptionType().toString() ;
		}
		
		if (eventSubscriptionQuery.getMessageId() != null) {
			sqlString = sqlString + " and MESSAGE_ID "+eventSubscriptionQuery.getMessageId() ;
		}
		
		if (eventSubscriptionQuery.getSignalId() != null) {
			sqlString = sqlString + " and SIGNAL_ID "+eventSubscriptionQuery.getSignalId() ;
		}
		
		
		return sqlString;

	}
	
	
	
	
	
	
	public void saveEventSubscriptionEntity(EventSubscriptionEntity eventSubscriptionEntity){
		

		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(eventSubscriptionEntity.getId());

		// 设置查询字符串
		String sqlText = "SELECT COUNT(1) FROM FIXFLOW_RUN_EVENT_SUBSCRIPTION WHERE SUBSCRIPTION_ID=?";
		// 执行查询Sql语句,判断任务是否存在于数据库中.
		int rowNum = Integer.parseInt(sqlCommand.queryForValue(sqlText, objectParamWhere).toString());

		if (rowNum == 0) {
			insertEventSubscriptionEntity(eventSubscriptionEntity);
		} else {
			updateEventSubscriptionEntity(eventSubscriptionEntity);
		}
		
		
	}
	
	


	void insertEventSubscriptionEntity(EventSubscriptionEntity eventSubscriptionEntity) {


		Map<String, Object> objectParam =eventSubscriptionEntity.getEventSubscriptionDbMap();


		// 执行插入语句
		sqlCommand.insert("FIXFLOW_RUN_EVENT_SUBSCRIPTION", objectParam);

	}
	
	

	void updateEventSubscriptionEntity(EventSubscriptionEntity eventSubscriptionEntity) {
		// 构建查询参数
		
		Map<String, Object> objectParam =eventSubscriptionEntity.getEventSubscriptionDbMap();

		// 构建Where查询参数
		Object[] objectParamWhere = { eventSubscriptionEntity.getId() };

		// 执行插入语句
		sqlCommand.update("FIXFLOW_RUN_EVENT_SUBSCRIPTION", objectParam, " SUBSCRIPTION_ID=?", objectParamWhere);

	}
	
	
	public void deleteEventSubscriptionEntityById(String id) {

		Object[] objectParamWhere = { id };

		sqlCommand.delete("FIXFLOW_RUN_EVENT_SUBSCRIPTION", " SUBSCRIPTION_ID=?", objectParamWhere);

	}
	
}
