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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.founder.fix.fixflow.core.HistoryService;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.runtime.IdentityLinkQuery;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.core.runtime.Token;
import com.founder.fix.fixflow.core.runtime.TokenQuery;
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

	/**
	 * 测试根据流程实例ID归档
	 */
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
		historyService.archiveByProcessInstanceId(processInstanceId);
		
		//创建流程实例查询
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询刚才启动的流程
		List<ProcessInstance> processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").list();
		//验证运行表已经不存在这条流程
		assertEquals(1, processInstances.size());
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
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询刚才启动的流程
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").list();
		//验证运行表已经不存在这条流程
		assertEquals(0, processInstances.size());
		
		//重置流程实例查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").his().list();
		//验证运行表已经不存在这条流程
		assertEquals(1, processInstances.size());
		
		//重置流程实例查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").run().list();
		//验证运行表已经不存在这条流程
		assertEquals(0, processInstances.size());
		
		//重置流程实例查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").run().his().list();
		//验证运行表已经不存在这条流程
		assertEquals(1, processInstances.size());
		
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		//查询刚才流程的任务
		taskInstances = taskQuery.processDefinitionKey("TaskServiceNewTest").list();
		//验证任务已经不存在
		assertEquals(0, taskInstances.size());
		
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		//查询归档表中的对应任务
		taskInstances = taskQuery.processDefinitionKey("TaskServiceNewTest").his().list();
		//验证归档表中可以到的任务个数不为0
		assertEquals(4, taskInstances.size());
		
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		//查询归档表中的对应任务
		taskInstances = taskQuery.processDefinitionKey("TaskServiceNewTest").run().list();
		//验证运行表中可以到的任务个数为0
		assertEquals(0, taskInstances.size());
		
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		//查询归档表中的对应任务
		taskInstances = taskQuery.processDefinitionKey("TaskServiceNewTest").his().run().list();
		//验证归档表中可以到的任务个数不为0
		assertEquals(4, taskInstances.size());
		
		//创建令牌查询
		TokenQuery tokenQuery = runtimeService.createTokenQuery();
		//查询run表的对应令牌
		List<Token> tokens = tokenQuery.processInstanceId(processInstanceId).list();
		//验证run表中令牌已经不存在
		assertEquals(0, tokens.size());
		
		//重置令牌查询
		tokenQuery = runtimeService.createTokenQuery();
		//查his表中对应令牌
		tokens = tokenQuery.processInstanceId(processInstanceId).his().list();
		//验证令牌存在
		assertEquals(1, tokens.size());
		
		//重置令牌查询
		tokenQuery = runtimeService.createTokenQuery();
		//查run表中对应令牌
		tokens = tokenQuery.processInstanceId(processInstanceId).run().list();
		//验证令牌存在
		assertEquals(0, tokens.size());
				
		//重置令牌查询
		tokenQuery = runtimeService.createTokenQuery();
		//查his表和run表中对应令牌
		tokens = tokenQuery.processInstanceId(processInstanceId).his().run().list();
		//验证令牌存在
		assertEquals(1, tokens.size());
		
	}
	
	/**
	 * 测试根据流程定义KEY归档
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testArchiveByProcessDefinitionKey(){
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
		historyService.archiveByProcessInstanceId(processInstanceId);
		
		//创建流程实例查询
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询刚才启动的流程
		List<ProcessInstance> processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").list();
		//验证运行表还存在这条流程，说明未结束的流程是不会被归档的
		assertEquals(1, processInstances.size());
				
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
		historyService.archiveByProcessDefinitionKey("TaskServiceNewTest");
		
		//重置流程实例查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").his().list();
		//验证运行表已经不存在这条流程
		assertEquals(1, processInstances.size());
		
		//重置流程实例查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").run().list();
		//验证运行表已经不存在这条流程
		assertEquals(0, processInstances.size());
	}
	
	/**
	 * 测试根据流程结束时间段归档
	 * @throws InterruptedException 
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testArchiveBetweenTime() throws InterruptedException{
		
		Date begin = null;
		Date end = null;
		Date middle = null;
		for(int i = 0; i<5;i++){
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
			
			if(i == 1){
				ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
				processInstance = processInstanceQuery.processInstanceId(processInstanceId).singleResult();
				begin = processInstance.getEndTime();
			}
			
			if(i == 3){
				ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
				processInstance = processInstanceQuery.processInstanceId(processInstanceId).singleResult();
				middle = processInstance.getEndTime();
			}
			
			if(i == 4){
				ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
				processInstance = processInstanceQuery.processInstanceId(processInstanceId).singleResult();
				end = processInstance.getEndTime();
			}
			Thread.sleep(1000);
		}
		//将1和2归档
		historyService.archiveBetweenTime(null, begin);
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		List<ProcessInstance> processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").list();
		//run表剩3个
		assertEquals(3, processInstances.size());
		
		//重置流程实例查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").his().list();
		//his表剩2个
		assertEquals(2, processInstances.size());
		
		//重置流程实例查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").his().run().list();
		//run和his表共3个
		assertEquals(5, processInstances.size());
		
		//将大于5归档
		historyService.archiveBetweenTime(end, null);
		
		//重置流程实例查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").list();
		//run表剩2个
		assertEquals(2, processInstances.size());
		
		//重置流程实例查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").his().list();
		//his表剩3个
		assertEquals(3, processInstances.size());
		
		//将3、4、5归档
		historyService.archiveBetweenTime(middle, end);
		
		//重置流程实例查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").list();
		//run表剩1个
		assertEquals(1, processInstances.size());
		
		//重置流程实例查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").his().list();
		//his表剩4个
		assertEquals(4, processInstances.size());
	}
	
	/**
	 * 测试归档已结束的全部流程
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testArchiveAll(){
		for(int i = 0; i<5;i++){
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
			
			//将4号和5号流程结束
			if(i>2){
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
			}
		}
		
		//将已经结束的流程归档
		historyService.archiveAll();
		
		//创建流程实例查询
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询运行表的流程实例
		List<ProcessInstance> processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").list();
		//验证运行表剩3条
		assertEquals(3, processInstances.size());
		
		//重置流程查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询历史表数据
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").his().list();
		//验证历史表中有2条
		assertEquals(2, processInstances.size());
		
	}
	
	/**
	 * 测试根据流程实例ID集合归档
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testArchiveByProcessInstanceIds(){
		List<String> processInstanceIds = new ArrayList<String>();
		for(int i = 0; i<5;i++){
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
			
			//将3号和4号流程实例加入到需要归档的集合中
			if(i>2){
				processInstanceIds.add(processInstanceId);
			}
		}
		
		//将归档集合中的流程实例归档
		historyService.archiveByProcessInstanceIds(processInstanceIds);
		
		//创建流程实例查询
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询运行表的流程实例
		List<ProcessInstance> processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").list();
		//验证运行表剩3条
		assertEquals(3, processInstances.size());
		
		//重置流程查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询历史表数据
		processInstances = processInstanceQuery.processDefinitionKey("TaskServiceNewTest").his().list();
		//验证历史表中有2条
		assertEquals(2, processInstances.size());
	}
}
