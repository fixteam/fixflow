package com.founder.fix.fixflow.core.impl.interceptor;


import com.founder.fix.fixflow.core.impl.Context;


/**
 * @author kenshin
 */
public class CommandExecutorImpl implements CommandExecutor {


	public <T> T execute(Command<T> command) {
		return command.execute(Context.getCommandContext());
	}






}
