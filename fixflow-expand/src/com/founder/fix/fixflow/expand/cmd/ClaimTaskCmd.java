package com.founder.fix.fixflow.expand.cmd;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.expand.command.ClaimTaskCommand;


/**
 * @author kenshin
 */
public class ClaimTaskCmd extends AbstractExpandTaskCmd<ClaimTaskCommand, Void> {



	public ClaimTaskCmd(ClaimTaskCommand claimTaskCommand) {
		super(claimTaskCommand);
	
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
			
			
			if(this.agent!=null&&!this.agent.equals("")){
				
				if (task.getAssignee() != null) {
					if (!task.getAssignee().equals(this.agent)) {
						// 当任务已经被另一个不是自己的用户占有，则抛出异常。
						throw new FixFlowException("任务 " + taskId + " 已经被另一个用户领取!");
					}
				} else {
					
					task.setAssignee(this.agent);
					task.setClaimTime(ClockUtil.getCurrentTime());
					Context.getCommandContext().getTaskManager()
							.saveTaskInstanceEntity(task);
					
				}
		
				
			}
			else{

					if (task.getAssignee() != null) {
						if (!task.getAssignee().equals(Authentication.getAuthenticatedUserId())) {
							// 当任务已经被另一个不是自己的用户占有，则抛出异常。
							throw new FixFlowException("任务 " + taskId + " 已经被另一个用户领取!");
						}
					} else {
						
						task.setAssignee(Authentication.getAuthenticatedUserId() );
						task.setClaimTime(ClockUtil.getCurrentTime());
						Context.getCommandContext().getTaskManager()
								.saveTaskInstanceEntity(task);
						
					}
			
			}
			
			
			
		}
		else{
			// 当用户编号为空的时候则将该任务设置为任何人都可以获取
			task.setAssignee(null);
			Context.getCommandContext().getTaskManager()
					.saveTaskInstanceEntity(task);
		}
		
		
		

		return null;
	}

}
