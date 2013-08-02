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

package com.founder.fix.fixflow.test.bpmn.gateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class InclusiveGatewayTest extends AbstractFixFlowTestCase{
	@Deployment(resources = { "com/founder/fix/fixflow/test/bpmn/gateway/InclusiveGatewayTest.bpmn" })
	public void testInclusiveGateway() {
		/*ProcessDefinitionBehavior processDefinition = modelService.createProcessDefinitionQuery().processDefinitionKey("ExclusiveGatewayTest").singleResult();
		assertNotNull(processDefinition);*/
		// 数据变量
		// 瞬态
		Map<String, Object> transientVariables = new HashMap<String,Object>();
		// 持久化
		// Map<String, Object> Variables = new HashMap<String, Object>();
		transientVariables.put("金额", 1500);
		
		// 启动测试流程
		// 创建一个启动并提交命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		// 设置流程名
		expandTaskCommand.setProcessDefinitionKey("InclusiveGatewayTest");
		// 设置流程的业务关联键
		expandTaskCommand.setBusinessKey("1234567890");
		// 命令类型，可以从流程引擎配置中查询 启动并提交为startandsubmit
		expandTaskCommand.setCommandType("startandsubmit");
		// 设置提交人
		expandTaskCommand.setInitiator("1200119390");
		// 设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		//设置数据变量
		expandTaskCommand.setTransientVariables(transientVariables);

		// 执行这个启动并提交的命令，返回启动的流程实例
		ProcessInstance processInstance = (ProcessInstance) taskService.expandTaskComplete(expandTaskCommand, null);
		// 拿到流程实例ID
		String processInstanceId = processInstance.getId();
		// 验证是否成功启动
		assertNotNull(processInstanceId);

		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		// 由于是包容网关，设定的金额为1500，同时满足后面两个线条，会发散成两个任务，任务实例应该有2个
		assertEquals(2, taskInstances.size());

		// 循环2个任务
		for (int i = 0; i < taskInstances.size(); i++) {
			// 创建一个同意命令
			expandTaskCommand = new ExpandTaskCommand();
			// 设置流程名
			expandTaskCommand.setProcessDefinitionKey("InclusiveGatewayTest");
			// 设置流程的业务关联键
			expandTaskCommand.setBusinessKey("1234567890");
			// 命令类型，可以从流程引擎配置中查询 启动并提交为通用
			expandTaskCommand.setCommandType("general");
			// 设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
			expandTaskCommand.setUserCommandId("HandleCommand_2");
			// 设置任务ID
			expandTaskCommand.setTaskId(taskInstances.get(i).getId());
			// 执行处理命令
			taskService.expandTaskComplete(expandTaskCommand, null);

			if (i == 0) {
				// 第一个任务停靠在UserTask_2上
				assertEquals("UserTask_2", taskInstances.get(i).getNodeId());
				// 第一个任务执行完，会走到UserTask_5节点上等待其他分支走到该节点，这时还是两个个任务实例了
				assertEquals(2, taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().size());
			}
			if (i == 1) {
				// 第二个任务停靠在UserTask_3上
				assertEquals("UserTask_3", taskInstances.get(i).getNodeId());
				// 第二个任务执行完，会走到UserTask_5节点，这时两个停留在UserTask_5上面的任务实例
				assertEquals(2, taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().size());
			}
		}

		// 走完两个任务，任务停留在UserTask_5上
		assertEquals("UserTask_5", taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().get(0).getNodeId());
	}
}
