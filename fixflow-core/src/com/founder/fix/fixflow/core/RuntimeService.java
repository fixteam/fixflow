package com.founder.fix.fixflow.core;



import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.command.MessageStartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.core.runtime.TokenQuery;
import com.founder.fix.fixflow.core.subscription.EventSubscriptionQuery;
import com.founder.fix.fixflow.core.task.TaskInstance;

/**
 * 获取运行时服务(用于管理运行时流程实例的操作)
 * @author kenshin
 */
public interface RuntimeService extends ProcessService{
  
	
	
	/**
	 * 人工启动流程实例
	 * @param startProcessInstanceCommand
	 * @return
	 */
	ProcessInstance noneStartProcessInstance(StartProcessInstanceCommand startProcessInstanceCommand);
	
	/**
	 * 定时任务启动流程实例
	 * @param startProcessInstanceCommand
	 * @return
	 */
	ProcessInstance timeStartProcessInstance(StartProcessInstanceCommand startProcessInstanceCommand);
	
	/**
	 * 消息启动流程实例
	 * @param messageStartProcessInstanceCommand
	 * @return
	 */
	ProcessInstance messageStartProcessInstance(MessageStartProcessInstanceCommand messageStartProcessInstanceCommand);
	
	
	
	
	@Deprecated
	/**
	 * 启动一个新的流程实例,基于流程定义的最新版本创建.
	 * 一个业务对象可以提供一个关键值,用来于流程实例进行关联这种业务的关键,
	 * 然后通过这个关键值方便地查找该流程实例，请参考{@link ProcessInstanceQuery#processInstanceBusinessKey(String)}. 
	 * 一般情况下强烈推荐使用 businessKey 做关联
	 * 注意, businessKey 必须在指定的 processDefinition 是唯一的。
	 * 一个流程定义的流程实例都不允许有相同businessKey。
	 * processdefinitionKey 和 businessKey 组合必须是唯一的。
	 * @param processDefinitionKey 流程定义key(xml定义里的 process id,数据库中的 key)
	 * @param businessKey 业务关联键
	 * @param initiator 提交人
	 * @param transientVariables 瞬态变量 可以为空
	 * @param variables 可持久化变量 可以为空
	 * @return 流程实例
	 */
	ProcessInstance startProcessInstanceByKey(StartProcessInstanceCommand startProcessInstanceCommand);
	

	
	
	@Deprecated
	/**
	 * 启动一个新的流程实例,基于流程定义的最新版本创建.
	 * 一个业务对象可以提供一个关键值,用来于流程实例进行关联这种业务的关键,
	 * 然后通过这个关键值方便地查找该流程实例，请参考{@link ProcessInstanceQuery#processInstanceBusinessKey(String)}. 
	 * 一般情况下强烈推荐使用 businessKey 做关联
	 * 注意, businessKey 必须在指定的 processDefinition 是唯一的。
	 * 一个流程定义的流程实例都不允许有相同businessKey。
	 * @param processDefinitionId  流程定义id，唯一编号,不能为空。(数据库中的 id)
	 * @param businessKey 业务关联键
	 * @param initiator 提交人
	 * @param transientVariables 瞬态变量 可以为空
	 * @param variables 可持久化变量 可以为空
	 * @return 流程实例
	 */
	ProcessInstance startProcessInstanceById(StartProcessInstanceCommand startProcessInstanceCommand);
	
	
	
	
	
	
	@Deprecated
	ProcessInstance startProcessInstanceByMessage(MessageStartProcessInstanceCommand messageStartProcessInstanceCommand);
	
	
	
	
	EventSubscriptionQuery createEventSubscriptionQuery();
	
	void signal(Map<String, Object> transientVariables);
	
	void signal(Map<String, Object> transientVariables,Map<String, Object> dataVariables);
	
	void tokenSignal(String tokenId,Map<String, Object> transientVariables);
	
	void tokenSignal(String tokenId, Map<String, Object> transientVariables,Map<String, Object> dataVariables);
	
	/**
	 * 令牌超时推动
	 * @param tokenId 令牌编号
	 */
	void tokenTimeOut(String tokenId,Map<String, Object> transientVariables);
	
	
	/**
	 * 令牌超时推动
	 * @param tokenId 令牌编号
	 * @param nodeId 开始的节点号
	 * @param transientVariables
	 */
	void tokenTimeOut(String tokenId,String nodeId,Map<String, Object> transientVariables);
	
	/**
	 * 删除一个现有的运行过程实例。
	 * @param processInstanceId 需要删除的流程实例编号,不能为空.
	 * @param cascade 是否级联删除流程实例相关数据.
	 * @return 
	 */
	boolean deleteProcessInstance(String processInstanceId, boolean cascade);
	
