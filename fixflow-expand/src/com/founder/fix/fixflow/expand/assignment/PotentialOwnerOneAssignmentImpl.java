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
package com.founder.fix.fixflow.expand.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.founder.fix.fixflow.core.action.AssignmentHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.task.IdentityLinkEntity;
import com.founder.fix.fixflow.core.impl.task.TaskAssigneeDefinitionTo;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.Assignable;
import com.founder.fix.fixflow.core.task.IdentityLinkType;
import com.founder.fix.fixflow.core.task.TaskDefinition;
import com.founder.fix.fixflow.core.task.TaskInstance;

/**
 * 共享 (当只有一人时采用独占策略)
 * 
 * @author kenshin
 * 
 */
public class PotentialOwnerOneAssignmentImpl implements AssignmentHandler {

	public void assign(Assignable assignable, ExecutionContext executionContext) {
		// TODO 自动生成的方法存根
		TaskDefinition taskDefinition = assignable.getTaskDefinition();

		List<TaskAssigneeDefinitionTo> taskAssigneeDefinitionTos = taskDefinition.getTaskAssigneeDefinitionTos();

		for (TaskAssigneeDefinitionTo taskAssigneeDefinitionTo : taskAssigneeDefinitionTos) {

			if (taskAssigneeDefinitionTo.getUserIdExpression() != null) {
				List<String> resultList = executionExpression(taskAssigneeDefinitionTo.getUserIdExpression(), executionContext);

				if (resultList.size() > 0) {
					for (String userId : resultList) {

						if (userId != null && !userId.equals("")) {

							if (taskAssigneeDefinitionTo.getIdentityLinkType().toString().equals(IdentityLinkType.assignee.toString())) {
								// assignable.addAssigneeUser(userId,
								// taskAssigneeDefinitionTo.getIncludeExclusion());
								if (userId.equals("fixflow_allusers")) {
									throw new FixFlowException("任务分配给所有人的时候不能采用独占方式分配,请重新修改节点 " + ((TaskInstance) assignable).getNodeId()
											+ " 的任务分配定义");
								}
								assignable.setAssignee(userId);
							} else {
								assignable.addCandidateUser(userId, taskAssigneeDefinitionTo.getIncludeExclusion());
							}
						}
					}
				}
			}

			if (taskAssigneeDefinitionTo.getGroupIdExpression() != null) {
				List<String> resultList = executionExpression(taskAssigneeDefinitionTo.getGroupIdExpression(), executionContext);

				if (resultList.size() > 0) {
					for (String groupId : resultList) {
						if (groupId != null && !groupId.equals("")) {

							if (taskAssigneeDefinitionTo.isContainsSub()) {
								List<GroupTo> groupTos = null;
								try {
									groupTos = Authentication.findGroupChildMembersIncludeByGroupId(groupId,
											taskAssigneeDefinitionTo.getGroupTypeExpression());

								} catch (Exception e) {
									throw new FixFlowException("节点 " + ((TaskInstance) assignable).getNodeId() + " 的任务获取子分配组出错！", e);
								}

								if (groupTos != null) {

									for (GroupTo groupTo : groupTos) {
										GroupTo groupToObj = new GroupTo(groupTo.getGroupId(), taskAssigneeDefinitionTo.getGroupTypeExpression());

										assignable.addCandidateGroup(groupToObj, taskAssigneeDefinitionTo.getIncludeExclusion());
									}

								} else {
									throw new FixFlowException("节点 " + ((TaskInstance) assignable).getNodeId() + " 的任务获取子分配组出错！");
								}

							} else {
								GroupTo groupTo = new GroupTo(groupId, taskAssigneeDefinitionTo.getGroupTypeExpression());

								assignable.addCandidateGroup(groupTo, taskAssigneeDefinitionTo.getIncludeExclusion());
							}

						}

					}
				}
			}

		}

		List<IdentityLinkEntity> identityLinkEntities = assignable.getTaskIdentityLinkEntitys();

		List<String> userIds = new ArrayList<String>();

		for (IdentityLinkEntity identityLinkEntity : identityLinkEntities) {

			if (identityLinkEntity.getUserId() != null) {
				userIds.add(identityLinkEntity.getUserId());
			}

			if (identityLinkEntity.getGroupId() != null) {

				GroupDefinition groupDefinition = Authentication.groupDefinition(identityLinkEntity.getGroupType());

				List<UserTo> userTos = groupDefinition.findUserByGroupId(identityLinkEntity.getGroupId());
				if (userTos.size() > 1) {
					return;
				} else {
					if (userTos.size() == 1) {
						if (userIds.size() >= 1) {
							return;
						}
						userIds.add(userTos.get(0).getUserId());
					}
				}
			}
		}

		if (userIds.size() == 1) {
			if(!userIds.get(0).equals("fixflow_allusers")){
				assignable.getTaskIdentityLinkEntitys().clear();
				assignable.setAssignee(userIds.get(0));
			}
			
			
		}

	}

	private List<String> executionExpression(String expression, ExecutionContext executionContext) {

		List<String> resultList = new ArrayList<String>();

		Object result = ExpressionMgmt.execute(expression, executionContext);
		if (result != null) {

			if (result instanceof String) {

				String[] dddStrings = result.toString().split(",");

				for (int i = 0; i < dddStrings.length; i++) {
					resultList.add(dddStrings[i]);
				}

			}

			if (result instanceof Integer) {
				resultList.add(result.toString());
			}

			if (result instanceof List<?>) {
				List<?> resultTempList = (List<?>) result;
				for (Object resultObj : resultTempList) {
					resultList.add(resultObj.toString());
				}
			}

			if (result instanceof Set<?>) {
				Set<?> resultTempList = (Set<?>) result;
				for (Object resultObj : resultTempList) {
					resultList.add(resultObj.toString());
				}
			}

		}

		return resultList;
	}

}
