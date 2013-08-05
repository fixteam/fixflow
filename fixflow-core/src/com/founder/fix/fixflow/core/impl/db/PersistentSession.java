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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.exception.FixFlowDbException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.ProcessDefinitionQueryImpl;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.job.JobEntity;
import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentPersistence;
import com.founder.fix.fixflow.core.impl.persistence.definition.ProcessDefinitionPersistence;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourcePersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.CommentPersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.EventSubscriptionPersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.IdentityLinkPersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.JobPersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.ProcessInstancePersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.TaskInstancePersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.TokenPersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.VariablePersistence;
import com.founder.fix.fixflow.core.impl.runtime.IdentityLinkQueryImpl;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceQueryImpl;
import com.founder.fix.fixflow.core.impl.runtime.TokenQueryImpl;
import com.founder.fix.fixflow.core.impl.subscription.EventSubscriptionEntity;
import com.founder.fix.fixflow.core.impl.subscription.EventSubscriptionQueryImpl;
import com.founder.fix.fixflow.core.impl.task.IdentityLinkEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.task.TaskQueryImpl;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.variable.VariableQueryEntity;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;

public class PersistentSession {

	protected Connection connection;

	public PersistentSession(Connection connection) {
		this.connection = connection;
	}

	public void update(String updateStatement, PersistentObject persistentObject) {
		if (updateStatement.equals("updateResource")) {
			ResourcePersistence resourceManager = new ResourcePersistence(connection);
			resourceManager.updateResource(persistentObject);
		}
	}

