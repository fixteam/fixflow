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
 * A representation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.Category#getCategoryValue <em>Category Value</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Category#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getCategory()
 * @model extendedMetaData="name='tCategory' kind='elementOnly'"
 * @generated
 */
public interface Category extends RootElement {
    /**
     * Returns the value of the '<em><b>Category Value</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.CategoryValue}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Category Value</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Category Value</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getCategory_CategoryValue()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='categoryValue' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<CategoryValue> getCategoryValue();

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getCategory_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Category#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // Category
