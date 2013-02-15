package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;


public class GetGroupInfoByGroupIdCmd implements Command<GroupTo> {



	protected String groupId;
	
	protected GroupDefinition groupDefinition;
	
	public GetGroupInfoByGroupIdCmd(String groupId, GroupDefinition groupDefinition) {
		this.groupId=groupId;
		this.groupDefinition=groupDefinition;
	}
	
	

	public GroupTo execute(CommandContext commandContext) {
		return this.groupDefinition.findGroupByGroupId(groupId);
	}

}
