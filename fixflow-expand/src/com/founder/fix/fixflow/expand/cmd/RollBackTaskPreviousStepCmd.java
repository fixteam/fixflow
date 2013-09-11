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

import java.util.List;


import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetPreviousStepTaskByTaskIdCmd;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.expand.command.RollBackTaskPreviousStepCommand;

public class RollBackTaskPreviousStepCmd extends AbstractExpandTaskCmd<RollBackTaskPreviousStepCommand,Void> {

	

	public RollBackTaskPreviousStepCmd(RollBackTaskPreviousStepCommand expandTaskCommand) {
		super(expandTaskCommand);
		//this.rollBackTaskId = expandTaskCommand.getRollBackTaskId();
		// TODO Auto-generated constructor stub
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
		
		
		
		// TODO Auto-generated method stub
		List<TaskInstance> taskInstanceQueryTos = (new GetPreviousStepTaskByTaskIdCmd(this.taskId)).execute(commandContext);

		if (taskInstanceQueryTos.size() == 0) {
			throw new FixFlowException("没有可以退回的任务");
		}

		
		
		
		if (taskInstanceQueryTos.size() > 1||(taskInstanceQueryTos.size()==1&&taskInstanceQueryTos.get(0).getTaskGroup()!=null)) {

			if (taskCommand != null) {

				String taskId = this.taskId;
				String taskComment = this.taskComment;
				String rollBackNodeId = taskInstanceQueryTos.get(0).getNodeId();

			

			
				try {

				
					
					UserTaskBehavior backNodeUserTask = (UserTaskBehavior) getExecutionContext().getProcessDefinition().getDefinitions().getElement(rollBackNodeId);
					taskInstance.toFlowNodeEnd(taskCommand, taskComment, backNodeUserTask, null);

					
					

				} catch (Exception e) {

					throw new FixFlowException("任务: " + taskId + " 退回失败!", e);
				}

			}else{
				throw new FixFlowException("未点击任务处理按钮,任务处理失败。");
			}

			try {
				saveProcessInstance(commandContext);
			} catch (Exception e) {
				throw new FixFlowException("流程实例持久化失败!", e);
			}
			return null;

		} else {

			if (taskCommand != null) {

				String taskId = this.taskId;
				String taskComment = this.taskComment;
				String rollBackNodeId = taskInstanceQueryTos.get(0).getNodeId();

				
				try {

					UserTaskBehavior backNodeUserTask = (UserTaskBehavior) getExecutionContext().getProcessDefinition().getDefinitions().getElement(rollBackNodeId);
					taskInstance.toFlowNodeEnd(taskCommand, taskComment, backNodeUserTask, taskInstanceQueryTos.get(0).getAssignee());


				} catch (Exception e) {

					throw new FixFlowException("任务: " + taskId + " 退回失败!", e);
				}

			}

			try {
				saveProcessInstance(commandContext);
			} catch (Exception e) {
				throw new FixFlowException("流程实例持久化失败!", e);
			}
			return null;

		}

	}

	
}

