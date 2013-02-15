package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.UserTask;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.util.CoreUtil;

public class GetSubTaskUserCommandCmd<T> implements Command<List<T>> {

	String processDefinitionKey;

	public GetSubTaskUserCommandCmd(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}


	@SuppressWarnings("unchecked")
	public List<T> execute(CommandContext commandContext) {
		
		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionByKey(processDefinitionKey);

		List<T> userCommandQueryList = new ArrayList<T>();
		Object flowNodeObject = processDefinition.getSubTask();
		if (flowNodeObject != null || flowNodeObject instanceof UserTask) {

			userCommandQueryList =(List<T>)CoreUtil.getSubmitNodeTaskCommandInst((UserTaskBehavior) flowNodeObject);
		}

		return userCommandQueryList;
	}

}
