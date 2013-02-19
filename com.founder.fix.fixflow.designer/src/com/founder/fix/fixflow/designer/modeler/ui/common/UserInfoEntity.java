/**
 * Copyright c FOUNDER CORPORATION 2011 All Rights Reserved.
 * UserInfoEntity.java
 */
package com.founder.fix.fixflow.designer.modeler.ui.common;

/**
 * [类名]<br>
 * UserInfoEntity.java<br>
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

public class UserInfoEntity extends BpmnEntity {

	/**
	 * 构造器
	 */
	public UserInfoEntity() {
		// TODO Auto-generated constructor stub
	}

	private String userId;
	
	private String userName;
	
	private String orgId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
