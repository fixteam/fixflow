package com.founder.fix.fixflow.designer.persistence;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.designer.database.SqlCommand;


public class DeploymentPersistence {
	
	
	public Connection connection;
	protected SqlCommand sqlCommand;
	

	public DeploymentPersistence(Connection connection)
	{
		this.connection = connection;
		// 初始化数据库操作类
		sqlCommand = new SqlCommand(connection);
	}
	
	public void insertDeployment(Map<String, Object> deploymentMap)
	{
		
		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();
		// 意见编号 String
		
		objectParam.put("ID", deploymentMap.get("id"));
		objectParam.put("NAME", deploymentMap.get("name"));
		objectParam.put("DEPLOY_TIME", deploymentMap.get("deploymentTime"));


		// 执行插入语句
		try {
			sqlCommand.insert("FIXFLOW_DEF_DEPLOYMENT", objectParam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void updateDeployment(Map<String, Object> deploymentMap)
	{
		
		

		
		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();
		// 令牌编号 String
		objectParam.put("DEPLOY_TIME", deploymentMap.get("DEPLOY_TIME"));
		
		// 构建Where查询参数
		Object[] objectParamWhere = { deploymentMap.get("ID") };

		// 执行更新语句
		try {
			sqlCommand.update("FIXFLOW_DEF_DEPLOYMENT", objectParam, " ID=?", objectParamWhere);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
