/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.variableconfig;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fix Flow Data Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable#getDataVariableDef <em>Data Variable Def</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getFixFlowDataVariable()
 * @model extendedMetaData="name='fixFlowDataVariable'"
 * @generated
 */
public interface FixFlowDataVariable extends EObject {
	/**
	 * Returns the value of the '<em><b>Data Variable Def</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Variable Def</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Variable Def</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getFixFlowDataVariable_DataVariableDef()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='dataVariableDef'"
	 * @generated
	 */
	EList<DataVariableDef> getDataVariableDef();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getFixFlowDataVariable_Type()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="name='type' namespace='' kind='attribute'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // FixFlowDataVariable
