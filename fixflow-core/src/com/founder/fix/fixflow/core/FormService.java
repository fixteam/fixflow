package com.founder.fix.fixflow.core;


public interface FormService  extends ProcessService{
	
	/**
	 * 获取开始节点表单
	 * 基于流程定义的最新版本创建获取
	 * @param processDefinitionKey 流程定义key(xml定义里的 process id,数据库中的 key)
	 * @return 开始节点表单URL
	 */
	String getStartFormByKey(String processDefinitionKey);
	
	
	/**
	 * 获取开始节点表单
	 * 基于一个指定具体编号的流程定义(指定版本).
	 * @param processDefinitionId 流程定义id，唯一编号,不能为空。(数据库中的 id)
	 * @return 开始节点表单URL
	 */
	String getStartFormById(String processDefinitionId);
	
	/**
	 * 获取任务表单
	 * @param processDefinitionId 流程定义id，唯一编号,不能为空。(数据库中的 id)
	 * @param nodeId 任务节点编号
	 * @return 节点表单URL
	 */
	String getTaskFormByNodeId(String processDefinitionId,String nodeId);
	
	
	

}
