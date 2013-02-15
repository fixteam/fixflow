package com.founder.fix.fixflow.core.task;


/**
 * 任务代理状态
 * @author kenshin
 */
public enum DelegationState {

  /**
   * 任务转发
   * 雇主(owner)委派任务交给指派人(assignee),
   * 当指派人(assignee)完成任务,
   * 任务是标记为{@link #RESOLVED},并送回给雇主(owner)。
   * 当发生这种情况,指派人(assignee)让雇主(owner)在待办事项里得到这个任务。
   */
  PENDING,
  /**
   * 任务返回
   * 指派人(assignee)解决的任务之后，将指派人(assignee)设置为雇主(owner)
   * 雇主(owner)在代办事宜可以发现这个任务。
   * 雇主现在是能够完成的任务。
   */
  RESOLVED
}
