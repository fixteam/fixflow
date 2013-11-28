package com.founder.fix.fixflow.bpmn.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.di.BpmnDiPackage;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.dd.dc.DcPackage;
import org.eclipse.dd.di.DiPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.DefinitionsBehavior;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;

public class FixFlowConverter {
	
	public FixFlowConverter(){
		
	}
	
	public ObjectNode convertBpmn2Json(String processKey,InputStream input){
		Definitions model = getDefinitions(processKey,input);
		BpmnJsonConverter converter = new BpmnJsonConverter();
		ObjectNode jsonNode = converter.convertToJson(model);
		return jsonNode;
	}
	
	public void save(Definitions defintion){
		ResourceSet resourceSet = getResourceSet();
		Bpmn2Resource resource = (Bpmn2Resource) resourceSet.getResource(URI.createFileURI("d:\\node_template.bpmn"), true);
		DocumentRoot documentRoot = (DocumentRoot) resource.getContents().get(0);
		documentRoot.setDefinitions(defintion);
		try {
			resource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据流程key和bpmn文件流获取definitions
	 * @param processKey流程定义key
	 * @param input bpmn文件流
	 * @return
	 */
	public Definitions getDefinitions(String processKey,InputStream input){
		ResourceSet resourceSet = getResourceSet();
		String filePath = this.getClass().getClassLoader().getResource("com/founder/fix/fixflow/bpmn/converter/fixflowfile.bpmn").toString();
		Resource ddddResource = null;
		if (!filePath.startsWith("jar")) {
			try {
				filePath = java.net.URLDecoder.decode(ReflectUtil.getResource("com/founder/fix/fixflow/bpmn/converter/fixflowfile.bpmn").getFile(),
						"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				throw new FixFlowException("流程定义文件加载失败！", e);
			}
			ddddResource = resourceSet.createResource(URI.createFileURI(filePath));
		} else {
			ddddResource = resourceSet.createResource(URI.createURI(filePath));
		}
		try {
			ddddResource.load(input, null);
		} catch (UnsupportedEncodingException e) {
			throw new FixFlowException("定义文件加载失败!", e);
		} catch (IOException e) {
			throw new FixFlowException("定义文件加载失败!", e);
		}
		DefinitionsBehavior definitions = (DefinitionsBehavior) ddddResource.getContents().get(0).eContents().get(0);
		definitions.setProcessId(processKey);
		return definitions;
	}
	/**
	 * 加载EMF模型
	 * @return
	 */
	private ResourceSet getResourceSet() {
		ResourceSet resourceSet = new ResourceSetImpl();
		(EPackage.Registry.INSTANCE).put("http://www.omg.org/spec/BPMN/20100524/MODEL", Bpmn2Package.eINSTANCE);
		(EPackage.Registry.INSTANCE).put("http://www.founderfix.com/fixflow", FixFlowPackage.eINSTANCE);
		(EPackage.Registry.INSTANCE).put("http://www.omg.org/spec/DD/20100524/DI", DiPackage.eINSTANCE);
		(EPackage.Registry.INSTANCE).put("http://www.omg.org/spec/DD/20100524/DC", DcPackage.eINSTANCE);
		(EPackage.Registry.INSTANCE).put("http://www.omg.org/spec/BPMN/20100524/DI", BpmnDiPackage.eINSTANCE);
		FixFlowPackage.eINSTANCE.eClass();
		FixFlowPackage xxxPackage = FixFlowPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.put(xxxPackage.getNsURI(), xxxPackage);
		Bpmn2ResourceFactoryImpl ddd = new Bpmn2ResourceFactoryImpl();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fixflow", ddd);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("bpmn", ddd);
		resourceSet.getPackageRegistry().put(xxxPackage.getNsURI(), xxxPackage);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("bpmn", ddd);
		return resourceSet;
	}
}
