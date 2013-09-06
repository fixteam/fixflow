/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author yangchenhui
 */
package com.founder.fix.fixflow.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ScheduleService;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.service.JobService;
import com.founder.fix.fixflow.shell.CommonServiceImpl;
@Scope("prototype")
@Service
public class JobServiceImpl extends CommonServiceImpl implements JobService {

	@Override
	public Map<String, Object> getJobList(Map<String, Object> params) throws SchedulerException, SQLException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String userId = StringUtil.getString(params.get("userId"));
		String queryId = StringUtil.getString(params.get("queryId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		ScheduleService scheduleService = processEngine.getScheduleService();
		Scheduler scheduler = scheduleService.getScheduler();
		try{
			List<Map<String,Object>> jobList = new ArrayList<Map<String,Object>>();
			List<JobDetail> list = scheduleService.getJobList(queryId);
			for(JobDetail job :list){
				Map<String,Object> jobMap = new HashMap<String,Object>();
				jobMap.put("jobName", job.getKey().getName());
				jobMap.put("groupName", job.getKey().getGroup());
				jobMap.put("processName", job.getJobDataMap().get("processName"));
				jobMap.put("processId", job.getJobDataMap().get("processId"));
				jobMap.put("processKey", job.getJobDataMap().get("processKey"));
				jobMap.put("processInstanceId", job.getJobDataMap().get("processInstanceId"));
				jobMap.put("nodeId", job.getJobDataMap().get("nodeId"));
				jobMap.put("bizKey", job.getJobDataMap().get("bizKey"));
				jobMap.put("jobType", job.getJobDataMap().get("jobType"));
				jobMap.put("processId", job.getJobDataMap().get("processId"));
				jobMap.put("nodeName", job.getJobDataMap().get("nodeName"));
				jobMap.put("jobKeyGroup", job.getKey().getGroup());
				jobMap.put("jobKeyName", job.getKey().getName());
				//判断job下的trigger是否全为暂停状态
				List<Trigger> triggerList = (List<Trigger>) scheduler.getTriggersOfJob(job.getKey());
				boolean isPaused = false;
				for(Trigger t:triggerList){
					TriggerState ts = scheduler.getTriggerState(t.getKey());
					if(ts.equals(TriggerState.PAUSED)){
						isPaused = true;
						break;
					}
				}
				jobMap.put("isPaused", isPaused);
				jobList.add(jobMap);
			}
			resultMap.put("dataList", jobList);
		}finally{
			closeProcessEngine();
		}
		return resultMap;
	}
	
	@Override
	public void continueScheduler(Map<String,Object> params) throws SQLException {
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		ScheduleService scheduleService = processEngine.getScheduleService();
		try{
			scheduleService.schedulerStart();
		}finally{
			closeProcessEngine();
		}
	}
	
	@Override
	public void suspendScheduler(Map<String,Object> params) throws SQLException {
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		ScheduleService scheduleService = processEngine.getScheduleService();
		try{
			scheduleService.schedulerShutdown();
		}finally{
			closeProcessEngine();
		}
	}
	
	@Override
	public void suspendJob(Map<String, Object> params) throws SchedulerException, SQLException {
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		ScheduleService scheduleService = processEngine.getScheduleService();
		String jobKeyName = StringUtil.getString(params.get("jobKeyName"));
		String jobKeyGroup = StringUtil.getString(params.get("jobKeyGroup"));
		try{
			scheduleService.suspendJob(jobKeyName, jobKeyGroup);
		}finally{
			closeProcessEngine();
		}
	}
	
	@Override
	public void continueJob(Map<String, Object> params) throws SQLException, SchedulerException {
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		ScheduleService scheduleService = processEngine.getScheduleService();
		String jobKeyName = StringUtil.getString(params.get("jobKeyName"));
		String jobKeyGroup = StringUtil.getString(params.get("jobKeyGroup"));
		try{
			scheduleService.continueJob(jobKeyName, jobKeyGroup);
		}finally{
			closeProcessEngine();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getJobTrigger(Map<String, Object> params) throws SQLException, SchedulerException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		ScheduleService scheduleService = processEngine.getScheduleService();
		Scheduler scheduler = scheduleService.getScheduler();
		try{
			String jobKeyName = StringUtil.getString(params.get("jobKeyName"));
			String jobKeyGroup = StringUtil.getString(params.get("jobKeyGroup"));
			List<Map<String,Object>> triggerList = new ArrayList<Map<String,Object>>();
			List<Trigger> triggers = scheduleService.getTriggerList(jobKeyName, jobKeyGroup);
			for(Trigger t : triggers){
				Map<String,Object> triggerMap = new HashMap<String,Object>();
				triggerMap.put("triggerName", t.getKey().getName());
				triggerMap.put("triggerGroup", t.getKey().getGroup());
				triggerMap.put("startTime", t.getStartTime());
				triggerMap.put("endTime", t.getEndTime());
				triggerMap.put("lastFireTime", t.getPreviousFireTime());
				triggerMap.put("nextFireTime", t.getNextFireTime());
				triggerMap.put("finalFireTime", t.getFinalFireTime());
				TriggerState ts = scheduler.getTriggerState(t.getKey());
				String triggerState = getTriggerStateByEmuType(ts);
				triggerMap.put("triggerState", triggerState);
				boolean isPaused = false;
				if(ts.equals(TriggerState.PAUSED)){
					isPaused = true;
				}
				triggerMap.put("isPaused", isPaused);
				triggerList.add(triggerMap);
			}
			resultMap.put("dataList", triggerList);
			Map<String,Object> jobMap = new HashMap<String,Object>();
			jobMap.put("jobKeyName", jobKeyName);
			jobMap.put("jobKeyGroup", jobKeyGroup);
			resultMap.put("job", jobMap);
		}finally{
			closeProcessEngine();
		}
		return resultMap;
	}
	
	@Override
	public void suspendTrigger(Map<String, Object> params) throws SchedulerException, SQLException {
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		ScheduleService scheduleService = processEngine.getScheduleService();
		String triggerKeyName = StringUtil.getString(params.get("triggerKeyName"));
		String triggerKeyGroup = StringUtil.getString(params.get("triggerKeyGroup"));
		try{
			scheduleService.suspendTrigger(triggerKeyName, triggerKeyGroup);
		}finally{
			closeProcessEngine();
		}
	}
	
	@Override
	public void continueTrigger(Map<String, Object> params) throws SchedulerException,SQLException {
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		ScheduleService scheduleService = processEngine.getScheduleService();
		String triggerKeyName = StringUtil.getString(params.get("triggerKeyName"));
		String triggerKeyGroup = StringUtil.getString(params.get("triggerKeyGroup"));
		try{
			scheduleService.continueTrigger(triggerKeyName, triggerKeyGroup);
		}finally{
			closeProcessEngine();
		}
	}
	
	/**
	 * 根据枚举的触发器类型转成中文
	 * @return
	 */
	public String getTriggerStateByEmuType(TriggerState triggerState) {
		String type = "";
		if(triggerState.toString().equals("BLOCKED")) {
			type = "锁定";
			return type;
		}
		if(triggerState.toString().equals("COMPLETE")) {
			type = "完成";
			return type;
		}
		if(triggerState.toString().equals("ERROR")) {
			type = "错误";
			return type;
		}
		if(triggerState.toString().equals("NONE")) {
			type = "无";
			return type;
		}
		if(triggerState.toString().equals("NORMAL")) {
			type = "普通";
			return type;
		}
		if(triggerState.toString().equals("PAUSED")) {
			type = "暂停";
			return type;
		}
		else {
			return type;
		}
	}

}
