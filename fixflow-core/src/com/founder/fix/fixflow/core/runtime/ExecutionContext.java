package com.founder.fix.fixflow.core.runtime;



import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.SequenceFlow;


import com.founder.fix.bpmn2extensions.fixflow.SkipStrategy;
import com.founder.fix.fixflow.core.context.ContextInstance;
import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.connector.ConnectorDefinition;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.task.TaskDefinition;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskMgmtInstance;

public interface ExecutionContext {
	
	
	TokenEntity getToken();
	
	SequenceFlow getSequenceFlow();
	
	void setSequenceFlow(SequenceFlow sequenceFlow);
	
	FlowNode getSequenceFlowSource();
	
	void setSequenceFlowSource(FlowNode flowNode);
	
	TaskDefinition getTaskDefinition();
	
	void setTaskDefinition(TaskDefinition taskDefinition);
	
	TaskInstance getTaskInstance();
	
	void setTaskInstance(TaskInstance taskInstance);
	


	/**
	 * 清理内容执行器临时数据
	 */
	void clearExecutionContextData();
	
	TaskMgmtInstance getTaskMgmtInstance();
	
	ProcessDefinitionBehavior getProcessDefinition();
	
	ProcessInstanceEntity getProcessInstance();
	
	ContextInstance getContextInstance();
	
	String getRollBackAssignee();
	
	void setRollBackAssignee(String rollBackAssignee);
	//表达式专用
	
	String getInitiator();
	
	String getBizKey();
	
	
	String getUserCommand();

	void setSubProcessInstance(ProcessInstance subProcessInstance);
	
	ProcessInstance getSubProcessInstance();

	void setEventSource(BaseElement eventSource);

	BaseElement getEventSource();

	BaseElementEvent getBaseElementEvent();

	void setBaseElementEvent(BaseElementEvent baseElementEvent);
	
	ConnectorDefinition getConnector();

	void setConnector(ConnectorDefinition connector);
	
	
	SqlCommand getSqlCommand();

	
	 Object selectOneObject(String sqlText);
	 
	String getStartAuthor();
	
	UserTo findUserInfoByUserId(String userId);
	
	String getGroupID();

	void setGroupID(String groupID);
	
	
	List<String> findUserDeptAndRole(String deptId,String roleId);
	
	String getProcessDefinitionKey();
	
	
	String getCallActivityInstanceId();
	
	void setCallActivityInstanceId(String callActivityInstanceId);
	
	/**
	 * 获取强制指定令牌将要去的节点
	 * @return
	 */
	FlowNode getToFlowNode();

	/**
	 * 强制指定令牌将要去的节点
	 * @param toFlowNode 节点对象
	 */
	void setToFlowNode(FlowNode toFlowNode);
	
	
	SkipStrategy getSkipStrategy();
	
	void setSkipStrategy(SkipStrategy skipStrategy);
	
	/**
	 * 获取上一步任务
	 * @return 如果上一步是会签则返回多个任务,否则则是一个。
	 */
	List<TaskInstance> getPreviousAssignee();
	
}
