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

import com.founder.fix.bpmn2extensions.sqlmappingconfig.DataBaseTable;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.database.DataBaseTableEnum;
import com.founder.fix.fixflow.core.runtime.QueryLocation;



public class ProcessInstanceObjKey {

	/**
	 * 查询类型
	 * @param tableType 0或null查运行表，1查历史表 2查历史和run表
	 * @return
	 */
	public static String getTableName(QueryLocation queryLocation){
		String tableName = "";
		if(QueryLocation.HIS.equals(queryLocation)){
			tableName =  ProcessInstanceHisTableName();
		}else if(QueryLocation.RUN_HIS.equals(queryLocation)){
			String runColumnName = "*";
			String hisColumnName = "*";
			DataBaseTable runTable = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getDataBaseTableConfig(DataBaseTableEnum.fixflow_run_processinstance);
			DataBaseTable hisTable = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getDataBaseTableConfig(DataBaseTableEnum.fixflow_his_processinstance);
			if(runTable !=null){
				runColumnName = runTable.getColumnValue();
			}
			if(hisTable != null){
				hisColumnName = hisTable.getColumnValue();
			}
			tableName = "(select "+runColumnName+" from "+ProcessInstanceTableName()+" union all select "+hisColumnName+" from "+ProcessInstanceHisTableName()+")";
		}else{
			tableName = ProcessInstanceTableName();
		}
		return tableName;
	}
	
	/**
	 * 获取流程实例表名
	 * @return
	 */
	public static String ProcessInstanceTableName(){
		DataBaseTable table = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getDataBaseTableConfig(DataBaseTableEnum.fixflow_run_processinstance);
		if(table != null){
			return table.getTableValue();
		}
		return "FIXFLOW_RUN_PROCESSINSTANCE";
		
	}
	
	/**
	 * 获取流程实例归档表明
	 * @return
	 */
	public static String ProcessInstanceHisTableName(){
		DataBaseTable table = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getDataBaseTableConfig(DataBaseTableEnum.fixflow_his_processinstance);
		if(table != null){
			return table.getTableValue();
		}
		return "FIXFLOW_HIS_PROCESSINSTANCE";
	}
	
	
	/**
	 * 编号
	 * @return
	 */
	public static FlowKeyProcessInstanceId ProcessInstanceId(){
		return new ProcessInstanceObjKey().new FlowKeyProcessInstanceId();
	}
	
