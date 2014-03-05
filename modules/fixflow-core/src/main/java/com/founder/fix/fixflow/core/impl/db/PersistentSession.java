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
package com.founder.fix.fixflow.core.impl.db;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.exception.FixFlowDbException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.ProcessDefinitionQueryImpl;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentEntity;
import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentPersistence;
import com.founder.fix.fixflow.core.impl.persistence.definition.ProcessDefinitionPersistence;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourcePersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.CommentPersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.EventSubscriptionPersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.HistoryPersistence;

import com.founder.fix.fixflow.core.impl.subscription.EventSubscriptionEntity;
import com.founder.fix.fixflow.core.impl.subscription.EventSubscriptionQueryImpl;
import com.founder.fix.fixflow.core.impl.util.StringUtil;


public class PersistentSession {

	protected Connection connection;

	public PersistentSession(Connection connection) {
		this.connection = connection;
	}

	public void update(String updateStatement, PersistentObject persistentObject) {
		
		if (updateStatement.equals("updateDeployment")) {
			DeploymentPersistence resourceManager = ProcessObjectFactory.FACTORYINSTANCE.createDeploymentPersistence(connection);
			resourceManager.updateDeployment((DeploymentEntity)persistentObject);
			return;
		}
		
		
		if (updateStatement.equals("updateResource")) {
			ResourcePersistence resourceManager = ProcessObjectFactory.FACTORYINSTANCE.createResourcePersistence(connection);
			resourceManager.updateResource(persistentObject);
			return;
		}
		
		
	}

	public void delete(String deleteStatement, Object parameter) {
		if (deleteStatement.equals("deleteDeployment")) {

			DeploymentPersistence deploymentPersistence = ProcessObjectFactory.FACTORYINSTANCE.createDeploymentPersistence(connection);
			deploymentPersistence.deleteDeployment(StringUtil.getString(parameter.toString()));
		}

		if (deleteStatement.equals("deleteEventSubscriptionEntity")) {
			EventSubscriptionPersistence eventSubscriptionPersistence = new EventSubscriptionPersistence(connection);
			try {
				eventSubscriptionPersistence.deleteEventSubscriptionEntityById(parameter.toString());
			} catch (Exception e) {
				throw new FixFlowException("事件订阅删除出错! 错误信息:  " + e.getMessage(), e);
			}
		}

		if (deleteStatement.equals("deleteProcessDefinitionsByDeploymentId")) {
			ProcessDefinitionPersistence processDefinitionPersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessDefinitionPersistence(connection);
			processDefinitionPersistence.deleteProcessDefinitionsByDeploymentId(StringUtil.getString(parameter.toString()));
		}
		if (deleteStatement.equals("deleteResourcesByDeploymentId")) {
			ResourcePersistence resourceManager = ProcessObjectFactory.FACTORYINSTANCE.createResourcePersistence(connection);
			resourceManager.deleteResourcesByDeploymentId(StringUtil.getString(parameter));

		}


	}

