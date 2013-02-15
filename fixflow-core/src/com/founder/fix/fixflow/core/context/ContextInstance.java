package com.founder.fix.fixflow.core.context;


import java.util.Map;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public interface ContextInstance {
	


	/**
	 * 设置流程实例瞬态变量Map
	 * @param transientVariableMap 变量Map
	 */
	void setTransientVariableMap(Map<String, Object> transientVariableMap);
	
	public Map<String, Object> getTransientVariableMap();
	
	/**
	 * 添加流程实例瞬态变量
	 * @param variableKey 变量key
	 * @param variableObj 变量对象
	 */
	void addTransientVariable(String variableKey,Object variableObj);
	
	/**
	 * 获取瞬态变量对象
	 * @param variableKey 瞬态变量Key
	 * @return 瞬态变量
	 */
	public Object getTransientVariable(String variableKey,ExecutionContext  executionContext);
	
	
	
	/**
	 * 获取持久化变量Map
	 * @return
	 */
	Map<String, Object> getVariableMap();

	/**
	 * 设置持久化变量
	 * @param variableMap
	 */
	void setVariableMap(Map<String, Object> variableMap);
	
	/**
	 * 添加持久化变量
	 * @param variableKey 变量key
	 * @param variableObj
	 */
	void addVariable(String variableKey,Object variableObj);
	
	/**
	 * 获取持久化变量
	 * @param variableKey
	 * @return
	 */
	Object getVariable(String variableKey);
	
	/**
	 * 注册流程实例全局变量
	 * @param variableKey 变量编号
	 * @param variableObj 变量值
	 */
	void addDataVariable(String variableKey,Object variableObj);
	
	
	/**
	 * 注册流程实例全局变量
	 * @param dataVariableMap
	 */
	void setDataVariable(Map<String, Object> dataVariableMap);

}
