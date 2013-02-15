package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.UserTask;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;

public class GetUserCommandCmd<T> implements Command<List<T>> {

	
	String processDefinitionId;
	
	String nodeId;
	
	
	public GetUserCommandCmd(String processDefinitionId,String nodeId)
	{
		this.processDefinitionId=processDefinitionId;
		this.nodeId=nodeId;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> execute(CommandContext commandContext) {

		
		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

		List<T> userCommandQueryList = new ArrayList<T>();
		Object flowNodeObject = processDefinition.getDefinitions().getElement(nodeId);
		if (flowNodeObject != null && flowNodeObject instanceof UserTask) {
			userCommandQueryList = (List<T>)((UserTaskBehavior) flowNodeObject).getTaskCommands();
		}

		return userCommandQueryList;
	}

}
