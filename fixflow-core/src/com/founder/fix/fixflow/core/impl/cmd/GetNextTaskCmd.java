package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.Token;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class GetNextTaskCmd implements Command<List<TaskInstance>>{
	
	protected String taskId;
	protected String processInstanceId;
	
	public GetNextTaskCmd(String taskId,String processInstanceId){
		this.taskId=taskId;
		this.processInstanceId=processInstanceId;
	}
	
	
	public List<TaskInstance> execute(CommandContext commandContext) {
		// TODO 自动生成的方法存根
		
		
		if(taskId==null||taskId.equals("")){
			
			if(processInstanceId==null||processInstanceId.equals("")){
				throw new FixFlowBizException("模拟执行的流程实例编号不能为空!");
			}
			
			ProcessInstance processInstance=commandContext.getProcessInstanceManager().findProcessInstanceById(processInstanceId);
			if(processInstance==null){
				throw new FixFlowBizException("流程实例没找到");
			}
			ProcessInstanceEntity processInstanceEntity=(ProcessInstanceEntity)processInstance;
			
			
			List<TaskInstanceEntity> taskInstanceEntities= processInstanceEntity.getTaskMgmtInstance().getTaskInstanceEntitys();
			List<TaskInstance> taskInstances=new ArrayList<TaskInstance>();
			for (TaskInstanceEntity taskInstanceEntity : taskInstanceEntities) {
				if(!taskInstanceEntity.hasEnded()){
					taskInstances.add(taskInstanceEntity);
				}
				
			}
			return taskInstances;
			
		}
		else{
			
			
			if(taskId==null||taskId.equals("")){
				throw new FixFlowBizException("模拟执行的任务编号不能为空!");
			}
			
			
			
			
			
			
			TaskInstance taskInstance=commandContext.getTaskManager().findTaskById(taskId);
			
		
			
			
			if(taskInstance==null){
				throw new FixFlowBizException("模拟执行的任务无法找到!");
			}
			
			String tokenIdString=taskInstance.getTokenId();
			
			
			if(!taskInstance.hasEnded()){
				throw new FixFlowBizException("模拟执行的当前任务必须已经结束!");
			}
			
			ProcessInstance processInstance=commandContext.getProcessInstanceManager().findProcessInstanceById(taskInstance.getProcessInstanceId());
			
			if(processInstance==null){
				throw new FixFlowBizException("未能找到任务对应的流程实例");
			}
			ProcessInstanceEntity processInstanceEntity=(ProcessInstanceEntity)processInstance;
			TokenEntity tokenEntity=processInstanceEntity.getTokenMap().get(tokenIdString);
			
			
			
			
			List<TaskInstanceEntity> taskInstanceEntities= processInstanceEntity.getTaskMgmtInstance().getTaskInstanceEntitys();
			List<TaskInstance> taskInstances=new ArrayList<TaskInstance>();
			for (TaskInstanceEntity taskInstanceEntity : taskInstanceEntities) {
				if(!taskInstanceEntity.hasEnded()){
					String nextTokenId=taskInstanceEntity.getTokenId();
					if(tokenIdString.equals(nextTokenId)){
						taskInstances.add(taskInstanceEntity);
					}else{
						
						//分支走到合并的时候的处理.
						if(tokenEntity==null){
							if(taskInstanceEntity.getCreateTime().compareTo(taskInstance.getCreateTime())>=0){
								taskInstances.add(taskInstanceEntity);
							}
						}
						else{
							Token tokenObj=tokenEntity.getChildren().get(nextTokenId);
							if(tokenObj!=null){
								taskInstances.add(taskInstanceEntity);
							}
						}
						
						
						
					}
					//tokenEntity.getChild(taskInstanceEntity.getTokenId())
					
				}
			}
			

			
			
			
			return taskInstances;
		}
		
		
		
	}

}
