package com.founder.fix.fixflow.core.impl.persistence;

import java.util.List;


import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.model.DeploymentQueryImpl;
import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentEntity;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;
import com.founder.fix.fixflow.core.model.Deployment;



public class DeploymentManager extends AbstractManager {

	public void insertDeployment(DeploymentEntity deployment) {
		getDbSqlSession().insert("insertDeployment",deployment);

		for (ResourceEntity resource : deployment.getResources().values()) {
			resource.setDeploymentId(deployment.getId());
			
			commandContext.getResourceManager().insertResource(resource);
		}

		Context.getProcessEngineConfiguration().getDeploymentCache().deploy(deployment);
	}

	public void deleteDeployment(String deploymentId, boolean cascade) {
		if (cascade) {
			List<ProcessDefinitionBehavior> processDefinitions = getDbSqlSession().createProcessDefinitionQuery().deploymentId(deploymentId).list();

			for (ProcessDefinitionBehavior processDefinition : processDefinitions) {
				String processDefinitionId = processDefinition.getId();

				commandContext.getProcessInstanceManager().deleteProcessInstancesByProcessDefinition(processDefinitionId, "deleted deployment", cascade);

				Context.getProcessEngineConfiguration().getDeploymentCache().removeProcessDefinition(processDefinitionId);
			}
		}

		commandContext.getProcessDefinitionManager().deleteProcessDefinitionsByDeploymentId(deploymentId);

		commandContext.getResourceManager().deleteResourcesByDeploymentId(deploymentId);

		getDbSqlSession().delete("deleteDeployment", deploymentId);
	}

	public DeploymentEntity findLatestDeploymentByName(String deploymentName) {
		List<?> list = getDbSqlSession().selectList("selectDeploymentsByName", deploymentName, new Page(0, 1));
		if (list != null && !list.isEmpty()) {
			return (DeploymentEntity) list.get(0);
		}
		return null;
	}

	public DeploymentEntity findDeploymentById(String deploymentId) {
		return (DeploymentEntity) getDbSqlSession().selectOne("selectDeploymentById", deploymentId);
	}

	public long findDeploymentCountByQueryCriteria(DeploymentQueryImpl deploymentQuery) {
		return (Long) getDbSqlSession().selectOne("selectDeploymentCountByQueryCriteria", deploymentQuery);
	}

	@SuppressWarnings("unchecked")
	public List<Deployment> findDeploymentsByQueryCriteria(DeploymentQueryImpl deploymentQuery, Page page) {
		final String query = "selectDeploymentsByQueryCriteria";
		return getDbSqlSession().selectList(query, deploymentQuery, page);
	}

	@SuppressWarnings("unchecked")
	public List<String> getDeploymentResourceNames(String deploymentId) {
		return getDbSqlSession().selectList("selectResourceNamesByDeploymentId", deploymentId);
	}

}
