package com.founder.fix.fixflow.core.impl.cmd;

import java.io.InputStream;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;
import com.founder.fix.fixflow.core.impl.util.IoUtil;

public class UpdateResourceCmd implements Command<Void> {

	protected String resourceId;
	protected InputStream inputStream;

	public UpdateResourceCmd(String resourceId, InputStream inputStream) {
		this.resourceId = resourceId;
		this.inputStream = inputStream;
	}

	public Void execute(CommandContext commandContext) {
		// TODO Auto-generated method stub

		ResourceEntity resourceEntity = new ResourceEntity();
		resourceEntity.setId(resourceId);
		resourceEntity
				.setBytes(IoUtil.readInputStream(inputStream, resourceId));

		commandContext.getResourceManager().updateResource(resourceEntity);
		return null;

	}

}
