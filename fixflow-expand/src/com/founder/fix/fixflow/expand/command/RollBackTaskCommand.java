package com.founder.fix.fixflow.expand.command;

import com.founder.fix.fixflow.core.impl.command.AbstractCustomExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class RollBackTaskCommand extends AbstractCustomExpandTaskCommand{
	
	/**
	 * 退回到的节点
	 */
	protected String rollBackNodeId;
	
	
	public RollBackTaskCommand(ExpandTaskCommand expandTaskCommand) {
		super(expandTaskCommand);
		this.rollBackNodeId=StringUtil.getString(expandTaskCommand.getParamMap().get("rollBackNodeId"));
	}

	
	
	public String getRollBackNodeId() {
		return rollBackNodeId;
	}

	public void setRollBackNodeId(String rollBackNodeId) {
		this.rollBackNodeId = rollBackNodeId;
	}
}
