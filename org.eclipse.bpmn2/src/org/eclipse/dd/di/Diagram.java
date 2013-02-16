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
package org.eclipse.dd.di;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.dd.di.Diagram#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.dd.di.Diagram#getOwnedStyle <em>Owned Style</em>}</li>
 *   <li>{@link org.eclipse.dd.di.Diagram#getRootElement <em>Root Element</em>}</li>
 *   <li>{@link org.eclipse.dd.di.Diagram#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.dd.di.Diagram#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.dd.di.Diagram#getResolution <em>Resolution</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.dd.di.DiPackage#getDiagram()
 * @model abstract="true"
 *        extendedMetaData="name='Diagram' kind='empty'"
 * @generated
 */
public interface Diagram extends EObject {
    /**
     * Returns the value of the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Documentation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Documentation</em>' attribute.
     * @see #setDocumentation(String)
     * @see org.eclipse.dd.di.DiPackage#getDiagram_Documentation()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='documentation'"
     * @generated
     */
    String getDocumentation();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.Diagram#getDocumentation <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Documentation</em>' attribute.
     * @see #getDocumentation()
     * @generated
     */
    void setDocumentation(String value);

    /**
     * Returns the value of the '<em><b>Owned Style</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.dd.di.Style}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Style</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Owned Style</em>' reference list.
     * @see org.eclipse.dd.di.DiPackage#getDiagram_OwnedStyle()
     * @model transient="true" changeable="false" derived="true" ordered="false"
     * @generated
     */
    List<Style> getOwnedStyle();

    /**
     * Returns the value of the '<em><b>Root Element</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.dd.di.DiagramElement#getOwningDiagram <em>Owning Diagram</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Root Element</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Root Element</em>' reference.
     * @see org.eclipse.dd.di.DiPackage#getDiagram_RootElement()
     * @see org.eclipse.dd.di.DiagramElement#getOwningDiagram
     * @model opposite="owningDiagram" resolveProxies="false" required="true" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
     * @generated
     */
    DiagramElement getRootElement();

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
     * @see org.eclipse.dd.di.DiPackage#getDiagram_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.Diagram#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

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
     * @see org.eclipse.dd.di.DiPackage#getDiagram_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.Diagram#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Resolution</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resolution</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resolution</em>' attribute.
     * @see #setResolution(float)
     * @see org.eclipse.dd.di.DiPackage#getDiagram_Resolution()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='resolution'"
     * @generated
     */
    float getResolution();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.Diagram#getResolution <em>Resolution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resolution</em>' attribute.
     * @see #getResolution()
     * @generated
     */
    void setResolution(float value);

} // Diagram
