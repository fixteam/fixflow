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
 * A representation of the model object '<em><b>Data Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 数据变量
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getId <em>Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getDataType <em>Data Type</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#isIsList <em>Is List</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#isIsPersistence <em>Is Persistence</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getExpression <em>Expression</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getBizType <em>Biz Type</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getFileName <em>File Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataVariable()
 * @model
 * @generated
 */
public interface DataVariable extends EObject {
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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataVariable_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Type</em>' attribute.
	 * @see #setDataType(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataVariable_DataType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getDataType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getDataType <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Type</em>' attribute.
	 * @see #getDataType()
	 * @generated
	 */
	void setDataType(String value);

	/**
	 * Returns the value of the '<em><b>Is List</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is List</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is List</em>' attribute.
	 * @see #setIsList(boolean)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataVariable_IsList()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	boolean isIsList();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#isIsList <em>Is List</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is List</em>' attribute.
	 * @see #isIsList()
	 * @generated
	 */
	void setIsList(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Persistence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Persistence</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Persistence</em>' attribute.
	 * @see #setIsPersistence(boolean)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataVariable_IsPersistence()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	boolean isIsPersistence();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#isIsPersistence <em>Is Persistence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Persistence</em>' attribute.
	 * @see #isIsPersistence()
	 * @generated
	 */
	void setIsPersistence(boolean value);

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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataVariable_Expression()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.fixflow.Documentation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataVariable_Documentation()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<Documentation> getDocumentation();

	/**
	 * Returns the value of the '<em><b>Biz Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Biz Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Biz Type</em>' attribute.
	 * @see #setBizType(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataVariable_BizType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getBizType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getBizType <em>Biz Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Biz Type</em>' attribute.
	 * @see #getBizType()
	 * @generated
	 */
	void setBizType(String value);

	/**
	 * Returns the value of the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Name</em>' attribute.
	 * @see #setFileName(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataVariable_FileName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getFileName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getFileName <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Name</em>' attribute.
	 * @see #getFileName()
	 * @generated
	 */
	void setFileName(String value);

} // DataVariable
