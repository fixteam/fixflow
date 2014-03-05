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

import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

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
	  * @param param<br>
	  * userId 用户编号<br>
	  * processDefinitionKey 流程定义<br>
	  * processInstanceId 流程实例编号<br>
	  * subject 流程标题<br>
	  * initor 发起人<br>
	  * status 流程状态<br>
	  * pageIndex 分页页序<br>
	  * pageSize 页大小<br>
	  * @return
	  * @throws SQLException
	  */
	public Map<String,Object> getProcessInstances(Map<String,Object> param) throws SQLException;

	/**
	  * getProcessTokens
	
	  * @Title: getProcessTokens
	  * @Description: 获取流程令牌
	  * @param param<br>
	  * userId 用户编号<br>
	  * processInstanceId 流程实例编号<br>
	  * @return
	  * @throws SQLException
	  */
	public Map<String,Object> getProcessTokens(Map<String,Object> param) throws SQLException;
	
	/**
	  * getProcessVariables
	
	  * @Title: getProcessVariables
	  * @Description: 获取流程变量
	  * @param params<br>
	  * userId 用户编号<br>
	  * processInstanceId 流程实例编号<br>
	  * @return
	  * @throws SQLException
	  */
	public Map<String,Object> getProcessVariables(Map<String,Object> params) throws SQLException;
	
	/**
	  * saveProcessVariables
	
	  * @Title: saveProcessVariables
	  * @Description: 修改流程变量
	  * @param params<br>
	  * userId 用户编号<br>
	  * processInstanceId 流程实例编号<br>
	  * deleteIndex 删除变量信息，多个逗号分隔
	  * insertAndUpdate 新增或修改流程变量，以map存在
	  * @throws SQLException
	  */
	public void saveProcessVariables(Map<String,Object> params) throws SQLException;
	
	/**
	 * 暂停流程实例
	 * @param params
	 * operProcessInstanceId操作的流程实例ID，多个 用逗号隔开
	 * @throws SQLException
	 */
	public void suspendProcessInstance(Map<String,Object> params) throws SQLException;
	
	/**
	 * 恢复流程实例
	 * @param params
	 * userId 用户编号
	 * operProcessInstanceId操作的流程实例ID，多个 用逗号隔开
	 * @throws SQLException
	 */
	public void continueProcessInstance(Map<String,Object> params) throws SQLException;
	
	/**
	 * 终止流程实例
	 * @param params
	 * userId 用户编号
	 * operProcessInstanceId操作的流程实例ID，多个 用逗号隔开
	 * @throws SQLException
	 */
	public void terminatProcessInstance(Map<String,Object> params) throws SQLException;
	
	/**
	 * 删除流程实例
	 * @param params
	 * userId 用户编号
	 * operProcessInstanceId操作的流程实例ID，多个 用逗号隔开
	 * @throws SQLException
	 */
	public void deleteProcessInstance(Map<String,Object> params) throws SQLException;
	
	/**
	 * 归档流程实例
	 * @param params
	 * operProcessInstanceId操作的流程实例ID，多个 用逗号隔开
	 * operProcessInstanceId操作的流程实例ID，多个 用逗号隔开
	 * @throws SQLException
	 */
	public void setHistory(Map<String,Object> params) throws SQLException;
}
