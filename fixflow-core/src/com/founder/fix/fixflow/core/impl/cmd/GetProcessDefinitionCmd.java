package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class GetProcessDefinitionCmd implements Command<ProcessDefinitionBehavior> {

	protected String processDefinitionId;
	
	public GetProcessDefinitionCmd(String processDefinitionId){
		this.processDefinitionId=processDefinitionId;
	}
	
	
	public ProcessDefinitionBehavior execute(CommandContext commandContext) {
		ProcessDefinitionBehavior processDefinitionBehavior= commandContext.getProcessDefinitionManager().findLatestProcessDefinitionById(processDefinitionId);
		return processDefinitionBehavior;
	}

}
