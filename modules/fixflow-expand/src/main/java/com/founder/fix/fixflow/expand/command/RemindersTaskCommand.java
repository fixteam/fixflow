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
