package com.founder.fix.fixflow.core.impl.variable;


import java.util.HashMap;
import java.util.Map;


import com.founder.fix.fixflow.core.exception.FixFlowException;

public class VariableTransferEntity extends VariableEntity{
	
	
	
	
	


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
