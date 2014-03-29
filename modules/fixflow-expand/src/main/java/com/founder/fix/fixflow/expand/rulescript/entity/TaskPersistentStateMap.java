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
 * @author ych
 */
package com.founder.fix.fixflow.expand.rulescript.entity;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.scriptlanguage.BusinessRulesScript;

/**
 * 将TaskEntry转换成属性Map
 * @author ych
 *
 */
public class TaskPersistentStateMap implements BusinessRulesScript{


	public Object execute(Object parameter, SqlCommand sqlCommand,
			ProcessEngineConfigurationImpl processEngineConfiguration) {
		TaskInstanceEntity taskInstance=(TaskInstanceEntity)parameter;
		Map<String,Object> persistentState = new HashMap<String, Object>();
		persistentState.put("taskInstanceId", taskInstance.getId());		
		persistentState.put("name", taskInstance.getName());		
		persistentState.put("description", taskInstance.getDescription());
		persistentState.put("processInstanceId", taskInstance.getProcessInstanceId());
		persistentState.put("processDefinitionId", taskInstance.getProcessDefinitionId());
		persistentState.put("processDefinitionKey", taskInstance.getProcessDefinitionKey());		
		persistentState.put("processDefinitionName", taskInstance.getProcessDefinitionName());	
		persistentState.put("version", taskInstance.getVersion());
		persistentState.put("tokenId", taskInstance.getTokenId());
		persistentState.put("nodeId", taskInstance.getNodeId());
		persistentState.put("nodeName", taskInstance.getNodeName());		
		persistentState.put("parentTaskInstanceId", taskInstance.getParentTaskInstanceId());		
		persistentState.put("assignee", taskInstance.getAssignee());
		persistentState.put("claimTime", taskInstance.getClaimTime());		
		persistentState.put("createTime", taskInstance.getCreateTime());
		persistentState.put("startTime", taskInstance.getStartTime());		
		persistentState.put("endTime", taskInstance.getEndTime());		
		persistentState.put("dueDate", taskInstance.getDueDate());		
		persistentState.put("priority", String.valueOf(taskInstance.getPriority()));		
		persistentState.put("category", String.valueOf(taskInstance.getCategory()));		
		persistentState.put("owner", taskInstance.getOwner());		
		persistentState.put("delegationState", StringUtil.getString(taskInstance.getDelegationState()));		
		persistentState.put("bizKey", taskInstance.getBizKey());		
		persistentState.put("taskComment", taskInstance.getTaskComment());		
		persistentState.put("formUri", taskInstance.getFormUri());
		persistentState.put("formUriView", taskInstance.getFormUriView());		
		persistentState.put("taskGroup", taskInstance.getTaskGroup());		
		persistentState.put("taskInstanceType", StringUtil.getString(taskInstance.getTaskInstanceType()));		
		persistentState.put("isBlocking", String.valueOf(taskInstance.isBlocking()));
		persistentState.put("isCancelled", String.valueOf(taskInstance.isCancelled()));		
		persistentState.put("isSuspended", String.valueOf(taskInstance.isSuspended()));		
		persistentState.put("isOpen", String.valueOf(taskInstance.isOpen()));
		persistentState.put("isDraft", String.valueOf(taskInstance.isDraft()));
		persistentState.put("expectedExecutionTime", String.valueOf(taskInstance.getExpectedExecutionTime()));
		persistentState.put("agent", taskInstance.getAgent());		
		persistentState.put("admin", taskInstance.getAdmin());		
		persistentState.put("callActivityInstanceId", taskInstance.getCallActivityInstanceId());		
		persistentState.put("pendingTaskId", taskInstance.getPendingTaskId());		
		persistentState.put("archiveTime", taskInstance.getArchiveTime());		
		persistentState.put("commandId", taskInstance.getCommandId());
		persistentState.put("commandType", taskInstance.getCommandType());		
		persistentState.put("commandMessage", taskInstance.getCommandMessage());
		
		Map<String,Object> extensionFields=taskInstance.getExtensionFields();	
			
		for (String key : extensionFields.keySet()) {
			persistentState.put(key, extensionFields.get(key));
		}
		return persistentState;
	}
	
}
