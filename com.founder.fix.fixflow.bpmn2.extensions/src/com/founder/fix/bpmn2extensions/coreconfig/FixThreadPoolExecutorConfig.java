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
 * A representation of the model object '<em><b>Fix Thread Pool Executor Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutorConfig#getFixThreadPoolExecutor <em>Fix Thread Pool Executor</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixThreadPoolExecutorConfig()
 * @model extendedMetaData="name='fixThreadPoolExecutorConfig'"
 * @generated
 */
public interface FixThreadPoolExecutorConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Fix Thread Pool Executor</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fix Thread Pool Executor</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fix Thread Pool Executor</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixThreadPoolExecutorConfig_FixThreadPoolExecutor()
	 * @model containment="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	EList<FixThreadPoolExecutor> getFixThreadPoolExecutor();

} // FixThreadPoolExecutorConfig
