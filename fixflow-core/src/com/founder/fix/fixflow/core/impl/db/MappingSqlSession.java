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

import com.founder.fix.bpmn2extensions.sqlmappingconfig.ResultMap;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.Rule;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.Select;
import com.founder.fix.fixflow.core.exception.FixFlowDbException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.cache.CacheObject;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.scriptlanguage.AbstractScriptLanguageMgmt;
import com.founder.fix.fixflow.core.scriptlanguage.BusinessRulesScript;
import com.founder.fix.fixflow.core.scriptlanguage.DeleteRulesScript;
import com.founder.fix.fixflow.core.scriptlanguage.InsertRulesScript;
import com.founder.fix.fixflow.core.scriptlanguage.SelectRulesScript;
import com.founder.fix.fixflow.core.scriptlanguage.UpdateRulesScript;

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
		sqlCommand = new SqlCommand(connection);
	}

	// insert
	// ///////////////////////////////////////////////////////////////////

	public void insert(String statement, PersistentObject persistentObject) {
		
		AbstractPersistentObject<?> abstractPersistentObject=(AbstractPersistentObject<?>)persistentObject;

		
		
		abstractPersistentObject.setAdd(false);

		Object parameterOld = scriptLanguageMgmt.getVariable("parameter");

		scriptLanguageMgmt.setVariable("parameter", persistentObject);
		scriptLanguageMgmt.setVariable("sqlCommand", sqlCommand);
		Rule rule = processEngineConfiguration.getRule(statement);
		String classPath = rule.getClassPath();
		if (StringUtil.isNotEmpty(classPath)) {
			Class<?> classObj = processEngineConfiguration.getRuleClass(rule.getId());
			if (classObj != null) {
				try {
					InsertRulesScript insertRulesScript = (InsertRulesScript) classObj.newInstance();
					insertRulesScript.execute(persistentObject, sqlCommand, processEngineConfiguration);
				} catch (Exception e) {
					throw new FixFlowException(e.getMessage(),e);
				}
			} else {
				throw new FixFlowException("Class : " + classPath + "未找到!");
			}
		} else {
			scriptLanguageMgmt.execute(rule.getSqlValue());
		}
		scriptLanguageMgmt.setVariable("parameter", parameterOld);
	}

	// update
	// ///////////////////////////////////////////////////////////////////

	public void update(String statement, PersistentObject persistentObject) {
		
		AbstractPersistentObject<?> abstractPersistentObject=(AbstractPersistentObject<?>)persistentObject;
		
		
		
		abstractPersistentObject.setAdd(false);

		
		
		
		
		
		
		

		Object parameterOld = scriptLanguageMgmt.getVariable("parameter");

		scriptLanguageMgmt.setVariable("parameter", persistentObject);
		scriptLanguageMgmt.setVariable("sqlCommand", sqlCommand);
		Rule rule = processEngineConfiguration.getRule(statement);
		String classPath = rule.getClassPath();
		if (StringUtil.isNotEmpty(classPath)) {
			Class<?> classObj = processEngineConfiguration.getRuleClass(rule.getId());
			if (classObj != null) {
				try {
					UpdateRulesScript updateRulesScript = (UpdateRulesScript) classObj.newInstance();
					updateRulesScript.execute(persistentObject, sqlCommand, processEngineConfiguration);
				} catch (Exception e) {
					throw new FixFlowException("Class : " + classPath + "未找到!");
				}
			} else {
				throw new FixFlowException("Class : " + classPath + "未找到!");
			}
		} else {
			scriptLanguageMgmt.execute(rule.getSqlValue());
		}
		scriptLanguageMgmt.setVariable("parameter", parameterOld);

	}

	// delete
	// ///////////////////////////////////////////////////////////////////

	public void delete(String statement, Object parameter) {
		Object parameterOld = scriptLanguageMgmt.getVariable("parameter");
		scriptLanguageMgmt.setVariable("parameter", parameter);
		scriptLanguageMgmt.setVariable("sqlCommand", sqlCommand);
		Rule rule = processEngineConfiguration.getRule(statement);
		String classPath = rule.getClassPath();
		if (StringUtil.isNotEmpty(classPath)) {
			Class<?> classObj = processEngineConfiguration.getRuleClass(rule.getId());
			if (classObj != null) {
				try {
					DeleteRulesScript deleteRulesScript = (DeleteRulesScript) classObj.newInstance();
					deleteRulesScript.execute(parameter, sqlCommand, processEngineConfiguration);
				} catch (Exception e) {
					throw new FixFlowException("Class : " + classPath + "未找到!");
				}
			} else {
				throw new FixFlowException("Class : " + classPath + "未找到!");
			}
		} else {
			scriptLanguageMgmt.execute(rule.getSqlValue());
		}
		scriptLanguageMgmt.setVariable("parameter", parameterOld);
	}

	public void delete(String statement, PersistentObject persistentObject) {
		Object parameterOld = scriptLanguageMgmt.getVariable("parameter");
		scriptLanguageMgmt.setVariable("parameter", persistentObject);
		scriptLanguageMgmt.setVariable("sqlCommand", sqlCommand);
		Rule rule = processEngineConfiguration.getRule(statement);

		String classPath = rule.getClassPath();
		if (StringUtil.isNotEmpty(classPath)) {
			Class<?> classObj = processEngineConfiguration.getRuleClass(rule.getId());
			if (classObj != null) {
				try {
					DeleteRulesScript deleteRulesScript = (DeleteRulesScript) classObj.newInstance();
					deleteRulesScript.execute(persistentObject, sqlCommand, processEngineConfiguration);
				} catch (Exception e) {
					throw new FixFlowException("Class : " + classPath + "未找到!");
				}
			} else {
				throw new FixFlowException("Class : " + classPath + "未找到!");
			}
		} else {
			scriptLanguageMgmt.execute(rule.getSqlValue());
		}
		scriptLanguageMgmt.setVariable("parameter", parameterOld);
		// scriptLanguageMgmt.execute(rule.getSqlValue());
	}

	// select
	// ///////////////////////////////////////////////////////////////////

	@SuppressWarnings({ "rawtypes" })
	public List selectList(String statement) {
		return selectList(statement, null, null);
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String statement, Object parameter) {
		return selectList(statement, parameter, null);
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String statement, Object parameter, Page page) {

		return selectList(statement, new ListQueryParameterObject(parameter, page));

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectList(String statement, ListQueryParameterObject parameter) {
		Object parameterOld = scriptLanguageMgmt.getVariable("parameter");
		scriptLanguageMgmt.setVariable("parameter", parameter);
		scriptLanguageMgmt.setVariable("sqlCommand", sqlCommand);
		Rule rule = processEngineConfiguration.getRule(statement);

		List returnObjList = null;

		String classPath = rule.getClassPath();
		if (StringUtil.isNotEmpty(classPath)) {
			Class<?> classObj = processEngineConfiguration.getRuleClass(rule.getId());
			if (classObj != null) {
				try {
					SelectRulesScript selectRulesScript = (SelectRulesScript) classObj.newInstance();
					returnObjList = (List) selectRulesScript.execute(parameter, sqlCommand, processEngineConfiguration);

				} catch (Exception e) {
					throw new FixFlowException("Class : " + classPath + "未找到!");
				}
			} else {
				throw new FixFlowException("Class : " + classPath + "未找到!");
			}
		} else {

			if (StringUtil.isEmpty(rule.getSqlValue())) {
				throw new FixFlowException("rule : " + rule.getId() + " 值不能为空!");
			}
			
			Object returnObj=scriptLanguageMgmt.execute(rule.getSqlValue());
			if(returnObj instanceof SqlQuery){
				
				SqlQuery sqlQuery=(SqlQuery)returnObj;
				QueryList queryList=sqlQuery.getQueryList();
				if(queryList!=null){
					if(queryList.getData()==null){
						returnObjList=sqlCommand.queryForList(queryList.getSqlText());
					}
					else{
						returnObjList=sqlCommand.queryForList(queryList.getSqlText(), queryList.getData());
					}
				}
				
			}else{
				returnObjList = (List)returnObj ;
			}
			
			
		}

		if (rule instanceof Select) {
			Select select = (Select) rule;
			String resultMapSelect = select.getResultMap();
			if (StringUtil.isNotEmpty(resultMapSelect)) {
				ResultMap resultMap = processEngineConfiguration.getResultMap(resultMapSelect);

				if (resultMap == null) {
					throw new FixFlowDbException("resultMap " + resultMapSelect + " 未找到!");
				}

				String mappingType = resultMap.getType();
				if (StringUtil.isNotEmpty(mappingType)) {
					List<Object> returnList = new ArrayList<Object>();

					for (Object object : returnObjList) {
						AbstractPersistentObject persistentObject = (AbstractPersistentObject) ReflectUtil.instantiate(mappingType);
						persistentObject.persistentInit(resultMap, (Map) object);
						persistentObject.setAdd(false);
						returnList.add(persistentObject);
					}
					scriptLanguageMgmt.setVariable("parameter", parameterOld);
					return returnList;

				} else {
					throw new FixFlowDbException("resultMap: " + resultMap + "中的 mappingType为空!");
				}

			}

		}
		scriptLanguageMgmt.setVariable("parameter", parameterOld);
		return returnObjList;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object selectOne(String statement, Object parameter) {
		Object parameterOld = scriptLanguageMgmt.getVariable("parameter");
		scriptLanguageMgmt.setVariable("parameter", parameter);
		scriptLanguageMgmt.setVariable("sqlCommand", sqlCommand);
		Rule rule = processEngineConfiguration.getRule(statement);

		Object returnObjList = null;

		String classPath = rule.getClassPath();
		if (StringUtil.isNotEmpty(classPath)) {
			Class<?> classObj = processEngineConfiguration.getRuleClass(rule.getId());
			if (classObj != null) {
				
				
				
				try {

					Object classObjInstance=classObj.newInstance();
					
					if(classObjInstance instanceof SelectRulesScript){
						SelectRulesScript selectRulesScript = (SelectRulesScript) classObjInstance;
						returnObjList = (Object) selectRulesScript.execute(parameter, sqlCommand, processEngineConfiguration);
					}
					if(classObjInstance instanceof BusinessRulesScript){
						BusinessRulesScript businessRulesScript = (BusinessRulesScript) classObjInstance;
						returnObjList = (Object) businessRulesScript.execute(parameter, sqlCommand, processEngineConfiguration);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new FixFlowException("执行Rule异常: " + e.getMessage(), e);
				}
			} else {
				throw new FixFlowException("Class : " + classPath + "未找到!");
			}
		} else {
			
			
			returnObjList = (Object) scriptLanguageMgmt.execute(rule.getSqlValue());
			
			if(returnObjList instanceof SqlQuery){
				
				SqlQuery sqlQuery=(SqlQuery)returnObjList;
				QueryList queryList=sqlQuery.getQueryList();
				if(queryList!=null){
					if(queryList.getData()==null){
						returnObjList=sqlCommand.queryForList(queryList.getSqlText());
					}
					else{
						returnObjList=sqlCommand.queryForList(queryList.getSqlText(), queryList.getData());
					}

					
				}
				
				QueryMap queryMap=sqlQuery.getQueryMap();
				if(queryMap!=null){
					if(queryMap.getData()==null){
						returnObjList=sqlCommand.queryForList(queryMap.getSqlText());
					}
					else{
						returnObjList=sqlCommand.queryForList(queryMap.getSqlText(), queryMap.getData());
					}
				}
				
				QueryForValue queryForValue=sqlQuery.getQueryForValue();
				if(queryForValue!=null){
					if(queryForValue.getData()==null){
						returnObjList=sqlCommand.queryForList(queryForValue.getSqlText());
					}
					else{
						returnObjList=sqlCommand.queryForList(queryForValue.getSqlText(), queryForValue.getData());
					}
				}
				
			}
			
		}

		if (returnObjList == null) {
			scriptLanguageMgmt.setVariable("parameter", parameterOld);
			return null;
		}
		if (returnObjList instanceof List) {
			if (((List) returnObjList).size() == 0) {
				return null;
			}
		}
		if (rule instanceof Select) {
			Select select = (Select) rule;
			String resultMapSelect = select.getResultMap();
			if (StringUtil.isNotEmpty(resultMapSelect)) {
				ResultMap resultMap = processEngineConfiguration.getResultMap(resultMapSelect);

				if (resultMap == null) {
					throw new FixFlowDbException("resultMap " + resultMap + " 未找到!");
				}

				String mappingType = resultMap.getType();
				if (StringUtil.isNotEmpty(mappingType)) {

					AbstractPersistentObject persistentObject = (AbstractPersistentObject) ReflectUtil.instantiate(mappingType);

					if (returnObjList instanceof List) {
						List listObj = (List) returnObjList;
						if (listObj.size() == 1 && listObj.get(0) instanceof Map) {

							persistentObject.persistentInit(resultMap, (Map) listObj.get(0));
							persistentObject.setAdd(false);
							scriptLanguageMgmt.setVariable("parameter", parameterOld);
							return persistentObject;
						}

					} else {
						if (returnObjList instanceof Map) {
							persistentObject.persistentInit(resultMap, (Map) returnObjList);
							scriptLanguageMgmt.setVariable("parameter", parameterOld);
							return persistentObject;
						}
					}

					scriptLanguageMgmt.setVariable("parameter", parameterOld);
					return persistentObject;

				} else {
					throw new FixFlowDbException("resultMap: " + resultMap + "中的 mappingType为空!");
				}

			}

		}
		scriptLanguageMgmt.setVariable("parameter", parameterOld);
		return returnObjList;
	}
	


}
