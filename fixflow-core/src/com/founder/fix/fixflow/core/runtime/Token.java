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
package com.founder.fix.fixflow.core.runtime;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;


import com.founder.fix.fixflow.core.impl.db.PersistentObject;

public interface Token  extends PersistentObject, Serializable {

	/**
	 * 获取令牌名称
	 * @return
	 */
	String getName();
	
	/**
	 * 获取令牌开始时间
	 * @return
	 */
	Date getStartTime();

	/**
	 * 获取令牌结束时间
	 * @return
	 */
	Date getEndTime();
	
	/**
	 * 获取节点进入时间
	 * @return
	 */
	Date getNodeEnterTime();
	
	/**
	 * 是否暂停
	 * @return
	 */
	boolean isSuspended();
	
	/**
	 * 加锁
	 * @return
	 */
	boolean getlock();
	
	/**
	 * 获取令牌所在节点ID
	 * @return
	 */
	String getNodeId();
	
	/**
	 * 获取流程实例ID
	 * @return
	 */
	String getProcessInstanceId();
	
	/**
	 * 获取父令牌ID
	 * @return
	 */
	String getParentTokenId();
	
	boolean isAbleToReactivateParent();

	/**
	 * 获取归档时间
	 * @return
	 */
	Date getArchiveTime();
	/**
	 * 获取令牌扩展字段(大小写区分)
	 * 
	 * @return 任务扩展字段
	 */
	Map<String, Object> getExtensionFields();

	/**
	 * 获取令牌扩展字段值
	 * 
	 * @param fieldName
	 *            字段名称(大小写区分)
	 * @return 任务扩展字段值
	 */
	Object getExtensionField(String fieldName);
}
