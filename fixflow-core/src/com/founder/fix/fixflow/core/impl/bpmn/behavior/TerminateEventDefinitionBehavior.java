package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.impl.TerminateEventDefinitionImpl;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class TerminateEventDefinitionBehavior extends TerminateEventDefinitionImpl {
	

	public boolean execute(ExecutionContext executionContext,Event event) {

		
		//结束整个流程实例
		executionContext.getToken().getProcessInstance().getRootToken().end();
		
		return false;
	}

}
