package com.founder.fix.fixflow.core.impl.persistence.definition;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;

public class DeploymentPersistence {
	
	
	public Connection connection;
	protected SqlCommand sqlCommand;
	

	public DeploymentPersistence(Connection connection)
	{
		this.connection = connection;
		// 初始化数据库操作类
		sqlCommand = new SqlCommand(connection);
	}
	
	public void insertDeployment(PersistentObject persistentObject)
	{
		Map<String, Object> deploymentMap = persistentObject.getPersistentState();

		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();
		// 意见编号 String
		
		objectParam.put("ID", deploymentMap.get("id"));
		objectParam.put("NAME", deploymentMap.get("name"));
		objectParam.put("DEPLOY_TIME", deploymentMap.get("deploymentTime"));


		// 执行插入语句
		sqlCommand.insert("FIXFLOW_DEF_DEPLOYMENT", objectParam);

	}
	
	public void deleteDeployment(String persistentObjectId)
	{
		// 构建Where查询参数
		Object[] objectParamWhere = { persistentObjectId };
		sqlCommand.delete("FIXFLOW_DEF_DEPLOYMENT", " ID=?",objectParamWhere);
		
	}

}
