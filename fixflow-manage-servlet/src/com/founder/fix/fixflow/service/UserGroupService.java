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
 * @author yangchenhui
 */
package com.founder.fix.fixflow.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Page;

/**
 * 用户组管理
 * @author Administrator
 *
 */
public interface UserGroupService {
	
	/**
	 * 用户
	 * @param page
	 * @return
	 * @throws SQLException 
	 */
	public Map<String, Object> getAllUsers(Map<String,Object> filter) throws SQLException;
	
	public Map<String, Object> getAllGroup(Map<String,Object> filter) throws SQLException;
	
	public List<Map<String, Object>> getAllGroupDefinition(Map<String,Object> params) throws SQLException;
	
	public Map<String,Object> getUserInfo(Map<String,Object> params) throws SQLException;
	
}
