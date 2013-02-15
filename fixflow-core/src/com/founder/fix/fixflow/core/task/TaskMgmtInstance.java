package com.founder.fix.fixflow.core.task;

import java.util.Set;

import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.Token;

public interface TaskMgmtInstance {

	TaskInstanceEntity createTaskInstanceEntity(TaskDefinition taskDefinition, ExecutionContext executionContext);
	
	public TaskInstanceEntity createTaskInstanceEntity(TaskDefinition taskDefinition, ExecutionContext executionContext,String taskGroup);

	void setProcessInstance(ProcessInstance processInstance);

	void performAssignment(TaskDefinition taskDefinition, Assignable assignable, ExecutionContext executionContext);

	/**
	 * 暂停这个令牌下的所有任务实例
	 */
	void suspend(Token token);

	/**
	 * 恢复这个令牌下的所有任务实例
	 */
	void resume(Token token);

	Set<TaskInstanceEntity> getTaskInstanceEntitys(Token token);

	Set<TaskInstanceEntity> getTaskInstanceEntitys();
}
