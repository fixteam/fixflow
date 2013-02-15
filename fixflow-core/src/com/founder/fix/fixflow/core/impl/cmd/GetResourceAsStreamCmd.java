package com.founder.fix.fixflow.core.impl.cmd;



import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;

public class GetResourceAsStreamCmd<T> implements Command<ResourceEntity>{

	protected String resourceId;
	
	public GetResourceAsStreamCmd(String resourceId)
	{
		this.resourceId=resourceId;
	}
	
	
	public ResourceEntity execute(CommandContext commandContext) {
		// TODO Auto-generated method stub

		return commandContext.getResourceManager().findResourceByResourceId(resourceId);
	}

}
