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
package com.founder.fix.fixflow.expand.cmd;


import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.expand.command.SubmitTaskCommand;

public class SubmitTaskCmd extends AbstractExpandTaskCmd<SubmitTaskCommand, Void> {

	public SubmitTaskCmd(SubmitTaskCommand submitTaskCommand) {
		super(submitTaskCommand);

	}

	public Void execute(CommandContext commandContext) {

		// 初始化任务命令执行所需要的常用对象
		loadProcessParameter(commandContext);

		// 将外部变量注册到流程实例运行环境中
		addVariable();

		// 执行处理命令中的开发人员设置的表达式
		runCommandExpression();

		// 获取正在操作的任务实例对象
		TaskInstanceEntity taskInstance = getTaskInstanceEntity();

		// 获取正在操作的任务命令对象实例
		TaskCommandInst taskCommand = getTaskCommandInst();
		
		ProcessInstanceEntity processInstanceImpl=getProcessInstance();
		
		ProcessDefinitionBehavior processDefinition=processInstanceImpl.getProcessDefinition();


		if (businessKey != null) {
			processInstanceImpl.setBizKeyWithoutCascade(this.businessKey);
		}

		if (initiator != null) {
			processInstanceImpl.setInitiatorWithoutCascade(this.initiator);
		}

		if (processDefinition.getTaskSubject() != null && processDefinition.getTaskSubject().getExpressionValue() != null) {

			Object result = ExpressionMgmt.execute(processDefinition.getTaskSubject().getExpressionValue(), getExecutionContext());

			if (result != null) {
				processInstanceImpl.setSubject(result.toString());
			}
		} else {
			processInstanceImpl.setSubject(processDefinition.getName());
		}

		
		TaskInstanceEntity taskInstanceImpl = getTaskInstanceEntity();

		


		if (taskInstanceImpl != null) {
			
			//结束任务，并推动token运行
			taskInstance.end(taskCommand, this.taskComment);

		} else {
			throw new FixFlowException("没有找到id为: " + taskId + " 的任务");
		}

		try {

			saveProcessInstance(commandContext);
			
		} catch (Exception e) {
			throw new FixFlowException("流程实例持久化失败!", e);
		}
		return null;
	}

}
