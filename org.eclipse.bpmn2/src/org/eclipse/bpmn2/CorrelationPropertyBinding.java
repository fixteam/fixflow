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
 * A representation of the model object '<em><b>Correlation Property Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.CorrelationPropertyBinding#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.CorrelationPropertyBinding#getCorrelationPropertyRef <em>Correlation Property Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getCorrelationPropertyBinding()
 * @model extendedMetaData="name='tCorrelationPropertyBinding' kind='elementOnly'"
 * @generated
 */
public interface CorrelationPropertyBinding extends BaseElement {
    /**
     * Returns the value of the '<em><b>Data Path</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Path</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Path</em>' containment reference.
     * @see #setDataPath(FormalExpression)
     * @see org.eclipse.bpmn2.Bpmn2Package#getCorrelationPropertyBinding_DataPath()
     * @model containment="true" required="true" ordered="false"
     *        extendedMetaData="kind='element' name='dataPath' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    FormalExpression getDataPath();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.CorrelationPropertyBinding#getDataPath <em>Data Path</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Path</em>' containment reference.
     * @see #getDataPath()
     * @generated
     */
    void setDataPath(FormalExpression value);

    /**
     * Returns the value of the '<em><b>Correlation Property Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Correlation Property Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Property Ref</em>' reference.
     * @see #setCorrelationPropertyRef(CorrelationProperty)
     * @see org.eclipse.bpmn2.Bpmn2Package#getCorrelationPropertyBinding_CorrelationPropertyRef()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='correlationPropertyRef'"
     * @generated
     */
    CorrelationProperty getCorrelationPropertyRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.CorrelationPropertyBinding#getCorrelationPropertyRef <em>Correlation Property Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation Property Ref</em>' reference.
     * @see #getCorrelationPropertyRef()
     * @generated
     */
    void setCorrelationPropertyRef(CorrelationProperty value);

} // CorrelationPropertyBinding
