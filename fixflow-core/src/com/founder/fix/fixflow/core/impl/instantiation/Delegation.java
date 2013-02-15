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
