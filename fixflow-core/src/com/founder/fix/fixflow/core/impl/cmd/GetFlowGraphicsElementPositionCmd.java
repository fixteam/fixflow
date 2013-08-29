/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl.cmd;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.DefinitionsBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;

public class GetFlowGraphicsElementPositionCmd implements Command<Map<String, Map<String, Object>>> {

	/**
	 * 流程定义编号
	 */
	protected String processDefinitionId;
	
	
	protected DefinitionsBehavior definitions;

	public GetFlowGraphicsElementPositionCmd(String processDefinitionId) {
		
		
		this.processDefinitionId = processDefinitionId;
		
		
	}

	public Map<String, Map<String, Object>> execute(CommandContext commandContext) {

		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();
		
		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);
		
		DefinitionsBehavior definitions = processDefinition.getDefinitions();
		Map<String, Map<String, Object>> positionInfo=new HashMap<String, Map<String,Object>>();
		this.definitions=definitions;
		List<BPMNDiagram> BPMNDiagramList = definitions.getDiagrams();
		
		for (BPMNDiagram bpmnDiagram : BPMNDiagramList) {
			
			
			for (DiagramElement diagramElement : bpmnDiagram.getPlane().getPlaneElement()) {
				
				if (diagramElement instanceof BPMNShape) {
					BPMNShape bpmnShape = (BPMNShape) diagramElement;
					Map<String, Object>  positionMap=new HashMap<String, Object>();
					
					BaseElement bpmnElement=getBaseElement(bpmnShape.getBpmnElement());
				
					
					
					if(bpmnElement==null){
						continue;
					}

					float x=bpmnShape.getBounds().getX();
					float y=bpmnShape.getBounds().getY();
					float height=bpmnShape.getBounds().getHeight();
					float width=bpmnShape.getBounds().getWidth();
					//height,width,x,y
					positionMap.put("x",x);
					positionMap.put("y",y);
					positionMap.put("height",height);
					positionMap.put("width",width);
					positionInfo.put(bpmnElement.getId(), positionMap);
				}
				if (diagramElement instanceof BPMNEdge) {
					//BPMNEdge bpmnEdge = (BPMNEdge) diagramElement;
					
				}
				
			}
			
			
			
		}
		
		return positionInfo;
		
		//return CommonNodeToSVG(bpmnShape, new SvgCallActivityTo());
	}

	private  BaseElement getBaseElement(BaseElement baseElement)
	{
		
		
		if(baseElement==null){
			return null;
		}
		
		if(baseElement.getId()==null){
			BasicEObjectImpl basicEObjectImpl=(BasicEObjectImpl)baseElement;
			if(basicEObjectImpl!=null&&basicEObjectImpl.eProxyURI()!=null){
				String elementId=basicEObjectImpl.eProxyURI().fragment();
				BaseElement bpmnElement=definitions.getElement(elementId);
				return bpmnElement;
			}
			else{
				return null;
			}
		}else{
			return baseElement;
		}
		
		
		
	}
	
	
	

}
