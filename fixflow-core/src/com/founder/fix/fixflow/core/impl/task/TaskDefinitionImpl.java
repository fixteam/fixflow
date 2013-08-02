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
package com.founder.fix.fixflow.core.impl.task;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.PotentialOwner;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.UserTask;

import com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.core.action.AssignmentHandler;
import com.founder.fix.fixflow.core.exception.FixFlowClassLoadingException;
import com.founder.fix.fixflow.core.exception.FixFlowDbException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.task.IdentityLinkType;
import com.founder.fix.fixflow.core.task.IncludeExclusion;
import com.founder.fix.fixflow.core.task.TaskDefinition;
import com.founder.fix.fixflow.core.task.TaskInstanceType;

public class TaskDefinitionImpl implements TaskDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1357653109003002722L;

	protected String id;

	protected String nameExpression;
	protected String descriptionExpression;
	protected String dueDateExpression;

	protected int expectedExecutionTimeValue;

	protected TaskInstanceType taskInstanceType;

	public TaskInstanceType getTaskInstanceType() {
		return taskInstanceType;
	}

	/**
	 * 分配策略
	 */
	protected AssignPolicyType assignPolicyType;

	List<TaskAssigneeDefinitionTo> taskAssigneeDefinitionTos = new ArrayList<TaskAssigneeDefinitionTo>();

	/**
	 * 资源范围
	 */
	protected List<String> resourceRange = new ArrayList<String>();

	public List<TaskAssigneeDefinitionTo> getTaskAssigneeDefinitionTos() {
		return taskAssigneeDefinitionTos;
	}

	public int getExpectedExecutionTimeValue() {
		return expectedExecutionTimeValue;
	}

	protected AssignmentHandler assignAction;

	protected UserTask userTaskNode;

	public TaskDefinitionImpl(UserTaskBehavior userTask) {

		this.id = GuidUtil.CreateGuid();
		this.userTaskNode = userTask;
		if (((UserTaskBehavior) userTask).getAssignmentActionClassName() != null) {
			try {
				assignAction = ProcessObjectFactory.FACTORYINSTANCE.createAssignmentHandler(((UserTaskBehavior) userTask)
						.getAssignmentActionClassName());
			} catch (Exception e) {
				throw new FixFlowClassLoadingException("用户自定义任务分配类创建失败!", e);
			}
		}

		if (((UserTaskBehavior) userTask).getTaskSubject() != null
				&& ((UserTaskBehavior) userTask).getTaskSubject().getExpressionValue() != null) {
			descriptionExpression = ((UserTaskBehavior) userTask).getTaskSubject().getExpressionValue();
		}

		// 计算预计执行时间
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;

		
		if( userTask.getExpectedExecutionTime()!=null){
			day =userTask.getExpectedExecutionTime().getDay();
			hour = userTask.getExpectedExecutionTime().getHour();
			minute = userTask.getExpectedExecutionTime().getMinute();
			second = userTask.getExpectedExecutionTime().getSecond();
		}

		expectedExecutionTimeValue = second + (minute * 60) + (hour * 60 * 60) + (day * 24 * 60 * 60);

		this.taskInstanceType=userTask.getTaskInstanceType();
		/*
		 * if(userTask.getDueDate()!=null&&userTask.getDueDate().getExpression()!=
		 * null) { dueDateExpression=userTask.getDueDate().getExpression(); }
		 */

		for (ResourceRole resourceRoleObj : userTask.getResources()) {
			
			
			
			

			String resourceTypeObj = StringUtil.getString(resourceRoleObj.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_TYPE));
			
			
			String includeExclusion = StringUtil.getString(resourceRoleObj.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__INCLUDE_EXCLUSION));
			
			
			String resourceRange = StringUtil.getString(resourceRoleObj.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_RANGE));
			
			
			boolean isContainsSub = StringUtil.getBoolean(resourceRoleObj.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__IS_CONTAINS_SUB));

			FormalExpression expression = (FormalExpression) resourceRoleObj.getResourceAssignmentExpression().getExpression();
			if (expression == null) {
				throw new FixFlowDbException("处理者表达式不能为空!");
			}
			TaskAssigneeDefinitionTo taskAssigneeDefinitionTo = new TaskAssigneeDefinitionTo();
			taskAssigneeDefinitionTo.setResourceRange(resourceRange);

			
			
			assignPolicyType=userTask.getAssignPolicyType();
			
	

			if (resourceTypeObj.equals("user")) {
				taskAssigneeDefinitionTo.setUserIdExpression(expression.getBody());
			} else {
				taskAssigneeDefinitionTo.setGroupIdExpression(expression.getBody());
				taskAssigneeDefinitionTo.setGroupTypeExpression(resourceTypeObj);
			}
			// 设置组是否包含子级
			taskAssigneeDefinitionTo.setContainsSub(isContainsSub);

			taskAssigneeDefinitionTo.setIncludeExclusion(IncludeExclusion.valueOf(includeExclusion));

			if (resourceRoleObj instanceof PotentialOwner) {
				taskAssigneeDefinitionTo.setIdentityLinkType(IdentityLinkType.candidate);
			} else {
				taskAssigneeDefinitionTo.setIdentityLinkType(IdentityLinkType.assignee);
			}

			taskAssigneeDefinitionTos.add(taskAssigneeDefinitionTo);
		}

	}

	public AssignmentHandler getAssignAction() {
		return assignAction;
	}

	public String getDescriptionExpression() {

		return descriptionExpression;
	}

	public String getDueDateExpression() {

		return dueDateExpression;
	}

	public String getNameExpression() {

		return nameExpression;
	}

	public void setDescriptionExpression(String descriptionExpression) {

		this.descriptionExpression = descriptionExpression;
	}

	public void setDueDateExpression(String dueDateExpression) {

		this.dueDateExpression = dueDateExpression;
	}

	public void setNameExpression(String nameExpression) {

		this.nameExpression = nameExpression;
	}

	public String getId() {
		return id;
	}

	public UserTask getUserTaskNode() {
		return userTaskNode;
	}

	public AssignPolicyType getAssignPolicyType() {
		return assignPolicyType;
	}

	public void setAssignPolicyType(AssignPolicyType assignPolicyType) {
		this.assignPolicyType = assignPolicyType;
	}
}
