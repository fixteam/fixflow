package com.founder.fix.fixflow.core.scriptlanguage;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;

public interface SelectRulesScript {
	

	/**
	 * 执行查询规则
	 * @param parameter 参数
	 * @return
	 */
	Object execute(Object parameter,SqlCommand sqlCommand,ProcessEngineConfigurationImpl processEngineConfiguration);

}
