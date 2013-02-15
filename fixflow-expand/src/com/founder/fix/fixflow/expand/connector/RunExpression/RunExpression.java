package com.founder.fix.fixflow.expand.connector.RunExpression;


import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;

public class RunExpression implements ConnectorHandler {

	private java.lang.String expressionText;
	protected java.lang.Object outputObj;
	public void execute(ExecutionContext executionContext) throws Exception {
		Object object=ExpressionMgmt.execute(expressionText, executionContext);
		outputObj=object;
	}

	public void  setExpressionText(java.lang.String expressionText){
		this.expressionText = expressionText;
	}

	public java.lang.Object  getOutputObj(){
		return outputObj ;
	}

}