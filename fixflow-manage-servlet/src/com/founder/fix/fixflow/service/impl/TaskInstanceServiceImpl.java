/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author shao
 */
package com.founder.fix.fixflow.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.founder.fix.fixflow.core.IdentityService;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.util.DateUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.service.TaskInstanceService;
import com.founder.fix.fixflow.shell.CommonServiceImpl;
import com.founder.fix.fixflow.shell.FixFlowShellProxy;
import com.founder.fix.fixflow.util.Pagination;

/**
 * @ClassName: TaskInstanceServiceImpl
 * @Description: TODO
 * @author shao
 *
 */
@Scope("prototype")
@Service
public class TaskInstanceServiceImpl  extends CommonServiceImpl implements TaskInstanceService {

	/*
	 * <p>Title: getTaskList</p>
	 * <p>Description: </p>
	 * @param params
	 * @return
	 * @throws Exception
	 * @see com.founder.fix.fixflow.service.TaskInstanceService#getTaskList(java.util.Map)
	 */
	@Override
	public Map<String, Object> getTaskList(Map<String, Object> filter)
			throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		ProcessEngine engine = getProcessEngine(filter
				.get("userId"));
		try {
			TaskQuery tq = engine.getTaskService().createTaskQuery();
			
			tq.taskAssignee(StringUtil.getString(filter.get("userId")));
			tq.taskCandidateUser(StringUtil.getString(filter.get("userId")));
			
			String descritpion = StringUtil.getString(filter.get("title"));
			if(StringUtil.isNotEmpty(descritpion))
				tq.taskDescriptionLike(descritpion);
			
			String initor	   = StringUtil.getString(filter.get("initor"));
			if(StringUtil.isNotEmpty(initor))
				tq.initiatorLike(initor);
			
			String bizKey	   = StringUtil.getString(filter.get("bizKey"));
			if(StringUtil.isNotEmpty(bizKey))
				tq.businessKey(bizKey);
			
			Date dates = null;
			Date datee = null;
			String dss = StringUtil.getString(filter.get("arrivalTimeS"));
			String dse = StringUtil.getString(filter.get("arrivalTimeE"));
			if(StringUtil.isNotEmpty(dss)){
				dates = DateUtil.stringToDate(dss,"yyyy-MM-dd");
			}
			if(StringUtil.isNotEmpty(dse)){
				datee = DateUtil.stringToDate(dse,"yyyy-MM-dd");
			}
			if(dates!=null)
				tq.taskCreatedAfter(datee);
			
			if(datee!=null)
				tq.taskCreatedBefore(dates);
			
			String pageI = StringUtil.getString(filter.get("pageIndex"));
			String rowI = StringUtil.getString(filter.get("pageSize"));
			
			int pageIndex=1;
			int rowNum   =10;
			if(StringUtil.isNotEmpty(pageI)){
				pageIndex = Integer.valueOf(pageI);
			}
			if(StringUtil.isNotEmpty(rowI)){
				rowNum = Integer.valueOf(rowI);
			}
			
			if(filter.get("ended")==null)
				tq.taskNotEnd();
			
			if(StringUtil.isNotEmpty(StringUtil.getString(filter.get("agentUserId")))){
				tq.isAgent(true);
				if(filter.get("agentType").equals("1")){
					tq.taskAssignee(StringUtil.getString(filter.get("userId")));
					tq.taskCandidateUser(StringUtil.getString(filter.get("userId")));
					tq.agentId(StringUtil.getString(filter.get("agentUserId")));
				}else{
					tq.taskAssignee(StringUtil.getString(filter.get("agentUserId")));
					tq.taskCandidateUser(StringUtil.getString(filter.get("agentUserId")));
					tq.agentId(StringUtil.getString(filter.get("userId")));
				}
			}else{
				tq.taskAssignee(StringUtil.getString(filter.get("userId")));
				tq.taskCandidateUser(StringUtil.getString(filter.get("userId")));
			}
			
			List<TaskInstance> lts = tq.listPagination(pageIndex, rowNum);
			Long count = tq.count();
			List<Map<String,Object>> instanceMaps = new ArrayList<Map<String,Object>>();
			
			Pagination page = new Pagination(pageIndex,rowNum);
			page.setTotal(count.intValue());
			IdentityService identsvz = engine.getIdentityService();
			
			for(TaskInstance tmp:lts){ 
				Map<String,Object> instances = tmp.getPersistentState();
				String userId = StringUtil.getString(instances.get("PI_START_AUTHOR"));
				
				UserTo user = identsvz.getUserTo(userId);
				instances.put("userName", user.getUserName());
				instanceMaps.add(instances);
			}
			result.put("dataList", instanceMaps);
			result.put("pageInfo", page);
			
		} finally {
			FixFlowShellProxy.closeProcessEngine(engine, false);
		}
		return result;
	}

}
