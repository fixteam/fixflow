package com.founder.fix.fixflow.expand.command;

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.command.AbstractCustomExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class RemindersTaskCommand extends AbstractCustomExpandTaskCommand{
	
	protected List<Map<String, String>> usersInfo;
	protected String title;
	
	
	protected String content;
	
	
	
	
	public String getContent() {
		return content;
	}

	public List<Map<String, String>> getUsersInfo() {
		return usersInfo;
	}
	
	public String getTitle() {
		return title;
	}

	@SuppressWarnings("unchecked")
	public RemindersTaskCommand(ExpandTaskCommand expandTaskCommand) {
		super(expandTaskCommand);
		
		this.usersInfo=(List<Map<String,String>>)(expandTaskCommand.getParamMap().get("usersInfo"));
		this.content=StringUtil.getString(expandTaskCommand.getParamMap().get("content"));
		this.title=StringUtil.getString(expandTaskCommand.getParamMap().get("title"));
		// TODO 自动生成的构造函数存根
	}

}
