package com.founder.fix.fixflow.util;

import java.util.List;

import com.founder.fix.bpmn2extensions.coreconfig.ResourcePath;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;

public class FlowWebConst {
	public static String privatePath = "";
	public static String sharedPath = "";
	
	static{
		ProcessEngineConfigurationImpl configuration = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration();
		List<ResourcePath> list = configuration.getResourcePathConfig().getResourcePath();
		for(ResourcePath resourcePath : list){
			if(configuration.FIXFLOW_EDITOR_PRIVATE_REPOSITORY.equals(resourcePath.getId())){
				privatePath = resourcePath.getPhysicalPath();
			}
			if(configuration.FIXFLOW_EDITOR_PUBLIC_REPOSITORY.equals(resourcePath.getId())){
				sharedPath = resourcePath.getPhysicalPath();
			}
		}
	}
}
