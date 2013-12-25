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
package com.founder.fix.fixflow.core.impl.persistence;

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.cache.CacheHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.command.QueryVariablesCommand;
import com.founder.fix.fixflow.core.impl.persistence.AbstractManager;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.task.TaskQueryImpl;
import com.founder.fix.fixflow.core.task.IdentityLink;

/**
 * 任务数据管理器
 * @author kenshin
 */
public class TaskManager extends AbstractManager {

	/**
	 * 根据任务编号查询任务实例
	 * @param id
	 * @return
	 */
	public TaskInstanceEntity findTaskById(String id) {
		if (id == null) {
			throw new FixFlowException("任务编号不能为空!");
		}
		return (TaskInstanceEntity)getMappingSqlSession().selectOne("selectTaskByTaskId", id);
	}

	/**
	 * 查询任务
	 * @param taskQuery
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaskInstanceEntity> findTasksByQueryCriteria(TaskQueryImpl taskQuery, Page page) {
		String query = "selectTaskByQueryCriteria";
		return getMappingSqlSession().selectList(query, taskQuery, page);
	}
	
	/**
	 * 查询任务数量
	 * @param taskQuery
	 * @return
	 */
	public long findTaskCountByQueryCriteria(TaskQueryImpl taskQuery) {
		return (Long) getMappingSqlSession().selectOne("selectTaskCountByQueryCriteria", taskQuery);
	}
	
	
	/**
	 * 查询我的代理人
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findAgentUsers(String userId){
		String query = "findAgentUsers";
		return getMappingSqlSession().selectList(query,userId);
	}
	
	/**
	 * 查询代理给我的人
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findAgentToUsers(String userId){
		String query = "findAgentToUsers";
		return getMappingSqlSession().selectList(query,userId);
	}


	/**
	 * 根据流程实例编号集合查询状态
	 * @param processInstanceIdList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTaskStatusByByProcessInstanceIdList(List<String> processInstanceIdList) {

		String query = "getTaskStatusByByProcessInstanceIdList";
		return getMappingSqlSession().selectList(query, processInstanceIdList);

	}
	
	/**
	 * 根据令牌集合查询任务
	 * @param tokenIdList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaskInstanceEntity> findTasksByTokenIdList(List<String> tokenIdList) {
		return getMappingSqlSession().selectList("selectTasksByTokenIdList", tokenIdList);
	}

	/**
	 * 保存任务
	 * @param taskInstance
	 */
	public void saveTaskInstanceEntity(TaskInstanceEntity taskInstance) {

		
		CacheHandler cacheHandler = Context.getProcessEngineConfiguration().getCacheHandler();
		cacheHandler.putCacheData("IdentityLink_" + taskInstance.getId(), null);
		TaskInstanceEntity taskInstanceEntity = findTaskById(taskInstance.getId());
		if(taskInstanceEntity == null){
			insert("insertTaskInstance",taskInstance);
			for (IdentityLink identityLink : taskInstance.getTaskIdentityLinks()) {
				getCommandContext().getIdentityLinkManager().insert(identityLink);
			}
			
			
		}else{
			update("updateTaskInstance",taskInstance);
			for (IdentityLink identityLink : taskInstance.getTaskIdentityLinks()) {
				getCommandContext().getIdentityLinkManager().saveIdentityLink(identityLink);
			}
			
		}
	}
	

	/**
	 * 删除任务
	 * @param taskInstanceId
	 * @param cascade
	 */
	public void deleteTaskById(String taskInstanceId, boolean cascade) {
		if (cascade) {
			getCommandContext().getIdentityLinkManager().deleteIdentityLinksByTaskId(taskInstanceId);
			QueryVariablesCommand queryVariablesCommand=new QueryVariablesCommand();
			queryVariablesCommand.setTaskInstanceId(taskInstanceId);
			getCommandContext().getVariableManager().deleteVariable(queryVariablesCommand);
			
		}
		getMappingSqlSession().delete("deleteTaskById", taskInstanceId);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaskInstanceEntity> findTaskByProcessInstanceIdNotEnd(String id) {
		return (List<TaskInstanceEntity>)getDbSqlSession().selectList("findTaskByProcessInstanceIdNotEnd", id);
	}
	
	/**新增方法****/
	
	public void deleteTaskByProcessInstanceId(String processInstanceId){
		delete("deleteTaskByProcessInstanceId", processInstanceId);
	}
}
