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
package com.founder.fix.fixflow.core.impl.db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.founder.fix.bpmn2extensions.sqlmappingconfig.DataBaseTable;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.Rule;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.Select;
import com.founder.fix.fixflow.core.exception.FixFlowDbException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.cache.CacheObject;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.scriptlanguage.AbstractScriptLanguageMgmt;

public class MappingSqlSession {

	protected Connection connection;

	protected CacheObject cacheObject;

	protected PersistentSession persistentSession;
	AbstractScriptLanguageMgmt scriptLanguageMgmt;
	ProcessEngineConfigurationImpl processEngineConfiguration;
	SqlCommand sqlCommand;
	public MappingSqlSession(Connection connection, CacheObject cacheObject) {
		this.cacheObject = cacheObject;
		this.connection = connection;
		scriptLanguageMgmt = Context.getAbstractScriptLanguageMgmt();
		processEngineConfiguration = Context.getProcessEngineConfiguration();
		scriptLanguageMgmt.setVariable("sysRulesConfig", processEngineConfiguration);
		sqlCommand=new SqlCommand(connection);
	}

	// insert
	// ///////////////////////////////////////////////////////////////////

	public void insert(String statement, PersistentObject persistentObject) {

		scriptLanguageMgmt.setVariable("parameter", persistentObject);
		scriptLanguageMgmt.setVariable("sqlCommand", sqlCommand);
		Rule rule = processEngineConfiguration.getRule(statement);
		scriptLanguageMgmt.execute(rule.getSqlValue());
	}

	// update
	// ///////////////////////////////////////////////////////////////////

	public void update(String statement, PersistentObject persistentObject) {
		scriptLanguageMgmt.setVariable("parameter", persistentObject);
		scriptLanguageMgmt.setVariable("sqlCommand", sqlCommand);
		Rule rule = processEngineConfiguration.getRule(statement);
		scriptLanguageMgmt.execute(rule.getSqlValue());
	}

	// delete
	// ///////////////////////////////////////////////////////////////////

	public void delete(String statement, Object parameter) {

		scriptLanguageMgmt.setVariable("parameter", parameter);
		scriptLanguageMgmt.setVariable("sqlCommand", sqlCommand);
		Rule rule = processEngineConfiguration.getRule(statement);
		scriptLanguageMgmt.execute(rule.getSqlValue());
	}

	public void delete(String statement, PersistentObject persistentObject) {
		scriptLanguageMgmt.setVariable("parameter", persistentObject);
		scriptLanguageMgmt.setVariable("sqlCommand", sqlCommand);
		Rule rule = processEngineConfiguration.getRule(statement);
		scriptLanguageMgmt.execute(rule.getSqlValue());
	}

	// select
	// ///////////////////////////////////////////////////////////////////

	@SuppressWarnings({ "rawtypes" })
	public List selectList(String statement) {
		return selectList(statement, null, 0, Integer.MAX_VALUE);
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String statement, Object parameter) {
		return selectList(statement, parameter, 0, Integer.MAX_VALUE);
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String statement, Object parameter, Page page) {
		if (page != null) {
			return selectList(statement, parameter, page.getFirstResult(), page.getMaxResults());
		} else {
			return selectList(statement, parameter, 0, Integer.MAX_VALUE);
		}
	}


	@SuppressWarnings("rawtypes")
	public List selectList(String statement, Object parameter, int firstResult, int maxResults) {
		return selectList(statement, new ListQueryParameterObject(parameter, firstResult, maxResults));
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectList(String statement, ListQueryParameterObject parameter) {

		
		
		
		
		scriptLanguageMgmt.setVariable("parameter", parameter);
		scriptLanguageMgmt.setVariable("sqlCommand", sqlCommand);
		Rule rule = processEngineConfiguration.getRule(statement);
		List returnObjList=(List)scriptLanguageMgmt.execute(rule.getSqlValue());
		if(rule instanceof Select){
			Select select=(Select)rule;
			String resultMap=select.getResultMap();
			if(StringUtil.isNotEmpty(resultMap)){
				DataBaseTable dataBaseTable=processEngineConfiguration.getDataBaseTable(resultMap);
				
				if(dataBaseTable==null){
					throw new FixFlowDbException("resultMap "+resultMap+" 未找到!");
				}
				
				String mappingType=dataBaseTable.getMappingType();
				if(StringUtil.isNotEmpty(mappingType)){
					List<Object> returnList=new ArrayList<Object>();
					
					for (Object object : returnObjList) {
						AbstractPersistentObject persistentObject =(AbstractPersistentObject)ReflectUtil.instantiate(mappingType);
						persistentObject.persistentInit(dataBaseTable,(Map)object);
						returnList.add(persistentObject);
					}
					return returnList;
					
				}else{
					throw new FixFlowDbException("resultMap: "+resultMap+"中的 mappingType为空!");
				}
				
				
				
			}
			
		}
		
		
		
		return returnObjList;

	}

	public Object selectOne(String statement, Object parameter) {
		
		scriptLanguageMgmt.setVariable("parameter", parameter);
		scriptLanguageMgmt.setVariable("sqlCommand", sqlCommand);
		Rule rule = processEngineConfiguration.getRule(statement);
		Object returnObj=scriptLanguageMgmt.execute(rule.getSqlValue());
		
		return returnObj;
	}

	public <T extends PersistentObject> T selectById(Class<T> entityClass, String id) {
		/*
		 * T persistentObject = cacheGet(entityClass, id); if
		 * (persistentObject!=null) { return persistentObject; } String
		 * selectStatement =
		 * dbSqlSessionFactory.getSelectStatement(entityClass); selectStatement
		 * = dbSqlSessionFactory.mapStatement(selectStatement); persistentObject
		 * = (T) sqlSession.selectOne(selectStatement, id); if
		 * (persistentObject==null) { return null; } cachePut(persistentObject,
		 * true); return persistentObject;
		 */
		return null;
	}

}
