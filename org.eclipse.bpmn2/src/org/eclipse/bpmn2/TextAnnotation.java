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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.TextAnnotation#getText <em>Text</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.TextAnnotation#getTextFormat <em>Text Format</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getTextAnnotation()
 * @model extendedMetaData="name='tTextAnnotation' kind='elementOnly'"
 * @generated
 */
public interface TextAnnotation extends Artifact {
    /**
     * Returns the value of the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Text</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' attribute.
     * @see #setText(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getTextAnnotation_Text()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='element' name='text' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    String getText();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.TextAnnotation#getText <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' attribute.
     * @see #getText()
     * @generated
     */
    void setText(String value);

    /**
     * Returns the value of the '<em><b>Text Format</b></em>' attribute.
     * The default value is <code>"text/plain"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Text Format</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text Format</em>' attribute.
     * @see #setTextFormat(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getTextAnnotation_TextFormat()
     * @model default="text/plain" ordered="false"
     *        extendedMetaData="kind='attribute' name='textFormat'"
     * @generated
     */
    String getTextFormat();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.TextAnnotation#getTextFormat <em>Text Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text Format</em>' attribute.
     * @see #getTextFormat()
     * @generated
     */
    void setTextFormat(String value);

} // TextAnnotation
