package com.founder.fix.fixflow.designer.modeler.ui.property.common;

import java.util.List;



public interface DataObjImport {
	
	/**
	 * 获取业务对象的编号
	 * @return
	 */
	String getId();
	
	/**
	 * 获取业务对象的名称
	 * @return
	 */
	String getName();
	
	/**
	 * 获取业务对象的类型
	 * @return
	 */
	String getType();
	
	/**
	 * 获取业务对象中的数据变量集合
	 * @return 数据变量集合
	 */
	List<DataVariableImport> getDataVariableList(String type);
	
	/**
	 * 获取业务对象的路径
	 * @return 业务对象的路径
	 */
	String getFilePath();
	
	

}
