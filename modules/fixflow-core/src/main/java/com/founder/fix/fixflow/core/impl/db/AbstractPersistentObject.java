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

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.bpmn2extensions.sqlmappingconfig.Result;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.ResultMap;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.util.JavaBeanUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.scriptlanguage.AbstractScriptLanguageMgmt;


public abstract class AbstractPersistentObject <T> implements PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1542067996535178080L;

	
	private ResultMap resultMap;
	
	
	private boolean isAdd=true;


	public boolean isAdd() {
		return isAdd;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

	/**
	 * 获取当前对象所使用的数据映射规则
	 * @return
	 */
	public ResultMap getResultMap() {
		return resultMap;
	}
	

	//用户自定义字段用来存储数据库表中的新增字段
	
	/**
	 * 用户自定义字段
	 */
	protected Map<String, Object> extensionFields = new HashMap<String, Object>();
	
	/**
	 * 获取用户自定义字段值
	 * @param fieldName 字段名称
	 * @return
	 */
	public Object getExtensionField(String fieldName) {
		return extensionFields.get(fieldName);
	}

	/**
	 * 获取用户自定义字段Map
	 * @return
	 */
	public Map<String, Object> getExtensionFields() {
		return extensionFields;
	}

	/**
	 * 设置用户自定义字段
	 * @param extensionFields
	 */
	protected void setExtensionFields(Map<String, Object> extensionFields) {
		this.extensionFields = extensionFields;
	}

	/**
	 * 添加一个用户自定义字段
	 * @param fieldName 字段名称
	 * @param fieldValue 字段值
	 */
	protected void addExtensionField(String fieldName, Object fieldValue) {
		this.extensionFields.put(fieldName, fieldValue);
	}
	
	

	/**
	 * 持久化扩展字段
	 */
	protected Map<String, Object> persistenceExtensionFields = new HashMap<String, Object>();

	/**
	 * 获取持久化Map
	 * @return
	 */
	public Map<String, Object> getPersistenceExtensionFields() {
		return persistenceExtensionFields;
	}

	/**
	 * 设置一个持久化Map,这个值将会被持久到表中
	 * @param fieldName 字段名称
	 * @param value 字段值
	 */
	public void setPersistenceExtensionField(String fieldName, Object value) {
		extensionFields.put(fieldName, value);
		persistenceExtensionFields.put(fieldName, value);
	}
	
	//    抽象方法 ////////////////////////
	
	/**
	 * 获取拷贝所使用的业务规则编号
	 * @return
	 */
	public abstract String getCloneRuleId();
	
	/**
	 * 获取能持久化到数据的Map的业务规则编号
	 * @return
	 */
	public abstract String getPersistentDbMapRuleId();
	
	/**
	 * 获取对象Map化业务规则编号
	 * @return
	 */
	public abstract String getPersistentStateRuleId();
	
	

	
	

	
	
	// 公用方法
	
	
	/**
	 * 从数据库初始化对象
	 * 
	 * @param entityMap
	 *            字段Map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public void persistentInit(ResultMap resultMap, Map<String, Object> entityMap) {

		String className = resultMap.getType();

		this.resultMap=resultMap;
		try {
			Class clazz = Class.forName(className);

			//Object obj = clazz.newInstance();

			// 获得类的所有属性
			
			List<Result> results=resultMap.getResult();
			
		

			for (Result result : results) {

				Object dataObj=entityMap.get(result.getColumn());
				if(dataObj==null){
					continue;
				}
				
//				PropertyDescriptor pd = new PropertyDescriptor(result.getProperty(), clazz);

				// 获得写方法

//				Method wM = pd.getWriteMethod();
				Method wM = JavaBeanUtil.getSetStringMethod(clazz, result.getProperty(), result.getJdbcType());
				
				if(wM==null){
					continue;
				}
				// 获得读方法

				//Method rM = pd.getReadMethod();

				// 获得方法的参数，因为是标准的set方法，所以只取第一个参数

				Class[] classes = wM.getParameterTypes();

				// 判断参数不为空，则只有一个

				if (classes != null && classes.length == 1) {

					// 判断参数类型

					if (classes[0].equals(String.class)) {
						wM.invoke(this, StringUtil.getString(dataObj));
					}else if(classes[0].equals(Date.class)){
						dataObj = StringUtil.getDate(dataObj);
						wM.invoke(this, dataObj);
					}else if(classes[0].equals(int.class)){
						wM.invoke(this, StringUtil.getInt(dataObj));
					}else if(classes[0].equals(byte[].class)){
						wM.invoke(this,(byte[]) dataObj);
					}


				}
				entityMap.remove(result.getColumn());
			}
			
			
			for (String mapKey : entityMap.keySet()) {
				addExtensionField(mapKey, entityMap.get(mapKey));
			}
			


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	
	@SuppressWarnings("unchecked")
	/**
	 * 拷贝对象
	 */
	public  T  clone(){
		
		AbstractScriptLanguageMgmt scriptLanguageMgmt=Context.getAbstractScriptLanguageMgmt();
		
		T tObject=(T)scriptLanguageMgmt.executeBusinessRules(getCloneRuleId(), this);
	
		return tObject;
		
	}
	
	/**
	 * 获取能持久化到数据的Map
	 * 
	 * @return 对应到数据库字段的Map
	 */
	public Map<String, Object> getPersistentDbMap() {
		Map<String, Object> objectParam = new HashMap<String, Object>();

	
		objectParam=(Map<String, Object>)Context.getCommandContext().getTaskManager().getPersistentDbMap(getPersistentDbMapRuleId(), this);

		return objectParam;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getPersistentState() {

		Map<String, Object> persistentState =null;
		persistentState=(Map<String, Object> )Context.getCommandContext().getMappingSqlSession().selectOne(getPersistentStateRuleId(), this);
		return persistentState;
	}
	
	

}
