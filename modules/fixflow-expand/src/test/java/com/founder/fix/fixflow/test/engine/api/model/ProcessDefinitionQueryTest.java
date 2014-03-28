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

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.model.DeploymentBuilder;
import com.founder.fix.fixflow.core.model.ProcessDefinitionQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
/**
 * 流程定义查询测试类
 * @author Administrator
 *
 */
public class ProcessDefinitionQueryTest extends AbstractFixFlowTestCase {

	/**
	 * 测试流程定义查询
	 */
	public void testProcessDefinitionQuery(){
		DeploymentBuilder deploymentBuilder = processEngine.getModelService().createDeployment().name("测试名称");
		//添加你要发布的定义
		deploymentBuilder.addClasspathResource("com/founder/fix/fixflow/test/engine/api/model/startProcessByUser.bpmn");
		String deploymentIdTemp = deploymentBuilder.deploy().getId();
		assertNotNull(deploymentIdTemp);
		//重置下流程发布，发布下一个流程
		DeploymentBuilder deploymentBuilder2 = processEngine.getModelService().createDeployment().name("测试发布");
		deploymentBuilder2.addClasspathResource("com/founder/fix/fixflow/test/engine/api/model/StartProcessInstanceTest.bpmn");
		//发布
		deploymentIdTemp = deploymentBuilder2.deploy().getId();
		assertNotNull(deploymentIdTemp);
		
		//创建流程定义查询
		ProcessDefinitionQuery processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//查询发布号为deploymentIdTemp的流程定义
		List<ProcessDefinitionBehavior> processDefinitionBehaviors = processDefinitionQuery.deploymentId(deploymentIdTemp).list();
		//验证是否为1个
		assertEquals(1, processDefinitionBehaviors.size());
		
		//创建流程定义查询
		processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//查询分类为测试流程定义分类的流程定义
		processDefinitionBehaviors = processDefinitionQuery.processDefinitionCategory("测试流程定义分类").list();
		//验证是否为1个
		assertEquals(1, processDefinitionBehaviors.size());
		
		//创建流程定义查询
		processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//查询分类like测试流程定的流程定义
		processDefinitionBehaviors = processDefinitionQuery.processDefinitionCategoryLike("测试流程定").list();
		//验证是否为1个
		assertEquals(1, processDefinitionBehaviors.size());
		
		//创建流程定义查询
		processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//查询startProcessByUser的版本号为1的流程定义
		processDefinitionBehaviors = processDefinitionQuery.processDefinitionKey("startProcessByUser").processDefinitionVersion(1).list();
		//验证是否为1个
		assertEquals(1, processDefinitionBehaviors.size());
		
		//创建流程定义查询
		processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//查询流程名称等于指定启动人权限的StartProcessInstanceTest流程的流程定义
		processDefinitionBehaviors = processDefinitionQuery.processDefinitionName("指定启动人权限的StartProcessInstanceTest流程").list();
		//验证是否为1个
		assertEquals(1, processDefinitionBehaviors.size());
		
		//创建流程定义查询
		processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//查询流程名称like  StartProcessInstanceTest流程的的流程定义
		processDefinitionBehaviors = processDefinitionQuery.processDefinitionNameLike("StartProcessInstanceTest流程").list();
		//验证是否为2个
		assertEquals(2, processDefinitionBehaviors.size());
		
		String processDefinitionId = processDefinitionBehaviors.get(0).getProcessDefinitionId();
		
		//创建流程定义查询
		processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//查询processDefinitionId的流程定义
		processDefinitionBehaviors = processDefinitionQuery.processDefinitionId(processDefinitionId).list();
		//验证是否为1个
		assertEquals(1, processDefinitionBehaviors.size());
	}
}
