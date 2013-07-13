package com.founder.fix.fixflow.expand.cmd;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.expand.command.ContinueTaskCommand;

/**
 * @author kenshin
 */
public class ContinueTaskCmd extends AbstractExpandTaskCmd<ContinueTaskCommand, Void> {

	public ContinueTaskCmd(ContinueTaskCommand continueTaskCommand) {
		super(continueTaskCommand);

	}

	public Void execute(CommandContext commandContext) {
		if (taskId == null || taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}
		if(this.userCommandId==null||this.userCommandId.equals("")){
			throw new FixFlowException("未点击任务处理按钮,任务处理失败。");
		}
		TaskInstanceEntity task = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		
	

		
		

		if (task == null) {
			throw new FixFlowException("无法找到编号为: " + taskId + " 的任务!");
		}
		
		String processInstanceId=task.getProcessInstanceId();
		ProcessInstanceEntity  processInstance =Context.getCommandContext().getProcessInstanceManager().findProcessInstanceById(processInstanceId);
		
		String tokenId=task.getTokenId();
		TokenEntity token=processInstance.getTokenMap().get(tokenId);
		token.resume();
		try {
			Context.getCommandContext().getProcessInstanceManager().saveProcessInstance(processInstance);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new FixFlowException("恢复任务时出现错误!", e);
			
		}
		return null;
	}

}
