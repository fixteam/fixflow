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

import javax.xml.namespace.QName;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.Extension#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Extension#isMustUnderstand <em>Must Understand</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Extension#getXsdDefinition <em>Xsd Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getExtension()
 * @model extendedMetaData="name='tExtension' kind='elementOnly'"
 * @generated
 */
public interface Extension extends EObject {
    /**
     * Returns the value of the '<em><b>Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Definition</em>' containment reference.
     * @see #setDefinition(ExtensionDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getExtension_Definition()
     * @model containment="true" required="true" ordered="false"
     * @generated
     */
    ExtensionDefinition getDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Extension#getDefinition <em>Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Definition</em>' containment reference.
     * @see #getDefinition()
     * @generated
     */
    void setDefinition(ExtensionDefinition value);

    /**
     * Returns the value of the '<em><b>Must Understand</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Must Understand</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Must Understand</em>' attribute.
     * @see #setMustUnderstand(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getExtension_MustUnderstand()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='mustUnderstand'"
     * @generated
     */
    boolean isMustUnderstand();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Extension#isMustUnderstand <em>Must Understand</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Must Understand</em>' attribute.
     * @see #isMustUnderstand()
     * @generated
     */
    void setMustUnderstand(boolean value);

    /**
     * Returns the value of the '<em><b>Xsd Definition</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Xsd Definition</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Xsd Definition</em>' attribute.
     * @see #setXsdDefinition(QName)
     * @see org.eclipse.bpmn2.Bpmn2Package#getExtension_XsdDefinition()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='definition'"
     * @generated
     */
    QName getXsdDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Extension#getXsdDefinition <em>Xsd Definition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Xsd Definition</em>' attribute.
     * @see #getXsdDefinition()
     * @generated
     */
    void setXsdDefinition(QName value);

} // Extension
