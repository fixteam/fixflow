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

}
