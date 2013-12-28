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
package com.founder.fix.fixflow.core.impl.interceptor;

import java.sql.Connection;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.cache.CacheObject;
import com.founder.fix.fixflow.core.impl.db.DbSqlSession;
import com.founder.fix.fixflow.core.impl.db.MappingSqlSession;
import com.founder.fix.fixflow.core.impl.persistence.CommentManager;
import com.founder.fix.fixflow.core.impl.persistence.DeploymentManager;
import com.founder.fix.fixflow.core.impl.persistence.EventSubscriptionManager;
import com.founder.fix.fixflow.core.impl.persistence.HistoryManager;
import com.founder.fix.fixflow.core.impl.persistence.IdentityLinkManager;
import com.founder.fix.fixflow.core.impl.persistence.JobManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.persistence.ResourceManager;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.core.impl.persistence.TokenManager;
import com.founder.fix.fixflow.core.impl.persistence.VariableManager;

/**
 * @author kenshin
 */
public class CommandContext {

	protected Command<?> command;

	protected ProcessEngineConfigurationImpl processEngineConfigurationImpl;

	public CommandContext(Command<?> command, ProcessEngineConfigurationImpl processEngineConfigurationImpl) {
		this.command = command;
		this.processEngineConfigurationImpl = processEngineConfigurationImpl;

	}

	public ProcessEngineConfigurationImpl getProcessEngineConfigurationImpl() {
		return processEngineConfigurationImpl;
	}

	public DeploymentManager getDeploymentManager() {

		DeploymentManager deploymentManager = new DeploymentManager();
		deploymentManager.setCommandContext(this);

		return deploymentManager;
	}

	public ResourceManager getResourceManager() {
		ResourceManager resourceManager = new ResourceManager();
		resourceManager.setCommandContext(this);

		return resourceManager;
	}

	public ProcessDefinitionManager getProcessDefinitionManager() {
		ProcessDefinitionManager processDefinitionManager = new ProcessDefinitionManager();
		processDefinitionManager.setCommandContext(this);

		return processDefinitionManager;
	}

	public ProcessInstanceManager getProcessInstanceManager() {
		ProcessInstanceManager processInstanceManager = new ProcessInstanceManager();
		processInstanceManager.setCommandContext(this);

		return processInstanceManager;
	}

	public TaskManager getTaskManager() {
		TaskManager taskManager = new TaskManager();
		taskManager.setCommandContext(this);

		return taskManager;
	}

	public IdentityLinkManager getIdentityLinkManager() {
		IdentityLinkManager identityLinkManager = new IdentityLinkManager();
		identityLinkManager.setCommandContext(this);

		return identityLinkManager;
	}


	
	public VariableManager getVariableManager() {
		VariableManager variableManager = new VariableManager();
		variableManager.setCommandContext(this);

		return variableManager;
	}

	public CommentManager getCommentManager() {
		CommentManager commentManager = new CommentManager();
		commentManager.setCommandContext(this);

		return commentManager;
	}

	public TokenManager getTokenManager() {
		TokenManager tokenManager = new TokenManager();
		tokenManager.setCommandContext(this);
		return tokenManager;
	}

	public EventSubscriptionManager getEventSubscriptionManager() {
		EventSubscriptionManager eventSubscriptionManager = new EventSubscriptionManager();
		eventSubscriptionManager.setCommandContext(this);
		return eventSubscriptionManager;
	}
	
	public HistoryManager getHistoryManager(){
		HistoryManager historyManager = new HistoryManager();
		historyManager.setCommandContext(this);
		return historyManager;
	}
	public JobManager getJobManager() {
		JobManager jobManager = new JobManager();
		jobManager.setCommandContext(this);
		return jobManager;
	}
	
	
	// getters and setters
	// //////////////////////////////////////////////////////

	public DbSqlSession getDbSqlSession() {
		return new DbSqlSession(Context.getDbConnection(), Context.getCacheObject());
	}
	
	public MappingSqlSession getMappingSqlSession() {
		return new MappingSqlSession(Context.getDbConnection(), Context.getCacheObject());
	}
	
	

	public CacheObject getCacheObject() {
		return Context.getCacheObject();
	}

	public Command<?> getCommand() {
		return command;
	}

	public Connection getConnection() {
		return Context.getDbConnection();
	}

}
