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
package com.founder.fix.fixflow.core.impl.flowgraphics.svg.to;

import com.founder.fix.fixflow.core.impl.flowgraphics.svg.component.SvgTaskComponent;

public class SvgTaskTo extends SvgShapeBaseTo {
	
	/**
	 * 任务节点类型默认值
	 */
	protected static final TaskType TaskType_DEFAULT = TaskType.Task;
	
	/**
	 * 任务节点类型
	 */
	protected TaskType taskType=TaskType_DEFAULT;
	
	
	/**
	 * 任务节点循环类型默认值
	 */
	protected static final LoopType LoopType_DEFAULT = LoopType.NoLoop;
	
	/**
	 * 任务节点循环类型
	 */
	protected LoopType loopType=LoopType_DEFAULT;
	

	


	


	public String getComponentClass() {
		return SvgTaskComponent.class.getCanonicalName();
	}
	
	
	/**
	 * 获取任务节点的类型
	 * @return
	 */
	public TaskType getTaskType() {
		return taskType;
	}

	/**
	 * 设置任务节点的类型
	 * @param taskType
	 */
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	/**
	 * 获取循环类型
	 * @return
	 */
	public LoopType getLoopType() {
		return loopType;
	}


	/**
	 * 设置循环类型
	 * @param loopType
	 */
	public void setLoopType(LoopType loopType) {
		this.loopType = loopType;
	}
	
	
	/**
	 * 任务类型枚举
	 * @author kenshin
	 *
	 */
	public enum TaskType
	{
		/**
		 * 默认任务节点
		 */
		Task,
		
		/**
		 * 用户任务节点
		 */
		UserTask,
		
		/**
		 * 脚本任务节点
		 */
		ScriptTask,
		
		/**
		 * 服务任务节点
		 */
		ServiceTask,
		
		/**
		 * 企业规则任务节点
		 */
		BusinessRuleTask,
		
		/**
		 * 手动任务
		 */
		ManualTask,
		
		/**
		 * 发送任务
		 */
		SendTask,
		
		ReceiveTask,

	}
	
	

	
}
