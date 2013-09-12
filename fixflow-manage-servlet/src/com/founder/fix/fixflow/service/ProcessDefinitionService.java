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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 流程定义管理
 * @author Administrator
 *
 */
public interface ProcessDefinitionService {
	
	/**
	 * 设置数据库连接
	 * @param connection
	 */
	public void setConnection(Connection connection);
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection();
	
	/**
	 * 查询流程定义
	 * @param params
	 * queryProcessName 流程名称
	 * queryProcessId 流程id
	 * queryType 流程分类
	 * pageIndex 页数
	 * pageSize页大小
	 * @return 流程定义的所有信息
	 * @throws SQLException
	 */
	public Map<String, Object> getProcessDefitionList(Map<String,Object> params) throws SQLException;
	
	/**
	 * 发布流程定义
	 * @param 
	 * key：deploymentId发布号（更新用）
	 * ProcessFile：文件流
	 * @throws Exception 
	 */
	public void deployByZip(Map<String,Object> params) throws Exception;
	
	/**
	 * 删除流程定义
	 * @param params 
	 * deploymentId发布号
	 */
	public void deleteDeploy(Map<String,Object> params);
	
	/**
	 * 获取流程定义文件流用于下载流程定义
	 * @param params  
	 * deploymentId 发布号
	 * @return 
	 * FILENAME文件名，
	 * BYTES文件字节流
	 */
	public List<Map<String,Object>> getResources(Map<String,Object> params);
}
