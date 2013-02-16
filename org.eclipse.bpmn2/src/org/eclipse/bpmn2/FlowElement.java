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
 * A representation of the model object '<em><b>Flow Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.FlowElement#getAuditing <em>Auditing</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.FlowElement#getMonitoring <em>Monitoring</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.FlowElement#getCategoryValueRef <em>Category Value Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.FlowElement#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getFlowElement()
 * @model abstract="true"
 *        extendedMetaData="name='tFlowElement' kind='elementOnly'"
 * @generated
 */
public interface FlowElement extends BaseElement {
    /**
     * Returns the value of the '<em><b>Auditing</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Auditing</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Auditing</em>' containment reference.
     * @see #setAuditing(Auditing)
     * @see org.eclipse.bpmn2.Bpmn2Package#getFlowElement_Auditing()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='auditing' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Auditing getAuditing();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.FlowElement#getAuditing <em>Auditing</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Auditing</em>' containment reference.
     * @see #getAuditing()
     * @generated
     */
    void setAuditing(Auditing value);

    /**
     * Returns the value of the '<em><b>Monitoring</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Monitoring</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Monitoring</em>' containment reference.
     * @see #setMonitoring(Monitoring)
     * @see org.eclipse.bpmn2.Bpmn2Package#getFlowElement_Monitoring()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='monitoring' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Monitoring getMonitoring();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.FlowElement#getMonitoring <em>Monitoring</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Monitoring</em>' containment reference.
     * @see #getMonitoring()
     * @generated
     */
    void setMonitoring(Monitoring value);

    /**
     * Returns the value of the '<em><b>Category Value Ref</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.CategoryValue}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Category Value Ref</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Category Value Ref</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getFlowElement_CategoryValueRef()
     * @model ordered="false"
     *        extendedMetaData="kind='element' name='categoryValueRef' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<CategoryValue> getCategoryValueRef();

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getFlowElement_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.FlowElement#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // FlowElement
