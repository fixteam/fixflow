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
package com.founder.fix.fixflow.core.impl.instantiation;


import java.util.HashMap;

import java.util.Map;

import org.dom4j.Element;
import org.eclipse.bpmn2.Definitions;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;

public class Delegation {


	protected static Map<String, Object> instantiatorCache = new HashMap<String, Object>();

	protected String className = null;
	protected String configuration = null;
	protected String configType = null;
	protected ProcessDefinitionBehavior processDefinition = null;
	transient Object instance = null;

	public Delegation() {
	}

	public Delegation(Object instance) {
		this.instance = instance;
	}

	public Delegation(String className) {
		this.className = className;
	}

	public void loadXml(Element element, Definitions definitions) {
		//processDefinition =//
		//className = element.attributeValue("class");
		if (className == null) {
			// jpdlReader.addWarning("no class specified in "+delegateElement.asXML());
		}

		//configType = delegateElement.attributeValue("config-type");

	}

	public Object getInstance() {
		if (instance == null) {
			instance = instantiate();
		}
		return instance;
	}

	public Object instantiate() {

		Object newInstance = ReflectUtil.instantiate(className);

		return newInstance;
	}


	// getters and setters
	// //////////////////////////////////////////////////////

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String instantiatorType) {
		this.configType = instantiatorType;
	}

	public ProcessDefinitionBehavior getProcessDefinition() {
		return processDefinition;
	}

	public void setProcessDefinition(ProcessDefinitionBehavior processDefinition) {
		this.processDefinition = processDefinition;
	}

}