	@SuppressWarnings({ "rawtypes"})
	public List selectList(String statement, Object parameter, Page page) {

		if (statement.equals("selectProcessDefinitionGroupKey")) {
			ProcessDefinitionPersistence processDefinitionPersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessDefinitionPersistence(connection);
			return processDefinitionPersistence.selectProcessDefinitionGroupKey();
		}



		if (statement.equals("findEventSubscriptionByQueryCriteria")) {
			EventSubscriptionPersistence eventSubscriptionPersistence = new EventSubscriptionPersistence(connection);
			try {
				eventSubscriptionPersistence.findEventSubscriptionByQueryCriteria((EventSubscriptionQueryImpl) parameter, page);
			} catch (Exception e) {
				throw new FixFlowException("事件订阅查询出错! 错误信息:  " + e.getMessage(), e);
			}
		}

		if (statement.equals("selectProcessDefinitionsByQueryCriteria")) {
			ProcessDefinitionPersistence processDefinitionPersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessDefinitionPersistence(connection);
			return processDefinitionPersistence.selectProcessDefinitionsByQueryCriteria((ProcessDefinitionQueryImpl) parameter,page);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public Object selectOne(String statement, Object parameter) {
		
		
		if (statement.equals("selectResourceByDeploymentIdAndResourceName")) {
			ResourcePersistence resourcePersistence = ProcessObjectFactory.FACTORYINSTANCE.createResourcePersistence(connection);
			Map<String, String> strmap = (Map<String, String>) parameter;
			String deploymentId = strmap.get("deploymentId");
			String resourceName = strmap.get("resourceName");
		
			return resourcePersistence.selectResourceByDeploymentIdAndResourceName(deploymentId,resourceName);
		}
		
		if (statement.equals("selectProcessDefinitionByDeploymentAndKey")) {
			ProcessDefinitionPersistence processDefinitionPersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessDefinitionPersistence(connection);
			
		
			return processDefinitionPersistence.selectProcessDefinitionByDeploymentAndKey(parameter);
		}
		

		

		
		
		if (statement.equals("findUserSubmitProcess")) {
			ProcessDefinitionPersistence processDefinitionPersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessDefinitionPersistence(connection);
			Map<String, String> strmap = (Map<String, String>) parameter;
			String userId = strmap.get("userId");
			int number = StringUtil.getInt(strmap.get("number"));
			return processDefinitionPersistence.findUserSubmitProcess(userId,number);
		}
		
		

		if (statement.equals("selectDeploymentById")) {
			DeploymentPersistence deploymentPersistence = ProcessObjectFactory.FACTORYINSTANCE.createDeploymentPersistence(connection);
			
			String deploymentId = StringUtil.getString(parameter);

			return deploymentPersistence.getDeployment(deploymentId);
		}
		
		



		if (statement.equals("selectLatestProcessDefinitionByKey")) {
			ProcessDefinitionPersistence processDefinitionPersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessDefinitionPersistence(connection);
			ProcessDefinitionBehavior processDefinition = processDefinitionPersistence.selectLatestProcessDefinitionByKey(parameter.toString());
			return processDefinition;
		}
		if (statement.equals("selectProcessDefinitionById")) {
			ProcessDefinitionPersistence processDefinitionPersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessDefinitionPersistence(connection);
			ProcessDefinitionBehavior processDefinition = processDefinitionPersistence.selectProcessDefinitionById(parameter.toString());
			return processDefinition;
		}

	

		if (statement.equals("selectResourceByResourceId")) {
			ResourcePersistence resourcePersistence = ProcessObjectFactory.FACTORYINSTANCE.createResourcePersistence(connection);
			return resourcePersistence.getResourceInputStream(parameter.toString());
		}

		if (statement.equals("selectProcessDefinitionByKeyAndVersion")) {
			ProcessDefinitionPersistence processDefinitionPersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessDefinitionPersistence(connection);
			ProcessDefinitionBehavior processDefinition = processDefinitionPersistence.selectLatestProcessDefinitionByKeyAndVersion(parameter);
			return processDefinition;
		}

		
		if(statement.equals("selectProcessDefinitionCountByQueryCriteria")){
			ProcessDefinitionPersistence processDefinitionPersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessDefinitionPersistence(connection);
			return processDefinitionPersistence.selectProcessDefinitionsCountByQueryCriteria((ProcessDefinitionQueryImpl)parameter);
		}

		return null;

	}

	public void save(String saveStatement, PersistentObject persistentObject) {

		

		if (saveStatement.equals("saveEventSubscriptionEntity")) {
			EventSubscriptionPersistence eventSubscriptionPersistence = new EventSubscriptionPersistence(connection);
			try {
				eventSubscriptionPersistence.saveEventSubscriptionEntity((EventSubscriptionEntity) persistentObject);
			} catch (Exception e) {
				throw new FixFlowDbException("事件订阅持久化出错! 错误信息:  " + e.getMessage(), e);
			}
		}

		

	}

	public void insert(String insertStatement, PersistentObject persistentObject) {

		if (insertStatement.equals("insertComment")) {
			CommentPersistence commentPersistence = new CommentPersistence(connection);
			commentPersistence.insertComment(persistentObject);
		}
		if (insertStatement.equals("insertDeployment")) {
			DeploymentPersistence deploymentPersistence = ProcessObjectFactory.FACTORYINSTANCE.createDeploymentPersistence(connection);
			deploymentPersistence.insertDeployment(persistentObject);
		}
		if (insertStatement.equals("insertResource")) {
			ResourcePersistence resourceManager = ProcessObjectFactory.FACTORYINSTANCE.createResourcePersistence(connection);
			resourceManager.insertResource(persistentObject);
		}
		if (insertStatement.equals("insertProcessDefinition")) {
			ProcessDefinitionPersistence processDefinitionPersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessDefinitionPersistence(connection);
			processDefinitionPersistence.insertProcessDefinition(persistentObject);
		}

	}
	
	@SuppressWarnings("unchecked")
	public Object execute(String execStatement,Object parameter){
		if("archive".equals(execStatement)){
			HistoryPersistence historyPersistence = ProcessObjectFactory.FACTORYINSTANCE.createHistoryPersistence(connection);
			Map<String,Object> paraMap = (Map<String,Object>)parameter;
			return historyPersistence.archive(paraMap);
		}
		
		return null;
	}

}
