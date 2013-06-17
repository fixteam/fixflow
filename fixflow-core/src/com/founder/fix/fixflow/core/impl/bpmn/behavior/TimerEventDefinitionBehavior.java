package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.Date;
import java.util.Map;

import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.impl.TimerEventDefinitionImpl;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.job.TokenTimeOutJob;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.QuartzUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.variable.VariableFlowTypeEntity;
import com.founder.fix.fixflow.core.impl.variable.VariableTransferEntity;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.variable.VariableFlowType;

public class TimerEventDefinitionBehavior extends TimerEventDefinitionImpl {
	
	public boolean execute(ExecutionContext executionContext,Event event) {
		
		
		Expression expression=this.getTimeDate();
		FormalExpression formalExpression=null;
		if(expression!=null){
			formalExpression=(FormalExpression)expression;
		}
		if(formalExpression==null){
			return false;
		}
		
		String expressionText=formalExpression.getBody();
		
		if(expressionText==null){
			throw new FixFlowException("超时表达式不能为空");
		}
		Date date=null;
		String expressionTemp=null;//"0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发 
		try {
			Object dateObj=ExpressionMgmt.execute(expressionText, executionContext);
			if(dateObj instanceof Date){
				date=(Date)dateObj;
			}
			else{
				expressionTemp=StringUtil.getString(dateObj);
			}
			
		} catch (Exception e) {
			throw new FixFlowException("超时表达式计算失败!原因是: "+e.toString(),e);
		}
		
		TokenEntity tokenEntity=executionContext.getToken();
		String processInstanceId=tokenEntity.getProcessInstance().getId();
		VariableTransferEntity variableTransferEntity = new VariableTransferEntity();
		Map<String, Object> transientVariableMap=tokenEntity.getProcessInstance().getContextInstance().getTransientVariableMap();
		String guidString=GuidUtil.CreateGuid();
		variableTransferEntity.addVariable(guidString, transientVariableMap);

		if (processInstanceId != null && !processInstanceId.equals("")) {
			VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.PROCESSINSTANCE, processInstanceId);
			variableTransferEntity.addVariableFlowType(variableFlowTypeEntity);
		}
	
		Context.getCommandContext().getVariableManager().saveVariable(variableTransferEntity);

		
	
		Scheduler scheduler=Context.getProcessEngineConfiguration().getScheduler();

		
		JobDetail job = QuartzUtil.createJobDetail(
				TokenTimeOutJob.class, tokenEntity.getId(), "FixTimeOutTask_"+tokenEntity.getId());

		
		
		Trigger trigger = null;
		
		if(date==null){
			trigger =  QuartzUtil.createCronTrigger(
					tokenEntity.getId(), "FixTimeOutTask_"+tokenEntity.getId(), expressionTemp);
		}else{
			trigger = (SimpleTrigger) QuartzUtil.createSimpleTrigger(
					tokenEntity.getId(), "FixTimeOutTask_"+tokenEntity.getId(), date);
		}
		
		
		
		
		//QuartzUtil.createCronTrigger(jobName, groupName, cronExpression);
		
		job.getJobDataMap().put("tokenId", tokenEntity.getId());
		job.getJobDataMap().put("transientVariableId", guidString);
		job.getJobDataMap().put("processInstanceId", processInstanceId);
		job.getJobDataMap().put("nodeId", event.getId());
		job.getJobDataMap().put("processKey", tokenEntity.getProcessInstance().getProcessDefinitionKey());
		job.getJobDataMap().put("processId", tokenEntity.getProcessInstance().getProcessDefinitionId());
		job.getJobDataMap().put("processName", tokenEntity.getProcessInstance().getProcessDefinition().getName());
		try {
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			throw new FixFlowException("超时任务启动记录失败!错误信息: "+e.toString(), e);
		}
		
		return false;
	}
	

}
