package com.founder.fix.fixflow.core.impl.cmd;


import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class DeleteIdentityLinkCmd implements Command<Void> {

	protected String linkId;
	
	
	public DeleteIdentityLinkCmd(String linkId){
		this.linkId=linkId;
	}
	
	public Void execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		
		
		
		commandContext.getIdentityLinkManager().deleteIdentityLinkById(linkId);

		return null;
	}

}
