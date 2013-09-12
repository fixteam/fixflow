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


import com.founder.fix.bpmn2extensions.coreconfig.DataBaseTable;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.database.DataBaseTableEnum;
import com.founder.fix.fixflow.core.runtime.QueryLocation;


public class VariableObjKey {

	/**
	 * 查询类型
	 * @param tableType 0或null查运行表，1查历史表 2查历史和run表
	 * @return
	 */
	public static String getTableName(QueryLocation queryLocation){
		String tableName = "";
		if(QueryLocation.HIS.equals(queryLocation)){
			tableName =  VariableHisTableName();
		}else if(QueryLocation.RUN_HIS.equals(queryLocation)){
			String runColumnName = "*";
			String hisColumnName = "*";
			DataBaseTable runTable = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getDataBaseTableConfig(DataBaseTableEnum.fixflow_run_variable);
			DataBaseTable hisTable = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getDataBaseTableConfig(DataBaseTableEnum.fixflow_his_variable);
			if(runTable !=null){
				runColumnName = runTable.getColumnValue();
			}
			if(hisTable != null){
				hisColumnName = hisTable.getColumnValue();
			}
			tableName = "(select "+runColumnName+" from "+VariableTableName()+" union all select "+hisColumnName+" from "+VariableHisTableName()+")";
		}else{
			tableName = VariableTableName();
		}
		return tableName;
	}
	/**
	 * 流程变量表名
	 * @return
	 */
	public static String VariableTableName(){
		DataBaseTable table = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getDataBaseTableConfig(DataBaseTableEnum.fixflow_run_variable);
		if(table != null){
			return table.getTableValue();
		}
		return "FIXFLOW_RUN_VARIABLE";
	}
	
	/**
	 * 流程变量归档表名
	 * @return
	 */
	public static String VariableHisTableName(){
		DataBaseTable table = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getDataBaseTableConfig(DataBaseTableEnum.fixflow_his_variable);
		if(table != null){
			return table.getTableValue();
		}
		return "FIXFLOW_HIS_VARIABLE";
	}
	
	/**
	 * 编号
	 * @return
	 */
	public static FlowKeyProcessInstanceId ProcessInstanceId(){
		return new VariableObjKey().new FlowKeyProcessInstanceId();
	}
	
	
	public class FlowKeyProcessInstanceId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "processInstanceId";
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
			return "流程实例编号";
		}

	}
	
	
	/**
	 * 变量key
	 * @return
	 */
	public static FlowKeyVariableKey VariableKey(){
		return new VariableObjKey().new FlowKeyVariableKey();
	}
	
	public class FlowKeyVariableKey implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "variableKey";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "VARIABLE_KEY";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "variableKey";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "变量key";
		}

	}
	
	
	/**
	 * 变量值
	 * @return
	 */
	public static FlowKeyVariableValue VariableValue(){
		return new VariableObjKey().new FlowKeyVariableValue();
	}
	
	public class FlowKeyVariableValue implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "variableValue";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "VARIABLE_VALUE";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "variableValue";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "变量值";
		}

	}
	
	/**
	 * 变量类全名
	 * @return
	 */
	public static FlowKeyVariableClassName VariableClassName(){
		return new VariableObjKey().new FlowKeyVariableClassName();
	}
	
	public class FlowKeyVariableClassName implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "variableClassName";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "VARIABLE_CLASSNAME";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "variableClassName";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "变量类全名";
		}

	}
	
	/**
	 * 令牌号
	 * @return
	 */
	public static FlowKeyTokenId TokenId(){
		return new VariableObjKey().new FlowKeyTokenId();
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
			return "令牌号";
		}

	}
	
	/**
	 * 节点编号
	 * @return
	 */
	public static FlowKeyNodeId NodeId(){
		return new VariableObjKey().new FlowKeyNodeId();
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
	
	
	/**
	 * 任务编号
	 * @return
	 */
	public static FlowKeyTaskInstanceId TaskInstanceId(){
		return new VariableObjKey().new FlowKeyTaskInstanceId();
	}
	
	public class FlowKeyTaskInstanceId implements ObjKeyInterface{

		public String EntityKey() {
			return "taskInstanceId";
		}
		
	
		public String DataBaseKey() {
			return "TASKINSTANCE_ID";
		}
		
		public String FullKey() {
			return "taskInstanceId";
		}

		public String KeyName() {
			return "任务编号";
		}

	}
	

	/**
	 * 变量业务类型
	 * @return
	 */
	public static FlowKeyVariableType VariableType(){
		return new VariableObjKey().new FlowKeyVariableType();
	}
	
	public class FlowKeyVariableType implements ObjKeyInterface{
		public String EntityKey() {
			// TODO Auto-generated method stub
			return "variableType";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "VARIABLE_TYPE";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "variableType";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "变量业务类型";
		}
	}
	
	
	
	/** 
	 * 业务查询数据
	 * @return
	 */
	public static FlowKeyBizData BizData(){
		return new VariableObjKey().new FlowKeyBizData();
	}
	
	public class FlowKeyBizData implements ObjKeyInterface{
		public String EntityKey() {
			// TODO Auto-generated method stub
			return "bizData";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "BIZ_DATA";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "bizData";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "业务查询数据";
		}
	}
	
	/**
	 * 归档时间
	 * @return
	 */
	public static FlowKeyArchiveTime ArchiveTime(){
		return new VariableObjKey().new FlowKeyArchiveTime();
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
