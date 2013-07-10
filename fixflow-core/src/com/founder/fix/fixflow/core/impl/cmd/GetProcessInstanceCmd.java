package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;

public class GetProcessInstanceCmd implements Command<ProcessInstance> {

	protected String processInstanceId;
	
	public GetProcessInstanceCmd(String processInstanceId){
		this.processInstanceId=processInstanceId;
	}
	
	
	public ProcessInstance execute(CommandContext commandContext) {
		

		ProcessInstance processInstance=commandContext.getProcessInstanceManager().findProcessInstanceById(processInstanceId);
		
		
		return processInstance;
	}

}
