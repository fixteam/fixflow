package com.founder.fix.fixflow.core.task;



import java.util.Date;
import java.util.List;
import java.util.Map;


import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;

public interface TaskInstance extends PersistentObject{

	int PRIORITY_MINIUM = 0;
	int PRIORITY_NORMAL = 50;
	int PRIORITY_MAXIMUM = 100;
	
	
	
	/**
	 * 获取任务编号
	 * @return
	 */
	String getId();
	
	/**
	 * 设置任务编号
	 * @param id 任务编号
	 */
	void setId(String id);

	/**
	 * 获取任务名称
	 * @return
	 */
	String getName();

	/**
	 * 设置任务名称
	 * @param name 任务名称
	 */
	void setName(String name);

	/**
	 * 任务说明
	 * @return
	 */
	String getDescription();

	/**
	 * 设置任务说明
	 * @return
	 */
	void setDescription(String description);

	/**
	 * 任务优先级
	 * @return
	 */
	int getPriority();

	/**
	 * 设置任务的优先级
	 * @param priority 任务优先级 int PRIORITY_MINIUM = 0;int PRIORITY_NORMAL = 50;int PRIORITY_MAXIMUM = 100;
	 */
	void setPriority(int priority);

	/**
	 * 获取任务的所有者
	 * @return
	 */
	String getOwner();

	/**
	 * 设置任务的所有者
	 * @param owner 任务的所有者
	 */
	void setOwner(String owner);

	/**
	 * 获取任务的代理人
	 * @return
	 */
	String getAssignee();

	/**
	 * 设置任务的代理人
	 * @param assignee 任务的代理人
	 */
	void setAssignee(String assignee);

	/**
	 * 获取任务所在的节点号
	 *
	 */
	String getNodeId();
	
	/**
	 * 设置任务所在节点的编号
	 * @param nodeId 所在节点编号
	 */
	void setNodeId(String nodeId);
	

	
	/**
	 * 设置任务所在节点的名称
	 * @param nodeName 所在节点名称
	 */
	void setNodeName(String nodeName);

	
	/**
	 * 获取流程实例编号
	 * @return
	 */
	String getProcessInstanceId();
	
	/**
	 * 设置流程实例编号
	 * @param processInstanceId 流程实例编号
	 */
	void setProcessInstanceId(String processInstanceId);
	

	/** 获取任务代理状态 {@link DelegationState} */
	DelegationState getDelegationState();
	
	/**
	 * 设置任务代理状态
	 * @param delegationState 代理状态 {@link DelegationState}
	 */
	void setDelegationState(DelegationState delegationState);

	/**
	 * 获取业务关联键
	 * @return
	 */
	String getBizKey();
	
	/**
	 * 设置业务关联键
	 * @param bizKey 业务关联键
	 */
	void setBizKey(String bizKey);

	/**
	 * 获取令牌编号
	 * @return
	 */
	String getTokenId();
	
	/**
	 * 设置令牌编号
	 * @param tokenId 令牌编号
	 */
	void setTokenId(String tokenId);

	/**
	 * 获取任务的创建时间
	 * @return
	 */
	Date getCreateTime();
	
	/**
	 * 设置创建时间
	 * @param createTime 创建时间
	 */
	void setCreateTime(Date createTime);

	/**
	 * 获取任务的开始时间
	 * @return
	 */
	Date getStartTime();
	
	/**
	 * 设置开始时间(一般第一次阅读时间)
	 * @param startTime 开始时间
	 */
	void setStartTime(Date startTime);

	/**
	 * 获取任务的结束时间
	 * @return
	 */
	Date getEndTime();
	
	/**
	 * 设置结束时间
	 * @param endTime 结束时间
	 */
	void setEndTime(Date endTime);
	
	

	/**
	 * 获取任务关联的流程定义编号
	 * @return
	 */
	String getProcessDefinitionId();

	/**
	 * 设置任务关联的流程定义编号
	 * @param processDefinitionId 流程定义编号(唯一编号)
	 */
	void setProcessDefinitionId(String processDefinitionId);
	
	/**
	 * 设置任务关联的流程定义编号
	 * @param processDefinitionKey 流程定义编号
	 */
	void setProcessDefinitionKey(String processDefinitionKey);
	
	/**
	 * 获取截止日期
	 * @return
	 */
	Date getDueDate();
	
