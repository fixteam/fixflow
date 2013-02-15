package com.founder.fix.fixflow.expand.command;

import com.founder.fix.fixflow.core.impl.command.AbstractCustomExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class PendingTaskCommand extends AbstractCustomExpandTaskCommand{

	
	/**
	 * 转办的用户编号
	 */
	protected String pendingUserId;
	
	
	public String getPendingUserId() {
		return pendingUserId;
	}


	public void setPendingUserId(String pendingUserId) {
		this.pendingUserId = pendingUserId;
	}


	public PendingTaskCommand(ExpandTaskCommand expandTaskCommand) {
		super(expandTaskCommand);
		// TODO Auto-generated constructor stub
		this.pendingUserId=StringUtil.getString(expandTaskCommand.getParamMap().get("pendingUserId"));
	}
	
	
	
	


}
