package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;


public class GroupDefinitionInfoCmd implements Command<GroupDefinition> {

	protected String groupType;

	public GroupDefinitionInfoCmd(String groupType) {
		this.groupType = groupType;
	}

	public GroupDefinition execute(CommandContext commandContext) {

		List<GroupDefinition> groupDefinitions = Context.getProcessEngineConfiguration().getGroupDefinitions();
		for (GroupDefinition groupDefinition : groupDefinitions) {

			if (groupDefinition.getId().equals(this.groupType)) {
				return groupDefinition;
			}

		}
		return null;
	}

}
