/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Source To Sub Process Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DataSourceToSubProcessMapping#getDataVariableMapping <em>Data Variable Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataSourceToSubProcessMapping()
 * @model
 * @generated
 */
public interface DataSourceToSubProcessMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Data Variable Mapping</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Variable Mapping</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Variable Mapping</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataSourceToSubProcessMapping_DataVariableMapping()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<DataVariableMapping> getDataVariableMapping();

} // DataSourceToSubProcessMapping
