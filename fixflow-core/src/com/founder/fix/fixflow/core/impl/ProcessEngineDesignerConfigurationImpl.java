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
package com.founder.fix.fixflow.core.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;

public class ProcessEngineDesignerConfigurationImpl extends ProcessEngineConfigurationImpl {


	@Override
	protected void initEmfFile() {
		// TODO 自动生成的方法存根
		
		if(this.processEngineConfigurationXmlPath!=null){
			
			
			ResourceSet resourceSet = new ResourceSetImpl();
			String filePath = this.processEngineConfigurationXmlPath;
			Resource resource = null;
			try {
				if (!filePath.startsWith("jar")) {
					filePath = java.net.URLDecoder.decode(ReflectUtil.getResource("com/founder/fix/fixflow/expand/config/fixflowconfig.xml").getFile(),
							"utf-8");
					resource = resourceSet.createResource(URI.createFileURI(filePath));
				} else {
					resource = resourceSet.createResource(URI.createURI(filePath));
				}

			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
				throw new FixFlowException("流程配置文件加载失败！", e2);
			}

			// register package in local resource registry
			resourceSet.getPackageRegistry().put(CoreconfigPackage.eINSTANCE.getNsURI(), CoreconfigPackage.eINSTANCE);
			// load resource
			try {
				resource.load(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new FixFlowException("流程配置文件加载失败", e);
			}

			fixFlowConfig = (FixFlowConfig) resource.getContents().get(0);
			
		}
		else{
			super.initEmfFile();
		}
		
	}
	
	
	

}
