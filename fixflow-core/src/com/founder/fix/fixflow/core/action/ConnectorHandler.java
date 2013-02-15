package com.founder.fix.fixflow.core.action;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public interface ConnectorHandler {
	
	/**
	 * 连接器执行方法
	 * @param executionContext 上下文环境变量
	 * @throws Exception
	 */
	void execute(ExecutionContext executionContext) throws Exception;
	



}
