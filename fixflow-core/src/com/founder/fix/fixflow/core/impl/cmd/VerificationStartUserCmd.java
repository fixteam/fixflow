package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.task.TaskAssigneeDefinitionTo;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.task.IdentityLinkType;

public class VerificationStartUserCmd implements Command<Boolean> {

	protected String userId;

	protected String processDefinitionKey;

	protected String processDefinitionId;

	public VerificationStartUserCmd(String userId, String processDefinitionKey, String processDefinitionId) {
		this.userId = userId;
		this.processDefinitionKey = processDefinitionKey;
		this.processDefinitionId = processDefinitionId;
	}

	public Boolean execute(CommandContext commandContext) {

		try {
			ProcessDefinitionBehavior processDefinitionBehavior = null;
			if (processDefinitionId != null) {
				processDefinitionBehavior = commandContext.getProcessDefinitionManager().findLatestProcessDefinitionByKey(
						processDefinitionKey);
			} else {
				if (processDefinitionKey != null) {
					processDefinitionBehavior = commandContext.getProcessDefinitionManager().findLatestProcessDefinitionByKey(
							processDefinitionKey);
				} else {
					return false;
				}

			}
			UserTaskBehavior userTask = (UserTaskBehavior) processDefinitionBehavior.getSubTask(processDefinitionBehavior
					.getNoneStartEvent());
			List<GroupTo> groupTos = Authentication.findGroupsByUser(userId);
			if (userTask != null) {
				List<TaskAssigneeDefinitionTo> taskAssigneeDefinitionTos = userTask.getTaskDefinition().getTaskAssigneeDefinitionTos();
				for (TaskAssigneeDefinitionTo taskAssigneeDefinitionTo : taskAssigneeDefinitionTos) {

					if (taskAssigneeDefinitionTo.getIdentityLinkType() == IdentityLinkType.assignee) {
						if (taskAssigneeDefinitionTo.getUserIdExpression() != null) {
							String userIdExpression=StringUtil.getString(ExpressionMgmt.execute(taskAssigneeDefinitionTo.getUserIdExpression(),processDefinitionBehavior));
							if (userIdExpression.equals(userId)) {
								return true;
							}
						}
					} else {

						String userIdString = null;
						if (taskAssigneeDefinitionTo.getUserIdExpression() != null
								&& !taskAssigneeDefinitionTo.getUserIdExpression().equals("")) {
							userIdString = StringUtil.getString(ExpressionMgmt.execute(taskAssigneeDefinitionTo.getUserIdExpression(),
									processDefinitionBehavior));

						}

						if (userIdString != null && !userIdString.equals("")) {
							if (userIdString.equals(userId) || userIdString.equals("fixflow_allusers")) {
								return true;
							}
						}

						String groupType = taskAssigneeDefinitionTo.getGroupTypeExpression();
						String groupId = taskAssigneeDefinitionTo.getGroupIdExpression();

						String groupIdObj = null;

						if (groupId != null) {
							groupIdObj = StringUtil.getString(ExpressionMgmt.execute(groupId, processDefinitionBehavior));
						}

						for (GroupTo groupTo : groupTos) {
							if (groupTo.getGroupId().equals(groupIdObj) && groupTo.getGroupType().equals(groupType)) {
								return true;
							}
						}
					}

				}

			} else {
				return false;
			}

			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
