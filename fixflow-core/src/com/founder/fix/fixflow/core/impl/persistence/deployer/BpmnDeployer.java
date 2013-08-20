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
package com.founder.fix.fixflow.core.impl.persistence.deployer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.di.BpmnDiPackage;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.dd.dc.DcPackage;
import org.eclipse.dd.di.DiPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.DefinitionsBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.db.DbSqlSession;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentEntity;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

/**
 * @author kenshins
 */
public class BpmnDeployer implements Deployer {

	private static final Logger LOG = Logger.getLogger(BpmnDeployer.class.getName());;

	public static final String BPMN_RESOURCE_SUFFIX = "bpmn";

	public static final String DIAGRAM_SUFFIXES = "png";

	public void deploy(DeploymentEntity deployment) {

		Map<String, ResourceEntity> resources = deployment.getResources();

		//if (resources.keySet().size() != 2) {
		//	throw new FixFlowException("发布的流程资源文件必须含有.bpmn和.png文件");
		//}
		ResourceEntity resourceBpmn = null;
		ResourceEntity resourcePng = null;

		for (String resourceName : resources.keySet()) {
			LOG.info("资源 " + resourceName + " 发布!");
			if (resourceName.toLowerCase().endsWith(BPMN_RESOURCE_SUFFIX)) {
				resourceBpmn = resources.get(resourceName);
			} else {
				if (resourceName.toLowerCase().endsWith(DIAGRAM_SUFFIXES)) {
					resourcePng = resources.get(resourceName);
				}
			}
		}

		if (resourceBpmn == null) {
			throw new FixFlowException("发布的流程资源文件必须含有.bpmn");
		}

		byte[] bytes = resourceBpmn.getBytes();
		ResourceSet resourceSet = getResourceSet();

		String filePath = this.getClass().getClassLoader().getResource("com/founder/fix/fixflow/expand/config/fixflowfile.bpmn").toString();
		Resource ddddResource = null;
		if (!filePath.startsWith("jar")) {
			try {
				filePath = java.net.URLDecoder.decode(ReflectUtil.getResource("com/founder/fix/fixflow/expand/config/fixflowfile.bpmn").getFile(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				throw new FixFlowException("流程定义文件加载失败！", e);
			}
			ddddResource = resourceSet.createResource(URI.createFileURI(filePath));
		} else {
			ddddResource = resourceSet.createResource(URI.createURI(filePath));
		}

		try {
			ddddResource.load(new ByteArrayInputStream(bytes), null);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new FixFlowException("定义文件加载失败!", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new FixFlowException("定义文件加载失败!", e);
		}

		DefinitionsBehavior definitions = (DefinitionsBehavior) ddddResource.getContents().get(0).eContents().get(0);

		ProcessDefinitionBehavior process = null;
		for (RootElement rootElement : definitions.getRootElements()) {
			if (rootElement instanceof ProcessDefinitionBehavior) {

				// ProcessDefinitionBehavior
				// processObj=(ProcessDefinitionBehavior)rootElement;
				// if(processObj.getProcessDefinitionKey().equals("")){
				process = (ProcessDefinitionBehavior) rootElement;
				break;
				// }

			}
		}

		process.setDefinitions(definitions);

		process.setResourceName(resourceBpmn.getName());
		process.setResourceId(resourceBpmn.getId());
		if(resourcePng!=null){
			String diagramResourceName = StringUtil.getString(resourcePng.getName());
			process.setDiagramResourceName(diagramResourceName);
		}
		

		CommandContext commandContext = Context.getCommandContext();
		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();

		int processDefinitionVersion;

		ProcessDefinitionBehavior latestProcessDefinition = processDefinitionManager.findLatestProcessDefinitionByKey(process.getProcessDefinitionKey());
		if (latestProcessDefinition != null) {
			processDefinitionVersion = latestProcessDefinition.getVersion() + 1;
		} else {
			processDefinitionVersion = 1;
		}
		
		
		if(deployment.getUpdateDeploymentId()!=null&&!deployment.getUpdateDeploymentId().equals("")){
			
			String deploymentId = deployment.getId();
			process.setDeploymentId(deploymentId);
			ProcessDefinitionBehavior persistedProcessDefinition = processDefinitionManager.findProcessDefinitionByDeploymentAndKey(
					deploymentId, process.getProcessDefinitionKey());
			//process.setId(persistedProcessDefinition.getId());
			process.setVersion(persistedProcessDefinition.getVersion());
			process.setProcessDefinitionId(persistedProcessDefinition.getProcessDefinitionId());
			Context.getProcessEngineConfiguration().getDeploymentCache().removeProcessDefinition(process.getProcessDefinitionId());
			
		}else{
			

			process.setVersion(processDefinitionVersion);
			process.setDeploymentId(deployment.getId());

			String processDefinitionId = process.getProcessDefinitionKey() + ":" + process.getVersion() + ":" + GuidUtil.CreateGuid(); // GUID

			process.setProcessDefinitionId(processDefinitionId);

			dbSqlSession.insert("insertProcessDefinition", process);
		}
		
		
		//Context.getProcessEngineConfiguration().getDeploymentCache().removeProcessDefinition(processDefinitionId);
		//Context.getProcessEngineConfiguration().getDeploymentCache().addProcessDefinition(process);

	}
	
	private ResourceSet getResourceSet() {
		// TODO Auto-generated method stub
		ResourceSet resourceSet = new ResourceSetImpl();
		(EPackage.Registry.INSTANCE).put("http://www.omg.org/spec/BPMN/20100524/MODEL", Bpmn2Package.eINSTANCE);
		(EPackage.Registry.INSTANCE).put("http://www.founderfix.com/fixflow", FixFlowPackage.eINSTANCE);
		(EPackage.Registry.INSTANCE).put("http://www.omg.org/spec/DD/20100524/DI", DiPackage.eINSTANCE);
		(EPackage.Registry.INSTANCE).put("http://www.omg.org/spec/DD/20100524/DC", DcPackage.eINSTANCE);
		(EPackage.Registry.INSTANCE).put("http://www.omg.org/spec/BPMN/20100524/DI", BpmnDiPackage.eINSTANCE);
		FixFlowPackage.eINSTANCE.eClass();

		FixFlowPackage xxxPackage = FixFlowPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.put(xxxPackage.getNsURI(), xxxPackage);
		Bpmn2ResourceFactoryImpl ddd = new Bpmn2ResourceFactoryImpl();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fixflow", ddd);

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("bpmn", ddd);

		resourceSet.getPackageRegistry().put(xxxPackage.getNsURI(), xxxPackage);

		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("bpmn", ddd);
		
		return resourceSet;
	}

	protected void createResource(String name, byte[] bytes, DeploymentEntity deploymentEntity) {
		ResourceEntity resource = new ResourceEntity();
		resource.setName(name);
		resource.setBytes(bytes);
		resource.setDeploymentId(deploymentEntity.getId());

		Context.getCommandContext().getDbSqlSession().insert("insertResource", resource);
	}

}
