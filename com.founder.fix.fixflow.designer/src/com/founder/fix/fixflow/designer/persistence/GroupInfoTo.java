package com.founder.fix.fixflow.designer.persistence;

public class GroupInfoTo {
	
	protected String typeId;
	
	protected String typeName;
	
	int orderId;

	/**
	 * 获取资源类型编号
	 * @return 资源类型编号
	 */
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	 * 获取资源类型名称
	 * @return 资源类型名称
	 */
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 获取资源类型排序号
	 * @return 资源类型排序号
	 */
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}
