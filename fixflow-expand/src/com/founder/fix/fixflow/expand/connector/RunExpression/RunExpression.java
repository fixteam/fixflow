package com.founder.fix.fixflow.expand.connector.RunExpression;


import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;

public class RunExpression implements ConnectorHandler {

	private java.lang.Object expressionText;

	public void execute(ExecutionContext executionContext) throws Exception {

	}

	public void  setExpressionText(java.lang.Object expressionText){
		this.expressionText = expressionText;
	}

	public java.lang.Object  getOutputObj(){
		return expressionText ;
	}

}