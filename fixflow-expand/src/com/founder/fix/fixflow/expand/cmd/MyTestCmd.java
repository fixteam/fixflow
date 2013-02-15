package com.founder.fix.fixflow.expand.cmd;

import java.util.Map;

import com.founder.fix.fixflow.core.impl.cmd.AbstractCommand;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class MyTestCmd extends AbstractCommand<String> {

	public MyTestCmd(Map<String, Object> parameterMap) {
		super(parameterMap);
		// TODO 自动生成的构造函数存根
	}

	public String execute(CommandContext commandContext) {
		// TODO 自动生成的方法存根
		System.out.print("jiangnan");
		return null;
	}

}
