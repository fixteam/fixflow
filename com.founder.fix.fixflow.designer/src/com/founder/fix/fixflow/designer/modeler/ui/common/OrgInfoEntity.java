/**
 * Copyright c FOUNDER CORPORATION 2011 All Rights Reserved.
 * OrgInfoEntity.java
 */
package com.founder.fix.fixflow.designer.modeler.ui.common;

/**
 * [类名]<br>
 * OrgInfoEntity.java<br>
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

public class OrgInfoEntity extends BpmnEntity {

	/**
	 * 构造器
	 */
	public OrgInfoEntity() {
		// TODO Auto-generated constructor stub
	}

	private String orgId;
	
	private String orgName;
	
	private String supOrgId;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSupOrgId() {
		return supOrgId;
	}

	public void setSupOrgId(String supOrgId) {
		this.supOrgId = supOrgId;
	}
	
}
