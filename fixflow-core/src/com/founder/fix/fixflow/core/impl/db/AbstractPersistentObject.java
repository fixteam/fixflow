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

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.founder.fix.bpmn2extensions.sqlmappingconfig.ColumnMapping;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.DataBaseTable;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;

public abstract class AbstractPersistentObject implements PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1542067996535178080L;

	/**
	 * 从数据库初始化对象
	 * 
	 * @param entityMap
	 *            字段Map
	 * @return
	 */
	public abstract void persistentInit(Map<String, Object> entityMap);

	/**
	 * 从数据库初始化对象
	 * 
	 * @param entityMap
	 *            字段Map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public void persistentInit(DataBaseTable dataBaseTable, Map<String, Object> entityMap) {

		String className = dataBaseTable.getMappingType();

		
		try {
			Class clazz = Class.forName(className);

			Object obj = clazz.newInstance();

			// 获得类的所有属性
			
			List<ColumnMapping> columnMappings=dataBaseTable.getColumnMapping();
			
		

			for (ColumnMapping columnMapping : columnMappings) {

				PropertyDescriptor pd = new PropertyDescriptor(columnMapping.getProperty(), clazz);

				// 获得写方法

				Method wM = pd.getWriteMethod();

				// 获得读方法

				Method rM = pd.getReadMethod();

				// 获得方法的参数，因为是标准的set方法，所以只取第一个参数

				Class[] classes = wM.getParameterTypes();

				// 判断参数不为空，则只有一个

				if (classes != null && classes.length == 1) {

					// 判断参数类型

					if (classes[0].equals(String.class)) {

						// 调用set方法，传参
						Object dataObj=entityMap.get(columnMapping.getColumn());
						wM.invoke(obj, dataObj);

						// 调用get方法，获得返回值

						//String str = (String) rM.invoke(obj);

						//System.out.println("Name is : " + str);

					}

					//if (classes[0].equals(int.class)) {

						//wM.invoke(obj, 2);

						//Integer num = (Integer) rM.invoke(obj);

						//System.out.println("Age is : " + num);

					//}

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取能持久化到数据的Map
	 * 
	 * @return 对应到数据库字段的Map
	 */
	public abstract Map<String, Object> getPersistentDbMap();

}
