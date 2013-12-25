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
 * @author yangchenhui
 */
package com.founder.fix.fixflow.core.impl.util;

import com.founder.fix.bpmn2extensions.sqlmappingconfig.Column;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.DataBaseTable;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.runtime.QueryLocation;

/**
 * 查询query中数据来源工具类，用来处理归档表 运行表之间的关系
 * @author Administrator
 *
 */
public class QueryTableUtil {
	
	/**
	 * 获取查询查询表名，run his run_his
	 * @param tableId
	 * @param queryLocation
	 * @return
	 */
	public static String getTableSelect(String tableId,QueryLocation queryLocation){
		ProcessEngineConfigurationImpl processEngineConfigurationImpl = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration();
		DataBaseTable dataBaseTable = processEngineConfigurationImpl.getDataBaseTable(tableId);
		if(dataBaseTable == null){
			throw new FixFlowException("未找到id为"+tableId+"的table配置");
		}
		if(!StringUtil.getBoolean(processEngineConfigurationImpl.getPigeonholeConfig().getIsEnable())){
			return dataBaseTable.getTableValue();
		}
		String tableName = "";
		if(QueryLocation.HIS.equals(queryLocation)){
			tableName =  dataBaseTable.getArchiveTable();
		}else if(QueryLocation.RUN_HIS.equals(queryLocation)){
			StringBuilder sbColumn = new StringBuilder();
			for(Column column :dataBaseTable.getColumn()){
				sbColumn.append(column.getColumn());
				sbColumn.append(",");
			}
			String columnString = sbColumn.toString().substring(0,sbColumn.toString().length()-1);
			tableName = dataBaseTable.getTableValue();
			String hisTableName = dataBaseTable.getArchiveTable();
			
			tableName = "(select "+columnString+" from "+tableName+" union all select "+columnString+" from "+hisTableName+")";
		}else{
			tableName = dataBaseTable.getTableValue();
		}
		return tableName;
	}

}
