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

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

/**
 * 流程归档服务测试类
 * @author Administrator
 *
 */
public class HistoryServiceTest extends AbstractFixFlowTestCase {

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testArchiveByProcessInstanceId(){
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
		String processInstanceId = processInstance.getId();
		//验证是否成功启动
		assertNotNull(processInstanceId);
		//测试归档未结束的流程实例
		try{
			historyService.archiveByProcessInstanceId(processInstanceId);
			fail();
		}catch(Exception ex){
			//归档未结束流程实例时，抛出fixFlowException异常
			assertTrue(ex instanceof FixFlowException);
		}
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
		//执行通用按钮
		taskService.expandTaskComplete(expandTaskCommandGeneral, null);
		//将上面结束的流程进行归档
		historyService.archiveByProcessInstanceId(processInstanceId);
		//创建流程实例查询
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询刚才启动的流程
		List<ProcessInstance> processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").list();
		//验证运行表已经不存在这条流程
		assertEquals(0, processInstances.size());
	}
}
