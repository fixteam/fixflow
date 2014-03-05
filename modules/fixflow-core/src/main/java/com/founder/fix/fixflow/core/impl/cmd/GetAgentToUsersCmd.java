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
 * @author yangchenhui
 */
package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.founder.fix.fixflow.core.impl.identity.UserDefinition;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

/**
 * 获取当前用户设置的代理人信息
 * @author Administrator
 *
 */
public class GetAgentToUsersCmd implements Command<List<UserTo>> {

	protected String userId;
	
	public GetAgentToUsersCmd(String userId){
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
