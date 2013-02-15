package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.util.FeatureMap;

import com.founder.fix.fixflow.core.impl.util.EMFExtensionUtil;

public class ProcessCommand {
	
	protected String id;

	protected String name;

	protected String expression;
	
	protected String processCommandType;
	
	public ProcessCommand(FeatureMap.Entry entry) {

		List<FeatureMap.Entry> expressionEntry=EMFExtensionUtil.getExtensionElementsInEntry(entry, "expression");
		String expressionValue=null;
		if(expressionEntry!=null&&expressionEntry.size()>0){
			expressionValue=EMFExtensionUtil.getExtensionElementValue(expressionEntry.get(0));
		}
		

		

		this.expression=expressionValue;
		this.id=EMFExtensionUtil.getExtensionElementAttributeValue(entry, "id");
		this.name=EMFExtensionUtil.getExtensionElementAttributeValue(entry, "name");
		
		
		this.processCommandType=EMFExtensionUtil.getExtensionElementAttributeValue(entry, "commandType");
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getExpression() {
		return expression;
	}
	
	public String getProcessCommandType() {
		return processCommandType;
	}

	public Map<String, Object> getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("id", this.id);
		persistentState.put("name", this.name);
		persistentState.put("type", processCommandType);
		return persistentState;
	}

}
