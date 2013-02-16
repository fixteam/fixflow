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
 * A representation of the model object '<em><b>Throw Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.ThrowEvent#getDataInputs <em>Data Inputs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ThrowEvent#getDataInputAssociation <em>Data Input Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ThrowEvent#getInputSet <em>Input Set</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ThrowEvent#getEventDefinitions <em>Event Definitions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ThrowEvent#getEventDefinitionRefs <em>Event Definition Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getThrowEvent()
 * @model abstract="true"
 *        extendedMetaData="name='tThrowEvent' kind='elementOnly'"
 * @generated
 */
public interface ThrowEvent extends Event {
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
     * @see org.eclipse.bpmn2.Bpmn2Package#getThrowEvent_DataInputs()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='dataInput' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<DataInput> getDataInputs();

    /**
     * Returns the value of the '<em><b>Data Input Association</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.DataInputAssociation}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Input Association</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Input Association</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getThrowEvent_DataInputAssociation()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='dataInputAssociation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<DataInputAssociation> getDataInputAssociation();

    /**
     * Returns the value of the '<em><b>Input Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Set</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Set</em>' containment reference.
     * @see #setInputSet(InputSet)
     * @see org.eclipse.bpmn2.Bpmn2Package#getThrowEvent_InputSet()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='inputSet' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    InputSet getInputSet();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ThrowEvent#getInputSet <em>Input Set</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Set</em>' containment reference.
     * @see #getInputSet()
     * @generated
     */
    void setInputSet(InputSet value);

    /**
     * Returns the value of the '<em><b>Event Definitions</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.EventDefinition}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Event Definitions</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Event Definitions</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getThrowEvent_EventDefinitions()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='eventDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' group='http://www.omg.org/spec/BPMN/20100524/MODEL#eventDefinition'"
     * @generated
     */
    List<EventDefinition> getEventDefinitions();

    /**
     * Returns the value of the '<em><b>Event Definition Refs</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.EventDefinition}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Event Definition Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Event Definition Refs</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getThrowEvent_EventDefinitionRefs()
     * @model ordered="false"
     *        extendedMetaData="kind='element' name='eventDefinitionRef' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<EventDefinition> getEventDefinitionRefs();

} // ThrowEvent
