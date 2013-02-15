package com.founder.fix.fixflow.core.impl.command;


public abstract class AbstractCustomExpandTaskCommand {
	
	protected ExpandTaskCommand expandTaskCommand;
	
	

	public ExpandTaskCommand getExpandTaskCommand() {
		return expandTaskCommand;
	}



	public AbstractCustomExpandTaskCommand (ExpandTaskCommand expandTaskCommand){
		
		this.expandTaskCommand=expandTaskCommand;
		
	}
	
	
	
}
