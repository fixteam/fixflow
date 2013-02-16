/**
 * <copyright>
 * 
 * Copyright (c) 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Reiner Hille-Doering (SAP AG) - initial API and implementation and/or initial documentation
 * 
 * </copyright>
 */
package org.eclipse.bpmn2;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Receive Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.ReceiveTask#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ReceiveTask#isInstantiate <em>Instantiate</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ReceiveTask#getMessageRef <em>Message Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ReceiveTask#getOperationRef <em>Operation Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getReceiveTask()
 * @model extendedMetaData="name='tReceiveTask' kind='elementOnly'"
 * @generated
 */
public interface ReceiveTask extends Task {
    /**
     * Returns the value of the '<em><b>Implementation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Implementation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Implementation</em>' attribute.
     * @see #setImplementation(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getReceiveTask_Implementation()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='implementation'"
     * @generated
     */
    String getImplementation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ReceiveTask#getImplementation <em>Implementation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implementation</em>' attribute.
     * @see #getImplementation()
     * @generated
     */
    void setImplementation(String value);

    /**
     * Returns the value of the '<em><b>Instantiate</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Instantiate</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Instantiate</em>' attribute.
     * @see #setInstantiate(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getReceiveTask_Instantiate()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='instantiate'"
     * @generated
     */
    boolean isInstantiate();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ReceiveTask#isInstantiate <em>Instantiate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Instantiate</em>' attribute.
     * @see #isInstantiate()
     * @generated
     */
    void setInstantiate(boolean value);

    /**
     * Returns the value of the '<em><b>Message Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Ref</em>' reference.
     * @see #setMessageRef(Message)
     * @see org.eclipse.bpmn2.Bpmn2Package#getReceiveTask_MessageRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='messageRef'"
     * @generated
     */
    Message getMessageRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ReceiveTask#getMessageRef <em>Message Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Ref</em>' reference.
     * @see #getMessageRef()
     * @generated
     */
    void setMessageRef(Message value);

    /**
     * Returns the value of the '<em><b>Operation Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation Ref</em>' reference.
     * @see #setOperationRef(Operation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getReceiveTask_OperationRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='operationRef'"
     * @generated
     */
    Operation getOperationRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ReceiveTask#getOperationRef <em>Operation Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operation Ref</em>' reference.
     * @see #getOperationRef()
     * @generated
     */
    void setOperationRef(Operation value);

} // ReceiveTask
