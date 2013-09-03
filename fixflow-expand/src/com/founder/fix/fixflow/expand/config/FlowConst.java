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
package com.founder.fix.fixflow.expand.config;

/**
 * 流程静态变量
 * @author jiang_nan
 *
 */
public class FlowConst {
	
	/**
	 * 流程处理命令类型静态变量
	 * @author jiang_nan
	 *
	 */
	public class ProcessCommandType {
		
		/**
		 * 代办任务、表单提交
		 */
		public static final String TO_DO_TASKS="toDoTasks";
		
		/**
		 * 共享任务
		 */
		public static final String SHARED_TASKS="sharedTasks";
		
		/**
		 * 流程追踪
		 */
		public static final String PROCESS_INSTANCE_INFO="processInstanceInfo";
		
		/**
		 * 已办任务
		 */
		public static final String FINISH_TASKS="finishTasks";
		
		/**
		 * 全部
		 */
		public static final String ALL="all";

	}
	

}
