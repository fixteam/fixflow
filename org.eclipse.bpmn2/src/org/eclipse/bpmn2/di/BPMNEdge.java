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
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.dd.di.LabeledEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNEdge#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNEdge#getBpmnElement <em>Bpmn Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNEdge#getMessageVisibleKind <em>Message Visible Kind</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNEdge#getSourceElement <em>Source Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNEdge#getTargetElement <em>Target Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNEdge()
 * @model extendedMetaData="name='BPMNEdge' kind='elementOnly'"
 * @generated
 */
public interface BPMNEdge extends LabeledEdge {
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
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNEdge_Label()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='BPMNLabel' namespace='http://www.omg.org/spec/BPMN/20100524/DI'"
     * @generated
     */
    BPMNLabel getLabel();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNEdge#getLabel <em>Label</em>}' containment reference.
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
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNEdge_BpmnElement()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='bpmnElement'"
     * @generated
     */
    BaseElement getBpmnElement();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNEdge#getBpmnElement <em>Bpmn Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bpmn Element</em>' reference.
     * @see #getBpmnElement()
     * @generated
     */
    void setBpmnElement(BaseElement value);

    /**
     * Returns the value of the '<em><b>Message Visible Kind</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.bpmn2.di.MessageVisibleKind}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message Visible Kind</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Visible Kind</em>' attribute.
     * @see org.eclipse.bpmn2.di.MessageVisibleKind
     * @see #setMessageVisibleKind(MessageVisibleKind)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNEdge_MessageVisibleKind()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='messageVisibleKind'"
     * @generated
     */
    MessageVisibleKind getMessageVisibleKind();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNEdge#getMessageVisibleKind <em>Message Visible Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Visible Kind</em>' attribute.
     * @see org.eclipse.bpmn2.di.MessageVisibleKind
     * @see #getMessageVisibleKind()
     * @generated
     */
    void setMessageVisibleKind(MessageVisibleKind value);

    /**
     * Returns the value of the '<em><b>Source Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Element</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Element</em>' reference.
     * @see #setSourceElement(DiagramElement)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNEdge_SourceElement()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='sourceElement'"
     * @generated
     */
    DiagramElement getSourceElement();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNEdge#getSourceElement <em>Source Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Element</em>' reference.
     * @see #getSourceElement()
     * @generated
     */
    void setSourceElement(DiagramElement value);

    /**
     * Returns the value of the '<em><b>Target Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Element</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Element</em>' reference.
     * @see #setTargetElement(DiagramElement)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNEdge_TargetElement()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='targetElement'"
     * @generated
     */
    DiagramElement getTargetElement();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNEdge#getTargetElement <em>Target Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Element</em>' reference.
     * @see #getTargetElement()
     * @generated
     */
    void setTargetElement(DiagramElement value);

} // BPMNEdge
