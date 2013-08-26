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
package com.founder.fix.fixflow.core.impl.runtime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.AbstractQuery;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.ProcessInstanceQueryProperty;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;
import com.founder.fix.fixflow.core.impl.task.QueryExpandTo;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.core.runtime.QueryLocation;


public class ProcessInstanceQueryImpl extends AbstractQuery<ProcessInstanceQuery, ProcessInstance> implements ProcessInstanceQuery {

	protected String executionId;
	protected String businessKey;
	protected String processDefinitionId;
	protected String processDefinitionKey;
	protected String superProcessInstanceId;
	protected String subProcessInstanceId;
	protected String initiator;
	protected String taskParticipants;
	protected Date updateTime;
	protected String isSuspended;
	protected List<String> processDefinitionKeyList=new ArrayList<String>();

	protected boolean isContainsSubProcess=false;
	


	// Unused, see dynamic query
	protected String activityId;

	protected CommandExecutor commandExecutor;
	
	
	public ProcessInstanceQueryImpl() {
		
	}

	public ProcessInstanceQueryImpl(CommandContext commandContext) {
		super(commandContext);
	}

	public ProcessInstanceQueryImpl(CommandExecutor commandExecutor) {
		super(commandExecutor);
	}

	public ProcessInstanceQueryImpl processInstanceId(String processInstanceId) {
		if (processInstanceId == null) {
			throw new FixFlowException("Process instance id is null");
		}
		this.executionId = processInstanceId;
		return this;
	}

	public ProcessInstanceQuery processInstanceBusinessKey(String businessKey) {
		if (businessKey == null) {
			throw new FixFlowException("Business key is null");
		}
		this.businessKey = businessKey;
		return this;
	}

	public ProcessInstanceQuery containsSubProcess() {
		this.isContainsSubProcess=true;
		return this;
	}
	
	public ProcessInstanceQuery isSuspended(boolean isSuspended) {
		this.isSuspended=String.valueOf(isSuspended);
		return this;
	}

	public ProcessInstanceQuery processInstanceBusinessKey(String businessKey, String processDefinitionKey) {
		if (businessKey == null) {
			throw new FixFlowException("Business key is null");
		}
		this.businessKey = businessKey;
		this.processDefinitionKey = processDefinitionKey;
		return this;
	}

	public ProcessInstanceQueryImpl processDefinitionId(String processDefinitionId) {
		if (processDefinitionId == null) {
			throw new FixFlowException("Process definition id is null");
		}
		this.processDefinitionId = processDefinitionId;
		return this;
	}
	
	public ProcessInstanceQueryImpl taskParticipants(String taskParticipants) {
		this.taskParticipants=taskParticipants;
		
		return this;
	}
	
	
	public ProcessInstanceQueryImpl initiator(String initiator) {
		this.initiator=initiator;
		
		return this;
	}
	
	
	protected String isPigeonhole;
	

	public ProcessInstanceQuery isPigeonhole() {
		// TODO 自动生成的方法存根
		isPigeonhole="1";
		return this;
	}

	public ProcessInstanceQuery notPigeonhole() {
		isPigeonhole="0";
		return this;
	}
	
	protected String isEnd;
	
	public ProcessInstanceQuery isEnd() {
		// TODO 自动生成的方法存根
		isEnd="true";
		return this;
	}

	public ProcessInstanceQuery notEnd() {
		isEnd="false";
		return this;
	}


	public ProcessInstanceQueryImpl processDefinitionKey(String processDefinitionKey) {
		if (processDefinitionKey == null) {
			throw new FixFlowException("Process definition key is null");
		}
		this.processDefinitionKey = processDefinitionKey;
		return this;
	}
	
	public ProcessInstanceQuery processDefinitionKey(List<String> processDefinitionKeyList) {
		if(processDefinitionKeyList!=null&&processDefinitionKeyList.size()>0){
			this.processDefinitionKeyList=processDefinitionKeyList;
		}
		else{
			throw new FixFlowException("processDefinitionKeyList 不能为空!");
		}
		return this;
	}

	public ProcessInstanceQuery superProcessInstanceId(String superProcessInstanceId) {
		this.superProcessInstanceId = superProcessInstanceId;
		return this;
	}

	public ProcessInstanceQuery subProcessInstanceId(String subProcessInstanceId) {
		this.subProcessInstanceId = subProcessInstanceId;
		return this;
	}

	public ProcessInstanceQuery orderByProcessInstanceId() {
		this.orderProperty = ProcessInstanceQueryProperty.PROCESS_INSTANCE_ID;
		return this;
	}
	
	public ProcessInstanceQuery orderByStartTime() {
		this.orderProperty = ProcessInstanceQueryProperty.START_TIME;
		return this;
	}
	

	public ProcessInstanceQuery orderByProcessDefinitionId() {
		this.orderProperty = ProcessInstanceQueryProperty.PROCESS_DEFINITION_ID;
		return this;
	}

	public ProcessInstanceQuery orderByProcessDefinitionKey() {
		this.orderProperty = ProcessInstanceQueryProperty.PROCESS_DEFINITION_KEY;
		return this;
	}
	//按更新时间排序--by ych 2013-07-23
	public ProcessInstanceQuery orderByUpdateTime() {
		this.orderProperty = ProcessInstanceQueryProperty.UPDATE_TIME;
		return this;
	}

	// results /////////////////////////////////////////////////////////////////

