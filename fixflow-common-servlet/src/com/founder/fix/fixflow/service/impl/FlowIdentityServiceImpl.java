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
 * @author Administrator
 */
package com.founder.fix.fixflow.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.derby.iapi.services.io.NewByteArrayInputStream;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.founder.fix.fixflow.core.ConnectionManagement;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.interceptor.Session;
import com.founder.fix.fixflow.core.impl.util.DateUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.service.FlowIdentityService;
import com.founder.fix.fixflow.shell.CommonServiceImpl;
import com.founder.fix.fixflow.shell.FixFlowShellProxy;

/**
 * @ClassName: FlowIdentityServiceImpl
 * @Description: TODO
 * @author Char
 * @date 2013-8-30 上午2:35:22
 * 
 */
@Scope("prototype")
@Service
public class FlowIdentityServiceImpl extends CommonServiceImpl implements
		FlowIdentityService {

	/*
	 * <p>Title: getUserTo</p> <p>Description: </p>
	 * 
	 * @param userId
	 * 
	 * @see
	 * com.founder.fix.fixflow.service.FlowIdentityService#getUserTo(java.lang
	 * .String)
	 */
	@Override
	public UserTo getUserTo(String userId) {
		UserTo userTo = null;
		try {
			userTo = this.getProcessEngine(userId).getIdentityService()
					.getUserTo(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userTo;
	}

	/*
	 * <p>Title: getUserName</p> <p>Description: </p>
	 * 
	 * @param userId
	 * 
	 * @see
	 * com.founder.fix.fixflow.service.FlowIdentityService#getUserName(java.
	 * lang.String)
	 */
	@Override
	public String getUserName(String userId) {
		return this.getUserTo(userId).getUserName();
	}

	/*
	 * <p>Title: getUserDelegationInfo</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see
	 * com.founder.fix.fixflow.service.FlowIdentityService#getUserDelegationInfo
	 * ()
	 */
	@Override
	public Map<String, Object> getUserDelegationInfo(String agentId)
			throws SQLException {
		Map<String, Object> resultData = new HashMap<String, Object>();
		Map<String, Object> agentInfo = new HashMap<String, Object>();
		List<Map<String, Object>> detailInfoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> eachResultData = new HashMap<String, Object>();
		Connection connection = null;
		try {
			connection = FixFlowShellProxy
					.getConnection(ConnectionManagement.defaultDataBaseId);
			this.connection = connection;
			this.getProcessEngine("");
			SqlCommand sqlCommand = new SqlCommand(connection);
			String sqlStr = "select * from fixflow_agent_agentinfo where agent_id = ?";
			List<Object> data = new ArrayList<Object>();
			data.add(agentId);
			List<Map<String, Object>> listData = sqlCommand.queryForList(
					sqlStr, data);
			if (listData.size() > 0) {
				Map<String, Object> eachAgentInfo = listData.get(0);
				agentInfo.put("agentId", agentId);
				agentInfo.put("agentName", this.getUserName(agentId));
				agentInfo.put("sDate", eachAgentInfo.get("SDATE"));
				agentInfo.put("eDate", eachAgentInfo.get("EDATE"));
				agentInfo.put("oDate", eachAgentInfo.get("ODATE"));
				agentInfo.put("status", eachAgentInfo.get("STATUS"));
				// eachAgentInfo.put("agentName", this.getUserName(agentId));

				sqlStr = "select * from fixflow_agent_agentdetails where agent_id = ?";
				
			
				data.clear();
				data.add(agentId);
				listData = sqlCommand.queryForList(sqlStr, data);
				for (Map<String, Object> rowDataMap : listData) {
					// if (resultData.size() > 0) {
					eachResultData.put("agentId", rowDataMap.get("AGENT_ID"));
					String auser = StringUtil
							.getString(rowDataMap.get("AUSER"));
					eachResultData.put("auser", auser);
					eachResultData.put("auserName", this.getUserName(auser));

					String processId = StringUtil.getString(rowDataMap
							.get("PROCESS_ID"));
					eachResultData.put("processId", processId);
					eachResultData.put("guid", rowDataMap.get("GUID"));
					String processName = "";
					if (processId.equals(FIX_FLOW_ALL_FLOW)) {
						processName = "所有流程";
					} else {
						ProcessDefinitionBehavior processDefinition = this
								.getProcessEngine(agentId).getModelService()
								.createProcessDefinitionQuery()
								.processDefinitionKey(processId)
								.latestVersion().singleResult();
						processName = processDefinition.getName();
					}
					eachResultData.put("processName", processName);
					detailInfoList.add(eachResultData);
					// }
				}

				agentInfo.put("detailInfoList", detailInfoList);
			} else {
//				String loginUser = "";
				Map<String, Object> eachAgentInfo = new HashMap<String, Object>();
				agentInfo.put("agentName", this.getUserName(agentId));
				agentInfo.put("agent", agentId);
				agentInfo.put("sDate", new Date());
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, 14);
				Date eDate = calendar.getTime();
				agentInfo.put("eDate", eDate);
				
				
				eachResultData.put("agentId", agentId);
				String auser = "";
				eachResultData.put("auser", auser);
				eachResultData.put("auserName", "");

				String processId = FIX_FLOW_ALL_FLOW;
				eachResultData.put("processId", processId);
				String processName = "所有流程";
				eachResultData.put("processName", processName);
				eachResultData.put("guid", java.util.UUID.randomUUID().toString());
				
				detailInfoList.add(eachResultData);
				agentInfo.put("detailInfoList", detailInfoList);
			}
		} finally {
			connection.close();
		}
		resultData.put("agentInfo", agentInfo);
		// mapInfo.put("agentId", userId);
		// mapInfo.put("agentName", this.getUserName(userId));
		System.out.println(resultData);
		return resultData;
	}

	/*
	 * <p>Title: saveUserDelegationInfo</p> <p>Description: </p>
	 * 
	 * @param delegationInfo
	 * 
	 * @see
	 * com.founder.fix.fixflow.service.FlowIdentityService#saveUserDelegationInfo
	 * (java.util.Map)
	 */
	@Override
	public void saveUserDelegationInfo(Map<String, Object> delegationInfo) {
	}

	/*
	 * <p>Title: setConnection</p> <p>Description: </p>
	 * 
	 * @param connection
	 * 
	 * @see
	 * com.founder.fix.fixflow.service.FlowIdentityService#setConnection(java
	 * .sql.Connection)
	 */
}
