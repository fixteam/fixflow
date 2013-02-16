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
 * A representation of the model object '<em><b>Expand Class Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClassConfig#getExpandClass <em>Expand Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandClassConfig()
 * @model extendedMetaData="name='expandClassConfig'"
 * @generated
 */
public interface ExpandClassConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Expand Class</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expand Class</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expand Class</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandClassConfig_ExpandClass()
	 * @model containment="true"
	 *        extendedMetaData="name='expandClass' kind='element'"
	 * @generated
	 */
	EList<ExpandClass> getExpandClass();

} // ExpandClassConfig
