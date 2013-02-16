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
 * A representation of the model object '<em><b>Diagram Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.dd.di.DiagramElement#getOwningDiagram <em>Owning Diagram</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DiagramElement#getOwningElement <em>Owning Element</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DiagramElement#getOwnedElement <em>Owned Element</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DiagramElement#getModelElement <em>Model Element</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DiagramElement#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DiagramElement#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.dd.di.DiPackage#getDiagramElement()
 * @model extendedMetaData="name='DiagramElement' kind='elementOnly' abstract='true'"
 * @generated
 */
public interface DiagramElement extends EObject {
    /**
     * Returns the value of the '<em><b>Owning Diagram</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.dd.di.Diagram#getRootElement <em>Root Element</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owning Diagram</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Owning Diagram</em>' reference.
     * @see org.eclipse.dd.di.DiPackage#getDiagramElement_OwningDiagram()
     * @see org.eclipse.dd.di.Diagram#getRootElement
     * @model opposite="rootElement" resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
     * @generated
     */
    Diagram getOwningDiagram();

    /**
     * Returns the value of the '<em><b>Owning Element</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.dd.di.DiagramElement#getOwnedElement <em>Owned Element</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owning Element</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Owning Element</em>' reference.
     * @see org.eclipse.dd.di.DiPackage#getDiagramElement_OwningElement()
     * @see org.eclipse.dd.di.DiagramElement#getOwnedElement
     * @model opposite="ownedElement" resolveProxies="false" transient="true" changeable="false" derived="true" ordered="false"
     * @generated
     */
    DiagramElement getOwningElement();

    /**
     * Returns the value of the '<em><b>Owned Element</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.dd.di.DiagramElement}.
     * It is bidirectional and its opposite is '{@link org.eclipse.dd.di.DiagramElement#getOwningElement <em>Owning Element</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Element</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Owned Element</em>' reference list.
     * @see org.eclipse.dd.di.DiPackage#getDiagramElement_OwnedElement()
     * @see org.eclipse.dd.di.DiagramElement#getOwningElement
     * @model opposite="owningElement" resolveProxies="false" transient="true" changeable="false" derived="true" ordered="false"
     * @generated
     */
    List<DiagramElement> getOwnedElement();

    /**
     * Returns the value of the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Model Element</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Model Element</em>' reference.
     * @see org.eclipse.dd.di.DiPackage#getDiagramElement_ModelElement()
     * @model transient="true" changeable="false" derived="true" ordered="false"
     * @generated
     */
    Object getModelElement();

    /**
     * Returns the value of the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Style</em>' reference.
     * @see org.eclipse.dd.di.DiPackage#getDiagramElement_Style()
     * @model transient="true" changeable="false" derived="true" ordered="false"
     * @generated
     */
    Style getStyle();

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
     * @see org.eclipse.dd.di.DiPackage#getDiagramElement_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.DiagramElement#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

} // DiagramElement
