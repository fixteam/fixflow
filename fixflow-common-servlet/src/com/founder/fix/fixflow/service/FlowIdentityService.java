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
package com.founder.fix.fixflow.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.identity.UserTo;

/**
 * @ClassName: FlowIdentityService
 * @Description: TODO
 * @author Char
 * @date 2013-8-30 上午2:34:33
 *
 */
public interface FlowIdentityService {
//	public void setConnection(Connection connection);
//	
//	public Connection getConnection();
	
	public static final String FIX_FLOW_ALL_FLOW = "_fix_flow_all_flow";
	
	UserTo getUserTo(String userId);
	
	String getUserName(String userId);
	
	Map<String, Object> getUserDelegationInfo(String agentId) throws SQLException;
	
	void saveUserDelegationInfo(Map<String, Object> delegationInfo) throws Exception;
}
