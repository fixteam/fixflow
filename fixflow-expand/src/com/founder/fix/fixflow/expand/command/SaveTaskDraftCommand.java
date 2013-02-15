package com.founder.fix.fixflow.expand.command;

import com.founder.fix.fixflow.core.impl.command.AbstractCustomExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class SaveTaskDraftCommand extends AbstractCustomExpandTaskCommand {


	protected String formUri;



	public String getFormUri() {
		return formUri;
	}



	public SaveTaskDraftCommand(ExpandTaskCommand expandTaskCommand) {
		super(expandTaskCommand);
		
		this.formUri=StringUtil.getString(expandTaskCommand.getParamMap().get("formUri"));
	
	}
	
	
	
		

	
	

}
