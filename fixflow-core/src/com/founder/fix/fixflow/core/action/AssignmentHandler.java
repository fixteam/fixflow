package com.founder.fix.fixflow.core.action;



import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.Assignable;


public interface AssignmentHandler {
	
	
	/**
	 * 分配任务
	 * @param assignable 任务实例对象
	 * @param executionContext 流程上下文
	 */
	void assign( Assignable assignable, ExecutionContext executionContext );

}
