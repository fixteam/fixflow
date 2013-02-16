/**
 * Copyright c FOUNDER CORPORATION 2011 All Rights Reserved.
 * RoleInfoEntity.java
 */
package com.founder.fix.fixflow.designer.modeler.ui.common;

/**
 * [类名]<br>
 * RoleInfoEntity.java<br>
 * <br>
 * [功能概要]<br>
 *
 * <br>
 * [变更履历]<br>
 *
 * <br>
 * 2011-8-11 ver1.0 <br>
 * <br>
 *
 * @作者 wangzhiwei
 *
 */

public class RoleInfoEntity extends BpmnEntity {

	/**
	 * 构造器
	 */
	public RoleInfoEntity() {
		// TODO Auto-generated constructor stub
	}
	
	private String roleId;
	
	private String roleName;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
