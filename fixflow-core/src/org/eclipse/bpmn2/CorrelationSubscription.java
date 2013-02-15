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
 * A representation of the model object '<em><b>Correlation Subscription</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.CorrelationSubscription#getCorrelationPropertyBinding <em>Correlation Property Binding</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.CorrelationSubscription#getCorrelationKeyRef <em>Correlation Key Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getCorrelationSubscription()
 * @model extendedMetaData="name='tCorrelationSubscription' kind='elementOnly'"
 * @generated
 */
public interface CorrelationSubscription extends BaseElement {
    /**
     * Returns the value of the '<em><b>Correlation Property Binding</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.CorrelationPropertyBinding}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Correlation Property Binding</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Property Binding</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getCorrelationSubscription_CorrelationPropertyBinding()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='correlationPropertyBinding' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<CorrelationPropertyBinding> getCorrelationPropertyBinding();

    /**
     * Returns the value of the '<em><b>Correlation Key Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Correlation Key Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Key Ref</em>' reference.
     * @see #setCorrelationKeyRef(CorrelationKey)
     * @see org.eclipse.bpmn2.Bpmn2Package#getCorrelationSubscription_CorrelationKeyRef()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='correlationKeyRef'"
     * @generated
     */
    CorrelationKey getCorrelationKeyRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.CorrelationSubscription#getCorrelationKeyRef <em>Correlation Key Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation Key Ref</em>' reference.
     * @see #getCorrelationKeyRef()
     * @generated
     */
    void setCorrelationKeyRef(CorrelationKey value);

} // CorrelationSubscription
