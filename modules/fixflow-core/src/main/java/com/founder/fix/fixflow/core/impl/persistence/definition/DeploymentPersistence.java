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

import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

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
	

	public DeploymentEntity getDeployment(String deploymentId)
	{
		// 构建Where查询参数

		List<Object> objectParamWhere=new ArrayList<Object>();
		objectParamWhere.add(deploymentId);
		
		List<Map<String, Object>> queryData=sqlCommand.queryForList("SELECT * FROM FIXFLOW_DEF_DEPLOYMENT WHERE ID=?", objectParamWhere);
		Map<String, Object> dataMap=queryData.get(0);
		
		DeploymentEntity deploymentEntity=new DeploymentEntity();
		deploymentEntity.setId(deploymentId);
		deploymentEntity.setDeploymentTime(StringUtil.getDate(dataMap.get("DEPLOY_TIME")));
		deploymentEntity.setName(StringUtil.getString(dataMap.get("NAME")));
		
		
		
		ResourcePersistence resourcePersistence=ProcessObjectFactory.FACTORYINSTANCE.createResourcePersistence(connection);
		List<ResourceEntity> resourceEntities=resourcePersistence.getResourceEntityByDeploymentId(deploymentId);
		for (ResourceEntity resourceEntity : resourceEntities) {
			deploymentEntity.addResource(resourceEntity);
		}
		return deploymentEntity;

	}

	public void updateDeployment(DeploymentEntity deploymentEntity) {
		
		
		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();
		// 令牌编号 String
		objectParam.put("DEPLOY_TIME", ClockUtil.getCurrentTime());
		
		// 构建Where查询参数
		Object[] objectParamWhere = { deploymentEntity.getId() };

		// 执行更新语句
		sqlCommand.update("FIXFLOW_DEF_DEPLOYMENT", objectParam, " ID=?", objectParamWhere);
		
	}
	
	

}
