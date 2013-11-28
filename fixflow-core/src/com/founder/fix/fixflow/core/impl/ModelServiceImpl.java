/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import com.founder.fix.fixflow.core.ModelService;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.cmd.DeleteDeploymentCmd;
import com.founder.fix.fixflow.core.impl.cmd.DeployCmd;
import com.founder.fix.fixflow.core.impl.cmd.DeploymentByZipCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetDefaultFromUriCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetDeploymentEntityCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetFlowGraphicsElementPositionCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetFlowGraphicsImgPathCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetFlowGraphicsImgStreamCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetFlowGraphicsSvgCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetModelInternationalizationResourcesCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetProcessDefinitionCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetProcessDefinitionGroupKeyCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetResourceAsStreamCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetStartProcessByUserIdCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetUserSubmitProcess;
import com.founder.fix.fixflow.core.impl.cmd.UpdateDeploymentByZipCmd;
import com.founder.fix.fixflow.core.impl.cmd.UpdateResourceCmd;
import com.founder.fix.fixflow.core.impl.model.DeploymentBuilderImpl;
import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentEntity;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.core.internationalization.FixFlowResources;
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

	
	public ResourceEntity getResourceAsStream(String resourceId) {
	
		return commandExecutor.execute(new GetResourceAsStreamCmd<ResourceEntity>(resourceId));

	}
	
	public DeploymentEntity getDeploymentEntity(String deploymentId) {
		return commandExecutor.execute(new GetDeploymentEntityCmd(deploymentId));
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

	
	public String getFlowGraphicsSvg(String processDefinitionId) {
		return commandExecutor.execute(new GetFlowGraphicsSvgCmd(processDefinitionId,null));
	}
	
	public String getFlowGraphicsSvgByDefKey(String processDefinitionKey) {
		return commandExecutor.execute(new GetFlowGraphicsSvgCmd(null,processDefinitionKey));
	}

	
	public String GetFlowGraphicsImgPath(String processDefinitionId) {

		return commandExecutor.execute(new GetFlowGraphicsImgPathCmd(processDefinitionId));
	}

	public Map<String, Map<String, Object>> GetFlowGraphicsElementPosition(String processDefinitionId) {

		return commandExecutor.execute(new GetFlowGraphicsElementPositionCmd(processDefinitionId,null));
	}
	
	public Map<String, Map<String, Object>> GetFlowGraphicsElementPositionByKey(String processDefinitionKey) {

		return commandExecutor.execute(new GetFlowGraphicsElementPositionCmd(null,processDefinitionKey));
	}

	public String getModelInternationalizationResources(String resourcesType, String resourceKey) {
		return commandExecutor.execute(new GetModelInternationalizationResourcesCmd(resourcesType,resourceKey));
	}

	public String getFlowNameInternationalizationResources(String resourceKey) {
		return commandExecutor.execute(new GetModelInternationalizationResourcesCmd(FixFlowResources.FlowNameResource,resourceKey));
	}

	public List<Map<String, String>> getStartProcessByUserId(String userId) {
		return commandExecutor.execute(new GetStartProcessByUserIdCmd(userId));
	}

	public InputStream GetFlowGraphicsImgStreamByDefId(String processDefinitionId) {
		return commandExecutor.execute(new GetFlowGraphicsImgStreamCmd(processDefinitionId,null));
	}

	public InputStream GetFlowGraphicsImgStreamByDefKey(String processDefinitionKey) {
		return commandExecutor.execute(new GetFlowGraphicsImgStreamCmd(null,processDefinitionKey));
	}
	
	public String deploymentByZip(String path){
		InputStream inputStream = ReflectUtil.getResourceAsStream(path);
		if(inputStream == null){
			return null;
		}
		return deploymentByZip(new ZipInputStream(inputStream));
	}
	
	public String deploymentByZip(ZipInputStream zipInputStream){
		return commandExecutor.execute(new DeploymentByZipCmd(createDeployment(),zipInputStream));
	}
	
	public String updateDeploymentByZip(ZipInputStream zipInputStream,String deploymentId){
		return commandExecutor.execute(new UpdateDeploymentByZipCmd(createDeployment(),zipInputStream,deploymentId));
	}
	
	public String updateDeploymentByZip(String path,String deploymentId){
		InputStream inputStream = ReflectUtil.getResourceAsStream(path);
		if(inputStream == null){
			return null;
		}
		return updateDeploymentByZip(new ZipInputStream(inputStream),deploymentId);
	}

	public List<Map<String, String>> getUserSubmitProcess(String userId, int number) {

		return commandExecutor.execute(new GetUserSubmitProcess(userId,number));
	}
}
