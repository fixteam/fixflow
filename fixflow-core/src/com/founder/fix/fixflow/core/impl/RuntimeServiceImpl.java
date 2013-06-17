package com.founder.fix.fixflow.core.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.impl.cmd.DeleteProcessInstanceByInstanceIdAndDefKeyCmd;
import com.founder.fix.fixflow.core.impl.cmd.DeleteProcessInstanceByInstanceIdCmd;
import com.founder.fix.fixflow.core.impl.cmd.ExecuteRuleScriptCmd;
import com.founder.fix.fixflow.core.impl.cmd.ExpandCommonCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetProcessCommand;
import com.founder.fix.fixflow.core.impl.cmd.MessageStartProcessInstanceCmd;
import com.founder.fix.fixflow.core.impl.cmd.NoneStartProcessInstanceCmd;
import com.founder.fix.fixflow.core.impl.cmd.ProcessPerformanceCmd;
import com.founder.fix.fixflow.core.impl.cmd.ProcessPerformanceInterface1Cmd;
import com.founder.fix.fixflow.core.impl.cmd.ProcessPerformanceInterface22Cmd;
import com.founder.fix.fixflow.core.impl.cmd.ProcessPerformanceInterface2Cmd;
import com.founder.fix.fixflow.core.impl.cmd.ProcessPerformanceInterface4Cmd;
import com.founder.fix.fixflow.core.impl.cmd.ProcessPerformanceInterface5Cmd;
import com.founder.fix.fixflow.core.impl.cmd.QueryVariablesCmd;
import com.founder.fix.fixflow.core.impl.cmd.SaveVariablesCmd;
import com.founder.fix.fixflow.core.impl.cmd.TimeStartProcessInstanceCmd;
import com.founder.fix.fixflow.core.impl.cmd.TokenSignalCmd;
import com.founder.fix.fixflow.core.impl.cmd.TokenTimeOutCmd;
import com.founder.fix.fixflow.core.impl.cmd.UpdateBusinessKeyCmd;
import com.founder.fix.fixflow.core.impl.command.MessageStartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.command.QueryVariablesCommand;
import com.founder.fix.fixflow.core.impl.command.SaveVariablesCommand;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceQueryImpl;
import com.founder.fix.fixflow.core.impl.runtime.TokenQueryImpl;
import com.founder.fix.fixflow.core.impl.subscription.EventSubscriptionQueryImpl;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.core.runtime.TokenQuery;
import com.founder.fix.fixflow.core.subscription.EventSubscriptionQuery;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class RuntimeServiceImpl extends ServiceImpl implements RuntimeService {

	public ProcessInstance noneStartProcessInstance(StartProcessInstanceCommand startProcessInstanceCommand) {
		return commandExecutor.execute(new NoneStartProcessInstanceCmd<ProcessInstance>(startProcessInstanceCommand));
	}

	public ProcessInstance timeStartProcessInstance(StartProcessInstanceCommand startProcessInstanceCommand) {
		return commandExecutor.execute(new TimeStartProcessInstanceCmd<ProcessInstance>(startProcessInstanceCommand));
	}

	public ProcessInstance messageStartProcessInstance(MessageStartProcessInstanceCommand messageStartProcessInstanceCommand) {
		return commandExecutor.execute(new MessageStartProcessInstanceCmd<ProcessInstance>(messageStartProcessInstanceCommand));
	}

	public boolean deleteProcessInstance(String processInstanceId, boolean cascade) {
		return commandExecutor.execute(new DeleteProcessInstanceByInstanceIdCmd(processInstanceId, cascade));
	}

	public boolean deleteProcessInstance(String processDefinitionKey, String businessKey, boolean cascade) {
		return commandExecutor.execute(new DeleteProcessInstanceByInstanceIdAndDefKeyCmd(processDefinitionKey, businessKey, cascade));
	}

	public ProcessInstanceQuery createProcessInstanceQuery() {
		return new ProcessInstanceQueryImpl(commandExecutor);
	}

	public TokenQuery createTokenQuery() {
		return new TokenQueryImpl(commandExecutor);
	}

	public void updateProcessInstanceBusinessKey(String processInstanceId, String businessKey) {
		commandExecutor.execute(new UpdateBusinessKeyCmd(processInstanceId, businessKey));
	}

	public void setProcessInstanceVariable(String processInstanceId, String variableName, Object value) {
		SaveVariablesCommand saveVariablesCommand = new SaveVariablesCommand();
		saveVariablesCommand.setProcessInstanceId(processInstanceId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(variableName, value);
		saveVariablesCommand.setVariables(map);
		commandExecutor.execute(new SaveVariablesCmd(saveVariablesCommand));
	}

	public void setProcessInstanceVariables(String processInstanceId, Map<String, ? extends Object> variables) {
		SaveVariablesCommand saveVariablesCommand = new SaveVariablesCommand();
		saveVariablesCommand.setProcessInstanceId(processInstanceId);
		saveVariablesCommand.setVariables(variables);
		commandExecutor.execute(new SaveVariablesCmd(saveVariablesCommand));
	}

	public Object getProcessInstanceVariable(String processInstanceId, String variableName) {
		QueryVariablesCommand queryVariablesCommand = new QueryVariablesCommand();
		queryVariablesCommand.setProcessInstanceId(processInstanceId);
		List<String> variableNames = new ArrayList<String>();
		variableNames.add(variableName);
		queryVariablesCommand.setVariableNames(variableNames);
		Map<String, Object> map = commandExecutor.execute(new QueryVariablesCmd<Map<String, Object>>(queryVariablesCommand));
		return map.get(variableName);
	}

	public Map<String, Object> getProcessInstanceVariables(String processInstanceId) {
		QueryVariablesCommand queryVariablesCommand = new QueryVariablesCommand();
		queryVariablesCommand.setProcessInstanceId(processInstanceId);
		Map<String, Object> map = commandExecutor.execute(new QueryVariablesCmd<Map<String, Object>>(queryVariablesCommand));
		return map;
	}

	public Map<String, Object> getProcessInstanceVariables(String processInstanceId, Collection<String> variableNames) {
		QueryVariablesCommand queryVariablesCommand = new QueryVariablesCommand();
		queryVariablesCommand.setProcessInstanceId(processInstanceId);
		queryVariablesCommand.setVariableNames(variableNames);
		Map<String, Object> map = commandExecutor.execute(new QueryVariablesCmd<Map<String, Object>>(queryVariablesCommand));
		return map;
	}

	public void setTokenVariable(String tokenId, String variableName, Object value) {
		SaveVariablesCommand saveVariablesCommand = new SaveVariablesCommand();
		saveVariablesCommand.setTokenId(tokenId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(variableName, value);
		saveVariablesCommand.setVariables(map);
		commandExecutor.execute(new SaveVariablesCmd(saveVariablesCommand));
	}

	public void setTokenVariables(String tokenId, Map<String, ? extends Object> variables) {
		SaveVariablesCommand saveVariablesCommand = new SaveVariablesCommand();
		saveVariablesCommand.setTokenId(tokenId);
		saveVariablesCommand.setVariables(variables);
		commandExecutor.execute(new SaveVariablesCmd(saveVariablesCommand));
	}

	public Object getTokenVariable(String tokenId, String variableName) {
		QueryVariablesCommand queryVariablesCommand = new QueryVariablesCommand();
		queryVariablesCommand.setTokenId(tokenId);
		List<String> variableNames = new ArrayList<String>();
		variableNames.add(variableName);
		queryVariablesCommand.setVariableNames(variableNames);
		Map<String, Object> map = commandExecutor.execute(new QueryVariablesCmd<Map<String, Object>>(queryVariablesCommand));
		return map.get(variableName);
	}

	public Map<String, Object> getTokenVariables(String tokenId) {
		QueryVariablesCommand queryVariablesCommand = new QueryVariablesCommand();
		queryVariablesCommand.setTokenId(tokenId);
		Map<String, Object> map = commandExecutor.execute(new QueryVariablesCmd<Map<String, Object>>(queryVariablesCommand));
		return map;
	}

	public Map<String, Object> getTokenVariables(String tokenId, Collection<String> variableNames) {
		QueryVariablesCommand queryVariablesCommand = new QueryVariablesCommand();
		queryVariablesCommand.setTokenId(tokenId);
		queryVariablesCommand.setVariableNames(variableNames);
		Map<String, Object> map = commandExecutor.execute(new QueryVariablesCmd<Map<String, Object>>(queryVariablesCommand));
		return map;
	}

	public EventSubscriptionQuery createEventSubscriptionQuery() {
		return new EventSubscriptionQueryImpl(commandExecutor);
	}

	public void tokenSignal(String tokenId, Map<String, Object> transientVariables) {
		commandExecutor.execute(new TokenSignalCmd(tokenId, transientVariables,null));
	}
	
	public void tokenSignal(String tokenId, Map<String, Object> transientVariables,Map<String, Object> dataVariables) {
		commandExecutor.execute(new TokenSignalCmd(tokenId, transientVariables,dataVariables));
	}
	

	public void signal(Map<String, Object> transientVariables) {
		commandExecutor.execute(new TokenSignalCmd(null, transientVariables,null));
		
	}

	public void signal(Map<String, Object> transientVariables, Map<String, Object> dataVariables) {
		commandExecutor.execute(new TokenSignalCmd(null, transientVariables,dataVariables));
		
	}


	public ProcessInstance startProcessInstanceByKey(StartProcessInstanceCommand startProcessInstanceCommand) {
		return commandExecutor.execute(new NoneStartProcessInstanceCmd<ProcessInstance>(startProcessInstanceCommand));
	}

	public ProcessInstance startProcessInstanceById(StartProcessInstanceCommand startProcessInstanceCommand) {
		return commandExecutor.execute(new NoneStartProcessInstanceCmd<ProcessInstance>(startProcessInstanceCommand));
	}

	public ProcessInstance startProcessInstanceByMessage(MessageStartProcessInstanceCommand messageStartProcessInstanceCommand) {

		return commandExecutor.execute(new MessageStartProcessInstanceCmd<ProcessInstance>(messageStartProcessInstanceCommand));
	}
	
	public void tokenTimeOut(String tokenId,Map<String, Object> transientVariables) {
		commandExecutor.execute(new TokenTimeOutCmd(tokenId,null,transientVariables));
	}

	public void tokenTimeOut(String tokenId,String nodeId,Map<String, Object> transientVariables) {
		commandExecutor.execute(new TokenTimeOutCmd(tokenId,nodeId,transientVariables));
	}

	public boolean insertPigeonholeData() {
		return true;
	}

	public List<Map<String, Object>> processPerformance(String processKey, String startTime, String endTime, int firstPage, int maxSize) {
		return commandExecutor.execute(new ProcessPerformanceCmd(processKey, startTime, endTime, firstPage, maxSize));
	}

	public List<Map<String, Object>> processPerformance(String startTime, String endTime) {
		return commandExecutor.execute(new ProcessPerformanceInterface1Cmd(startTime, endTime));
	}

	public List<Map<String, Object>> processPerformance(String[] processKey, String startTime, String endTime) {
		return commandExecutor.execute(new ProcessPerformanceInterface4Cmd(processKey, startTime, endTime));
	}

	public List<Map<String, Object>> processPerformance(String startTime, String endTime, String pid) {
		return commandExecutor.execute(new ProcessPerformanceInterface5Cmd( startTime, endTime, pid));
	}

	public List<Map<String, Object>> processPerformance(String startTime, String endTime, int firstPage, int maxSize) {
		return commandExecutor.execute(new ProcessPerformanceInterface2Cmd(startTime, endTime, firstPage, maxSize));
	}

	public int processPerformance2(String startTime, String endTime) {
		return commandExecutor.execute(new ProcessPerformanceInterface22Cmd(startTime, endTime));
	}
	public <T> T ExpandCmd(String cmdId, Map<String, Object> parameterMap, T classReturn) {

		return commandExecutor.execute(new ExpandCommonCmd<T>(cmdId, parameterMap));

	}

	public List<TaskInstance> getNotDoneTask(ProcessInstance processInstance) {
		List<TaskInstanceEntity> taskInstanceEntities= ((ProcessInstanceEntity)processInstance).getTaskMgmtInstance().getTaskInstanceEntitys();
		List<TaskInstance> taskInstances=new ArrayList<TaskInstance>();
		for (TaskInstanceEntity taskInstanceEntity : taskInstanceEntities) {
			if(!taskInstanceEntity.hasEnded()){
				taskInstances.add(taskInstanceEntity);
			}
			
		}
		return taskInstances;
	}

	public Object executeRuleScript(String ruleScript) {

		return commandExecutor.execute(new ExecuteRuleScriptCmd<Object>(ruleScript));

	}

	public List<Map<String, Object>> GetProcessCommandByProcessInstanceId(String processInstanceId) {
		// TODO 自动生成的方法存根
		return commandExecutor.execute(new GetProcessCommand(processInstanceId));
	}

	



}