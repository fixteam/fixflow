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
package com.founder.fix.fixflow.core;

import java.util.Date;

public interface HistoryService extends ProcessService{

	/**
	 *根据流程实例号归档
	 * @param processinstanceId
	 * @return 
	 */
	public boolean archiveByProcessInstanceId(String processinstanceId);
	
	/**
	 * 根据流程定义key归档
	 * @param processDefinitionKey
	 * @return
	 */
	public boolean archiveByProcessDefinitionKey(String processDefinitionKey);
	
	/**
	 * 归档两个时间段之间的流程实例
	 * @param begin 开始时间，可为null
	 * @param end 结束时间 可为null
	 * @return
	 */
	public boolean archiveBetweenTime(Date begin,Date end);
	
	/**
	 * 归档已经结束的所有流程
	 * @return
	 */
	public boolean archiveEnd();
	
}
