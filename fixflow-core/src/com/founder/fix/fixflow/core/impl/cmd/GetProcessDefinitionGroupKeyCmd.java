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

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.internationalization.FixFlowResources;

public class GetProcessDefinitionGroupKeyCmd  implements Command<List<Map<String, Object>>>  {

	public GetProcessDefinitionGroupKeyCmd(){
		
		
	}
	
	
	public List<Map<String, Object>> execute(CommandContext commandContext) {
		

		Boolean booleanTemp = StringUtil.getBoolean(Context.getProcessEngineConfiguration().getInternationalizationConfig().getIsEnable());
		List<Map<String, Object>> listMap=commandContext.getProcessDefinitionManager().selectProcessDefinitionGroupKey();
		
		//用户名称国际化处理
		if (booleanTemp) {

			FixFlowResources fixFlowResources = Context.getProcessEngineConfiguration().getFixFlowResources();

			for (Map<String, Object> map : listMap) {
				String processKey=StringUtil.getString(map.get("PROCESS_KEY"));
				//String processName=StringUtil.getString(map.get("PROCESS_NAME"));
				
				
				String nameTemp = fixFlowResources.getResourceName(FixFlowResources.FlowNameResource, processKey);
				if (nameTemp == null || nameTemp.equals("")) {
					continue;
				} else {
					map.put("PROCESS_NAME", nameTemp);
				}
			}
			
			return listMap;
			

		} else {
			return listMap;
		}
    	
		

	}

}
