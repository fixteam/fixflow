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
