package com.founder.fix.fixflow.designer.jobconfig;

import org.quartz.JobKey;

public class JobTo {
	private String jobName;
	private String jobGroupName;
	private String processName;
	private String processUniqueKey;
	private String processId;
	private String nodeId;
	private String nodeName;
	private String processInstanceId;
	private String tokenId;
	private String quartzExpression;
	private String currentStatus;
	private String bizKey;
	private String jobType;
	private String transientVariableId;
	private String nextFireTime;
	private JobKey jobKey;
	private String connectorId;
	private String connectorInstanceId;
	private String connectorInstanceName;
	
	
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getProcessUniqueKey() {
		return processUniqueKey;
	}
	public void setProcessUniqueKey(String processUniqueKey) {
		this.processUniqueKey = processUniqueKey;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getQuartzExpression() {
		return quartzExpression;
	}
	public void setQuartzExpression(String quartzExpression) {
		this.quartzExpression = quartzExpression;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getNextFireTime() {
		return nextFireTime;
	}
	public void setNextFireTime(String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	public JobKey getJobKey() {
		return jobKey;
	}
	public void setJobKey(JobKey jobKey) {
		this.jobKey = jobKey;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroupName() {
		return jobGroupName;
	}
	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}
	public String getBizKey() {
		return bizKey;
	}
	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getTransientVariableId() {
		return transientVariableId;
	}
	public void setTransientVariableId(String transientVariableId) {
		this.transientVariableId = transientVariableId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getConnectorId() {
		return connectorId;
	}
	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}
	public String getConnectorInstanceId() {
		return connectorInstanceId;
	}
	public void setConnectorInstanceId(String connectorInstanceId) {
		this.connectorInstanceId = connectorInstanceId;
	}
	public String getConnectorInstanceName() {
		return connectorInstanceName;
	}
	public void setConnectorInstanceName(String connectorInstanceName) {
		this.connectorInstanceName = connectorInstanceName;
	}
}
