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

package com.founder.fix.fixflow.test.bpmn.scripttask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class ScriptTaskTest extends AbstractFixFlowTestCase {
	/**
	 * 测试脚本任务
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/bpmn/scripttask/ScriptTaskTest.bpmn" })
	public void testMultiInstanceLoop() {
		/*// 拿到流程定义
		ProcessDefinitionBehavior processDefinition = modelService.createProcessDefinitionQuery().processDefinitionKey("UserTaskTest").singleResult();
		// 测试是否为空
		assertNotNull(processDefinition);*/

		// 流程数据变量
		// 瞬态
		Map<String, Object> transientVariables = new HashMap<String, Object>();
		// 持久化
		// Map<String, Object> Variables = new HashMap<String, Object>();

		//打印语句变量
		transientVariables.put("打印语句", "打印语句测试");//后面给boolean值就可以
		// 创建一个启动并提交命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		// 设置流程名
		expandTaskCommand.setProcessDefinitionKey("ScriptTaskTest");
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
		//拿到流程实例ID
		String processInstanceId = processInstance.getId();
		// 验证是否成功启动
		assertNotNull(processInstanceId);

		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();

		// 脚本任务没有停留，流程实例应该结束，任务实例应该为0
		assertEquals(0, taskInstances.size());
		
		//看打印语句是否已经打印到控制台
	}
}
