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
 * A representation of the model object '<em><b>Data Variable Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig#getDataVariableType <em>Data Variable Type</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig#getFixFlowDataVariable <em>Fix Flow Data Variable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig#getDataVariableDataType <em>Data Variable Data Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getDataVariableConfig()
 * @model extendedMetaData="name='dataVariableConfig'"
 * @generated
 */
public interface DataVariableConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Data Variable Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Variable Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Variable Type</em>' containment reference.
	 * @see #setDataVariableType(DataVariableType)
	 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getDataVariableConfig_DataVariableType()
	 * @model containment="true" required="true"
	 *        extendedMetaData="name='dataVariableType' kind='element'"
	 * @generated
	 */
	DataVariableType getDataVariableType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig#getDataVariableType <em>Data Variable Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Variable Type</em>' containment reference.
	 * @see #getDataVariableType()
	 * @generated
	 */
	void setDataVariableType(DataVariableType value);

	/**
	 * Returns the value of the '<em><b>Fix Flow Data Variable</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fix Flow Data Variable</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fix Flow Data Variable</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getDataVariableConfig_FixFlowDataVariable()
	 * @model containment="true"
	 *        extendedMetaData="name='fixFlowDataVariable' kind='element'"
	 * @generated
	 */
	EList<FixFlowDataVariable> getFixFlowDataVariable();

	/**
	 * Returns the value of the '<em><b>Data Variable Data Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Variable Data Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Variable Data Type</em>' containment reference.
	 * @see #setDataVariableDataType(DataVariableDataType)
	 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getDataVariableConfig_DataVariableDataType()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='dataVariableDataType'"
	 * @generated
	 */
	DataVariableDataType getDataVariableDataType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig#getDataVariableDataType <em>Data Variable Data Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Variable Data Type</em>' containment reference.
	 * @see #getDataVariableDataType()
	 * @generated
	 */
	void setDataVariableDataType(DataVariableDataType value);

} // DataVariableConfig
