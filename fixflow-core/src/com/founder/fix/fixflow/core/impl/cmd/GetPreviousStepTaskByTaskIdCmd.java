package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.task.TaskInstance;


public class GetPreviousStepTaskByTaskIdCmd implements Command<List<TaskInstance>> {

	protected String taskId;
	
	public GetPreviousStepTaskByTaskIdCmd(String taskId){
		this.taskId=taskId;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TaskInstance> execute(CommandContext commandContext) {
		TaskManager taskManager = commandContext.getTaskManager();

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
		
		List<TaskInstance> taskInstanceQueryToList=(List)taskManager.findTasksByTokenIdList(tokenIdList);
		
		List<TaskInstance> taskInstanceQueryToTemp=new ArrayList<TaskInstance>();
		
		for (TaskInstance taskInstanceQueryTo : taskInstanceQueryToList) {
			if(!taskInstanceQueryTo.getId().equals(this.taskId)){
				if(taskInstanceQueryTo.getTaskGroup()!=null){
					
					taskInstanceQueryToTemp.add(taskInstanceQueryTo);
				}
				else {
					if(taskInstanceQueryToTemp.size()==0){
						taskInstanceQueryToTemp.add(taskInstanceQueryTo);
						return taskInstanceQueryToTemp;
					}
					else{
						return taskInstanceQueryToTemp;
					}
					
				}
			}
		}
		
		return taskInstanceQueryToList;
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
