package com.founder.fix.fixflow.test.engine.api.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskInstanceType;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class TaskServiceNewTest extends AbstractFixFlowTestCase {
	
	/**
	 * 测试启动并提交，手工启动流程。
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/flow_test.bpmn"})
	public void testStartAndSubmit(){
		
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("flow_test");
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
		assertEquals(nodeId, "UserTask_3");
	}
	
	/**
	 * 测试提交命令，提交和启动并提交的区别在于：1.第一个节点是共享：则启动并提交=启动+接收任务+提交 2.第一个节点是独占：则启动并提交=启动+提交
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/flow_test.bpmn"})
	public void testSubmit(){
		
		//启动流程命令
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		//设置流程编号
		startProcessInstanceCommand.setProcessDefinitionKey("flow_test");
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
		assertEquals(nodeId, "UserTask_3");
	}
	
	/**
	 * 测试接收任务和释放任务，flow_test2流程中的任务分配类型为共享模式
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/flow_test2.bpmn"})
	public void testClaim(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("flow_test2");
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
		//设置命令未领取任务
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
		assertEquals(nodeId, "UserTask_3");
		
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
		assertEquals(nodeId, "UserTask_3");
		
	}
	/**
	 * 测试退回上一步
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/flow_test.bpmn"})
	public void testRollBackTaskPreviousStep(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("flow_test");
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
		assertEquals(nodeId, "UserTask_3");
		
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
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/flow_test.bpmn"})
	public void testRollBackTaskByExpression(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("flow_test");
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
		assertEquals(nodeId, "UserTask_3");
		
		//创建通用命令
		ExpandTaskCommand expandTaskCommandRollBack=new ExpandTaskCommand();
		//设置命令为退回上一步
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
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/flow_test.bpmn"})
	public void testRollBack(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("flow_test");
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
		assertEquals(nodeId, "UserTask_3");
		
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
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/flow_test.bpmn"})
	public void testRollBackTaskByTaskId(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("flow_test");
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
		assertEquals(nodeId, "UserTask_3");
		
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
	
}
