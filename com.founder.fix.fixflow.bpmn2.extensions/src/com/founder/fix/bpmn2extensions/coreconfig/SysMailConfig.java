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
 * A representation of the model object '<em><b>Sys Mail Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig#getSelected <em>Selected</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig#getMailInfo <em>Mail Info</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getSysMailConfig()
 * @model
 * @generated
 */
public interface SysMailConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Selected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selected</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selected</em>' attribute.
	 * @see #setSelected(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getSysMailConfig_Selected()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getSelected();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig#getSelected <em>Selected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Selected</em>' attribute.
	 * @see #getSelected()
	 * @generated
	 */
	void setSelected(String value);

	/**
	 * Returns the value of the '<em><b>Mail Info</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mail Info</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mail Info</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getSysMailConfig_MailInfo()
	 * @model containment="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	EList<MailInfo> getMailInfo();

} // SysMailConfig
