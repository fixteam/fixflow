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
package com.founder.fix.fixflow.expand.rulescript.select;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.task.TaskQueryImpl;
import com.founder.fix.fixflow.core.scriptlanguage.SelectRulesScript;

public class TaskQueryCountScript implements SelectRulesScript {

	protected TaskQueryImpl taskQuery;
	protected Page page;
	protected SqlCommand sqlCommand;
	protected Pagination pagination;

	public Object execute(Object parameter, SqlCommand sqlCommand, ProcessEngineConfigurationImpl processEngineConfiguration) {

		this.taskQuery = (TaskQueryImpl) parameter;
		this.sqlCommand = sqlCommand;
		this.pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();

		return findTaskCountByQueryCriteria(taskQuery);
	}
	
	
	public long findTaskCountByQueryCriteria(TaskQueryImpl taskQuery) {
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
		
		TaskQueryScript taskQueryScript=new TaskQueryScript();
		
		
		selectTaskByQueryCriteriaSql = taskQueryScript.selectTaskByQueryCriteriaSql(selectTaskByQueryCriteriaSql, taskQuery, objectParamWhere,sqlCommand);
		if(taskQuery.getIsAgent()){
			selectTaskByQueryCriteriaSql =selectTaskByQueryCriteriaSql+
					" and ((SELECT count(1) FROM "+
					"FIXFLOW_AGENT_AGENTDETAILS ag1,FIXFLOW_AGENT_AGENTINFO ag2 "+
					"where ag1.AGENT_ID=ag2.AGENT_ID  AND ag2.SDATE<="+pagination.getCurrentDateSql()+" AND ag2.EDATE>="+pagination.getCurrentDateSql()+" AND ag1.AUSER='"+agentOldAssignee+"' "+
					"AND ag1.AGENT_ID='"+taskQuery.getAgentId()+"' and ag1.PROCESS_ID='_fix_flow_all_flow')>0 or "+
					"(T.PROCESSDEFINITION_KEY in (SELECT ag.PROCESS_ID FROM (SELECT ag1.*,ag2.SDATE,ag2.EDATE FROM "+
					"FIXFLOW_AGENT_AGENTDETAILS ag1,FIXFLOW_AGENT_AGENTINFO ag2 "+
					"where ag1.AGENT_ID=ag2.AGENT_ID and ag2.STATUS='1' AND ag2.SDATE<="+pagination.getCurrentDateSql()+" AND ag2.EDATE>="+pagination.getCurrentDateSql()+" AND ag1.AUSER='"+agentOldAssignee+"' AND ag1.AGENT_ID='"+taskQuery.getAgentId()+"') ag)"+
					"))";
		}
		Object returnObj = sqlCommand.queryForValue(selectTaskByQueryCriteriaSql, objectParamWhere);
		
		if(taskQuery.getIsAgent()){
			if(taskQuery.getAssignee()!=null&&!taskQuery.getAssignee().equals("")){

				taskQuery.taskAssignee(agentOldAssignee);
			}
			if(taskQuery.getCandidateUser()!=null&&!taskQuery.getCandidateUser().equals("")){

				taskQuery.taskCandidateUser(agentOldAssignee);
			}
		}
		
		return Integer.parseInt(returnObj.toString());
	}


}
