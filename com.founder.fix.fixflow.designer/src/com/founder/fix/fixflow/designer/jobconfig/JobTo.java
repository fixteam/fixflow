package com.founder.fix.fixflow.designer.jobconfig;

import org.quartz.JobKey;

public class JobTo {
	private String processName;
	private String processUniqueKey;
	private String processId;
	private String nodeId;
	private String processInstanceId;
	private String tokenId;
	private String quartzExpression;
	private String currentStatus;
	private String nextFireTime;
	private JobKey jobKey;
	
	
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
}
