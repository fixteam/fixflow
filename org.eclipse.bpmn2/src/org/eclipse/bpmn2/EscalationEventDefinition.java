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
 * A representation of the model object '<em><b>Escalation Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.EscalationEventDefinition#getEscalationRef <em>Escalation Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getEscalationEventDefinition()
 * @model extendedMetaData="name='tEscalationEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface EscalationEventDefinition extends EventDefinition {
    /**
     * Returns the value of the '<em><b>Escalation Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Escalation Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Escalation Ref</em>' reference.
     * @see #setEscalationRef(Escalation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getEscalationEventDefinition_EscalationRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='escalationRef'"
     * @generated
     */
    Escalation getEscalationRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.EscalationEventDefinition#getEscalationRef <em>Escalation Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Escalation Ref</em>' reference.
     * @see #getEscalationRef()
     * @generated
     */
    void setEscalationRef(Escalation value);

} // EscalationEventDefinition
