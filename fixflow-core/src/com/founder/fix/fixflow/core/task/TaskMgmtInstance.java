/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.task;

import java.util.List;

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

	List<TaskInstanceEntity> getTaskInstanceEntitys(Token token);
	
	TaskInstanceEntity getTaskInstanceEntitys(String taskId);
	
	List<TaskInstanceEntity> getTaskInstanceEntitys();
}
