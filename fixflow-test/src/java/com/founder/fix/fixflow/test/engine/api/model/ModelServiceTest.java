package com.founder.fix.fixflow.test.engine.api.model;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.model.DeploymentBuilder;
import com.founder.fix.fixflow.core.model.ProcessDefinitionQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class ModelServiceTest extends AbstractFixFlowTestCase {
	
	/**
	 * 测试流程定义查询
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn" })
	public void testProcessDefinitionQuery() {
		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		assertNotNull(processDefinitionBehavior);
	}

	
	
	/**
	 * 测试流程定义发布
	 */
	public void testDeployProcessDefinition() {

		DeploymentBuilder deploymentBuilder = processEngine.getModelService().createDeployment().name("测试名称");
		//添加你要发布的定义
		deploymentBuilder.addClasspathResource("com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn");
		//发布
		String deploymentIdTemp = deploymentBuilder.deploy().getId();
		assertNotNull(deploymentIdTemp);

		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		assertNotNull(processDefinitionBehavior);
		
	}
	
	
	/**
	 * 获取流程图SVG格式
	 */
	public void testGetFlowGraphicsSvg() {

		DeploymentBuilder deploymentBuilder = modelService.createDeployment().name("测试名称");
		//添加你要发布的定义
		deploymentBuilder.addClasspathResource("com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn");
		//发布
		String deploymentIdTemp = deploymentBuilder.deploy().getId();
		assertNotNull(deploymentIdTemp);

		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		assertNotNull(processDefinitionBehavior);
		
		//根据流程定义唯一编号获取流程图SVG
		String svgString=modelService.getFlowGraphicsSvg(processDefinitionBehavior.getProcessDefinitionId());
		assertNotNull(svgString);
		
	}
	
	

}
