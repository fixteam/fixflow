package com.founder.fix.fixflow.core;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;

public abstract class ProcessEngineConfiguration {

	String jiangnanString="111222";
	protected String processEngineName = ProcessEngineManagement.NAME_DEFAULT;
	

	
	protected ProcessEngineConfiguration() {
	}

	public static ProcessEngineConfiguration createProcessEngineConfiguration() {
		return new ProcessEngineConfigurationImpl();
	}

	public abstract ProcessEngine buildProcessEngine();

	public String getProcessEngineName() {
		return processEngineName;
	}

	public ProcessEngineConfiguration setProcessEngineName(
			String processEngineName) {
		this.processEngineName = processEngineName;
		return this;
	}

}
