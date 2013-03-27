package com.founder.fix.fixflow.core.task;



import java.io.Serializable;
import java.util.List;

import org.eclipse.bpmn2.UserTask;

import com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType;
import com.founder.fix.fixflow.core.action.AssignmentHandler;
import com.founder.fix.fixflow.core.impl.task.TaskAssigneeDefinitionTo;

public interface TaskDefinition extends Serializable{
	
	String getId();

	String getNameExpression();

	void setNameExpression(String nameExpression);

	String getDescriptionExpression();

	void setDescriptionExpression(String descriptionExpression);
	List<TaskAssigneeDefinitionTo> getTaskAssigneeDefinitionTos();
	
	String getDueDateExpression();
	  
	void setDueDateExpression(String dueDateExpression);

	AssignmentHandler getAssignAction();
	int getExpectedExecutionTimeValue();
	
	UserTask getUserTaskNode();
	
	AssignPolicyType getAssignPolicyType();
	
	TaskInstanceType getTaskInstanceType();
}
