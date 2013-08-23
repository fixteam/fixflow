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
import java.util.Map;

import org.quartz.SchedulerException;

/**
 * 定时任务管理
 * @author Administrator
 *
 */
public interface JobService {

	/**
	 * 获取job集合
	 * @param params
	 * @return
	 * @throws SchedulerException
	 * @throws SQLException
	 */
	public Map<String,Object> getJobList(Map<String,Object> params) throws SchedulerException, SQLException;
	
	/**
	 * 暂停调度
	 * @throws SQLException 
	 */
	public void suspendScheduler(Map<String,Object> params) throws SQLException;
	
	/**
	 * 恢复调度
	 * @throws SQLException 
	 */
	public void continueScheduler(Map<String,Object> params) throws SQLException;
	
	/**
	 * 暂停job
	 * @param params
	 * @throws SchedulerException 
	 * @throws SQLException 
	 */
	public void suspendJob(Map<String,Object> params) throws SchedulerException, SQLException;
	
	/**
	 * 恢复job
	 * @param params
	 * @throws SQLException 
	 * @throws SchedulerException 
	 */
	public void continueJob(Map<String,Object> params) throws SQLException, SchedulerException;
	
	/**
	 * 获取job的触发器
	 * @param params
	 * @return
	 * @throws SQLException 
	 * @throws SchedulerException 
	 */
	public Map<String,Object> getJobTrigger(Map<String,Object> params) throws SQLException, SchedulerException;
	
	/**
	 * 暂停触发器
	 * @param params
	 * @throws SchedulerException 
	 * @throws SQLException 
	 */
	public void suspendTrigger(Map<String,Object> params) throws SchedulerException, SQLException;
	
	/**
	 * 恢复触发器
	 * @param params
	 * @throws SQLException 
	 * @throws SchedulerException 
	 */
	public void continueTrigger(Map<String,Object> params) throws SchedulerException, SQLException;
	
	/**
	 * 增加触发器
	 * @param params
	 * @throws SchedulerException
	 * @throws SQLException
	 */
	public void addTrigger(Map<String,Object> params) throws SchedulerException, SQLException;
	
	/**
	 * 删除触发器
	 * @param params
	 * @throws SchedulerException
	 * @throws SQLException
	 */
	public void deleteTrigger(Map<String,Object> params) throws SchedulerException, SQLException;
	
	/**
	 * 添加一个job
	 * @param params
	 * @throws SchedulerException
	 * @throws SQLException
	 */
	public void addJob(Map<String,Object> params) throws SchedulerException, SQLException;
	
	/**
	 * 删除一个job
	 * @param params
	 * @return
	 * @throws SchedulerException
	 * @throws SQLException
	 */
	public boolean deleteJob(Map<String,Object> params) throws SchedulerException, SQLException;
	
	
}
