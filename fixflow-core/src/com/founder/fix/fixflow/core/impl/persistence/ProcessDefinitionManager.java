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
		return (ProcessDefinitionBehavior) getDbSqlSession().selectOne("selectLatestProcessDefinitionByKey",processDefinitionKey);
	}

	public void deleteProcessDefinitionsByDeploymentId(String deploymentId) {
		getDbSqlSession().delete("deleteProcessDefinitionsByDeploymentId", deploymentId);

	}

	public ProcessDefinitionBehavior findLatestProcessDefinitionById(String processDefinitionId) {
		return (ProcessDefinitionBehavior) getDbSqlSession().selectOne("selectProcessDefinitionById",processDefinitionId);
	}

	public ProcessDefinitionBehavior findLatestProcessDefinitionByKeyAndVersion(String processDefinitionKey,
			int processDefinitionVersion) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("processDefinitionKey", processDefinitionKey);
		parameters.put("processDefinitionVersion", processDefinitionVersion);
		return (ProcessDefinitionBehavior) getDbSqlSession().selectOne("selectProcessDefinitionByKeyAndVersion",parameters);
	}

	@SuppressWarnings("unchecked")
	public List<ProcessDefinitionBehavior> findProcessDefinitionsByQueryCriteria(ProcessDefinitionQueryImpl processDefinitionQuery, Page page) {
		final String query = "selectProcessDefinitionsByQueryCriteria";
		return getDbSqlSession().selectList(query, processDefinitionQuery, page);
	}

	public long findProcessDefinitionCountByQueryCriteria(ProcessDefinitionQueryImpl processDefinitionQuery) {
		return (Long) getDbSqlSession().selectOne("selectProcessDefinitionCountByQueryCriteria", processDefinitionQuery);
	}

	public ProcessDefinitionBehavior findProcessDefinitionByDeploymentAndKey(String deploymentId,
			String processDefinitionKey) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("deploymentId", deploymentId);
		parameters.put("processDefinitionKey", processDefinitionKey);
		return (ProcessDefinitionBehavior) getDbSqlSession().selectOne("selectProcessDefinitionByDeploymentAndKey",parameters);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectProcessDefinitionGroupKey() {
		return (List<Map<String, Object>>) getDbSqlSession().selectList("selectProcessDefinitionGroupKey");
	}

}
