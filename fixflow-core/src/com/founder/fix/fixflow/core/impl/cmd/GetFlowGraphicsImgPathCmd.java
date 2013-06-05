package com.founder.fix.fixflow.core.impl.cmd;



import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;

public class GetFlowGraphicsImgPathCmd implements Command<String>{

	/**
	 * 流程定义编号
	 */
	protected String processDefinitionId;
	
	

	public GetFlowGraphicsImgPathCmd(String processDefinitionId) {
		
		
		this.processDefinitionId = processDefinitionId;
		
		
	}

	public String execute(CommandContext commandContext) {

		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();
		
		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);
		
	
		//当前语言
		String nowLanguage=commandContext.getProcessEngineConfigurationImpl().getFixFlowResources().getNowLanguage();
		String pathString="fixflowdiagram/"+nowLanguage+"/"+processDefinition.getProcessDefinitionKey()+"/"+processDefinition.getProcessDefinitionId().replace(":","_")+".PNG";
		
		return pathString;
		
		//return CommonNodeToSVG(bpmnShape, new SvgCallActivityTo());
	}
	
	
	
	

}
