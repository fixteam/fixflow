package com.founder.fix.fixflow.core.impl.interceptor;



/**
 * 
 * 
 * @author kenshin
 */
public interface CommandExecutor {



	<T> T execute(Command<T> command);

}
