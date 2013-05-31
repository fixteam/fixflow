/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Designer Org Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig#getAllUserInfo <em>All User Info</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig#getGroupInfo <em>Group Info</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig#getDataBaseId <em>Data Base Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDesignerOrgConfig()
 * @model extendedMetaData="name='designerOrgConfig'"
 * @generated
 */
public interface DesignerOrgConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>All User Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All User Info</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All User Info</em>' containment reference.
	 * @see #setAllUserInfo(AllUserInfo)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDesignerOrgConfig_AllUserInfo()
	 * @model containment="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	AllUserInfo getAllUserInfo();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig#getAllUserInfo <em>All User Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All User Info</em>' containment reference.
	 * @see #getAllUserInfo()
	 * @generated
	 */
	void setAllUserInfo(AllUserInfo value);

	/**
	 * Returns the value of the '<em><b>Group Info</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Info</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Info</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDesignerOrgConfig_GroupInfo()
	 * @model containment="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	EList<GroupInfo> getGroupInfo();

	/**
	 * Returns the value of the '<em><b>Data Base Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Base Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Base Id</em>' attribute.
	 * @see #setDataBaseId(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDesignerOrgConfig_DataBaseId()
	 * @model extendedMetaData="name='dataBaseId' kind='attribute'"
	 * @generated
	 */
	String getDataBaseId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig#getDataBaseId <em>Data Base Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Base Id</em>' attribute.
	 * @see #getDataBaseId()
	 * @generated
	 */
	void setDataBaseId(String value);

} // DesignerOrgConfig
