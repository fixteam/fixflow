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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableEntity;
import com.founder.fix.fixflow.core.impl.persistence.instance.IdentityLinkPersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.TaskInstancePersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.VariablePersistence;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceQueryImpl;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.IdentityLinkEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.objkey.TokenObjKey;

public class ProcessInstanceManager extends AbstractManager {

	@SuppressWarnings("unchecked")
	public void deleteProcessInstancesByProcessDefinition(String processDefinitionId, String deleteReason, boolean cascade) {
		List<String> processInstanceIds = getDbSqlSession().selectList("selectProcessInstanceIdsByProcessDefinitionId", processDefinitionId);
		for(String processInstanceId :processInstanceIds){
			deleteProcessInstance(processInstanceId,cascade);
		}
	}

	public void deleteProcessInstance(String processInstanceId,  boolean cascade) {
		
		if(cascade){
			getDbSqlSession().delete("deleteProcessInstanceByProcessInstanceId", processInstanceId);
			
			getDbSqlSession().delete("deleteIdentityLinkByProcessInstanceId", processInstanceId);
			getDbSqlSession().delete("deleteTaskInstanceByProcessInstanceId", processInstanceId);
			getDbSqlSession().delete("deleteVariableByProcessInstanceId", processInstanceId);
			getDbSqlSession().delete("deleteTokenByProcessInstanceId", processInstanceId);
			
			
		}
		else{
			getDbSqlSession().delete("deleteProcessInstanceByProcessInstanceId", processInstanceId);
		}
		

	}


	public ProcessInstanceEntity findSubProcessInstanceBySuperExecutionId(String superExecutionId) {

		return null;
	}
	
	public ProcessInstanceEntity findProcessInstanceById(String processInstanceId) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processInstanceId", processInstanceId);
		parameters.put("processDefinition", null);
		return (ProcessInstanceEntity) getDbSqlSession().selectOne("selectProcessInstance", parameters);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProcessInstanceEntity> findSubProcessInstanceById(String processInstanceId) {

		return (List) getDbSqlSession().selectOne("findSubProcessInstanceById", processInstanceId);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProcessInstanceEntity> findSubProcessInstanceByIdAndToken(String processInstanceId,String tokenId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processInstanceId", processInstanceId);
		parameters.put("tokenId", tokenId);
		return (List) getDbSqlSession().selectOne("findSubProcessInstanceByIdAndToken",parameters);

	}

	public ProcessInstanceEntity findProcessInstanceById(String processInstanceId, ProcessDefinitionBehavior processDefinition) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processInstanceId", processInstanceId);
		parameters.put("processDefinition", processDefinition);
		return (ProcessInstanceEntity) getDbSqlSession().selectOne("selectProcessInstance", parameters);

	}

	public long findProcessInstanceCountByQueryCriteria(Object executionQuery) {

		return (Long) getDbSqlSession().selectOne("selectProcessInstanceCountByQueryCriteria", executionQuery);

	}

	@SuppressWarnings("unchecked")
	public List<ProcessInstanceEntity> findProcessInstanceByQueryCriteria(ProcessInstanceQueryImpl processInstanceQueryImpl, Page page) {
		return getDbSqlSession().selectList("selectProcessInstanceByQueryCriteria", processInstanceQueryImpl, page);
	}
	
