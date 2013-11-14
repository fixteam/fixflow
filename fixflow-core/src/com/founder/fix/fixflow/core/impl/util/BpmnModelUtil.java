package com.founder.fix.fixflow.core.impl.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.dd.di.DiagramElement;

public class BpmnModelUtil {
	
	
	public static String getDocumentation(BaseElement baseElement){
		List<Documentation> documentations=baseElement.getDocumentation();
		if(documentations.size()==0){
			return null;
		}
		else{
			String documentationText=documentations.get(0).getText();
			return documentationText;
		}
	}
	
	public static void setDocumentation(BaseElement baseElement,String documentationText){
		List<Documentation> documentations=baseElement.getDocumentation();
		if(documentations==null){
			documentations=new ArrayList<Documentation>();
			Documentation documentation=Bpmn2Factory.eINSTANCE.createDocumentation();
			documentation.setText(documentationText);
			documentations.add(documentation);
			return;
		}
		if(documentations.size()==0){
			Documentation documentation=Bpmn2Factory.eINSTANCE.createDocumentation();
			documentation.setText(documentationText);
			documentations.add(documentation);
			return;
		}
		else{
			documentations.get(0).setText(documentationText);
			return;
		}
	}
	
	
	public static String getExpression(Expression expression){
		if(expression==null){
			return null;
		}
		return ((FormalExpression)expression).getBody();
		
	}
	

	
	public static Expression getExpressionByString(String expression){
		FormalExpression formalExpression=Bpmn2Factory.eINSTANCE.createFormalExpression();
		formalExpression.setBody(expression);
		return formalExpression;
		
	}
	
	public static BPMNShape getBpmnShape(Definitions definitions,String elementId){
		List<DiagramElement> diagramElements=definitions.getDiagrams().get(0).getPlane().getPlaneElement();
		
		
		for (DiagramElement diagramElement : diagramElements) {
			if(diagramElement instanceof BPMNShape){
				String bpmnId=((BPMNShape)diagramElement).getBpmnElement().getId();
				if(elementId.equals(bpmnId)){
			
					return (BPMNShape)diagramElement;
				}
			}
		}
		
		return null;
		  
		  
		
		
	}
	
	public static BPMNEdge getBpmnEdge(Definitions definitions,String elementId){
		List<DiagramElement> diagramElements=definitions.getDiagrams().get(0).getPlane().getPlaneElement();
		
		
		for (DiagramElement diagramElement : diagramElements) {
			if(diagramElement instanceof BPMNEdge){
				String bpmnId=((BPMNEdge)diagramElement).getBpmnElement().getId();
				if(elementId.equals(bpmnId)){
			
					return (BPMNEdge)diagramElement;
				}
			}
		}
		
		return null;
		  
		  
		
		
	}
	
	
	public static BaseElement  getBaseElement(Definitions definitions,String elementId){
		
		
		
		return EMFUtil.findElement(elementId, definitions);

		  
		
		
	}
	
	public static <T> T  getElement(Definitions definitions,String elementId,Class<T> class1){
		
		
		
		return EMFUtil.findElement(elementId, definitions, class1);

	}
	
	public static <T> List<T>  getElementList(BaseElement baseElement,Class<T> class1){
		
		
		
		return EMFUtil.getAll(class1, baseElement);

	}
	
	public static Process getProcess(BaseElement baseElement){

		return EMFUtil.getAll(Process.class, baseElement).get(0);
	}
	
	public static List<Process> getProcessList(BaseElement baseElement){

		return EMFUtil.getAll(Process.class, baseElement);
	}

}
