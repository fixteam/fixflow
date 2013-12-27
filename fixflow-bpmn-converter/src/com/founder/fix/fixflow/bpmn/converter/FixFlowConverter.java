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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

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
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.DefinitionsBehavior;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
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
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("org/activiti/editor/language/default_process.bpmn");
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
			ex.printStackTrace();
		}finally{
			try {
				if(outputStream !=null)
					outputStream.close();
				if(buffOS != null)
					buffOS.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		}
	}
	
	/**
	 * 获取一个空的definitions
	 * @return
	 */
	public Definitions getNoneDefinitions(){
		Definitions definitions = null;
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("org/activiti/editor/language/node_template.bpmn");
		definitions = getDefinitions("node-tempplate",inputStream);
		return definitions;
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
