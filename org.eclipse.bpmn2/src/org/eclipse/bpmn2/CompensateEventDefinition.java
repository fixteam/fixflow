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
 * A representation of the model object '<em><b>Compensate Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.CompensateEventDefinition#getActivityRef <em>Activity Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.CompensateEventDefinition#isWaitForCompletion <em>Wait For Completion</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getCompensateEventDefinition()
 * @model extendedMetaData="name='tCompensateEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface CompensateEventDefinition extends EventDefinition {
    /**
     * Returns the value of the '<em><b>Activity Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Activity Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Activity Ref</em>' reference.
     * @see #setActivityRef(Activity)
     * @see org.eclipse.bpmn2.Bpmn2Package#getCompensateEventDefinition_ActivityRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='activityRef'"
     * @generated
     */
    Activity getActivityRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.CompensateEventDefinition#getActivityRef <em>Activity Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Activity Ref</em>' reference.
     * @see #getActivityRef()
     * @generated
     */
    void setActivityRef(Activity value);

    /**
     * Returns the value of the '<em><b>Wait For Completion</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Wait For Completion</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Wait For Completion</em>' attribute.
     * @see #setWaitForCompletion(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getCompensateEventDefinition_WaitForCompletion()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='waitForCompletion'"
     * @generated
     */
    boolean isWaitForCompletion();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.CompensateEventDefinition#isWaitForCompletion <em>Wait For Completion</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Wait For Completion</em>' attribute.
     * @see #isWaitForCompletion()
     * @generated
     */
    void setWaitForCompletion(boolean value);

} // CompensateEventDefinition
