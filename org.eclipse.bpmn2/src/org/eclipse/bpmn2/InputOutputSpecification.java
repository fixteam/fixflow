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
 * A representation of the model object '<em><b>Input Output Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.InputOutputSpecification#getDataInputs <em>Data Inputs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.InputOutputSpecification#getDataOutputs <em>Data Outputs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.InputOutputSpecification#getInputSets <em>Input Sets</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.InputOutputSpecification#getOutputSets <em>Output Sets</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getInputOutputSpecification()
 * @model extendedMetaData="name='tInputOutputSpecification' kind='elementOnly'"
 * @generated
 */
public interface InputOutputSpecification extends BaseElement {
    /**
     * Returns the value of the '<em><b>Data Inputs</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.DataInput}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Inputs</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Inputs</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getInputOutputSpecification_DataInputs()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='dataInput' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<DataInput> getDataInputs();

    /**
     * Returns the value of the '<em><b>Data Outputs</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.DataOutput}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Outputs</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Outputs</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getInputOutputSpecification_DataOutputs()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='dataOutput' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<DataOutput> getDataOutputs();

    /**
     * Returns the value of the '<em><b>Input Sets</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.InputSet}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Sets</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Sets</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getInputOutputSpecification_InputSets()
     * @model containment="true" required="true" ordered="false"
     *        extendedMetaData="kind='element' name='inputSet' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<InputSet> getInputSets();

    /**
     * Returns the value of the '<em><b>Output Sets</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.OutputSet}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Output Sets</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output Sets</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getInputOutputSpecification_OutputSets()
     * @model containment="true" required="true" ordered="false"
     *        extendedMetaData="kind='element' name='outputSet' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<OutputSet> getOutputSets();

} // InputOutputSpecification
