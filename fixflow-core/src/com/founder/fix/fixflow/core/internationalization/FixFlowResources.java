package com.founder.fix.fixflow.core.internationalization;


public interface FixFlowResources {
	

	public String getResourceName(String processId,String resourceKey);
	
	public String getResourceName(String processId,String resourceKey,String languageType);
	
	public void systemInit();
	
	
		

}
