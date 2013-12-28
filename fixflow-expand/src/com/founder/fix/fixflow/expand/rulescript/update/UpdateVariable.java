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
package com.founder.fix.fixflow.expand.rulescript.update;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableEntity;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.scriptlanguage.UpdateRulesScript;

public class UpdateVariable implements UpdateRulesScript {

	public void execute(Object parameter, SqlCommand sqlCommand, ProcessEngineConfigurationImpl processEngineConfiguration) {
		
		DataVariableEntity dataVariable=(DataVariableEntity)parameter;
		
		String sqlWhereQueryString = "";
		// 构建查询参数
		List<Object> objectParamWhere = new ArrayList<Object>();
		String variableKey = dataVariable.getId();
		String processInstanceId = dataVariable.getProcessInstanceId();
		String tokenId = dataVariable.getTokenId();
		String taskInstanceId = dataVariable.getTaskInstanceId();
		String nodeId = dataVariable.getNodeId();
	
		if (processInstanceId == null) {
			sqlWhereQueryString = sqlWhereQueryString + " PROCESSINSTANCE_ID IS NULL AND ";
		} else {
			sqlWhereQueryString = sqlWhereQueryString + " PROCESSINSTANCE_ID=? AND ";
			objectParamWhere.add(processInstanceId);
		}

		if (tokenId == null) {
			sqlWhereQueryString = sqlWhereQueryString + " TOKEN_ID IS NULL AND ";
		} else {
			sqlWhereQueryString = sqlWhereQueryString + " TOKEN_ID=? AND";
			objectParamWhere.add(tokenId);
		}

		if (taskInstanceId == null) {
			sqlWhereQueryString = sqlWhereQueryString + " TASKINSTANCE_ID IS NULL AND ";
		} else {
			sqlWhereQueryString = sqlWhereQueryString + " TASKINSTANCE_ID=? AND ";
			objectParamWhere.add(taskInstanceId);
		}

		if (nodeId == null) {
			sqlWhereQueryString = sqlWhereQueryString + " NODE_ID IS NULL ";
		} else {
			sqlWhereQueryString = sqlWhereQueryString + " NODE_ID=? ";
			objectParamWhere.add(nodeId);
		}

		sqlWhereQueryString = sqlWhereQueryString + " AND VARIABLE_KEY=?";
		objectParamWhere.add(variableKey);

		// 构建Where查询参数
		Object[] objectParamObj = new Object[objectParamWhere.size()];
		for (int i = 0; i < objectParamWhere.size(); i++) {
			objectParamObj[i] = objectParamWhere.get(i);
		}

		// 执行更新语句
		sqlCommand.update("FIXFLOW_RUN_VARIABLE", dataVariable.getPersistentDbMap(), sqlWhereQueryString, objectParamObj);

	}

}
