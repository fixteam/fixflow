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
package com.founder.fix.fixflow.core.impl.runtime;

import java.util.List;

import com.founder.fix.fixflow.core.impl.AbstractQuery;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;
import com.founder.fix.fixflow.core.runtime.IdentityLinkQuery;
import com.founder.fix.fixflow.core.runtime.QueryLocation;
import com.founder.fix.fixflow.core.task.IdentityLink;
import com.founder.fix.fixflow.core.task.IdentityLinkType;
import com.founder.fix.fixflow.core.task.IncludeExclusion;

public class IdentityLinkQueryImpl extends AbstractQuery<IdentityLinkQuery, IdentityLink> implements IdentityLinkQuery{

	
	protected String id;

	


	protected IdentityLinkType type;

	

	protected String userId;


	protected String groupId;


	protected String groupType;
	
	
	protected QueryLocation queryLocation = null;
	
	
	protected IncludeExclusion includeExclusion;
	

	protected String taskId;
	
	
	
	public IdentityLinkQueryImpl() {
	}

	public IdentityLinkQueryImpl(CommandContext commandContext) {
		super(commandContext);
	}

	public IdentityLinkQueryImpl(CommandExecutor commandExecutor) {
		super(commandExecutor);
	}
	
	
	
	public IdentityLinkQuery id(String id) {
		this.id=id;
		return this;
	}

	public IdentityLinkQuery taskId(String taskId) {
		this.taskId=taskId;
		return this;
	}

	public IdentityLinkQuery userId(String userId) {
		this.userId=userId;
		return this;
	}

	public IdentityLinkQuery groupId(String groupId) {
		this.groupId=groupId;
		return this;
	}

	public IdentityLinkQuery groupType(String groupType) {
		this.groupType=groupType;
		return this;
	}
	
	//containAndExclude
	
	public IdentityLinkQuery includeExclusion(IncludeExclusion includeExclusion) {
		this.includeExclusion=includeExclusion;
		return this;
	}

	public IdentityLinkQuery type(IdentityLinkType type) {
		this.type=type;
		return this;
	}
	
	public IdentityLinkQuery his() {
		if(this.queryLocation != null){
			this.queryLocation = QueryLocation.RUN_HIS;
		}else{
			this.queryLocation = QueryLocation.HIS;
		}
		return this;
	}
	
	public IdentityLinkQuery run() {
		if(this.queryLocation != null){
			this.queryLocation = QueryLocation.RUN_HIS;
		}else{
			this.queryLocation = QueryLocation.RUN;
		}
		return this;
	}

	public long executeCount(CommandContext commandContext) {
		checkQueryOk();
		// ensureVariablesInitialized();
		return commandContext.getIdentityLinkManager().findIdentityLinkCountByQueryCriteria(this);
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<IdentityLink> executeList(CommandContext commandContext, Page page) {
		checkQueryOk();
		// ensureVariablesInitialized();
		return (List)commandContext.getIdentityLinkManager().findIdentityLinkByQueryCriteria(this, page);
	}
	
	public String getId() {
		return id;
	}
	
	public IdentityLinkType getType() {
		return type;
	}

	
	public String getUserId() {
		return userId;
	}
	
	public String getGroupId() {
		return groupId;
	}

	
	public String getGroupType() {
		return groupType;
	}

	
	public IncludeExclusion getIncludeExclusion() {
		 
		return includeExclusion;
	}
	
	public String getTaskId() {
		return taskId;
	}

	public QueryLocation getQueryLocation() {
		return queryLocation;
	}

}
