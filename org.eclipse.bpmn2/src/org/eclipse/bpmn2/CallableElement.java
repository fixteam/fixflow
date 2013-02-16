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
 * A representation of the model object '<em><b>Callable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.CallableElement#getSupportedInterfaceRefs <em>Supported Interface Refs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.CallableElement#getIoSpecification <em>Io Specification</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.CallableElement#getIoBinding <em>Io Binding</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.CallableElement#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getCallableElement()
 * @model extendedMetaData="name='tCallableElement' kind='elementOnly' abstract='true'"
 * @generated
 */
public interface CallableElement extends RootElement {
    /**
     * Returns the value of the '<em><b>Supported Interface Refs</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.Interface}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Supported Interface Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Supported Interface Refs</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getCallableElement_SupportedInterfaceRefs()
     * @model ordered="false"
     *        extendedMetaData="kind='element' name='supportedInterfaceRef' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<Interface> getSupportedInterfaceRefs();

    /**
     * Returns the value of the '<em><b>Io Specification</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Io Specification</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Io Specification</em>' containment reference.
     * @see #setIoSpecification(InputOutputSpecification)
     * @see org.eclipse.bpmn2.Bpmn2Package#getCallableElement_IoSpecification()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='ioSpecification' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    InputOutputSpecification getIoSpecification();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.CallableElement#getIoSpecification <em>Io Specification</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Io Specification</em>' containment reference.
     * @see #getIoSpecification()
     * @generated
     */
    void setIoSpecification(InputOutputSpecification value);

    /**
     * Returns the value of the '<em><b>Io Binding</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.InputOutputBinding}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Io Binding</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Io Binding</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getCallableElement_IoBinding()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='ioBinding' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<InputOutputBinding> getIoBinding();

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getCallableElement_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.CallableElement#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // CallableElement
