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
 * A representation of the model object '<em><b>Task Command Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig#getTaskCommandDef <em>Task Command Def</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandConfig()
 * @model extendedMetaData="name='taskCommandConfig'"
 * @generated
 */
public interface TaskCommandConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Task Command Def</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task Command Def</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task Command Def</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandConfig_TaskCommandDef()
	 * @model containment="true"
	 *        extendedMetaData="name='taskCommandDef' kind='element'"
	 * @generated
	 */
	EList<TaskCommandDef> getTaskCommandDef();

} // TaskCommandConfig
