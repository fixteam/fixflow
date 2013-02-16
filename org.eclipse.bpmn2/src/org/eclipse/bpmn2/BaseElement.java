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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Base Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.BaseElement#getExtensionValues <em>Extension Values</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.BaseElement#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.BaseElement#getExtensionDefinitions <em>Extension Definitions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.BaseElement#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.BaseElement#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getBaseElement()
 * @model extendedMetaData="name='tBaseElement' kind='elementOnly' abstract='true'"
 * @generated
 */
public interface BaseElement extends EObject {
    /**
     * Returns the value of the '<em><b>Extension Values</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.ExtensionAttributeValue}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extension Values</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extension Values</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getBaseElement_ExtensionValues()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='extensionElements' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<ExtensionAttributeValue> getExtensionValues();

    /**
     * Returns the value of the '<em><b>Documentation</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.Documentation}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Documentation</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Documentation</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getBaseElement_Documentation()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='documentation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<Documentation> getDocumentation();

    /**
     * Returns the value of the '<em><b>Extension Definitions</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.ExtensionDefinition}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extension Definitions</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extension Definitions</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getBaseElement_ExtensionDefinitions()
     * @model ordered="false"
     * @generated
     */
    List<ExtensionDefinition> getExtensionDefinitions();

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getBaseElement_Id()
     * @model id="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='id'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.BaseElement#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Any Attribute</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Any Attribute</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Any Attribute</em>' attribute list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getBaseElement_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='attributeWildcard' wildcards='##other' name=':3' processing='lax'"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // BaseElement
