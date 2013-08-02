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
 * @author yangchenhui
 */
package com.founder.fix.fixflow.test.engine.api.model;

import java.util.List;
import java.util.Map;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;
import com.founder.fix.fixflow.core.model.DeploymentBuilder;
import com.founder.fix.fixflow.core.model.ProcessDefinitionQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

/**
 * ModelService的测试类
 */
public class ModelServiceTest extends AbstractFixFlowTestCase {
	
	/**
	 * 测试流程定义查询
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn" })
	public void testProcessDefinitionQuery() {
		//创建一个查询
		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		//因为方法上面的声明会自动发布流程，所以此刻可以查到这个流程信息
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否查到此流程发布的信息
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
		//创建一个查询
		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		//查询上面发布的流程
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否发布成功
		assertNotNull(processDefinitionBehavior);
		
	}
	/**
	 * 测试删除流程删除定义部署 ---方法未实现
	 */
	public void testDeleteDeployment() {

		DeploymentBuilder deploymentBuilder = processEngine.getModelService().createDeployment().name("测试名称");
		//添加你要发布的定义
		deploymentBuilder.addClasspathResource("com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn");
		//发布
		String deploymentIdTemp = deploymentBuilder.deploy().getId();
		assertNotNull(deploymentIdTemp);
		
		//删除定义部署，并级联删除其下的所有流程定义
		modelService.deleteDeployment(deploymentIdTemp, true);
		
//		//创建一个定义部署的查询 ----方法未实现
//		DeploymentQuery deploymentQuery = modelService.createDeploymentQuery();
//		//根据id找到定义部署
//		com.founder.fix.fixflow.core.model.Deployment deployment = deploymentQuery.deploymentId(deploymentIdTemp).singleResult();
//		//验证定义部署已经被删除
//		assertNull(deployment);
		//创建一个流程定义查询
		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		//查询上面发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证流程定义已经被删除
		assertNull(processDefinitionBehavior);
		
	}
	/**
	 * 测试检索一个指定的定义部署清单资源---方法未实现
	 */
	public void testGetDeploymentResourceNames(){
		DeploymentBuilder deploymentBuilder = processEngine.getModelService().createDeployment().name("测试名称");
		//添加你要发布的定义
		deploymentBuilder.addClasspathResource("com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn");
		//发布
		String deploymentIdTemp = deploymentBuilder.deploy().getId();
		assertNotNull(deploymentIdTemp);
		List<String> list = modelService.getDeploymentResourceNames(deploymentIdTemp);
		//System.out.println(list.size()+"***************");
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
		//创建一个流程定义查询
		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		//查询刚刚发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否发布成功
		assertNotNull(processDefinitionBehavior);
		
		//根据流程定义唯一编号获取流程图SVG
		String svgString=modelService.getFlowGraphicsSvg(processDefinitionBehavior.getProcessDefinitionId());
		//验证是否得到
		assertNotNull(svgString);
		
	}
	/**
	 * 获取流程的默认表单
	 */
	public void testGetDefaultFormUri(){
		DeploymentBuilder deploymentBuilder = modelService.createDeployment().name("测试名称");
		//添加你要发布的定义
		deploymentBuilder.addClasspathResource("com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn");
		//发布
		String deploymentIdTemp = deploymentBuilder.deploy().getId();
		assertNotNull(deploymentIdTemp);
		//创建一个流程定义查询
		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		//查询Process_TaskServiceTest的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		//获取流程定义
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否为空
		assertNotNull(processDefinitionBehavior);
		
		//根据流程定义唯一编号获取流程默认的表单formUri
		String formUri=modelService.getDefaultFromUri(processDefinitionBehavior.getProcessDefinitionId());
		//验证是否正确得到流程默认表单
		assertEquals("aaa.html", formUri);
	}
	/**
	 * 获取流程图IMG路径
	 */
	public void testGetFlowGraphicsImgPath(){
		DeploymentBuilder deploymentBuilder = modelService.createDeployment().name("测试名称");
		//添加你要发布的定义
		deploymentBuilder.addClasspathResource("com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn");
		//发布
		String deploymentIdTemp = deploymentBuilder.deploy().getId();
		//验证是否发布成功
		assertNotNull(deploymentIdTemp);
		//创建一个流程定义查询
		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		//查询刚发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否得到
		assertNotNull(processDefinitionBehavior);
		
		//根据流程定义唯一编号获取流程图img
		String imgPath=modelService.GetFlowGraphicsImgPath(processDefinitionBehavior.getProcessDefinitionId());
		//验证是否获得img的path
		assertNotNull(imgPath);
	}
	/**
	 * 通过字节流访问定义部署资源。
	 */
	public void testGetResourceAsStream(){
		DeploymentBuilder deploymentBuilder = modelService.createDeployment().name("测试名称");
		//添加你要发布的定义
		deploymentBuilder.addClasspathResource("com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn");
		//发布
		String deploymentIdTemp = deploymentBuilder.deploy().getId();
		//验证是否发布成功
		assertNotNull(deploymentIdTemp);
		//定义一个流程定义查询
		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		//查询刚发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否得到流程定义
		assertNotNull(processDefinitionBehavior);
		
		//定义部署资源的字节流
		ResourceEntity resourceEntity = modelService.getResourceAsStream(processDefinitionBehavior.getResourceId());
		//验证是否得到定义部署资源的字节流
		assertNotNull(resourceEntity);
	}
	/**
	 * 获取流程定义
	 */
	public void testGetProcessDefinition(){
		DeploymentBuilder deploymentBuilder = modelService.createDeployment().name("测试名称");
		//添加你要发布的定义
		deploymentBuilder.addClasspathResource("com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn");
		//发布
		String deploymentIdTemp = deploymentBuilder.deploy().getId();
		//验证是否发布成功
		assertNotNull(deploymentIdTemp);
		//定义一个流程定义查询
		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		//查询刚发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否得到流程定义
		assertNotNull(processDefinitionBehavior);
		
		//根据流程定义号获得流程定义
		ProcessDefinitionBehavior processDefinitionBehavior2 = modelService.getProcessDefinition(processDefinitionBehavior.getProcessDefinitionId());
		//验证获得的流程定义正确
		assertEquals(processDefinitionBehavior, processDefinitionBehavior2);
	}
	/**
	 *  获取流程图节点信息，只包含节点，不包含线条信息
	 */
	public void testGetFlowGraphicsElementPosition(){
		DeploymentBuilder deploymentBuilder = modelService.createDeployment().name("测试名称");
		//添加你要发布的定义
		deploymentBuilder.addClasspathResource("com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn");
		//发布
		String deploymentIdTemp = deploymentBuilder.deploy().getId();
		//验证是否发布成功
		assertNotNull(deploymentIdTemp);
		//定义一个流程定义查询
		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		//查询刚发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否得到流程定义
		assertNotNull(processDefinitionBehavior);
		//获得流程图节点的位置信息
		Map<String,Map<String,Object>> map = modelService.GetFlowGraphicsElementPosition(processDefinitionBehavior.getProcessDefinitionId());
		//验证是否含有UserTask_1，UserTask_4，EndEvent_1等节点
		assertTrue(map.containsKey("UserTask_1"));
		assertTrue(map.containsKey("UserTask_4"));
		assertTrue(map.containsKey("EndEvent_1"));
	}

}
