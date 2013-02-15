package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import org.eclipse.bpmn2.impl.ScriptTaskImpl;

import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class ScriptTaskBehavior extends ScriptTaskImpl {
	
	
	public void execute(ExecutionContext executionContext) {

		ExpressionMgmt.execute(this.getScript(), executionContext);
		super.execute(executionContext);
	}

}
