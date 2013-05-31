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
 * A representation of the model object '<em><b>Import Data Variable Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.ImportDataVariableConfig#getImportDataVariable <em>Import Data Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getImportDataVariableConfig()
 * @model extendedMetaData="name='importDataVariableConfig'"
 * @generated
 */
public interface ImportDataVariableConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Import Data Variable</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.coreconfig.ImportDataVariable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Import Data Variable</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Import Data Variable</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getImportDataVariableConfig_ImportDataVariable()
	 * @model containment="true" required="true"
	 *        extendedMetaData="name='importDataVariable' kind='element'"
	 * @generated
	 */
	EList<ImportDataVariable> getImportDataVariable();

} // ImportDataVariableConfig
