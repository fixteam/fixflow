package com.founder.fix.fixflow.test.simulationrun;

import java.util.List;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.command.SubmitTaskCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;

public class FixRun extends AbstractFixFlowTestCase{
	
	static ProcessDefinitionBehavior processDefinition;
	
	
	public void testDeployment() {
		//获取流程定义
		processDefinition = modelService.createProcessDefinitionQuery().processDefinitionKey("ProcessTest")
				.singleResult();
		
		assertNotNull(processDefinition);
	}
	
	public void testGetStartForm() {
		//获取启动表单
		String formUri=processDefinition.getStartFormKey();
		assertNotNull(formUri);
	
	}
	
	
	public void testGetSubTask(){
		//获取任务节点对象
		UserTaskBehavior userTaskBehavior=(UserTaskBehavior)processDefinition.getSubTask();
		assertNotNull(userTaskBehavior);
		//获取任务节点处理命令
		List<TaskCommandInst> taskCommands=userTaskBehavior.getTaskCommands();
		assertEquals(taskCommands.size(),1);

	}
	
	
	public void testSubFlow(){
		
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		startProcessInstanceCommand.setProcessDefinitionKey("ProcessTest");
		startProcessInstanceCommand.setBusinessKey("1234567890");
		startProcessInstanceCommand.setStartAuthor("1200119390");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		assertNotNull(processInstance);
		
		TaskInstance taskInstanceQueryTo=taskService.createTaskQuery().taskAssignee("1200119390").processInstanceId(processInstance.getId()).singleResult();
		
		//完成第一步提交任务
		SubmitTaskCommand submitTaskCommand = new SubmitTaskCommand();
		submitTaskCommand.setTaskId(taskInstanceQueryTo.getId());
		submitTaskCommand.setUserCommandId("Advance_1");
		submitTaskCommand.setTaskComment("流程提交!");
		submitTaskCommand.setBusinessKey("1234567890");
		submitTaskCommand.setInitiator("1200119390");
		taskService.submitTask(submitTaskCommand);
		
		List<TaskInstance> taskInstanceQueryTos=taskService.createTaskQuery().taskAssignee("1200119390").processInstanceId(processInstance.getId()).list();
		assertEquals(taskInstanceQueryTos.size(),1);
		
	}
	


	

}
