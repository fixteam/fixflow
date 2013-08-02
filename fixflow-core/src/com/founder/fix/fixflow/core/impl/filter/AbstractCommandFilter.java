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
package com.founder.fix.fixflow.core.impl.filter;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.task.TaskInstance;

public abstract class AbstractCommandFilter {
	/**
	 * 自动领取
	 */
	public static String AUTO_CLAIM="autoclaim";
	/**
	 * 手动领取
	 */
	public static String MANUAL_CLAIM="manualclaim";
	
	
	protected boolean isProcessTracking=false;
	
	protected TaskCommandInst taskCommandInst;
	

	public boolean isProcessTracking() {
		return isProcessTracking;
	}


	public void setProcessTracking(boolean isProcessTracking) {
		this.isProcessTracking = isProcessTracking;
	}


	public TaskCommandInst getTaskCommandInst() {
		return taskCommandInst;
	}


	public void setTaskCommandInst(TaskCommandInst taskCommandInst) {
		this.taskCommandInst = taskCommandInst;
	}


	/**
	 * 获取任务的处理方式
	 * @return
	 */
	public static String  getCommandType() {
		ProcessEngine processEngine=ProcessEngineManagement.getDefaultProcessEngine();
		String commandType=processEngine.getProcessEngineConfiguration().getTaskCommandConfig().getCommandType();
		if(commandType==null){
			commandType=MANUAL_CLAIM;
		}
		return commandType;
	}
	
	/**
	 * 判断是否是自动领取
	 * @return
	 */
	public static boolean isAutoClaim(){
		return getCommandType().equals(AUTO_CLAIM);
	}
	
	/**
	 * 判断是否是手动领取
	 * @return
	 */
	public static boolean isManualClaim(){
		return getCommandType().equals(MANUAL_CLAIM);
	}
	
	public abstract boolean accept(TaskInstance taskInstance);
	

}
