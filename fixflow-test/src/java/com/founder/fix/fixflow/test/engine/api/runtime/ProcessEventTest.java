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

public class ProcessEventTest  extends AbstractFixFlowTestCase {

	/**
	 * 测试流程事件，在流程定义中配置连接器的各种事件。
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/runtime/processEventTest.bpmn"})
	public void testProcessEvent(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("processEventTest");
		//设置流程的业务关联键
		expandTaskCommand.setBusinessKey("BK_processEventTest");
		//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
		expandTaskCommand.setCommandType("startandsubmit");
		//设置提交人
		expandTaskCommand.setInitiator("1200119390");
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		//执行这个启动并提交的命令
		ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
		String processInstanceId = processInstance.getId();
		//获取流程变量
		Object variableValue = runtimeService.getProcessInstanceVariable(processInstanceId, "testEvent");
		//流程定义的启动事件中会将此变量设置为“启动” 详细请参考processEventTest.bpmn的流程定义
		assertEquals("启动", variableValue);
		
		// 创建任务查询
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		// 获取一条任务
		TaskInstance taskInstance = taskInstances.get(0);
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
		
		//获取流程变量
		variableValue = runtimeService.getProcessInstanceVariable(processInstanceId, "testEvent");
		//在流程定义的结束事件中会将次变量的值设置为“结束” 详细请参考processEventTest.bpmn的流程定义
		assertEquals("结束", variableValue);
	}
	
	/**
	 * 测试流程终止事件，在流程定义中配置连接器的各种事件。
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/runtime/processEventTest.bpmn"})
	public void testTermanitionEvent(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("processEventTest");
		//设置流程的业务关联键
		expandTaskCommand.setBusinessKey("BK_processEventTest");
		//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
		expandTaskCommand.setCommandType("startandsubmit");
		//设置提交人
		expandTaskCommand.setInitiator("1200119390");
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		//执行这个启动并提交的命令
		ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
		String processInstanceId = processInstance.getId();
		
		//获取testEvent变量的值
		Object variableValue = runtimeService.getProcessInstanceVariable(processInstanceId, "testEvent");
		//在此流程定义的启动事件中会将testEvent变量的值赋值为“启动”详细请参考processEventTest.bpmn的流程定义
		assertEquals("启动", variableValue);
		
		//终止流程
		runtimeService.terminatProcessInstance(processInstanceId);
		
		//获取流程变量
		variableValue = runtimeService.getProcessInstanceVariable(processInstanceId, "testEvent");
		//在此流程定义的终止事件中会将testEvent变量的值赋值为“终止”详细请参考processEventTest.bpmn的流程定义
		assertEquals("终止", variableValue);
		
		//创建一个通用命令  重新启动一个流程，用于测试按钮终止事件是否触发
		expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("processEventTest");
		//设置流程的业务关联键
		expandTaskCommand.setBusinessKey("BK_processEventTest");
		//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
		expandTaskCommand.setCommandType("startandsubmit");
		//设置提交人
		expandTaskCommand.setInitiator("1200119390");
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		//执行这个启动并提交的命令
		processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
		//获取流程实例编号
		processInstanceId = processInstance.getId();
		
		// 创建任务查询
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		// 获取一条任务
		TaskInstance taskInstance = taskInstances.get(0);
		//创建通用命令
		ExpandTaskCommand expandTaskCommandGeneral=new ExpandTaskCommand();
		//设置命令为领取任务
		expandTaskCommandGeneral.setCommandType("terminationProcess");
		//设置命令的ID，需和节点上配置的按钮编号对应，会执行其中脚本
		expandTaskCommandGeneral.setUserCommandId("HandleCommand_3");
		//设置命令的处理任务号
		expandTaskCommandGeneral.setTaskId(taskInstance.getId());
		//领取任务
		taskService.expandTaskComplete(expandTaskCommandGeneral, null);
		//获取流程变量的值
		variableValue = runtimeService.getProcessInstanceVariable(processInstanceId, "testEvent");
		//在流程定义的终止事件中会将变量的值设置为“终止”详细请参考processEventTest.bpmn的流程定义
		assertEquals("终止", variableValue);
	}
	
}
