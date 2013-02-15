package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class GetUserEndTaskNodesInProcessInstanceCmd implements Command<List<UserTaskBehavior>>{

	protected String processInstanceId;
	
	public GetUserEndTaskNodesInProcessInstanceCmd(String processInstanceId){
		this.processInstanceId=processInstanceId;
	}
	
	public List<UserTaskBehavior> execute(CommandContext commandContext) {
		// TODO 自动生成的方法存根
		
		
		
		return null;
	}
	
	

}
