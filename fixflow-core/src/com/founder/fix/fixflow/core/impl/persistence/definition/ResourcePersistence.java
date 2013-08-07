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
	
	public List<ResourceEntity> getResourceEntityByDeploymentId(String deploymentId)
	{
		
		List<ResourceEntity> resourceEntitys=new ArrayList<ResourceEntity>();
		
		List<Object> objectParamWhere = new ArrayList<Object>();

		String selectSql = "SELECT * FROM FIXFLOW_DEF_BYTEARRAY WHERE DEPLOYMENT_ID = ?";
		
		objectParamWhere.add(deploymentId.toString());

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(selectSql, objectParamWhere);

		for (Map<String, Object> map : dataObj) {
			String id=StringUtil.getString(map.get("ID"));
			String name=StringUtil.getString(map.get("NAME"));
			byte[] bytes=null;
			Object bytesObject = map.get("BYTES");
			if (bytesObject != null) {
				bytes=(byte[])bytesObject;
			}
			
			
			
			ResourceEntity resourceEntity=new ResourceEntity();
			resourceEntity.setId(id);
			resourceEntity.setName(name);
			resourceEntity.setBytes(bytes);
			resourceEntity.setDeploymentId(deploymentId);
			resourceEntitys.add(resourceEntity);
		}
		
		
		
		return resourceEntitys;
		
	}
	
	public ResourceEntity selectResourceByDeploymentIdAndResourceName(String deploymentId, String resourceName) {

		
		
		
		

		String sqlText = "SELECT * FROM FIXFLOW_DEF_BYTEARRAY WHERE NAME=? and DEPLOYMENT_ID=?";

		// 构建查询参数

		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(resourceName);
		objectParamWhere.add(deploymentId);

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);
		Map<String, Object> dataMap = dataObj.get(0);
		
		
		String id=StringUtil.getString(dataMap.get("ID"));
		Object bytesObject = dataMap.get("BYTES");
		
		
		
		ResourceEntity entity=new ResourceEntity();
		entity.setId(id);
		entity.setName(resourceName);
		entity.setDeploymentId(deploymentId);
		if (bytesObject != null) {
			byte[] bytes = (byte[]) bytesObject;
			entity.setBytes(bytes);
		}
		
		return entity;
		
		
	}
}
