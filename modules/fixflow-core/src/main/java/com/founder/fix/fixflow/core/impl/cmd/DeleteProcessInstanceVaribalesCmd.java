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
 * @author yangchenhui
 */
package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.impl.command.QueryVariablesCommand;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class DeleteProcessInstanceVaribalesCmd implements Command<Void> {

	protected QueryVariablesCommand queryVariablesCommand;
	public DeleteProcessInstanceVaribalesCmd(QueryVariablesCommand queryVariablesCommand) {
		// TODO 自动生成的构造函数存根
		this.queryVariablesCommand = queryVariablesCommand;
	}
	public Void execute(CommandContext commandContext) {
		commandContext.getVariableManager().deleteVariable(queryVariablesCommand);
		return null;
	}
	

}
