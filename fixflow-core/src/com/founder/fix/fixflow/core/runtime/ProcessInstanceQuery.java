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

	/**
	 * 根据流程实例号查询
	 * @param processInstanceId
	 * @return
	 */
	ProcessInstanceQuery processInstanceId(String processInstanceId);

	/**
	 * 根据业务关联键查询
	 * @param processInstanceBusinessKey
	 * @return
	 */
	ProcessInstanceQuery processInstanceBusinessKey(String processInstanceBusinessKey);

	/**
	 * 
	 * @param processInstanceBusinessKey
	 * @param processDefinitionKey
	 * @return
	 */
	ProcessInstanceQuery processInstanceBusinessKey(String processInstanceBusinessKey, String processDefinitionKey);

	/**
	 * 根据流程定义key查询
	 * @param processDefinitionKey
	 * @return
	 */
	ProcessInstanceQuery processDefinitionKey(String processDefinitionKey);
	
	/**
	 * 根据流程定义key查询
	 * @param processDefinitionKeyList
	 * @return
	 */
	ProcessInstanceQuery  processDefinitionKey(List<String> processDefinitionKeyList);

	/**
	 * 根据流程定义编号查询
	 * @param processDefinitionId
	 * @return
	 */
	ProcessInstanceQuery processDefinitionId(String processDefinitionId);
	
	/**
	 * 流程发起人
	 * @param initiator
	 * @return
	 */
	ProcessInstanceQuery initiator(String initiator);
	
	/**
	 * 流程发起人like查询
	 * @param initiatorLike
	 * @return
	 */
	ProcessInstanceQuery initiatorLike(String initiatorLike);
	
	/**
	 * 已经结束的流程实例
	 * @return
	 */
	ProcessInstanceQuery isEnd();
	
	/**
	 * 含有子流程
	 * @return
	 */
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
	
	/**
	 * 未结束的流程实例
	 * @return
	 */
	ProcessInstanceQuery notEnd();
	
	/**
	 * 
	 * @return
	 */
	ProcessInstanceQuery isPigeonhole();
	
	/**
	 * 
	 * @return
	 */
	ProcessInstanceQuery notPigeonhole();

	/**
	 * 根据任务主题查询
	 * @param subject
	 * @return
	 */
	ProcessInstanceQuery subject(String subject);
	
	/**
	 * 根据任务主题like查询
	 * @param subjectLike
	 * @return
	 */
	ProcessInstanceQuery subjectLike(String subjectLike);
	
	/**
	 * 流程开始时间等于
	 * @param startTime
	 * @return
	 */
	ProcessInstanceQuery startTimeOn(Date startTime);

	/**
	 * 流程开始时间大于
	 * @param startTimeBefore
	 * @return
	 */
	ProcessInstanceQuery startTimeBefore(Date startTimeBefore);

	/**
	 * 流程开始时间小于
	 * @param startTimeAfter
	 * @return
	 */
	ProcessInstanceQuery startTimeAfter(Date startTimeAfter);
	
	/**
	 * 根据任务主题
	 * @param taskParticipants
	 * @return
	 */
	ProcessInstanceQuery taskParticipants(String taskParticipants);

	/**
	 * 根据父流程实例号查询
	 * @param superProcessInstanceId
	 * @return
	 */
	ProcessInstanceQuery superProcessInstanceId(String superProcessInstanceId);

	/**
	 * 根据子流程实例号查询
	 * @param subProcessInstanceId
	 * @return
	 */
	ProcessInstanceQuery subProcessInstanceId(String subProcessInstanceId);
	
	/**
	 * 根据流程实例编号排序
	 * @return
	 */
	ProcessInstanceQuery orderByProcessInstanceId();
	
	/**
	 * 根据流程实例开始时间排序
	 * @return
	 */
	ProcessInstanceQuery orderByStartTime();

	/**
	 * 根据流程定义key排序
	 * @return
	 */
	ProcessInstanceQuery orderByProcessDefinitionKey();

	/**
	 * 根据流程定义编号排序
	 * @return
	 */
	ProcessInstanceQuery orderByProcessDefinitionId();
	
	/**
	 * 根据上次更新时间排序
	 * @return
	 */
	ProcessInstanceQuery orderByUpdateTime();

}
