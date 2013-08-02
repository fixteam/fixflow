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
 * 身份类型
 * @author kenshin
 *
 */
public enum IdentityLinkType {

	/**
	 * 任务领取人类型
	 * 通过领取任务，某一用户将成为任务的责任人，这个任务将从组的其他成员的任务列表里面消失。
	 */
	assignee,

	/**
	 * 任务候选人类型
	 * 这个类型标识任务处于共享模式,范围内的任何人都可以领取任务
	 */
	candidate,

	/**
	 * 任务所有者类型
	 * 当任务处理完毕后身份类型将被更新为此类型,用来标识任务的最终负责人.
	 */
	owner



}
