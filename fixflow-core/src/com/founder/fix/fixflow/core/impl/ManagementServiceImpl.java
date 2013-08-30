package com.founder.fix.fixflow.core.impl;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.ManagementService;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class ManagementServiceImpl extends ServiceImpl implements ManagementService {

	private ProcessEngine getProcessEngine(){
		return ProcessEngineManagement.getDefaultProcessEngine();
	}
	
	public void complete(String taskId, String taskComment, Map<String, Object> transientVariables) {
		ExpandTaskCommand expandTaskCommandClaim=new ExpandTaskCommand();
		expandTaskCommandClaim.setCommandType("general");
		expandTaskCommandClaim.setTaskId(taskId);
		expandTaskCommandClaim.setTaskComment(taskComment);
		expandTaskCommandClaim.setAdmin(Authentication.getAuthenticatedUserId());
		expandTaskCommandClaim.setTransientVariables(transientVariables);
		
		getProcessEngine().getTaskService().expandTaskComplete(expandTaskCommandClaim, null);
	}

	public void claim(String taskId) {
		ExpandTaskCommand expandTaskCommandClaim=new ExpandTaskCommand();
		expandTaskCommandClaim.setCommandType("claim");
		expandTaskCommandClaim.setTaskId(taskId);
		expandTaskCommandClaim.setAdmin(Authentication.getAuthenticatedUserId());
		getProcessEngine().getTaskService().expandTaskComplete(expandTaskCommandClaim, null);
	}

	public void claim(String taskId, String claimUserId) {
		ExpandTaskCommand expandTaskCommandClaim=new ExpandTaskCommand();
		expandTaskCommandClaim.setCommandType("claim");
		expandTaskCommandClaim.setTaskId(taskId);
		expandTaskCommandClaim.setAdmin(Authentication.getAuthenticatedUserId());
		getProcessEngine().getTaskService().expandTaskComplete(expandTaskCommandClaim, null);		
	}

	public void release(String taskId) {
		ExpandTaskCommand expandTaskCommandClaim=new ExpandTaskCommand();
		expandTaskCommandClaim.setCommandType("releaseTask");
		expandTaskCommandClaim.setTaskId(taskId);
		expandTaskCommandClaim.setAdmin(Authentication.getAuthenticatedUserId());
		getProcessEngine().getTaskService().expandTaskComplete(expandTaskCommandClaim, null);
	}

	public void transfer(String taskId, String transferUserId, String taskComment, Map<String, Object> transientVariables) {
		ExpandTaskCommand expandTaskCommand=new ExpandTaskCommand();
		expandTaskCommand.setCommandType("transfer");
		expandTaskCommand.setTaskId(taskId);
		expandTaskCommand.setAdmin(Authentication.getAuthenticatedUserId());
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("transferUserId", transferUserId);
		expandTaskCommand.setParamMap(paramMap);
		getProcessEngine().getTaskService().expandTaskComplete(expandTaskCommand, null);
	}

	public void rollBack(String taskId, String rollBackNodeId, String taskComment, Map<String, Object> transientVariables) {
		ExpandTaskCommand expandTaskCommand=new ExpandTaskCommand();
		expandTaskCommand.setCommandType("rollBack");
		expandTaskCommand.setTaskId(taskId);
		expandTaskCommand.setAdmin(Authentication.getAuthenticatedUserId());
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("rollBackNodeId", rollBackNodeId);
		expandTaskCommand.setParamMap(paramMap);
		getProcessEngine().getTaskService().expandTaskComplete(expandTaskCommand, null);
	}

	public void resumeTask(String taskId) {
		TaskService taskService=getProcessEngine().getTaskService();
		TaskInstance taskInstance=taskService.createTaskQuery().taskId(taskId).singleResult();
		taskInstance.resume();
		taskService.saveTask(taskInstance);
	}

	public void suspendTask(String taskId) {
		TaskService taskService=getProcessEngine().getTaskService();
		TaskInstance taskInstance=taskService.createTaskQuery().taskId(taskId).singleResult();
		taskInstance.suspend();
		taskService.saveTask(taskInstance);
	}

	
	
	
	

}
