package com.founder.fix.fixflow.test.bpmn.gateway;

import java.util.List;


import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.Token;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class ParallelGatewayTest extends AbstractFixFlowTestCase {

	@Deployment(resources = { "com/founder/fix/fixflow/test/bpmn/gateway/ParallelGatewayTest.bpmn" })
	public void testDeployment() {
		ProcessDefinitionBehavior processDefinition = modelService.createProcessDefinitionQuery().processDefinitionKey("ParallelGatewayTest")
				.singleResult();
		assertNotNull(processDefinition);
	}

	public void testRunProcessInstance() {

		//启动测试流程
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		startProcessInstanceCommand.setProcessDefinitionKey("ParallelGatewayTest");
		startProcessInstanceCommand.setBusinessKey("1234567890");
		startProcessInstanceCommand.setStartAuthor("1200119390");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		assertNotNull(processInstance);

	}

	public void testForkToken() {
		
		ProcessInstance processInstance =runtimeService.createProcessInstanceQuery().processInstanceBusinessKey("1234567890", "ParallelGatewayTest").singleResult();
		List<Token> tokenQueryToList=runtimeService.createTokenQuery().processInstanceId(processInstance.getId()).list();
		assertEquals(tokenQueryToList.size(), 1);
		
		TaskInstance taskInstanceQueryTo=taskService.createTaskQuery().taskAssignee("1200119390").processInstanceId(processInstance.getId()).singleResult();
		
		//完成第一步提交任务
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		expandTaskCommand.setTaskId(taskInstanceQueryTo.getId());
		expandTaskCommand.setUserCommandId("general1");
		expandTaskCommand.setTaskComment("流程提交!");
		taskService.expandTaskComplete(expandTaskCommand, null);
		//这时应该有3个令牌
		tokenQueryToList=runtimeService.createTokenQuery().processInstanceId(processInstance.getId()).list();
		assertEquals(tokenQueryToList.size(), 3);
		//2个任务
		List<TaskInstance> taskInstanceQueryToList=taskService.createTaskQuery().taskAssignee("1200119390").taskNotEnd().processInstanceId(processInstance.getId()).list();
		assertEquals(taskInstanceQueryToList.size(), 2);
		
		//完成第一个分支任务
		expandTaskCommand = new ExpandTaskCommand();
		expandTaskCommand.setTaskId(taskInstanceQueryToList.get(0).getId());
		expandTaskCommand.setUserCommandId("general1");
		expandTaskCommand.setTaskComment("分支处理完毕!");
		taskService.expandTaskComplete(expandTaskCommand, null);
		
		//完成第二个分支任务
		expandTaskCommand = new ExpandTaskCommand();
		expandTaskCommand.setTaskId(taskInstanceQueryToList.get(1).getId());
		expandTaskCommand.setUserCommandId("general1");
		expandTaskCommand.setTaskComment("分支处理完毕!");
		taskService.expandTaskComplete(expandTaskCommand, null);
		
		
		//这时令牌应该只剩下一个
		tokenQueryToList=runtimeService.createTokenQuery().processInstanceId(processInstance.getId()).tokenNotEnd().list();
		assertEquals(tokenQueryToList.size(), 1);
		
		
	}

}
