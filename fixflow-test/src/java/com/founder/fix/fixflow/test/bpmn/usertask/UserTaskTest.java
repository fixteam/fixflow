package com.founder.fix.fixflow.test.bpmn.usertask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class UserTaskTest extends AbstractFixFlowTestCase {
	@Deployment(resources = { "com/founder/fix/fixflow/test/bpmn/usertask/UserTaskTest.bpmn" })
	public void testSequenceFlow() {
		// 拿到流程定义
		ProcessDefinitionBehavior processDefinition = modelService.createProcessDefinitionQuery().processDefinitionKey("SequenceFlowTest").singleResult();
		// 测试是否为空
		assertNotNull(processDefinition);

		// 流程数据变量
		// 瞬态
		Map<String, Object> transientVariables = new HashMap<String, Object>();
		// 持久化
		// Map<String, Object> Variables = new HashMap<String, Object>();

//		transientVariables.put("test", "123");

		// 设置流程实例参数
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		// 设置流程ID
		startProcessInstanceCommand.setProcessDefinitionKey("UserTaskTest");
		// 设置流程业务关联键
		startProcessInstanceCommand.setBusinessKey("1234567890");
		// 设置启动提交人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		// 设置数据变量
		startProcessInstanceCommand.setTransientVariables(transientVariables);

		// 启动流程，只启动流程 ，流程停在第一步
		ProcessInstance processInstanceQueryTo = runtimeService.noneStartProcessInstance(startProcessInstanceCommand);
		String processInstanceId = processInstanceQueryTo.getId();
		// 验证是否成功启动
		assertNotNull(processInstanceId);
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		List<TaskInstance> taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		// 获取一条任务
		TaskInstance taskInstance = taskInstances.get(0);
		String nodeId = taskInstance.getNodeId();
		// 验证流程实例是否在第一个节点
		assertEquals(nodeId, "UserTask_1");

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
		
		//执行这个启动并提交的命令，返回启动的流程实例
		ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
		processInstanceId = processInstance.getId();
		//验证是否成功启动
		assertNotNull(processInstanceId);
		
		taskQuery = taskService.createTaskQuery();
		// 查找 1200119390 的这个流程实例的当前独占任务
		taskInstances = taskQuery.taskAssignee("1200119390").processInstanceId(processInstanceId).taskNotEnd().list();
		
		assertEquals(0, taskInstances.size());
		
		// 验证是否成功启动
//		taskInstance = taskInstances.get(0);
		
//		nodeId = taskInstance.getNodeId();
		// 验证当前任务是否在第二个节点
//		assertEquals(nodeId, "UserTask_2");
		
	}
}
