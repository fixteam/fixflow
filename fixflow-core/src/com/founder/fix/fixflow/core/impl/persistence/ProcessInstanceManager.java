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

import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableEntity;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceQueryImpl;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.runtime.Token;

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

		//List<TaskInstanceEntity> taskInstances =processInstance.getTaskMgmtInstance().getTaskInstanceEntitys();
		
		//保存流程实例和令牌
		int count = selectProcessInstanceCountById(processInstance.getId());
		if(count == 0){
			insert(processInstance);

		}else{
			update(processInstance);
			
		}
		
		for (Token token : processInstance.getTokenList()) {
			commandContext.getTokenManager().saveToken(token);
		}
		
		for (TaskInstanceEntity taskInstance : taskInstanceEntities) {
			commandContext.getTaskManager().saveTaskInstanceEntity(taskInstance);
		}

		
		
		// 存储流程环境变量
		for (DataVariableEntity dataVariableEntity : processInstance.getDataVariableMgmtInstance().getDataVariableEntities()) {
			if (dataVariableEntity.isPersistence()) {
				commandContext.getVariableManager().saveVariable(dataVariableEntity);
			}
		}
		
	}
	
	public void UpdateProcessInstanceBusinessKey(ProcessInstanceEntity processInstance) throws Exception {
		/** 5.1版本修改
		getDbSqlSession().save("saveProcessInstance", processInstance);
		*/
		update(processInstance);
	}
	
	
	/**
	 * 新增流程实例
	 * @param processInstanceEntity
	 */
	public void insert(ProcessInstanceEntity processInstanceEntity){
		getMappingSqlSession().insert("insertProcessInstance", processInstanceEntity);
	}
	
	/**
	 * 更新流程实例
	 * @param processInstanceEntity
	 */
	public void update(ProcessInstanceEntity processInstanceEntity){
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
	

	

}
