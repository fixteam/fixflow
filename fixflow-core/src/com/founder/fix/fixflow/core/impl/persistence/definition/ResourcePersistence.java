package com.founder.fix.fixflow.core.impl.persistence.definition;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class ResourcePersistence {
	
	public Connection connection;
	protected SqlCommand sqlCommand;
	

	public ResourcePersistence(Connection connection)
	{
		this.connection = connection;
		// 初始化数据库操作类
		sqlCommand = new SqlCommand(connection);
	}
	
	
	public void insertResource(PersistentObject persistentObject)
	{
		Map<String, Object> resourceMap = persistentObject.getPersistentState();

		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();


		objectParam.put("ID", resourceMap.get("id"));
		objectParam.put("NAME", resourceMap.get("name"));
		objectParam.put("BYTES", resourceMap.get("bytes"));
		objectParam.put("DEPLOYMENT_ID", resourceMap.get("deploymentId"));
		
		
		
		// 执行插入语句
		sqlCommand.insert("FIXFLOW_DEF_BYTEARRAY", objectParam);

	}
	
	public void updateResource(PersistentObject persistentObject)
	{
		
		ResourceEntity resourceEntity=(ResourceEntity)persistentObject;

		
		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();
		// 令牌编号 String
		objectParam.put("BYTES", resourceEntity.getBytes());
		
		// 构建Where查询参数
		Object[] objectParamWhere = { resourceEntity.getId() };

		// 执行更新语句
		sqlCommand.update("FIXFLOW_DEF_BYTEARRAY", objectParam, " ID=?", objectParamWhere);
		
	}
	
	public void deleteResourcesByDeploymentId(String deploymentId)
	{
		// 构建Where查询参数
		Object[] objectParamWhere = { deploymentId };
		sqlCommand.delete("FIXFLOW_DEF_BYTEARRAY", " ID=?",objectParamWhere);
	}
	
	
	public ResourceEntity getResourceInputStream(String resourceId)
	{
		List<Object> objectParamWhere = new ArrayList<Object>();

		String selectSql = "SELECT * FROM FIXFLOW_DEF_BYTEARRAY WHERE ID = ?";
		
		objectParamWhere.add(resourceId.toString());

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(selectSql, objectParamWhere);

		String id=StringUtil.getString(dataObj.get(0).get("ID"));
		String name=StringUtil.getString(dataObj.get(0).get("NAME"));
		byte[] bytes=null;
		Object bytesObject = dataObj.get(0).get("BYTES");
		if (bytesObject != null) {
			bytes=(byte[])bytesObject;
		}
		
		String deploymentId=StringUtil.getString(dataObj.get(0).get("DEPLOYMENT_ID"));
		
		ResourceEntity resourceEntity=new ResourceEntity();
		resourceEntity.setId(id);
		resourceEntity.setName(name);
		resourceEntity.setBytes(bytes);
		resourceEntity.setDeploymentId(deploymentId);
		
		return resourceEntity;
		
	}

}
