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
package com.founder.fix.fixflow.service;

import java.sql.SQLException;
import java.util.Map;

/**
 * @ClassName: ProcessInstanceService
 * @Description: TODO
 * @author shao
 *
 */
public interface ProcessInstanceService {
	/**
	  * getProcessInstances
	
	  * @Title: getProcessInstances
	  * @Description: 获取所有的流程实例
	  * @param param
	  * @return
	  * @throws SQLException
	  */
	public Map<String,Object> getProcessInstances(Map<String,Object> param) throws SQLException;

	public Map<String,Object> getProcessTokens(Map<String,Object> param) throws SQLException;
}
