package com.founder.fix.fixflow.core.impl.expression;


import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;



public class ExpressionMgmt {

	


	public static Object execute(String scriptText, ExecutionContext executionContext) {

		return Context.getAbstractScriptLanguageMgmt().execute(scriptText, executionContext);
		
	}

	public static Object getVariable(String variableName) {
		return Context.getAbstractScriptLanguageMgmt().getVariable(variableName);
		
	}
	
	public static void setVariable(String variableName, Object variableObj,ExecutionContext executionContext) {

		Context.getAbstractScriptLanguageMgmt().setVariable(variableName,variableObj,executionContext);
		
	}
	

	public static void setVariable(String variableName, Object variableObj) {


		Context.getAbstractScriptLanguageMgmt().setVariable(variableName,variableObj);
		
	}

	public static Object execute(String scriptText, ProcessDefinitionBehavior processDefinition) {
		return Context.getAbstractScriptLanguageMgmt().execute(scriptText,processDefinition);
		
	}

	

}
