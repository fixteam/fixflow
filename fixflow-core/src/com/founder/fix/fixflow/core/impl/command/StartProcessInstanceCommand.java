package com.founder.fix.fixflow.core.impl.command;


import com.founder.fix.fixflow.core.command.CommandParams;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.runtime.Token;

/**
*
* 启动一个新的流程实例,基于流程定义的最新版本创建.
* 一个业务对象可以提供一个关键值,用来于流程实例进行关联这种业务的关键,
* 然后通过这个关键值方便地查找该流程实例，请参考{@link ProcessInstanceQuery#processInstanceBusinessKey(String)}. 
* 一般情况下强烈推荐使用 businessKey 做关联
* 注意, businessKey 必须在指定的 processDefinition 是唯一的。
* 一个流程定义的流程实例都不允许有相同businessKey。
* processdefinitionKey 和 businessKey 组合必须是唯一的。
* @param processDefinitionKey 流程定义key(xml定义里的 process id,数据库中的 key)
* @param businessKey 业务关联键
* @param initiator 启动人
* @param transientVariables 瞬态变量 可以为空
* @param variables 可持久化变量 可以为空
*
* @author kenshin
 */
public class StartProcessInstanceCommand extends CommandParams {

	/**
	 * 流程定义id，唯一编号,不能为空。(数据库中的 id)
	 */
	protected String processDefinitionId;

	/**
	 * 流程定义key(xml定义里的 process id,数据库中的 key)
	 */
	protected String processDefinitionKey;

	/**
	 * 业务关联键
	 */
	protected String businessKey;

	/**
	 * 启动人
	 */
	protected String startAuthor;
	
	/**
	 * 父流程实例
	 */
	protected ProcessInstanceEntity parentProcessInstance;
	
	protected Token parentProcessInstanceToken;
	

	

	/**
	 * 流程定义id，唯一编号,不能为空。(数据库中的 id)
	 * @return
	 */
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	/**
	 * 流程定义id，唯一编号,不能为空。(数据库中的 id)
	 * @param processDefinitionId
	 */
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	/**
	 * 流程定义key(xml定义里的 process id,数据库中的 key)
	 */
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	/**
	 * 流程定义key(xml定义里的 process id,数据库中的 key)
	 */
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	/**
	 * 业务关联键
	 */
	public String getBusinessKey() {
		return businessKey;
	}

	/**
	 * 业务关联键
	 */
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	/**
	 * 启动人
	 */
	public String getStartAuthor() {
		return startAuthor;
	}
	/**
	 * 启动人
	 */
	public void setStartAuthor(String startAuthor) {
		this.startAuthor = startAuthor;
	}
	
	public ProcessInstanceEntity getParentProcessInstance() {
		return parentProcessInstance;
	}

	public void setParentProcessInstance(ProcessInstanceEntity parentProcessInstance) {
		this.parentProcessInstance = parentProcessInstance;
	}
	
	public Token getParentProcessInstanceToken() {
		return parentProcessInstanceToken;
	}

	public void setParentProcessInstanceToken(Token parentProcessInstanceToken) {
		this.parentProcessInstanceToken = parentProcessInstanceToken;
	}

}
