/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.expand.cmd;

import java.util.List;

import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
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

		
		if(Authentication.getAuthenticatedUserId()==null||Authentication.getAuthenticatedUserId().equals("")){
			throw new FixFlowException("登录用户不能!");
		}
		
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
		
		
		
		
		
		if(taskCommand==null){
			throw new FixFlowException("未点击任务处理按钮,任务处理失败。");
		}


		//获取流程实例
		ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(processInstanceId, processDefinition);

		
		//放置当前点击的按钮ID
		processInstanceImpl.getContextInstance().addTransientVariable("fixVariable_userCommand", userCommandId);
		//放置持久化变量
		processInstanceImpl.getContextInstance().setVariableMap(variables);
		
		//processInstanceImpl.setBizKey(this.businessKey);

		//获取当前任务所在令牌
		TokenEntity token = processInstanceImpl.getTokenMap().get(tokenId);
		
		//放置瞬态变量
		processInstanceImpl.getContextInstance().setTransientVariableMap(transientVariables);

		
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
			//判断是否是自动处理
			if(AbstractCommandFilter.isAutoClaim()){
				taskInstanceImpl.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
			}
			//调用任务的完成方法
			taskInstanceImpl.end();
			//设置是否为草稿
			taskInstanceImpl.setDraft(false);
			//设置任务上点击的处理命令
			taskInstanceImpl.setCommandId(taskCommand.getId());
			//设置任务上点击的处理命令类型
			taskInstanceImpl.setCommandType(StringUtil.getString(taskCommand.getTaskCommandType()));
			//设置任务上点击的处理命令文本
			taskInstanceImpl.setCommandMessage(taskCommand.getName());
			//处理意见
			taskInstanceImpl.setTaskComment(this.taskComment);
			
			if(this.admin!=null&&!this.admin.equals("")){
				taskInstanceImpl.setAdmin(this.admin);
			}
			
			
			if(this.agent!=null&&!this.agent.equals("")){
				taskInstanceImpl.setAgent(Authentication.getAuthenticatedUserId());
				taskInstanceImpl.setAssigneeWithoutCascade(this.agent);
			}else{
				taskInstanceImpl.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
				taskInstanceImpl.setAgent(null);
			}
			//if(this.agent!=null&&!this.agent.equals("")){
			//	taskInstanceImpl.setAgent(Authentication.getAuthenticatedUserId());
			//}
			
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
