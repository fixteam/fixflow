package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;


public class GetRecoverTaskCmd implements Command<List<TaskInstance>>{
	
	protected String taskId;
	
	protected String taskCommandId;
	
	public GetRecoverTaskCmd(String taskId,String taskCommandId)
	{
		this.taskId=taskId;
		this.taskCommandId=taskCommandId;
	}
	
	public List<TaskInstance> execute(CommandContext commandContext) {
		
		
		TaskManager taskManager = commandContext.getTaskManager();

		TaskInstance taskInstanceQuery = taskManager.findTaskById(taskId);
		
		if(taskId==null||taskId.equals("")){
			throw new FixFlowException("taskId不能为空");
		}

		
		if(taskInstanceQuery==null){
			throw new FixFlowException("没有查询到相关任务");
		}
		


		

		ProcessEngine processEngine = ProcessEngineManagement.getDefaultProcessEngine();
		TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery();
		List<TaskInstance> taskInstancesEnd = taskQuery.processInstanceId(taskInstanceQuery.getProcessInstanceId())
				.taskAssignee(Authentication.getAuthenticatedUserId()).taskIsEnd().list();

		String tokenId = taskInstanceQuery.getTokenId();
		String nodeId = taskInstanceQuery.getNodeId();
		String processDefinitionId = taskInstanceQuery.getProcessDefinitionId();
		ProcessInstanceManager processInstanceManager = Context.getCommandContext().getProcessInstanceManager();

		String processInstanceId = taskInstanceQuery.getProcessInstanceId();

		ProcessDefinitionManager processDefinitionManager = Context.getCommandContext().getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

		ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(processInstanceId, processDefinition);

		TokenEntity token = processInstanceImpl.getTokenMap().get(tokenId);

		ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);
		
		
		UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(nodeId);
		
		TaskCommandInst taskCommandInst =userTask.getTaskCommandsMap().get(taskCommandId);
		List<TaskInstance> taskInstances=new ArrayList<TaskInstance>();
		
		

		Object returnValueObject = null;
		if (taskCommandInst != null && taskCommandInst.getExpression() != null) {
			try {

				returnValueObject = ExpressionMgmt.execute(taskCommandInst.getExpression(), executionContext);
			} catch (Exception e) {
				throw new FixFlowException("用户命令表达式执行异常!", e);
			}
		}
		if (returnValueObject == null || returnValueObject.equals("")) {
			return taskInstancesEnd;
		} else {
			String nodeIdString = StringUtil.getString(returnValueObject);
			String[] nodeIdSZ = nodeIdString.split(",");

			for (TaskInstance taskInstanceTemp : taskInstancesEnd) {
				if (isExist(nodeIdSZ, taskInstanceTemp.getNodeId())) {
					taskInstances.add(taskInstanceTemp);
				}
			}
			return taskInstances;
		}
		
		
		
	}
	
	private boolean isExist(String[] contentStrings, String content) {
		for (String string : contentStrings) {
			if (string.equals(content)) {
				return true;
			}
		}
		return false;
	}

	


}
