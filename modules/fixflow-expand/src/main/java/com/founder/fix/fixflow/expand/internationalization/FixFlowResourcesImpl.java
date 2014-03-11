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
package com.founder.fix.fixflow.expand.internationalization;

import com.founder.fix.fixflow.core.internationalization.FixFlowResources;

/**
 * fixflow国际化实现
 * @author ych
 *
 */
public class FixFlowResourcesImpl implements FixFlowResources {

	public String getResourceValue(String propertiesName, String resourceKey) {
		String resourceName=FixResourceCore.getResource(propertiesName, resourceKey);
		return resourceName;
	}
	
	public String getResourceValue(String propertiesName, String resourceKey,
			Object ... args) {
		String resourceName=FixResourceCore.getResource(propertiesName, resourceKey,args);
		return resourceName;
	}

	public String getResourceValue(String propertiesName, String resourceKey,
			String languageType) {
		setNowLanguage(languageType);
		String resourceName=FixResourceCore.getResource(propertiesName, resourceKey);
		return resourceName;
	}
	
	public String getResourceValue(String propertiesName, String resourceKey,
			String languageType,Object... args) {
		setNowLanguage(languageType);
		String resourceName=FixResourceCore.getResource(propertiesName, resourceKey);
		return resourceName;
	}

	public void systemInit(String resourcePath) {
		resourcePath = this.getClass().getClassLoader().getResource("/").getPath() + resourcePath;
		FixResourceCore.systemInit(resourcePath);
	}
	
	public void reload(){
		String resourcePath = FixResourceCore.getResourcePath();
		FixResourceCore.systemInit(resourcePath);
	}

	public String getNowLanguage() {
		return FixResourceCore.getNowLanguage(); 
	}

	public void setNowLanguage(String languageType) {
		FixResourceCore.setNowLanguage(languageType); 
	}
    
}
