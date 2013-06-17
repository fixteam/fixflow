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
 * A representation of the model object '<em><b>Data Variable Biz Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableBizType#getTypeId <em>Type Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableBizType#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableBizType#getImg <em>Img</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getDataVariableBizType()
 * @model extendedMetaData="name='dataVariableBizType'"
 * @generated
 */
public interface DataVariableBizType extends EObject {
	/**
	 * Returns the value of the '<em><b>Type Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Id</em>' attribute.
	 * @see #setTypeId(String)
	 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getDataVariableBizType_TypeId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="name='typeId' kind='attribute'"
	 * @generated
	 */
	String getTypeId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableBizType#getTypeId <em>Type Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Id</em>' attribute.
	 * @see #getTypeId()
	 * @generated
	 */
	void setTypeId(String value);

	/**
	 * Returns the value of the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Name</em>' attribute.
	 * @see #setTypeName(String)
	 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getDataVariableBizType_TypeName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="name='typeName' kind='attribute'"
	 * @generated
	 */
	String getTypeName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableBizType#getTypeName <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' attribute.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(String value);

	/**
	 * Returns the value of the '<em><b>Img</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Img</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Img</em>' attribute.
	 * @see #setImg(String)
	 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#getDataVariableBizType_Img()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getImg();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableBizType#getImg <em>Img</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Img</em>' attribute.
	 * @see #getImg()
	 * @generated
	 */
	void setImg(String value);

} // DataVariableBizType
