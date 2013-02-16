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
 * A representation of the model object '<em><b>Resource Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.ResourceParameter#isIsRequired <em>Is Required</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ResourceParameter#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ResourceParameter#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getResourceParameter()
 * @model extendedMetaData="name='tResourceParameter' kind='elementOnly'"
 * @generated
 */
public interface ResourceParameter extends BaseElement {
    /**
     * Returns the value of the '<em><b>Is Required</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Required</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Required</em>' attribute.
     * @see #setIsRequired(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getResourceParameter_IsRequired()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='isRequired'"
     * @generated
     */
    boolean isIsRequired();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ResourceParameter#isIsRequired <em>Is Required</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Required</em>' attribute.
     * @see #isIsRequired()
     * @generated
     */
    void setIsRequired(boolean value);

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getResourceParameter_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ResourceParameter#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' reference.
     * @see #setType(ItemDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getResourceParameter_Type()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='type'"
     * @generated
     */
    ItemDefinition getType();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ResourceParameter#getType <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
    void setType(ItemDefinition value);

} // ResourceParameter
