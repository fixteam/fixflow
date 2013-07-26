package com.founder.fix.fixflow.core.impl.bpmn.behavior;


import com.founder.fix.bpmn2extensions.fixflow.TaskSubject;

public class TaskSubjectBehavior {

	protected String id;
	
	protected String name;

	protected String expressionValue;
	
	
	public TaskSubjectBehavior(TaskSubject taskSubject)
	{
		
		if(taskSubject.getExpression()!=null){
			this.expressionValue=taskSubject.getExpression().getValue();
		}
		

	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getExpressionValue() {
		return expressionValue;
	}
	
	
	
	
	

}
