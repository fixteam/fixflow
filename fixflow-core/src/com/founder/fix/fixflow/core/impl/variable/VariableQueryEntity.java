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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.exception.FixFlowException;


public class VariableQueryEntity extends VariableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6017384908518015939L;
	protected List<String> variableNames=new ArrayList<String>();
	
	public void addVariableName(String variableName){
		variableNames.add(variableName);
	}
	
	
	public List<String> getVariableNames() {
		return variableNames;
	}

	public void setVariableNames(Collection<String> variableNames) {
		
		if(variableNames==null){
			throw new FixFlowException("variableNames 不能问空 ！");
		}
		List<String> variableNamesList=new ArrayList<String>();
		
		for (String nameString : variableNames) {
			variableNamesList.add(nameString);
		}
		this.variableNames = variableNamesList;
	}

	public VariableQueryEntity(){
		
	}
	
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getPersistentState() {
		// TODO Auto-generated method stub
		return null;
	}

}
