package com.founder.fix.fixflow.core;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;

public abstract class ProcessEngineConfiguration {
	
	
	

	protected String processEngineName = ProcessEngineManagement.NAME_DEFAULT;
	
	protected String processEngineConfigurationXmlPath;

	

	protected ProcessEngineConfiguration() {
	}

	public static ProcessEngineConfiguration createProcessEngineConfiguration(String processEngineName) {
		
		if(processEngineName!=null){
			if(processEngineName.equals(ProcessEngineManagement.NAME_DEFAULT)){
				return new ProcessEngineConfigurationImpl();
			}
			else{
				if(processEngineName.equals(ProcessEngineManagement.NAME_DESIGNER)){
					return new ProcessEngineConfigurationImpl();
				}
			}
		}
		else{
			return new ProcessEngineConfigurationImpl();
		}
		
		return  new ProcessEngineConfigurationImpl();

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
	
	public String getProcessEngineConfigurationXmlPath() {
		return processEngineConfigurationXmlPath;
	}

	public void setProcessEngineConfigurationXmlPath(String processEngineConfigurationXmlPath) {
		this.processEngineConfigurationXmlPath = processEngineConfigurationXmlPath;
	}

}
