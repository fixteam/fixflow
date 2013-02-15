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
