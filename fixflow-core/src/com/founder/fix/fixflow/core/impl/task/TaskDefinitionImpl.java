package com.founder.fix.fixflow.core.impl.task;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.PotentialOwner;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.ecore.util.FeatureMap;

import com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType;
import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.fixflow.core.action.AssignmentHandler;
import com.founder.fix.fixflow.core.exception.FixFlowClassLoadingException;
import com.founder.fix.fixflow.core.exception.FixFlowDbException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.util.EMFExtensionUtil;
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

	public TaskDefinitionImpl(UserTask userTask) {

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

		if (((UserTaskBehavior) userTask).getTaskSubject() != null && ((UserTaskBehavior) userTask).getTaskSubject().getExpressionValue() != null) {
			descriptionExpression = ((UserTaskBehavior) userTask).getTaskSubject().getExpressionValue();
		}

		// 计算预计执行时间
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;
		List<FeatureMap.Entry> entries = EMFExtensionUtil.getExtensionElements(userTask, "expectedExecutionTime");
		if (entries.size() > 0) {
			FeatureMap.Entry entry = entries.get(0);
			day = StringUtil.getInt(EMFExtensionUtil.getExtensionElementAttributeValue(entry, "day"));
			hour = StringUtil.getInt(EMFExtensionUtil.getExtensionElementAttributeValue(entry, "hour"));
			minute = StringUtil.getInt(EMFExtensionUtil.getExtensionElementAttributeValue(entry, "minute"));
			second = StringUtil.getInt(EMFExtensionUtil.getExtensionElementAttributeValue(entry, "second"));

		}
		expectedExecutionTimeValue = second + (minute * 60) + (hour * 60 * 60) + (day * 24 * 60 * 60);
		
		
		String taskTypeString=EMFExtensionUtil.getAnyAttributeValue(userTask, "taskType");
		if(taskTypeString==null||taskTypeString.equals("")){
			taskInstanceType=TaskInstanceType.FIXFLOWTASK;
		}
		else{
			if(TaskInstanceType.FIXFLOWTASK.equals(taskTypeString)){
				taskInstanceType=TaskInstanceType.FIXFLOWTASK;
			}else{
				if(TaskInstanceType.FIXNOTICETASK.equals(taskTypeString)){
					taskInstanceType=TaskInstanceType.FIXNOTICETASK;
				}
				else {
					
					taskInstanceType=TaskInstanceType.valueOf(taskTypeString);
				}
			}
		}

		/*
		 * if(userTask.getDueDate()!=null&&userTask.getDueDate().getExpression()!=
		 * null) { dueDateExpression=userTask.getDueDate().getExpression(); }
		 */

		for (ResourceRole resourceRoleObj : userTask.getResources()) {

			String resourceTypeObj = EMFExtensionUtil.getAnyAttributeValue(resourceRoleObj, "resourceType");
			String includeExclusion = EMFExtensionUtil.getAnyAttributeValue(resourceRoleObj, "includeExclusion");
			String resourceRange = EMFExtensionUtil.getAnyAttributeValue(resourceRoleObj, "resourceRange");
			boolean isContainsSub = StringUtil.getBoolean(EMFExtensionUtil.getAnyAttributeValue(resourceRoleObj, "isContainsSub"));

			FormalExpression expression = (FormalExpression) resourceRoleObj.getResourceAssignmentExpression().getExpression();
			if (expression == null) {
				throw new FixFlowDbException("处理者表达式不能为空!");
			}
			TaskAssigneeDefinitionTo taskAssigneeDefinitionTo = new TaskAssigneeDefinitionTo();
			taskAssigneeDefinitionTo.setResourceRange(resourceRange);

			List<FeatureMap.Entry> assignPolicyTypeList = EMFExtensionUtil.getExtensionElements(userTask, "assignPolicyType");
			if (assignPolicyTypeList.size() > 0) {
				FeatureMap.Entry entryType = assignPolicyTypeList.get(0);
				String id = StringUtil.getString(EMFExtensionUtil.getExtensionElementAttributeValue(entryType, "id"));

				assignPolicyType = FixFlowFactory.eINSTANCE.createAssignPolicyType();
				assignPolicyType.setId(id);

				if (EMFExtensionUtil.getExtensionElementsInEntry(entryType, "expression").size() > 0) {
					FeatureMap.Entry expressionEntry = EMFExtensionUtil.getExtensionElementsInEntry(entryType, "expression").get(0);

					String expressionValue = EMFExtensionUtil.getExtensionElementValue(expressionEntry);

					Expression expressionType = FixFlowFactory.eINSTANCE.createExpression();
					expressionType.setValue(expressionValue);
					assignPolicyType.setExpression(expressionType);
				}

			}

			/*
			 * AssignPolicyConfig
			 * assignPolicyConfig=Context.getProcessEngineConfiguration
			 * ().getAssignPolicyConfig(); AssignPolicy assignPolicy=null; for
			 * (AssignPolicy assignPolicyObj :
			 * assignPolicyConfig.getAssignPolicy()) {
			 * if(assignPolicyObj.getId().equals(assignPolicyType)){
			 * assignPolicy=assignPolicyObj; } }
			 */

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
