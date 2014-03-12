/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author ych
 */
package com.founder.fix.fixflow.bpmn.converter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.Process;
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
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.exception.FixFlowClassLoadingException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.DefinitionsBehavior;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.core.internationalization.ExceptionCode;
import com.founder.fix.fixflow.editor.language.json.converter.BpmnJsonConverter;

public class FixFlowConverter {
	
	public FixFlowConverter(){
		
	}
	
	/**
	 * 将bpmn模型转换成jsonNode
	 * @param processKey
	 * @param input
	 * @return
	 */
	public ObjectNode convertBpmn2Json(String processKey,InputStream input){
		Definitions model = getDefinitions(processKey,input);
		BpmnJsonConverter converter = new BpmnJsonConverter();
		ObjectNode jsonNode = converter.convertToJson(model);
		return jsonNode;
	}
	
	/**
	 * 将bpmn模型转换成jsonNode
	 * @param processKey
	 * @param input
	 * @return
	 */
	public ObjectNode convertDefinitions2Json(Definitions definitions){
		BpmnJsonConverter converter = new BpmnJsonConverter();
		ObjectNode jsonNode = converter.convertToJson(definitions);
		return jsonNode;
	}
	
	/**
	 * 测试方法 不可用
	 * @param defintion
	 */
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
	 * 创建新的bpmn文件
	 * @param uri
	 * @param processId
	 * @param processName
	 */
	public void createBPMNFile(String path,String processId,String processName){
		File newFile = new File(path);
		InputStream inputStream = null;
		String filePath = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getDefaultTemplatePath();
		try {
			inputStream = ReflectUtil.getResourceAsStream(filePath);
		}catch(Exception ex){
			throw new FixFlowClassLoadingException(ExceptionCode.CLASSLOAD_EXCEPTION,ex);
		}
		FileOutputStream outputStream = null;
		BufferedOutputStream buffOS = null;
		InputStream inputStreamNewFile = null;
		try{
			newFile.createNewFile();
			outputStream = new FileOutputStream(newFile);
			BufferedInputStream buffInput = new BufferedInputStream(inputStream);
			buffOS = new BufferedOutputStream(outputStream);
			int word = 0;
			while ((word = buffInput.read()) != -1){
				buffOS.write(word);
			}
			buffOS.flush();
			inputStreamNewFile = new FileInputStream(newFile);
		}catch(Exception ex){
			throw new FixFlowException(ExceptionCode.EXCEPTION_DEFAULT,ex);
		}finally{
			try {
				if(outputStream !=null)
					outputStream.close();
				if(buffOS != null)
					buffOS.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new FixFlowException(ExceptionCode.EXCEPTION_DEFAULT,e);
			}
		}
		URI uri = URI.createFileURI(path);
		Definitions definitions = getDefinitions("process_1",inputStreamNewFile);
		Process process =  (Process)definitions.getRootElements().get(0);
		process.setId(processId);
		process.setName(processName);
		save(definitions, uri);
	}
	
	/**
	 * 将jsonNode转换并保存成bpmn文件
	 * @param modelNode
	 * @param uri
	 */
	public void save(JsonNode modelNode,URI uri){
		Definitions defintion = new BpmnJsonConverter().convertToBpmnModel(modelNode);
		save(defintion,uri);
	}
	
	/**
	 * 将definitions保存成bpmn文件
	 * @param definitions
	 * @param uri
	 */
	public void save(Definitions definitions,URI uri){
		ResourceSet resourceSet = getResourceSet();
		Bpmn2Resource resource = (Bpmn2Resource) resourceSet.getResource(uri, true);
		DocumentRoot documentRoot = (DocumentRoot) resource.getContents().get(0);
		documentRoot.setDefinitions(definitions);
		try {
			resource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FixFlowException(ExceptionCode.EXCEPTION_DEFAULT,e);
		}
	}
	
	/**
	 * 获取一个空的definitions
	 * @return
	 */
	public Definitions getNoneDefinitions(){
		Definitions definitions = null;
		String filePath = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getFixFlowFilePath();;
		try {
			InputStream in = null;
			in = ReflectUtil.getResourceAsStream(filePath);
			definitions = getDefinitions("node-tempplate",in);
			return definitions;
		}catch(Exception ex){
			throw new FixFlowClassLoadingException(ExceptionCode.CLASSLOAD_EXCEPTION,ex);
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
		String fixflowFilePath = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getFixFlowFilePath();
		URL url = ReflectUtil.getResource(fixflowFilePath);
		if(url == null){
			throw new FixFlowClassLoadingException(ExceptionCode.CLASSLOAD_EXCEPTION_FILENOTFOUND,fixflowFilePath);
		}
		String filePath = url.toString();
		Resource ddddResource = null;
		try {
			if (!filePath.startsWith("jar")) {
				ddddResource = resourceSet.createResource(URI.createFileURI(fixflowFilePath));
			} else {
				ddddResource = resourceSet.createResource(URI.createURI(filePath));
			}
			ddddResource.load(input, null);
		} catch (Exception e) {
			throw new FixFlowException(ExceptionCode.EXCEPTION_DEFAULT,e);
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
