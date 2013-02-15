package com.founder.fix.fixflow.expand.command;

import com.founder.fix.fixflow.core.impl.command.AbstractCustomExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class TransferTaskCommand extends AbstractCustomExpandTaskCommand {
	/**
	 * 转发的用户编号
	 */
	protected String transferUserId;
	
	
	
	public TransferTaskCommand(ExpandTaskCommand expandTaskCommand) {
		super(expandTaskCommand);
		this.transferUserId=StringUtil.getString(expandTaskCommand.getParamMap().get("transferUserId"));
	}
	
	public String getTransferUserId() {
		return transferUserId;
	}
	
	public void setTransferUserId(String transferUserId) {
		this.transferUserId = transferUserId;
	}
}
