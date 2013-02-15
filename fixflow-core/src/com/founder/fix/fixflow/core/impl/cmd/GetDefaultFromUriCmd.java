package com.founder.fix.fixflow.core.impl.cmd;



import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class GetDefaultFromUriCmd implements Command<String>{

	protected String processDefinitionId;
	
	public GetDefaultFromUriCmd(String processDefinitionId){
		this.processDefinitionId=processDefinitionId;
	}
	
	public String execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		
		ProcessDefinitionBehavior processDefinitionBehavior=commandContext.getProcessDefinitionManager().findLatestProcessDefinitionById(processDefinitionId);
		String defaultFormUri=processDefinitionBehavior.getDefaultFormUri();
		
		
		return defaultFormUri;
	}

}
