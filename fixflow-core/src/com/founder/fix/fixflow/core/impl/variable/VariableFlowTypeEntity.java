package com.founder.fix.fixflow.core.impl.variable;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.variable.VariableFlowType;


public class VariableFlowTypeEntity{
	
	
	protected VariableFlowType variableFlowType;

	protected String typeValue;

	
	public VariableFlowTypeEntity(VariableFlowType variableFlowType,String typeValue){
		
		if(variableFlowType==null){
			throw new FixFlowException("variableFlowType 不能为空");
		}
		if(typeValue==null||typeValue.equals("")){
			throw new FixFlowException("typeValue 不能为空");
		}
		
		this.variableFlowType=variableFlowType;
		this.typeValue=typeValue;
	}
	
	public VariableFlowType getVariableFlowType() {
		return variableFlowType;
	}


	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		if(typeValue==null||typeValue.equals("")){
			throw new FixFlowException("typeValue 不能为空");
		}
		this.typeValue = typeValue;
	}

	
	
	
}
