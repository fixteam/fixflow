package com.founder.fix.fixflow.core.impl.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.DefinitionsBehavior;

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
	
	/**
	 * 增加扩展元素
	 * @param baseElement
	 * @param eReference
	 * @param o
	 * @return
	 */
	public static boolean addExtensionElement(BaseElement baseElement,EReference eReference,Object o){
		final FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) eReference, o);
  	  	if(baseElement.getExtensionValues().size() > 0){
  	  		baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
  	  	}else{
  	  		ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
  	  		extensionElement.getValue().add(extensionElementEntry);
  	  		baseElement.getExtensionValues().add(extensionElement);
  	  	} 
		return false;
	}
	
	/**
	 * 增加擴展屬性
	 * @param baseElement
	 * @param eReference
	 * @param o
	 * @return
	 */
	public static boolean addExtensionAttribute(BaseElement baseElement,EAttribute eAttribute,Object o){
		final FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) eAttribute, o);
		baseElement.getAnyAttribute().add(extensionElementEntry);
		return false;
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
				BPMNShape bpmnShape = (BPMNShape) diagramElement;
				BaseElement bpmnElement=getBaseElement((DefinitionsBehavior)definitions,bpmnShape.getBpmnElement());
				if(bpmnElement==null){
					continue;
				}
				if(elementId.equals(bpmnElement.getId())){
					return bpmnShape;
				}
			}
		}
		return null;
	}
	
	public static  BaseElement getBaseElement(DefinitionsBehavior definitions,BaseElement baseElement){
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
