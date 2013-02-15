package com.founder.fix.fixflow.core.action;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;


public interface DecisionHandler {
	
	/**
	 * 线条判定方法,返回 true 则允许通过线条,返回 false 则不允许通过线条.
	 * @param executionContext 上下文执行内容对象
	 * @return 是否允许通过
	 * @throws Exception
	 */
	boolean decide(ExecutionContext executionContext);

}
