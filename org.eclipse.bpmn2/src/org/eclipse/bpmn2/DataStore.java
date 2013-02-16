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
 * A representation of the model object '<em><b>Data Store</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.DataStore#getCapacity <em>Capacity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataStore#isIsUnlimited <em>Is Unlimited</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataStore#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getDataStore()
 * @model extendedMetaData="name='tDataStore' kind='elementOnly'"
 * @generated
 */
public interface DataStore extends ItemAwareElement, RootElement {
    /**
     * Returns the value of the '<em><b>Capacity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Capacity</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Capacity</em>' attribute.
     * @see #setCapacity(int)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataStore_Capacity()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='capacity'"
     * @generated
     */
    int getCapacity();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DataStore#getCapacity <em>Capacity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Capacity</em>' attribute.
     * @see #getCapacity()
     * @generated
     */
    void setCapacity(int value);

    /**
     * Returns the value of the '<em><b>Is Unlimited</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Unlimited</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Unlimited</em>' attribute.
     * @see #setIsUnlimited(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataStore_IsUnlimited()
     * @model default="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='isUnlimited'"
     * @generated
     */
    boolean isIsUnlimited();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DataStore#isIsUnlimited <em>Is Unlimited</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Unlimited</em>' attribute.
     * @see #isIsUnlimited()
     * @generated
     */
    void setIsUnlimited(boolean value);

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataStore_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DataStore#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // DataStore
