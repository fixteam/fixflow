package com.founder.fix.fixflow.core.impl.util;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class CoreUtil {
	
	public static List<TaskCommandInst> getTaskCommandInst(TaskInstance taskInstance){
		
		ProcessDefinitionManager processDefinitionManager=Context.getCommandContext().getProcessDefinitionManager();
		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(taskInstance.getProcessDefinitionId());

		return getTaskCommandInst(taskInstance,processDefinition);
		
		
	}
	
	public static List<TaskCommandInst> getTaskCommandInst(TaskInstance taskInstance,ProcessDefinitionBehavior processDefinition){
		
		
		UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(taskInstance.getNodeId());
		
		List<TaskCommandInst> taskCommandInsts= userTask.getTaskCommands();
		List<TaskCommandInst> taskCommandInstsNew=new ArrayList<TaskCommandInst>();
		for (TaskCommandInst taskCommandInst : taskCommandInsts) {
			AbstractCommandFilter abstractCommandFilter=Context.getProcessEngineConfiguration().getAbstractCommandFilterMap().get(taskCommandInst.getTaskCommandType());
			if(abstractCommandFilter!=null){
				if(abstractCommandFilter.accept(taskInstance)){
					taskCommandInstsNew.add(taskCommandInst);
				}
			}
			else{
				taskCommandInstsNew.add(taskCommandInst);
			}
		}
		
		
		
		return taskCommandInstsNew;
	}
	
	
	
	public static List<TaskCommandInst> getSubmitNodeTaskCommandInst(UserTaskBehavior userTask){
		
		
			
		List<TaskCommandInst> taskCommandInsts= userTask.getTaskCommands();
		List<TaskCommandInst> taskCommandInstsNew=new ArrayList<TaskCommandInst>();
		for (TaskCommandInst taskCommandInst : taskCommandInsts) {
			AbstractCommandFilter abstractCommandFilter=Context.getProcessEngineConfiguration().getAbstractCommandFilterMap().get(taskCommandInst.getTaskCommandType());
			if(abstractCommandFilter!=null){
				if(abstractCommandFilter.accept(null)){
					taskCommandInstsNew.add(taskCommandInst);
				}
			}
			else{
				taskCommandInstsNew.add(taskCommandInst);
			}
		}
		
		
		
		return taskCommandInstsNew;
	}

}
