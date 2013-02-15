package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class DeleteProcessInstanceByInstanceIdCmd implements Command<Boolean> {

	
	
	protected String processInstanceId;
	protected boolean cascade;
	
	
	
	
	public DeleteProcessInstanceByInstanceIdCmd(String processInstanceId, boolean cascade){
		
		this.processInstanceId=processInstanceId;
		this.cascade=cascade;
		
	}
	
	
	public Boolean execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		
		commandContext.getProcessInstanceManager().deleteProcessInstance(processInstanceId, cascade);
		
		return true;
	}
	
	
	

}
