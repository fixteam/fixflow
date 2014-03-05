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
package com.founder.fix.fixflow.core.task;

import java.util.Date;

import com.founder.fix.fixflow.core.impl.db.PersistentObject;

/**
 * 意见查询对象
 * @author kenshin
 *
 */
public interface CommentQueryTo extends PersistentObject {

	/** 提出意见的用户 */
	String getUserId();

	/** 提出意见的时间 */
	Date getTime();

	/** 任务编号 */
	String getTaskId();

	/** 流程实例编号 */
	String getProcessInstanceId();
	
	/**
	 * 任务用户命令
	 * @return
	 */
	String getMessage();

	/**
	 * 获取意见的内容
	 * 
	 * @see TaskService#getTaskComments(String)
	 */
	String getFullMessage();
}
