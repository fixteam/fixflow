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
package com.founder.fix.fixflow.test.engine.api.runtime;
import java.util.List;

import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

/**
 * 流程变量测试类
 * @author Administrator
 *
 */
public class ProcessVariablesTest extends AbstractFixFlowTestCase {
	
	/**
	 * 测试流程持久化变量，持久化变量是保存在数据库中，设置一次，在整个流程实例中均可使用
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/runtime/ProcessVariablesTest.bpmn"})
	public void testVariables(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("ProcessVariablesTest");
		//设置流程的业务关联键
		expandTaskCommand.setBusinessKey("BK_ProcessVariablesTest");
		//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
		expandTaskCommand.setCommandType("startandsubmit");
		//设置提交人
		expandTaskCommand.setInitiator("1200119390");
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		//执行这个启动并提交的命令，返回启动的流程实例
		ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
		String processInstanceId = processInstance.getId();
		//验证是否成功启动
		assertNotNull(processInstanceId);
		
		String variableValue = (String)runtimeService.getProcessInstanceVariable(processInstanceId, "variable");
		assertEquals("持久化变量", variableValue);
		
	}
	
	/**
	 * 测试流程瞬态变量，瞬态变量是存在线程副本里，在一个线程的生命周期内可以使用   流程排他网关设置如果瞬态变量可用则走User_Task3节点
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/runtime/ProcessVariablesTest.bpmn"})
	public void testTransientVariables(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("ProcessVariablesTest");
		//设置流程的业务关联键
		expandTaskCommand.setBusinessKey("BK_ProcessVariablesTest");
		//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
		expandTaskCommand.setCommandType("startandsubmit");
		//设置提交人
		expandTaskCommand.setInitiator("1200119390");
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		//执行这个启动并提交的命令，返回启动的流程实例
		ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
		String processInstanceId = processInstance.getId();
		//验证是否成功启动
		assertNotNull(processInstanceId);
		
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
		assertEquals(nodeId, "UserTask_3");
	}
}
