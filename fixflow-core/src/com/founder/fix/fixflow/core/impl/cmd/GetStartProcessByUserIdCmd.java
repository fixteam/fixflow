package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class GetStartProcessByUserIdCmd implements Command<List<Map<String, String>>>{

	protected String userId;
	
	public GetStartProcessByUserIdCmd(String userId){
		this.userId=userId;
	}
	
	
	public List<Map<String, String>> execute(CommandContext commandContext) {
		
		CommandExecutor commandExecutor=Context.getProcessEngineConfiguration().getCommandExecutor();
		
		List<Map<String, Object>> processDefData=commandExecutor.execute(new GetProcessDefinitionGroupKeyCmd());
		
		List<Map<String,String>> processData=new ArrayList<Map<String,String>>();
		
		for (Map<String, Object> map : processDefData) {
			String processKey=StringUtil.getString(map.get("PROCESS_KEY"));
			boolean state=commandExecutor.execute(new VerificationStartUserCmd(this.userId,processKey,null));
			if(state){
				Map<String, String> dataMap=new HashMap<String, String>();
				
				dataMap.put("processDefinitionId", StringUtil.getString(map.get("PROCESS_ID")));
				dataMap.put("processDefinitionName", StringUtil.getString(map.get("PROCESS_NAME")));
				dataMap.put("processDefinitionKey", StringUtil.getString(map.get("PROCESS_KEY")));
				dataMap.put("category", StringUtil.getString(map.get("CATEGORY")));
				dataMap.put("version", StringUtil.getString(map.get("VERSION")));
				dataMap.put("resourceName", StringUtil.getString(map.get("RESOURCE_NAME")));
				dataMap.put("resourceId", StringUtil.getString(map.get("RESOURCE_ID")));
				dataMap.put("deploymentId", StringUtil.getString(map.get("DEPLOYMENT_ID")));
				dataMap.put("diagramResourceName", StringUtil.getString(map.get("DIAGRAM_RESOURCE_NAME")));
				processData.add(dataMap);
			}
		}
		
		return processData;
		
		
	
	}

}
