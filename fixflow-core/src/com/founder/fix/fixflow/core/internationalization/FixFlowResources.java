/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.internationalization;

import java.sql.Connection;


public interface FixFlowResources {
	
	public static String TaskComandResource="FixFlow_SystemTaskComandResource";
	
	public static String ExceptionResource="FixFlow_ExceptionResource";

	public static String OrganizationResource="FixFlow_OrganizationResource";
	
	
	
	public static String SystemResource="FixFlow_SystemResource";
	
	public static String FlowNameResource="FixFlow_FlowNameResource";
	
	

	public String getResourceName(String processId,String resourceKey);
	
	public String getResourceName(String processId,String resourceKey,String languageType);
	
	public void systemInit(Connection connection);
	
	public String getNowLanguage();
		

}
