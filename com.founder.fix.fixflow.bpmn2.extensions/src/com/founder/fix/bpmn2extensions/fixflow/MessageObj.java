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
 * A representation of the model object '<em><b>Message Obj</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getId <em>Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getName <em>Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getDataVariable <em>Data Variable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getTargetProcess <em>Target Process</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getTargetNode <em>Target Node</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getProcessInstanceVariable <em>Process Instance Variable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getTokenVariable <em>Token Variable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getMessageType <em>Message Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getMessageObj()
 * @model extendedMetaData="name='messageObj'"
 * @generated
 */
public interface MessageObj extends EObject {
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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getMessageObj_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getMessageObj_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="name='name' kind='attribute'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' containment reference.
	 * @see #setDocumentation(Documentation)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getMessageObj_Documentation()
	 * @model containment="true"
	 *        extendedMetaData="name='documentation' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	Documentation getDocumentation();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getDocumentation <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' containment reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(Documentation value);

	/**
	 * Returns the value of the '<em><b>Data Variable</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.fixflow.DataVariable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Variable</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Variable</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getMessageObj_DataVariable()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<DataVariable> getDataVariable();

	/**
	 * Returns the value of the '<em><b>Target Process</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Process</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Process</em>' attribute.
	 * @see #setTargetProcess(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getMessageObj_TargetProcess()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getTargetProcess();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getTargetProcess <em>Target Process</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Process</em>' attribute.
	 * @see #getTargetProcess()
	 * @generated
	 */
	void setTargetProcess(String value);

	/**
	 * Returns the value of the '<em><b>Target Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Node</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Node</em>' attribute.
	 * @see #setTargetNode(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getMessageObj_TargetNode()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="name='targetNode' kind='attribute'"
	 * @generated
	 */
	String getTargetNode();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getTargetNode <em>Target Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Node</em>' attribute.
	 * @see #getTargetNode()
	 * @generated
	 */
	void setTargetNode(String value);

	/**
	 * Returns the value of the '<em><b>Process Instance Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Instance Variable</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Instance Variable</em>' containment reference.
	 * @see #setProcessInstanceVariable(ProcessInstanceVariable)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getMessageObj_ProcessInstanceVariable()
	 * @model containment="true"
	 *        extendedMetaData="name='processInstanceVariable' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	ProcessInstanceVariable getProcessInstanceVariable();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getProcessInstanceVariable <em>Process Instance Variable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process Instance Variable</em>' containment reference.
	 * @see #getProcessInstanceVariable()
	 * @generated
	 */
	void setProcessInstanceVariable(ProcessInstanceVariable value);

	/**
	 * Returns the value of the '<em><b>Token Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Token Variable</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Token Variable</em>' containment reference.
	 * @see #setTokenVariable(TokenVariable)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getMessageObj_TokenVariable()
	 * @model containment="true"
	 *        extendedMetaData="name='tokenVariable' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	TokenVariable getTokenVariable();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getTokenVariable <em>Token Variable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Token Variable</em>' containment reference.
	 * @see #getTokenVariable()
	 * @generated
	 */
	void setTokenVariable(TokenVariable value);

	/**
	 * Returns the value of the '<em><b>Message Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Type</em>' attribute.
	 * @see #setMessageType(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getMessageObj_MessageType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getMessageType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getMessageType <em>Message Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Type</em>' attribute.
	 * @see #getMessageType()
	 * @generated
	 */
	void setMessageType(String value);

} // MessageObj
