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
 * A representation of the model object '<em><b>Event Based Gateway</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.EventBasedGateway#getEventGatewayType <em>Event Gateway Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.EventBasedGateway#isInstantiate <em>Instantiate</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getEventBasedGateway()
 * @model extendedMetaData="name='tEventBasedGateway' kind='elementOnly'"
 * @generated
 */
public interface EventBasedGateway extends Gateway {
    /**
     * Returns the value of the '<em><b>Event Gateway Type</b></em>' attribute.
     * The default value is <code>"Exclusive"</code>.
     * The literals are from the enumeration {@link org.eclipse.bpmn2.EventBasedGatewayType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Event Gateway Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Event Gateway Type</em>' attribute.
     * @see org.eclipse.bpmn2.EventBasedGatewayType
     * @see #setEventGatewayType(EventBasedGatewayType)
     * @see org.eclipse.bpmn2.Bpmn2Package#getEventBasedGateway_EventGatewayType()
     * @model default="Exclusive" ordered="false"
     *        extendedMetaData="kind='attribute' name='eventGatewayType'"
     * @generated
     */
    EventBasedGatewayType getEventGatewayType();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.EventBasedGateway#getEventGatewayType <em>Event Gateway Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Event Gateway Type</em>' attribute.
     * @see org.eclipse.bpmn2.EventBasedGatewayType
     * @see #getEventGatewayType()
     * @generated
     */
    void setEventGatewayType(EventBasedGatewayType value);

    /**
     * Returns the value of the '<em><b>Instantiate</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Instantiate</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Instantiate</em>' attribute.
     * @see #setInstantiate(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getEventBasedGateway_Instantiate()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='instantiate'"
     * @generated
     */
    boolean isInstantiate();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.EventBasedGateway#isInstantiate <em>Instantiate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Instantiate</em>' attribute.
     * @see #isInstantiate()
     * @generated
     */
    void setInstantiate(boolean value);

} // EventBasedGateway
