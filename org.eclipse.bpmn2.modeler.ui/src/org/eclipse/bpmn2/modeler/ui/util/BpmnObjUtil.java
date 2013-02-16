package org.eclipse.bpmn2.modeler.ui.util;

import java.io.IOException;
import java.net.URL;

import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateConnectionFeature;
import org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateFeature;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.diagram.BPMNFeatureProvider;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

public class BpmnObjUtil {
	
	
	
	public static Process getProcess(ModelHandler modelHandler,EObject be){
		Process processnew=null;
		Participant participant =modelHandler.getParticipant(be);
		if(participant!=null){
			processnew=participant.getProcessRef();
		}
		else{
			processnew=(Process)modelHandler.getDefinitions().getRootElements().get(0);

		}
		return processnew;
	}
	

	public static Image  getImage(EObject be) {
	
        BPMN2Editor editor = BPMN2Editor.getEditor( be );
        
        if (editor!=null) {
        	
		    @SuppressWarnings("restriction")
			BPMNFeatureProvider fp = (BPMNFeatureProvider)editor.getDiagramTypeProvider().getFeatureProvider();
		    IFeature cf = fp.getCreateFeatureForBusinessObject(be.getClass());
			if (cf instanceof AbstractBpmn2CreateFeature) {
				return GraphitiUi.getImageService().getImageForId(
						((AbstractBpmn2CreateFeature)cf).getCreateImageId());
			}
			if (cf instanceof AbstractBpmn2CreateConnectionFeature) {
				return GraphitiUi.getImageService().getImageForId(
						((AbstractBpmn2CreateConnectionFeature)cf).getCreateImageId());
			}
        }
		return null;
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
		
		if (elementName.equals("CallActivity")) {
            return "调用服务";
        }
		
		return ModelUtil.toDisplayName(element.getName());


	}
	
	
	public static Image getImage(EClass element) {
		
		//return null;
		String pathString=getPluginDevelopRealPath("icons/16/"+element.getName().replace("Impl", "")+".png");
		if(pathString==null||pathString.equals("")){
			return null;
		}
		Image image = new Image(null, pathString);
		
		
		
		return image;
	}
	
	/**
	 * 获取插件项目下(jar包中)的某个文件目录路径
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getPluginDevelopRealPath(String filePath) {
		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
		URL url = bundle.getResource(filePath);
		try {
			return FileLocator.toFileURL(url).getFile();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		return "";
	}

}
