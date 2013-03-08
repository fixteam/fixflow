/**
 * Copyright c FOUNDER CORPORATION 2011 All Rights Reserved.
 * BizobjEntity.java
 */
package com.founder.fix.fixflow.designer.modeler.ui.property.common;

/**
 * [类名]<br>
 * BizobjEntity.java<br>
 * <br>
 * [功能概要]<br>
 * 
 * <br>
 * [变更履历]<br>
 * 
 * <br>
 * 2011-7-25 ver1.0 <br>
 * <br>
 * 
 * @作者 wangzhiwei
 * 
 */
public class BizobjEntity {

	/**
	 * 
	 */
	public BizobjEntity() {
		// TODO Auto-generated constructor stub
	}

	private String id;  //id
	
	private String name;  //显示的名称
	
	private String realFullPath;  //真实全路径

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealFullPath() {
		return realFullPath;
	}

	public void setRealFullPath(String realFullPath) {
		this.realFullPath = realFullPath;
	}
	
}
