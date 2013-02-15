package com.founder.fix.fixflow.core.impl.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceQueryImpl;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;


public class ProcessInstanceManager extends AbstractManager {

	public void deleteProcessInstancesByProcessDefinition(String processDefinitionId, String deleteReason, boolean cascade) {

	}

	public void deleteProcessInstance(String processInstanceId,  boolean cascade) {
		
		if(cascade){
			getDbSqlSession().delete("deleteProcessInstanceByProcessInstanceId", processInstanceId);
			
			getDbSqlSession().delete("deleteIdentityLinkByProcessInstanceId", processInstanceId);
			getDbSqlSession().delete("deleteTaskInstanceByProcessInstanceId", processInstanceId);
			getDbSqlSession().delete("deleteVariableByProcessInstanceId", processInstanceId);
			getDbSqlSession().delete("deleteTokenByProcessInstanceId", processInstanceId);
			
			
		}
		else{
			getDbSqlSession().delete("deleteProcessInstanceByProcessInstanceId", processInstanceId);
		}
		

	}


	public ProcessInstanceEntity findSubProcessInstanceBySuperExecutionId(String superExecutionId) {

		return null;
	}
	
	public ProcessInstanceEntity findProcessInstanceById(String processInstanceId) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processInstanceId", processInstanceId);
		parameters.put("processDefinition", null);
		return (ProcessInstanceEntity) getDbSqlSession().selectOne("selectProcessInstance", parameters);

	}

	public ProcessInstanceEntity findProcessInstanceById(String processInstanceId, ProcessDefinitionBehavior processDefinition) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processInstanceId", processInstanceId);
		parameters.put("processDefinition", processDefinition);
		return (ProcessInstanceEntity) getDbSqlSession().selectOne("selectProcessInstance", parameters);

	}

	public long findProcessInstanceCountByQueryCriteria(Object executionQuery) {

		return (Long) getDbSqlSession().selectOne("selectProcessInstanceCountByQueryCriteria", executionQuery);

	}

	@SuppressWarnings("unchecked")
	public List<ProcessInstanceEntity> findProcessInstanceByQueryCriteria(ProcessInstanceQueryImpl processInstanceQueryImpl, Page page) {
		return getDbSqlSession().selectList("selectProcessInstanceByQueryCriteria", processInstanceQueryImpl, page);
	}
	
	public ProcessInstanceEntity findProcessInstanceByDefKeyAndBusinessKey(String processDefinitionKey,String businessKey) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processDefinitionKey", processDefinitionKey);
		parameters.put("businessKey", businessKey);
		return (ProcessInstanceEntity)getDbSqlSession().selectOne("selectProcessInstanceByDefKeyAndBusinessKey",parameters );
	}

	public void saveProcessInstance(ProcessInstanceEntity processInstance) throws Exception {
		
		String processLocation="";
		
		Set<TaskInstanceEntity> taskInstanceEntities=processInstance.getTaskMgmtInstance().getTaskInstanceEntitys();
		
		
		for (TaskInstanceEntity taskInstanceEntity : taskInstanceEntities) {
			if(!taskInstanceEntity.hasEnded()){
				if(processLocation.equals("")){
					processLocation=processLocation+taskInstanceEntity.getNodeName();
				}
				else{
					processLocation=processLocation+","+taskInstanceEntity.getNodeName();
				}
			}
		}

		processInstance.setProcessLocation(processLocation);
		
		
		getDbSqlSession().save("saveProcessInstance", processInstance);
		
	}
	
	public void UpdateProcessInstanceBusinessKey(ProcessInstanceEntity processInstance) throws Exception {

		getDbSqlSession().save("saveProcessInstance", processInstance);

	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getProcessPerformance(String startTime,String endTime,String processKey,Page page) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		parameters.put("processKey", processKey);
		return getDbSqlSession().selectList("selectProcessPerformance", parameters, page);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getProcessPerformance(String startTime,String endTime,Page page) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		return getDbSqlSession().selectList("selectProcessPerformanceInterface2", parameters, page);
	}
	
	public int getProcessPerformance2(String startTime,String endTime) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		return StringUtil.getInt(getDbSqlSession().selectOne("selectProcessPerformanceInterface22", parameters));
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getProcessPerformance(String startTime,String endTime) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		return getDbSqlSession().selectList("selectProcessPerformanceInterface1", parameters);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getProcessPerformance(String[] processKey,String startTime,String endTime) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processKey", processKey);
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		return getDbSqlSession().selectList("selectProcessPerformanceInterface4", parameters);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getProcessPerformance(String startTime,String endTime, String pid) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		parameters.put("pid", pid);
		return getDbSqlSession().selectList("selectProcessPerformanceInterface5", parameters);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getProcessPerformanceTask(String[] pid, String startTime,String endTime) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("pid", pid);
		parameters.put("startTime", startTime);
		parameters.put("endTime", endTime);
		return getDbSqlSession().selectList("selectProcessPerformanceInterface3", parameters);
	}

}
