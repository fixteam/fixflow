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
 * A representation of the model object '<em><b>Data Output</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.DataOutput#getOutputSetWithOptional <em>Output Set With Optional</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataOutput#getOutputSetWithWhileExecuting <em>Output Set With While Executing</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataOutput#getOutputSetRefs <em>Output Set Refs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataOutput#isIsCollection <em>Is Collection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DataOutput#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getDataOutput()
 * @model extendedMetaData="name='tDataOutput' kind='elementOnly'"
 * @generated
 */
public interface DataOutput extends ItemAwareElement {
    /**
     * Returns the value of the '<em><b>Output Set With Optional</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.OutputSet}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.OutputSet#getOptionalOutputRefs <em>Optional Output Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Output Set With Optional</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output Set With Optional</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataOutput_OutputSetWithOptional()
     * @see org.eclipse.bpmn2.OutputSet#getOptionalOutputRefs
     * @model opposite="optionalOutputRefs" transient="true" derived="true" ordered="false"
     * @generated
     */
    List<OutputSet> getOutputSetWithOptional();

    /**
     * Returns the value of the '<em><b>Output Set With While Executing</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.OutputSet}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.OutputSet#getWhileExecutingOutputRefs <em>While Executing Output Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Output Set With While Executing</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output Set With While Executing</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataOutput_OutputSetWithWhileExecuting()
     * @see org.eclipse.bpmn2.OutputSet#getWhileExecutingOutputRefs
     * @model opposite="whileExecutingOutputRefs" transient="true" derived="true" ordered="false"
     * @generated
     */
    List<OutputSet> getOutputSetWithWhileExecuting();

    /**
     * Returns the value of the '<em><b>Output Set Refs</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.OutputSet}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.OutputSet#getDataOutputRefs <em>Data Output Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Output Set Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output Set Refs</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataOutput_OutputSetRefs()
     * @see org.eclipse.bpmn2.OutputSet#getDataOutputRefs
     * @model opposite="dataOutputRefs" required="true" transient="true" derived="true" ordered="false"
     * @generated
     */
    List<OutputSet> getOutputSetRefs();

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataOutput_IsCollection()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='isCollection'"
     * @generated
     */
    boolean isIsCollection();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DataOutput#isIsCollection <em>Is Collection</em>}' attribute.
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
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataOutput_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DataOutput#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // DataOutput
