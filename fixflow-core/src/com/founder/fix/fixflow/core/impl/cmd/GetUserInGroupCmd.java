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

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class GetUserInGroupCmd implements Command<List<UserTo>>{
	
	
	protected String groupId;
	protected String groupType;
	protected boolean childMembers;
	
	
	public GetUserInGroupCmd(String groupId, String groupType,boolean childMembers){
		this.groupId=groupId;
		this.groupType=groupType;
		this.childMembers=childMembers;
	}
	
	public List<UserTo> execute(CommandContext commandContext) {
		
		GroupDefinition groupDefinitionObj=null;
		List<GroupDefinition> groupDefinitions = Context.getProcessEngineConfiguration().getGroupDefinitions();
		for (GroupDefinition groupDefinition : groupDefinitions) {

			if (groupDefinition.getId().equals(this.groupType)) {
				groupDefinitionObj= groupDefinition;
				break;
			}

		}
		if(groupDefinitionObj==null){
			throw new FixFlowException("编号为: "+groupId+" 类型为: "+groupType+" 的组不存在");
		}
		
		if(childMembers){
			return groupDefinitionObj.findUserChildMembersIncludeByGroupId(groupId);
		}
		else{
			return groupDefinitionObj.findUserByGroupId(groupId);
		}
		

	}

}
