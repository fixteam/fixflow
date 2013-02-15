package com.founder.fix.fixflow.core.impl.cmd;


import com.founder.fix.fixflow.core.impl.identity.UserDefinition;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class UserDefinitionInfoCmd implements Command<UserDefinition>  {

	public UserDefinitionInfoCmd(){
		
	}
	
	
	public UserDefinition execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		
		UserDefinition userDefinition=commandContext.getProcessEngineConfigurationImpl().getUserDefinition();
		
		return userDefinition;
	}

}
