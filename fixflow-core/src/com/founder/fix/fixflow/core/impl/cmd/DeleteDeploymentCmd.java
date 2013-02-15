package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class DeleteDeploymentCmd implements Command<Void> {

	protected String deploymentId;
	protected boolean cascade;

	public DeleteDeploymentCmd(String deploymentId, boolean cascade) {
		this.deploymentId = deploymentId;
		this.cascade = cascade;
	}

	public Void execute(CommandContext commandContext) {
		if (deploymentId == null) {
			throw new FixFlowException("deploymentId 不能为空");
		}

		commandContext.getDeploymentManager().deleteDeployment(deploymentId,
				cascade);

		return null;
	}

}
