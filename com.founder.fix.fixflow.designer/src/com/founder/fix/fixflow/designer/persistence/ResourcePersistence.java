package com.founder.fix.fixflow.designer.persistence;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.designer.database.SqlCommand;



public class ResourcePersistence {
	
	public Connection connection;
	protected SqlCommand sqlCommand;
	

	public ResourcePersistence(Connection connection)
	{
		this.connection = connection;
		// 初始化数据库操作类
		sqlCommand = new SqlCommand(connection);
	}
	
	
	public void updateResource(Map<String, Object> resourceMap)
	{
		
		

		
		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();
		// 令牌编号 String
		objectParam.put("BYTES", resourceMap.get("BYTES"));
		
		objectParam.put("NAME", resourceMap.get("NAME"));
		
		// 构建Where查询参数
		Object[] objectParamWhere = { resourceMap.get("ID") };
		
		
		

		// 执行更新语句
		try {
			sqlCommand.update("FIXFLOW_DEF_BYTEARRAY", objectParam, " ID=?", objectParamWhere);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertResource(Map<String, Object> resourceMap)
	{
		
		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();


		objectParam.put("ID", resourceMap.get("id"));
		objectParam.put("NAME", resourceMap.get("name"));
		objectParam.put("BYTES", resourceMap.get("bytes"));
		objectParam.put("DEPLOYMENT_ID", resourceMap.get("deploymentId"));
		
		
		
		// 执行插入语句
		try {
			sqlCommand.insert("FIXFLOW_DEF_BYTEARRAY", objectParam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
