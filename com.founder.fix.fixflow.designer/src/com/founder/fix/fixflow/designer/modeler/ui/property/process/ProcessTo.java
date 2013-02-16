package com.founder.fix.fixflow.designer.modeler.ui.property.process;

public class ProcessTo {
	
	/**
	 * 定义编号
	 */
	protected String processKey;
	
	/**
	 * 唯一编号
	 */
	protected String processId;
	
	/**
	 * 流程分类
	 */
	protected String category;
	
	/**
	 * 流程名称
	 */
	protected String processName;
	
	/**
	 * 流程版本号
	 */
	protected int version;
	
	public String getProcessKey() {
		return processKey;
	}
	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	
	
}
