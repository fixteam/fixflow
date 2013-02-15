package com.founder.fix.fixflow.core.impl.command;

public class RollBackTaskCommand extends BaseTaskCommand {

	/**
	 * 退回到的节点
	 */
	protected String rollBackNodeId;
	
	public RollBackTaskCommand() {

	}
	
	public RollBackTaskCommand(BaseTaskCommand taskCommand){
		super(taskCommand);
	}

	public String getRollBackNodeId() {
		return rollBackNodeId;
	}

	public void setRollBackNodeId(String rollBackNodeId) {
		this.rollBackNodeId = rollBackNodeId;
	}
	
}
