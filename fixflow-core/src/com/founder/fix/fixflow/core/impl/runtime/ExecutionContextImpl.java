package com.founder.fix.fixflow.core.impl.runtime;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.SequenceFlow;

import com.founder.fix.bpmn2extensions.fixflow.SkipStrategy;
import com.founder.fix.fixflow.core.context.ContextInstance;
import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.connector.ConnectorDefinition;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.util.CoreUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskDefinition;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskMgmtInstance;

public class ExecutionContextImpl implements ExecutionContext {

	/**
	 * 引擎令牌对象
	 */
	protected TokenEntity token;

	/**
	 * 线条对象
	 */
	protected SequenceFlow sequenceFlow;

	/**
	 * 线条来自于的节点
	 */
	protected FlowNode sequenceFlowSource;

	protected TaskDefinition taskDefinition;

	protected TaskInstance taskInstance;

	protected ProcessInstance subProcessInstance;

	protected ConnectorDefinition connector;

	protected BaseElementEvent baseElementEvent;

	protected BaseElement eventSource = null;

	protected String rollBackAssignee;

	protected String groupID;

	protected String callActivityInstanceId;

	/**
	 * 将要去的节点 当指定了将要去的节点，离开节点的时候令牌将不按照线条上的走向行走， 而是直接转移到指定的节点，多用于退回跳转。
	 */
	protected FlowNode toFlowNode;

	protected SkipStrategy skipStrategy;

	/**
	 * 执行内容对象默认构造函数
	 * 
	 * @param token
	 */
	public ExecutionContextImpl(TokenEntity token) {

		this.token = token;
	}

	public ExecutionContextImpl(ExecutionContext other) {
		this.token = other.getToken();
		this.baseElementEvent = other.getBaseElementEvent();
		this.connector = other.getConnector();
	}

	public void clearExecutionContextData() {

		this.setSequenceFlow(null);

		this.setSequenceFlowSource(null);

		this.setGroupID(null);

		this.setToFlowNode(null);

	}

	/**
	 * 获取令牌
	 * 
	 * @return
	 */
	public TokenEntity getToken() {
		// TODO Auto-generated method stub
		return token;
	}

	/**
	 * 设置线条
	 * 
	 * @param sequenceFlow
	 *            线条
	 */
	public void setSequenceFlow(SequenceFlow sequenceFlow) {
		this.sequenceFlow = sequenceFlow;

	}

	public void setSequenceFlowSource(FlowNode flowNode) {
		this.sequenceFlowSource = flowNode;
	}

	public TaskMgmtInstance getTaskMgmtInstance() {
		return token.getProcessInstance().getTaskMgmtInstance();
	}

	public void setTaskDefinition(TaskDefinition taskDefinition) {

		this.taskDefinition = taskDefinition;
	}

	public void setTaskInstance(TaskInstance taskInstance) {
		this.taskInstance = taskInstance;
	}

	/**
	 * 获取线条
	 * 
	 * @return
	 */
	public SequenceFlow getSequenceFlow() {

		return sequenceFlow;
	}

	public FlowNode getSequenceFlowSource() {

		return sequenceFlowSource;
	}

	public TaskDefinition getTaskDefinition() {

		return taskDefinition;
	}

	public TaskInstance getTaskInstance() {

		return taskInstance;
	}

	public ProcessDefinitionBehavior getProcessDefinition() {
		return token.getProcessInstance().getProcessDefinition();
	}

	public ProcessInstanceEntity getProcessInstance() {
		return token.getProcessInstance();
	}

	public ContextInstance getContextInstance() {
		// TODO Auto-generated method stub
		return token.getProcessInstance().getContextInstance();
	}

	public String getInitiator() {
		return getProcessInstance().getInitiator();
	}

	public String getStartAuthor() {
		return getProcessInstance().getStartAuthor();
	}

	public String getBizKey() {
		return getProcessInstance().getBizKey();
	}

	public String getUserCommand() {
		if (getContextInstance().getTransientVariable("fixVariable_userCommand", this) != null) {
			return getContextInstance().getTransientVariable("fixVariable_userCommand", this).toString();
		}
		return null;
	}

	public void setSubProcessInstance(ProcessInstance subProcessInstance) {
		// TODO Auto-generated method stub
		this.subProcessInstance = subProcessInstance;
	}

	public ProcessInstance getSubProcessInstance() {
		return this.subProcessInstance;
	}

	public void setEventSource(BaseElement eventSource) {
		this.eventSource = eventSource;
	}

	public BaseElement getEventSource() {
		return this.eventSource;
	}

	public void setConnector(ConnectorDefinition connector) {
		this.connector = connector;
	}

	public BaseElementEvent getBaseElementEvent() {
		return baseElementEvent;
	}

	public void setBaseElementEvent(BaseElementEvent baseElementEvent) {
		this.baseElementEvent = baseElementEvent;
	}

	public ConnectorDefinition getConnector() {
		return this.connector;
	}

	public SqlCommand getSqlCommand() {
		// TODO Auto-generated method stub

		SqlCommand sqlCommand = new SqlCommand(Context.getDbConnection());
		return sqlCommand;
	}

	public Object selectOneObject(String sqlText) {
		// TODO Auto-generated method stub

		SqlCommand sqlCommand = new SqlCommand(Context.getDbConnection());
		return sqlCommand.queryForValue(ExpressionMgmt.execute(sqlText, this).toString());

	}

	public String getRollBackAssignee() {
		// TODO Auto-generated method stub
		return this.rollBackAssignee;
	}

	public void setRollBackAssignee(String rollBackAssignee) {
		// TODO Auto-generated method stub
		this.rollBackAssignee = rollBackAssignee;
	}

	public UserTo findUserInfoByUserId(String userId) {
		// TODO Auto-generated method stub
		return Authentication.findUserInfoByUserId(userId);
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public List<String> findUserDeptAndRole(String deptId, String roleId) {

		return Authentication.findUserDeptAndRole(deptId, roleId);
	}

	public String getProcessDefinitionKey() {
		return getProcessDefinition().getProcessDefinitionKey();
	}

	public String getCallActivityInstanceId() {
		return callActivityInstanceId;
	}

	public void setCallActivityInstanceId(String callActivityInstanceId) {
		this.callActivityInstanceId = callActivityInstanceId;
	}

	public FlowNode getToFlowNode() {
		return toFlowNode;
	}

	public void setToFlowNode(FlowNode toFlowNode) {
		this.toFlowNode = toFlowNode;
	}

	public SkipStrategy getSkipStrategy() {
		return this.skipStrategy;
	}

	public void setSkipStrategy(SkipStrategy skipStrategy) {
		this.skipStrategy = skipStrategy;
	}

	/**
	 * 获取上一步任务
	 */
	public List<TaskInstance> getPreviousAssignee() {

		
		List<TaskInstance> taskInstanceQueryToTemp=new ArrayList<TaskInstance>();
		TaskInstance taskInstanceQuery = getTaskInstance();
		
		if(taskInstanceQuery==null){
			taskInstanceQueryToTemp=CoreUtil.getRollBackTaskByToken(getToken());
		}
		else{
			taskInstanceQueryToTemp=CoreUtil.getRollBackTask(taskInstanceQuery);
		}
		

		return taskInstanceQueryToTemp;
		
	}


}
