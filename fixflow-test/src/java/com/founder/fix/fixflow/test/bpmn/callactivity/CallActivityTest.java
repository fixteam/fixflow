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

package com.founder.fix.fixflow.test.bpmn.callactivity;

import java.util.List;

import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class CallActivityTest extends AbstractFixFlowTestCase {

	@Deployment(resources = { "com/founder/fix/fixflow/test/bpmn/callactivity/CallActivityTest.bpmn","com/founder/fix/fixflow/test/bpmn/subprocess/SubProcessTest.bpmn" })
	public void testCallActivity() {
		/*ProcessDefinitionBehavior processDefinition = modelService.createProcessDefinitionQuery().processDefinitionKey("ExclusiveGatewayTest").singleResult();
		assertNotNull(processDefinition);*/
		// 数据变量
		// 瞬态
//		Map<String, Object> transientVariables = new HashMap<String,Object>();
		// 持久化
		// Map<String, Object> Variables = new HashMap<String, Object>();
		
		// 启动测试流程
		// 创建一个启动并提交命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		// 设置流程名
		expandTaskCommand.setProcessDefinitionKey("CallActivityTest");
		// 设置流程的业务关联键
		expandTaskCommand.setBusinessKey("1234567890");
		// 命令类型，可以从流程引擎配置中查询 启动并提交为startandsubmit
		expandTaskCommand.setCommandType("startandsubmit");
		// 设置提交人
		expandTaskCommand.setInitiator("1200119390");
		// 设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		//设置数据变量
//		expandTaskCommand.setTransientVariables(transientVariables);
		// 执行这个启动并提交的命令，返回启动的流程实例
		ProcessInstance processInstance = (ProcessInstance) taskService.expandTaskComplete(expandTaskCommand, null);
		// 拿到流程实例ID
		String mainProcessInstanceId = processInstance.getId();
		// 验证是否成功启动
		assertNotNull(mainProcessInstanceId);
		//创建流程实例查询
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//拿到子流程实例
		ProcessInstance subProcessInstance = processInstanceQuery.processDefinitionKey("SubProcessTest").singleResult();
		//验证是否为设置的子流程
		assertEquals("SubProcessTest", subProcessInstance.getProcessDefinitionKey());
		//主流程的任务应该没有了，查询子流程中的任务
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(subProcessInstance.getId()).taskNotEnd().list();
		TaskInstance taskInstance = taskInstances.get(0);
		//启动子流程，并且令牌停留在UserTask_1节点
		assertEquals("UserTask_1", taskInstance.getNodeId());

		//下面是正常走SubProcess流程
		//===========================================================================
		// 创建一个启动并提交命令
		expandTaskCommand = new ExpandTaskCommand();
		// 设置流程名
		expandTaskCommand.setProcessDefinitionKey("SubProcessTest");
		// 设置流程的业务关联键
		expandTaskCommand.setBusinessKey("1234567890");
		// 命令类型，可以从流程引擎配置中查询 启动并提交为startandsubmit
		expandTaskCommand.setCommandType("submit");
		// 设置提交人
		expandTaskCommand.setInitiator("1200119390");
		// 设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		//设置任务编号
		expandTaskCommand.setTaskId(taskInstance.getId());
		//设置数据变量
//				expandTaskCommand.setTransientVariables(transientVariables);

		// 执行这个启动并提交的命令，返回启动的流程实例
		processInstance = (ProcessInstance) taskService.expandTaskComplete(expandTaskCommand, null);
		// 拿到流程实例ID
		String subProcessInstanceId = subProcessInstance.getId();
		// 验证是否成功启动
		assertNotNull(subProcessInstanceId);
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(subProcessInstanceId).taskNotEnd().list();
		taskInstance = taskInstances.get(0);
		//启动子流程，并且令牌停留在UserTask_2节点
		assertEquals("UserTask_2", taskInstance.getNodeId());
		//创建一个处理命令
		expandTaskCommand = new ExpandTaskCommand();
		// 设置流程名
		expandTaskCommand.setProcessDefinitionKey("SubProcessTest");
		// 设置流程的业务关联键
		expandTaskCommand.setBusinessKey("1234567890");
		// 命令类型，通用按钮
		expandTaskCommand.setCommandType("general");
		// 设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		//设置任务ID
		expandTaskCommand.setTaskId(taskInstance.getId());
		//执行处理命令
		taskService.expandTaskComplete(expandTaskCommand, null);
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(subProcessInstanceId).taskNotEnd().list();
		taskInstance = taskInstances.get(0);
		//令牌停留在UserTask_3节点
		assertEquals("UserTask_3", taskInstance.getNodeId());
		//创建一个处理命令
		expandTaskCommand = new ExpandTaskCommand();
		// 设置流程名
		expandTaskCommand.setProcessDefinitionKey("SubProcessTest");
		// 设置流程的业务关联键
		expandTaskCommand.setBusinessKey("1234567890");
		// 命令类型，通用按钮
		expandTaskCommand.setCommandType("general");
		// 设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		//设置任务ID
		expandTaskCommand.setTaskId(taskInstance.getId());
		//执行处理命令
		taskService.expandTaskComplete(expandTaskCommand, null);
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(subProcessInstanceId).taskNotEnd().list();
		taskInstance = taskInstances.get(0);
		//令牌停留在UserTask_4节点
		assertEquals("UserTask_4", taskInstance.getNodeId());
		//创建一个处理命令
		expandTaskCommand = new ExpandTaskCommand();
		// 设置流程名
		expandTaskCommand.setProcessDefinitionKey("SubProcessTest");
		// 设置流程的业务关联键
		expandTaskCommand.setBusinessKey("1234567890");
		// 命令类型，通用按钮
		expandTaskCommand.setCommandType("general");
		// 设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		//设置任务ID
		expandTaskCommand.setTaskId(taskInstance.getId());
		//执行处理命令
		taskService.expandTaskComplete(expandTaskCommand, null);
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(subProcessInstanceId).taskNotEnd().list();
		//全部走完，任务为空
		assertEquals(0, taskInstances.size());
		//===========================================================================
		//这个时候子流程走完会回到主流程上来
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(mainProcessInstanceId).taskNotEnd().list();
		taskInstance = taskInstances.get(0);
		//令牌停留在UserTask_2节点
		assertEquals("UserTask_2", taskInstance.getNodeId());
	}
}