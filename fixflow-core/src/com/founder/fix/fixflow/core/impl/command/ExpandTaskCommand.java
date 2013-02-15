package com.founder.fix.fixflow.core.impl.command;

import java.util.Map;




/**
 * 扩展类型
 * @author kenshin
 *
 */
public class ExpandTaskCommand extends BaseTaskCommand {
	
	
	public ExpandTaskCommand(){};
	
	public ExpandTaskCommand(BaseTaskCommand baseTaskCommand){
		super(baseTaskCommand);
	}
	
	protected String businessKey;
	
	protected String initiator;
	
	protected String processDefinitionKey;
	
	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	/**
	 * 动态参数Map
	 */
	protected Map<String, Object> paramMap;

	/**
	 * 获取动态参数Map
	 * @return
	 */
	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	/**
	 * 设置动态参数Map
	 * @param paramMap 动态参数Map
	 */
	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}


}
