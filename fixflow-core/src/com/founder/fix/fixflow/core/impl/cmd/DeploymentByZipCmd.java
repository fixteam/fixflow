package com.founder.fix.fixflow.core.impl.cmd;

import java.util.zip.ZipInputStream;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.model.DeploymentBuilder;

public class DeploymentByZipCmd implements Command<String> {

	protected ZipInputStream zipInputStream;
	protected DeploymentBuilder deploymentBuilder;
	public DeploymentByZipCmd(DeploymentBuilder deploymentBuilder,ZipInputStream zipInputStream){
		this.zipInputStream = zipInputStream;
		this.deploymentBuilder = deploymentBuilder;
	}
	public String execute(CommandContext commandContext) {
		if(zipInputStream == null){
			return null;
		}
		deploymentBuilder.addZipInputStream(zipInputStream);
		return deploymentBuilder.deploy().getId();
	}

}
