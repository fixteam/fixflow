package com.founder.fix.fixflow.core.impl.bpmn.behavior;


import com.founder.fix.bpmn2extensions.fixflow.DataVariable;


public class DataVariableBehavior {

	protected String id;

	protected String dataType;

	protected boolean isList;

	protected boolean isPersistence;

	protected String expression;

	protected String documentation;
	
	protected String nodeId;
	
	

	protected boolean isPubilc;

	public DataVariableBehavior(DataVariable dataVariable,String nodeId,boolean isPubilc) {
		
		this.nodeId=nodeId;
		this.isPubilc=isPubilc;
		
		String expressionValue = null;
		if(dataVariable.getExpression()!=null){
			expressionValue=dataVariable.getExpression().getValue();
		}
		
		

		this.expression = expressionValue;
		this.id = dataVariable.getId();
		this.dataType =dataVariable.getDataType();
		this.isList = dataVariable.isIsList();
		this.isPersistence = dataVariable.isIsPersistence();

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
