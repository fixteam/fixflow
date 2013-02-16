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
 *
 */
package org.eclipse.bpmn2.di;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.dd.di.LabeledShape;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Shape</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNShape#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNShape#getBpmnElement <em>Bpmn Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNShape#getChoreographyActivityShape <em>Choreography Activity Shape</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNShape#isIsExpanded <em>Is Expanded</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNShape#isIsHorizontal <em>Is Horizontal</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNShape#isIsMarkerVisible <em>Is Marker Visible</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNShape#isIsMessageVisible <em>Is Message Visible</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNShape#getParticipantBandKind <em>Participant Band Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNShape()
 * @model extendedMetaData="name='BPMNShape' kind='elementOnly'"
 * @generated
 */
public interface BPMNShape extends LabeledShape {
    /**
     * Returns the value of the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' containment reference.
     * @see #setLabel(BPMNLabel)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNShape_Label()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='BPMNLabel' namespace='http://www.omg.org/spec/BPMN/20100524/DI'"
     * @generated
     */
    BPMNLabel getLabel();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNShape#getLabel <em>Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' containment reference.
     * @see #getLabel()
     * @generated
     */
    void setLabel(BPMNLabel value);

    /**
     * Returns the value of the '<em><b>Bpmn Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bpmn Element</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bpmn Element</em>' reference.
     * @see #setBpmnElement(BaseElement)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNShape_BpmnElement()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='bpmnElement'"
     * @generated
     */
    BaseElement getBpmnElement();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNShape#getBpmnElement <em>Bpmn Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bpmn Element</em>' reference.
     * @see #getBpmnElement()
     * @generated
     */
    void setBpmnElement(BaseElement value);

    /**
     * Returns the value of the '<em><b>Choreography Activity Shape</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Choreography Activity Shape</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Choreography Activity Shape</em>' reference.
     * @see #setChoreographyActivityShape(BPMNShape)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNShape_ChoreographyActivityShape()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='choreographyActivityShape'"
     * @generated
     */
    BPMNShape getChoreographyActivityShape();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNShape#getChoreographyActivityShape <em>Choreography Activity Shape</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Choreography Activity Shape</em>' reference.
     * @see #getChoreographyActivityShape()
     * @generated
     */
    void setChoreographyActivityShape(BPMNShape value);

    /**
     * Returns the value of the '<em><b>Is Expanded</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Expanded</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Expanded</em>' attribute.
     * @see #setIsExpanded(boolean)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNShape_IsExpanded()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='isExpanded'"
     * @generated
     */
    boolean isIsExpanded();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNShape#isIsExpanded <em>Is Expanded</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Expanded</em>' attribute.
     * @see #isIsExpanded()
     * @generated
     */
    void setIsExpanded(boolean value);

    /**
     * Returns the value of the '<em><b>Is Horizontal</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Horizontal</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Horizontal</em>' attribute.
     * @see #setIsHorizontal(boolean)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNShape_IsHorizontal()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='isHorizontal'"
     * @generated
     */
    boolean isIsHorizontal();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNShape#isIsHorizontal <em>Is Horizontal</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Horizontal</em>' attribute.
     * @see #isIsHorizontal()
     * @generated
     */
    void setIsHorizontal(boolean value);

    /**
     * Returns the value of the '<em><b>Is Marker Visible</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Marker Visible</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Marker Visible</em>' attribute.
     * @see #setIsMarkerVisible(boolean)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNShape_IsMarkerVisible()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='isMarkerVisible'"
     * @generated
     */
    boolean isIsMarkerVisible();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNShape#isIsMarkerVisible <em>Is Marker Visible</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Marker Visible</em>' attribute.
     * @see #isIsMarkerVisible()
     * @generated
     */
    void setIsMarkerVisible(boolean value);

    /**
     * Returns the value of the '<em><b>Is Message Visible</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Message Visible</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Message Visible</em>' attribute.
     * @see #setIsMessageVisible(boolean)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNShape_IsMessageVisible()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='isMessageVisible'"
     * @generated
     */
    boolean isIsMessageVisible();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNShape#isIsMessageVisible <em>Is Message Visible</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Message Visible</em>' attribute.
     * @see #isIsMessageVisible()
     * @generated
     */
    void setIsMessageVisible(boolean value);

    /**
     * Returns the value of the '<em><b>Participant Band Kind</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.bpmn2.di.ParticipantBandKind}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Participant Band Kind</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Participant Band Kind</em>' attribute.
     * @see org.eclipse.bpmn2.di.ParticipantBandKind
     * @see #setParticipantBandKind(ParticipantBandKind)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNShape_ParticipantBandKind()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='participantBandKind'"
     * @generated
     */
    ParticipantBandKind getParticipantBandKind();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNShape#getParticipantBandKind <em>Participant Band Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Participant Band Kind</em>' attribute.
     * @see org.eclipse.bpmn2.di.ParticipantBandKind
     * @see #getParticipantBandKind()
     * @generated
     */
    void setParticipantBandKind(ParticipantBandKind value);

} // BPMNShape
