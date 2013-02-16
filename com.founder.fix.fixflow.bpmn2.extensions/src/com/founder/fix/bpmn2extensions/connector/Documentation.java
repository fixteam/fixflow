/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.connector;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Documentation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Documentation#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getDocumentation()
 * @model
 * @generated
 */
public interface Documentation extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getDocumentation_Value()
	 * @model extendedMetaData="kind='simple'"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Documentation#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // Documentation
