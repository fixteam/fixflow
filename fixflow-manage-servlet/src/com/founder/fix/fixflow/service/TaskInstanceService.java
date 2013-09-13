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

import java.util.Map;

/**
 * @ClassName: TaskInstanceService
 * @Description: TODO
 * @author shao
 *
 */
public interface TaskInstanceService {
	/**
	  * getTaskList
	
	  * @Title: getTaskList
	  * @Description: 获取任务列表
	  * @param params
	  * @return
	  * @throws Exception
	  */
	public Map<String,Object> getTaskList(Map<String,Object> filter) throws Exception;
	
	public void suspendTask(Map<String,Object> filter) throws Exception;
	
	public void resumeTask(Map<String,Object> filter) throws Exception;
	
	public void transferTask(Map<String,Object> filter) throws Exception;
	
	public void rollBackNode(Map<String,Object> filter) throws Exception;
	
	public void rollBackStep(Map<String,Object> filter) throws Exception;
}
