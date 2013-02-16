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
 * A representation of the model object '<em><b>Input Output Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.InputOutputBinding#getInputDataRef <em>Input Data Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.InputOutputBinding#getOperationRef <em>Operation Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.InputOutputBinding#getOutputDataRef <em>Output Data Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getInputOutputBinding()
 * @model extendedMetaData="name='tInputOutputBinding' kind='elementOnly'"
 * @generated
 */
public interface InputOutputBinding extends BaseElement {
    /**
     * Returns the value of the '<em><b>Input Data Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Data Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Data Ref</em>' reference.
     * @see #setInputDataRef(InputSet)
     * @see org.eclipse.bpmn2.Bpmn2Package#getInputOutputBinding_InputDataRef()
     * @model resolveProxies="false" required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='inputDataRef'"
     * @generated
     */
    InputSet getInputDataRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.InputOutputBinding#getInputDataRef <em>Input Data Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Data Ref</em>' reference.
     * @see #getInputDataRef()
     * @generated
     */
    void setInputDataRef(InputSet value);

    /**
     * Returns the value of the '<em><b>Operation Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation Ref</em>' reference.
     * @see #setOperationRef(Operation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getInputOutputBinding_OperationRef()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='operationRef'"
     * @generated
     */
    Operation getOperationRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.InputOutputBinding#getOperationRef <em>Operation Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operation Ref</em>' reference.
     * @see #getOperationRef()
     * @generated
     */
    void setOperationRef(Operation value);

    /**
     * Returns the value of the '<em><b>Output Data Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Output Data Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output Data Ref</em>' reference.
     * @see #setOutputDataRef(OutputSet)
     * @see org.eclipse.bpmn2.Bpmn2Package#getInputOutputBinding_OutputDataRef()
     * @model resolveProxies="false" required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='outputDataRef'"
     * @generated
     */
    OutputSet getOutputDataRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.InputOutputBinding#getOutputDataRef <em>Output Data Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output Data Ref</em>' reference.
     * @see #getOutputDataRef()
     * @generated
     */
    void setOutputDataRef(OutputSet value);

} // InputOutputBinding
