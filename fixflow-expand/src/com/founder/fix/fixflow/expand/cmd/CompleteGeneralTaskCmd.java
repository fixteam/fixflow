package com.founder.fix.fixflow.expand.cmd;

import java.util.Set;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.expand.command.GeneralTaskCommand;

public class CompleteGeneralTaskCmd extends AbstractExpandTaskCmd<GeneralTaskCommand, Void>{
	
	public CompleteGeneralTaskCmd(GeneralTaskCommand abstractCustomExpandTaskCommand) {
		super(abstractCustomExpandTaskCommand);
		// TODO Auto-generated constructor stub
	}

	public Void execute(CommandContext commandContext) {

		
		if (taskId == null||taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}

		TaskManager taskManager = commandContext.getTaskManager();

		TaskInstance taskInstanceQuery = taskManager.findTaskById(taskId);

		String tokenId = taskInstanceQuery.getTokenId();
		String nodeId = taskInstanceQuery.getNodeId();
		String processDefinitionId = taskInstanceQuery.getProcessDefinitionId();
		ProcessInstanceManager processInstanceManager = commandContext.getProcessInstanceManager();

		String processInstanceId = taskInstanceQuery.getProcessInstanceId();

		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

		UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(nodeId);
		
		TaskCommandInst taskCommand=null;
		
		if(this.admin!=null&&!this.admin.equals("")){
			
			String taskCommandName=commandContext.getProcessEngineConfigurationImpl().getTaskCommandDefMap().get(userCommandId).getName();
			
			taskCommand=new TaskCommandInst(userCommandId, taskCommandName, null, userCommandId, true);
			
			
		}
		else{
			taskCommand = userTask.getTaskCommandsMap().get(userCommandId);
		}
		
		


		ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(processInstanceId, processDefinition);

		
		
		processInstanceImpl.getContextInstance().addTransientVariable("fixVariable_userCommand", userCommandId);

		processInstanceImpl.getContextInstance().setVariableMap(variables);
		
		//processInstanceImpl.setBizKey(this.businessKey);

		TokenEntity token = processInstanceImpl.getTokenMap().get(tokenId);
		
		processInstanceImpl.getContextInstance().setTransientVariableMap(transientVariables);

		ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);

		if (taskCommand != null && taskCommand.getExpression() != null) {
			try {
				
				ExpressionMgmt.execute(taskCommand.getExpression(), executionContext);
			} catch (Exception e) {
				throw new FixFlowException("用户命令表达式执行异常!", e);
			}
		}

		Set<TaskInstanceEntity> taskInstances = processInstanceImpl.getTaskMgmtInstance().getTaskInstanceEntitys();

		TaskInstanceEntity taskInstanceImpl=null;
		
		for (TaskInstanceEntity taskInstance : taskInstances) {
			if (taskInstance.getId().equals(taskId)) {
			
				
				taskInstanceImpl=taskInstance;
	
			}
		}
		
		if(taskInstanceImpl!=null){
			taskInstanceImpl.end();
			taskInstanceImpl.setDraft(false);
			taskInstanceImpl.setCommandId(taskCommand.getId());
			taskInstanceImpl.setCommandType(StringUtil.getString(taskCommand.getTaskCommandType()));
			taskInstanceImpl.setCommandMessage(taskCommand.getName());
			taskInstanceImpl.setTaskComment(this.taskComment);
			if(this.admin!=null&&!this.admin.equals("")){
				taskInstanceImpl.setAdmin(this.admin);
			}
			
			if(this.agent!=null&&!this.agent.equals("")){
				taskInstanceImpl.setAgent(Authentication.getAuthenticatedUserId());
			}
			
		}
		else{
			throw new FixFlowException("没有找到id为: "+taskId+" 的任务");
		}
		
		


		try {
			processInstanceManager.saveProcessInstance(processInstanceImpl);
		} catch (Exception e) {
			throw new FixFlowException("流程实例持久化失败!", e);
		}
		return null;
		
	}

}
