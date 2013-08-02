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
