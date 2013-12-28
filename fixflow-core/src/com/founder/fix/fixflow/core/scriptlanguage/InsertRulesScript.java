package com.founder.fix.fixflow.core.scriptlanguage;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;

public interface InsertRulesScript {
	
	/**
	 * 执行插入规则
	 * @param parameter 参数
	 * @return
	 */
	void execute(Object parameter,SqlCommand sqlCommand,ProcessEngineConfigurationImpl processEngineConfiguration);

}
