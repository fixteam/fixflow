package com.founder.fix.fixflow.core.action;



import com.founder.fix.fixflow.core.command.CommandParams;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public interface CommandHandler {
	
	
	void execute(ExecutionContext executionContext,CommandParams commandParams,TaskCommandInst taskCommand);

}