	/**
	 * 设置截止日期
	 * @param dueDate 截止日期
	 */
	void setDueDate(Date dueDate);

	/**
	 * 获取父任务的编号
	 * @return
	 */
	String getParentTaskInstanceId();
	
	/**
	 * 设置父任务实例编号
	 * @param parentTaskInstanceId 父任务实例编号
	 */
	void setParentTaskInstanceId(String parentTaskInstanceId);
	

	/**
	 * 任务是否阻塞
	 * @return
	 */
	boolean isBlocking();
	
	/**
	 * 设置任务是否阻塞
	 * @param isBlocking 任务是否阻塞
	 */
	void setBlocking(boolean isBlocking);
	
	/**
	 * 任务是否打开
	 * @return
	 */
	boolean isOpen();
	
	/**
	 * 恢复任务
	 */
	void resume();
	
	/**
	 * 暂停任务
	 */
	void suspend();
	
	
	
	/**
	 * 任务是否取消
	 * @return
	 */
	boolean isCancelled();
	
	/**
	 * 设置任务是否取消
	 * @param isCancelled 任务是否取消
	 */
	void setCancelled(boolean isCancelled);
	
	
	/**
	 * 任务是否结束
	 * @return
	 */
	boolean hasEnded();
	
	/**
	 * 任务是否暂停
	 * @return
	 */
	boolean isSuspended();

	/**
	 * 获取任务的处理命令类型
	 * @return
	 */
	String getCommandType();

	/**
	 * 获取任务处理命令内容(国际化)
	 * @return
	 */
	String getCommandMessage();
	
	/**
	 * 获取数据中存储的默认的处理命令名称
	 * @return
	 */
	String getDefaultCommandMessage();

	/**
	 * 获取任务的意见
	 * @return
	 */
	String getTaskComment();

	/**
	 * 获取任务所在的节点名称
	 * @return
	 */
	String getNodeName();
	
	/**
	 * 获取任务的领取时间
	 * @return
	 */
	Date getClaimTime();

	String getProcessDefinitionKey();
	
	
	List<IdentityLink> getIdentityLinkQueryToList();
	
	
    void addCandidateGroup(GroupTo groupTo,IncludeExclusion includeExclusion);
	

	void addCandidateUser(String userId,IncludeExclusion includeExclusion);
	
	
	
	/**
	 * 获取任务对应的流程定义对象
	 * @return 流程定义对象
	 */
	ProcessDefinitionBehavior getProcessDefinition();
	
	/**
	 * 获取表单地址
	 * @return
	 */
	String getFormUri();
	
	
	/**
	 * 设置表单地址
	 * @return
	 */
	void setFormUri(String formUri);
	
	/**
	 * 获取会签任务分组编号
	 * @return
	 */
	String getTaskGroup();
	
	/**
	 * 设置会签任务组
	 * @param taskGroup 签任务组
	 */
	void setTaskGroup(String taskGroup);
	
	
	/**
	 * 获取任务实例类型
	 * @return 任务实例类型 {@link TaskInstanceType}
	 */
	TaskInstanceType getTaskInstanceType();

	/**
	 * 设置任务实例
	 * @param taskInstanceType 任务实例 {@link TaskInstanceType}
	 */
	void setTaskInstanceType(TaskInstanceType taskInstanceType);
	
	String getProcessDefinitionName();

	void setProcessDefinitionName(String processDefinitionName);
	
	
	
	boolean isDraft();
	
	void setDraft(boolean isDraft);
	
	String getCategory();

	void setCategory(String category);
	
	int getExpectedExecutionTime();

	void setExpectedExecutionTime(int expectedExecutionTime);
	
	
	String getCallActivityInstanceId();

	void setCallActivityInstanceId(String callActivityTaskId);
	
	
	/**
	 * 获取任务扩展字段(大小写区分)
	 * @return 任务扩展字段
	 */
	Map<String, Object> getExtensionFields();
	
	/**
	 * 获取任务扩展字段值
	 * @param fieldName 字段名称(大小写区分)
	 * @return 任务扩展字段值
	 */
	Object getExtensionField(String fieldName);
	
	
	/**
	 * 给task添加一个新的字段值用来保存到数据库中
	 * @param fieldName 数据库字段名(大小写区分)
	 * @param value 值
	 */
	void setPersistenceExtensionField(String fieldName,Object value);

}
