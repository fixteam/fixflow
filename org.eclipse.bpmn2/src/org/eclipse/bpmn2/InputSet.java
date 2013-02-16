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
 * A representation of the model object '<em><b>Input Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.InputSet#getDataInputRefs <em>Data Input Refs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.InputSet#getOptionalInputRefs <em>Optional Input Refs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.InputSet#getWhileExecutingInputRefs <em>While Executing Input Refs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.InputSet#getOutputSetRefs <em>Output Set Refs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.InputSet#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getInputSet()
 * @model extendedMetaData="name='tInputSet' kind='elementOnly'"
 * @generated
 */
public interface InputSet extends BaseElement {
    /**
     * Returns the value of the '<em><b>Data Input Refs</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.DataInput}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.DataInput#getInputSetRefs <em>Input Set Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Input Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Input Refs</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getInputSet_DataInputRefs()
     * @see org.eclipse.bpmn2.DataInput#getInputSetRefs
     * @model opposite="inputSetRefs" resolveProxies="false" ordered="false"
     *        extendedMetaData="kind='element' name='dataInputRefs' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<DataInput> getDataInputRefs();

    /**
     * Returns the value of the '<em><b>Optional Input Refs</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.DataInput}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.DataInput#getInputSetWithOptional <em>Input Set With Optional</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Optional Input Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Optional Input Refs</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getInputSet_OptionalInputRefs()
     * @see org.eclipse.bpmn2.DataInput#getInputSetWithOptional
     * @model opposite="inputSetWithOptional" resolveProxies="false" ordered="false"
     *        extendedMetaData="kind='element' name='optionalInputRefs' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<DataInput> getOptionalInputRefs();

    /**
     * Returns the value of the '<em><b>While Executing Input Refs</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.DataInput}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.DataInput#getInputSetWithWhileExecuting <em>Input Set With While Executing</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>While Executing Input Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>While Executing Input Refs</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getInputSet_WhileExecutingInputRefs()
     * @see org.eclipse.bpmn2.DataInput#getInputSetWithWhileExecuting
     * @model opposite="inputSetWithWhileExecuting" resolveProxies="false" ordered="false"
     *        extendedMetaData="kind='element' name='whileExecutingInputRefs' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<DataInput> getWhileExecutingInputRefs();

    /**
     * Returns the value of the '<em><b>Output Set Refs</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.OutputSet}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.OutputSet#getInputSetRefs <em>Input Set Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Output Set Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output Set Refs</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getInputSet_OutputSetRefs()
     * @see org.eclipse.bpmn2.OutputSet#getInputSetRefs
     * @model opposite="inputSetRefs" resolveProxies="false" ordered="false"
     *        extendedMetaData="kind='element' name='outputSetRefs' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<OutputSet> getOutputSetRefs();

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getInputSet_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.InputSet#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // InputSet
