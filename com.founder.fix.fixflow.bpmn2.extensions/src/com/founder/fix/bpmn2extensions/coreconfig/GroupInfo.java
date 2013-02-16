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
 * A representation of the model object '<em><b>Group Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupId <em>Group Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupName <em>Group Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getIsEnabled <em>Is Enabled</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getIsDisplayUser <em>Is Display User</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupIdField <em>Group Id Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupNameField <em>Group Name Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getSupGroupIdField <em>Sup Group Id Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getSqlText <em>Sql Text</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getUserInfo <em>User Info</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupDefinitionImpl <em>Group Definition Impl</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupInfo()
 * @model extendedMetaData="name='groupInfo'"
 * @generated
 */
public interface GroupInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Group Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Id</em>' attribute.
	 * @see #setGroupId(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupInfo_GroupId()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getGroupId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupId <em>Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Id</em>' attribute.
	 * @see #getGroupId()
	 * @generated
	 */
	void setGroupId(String value);

	/**
	 * Returns the value of the '<em><b>Group Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Name</em>' attribute.
	 * @see #setGroupName(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupInfo_GroupName()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getGroupName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupName <em>Group Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Name</em>' attribute.
	 * @see #getGroupName()
	 * @generated
	 */
	void setGroupName(String value);

	/**
	 * Returns the value of the '<em><b>Is Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Enabled</em>' attribute.
	 * @see #setIsEnabled(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupInfo_IsEnabled()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIsEnabled();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getIsEnabled <em>Is Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Enabled</em>' attribute.
	 * @see #getIsEnabled()
	 * @generated
	 */
	void setIsEnabled(String value);

	/**
	 * Returns the value of the '<em><b>Is Display User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Display User</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Display User</em>' attribute.
	 * @see #setIsDisplayUser(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupInfo_IsDisplayUser()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIsDisplayUser();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getIsDisplayUser <em>Is Display User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Display User</em>' attribute.
	 * @see #getIsDisplayUser()
	 * @generated
	 */
	void setIsDisplayUser(String value);

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
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupInfo_GroupIdField()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getGroupIdField();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupIdField <em>Group Id Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Id Field</em>' attribute.
	 * @see #getGroupIdField()
	 * @generated
	 */
	void setGroupIdField(String value);

	/**
	 * Returns the value of the '<em><b>Group Name Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Name Field</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Name Field</em>' attribute.
	 * @see #setGroupNameField(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupInfo_GroupNameField()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getGroupNameField();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupNameField <em>Group Name Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Name Field</em>' attribute.
	 * @see #getGroupNameField()
	 * @generated
	 */
	void setGroupNameField(String value);

	/**
	 * Returns the value of the '<em><b>Sup Group Id Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sup Group Id Field</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sup Group Id Field</em>' attribute.
	 * @see #setSupGroupIdField(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupInfo_SupGroupIdField()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getSupGroupIdField();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getSupGroupIdField <em>Sup Group Id Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sup Group Id Field</em>' attribute.
	 * @see #getSupGroupIdField()
	 * @generated
	 */
	void setSupGroupIdField(String value);

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
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupInfo_SqlText()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getSqlText();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getSqlText <em>Sql Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sql Text</em>' attribute.
	 * @see #getSqlText()
	 * @generated
	 */
	void setSqlText(String value);

	/**
	 * Returns the value of the '<em><b>User Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Info</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Info</em>' containment reference.
	 * @see #setUserInfo(UserInfo)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupInfo_UserInfo()
	 * @model containment="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	UserInfo getUserInfo();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getUserInfo <em>User Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Info</em>' containment reference.
	 * @see #getUserInfo()
	 * @generated
	 */
	void setUserInfo(UserInfo value);

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
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupInfo_DataSource()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getDataSource();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getDataSource <em>Data Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Source</em>' attribute.
	 * @see #getDataSource()
	 * @generated
	 */
	void setDataSource(String value);

	/**
	 * Returns the value of the '<em><b>Group Definition Impl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Definition Impl</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Definition Impl</em>' attribute.
	 * @see #setGroupDefinitionImpl(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupInfo_GroupDefinitionImpl()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getGroupDefinitionImpl();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupDefinitionImpl <em>Group Definition Impl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Definition Impl</em>' attribute.
	 * @see #getGroupDefinitionImpl()
	 * @generated
	 */
	void setGroupDefinitionImpl(String value);

} // GroupInfo
