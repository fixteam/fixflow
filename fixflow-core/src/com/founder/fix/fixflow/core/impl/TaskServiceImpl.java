package com.founder.fix.fixflow.core.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.bpmn2extensions.coreconfig.Priority;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.cmd.AddCommentCmd;
import com.founder.fix.fixflow.core.impl.cmd.CompleteGeneralTaskSimulationRunCmd;
import com.founder.fix.fixflow.core.impl.cmd.DeleteIdentityLinkCmd;
import com.founder.fix.fixflow.core.impl.cmd.DeleteTaskCmd;
import com.founder.fix.fixflow.core.impl.cmd.ExpandCommonCmd;
import com.founder.fix.fixflow.core.impl.cmd.ExpandTaskComplete;
import com.founder.fix.fixflow.core.impl.cmd.GetAgentUsersAndCountCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetAgentUsersCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetNextTaskCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetPreviousStepTaskByTaskIdCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetPriorityCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetProcessInstanceCommentsCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetRollBackNodeCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetRollBackScreeningTaskCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetRollBackTaskCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetSubTaskUserCommandCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetSystemTaskCommandCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetTaskCommandByTaskIdCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetTaskCommandByTaskInstanceCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetTaskCommentsCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetTaskStatusByByProcessInstanceIdCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetTaskUsersByTaskIdCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetUserCommandCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetUserEndTaskNodesInProcessInstanceCmd;
import com.founder.fix.fixflow.core.impl.cmd.ProcessPerformanceInterface3Cmd;
import com.founder.fix.fixflow.core.impl.cmd.QueryVariablesCmd;
import com.founder.fix.fixflow.core.impl.cmd.SaveIdentityLinkCmd;
import com.founder.fix.fixflow.core.impl.cmd.SaveTaskCmd;
import com.founder.fix.fixflow.core.impl.cmd.SaveTaskDraftCmd;
import com.founder.fix.fixflow.core.impl.cmd.SaveVariablesCmd;
import com.founder.fix.fixflow.core.impl.cmd.TransferTaskCmd;
import com.founder.fix.fixflow.core.impl.command.AbstractCustomExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.GeneralTaskCommand;
import com.founder.fix.fixflow.core.impl.command.QueryVariablesCommand;
import com.founder.fix.fixflow.core.impl.command.SaveTaskDraftCommand;
import com.founder.fix.fixflow.core.impl.command.SaveVariablesCommand;
import com.founder.fix.fixflow.core.impl.command.TransferTaskCommand;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.runtime.IdentityLinkQueryImpl;
import com.founder.fix.fixflow.core.impl.task.IdentityLinkEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.task.TaskQueryImpl;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.runtime.IdentityLinkQuery;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.CommentQueryTo;
import com.founder.fix.fixflow.core.task.IdentityLink;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.core.task.UserCommandQueryTo;

public class TaskServiceImpl extends ServiceImpl implements TaskService {

	public TaskInstance newTask() {

		String guidString=GuidUtil.CreateGuid();
		return newTask(guidString);

	}

	public TaskInstance newTask(String taskId) {

		TaskInstance newTask = TaskInstanceEntity.create();
		newTask.setId(taskId);
		return newTask;
	}

	public void saveTask(TaskInstance task) {
		commandExecutor.execute(new SaveTaskCmd(task));
	}

	public void deleteTask(String taskId) {
		commandExecutor.execute(new DeleteTaskCmd(taskId, false));

	}

	public void deleteTasks(Collection<String> taskIds) {
		commandExecutor.execute(new DeleteTaskCmd(taskIds, false));
	}

	public void deleteTask(String taskId, boolean cascade) {
		commandExecutor.execute(new DeleteTaskCmd(taskId, cascade));
	}

	public void deleteTasks(Collection<String> taskIds, boolean cascade) {
		commandExecutor.execute(new DeleteTaskCmd(taskIds, cascade));
	}

	public TaskQuery createTaskQuery() {
		return new TaskQueryImpl(commandExecutor);
	}

	public IdentityLinkQuery createIdentityLinkQuery() {

		return new IdentityLinkQueryImpl(commandExecutor);
	}

	public List<UserCommandQueryTo> getSubTaskUserCommandByKey(String processDefinitionKey) {
		return commandExecutor.execute(new GetSubTaskUserCommandCmd<UserCommandQueryTo>(processDefinitionKey));
	}

	public List<UserCommandQueryTo> getUserCommandById(String processDefinitionId, String nodeId) {
		return commandExecutor.execute(new GetUserCommandCmd<UserCommandQueryTo>(processDefinitionId, nodeId));
	}
	
	
	public List<TaskCommandInst> getTaskCommandById(String processDefinitionId, String nodeId) {
		return commandExecutor.execute(new GetUserCommandCmd<TaskCommandInst>(processDefinitionId, nodeId));	
	}

