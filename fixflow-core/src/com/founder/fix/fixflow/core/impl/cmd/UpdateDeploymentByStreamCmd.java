package com.founder.fix.fixflow.core.impl.cmd;

import java.io.InputStream;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.model.DeploymentBuilder;

public class UpdateDeploymentByStreamCmd implements Command<String> {

	protected Map<String,InputStream> inputStreamMap = null; 
	protected DeploymentBuilder deploymentBuilder = null;
	protected String deploymentId = null;
	public UpdateDeploymentByStreamCmd(DeploymentBuilder deploymentBuilder,Map<String,InputStream> inputStreamMap,String deploymentId) {
		this.deploymentBuilder = deploymentBuilder;
		this.inputStreamMap = inputStreamMap;
		this.deploymentId = deploymentId;
	}
	
	public String execute(CommandContext commandContext) {
		if(inputStreamMap != null){
			for(String key : inputStreamMap.keySet()){
				InputStream tmpInputStream = inputStreamMap.get(key);
				deploymentBuilder.addInputStream(key, tmpInputStream);
			}
		}
		deploymentBuilder.updateDeploymentId(deploymentId);
		return deploymentBuilder.deploy().getId();
	}
}
