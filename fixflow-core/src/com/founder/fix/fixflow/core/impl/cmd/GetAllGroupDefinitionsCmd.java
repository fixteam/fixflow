package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class GetAllGroupDefinitionsCmd implements Command<List<GroupDefinition>>{

	public List<GroupDefinition> execute(CommandContext commandContext) {
		List<GroupDefinition> groupDefinitions = Context.getProcessEngineConfiguration().getGroupDefinitions();
		return groupDefinitions;
	}
}
