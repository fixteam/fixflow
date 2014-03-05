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

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call Choreography</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.CallChoreography#getParticipantAssociations <em>Participant Associations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.CallChoreography#getCalledChoreographyRef <em>Called Choreography Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getCallChoreography()
 * @model extendedMetaData="name='tCallChoreography' kind='elementOnly'"
 * @generated
 */
public interface CallChoreography extends ChoreographyActivity {
    /**
     * Returns the value of the '<em><b>Participant Associations</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.ParticipantAssociation}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Participant Associations</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Participant Associations</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getCallChoreography_ParticipantAssociations()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='participantAssociation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<ParticipantAssociation> getParticipantAssociations();

    /**
     * Returns the value of the '<em><b>Called Choreography Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Called Choreography Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Called Choreography Ref</em>' reference.
     * @see #setCalledChoreographyRef(Choreography)
     * @see org.eclipse.bpmn2.Bpmn2Package#getCallChoreography_CalledChoreographyRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='calledChoreographyRef'"
     * @generated
     */
    Choreography getCalledChoreographyRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.CallChoreography#getCalledChoreographyRef <em>Called Choreography Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Called Choreography Ref</em>' reference.
     * @see #getCalledChoreographyRef()
     * @generated
     */
    void setCalledChoreographyRef(Choreography value);

} // CallChoreography
