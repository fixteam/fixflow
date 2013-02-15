package com.founder.fix.fixflow.core.impl.persistence.definition;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.model.Deployment;

/**
 * @author kenshin
 */
public class DeploymentEntity implements Serializable, Deployment , PersistentObject {

	private static final long serialVersionUID = 1L;

	protected String id;
	protected String name;
	protected Map<String, ResourceEntity> resources;
	protected Date deploymentTime;
	protected boolean validatingSchema = true;
	protected boolean isNew;
	
	public DeploymentEntity()
	{
		this.id=GuidUtil.CreateGuid();
	}

	public ResourceEntity getResource(String resourceName) {
		return getResources().get(resourceName);
	}

	public void addResource(ResourceEntity resource) {
		if (resources == null) {
			resources = new HashMap<String, ResourceEntity>();
		}
		resources.put(resource.getName(), resource);
	}

	@SuppressWarnings("null")
	public Map<String, ResourceEntity> getResources() {
		if (resources == null && id != null) {
			List<ResourceEntity> resourcesList = null;/*
													 * Context
													 * .getCommandContext()
													 * .getResourceManager()
													 * .findResourcesByDeploymentId
													 * (id);
													 */
			resources = new HashMap<String, ResourceEntity>();
			for (ResourceEntity resource : resourcesList) {
				resources.put(resource.getName(), resource);
			}
		}
		return resources;
	}

	public Map<String, Object> getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("id", this.id);
		persistentState.put("name", this.name);
		persistentState.put("deploymentTime", this.deploymentTime);
		return persistentState;
	}

	// getters and setters
	// //////////////////////////////////////////////////////

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setResources(Map<String, ResourceEntity> resources) {
		this.resources = resources;
	}

	public Date getDeploymentTime() {
		return deploymentTime;
	}

	public void setDeploymentTime(Date deploymentTime) {
		this.deploymentTime = deploymentTime;
	}

	public boolean isValidatingSchema() {
		return validatingSchema;
	}

	public void setValidatingSchema(boolean validatingSchema) {
		this.validatingSchema = validatingSchema;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
}
