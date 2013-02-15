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
