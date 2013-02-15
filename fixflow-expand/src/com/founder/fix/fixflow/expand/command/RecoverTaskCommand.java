package com.founder.fix.fixflow.expand.command;

import com.founder.fix.fixflow.core.impl.command.AbstractCustomExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class RecoverTaskCommand extends AbstractCustomExpandTaskCommand {


	/**
	 * 追回到的任务编号
	 */
	protected String recoverNodeId;
	



	public String getRecoverNodeId() {
		return recoverNodeId;
	}




	public RecoverTaskCommand(ExpandTaskCommand expandTaskCommand) {
		super(expandTaskCommand);
		//this.recoverTaskId=StringUtil.getString(expandTaskCommand.getParamMap().get("recoverTaskId"));
		this.recoverNodeId=StringUtil.getString(expandTaskCommand.getParamMap().get("recoverNodeId"));
		
	}

}
