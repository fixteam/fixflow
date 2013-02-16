/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getUserIdField <em>User Id Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getUserNameField <em>User Name Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getGroupIdField <em>Group Id Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getSqlText <em>Sql Text</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getDataSource <em>Data Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getUserInfo()
 * @model extendedMetaData="name='userInfo'"
 * @generated
 */
public interface UserInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>User Id Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Id Field</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Id Field</em>' attribute.
	 * @see #setUserIdField(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getUserInfo_UserIdField()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getUserIdField();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getUserIdField <em>User Id Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Id Field</em>' attribute.
	 * @see #getUserIdField()
	 * @generated
	 */
	void setUserIdField(String value);

	/**
	 * Returns the value of the '<em><b>User Name Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Name Field</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Name Field</em>' attribute.
	 * @see #setUserNameField(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getUserInfo_UserNameField()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getUserNameField();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getUserNameField <em>User Name Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Name Field</em>' attribute.
	 * @see #getUserNameField()
	 * @generated
	 */
	void setUserNameField(String value);

	/**
	 * Returns the value of the '<em><b>Group Id Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Id Field</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Id Field</em>' attribute.
	 * @see #setGroupIdField(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getUserInfo_GroupIdField()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getGroupIdField();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getGroupIdField <em>Group Id Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Id Field</em>' attribute.
	 * @see #getGroupIdField()
	 * @generated
	 */
	void setGroupIdField(String value);

	/**
	 * Returns the value of the '<em><b>Sql Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sql Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sql Text</em>' attribute.
	 * @see #setSqlText(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getUserInfo_SqlText()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getSqlText();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getSqlText <em>Sql Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sql Text</em>' attribute.
	 * @see #getSqlText()
	 * @generated
	 */
	void setSqlText(String value);

	/**
	 * Returns the value of the '<em><b>Data Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Source</em>' attribute.
	 * @see #setDataSource(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getUserInfo_DataSource()
	 * @model required="true"
	 *        extendedMetaData="name='dataSource' kind='attribute'"
	 * @generated
	 */
	String getDataSource();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getDataSource <em>Data Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Source</em>' attribute.
	 * @see #getDataSource()
	 * @generated
	 */
	void setDataSource(String value);

} // UserInfo
