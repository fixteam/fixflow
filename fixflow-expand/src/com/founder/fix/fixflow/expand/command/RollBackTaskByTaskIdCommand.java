package com.founder.fix.fixflow.expand.command;

import com.founder.fix.fixflow.core.impl.command.AbstractCustomExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class RollBackTaskByTaskIdCommand extends AbstractCustomExpandTaskCommand{

	protected String rollBackTaskId;
	
	public String getRollBackTaskId() {
		return rollBackTaskId;
	}

	public RollBackTaskByTaskIdCommand(ExpandTaskCommand expandTaskCommand) {
		super(expandTaskCommand);
		this.rollBackTaskId=StringUtil.getString(expandTaskCommand.getParamMap().get("rollBackTaskId"));
		// TODO Auto-generated constructor stub
	}

}
