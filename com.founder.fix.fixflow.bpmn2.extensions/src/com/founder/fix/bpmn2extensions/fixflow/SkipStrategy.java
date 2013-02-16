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
 * A representation of the model object '<em><b>Skip Strategy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#getExpression <em>Expression</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#isIsCreateSkipProcess <em>Is Create Skip Process</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#getSkipAssignee <em>Skip Assignee</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#getSkipComment <em>Skip Comment</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#isIsEnable <em>Is Enable</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getSkipStrategy()
 * @model
 * @generated
 */
public interface SkipStrategy extends EObject {
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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getSkipStrategy_Expression()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Is Create Skip Process</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 是否创建跳过处理过程
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Create Skip Process</em>' attribute.
	 * @see #setIsCreateSkipProcess(boolean)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getSkipStrategy_IsCreateSkipProcess()
	 * @model default="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	boolean isIsCreateSkipProcess();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#isIsCreateSkipProcess <em>Is Create Skip Process</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Create Skip Process</em>' attribute.
	 * @see #isIsCreateSkipProcess()
	 * @generated
	 */
	void setIsCreateSkipProcess(boolean value);

	/**
	 * Returns the value of the '<em><b>Skip Assignee</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skip Assignee</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skip Assignee</em>' containment reference.
	 * @see #setSkipAssignee(SkipAssignee)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getSkipStrategy_SkipAssignee()
	 * @model containment="true"
	 *        extendedMetaData="name='skipAssignee' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	SkipAssignee getSkipAssignee();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#getSkipAssignee <em>Skip Assignee</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skip Assignee</em>' containment reference.
	 * @see #getSkipAssignee()
	 * @generated
	 */
	void setSkipAssignee(SkipAssignee value);

	/**
	 * Returns the value of the '<em><b>Skip Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skip Comment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skip Comment</em>' containment reference.
	 * @see #setSkipComment(SkipComment)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getSkipStrategy_SkipComment()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	SkipComment getSkipComment();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#getSkipComment <em>Skip Comment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skip Comment</em>' containment reference.
	 * @see #getSkipComment()
	 * @generated
	 */
	void setSkipComment(SkipComment value);

	/**
	 * Returns the value of the '<em><b>Is Enable</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Enable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Enable</em>' attribute.
	 * @see #setIsEnable(boolean)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getSkipStrategy_IsEnable()
	 * @model default="false" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	boolean isIsEnable();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#isIsEnable <em>Is Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Enable</em>' attribute.
	 * @see #isIsEnable()
	 * @generated
	 */
	void setIsEnable(boolean value);

} // SkipStrategy
