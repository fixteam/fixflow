package com.founder.fix.fixflow.designer.usercontrol;

/**
 * 表达式对象
 * @author jiangnan
 *
 */
public class ExpressionTo {
	
	/**
	 * 表达式对象的名称
	 */
	protected String name;
	
	/**
	 * 表达式对象的值
	 */
	protected String expressionText;
	
	/**
	 * 获取表达式对象的值
	 * @return 表达式对象的值
	 */
	public String getExpressionText() {
		return expressionText;
	}
	/**
	 * 设置表达式对象的值
	 * @param expressionText 表达式对象的值
	 */
	public void setExpressionText(String expressionText) {
		this.expressionText = expressionText;
	}
	/**
	 * 获取表达式对象的名称
	 * @return 表达式对象的名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置表达式对象的名称
	 * @param name 表达式对象的名称
	 */
	public void setName(String name) {
		this.name = name;
	}

}
