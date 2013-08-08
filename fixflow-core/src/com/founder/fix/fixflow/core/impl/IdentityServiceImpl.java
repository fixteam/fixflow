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
package com.founder.fix.fixflow.core.impl;


import java.util.List;

import com.founder.fix.fixflow.core.IdentityService;
import com.founder.fix.fixflow.core.impl.cmd.GetGroupInfoByGroupIdCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetUserInGroupCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetUserInGroupsCmd;
import com.founder.fix.fixflow.core.impl.cmd.GroupDefinitionInfoCmd;
import com.founder.fix.fixflow.core.impl.cmd.UserDefinitionInfoCmd;
import com.founder.fix.fixflow.core.impl.cmd.VerificationStartUserCmd;

import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserDefinition;
import com.founder.fix.fixflow.core.impl.identity.UserTo;

public class IdentityServiceImpl extends ServiceImpl implements IdentityService {

	public GroupDefinition getGroupDefinition(String groupType) {
		return commandExecutor.execute(new GroupDefinitionInfoCmd(groupType));
	}

	public GroupTo getGroup(String groupId, String groupType) {
		return commandExecutor.execute(new GetGroupInfoByGroupIdCmd(groupId,getGroupDefinition(groupType)));
	}

	public UserDefinition getUserDefinition() {
		return commandExecutor.execute(new UserDefinitionInfoCmd());
	}

	public UserTo getUserTo(String userId) {
		return commandExecutor.execute(new UserDefinitionInfoCmd()).findUserByUserId(userId);
	}

	public boolean verificationStartUserByKey(String userId, String processDefinitionKey) {
	
		return commandExecutor.execute(new VerificationStartUserCmd(userId,processDefinitionKey,null));
	}

	public boolean verificationStartUserById(String userId, String processDefinitionId) {
	
		return commandExecutor.execute(new VerificationStartUserCmd(userId,null,processDefinitionId));
	}

	public List<UserTo> getUserInGroupChildMembersInclude(String groupId, String groupType) {
		
		return commandExecutor.execute(new GetUserInGroupCmd(groupId,groupType,true));
	}

	public List<UserTo> getUserInGroup(String groupId, String groupType) {
		return commandExecutor.execute(new GetUserInGroupCmd(groupId,groupType,false));
	}

	public List<GroupTo> getUserInGroups(String userId) {
		
		return commandExecutor.execute(new GetUserInGroupsCmd(userId));
	}
	
	

}
