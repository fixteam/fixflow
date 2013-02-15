package com.founder.fix.fixflow.core.impl.command;

public class TransferTaskCommand extends BaseTaskCommand {

	/**
	 * 转发的用户编号
	 */
	protected String transferUserId;
	
	public TransferTaskCommand()
	{
		
	}

	public TransferTaskCommand(BaseTaskCommand taskCommand){
		super(taskCommand);
	}
	
	public String getTransferUserId() {
		return transferUserId;
	}

	public void setTransferUserId(String transferUserId) {
		this.transferUserId = transferUserId;
	}
}
