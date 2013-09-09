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
package com.founder.fix.fixflow.shell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.IdentityService;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceType;
import com.founder.fix.fixflow.core.task.IdentityLink;
import com.founder.fix.fixflow.core.task.TaskInstance;

/**
 * 流程工具类
 * @author Administrator
 *
 */
public class FlowUtilServiceImpl extends CommonServiceImpl {

	/**
	 * 获得实例的当前处理信息
	 * 
	 * @param taskInstanceQueryTo
	 * @return 例如 "人工任务(共享角色:功能角色)(共享部门:平台产品部,财务部)"
	 */
	public String getShareTaskNowNodeInfo(TaskInstance taskInstanceQueryTo) {
		if(taskInstanceQueryTo.getEndTime()==null){
			try {
				return processState(taskInstanceQueryTo);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		else{
			String processInstanceId = taskInstanceQueryTo.getProcessInstanceId();
			return getShareTaskNowNodeInfo(processInstanceId);
		}
		return null;
	}

	/**
	 * @param processInstanceId
	 * @return
	 */
	public String getShareTaskNowNodeInfo(String processInstanceId) {
		try {
			String taskInfo = "";
			ProcessEngine engine = getProcessEngine(null);
			ProcessInstance processInstanceQueryTo = engine.getRuntimeService().getProcessInstance(processInstanceId);
			if (processInstanceQueryTo.getEndTime() != null) {
				if(processInstanceQueryTo.getInstanceType().equals(ProcessInstanceType.COMPLETE)){
					return "完成";
				}else{
					return "已终止";
				}
			}
			List<TaskInstance> taskInstanceQueryTos = new ArrayList<TaskInstance>();
			taskInstanceQueryTos =engine.getTaskService().createTaskQuery().processInstanceId(processInstanceId).taskNotEnd().list();
			for (TaskInstance taskInstanceQueryTo2 : taskInstanceQueryTos) {
				if(taskInfo.equals("")&&taskInstanceQueryTos.size()==1){
					taskInfo=taskInfo+processState(taskInstanceQueryTo2);
				}
				else{
					taskInfo=taskInfo+"<div>"+processState(taskInstanceQueryTo2)+"</div>";
				}
			}
			return taskInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private String processState(TaskInstance taskInstanceQueryTo) throws Exception{
		String taskInfo="";
		String assignee = taskInstanceQueryTo.getAssignee();
		ProcessEngine engine = getProcessEngine(null);
		ProcessDefinitionBehavior processDefinition = engine.getModelService().getProcessDefinition(taskInstanceQueryTo.getProcessDefinitionId());
		String nodeName = processDefinition.getFlowElement(taskInstanceQueryTo.getNodeId()).getName();
		taskInfo = taskInfo + nodeName;
		IdentityService identityService = engine.getIdentityService();
		if (assignee == null) {
			List<UserTo> userTos=new ArrayList<UserTo>();
			Map<String, List<GroupTo>> groupTosMap=new HashMap<String, List<GroupTo>>();
			List<IdentityLink> identityLinkList = taskInstanceQueryTo.getIdentityLinkQueryToList();
			for (IdentityLink identityLinkQueryTo : identityLinkList) {
				String userId = identityLinkQueryTo.getUserId();
				if (userId == null) {
					String groupTypeId = identityLinkQueryTo.getGroupType();
					String groupId = identityLinkQueryTo.getGroupId();
					GroupTo groupTo = identityService.getGroup(groupId, groupTypeId);
					if (groupTo == null) {
						continue;
					}
					if(groupTosMap.get(groupTypeId)!=null){
						groupTosMap.get(groupTypeId).add(groupTo);
					}
					else{
						List<GroupTo> groupTos=new ArrayList<GroupTo>();
						groupTos.add(groupTo);
						groupTosMap.put(groupTypeId, groupTos);
					}
					
				} else {
					UserTo userTo=null;
					if (userId.equals("fixflow_allusers")) {
						userTo=new UserTo("fixflow_allusers", "所有人", null);
					} else {
						userTo= identityService.getUserTo(userId);
					}
					if(userTo!=null){
						userTos.add(userTo);
					}
				}
			}
			if(userTos.size()>0){
				String groupTypeName="";
				groupTypeName = "用户";
				taskInfo += "(共享 " + groupTypeName + " : ";
				for (int i = 0; i < userTos.size(); i++) {
					UserTo userTo=userTos.get(i);
					if(i==userTos.size()-1){
						taskInfo += userTo.getUserName();
					}
					else{
						taskInfo += userTo.getUserName()+",";
					}
				}
				taskInfo=taskInfo+")";
			}
			for (String groupToKey : groupTosMap.keySet()) {
				List<GroupTo> groupTos=groupTosMap.get(groupToKey);
				GroupDefinition groupDefinition = identityService.getGroupDefinition(groupToKey);
				String groupTypeName = "";
				groupTypeName = groupDefinition.getName();
				taskInfo += "(共享 " + groupTypeName + " : ";
				taskInfo += listToStr(groupTos, ",",groupDefinition) + ")";
			}
		} else {
			UserTo user = identityService.getUserTo(assignee);
			String username = user.getUserName();
			username="<span title='"+username+"("+assignee+")'>"+username+"</span>";
			taskInfo = taskInfo + " (处理者 ： " + username + ") ";
		}
		return taskInfo;
	}
	
	public static String listToStr(List<GroupTo> groupTos, String joinChar,GroupDefinition groupDefinition){
		if(groupTos==null||groupTos.size()==0|| joinChar == null){
			return "";
		}
		String listStr = "";
		for(GroupTo groupTo : groupTos){
			List<UserTo> userTos=groupDefinition.findUserChildMembersIncludeByGroupId(groupTo.getGroupId());
			String nameList="";
			int x=0;
			int y=5;
			if(userTos.size()>y){
				userTos=userTos.subList(0, y);
				x=1;
			}
			for (int i = 0; i < userTos.size(); i++) {
				UserTo userTo=userTos.get(i);
				if(i==userTos.size()-1){
					nameList=nameList+userTo.getUserName()+"("+userTo.getUserId()+")";
				}
				else{
					nameList=nameList+userTo.getUserName()+"("+userTo.getUserId()+"),  ";
				}
			}
			if(x==1){
				nameList=nameList+" .......";
			}
			listStr = listStr+"<span title='"+nameList+"'>"+groupTo.getGroupName()+"</span>"+joinChar;
		}
		listStr = listStr.substring(0, listStr.length()- joinChar.length());
		return listStr;
	}
	
	/**
	 * 获取流程运行状态
	 * @param status
	 * @return
	 */
	public static ProcessInstanceType getInstanceStaus(String status){
		if("运行中".equals(status)){
			return ProcessInstanceType.RUNNING;
		}else if("暂停".equals(status)){
			return ProcessInstanceType.SUSPEND;
		}else if("完成".equals(status)){
			return ProcessInstanceType.COMPLETE;
		}else if("终止".equals(status)){
			return ProcessInstanceType.TERMINATION;
		}
		return null;
	}

}
