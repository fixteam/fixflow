package com.founder.fix.fixflow.core.impl.command;

public class SubmitTaskCommand extends BaseTaskCommand{
	
	
	
	

	public SubmitTaskCommand(){};
	
	public SubmitTaskCommand(BaseTaskCommand taskCommand) {
		// TODO Auto-generated constructor stub
		super(taskCommand);
	}
	/**
	 * 业务关联键
	 */
	protected String businessKey;
	/**
	 * 获取业务关联键
	 * @return 业务关联键
	 */
	public String getBusinessKey() {
		return businessKey;
	}

	/**
	 * 设置业务关联键
	 * @param businessKey 业务关联键
	 */
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	
	
	/**
	 * 提交人
	 */
	protected String initiator;
	
	
	
	/**
	 * 提交人
	 */
	public String getInitiator() {
		return initiator;
	}
	/**
	 * 提交人
	 */
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}
	
	
	
	
	

}
