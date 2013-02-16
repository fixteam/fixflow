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
package org.eclipse.bpmn2.di.impl;

import org.eclipse.bpmn2.di.BPMNLabelStyle;
import org.eclipse.bpmn2.di.BpmnDiPackage;
import org.eclipse.dd.dc.Font;
import org.eclipse.dd.di.impl.StyleImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BPMN Label Style</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.di.impl.BPMNLabelStyleImpl#getFont <em>Font</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BPMNLabelStyleImpl extends StyleImpl implements BPMNLabelStyle {
    /**
     * The cached value of the '{@link #getFont() <em>Font</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFont()
     * @generated
     * @ordered
     */
    protected Font font;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BPMNLabelStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BpmnDiPackage.Literals.BPMN_LABEL_STYLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Font getFont() {
        return font;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFont(Font newFont, NotificationChain msgs) {
        Font oldFont = font;
        font = newFont;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    BpmnDiPackage.BPMN_LABEL_STYLE__FONT, oldFont, newFont);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFont(Font newFont) {
        if (newFont != font) {
            NotificationChain msgs = null;
            if (font != null)
                msgs = ((InternalEObject) font).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - BpmnDiPackage.BPMN_LABEL_STYLE__FONT, null, msgs);
            if (newFont != null)
                msgs = ((InternalEObject) newFont).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - BpmnDiPackage.BPMN_LABEL_STYLE__FONT, null, msgs);
            msgs = basicSetFont(newFont, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    BpmnDiPackage.BPMN_LABEL_STYLE__FONT, newFont, newFont));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
            NotificationChain msgs) {
        switch (featureID) {
        case BpmnDiPackage.BPMN_LABEL_STYLE__FONT:
            return basicSetFont(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case BpmnDiPackage.BPMN_LABEL_STYLE__FONT:
            return getFont();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case BpmnDiPackage.BPMN_LABEL_STYLE__FONT:
            setFont((Font) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case BpmnDiPackage.BPMN_LABEL_STYLE__FONT:
            setFont((Font) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case BpmnDiPackage.BPMN_LABEL_STYLE__FONT:
            return font != null;
        }
        return super.eIsSet(featureID);
    }

} //BPMNLabelStyleImpl