	public List<TaskCommandInst> getSubTaskTaskCommandByKey(String processDefinitionKey) {
		return commandExecutor.execute(new GetSubTaskUserCommandCmd<TaskCommandInst>(processDefinitionKey));
	}



	public void complete(String taskId) {
		// TODO Auto-generated method stub

	}

	public void complete(String taskId, Map<String, Object> variables) {
		// TODO Auto-generated method stub

	}
	
	public void claim(String taskId, String claimUserId) {
		// TODO 自动生成的方法存根
		
		ExpandTaskCommand expandTaskCommandClaim=new ExpandTaskCommand();
		expandTaskCommandClaim.setCommandType("claim");
		expandTaskCommandClaim.setTaskId(taskId);
		this.expandTaskComplete(expandTaskCommandClaim, null);
		
		
	}


	public void release(String taskId){
		ExpandTaskCommand expandTaskCommandClaim=new ExpandTaskCommand();
		expandTaskCommandClaim.setCommandType("releaseTask");
		expandTaskCommandClaim.setTaskId(taskId);
		this.expandTaskComplete(expandTaskCommandClaim, null);
	}

	public void transferTask(TransferTaskCommand transferTaskCommand) {

		commandExecutor.execute(new TransferTaskCmd(transferTaskCommand));
	}



	public <T> T expandTaskComplete(ExpandTaskCommand expandTaskCommand, T classReturn) {

		return (T) commandExecutor.execute(new ExpandTaskComplete<AbstractCustomExpandTaskCommand, T>(expandTaskCommand));

	}

	public void setAssignee(String taskId, String userId) {
		// TODO Auto-generated method stub

	}

	public void setOwner(String taskId, String userId) {
		// TODO Auto-generated method stub

	}

	public void addComment(String taskId, String processInstanceId, String message, String fullMessage) {
		commandExecutor.execute(new AddCommentCmd(taskId, processInstanceId, message, fullMessage));

	}

	public List<CommentQueryTo> getTaskComments(String taskId) {
		return commandExecutor.execute(new GetTaskCommentsCmd(taskId));
	}

	public List<CommentQueryTo> getProcessInstanceComments(String processInstanceId) {
		return commandExecutor.execute(new GetProcessInstanceCommentsCmd(processInstanceId));
	}

	public List<TaskInstance> getRollBackTask(String taskId) {
		return commandExecutor.execute(new GetRollBackTaskCmd(taskId));
	}

	public List<UserTaskBehavior> getRollBackNode(String taskId) {
		return commandExecutor.execute(new GetRollBackNodeCmd(taskId));
	}

	public void saveTaskDraft(SaveTaskDraftCommand saveTaskDraftCommand) {
		commandExecutor.execute(new SaveTaskDraftCmd(saveTaskDraftCommand));
	}


	/**
	 * 模拟提交任务
	 * 
	 * @param generalTaskCommand
	 */
	public ProcessInstance commandCompleteSimulationRun(GeneralTaskCommand generalTaskCommand) {
		return commandExecutor.execute(new CompleteGeneralTaskSimulationRunCmd(generalTaskCommand));
	}

	/**
	 * 根据流程实例编号获取任务状态
	 */
	public List<Map<String, Object>> getTaskStatusByByProcessInstanceId(List<String> processInstanceIdList) {
		return commandExecutor.execute(new GetTaskStatusByByProcessInstanceIdCmd(processInstanceIdList));
	}

	public List<TaskInstance> getPreviousStepTaskByTaskId(String taskId) {
		return commandExecutor.execute(new GetPreviousStepTaskByTaskIdCmd(taskId));
	}

	public List<TaskInstance> getRollBackTaskByTaskId(String taskId) {
		return commandExecutor.execute(new GetRollBackTaskCmd(taskId));
	}

