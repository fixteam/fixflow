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
 * A representation of the model object '<em><b>Item Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.ItemDefinition#isIsCollection <em>Is Collection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemDefinition#getImport <em>Import</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemDefinition#getItemKind <em>Item Kind</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemDefinition#getStructureRef <em>Structure Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getItemDefinition()
 * @model extendedMetaData="name='tItemDefinition' kind='elementOnly'"
 * @generated
 */
public interface ItemDefinition extends RootElement {
    /**
     * Returns the value of the '<em><b>Is Collection</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Collection</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Collection</em>' attribute.
     * @see #setIsCollection(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemDefinition_IsCollection()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='isCollection'"
     * @generated
     */
    boolean isIsCollection();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemDefinition#isIsCollection <em>Is Collection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Collection</em>' attribute.
     * @see #isIsCollection()
     * @generated
     */
    void setIsCollection(boolean value);

    /**
     * Returns the value of the '<em><b>Import</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Import</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Import</em>' reference.
     * @see #setImport(Import)
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemDefinition_Import()
     * @model transient="true" derived="true" ordered="false"
     * @generated
     */
    Import getImport();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemDefinition#getImport <em>Import</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Import</em>' reference.
     * @see #getImport()
     * @generated
     */
    void setImport(Import value);

    /**
     * Returns the value of the '<em><b>Item Kind</b></em>' attribute.
     * The default value is <code>"Information"</code>.
     * The literals are from the enumeration {@link org.eclipse.bpmn2.ItemKind}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Item Kind</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Item Kind</em>' attribute.
     * @see org.eclipse.bpmn2.ItemKind
     * @see #setItemKind(ItemKind)
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemDefinition_ItemKind()
     * @model default="Information" ordered="false"
     *        extendedMetaData="kind='attribute' name='itemKind'"
     * @generated
     */
    ItemKind getItemKind();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemDefinition#getItemKind <em>Item Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Item Kind</em>' attribute.
     * @see org.eclipse.bpmn2.ItemKind
     * @see #getItemKind()
     * @generated
     */
    void setItemKind(ItemKind value);

    /**
     * Returns the value of the '<em><b>Structure Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Structure Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Structure Ref</em>' reference.
     * @see #setStructureRef(Object)
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemDefinition_StructureRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='structureRef'"
     * @generated
     */
    Object getStructureRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemDefinition#getStructureRef <em>Structure Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Structure Ref</em>' reference.
     * @see #getStructureRef()
     * @generated
     */
    void setStructureRef(Object value);

} // ItemDefinition
