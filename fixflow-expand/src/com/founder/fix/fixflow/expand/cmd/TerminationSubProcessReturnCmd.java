package com.founder.fix.fixflow.expand.cmd;

import java.util.List;


import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetRollBackTaskCmd;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.expand.command.TerminationSubProcessReturnCommand;

public class TerminationSubProcessReturnCmd extends AbstractExpandTaskCmd<TerminationSubProcessReturnCommand, Void> {

	public TerminationSubProcessReturnCmd(TerminationSubProcessReturnCommand abstractCustomExpandTaskCommand) {
		super(abstractCustomExpandTaskCommand);
	}

	public Void execute(CommandContext commandContext) {

		// 初始化任务命令执行所需要的常用对象
		loadProcessParameter(commandContext);

		// 将外部变量注册到流程实例运行环境中
		addVariable();

		// 执行处理命令中的开发人员设置的表达式
		// runCommandExpression();

		// 获取正在操作的任务实例对象
		TaskInstanceEntity taskInstance = getTaskInstanceEntity();

		// 获取正在操作的任务命令对象实例
		TaskCommandInst taskCommand = getTaskCommandInst();
		
		
		
		
		
		

		

		if (taskInstance != null) {
			// 结束任务
			taskInstance.customEnd(taskCommand, this.taskComment);

		}

		ProcessInstanceEntity processInstanceImpl = getProcessInstance();
		
		String parentProcessTokenId=processInstanceImpl.getParentProcessInstanceTokenId();
		if(StringUtil.isEmpty(parentProcessTokenId)){
			throw new FixFlowException("流程实例没有父流程");
		}
		
		// 终止流程实例
		processInstanceImpl.termination();

		saveProcessInstance(commandContext);

		ProcessInstanceEntity parentProcessInstance = processInstanceImpl.getParentProcessInstance();

		parentProcessInstance.terminationSubProcess(parentProcessTokenId);
		
		

		

		ExecutionContext subExecutionContext = getExecutionContext();

		Object expressionValue = null;
		if (taskCommand != null && taskCommand.getExpression() != null) {
			try {

				expressionValue = ExpressionMgmt.execute(taskCommand.getExpression(), subExecutionContext);

			} catch (Exception e) {
				throw new FixFlowException(taskInstance.getToken().getFlowNode().getName() + " 节点,用户命令表达式执行异常!", e);
			}
		}
		String rollBackNodeId = null;
		if (expressionValue != null && !expressionValue.equals("")) {
			rollBackNodeId = expressionValue.toString();
		} else {
			throw new FixFlowException("没有在表达式中指定退回节点!");
		}
		
		rollBackSubProcess(rollBackNodeId,processInstanceImpl.getId(),parentProcessTokenId,parentProcessInstance,commandContext);
		return null;
		
		
	
	}
	
	private void rollBackSubProcess(String rollBackNodeId,String subProcessInstanceId,String parentProcessTokenId,ProcessInstanceEntity parentProcessInstance,CommandContext commandContext){
		
	
		
		ProcessEngine processEngine=ProcessEngineManagement.getDefaultProcessEngine();
		
		TokenEntity parentToken=parentProcessInstance.getTokenMap().get(parentProcessTokenId);
		
		ExecutionContext parentExecutionContext=ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(parentToken);
		
		
		TaskInstanceEntity taskInstanceCallAct = (TaskInstanceEntity) processEngine.getTaskService().createTaskQuery()
				.callActivityInstanceId(subProcessInstanceId).taskNotEnd().singleResult();
		
		// 获取正在操作的任务命令对象实例
		TaskCommandInst taskCommand = getTaskCommandInst();
		taskInstanceCallAct.customEnd(taskCommand, this.taskComment);
		taskInstanceCallAct.setAssignee(Authentication.getAuthenticatedUserId());
		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity((TaskInstanceEntity) taskInstanceCallAct);

		
		List<TaskInstance> taskInstanceQueryTos = (new GetRollBackTaskCmd(taskInstanceCallAct.getId())).execute(commandContext);
		
		TaskInstance taskInstanceQueryRollBack = null;

		for (TaskInstance taskInstanceQueryTo : taskInstanceQueryTos) {
			if (taskInstanceQueryTo.getNodeId().equals(rollBackNodeId)) {
				taskInstanceQueryRollBack = taskInstanceQueryTo;
				break;
			}
		}
		if (taskInstanceQueryRollBack == null) {
			throw new FixFlowException("主流程没有可以退回的任务!");
		}

		
		


		if (taskInstanceQueryRollBack.getTaskGroup() != null) {

			try {
				
				UserTaskBehavior backNodeUserTask = (UserTaskBehavior) parentExecutionContext.getProcessDefinition().getDefinitions().getElement(rollBackNodeId);

				parentExecutionContext.setToFlowNode(backNodeUserTask);
	
				parentToken.signal(parentExecutionContext);

				
				//taskInstance.toFlowNodeEnd(taskCommand, taskComment, backNodeUserTask, null);

			} catch (Exception e) {

				throw new FixFlowException("任务: " + taskId + " 退回失败!", e);
			}

			try {
				Context.getCommandContext().getProcessInstanceManager().saveProcessInstance(parentProcessInstance);
			} catch (Exception e) {
				throw new FixFlowException("流程实例持久化失败!", e);
			}
	

		} else {

			try {
				
				UserTaskBehavior backNodeUserTask = (UserTaskBehavior) parentExecutionContext.getProcessDefinition().getDefinitions().getElement(rollBackNodeId);

				parentExecutionContext.setToFlowNode(backNodeUserTask);
				
				parentExecutionContext.setRollBackAssignee(taskInstanceQueryRollBack.getAssignee());
				
				parentToken.signal(parentExecutionContext);
				
			
				
			} catch (Exception e) {

				throw new FixFlowException("任务: " + taskId + " 退回失败!", e);
			}

			try {
				Context.getCommandContext().getProcessInstanceManager().saveProcessInstance(parentProcessInstance);
			} catch (Exception e) {
				throw new FixFlowException("流程实例持久化失败!", e);
			}
			

		}
		
	}

}
