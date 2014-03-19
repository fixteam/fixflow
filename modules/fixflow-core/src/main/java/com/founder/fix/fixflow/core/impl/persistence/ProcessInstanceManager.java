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
package com.founder.fix.fixflow.core.impl.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.UserTask;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableEntity;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceQueryImpl;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.Token;

public class ProcessInstanceManager extends AbstractManager {

	/**
	 * 根据流程定义唯一编号删除流程实例
	 * 
	 * @param processDefinitionId
	 *            流程唯一编号
	 * @param deleteReason
	 *            无用参数
	 * @param cascade
	 *            是否级联删除
	 */
	@SuppressWarnings("unchecked")
	public void deleteProcessInstancesByProcessDefinition(String processDefinitionId, String deleteReason, boolean cascade) {
		List<String> processInstanceIds = getMappingSqlSession().selectList("selectProcessInstanceIdsByProcessDefinitionId", processDefinitionId);
		for (String processInstanceId : processInstanceIds) {
			deleteProcessInstance(processInstanceId, cascade);
		}
	}

	/**
	 * 删除流程实例
	 * 
	 * @param processInstanceId
	 *            流程实例编号
	 * @param cascade
	 *            是否级联
	 */
	public void deleteProcessInstance(String processInstanceId, boolean cascade) {

		getMappingSqlSession().delete("deleteProcessInstanceByProcessInstanceId", processInstanceId);

		if (cascade) {

			getCommandContext().getIdentityLinkManager().deleteIdentityLinkByProcessInstanceId(processInstanceId);
			getCommandContext().getTaskManager().deleteTaskByProcessInstanceId(processInstanceId);
			getCommandContext().getVariableManager().deleteVariableByProcessInstanceId(processInstanceId);
			getCommandContext().getTokenManager().deleteTokenByProcessInstanceId(processInstanceId);

		}

	}

	/**
	 * 获取流程实例
	 * 
	 * @param processInstanceId
	 *            流程实例编号
	 * @return
	 */
	public ProcessInstanceEntity findProcessInstanceById(String processInstanceId) {

		//CacheHandler cacheHandler = Context.getProcessEngineConfiguration().getCacheHandler();
		//Object cacheObj = cacheHandler.getCacheData("ProcessInstance_" + processInstanceId);

		//if (cacheObj == null) {

			ProcessInstanceEntity processInstanceEntity = (ProcessInstanceEntity) getMappingSqlSession().selectOne("findProcessInstanceById",
					processInstanceId);
			if(processInstanceEntity==null){
				throw new FixFlowException("查询的流程实例: "+processInstanceId +" 不存在");
			}
			initProcessInstance(processInstanceEntity);
			return processInstanceEntity;
		//	cacheHandler.putCacheData("ProcessInstance_" + processInstanceId, processInstanceEntity);
		//	return (ProcessInstanceEntity) getMappingSqlSession().selectOne("findProcessInstanceById", processInstanceId);

		//} else {
		//	return (ProcessInstanceEntity) cacheObj;
		//}

	}

	private void initProcessInstance(ProcessInstanceEntity processInstanceEntity) {

		String processDefinitionId = processInstanceEntity.getProcessDefinitionId();

		ProcessDefinitionManager processDefinitionManager = getCommandContext().getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

		processInstanceEntity.setProcessDefinition(processDefinition);

		readToken(processInstanceEntity);
	

	}

	void readToken(ProcessInstanceEntity processInstanceEntity) {
		
		String rocessInstanceId=processInstanceEntity.getId();
		
		List<TokenEntity> tokenEntities=getCommandContext().getTokenManager().findTokensByProcessInstanceId(rocessInstanceId);
		
		
		for (TokenEntity tokenEntity : tokenEntities) {
			tokenEntity.setProcessInstance(processInstanceEntity);
			processInstanceEntity.addTokenList(tokenEntity);
			if(StringUtil.isEmpty(tokenEntity.getParentTokenId())&&!tokenEntity.isFreeToken()){
				processInstanceEntity.setRootToken(tokenEntity);
			}
		}
		
		for (TokenEntity tokenEntity : tokenEntities) {
			if(StringUtil.isNotEmpty(tokenEntity.getParentTokenId())){
				TokenEntity tokenEntityParent=processInstanceEntity.getTokenMap().get(tokenEntity.getParentTokenId());
				tokenEntity.setParent(tokenEntityParent);
				tokenEntityParent.addChild(tokenEntity);
			}
		}
		
		List<TaskInstanceEntity> taskInstanceEntities=getCommandContext().getTaskManager().findTaskByProcessInstanceIdNotEnd(rocessInstanceId);
	
		for (TaskInstanceEntity taskInstanceEntity : taskInstanceEntities) {
			
			TokenEntity tokenEntity =processInstanceEntity.getTokenMap().get(taskInstanceEntity.getTokenId());
			if(tokenEntity.getFlowNode() instanceof UserTask){
				processInstanceEntity.getTaskMgmtInstance().addTaskInstanceEntity(taskInstanceEntity);
				
				taskInstanceEntity.setToken(tokenEntity);
			}

			
		}
		

	}

	



	public ProcessInstanceEntity findProcessInstanceById(String processInstanceId, ProcessDefinitionBehavior processDefinition) {

		return findProcessInstanceById(processInstanceId);

	}

