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
package org.eclipse.dd.dc;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bounds</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.dd.dc.Bounds#getHeight <em>Height</em>}</li>
 *   <li>{@link org.eclipse.dd.dc.Bounds#getWidth <em>Width</em>}</li>
 *   <li>{@link org.eclipse.dd.dc.Bounds#getX <em>X</em>}</li>
 *   <li>{@link org.eclipse.dd.dc.Bounds#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.dd.dc.DcPackage#getBounds()
 * @model extendedMetaData="name='Bounds' kind='empty'"
 * @generated
 */
public interface Bounds extends EObject {
    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Height</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(float)
     * @see org.eclipse.dd.dc.DcPackage#getBounds_Height()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='height'"
     * @generated
     */
    float getHeight();

    /**
     * Sets the value of the '{@link org.eclipse.dd.dc.Bounds#getHeight <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(float value);

    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Width</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(float)
     * @see org.eclipse.dd.dc.DcPackage#getBounds_Width()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='width'"
     * @generated
     */
    float getWidth();

    /**
     * Sets the value of the '{@link org.eclipse.dd.dc.Bounds#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(float value);

    /**
     * Returns the value of the '<em><b>X</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>X</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #setX(float)
     * @see org.eclipse.dd.dc.DcPackage#getBounds_X()
     * @model default="0" required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='x'"
     * @generated
     */
    float getX();

    /**
     * Sets the value of the '{@link org.eclipse.dd.dc.Bounds#getX <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>X</em>' attribute.
     * @see #getX()
     * @generated
     */
    void setX(float value);

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Y</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' attribute.
     * @see #setY(float)
     * @see org.eclipse.dd.dc.DcPackage#getBounds_Y()
     * @model default="0" required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='y'"
     * @generated
     */
    float getY();

    /**
     * Sets the value of the '{@link org.eclipse.dd.dc.Bounds#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
    void setY(float value);

} // Bounds
