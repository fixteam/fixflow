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

import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.expand.command.ReleaseTaskCommand;


public class ReleaseTaskCmd extends AbstractExpandTaskCmd<ReleaseTaskCommand, Void>{

	public ReleaseTaskCmd(ReleaseTaskCommand abstractCustomExpandTaskCommand) {
		super(abstractCustomExpandTaskCommand);
	}

	public Void execute(CommandContext commandContext) {

		if (taskId == null||taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}


		TaskInstanceEntity task = Context.getCommandContext().getTaskManager()
				.findTaskById(taskId);

		if (task == null) {
			throw new FixFlowException("无法找到编号为: " + taskId + " 的任务!");
		}
		if (Authentication.getAuthenticatedUserId() != null) {
			if (task.getAssignee() != null) {
				if (!task.getAssignee().equals(Authentication.getAuthenticatedUserId())) {
					// 当任务已经被另一个不是自己的用户占有，则抛出异常。
					throw new FixFlowException("任务 " + taskId + " 已经被另一个用户领取!您不能做还回操作!");
				}
				else{
					
					if(task.getIdentityLinkQueryToList().size()>0){
						task.setAssignee(null);
						task.setClaimTime(null);
						Context.getCommandContext().getTaskManager()
								.saveTaskInstanceEntity(task);
					}
					else{
						throw new FixFlowBizException("任务 " + taskId + " 没有候选处理者不能被释放!");
					}
					
					
					
				}
			} else {
				throw new FixFlowException("任务 " + taskId + " 没有被领取,所以不能做还回操作!");

			}
		}else{
			throw new FixFlowException("流程操作没有设置处理者");
			
		}
		

		return null;
	}

}
