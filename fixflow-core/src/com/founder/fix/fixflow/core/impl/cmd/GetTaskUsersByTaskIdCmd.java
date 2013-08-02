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

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.task.IdentityLink;
import com.founder.fix.fixflow.core.task.IdentityLinkType;

public class GetTaskUsersByTaskIdCmd  implements Command<List<UserTo>>{

	protected String taskId;
	
	
	public GetTaskUsersByTaskIdCmd(String taskId){
		this.taskId=taskId;
	}
	
	public List<UserTo> execute(CommandContext commandContext) {
		// TODO 自动生成的方法存根
		
		TaskInstanceEntity taskInstanceEntity=commandContext.getTaskManager().findTaskById(taskId);
		List<IdentityLink> identityLinks=taskInstanceEntity.getIdentityLinkQueryToList();
		List<UserTo> userTos=new ArrayList<UserTo>();
		
		
		
		
		
		if(taskInstanceEntity.getAssignee()!=null){
			
			String userId=taskInstanceEntity.getAssignee();
			UserTo userTo=Authentication.findUserInfoByUserId(userId);
			userTos.add(userTo);
			return userTos;
		}
		
		for (IdentityLink identityLink : identityLinks) {
			if(identityLink.getType()==IdentityLinkType.candidate){
				
				if(identityLink.getUserId()!=null){
					String userId=identityLink.getUserId();
					UserTo userTo=Authentication.findUserInfoByUserId(userId);
					userTos.add(userTo);
				}
				else{
					if(identityLink.getGroupId()!=null){
						
						GroupDefinition groupDefinition=Authentication.groupDefinition(identityLink.getGroupType());
						
						List<UserTo> userToTemp= groupDefinition.findUserChildMembersIncludeByGroupId(identityLink.getGroupId());
						
						userTos.addAll(userToTemp);
						
						
					}
				}
				
			}
		}
		
		
		
		return userTos;

	}

}