	public long executeCount(CommandContext commandContext) {
		checkQueryOk();
		// ensureVariablesInitialized();
		return commandContext.getProcessInstanceManager().findProcessInstanceCountByQueryCriteria(this);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProcessInstance> executeList(CommandContext commandContext, Page page) {
		checkQueryOk();
		// ensureVariablesInitialized();
		return (List)commandContext.getProcessInstanceManager().findProcessInstanceByQueryCriteria(this, page);
	}

	// getters /////////////////////////////////////////////////////////////////

	public boolean getOnlyProcessInstances() {
		return true; // See dynamic query in runtime.mapping.xml
	}

	public String getProcessInstanceId() {
		return executionId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public String getActivityId() {
		return null; // Unused, see dynamic query
	}

	public String getSuperProcessInstanceId() {
		return superProcessInstanceId;
	}

	public String getSubProcessInstanceId() {
		return subProcessInstanceId;
	}
	
	public String getInitiator() {
		return initiator;
	}
	
	public String getTaskParticipants() {
		return taskParticipants;
	}
	
	protected String initiatorLike;

	protected String subject;
	
	protected String subjectLike;
	
	protected Date startTime;
	
	protected Date startTimeBefore;
	
	protected Date startTimeAfter;
	
	protected Date archiveTime;
	
	protected Date archiveTimeBefore;
	
	protected Date archiveTimeAfter;
	
	protected QueryLocation queryLocation = null;

	public ProcessInstanceQuery initiatorLike(String initiatorLike) {
		this.initiatorLike=initiatorLike;
		return this;
	}

	public ProcessInstanceQuery subject(String subject) {
		this.subject=subject;
		return this;
	}

	public ProcessInstanceQuery subjectLike(String subjectLike) {
		this.subjectLike=subjectLike;
		return this;
	}

	public ProcessInstanceQuery startTimeOn(Date startTime) {
		this.startTime=startTime;
		return this;
	}

	public ProcessInstanceQuery startTimeBefore(Date startTimeBefore) {
		this.startTimeBefore=startTimeBefore;
		return this;
	}

	public ProcessInstanceQuery startTimeAfter(Date startTimeAfter) {
		this.startTimeAfter=startTimeAfter;
		return this;
	}
	
	public ProcessInstanceQuery archiveTimeOn(Date archiveTime) {
		this.archiveTime = archiveTime;
		return this;
	}
	
	public ProcessInstanceQuery archiveTimeAfter(Date archiveTimeAfter) {
		this.archiveTimeAfter = archiveTimeAfter;
		return this;
	}
	
	public ProcessInstanceQuery archiveTimeBefore(Date archiveTimeBefore) {
		this.archiveTimeBefore = archiveTimeBefore;
		return this;
	}
	
	public ProcessInstanceQuery his() {
		if(this.queryLocation != null){
			this.queryLocation = QueryLocation.RUN_HIS;
		}else{
			this.queryLocation = QueryLocation.HIS;
		}
		return this;
	}
	
	public ProcessInstanceQuery run() {
		if(this.queryLocation != null){
			this.queryLocation = QueryLocation.RUN_HIS;
		}else{
			this.queryLocation = QueryLocation.RUN;
		}
		return this;
	}

	
	public String getExecutionId() {
		return executionId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public CommandExecutor getCommandExecutor() {
		return commandExecutor;
	}

	public Date getArchiveTime() {
		return archiveTime;
	}

	public Date getArchiveTimeBefore() {
		return archiveTimeBefore;
	}

	public Date getArchiveTimeAfter() {
		return archiveTimeAfter;
	}

	public String getInitiatorLike() {
		return initiatorLike;
	}

	public String getSubject() {
		return subject;
	}

	public String getSubjectLike() {
		return subjectLike;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getStartTimeBefore() {
		return startTimeBefore;
	}

	public Date getStartTimeAfter() {
		return startTimeAfter;
	}

	public String getIsPigeonhole() {
		return isPigeonhole;
	}

	public QueryLocation getQueryLocation() {
		return queryLocation;
	}

	public String getIsEnd() {
		return isEnd;
	}
	
	public String getIsSuspended() {
		return isSuspended;
	}

	
	public List<String> getProcessDefinitionKeyList() {
		return processDefinitionKeyList;
	}

	public QueryExpandTo getQueryExpandTo() {
		return queryExpandTo;
	}
	
	public boolean isContainsSubProcess() {
		return isContainsSubProcess;
	}
	
	/* 变量查询 */
	protected String processInstanceVariableKey;
	protected String processInstanceVariableValue;
	protected boolean processInstanceVariableValueIsLike;

	public ProcessInstanceQuery processInstanceVariableData(String variableValue, boolean isLike) {
		this.processInstanceVariableValue=variableValue;
		this.processInstanceVariableValueIsLike=isLike;
		return this;
	}

	public ProcessInstanceQuery processInstanceVariableData(String variableKey, String variableValue, boolean isLike) {
		this.processInstanceVariableValue=variableValue;
		this.processInstanceVariableValueIsLike=isLike;
		this.processInstanceVariableKey=variableKey;
		return this;
	}


	
	public String getProcessInstanceVariableKey() {
		return processInstanceVariableKey;
	}

	public String getProcessInstanceVariableValue() {
		return processInstanceVariableValue;
	}

	public boolean isProcessInstanceVariableValueIsLike() {
		return processInstanceVariableValueIsLike;
	}
	
	


}
