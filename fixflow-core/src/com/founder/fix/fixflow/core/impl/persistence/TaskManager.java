package com.founder.fix.fixflow.core.impl.persistence;

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.cache.CacheHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.persistence.AbstractManager;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.task.TaskQueryImpl;
import com.founder.fix.fixflow.core.impl.variable.VariableFlowTypeEntity;
import com.founder.fix.fixflow.core.impl.variable.VariableQueryEntity;
import com.founder.fix.fixflow.core.variable.VariableFlowType;

/**
 * @author kenshin
 */
public class TaskManager extends AbstractManager {

	public TaskInstanceEntity findTaskById(String id) {
		if (id == null) {
			throw new FixFlowException("任务编号不能为空!");
		}

		return (TaskInstanceEntity) getDbSqlSession().selectOne("selectTaskInstance", id);
	}

	@SuppressWarnings("unchecked")
	public List<TaskInstanceEntity> findTasksByQueryCriteria(TaskQueryImpl taskQuery, Page page) {

		String query = "selectTaskByQueryCriteria";
		return getDbSqlSession().selectList(query, taskQuery, page);

	}
	
	
	//GetAgentUsersCmd
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findAgentUsers(String userId){
		String query = "findAgentUsers";
		return getDbSqlSession().selectList(query,userId);
	}

	// getTaskStatusByByProcessInstanceIdList

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTaskStatusByByProcessInstanceIdList(List<String> processInstanceIdList) {

		String query = "getTaskStatusByByProcessInstanceIdList";
		return getDbSqlSession().selectList(query, processInstanceIdList);

	}

	public long findTaskCountByQueryCriteria(TaskQueryImpl taskQuery) {
		return (Long) getDbSqlSession().selectOne("selectTaskCountByQueryCriteria", taskQuery);
	}

	public List<TaskInstanceEntity> findTasksByParentTaskId(String parentTaskId) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<TaskInstanceEntity> findTasksByTokenIdList(List<String> tokenIdList) {
		return getDbSqlSession().selectList("selectTasksByTokenIdList", tokenIdList);
	}

	public void saveTaskInstanceEntity(TaskInstanceEntity taskInstance) {
		// 先清空掉缓存再存储
		CacheHandler cacheHandler = Context.getProcessEngineConfiguration().getCacheHandler();
		cacheHandler.putCacheData("IdentityLink_" + taskInstance.getId(), null);
		getDbSqlSession().save("saveTaskInstance", taskInstance);
	}

	public void deleteTaskInstanceByTaskInstanceId(String taskInstanceId, boolean cascade) {

		if (cascade) {

			getCommandContext().getIdentityLinkManager().deleteIdentityLinksByTaskId(taskInstanceId);

			VariableQueryEntity variableQueryEntity = new VariableQueryEntity();
			VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.TASKINSTANCE, taskInstanceId);

			variableQueryEntity.addVariableFlowType(variableFlowTypeEntity);

			getCommandContext().getVariableManager().deleteVariable(variableQueryEntity);
			getDbSqlSession().delete("deleteTaskInstanceByTaskInstanceId", taskInstanceId);
			

		} else {
			getDbSqlSession().delete("deleteTaskInstanceByTaskInstanceId", taskInstanceId);
		}

	}

}
