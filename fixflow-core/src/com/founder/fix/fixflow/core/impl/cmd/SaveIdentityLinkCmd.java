package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.task.IdentityLink;


public class SaveIdentityLinkCmd implements Command<Void>{


	protected IdentityLink identityLink;
	
	public SaveIdentityLinkCmd(IdentityLink identityLink){
		this.identityLink=identityLink;
	
	}
	
	public Void execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		
		if(identityLink.getUserId()!=null&&identityLink.getGroupId()!=null){
			throw new FixFlowException("userId、groupTo 不能同时存在");
		}

		

		
		commandContext.getIdentityLinkManager().saveIdentityLink(identityLink);
		
		return null;
	}

}
