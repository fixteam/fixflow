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
 * A representation of the model object '<em><b>Data Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.DataAssociation#getSourceRef <em>Source Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataAssociation#getTargetRef <em>Target Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataAssociation#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataAssociation#getAssignment <em>Assignment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getDataAssociation()
 * @model extendedMetaData="name='tDataAssociation' kind='elementOnly'"
 * @generated
 */
public interface DataAssociation extends BaseElement {
    /**
     * Returns the value of the '<em><b>Source Ref</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.ItemAwareElement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Ref</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Ref</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataAssociation_SourceRef()
     * @model resolveProxies="false" ordered="false"
     *        extendedMetaData="kind='element' name='sourceRef' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<ItemAwareElement> getSourceRef();

    /**
     * Returns the value of the '<em><b>Target Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Ref</em>' reference.
     * @see #setTargetRef(ItemAwareElement)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataAssociation_TargetRef()
     * @model resolveProxies="false" required="true" ordered="false"
     *        extendedMetaData="kind='element' name='targetRef' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ItemAwareElement getTargetRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DataAssociation#getTargetRef <em>Target Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Ref</em>' reference.
     * @see #getTargetRef()
     * @generated
     */
    void setTargetRef(ItemAwareElement value);

    /**
     * Returns the value of the '<em><b>Transformation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transformation</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Transformation</em>' containment reference.
     * @see #setTransformation(FormalExpression)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataAssociation_Transformation()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='transformation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    FormalExpression getTransformation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DataAssociation#getTransformation <em>Transformation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Transformation</em>' containment reference.
     * @see #getTransformation()
     * @generated
     */
    void setTransformation(FormalExpression value);

    /**
     * Returns the value of the '<em><b>Assignment</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.Assignment}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Assignment</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Assignment</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataAssociation_Assignment()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='assignment' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<Assignment> getAssignment();

} // DataAssociation
