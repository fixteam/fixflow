package com.founder.fix.fixflow.designer.persistence;


import java.sql.Connection;
import java.util.HashMap;

import java.util.Map;




import com.founder.fix.fixflow.designer.database.SqlCommand;






public class ProcessDefinitionPersistence {

	public Connection connection;
	protected SqlCommand sqlCommand;

	public ProcessDefinitionPersistence(Connection connection) {
		this.connection = connection;
		// 初始化数据库操作类
		sqlCommand = new SqlCommand(connection);
	}
	
	
	
	
	public void insertProcessDefinition(Map<String, Object> resourceMap) throws Exception
	{
		
		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();

	
		objectParam.put("PROCESS_ID", resourceMap.get("processDefinitionId"));
		objectParam.put("PROCESS_NAME", resourceMap.get("processDefinitionName"));
		objectParam.put("PROCESS_KEY", resourceMap.get("processDefinitionKey"));
		objectParam.put("CATEGORY", resourceMap.get("category"));
		objectParam.put("VERSION", resourceMap.get("version"));
		objectParam.put("RESOURCE_NAME", resourceMap.get("resourceName"));
		objectParam.put("DEPLOYMENT_ID", resourceMap.get("deploymentId"));
		//objectParam.put("START_FORM_KEY", resourceMap.get("startForm"));
		objectParam.put("RESOURCE_ID", resourceMap.get("resourceId"));
		//objectParam.put("SUB_TASK_ID", resourceMap.get("subTaskId"));
		

		
		// 执行插入语句
		sqlCommand.insert("FIXFLOW_DEF_PROCESSDEFINITION", objectParam);
	}

	
	public void updateProcessDefinition(Map<String, Object> resourceMap) throws Exception
	{
		
		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();


		// 令牌编号 String
		objectParam.put("PROCESS_NAME", resourceMap.get("processDefinitionName"));
		
		objectParam.put("CATEGORY", resourceMap.get("category"));
		
		// 构建Where查询参数
		Object[] objectParamWhere = { resourceMap.get("processDefinitionId") };
		
		
		

		// 执行更新语句
		try {
			sqlCommand.update("FIXFLOW_DEF_PROCESSDEFINITION", objectParam, " PROCESS_ID=?", objectParamWhere);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
