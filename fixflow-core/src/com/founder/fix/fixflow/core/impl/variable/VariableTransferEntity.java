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


import java.util.HashMap;
import java.util.Map;


import com.founder.fix.fixflow.core.exception.FixFlowException;

public class VariableTransferEntity extends VariableEntity{
	
	
	
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -5807158924996628168L;
	protected Map<String, Object> variableMap=new HashMap<String, Object>();
	

	

	public VariableTransferEntity(){
		
	}
	


	public void addVariable(String variableKey,Object variableValue){
		if(variableKey==null){
			throw new FixFlowException("variableKey 不能为空");
		}
		if(variableValue==null){
			System.out.print("警告!数据变量 "+ variableKey +" 的variableValue为空!");
		}
		variableMap.put(variableKey, variableValue);
	}
	
	public void setVariableMap(Map<String, Object> variableMap) {
		for (String mapKey : variableMap.keySet()) {
			String mapKeyObj=mapKey;
			Object mapValueObj=variableMap.get(mapKey);
			addVariable(mapKeyObj,mapValueObj);
		}
	}
	


	
	public Map<String, Object> getVariableMap() {
		return variableMap;
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
