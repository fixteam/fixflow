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
package com.founder.fix.fixflow.core.impl.persistence.instance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.task.TaskQueryImpl;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.task.TaskInstanceType;

public class TaskInstancePersistence {

	protected Connection connection;
	protected SqlCommand sqlCommand;

	public TaskInstancePersistence(Connection connection) {
		this.connection = connection;
		sqlCommand = new SqlCommand(connection);
	}

	public TaskInstanceEntity findTaskById(String id) {

		String sqlText = "SELECT * FROM FIXFLOW_RUN_TAKSINSTANECE WHERE TASKINSTANCE_ID=?";

		// 构建查询参数

		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(id);

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);
		TaskInstanceEntity taskInstanceImpl=null;
		if(dataObj.size()>0){
			Map<String, Object> dataMap = dataObj.get(0);
			taskInstanceImpl = getTaskInstanceEntity(dataMap);
		}
		

		

		return taskInstanceImpl;

	}

	public TaskInstanceEntity getTaskInstanceEntity(Map<String, Object> dataMap) {
		TaskInstanceEntity taskInstanceEntity = new TaskInstanceEntity(dataMap);
		return taskInstanceEntity;
	}

	
	
	
	protected String selectTaskByQueryCriteriaSql(String selectTaskByQueryCriteriaSql, TaskQueryImpl taskQuery, Page page, List<Object> objectParamWhere) {

		selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " FROM FIXFLOW_RUN_TAKSINSTANECE T ";

		// inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = T.ID_
		if (taskQuery.getCandidateUser() != null) {
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " left join FIXFLOW_RUN_TASKIDENTITYLINK I on I.TASKINSTANCE_ID = T.TASKINSTANCE_ID ";
		}
		
		
		
		
		//initiator
		if(taskQuery.isAssigneeNotNull()){
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and T.ASSIGNEE IS NOT NULL ";
		}
		
		//分类
		if(taskQuery.getCategory()!=null){
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and T.CATEGORY =? ";
			objectParamWhere.add(taskQuery.getCategory());
		}
		
		if(taskQuery.isCandidateNotNull()){
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and T.ASSIGNEE IS NULL and I.TYPE = 'candidate' ";
		}
		

		// if (taskQuery.getProcessDefinitionKey() != null ||
		// taskQuery.getProcessDefinitionName() != null) {
		selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + "  LEFT JOIN  FIXFLOW_RUN_PROCESSINSTANECE P on T.PROCESSINSTANCE_ID = P.PROCESSINSTANCE_ID ";
		// }
		
		
		//自定义扩展查询
		if(taskQuery.getQueryExpandTo()!=null&&taskQuery.getQueryExpandTo().getLeftJoinSql()!=null&&!taskQuery.getQueryExpandTo().getLeftJoinSql().equals("")){
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + taskQuery.getQueryExpandTo().getLeftJoinSql(); 
		}
		
		selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " WHERE 1=1 ";

		if (taskQuery.getTaskTypeList().size() > 0) {
			List<TaskInstanceType> taskInstanceTypes = taskQuery.getTaskTypeList();

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and (";

			for (int i = 0; i < taskInstanceTypes.size(); i++) {
				if (i == taskInstanceTypes.size() - 1) {
					TaskInstanceType taskInstanceType = taskInstanceTypes.get(i);
					selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " T.TASKTYPE=? ) ";
					objectParamWhere.add(taskInstanceType.toString());
				} else {
					TaskInstanceType taskInstanceType = taskInstanceTypes.get(i);
					selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " T.TASKTYPE=? OR ";
					objectParamWhere.add(taskInstanceType.toString());
				}

			}

		}
		
		if(taskQuery.getQueryExpandTo()!=null&&taskQuery.getQueryExpandTo().getWhereSql()!=null&&!taskQuery.getQueryExpandTo().getWhereSql().equals("")){
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql +" and "+taskQuery.getQueryExpandTo().getWhereSql();
			if(taskQuery.getQueryExpandTo().getWhereSqlObj()!=null&&taskQuery.getQueryExpandTo().getWhereSqlObj().size()>0){
				objectParamWhere.add(taskQuery.getQueryExpandTo().getWhereSqlObj());
			}
			
		}

		if (taskQuery.getCreateTime() != null) {

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  T.CREATE_TIME=? ";
			objectParamWhere.add(taskQuery.getCreateTime());
		}

		if(taskQuery.getInitiator()!=null){
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  P.INITIATOR=? ";
			objectParamWhere.add(taskQuery.getInitiator());
		}
		
		if(taskQuery.getInitiatorLike()!=null){
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  P.INITIATOR  LIKE '%"+taskQuery.getInitiatorLike()+"%' ";
			
		}
		
		if (taskQuery.getTokenId() != null) {

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  T.TOKEN_ID=? ";
			objectParamWhere.add(taskQuery.getTokenId());
		}

		
		if (taskQuery.getNodeId() != null) {

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  T.NODE_ID=? ";
			objectParamWhere.add(taskQuery.getNodeId());
		}
		
		if (taskQuery.getCreateTimeBefore() != null) {

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  T.CREATE_TIME>=? ";
			objectParamWhere.add(taskQuery.getCreateTimeBefore());
		}

		if (taskQuery.getCreateTimeAfter() != null) {

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  T.CREATE_TIME<=? ";
			objectParamWhere.add(taskQuery.getCreateTimeAfter());
		}

		if (taskQuery.getName() != null) {

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  T.NAME=? ";
			objectParamWhere.add(taskQuery.getName());
		}

		if (taskQuery.getDescription() != null) {

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  T.DESCRIPTION=? ";
			objectParamWhere.add(taskQuery.getDescription());
		}

		if (taskQuery.getDescriptionLike() != null) {
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  T.DESCRIPTION LIKE '%" + taskQuery.getDescriptionLike() + "%'  ";
			// objectParamWhere.add(taskQuery.getDescriptionLike());
		}

		if (taskQuery.getNameLike() != null) {

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  T.NAME LIKE '%" + taskQuery.getNameLike() + "%' ";
			// objectParamWhere.add(taskQuery.getNameLike());
		}

		if (taskQuery.getProcessDefinitionKey() != null) {

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  T.PROCESSDEFINITION_KEY=? ";
			objectParamWhere.add(taskQuery.getProcessDefinitionKey());
		}
		
		if(taskQuery.getProcessDefinitionKeyList().size()>0){
			
			
			List<String> processDefinitionKeyList = taskQuery.getProcessDefinitionKeyList();

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and T.PROCESSDEFINITION_KEY IN (";

			for (int i = 0; i < processDefinitionKeyList.size(); i++) {
				if (i == processDefinitionKeyList.size() - 1) {
					String processDefinitionKey = processDefinitionKeyList.get(i);
					selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " ? ) ";
					objectParamWhere.add(processDefinitionKey.toString());
				} else {
					String processDefinitionKey = processDefinitionKeyList.get(i);
					selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " ?, ";
					objectParamWhere.add(processDefinitionKey.toString());
				}

			}
		
		}

		if (taskQuery.getProcessDefinitionName() != null) {

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and T.PROCESSDEFINITION_NAME=? ";
			objectParamWhere.add(taskQuery.getProcessDefinitionName());
		}

		
		if (taskQuery.getIsSuspended() != null) {

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  T.ISSUSPENDED=? ";
			objectParamWhere.add(taskQuery.getIsSuspended());
		}
		if (taskQuery.getTaskId() != null) {
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and T.TASKINSTANCE_ID=? ";
			objectParamWhere.add(taskQuery.getTaskId());
		}
		
		if (taskQuery.getCallActivityInstanceId() != null) {

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and  T.CALLACTIVITY_INSTANCE_ID=? ";
			objectParamWhere.add(taskQuery.getCallActivityInstanceId());
		}

		if (taskQuery.getBusinessKey() != null) {
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and T.BIZKEY=? ";
			objectParamWhere.add(taskQuery.getBusinessKey());
		}

		if (taskQuery.getProcessInstanceId() != null) {
			if(taskQuery.isContainsSubProcess()){
				
				
				//这个地方需要用到递归去寻找所有的子流程
				
				List<Object> dataList=new ArrayList<Object>();
				
				dataList.add(taskQuery.getProcessInstanceId());
				StringBuffer  processInstanceIdList=new StringBuffer();
				List<Map<String, Object>> dataListMaps=sqlCommand.queryForList("SELECT * FROM FIXFLOW_RUN_PROCESSINSTANECE WHERE PARENT_INSTANCE_ID=?", dataList);
				processInstanceIdList.append("'"+taskQuery.getProcessInstanceId()+"'");
				if(dataListMaps.size()>0){
					
					
					
					getSubProcessId(taskQuery.getProcessInstanceId(),processInstanceIdList);
				}
				
				selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and T.PROCESSINSTANCE_ID in ("+processInstanceIdList.toString()+") ";
				
			}
			else{
				
					selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and T.PROCESSINSTANCE_ID=? ";
					objectParamWhere.add(taskQuery.getProcessInstanceId());
				
			}
		}

		if (taskQuery.getEnd() != null) {
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and T.END_TIME " + taskQuery.getEnd() + " ";

		}

		if (taskQuery.getCandidateUser() != null || taskQuery.getCandidateGroup() != null) {
			//String selectTaskByQueryCriteriaExclusionSql = " and T.taskinstance_id not in (select z.taskinstance_id from FIXFLOW_RUN_TASKIDENTITYLINK Z where Z.TYPE = 'candidate' and (";
			List<GroupTo> candidateGroups = taskQuery.getCandidateGroups();

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and ((T.ASSIGNEE is null " + " and I.TYPE = 'candidate' " + " and ( ";

			if (taskQuery.getCandidateUser() != null) {
				selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " (I.USER_ID=? or I.USER_ID='fixflow_allusers')";
				//selectTaskByQueryCriteriaExclusionSql = selectTaskByQueryCriteriaExclusionSql + " ((I.USER_ID=? or I.USER_ID='fixflow_allusers') and include_exclusion ='EXCLUSION') ";
				objectParamWhere.add(taskQuery.getCandidateUser());
				//objectParamWhere.add(taskQuery.getCandidateUser());
			}

			if (taskQuery.getCandidateUser() != null && candidateGroups.size() > 0) {
				selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " or ";
				//selectTaskByQueryCriteriaExclusionSql = selectTaskByQueryCriteriaExclusionSql + " or ";
			}
			// group_id ='4' and group_type = '5'
			if (candidateGroups != null && candidateGroups.size() > 0) {
				selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " (";
				//selectTaskByQueryCriteriaExclusionSql = selectTaskByQueryCriteriaExclusionSql + " (";
				for (int i = 0; i < candidateGroups.size(); i++) {
					if (i == 0) {
						selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + "(group_id ='" + candidateGroups.get(i).getGroupId() + "' and group_type ='"
								+ candidateGroups.get(i).getGroupType() + "' )";
						//selectTaskByQueryCriteriaExclusionSql = selectTaskByQueryCriteriaExclusionSql + "(group_id ='" + candidateGroups.get(i).getGroupId() + "' and group_type ='"
								//+ candidateGroups.get(i).getGroupType() + "' and include_exclusion ='EXCLUSION')";
					} else {
						selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " or (group_id ='" + candidateGroups.get(i).getGroupId() + "' and group_type ='"
								+ candidateGroups.get(i).getGroupType() + "' )";
						//selectTaskByQueryCriteriaExclusionSql = selectTaskByQueryCriteriaExclusionSql + " or (group_id ='" + candidateGroups.get(i).getGroupId() + "' and group_type ='"
								//+ candidateGroups.get(i).getGroupType() + "' and include_exclusion ='EXCLUSION')";
					}

				}
				selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " )";
				
				
				//selectTaskByQueryCriteriaExclusionSql = selectTaskByQueryCriteriaExclusionSql + " )";
			}

			/*
			 * if (taskQuery.getCandidateUser() != null && candidateRoles !=
			 * null && candidateRoles.size() > 0) { selectTaskByQueryCriteriaSql
			 * = selectTaskByQueryCriteriaSql + " or "; }
			 * 
			 * if (candidateRoles != null && candidateRoles.size() > 0) {
			 * selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql +
			 * " I.ROLE_ID IN ("; for (int i = 0; i < candidateRoles.size();
			 * i++) { if (i == 0) { selectTaskByQueryCriteriaSql =
			 * selectTaskByQueryCriteriaSql + "'" + candidateRoles.get(i) + "'";
			 * } else { selectTaskByQueryCriteriaSql =
			 * selectTaskByQueryCriteriaSql + ",'" + candidateRoles.get(i) +
			 * "'"; }
			 * 
			 * } selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql +
			 * " ) "; }
			 */
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " )";
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " ) ";

			if (taskQuery.getAssignee() != null) {
				selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " or ( ";
				selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " T.ASSIGNEE=? ";
				objectParamWhere.add(taskQuery.getAssignee());
				selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " )";
			}
				
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " ) ";
			//selectTaskByQueryCriteriaExclusionSql = selectTaskByQueryCriteriaExclusionSql + " ) ";
			//selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql  + ")";//+ selectTaskByQueryCriteriaExclusionSql
		}else{
			if (taskQuery.getAssignee() != null) {
				selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " and T.ASSIGNEE=? ";
				objectParamWhere.add(taskQuery.getAssignee());
			}
			
			
		}

		return selectTaskByQueryCriteriaSql;

	}
	
	
	public List<GroupTo> getAgentCandidateGroups(String userId,String agentId){
		
		List<GroupTo> groupTos=new ArrayList<GroupTo>();
		
		//CacheHandler cache = Context.getProcessEngineConfiguration().getCacheHandler();
		List<Object> objectParamWhere = new ArrayList<Object>();
		//Object cacheData = cache.getCacheData("dept_findGroupMembersByUser_" + userId);
		String sql="";
		if(agentId!=null){
			sql=" SELECT z.EID FROM "+
					"( "+
					" select EID,WFNAME,NODEID,AUSER1 as USERID from FIXFLOW_OUTAGENT_AGENTDETAILS "+
					" WHERE AUSER1 not in (select a.EID from FIXFLOW_OUTAGENT_AGENTDETAILS a,FIXFLOW_OUTAGENT_AGENTINFO b "+
					" WHERE a.EID=b.EID AND STATUS='0' ) "+
					" union "+
					" select EID,WFNAME,NODEID,AUSER2  as USERID from FIXFLOW_OUTAGENT_AGENTDETAILS  "+
					" WHERE AUSER1 in (select a.EID from FIXFLOW_OUTAGENT_AGENTDETAILS a,FIXFLOW_OUTAGENT_AGENTINFO b "+
					" WHERE a.EID=b.EID AND STATUS='0' ) "+
					" ) z WHERE z.USERID=? AND z.EID=? ";
					
					objectParamWhere.add(userId);
					objectParamWhere.add(agentId);
		}
		else{
			 sql=" SELECT z.EID FROM "+
						"( "+
						" select EID,WFNAME,NODEID,AUSER1 as USERID from FIXFLOW_OUTAGENT_AGENTDETAILS "+
						" WHERE AUSER1 not in (select a.EID from FIXFLOW_OUTAGENT_AGENTDETAILS a,FIXFLOW_OUTAGENT_AGENTINFO b "+
						" WHERE a.EID=b.EID AND STATUS='0' ) "+
						" union "+
						" select EID,WFNAME,NODEID,AUSER2  as USERID from FIXFLOW_OUTAGENT_AGENTDETAILS  "+
						" WHERE AUSER1 in (select a.EID from FIXFLOW_OUTAGENT_AGENTDETAILS a,FIXFLOW_OUTAGENT_AGENTINFO b "+
						" WHERE a.EID=b.EID AND STATUS='0' ) "+
						" ) z WHERE z.USERID=? ";
						
						objectParamWhere.add(userId);
		}
		
		
		
		List<Map<String, Object>> listMaps=sqlCommand.queryForList(sql, objectParamWhere);
		
		
		for (Map<String, Object> map : listMaps) {
			
			groupTos.addAll(Authentication.findGroupsByUser(StringUtil.getString(map.get("EID"))));

		}
		
		return groupTos;
		
		
	}

	public List<TaskInstanceEntity> findTasksByQueryCriteria(TaskQueryImpl taskQuery, Page page) {
		
		String selectTaskByQueryCriteriaSql ="";
		List<Object> objectParamWhere = new ArrayList<Object>();
		
		String agentOldAssignee=null;
		if(taskQuery.getIsAgent()){
			
			
			if(taskQuery.getAssignee()!=null&&!taskQuery.getAssignee().equals("")){
				agentOldAssignee=taskQuery.getAssignee();
				taskQuery.taskAssignee(taskQuery.getAgentId());
			}
			
			if(taskQuery.getCandidateUser()!=null&&!taskQuery.getCandidateUser().equals("")){
				agentOldAssignee=taskQuery.getCandidateUser();
				taskQuery.taskCandidateUser(taskQuery.getAgentId());
			}
			
			
		}
		
		
		selectTaskByQueryCriteriaSql = "select distinct " + Context.getProcessEngineConfiguration().getDbConfig().getDbSqlMap().get("topOrderBy") + " T.* ";

		//selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + ",P.INITIATOR AS PI_INITIATOR ,P.START_AUTHOR AS PI_START_AUTHOR,P.START_TIME AS PI_START_TIME,P.SUBJECT AS PI_SUBJECT ";

		if(taskQuery.getQueryExpandTo()!=null&&taskQuery.getQueryExpandTo().getFieldSql()!=null&&!taskQuery.getQueryExpandTo().getFieldSql().equals("")){
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + ",P.INITIATOR AS PI_INITIATOR ,P.START_AUTHOR AS PI_START_AUTHOR,P.START_TIME AS PI_START_TIME,P.SUBJECT AS PI_SUBJECT ,"+taskQuery.getQueryExpandTo().getFieldSql();
		}else{
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + ",P.INITIATOR AS PI_INITIATOR ,P.START_AUTHOR AS PI_START_AUTHOR,P.START_TIME AS PI_START_TIME,P.SUBJECT AS PI_SUBJECT ";
		}
		
		
		selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql(selectTaskByQueryCriteriaSql, taskQuery, page, objectParamWhere);

		
		
		if(taskQuery.getIsAgent()){
			
			selectTaskByQueryCriteriaSql =selectTaskByQueryCriteriaSql+
					" and ((SELECT count(1) FROM "+
					"FIXFLOW_AGENT_AGENTDETAILS ag1,FIXFLOW_AGENT_AGENTINFO ag2 "+
					"where ag1.AGENT_ID=ag2.AGENT_ID AND ag2.SDATE<=sysdate AND ag2.EDATE>=sysdate AND ag2.STATUS='1' AND ag1.AUSER='"+agentOldAssignee+"' "+
					"AND ag1.AGENT_ID='"+taskQuery.getAgentId()+"' and ag1.PROCESS_ID='_fix_flow_all_flow')>0 or "+
					"(T.PROCESSDEFINITION_KEY in (SELECT ag.PROCESS_ID FROM (SELECT ag1.*,ag2.SDATE,ag2.EDATE FROM "+
					"FIXFLOW_AGENT_AGENTDETAILS ag1,FIXFLOW_AGENT_AGENTINFO ag2 "+
					"where ag1.AGENT_ID=ag2.AGENT_ID and ag2.STATUS='1' AND ag2.SDATE<=sysdate AND ag2.EDATE>=sysdate AND ag1.AUSER='"+agentOldAssignee+"' AND ag1.AGENT_ID='"+taskQuery.getAgentId()+"') ag)"+
					"))";
			
			
		}
		


		if (taskQuery.getOrderBy() != null) {

			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " order by " + taskQuery.getOrderBy().toString();
		}

		if (page != null) {
			Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();
			selectTaskByQueryCriteriaSql = pagination.getPaginationSql(selectTaskByQueryCriteriaSql, page.getFirstResult(), page.getMaxResults(), "*");
		}

		if (taskQuery.getOrderBy() != null && page != null) {
			//String sssString="T.END_TIME desc,T.CREATE_TIME desc";
			String orderBySql=taskQuery.getOrderBy();
			String orderBySqlFin="";
			if(orderBySql.indexOf(",")>=0){
				
				String[] orderBySqlTemp=orderBySql.split(",");
				for (String orderByObj : orderBySqlTemp) {
					if(orderBySqlFin.equals("")){
						orderBySqlFin=orderBySqlFin+orderByObj.substring(orderByObj.indexOf(".")+1,orderByObj.length());
					}
					else{
						orderBySqlFin=orderBySqlFin+","+orderByObj.substring(orderByObj.indexOf(".")+1,orderByObj.length());
					}
					
				}
				selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " order by " + orderBySqlFin;
				
			}else{
				selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + " order by " + taskQuery.getOrderBy().toString().substring(2);
			}
			
		}

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(selectTaskByQueryCriteriaSql, objectParamWhere);

		List<TaskInstanceEntity> taskInstanceImpls = new ArrayList<TaskInstanceEntity>();

		for (Map<String, Object> dataMap : dataObj) {

			TaskInstanceEntity taskInstanceImpl = getTaskInstanceEntity(dataMap);

			taskInstanceImpls.add(taskInstanceImpl);
		}

		return taskInstanceImpls;
		// select distinct T.* from
	}

	public long findTaskCountByQueryCriteria(TaskQueryImpl taskQuery) {
		// select count(distinct T.ID_)
		
		
		String selectTaskByQueryCriteriaSql = "";
		List<Object> objectParamWhere = new ArrayList<Object>();
		
		String agentOldAssignee=null;
		if(taskQuery.getIsAgent()){
			
			
			if(taskQuery.getAssignee()!=null&&!taskQuery.getAssignee().equals("")){
				agentOldAssignee=taskQuery.getAssignee();
				taskQuery.taskAssignee(taskQuery.getAgentId());
			}
			
			if(taskQuery.getCandidateUser()!=null&&!taskQuery.getCandidateUser().equals("")){
				agentOldAssignee=taskQuery.getCandidateUser();
				taskQuery.taskCandidateUser(taskQuery.getAgentId());
			}
			
			
		}
		
		
		selectTaskByQueryCriteriaSql = "select count(distinct T.TASKINSTANCE_ID) ";
		selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql(selectTaskByQueryCriteriaSql, taskQuery, null, objectParamWhere);
		
		
		if(taskQuery.getIsAgent()){
			
			selectTaskByQueryCriteriaSql =selectTaskByQueryCriteriaSql+
					" and ((SELECT count(1) FROM "+
					"FIXFLOW_AGENT_AGENTDETAILS ag1,FIXFLOW_AGENT_AGENTINFO ag2 "+
					"where ag1.AGENT_ID=ag2.AGENT_ID  AND ag2.SDATE<=sysdate AND ag2.EDATE>=sysdate AND ag1.AUSER='"+agentOldAssignee+"' "+
					"AND ag1.AGENT_ID='"+taskQuery.getAgentId()+"' and ag1.PROCESS_ID='_fix_flow_all_flow')>0 or "+
					"(T.PROCESSDEFINITION_KEY in (SELECT ag.PROCESS_ID FROM (SELECT ag1.*,ag2.SDATE,ag2.EDATE FROM "+
					"FIXFLOW_AGENT_AGENTDETAILS ag1,FIXFLOW_AGENT_AGENTINFO ag2 "+
					"where ag1.AGENT_ID=ag2.AGENT_ID and ag2.STATUS='1' AND ag2.SDATE<=sysdate AND ag2.EDATE>=sysdate AND ag1.AUSER='"+agentOldAssignee+"' AND ag1.AGENT_ID='"+taskQuery.getAgentId()+"') ag)"+
					"))";
			
			
		}
		
		
		/*
		if(taskQuery.getIsAgent()){
			
			selectTaskByQueryCriteriaSql = "SELECT count(distinct T.TASKINSTANCE_ID) FROM ( select distinct " + Context.getProcessEngineConfiguration().getDbConfig().getDbSqlMap().get("topOrderBy") + " T.* ";
			//selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql + ",P.INITIATOR AS PI_INITIATOR ,P.START_AUTHOR AS PI_START_AUTHOR,P.START_TIME AS PI_START_TIME,P.SUBJECT AS PI_SUBJECT ";
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSqlAgent(selectTaskByQueryCriteriaSql, taskQuery, null, objectParamWhere);

		}
		else{
			
			if(taskQuery.getAgentId()!=null){
				throw new FixFlowException("代理查询未开启, 但是缺设置了代理人.");
			}
		
			selectTaskByQueryCriteriaSql = "select count(distinct T.TASKINSTANCE_ID) ";
			selectTaskByQueryCriteriaSql = selectTaskByQueryCriteriaSql(selectTaskByQueryCriteriaSql, taskQuery, null, objectParamWhere);
		}*/
		
		
		Object returnObj = sqlCommand.queryForValue(selectTaskByQueryCriteriaSql, objectParamWhere);
		return Integer.parseInt(returnObj.toString());
	}

	public List<TaskInstanceEntity> findTasksByParentTaskId(String parentTaskId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveTaskInstance(TaskInstanceEntity taskInstanceEntity) {

		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(taskInstanceEntity.getId());

		// 设置查询字符串
		String sqlText = "SELECT COUNT(1) FROM FIXFLOW_RUN_TAKSINSTANECE WHERE TASKINSTANCE_ID=?";
		// 执行查询Sql语句,判断任务是否存在于数据库中.
		int rowNum = Integer.parseInt(sqlCommand.queryForValue(sqlText, objectParamWhere).toString());

		if (rowNum == 0) {
			insertTaskInstance(taskInstanceEntity);
		} else {
			updateTaskInstance(taskInstanceEntity);
		}

	}

	void insertTaskInstance(TaskInstanceEntity taskInstanceEntity) {


		Map<String, Object> objectParam =taskInstanceEntity.getPersistentDbMap();


		// 执行插入语句
		sqlCommand.insert("FIXFLOW_RUN_TAKSINSTANECE", objectParam);

	}
	
	

	void updateTaskInstance(TaskInstanceEntity taskInstanceEntity) {
		// 构建查询参数
		
		Map<String, Object> objectParam =taskInstanceEntity.getPersistentDbMap();

		// 构建Where查询参数
		Object[] objectParamWhere = { taskInstanceEntity.getId() };

		// 执行插入语句
		sqlCommand.update("FIXFLOW_RUN_TAKSINSTANECE", objectParam, " TASKINSTANCE_ID=?", objectParamWhere);

	}

	@SuppressWarnings("rawtypes")
	public List findTasksByTokenIdList(List<String> tokenIdList) {

		String sqlText = "SELECT * FROM FIXFLOW_RUN_TAKSINSTANECE WHERE END_TIME is not null and TASKTYPE='FIXFLOWTASK' and TOKEN_ID IN ( ";

		for (int i = 0; i < tokenIdList.size(); i++) {

			if (i == 0) {
				sqlText = sqlText + "'" + tokenIdList.get(i) + "'";
			} else {
				sqlText = sqlText + ",'" + tokenIdList.get(i) + "'";
			}

		}

		sqlText = sqlText + " ) ORDER BY END_TIME DESC";

		// 构建查询参数

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText);

		List<TaskInstanceEntity> taskInstanceImplList = new ArrayList<TaskInstanceEntity>();

		for (int i = 0; i < dataObj.size(); i++) {

			Map<String, Object> dataMap = dataObj.get(i);
			TaskInstanceEntity taskInstanceImpl = getTaskInstanceEntity(dataMap);
			taskInstanceImplList.add(taskInstanceImpl);

		}

		return taskInstanceImplList;

	}

	public void deleteTaskInstanceByProcessInstanceId(String processInstanceId) {

		Object[] objectParamWhere = { processInstanceId };
		// String
		// sqlString="DELETE FROM FIXFLOW_RUN_TOKEN WHERE PROCESSINSTANCE_ID=?";

		sqlCommand.delete("FIXFLOW_RUN_TAKSINSTANECE", " PROCESSINSTANCE_ID=?", objectParamWhere);

	}

	public void deleteTaskInstanceByTaskInstanceId(List<String> taskInstanceIds) {
		for (String taskInstanceId : taskInstanceIds) {
			deleteTaskInstanceByTaskInstanceId(taskInstanceId);
		}
	}

	public void deleteTaskInstanceByTaskInstanceId(String taskInstanceId) {

		Object[] objectParamWhere = { taskInstanceId };
		// String
		// sqlString="DELETE FROM FIXFLOW_RUN_TOKEN WHERE PROCESSINSTANCE_ID=?";

		sqlCommand.delete("FIXFLOW_RUN_TAKSINSTANECE", " TASKINSTANCE_ID=?", objectParamWhere);

	}

	public List<Map<String, Object>> getTaskStatusByByProcessInstanceIdList(List<String> processInstanceIdList) {

		String id = "";

		for (int i = 0; i < processInstanceIdList.size(); i++) {
			if (i == 0) {
				id = "'" + processInstanceIdList.get(i) + "'";
			} else {
				id = id + ",'" + processInstanceIdList.get(i) + "'";
			}
		}

		String sqlText = "select x.*,y.END_TIME as PROCESSINSTANCE_ENDTIME  from (select a.*,b.ID,b.type,b.user_id"
				+ ",b.GROUP_ID,b.group_type,b.include_exclusion from  (select * from FIXFLOW_RUN_TAKSINSTANECE where END_TIME is" + " null and PROCESSINSTANCE_ID in (" + id
				+ ")) a left join FIXFLOW_RUN_TASKIDENTITYLINK b on " + " a.TASKINSTANCE_ID=b.TASKINSTANCE_ID)  x,FIXFLOW_RUN_PROCESSINSTANECE y where x.PROCESSINSTANCE_ID=" + "y.PROCESSINSTANCE_ID";

		List<Map<String, Object>> returnList = sqlCommand.queryForList(sqlText);

		return returnList;

	}
	
	
	
	public List<Map<String, Object>> findAgentUsers(String userId){
		
		
		
		String sql="SELECT distinct(z.AGENT_ID) EID FROM ( select a.AGENT_ID,b.STATUS,a.AUSER as USERID  from FIXFLOW_AGENT_AGENTDETAILS a left join "
		+"FIXFLOW_AGENT_AGENTINFO b on a.AGENT_ID=b.AGENT_ID where b.STATUS='1'"+
		"and  a.AUSER=?) z";
		/*
		String sql=" SELECT distinct(z.EID) EID FROM "+
				"( "+
				" select EID,WFNAME,NODEID,AUSER1 as USERID from FIXFLOW_OUTAGENT_AGENTDETAILS "+
				" WHERE AUSER1 not in (select a.EID from FIXFLOW_OUTAGENT_AGENTDETAILS a,FIXFLOW_OUTAGENT_AGENTINFO b "+
				" WHERE a.EID=b.EID AND STATUS='0' ) "+
				" union "+
				" select EID,WFNAME,NODEID,AUSER2  as USERID from FIXFLOW_OUTAGENT_AGENTDETAILS  "+
				" WHERE AUSER1 in (select a.EID from FIXFLOW_OUTAGENT_AGENTDETAILS a,FIXFLOW_OUTAGENT_AGENTINFO b "+
				" WHERE a.EID=b.EID AND STATUS='0' ) "+
				" ) z WHERE z.USERID=? ";
		*/
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(userId);
		
		//List<UserTo> userTos=new ArrayList<UserTo>();
		
		List<Map<String, Object>> listMaps=sqlCommand.queryForList(sql, objectParamWhere);
		return listMaps;
	}
	
	private void getSubProcessId(String processInstanceId,StringBuffer processInstanceIdList){
		//这个地方需要用到递归去寻找所有的子流程
		List<Object> dataList=new ArrayList<Object>();
		
		
		dataList.add(processInstanceId);
		
		List<Map<String, Object>> dataListMaps=sqlCommand.queryForList("SELECT * FROM FIXFLOW_RUN_PROCESSINSTANECE WHERE PARENT_INSTANCE_ID=?", dataList);
		if(dataListMaps.size()>0){
			
			for (Map<String, Object> map : dataListMaps) {
				processInstanceIdList.append(",'"+StringUtil.getString(map.get("PROCESSINSTANCE_ID"))+"'");
				getSubProcessId(StringUtil.getString(map.get("PROCESSINSTANCE_ID")),processInstanceIdList);
			}

		}

	}


}
