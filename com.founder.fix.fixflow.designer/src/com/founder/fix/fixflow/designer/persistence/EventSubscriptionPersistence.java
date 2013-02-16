package com.founder.fix.fixflow.designer.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.designer.database.SqlCommand;



public class EventSubscriptionPersistence {
	protected Connection connection;
	protected SqlCommand sqlCommand;

	public EventSubscriptionPersistence(Connection connection) {
		this.connection = connection;
		sqlCommand = new SqlCommand(connection);
	}
	
	
	

	
	
	
	public void saveEventSubscriptionEntity(EventSubscriptionEntity eventSubscriptionEntity) throws Exception{
		

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
	
	


	void insertEventSubscriptionEntity(EventSubscriptionEntity eventSubscriptionEntity) throws Exception {


		Map<String, Object> objectParam =eventSubscriptionEntity.getEventSubscriptionDbMap();


		// 执行插入语句
		sqlCommand.insert("FIXFLOW_RUN_EVENT_SUBSCRIPTION", objectParam);

	}
	
	

	void updateEventSubscriptionEntity(EventSubscriptionEntity eventSubscriptionEntity) throws Exception {
		// 构建查询参数
		
		Map<String, Object> objectParam =eventSubscriptionEntity.getEventSubscriptionDbMap();

		// 构建Where查询参数
		Object[] objectParamWhere = { eventSubscriptionEntity.getId() };

		// 执行插入语句
		sqlCommand.update("FIXFLOW_RUN_EVENT_SUBSCRIPTION", objectParam, " SUBSCRIPTION_ID=?", objectParamWhere);

	}
	
	
	public void deleteEventSubscriptionEntityById(String id) throws Exception {

		Object[] objectParamWhere = { id };

		sqlCommand.delete("FIXFLOW_RUN_EVENT_SUBSCRIPTION", " SUBSCRIPTION_ID=?", objectParamWhere);

	}
	
}
