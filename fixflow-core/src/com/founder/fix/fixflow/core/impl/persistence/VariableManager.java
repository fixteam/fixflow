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
package com.founder.fix.fixflow.core.impl.persistence;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.command.QueryVariablesCommand;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableEntity;
import com.founder.fix.fixflow.core.impl.db.PersistentObject;


public class VariableManager extends AbstractManager {
	
	
	public void saveVariable(DataVariableEntity dataVariableEntity){
		
		
		String variableKey=dataVariableEntity.getVariableKey();		
		String nodeId=dataVariableEntity.getNodeId();		
		String taskInstanceId=dataVariableEntity.getTaskInstanceId();
		String tokenId=dataVariableEntity.getTokenId();
		String processInstanceId=dataVariableEntity.getProcessInstanceId();
	
		List<String> keyList=new ArrayList<String>();		
		keyList.add(variableKey);
		
		QueryVariablesCommand queryVariablesCommand=new QueryVariablesCommand();
		queryVariablesCommand.setVariableNames(keyList);
		queryVariablesCommand.setNodeId(nodeId);
		queryVariablesCommand.setTaskInstanceId(taskInstanceId);
		queryVariablesCommand.setTokenId(tokenId);
		queryVariablesCommand.setProcessInstanceId(processInstanceId);
		
		// 执行查询流程是Sql语句,判断流程实例是否存在于数据库中.
		List<DataVariableEntity> dataVariableEntities=selectVariableByQuery(queryVariablesCommand);
		
		if(dataVariableEntities==null||dataVariableEntities.size()==0){
			// 数据库不存在这个变量,则执行创建新变量的方法.
			insert("insertVariable",dataVariableEntity);
		}else{
			// 当数据库中已经存在这个变量
			update("updateVariable",dataVariableEntity);
		}
		
		
	
	}
	
	public void insert(PersistentObject persistentObject) {
		getDbSqlSession().insert("insertVariable", persistentObject);
	}
	
	public void update(PersistentObject persistentObject){
		getDbSqlSession().update("updateVariable", persistentObject);
	}
	
	
	public List<DataVariableEntity> selectVariableByQuery(QueryVariablesCommand queryVariablesCommand){

		return selectVariableByQuery(queryVariablesCommand,null);
	}
	
	@SuppressWarnings("unchecked")
	public List<DataVariableEntity> selectVariableByQuery(QueryVariablesCommand queryVariablesCommand,Page page){
		
		
		
		return (List<DataVariableEntity>)getDbSqlSession().selectList("selectVariableByQuery", queryVariablesCommand,page);
	}
	
	
	public Map<String, Object> queryVariable(QueryVariablesCommand queryVariablesCommand){
		
		List<DataVariableEntity> dataVariableEntities=selectVariableByQuery(queryVariablesCommand);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		for (DataVariableEntity dataVariableEntity : dataVariableEntities) {
			dataMap.put(dataVariableEntity.getVariableKey(), dataVariableEntity.getVariableObject());
		}
		
		return dataMap;
	}
	
	public void deleteVariable(QueryVariablesCommand queryVariablesCommand){
		getDbSqlSession().delete("deleteVariable", queryVariablesCommand);
	}

}