	/**
	 * 查询子流程
	 * 
	 * @param processInstanceId
	 *            主流程实例编号
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public List<ProcessInstanceEntity> findSubProcessInstanceById(String processInstanceId) {
		
		List<ProcessInstanceEntity> processInstanceEntities=(List<ProcessInstanceEntity>) getMappingSqlSession().selectList("findSubProcessInstanceById", processInstanceId);
		for (ProcessInstanceEntity processInstanceEntity : processInstanceEntities) {
			initProcessInstance(processInstanceEntity);
		}
//		String processDefinitionId=StringUtil.getString(map.get(ProcessInstanceObjKey.ProcessDefinitionId().DataBaseKey()));
//		ProcessDefinitionBehavior processDefinitionBehavior=persistence.selectProcessDefinitionById(processDefinitionId);
//		ProcessInstanceEntity processInstanceEntity=getProcessInstance(processInstanceId, processDefinitionBehavior);
//		processInstanceEntities.add(processInstanceEntity);
		
		
		return processInstanceEntities;

	}

	/**
	 * 根据流程实例号和令牌号 查询子流程实例
	 * 
	 * @param processInstanceId
	 * @param tokenId
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	public List<ProcessInstanceEntity> findSubProcessInstanceByIdAndToken(String processInstanceId, String tokenId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processInstanceId", processInstanceId);
		parameters.put("tokenId", tokenId);
		List<ProcessInstanceEntity> processInstanceEntities=(List<ProcessInstanceEntity>) getMappingSqlSession().selectList("findSubProcessInstanceByIdAndToken", parameters);
		
		if(processInstanceEntities != null){
			for (ProcessInstanceEntity processInstanceEntity : processInstanceEntities) {
				initProcessInstance(processInstanceEntity);
			}
		}
		return processInstanceEntities;

	}

	/**
	 * 查询流程实例对象
	 * 
	 * @param processInstanceQueryImpl
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProcessInstanceEntity> findProcessInstanceByQueryCriteria(ProcessInstanceQueryImpl processInstanceQueryImpl, Page page) {
		return getMappingSqlSession().selectList("selectProcessInstanceByQueryCriteria", processInstanceQueryImpl, page);
	}

	/**
	 * 查询流程实例数量
	 * 
	 * @param executionQuery
	 * @return
	 */
	public long findProcessInstanceCountByQueryCriteria(Object executionQuery) {

		return (Long) getMappingSqlSession().selectOne("selectProcessInstanceCountByQueryCriteria", executionQuery);

	}

	public ProcessInstanceEntity findProcessInstanceByDefKeyAndBusinessKey(String processDefinitionKey, String businessKey) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processDefinitionKey", processDefinitionKey);
		parameters.put("businessKey", businessKey);
		return (ProcessInstanceEntity) getMappingSqlSession().selectOne("findProcessInstanceByDefKeyAndBusinessKey", parameters);
	}

	public void saveProcessInstance(ProcessInstanceEntity processInstance){
		String processLocation = "";

		List<TaskInstanceEntity> taskInstanceEntities = processInstance.getTaskMgmtInstance().getTaskInstancesNoDB();

		for (TaskInstanceEntity taskInstanceEntity : taskInstanceEntities) {
			if (!taskInstanceEntity.hasEnded()) {
				if (processLocation.equals("")) {
					processLocation = processLocation + taskInstanceEntity.getNodeId();
				} else {
					processLocation = processLocation + "," + taskInstanceEntity.getNodeId();
				}
			}
		}
		// 添加更新时间的操作
		processInstance.setUpdateTime(new Date());
		processInstance.setProcessLocation(processLocation);

		// List<TaskInstanceEntity> taskInstances
		// =processInstance.getTaskMgmtInstance().getTaskInstanceEntitys();

		// 保存流程实例和令牌

		if (processInstance.isAdd()) {
			insert(processInstance);

			for (Token token : processInstance.getTokenList()) {
				commandContext.getTokenManager().insert(token);
			}

			for (TaskInstanceEntity taskInstance : taskInstanceEntities) {
				commandContext.getTaskManager().insert(taskInstance);
			}

		} else {
			update(processInstance);

			for (Token token : processInstance.getTokenList()) {
				commandContext.getTokenManager().saveToken(token);
			}

			for (TaskInstanceEntity taskInstance : taskInstanceEntities) {
				commandContext.getTaskManager().saveTaskInstanceEntity(taskInstance);
			}
		}

		// 存储流程环境变量
		for (DataVariableEntity dataVariableEntity : processInstance.getDataVariableMgmtInstance().getDataVariableEntities()) {
			if (dataVariableEntity.isPersistence()) {
				commandContext.getVariableManager().saveVariable(dataVariableEntity);
			}
		}

	}

	public void UpdateProcessInstanceBusinessKey(ProcessInstanceEntity processInstance) throws Exception {
		/**
		 * 5.1版本修改 getDbSqlSession().save("saveProcessInstance",
		 * processInstance);
		 */
		update(processInstance);
	}

	/**
	 * 新增流程实例
	 * 
	 * @param processInstanceEntity
	 */
	public void insert(ProcessInstanceEntity processInstanceEntity) {
		getMappingSqlSession().insert("insertProcessInstance", processInstanceEntity);
	}

	/**
	 * 更新流程实例
	 * 
	 * @param processInstanceEntity
	 */
	public void update(ProcessInstanceEntity processInstanceEntity) {
		getMappingSqlSession().update("updateProcessInstance", processInstanceEntity);
	}

	/**
	 * 查询指定ID是否存在数据库
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public int selectProcessInstanceCountById(String processInstanceId) {

		return StringUtil.getInt(getMappingSqlSession().selectOne("selectProcessInstanceCountById", processInstanceId));

	}

}
