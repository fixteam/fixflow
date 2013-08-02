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
package com.founder.fix.fixflow.core.impl.db;

import java.util.Map;


public abstract class AbstractPersistentObject implements PersistentObject {

	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1542067996535178080L;

	
	/**
	 * 从数据库初始化对象
	 * @param entityMap 字段Map
	 * @return
	 */
	public abstract void persistentInit(Map<String, Object> entityMap);
	
	/**
	 * 获取能持久化到数据的Map
	 * @return 对应到数据库字段的Map
	 */
	public abstract Map<String, Object> getPersistentDbMap();
	


}
