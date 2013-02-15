package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.identity.UserDefinition;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class GetAgentUsersCmd implements  Command<List<UserTo>> {

	
	protected String userId;
	
	public GetAgentUsersCmd(String userId){
		this.userId=userId;
	}
	
	public List<UserTo> execute(CommandContext commandContext) {
		
		List<Map<String, Object>> listmMaps=commandContext.getTaskManager().findAgentUsers(userId);
		List<UserTo> userTos=new ArrayList<UserTo>();
		UserDefinition userDefinition=commandContext.getProcessEngineConfigurationImpl().getUserDefinition();
		
		for (Map<String, Object> map : listmMaps) {
			String userIdDataString=StringUtil.getString(map.get("USERID"));
			
			if(userIdDataString!=null&&!userIdDataString.equals("")){
				UserTo userTo=userDefinition.findUserByUserId(userIdDataString);
				userTos.add(userTo);
			}
			
			
		}
		
		return userTos;
	}

	

}