	/**
	 * 删除一个现有的运行过程实例。
	 * @param processDefinitionKey 流程定义key
	 * @param businessKey 业务关联键
	 * @param cascade 是否级联删除流程实例相关数据.
	 * @return 
	 */
	boolean deleteProcessInstance(String processDefinitionKey, String businessKey, boolean cascade);
	
	
	void updateProcessInstanceBusinessKey(String processInstanceId,String businessKey);
	
	
	ProcessInstanceQuery createProcessInstanceQuery();
	
	/**
	 * 创建令牌的查询
	 * @return
	 */
	TokenQuery createTokenQuery();
	
	
	
	
	/**
	 * 设置流程实例的持久变量
	 * 
	 * @param processInstanceId
	 *            流程实例编号
	 * @param variableName
	 *            变量key
	 * @param value
	 *            变量值
	 */
	void setProcessInstanceVariable(String processInstanceId, String variableName, Object value);

	/**
	 * 设置流程实例的持久变量
	 * 
	 * @param processInstanceId
	 *            流程实例编号
	 * @param variables
	 *            变量Map
	 */
	void setProcessInstanceVariables(String processInstanceId, Map<String, ? extends Object> variables);

	/**
	 * 获取流程实例的持久变量
	 * 
	 * @param processInstanceId
	 *            流程实例编号
	 * @param variableName
	 *            变量key
	 * @return 变量值
	 */
	Object getProcessInstanceVariable(String processInstanceId, String variableName);

	/**
	 * 获取流程实例的所有持久化变量
	 * 
	 * @param processInstanceId
	 *            任务编号
	 * @return 变量Map
	 */
	Map<String, Object> getProcessInstanceVariables(String processInstanceId);

	/**
	 * 获取流程实例的指定的持久化变量
	 * 
	 * @param processInstanceId
	 *            流程实例编号
	 * @param variableNames
	 *            变量key集合
	 * @return 变量Map
	 */
	Map<String, Object> getProcessInstanceVariables(String processInstanceId, Collection<String> variableNames);

	
	
	
	
	
	/**
	 * 设置令牌的持久变量
	 * 
	 * @param tokenId
	 *            令牌编号
	 * @param variableName
	 *            变量key
	 * @param value
	 *            变量值
	 */
	void setTokenVariable(String tokenId, String variableName, Object value);

	/**
	 * 设置令牌的持久变量
	 * 
	 * @param tokenId
	 *            令牌编号
	 * @param variables
	 *            变量Map
	 */
	void setTokenVariables(String tokenId, Map<String, ? extends Object> variables);

	/**
	 * 获取令牌的持久变量
	 * 
	 * @param tokenId
	 *            令牌编号
	 * @param variableName
	 *            变量key
	 * @return 变量值
	 */
	Object getTokenVariable(String tokenId, String variableName);

	/**
	 * 获取令牌的所有持久化变量
	 * 
	 * @param tokenId
	 *            令牌编号
	 * @return 变量Map
	 */
	Map<String, Object> getTokenVariables(String tokenId);

	/**
	 * 获取令牌的指定的持久化变量
	 * 
	 * @param tokenId
	 *            令牌编号
	 * @param variableNames
	 *            变量key集合
	 * @return 变量Map
	 */
	Map<String, Object> getTokenVariables(String tokenId, Collection<String> variableNames);
	
	/**
	 * 插入流程归档数据
	 */
	public boolean insertPigeonholeData();
	
	/**
	 * 流程绩效接口
	 * @param processKey 流程名
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param firstPage 第几页
	 * @param maxSize 每页大小
	 * @return
	 */
	List<Map<String,Object>> processPerformance(String processKey, String startTime, String endTime, int firstPage, int maxSize );
	
	/**
	 * 流程绩效 接口4
	 * @param processKey 流程名
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	List<Map<String,Object>> processPerformance(String[] processKey, String startTime, String endTime);
	
	/**
	 * 流程绩效接口1
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Map<String,Object>> processPerformance(String startTime, String endTime);
	
	/**
	 * 流程绩效接口5
	 * @param startTime
	 * @param endTime
	 * @param pid
	 * @return
	 */
	List<Map<String,Object>> processPerformance(String startTime, String endTime, String pid);
	
	/**
	 * 流程绩效接口2
	 * @param startTime
	 * @param endTime
	 * @param firstPage
	 * @param maxSize
	 * @return
	 */
	List<Map<String,Object>> processPerformance(String startTime, String endTime, int firstPage, int maxSize);
	
	/**
	 * 流程绩效接口2取总
	 * @param startTime
	 * @param endTime
	 * @param firstPage
	 * @param maxSize
	 * @return
	 */
	int processPerformance2(String startTime, String endTime);
	
	<T> T ExpandCmd(String cmdId,Map<String, Object> parameterMap, T classReturn);
	
	
	
	List<TaskInstance> getNotDoneTask(ProcessInstance processInstance);
	
}
