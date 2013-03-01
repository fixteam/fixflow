package com.founder.fix.fixflow.expand.cmd;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.task.DelegationState;
import com.founder.fix.fixflow.expand.command.ResolvedTaskCommand;

public class ResolvedTaskCmd extends AbstractExpandTaskCmd<ResolvedTaskCommand, Void> {

	public ResolvedTaskCmd(ResolvedTaskCommand abstractCustomExpandTaskCommand) {
		super(abstractCustomExpandTaskCommand);
	}

	public Void execute(CommandContext commandContext) {

		if (taskId == null || taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}

		TaskInstanceEntity task = Context.getCommandContext().getTaskManager().findTaskById(taskId);

		if (task == null) {
			throw new FixFlowException("无法找到编号为: " + taskId + " 的任务!");
		}
		if (Authentication.getAuthenticatedUserId() != null) {
			String owner = task.getOwner();

			String nodeId = task.getNodeId();
			String processDefinitionId = task.getProcessDefinitionId();

			ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

			ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

			UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(nodeId);

			TaskCommandInst taskCommand = null;

			if (this.admin != null && !this.admin.equals("")) {

				String taskCommandName = commandContext.getProcessEngineConfigurationImpl().getTaskCommandDefMap().get(userCommandId).getName();

				taskCommand = new TaskCommandInst(userCommandId, taskCommandName, null, userCommandId, true);

			} else {
				taskCommand = userTask.getTaskCommandsMap().get(userCommandId);
			}

			task.customEnd(taskCommand, taskComment, this.agent, this.admin);

			

			task.setDelegationState(DelegationState.RESOLVED);

			Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(task);

			task.setIdWithoutCascade(GuidUtil.CreateGuid());
			task.setAssigneeWithoutCascade(owner);
			task.setCreateTimeWithoutCascade(ClockUtil.getCurrentTime());

			task.setEndTimeWithoutCascade(null);
			task.setCommandId(null);
			task.setCommandType(null);
			task.setCommandMessage(null);
			task.setTaskComment(null);
			task.setAgent(null);
			task.setAdmin(null);
			task.setDelegationState(null);
			
			if(task.getPendingTaskId()==null){
				
			}
			else{
				TaskInstanceEntity resolvedTask = Context.getCommandContext().getTaskManager().findTaskById(task.getPendingTaskId());

				task.setFormUri(resolvedTask.getFormUri());
			}
			
			
			
			
			Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(task);

		} else {
			throw new FixFlowException("无法找到当前处理者");
		}

		return null;
	}

}
