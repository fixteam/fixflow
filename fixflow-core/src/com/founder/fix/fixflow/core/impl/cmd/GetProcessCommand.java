package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.internationalization.FixFlowResources;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class GetProcessCommand implements Command<List<Map<String, Object>>>{

	protected String processInstanceId;
	
	public GetProcessCommand(String processInstanceId){
		this.processInstanceId=processInstanceId;
	}
	
	
	public List<Map<String, Object>> execute(CommandContext commandContext) {


		
		TaskService taskService = ProcessEngineManagement.getDefaultProcessEngine().getTaskService();
		String userId=Authentication.getAuthenticatedUserId();
		List<TaskInstance> taskInstances=taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(userId).taskIsEnd().list();

		
		List<Map<String, Object>> listMap=new ArrayList<Map<String,Object>>();
		
		
		
		List<TaskInstance> taskInstancesNotEndA=taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(userId).taskNotEnd().list();
		
		List<TaskInstance> taskInstancesNotEndC=taskService.createTaskQuery().processInstanceId(processInstanceId).taskCandidateUser(userId).taskNotEnd().list();
		
		Map<String, String> notEndTask=new HashMap<String, String>();
		//独占未完成
		for (TaskInstance taskInstance : taskInstancesNotEndA) {
			List<TaskCommandInst> taskCommandInsts=taskService.GetTaskCommandByTaskInstance(taskInstance,true);
			for (TaskCommandInst taskCommandInst : taskCommandInsts) {
			
				Map<String, Object> mapTemp=taskCommandInst.getPersistentState();
				if( mapTemp.get("type").equals("reminders")||mapTemp.get("type").equals("recover")||mapTemp.get("type").equals("processStatus")){
					mapTemp.put("taskId", taskInstance.getId());
					notEndTask.put( taskInstance.getNodeId(), taskInstance.getNodeId());
						listMap.add(mapTemp);
				}

			}
		}
		
		//共享未完成
		for (TaskInstance taskInstance : taskInstancesNotEndC) {
			List<TaskCommandInst> taskCommandInsts=taskService.GetTaskCommandByTaskInstance(taskInstance,true);
			for (TaskCommandInst taskCommandInst : taskCommandInsts) {
			
				Map<String, Object> mapTemp=taskCommandInst.getPersistentState();
				if( mapTemp.get("type").equals("reminders")||mapTemp.get("type").equals("recover")||mapTemp.get("type").equals("processStatus")){
					mapTemp.put("taskId", taskInstance.getId());
					notEndTask.put( taskInstance.getNodeId(), taskInstance.getNodeId());
						listMap.add(mapTemp);
				}
				

			}
		}
		
		
		Map<String, String> cfData=new HashMap<String, String>();
		
		for (TaskInstance taskInstance : taskInstances) {
			if(cfData.get(taskInstance.getNodeId())!=null){
				continue;
			}
			if(notEndTask.get(taskInstance.getNodeId())!=null){
				continue;
			}
			cfData.put(taskInstance.getNodeId(), taskInstance.getNodeId());
			List<TaskCommandInst> taskCommandInsts=taskService.GetTaskCommandByTaskInstance(taskInstance,true);
			for (TaskCommandInst taskCommandInst : taskCommandInsts) {
				
				Map<String, Object> mapTemp=taskCommandInst.getPersistentState();
				if( mapTemp.get("type").equals("reminders")||mapTemp.get("type").equals("recover")||mapTemp.get("type").equals("processStatus")){
					mapTemp.put("taskId", taskInstance.getId());
					
						listMap.add(mapTemp);
				}
			}
			
		}
		
		int x=0;
		for (Map<String, Object>  map : listMap) {
			if(map.get("type").equals("processStatus")){
				x=1;
				break;
			}
			
		}
		
		
		
		
		
		if(x==0){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", "processStatus");
			
			
			boolean booleanTemp = StringUtil.getBoolean(Context.getProcessEngineConfiguration().getInternationalizationConfig().getIsEnable());

			//用户名称国际化处理
			if (booleanTemp) {

				FixFlowResources fixFlowResources = Context.getProcessEngineConfiguration().getFixFlowResources();

				String nameTemp = fixFlowResources.getResourceName(FixFlowResources.TaskComandResource, "System_processStatus");
				if (nameTemp == null || nameTemp.equals("")) {
					map.put("name", "流程状态");
				} else {
					map.put("name", StringUtil.getString(nameTemp));
				}

			} else {
				map.put("name", "流程状态");
			}
			
			
			
			map.put("type", "processStatus");
			listMap.add(map);
		}

		return listMap;
		
		
	}

}
