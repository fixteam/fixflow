package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.impl.MessageEventDefinitionImpl;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class MessageEventDefinitionBehavior extends MessageEventDefinitionImpl {

	@Override
	public void catchExecute(ExecutionContext executionContext, CatchEvent event) {
		// TODO Auto-generated method stub
		super.catchExecute(executionContext, event);
	}

	@Override
	public void throwExecute(ExecutionContext executionContext, ThrowEvent event) {
		// TODO Auto-generated method stub
		super.throwExecute(executionContext, event);
	}
	
	
	

}
