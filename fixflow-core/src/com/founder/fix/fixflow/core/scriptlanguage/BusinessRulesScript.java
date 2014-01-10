package com.founder.fix.fixflow.core.scriptlanguage;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;

public interface BusinessRulesScript {
	
	/**
	 * 执行业务规则
	 * @param parameter 参数
	 * @return
	 */
	Object execute(Object parameter,SqlCommand sqlCommand,ProcessEngineConfigurationImpl processEngineConfiguration);
	
	
	

}
