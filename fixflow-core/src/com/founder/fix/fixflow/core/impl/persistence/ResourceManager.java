package com.founder.fix.fixflow.core.impl.persistence;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;



public class ResourceManager extends AbstractManager {

	public void insertResource(ResourceEntity resource) {
		getDbSqlSession().insert("insertResource",resource);
	}

	public void deleteResourcesByDeploymentId(String deploymentId) {
		getDbSqlSession().delete("deleteResourcesByDeploymentId", deploymentId);
	}

	public ResourceEntity findResourceByDeploymentIdAndResourceName(String deploymentId, String resourceName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deploymentId", deploymentId);
		params.put("resourceName", resourceName);
		return (ResourceEntity) getDbSqlSession().selectOne("selectResourceByDeploymentIdAndResourceName", params);
	}
	
	public ResourceEntity findResourceByResourceId(String resourceId)
	{
		return (ResourceEntity)getDbSqlSession().selectOne("selectResourceByResourceId", resourceId);
	}
	
	public void updateResource(ResourceEntity resourceEntity)
	{
		getDbSqlSession().update("updateResource", resourceEntity);
	}

	@SuppressWarnings("unchecked")
	public List<ResourceEntity> findResourcesByDeploymentId(String deploymentId) {
		return getDbSqlSession().selectList("selectResourcesByDeploymentId", deploymentId);
	}

}
