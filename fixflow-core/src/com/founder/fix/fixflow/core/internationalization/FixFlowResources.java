package com.founder.fix.fixflow.core.internationalization;


public interface FixFlowResources {
	
	public static String TaskComandResource="FixFlow_SystemTaskComandResource";
	
	public static String ExceptionResource="FixFlow_ExceptionResource";
	

	public String getResourceName(String processId,String resourceKey);
	
	public String getResourceName(String processId,String resourceKey,String languageType);
	
	public void systemInit();
	
	
		

}
