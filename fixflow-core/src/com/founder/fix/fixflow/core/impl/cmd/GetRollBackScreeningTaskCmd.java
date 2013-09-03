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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.FlowNode;


import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskQueryImpl;
import com.founder.fix.fixflow.core.impl.util.CoreUtil;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;

public class GetRollBackScreeningTaskCmd implements Command<List<TaskInstance>>{

	
	protected String taskId;
	
	public GetRollBackScreeningTaskCmd(String taskId)
	{
		this.taskId=taskId;
	}
	
	public List<TaskInstance> execute(CommandContext commandContext) {
		
		
		TaskManager taskManager = commandContext.getTaskManager();
		if(taskId==null||taskId.equals("")){
			throw new FixFlowBizException("taskId 不能为空");
		}
		TaskInstance taskInstanceQuery = taskManager.findTaskById(taskId);
		
		
		
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
		
		//获取这个节点关系上之前的所有人工任务(UserTask)
		Map<String,FlowNode> flowNodes=CoreUtil.getBeforeFlowNode(token.getFlowNode());
		
		//获取这个令牌自己和爸爸相关的所有任务

		
		TaskQuery taskQuery=new TaskQueryImpl(Context.getCommandContext());


		List<TaskInstance> taskInstanceQueryToList = new ArrayList<TaskInstance>();


		taskQuery.processInstanceId(processInstanceId);
		taskQuery.taskIsEnd().orderByEndTime().asc().orderByTaskCreateTime().asc();
		taskInstanceQueryToList = taskQuery.list();
		
		
		
		List<String> processInstanceIdList=new ArrayList<String>();
		processInstanceIdList.add(token.getProcessInstanceId());
		taskManager.getTaskStatusByByProcessInstanceIdList(processInstanceIdList);
		
		//List<TaskInstance> taskInstanceQueryToList=(List)taskManager.findTasksByTokenIdList(tokenIdList);
		List<TaskInstance> taskInstanceQueryToListNew=new ArrayList<TaskInstance>();
		//Map<String, TaskInstance> taskMap=new HashMap<String, TaskInstance>();
		
		//TaskInstance previousTaskInstance=null;
		
		Map<String, String> pcNodeMap= new HashMap<String, String>();
		String tempString=taskInstanceQuery.getNodeId()+Authentication.getAuthenticatedUserId();
		
		
		for (int i = 0; i < taskInstanceQueryToList.size(); i++) {
			TaskInstance taskInstance=taskInstanceQueryToList.get(i);
			
			if(flowNodes.get(taskInstance.getNodeId())!=null&&!taskInstance.getId().equals(this.taskId)){
				
				
				
				if(!tempString.equals(taskInstance.getNodeId()+taskInstance.getAssignee())){
					//taskInstanceQueryToListNew.add(taskInstance);
					if(pcNodeMap.get(taskInstance.getNodeId()+taskInstance.getAssignee())==null){
						taskInstanceQueryToListNew.add(taskInstance);
						pcNodeMap.put(taskInstance.getNodeId()+taskInstance.getAssignee(), "");
					}
					//taskMap.put(taskInstance.getNodeId(), taskInstance);
				}
				
				
				
			}
			//previousTaskInstance=taskInstance;
		}
		
		
		
		
		
		/*
		
		List<TaskInstance> taskInstanceQueryToListFin=new ArrayList<TaskInstance>();
		
		
		for (int i = 0; i < taskInstanceQueryToListNew.size(); i++) {
			TaskInstance taskInstance=taskInstanceQueryToListNew.get(i);
			
			
			if(pcNodeMap.get(taskInstance.getNodeId())==null){
				if(i==0){
					if(taskInstanceQueryToListNew.size()>1){
						if(taskInstance.getNodeId().equals(taskInstanceQueryToListNew.get(i+1))){
							
						}
						else{
							pcNodeMap.put(taskInstance.getNodeId(), "");
						}
						taskInstanceQueryToListFin.add(taskInstance);
					}
					else{
						taskInstanceQueryToListFin.add(taskInstance);
					}
					
					
					continue;
				}
				
				if(i==taskInstanceQueryToListNew.size()-1){
					//if(taskInstance.getNodeId().equals(taskInstanceQueryToListNew.get(i-1))){
						taskInstanceQueryToListFin.add(taskInstance);
						pcNodeMap.put(taskInstance.getNodeId(), "");
					//}
					//else{
						
						//taskInstanceQueryToListFin.add(taskInstance);
						//pcNodeMap.put(taskInstance.getNodeId(), "");
						
					//}
					continue;
				}
				
			
				if(taskInstance.getNodeId().equals(taskInstanceQueryToListNew.get(i+1))){
					taskInstanceQueryToListFin.add(taskInstance);
				}else {
					//if(taskInstance.getNodeId().equals(taskInstanceQueryToListNew.get(i-1))){
						taskInstanceQueryToListFin.add(taskInstance);
						pcNodeMap.put(taskInstance.getNodeId(), "");
					//}
					//else{
						
						//taskInstanceQueryToListFin.add(taskInstance);
						//pcNodeMap.put(taskInstance.getNodeId(), "");
						
					//}
				}
			}

		}*/
		
		
		/*
		
		for (int i = 0; i < taskInstanceQueryToList.size(); i++) {
			TaskInstance taskInstance=taskInstanceQueryToList.get(i);
			
			if(taskMap.get(taskInstance.getNodeId())==null&&flowNodes.get(taskInstance.getNodeId())!=null){
				taskMap.put(taskInstance.getNodeId(), taskInstance);
				taskInstanceQueryToListNew.add(taskInstance);
				
			}
			else{
				if(flowNodes.get(taskInstance.getNodeId())!=null){
					if(previousTaskInstance!=null){
						if(taskInstance.getNodeId().equals(previousTaskInstance.getNodeId())){
							if(pcNodeMap.get(taskInstance.getNodeId())==null){
								pcNodeMap.put(taskInstance.getNodeId(), flowNodes.get(taskInstance.getNodeId()));
								taskMap.put(taskInstance.getNodeId(), taskInstance);
								taskInstanceQueryToListNew.add(taskInstance);
							}
							
							//previousTaskInstance=taskInstance;
						}
						else{
							
						}
					
						//else{
						//	previousTaskInstance=taskInstance;
						//}
					}
				}
			}
			
			previousTaskInstance=taskInstance;
		}
		*/
		
		
		/*
		//剔除掉重复的和之后的任务。
		for (TaskInstance taskInstance : taskInstanceQueryToList) {
			
			
			
			
			if(taskMap.get(taskInstance.getNodeId())==null&&flowNodes.get(taskInstance.getNodeId())!=null){
				taskMap.put(taskInstance.getNodeId(), taskInstance);
				taskInstanceQueryToListNew.add(taskInstance);
				
			}
			else{
				if(flowNodes.get(taskInstance.getNodeId())!=null){
					if(previousTaskInstance!=null){
						if(taskInstance.getNodeId().equals(previousTaskInstance.getNodeId())){
							if(pcNodeMap.get(taskInstance.getNodeId())==null){
								pcNodeMap.put(taskInstance.getNodeId(), flowNodes.get(taskInstance.getNodeId()));
								taskMap.put(taskInstance.getNodeId(), taskInstance);
								taskInstanceQueryToListNew.add(taskInstance);
							}
							
							//previousTaskInstance=taskInstance;
						}
						else{
							
						}
					
						//else{
						//	previousTaskInstance=taskInstance;
						//}
					}
				}
			}
			
			previousTaskInstance=taskInstance;
		
		}
		
		
		
		List<TaskInstance> taskInstanceQueryToListFin=new ArrayList<TaskInstance>();
		
		Map<String,TaskInstance> flowNodesNew=new HashMap<String, TaskInstance>();
		
		
		
		
		for (TaskInstance taskInstanceNewObj : taskInstanceQueryToListNew) {
			
			if(taskInstanceNewObj.getId().equals(this.taskId)){
				
			}
			else{
				//if(flowNodesNew.get(taskInstanceNewObj.getNodeId())==null){
					//flowNodesNew.put(taskInstanceNewObj.getNodeId(), taskInstanceNewObj);
					taskInstanceQueryToListFin.add(taskInstanceNewObj);
				//}

				
			}
			
		}
		
		
		
		/*
		List<TaskInstanceQueryTo> taskInstanceQueryToListTemp=new ArrayList<TaskInstanceQueryTo>();
		for (TaskInstanceQueryTo taskInstanceQueryTo : taskInstanceQueryToList) {
			if(!taskInstanceQueryTo.getNodeId().equals(token.getNodeId())){
				taskInstanceQueryToListTemp.add(taskInstanceQueryTo);
			}
		}*/


		
		return taskInstanceQueryToListNew;
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
