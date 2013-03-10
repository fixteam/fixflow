package com.founder.fix.fixflow.expand.cmd;


import java.util.List;


import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.expand.command.StartAndSubmitTaskCommand;

public class StartAndSubmitTaskCmd extends AbstractExpandTaskCmd<StartAndSubmitTaskCommand, ProcessInstance>{

	

	
	public StartAndSubmitTaskCmd(StartAndSubmitTaskCommand startAndSubmitTaskCommand) {
		super(startAndSubmitTaskCommand);
	
	}

	public ProcessInstance execute(CommandContext commandContext) {
		
		

		
		ProcessEngine processEngine =ProcessEngineManagement.getDefaultProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		// runtimeService.getCommandExecutor().setConnection(dbgr.getConnection());
		TaskService taskService = processEngine.getTaskService();
		if(this.taskId!=null&&!this.taskId.equals("")){
			
			
			//TaskInstance taskInstance=commandContext.getTaskManager().findTaskById(this.taskId);
			ExpandTaskCommand expandTaskCommand=new ExpandTaskCommand();
			expandTaskCommand.setCommandType("submit");
			expandTaskCommand.setTaskComment(this.taskComment);
			expandTaskCommand.setTaskId(this.taskId);
			expandTaskCommand.setUserCommandId(this.userCommandId);
			expandTaskCommand.setTransientVariables(transientVariables);
			expandTaskCommand.setBusinessKey(businessKey);
			expandTaskCommand.setInitiator(initiator);
			if(this.agent!=null&&!this.agent.equals("")){
				expandTaskCommand.setAgent(this.agent);
			}
			taskService.expandTaskComplete(expandTaskCommand, null);
			return null;
			
		}else{
			StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
			startProcessInstanceCommand.setProcessDefinitionKey(processDefinitionKey);
			startProcessInstanceCommand.setBusinessKey(businessKey);
			startProcessInstanceCommand.setStartAuthor(initiator);
			startProcessInstanceCommand.setTransientVariables(transientVariables);
			// startProcessInstanceCommand.setVariables(Variables);
			ProcessInstance processInstanceQueryTo = runtimeService
					.noneStartProcessInstance(startProcessInstanceCommand);

			// 任务第一步提交完还需找到一个待办事宜再执行掉才算真正完成
			String processInstanceId = processInstanceQueryTo.getId();

			
			TaskQuery taskQuery = taskService.createTaskQuery()
					.processInstanceId(processInstanceId);

			//先去找独占任务没有的话就去找共享任务并完成他
			List<TaskInstance> taskQueryList = taskQuery.taskAssignee(initiator).taskNotEnd()
					.list();

			for (TaskInstance instanceQueryTo : taskQueryList) {
				String nodeId = instanceQueryTo.getNodeId();
				String processDefinitionId = instanceQueryTo
						.getProcessDefinitionId();
				List<TaskCommandInst> commandList = taskService.getTaskCommandById(processDefinitionId, nodeId);
				
				TaskCommandInst userCommand = commandList.get(0);
				
				
				ExpandTaskCommand expandTaskCommand=new ExpandTaskCommand();
				expandTaskCommand.setCommandType("submit");
				expandTaskCommand.setTaskComment(this.taskComment);
				expandTaskCommand.setTaskId(instanceQueryTo.getId());
				expandTaskCommand.setUserCommandId(userCommand.getId());
				expandTaskCommand.setTransientVariables(transientVariables);
				expandTaskCommand.setBusinessKey(businessKey);
				expandTaskCommand.setInitiator(initiator);

				if(this.agent!=null&&!this.agent.equals("")){
					expandTaskCommand.setAgent(this.agent);
				}
			

				taskService.expandTaskComplete(expandTaskCommand, null);
			}
			
			
			
			if (taskQueryList.size() <= 0) {
				TaskQuery taskQueryNew=taskService.createTaskQuery().processInstanceId(processInstanceId);
				List<TaskInstance> taskQueryCandidateList = taskQueryNew.taskCandidateUser(initiator).taskNotEnd().list();

				for (TaskInstance instanceQueryTo : taskQueryCandidateList) {

					
					
					ExpandTaskCommand expandTaskCommandClaim=new ExpandTaskCommand();
					expandTaskCommandClaim.setCommandType("claim");
					expandTaskCommandClaim.setTaskId(instanceQueryTo.getId());
					taskService.expandTaskComplete(expandTaskCommandClaim, null);
					
					//taskService.claim(instanceQueryTo.getId(), initiator);
					
					//String nodeId = instanceQueryTo.getNodeId();
					//String processDefinitionId = instanceQueryTo
					//		.getProcessDefinitionId();
					//List<TaskCommandInst> commandList = taskService
					//		.getTaskCommandById(processDefinitionId, nodeId);
					
					//TaskCommandInst userCommand = commandList.get(0);
					
					ExpandTaskCommand expandTaskCommand=new ExpandTaskCommand();
					expandTaskCommand.setCommandType("submit");
					expandTaskCommand.setTaskComment(this.taskComment);
					expandTaskCommand.setTaskId(instanceQueryTo.getId());
					expandTaskCommand.setUserCommandId(this.userCommandId);
					expandTaskCommand.setTransientVariables(transientVariables);
					expandTaskCommand.setBusinessKey(businessKey);
					expandTaskCommand.setInitiator(initiator);
					
					if(this.agent!=null&&!this.agent.equals("")){
						expandTaskCommand.setAgent(this.agent);
					}
				
				

					taskService.expandTaskComplete(expandTaskCommand, null);

				}
				
				
			}
			return processInstanceQueryTo;
		}
		
		
		
	}

}
