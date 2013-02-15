package com.founder.fix.fixflow.core.impl.bpmn.behavior;


import org.eclipse.emf.ecore.util.FeatureMap;

import com.founder.fix.fixflow.core.impl.util.EMFExtensionUtil;

public class TaskSubject {

	protected String id;
	
	protected String name;

	protected String expressionValue;
	
	
	public TaskSubject(FeatureMap.Entry entry)
	{
		FeatureMap.Entry expressionEntry=EMFExtensionUtil.getExtensionElementsInEntry(entry, "expression").get(0);
		String expressionValue=EMFExtensionUtil.getExtensionElementValue(expressionEntry);
	
		this.expressionValue=expressionValue;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getExpressionValue() {
		return expressionValue;
	}
	
	
	
	
	

}