	public ProcessInstanceEntity findProcessInstanceByDefKeyAndBusinessKey(String processDefinitionKey,String businessKey) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processDefinitionKey", processDefinitionKey);
		parameters.put("businessKey", businessKey);
		return (ProcessInstanceEntity)getDbSqlSession().selectOne("selectProcessInstanceByDefKeyAndBusinessKey",parameters );
	}

	public void saveProcessInstance(ProcessInstanceEntity processInstance) throws Exception {
		String processLocation="";
		List<TaskInstanceEntity> taskInstanceEntities=processInstance.getTaskMgmtInstance().getTaskInstanceEntitys();
		for (TaskInstanceEntity taskInstanceEntity : taskInstanceEntities) {
			if(!taskInstanceEntity.hasEnded()){
				if(processLocation.equals("")){
					processLocation=processLocation+taskInstanceEntity.getNodeId();
				}
				else{
					processLocation=processLocation+","+taskInstanceEntity.getNodeId();
				}
			}
		}
		//添加更新时间的操作
		processInstance.setUpdateTime(new Date());
		processInstance.setProcessLocation(processLocation);
		
		/* 5.1版本修改
		getDbSqlSession().save("saveProcessInstance", processInstance);
		*/
		
		
		//保存流程实例和令牌
		int count = selectProcessInstanceCountById(processInstance.getId());
		if(count == 0){
			insertProcessInstance(processInstance);
		}else{
			updateProcessInstance(processInstance);
		}
		//保存根令牌
		commandContext.getTokenManager().saveRootToken(processInstance.getRootToken());
		//保存FreeToken
		saveFreeToken(processInstance);
		// 存储任务实例
		saveTaskInstance(processInstance);
		// 存储流程环境变量
		saveVariableInstance(processInstance);
		
	}
	
	public void UpdateProcessInstanceBusinessKey(ProcessInstanceEntity processInstance) throws Exception {
		/** 5.1版本修改
		getDbSqlSession().save("saveProcessInstance", processInstance);
		*/
		updateProcessInstance(processInstance);
	}
	
	/**新增**/
	
	/**
	 * 新增流程实例
	 * @param processInstanceEntity
	 */
	public void insertProcessInstance(ProcessInstanceEntity processInstanceEntity){
		getMappingSqlSession().insert("insertProcessInstance", processInstanceEntity);
	}
	
	/**
	 * 更新流程实例
	 * @param processInstanceEntity
	 */
	public void updateProcessInstance(ProcessInstanceEntity processInstanceEntity){
		getMappingSqlSession().update("updateProcessInstance", processInstanceEntity);
	}
	
	/**
	 * 查询指定ID是否存在数据库
	 * @param processInstanceId
	 * @return
	 */
	public int selectProcessInstanceCountById(String processInstanceId){
		
		return 0;
	}
	
	/**
	 * 保存流程中需要保存的任务实例
	 * @param processInstanceEntity
	 */
	public void saveTaskInstance(ProcessInstanceEntity processInstanceEntity){
		if (processInstanceEntity.getTaskMgmtInstance().getTaskInstanceEntitys() != null) {
			for (TaskInstanceEntity taskInstance : processInstanceEntity.getTaskMgmtInstance().getTaskInstanceEntitys()) {
				commandContext.getTaskManager().saveTaskInstanceEntity(taskInstance);
				for (IdentityLinkEntity identityLink : taskInstance.getTaskIdentityLinkEntitys()) {
					commandContext.getIdentityLinkManager().saveIdentityLink(identityLink);
				}
			}
		}
	}
	
	/**
	 * 保存FreeToken
	 * @param processInstanceEntity
	 */
	public void saveFreeToken(ProcessInstanceEntity processInstanceEntity){
		for (TokenEntity token : processInstanceEntity.getTokenList()) {
			if(token.isFreeToken()){
				commandContext.getTokenManager().saveToken(token);
			}
		}
	}
	
	/**
	 * 持久化流程变量
	 * @param processInstanceEntity
	 */
	public void saveVariableInstance(ProcessInstanceEntity processInstanceEntity){
		for (DataVariableEntity dataVariableEntity : processInstanceEntity.getDataVariableMgmtInstance().getDataVariableEntities()) {
			if (dataVariableEntity.isPersistence()) {
				commandContext.getVariableManager().saveVariable(dataVariableEntity);
			}
		}
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getProcessPerformance(String startTime,String endTime,String processKey,Page page) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		parameters.put("processKey", processKey);
		return getDbSqlSession().selectList("selectProcessPerformance", parameters, page);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getProcessPerformance(String startTime,String endTime,Page page) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		return getDbSqlSession().selectList("selectProcessPerformanceInterface2", parameters, page);
	}
	
	public int getProcessPerformance2(String startTime,String endTime) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		return StringUtil.getInt(getDbSqlSession().selectOne("selectProcessPerformanceInterface22", parameters));
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getProcessPerformance(String startTime,String endTime) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		return getDbSqlSession().selectList("selectProcessPerformanceInterface1", parameters);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getProcessPerformance(String[] processKey,String startTime,String endTime) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processKey", processKey);
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		return getDbSqlSession().selectList("selectProcessPerformanceInterface4", parameters);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getProcessPerformance(String startTime,String endTime, String pid) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		parameters.put("pid", pid);
		return getDbSqlSession().selectList("selectProcessPerformanceInterface5", parameters);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getProcessPerformanceTask(String[] pid, String startTime,String endTime) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("pid", pid);
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		return getDbSqlSession().selectList("selectProcessPerformanceInterface3", parameters);
	}

}
