/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Instance Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ProcessInstanceVariable#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getProcessInstanceVariable()
 * @model extendedMetaData="name='processInstanceVariable'"
 * @generated
 */
public interface ProcessInstanceVariable extends EObject {
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(Expression)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getProcessInstanceVariable_Expression()
	 * @model containment="true"
	 *        extendedMetaData="name='expression' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ProcessInstanceVariable#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

} // ProcessInstanceVariable
