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
 * A representation of the model object '<em><b>Flow Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.FlowNode#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.FlowNode#getLanes <em>Lanes</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.FlowNode#getOutgoing <em>Outgoing</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getFlowNode()
 * @model abstract="true"
 *        extendedMetaData="name='tFlowNode' kind='elementOnly'"
 * @generated
 */
public interface FlowNode extends FlowElement {
    /**
     * Returns the value of the '<em><b>Incoming</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.SequenceFlow}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.SequenceFlow#getTargetRef <em>Target Ref</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Incoming</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Incoming</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getFlowNode_Incoming()
     * @see org.eclipse.bpmn2.SequenceFlow#getTargetRef
     * @model opposite="targetRef" resolveProxies="false" ordered="false"
     *        extendedMetaData="kind='element' name='incoming' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<SequenceFlow> getIncoming();

    /**
     * Returns the value of the '<em><b>Lanes</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.Lane}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.Lane#getFlowNodeRefs <em>Flow Node Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lanes</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lanes</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getFlowNode_Lanes()
     * @see org.eclipse.bpmn2.Lane#getFlowNodeRefs
     * @model opposite="flowNodeRefs" transient="true" derived="true" ordered="false"
     * @generated
     */
    List<Lane> getLanes();

    /**
     * Returns the value of the '<em><b>Outgoing</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.SequenceFlow}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.SequenceFlow#getSourceRef <em>Source Ref</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Outgoing</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outgoing</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getFlowNode_Outgoing()
     * @see org.eclipse.bpmn2.SequenceFlow#getSourceRef
     * @model opposite="sourceRef" resolveProxies="false"
     *        extendedMetaData="kind='element' name='outgoing' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<SequenceFlow> getOutgoing();

} // FlowNode
