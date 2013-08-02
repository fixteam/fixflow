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
package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.task.TaskInstance;


public class GetRollBackNodeCmd implements Command<List<UserTaskBehavior>>{
	
protected String taskId;
	
	public GetRollBackNodeCmd(String taskId)
	{
		this.taskId=taskId;
	}
	
	public List<UserTaskBehavior> execute(CommandContext commandContext) {
		
		
		TaskManager taskManager = commandContext.getTaskManager();

		TaskInstance taskInstanceQuery = taskManager.findTaskById(taskId);
		
		if(taskId==null||taskId.equals("")){
			throw new FixFlowException("taskId不能为空");
		}

		
		if(taskInstanceQuery==null){
			throw new FixFlowException("没有查询到相关任务");
		}
		
		String tokenId = taskInstanceQuery.getTokenId();
		String processDefinitionId = taskInstanceQuery.getProcessDefinitionId();
		ProcessInstanceManager processInstanceManager = commandContext.getProcessInstanceManager();

		String processInstanceId = taskInstanceQuery.getProcessInstanceId();

		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

		ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(processInstanceId, processDefinition);

		TokenEntity token=processInstanceImpl.getTokenMap().get(tokenId);
		
		
		
		List<String> tokenIdList=new ArrayList<String>();
		

		addTokenParent(token, tokenIdList);
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<TaskInstance> taskInstanceQueryToList=(List)taskManager.findTasksByTokenIdList(tokenIdList);
		
		
		
	
		List<UserTaskBehavior> userTaskBehaviorList=new ArrayList<UserTaskBehavior>();
		for (TaskInstance taskInstanceQueryTo : taskInstanceQueryToList) {
			if(!(taskInstanceQueryTo.getNodeId().equals(token.getNodeId()))&&cleanRollBackTask(userTaskBehaviorList,taskInstanceQueryTo.getNodeId())){
				UserTaskBehavior userTaskBehavior= (UserTaskBehavior)processDefinition.getDefinitions().getElement(taskInstanceQueryTo.getNodeId());
				if(userTaskBehavior!=null){
					userTaskBehaviorList.add(userTaskBehavior);
				}
				
				
			}
		}
		
		return userTaskBehaviorList;
	}
	
	
	private boolean cleanRollBackTask(List<UserTaskBehavior> userTaskBehaviorList,String nodeId){
		for (UserTaskBehavior userTaskBehavior : userTaskBehaviorList) {
			if(userTaskBehavior.getId().equals(nodeId)){
				return false;
			}
			
		}
		return true;
		
	}
	
	
	private void addTokenParent(TokenEntity token,List<String> tokenList)
	{
		
		tokenList.add(token.getId());
		if(token.getParent()!=null)
		{
			addTokenParent(token.getParent(),tokenList);
		}
	}


}
