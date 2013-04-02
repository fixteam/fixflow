package com.founder.fix.fixflow.core.internationalization;


public interface FixFlowResources {
	
	public static String TaskComandResource="FixFlow_SystemTaskComandResource";
	
	public static String ExceptionResource="FixFlow_ExceptionResource";

	public static String OrganizationResource="FixFlow_OrganizationResource";
	
	
	
	public static String SystemResource="FixFlow_SystemResource";
	
	public static String FlowNameResource="FixFlow_FlowNameResource";
	
	

	public String getResourceName(String processId,String resourceKey);
	
	public String getResourceName(String processId,String resourceKey,String languageType);
	
	public void systemInit();
	
	public String getNowLanguage();
		

}
