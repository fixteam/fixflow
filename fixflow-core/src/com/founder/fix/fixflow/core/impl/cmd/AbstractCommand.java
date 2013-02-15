package com.founder.fix.fixflow.core.impl.cmd;

import java.util.Map;

import com.founder.fix.fixflow.core.impl.interceptor.Command;

public abstract class AbstractCommand<T> implements Command<T>{
	
	public AbstractCommand(Map<String, Object> parameterMap) {
	}

}
