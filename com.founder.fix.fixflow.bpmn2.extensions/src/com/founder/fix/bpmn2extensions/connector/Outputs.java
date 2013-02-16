/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.connector;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Outputs</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Outputs#getOutputParameter <em>Output Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getOutputs()
 * @model
 * @generated
 */
public interface Outputs extends EObject {
	/**
	 * Returns the value of the '<em><b>Output Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.connector.OutputParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Parameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Parameter</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getOutputs_OutputParameter()
	 * @model containment="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	EList<OutputParameter> getOutputParameter();

} // Outputs
