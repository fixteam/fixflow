package com.founder.fix.fixflow.expand.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.founder.fix.fixflow.core.action.AssignmentHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.task.TaskAssigneeDefinitionTo;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.Assignable;
import com.founder.fix.fixflow.core.task.IdentityLinkType;
import com.founder.fix.fixflow.core.task.TaskDefinition;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class SimulationRunAssignmentImpl implements AssignmentHandler {

	@SuppressWarnings("unchecked")
	public void assign(Assignable assignable, ExecutionContext executionContext) {
		// TODO 自动生成的方法存根
		
		
		
		Object valueObject=Context.getAbstractScriptLanguageMgmt().getVariable("selectInfo");
		boolean succecd = false;
		if(valueObject != null){
			TaskDefinition taskDefinition = assignable.getTaskDefinition();
			String nodeId = taskDefinition.getUserTaskNode().getId();
			@SuppressWarnings("unused")
			String taskId=assignable.getId();
			List<Map<String, String>> selectInfoList = (List<Map<String, String>> )valueObject;
			
			for(Map<String, String> seleInfoMap : selectInfoList){
//				String _taskId = seleInfoMap.get("taskId");
				String _nodeId = seleInfoMap.get("nodeId");
				String _processer = seleInfoMap.get("processer");
				
				if(nodeId.equals(_nodeId)){
					assignable.setAssignee(_processer);
					succecd = true;
					break;
				}
			}
		}
		
		
		if(!succecd){
			TaskDefinition taskDefinition=assignable.getTaskDefinition();
			
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