	public class FlowKeyProcessInstanceId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "id";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "PROCESSINSTANCE_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "processInstanceId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "编号";
		}

	}
	
	
	/**
	 * 编号
	 * @return
	 */
	public static FlowKeyProcessLocation ProcessLocation(){
		return new ProcessInstanceObjKey().new FlowKeyProcessLocation();
	}
	
	public class FlowKeyProcessLocation implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "processLocation";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "PROCESSLOCATION";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "processLocation";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "流程位置";
		}

	}
	
	
	
	
	
	/**
	 * 流程定义唯一编号
	 * @return
	 */
	public static FlowKeyProcessDefinitionId ProcessDefinitionId(){
		return new ProcessInstanceObjKey().new FlowKeyProcessDefinitionId();
	}
	
	public class FlowKeyProcessDefinitionId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "processDefinitionId";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "PROCESSDEFINITION_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "processDefinitionId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "流程定义唯一编号";
		}

	}

	
	
	
	
	
	/**
	 * 流程实例主题
	 * @return
	 */
	public static FlowKeySubject Subject(){
		return new ProcessInstanceObjKey().new FlowKeySubject();
	}
	
	public class FlowKeySubject implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "subject";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "SUBJECT";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "subject";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "流程实例主题";
		}

	}

	
	
	
	/**
	 * 启动时间
	 * @return
	 */
	public static FlowKeyStartTime StartTime(){
		return new ProcessInstanceObjKey().new FlowKeyStartTime();
	}
	
	public class FlowKeyStartTime implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "startTime";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "START_TIME";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "startTime";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "启动时间";
		}

	}
	
	
	
	
	/**
	 * 结束时间
	 * @return
	 */
	public static FlowKeyEndTime EndTime(){
		return new ProcessInstanceObjKey().new FlowKeyEndTime();
	}
	
	public class FlowKeyEndTime implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "endTime";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "END_TIME";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "endTime";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "结束时间";
		}

	}
	
	
	
	/**
	 * 业务定义编号
	 * @return
	 */
	public static FlowKeyDefinitionId DefinitionId(){
		return new ProcessInstanceObjKey().new FlowKeyDefinitionId();
	}
	
	public class FlowKeyDefinitionId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "definitionId";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "DEFINITION_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "definitionId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "业务定义编号";
		}

	}
	
	
	
	

	/**
	 * 根令牌编号
	 * @return
	 */
	public static FlowKeyRootTokenId RootTokenId(){
		return new ProcessInstanceObjKey().new FlowKeyRootTokenId();
	}
	
	public class FlowKeyRootTokenId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "rootTokenId";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "ROOTTOKEN_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "rootTokenId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "根令牌编号";
		}

	}
	
	
	
	
	/**
	 * 业务关联键值
	 * @return
	 */
	public static FlowKeyBizKey BizKey(){
		return new ProcessInstanceObjKey().new FlowKeyBizKey();
	}
	
	public class FlowKeyBizKey implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "bizKey";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "BIZ_KEY";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "bizKey";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "业务关联键值";
		}

	}
	
	
	
	/**
	 * 提交人
	 * @return
	 */
	public static FlowKeyInitiator Initiator(){
		return new ProcessInstanceObjKey().new FlowKeyInitiator();
	}
	
	public class FlowKeyInitiator implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "initiator";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "INITIATOR";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "initiator";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "提交人";
		}

	}
	
	
	/**
	 * 启动人
	 * @return
	 */
	public static FlowKeyStartAuthor StartAuthor(){
		return new ProcessInstanceObjKey().new FlowKeyStartAuthor();
	}
	
	public class FlowKeyStartAuthor implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "startAuthor";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "START_AUTHOR";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "startAuthor";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "启动人";
		}

	}
	
	
	
	/**
	 * 是否暂停
	 * @return
	 */
	public  static FlowKeyIsSuspended IsSuspended(){
		return new ProcessInstanceObjKey().new FlowKeyIsSuspended();
	}
	
	public class FlowKeyIsSuspended implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "isSuspended";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "ISSUSPENDED";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "isSuspended";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "是否暂停";
		}

	}
	
	
	
	/**
	 * 流程定义编号
	 * @return
	 */
	public  static FlowKeyProcessDefinitionKey ProcessDefinitionKey(){
		return new ProcessInstanceObjKey().new FlowKeyProcessDefinitionKey();
	}
	
	public class FlowKeyProcessDefinitionKey implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "processDefinitionKey";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "PROCESSDEFINITION_KEY";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "processDefinitionKey";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "流程定义编号";
		}

	}
	
	
	
	/**
	 * 父流程实例编号
	 * @return
	 */
	public  static FlowKeyParentProcessInstanceId ParentProcessInstanceId(){
		return new ProcessInstanceObjKey().new FlowKeyParentProcessInstanceId();
	}
	
	public class FlowKeyParentProcessInstanceId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "parentProcessInstanceId";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "PARENT_INSTANCE_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "parentProcessInstanceId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "父流程实例编号";
		}

	}
	
	
	
	
	/**
	 * 父流程实例令牌编号
	 * @return
	 */
	public static FlowKeyParentProcessInstanceTokenId ParentProcessInstanceTokenId(){
		return new ProcessInstanceObjKey().new FlowKeyParentProcessInstanceTokenId();
	}
	
	public class FlowKeyParentProcessInstanceTokenId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "parentProcessInstanceTokenId";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "PARENT_INSTANCE_TOKEN_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "parentProcessInstanceTokenId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "父流程实例令牌编号";
		}

	}
	
	/**
	 * 实例状态
	 * @return
	 */
	public static FlowKeyInstanceStatus InstanceStatus(){
		return new ProcessInstanceObjKey().new FlowKeyInstanceStatus();
	}
	
	public class FlowKeyInstanceStatus implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "instanceStatus";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "INSTANCE_STATUS";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "instanceStatus";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "实例状态";
		}

	}
	
	/**
	 * 更新时间
	 * @return
	 */
	public static FlowKeyUpdateTime UpdateTime(){
		return new ProcessInstanceObjKey().new FlowKeyUpdateTime();
	}
	
	public class FlowKeyUpdateTime implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "updateTime";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "UPDATE_TIME";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "updateTime";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "更新时间";
		}

	}
	
	/**
	 * 归档时间
	 * @return
	 */
	public static FlowKeyArchiveTime ArchiveTime(){
		return new ProcessInstanceObjKey().new FlowKeyArchiveTime();
	}
	
	public class FlowKeyArchiveTime implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "archive_time";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "ARCHIVE_TIME";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "archiveTime";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "归档时间";
		}

	}
	
}
