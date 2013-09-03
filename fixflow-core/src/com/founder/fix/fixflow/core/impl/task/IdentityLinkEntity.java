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
import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.objkey.TaskIdentityLinkObjKey;
import com.founder.fix.fixflow.core.task.IncludeExclusion;
import com.founder.fix.fixflow.core.task.IdentityLink;
import com.founder.fix.fixflow.core.task.IdentityLinkType;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class IdentityLinkEntity extends AbstractPersistentObject implements IdentityLink {

	private static final long serialVersionUID = 1L;

	protected String id;

	protected IdentityLinkType type;

	protected String userId;

	protected String groupId;

	protected String groupType;
	
	protected Date archiveTime;

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
	
	public Date getArchiveTime() {
		return archiveTime;
	}

	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

	protected IncludeExclusion includeExclusion;

	protected String taskId;

	protected TaskInstance taskInstance;

	public IdentityLinkEntity() {

	}

	public IdentityLinkEntity(String id) {
		this.id = id;
	}

	public boolean isUser() {
		return userId != null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TaskInstance getTaskInstance() {
		return taskInstance;
	}

	public void setTaskInstance(TaskInstance taskInstance) {
		this.taskInstance = taskInstance;
		this.taskId = taskInstance.getId();
	}

	public void setType(IdentityLinkType type) {
		this.type = type;
	}

	public IdentityLinkType getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	public void setUserId(String userId) {

		this.userId = userId;

	}

	public String getUserId() {
		return userId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public GroupTo getGroup() {

		return new GroupTo(groupId, groupType);
	}

	public void setGroup(GroupTo group) {

		this.groupId = group.getGroupId();
		this.groupType = group.getGroupType();
	}

	public IncludeExclusion getIncludeExclusion() {
		return includeExclusion;
	}

	public void setIncludeExclusion(IncludeExclusion includeExclusion) {
		this.includeExclusion = includeExclusion;
	}

	// 持久化使用

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

	public Map<String, Object> getPersistentState() {

		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();

		// 身份链接编号 String
		objectParam.put(TaskIdentityLinkObjKey.Id().FullKey(), this.getId());
		// 身份链接类型 String
		objectParam.put(TaskIdentityLinkObjKey.Type().FullKey(), this.getType().toString());
		// 用户编号 String
		objectParam.put(TaskIdentityLinkObjKey.UserId().FullKey(), this.getUserId());
		// 组编号 String
		objectParam.put(TaskIdentityLinkObjKey.GroupId().FullKey(), this.getGroupId());
		// 组类型 String
		objectParam.put(TaskIdentityLinkObjKey.GroupType().FullKey(), this.getGroupType());
		// 包含排除 String
		objectParam.put(TaskIdentityLinkObjKey.IncludeExclusion().FullKey(), this.getIncludeExclusion().toString());
		// 任务实例编号 String
		objectParam.put(TaskIdentityLinkObjKey.TaskInstanceId().FullKey(), this.getTaskId());
		// 归档时间
		objectParam.put(TaskIdentityLinkObjKey.ArchiveTime().FullKey(), this.getArchiveTime());
		return objectParam;

	}

	public IdentityLinkEntity(Map<String, Object> entityMap){
		persistentInit(entityMap);
	}
	public void persistentInit(Map<String, Object> entityMap) {
		for (String dataKey : entityMap.keySet()) {

			if (dataKey.equals(TaskIdentityLinkObjKey.Id().DataBaseKey())) {
				this.id = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TaskIdentityLinkObjKey.Type().DataBaseKey())) {
				this.type = IdentityLinkType.valueOf(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskIdentityLinkObjKey.UserId().DataBaseKey())) {
				this.userId = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TaskIdentityLinkObjKey.GroupId().DataBaseKey())) {
				this.groupId = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TaskIdentityLinkObjKey.GroupType().DataBaseKey())) {
				this.groupType = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TaskIdentityLinkObjKey.IncludeExclusion().DataBaseKey())) {
				this.includeExclusion = IncludeExclusion.valueOf(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskIdentityLinkObjKey.TaskInstanceId().DataBaseKey())) {
				this.taskId = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}
			
			if (dataKey.equals(TaskIdentityLinkObjKey.ArchiveTime().DataBaseKey())) {
				this.archiveTime = StringUtil.getDate(entityMap.get(dataKey));
				continue;
			}

		}
	}

	public Map<String, Object> getPersistentDbMap() {
		// 构建查询参数
				Map<String, Object> objectParam = new HashMap<String, Object>();

				// 身份链接编号 String
				objectParam.put(TaskIdentityLinkObjKey.Id().DataBaseKey(), this.getId());
				// 身份链接类型 String
				objectParam.put(TaskIdentityLinkObjKey.Type().DataBaseKey(), this.getType().toString());
				// 用户编号 String
				objectParam.put(TaskIdentityLinkObjKey.UserId().DataBaseKey(), this.getUserId());
				// 组编号 String
				objectParam.put(TaskIdentityLinkObjKey.GroupId().DataBaseKey(), this.getGroupId());
				// 组类型 String
				objectParam.put(TaskIdentityLinkObjKey.GroupType().DataBaseKey(), this.getGroupType());

				// 包含排除 String
				objectParam.put(TaskIdentityLinkObjKey.IncludeExclusion().DataBaseKey(), this.getIncludeExclusion().toString());
				// 任务实例编号 String
				objectParam.put(TaskIdentityLinkObjKey.TaskInstanceId().DataBaseKey(), this.getTaskId());
				// 任务实例归档时间
				objectParam.put(TaskIdentityLinkObjKey.ArchiveTime().DataBaseKey(), this.getArchiveTime());
				return objectParam;
	}
}
