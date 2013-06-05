package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class ExecuteRuleScriptCmd<T> implements Command<T> {

	protected String ruleScript;
	
	public ExecuteRuleScriptCmd(String ruleScript){
		this.ruleScript=ruleScript;
	}
	
	
	@SuppressWarnings("unchecked")
	public T execute(CommandContext commandContext) {

		
		return (T)Context.getAbstractScriptLanguageMgmt().execute(ruleScript);
	}
	
	

}
