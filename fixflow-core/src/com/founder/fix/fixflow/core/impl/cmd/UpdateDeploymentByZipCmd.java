package com.founder.fix.fixflow.core.impl.cmd;

import java.util.zip.ZipInputStream;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.model.DeploymentBuilder;

public class UpdateDeploymentByZipCmd implements Command<String> {

	protected ZipInputStream zipInputStream;
	protected DeploymentBuilder deploymentBuilder;
	protected String deploymentId;
	public UpdateDeploymentByZipCmd(DeploymentBuilder deploymentBuilder,ZipInputStream zipInputStream,String deploymentId){
		this.zipInputStream = zipInputStream;
		this.deploymentBuilder = deploymentBuilder;
		this.deploymentId = deploymentId;
	}
	public String execute(CommandContext commandContext) {
		//发布流程定义
		deploymentBuilder.addZipInputStream(zipInputStream);
		//设置需要更新的发布号
		deploymentBuilder.updateDeploymentId(deploymentId);
		//更新流程定义
		return deploymentBuilder.deploy().getId();
	}

}
