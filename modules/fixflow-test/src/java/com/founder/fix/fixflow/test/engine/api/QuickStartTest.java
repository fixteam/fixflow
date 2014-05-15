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
package com.founder.fix.fixflow.test.engine.api;

import java.util.List;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.model.ProcessDefinitionQuery;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

/**
 * 快速启动
 * 1.获取启动按钮及其表单
 * 2.获取提交表单Toolbar
 * 3.处理提交表单toolbar
 * 4.获取第二个任务节点的任务【查询代办任务】
 * 5.打开第二个任务节点表单
 * 6.获取第二个任务节点toolbar
 * 7.完成第二个节点处理命令
 * 8.流程结束,查询流程追踪。
 * @author yangchenhui
 *
 */
public class QuickStartTest extends AbstractFixFlowTestCase {

	/**
	 * 测试快速启动，包含流程的全部基本操作
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testQuickStart(){
		
		/*********1.获取启动按钮及其表单 ***********************/
		//获取流程的启动表单
		String formUrl = formService.getStartFormByKey("TaskServiceNewTest");
		//验证表单是否为配置的“add”
		assertEquals("add", formUrl);
		/*********2.获取提交表单Toolbar***********************/
		List<TaskCommandInst> commandList = taskService.getSubTaskTaskCommandByKey("TaskServiceNewTest");
		TaskCommandInst startAndSubmitCommand = null;
		for(int i = 0;i < commandList.size(); i++){
			if("startandsubmit".equals(commandList.get(i).getTaskCommandType())){
				startAndSubmitCommand = commandList.get(i);
			}
		}
		assertNotNull(startAndSubmitCommand);
		/*********3.处理提交表单toolbar***********************/
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("TaskServiceNewTest");
		//设置流程的业务关联键
		expandTaskCommand.setBusinessKey("BK_testStartProcessInstanceByKey");
		//命令类型
		expandTaskCommand.setCommandType(startAndSubmitCommand.getTaskCommandType());
		//设置提交人
		expandTaskCommand.setInitiator("1200119390");
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId(startAndSubmitCommand.getId());
		//执行这个启动并提交的命令，返回启动的流程实例
		ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
		String processInstanceId = processInstance.getId();
		//验证是否成功启动
		assertNotNull(processInstanceId);
		
		/*********4.获取第二个任务节点的任务【查询代办任务】***********************/
		// 创建任务查询
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		// 获取一条任务
		TaskInstance taskInstance = taskInstances.get(0);
		//验证这个待办是否为空
		assertNotNull(taskInstance);
		String nodeId = taskInstance.getNodeId();
		//验证流程实例是否在第二个节点
		assertEquals(nodeId, "UserTask_2");
		
		/*********5.打开第二个任务节点表单***********************/
		
		//获取任务节点上的formUrl
		String taskFormUrl = taskInstance.getFormUri();
		//验证是否为配置的add
		assertEquals("add", taskFormUrl);
		
		/*********6.获取第二个任务节点toolbar***********************/
		//创建流程定义查询
		ProcessDefinitionQuery processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//获取流程定义
		ProcessDefinitionBehavior processDefinition = processDefinitionQuery.processDefinitionKey("TaskServiceNewTest").singleResult();
		//获取流程定义编号   页面上用的时候应该能拿到流程定义号，就不用这样取了。
		String processDefinitionId = processDefinition.getProcessDefinitionId();
		//获取节点上的处理命令
		List<TaskCommandInst> taskCommandList= taskService.getTaskCommandById(processDefinitionId, nodeId);
		assertNotNull(taskCommandList);
		
		/*********7.完成第二个节点处理命令***********************/
		TaskCommandInst generalTaskCommand = null;
		for(int i = 0;i < taskCommandList.size(); i++){
			if("general".equals(taskCommandList.get(i).getTaskCommandType())){
				generalTaskCommand = taskCommandList.get(i);
			}
		}
		assertNotNull(generalTaskCommand);
		
		//创建通用命令
		ExpandTaskCommand expandTaskCommandGeneral=new ExpandTaskCommand();
		//设置命令为领取任务
		expandTaskCommandGeneral.setCommandType("general");
		//设置命令的ID，需和节点上配置的按钮编号对应，会执行其中脚本
		expandTaskCommandGeneral.setUserCommandId("HandleCommand_2");
		//设置命令的处理任务号
		expandTaskCommandGeneral.setTaskId(taskInstance.getId());
		//领取任务
		taskService.expandTaskComplete(expandTaskCommandGeneral, null);
		
		//创建流程实例查询
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//获得上面的流程实例
		ProcessInstance process = processInstanceQuery.processInstanceId(processInstanceId).singleResult();
		//验证流程实例是否已经结束，结束说明通用按钮已经驱动流程离开当前节点。
		assertTrue(process.hasEnded());
		
		/*********8.流程结束,查询流程追踪。***********************/
		
		//重置任务实例查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询1200119390参与的流程(流程追踪)
		List<ProcessInstance> processInstances = processInstanceQuery.taskParticipants("1200119390").processDefinitionKey("TaskServiceNewTest").list();
		//验证是否查询正确
		assertNotNull(processInstances);
		
	}
}
