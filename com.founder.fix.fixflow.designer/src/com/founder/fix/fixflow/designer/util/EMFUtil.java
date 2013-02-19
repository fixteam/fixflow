package com.founder.fix.fixflow.designer.util;

import java.io.IOException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.founder.fix.bpmn2extensions.connector.Connector;
import com.founder.fix.bpmn2extensions.connectormenu.Menu;
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage;
import com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig;
import com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig;

public class EMFUtil {

	/**
	 * 加载所有的数据变量
	 * 
	 * @return
	 */
	public static DataVariableConfig getFixFlowDataVariableConfig(String filePath) {

		DataVariableConfig dataVariableConfig = null;

		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xml", new XMIResourceFactoryImpl());

		Resource resource = resourceSet.getResource(
				URI.createFileURI(filePath), true);

		// register package in local resource registry
		resourceSet.getPackageRegistry().put(
				CoreconfigPackage.eINSTANCE.getNsURI(),
				CoreconfigPackage.eINSTANCE);
		// load resource
		try {
			resource.load(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dataVariableConfig = (DataVariableConfig) resource.getContents().get(0);

		// XMIResource resource = (XMIResource) new
		// ResourceSetImpl().getResource(URI.createFileURI(getDataVariableXMLPath()),
		// true);
		// dataVariableConfig = (DataVariableConfig)
		// resource.getContents().get(0);

		return dataVariableConfig;
	}

	/**
	 * 加载所有的fixflowconfig配置信息
	 * 
	 * @return
	 */
	public static FixFlowConfig getFixFlowConfig(String filePath) {
	
		FixFlowConfig fixFlowConfig=null;
		
			ResourceSet resourceSet = new ResourceSetImpl();

			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
					.put("xml", new XMIResourceFactoryImpl());

			

			Resource resource = resourceSet.getResource(URI.createFileURI(filePath), true);

			// register package in local resource registry
			resourceSet.getPackageRegistry().put(
					CoreconfigPackage.eINSTANCE.getNsURI(),
					CoreconfigPackage.eINSTANCE);
			// load resource
			try {
				resource.load(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			fixFlowConfig = (FixFlowConfig) resource.getContents().get(0);

		return fixFlowConfig;
	
	}
	
	public static ScriptLanguage getScriptLanguage() {
		ScriptLanguageConfig scriptLanguageConfig=FixFlowConfigUtil.getFixFlowConfig().getScriptLanguageConfig();


		for (ScriptLanguage scriptLanguage : scriptLanguageConfig.getScriptLanguage()) {
			if(scriptLanguage.getId().equals(scriptLanguageConfig.getSelected())){
				return scriptLanguage;
			}
		}
		return null;
		
	}
	
	
	/**
	 * 加载所有的fixflowconfig配置信息
	 * 
	 * @return
	 */
	public static Menu getConnectorMenuConfig(String filePath) {

		
		Menu root=null;
		
		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xml", new XMIResourceFactoryImpl());

		Resource resource = resourceSet.getResource(
				URI.createFileURI(filePath), true);

		// register package in local resource registry
		resourceSet.getPackageRegistry().put(
				CoreconfigPackage.eINSTANCE.getNsURI(),
				CoreconfigPackage.eINSTANCE);
		// load resource
		try {
			resource.load(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		root = (Menu) resource.getContents()
				.get(0);
		return root;
	
	}
	
	/**
	 * 加载所有的fixflowconfig配置信息
	 * 
	 * @return
	 */
	public static Connector getConnectorConfig(String filePath) {

		
		Connector connector=null;
		
		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xml", new XMIResourceFactoryImpl());

		Resource resource = resourceSet.getResource(
				URI.createFileURI(filePath), true);

		// register package in local resource registry
		resourceSet.getPackageRegistry().put(
				CoreconfigPackage.eINSTANCE.getNsURI(),
				CoreconfigPackage.eINSTANCE);
		// load resource
		try {
			resource.load(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		connector = (Connector) resource.getContents()
				.get(0);
		return connector;
	
	}
	

}
