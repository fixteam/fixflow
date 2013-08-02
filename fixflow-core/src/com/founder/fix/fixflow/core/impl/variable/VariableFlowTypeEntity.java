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
