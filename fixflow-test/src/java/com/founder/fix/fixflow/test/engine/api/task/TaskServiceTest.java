package com.founder.fix.fixflow.test.engine.api.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.FlowElement;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.GeneralTaskCommand;
import com.founder.fix.fixflow.core.impl.command.RollBackTaskCommand;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.command.SubmitTaskCommand;
import com.founder.fix.fixflow.core.impl.command.TransferTaskCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.Token;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.core.task.UserCommandQueryTo;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class TaskServiceTest extends AbstractFixFlowTestCase {

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/runtime/StartProcessInstanceTest.bpmn" })
	public void testTaskComplete() {

		// 创建流程启动参数对象
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		// 设置流程启动所需要的流程Key(一个流程的key可能有多个不同的流程版本)
		startProcessInstanceCommand.setProcessDefinitionKey("Process_StartProcessInstanceTest");
		// 设置流程的业务关联值
		startProcessInstanceCommand.setBusinessKey("BK_testStartProcessInstanceByKey");
		// 设置流程的启动人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		// 启动流程,按照这个流程Key的最新版本
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		String processInstanceId = processInstance.getId();
		// 验证流程实例是否为空
		assertNotNull(processInstance);

		// 创建任务查询
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 这个用户的10条任务
		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).listPage(1, 10);
		// 获取一条任务
		TaskInstance taskInstance = taskInstances.get(0);
		assertNotNull(taskInstance);
		// 获取新创建的流程实例所对应的流程定义编号
		String processDefinitionId = processInstance.getProcessDefinitionId();
		// 获取任务所在的流程节点编号
		String nodeId = taskInstance.getNodeId();
		// 获取这个任务所有的用户处理命令
		List<TaskCommandInst> userCommandQueryTos = taskService.getTaskCommandById(processDefinitionId, nodeId);
		// 取出默认的第一条
		UserCommandQueryTo userCommandQueryTo = userCommandQueryTos.get(0);

		// 确定为提交类型的处理命令
		assertEquals(userCommandQueryTo.getId(), "Advance_1");
		assertEquals(userCommandQueryTo.getTaskCommandType(), "submit");
		assertEquals(userCommandQueryTo.getName(), "提交");

		// 创建提交型任务处理命令参数对象
		SubmitTaskCommand submitTaskCommand = new SubmitTaskCommand();
		submitTaskCommand.setCommandType("submit");
		submitTaskCommand.setTaskId(taskInstance.getId());
		// 有的时候在启动的时候并不知道BusinessKey只有在任务提交的时候才知道
		submitTaskCommand.setBusinessKey("BK_testStartProcessInstanceByKey");
		// 设置流程提交人,这里的提交人跟启动人是不一样的。
		submitTaskCommand.setInitiator("1200119390");
		// 设置任务处理的意见
		submitTaskCommand.setTaskComment("我提交了这个任务！");
		// 设置对任务发出的处理命令
		submitTaskCommand.setUserCommandId(userCommandQueryTo.getId());

		// 提交第一个任务
		taskService.submitTask(submitTaskCommand);

		// 再第一步提交完毕之后,流程将进入第二步。

		taskQuery = taskService.createTaskQuery();
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).listPage(1, 10);
		// 这个时候1200119390将有两条任务一条是已经完成的提交任务，第二条是还没有完成任务。
		assertEquals(taskInstances.size(), 2);

		// 查询这个人已经完成的任务
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskIsEnd().listPage(1, 10);
		assertEquals(taskInstances.size(), 1);

		// 查询这个人没有完成的任务
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().listPage(1, 10);
		assertEquals(taskInstances.size(), 1);

		// 下面我们来完成这个任务
		TaskInstance taskInstanceNotEnd = taskInstances.get(0);
		// 创建通用型任务处理命令参数对象
		GeneralTaskCommand generalTaskCommand = new GeneralTaskCommand();
		generalTaskCommand.setCommandType("general");
		generalTaskCommand.setTaskComment("这个审批单我同意了!");
		generalTaskCommand.setTaskId(taskInstanceNotEnd.getId());
		generalTaskCommand.setUserCommandId("Normal_1");
		// 完成这个任务
		taskService.commandComplete(generalTaskCommand);

		// 完成这个任务之后流程结束
		ProcessInstance processInstanceQueryTo = runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
		// 查看这个流程实例信息已经结束
		assertEquals(processInstanceQueryTo.getEndTime() != null, true);

	}

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn" })
	public void testTaskVariables() {

		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		startProcessInstanceCommand.setProcessDefinitionKey("Process_TaskServiceTest");
		startProcessInstanceCommand.setBusinessKey("BK_testTaskVariables");
		startProcessInstanceCommand.setStartAuthor("1200119390");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		String processInstanceId = processInstance.getId();
		TaskInstance taskInstance = taskService.createTaskQuery().taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().singleResult();

		// 创建提交型任务处理命令参数对象
		SubmitTaskCommand submitTaskCommand = new SubmitTaskCommand();
		submitTaskCommand.setCommandType("submit");
		submitTaskCommand.setTaskId(taskInstance.getId());
		// 有的时候在启动的时候并不知道BusinessKey只有在任务提交的时候才知道
		submitTaskCommand.setBusinessKey("BK_testTaskVariables");
		// 设置流程提交人,这里的提交人跟启动人是不一样的。
		submitTaskCommand.setInitiator("1200119390");
		// 设置任务处理的意见
		submitTaskCommand.setTaskComment("我提交了这个任务！");
		// 设置对任务发出的处理命令
		submitTaskCommand.setUserCommandId("Advance_1");

		Map<String, Object> mapVariables = new HashMap<String, Object>();
		mapVariables.put("amount", 400);
		// 在提交任务的时候的往流程中注入变量(这个变量将在线条上产生影响,具体请查看流程定义文件。)
		submitTaskCommand.setTransientVariables(mapVariables);

		// 提交第一个任务
		taskService.submitTask(submitTaskCommand);

		// 现在我们来查看流程所在的位置

		// 创建个流程令牌查询
		Token tokenQueryTo = runtimeService.createTokenQuery().processInstanceId(processInstanceId).singleResult();
		// 获取流程主令牌所在的节点
		String nodeId = tokenQueryTo.getNodeId();
		// 获取这个流程的唯一流程编号
		String processDefinitionId = processInstance.getProcessDefinitionId();
		// 创建流程定义查询获取这个流程定义
		ProcessDefinitionBehavior processDefinition = modelService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		// 查询当前流程所在的节点,并获取到他的名称。
		String nodeName = ((FlowElement) processDefinition.getDefinitions().getElement(nodeId)).getName();
		// 根据流程定义 amount 变量大于 300 则流程会走向 UserTask_3节点
		assertEquals(nodeId, "UserTask_3");
		assertEquals(nodeName, "共享任务");

	}

	// RollBackTaskCommand

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn" })
	/**
	 * 领取任务测试
	 */
	public void testClaimTask() {

		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		startProcessInstanceCommand.setProcessDefinitionKey("Process_TaskServiceTest");
		startProcessInstanceCommand.setBusinessKey("BK_testClaimTask");
		startProcessInstanceCommand.setStartAuthor("1200119390");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		String processInstanceId = processInstance.getId();
		TaskInstance taskInstance = taskService.createTaskQuery().taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().singleResult();

		// 创建提交型任务处理命令参数对象
		SubmitTaskCommand submitTaskCommand = new SubmitTaskCommand();
		submitTaskCommand.setCommandType("submit");
		submitTaskCommand.setTaskId(taskInstance.getId());
		// 有的时候在启动的时候并不知道BusinessKey只有在任务提交的时候才知道
		submitTaskCommand.setBusinessKey("BK_testClaimTask");
		// 设置流程提交人,这里的提交人跟启动人是不一样的。
		submitTaskCommand.setInitiator("1200119390");
		// 设置任务处理的意见
		submitTaskCommand.setTaskComment("我提交了这个任务！");
		// 设置对任务发出的处理命令
		submitTaskCommand.setUserCommandId("Advance_1");

		Map<String, Object> mapVariables = new HashMap<String, Object>();
		mapVariables.put("amount", 400);
		// 在提交任务的时候的往流程中注入变量(这个变量将在线条上产生影响,具体请查看流程定义文件。)
		submitTaskCommand.setTransientVariables(mapVariables);

		// 提交第一个任务
		taskService.submitTask(submitTaskCommand);

		// 查询1200119390的共享任务
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskCandidateUser("1200119390").taskNotEnd();

		TaskInstance taskInstanceCandidate = taskQuery.singleResult();

		// 开始领取共享任务
		taskService.claim(taskInstanceCandidate.getId(), "1200119390");

		// 查询1200119390的独占任务
		taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskAssignee("1200119390").taskNotEnd();

		// 确认任务已经被领取过来变成1200119390独占，领取成功。
		TaskInstance taskInstanceAssignee = taskQuery.singleResult();
		assertNotNull(taskInstanceAssignee);

	}

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn" })
	/**
	 * 退回任务测试
	 */
	public void testRollBackTask() {

		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		startProcessInstanceCommand.setProcessDefinitionKey("Process_TaskServiceTest");
		startProcessInstanceCommand.setBusinessKey("BK_testRollBackTask");
		startProcessInstanceCommand.setStartAuthor("1200119390");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		String processInstanceId = processInstance.getId();
		TaskInstance taskInstance = taskService.createTaskQuery().taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().singleResult();

		// 创建提交型任务处理命令参数对象
		SubmitTaskCommand submitTaskCommand = new SubmitTaskCommand();
		submitTaskCommand.setCommandType("submit");
		submitTaskCommand.setTaskId(taskInstance.getId());
		// 有的时候在启动的时候并不知道BusinessKey只有在任务提交的时候才知道
		submitTaskCommand.setBusinessKey("BK_testRollBackTask");
		// 设置流程提交人,这里的提交人跟启动人是不一样的。
		submitTaskCommand.setInitiator("1200119390");
		// 设置任务处理的意见
		submitTaskCommand.setTaskComment("我提交了这个任务！");
		// 设置对任务发出的处理命令
		submitTaskCommand.setUserCommandId("Advance_1");

		Map<String, Object> mapVariables = new HashMap<String, Object>();
		mapVariables.put("amount", 400);
		// 在提交任务的时候的往流程中注入变量(这个变量将在线条上产生影响,具体请查看流程定义文件。)
		submitTaskCommand.setTransientVariables(mapVariables);

		// 提交第一个任务
		taskService.submitTask(submitTaskCommand);

		// 查询1200119390的共享任务
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskCandidateUser("1200119390").taskNotEnd();

		TaskInstance taskInstanceCandidate = taskQuery.singleResult();

		// 开始领取共享任务
		taskService.claim(taskInstanceCandidate.getId(), "1200119390");

		// 查询1200119390的独占任务
		taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskAssignee("1200119390").taskNotEnd();

		// 确认任务已经被领取过来变成1200119390独占，领取成功。
		TaskInstance taskInstanceAssignee = taskQuery.singleResult();

		// 下面来处理这个任务,对其做退回处理

		RollBackTaskCommand rollBackTaskCommand = new RollBackTaskCommand();
		rollBackTaskCommand.setCommandType("rollBack");
		// 退回到第一个提交节点,这种退回操作回将任务退回到用户指定的节点,任务会在节点上重新分配,不会直接分配给原来的处理者。
		rollBackTaskCommand.setRollBackNodeId("UserTask_1");
		// 设置处理意见
		rollBackTaskCommand.setTaskComment("我不同意这个任务!");
		rollBackTaskCommand.setTaskId(taskInstanceAssignee.getId());
		rollBackTaskCommand.setTransientVariables(mapVariables);
		rollBackTaskCommand.setUserCommandId("Advance_1");
		// 执行任务退回
		taskService.rollBackTask(rollBackTaskCommand);

		// 查询1200119390的独占任务
		taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskAssignee("1200119390").taskNotEnd();

		taskInstanceAssignee = taskQuery.singleResult();

		// 确定的确退回到了指定节点
		assertEquals(taskInstanceAssignee.getNodeId(), "UserTask_1");
	}

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn" })
	/**
	 * 转发任务测试
	 */
	public void testTransferTask() {

		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		startProcessInstanceCommand.setProcessDefinitionKey("Process_TaskServiceTest");
		startProcessInstanceCommand.setBusinessKey("BK_testTransferTask");
		startProcessInstanceCommand.setStartAuthor("1200119390");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		String processInstanceId = processInstance.getId();
		TaskInstance taskInstance = taskService.createTaskQuery().taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().singleResult();

		// 创建提交型任务处理命令参数对象
		SubmitTaskCommand submitTaskCommand = new SubmitTaskCommand();
		submitTaskCommand.setCommandType("submit");
		submitTaskCommand.setTaskId(taskInstance.getId());
		// 有的时候在启动的时候并不知道BusinessKey只有在任务提交的时候才知道
		submitTaskCommand.setBusinessKey("BK_testTransferTask");
		// 设置流程提交人,这里的提交人跟启动人是不一样的。
		submitTaskCommand.setInitiator("1200119390");
		// 设置任务处理的意见
		submitTaskCommand.setTaskComment("我提交了这个任务！");
		// 设置对任务发出的处理命令
		submitTaskCommand.setUserCommandId("Advance_1");

		Map<String, Object> mapVariables = new HashMap<String, Object>();
		mapVariables.put("amount", 400);
		// 在提交任务的时候的往流程中注入变量(这个变量将在线条上产生影响,具体请查看流程定义文件。)
		submitTaskCommand.setTransientVariables(mapVariables);

		// 提交第一个任务
		taskService.submitTask(submitTaskCommand);

		// 查询1200119390的共享任务
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskCandidateUser("1200119390").taskNotEnd();

		TaskInstance taskInstanceCandidate = taskQuery.singleResult();

		// 开始领取共享任务
		taskService.claim(taskInstanceCandidate.getId(), "1200119390");

		// 查询1200119390的独占任务
		taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskAssignee("1200119390").taskNotEnd();

		// 确认任务已经被领取过来变成1200119390独占，领取成功。
		TaskInstance taskInstanceAssignee = taskQuery.singleResult();

		// 下面来处理这个任务,对其做转发处理

		TransferTaskCommand transferTaskCommand = new TransferTaskCommand();
		transferTaskCommand.setCommandType("rollBack");
		// 退回到第一个提交节点,这种退回操作回将任务退回到用户指定的节点,任务会在节点上重新分配,不会直接分配给原来的处理者。
		transferTaskCommand.setTransferUserId("123123123");
		// 设置处理意见
		transferTaskCommand.setTaskComment("这个任务我转发给你了哦!");
		// 设置任务转发人
		transferTaskCommand.setTaskId(taskInstanceAssignee.getId());
		transferTaskCommand.setTransientVariables(mapVariables);
		transferTaskCommand.setUserCommandId("Advance_1");
		// 执行任务转发
		taskService.transferTask(transferTaskCommand);

		// 查询123123123的独占任务
		taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskAssignee("123123123").taskNotEnd();

		taskInstanceAssignee = taskQuery.singleResult();

		assertNotNull(taskInstanceAssignee);
	}

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn" })
	/**
	 * 执行扩展任务(退回上一步)
	 */
	public void testExpandTaskRollBackTaskPreviousStep() {

		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		startProcessInstanceCommand.setProcessDefinitionKey("Process_TaskServiceTest");
		startProcessInstanceCommand.setBusinessKey("BK_testExpandTaskRollBackTaskPreviousStep");
		startProcessInstanceCommand.setStartAuthor("1200119390");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		String processInstanceId = processInstance.getId();
		TaskInstance taskInstance = taskService.createTaskQuery().taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().singleResult();

		// 创建提交型任务处理命令参数对象
		SubmitTaskCommand submitTaskCommand = new SubmitTaskCommand();
		submitTaskCommand.setCommandType("submit");
		submitTaskCommand.setTaskId(taskInstance.getId());
		// 有的时候在启动的时候并不知道BusinessKey只有在任务提交的时候才知道
		submitTaskCommand.setBusinessKey("BK_testExpandTaskRollBackTaskPreviousStep");
		// 设置流程提交人,这里的提交人跟启动人是不一样的。
		submitTaskCommand.setInitiator("1200119390");
		// 设置任务处理的意见
		submitTaskCommand.setTaskComment("我提交了这个任务！");
		// 设置对任务发出的处理命令
		submitTaskCommand.setUserCommandId("Advance_1");

		Map<String, Object> mapVariables = new HashMap<String, Object>();
		mapVariables.put("amount", 400);
		// 在提交任务的时候的往流程中注入变量(这个变量将在线条上产生影响,具体请查看流程定义文件。)
		submitTaskCommand.setTransientVariables(mapVariables);

		// 提交第一个任务
		taskService.submitTask(submitTaskCommand);

		// 查询1200119390的共享任务
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskCandidateUser("1200119390").taskNotEnd();

		TaskInstance taskInstanceCandidate = taskQuery.singleResult();

		// 开始领取共享任务
		taskService.claim(taskInstanceCandidate.getId(), "1200119390");

		// 查询1200119390的独占任务
		taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskAssignee("1200119390").taskNotEnd();

		// 确认任务已经被领取过来变成1200119390独占，领取成功。
		TaskInstance taskInstanceAssignee = taskQuery.singleResult();

		// 用户自己创建的处理命令都以这种方式来执行,首先先创建一个扩展命令处理参数对象
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		// 设置需要处理的任务号
		expandTaskCommand.setTaskId(taskInstanceAssignee.getId());
		// 设置用户点击的处理命令号
		expandTaskCommand.setUserCommandId("Advance_3");
		// 处理意见
		expandTaskCommand.setTaskComment("把这个任务退回上一步了！");
		// 处理命令类型(这里的类型请在流程配置里的处理命令扩展里查看那)
		expandTaskCommand.setCommandType("rollBackTaskPreviousStep");
		// 执行处理命令
		taskService.expandTaskComplete(expandTaskCommand, null);

		taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskAssignee("1200119390").taskNotEnd();

		taskInstanceAssignee = taskQuery.singleResult();

		// 任务已经退回到上一个步骤的节点了
		assertNotNull(taskInstanceAssignee.getNodeId(), "UserTask_1");

	}

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn" })
	/**
	 * 执行扩展任务(退回到指定的任务)
	 */
	public void testExpandTaskRollBackTaskByTaskId() {

		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		startProcessInstanceCommand.setProcessDefinitionKey("Process_TaskServiceTest");
		startProcessInstanceCommand.setBusinessKey("BK_testExpandTaskRollBackTaskByTaskId");
		startProcessInstanceCommand.setStartAuthor("1200119390");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		String processInstanceId = processInstance.getId();
		TaskInstance taskInstance = taskService.createTaskQuery().taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().singleResult();
		// 第一个任务号
		String oneTaskInstance = taskInstance.getId();
		// 创建提交型任务处理命令参数对象
		SubmitTaskCommand submitTaskCommand = new SubmitTaskCommand();
		submitTaskCommand.setCommandType("submit");
		submitTaskCommand.setTaskId(taskInstance.getId());
		// 有的时候在启动的时候并不知道BusinessKey只有在任务提交的时候才知道
		submitTaskCommand.setBusinessKey("BK_testExpandTaskRollBackTaskByTaskId");
		// 设置流程提交人,这里的提交人跟启动人是不一样的。
		submitTaskCommand.setInitiator("1200119390");
		// 设置任务处理的意见
		submitTaskCommand.setTaskComment("我提交了这个任务！");
		// 设置对任务发出的处理命令
		submitTaskCommand.setUserCommandId("Advance_1");

		Map<String, Object> mapVariables = new HashMap<String, Object>();
		mapVariables.put("amount", 400);
		// 在提交任务的时候的往流程中注入变量(这个变量将在线条上产生影响,具体请查看流程定义文件。)
		submitTaskCommand.setTransientVariables(mapVariables);

		// 提交第一个任务
		taskService.submitTask(submitTaskCommand);

		// 查询1200119390的共享任务
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskCandidateUser("1200119390").taskNotEnd();

		TaskInstance taskInstanceCandidate = taskQuery.singleResult();

		// 开始领取共享任务
		taskService.claim(taskInstanceCandidate.getId(), "1200119390");

		// 查询1200119390的独占任务
		taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskAssignee("1200119390").taskNotEnd();

		// 确认任务已经被领取过来变成1200119390独占，领取成功。
		TaskInstance taskInstanceAssignee = taskQuery.singleResult();

		// 用户自己创建的处理命令都以这种方式来执行,首先先创建一个扩展命令处理参数对象
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		// 设置需要处理的任务号
		expandTaskCommand.setTaskId(taskInstanceAssignee.getId());
		// 设置用户点击的处理命令号
		expandTaskCommand.setUserCommandId("Advance_4");
		// 处理意见
		expandTaskCommand.setTaskComment("我把这个任务退回给我选中的一个任务了！");
		// 处理命令类型(这里的类型请在流程配置里的处理命令扩展里查看那)
		expandTaskCommand.setCommandType("rollBackTaskByTaskId");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rollBackTaskId", oneTaskInstance);
		// 创建一个RollBackTaskByTaskIdCommand所需要的参数
		expandTaskCommand.setParamMap(map);
		// 执行处理命令
		taskService.expandTaskComplete(expandTaskCommand, null);

		taskQuery = taskService.createTaskQuery();
		taskQuery.processInstanceId(processInstanceId).taskAssignee("1200119390").taskNotEnd();

		taskInstanceAssignee = taskQuery.singleResult();

		// 任务已经退回到指定任务的节点了
		assertNotNull(taskInstanceAssignee.getNodeId(), "UserTask_1");

	}

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn" })
	/**
	 * 独占任务查询(我的代办事宜)
	 */
	public void testTaskAssigneeQuery() {

		// 启动100次流程
		for (int i = 0; i < 100; i++) {
			StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
			startProcessInstanceCommand.setProcessDefinitionKey("Process_TaskServiceTest");
			startProcessInstanceCommand.setBusinessKey("BK_testTaskAssigneeQuery" + i);
			startProcessInstanceCommand.setStartAuthor("1200119390");
			runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);

		}

		TaskQuery taskQuery = taskService.createTaskQuery();

		// 独占、没有结束
		taskQuery.processDefinitionKey("Process_TaskServiceTest").taskAssignee("1200119390").taskNotEnd();

		// 查询出所有独占的任务
		List<TaskInstance> taskInstances = taskQuery.list();

		assertEquals(taskInstances.size(), 100);

	}

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn" })
	/**
	 * 共享任务查询(我的共享任务)
	 */
	public void testTaskCandidateQuery() {

		// 启动100次流程
		for (int i = 0; i < 100; i++) {
			StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
			startProcessInstanceCommand.setProcessDefinitionKey("Process_TaskServiceTest");
			startProcessInstanceCommand.setBusinessKey("BK_testTaskAssigneeQuery" + i);
			startProcessInstanceCommand.setStartAuthor("1200119390");
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
			String processInstanceId = processInstance.getId();
			TaskInstance taskInstance = taskService.createTaskQuery().taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().singleResult();

			// 创建提交型任务处理命令参数对象
			SubmitTaskCommand submitTaskCommand = new SubmitTaskCommand();
			submitTaskCommand.setCommandType("submit");
			submitTaskCommand.setTaskId(taskInstance.getId());
			// 有的时候在启动的时候并不知道BusinessKey只有在任务提交的时候才知道
			submitTaskCommand.setBusinessKey("BK_testTaskAssigneeQuery" + i);
			// 设置流程提交人,这里的提交人跟启动人是不一样的。
			submitTaskCommand.setInitiator("1200119390");
			// 设置任务处理的意见
			submitTaskCommand.setTaskComment("我提交了这个任务！");
			// 设置对任务发出的处理命令
			submitTaskCommand.setUserCommandId("Advance_1");

			Map<String, Object> mapVariables = new HashMap<String, Object>();
			mapVariables.put("amount", 400);
			// 在提交任务的时候的往流程中注入变量(这个变量将在线条上产生影响,具体请查看流程定义文件。)
			submitTaskCommand.setTransientVariables(mapVariables);

			// 提交第一个任务
			taskService.submitTask(submitTaskCommand);
		}

		TaskQuery taskQuery = taskService.createTaskQuery();

		// 共享、没有结束
		taskQuery.processDefinitionKey("Process_TaskServiceTest").taskCandidateUser("1200119390").taskNotEnd();

		// 查询出所有共享的任务
		List<TaskInstance> taskInstances = taskQuery.list();

		assertEquals(taskInstances.size(), 100);

	}

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn" })
	/**
	 * 任务查询高级()
	 */
	public void testTaskQuery() {

		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		startProcessInstanceCommand.setProcessDefinitionKey("Process_TaskServiceTest");
		startProcessInstanceCommand.setBusinessKey("BK_testTaskQuery");
		startProcessInstanceCommand.setStartAuthor("1200119390");
		runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);

		TaskQuery taskQuery = taskService.createTaskQuery();

		// 独占、没有结束
		taskQuery.processDefinitionKey("Process_TaskServiceTest").taskAssignee("1200119390").taskNotEnd();
		taskQuery.processDefinitionName("TaskServiceTest");
		// 查询出所有独占的任务
		List<TaskInstance> taskInstances = taskQuery.list();

		assertEquals(taskInstances.size(), 1);

	}

}
