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
