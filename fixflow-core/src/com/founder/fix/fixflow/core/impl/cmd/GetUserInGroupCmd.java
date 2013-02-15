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
