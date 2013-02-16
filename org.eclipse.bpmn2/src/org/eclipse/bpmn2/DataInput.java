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
 * A representation of the model object '<em><b>Data Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.DataInput#getInputSetWithOptional <em>Input Set With Optional</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataInput#getInputSetWithWhileExecuting <em>Input Set With While Executing</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataInput#getInputSetRefs <em>Input Set Refs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataInput#isIsCollection <em>Is Collection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataInput#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getDataInput()
 * @model extendedMetaData="name='tDataInput' kind='elementOnly'"
 * @generated
 */
public interface DataInput extends ItemAwareElement {
    /**
     * Returns the value of the '<em><b>Input Set With Optional</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.InputSet}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.InputSet#getOptionalInputRefs <em>Optional Input Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Set With Optional</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Set With Optional</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataInput_InputSetWithOptional()
     * @see org.eclipse.bpmn2.InputSet#getOptionalInputRefs
     * @model opposite="optionalInputRefs" transient="true" derived="true" ordered="false"
     * @generated
     */
    List<InputSet> getInputSetWithOptional();

    /**
     * Returns the value of the '<em><b>Input Set With While Executing</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.InputSet}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.InputSet#getWhileExecutingInputRefs <em>While Executing Input Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Set With While Executing</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Set With While Executing</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataInput_InputSetWithWhileExecuting()
     * @see org.eclipse.bpmn2.InputSet#getWhileExecutingInputRefs
     * @model opposite="whileExecutingInputRefs" transient="true" derived="true" ordered="false"
     * @generated
     */
    List<InputSet> getInputSetWithWhileExecuting();

    /**
     * Returns the value of the '<em><b>Input Set Refs</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.InputSet}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.InputSet#getDataInputRefs <em>Data Input Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Set Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Set Refs</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataInput_InputSetRefs()
     * @see org.eclipse.bpmn2.InputSet#getDataInputRefs
     * @model opposite="dataInputRefs" required="true" transient="true" derived="true" ordered="false"
     * @generated
     */
    List<InputSet> getInputSetRefs();

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataInput_IsCollection()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='isCollection'"
     * @generated
     */
    boolean isIsCollection();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DataInput#isIsCollection <em>Is Collection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Collection</em>' attribute.
     * @see #isIsCollection()
     * @generated
     */
    void setIsCollection(boolean value);

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataInput_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DataInput#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // DataInput
