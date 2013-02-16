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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.Relationship#getSources <em>Sources</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Relationship#getTargets <em>Targets</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Relationship#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Relationship#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getRelationship()
 * @model extendedMetaData="name='tRelationship' kind='elementOnly'"
 * @generated
 */
public interface Relationship extends BaseElement {
    /**
     * Returns the value of the '<em><b>Sources</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sources</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sources</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getRelationship_Sources()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='element' name='source' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<EObject> getSources();

    /**
     * Returns the value of the '<em><b>Targets</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Targets</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Targets</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getRelationship_Targets()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='element' name='target' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<EObject> getTargets();

    /**
     * Returns the value of the '<em><b>Direction</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.bpmn2.RelationshipDirection}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Direction</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Direction</em>' attribute.
     * @see org.eclipse.bpmn2.RelationshipDirection
     * @see #setDirection(RelationshipDirection)
     * @see org.eclipse.bpmn2.Bpmn2Package#getRelationship_Direction()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='direction'"
     * @generated
     */
    RelationshipDirection getDirection();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Relationship#getDirection <em>Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Direction</em>' attribute.
     * @see org.eclipse.bpmn2.RelationshipDirection
     * @see #getDirection()
     * @generated
     */
    void setDirection(RelationshipDirection value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getRelationship_Type()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='type'"
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Relationship#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(String value);

} // Relationship
