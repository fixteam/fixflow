package com.founder.fix.fixflow.core.task;


import java.util.Date;
import java.util.List;

import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.task.IdentityLinkEntity;




/**
 * 任务分配接口
 * @author kenshin
 *
 */
public interface Assignable {


	String getId();


	String getName();


	void setName(String name);

	String getDescription();


	void setDescription(String description);


	int getPriority();


	void setPriority(int priority);

	/**
	  * 获取任务的直接指派人
	  * @return
	  */
	String getAssignee();

	/**
	 * 设置任务的直接指派人
	 * @param assignee 用户编号
	 */
	void setAssignee(String assignee);

	/**
	 * 获取任务的负责人
	 * @return 负责人编号
	 */
	String getOwner();

	/**
	 * 设置任务的负责人
	 * @param owner 用户编号
	 */
	void setOwner(String owner);
	
	/**
	 * 添加一个用户做为候选
	 * @param userId 用户编号
	 */
	void addCandidateUser(String userId,IncludeExclusion includeExclusion);


	/**
	 * 添加一个候选组
	 * @param groupTo
	 * @param includeExclusion
	 */
	void addCandidateGroup(GroupTo groupTo,IncludeExclusion includeExclusion);

	
	List<IdentityLinkEntity> getTaskIdentityLinkEntitys();



	Date getDueDate();


	void setDueDate(Date dueDate);

	
	Date getCreateTime();

	String getProcessInstanceId();

	String getProcessDefinitionId();

	TaskDefinition getTaskDefinition();

	List<IdentityLink> getTaskIdentityLinks();

}
