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
 *
 */
package org.eclipse.bpmn2.di;

import org.eclipse.dd.di.Label;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNLabel#getLabelStyle <em>Label Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNLabel()
 * @model extendedMetaData="name='BPMNLabel' kind='elementOnly'"
 * @generated
 */
public interface BPMNLabel extends Label {
    /**
     * Returns the value of the '<em><b>Label Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Style</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label Style</em>' reference.
     * @see #setLabelStyle(BPMNLabelStyle)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNLabel_LabelStyle()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='labelStyle'"
     * @generated
     */
    BPMNLabelStyle getLabelStyle();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNLabel#getLabelStyle <em>Label Style</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label Style</em>' reference.
     * @see #getLabelStyle()
     * @generated
     */
    void setLabelStyle(BPMNLabelStyle value);

} // BPMNLabel