	public void delete(String deleteStatement, Object parameter) {
		if (deleteStatement.equals("deleteDeployment")) {

			DeploymentPersistence deploymentPersistence = new DeploymentPersistence(connection);
			deploymentPersistence.deleteDeployment(StringUtil.getString(parameter.toString()));
		}

		if (deleteStatement.equals("deleteJob")) {
			JobPersistence jobPersistence = new JobPersistence(connection);
			jobPersistence.deleteJob(parameter.toString());
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
			ProcessDefinitionPersistence processDefinitionPersistence = new ProcessDefinitionPersistence(connection);
			processDefinitionPersistence.deleteProcessDefinitionsByDeploymentId(StringUtil.getString(parameter.toString()));
		}
		if (deleteStatement.equals("deleteResourcesByDeploymentId")) {
			ResourcePersistence resourceManager = new ResourcePersistence(connection);
			resourceManager.deleteResourcesByDeploymentId(StringUtil.getString(parameter));

		}

		if (deleteStatement.equals("deleteProcessInstanceByProcessInstanceId")) {
			ProcessInstancePersistence processInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessInstancePersistence(connection);
			processInstancePersistence.deleteProcessInstanceByProcessInstanceId(StringUtil.getString(parameter.toString()));
		}
		if (deleteStatement.equals("deleteTaskInstanceByProcessInstanceId")) {

			TaskInstancePersistence taskInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createTaskInstancePersistence(connection);
			taskInstancePersistence.deleteTaskInstanceByProcessInstanceId(StringUtil.getString(parameter.toString()));
		}
		if (deleteStatement.equals("deleteTokenByProcessInstanceId")) {
			TokenPersistence tokenPersistence = new TokenPersistence(connection);
			tokenPersistence.deleteTokenByProcessInstanceId(StringUtil.getString(parameter.toString()));

		}

		if (deleteStatement.equals("deleteIdentityLinkByProcessInstanceId")) {
			IdentityLinkPersistence identityLinkPersistence = new IdentityLinkPersistence(connection);
			identityLinkPersistence.deleteIdentityLinkByProcessInstanceId(StringUtil.getString(parameter.toString()));

		}

		if (deleteStatement.equals("deleteTaskInstanceByTaskInstanceId")) {
			TaskInstancePersistence taskInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createTaskInstancePersistence(connection);
			taskInstancePersistence.deleteTaskInstanceByTaskInstanceId((StringUtil.getString(parameter.toString())));
		}

		if (deleteStatement.equals("deleteVariable")) {
			VariablePersistence variablePersistence = new VariablePersistence(connection);
			variablePersistence.deleteVariable((VariableQueryEntity) parameter);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectList(String statement, Object parameter, Page page) {

		if (statement.equals("selectProcessDefinitionGroupKey")) {
			ProcessDefinitionPersistence processDefinitionPersistence = new ProcessDefinitionPersistence(connection);
			return processDefinitionPersistence.selectProcessDefinitionGroupKey();
		}

		if (statement.endsWith("findAgentUsers")) {
			TaskInstancePersistence taskInstancePersistence = new TaskInstancePersistence(connection);
			return taskInstancePersistence.findAgentUsers(StringUtil.getString(parameter));
		}

		if (statement.equals("selectTaskByQueryCriteria")) {
			TaskInstancePersistence taskInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createTaskInstancePersistence(connection);
			return taskInstancePersistence.findTasksByQueryCriteria((TaskQueryImpl) parameter, page);
		}

		if (statement.equals("findEventSubscriptionByQueryCriteria")) {
			EventSubscriptionPersistence eventSubscriptionPersistence = new EventSubscriptionPersistence(connection);
			try {
				eventSubscriptionPersistence.findEventSubscriptionByQueryCriteria((EventSubscriptionQueryImpl) parameter, page);
			} catch (Exception e) {
				throw new FixFlowException("事件订阅查询出错! 错误信息:  " + e.getMessage(), e);
			}
		}

		if (statement.equals("selectProcessPerformance")) {
			ProcessInstancePersistence processInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessInstancePersistence(connection);
			Map<String, String> strmap = (Map<String, String>) parameter;
			String startTime = strmap.get("startTime");
			String endTime = strmap.get("endTime");
			String processKey = strmap.get("processKey");
			return processInstancePersistence.selectProcessPerformance(startTime, endTime, processKey, page);
		}

		if (statement.equals("selectProcessPerformanceInterface2")) {
			ProcessInstancePersistence processInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessInstancePersistence(connection);
			Map<String, String> strmap = (Map<String, String>) parameter;
			String startTime = strmap.get("startTime");
			String endTime = strmap.get("endTime");
			return processInstancePersistence.selectProcessPerformance(startTime, endTime, page);
		}

		if (statement.equals("selectProcessPerformanceInterface1")) {
			ProcessInstancePersistence processInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessInstancePersistence(connection);
			Map<String, String> strmap = (Map<String, String>) parameter;
			String startTime = strmap.get("startTime");
			String endTime = strmap.get("endTime");
			return processInstancePersistence.selectProcessPerformance(startTime, endTime);
		}

		if (statement.equals("selectProcessPerformanceInterface4")) {
			ProcessInstancePersistence processInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessInstancePersistence(connection);
			Map<String, Object> strmap = (Map<String, Object>) parameter;
			String[] processKey = (String[]) strmap.get("processKey");
			String startTime = (String) strmap.get("startTime");
			String endTime = (String) strmap.get("endTime");
			return processInstancePersistence.selectProcessPerformance(processKey, startTime, endTime);
		}

		if (statement.equals("selectProcessPerformanceInterface3")) {
			ProcessInstancePersistence processInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessInstancePersistence(connection);
			Map<String, Object> strmap = (Map<String, Object>) parameter;
			String[] pid = (String[]) strmap.get("pid");
			String startTime = (String) strmap.get("startTime");
			String endTime = (String) strmap.get("endTime");
			return processInstancePersistence.selectProcessPerformanceTask(pid, startTime, endTime);
		}

		if (statement.equals("selectProcessPerformanceInterface5")) {
			ProcessInstancePersistence processInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessInstancePersistence(connection);
			Map<String, String> strmap = (Map<String, String>) parameter;
			String startTime = strmap.get("startTime");
			String endTime = strmap.get("endTime");
			String pid = strmap.get("pid");
			return processInstancePersistence.selectProcessPerformance(startTime, endTime, pid);
		}

		if (statement.equals("selectCommentsByProcessInstanceId")) {
			CommentPersistence commentPersistence = new CommentPersistence(connection);
			return commentPersistence.selectCommentsByProcessInstanceId(parameter);
		}
		if (statement.equals("selectTasksByTokenIdList")) {
			TaskInstancePersistence taskInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createTaskInstancePersistence(connection);
			return taskInstancePersistence.findTasksByTokenIdList((List<String>) parameter);
		}
		if (statement.equals("selectProcessDefinitionsByQueryCriteria")) {
			ProcessDefinitionPersistence processDefinitionPersistence = new ProcessDefinitionPersistence(connection);
			return processDefinitionPersistence.selectProcessDefinitionsByQueryCriteria((ProcessDefinitionQueryImpl) parameter);
		}

		if (statement.equals("selectProcessInstanceByQueryCriteria")) {
			ProcessInstancePersistence processInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessInstancePersistence(connection);
			return processInstancePersistence.selectProcessInstanceByQueryCriteria((ProcessInstanceQueryImpl) parameter, page);
		}

		if (statement.equals("selectTokenByQueryCriteria")) {
			TokenPersistence tokenPersistence = new TokenPersistence(connection);
			return tokenPersistence.selectTokenByQueryCriteria((TokenQueryImpl) parameter, page);
		}

		if (statement.equals("selectIdentityLinkByQueryCriteria")) {
			IdentityLinkPersistence identityLinkPersistence = new IdentityLinkPersistence(connection);
			return identityLinkPersistence.selectIdentityLinkByQueryCriteria((IdentityLinkQueryImpl) parameter, page);
		}

		if (statement.equals("selectIdentityLinksByTask")) {
			IdentityLinkPersistence identityLinkPersistence = new IdentityLinkPersistence(connection);
			return identityLinkPersistence.selectIdentityLinksByTask(StringUtil.getString(parameter));
		}

		if (statement.equals("getTaskStatusByByProcessInstanceIdList")) {
			TaskInstancePersistence taskInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createTaskInstancePersistence(connection);
			return taskInstancePersistence.getTaskStatusByByProcessInstanceIdList((List<String>) (parameter));
		}

		if (statement.equals("getTaskStatusByByProcessInstanceIdList")) {
			TaskInstancePersistence taskInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createTaskInstancePersistence(connection);
			return taskInstancePersistence.getTaskStatusByByProcessInstanceIdList((List<String>) (parameter));
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public Object selectOne(String statement, Object parameter) {

		if (statement.equals("selectProcessPerformanceInterface22")) {
			ProcessInstancePersistence processInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessInstancePersistence(connection);
			Map<String, String> strmap = (Map<String, String>) parameter;
			String startTime = strmap.get("startTime");
			String endTime = strmap.get("endTime");
			return processInstancePersistence.selectProcessPerformance2(startTime, endTime);
		}

		if (statement.equals("selectProcessInstance")) {

			Map<String, Object> parameters = (HashMap<String, Object>) parameter;
			ProcessInstancePersistence processInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessInstancePersistence(connection);
			String processInstanceId = parameters.get("processInstanceId").toString();
			ProcessDefinitionBehavior processDefinition = (ProcessDefinitionBehavior) parameters.get("processDefinition");
			ProcessInstanceEntity processInstanceImpl;
			try {
				processInstanceImpl = processInstancePersistence.getProcessInstance(processInstanceId, processDefinition);
			} catch (Exception e) {
				throw new FixFlowException("流程实例加载出错! 错误信息:  " + e.getMessage(), e);
			}
			return processInstanceImpl;

		}

		if (statement.equals("selectProcessInstanceByDefKeyAndBusinessKey")) {

			Map<String, Object> parameters = (HashMap<String, Object>) parameter;
			ProcessInstancePersistence processInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessInstancePersistence(connection);
			String processDefinitionKey = StringUtil.getString(parameters.get("processDefinitionKey"));
			String businessKey = StringUtil.getString(parameters.get("businessKey"));
			ProcessInstanceEntity processInstanceQueryTo;
			try {
				processInstanceQueryTo = processInstancePersistence.getProcessInstance(processDefinitionKey, businessKey);
			} catch (Exception e) {
				throw new FixFlowException("流程实例加载出错! 错误信息:  " + e.getMessage(), e);
			}
			return processInstanceQueryTo;

		}

		if (statement.equals("selectLatestProcessDefinitionByKey")) {
			ProcessDefinitionPersistence processDefinitionPersistence = new ProcessDefinitionPersistence(connection);
			ProcessDefinitionBehavior processDefinition = processDefinitionPersistence.selectLatestProcessDefinitionByKey(parameter.toString());
			return processDefinition;
		}
		if (statement.equals("selectProcessDefinitionById")) {
			ProcessDefinitionPersistence processDefinitionPersistence = new ProcessDefinitionPersistence(connection);
			ProcessDefinitionBehavior processDefinition = processDefinitionPersistence.selectProcessDefinitionById(parameter.toString());
			return processDefinition;
		}

		if (statement.equals("selectTaskInstance")) {
			TaskInstancePersistence taskInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createTaskInstancePersistence(connection);
			String taskInstanceId = parameter.toString();
			return taskInstancePersistence.findTaskById(taskInstanceId);
		}

		if (statement.equals("selectTaskCountByQueryCriteria")) {
			TaskInstancePersistence taskInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createTaskInstancePersistence(connection);
			return taskInstancePersistence.findTaskCountByQueryCriteria((TaskQueryImpl) parameter);

		}

		if (statement.equals("selectProcessInstanceCountByQueryCriteria")) {
			ProcessInstancePersistence processInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessInstancePersistence(connection);
			return processInstancePersistence.selectProcessInstanceCountByQueryCriteria((ProcessInstanceQueryImpl) parameter);
		}

		if (statement.equals("selectTokenCountByQueryCriteria")) {
			TokenPersistence tokenPersistence = new TokenPersistence(connection);
			return tokenPersistence.selectTokenCountByQueryCriteria((TokenQueryImpl) parameter);
		}

		if (statement.equals("selectIdentityLinkCountByQueryCriteria")) {
			IdentityLinkPersistence identityLinkPersistence = new IdentityLinkPersistence(connection);
			return identityLinkPersistence.selectIdentityLinkCountByQueryCriteria((IdentityLinkQueryImpl) parameter);
		}

		if (statement.equals("selectResourceByResourceId")) {
			ResourcePersistence resourcePersistence = new ResourcePersistence(connection);
			return resourcePersistence.getResourceInputStream(parameter.toString());
		}

		if (statement.equals("selectProcessDefinitionByKeyAndVersion")) {
			ProcessDefinitionPersistence processDefinitionPersistence = new ProcessDefinitionPersistence(connection);
			ProcessDefinitionBehavior processDefinition = processDefinitionPersistence.selectLatestProcessDefinitionByKeyAndVersion(parameter);
			return processDefinition;
		}

		if (statement.equals("queryVariable")) {
			VariablePersistence variablePersistence = new VariablePersistence(connection);
			return variablePersistence.queryVariable(parameter);
		}

		return null;

	}

	public void save(String saveStatement, PersistentObject persistentObject) {

		if (saveStatement.equals("saveProcessInstance")) {
			ProcessInstancePersistence processInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createProcessInstancePersistence(connection);
			try {
				processInstancePersistence.saveProcessInstance((ProcessInstance) persistentObject);
			} catch (Exception e) {
				throw new FixFlowDbException("流程实例持久化出错! 错误信息:  " + e.getMessage(), e);
			}
		}
		if (saveStatement.equals("saveJob")) {
			try {
				JobPersistence jobPersistence = new JobPersistence(connection);
				jobPersistence.saveJob((JobEntity) persistentObject);
			} catch (Exception e) {
				throw new FixFlowDbException("流程实例持久化出错! 错误信息:  " + e.getMessage(), e);
			}
		}

		if (saveStatement.equals("saveEventSubscriptionEntity")) {
			EventSubscriptionPersistence eventSubscriptionPersistence = new EventSubscriptionPersistence(connection);
			try {
				eventSubscriptionPersistence.saveEventSubscriptionEntity((EventSubscriptionEntity) persistentObject);
			} catch (Exception e) {
				throw new FixFlowDbException("事件订阅持久化出错! 错误信息:  " + e.getMessage(), e);
			}
		}

		if (saveStatement.equals("saveTaskInstance")) {
			TaskInstancePersistence taskInstancePersistence = ProcessObjectFactory.FACTORYINSTANCE.createTaskInstancePersistence(connection);
			try {
				taskInstancePersistence.saveTaskInstance((TaskInstanceEntity) persistentObject);
			} catch (Exception e) {
				throw new FixFlowDbException("任务实例持久化出错! 错误信息:  " + e.getMessage(), e);
			}
		}

		if (saveStatement.equals("saveVariable")) {
			VariablePersistence variableInstancePersistence = new VariablePersistence(connection);
			try {
				variableInstancePersistence.saveVariable(persistentObject);
			} catch (Exception e) {
				throw new FixFlowDbException("数据变量持久化出错! 错误信息:  " + e.getMessage(), e);
			}
		}

		if (saveStatement.equals("saveIdentityLink")) {
			IdentityLinkPersistence identityLinkPersistence = new IdentityLinkPersistence(connection);
			try {
				identityLinkPersistence.saveIdentityLink((IdentityLinkEntity) persistentObject);
			} catch (Exception e) {
				throw new FixFlowDbException("候选用户持久化出错! 错误信息:  " + e.getMessage(), e);
			}
		}

	}

	public void insert(String insertStatement, PersistentObject persistentObject) {

		if (insertStatement.equals("insertComment")) {
			CommentPersistence commentPersistence = new CommentPersistence(connection);
			commentPersistence.insertComment(persistentObject);
		}
		if (insertStatement.equals("insertDeployment")) {
			DeploymentPersistence deploymentPersistence = new DeploymentPersistence(connection);
			deploymentPersistence.insertDeployment(persistentObject);
		}
		if (insertStatement.equals("insertResource")) {
			ResourcePersistence resourceManager = new ResourcePersistence(connection);
			resourceManager.insertResource(persistentObject);
		}
		if (insertStatement.equals("insertProcessDefinition")) {
			ProcessDefinitionPersistence processDefinitionPersistence = new ProcessDefinitionPersistence(connection);
			processDefinitionPersistence.insertProcessDefinition(persistentObject);
		}

	}

}
