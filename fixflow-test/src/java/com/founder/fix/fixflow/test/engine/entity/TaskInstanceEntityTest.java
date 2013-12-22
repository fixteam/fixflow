package com.founder.fix.fixflow.test.engine.entity;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

import com.founder.fix.bpmn2extensions.sqlmappingconfig.DataBaseTable;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.Result;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.ResultMap;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;

public class TaskInstanceEntityTest extends AbstractFixFlowTestCase{

	@SuppressWarnings("rawtypes")
	public void testTaskInstanceEntityMapping() throws Exception {
		
		ResultMap resultMap=processEngineConfiguration.getResultMap("taskResultMap");
		
		
		
		
		Class clazz = Class.forName(resultMap.getType());

		Object obj = clazz.newInstance();
		
		List<Result> results=resultMap.getResult();
		
		

		for (Result columnMapping : results) {

			PropertyDescriptor pd = new PropertyDescriptor(columnMapping.getProperty(), clazz);

			// 获得写方法

			Method wM = pd.getWriteMethod();
			
			wM.invoke(obj,"");

			// 获得读方法

			//Method rM = pd.getReadMethod();
		}


		
		
	}

}
