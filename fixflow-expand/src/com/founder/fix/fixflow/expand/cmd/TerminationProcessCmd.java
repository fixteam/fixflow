package com.founder.fix.fixflow.expand.cmd;

import java.util.List;

import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
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
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceType;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.expand.command.TerminationProcessCommand;

public class TerminationProcessCmd extends AbstractExpandTaskCmd<TerminationProcessCommand, Void> {

	public TerminationProcessCmd(TerminationProcessCommand abstractCustomExpandTaskCommand) {
		// TODO 自动生成的构造函数存根
		super(abstractCustomExpandTaskCommand);
	}
	
	public Void execute(CommandContext commandContext) {
		// TODO 自动生成的方法存根
		if (taskId == null||taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}
		//获取任务管理器
		TaskManager taskManager = commandContext.getTaskManager();
		//根据指定id查询出任务的TO  不能做改变操作
		TaskInstance taskInstanceQuery = taskManager.findTaskById(taskId);
		
		if(taskInstanceQuery.hasEnded()){
			throw new FixFlowBizException("当前的任务已经结束,无法继续处理!");
		}
		//获取需要的参数
		String tokenId = taskInstanceQuery.getTokenId();
		String nodeId = taskInstanceQuery.getNodeId();
		String processDefinitionId = taskInstanceQuery.getProcessDefinitionId();
		String processInstanceId = taskInstanceQuery.getProcessInstanceId();
		//创建流程实例管理器
		ProcessInstanceManager processInstanceManager = commandContext.getProcessInstanceManager();
		//查询出一个流程对象
		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();
		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);
		//获取任务所在节点对象
		UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(nodeId);
		//创建任务命令对象
		TaskCommandInst taskCommand=null;
		if(this.admin!=null&&!this.admin.equals("")){
			String taskCommandName=commandContext.getProcessEngineConfigurationImpl().getTaskCommandDefMap().get(userCommandId).getName();
			taskCommand=new TaskCommandInst(userCommandId, taskCommandName, null, userCommandId, true);
		}
		else{
			taskCommand = userTask.getTaskCommandsMap().get(userCommandId);
		}
		//没有找到点击按钮则抛出异常
		if(taskCommand==null){
			throw new FixFlowException("未点击任务处理按钮,任务处理失败。");
		}
		//获取流程实例
		ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(processInstanceId, processDefinition);
		//放置当前点击的按钮ID
		processInstanceImpl.getContextInstance().addTransientVariable("fixVariable_userCommand", userCommandId);
		//获取当前任务所在令牌
		TokenEntity token = processInstanceImpl.getTokenMap().get(tokenId);
		//获取任务管理器
		List<TaskInstanceEntity> taskInstances = processInstanceImpl.getTaskMgmtInstance().getTaskInstanceEntitys();
		//获取正在操作的任务的实体
		TaskInstanceEntity taskInstanceImpl=null;
		for (TaskInstanceEntity taskInstance : taskInstances) {
			if (taskInstance.getId().equals(taskId)) {
				taskInstanceImpl=taskInstance;
			}
		}
		//执行按钮表达式
		ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);
		executionContext.setTaskInstance(taskInstanceImpl);
		if (taskCommand != null && taskCommand.getExpression() != null) {
			try {
				
				ExpressionMgmt.execute(taskCommand.getExpression(), executionContext);
			} catch (Exception e) {
				throw new FixFlowException("用户命令表达式执行异常!", e);
			}
		}
		if(taskInstanceImpl!=null){
			//结束任务
			taskInstanceImpl.customEnd(taskCommand, this.taskComment, processDefinitionId, processInstanceId);
			if(this.agent!=null&&!this.agent.equals("")){
				taskInstanceImpl.setAgent(Authentication.getAuthenticatedUserId());
				taskInstanceImpl.setAssigneeWithoutCascade(this.agent);
			}else{
				taskInstanceImpl.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
				taskInstanceImpl.setAgent(null);
			}
			//保存任务
			Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstanceImpl);
		}else{
			throw new FixFlowException("没有找到id为: "+taskId+" 的任务");
		}
		//结束流程实例
		processInstanceImpl.end();
		//更新实例状态为终止
		processInstanceImpl.setInstanceType(ProcessInstanceType.TERMINATION);
		try {
			//持久化实例
			processInstanceManager.saveProcessInstance(processInstanceImpl);
		} catch (Exception e) {
			throw new FixFlowException("流程实例持久化失败!", e);
		}
		return null;
	}
}
