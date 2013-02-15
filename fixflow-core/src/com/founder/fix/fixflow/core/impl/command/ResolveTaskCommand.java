package com.founder.fix.fixflow.core.impl.command;

public class ResolveTaskCommand extends BaseTaskCommand{

	/**
	 * 需要还回委托任务的编号
	 */
	protected String resolveTaskId;

	/**
	 * 获取被还回的委托任务编号
	 */
	public String getResolveTaskId() {
		return resolveTaskId;
	}

	/**
	 * 设置被还回的委托任务编号
	 */
	public void setResolveTaskId(String resolveTaskId) {
		this.resolveTaskId = resolveTaskId;
	}
	
}
