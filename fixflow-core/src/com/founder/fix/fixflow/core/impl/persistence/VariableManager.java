package com.founder.fix.fixflow.core.impl.persistence;


import java.util.Map;

import com.founder.fix.fixflow.core.impl.variable.VariableQueryEntity;
import com.founder.fix.fixflow.core.impl.variable.VariableTransferEntity;


public class VariableManager extends AbstractManager {
	
	
	public void saveVariable(VariableTransferEntity variableTransferEntity){
		getDbSqlSession().save("saveVariable", variableTransferEntity);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryVariable(VariableQueryEntity variableQueryEntity){
		return (Map<String, Object>)getDbSqlSession().selectOne("queryVariable", variableQueryEntity);
	}
	
	public void deleteVariable(VariableQueryEntity variableQueryEntity){
		getDbSqlSession().delete("deleteVariable", variableQueryEntity);
	}

}
