package com.founder.fix.fixflow.test.bpmn.usertask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class UserTaskTest extends AbstractFixFlowTestCase {
	/**
	 * 测试多实例任务运行(会签)
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/bpmn/usertask/UserTaskTest.bpmn" })
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

		// 多实例运行(会签)变量
		transientVariables.put("输入数据集", "1200119390,test01,test02".split(","));// 模拟接收三个人的数组，
		transientVariables.put("输入项", "");// 把上面存人编号的数组一个个取出放在这个变量里面
		transientVariables.put("输出项", "");// 进行任务处理命令后存的值
		transientVariables.put("输出数据集", new ArrayList<String>());// 多实例运行完毕后存放的List
		//跳过策略变量
		transientVariables.put("跳过策略条件", false);//后面给boolean值就可以
		// 创建一个启动并提交命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		// 设置流程名
		expandTaskCommand.setProcessDefinitionKey("UserTaskTest");
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

		// 是否已经分成两个任务，多实例
		assertEquals(3, taskInstances.size());

		// 循环执行多实例
		for (int i = 0; i < taskInstances.size(); i++) {
			// 创建一个同意命令
			expandTaskCommand = new ExpandTaskCommand();
			// 设置流程名
			expandTaskCommand.setProcessDefinitionKey("UserTaskTest");
			// 设置流程的业务关联键
			expandTaskCommand.setBusinessKey("1234567890");
			// 命令类型，可以从流程引擎配置中查询 启动并提交为通用
			expandTaskCommand.setCommandType("general");
			// 设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
			expandTaskCommand.setUserCommandId("HandleCommand_2");// 不同意是HandleCommand_3
			// 设置任务ID
			expandTaskCommand.setTaskId(taskInstances.get(i).getId());
			// 执行处理命令
			taskService.expandTaskComplete(expandTaskCommand, null);

			if (i == 0) {
				// 第一次循环结束，输出项被赋值同意并且输出数据集里面有一个数据
				assertEquals("同意", Context.getAbstractScriptLanguageMgmt().getVariable("输出项"));
				assertEquals(1, ((List<String>)Context.getAbstractScriptLanguageMgmt().getVariable("输出数据集")).size());
			}
			if (i == 1) {
				// 第二次循环结束，输出项被赋值同意并且输出数据集里面有两个数据
				assertEquals("同意", Context.getAbstractScriptLanguageMgmt().getVariable("输出项"));
				assertEquals(2, ((List<String>)Context.getAbstractScriptLanguageMgmt().getVariable("输出数据集")).size());
			}
			if (i == 2) {
				// 第三次循环结束，输出项被赋值同意并且输出数据集里面有三个数据
				assertEquals("同意", Context.getAbstractScriptLanguageMgmt().getVariable("输出项"));
				assertEquals(3, ((List<String>)Context.getAbstractScriptLanguageMgmt().getVariable("输出数据集")).size());
			}
		}

		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();

		// 是否已经合并成一个任务 如果是不同意大于2个，就直接结束任务
//		assertEquals(0, taskInstances.size());
		
		//如果走完了会合并成一个任务
		assertEquals(1, taskInstances.size());
		
		//如果是同意大于2个，就走到usertask_3节点
		assertEquals("UserTask_3", taskInstances.get(0).getNodeId());
	}
	
	/**
	 * 测试任务主题
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/bpmn/usertask/UserTaskTest.bpmn" })
	public void testTaskSubject() {
		// 流程数据变量
		// 瞬态
		Map<String, Object> transientVariables = new HashMap<String, Object>();
		// 持久化
		// Map<String, Object> Variables = new HashMap<String, Object>();

		// 多实例运行(会签)变量
		transientVariables.put("输入数据集", "1200119390,test01,test02".split(","));// 模拟接收三个人的数组，
		transientVariables.put("输入项", "");// 把上面存人编号的数组一个个取出放在这个变量里面
		transientVariables.put("输出项", "");// 进行任务处理命令后存的值
		transientVariables.put("输出数据集", new ArrayList<String>());// 多实例运行完毕后存放的List
		//跳过策略变量
		transientVariables.put("跳过策略条件", false);//后面给boolean值就可以
		// 创建一个启动并提交命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		// 设置流程名
		expandTaskCommand.setProcessDefinitionKey("UserTaskTest");
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

		//任务主题流程默认给了人工任务测试流程,那就是人工任务测试流程
//		assertEquals("人工任务测试流程", taskInstances.get(0).getDescription());
		//如果有节点上设置的主题，那就用节点上设置的主题
		assertEquals("节点主题", taskInstances.get(0).getDescription());
	}
	
	/**
	 * 测试跳过策略
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/bpmn/usertask/UserTaskTest.bpmn" })
	public void testSkipStrategy() {
		// 流程数据变量
		// 瞬态
		Map<String, Object> transientVariables = new HashMap<String, Object>();
		// 持久化
		// Map<String, Object> Variables = new HashMap<String, Object>();

		// 多实例运行(会签)变量
		transientVariables.put("输入数据集", "1200119390,test01,test02".split(","));// 模拟接收三个人的数组，
		transientVariables.put("输入项", "");// 把上面存人编号的数组一个个取出放在这个变量里面
		transientVariables.put("输出项", "");// 进行任务处理命令后存的值
		transientVariables.put("输出数据集", new ArrayList<String>());// 多实例运行完毕后存放的List
		//跳过策略变量
		transientVariables.put("跳过策略条件", true);//后面给boolean值就可以
		// 创建一个启动并提交命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		// 设置流程名
		expandTaskCommand.setProcessDefinitionKey("UserTaskTest");
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
		//直接跳到任务节点UserTask_4上
		assertEquals("UserTask_4", taskInstances.get(0).getNodeId());
		//查询已经完成的任务
		List<TaskInstance> taskInstances2 = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskIsEnd().list();
		for (TaskInstance taskInstance : taskInstances2) {
			//循环，查询任务类型是FIXBPMTASK的
			if(taskInstance.getTaskInstanceType().toString().equals("FIXBPMTASK")) {
				//查看处理命令类型是不是自动跳过
				assertEquals("skipNode", taskInstance.getCommandType());
			}
		}
	}
}
