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
package com.founder.fix.fixflow.expand.cmd;



import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.expand.command.RemindersTaskCommand;

public class RemindersTaskCmd  extends AbstractExpandTaskCmd<RemindersTaskCommand,Void>{

	
	protected List<Map<String, String>> usersInfo;
	
	protected String title;
	
	protected String content;
	
	
	public RemindersTaskCmd(RemindersTaskCommand remindersTaskCommand) {
		super(remindersTaskCommand);
		this.usersInfo=remindersTaskCommand.getUsersInfo();
		this.content=remindersTaskCommand.getContent();
		this.title=remindersTaskCommand.getTitle();
		
	}

	public Void execute(CommandContext commandContext) {


		//初始化任务命令执行所需要的常用对象
		loadProcessParameter(commandContext);
		
		//将外部变量注册到流程实例运行环境中
		addVariable();
		
		//执行处理命令中的开发人员设置的表达式
		runCommandExpression();
		
		//获取正在操作的任务实例对象
		//TaskInstanceEntity taskInstance=getTaskInstanceEntity();
		
		//获取正在操作的任务命令对象实例
		//TaskCommandInst taskCommand=getTaskCommandInst();
		
		
		System.out.print("usersInfo: "+usersInfo+"\n title: "+title+"\n content: "+content);
		
		saveProcessInstance(commandContext);
		
		throw new FixFlowException("催办任务需用开发人员实现催办逻辑!");
		

	}

}
