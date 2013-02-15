package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class UpdateBusinessKeyCmd implements Command<Void>{

	
	
	/**
	 * 流程实例
	 */
	protected String processInstanceId;
	
	/**
	 * 业务关联键
	 */
	protected String businessKey;
	
	
	
	
	public  UpdateBusinessKeyCmd(String processInstanceId,String businessKey)
	{
		
	}
	
	public Void execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
