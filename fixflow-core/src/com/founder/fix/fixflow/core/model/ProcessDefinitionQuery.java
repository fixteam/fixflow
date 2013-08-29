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
  
	/**
	 * 根据流程定义编号查询
	 * @param processDefinitionId 流程定义编号
	 * @return
	 */
	ProcessDefinitionQuery processDefinitionId(String processDefinitionId);

	/**
	 * 根据流程分类查询
	 * @param processDefinitionCategory 流程分类
	 * @return
	 */
	ProcessDefinitionQuery processDefinitionCategory(String processDefinitionCategory);

	/**
	 * 根据流程分类like匹配
	 * @param processDefinitionCategoryLike
	 * @return
	 */
	ProcessDefinitionQuery processDefinitionCategoryLike(String processDefinitionCategoryLike);

	/**
	 * 根据流程名称查询
	 * @param processDefinitionName
	 * @return
	 */
	ProcessDefinitionQuery processDefinitionName(String processDefinitionName);

	/**
	 * 根据流程名称like匹配
	 * @param processDefinitionNameLike
	 * @return
	 */
	ProcessDefinitionQuery processDefinitionNameLike(String processDefinitionNameLike);

	/**
	 * 根据发布号查询
	 * @param deploymentId
	 * @return
	 */
	ProcessDefinitionQuery deploymentId(String deploymentId);
	
	/**
	 * 查询最新版本
	 * @return
	 */
	ProcessDefinitionQuery latestVersion();

	/**
	 * 根据流程定义key查询
	 * @param processDefinitionKey
	 * @return
	 */
	ProcessDefinitionQuery processDefinitionKey(String processDefinitionKey);

	/**
	 * 流程定义key like匹配
	 * @param processDefinitionKeyLike
	 * @return
	 */
	ProcessDefinitionQuery processDefinitionKeyLike(String processDefinitionKeyLike);

	/**
	 * 流程定义版本号
	 * @param processDefinitionVersion
	 * @return
	 */
	ProcessDefinitionQuery processDefinitionVersion(Integer processDefinitionVersion);

	/**
	 * 根据流程分类排序
	 * @return
	 */
	ProcessDefinitionQuery orderByProcessDefinitionCategory();

	/**
	 * 根据流程定义key排序
	 * @return
	 */
	ProcessDefinitionQuery orderByProcessDefinitionKey();

	/**
	 * 根据流程定义id排序
	 * @return
	 */
	ProcessDefinitionQuery orderByProcessDefinitionId();

	/**
	 * 根据版本号排序
	 * @return
	 */
	ProcessDefinitionQuery orderByProcessDefinitionVersion();

	/**
	 * 根据流程定义名称排序
	 * @return
	 */
	ProcessDefinitionQuery orderByProcessDefinitionName();

	/**
	 * 根据发布号排序
	 * @return
	 */
	ProcessDefinitionQuery orderByDeploymentId();
}
