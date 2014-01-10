package com.founder.fix.fixflow.core.scriptlanguage;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;

public interface DeleteRulesScript {
	
	/**
	 * 执行删除规则
	 * @param parameter 参数
	 * @return
	 */
	void execute(Object parameter,SqlCommand sqlCommand,ProcessEngineConfigurationImpl processEngineConfiguration);

}