	public void setVariable(String taskId, String variableName, Object value) {

		SaveVariablesCommand saveVariablesCommand = new SaveVariablesCommand();
		saveVariablesCommand.setTaskInstanceId(taskId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(variableName, value);
		saveVariablesCommand.setVariables(map);
		commandExecutor.execute(new SaveVariablesCmd(saveVariablesCommand));

	}

	public void setVariables(String taskId, Map<String, ? extends Object> variables) {
		SaveVariablesCommand saveVariablesCommand = new SaveVariablesCommand();
		saveVariablesCommand.setTaskInstanceId(taskId);
		saveVariablesCommand.setVariables(variables);
		commandExecutor.execute(new SaveVariablesCmd(saveVariablesCommand));
	}

	public Object getVariable(String taskId, String variableName) {
		QueryVariablesCommand queryVariablesCommand = new QueryVariablesCommand();
		queryVariablesCommand.setTaskInstanceId(taskId);
		List<String> variableNames = new ArrayList<String>();
		variableNames.add(variableName);
		queryVariablesCommand.setVariableNames(variableNames);
		Map<String, Object> map = commandExecutor.execute(new QueryVariablesCmd<Map<String, Object>>(queryVariablesCommand));
		return map.get(variableName);
	}

	public Map<String, Object> getVariables(String taskId) {
		QueryVariablesCommand queryVariablesCommand = new QueryVariablesCommand();
		queryVariablesCommand.setTaskInstanceId(taskId);
		Map<String, Object> map = commandExecutor.execute(new QueryVariablesCmd<Map<String, Object>>(queryVariablesCommand));
		return map;
	}

	public Map<String, Object> getVariables(String taskId, Collection<String> variableNames) {
		QueryVariablesCommand queryVariablesCommand = new QueryVariablesCommand();
		queryVariablesCommand.setTaskInstanceId(taskId);
		queryVariablesCommand.setVariableNames(variableNames);
		Map<String, Object> map = commandExecutor.execute(new QueryVariablesCmd<Map<String, Object>>(queryVariablesCommand));
		return map;
	}

	public IdentityLink newIdentityLink() {
		String guidString=GuidUtil.CreateGuid();
		return newIdentityLink(guidString);
		
	}

	public IdentityLink newIdentityLink(String linkId) {
		
		IdentityLink identityLink = new IdentityLinkEntity(linkId);	
		return identityLink;
		
	}

	public void saveIdentityLink(IdentityLink identityLink) {
		commandExecutor.execute(new SaveIdentityLinkCmd(identityLink));
	}

	public void deleteIdentityLink(String linkId) {
		commandExecutor.execute(new DeleteIdentityLinkCmd(linkId));
	}

	public void deleteIdentityLink(Collection<String> linkIds) {
		for (String linkId : linkIds) {
			commandExecutor.execute(new DeleteIdentityLinkCmd(linkId));
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String, Object>> processPerformance(String[] pid, String startTime, String endTime, String type) {
		// TODO Auto-generated method stub
		if(type.equals("person")) {
			return commandExecutor.execute(new ProcessPerformanceInterface3Cmd(pid, startTime, endTime));
		}
		if(type.equals("org")) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("starttime", startTime);
			map.put("endtime", endTime);
			map.put("org", pid);
			return commandExecutor.execute(new ExpandCommonCmd("PerformanceInterfaceTask", map));
		}
		else
			return null;
	}

	public List<UserTo> getAgentUsers(String userId) {

		return commandExecutor.execute(new GetAgentUsersCmd(userId));
	}

	public List<Map<String, Object>> getAgentUsersAndCount(String userId) {

		return commandExecutor.execute(new GetAgentUsersAndCountCmd(userId));
	}

	public List<TaskCommandInst> getSystemTaskCommand(String commandType) {
		
		
		return commandExecutor.execute(new GetSystemTaskCommandCmd(commandType));
		

	}

	public List<UserTaskBehavior> getUserEndTaskNodesInProcessInstance(
			String processInstanceId) {

		return commandExecutor.execute(new GetUserEndTaskNodesInProcessInstanceCmd(processInstanceId));
	}

	public List<TaskCommandInst> GetTaskCommandByTaskId(String taskId) {
		// TODO 自动生成的方法存根
		return commandExecutor.execute(new GetTaskCommandByTaskIdCmd(taskId));
	}

	public List<TaskCommandInst> GetTaskCommandByTaskInstance(
			TaskInstance taskInstance) {
		return commandExecutor.execute(new GetTaskCommandByTaskInstanceCmd(taskInstance));
	}

	public List<UserTo> getTaskUsersByTaskId(String taskId) {
		// TODO 自动生成的方法存根
		
		return commandExecutor.execute(new GetTaskUsersByTaskIdCmd(taskId));
		
	}

	public Priority getPriority(int priorityValue) {
		
		return commandExecutor.execute(new GetPriorityCmd(priorityValue));
	}

	public List<TaskInstance> getNextTask(String taskId,String processInstanceId) {


		return commandExecutor.execute(new GetNextTaskCmd(taskId,processInstanceId));
	}

	public List<TaskInstance> getRollBackScreeningTask(String taskId) {

		return commandExecutor.execute(new GetRollBackScreeningTaskCmd(taskId));
	}

	



}
