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
