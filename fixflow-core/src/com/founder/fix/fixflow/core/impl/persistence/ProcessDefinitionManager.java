package com.founder.fix.fixflow.core.impl.persistence;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.ProcessDefinitionQueryImpl;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;


/**
 * 流程定义管理器
 * 
 * @author Kenshin
 */
public class ProcessDefinitionManager extends AbstractManager {

	public ProcessDefinitionBehavior findLatestProcessDefinitionByKey(String processDefinitionKey) {
		return (ProcessDefinitionBehavior)getDbSqlSession().selectOne("selectLatestProcessDefinitionByKey", processDefinitionKey);
		//return FixFlowCreate.createProcessDefinition();

	}

	public void deleteProcessDefinitionsByDeploymentId(String deploymentId) {
		
		 getDbSqlSession().delete("deleteProcessDefinitionsByDeploymentId", deploymentId);

	}

	public ProcessDefinitionBehavior findLatestProcessDefinitionById(String processDefinitionId) {
		return (ProcessDefinitionBehavior)getDbSqlSession().selectOne("selectProcessDefinitionById", processDefinitionId);
		//return FixFlowCreate.createProcessDefinition();
	}
	
	public ProcessDefinitionBehavior findLatestProcessDefinitionByKeyAndVersion(String processDefinitionKey,int processDefinitionVersion) {
		 Map<String, Object> parameters = new HashMap<String, Object>();
		    parameters.put("processDefinitionKey", processDefinitionKey);
		    parameters.put("processDefinitionVersion", processDefinitionVersion);
		return (ProcessDefinitionBehavior)getDbSqlSession().selectOne("selectProcessDefinitionByKeyAndVersion", parameters);
		//return FixFlowCreate.createProcessDefinition();
	}

	@SuppressWarnings("unchecked")
	public List<ProcessDefinitionBehavior> findProcessDefinitionsByQueryCriteria(ProcessDefinitionQueryImpl processDefinitionQuery, Page page) {
		
		  final String query = "selectProcessDefinitionsByQueryCriteria";
		  
		  return getDbSqlSession().selectList(query, processDefinitionQuery,page);
		

		/*
		List<ProcessDefinition> processDefinitionList = new ArrayList<ProcessDefinition>();
		processDefinitionList.add(FixFlowCreate.createProcessDefinition());

		return processDefinitionList;*/
	}

	public long findProcessDefinitionCountByQueryCriteria(ProcessDefinitionQueryImpl processDefinitionQuery) {
	    return (Long) getDbSqlSession().selectOne("selectProcessDefinitionCountByQueryCriteria", processDefinitionQuery);
	  }
	  
	  public ProcessDefinitionBehavior findProcessDefinitionByDeploymentAndKey(String deploymentId, String processDefinitionKey) {
	    Map<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("deploymentId", deploymentId);
	    parameters.put("processDefinitionKey", processDefinitionKey);
	    return (ProcessDefinitionBehavior) getDbSqlSession().selectOne("selectProcessDefinitionByDeploymentAndKey", parameters);
	  }
	  
	  @SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectProcessDefinitionGroupKey(){
		  return (List<Map<String, Object>>)getDbSqlSession().selectList("selectProcessDefinitionGroupKey");
	  }

}
