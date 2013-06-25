package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class GetModelInternationalizationResourcesCmd implements Command<String> {

	protected String resourcesType;
	protected String resourceKey;
	
	public GetModelInternationalizationResourcesCmd(String resourcesType, String resourceKey){
		this.resourcesType=resourcesType;
		this.resourceKey=resourceKey;
	}
	
	public String execute(CommandContext commandContext) {


		
		Boolean booleanTemp=StringUtil.getBoolean(Context.getProcessEngineConfiguration().getInternationalizationConfig().getIsEnable());
    	
    	
    	if(booleanTemp){
    		
        	String nameTemp=null;
        	
        	nameTemp=Context.getProcessEngineConfiguration().getFixFlowResources().getResourceName(resourcesType, resourceKey);
            	


        	if(nameTemp==null){
        		return resourceKey;
        	}
        	return nameTemp;
        	
    	}
    	else{
    		return resourceKey;
    	}
		
		
		
	}

}
