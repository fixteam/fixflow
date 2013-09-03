/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl.command;

import com.founder.fix.fixflow.core.command.CommandParams;

/**
 * 任务处理命令参数基类
 * @author kenshin
 *
 */
public class BaseTaskCommand extends CommandParams{
	
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
	 * 外出代理人
	 */
	protected String agent;
	
	/**
	 * 管理员处理
	 */
	protected String admin;

	
	


	/**
	 * 处理者
	 */
	protected String handler;
	
	/**
	 * 获取当前对任务的处理者
	 * @return
	 */
	public String getHandler() {
		return handler;
	}

	/**
	 * 设置当前的任务处理者
	 * @param handler 任务处理者编号
	 */
	public void setHandler(String handler) {
		this.handler = handler;
	}

	/**
	 * 获取外出代理人
	 * @return
	 */
	public String getAgent() {
		return agent;
	}

	/**
	 * 设置外出代理人
	 * @param agent 外出代理人编号
	 */
	public void setAgent(String agent) {
		this.agent = agent;
	}

	/**
	 * 获取任务编号
	 * @return
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * 设置任务编号
	 * @param taskId
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}
	

	/**
	 * 获取任务意见
	 * @return
	 */
	public String getTaskComment() {
		return taskComment;
	}

	/**
	 * 设置任务意见
	 * @param taskComment
	 */
	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}

	
	/**
	 * 获取用户命令编号
	 * @return
	 */
	public String getUserCommandId() {
		return userCommandId;
	}

	/**
	 * 设置用户命令编号
	 * @param userCommandId
	 */
	public void setUserCommandId(String userCommandId) {
		this.userCommandId = userCommandId;
	}
	
	/**
	 * 获取管理员处理
	 * @return
	 */
	public String getAdmin() {
		return admin;
	}

	/**
	 * 设置管理员处理
	 * @param admin 管理员编号
	 */
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	
	public BaseTaskCommand(BaseTaskCommand taskCommand){
		this.taskId = taskCommand.taskId;
		this.userCommandId = taskCommand.userCommandId;
		this.variables = taskCommand.variables;
		this.transientVariables = taskCommand.transientVariables;
		this.taskComment=taskCommand.taskComment;
		this.commandType=taskCommand.commandType;
		this.agent=taskCommand.agent;
		this.handler = taskCommand.handler;
		this.admin=taskCommand.getAdmin();
	}
	
	
	public BaseTaskCommand ()
	{
		
	}
	

}
