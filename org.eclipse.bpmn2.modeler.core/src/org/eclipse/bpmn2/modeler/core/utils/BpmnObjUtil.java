package org.eclipse.bpmn2.modeler.core.utils;


import java.io.IOException;

import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class BpmnObjUtil {
	
	
	
	public static Process getProcess(EObject be){
		Process processnew=null;
		ModelHandler modelHandler = null;
		try {
			modelHandler = ModelHandler.getInstance(be);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		Participant participant =modelHandler.getParticipant(be);
		if(participant!=null){
			processnew=participant.getProcessRef();
		}
		else{
			processnew=(Process)modelHandler.getDefinitions().getRootElements().get(0);

		}
		return processnew;
	}
	


	
	public static String getObjDisplayName(EClass element) {

		String elementName=element.getName();
		if (elementName.equals("BusinessRuleTask")) {
			return "业务规则任务";
		}
		
		
		if (elementName.equals("Task")) {
            return "任务";
        }
		
		
		if (elementName.equals("UserTask")) {
            return "人工任务";
        }
		if (elementName.equals("ScriptTask")) {
            return "脚本任务";
        }
		if (elementName.equals("ManualTask")) {
            return "手工任务";
        }
		
		if (elementName.equals("ServiceTask")) {
            return "服务任务";
        }
		
		
		if (elementName.equals("SendTask")) {
            return "发送任务";
        }
		
		if (elementName.equals("ReceiveTask")) {
            return "接收任务";
        }
		
		if (elementName.equals("SubProcess")) {
            return "子流程";
        }

		
		if (elementName.equals("InclusiveGateway")) {
            return "包容网关";
        }
		if (elementName.equals("ExclusiveGateway")) {
            return "排他网关";
        }
		if (elementName.equals("ParallelGateway")) {
            return "并行网关";
        }
		
		if (elementName.equals("ComplexGateway")) {
            return "条件网关";
        }
		
		if (elementName.equals("EndEvent")) {
            return "结束事件";
        }
		
		return ModelUtil.toDisplayName(element.getName());


	}
	


}
