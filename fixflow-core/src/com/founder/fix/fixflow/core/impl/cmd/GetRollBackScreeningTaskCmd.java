package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.FlowNode;


import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.util.CoreUtil;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class GetRollBackScreeningTaskCmd implements Command<List<TaskInstance>>{

	
	protected String taskId;
	
	public GetRollBackScreeningTaskCmd(String taskId)
	{
		this.taskId=taskId;
	}
	
	public List<TaskInstance> execute(CommandContext commandContext) {
		
		
		TaskManager taskManager = commandContext.getTaskManager();

		TaskInstance taskInstanceQuery = taskManager.findTaskById(taskId);
		
		if(taskId==null||taskId.equals("")){
			throw new FixFlowBizException("taskId 不能为空");
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
		
		//获取这个节点关系上之前的所有人工任务(UserTask)
		Map<String,FlowNode> flowNodes=CoreUtil.getBeforeFlowNode(token.getFlowNode());
		
		//获取这个令牌自己和爸爸相关的所有任务
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<TaskInstance> taskInstanceQueryToList=(List)taskManager.findTasksByTokenIdList(tokenIdList);
		List<TaskInstance> taskInstanceQueryToListNew=new ArrayList<TaskInstance>();
		Map<String, TaskInstance> taskMap=new HashMap<String, TaskInstance>();
		
		TaskInstance previousTaskInstance=null;
		
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
							taskMap.put(taskInstance.getNodeId(), taskInstance);
							taskInstanceQueryToListNew.add(taskInstance);
							//previousTaskInstance=taskInstance;
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
				if(flowNodesNew.get(taskInstanceNewObj.getNodeId())==null){
					flowNodesNew.put(taskInstanceNewObj.getNodeId(), taskInstanceNewObj);
					taskInstanceQueryToListFin.add(taskInstanceNewObj);
				}

				
			}
			
		}
		
		
		
		/*
		List<TaskInstanceQueryTo> taskInstanceQueryToListTemp=new ArrayList<TaskInstanceQueryTo>();
		for (TaskInstanceQueryTo taskInstanceQueryTo : taskInstanceQueryToList) {
			if(!taskInstanceQueryTo.getNodeId().equals(token.getNodeId())){
				taskInstanceQueryToListTemp.add(taskInstanceQueryTo);
			}
		}*/


		
		return taskInstanceQueryToListFin;
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
