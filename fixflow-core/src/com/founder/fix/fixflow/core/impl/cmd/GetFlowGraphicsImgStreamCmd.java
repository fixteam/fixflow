package com.founder.fix.fixflow.core.impl.cmd;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;

public class GetFlowGraphicsImgStreamCmd  implements Command<InputStream> {

	protected String processDefinitionId;
	protected String processDefinitionKey;
	
	public GetFlowGraphicsImgStreamCmd(String processDefinitionId,String processDefinitionKey){
		this.processDefinitionId=processDefinitionId;
		this.processDefinitionKey=processDefinitionKey;
	}
	
	public InputStream execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		ProcessDefinitionBehavior processDefinitionBehavior=null;
		
		if(this.processDefinitionId!=null&&!this.processDefinitionId.equals("")){
			
			processDefinitionBehavior=commandContext.getProcessDefinitionManager().findLatestProcessDefinitionById(this.processDefinitionId);
			
			
		}else{
			if(this.processDefinitionKey!=null&&!this.processDefinitionKey.equals("")){
				processDefinitionBehavior=commandContext.getProcessDefinitionManager().findLatestProcessDefinitionByKey(processDefinitionKey);
			}
			else{
				throw new FixFlowBizException("查询流程图的processDefinitionId、processDefinitionKey不能都为空!");
			}
		}
		
		String deploymentId=processDefinitionBehavior.getDeploymentId();
		String diagramResourceName=processDefinitionBehavior.getDiagramResourceName();
		ResourceEntity resourceEntity=commandContext.getResourceManager().findResourceByDeploymentIdAndResourceName(deploymentId, diagramResourceName);
		InputStream inputStream = new ByteArrayInputStream(resourceEntity.getBytes()); 
		return inputStream;
		
	
	}

}
