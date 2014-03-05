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
 * A representation of the model object '<em><b>Flow Elements Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.FlowElementsContainer#getLaneSets <em>Lane Sets</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.FlowElementsContainer#getFlowElements <em>Flow Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getFlowElementsContainer()
 * @model abstract="true"
 * @generated
 */
public interface FlowElementsContainer extends BaseElement {
    /**
     * Returns the value of the '<em><b>Lane Sets</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.LaneSet}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lane Sets</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lane Sets</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getFlowElementsContainer_LaneSets()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='laneSet' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<LaneSet> getLaneSets();

    /**
     * Returns the value of the '<em><b>Flow Elements</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.FlowElement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Flow Elements</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Flow Elements</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getFlowElementsContainer_FlowElements()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='flowElement' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' group='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    List<FlowElement> getFlowElements();

} // FlowElementsContainer
