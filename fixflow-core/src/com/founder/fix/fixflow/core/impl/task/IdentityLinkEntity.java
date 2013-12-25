/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl.task;

import java.util.Date;

import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.task.IncludeExclusion;
import com.founder.fix.fixflow.core.task.IdentityLink;
import com.founder.fix.fixflow.core.task.IdentityLinkType;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class IdentityLinkEntity extends AbstractPersistentObject<IdentityLinkEntity> implements IdentityLink {

	private static final long serialVersionUID = 1L;
	public static final String RULE_GET_IDENTITYLINK_PERSISTENT_STATE = "getIdentityLinkPersistentState";
	public static final String RULE_GET_IDENTITYLINK_PERSISTENT_DBMAP = "getIdentityLinkPersistentDbMap";
	public static final String RULE_GET_IDENTITYLINK_CLONE = "identityLinkClone";

	//持久化字段
	protected String id;

	protected IdentityLinkType type;

	protected String userId;

	protected String groupId;

	protected String groupType;
	
	protected String taskId;
	
	protected IncludeExclusion includeExclusion;
	
	protected Date archiveTime;
	
	
	//get和set方法
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setType(IdentityLinkType type) {
		this.type = type;
	}
	
	public void setTypeString(String type) {
		this.type = IdentityLinkType.valueOf(type);
	}

	public IdentityLinkType getType() {
		return this.type;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public IncludeExclusion getIncludeExclusion() {
		return includeExclusion;
	}

	public void setIncludeExclusion(IncludeExclusion includeExclusion) {
		this.includeExclusion = includeExclusion;
	}
	
	public void setIncludeExclusionString(String includeExclusion) {
		if(StringUtil.isNotEmpty(includeExclusion)){
			this.includeExclusion = IncludeExclusion.valueOf(includeExclusion);
		}
	}
	
	public Date getArchiveTime() {
		return archiveTime;
	}

	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

	//定义对象
	protected TaskInstance taskInstance;

	//对象set和get方法
	public TaskInstance getTaskInstance() {
		return taskInstance;
	}

	public void setTaskInstance(TaskInstance taskInstance) {
		this.taskInstance = taskInstance;
		this.taskId = taskInstance.getId();
	}
	
	//构造函数
	public IdentityLinkEntity() {

	}

	public IdentityLinkEntity(String id) {
		this.id = id;
	}

	public boolean isUser() {
		return userId != null;
	}

	public GroupTo getGroup() {
		return new GroupTo(groupId, groupType);
	}

	public void setGroup(GroupTo group) {
		this.groupId = group.getGroupId();
		this.groupType = group.getGroupType();
	}
	

	@Override
	public String getCloneRuleId() {
		return RULE_GET_IDENTITYLINK_CLONE;
	}
	
	@Override
	public String getPersistentDbMapRuleId() {
		return RULE_GET_IDENTITYLINK_PERSISTENT_DBMAP;
	}
	
	@Override
	public String getPersistentStateRuleId() {
		return RULE_GET_IDENTITYLINK_PERSISTENT_STATE;
	}
	
	//过时方法
	public void setGroupIdWithoutCascade(String groupId) {
		this.groupId = groupId;
	}

	public void setGroupTypeWithoutCascade(String groupType) {
		this.groupType = groupType;
	}

	public void setIdWithoutCascade(String id) {
		this.id = id;
	}

	public void setIncludeExclusionWithoutCascade(IncludeExclusion includeExclusion) {
		this.includeExclusion = includeExclusion;
	}

	public void setGroupWithoutCascade(GroupTo group) {
		this.groupId = group.getGroupId();
		this.groupType = group.getGroupType();
	}

	public void setTypeWithoutCascade(IdentityLinkType identityLinkType) {
		this.type = identityLinkType;
	}

	public void setUserIdWithoutCascade(String userId) {
		this.userId = userId;
	}

	public void setTaskIdWithoutCascade(String taskId) {
		this.taskId = taskId;
	}
}
