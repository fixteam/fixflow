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
 * A representation of the model object '<em><b>Participant Multiplicity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.ParticipantMultiplicity#getMaximum <em>Maximum</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ParticipantMultiplicity#getMinimum <em>Minimum</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getParticipantMultiplicity()
 * @model extendedMetaData="name='tParticipantMultiplicity' kind='elementOnly'"
 * @generated
 */
public interface ParticipantMultiplicity extends BaseElement {
    /**
     * Returns the value of the '<em><b>Maximum</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Maximum</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Maximum</em>' attribute.
     * @see #setMaximum(int)
     * @see org.eclipse.bpmn2.Bpmn2Package#getParticipantMultiplicity_Maximum()
     * @model default="1" ordered="false"
     *        extendedMetaData="kind='attribute' name='maximum'"
     * @generated
     */
    int getMaximum();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ParticipantMultiplicity#getMaximum <em>Maximum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Maximum</em>' attribute.
     * @see #getMaximum()
     * @generated
     */
    void setMaximum(int value);

    /**
     * Returns the value of the '<em><b>Minimum</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Minimum</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Minimum</em>' attribute.
     * @see #setMinimum(int)
     * @see org.eclipse.bpmn2.Bpmn2Package#getParticipantMultiplicity_Minimum()
     * @model default="0" ordered="false"
     *        extendedMetaData="kind='attribute' name='minimum'"
     * @generated
     */
    int getMinimum();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ParticipantMultiplicity#getMinimum <em>Minimum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Minimum</em>' attribute.
     * @see #getMinimum()
     * @generated
     */
    void setMinimum(int value);

} // ParticipantMultiplicity
