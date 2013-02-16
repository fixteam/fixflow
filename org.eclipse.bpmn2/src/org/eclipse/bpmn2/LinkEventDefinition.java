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
 * A representation of the model object '<em><b>Link Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.LinkEventDefinition#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.LinkEventDefinition#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.LinkEventDefinition#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getLinkEventDefinition()
 * @model extendedMetaData="name='tLinkEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface LinkEventDefinition extends EventDefinition {
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.LinkEventDefinition}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.LinkEventDefinition#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getLinkEventDefinition_Source()
     * @see org.eclipse.bpmn2.LinkEventDefinition#getTarget
     * @model opposite="target" ordered="false"
     *        extendedMetaData="kind='element' name='source' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<LinkEventDefinition> getSource();

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.LinkEventDefinition#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(LinkEventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getLinkEventDefinition_Target()
     * @see org.eclipse.bpmn2.LinkEventDefinition#getSource
     * @model opposite="source" ordered="false"
     *        extendedMetaData="kind='element' name='target' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    LinkEventDefinition getTarget();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.LinkEventDefinition#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(LinkEventDefinition value);

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getLinkEventDefinition_Name()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.LinkEventDefinition#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // LinkEventDefinition
