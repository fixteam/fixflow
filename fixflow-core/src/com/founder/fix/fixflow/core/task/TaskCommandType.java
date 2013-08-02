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



/**
 * 用户命令类型模枚举
 * @author kenshin
 *
 */
public enum TaskCommandType {
	
	
	/**
	 * 草稿
	 */
	draft,
	/**
	 * 通用类型
	 */
	general,
	/**
	 * 退回类型
	 */
	rollBack,
	/**
	 * 转发类型
	 */
	transfer,
	/**
	 * 代理任务类型
	 */
	delegation,
	/**
	 * 扩展类型
	 */
	expand,
	/**
	 * 提交任务
	 */
	submit;


}
