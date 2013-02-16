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
 * A representation of the model object '<em><b>Message Flow Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.MessageFlowAssociation#getInnerMessageFlowRef <em>Inner Message Flow Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.MessageFlowAssociation#getOuterMessageFlowRef <em>Outer Message Flow Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getMessageFlowAssociation()
 * @model extendedMetaData="name='tMessageFlowAssociation' kind='elementOnly'"
 * @generated
 */
public interface MessageFlowAssociation extends BaseElement {
    /**
     * Returns the value of the '<em><b>Inner Message Flow Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Inner Message Flow Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Inner Message Flow Ref</em>' reference.
     * @see #setInnerMessageFlowRef(MessageFlow)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMessageFlowAssociation_InnerMessageFlowRef()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='innerMessageFlowRef'"
     * @generated
     */
    MessageFlow getInnerMessageFlowRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.MessageFlowAssociation#getInnerMessageFlowRef <em>Inner Message Flow Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Inner Message Flow Ref</em>' reference.
     * @see #getInnerMessageFlowRef()
     * @generated
     */
    void setInnerMessageFlowRef(MessageFlow value);

    /**
     * Returns the value of the '<em><b>Outer Message Flow Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Outer Message Flow Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outer Message Flow Ref</em>' reference.
     * @see #setOuterMessageFlowRef(MessageFlow)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMessageFlowAssociation_OuterMessageFlowRef()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='outerMessageFlowRef'"
     * @generated
     */
    MessageFlow getOuterMessageFlowRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.MessageFlowAssociation#getOuterMessageFlowRef <em>Outer Message Flow Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Outer Message Flow Ref</em>' reference.
     * @see #getOuterMessageFlowRef()
     * @generated
     */
    void setOuterMessageFlowRef(MessageFlow value);

} // MessageFlowAssociation
