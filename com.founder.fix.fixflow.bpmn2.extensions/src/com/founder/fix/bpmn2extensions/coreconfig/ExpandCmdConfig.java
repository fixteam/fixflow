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
 * A representation of the model object '<em><b>Expand Cmd Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmdConfig#getExpandCmd <em>Expand Cmd</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandCmdConfig()
 * @model extendedMetaData="name='expandCmdConfig'"
 * @generated
 */
public interface ExpandCmdConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Expand Cmd</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expand Cmd</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expand Cmd</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandCmdConfig_ExpandCmd()
	 * @model containment="true"
	 *        extendedMetaData="name='expandCmd' kind='element'"
	 * @generated
	 */
	EList<ExpandCmd> getExpandCmd();

} // ExpandCmdConfig
