
package com.founder.fix.fixflow.core.impl.persistence.deployer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentEntity;


/**
 * @author kenshin
 */
public class DeploymentCache {

  protected Map<String, ProcessDefinitionBehavior> processDefinitionCache = new HashMap<String, ProcessDefinitionBehavior>(); 
  protected Map<String, Object> knowledgeBaseCache = new HashMap<String, Object>(); 
  protected List<Deployer> deployers;
  
  public void deploy(DeploymentEntity deployment) {
    for (Deployer deployer: deployers) {
      deployer.deploy(deployment);
    }
  }

  public ProcessDefinitionBehavior findDeployedProcessDefinitionById(String processDefinitionId) {
    if (processDefinitionId == null) {
      throw new FixFlowException("Invalid process definition id : null");
    }
    ProcessDefinitionBehavior processDefinition = (ProcessDefinitionBehavior) Context
      .getCommandContext()
      .getProcessDefinitionManager()
      .findLatestProcessDefinitionById(processDefinitionId);
    if(processDefinition == null) {
      throw new FixFlowException("no deployed process definition found with id '" + processDefinitionId + "'");
    }
    processDefinition = resolveProcessDefinition(processDefinition);
    return processDefinition;
  }

  public ProcessDefinitionBehavior findDeployedLatestProcessDefinitionByKey(String processDefinitionKey) {
	  ProcessDefinitionBehavior processDefinition = (ProcessDefinitionBehavior) Context
      .getCommandContext()
      .getProcessDefinitionManager()
      .findLatestProcessDefinitionByKey(processDefinitionKey);
    if (processDefinition==null) {
      throw new FixFlowException("no processes deployed with key '"+processDefinitionKey+"'");
    }
    processDefinition = resolveProcessDefinition(processDefinition);
    return processDefinition;
  }

  protected ProcessDefinitionBehavior resolveProcessDefinition(ProcessDefinitionBehavior processDefinition) {
    String processDefinitionId = processDefinition.getId();
    String deploymentId = processDefinition.getDeploymentId();
    processDefinition = processDefinitionCache.get(processDefinitionId);
    if (processDefinition==null) {
      DeploymentEntity deployment = Context
        .getCommandContext()
        .getDeploymentManager()
        .findDeploymentById(deploymentId);
      deployment.setNew(false);
      deploy(deployment);
      processDefinition = processDefinitionCache.get(processDefinitionId);
      
      if (processDefinition==null) {
        throw new FixFlowException("deploying "+deploymentId+" didn't put process definition "+processDefinitionId+" in the cache");
      }
    }
    return processDefinition;
  }

  public void addProcessDefinition(ProcessDefinitionBehavior processDefinition) {
    processDefinitionCache.put(processDefinition.getProcessDefinitionId(), processDefinition);
  }

  public void removeProcessDefinition(String processDefinitionId) {
    processDefinitionCache.remove(processDefinitionId);
  }
  
  public void cleanProcessDefinitionCache(){
	  processDefinitionCache.clear();
  }

  public void addKnowledgeBase(String knowledgeBaseId, Object knowledgeBase) {
    knowledgeBaseCache.put(knowledgeBaseId, knowledgeBase);
  }

  public void removeKnowledgeBase(String knowledgeBaseId) {
    knowledgeBaseCache.remove(knowledgeBaseId);
  }

  // getters and setters //////////////////////////////////////////////////////

  public Map<String, ProcessDefinitionBehavior> getProcessDefinitionCache() {
    return processDefinitionCache;
  }
  
  public void setProcessDefinitionCache(Map<String, ProcessDefinitionBehavior> processDefinitionCache) {
    this.processDefinitionCache = processDefinitionCache;
  }
  
  public Map<String, Object> getKnowledgeBaseCache() {
    return knowledgeBaseCache;
  }
  
  public void setKnowledgeBaseCache(Map<String, Object> knowledgeBaseCache) {
    this.knowledgeBaseCache = knowledgeBaseCache;
  }
  
  public List<Deployer> getDeployers() {
    return deployers;
  }
  
  public void setDeployers(List<Deployer> deployers) {
    this.deployers = deployers;
  }
}
