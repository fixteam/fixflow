package com.founder.fix.fixflow.expand.expression;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.impl.ExternalContent;

public class GroovyUtil {
	
	public Object performGroovyExpression(String expression,String userId){
		
		
	
		
		ExternalContent externalContent=new ExternalContent();
		externalContent.setAuthenticatedUserId(userId);
		
		
		ProcessEngine processEngine=ProcessEngineManagement.getDefaultProcessEngine();
		processEngine.setExternalContent(externalContent);
		
		
		
		
		
		return null;

		
		
	}

}
