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
 * A representation of the model object '<em><b>Escalation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.Escalation#getEscalationCode <em>Escalation Code</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Escalation#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Escalation#getStructureRef <em>Structure Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getEscalation()
 * @model extendedMetaData="name='tEscalation' kind='elementOnly'"
 * @generated
 */
public interface Escalation extends RootElement {
    /**
     * Returns the value of the '<em><b>Escalation Code</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Escalation Code</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Escalation Code</em>' attribute.
     * @see #setEscalationCode(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getEscalation_EscalationCode()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='escalationCode'"
     * @generated
     */
    String getEscalationCode();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Escalation#getEscalationCode <em>Escalation Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Escalation Code</em>' attribute.
     * @see #getEscalationCode()
     * @generated
     */
    void setEscalationCode(String value);

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getEscalation_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Escalation#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Structure Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Structure Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Structure Ref</em>' reference.
     * @see #setStructureRef(ItemDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getEscalation_StructureRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='structureRef'"
     * @generated
     */
    ItemDefinition getStructureRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Escalation#getStructureRef <em>Structure Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Structure Ref</em>' reference.
     * @see #getStructureRef()
     * @generated
     */
    void setStructureRef(ItemDefinition value);

} // Escalation
