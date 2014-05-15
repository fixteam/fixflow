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

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.eclipse.bpmn2.FlowElement;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentEntity;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.core.model.DeploymentBuilder;
import com.founder.fix.fixflow.core.model.ProcessDefinitionQuery;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
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
	 * 测试删除流程删除定义部署 
	 */
	public void testDeleteDeployment() {

		DeploymentBuilder deploymentBuilder = processEngine.getModelService().createDeployment().name("测试名称");
		//添加你要发布的定义
		deploymentBuilder.addClasspathResource("com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn");
		//发布
		String deploymentIdTemp = deploymentBuilder.deploy().getId();
		assertNotNull(deploymentIdTemp);
		//创建一个流程定义查询
		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		//查询上面发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		//获得刚发布的流程定义
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证流程定义存在
		assertNotNull(processDefinitionBehavior);
		//删除定义部署，并级联删除其下的所有流程定义
		modelService.deleteDeployment(deploymentIdTemp, true);
		//重置流程定义查询
		processDefinitionQuery=modelService.createProcessDefinitionQuery();
		//查询上面发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		//查找刚发布的流程定义
		processDefinitionBehavior=processDefinitionQuery.singleResult();
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
	
	/**
	 * 测试获取用户有权限启动的流程。  startProcessByUser.bpmn设置为所有人启动，StartProcessInstanceTest.bpmn设置为1035555启动，结果中应含有前者，不含有后者。
	 */
	public void testGetStartProcessByUserId() {

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
		
		//取到用户可以启动的流程列表
		List<Map<String,String>> processList = modelService.getStartProcessByUserId("10691103");
		//判断含有startProcessByUser
		boolean isHave = false;
		//判断不含有Process_StartProcessInstanceTest
		boolean isNotHave = true;
		for(Map <String,String> processMap :processList){
			//循环遍历取到的流程定义
			String process_key = processMap.get("processDefinitionKey");
			//如果含有startProcessByUser则测试通过
			if("startProcessByUser".equals(process_key)){
				isHave = true;
			}
			//如果含有Process_StartProcessInstanceTest则测试失败
			if("Process_StartProcessInstanceTest".equals(process_key)){
				isNotHave = false;
			}
		}
		//如果含有startProcessByUser则测试通过
		assertTrue(isHave);
		//如果含有Process_StartProcessInstanceTest则测试失败
		assertTrue(isNotHave);
	}
	
	/**
	 * 测试通过zip文件发布流程，流程定义发布全部采用从设计器导出的.zip文件   发布.zip文件中包含xxx.bpmn、xxx.png
	 */
	public void testAddZipInputStream(){
		//创建一个发布
		DeploymentBuilder deploymentBuilder = processEngine.getModelService().createDeployment().name("测试名称");
		//获取zip文件的inputStream流
		InputStream inputStream = ReflectUtil.getResourceAsStream("com/founder/fix/fixflow/test/engine/api/model/Process_TaskServiceTest.zip");
		//发布流程定义
		deploymentBuilder.addZipInputStream(new ZipInputStream(inputStream));
		String deploymentId = deploymentBuilder.deploy().getId();
		//验证是否发布成功
		assertNotNull(deploymentId);
		//创建流程定义查询
		ProcessDefinitionQuery processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//查询刚发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否查询到
		assertNotNull(processDefinitionBehavior);
	}
	
	/**
	 * 测试获取流程图
	 */
	public void testGetFlowGraphicsImgStream(){
		//创建一个发布
		DeploymentBuilder deploymentBuilder = processEngine.getModelService().createDeployment().name("测试名称");
		//获取zip文件的inputStream流
		InputStream inputStream = ReflectUtil.getResourceAsStream("com/founder/fix/fixflow/test/engine/api/model/Process_TaskServiceTest.zip");
		//发布流程定义
		deploymentBuilder.addZipInputStream(new ZipInputStream(inputStream));
		String deploymentId = deploymentBuilder.deploy().getId();
		//验证是否发布成功
		assertNotNull(deploymentId);
		//创建流程定义查询
		ProcessDefinitionQuery processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//查询刚发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否查询到
		assertNotNull(processDefinitionBehavior);
		
		//获取流程定义编号
		String processDefinitionId = processDefinitionBehavior.getProcessDefinitionId();
		//获取流程定义key
		String processDefinitionKey = processDefinitionBehavior.getProcessDefinitionKey();
		//通过流程定义编号获取流程图的文件流
		InputStream streamById = modelService.GetFlowGraphicsImgStreamByDefId(processDefinitionId);
		//验证是否成功获取
		assertNotNull(streamById);
		//通过流程定义key获取流程图的文件流
		InputStream streamByKey = modelService.GetFlowGraphicsImgStreamByDefKey(processDefinitionKey);
		//验证是否成功获取
		assertNotNull(streamByKey);
	}
	
	/**
	 * 测试通过zip文件发布流程，流程定义发布全部采用从设计器导出的.zip文件   发布.zip文件中包含xxx.bpmn、xxx.png
	 */
	public void testUpdateDeploymentId(){
		//创建一个发布
		DeploymentBuilder deploymentBuilder = processEngine.getModelService().createDeployment().name("测试名称");
		//获取zip文件的inputStream流
		InputStream inputStream = ReflectUtil.getResourceAsStream("com/founder/fix/fixflow/test/engine/api/model/Process_TaskServiceTest.zip");
		//发布流程定义
		deploymentBuilder.addZipInputStream(new ZipInputStream(inputStream));
		String deploymentId = deploymentBuilder.deploy().getId();
		//验证是否发布成功
		assertNotNull(deploymentId);
		//创建流程定义查询
		ProcessDefinitionQuery processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//查询刚发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否查询到
		assertNotNull(processDefinitionBehavior);
		
		String deployId = processDefinitionBehavior.getDeploymentId();
		deploymentBuilder = processEngine.getModelService().createDeployment().name("测试名称");
		//获取zip文件的inputStream流
		inputStream = ReflectUtil.getResourceAsStream("com/founder/fix/fixflow/test/engine/api/model/Process_TaskServiceTest_new.zip");
		//发布流程定义
		deploymentBuilder.addZipInputStream(new ZipInputStream(inputStream));
		//设置需要更新的发布号
		deploymentBuilder.updateDeploymentId(deployId);
		//更新流程定义
		deploymentId = deploymentBuilder.deploy().getId();
		//重置流程定义查询
		processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//查询刚发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		//获取查询到的流程定义
		processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否查询到
		assertNotNull(processDefinitionBehavior);
		
		//查询新的流程定义中有没有ScriptTask_1
		FlowElement flowElement = processDefinitionBehavior.getFlowElement("ScriptTask_1");
		//验证是否查询到此节点
		assertNotNull(flowElement);
		
	}
	
	/**
	 * 测试通过zip文件发布流程
	 */
	public void testDeploymentByZip(){
		//通过zip文件的path发布流程
		String deploymentId = modelService.deploymentByZip("com/founder/fix/fixflow/test/engine/api/model/Process_TaskServiceTest.zip");
		assertNotNull(deploymentId);
		//获取ZIP文件流
		ZipInputStream zipInputStream = new ZipInputStream(ReflectUtil.getResourceAsStream("com/founder/fix/fixflow/test/engine/api/model/Process_TaskServiceTest_new.zip"));
		//通过zip文件流发布流程
		deploymentId = modelService.deploymentByZip(zipInputStream);
		//验证是否发布成功
		assertNotNull(deploymentId);
	}
	
	/**
	 * 测试通过zip文件更新流程
	 */
	public void testUpdateDeploymentByZip(){
		//通过zip文件的path发布流程
		String deploymentId = modelService.deploymentByZip("com/founder/fix/fixflow/test/engine/api/model/Process_TaskServiceTest.zip");
		assertNotNull(deploymentId);

		//创建流程定义查询
		ProcessDefinitionQuery processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//查询刚发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否查询到
		assertNotNull(processDefinitionBehavior);
		
		String deployId = processDefinitionBehavior.getDeploymentId();
		
		//更新流程定义
		deploymentId = modelService.updateDeploymentByZip("com/founder/fix/fixflow/test/engine/api/model/Process_TaskServiceTest_new.zip", deployId);
		//重置流程定义查询
		processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//查询刚发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		//获取查询到的流程定义
		processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否查询到
		assertNotNull(processDefinitionBehavior);
		
		//查询新的流程定义中有没有ScriptTask_1
		FlowElement flowElement = processDefinitionBehavior.getFlowElement("ScriptTask_1");
		//验证是否查询到此节点
		assertNotNull(flowElement);
		
	}
	
	/**
	 * 测试获取发布实例
	 */
	public void testGetDeploymentEntity(){
		//通过zip文件的path发布流程
		String deploymentId = modelService.deploymentByZip("com/founder/fix/fixflow/test/engine/api/model/Process_TaskServiceTest.zip");
		//验证是否发布成功
		assertNotNull(deploymentId);
		//获取发布实例
		DeploymentEntity deploymentEntity = modelService.getDeploymentEntity(deploymentId);
		//验证是否获取成功
		assertNotNull(deploymentEntity);
		//获取发布的资源信息
		Map<String,ResourceEntity> map = deploymentEntity.getResources();
		//验证是否获取成功
		assertNotNull(map);
		//需要包含png文件和bpmn文件
		assertEquals(2, map.keySet().size());
		for(String key:map.keySet()){
			//获取资源文件
			ResourceEntity resourceEntity = map.get(key);
			//验证资源文件的大字段不为空
			assertNotNull(resourceEntity.getBytes());
		}
	}
	/**
	 * 测试获取用户最近提交流程
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testGetUserSubmitProcess(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("TaskServiceNewTest");
		//设置流程的业务关联键
		expandTaskCommand.setBusinessKey("BK_testStartProcessInstanceByKey");
		//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
		expandTaskCommand.setCommandType("startandsubmit");
		//设置提交人
		expandTaskCommand.setInitiator("1200119390");
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		//执行这个启动并提交的命令，返回启动的流程实例
		ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
		//获取流程实例编号
		String processInstanceId = processInstance.getId();
		//获取流程定义编号
		String processDefinitionId = processInstance.getProcessDefinitionId();
		//验证是否成功启动
		assertNotNull(processInstanceId);
		//获取用户1200119390最近提交的第一个流程
		List<Map<String,String>> processDefinitonMap = modelService.getUserSubmitProcess("1200119390", 1);
		//获取取到的流程定义编号
		String tmpProcessDefinitionId = processDefinitonMap.get(0).get("processDefinitionId");
		//验证是否为上面提交的定义编号
		assertEquals(processDefinitionId, tmpProcessDefinitionId);
	}
	
}


