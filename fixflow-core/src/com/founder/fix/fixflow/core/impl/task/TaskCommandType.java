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
package com.founder.fix.fixflow.core.impl.task;

/**
 * 任务处理命令系统类型
 * @author kenshin
 *
 */
public class TaskCommandType {

	/**
	 * 自动结束
	 */
	public static String AUTOEND="autoEnd";
	/**
	 * 自动跳过
	 */
	public static String SKIPNODE="skipNode";
	/**
	 * 子流程结束
	 */
	public static String SUBPROCESSEND="subProcessEnd";
	/**
	 * 流程开始
	 */
	public static String STARTEVENT="startEvent";
	/**
	 * 流程结束
	 */
	public static String ENDEVENT="endEvent";
	
	/**
	 * 等待结束
	 */
	public static String RECEIVEEND="receiveEnd";

}
