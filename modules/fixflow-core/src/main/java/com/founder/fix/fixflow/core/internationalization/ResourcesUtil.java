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

import com.founder.fix.fixflow.core.ProcessEngineManagement;

/**
 * 国际化资源工具类
 * 用来获取对应资源文件中，对应key的国际化值
 * @author ych
 *
 */
public class ResourcesUtil {
	
	public static String getResourcesValue(String propertiesName,String key){
		FixFlowResources fixFlowResources=ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getFixFlowResources();
    	if(fixFlowResources == null){
    		return key;
    	}
    	return fixFlowResources.getResourceValue(propertiesName, key);
	}
	
	public static String getResourcesValue(String propertiesName,String key,String languageType){
		FixFlowResources fixFlowResources=ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getFixFlowResources();
    	if(fixFlowResources == null){
    		return key;
    	}
    	return fixFlowResources.getResourceValue(propertiesName, key,languageType);
	}

}
