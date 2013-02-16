/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.variableconfig;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Type Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef#getName <em>Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef#getTypeValue <em>Type Value</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getDataTypeDef()
 * @model extendedMetaData="name='dataTypeDef'"
 * @generated
 */
public interface DataTypeDef extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getDataTypeDef_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="name='name' kind='attribute'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Value</em>' attribute.
	 * @see #setTypeValue(String)
	 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getDataTypeDef_TypeValue()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="name='typeValue' kind='attribute'"
	 * @generated
	 */
	String getTypeValue();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef#getTypeValue <em>Type Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Value</em>' attribute.
	 * @see #getTypeValue()
	 * @generated
	 */
	void setTypeValue(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getDataTypeDef_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // DataTypeDef
