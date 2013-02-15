package com.founder.fix.fixflow.core.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.ModelService;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.cmd.DeleteDeploymentCmd;
import com.founder.fix.fixflow.core.impl.cmd.DeployCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetDefaultFromUriCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetFlowGraphicsSvgCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetProcessDefinitionCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetProcessDefinitionGroupKeyCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetResourceAsStreamCmd;
import com.founder.fix.fixflow.core.impl.cmd.UpdateResourceCmd;
import com.founder.fix.fixflow.core.impl.model.DeploymentBuilderImpl;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;
import com.founder.fix.fixflow.core.model.Deployment;
import com.founder.fix.fixflow.core.model.DeploymentBuilder;
import com.founder.fix.fixflow.core.model.DeploymentQuery;
import com.founder.fix.fixflow.core.model.ProcessDefinitionQuery;

public class ModelServiceImpl extends ServiceImpl implements ModelService {

	public DeploymentBuilder createDeployment() {
		return new DeploymentBuilderImpl(this);
	}

	public Deployment deploy(DeploymentBuilderImpl deploymentBuilder) {
		return commandExecutor.execute(new DeployCmd<Deployment>(deploymentBuilder));
	}

	public void deleteDeployment(String deploymentId) {
		commandExecutor.execute(new DeleteDeploymentCmd(deploymentId, false));
	}

	public void deleteDeployment(String deploymentId, boolean cascade) {
		commandExecutor.execute(new DeleteDeploymentCmd(deploymentId, cascade));
	}

	public List<String> getDeploymentResourceNames(String deploymentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public InputStream getResourceAsStream(String deploymentId, String resourceName) {
		// TODO Auto-generated method stub
		return null;
	}

	public ProcessDefinitionQuery createProcessDefinitionQuery() {

		return new ProcessDefinitionQueryImpl(commandExecutor);
	}

	public DeploymentQuery createDeploymentQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFlowGraphicsSvg(String processDefinitionId) {
		return commandExecutor.execute(new GetFlowGraphicsSvgCmd(processDefinitionId));
	}

	public ResourceEntity getResourceAsStream(String resourceId) {
	
		return commandExecutor.execute(new GetResourceAsStreamCmd<ResourceEntity>(resourceId));

	}

	public void updateResource(String resourceId, InputStream inputStream) {

		commandExecutor.execute(new UpdateResourceCmd(resourceId, inputStream));

	}

	public String getDefaultFromUri(String processDefinitionId) {

		
	
		return commandExecutor.execute(new GetDefaultFromUriCmd(processDefinitionId));

	}

	public ProcessDefinitionBehavior getProcessDefinition(String processDefinitionId) {

		return commandExecutor.execute(new GetProcessDefinitionCmd(processDefinitionId));
	
	}

	public List<Map<String, Object>> selectProcessDefinitionGroupKey() {
		
		return commandExecutor.execute(new GetProcessDefinitionGroupKeyCmd());
	}

}
