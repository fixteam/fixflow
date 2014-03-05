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
package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.impl.TimerEventDefinitionImpl;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.job.TokenTimeOutJob;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.QuartzUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class TimerEventDefinitionBehavior extends TimerEventDefinitionImpl {
	
	@SuppressWarnings("unchecked")
	public void execute(ExecutionContext executionContext,Event event) {
		
		
		Expression expression=this.getTimeDate();
		FormalExpression formalExpression=null;
		if(expression!=null){
			formalExpression=(FormalExpression)expression;
		}
		if(formalExpression==null){
			return ;
		}
		
		String expressionText=formalExpression.getBody();
		
		if(expressionText==null){
			throw new FixFlowException("超时表达式不能为空");
		}
		Date date=null;
		String expressionTemp=null;//"0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发 
		List<Object> timeObjects=new ArrayList<Object>();
		try {
			Object dateObj=ExpressionMgmt.execute(expressionText, executionContext);
			if(dateObj instanceof Date){
				date=(Date)dateObj;
			}
			else{
				if(dateObj instanceof List){
					timeObjects=(List<Object>)dateObj;
				}else{
					expressionTemp=StringUtil.getString(dateObj);
				}
				
			}
			
		} catch (Exception e) {
			throw new FixFlowException("超时表达式计算失败!原因是: "+e.toString(),e);
		}
		
		TokenEntity tokenEntity=executionContext.getToken();
		String processInstanceId=tokenEntity.getProcessInstance().getId();
		/*VariableTransferEntity variableTransferEntity = new VariableTransferEntity();
		Map<String, Object> transientVariableMap=tokenEntity.getProcessInstance().getContextInstance().getTransientVariableMap();
		String guidString=GuidUtil.CreateGuid();
		variableTransferEntity.addVariable(guidString, transientVariableMap);
		
		if (processInstanceId != null && !processInstanceId.equals("")) {
			VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.PROCESSINSTANCE, processInstanceId);
			variableTransferEntity.addVariableFlowType(variableFlowTypeEntity);
		}
	
		Context.getCommandContext().getVariableManager().saveVariable(variableTransferEntity);
		*/
		
		
		Scheduler scheduler=Context.getProcessEngineConfiguration().getScheduler();

		
		
		
		Map<JobDetail, List<Trigger>> jobList=new HashMap<JobDetail, List<Trigger>>();
		
		
		
		
		
		
		
		JobDetail job = QuartzUtil.createJobDetail(
				TokenTimeOutJob.class,  GuidUtil.CreateGuid()+"_"+tokenEntity.getId(), tokenEntity.getId()+"_"+processInstanceId+"_"+tokenEntity.getProcessInstance().getProcessDefinitionId());
		job.getJobDataMap().put("tokenId", tokenEntity.getId());
		//job.getJobDataMap().put("transientVariableId", guidString);
		job.getJobDataMap().put("processInstanceId", processInstanceId);
		job.getJobDataMap().put("nodeId", event.getId());
		job.getJobDataMap().put("processKey", tokenEntity.getProcessInstance().getProcessDefinitionKey());
		job.getJobDataMap().put("processId", tokenEntity.getProcessInstance().getProcessDefinitionId());
		job.getJobDataMap().put("processName", tokenEntity.getProcessInstance().getProcessDefinition().getName());
		job.getJobDataMap().put("bizKey", tokenEntity.getProcessInstance().getBizKey());
		job.getJobDataMap().put("jobType", "fixTimeOutTask");
		
		
		if(date==null){
			
			if(expressionTemp!=null&&!expressionTemp.equals("")){
				Trigger trigger = null;
				trigger =  QuartzUtil.createCronTrigger(
						GuidUtil.CreateGuid(), "FixTimeOutTask_"+tokenEntity.getId(), expressionTemp);
				
				List<Trigger> triggers=new ArrayList<Trigger>();
				triggers.add(trigger);
				jobList.put(job, triggers);
				
			}else{
				
				if(timeObjects.size()>0){
					
					List<Trigger> triggers=new ArrayList<Trigger>();
					for (Object object : timeObjects) {
						if(object instanceof Date){
							Trigger trigger = null;
							
							
							
							trigger = (SimpleTrigger) QuartzUtil.createSimpleTrigger(
									GuidUtil.CreateGuid(), "FixTimeOutTask_"+tokenEntity.getId(), StringUtil.getDate(object));
							
							triggers.add(trigger);
							
						}
						if(object instanceof String){
							Trigger trigger = null;
							trigger =  QuartzUtil.createCronTrigger(
									GuidUtil.CreateGuid(), "FixTimeOutTask_"+tokenEntity.getId(), StringUtil.getString(object));
							triggers.add(trigger);
						}
					}
					jobList.put(job, triggers);
				}else{
					throw new FixFlowBizException("定时任务节点没有设置时间!");
				}
				
			
				
			}
			
			
			
			
		}else{
			
			
			Trigger trigger = null;
			
			
			
			trigger = (SimpleTrigger) QuartzUtil.createSimpleTrigger(
					GuidUtil.CreateGuid(), "FixTimeOutTask_"+tokenEntity.getId(), date);
			List<Trigger> triggers=new ArrayList<Trigger>();
			triggers.add(trigger);
			jobList.put(job, triggers);
		}
		
		
		
		
		//QuartzUtil.createCronTrigger(jobName, groupName, cronExpression);
		
		
		try {
			scheduler.scheduleJobs(jobList, true);//.scheduleJob(job, trigger);
		} catch (Exception e) {
			throw new FixFlowException("超时任务启动记录失败!错误信息: "+e.toString(), e);
		}
		
	
	}
	

}
