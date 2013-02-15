package com.founder.fix.fixflow.core.action;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;



public interface ActionHandler {
	
	/**
	 * 动作执行方法
	 * @param executionContext
	 * @throws Exception
	 */
	void execute(ExecutionContext executionContext);

}
