package com.founder.fix.fixflow.designer.modeler.ui.property.common;



public interface DataVariableImport {
	
	/**
	 * 数据变量编号
	 * @return
	 */
	String getId();
	
	/**
	 * 数据变量名称
	 * @return
	 */
	String getName();
	
	/**
	 * 数据变量类型
	 * @return
	 */
	String getDataType();
	
	/**
	 * 获取变量值
	 * @return
	 */
	String getVariableValue();
	

	
}
