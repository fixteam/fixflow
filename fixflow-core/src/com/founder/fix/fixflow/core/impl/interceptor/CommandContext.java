package com.founder.fix.fixflow.core.impl.interceptor;

import java.sql.Connection;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.cache.CacheObject;
import com.founder.fix.fixflow.core.impl.db.DbSqlSession;
import com.founder.fix.fixflow.core.impl.persistence.CommentManager;
import com.founder.fix.fixflow.core.impl.persistence.DeploymentManager;
import com.founder.fix.fixflow.core.impl.persistence.EventSubscriptionManager;
import com.founder.fix.fixflow.core.impl.persistence.IdentityLinkManager;
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
	// getters and setters
	// //////////////////////////////////////////////////////

	public DbSqlSession getDbSqlSession() {
		return new DbSqlSession(Context.getDbConnection(), Context.getCacheObject());
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
