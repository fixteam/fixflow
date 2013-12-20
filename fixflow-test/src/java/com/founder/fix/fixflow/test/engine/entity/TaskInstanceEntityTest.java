package com.founder.fix.fixflow.test.engine.entity;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

import com.founder.fix.bpmn2extensions.sqlmappingconfig.ColumnMapping;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.DataBaseTable;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;

public class TaskInstanceEntityTest extends AbstractFixFlowTestCase{

	@SuppressWarnings("rawtypes")
	public void testTaskInstanceEntityMapping() throws Exception {
		
		DataBaseTable dataBaseTable=processEngineConfiguration.getDataBaseTable("fixflow_run_taskinstance");
		
		
		
		
		Class clazz = Class.forName(dataBaseTable.getMappingType());

		Object obj = clazz.newInstance();
		
		List<ColumnMapping> columnMappings=dataBaseTable.getColumnMapping();
		
		

		for (ColumnMapping columnMapping : columnMappings) {

			PropertyDescriptor pd = new PropertyDescriptor(columnMapping.getProperty(), clazz);

			// 获得写方法

			Method wM = pd.getWriteMethod();

			// 获得读方法

			Method rM = pd.getReadMethod();
		}


		
		
	}

}
