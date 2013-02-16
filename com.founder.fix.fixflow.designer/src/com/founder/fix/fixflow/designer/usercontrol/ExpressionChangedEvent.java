package com.founder.fix.fixflow.designer.usercontrol;

import java.util.EventObject;


public class ExpressionChangedEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1999273206266333243L;
	
	protected ExpressionTo expressionTo;
	
	protected ExpressionComboViewer eventSource;
	
	public ExpressionTo getExpressionTo() {
		return expressionTo;
	}


	public ExpressionChangedEvent(ExpressionComboViewer eventSource,ExpressionTo expressionTo) {
		super(eventSource);
		this.eventSource=eventSource;
		this.expressionTo=expressionTo;
		// TODO Auto-generated constructor stub
	}
	
	
	

}
