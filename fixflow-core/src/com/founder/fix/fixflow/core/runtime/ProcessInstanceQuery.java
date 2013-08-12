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
package com.founder.fix.fixflow.core.runtime;


import java.util.Date;
import java.util.List;

import com.founder.fix.fixflow.core.query.Query;


public interface ProcessInstanceQuery extends Query<ProcessInstanceQuery, ProcessInstance> {

	ProcessInstanceQuery processInstanceId(String processInstanceId);

	ProcessInstanceQuery processInstanceBusinessKey(String processInstanceBusinessKey);

	ProcessInstanceQuery processInstanceBusinessKey(String processInstanceBusinessKey, String processDefinitionKey);

	ProcessInstanceQuery processDefinitionKey(String processDefinitionKey);
	
	ProcessInstanceQuery  processDefinitionKey(List<String> processDefinitionKeyList);

	ProcessInstanceQuery processDefinitionId(String processDefinitionId);
	
	ProcessInstanceQuery initiator(String initiator);
	
	ProcessInstanceQuery initiatorLike(String initiatorLike);
	
	ProcessInstanceQuery isEnd();
	
	ProcessInstanceQuery containsSubProcess();
	
	/**
	 * 根据流程实例所有变量查询
	 * @param variableValue 变量值
	 * @param isLike 是否Like
	 * @return
	 */
	ProcessInstanceQuery processInstanceVariableData(String variableValue,boolean isLike);
	
	/**
	 * 根据流程实例指定的变量查询
	 * @param variableKey  变量Key
	 * @param variableValue 变量值
	 * @param isLike 是否Like
	 * @return
	 */
	ProcessInstanceQuery processInstanceVariableData(String variableKey,String variableValue,boolean isLike);
	

	ProcessInstanceQuery notEnd();
	
	
	ProcessInstanceQuery isPigeonhole();
	
	
	ProcessInstanceQuery notPigeonhole();

	
	ProcessInstanceQuery subject(String subject);
	
	ProcessInstanceQuery subjectLike(String subjectLike);
	
	ProcessInstanceQuery startTimeOn(Date startTime);

	ProcessInstanceQuery startTimeBefore(Date startTimeBefore);

	ProcessInstanceQuery startTimeAfter(Date startTimeAfter);
	
	ProcessInstanceQuery taskParticipants(String taskParticipants);

	ProcessInstanceQuery superProcessInstanceId(String superProcessInstanceId);

	ProcessInstanceQuery subProcessInstanceId(String subProcessInstanceId);

	ProcessInstanceQuery variableValueEquals(String name, Object value);

	ProcessInstanceQuery variableValueNotEquals(String name, Object value);

	ProcessInstanceQuery variableValueGreaterThan(String name, Object value);

	ProcessInstanceQuery variableValueGreaterThanOrEqual(String name, Object value);

	ProcessInstanceQuery variableValueLessThan(String name, Object value);

	ProcessInstanceQuery variableValueLessThanOrEqual(String name, Object value);

	ProcessInstanceQuery variableValueLike(String name, String value);
	


	// ordering
	// /////////////////////////////////////////////////////////////////

	ProcessInstanceQuery orderByProcessInstanceId();
	
	ProcessInstanceQuery orderByStartTime();

	ProcessInstanceQuery orderByProcessDefinitionKey();

	ProcessInstanceQuery orderByProcessDefinitionId();
	
	ProcessInstanceQuery orderByUpdateTime();

}
