package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.founder.fix.fixflow.core.impl.identity.UserDefinition;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class GetAgentUsersAndCountCmd implements  Command<List<Map<String, Object>>>{

	protected String userId;
	
	public GetAgentUsersAndCountCmd(String userId){
		
		this.userId=userId;
		
	}
	
	public List<Map<String, Object>> execute(CommandContext commandContext) {
		
		List<Map<String, Object>> returnListMaps=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> listmMaps=commandContext.getTaskManager().findAgentUsers(userId);
		UserDefinition userDefinition=commandContext.getProcessEngineConfigurationImpl().getUserDefinition();
		
		for (Map<String, Object> map : listmMaps) {
			String userIdDataString=StringUtil.getString(map.get("EID"));
			
			if(userIdDataString!=null&&!userIdDataString.equals("")){
				UserTo userTo=userDefinition.findUserByUserId(userIdDataString);
				//userTos.add(userTo);
				
				Map<String, Object> mapDataMap=new HashMap<String, Object>();
				//mapDataMap.put("userto", userTo);
				mapDataMap.put("userid", userTo.getUserId());
				mapDataMap.put("username", userTo.getUserName());
				mapDataMap.put("count", 0);
				returnListMaps.add(mapDataMap);
			}
			
			
		}
		
		return returnListMaps;
	}
	
	

}
