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
package com.founder.fix.fixflow.core.objkey;



public class TaskInstanceObjKey {
	

	public class FlowKeyTaskInstanceId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "id";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "TASKINSTANCE_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "taskInstanceId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "任务实例编号";
		}

	}
	
	
	
	
	
	public class FlowKeyCategory implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "category";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "CATEGORY";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "category";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "用户分类";
		}

	}
	
	public class FlowKeyAgent implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "agent";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "AGENT";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "agent";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "任务代理处理者";
		}

	}
	
	public class FlowKeyIsDraft implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "isDraft";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "ISDRAFT";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "isDraft";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "任务实例编号";
		}

	}
	
	//ISDRAFT
	
	
	
	public class FlowKeyProcessInstanceId implements ObjKeyInterface{

		public String EntityKey() {
			return "processInstanceId";
		}
		
	
		public String DataBaseKey() {
			return "PROCESSINSTANCE_ID";
		}
		
		public String FullKey() {
			return "processInstanceId";
		}

		public String KeyName() {
			return "任务实例编号";
		}

	}
	
	
	public class FlowKeyProcessDefinitionId implements ObjKeyInterface{

		public String EntityKey() {
			return "processDefinitionId";
		}
		
	
		public String DataBaseKey() {
			return "PROCESSDEFINITION_ID";
		}
		
		public String FullKey() {
			return "processDefinitionId";
		}

		public String KeyName() {
			return "流程定义唯一编号";
		}

	}
	
	
	public class FlowKeyVersion implements ObjKeyInterface{

		public String EntityKey() {
			return "version";
		}
		
	
		public String DataBaseKey() {
			return "VERSION";
		}
		
		public String FullKey() {
			return "version";
		}

		public String KeyName() {
			return "任务版本号";
		}

	}
	
	
	public class FlowKeyTokenId implements ObjKeyInterface{

		public String EntityKey() {
			return "tokenId";
		}
		
	
		public String DataBaseKey() {
			return "TOKEN_ID";
		}
		
		public String FullKey() {
			return "tokenId";
		}

		public String KeyName() {
			return "任务令牌号";
		}

	}
	
	
	public class FlowKeyNodeId implements ObjKeyInterface{

		public String EntityKey() {
			return "nodeId";
		}
		
	
		public String DataBaseKey() {
			return "NODE_ID";
		}
		
		public String FullKey() {
			return "nodeId";
		}

		public String KeyName() {
			return "节点编号";
		}

	}
	
	
	public class FlowKeyDescription implements ObjKeyInterface{

		public String EntityKey() {
			return "description";
		}
		
	
		public String DataBaseKey() {
			return "DESCRIPTION";
		}
		
		public String FullKey() {
			return "description";
		}

		public String KeyName() {
			return "任务主题";
		}

	}
	
	
	public class FlowKeyParentTaskInstanceId implements ObjKeyInterface{

		public String EntityKey() {
			return "parentTaskInstanceId";
		}
		
	
		public String DataBaseKey() {
			return "PARENTTASK_ID";
		}
		
		public String FullKey() {
			return "parentTaskInstanceId";
		}

		public String KeyName() {
			return "任务主题";
		}

	}
	
	
	
	public class FlowKeyAssignee implements ObjKeyInterface{

		public String EntityKey() {
			return "assignee";
		}
		
	
		public String DataBaseKey() {
			return "ASSIGNEE";
		}
		
		public String FullKey() {
			return "assignee";
		}

		public String KeyName() {
			return "任务代理人";
		}

	}
	
	public class FlowKeyClaimTime implements ObjKeyInterface{

		public String EntityKey() {
			return "claimTime";
		}
		
	
		public String DataBaseKey() {
			return "CLAIM_TIME";
		}
		
		public String FullKey() {
			return "claimTime";
		}

		public String KeyName() {
			return "领取时间";
		}

	}
	
	
	public class FlowKeyName implements ObjKeyInterface{

		public String EntityKey() {
			return "name";
		}
		
	
		public String DataBaseKey() {
			return "NAME";
		}
		
		public String FullKey() {
			return "name";
		}

		public String KeyName() {
			return "任务名称";
		}

	}
	
	

	public class FlowKeyCreateTime implements ObjKeyInterface{

		public String EntityKey() {
			return "createTime";
		}
		
	
		public String DataBaseKey() {
			return "CREATE_TIME";
		}
		
		public String FullKey() {
			return "createTime";
		}

		public String KeyName() {
			return "创建时间";
		}

	}
	
	
	public class FlowKeyStartTime implements ObjKeyInterface{

		public String EntityKey() {
			return "startTime";
		}
		
	
		public String DataBaseKey() {
			return "START_TIME";
		}
		
		public String FullKey() {
			return "startTime";
		}

		public String KeyName() {
			return "开始时间";
		}

	}

	
	public class FlowKeyIsBlocking implements ObjKeyInterface{

		public String EntityKey() {
			return "isBlocking";
		}
		
	
		public String DataBaseKey() {
			return "ISBLOCKING";
		}
		
		public String FullKey() {
			return "isBlocking";
		}

		public String KeyName() {
			return "是否阻塞";
		}

	}
	
	public class FlowKeyExpectedExecutionTime implements ObjKeyInterface{

		public String EntityKey() {
			return "expectedExecutionTime";
		}
		
	
		public String DataBaseKey() {
			return "EXPECTED_EXECUTIONTIME";
		}
		
		public String FullKey() {
			return "isBlocking";
		}

		public String KeyName() {
			return "预计执行时间";
		}

	}

	
	public class FlowKeyEndTime implements ObjKeyInterface{

		public String EntityKey() {
			return "endTime";
		}
		
	
		public String DataBaseKey() {
			return "END_TIME";
		}
		
		public String FullKey() {
			return "endTime";
		}

		public String KeyName() {
			return "结束时间";
		}

	}

	public class FlowKeyDueDate implements ObjKeyInterface{

		public String EntityKey() {
			return "dueDate";
		}
		
	
		public String DataBaseKey() {
			return "DUEDATE";
		}
		
		public String FullKey() {
			return "dueDate";
		}

		public String KeyName() {
			return "处理期限";
		}

	}

	
	public class FlowKeyPriority implements ObjKeyInterface{

		public String EntityKey() {
			return "priority";
		}
		
	
		public String DataBaseKey() {
			return "PRIORITY";
		}
		
		public String FullKey() {
			return "priority";
		}

		public String KeyName() {
			return "优先级别";
		}

	}
	
	

	
	
	public class FlowKeyOwner implements ObjKeyInterface{

		public String EntityKey() {
			return "owner";
		}
		
	
		public String DataBaseKey() {
			return "OWNER";
		}
		
		public String FullKey() {
			return "owner";
		}

		public String KeyName() {
			return "任务所有者";
		}

	}
	
	
	public class FlowKeyDelegationState implements ObjKeyInterface{

		public String EntityKey() {
			return "delegationState";
		}
		
	
		public String DataBaseKey() {
			return "DELEGATIONSTATESTRING";
		}
		
		public String FullKey() {
			return "delegationState";
		}

		public String KeyName() {
			return "任务代理状态";
		}

	}
	
	
	public class FlowKeyBizKey implements ObjKeyInterface{

		public String EntityKey() {
			return "bizKey";
		}
		
	
		public String DataBaseKey() {
			return "BIZKEY";
		}
		
		public String FullKey() {
			return "bizKey";
		}

		public String KeyName() {
			return "业务关联键值";
		}

	}

	public class FlowKeyCommandType implements ObjKeyInterface{

		public String EntityKey() {
			return "commandType";
		}
		
	
		public String DataBaseKey() {
			return "COMMAND_TYPE";
		}
		
		public String FullKey() {
			return "commandType";
		}

		public String KeyName() {
			return "处理命令类型";
		}

	}
	
	public class FlowKeyCommandId implements ObjKeyInterface{

		public String EntityKey() {
			return "commandId";
		}
		
	
		public String DataBaseKey() {
			return "COMMAND_ID";
		}
		
		public String FullKey() {
			return "commandId";
		}

		public String KeyName() {
			return "处理命令编号";
		}

	}
	
	
	public class FlowKeyCommandMessage implements ObjKeyInterface{

		public String EntityKey() {
			return "commandMessage";
		}
		
	
		public String DataBaseKey() {
			return "COMMAND_MESSAGE";
		}
		
		public String FullKey() {
			return "commandMessage";
		}

		public String KeyName() {
			return "处理命令内容";
		}

	}
	
	public class FlowKeyTaskComment implements ObjKeyInterface{

		public String EntityKey() {
			return "taskComment";
		}
		
	
		public String DataBaseKey() {
			return "TASK_COMMENT";
		}
		
		public String FullKey() {
			return "taskComment";
		}

		public String KeyName() {
			return "";
		}

	}
	
	
	
	public class FlowKeyNodeName implements ObjKeyInterface{

		public String EntityKey() {
			return "nodeName";
		}
		
	
		public String DataBaseKey() {
			return "NODE_NAME";
		}
		
		public String FullKey() {
			return "nodeName";
		}

		public String KeyName() {
			return "节点名称";
		}

	}
	
	
	public class FlowKeyProcessDefinitionKey implements ObjKeyInterface{

		public String EntityKey() {
			return "processDefinitionKey";
		}
		
	
		public String DataBaseKey() {
			return "PROCESSDEFINITION_KEY";
		}
		
		public String FullKey() {
			return "processDefinitionKey";
		}

		public String KeyName() {
			return "流程定义编号";
		}

	}
	
	
	
	
	
	

	
	public class FlowKeyFormUri implements ObjKeyInterface{

		public String EntityKey() {
			return "formUri";
		}
		
	
		public String DataBaseKey() {
			return "FORMURI";
		}
		
		public String FullKey() {
			return "formUri";
		}

		public String KeyName() {
			return "操作表单地址";
		}

	}
	
	public class FlowKeyFormUriView implements ObjKeyInterface{

		public String EntityKey() {
			return "formUriView";
		}
		
	
		public String DataBaseKey() {
			return "FORMURIVIEW";
		}
		
		public String FullKey() {
			return "formUriView";
		}

		public String KeyName() {
			return "浏览表单地址";
		}

	}
	
	public class FlowKeyAdmin implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "admin";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "ADMIN";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "admin";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "管理员处理";
		}

	}
	
	
	public class FlowKeyTaskGroup implements ObjKeyInterface{

		public String EntityKey() {
			return "taskGroup";
		}
		
	
		public String DataBaseKey() {
			return "TASKGROUP";
		}
		
		public String FullKey() {
			return "taskGroup";
		}

		public String KeyName() {
			return "会签任务组编号";
		}

	}
	
	
	
	
	public class FlowKeyHasEnded implements ObjKeyInterface{

		public String EntityKey() {
			return "hasEnded";
		}
		
	
		public String DataBaseKey() {
			return "HASENDED";
		}
		
		public String FullKey() {
			return "hasEnded";
		}

		public String KeyName() {
			return "是否结束";
		}

	}
	
	public class FlowKeyTaskInstanceType implements ObjKeyInterface{

		public String EntityKey() {
			return "taskInstanceType";
		}
		
	
		public String DataBaseKey() {
			return "TASKTYPE";
		}
		
		public String FullKey() {
			return "taskInstanceType";
		}

		public String KeyName() {
			return "任务类型";
		}

	}
	
	
	public class FlowKeyProcessDefinitionName implements ObjKeyInterface{

		public String EntityKey() {
			return "processDefinitionName";
		}
		
	
		public String DataBaseKey() {
			return "PROCESSDEFINITION_NAME";
		}
		
		public String FullKey() {
			return "processDefinitionName";
		}

		public String KeyName() {
			return "流程定义名称";
		}

	}
	
	
	
	public class FlowKeyIsCancelled implements ObjKeyInterface{
		
		public String EntityKey() {
			return "isCancelled";
		}
		
	
		public String DataBaseKey() {
			return "ISCANCELLED";
		}
		
		public String FullKey() {
			return "isCancelled";
		}

		public String KeyName() {
			return "是否取消";
		}

	}
	
	

	public class FlowKeyIsSuspended implements ObjKeyInterface{
		
		public String EntityKey() {
			return "isSuspended";
		}
		
	
		public String DataBaseKey() {
			return "ISSUSPENDED";
		}
		
		public String FullKey() {
			return "isSuspended";
		}

		public String KeyName() {
			return "是否暂停";
		}

	}
	
	
	public class FlowKeyIsOpen implements ObjKeyInterface{
		
		public String EntityKey() {
			return "isOpen";
		}
		
	
		public String DataBaseKey() {
			return "ISOPEN";
		}
		
		public String FullKey() {
			return "isOpen";
		}

		public String KeyName() {
			return "是否打开";
		}

	}
	
	public class FlowKeyPendingTaskId implements ObjKeyInterface{
		
		public String EntityKey() {
			return "pendingTaskId";
		}
		
	
		public String DataBaseKey() {
			return "PENDINGTASKID";
		}
		
		public String FullKey() {
			return "pendingTaskId";
		}

		public String KeyName() {
			return "转办任务编号";
		}

	}
	
	
	//CALLACTIVITY_INSTANCE_ID
	public class FlowKeyCallActivityInstanceId implements ObjKeyInterface{
		
		public String EntityKey() {
			return "callActivityInstanceId";
		}
		
	
		public String DataBaseKey() {
			return "CALLACTIVITY_INSTANCE_ID";
		}
		
		public String FullKey() {
			return "callActivityInstanceId";
		}

		public String KeyName() {
			return "调用子流程时生成的子流程实例号，用于子流程结束的时候关闭这个任务";
		}

	}
	
	
	/**
	 * 获取任务编号
	 * @return
	 */
	public static FlowKeyTaskInstanceId TaskInstanceId(){
		return new TaskInstanceObjKey().new FlowKeyTaskInstanceId();
	}
	
	/**
	 * 获取流程实例编号
	 * @return
	 */
	public static FlowKeyProcessInstanceId ProcessInstanceId(){
		return new TaskInstanceObjKey().new FlowKeyProcessInstanceId();
	}
	
	/**
	 * 获取流程定义唯一编号
	 * @return
	 */
	public static FlowKeyProcessDefinitionId ProcessDefinitionId(){
		return new TaskInstanceObjKey().new FlowKeyProcessDefinitionId();
	}

	
	

	
	/**
	 * 获取流程定义唯一编号
	 * @return
	 */
	public static FlowKeyVersion Version(){
		return new TaskInstanceObjKey().new FlowKeyVersion();
	}
	
	
	/**
	 * 任务令牌号
	 * @return
	 */
	public static FlowKeyTokenId TokenId(){
		return new TaskInstanceObjKey().new FlowKeyTokenId();
	}

	
	/**
	 * 节点编号
	 * @return
	 */
	public static FlowKeyNodeId NodeId(){
		return new TaskInstanceObjKey().new FlowKeyNodeId();
	}
	
	/**
	 * 任务主题
	 * @return
	 */
	public static FlowKeyDescription Description(){
		return new TaskInstanceObjKey().new FlowKeyDescription();
	}
	
	
	/**
	 * 父任务编号
	 * @return
	 */
	public static FlowKeyParentTaskInstanceId ParentTaskInstanceId(){
		return new TaskInstanceObjKey().new FlowKeyParentTaskInstanceId();
	}
	
	
	/**
	 * 任务代理人
	 * @return
	 */
	public static FlowKeyAssignee Assignee(){
		return new TaskInstanceObjKey().new FlowKeyAssignee();
	}
	
	/**
	 * 任务领取时间
	 * @return
	 */
	public static FlowKeyClaimTime ClaimTime(){
		return new TaskInstanceObjKey().new FlowKeyClaimTime();
	}
	
	
	
	/**
	 * 任务的名称
	 * @return
	 */
	public static FlowKeyName Name(){
		return new TaskInstanceObjKey().new FlowKeyName();
	}
	
	

	/**
	 * 创建时间
	 * @return
	 */
	public static FlowKeyCreateTime CreateTime(){
		return new TaskInstanceObjKey().new FlowKeyCreateTime();
	}
	
	
	/**
	 * 开始时间
	 * @return
	 */
	public static FlowKeyStartTime StartTime(){
		return new TaskInstanceObjKey().new FlowKeyStartTime();
	}
	
	
	/**
	 * 是否阻塞
	 * @return
	 */
	public static FlowKeyIsBlocking IsBlocking(){
		return new TaskInstanceObjKey().new FlowKeyIsBlocking();
	}
	
	/**
	 * 结束时间
	 * @return
	 */
	public static FlowKeyEndTime EndTime(){
		return new TaskInstanceObjKey().new FlowKeyEndTime();
	}
	
	
	/**
	 * 处理期限
	 * @return
	 */
	public static FlowKeyDueDate DueDate(){
		return new TaskInstanceObjKey().new FlowKeyDueDate();
	}
	
	
	/**
	 * 有限级别
	 * @return
	 */
	public static FlowKeyPriority Priority(){
		return new TaskInstanceObjKey().new FlowKeyPriority();
	}
	
	
	/**
	 * 任务分类
	 * @return
	 */
	public static FlowKeyCategory Category(){
		return new TaskInstanceObjKey().new FlowKeyCategory();
	}
	
	
	
	/**
	 * 任务所有者
	 * @return
	 */
	public static FlowKeyOwner Owner(){
		return new TaskInstanceObjKey().new FlowKeyOwner();
	}
	
	
	/**
	 * 任务代理状态
	 * @return
	 */
	public static FlowKeyDelegationState DelegationState(){
		return new TaskInstanceObjKey().new FlowKeyDelegationState();
	}
	
	
	/**
	 * 业务关联键值
	 * @return
	 */
	public static FlowKeyBizKey BizKey(){
		return new TaskInstanceObjKey().new FlowKeyBizKey();
	}
	
	
	/**
	 * 处理命令编号
	 * @return
	 */
	public static FlowKeyCommandId CommandId(){
		return new TaskInstanceObjKey().new FlowKeyCommandId();
	}
	/**
	 * 处理命令类型
	 * @return
	 */
	public static FlowKeyCommandType CommandType(){
		return new TaskInstanceObjKey().new FlowKeyCommandType();
	}
	
	
	/**
	 * 处理命令内容
	 * @return
	 */
	public static FlowKeyCommandMessage CommandMessage(){
		return new TaskInstanceObjKey().new FlowKeyCommandMessage();
	}
	
	/**
	 * 代理处理者
	 * @return
	 */
	public static FlowKeyAgent Agent(){
		return new TaskInstanceObjKey().new FlowKeyAgent();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static FlowKeyTaskComment TaskComment(){
		return new TaskInstanceObjKey().new FlowKeyTaskComment();
	}
	
	
	/**
	 * 节点名称
	 * @return
	 */
	public static FlowKeyNodeName NodeName(){
		return new TaskInstanceObjKey().new FlowKeyNodeName();
	}
	
	/**
	 * 节点名称
	 * @return
	 */
	public static FlowKeyProcessDefinitionKey ProcessDefinitionKey(){
		return new TaskInstanceObjKey().new FlowKeyProcessDefinitionKey();
	}
	
	
	
	/**
	 * 操作表单地址
	 * @return
	 */
	public static FlowKeyFormUri FormUri(){
		return new TaskInstanceObjKey().new FlowKeyFormUri();
	}

	/**
	 * 浏览表单地址
	 * @return
	 */
	public static FlowKeyFormUriView FormUriView(){
		return new TaskInstanceObjKey().new FlowKeyFormUriView();
	}
	
	/**
	 * 会签任务分组编号
	 * @return
	 */
	public static FlowKeyTaskGroup TaskGroup(){
		return new TaskInstanceObjKey().new FlowKeyTaskGroup();
	}
	

	/**
	 * 任务类型
	 * @return
	 */
	public static FlowKeyTaskInstanceType TaskInstanceType(){
		
		return new TaskInstanceObjKey().new FlowKeyTaskInstanceType();
	}

	
	/**
	 * 流程定义名称
	 * @return
	 */
	public static FlowKeyProcessDefinitionName ProcessDefinitionName(){
		
		return new TaskInstanceObjKey().new FlowKeyProcessDefinitionName();
	}
	
	

	/**
	 * 是否取消
	 * @return
	 */
	public static FlowKeyIsCancelled IsCancelled(){
		
		return new TaskInstanceObjKey().new FlowKeyIsCancelled();
	}

	/**
	 * 是否暂停
	 * @return
	 */
	public static FlowKeyIsSuspended IsSuspended(){
		
		return new TaskInstanceObjKey().new FlowKeyIsSuspended();
	}


	/**
	 * 是否打开
	 * @return
	 */
	public static FlowKeyIsOpen IsOpen(){
		
		return new TaskInstanceObjKey().new FlowKeyIsOpen();
	}
	
	
	/**
	 * 是否结束
	 * @return
	 */
	public static FlowKeyHasEnded HasEnded(){
		
		return new TaskInstanceObjKey().new FlowKeyHasEnded();
	}
	
	
	/**
	 * 是否草稿
	 * @return
	 */
	public static FlowKeyIsDraft IsDraft(){
		
		return new TaskInstanceObjKey().new FlowKeyIsDraft();
	}
	
	
	/**
	 * 预计执行时间
	 * @return
	 */
	public static FlowKeyExpectedExecutionTime ExpectedExecutionTime(){
		
		return new TaskInstanceObjKey().new FlowKeyExpectedExecutionTime();
	}
	
	/**
	 * 管理员处理字段
	 * @return
	 */
	public static FlowKeyAdmin Admin(){
		
		return new TaskInstanceObjKey().new FlowKeyAdmin();
	}
	
	
	/**
	 * 调用节点人物记录编号
	 * @return
	 */
	public static FlowKeyCallActivityInstanceId CallActivityInstanceId(){
		
		return new TaskInstanceObjKey().new FlowKeyCallActivityInstanceId();
	}
	
	
	/**
	 * 转办任务编号
	 * @return
	 */
	public static FlowKeyPendingTaskId PendingTaskId(){
		
		return new TaskInstanceObjKey().new FlowKeyPendingTaskId();
	}
	
	/**
	 * 任务实例表名
	 * @return
	 */
	public static String TaskInstanceTableName(){
		return "FIXFLOW_RUN_TAKSINSTANECE";
	}
	
}
