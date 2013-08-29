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
package com.founder.fix.fixflow.core.objkey;

public interface ObjKeyInterface {
	/**
	 * 查询中拼接开始时间
	 */
	public static String BEGIN_TIME_KEY="begin_";
	
	/**
	 * 查询中拼接结束时间
	 */
	public static String AFTER_TIME_KEY="after_";
	
	/**
	 * 获取用于流程内部流转的实例key
	 * @return 实例key
	 */
	String EntityKey();
	

	String FullKey();
	
	/**
	 * 获取用户数据库持久化存储的字段Key
	 * @return 数据库字段Key
	 */
	String DataBaseKey();
	
	
	/**
	 * key 的显示名称
	 * @return 显示名称
	 */
	String KeyName();


}
