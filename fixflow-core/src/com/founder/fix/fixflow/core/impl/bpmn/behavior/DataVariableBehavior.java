package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.List;

import org.eclipse.emf.ecore.util.FeatureMap;

import com.founder.fix.fixflow.core.impl.util.EMFExtensionUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class DataVariableBehavior {

	protected String id;

	protected String dataType;

	protected boolean isList;

	protected boolean isPersistence;

	protected String expression;

	protected String documentation;
	
	protected String nodeId;
	
	

	protected boolean isPubilc;

	public DataVariableBehavior(FeatureMap.Entry entry,String nodeId,boolean isPubilc) {
		
		this.nodeId=nodeId;
		this.isPubilc=isPubilc;
		
		List<FeatureMap.Entry> entries = EMFExtensionUtil.getExtensionElementsInEntry(entry, "expression");
		String expressionValue = null;
		if (entries != null&&entries.size()>0) {
			FeatureMap.Entry expressionEntry = EMFExtensionUtil.getExtensionElementsInEntry(entry, "expression").get(0);
			expressionValue = EMFExtensionUtil.getExtensionElementValue(expressionEntry);
		}

		this.expression = expressionValue;
		this.id = EMFExtensionUtil.getExtensionElementAttributeValue(entry, "id");
		this.dataType = EMFExtensionUtil.getExtensionElementAttributeValue(entry, "dataType");
		this.isList = StringUtil.getBoolean(EMFExtensionUtil.getExtensionElementAttributeValue(entry, "isList"));
		this.isPersistence = StringUtil.getBoolean(EMFExtensionUtil.getExtensionElementAttributeValue(entry, "isPersistence"));

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isList() {
		return isList;
	}

	public void setList(boolean isList) {
		this.isList = isList;
	}

	public boolean isPersistence() {
		return isPersistence;
	}

	public void setPersistence(boolean isPersistence) {
		this.isPersistence = isPersistence;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public boolean isPubilc() {
		return isPubilc;
	}

	public void setPubilc(boolean isPubilc) {
		this.isPubilc = isPubilc;
	}

}
