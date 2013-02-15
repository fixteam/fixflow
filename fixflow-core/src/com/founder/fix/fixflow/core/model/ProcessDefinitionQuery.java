package com.founder.fix.fixflow.core.model;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.query.Query;

/**
 * 查询流程定义 {@link ProcessDefinition}.
 * 
 * @author kenshin
 */
public interface ProcessDefinitionQuery extends Query<ProcessDefinitionQuery, ProcessDefinitionBehavior> {
  

  ProcessDefinitionQuery processDefinitionId(String processDefinitionId);
  

  ProcessDefinitionQuery processDefinitionCategory(String processDefinitionCategory);
  

  ProcessDefinitionQuery processDefinitionCategoryLike(String processDefinitionCategoryLike);


  ProcessDefinitionQuery processDefinitionName(String processDefinitionName);
  

  ProcessDefinitionQuery processDefinitionNameLike(String processDefinitionNameLike);


  ProcessDefinitionQuery deploymentId(String deploymentId);


  ProcessDefinitionQuery processDefinitionKey(String processDefinitionKey);


  ProcessDefinitionQuery processDefinitionKeyLike(String processDefinitionKeyLike);
  

  ProcessDefinitionQuery processDefinitionVersion(Integer processDefinitionVersion);
  

  ProcessDefinitionQuery latestVersion();

  
  ProcessDefinitionQuery processDefinitionResourceName(String resourceName);


  ProcessDefinitionQuery processDefinitionResourceNameLike(String resourceNameLike);

  
  
  ProcessDefinitionQuery orderByProcessDefinitionCategory();
  

  ProcessDefinitionQuery orderByProcessDefinitionKey();


  ProcessDefinitionQuery orderByProcessDefinitionId();
  

  ProcessDefinitionQuery orderByProcessDefinitionVersion();
  

  ProcessDefinitionQuery orderByProcessDefinitionName();
  

  ProcessDefinitionQuery orderByDeploymentId();
}
