package com.founder.fix.fixflow.core.impl.cmd;


import java.util.Map;

import com.founder.fix.fixflow.core.impl.command.AbstractCustomExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.interceptor.Command;

public abstract class AbstractExpandTaskCmd<P extends AbstractCustomExpandTaskCommand,T>  implements Command<T>{

	/**
	 * 任务编号
	 */
	protected String taskId;
	
	/**
	 * 任务命令类型
	 */
	protected String commandType;
	
	/**
	 * 用户命令编号
	 */
	protected String userCommandId;
	
	/**
	 * 任务意见
	 */
	protected String taskComment;
	
	/**
	 * 瞬态流程实例变量Map
	 */
	protected Map<String, Object> transientVariables = null;

	/**
	 * 持久化流程实例变量Map
	 */
	protected Map<String, Object> variables = null;
	
	protected ExpandTaskCommand expandTaskCommand=null;
	
	
	protected String businessKey;
	
	protected String initiator;
	
	protected String processDefinitionKey;
	
	protected String agent;
	
	protected String admin;
	
	public AbstractExpandTaskCmd (P abstractCustomExpandTaskCommand){
		

		
		this.taskId=abstractCustomExpandTaskCommand.getExpandTaskCommand().getTaskId();
		this.commandType=abstractCustomExpandTaskCommand.getExpandTaskCommand().getCommandType();
		this.userCommandId=abstractCustomExpandTaskCommand.getExpandTaskCommand().getUserCommandId();
		this.taskComment=abstractCustomExpandTaskCommand.getExpandTaskCommand().getTaskComment();
		this.transientVariables = abstractCustomExpandTaskCommand.getExpandTaskCommand().getTransientVariables();
		this.variables = abstractCustomExpandTaskCommand.getExpandTaskCommand().getVariables();
		this.expandTaskCommand=abstractCustomExpandTaskCommand.getExpandTaskCommand();
		
		this.initiator=abstractCustomExpandTaskCommand.getExpandTaskCommand().getInitiator();
		this.processDefinitionKey=abstractCustomExpandTaskCommand.getExpandTaskCommand().getProcessDefinitionKey();
		this.businessKey=abstractCustomExpandTaskCommand.getExpandTaskCommand().getBusinessKey();
		this.agent=abstractCustomExpandTaskCommand.getExpandTaskCommand().getAgent();
		this.admin=abstractCustomExpandTaskCommand.getExpandTaskCommand().getAdmin();
	}
}
