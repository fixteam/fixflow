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
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig#getCommandType <em>Command Type</em>}</li>
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

	/**
	 * Returns the value of the '<em><b>Command Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Command Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Command Type</em>' attribute.
	 * @see #setCommandType(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandConfig_CommandType()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getCommandType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig#getCommandType <em>Command Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Command Type</em>' attribute.
	 * @see #getCommandType()
	 * @generated
	 */
	void setCommandType(String value);

} // TaskCommandConfig
