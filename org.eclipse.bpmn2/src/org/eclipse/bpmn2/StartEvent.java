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
 * A representation of the model object '<em><b>Start Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.StartEvent#isIsInterrupting <em>Is Interrupting</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getStartEvent()
 * @model extendedMetaData="name='tStartEvent' kind='elementOnly'"
 * @generated
 */
public interface StartEvent extends CatchEvent {
    /**
     * Returns the value of the '<em><b>Is Interrupting</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Interrupting</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Interrupting</em>' attribute.
     * @see #setIsInterrupting(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getStartEvent_IsInterrupting()
     * @model default="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='isInterrupting'"
     * @generated
     */
    boolean isIsInterrupting();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.StartEvent#isIsInterrupting <em>Is Interrupting</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Interrupting</em>' attribute.
     * @see #isIsInterrupting()
     * @generated
     */
    void setIsInterrupting(boolean value);

} // StartEvent
