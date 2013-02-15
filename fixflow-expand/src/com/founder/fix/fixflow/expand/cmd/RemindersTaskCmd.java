package com.founder.fix.fixflow.expand.cmd;



import java.util.List;
import java.util.Map;

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
		// TODO 自动生成的方法存根
		
		System.out.print("usersInfo: "+usersInfo+"\n title: "+title+"\n content: "+content);
		
		
		return null;
	}

}
