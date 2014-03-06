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
package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.internationalization.ResourcesUtil;

public class GetModelInternationalizationResourcesCmd implements Command<String> {

	protected String resourcesType;
	protected String resourceKey;
	
	public GetModelInternationalizationResourcesCmd(String resourcesType, String resourceKey){
		this.resourcesType=resourcesType;
		this.resourceKey=resourceKey;
	}
	
	public String execute(CommandContext commandContext) {


		
		Boolean booleanTemp=StringUtil.getBoolean(Context.getProcessEngineConfiguration().getInternationalizationConfig().getIsEnable());
    	
    	
    	if(booleanTemp){
    		
        	String nameTemp=null;
        	
        	nameTemp=ResourcesUtil.getResourcesValue(resourcesType, resourceKey);
            	


        	if(nameTemp==null){
        		return resourceKey;
        	}
        	return nameTemp;
        	
    	}
    	else{
    		return resourceKey;
    	}
		
		
		
	}

}
