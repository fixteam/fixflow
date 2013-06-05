package com.founder.fix.fixflow.expand.expression;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.RuntimeService;

public class ExpressionUtil {
	
	public Object performGroovyExpression(String expression){

		ProcessEngine processEngine=ProcessEngineManagement.getDefaultProcessEngine();
		//processEngine.setExternalContent(externalContent);
		RuntimeService runtimeService=processEngine.getRuntimeService();
		
		return runtimeService.executeRuleScript(expression);

		
		
	}

}
