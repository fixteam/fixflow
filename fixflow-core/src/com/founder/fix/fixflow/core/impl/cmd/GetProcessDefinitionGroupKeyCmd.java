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
