package com.founder.fix.fixflow.designer.util;

import org.eclipse.bpmn2.Collaboration;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType;

public class FixBpmnUtil {
	
		public static Bpmn2DiagramType getBpmn2DiagramType(Definitions definitions){
		
		for (RootElement rootElement : definitions.getRootElements()) {
			if(rootElement instanceof Collaboration){
				return Bpmn2DiagramType.COLLABORATION;
			}
		}
		return Bpmn2DiagramType.PROCESS;
		
	}

}
