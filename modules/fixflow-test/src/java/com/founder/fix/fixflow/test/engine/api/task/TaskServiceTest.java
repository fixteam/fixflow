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
package com.founder.fix.fixflow.test.engine.api.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.model.ProcessDefinitionQuery;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceType;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskInstanceType;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class TaskServiceTest extends AbstractFixFlowTestCase {
	
	/**
	 * 测试启动并提交，手工启动流程。
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testStartAndSubmit(){
		
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
		//验证这个待办是否为空
		assertNotNull(taskInstance);
		String nodeId = taskInstance.getNodeId();
		//验证流程实例是否在第二个节点
		assertEquals(nodeId, "UserTask_2");
	}
	
	/**
	 * 测试提交命令，提交和启动并提交的区别在于：1.第一个节点是共享：则启动并提交=启动+接收任务+提交 2.第一个节点是独占：则启动并提交=启动+提交
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testSubmit(){
		
		//启动流程命令
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		//设置流程编号
		startProcessInstanceCommand.setProcessDefinitionKey("TaskServiceNewTest");
		//设置业务关联键
		startProcessInstanceCommand.setBusinessKey("BK_testStartProcessInstanceByKey");
		//设置流程启动人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		//启动流程，只启动流程 ，流程停在第一步
		ProcessInstance processInstanceQueryTo = runtimeService
				.noneStartProcessInstance(startProcessInstanceCommand);
		String processInstanceId = processInstanceQueryTo.getId();
		//验证是否成功启动
		assertNotNull(processInstanceId);
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		// 获取一条任务
		TaskInstance taskInstance = taskInstances.get(0);
		String nodeId = taskInstance.getNodeId();
		//验证流程实例是否在第一个节点
		assertEquals(nodeId, "UserTask_1");
		
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置命令类型
		expandTaskCommand.setCommandType("submit");
		//设置任务号
		expandTaskCommand.setTaskId(taskInstance.getId());
		//设置命令ID  与节点上处理命令中一致，会执行按钮脚本
		expandTaskCommand.setUserCommandId("HandleCommand_3");
		//设置业务关联键
		expandTaskCommand.setBusinessKey("BK_testStartProcessInstanceByKey");
		//设置提交人
		expandTaskCommand.setInitiator("1200119390");
		//执行这个启动并提交的命令，返回启动的流程实例
		taskService.expandTaskComplete(expandTaskCommand, null);
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		//验证是否成功启动
		taskInstance = taskInstances.get(0);
		nodeId = taskInstance.getNodeId();
		//验证当前任务是否在第二个节点
		assertEquals(nodeId, "UserTask_2");
	}
	
	/**
	 * 测试通用命令 通用按钮只会驱动流程令牌离开，执行表达式命令，不做额外操作
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testGeneral(){
		
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
		//验证这个待办是否为空
		assertNotNull(taskInstance);
		String nodeId = taskInstance.getNodeId();
		//验证流程实例是否在第二个节点
		assertEquals(nodeId, "UserTask_2");
		
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
	}
	/**
	 * 测试接收任务和释放任务，TaskServiceCandidateTest流程中的任务分配类型为共享模式
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceCandidateTest.bpmn"})
	public void testClaim(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("TaskServiceCandidateTest");
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
		//未领任务前 独占任务应该为0
		assertEquals(taskInstances.size(), 0);
		//获取1200119390的本流程实例的共享任务
		taskInstances = taskQuery.taskCandidateUser("1200119390").processInstanceId(processInstanceId).taskNotEnd().list(); 
		//验证此时的共享任务为1
		assertEquals(taskInstances.size(), 1);
		
		//获取当前任务节点
		TaskInstance taskInstance = taskInstances.get(0);
		//创建通用命令
		ExpandTaskCommand expandTaskCommandClaim=new ExpandTaskCommand();
		//设置命令为领取任务
		expandTaskCommandClaim.setCommandType("claim");
		//设置命令的ID，需和节点上配置的按钮编号对应，会执行其中脚本
		expandTaskCommandClaim.setUserCommandId("HandleCommand_3");
		//设置命令的处理任务号
		expandTaskCommandClaim.setTaskId(taskInstance.getId());
		//领取任务
		taskService.expandTaskComplete(expandTaskCommandClaim, null);
		
		//重置任务查询 查询共享
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前共享任务
		taskInstances = taskQuery.taskCandidateUser("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		//领取任务后，共享任务数为0
		assertEquals(taskInstances.size(), 0);
		//重置任务查询 查询独占
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		//领取任务后，独占任务数为1
		assertEquals(taskInstances.size(), 1);
		
		//取得当前任务
		taskInstance = taskInstances.get(0);
		//取得任务所在节点
		String nodeId = taskInstance.getNodeId();
		//验证当前任务是否在第二个节点
		assertEquals(nodeId, "UserTask_4");
		
		//创建通用命令
		ExpandTaskCommand expandTaskCommandRelease=new ExpandTaskCommand();
		//设置命令为释放任务
		expandTaskCommandRelease.setCommandType("releaseTask");
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommandRelease.setUserCommandId("HandleCommand_4");
		//设置命令的处理任务号
		expandTaskCommandRelease.setTaskId(taskInstance.getId());
		//领取任务
		taskService.expandTaskComplete(expandTaskCommandRelease, null);
		
		//重置任务查询 查询独占
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		//释放任务后，独占任务数为0
		assertEquals(taskInstances.size(), 0);
		//重置任务查询 查询共享
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前共享任务
		taskInstances = taskQuery.taskCandidateUser("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		//释放任务后，共享任务数为1
		assertEquals(taskInstances.size(), 1);
		//取得当前任务
		taskInstance = taskInstances.get(0);
		//取得任务所在节点
		nodeId = taskInstance.getNodeId();
		//验证当前任务是否在第二个节点
		assertEquals(nodeId, "UserTask_4");
		
	}
	/**
	 * 测试退回上一步
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testRollBackTaskPreviousStep(){
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
		//验证此时的独占任务为1
		assertEquals(taskInstances.size(), 1);
		TaskInstance taskInstance = taskInstances.get(0);
		String nodeId = taskInstance.getNodeId();
		assertEquals(nodeId, "UserTask_2");
		
		//创建通用命令
		ExpandTaskCommand expandTaskCommandRollBack=new ExpandTaskCommand();
		//设置命令为退回上一步
		expandTaskCommandRollBack.setCommandType("rollBackTaskPreviousStep");
		//设置命令按钮的iD,与节点上处理命令设置一致
		expandTaskCommandRollBack.setUserCommandId("HandleCommand_3");
		//设置命令的处理任务号
		expandTaskCommandRollBack.setTaskId(taskInstance.getId());
		//执行退回操作
		taskService.expandTaskComplete(expandTaskCommandRollBack, null);
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		// 查询此流程实例的当前任务号
		taskInstances = taskQuery.processInstanceId(processInstanceId).taskNotEnd().list(); 
		taskInstance = taskInstances.get(0);
		//查询当前任务号的节点名称
		nodeId = taskInstance.getNodeId();
		//验证退回操作后任务是否回到UserTask_1节点
		assertEquals(nodeId, "UserTask_1");
	}
	/**
	 * 测试退回指定步骤
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testRollBackTaskByExpression(){
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
		//验证此时的独占任务为1
		assertEquals(taskInstances.size(), 1);
		//取得当前任务
		TaskInstance taskInstance = taskInstances.get(0);
		//取得当前任务节点
		String nodeId = taskInstance.getNodeId();
		//验证是否在第二个节点
		assertEquals(nodeId, "UserTask_2");
		
		//创建通用命令
		ExpandTaskCommand expandTaskCommandRollBack=new ExpandTaskCommand();
		//设置命令为退回制定步骤
		expandTaskCommandRollBack.setCommandType("rollBackTaskByExpression");
		//设置命令按钮的iD,与节点上处理命令设置一致
		expandTaskCommandRollBack.setUserCommandId("HandleCommand_4");
		//设置命令的处理任务号
		expandTaskCommandRollBack.setTaskId(taskInstance.getId());
		//执行退回操作
		taskService.expandTaskComplete(expandTaskCommandRollBack, null);
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		// 查询此流程实例的当前任务号
		taskInstances = taskQuery.processInstanceId(processInstanceId).taskNotEnd().list(); 
		taskInstance = taskInstances.get(0);
		//查询当前任务号的节点名称
		nodeId = taskInstance.getNodeId();
		//验证退回操作后任务是否回到UserTask_1节点
		assertEquals(nodeId, "UserTask_1");
	}
	/**
	 * 测试退回-节点
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testRollBack(){
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
		//验证此时的独占任务为1
		assertEquals(taskInstances.size(), 1);
		//获得当前任务
		TaskInstance taskInstance = taskInstances.get(0);
		//获得任务节点
		String nodeId = taskInstance.getNodeId();
		//验证是否在第二个节点
		assertEquals(nodeId, "UserTask_2");
		
		//创建通用命令
		ExpandTaskCommand expandTaskCommandRollBack=new ExpandTaskCommand();
		//设置命令为退回-节点
		expandTaskCommandRollBack.setCommandType("rollBack");
		//设置命令按钮的iD,与节点上处理命令设置一致
		expandTaskCommandRollBack.setUserCommandId("HandleCommand_5");
		//设置命令的处理任务号
		expandTaskCommandRollBack.setTaskId(taskInstance.getId());
		//设置命令所需的额外参数 rollBackNodeId
		Map <String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("rollBackNodeId", "UserTask_1");
		//设置命令参数
		expandTaskCommandRollBack.setParamMap(paraMap);
		//执行退回操作
		taskService.expandTaskComplete(expandTaskCommandRollBack, null);
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		// 查询此流程实例的当前任务号
		taskInstances = taskQuery.processInstanceId(processInstanceId).taskNotEnd().list(); 
		taskInstance = taskInstances.get(0);
		//查询当前任务号的节点名称
		nodeId = taskInstance.getNodeId();
		//验证退回操作后任务是否回到UserTask_1节点
		assertEquals(nodeId, "UserTask_1");
	}
	/**
	 * 测试退回-任务
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testRollBackTaskByTaskId(){
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
		//验证此时的独占任务为1
		assertEquals(taskInstances.size(), 1);
		//获得当前任务
		TaskInstance taskInstance = taskInstances.get(0);
		//获得当前任务节点
		String nodeId = taskInstance.getNodeId();
		//验证是否在第二个节点
		assertEquals(nodeId, "UserTask_2");
		
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		// 查询此流程实例的已经处理过的 UserTask_1节点的任务编号
		taskInstances = taskQuery.processInstanceId(processInstanceId).taskIsEnd().nodeId("UserTask_1").list(); 
		//处理过的 UserTask_1节点的任务编号
		String taskId = taskInstances.get(0).getId();
				
		//创建通用命令
		ExpandTaskCommand expandTaskCommandRollBack=new ExpandTaskCommand();
		//设置命令为退回-节点
		expandTaskCommandRollBack.setCommandType("rollBackTaskByTaskId");
		//设置命令按钮的iD,与节点上处理命令设置一致
		expandTaskCommandRollBack.setUserCommandId("HandleCommand_6");
		//设置命令的处理任务号
		expandTaskCommandRollBack.setTaskId(taskInstance.getId());
		//设置命令所需的额外参数 rollBackTaskId--需要退回的任务号
		Map <String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("rollBackTaskId", taskId);
		//设置命令参数
		expandTaskCommandRollBack.setParamMap(paraMap);
		//执行退回操作
		taskService.expandTaskComplete(expandTaskCommandRollBack, null);
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		// 查询此流程实例的当前任务号
		taskInstances = taskQuery.processInstanceId(processInstanceId).taskNotEnd().list(); 
		taskInstance = taskInstances.get(0);
		//查询当前任务号的节点名称
		nodeId = taskInstance.getNodeId();
		//验证退回操作后任务是否回到UserTask_1节点
		assertEquals(nodeId, "UserTask_1");
	}
	
	/**
	 * 测试保存草稿 ， 保存草稿后，会将当前任务存为草稿状态
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testSaveTaskDraft(){
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
		//获得任务实例
		TaskInstance taskInstance = taskInstances.get(0);
		//获得当前任务节点
		String nodeId = taskInstance.getNodeId();
		//验证是否在第二个节点
		assertEquals(nodeId, "UserTask_2");
			
		//保存草稿操作前，验证任务为非草稿状态
		assertEquals(taskInstance.isDraft(),false);
		//创建通用命令
		ExpandTaskCommand expandTaskCommandSaveTaskDraft=new ExpandTaskCommand();
		//设置命令为保存草稿
		expandTaskCommandSaveTaskDraft.setCommandType("saveTaskDraft");
		//设置命令按钮的iD,与节点上处理命令设置一致
		expandTaskCommandSaveTaskDraft.setUserCommandId("HandleCommand_7");
		//设置命令的处理任务号
		expandTaskCommandSaveTaskDraft.setTaskId(taskInstance.getId());
		//执行这个启动并提交的命令，返回启动的流程实例
		taskService.expandTaskComplete(expandTaskCommandSaveTaskDraft, null);
		
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstance = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().get(0);
		//保存草稿操作后，验证任务为草稿状态
		assertEquals(taskInstance.isDraft(),true);
	}
	
	/**
	 * 测试删除实例 ---该命令尚未完善
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testDeleteProcessInstance(){
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
		//验证是流程实例是否存在
		assertNotNull(processInstanceId);
		
		
//		// 创建任务查询
//		TaskQuery taskQuery = taskService.createTaskQuery();
//		// 查找 1200119390 的这个流程实例的当前独占任务
//		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
//		//取得当前任务
//		TaskInstance taskInstance = taskInstances.get(0);
//		//创建通用命令
//		ExpandTaskCommand expandTaskCommandSaveTaskDraft=new ExpandTaskCommand();
//		//设置命令为删除实例
//		expandTaskCommandSaveTaskDraft.setCommandType("deleteProcessInstance");
//		//设置命令按钮的iD,与节点上处理命令设置一致
//		expandTaskCommandSaveTaskDraft.setUserCommandId("HandleCommand_8");
//		//设置命令的处理任务号
//		expandTaskCommandSaveTaskDraft.setTaskId(taskInstance.getId());
//		//执行这个启动并提交的命令，返回启动的流程实例
//		taskService.expandTaskComplete(expandTaskCommandSaveTaskDraft, null);
		
//		// 重置任务查询
//		taskQuery = taskService.createTaskQuery();
//		//获取此任务实例的任务数
//		int taskCount = taskQuery.processInstanceId(processInstanceId).list().size();
		//验证实例相关任务被删除
//		assertEquals(taskCount,0);
	}
	/**
	 * 测试暂停和恢复任务，暂停和恢复需成对出现，暂停任务后，任务为暂停状态，只可以有恢复按钮
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testSuspendAndContinueTask(){
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
		//验证是流程实例是否存在
		assertNotNull(processInstanceId);
		
		// 创建任务查询
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		//取得当前任务
		TaskInstance taskInstance = taskInstances.get(0);
		
		//暂停任务前，任务为非暂停状态
		assertEquals(taskInstance.isSuspended(),false);
		//创建通用命令
		ExpandTaskCommand expandTaskCommandSuspendTask=new ExpandTaskCommand();
		//设置命令为暂停任务
		expandTaskCommandSuspendTask.setCommandType("suspendTask");
		//设置命令按钮的iD,与节点上处理命令设置一致
		expandTaskCommandSuspendTask.setUserCommandId("HandleCommand_9");
		//设置命令的处理任务号
		expandTaskCommandSuspendTask.setTaskId(taskInstance.getId());
		//执行这个暂停任务的命令
		taskService.expandTaskComplete(expandTaskCommandSuspendTask, null);
		
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstance = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().get(0);
		//暂停任务后，验证任务为暂停状态
		assertEquals(taskInstance.isSuspended(),true);
		
		
		//创建通用命令
		ExpandTaskCommand expandTaskCommandContinueTask=new ExpandTaskCommand();
		//设置命令为恢复任务
		expandTaskCommandContinueTask.setCommandType("continueTask");
		//设置命令按钮的iD,与节点上处理命令设置一致
		expandTaskCommandContinueTask.setUserCommandId("HandleCommand_10");
		//设置命令的处理任务号
		expandTaskCommandContinueTask.setTaskId(taskInstance.getId());
		//执行这个恢复任务的命令
		taskService.expandTaskComplete(expandTaskCommandContinueTask, null);
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstance = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().get(0);
		//恢复任务操作后，验证任务为未暂停状态
		assertEquals(taskInstance.isSuspended(),false);
	}
	/**
	 * 测试转办和还回操作，转办和还回操作需成对出现，转办后，任务会到选择的人待办中，并且只有还回按钮使流程回到转办人
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testPendingAndResolved(){
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
		//验证是流程实例是否存在
		assertNotNull(processInstanceId);
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		TaskInstance taskInstance = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().get(0);
		//暂停任务后，验证任务为暂停状态
		assertEquals(taskInstance.getNodeId(),"UserTask_2");
		
		
		//创建通用命令
		ExpandTaskCommand expandTaskCommandPending=new ExpandTaskCommand();
		//设置命令为转办任务
		expandTaskCommandPending.setCommandType("pending");
		//设置命令按钮的iD,与节点上处理命令设置一致
		expandTaskCommandPending.setUserCommandId("HandleCommand_11");
		
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		//设置转办的用户号
		paramsMap.put("pendingUserId", "13223212313");
		expandTaskCommandPending.setParamMap(paramsMap);
		//设置命令的处理任务号
		expandTaskCommandPending.setTaskId(taskInstance.getId());
		//执行这个转办的命令
		taskService.expandTaskComplete(expandTaskCommandPending, null);
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		// 查找 13223212313 的这个流程实例的当前独占任务
		taskInstance = taskQuery.taskAssignee("13223212313").processInstanceId(processInstanceId).taskNotEnd().list().get(0);
		//转办操作后，转办接收人的待办中存在这条任务
		assertNotNull(taskInstance);
		
		//查询是否有还回命令
		List<TaskCommandInst> taskCommandInsts=taskService.GetTaskCommandByTaskInstance(taskInstance,false);
		boolean isHaveResolvedCommand = false;
		//循环查找是否有还回命令
		for(TaskCommandInst taskCommandInst:taskCommandInsts){
			if("resolved".equals(taskCommandInst.getTaskCommandType())){
				isHaveResolvedCommand = true;
				break;
			}
		}
		//验证存在还回命令
		assertEquals(isHaveResolvedCommand,true);
		
		//创建通用命令
		ExpandTaskCommand expandTaskCommandResolved=new ExpandTaskCommand();
		//设置命令为还回任务
		expandTaskCommandResolved.setCommandType("resolved");
		//设置命令按钮的iD,与节点上处理命令设置一致
		expandTaskCommandResolved.setUserCommandId("HandleCommand_12");
		//设置命令的处理任务号
		expandTaskCommandResolved.setTaskId(taskInstance.getId());
		//执行这个还回任务的命令
		taskService.expandTaskComplete(expandTaskCommandResolved, null);
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstance = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().get(0);
		//还回操作后，任务回到换来的处理人
		assertNotNull(taskInstance);
		
		//查询是否有还回命令
		taskCommandInsts=taskService.GetTaskCommandByTaskInstance(taskInstance,false);
		isHaveResolvedCommand = false;
		//循环查找是否有还回命令
		for(TaskCommandInst taskCommandInst:taskCommandInsts){
			//如果存在类型为还回的按钮则结束
			if("resolved".equals(taskCommandInst.getTaskCommandType())){
				isHaveResolvedCommand = true;
				break;
			}
		}
		//验证不存在还回命令
		assertEquals(isHaveResolvedCommand,false);
	}
	
	/**
	 * 测试追回操作  追回按钮需要配置在可以被追回的节点上，表达式中配置可以有追回操作的的节点编号，不配置则前面所有节点均可追回
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testRecover(){
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
		//验证是流程实例是否存在
		assertNotNull(processInstanceId);
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		TaskInstance taskInstance = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().get(0);
		//查询是否有追回命令
		List<TaskCommandInst> taskCommandInsts=taskService.GetTaskCommandByTaskInstance(taskInstance,true);
		boolean isHaveRecoverCommand = false;
		//循环查找是否有追回命令
		for(TaskCommandInst taskCommandInst:taskCommandInsts){
			//如果存在类型为追回的按钮则结束
			if("recover".equals(taskCommandInst.getTaskCommandType())){
				isHaveRecoverCommand = true;
				break;
			}
		}
		//验证存在追回命令
		assertEquals(isHaveRecoverCommand,true);
		
		//创建通用命令
		ExpandTaskCommand expandTaskCommandRecover=new ExpandTaskCommand();
		//设置命令为暂停实例
		expandTaskCommandRecover.setCommandType("recover");
		//设置命令按钮的iD,与节点上处理命令设置一致
		expandTaskCommandRecover.setUserCommandId("HandleCommand_13");
		//设置命令的处理任务号
		expandTaskCommandRecover.setTaskId(taskInstance.getId());
		//设置追回命令必须的参数recoverNodeId
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("recoverNodeId", "UserTask_1");
		expandTaskCommandRecover.setParamMap(map);
		//执行这个追回的命令
		taskService.expandTaskComplete(expandTaskCommandRecover, null);
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		// 查找当前流程实例的当前处理任务
		taskInstance = taskQuery.processInstanceId(processInstanceId).taskNotEnd().list().get(0);
		//验证任务是否被追回到UserTask_1节点
		assertEquals("UserTask_1", taskInstance.getNodeId());
	}
	
	/**
	 * 测试暂停和恢复流程实例
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testSuspendProcessInstance(){
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
		//验证是流程实例是否存在
		assertNotNull(processInstanceId);
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		TaskInstance taskInstance = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().get(0);
		//创建通用命令
		ExpandTaskCommand expandTaskCommandSuspendProcessInstance=new ExpandTaskCommand();
		//设置命令为暂停实例
		expandTaskCommandSuspendProcessInstance.setCommandType("suspendProcessInstance");
		//设置命令按钮的iD,与节点上处理命令设置一致
		expandTaskCommandSuspendProcessInstance.setUserCommandId("HandleCommand_14");
		//设置命令的处理任务号
		expandTaskCommandSuspendProcessInstance.setTaskId(taskInstance.getId());
		//执行这个暂停实例的命令
		taskService.expandTaskComplete(expandTaskCommandSuspendProcessInstance, null);
		//根据实例号得到流程实例
		processInstance = runtimeService.getProcessInstance(processInstanceId);
		
		//验证实例处理暂停状态
		assertEquals(processInstance.isSuspended(),true);
		
		//创建通用命令
		ExpandTaskCommand expandTaskCommandContinueProcessInstance=new ExpandTaskCommand();
		//设置命令为恢复实例
		expandTaskCommandContinueProcessInstance.setCommandType("continueProcessInstance");
		//设置命令按钮的iD,与节点上处理命令设置一致
		expandTaskCommandContinueProcessInstance.setUserCommandId("HandleCommand_15");
		//设置命令的处理任务号
		expandTaskCommandContinueProcessInstance.setTaskId(taskInstance.getId());
		//执行这个恢复实例的命令
		taskService.expandTaskComplete(expandTaskCommandContinueProcessInstance, null);
		//根据实例号得到流程实例
		processInstance = runtimeService.getProcessInstance(processInstanceId);
		
		//验证流程实例已处于非暂停状态
		assertEquals(processInstance.isSuspended(),false);

	}
	
	/**
	 * 测试暂停和恢复流程实例
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn"})
	public void testTermination(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("Process_TaskServiceTest");
		//设置流程的业务关联键
		expandTaskCommand.setBusinessKey("BK_testStartProcessInstanceByKey");
		//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
		expandTaskCommand.setCommandType("startandsubmit");
		//设置提交人
		expandTaskCommand.setInitiator("1200119390");
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		Map<String, Object> mapVariables = new HashMap<String, Object>();
		//设置变量，流程线条上用到，amount<300时走独占任务，否则都共享任务
		mapVariables.put("amount", 280);
		expandTaskCommand.setTransientVariables(mapVariables);
		//执行这个启动并提交的命令，返回启动的流程实例
		ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
		String processInstanceId = processInstance.getId();
		//验证是否成功启动
		assertNotNull(processInstanceId);
		//验证流程启动后实例状态为running(运行中)
		assertEquals(ProcessInstanceType.RUNNING, processInstance.getInstanceType());
		// 创建任务查询
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		//验证更新时间不为空
		assertNotNull(processInstance.getUpdateTime());
		TaskInstance taskInstance = taskInstances.get(0);
		//创建通用命令
		ExpandTaskCommand expandTaskCommandSuspendProcessInstance=new ExpandTaskCommand();
		//设置命令为暂停实例
		expandTaskCommandSuspendProcessInstance.setCommandType("terminationProcess");
		//设置命令按钮的iD,与节点上处理命令设置一致
		expandTaskCommandSuspendProcessInstance.setUserCommandId("HandleCommand_2");
		//设置命令的处理任务号
		expandTaskCommandSuspendProcessInstance.setTaskId(taskInstance.getId());
		//执行这个暂停实例的命令
		taskService.expandTaskComplete(expandTaskCommandSuspendProcessInstance, null);
		//根据实例号得到流程实例
		processInstance = runtimeService.getProcessInstance(processInstanceId);
		//验证实例状态为TERMINATION（终止）
		assertEquals(ProcessInstanceType.TERMINATION, processInstance.getInstanceType());
		//验证更新时间不为空
		assertNotNull(processInstance.getUpdateTime());
		//验证流程实例已经结束
		assertTrue(processInstance.hasEnded());
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		//验证任务已经被结束
		assertEquals(0, taskInstances.size());
	}
	/**
	 * 测试接收任务和释放任务的taskService中的现有方法，不 以命令的形式，TaskServiceCandidateTest流程中的任务分配类型为共享模式
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceCandidateTest.bpmn"})
	public void testClaimMethod(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("TaskServiceCandidateTest");
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
		//未领任务前 独占任务应该为0
		assertEquals(taskInstances.size(), 0);
		//获取1200119390的本流程实例的共享任务
		taskInstances = taskQuery.taskCandidateUser("1200119390").processInstanceId(processInstanceId).taskNotEnd().list(); 
		//验证此时的共享任务为1
		assertEquals(taskInstances.size(), 1);
		
		//获取当前任务节点
		TaskInstance taskInstance = taskInstances.get(0);
		//接收任务，userid不传的话将会去线程副本中获取
		taskService.claim(taskInstance.getId(), null);
		
		//重置任务查询 查询共享
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前共享任务
		taskInstances = taskQuery.taskCandidateUser("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		//领取任务后，共享任务数为0
		assertEquals(taskInstances.size(), 0);
		//重置任务查询 查询独占
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		//领取任务后，独占任务数为1
		assertEquals(taskInstances.size(), 1);
		
		//取得当前任务
		taskInstance = taskInstances.get(0);
		//取得任务所在节点
		String nodeId = taskInstance.getNodeId();
		//验证当前任务是否在第二个节点
		assertEquals(nodeId, "UserTask_4");
		//释放任务
		taskService.release(taskInstance.getId());
		//重置任务查询 查询独占
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		//释放任务后，独占任务数为0
		assertEquals(taskInstances.size(), 0);
		//重置任务查询 查询共享
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前共享任务
		taskInstances = taskQuery.taskCandidateUser("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		//释放任务后，共享任务数为1
		assertEquals(taskInstances.size(), 1);
		//取得当前任务
		taskInstance = taskInstances.get(0);
		//取得任务所在节点
		nodeId = taskInstance.getNodeId();
		//验证当前任务是否在第二个节点
		assertEquals(nodeId, "UserTask_4");
		
	}
	/**
	 * 测试设置代理人---该命令尚未实现
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testSetAssignee(){
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
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		TaskInstance taskInstance = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().get(0);
		
		String taskId = taskInstance.getId();
		taskService.setAssignee(taskId, "121231231");
		taskQuery = taskService.createTaskQuery();
		taskInstance = taskQuery.taskId(taskId).singleResult();
		//assertEquals("121231231", taskInstance.getAssignee());
	}
	/**
	 * 测试查询可退回的任务
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testGetRollBackTask(){
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
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		TaskInstance taskInstance = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().get(0);
		//查询可退回的任务
		List<TaskInstance> taskInstances = taskService.getRollBackTask(taskInstance.getId());
		//验证可退回任务数为1
		assertEquals(1, taskInstances.size());
		//获得可退回的任务
		taskInstance = taskInstances.get(0);
		//验证是否是第一个节点
		assertEquals("UserTask_1",taskInstance.getNodeId());
	}
	
	/**
	 * 测试查询可退回的节点
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testGetRollBackNode(){
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
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		TaskInstance taskInstance = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().get(0);
		//查询可退回的节点
		List<UserTaskBehavior> userTaskBehaviors = taskService.getRollBackNode(taskInstance.getId());
		//验证可退回节点数为1
		assertEquals(1, userTaskBehaviors.size()); 
		//获得可退回的节点
		UserTaskBehavior userTaskBehavior = userTaskBehaviors.get(0);
		//验证是否是第一个节点
		assertEquals("UserTask_1",userTaskBehavior.getId());
	}
	/**
	 * 测试查询获取任务的上一步骤任务对象(非会签情况下上一步骤只有一个任务,会签情况下可能会有多个任务)
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testGetPreviousStepTaskByTaskId(){
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
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		TaskInstance taskInstance = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list().get(0);
		//获取任务的上一步骤任务对象(非会签情况下上一步骤只有一个任务,会签情况下可能会有多个任务)
		List<TaskInstance> taskInstances = taskService.getPreviousStepTaskByTaskId(taskInstance.getId());
		//验证任务数是否为1
		assertEquals(1, taskInstances.size());
		//获得任务
		taskInstance = taskInstances.get(0);
		//验证是否是第一个节点
		assertEquals("UserTask_1",taskInstance.getNodeId());
	}
	
	
	/**
	 * 测试任务的各种查询，任务查询中，除了(共享，独占)和（类别）分别为or关系，其他查询之间均为and关系，taskQuery每次查询都会保存一个条件，所以需要时注意重置查询
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn"})
	public void testTaskQuery(){
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processDefinitionKey("Process_TaskServiceTest").taskNotEnd().list();
		for(int i = 0;i<50;i++){
			//创建一个通用命令
			ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
			//设置流程名
			expandTaskCommand.setProcessDefinitionKey("Process_TaskServiceTest");
			//设置流程的业务关联键
			expandTaskCommand.setBusinessKey("BK_testStartProcessInstanceByKey");
			//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
			expandTaskCommand.setCommandType("startandsubmit");
			//设置提交人
			expandTaskCommand.setInitiator("1200119390");
			//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
			expandTaskCommand.setUserCommandId("HandleCommand_2");
			
			
			Map<String, Object> mapVariables = new HashMap<String, Object>();
			//设置变量，流程线条上用到，amount<300时走独占任务，否则都共享任务
			int amount = 280+i;
			mapVariables.put("amount", amount);
			expandTaskCommand.setTransientVariables(mapVariables);
			//执行这个启动并提交的命令，返回启动的流程实例
			ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
			String processInstanceId = processInstance.getId();
			//验证是否成功启动
			assertNotNull(processInstanceId);
		}
		
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstances = taskQuery.taskAssignee("1200119390").processDefinitionKey("Process_TaskServiceTest").taskNotEnd().list();
		//验证独占任务为20
		assertEquals(20, taskInstances.size());
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		//取得此流程，此用户的共享任务
		List<TaskInstance> taskInstancesCandidate = taskQuery.taskCandidateUser("1200119390").processDefinitionKey("Process_TaskServiceTest").taskNotEnd().list();
		//验证共享任务为30
		assertEquals(30, taskInstancesCandidate.size());
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		//查询 1200119390 的这个流程定义的共享和独占任务（此流程的待办事项）
		List<TaskInstance> taskAssigneeAndCandidate =  taskQuery.taskCandidateUser("1200119390").taskAssignee("1200119390").processDefinitionKey("Process_TaskServiceTest").taskNotEnd().list();
		//验证共享和独占任务的总和为50
		assertEquals(50, taskAssigneeAndCandidate.size());
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		//查询1200119390 的这个流程定义的已办任务
		List<TaskInstance> taskEnd = taskQuery.processDefinitionKey("Process_TaskServiceTest").taskAssignee("1200119390").taskIsEnd().addTaskType(TaskInstanceType.FIXFLOWTASK).list();
		//验证已办任务是否为50
		assertEquals(50, taskEnd.size());
		//取得第一条任务
		TaskInstance taskInstance = taskEnd.get(0);
		//验证任务中的流程定义名称不为空
		assertNotNull(taskInstance.getProcessDefinitionName());
		//测试分页 取1-9条
		taskEnd = taskQuery.listPage(1, 9);
		//验证取到9条任务
		assertEquals(9, taskEnd.size());
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		//获取流程定义名称等于TaskServiceTest的任务实例
		long count = taskQuery.processDefinitionName("TaskServiceTest").count();
		//验证是否为150个
		assertEquals(150, count);
		
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		//获取流程定义名称likeTaskServiceTest的人物实例
		count = taskQuery.processDefinitionNameLike("TaskServiceTest").count();
		//验证是否为150个
		assertEquals(150, count);
		
	}
	
	/**
	 * 测试开始节点的toolbar
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn"})
	public void testStartToolBar(){
		//获取此流程的开始节点可显示的命令
		List<TaskCommandInst> commands = taskService.getSubTaskTaskCommandByKey("Process_TaskServiceTest");
		//验证是否为1个命令，开始节点只配了一个，详见流程定义
		assertEquals(1, commands.size());
		//获取这个命令
		TaskCommandInst taskCommandInst = commands.get(0);
		//验证这个命令是否为启动并提交
		assertEquals("startandsubmit", taskCommandInst.getTaskCommandType());
	}
	
	/**
	 * 测试非开始节点的toolbar
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn"})
	public void testUserTaskToolBar(){
		//获取流程定义查询
		ProcessDefinitionQuery processDefinitionQuery = modelService.createProcessDefinitionQuery();
		//获得发布的流程定义
		ProcessDefinitionBehavior processDefinitionBehavior  = processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest").singleResult();
		//获取此流程的开始节点可显示的命令
		List<TaskCommandInst> commands = taskService.getTaskCommandById(processDefinitionBehavior.getProcessDefinitionId(), "UserTask_3");
		//验证是否为7个命令，详见流程定义
		assertEquals(7, commands.size());
	}
	
	/**
	 * 测试流程追踪的toolbar
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn"})
	public void testProcessTrackingToolBar(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("Process_TaskServiceTest");
		//设置流程的业务关联键
		expandTaskCommand.setBusinessKey("BK_testStartProcessInstanceByKey");
		//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
		expandTaskCommand.setCommandType("startandsubmit");
		//设置提交人
		expandTaskCommand.setInitiator("1200119390");
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		
		
		Map<String, Object> mapVariables = new HashMap<String, Object>();
		//设置变量，流程线条上用到，amount>300时走共享任务
		mapVariables.put("amount", 320);
		expandTaskCommand.setTransientVariables(mapVariables);
		//执行这个启动并提交的命令，返回启动的流程实例
		ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
		String processInstanceId = processInstance.getId();
		//验证是否成功启动
		assertNotNull(processInstanceId);
		
		//创建一个任务查询
		TaskQuery taskQuery = taskService.createTaskQuery();
		//查询上面启动的流程实例
		taskQuery.processInstanceId(processInstanceId);
		//查询已完成的任务，（处理过程）
		TaskInstance taskDone = taskQuery.taskNotEnd().singleResult();
		//获取流程追踪情况下的按钮
		List<TaskCommandInst> commands = taskService.GetTaskCommandByTaskInstance(taskDone, true);
		//验证按钮个数是否为1
		assertEquals(1, commands.size());
		//取得按钮对象
		TaskCommandInst command = commands.get(0);
		//验证按钮是否为追回按钮
		assertEquals("recover",command.getTaskCommandType());
	}
	
	/**
	 * 测试获取处理过程
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn"})
	public void testProcList(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("Process_TaskServiceTest");
		//设置流程的业务关联键
		expandTaskCommand.setBusinessKey("BK_testStartProcessInstanceByKey");
		//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
		expandTaskCommand.setCommandType("startandsubmit");
		//设置提交人
		expandTaskCommand.setInitiator("1200119390");
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		
		
		Map<String, Object> mapVariables = new HashMap<String, Object>();
		//设置变量，流程线条上用到，amount<300时走独占任务，否则都共享任务
		mapVariables.put("amount", 280);
		expandTaskCommand.setTransientVariables(mapVariables);
		//执行这个启动并提交的命令，返回启动的流程实例
		ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
		String processInstanceId = processInstance.getId();
		//验证是否成功启动
		assertNotNull(processInstanceId);
		
		//创建一个任务查询
		TaskQuery taskQuery = taskService.createTaskQuery();
		//查询上面启动的流程实例
		taskQuery.processInstanceId(processInstanceId);
		//查询已完成的任务，（处理过程）
		List<TaskInstance> taskDone = taskQuery.taskIsEnd().list();
		//验证任务数是否为2，启动和开始是两个任务
		assertEquals(2, taskDone.size());
		//重置任务查询
		taskQuery = taskService.createTaskQuery();
		//查询未完成的任务，（当前处理）
		List<TaskInstance> taskNotEnd = taskQuery.processInstanceId(processInstanceId).taskNotEnd().list();
		//验证当前处理任务是否为1
		assertEquals(1, taskNotEnd.size());
	}
	
//	//给邵帅造数据用
//	
//	public void testAdd(){
//		for(int i=0;i<5;i++){
//			//创建一个通用命令
//			ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
//			//设置流程名
//			expandTaskCommand.setProcessDefinitionKey("process_TestToolbar");
//			//设置流程的业务关联键
//			expandTaskCommand.setBusinessKey("BK_testStartProcessInstanceByKey"+i);
//			//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
//			expandTaskCommand.setCommandType("startandsubmit");
//			//设置提交人
//			expandTaskCommand.setInitiator("1200119390");
//			//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
//			expandTaskCommand.setUserCommandId("HandleCommand_2");
//			ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
//			String processInstanceId = processInstance.getId();
//			//验证是否成功启动
//			assertNotNull(processInstanceId);
//			
//			/**************下面代码自动执行通用按钮，使流程结束,如果生成待办则下面不需要****************************/
//			
//			// 创建任务查询
//			TaskQuery taskQuery = taskService.createTaskQuery();
//			// 查找 1200119390 的这个流程实例的当前独占任务
//			List<TaskInstance> taskInstances = taskQuery.taskCandidateUser("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
//			// 获取一条任务
//			TaskInstance taskInstance = taskInstances.get(0);
//			//验证这个待办是否为空
//			assertNotNull(taskInstance);
//			String nodeId = taskInstance.getNodeId();
//			//验证流程实例是否在第二个节点
//			assertEquals(nodeId, "UserTask_2");
//			//创建一个通用命令
//			expandTaskCommand = new ExpandTaskCommand();
//			//设置流程名
//			expandTaskCommand.setProcessDefinitionKey("process_TestToolbar");
//			//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
//			expandTaskCommand.setCommandType("claim");
//			expandTaskCommand.setTaskId(taskInstance.getId());
//			//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
//			expandTaskCommand.setUserCommandId("HandleCommand_3");
//			taskService.expandTaskComplete(expandTaskCommand, null);
//			
//			//创建一个通用命令
//			expandTaskCommand = new ExpandTaskCommand();
//			//设置流程名
//			expandTaskCommand.setProcessDefinitionKey("process_TestToolbar");
//			//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
//			expandTaskCommand.setCommandType("general");
//			expandTaskCommand.setTaskId(taskInstance.getId());
//			//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
//			expandTaskCommand.setUserCommandId("HandleCommand_2");
//			taskService.expandTaskComplete(expandTaskCommand, null);
//		}
//		
//	}
}
