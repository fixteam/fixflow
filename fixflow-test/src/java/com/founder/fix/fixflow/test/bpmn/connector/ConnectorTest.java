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
 * @author demonrain
 */

package com.founder.fix.fixflow.test.bpmn.connector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowConnectorTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class ConnectorTest extends AbstractFixFlowConnectorTestCase {
	@Deployment(resources = { "com/founder/fix/fixflow/test/bpmn/connector/ConnectorTest.bpmn" })
	public void testConnector() {
		// 流程数据变量
		// 瞬态
		Map<String, Object> transientVariables = new HashMap<String, Object>();
		// 持久化
		// Map<String, Object> Variables = new HashMap<String, Object>();

		transientVariables.put("连接器跳过策略条件", false);
		transientVariables.put("测试变量", "我是测试变量");
		transientVariables.put("输出变量", "");

		// 设置流程实例参数
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		// 设置流程ID
		startProcessInstanceCommand.setProcessDefinitionKey("ConnectorTest");
		// 设置流程业务关联键
		startProcessInstanceCommand.setBusinessKey("1234567890");
		// 设置启动提交人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		// 设置数据变量
		startProcessInstanceCommand.setTransientVariables(transientVariables);

		// 启动流程，只启动流程 ，流程停在第一步
		ProcessInstance processInstanceQueryTo = runtimeService.noneStartProcessInstance(startProcessInstanceCommand);
		String processInstanceId = processInstanceQueryTo.getId();
		// 验证是否成功启动
		assertNotNull(processInstanceId);
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		// 获取一条任务
		TaskInstance taskInstance = taskInstances.get(0);
		String nodeId = taskInstance.getNodeId();
		// 验证流程实例是否在第一个节点
		assertEquals(nodeId, "UserTask_1");
		
		// 创建一个启动并提交命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		// 设置流程名
		expandTaskCommand.setProcessDefinitionKey("ConnectorTest");
		// 设置流程的业务关联键
		expandTaskCommand.setBusinessKey("1234567890");
		// 命令类型，可以从流程引擎配置中查询 启动并提交为startandsubmit
		expandTaskCommand.setCommandType("startandsubmit");
		// 设置提交人
		expandTaskCommand.setInitiator("1200119390");
		// 设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		// 执行这个启动并提交的命令，返回启动的流程实例
		ProcessInstance processInstance = (ProcessInstance) taskService.expandTaskComplete(expandTaskCommand, null);
		processInstanceId = processInstance.getId();
		
		// 验证是否成功启动
		assertNotNull(processInstanceId);
		
		//创建任务查询
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		// 验证是否成功启动
		taskInstance = taskInstances.get(0);
		nodeId = taskInstance.getNodeId();
		
		// 验证当前任务是否在第二个节点
		assertEquals(nodeId, "UserTask_2");

		//如果没有跳过，测试变量的值会被赋值
		assertEquals("我是赋值后的测试变量", Context.getAbstractScriptLanguageMgmt().getVariable("测试变量"));
		//验证变量是否已经赋值到输出变量
		assertEquals("我是赋值后的测试变量", Context.getAbstractScriptLanguageMgmt().getVariable("输出变量"));
		//如果有跳过策略，测试变量的值应该不变
//		assertEquals("我是测试变量", Context.getAbstractScriptLanguageMgmt().getVariable("测试变量"));
		//验证变量是否已经赋值到输出变量
//		assertEquals("", Context.getAbstractScriptLanguageMgmt().getVariable("输出变量"));
/*		

		// 创建一个启动并提交命令
		expandTaskCommand = new ExpandTaskCommand();
		// 设置流程名
		expandTaskCommand.setProcessDefinitionKey("ConnectorTest");
		// 设置流程的业务关联键
		expandTaskCommand.setBusinessKey("1234567890");
		// 命令类型，可以从流程引擎配置中查询 启动并提交为startandsubmit
		expandTaskCommand.setCommandType("general");
		// 设置提交人
		expandTaskCommand.setInitiator("1200119390");
		// 设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		
		expandTaskCommand.setTaskId(taskInstance.getId());

		// 执行这个启动并提交的命令，返回启动的流程实例
		processInstance = (ProcessInstance) taskService.expandTaskComplete(expandTaskCommand, null);
		processInstanceId = processInstance.getId();
		// 验证是否成功启动
		assertNotNull(processInstanceId);
		
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();

//		assertEquals(0, taskInstances.size());

		// 验证是否成功启动
		taskInstance = taskInstances.get(0);

		nodeId = taskInstance.getNodeId();
		// 验证当前任务是否在第二个节点
		assertEquals(nodeId, "UserTask_3");*/
	}
}
