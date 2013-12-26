package com.founder.fix.fixflow.expand.rulescript;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableEntity;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.scriptlanguage.BusinessRulesScript;

public class VariablePersistentDbMap implements BusinessRulesScript {

	public Object execute(Object parameter, SqlCommand sqlCommand, ProcessEngineConfigurationImpl processEngineConfiguration) {

		
		DataVariableEntity dataVariable=(DataVariableEntity)parameter;
		
		Object variableValue=dataVariable.getExpressionValue();
		byte[] variableValueDb = DataVariableEntity.ObjectToBytes(variableValue);
		
		String variableType = dataVariable.getVariableType();
		
		String variableClassName = null;
		if (variableValue != null) {
			variableClassName = variableValue.getClass().getCanonicalName();
		}
	
		Map<String, Object> objectParam = new HashMap<String, Object>();
		objectParam.put("PROCESSINSTANCE_ID", dataVariable.getProcessInstanceId());		
		objectParam.put("VARIABLE_KEY", dataVariable.getVariableKey());		
		objectParam.put("VARIABLE_VALUE", variableValueDb);
		objectParam.put("VARIABLE_CLASSNAME", variableClassName);
		objectParam.put("TASKINSTANCE_ID", dataVariable.getTaskInstanceId());
		objectParam.put("TOKEN_ID", dataVariable.getTokenId());	
		objectParam.put("NODE_ID", dataVariable.getNodeId());		
		objectParam.put("BIZ_DATA", dataVariable.getBizData());
		objectParam.put("ARCHIVE_TIME", dataVariable.getArchiveTime());
		objectParam.put("VARIABLE_TYPE", variableType);
	
		if (variableType != null && variableType.equals(DataVariableEntity.QUERY_DATA_KEY)) {
			objectParam.put("BIZ_DATA", StringUtil.getString(variableValue));
		}
		
		
		Map<String, Object> persistenceExtensionFields=dataVariable.getPersistenceExtensionFields();		
		for (String key : persistenceExtensionFields.keySet()) {
			objectParam.put(key, persistenceExtensionFields.get(key));
		}
		return objectParam;
		
	}

}
