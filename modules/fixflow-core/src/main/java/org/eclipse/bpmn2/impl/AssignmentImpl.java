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
package org.eclipse.bpmn2.impl;

import org.eclipse.bpmn2.Assignment;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Expression;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.AssignmentImpl#getFrom <em>From</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.AssignmentImpl#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssignmentImpl extends BaseElementImpl implements Assignment {
    /**
     * The cached value of the '{@link #getFrom() <em>From</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFrom()
     * @generated
     * @ordered
     */
    protected Expression from;

    /**
     * The cached value of the '{@link #getTo() <em>To</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTo()
     * @generated
     * @ordered
     */
    protected Expression to;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AssignmentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.ASSIGNMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getFrom() {
        return from;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFrom(Expression newFrom, NotificationChain msgs) {
        Expression oldFrom = from;
        from = newFrom;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.ASSIGNMENT__FROM, oldFrom, newFrom);
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
    public void setFrom(Expression newFrom) {
        if (newFrom != from) {
            NotificationChain msgs = null;
            if (from != null)
                msgs = ((InternalEObject) from).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - Bpmn2Package.ASSIGNMENT__FROM, null, msgs);
            if (newFrom != null)
                msgs = ((InternalEObject) newFrom).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - Bpmn2Package.ASSIGNMENT__FROM, null, msgs);
            msgs = basicSetFrom(newFrom, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ASSIGNMENT__FROM,
                    newFrom, newFrom));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getTo() {
        return to;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTo(Expression newTo, NotificationChain msgs) {
        Expression oldTo = to;
        to = newTo;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.ASSIGNMENT__TO, oldTo, newTo);
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
    public void setTo(Expression newTo) {
        if (newTo != to) {
            NotificationChain msgs = null;
            if (to != null)
                msgs = ((InternalEObject) to).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - Bpmn2Package.ASSIGNMENT__TO, null, msgs);
            if (newTo != null)
                msgs = ((InternalEObject) newTo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - Bpmn2Package.ASSIGNMENT__TO, null, msgs);
            msgs = basicSetTo(newTo, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ASSIGNMENT__TO,
                    newTo, newTo));
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
        case Bpmn2Package.ASSIGNMENT__FROM:
            return basicSetFrom(null, msgs);
        case Bpmn2Package.ASSIGNMENT__TO:
            return basicSetTo(null, msgs);
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
        case Bpmn2Package.ASSIGNMENT__FROM:
            return getFrom();
        case Bpmn2Package.ASSIGNMENT__TO:
            return getTo();
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
        case Bpmn2Package.ASSIGNMENT__FROM:
            setFrom((Expression) newValue);
            return;
        case Bpmn2Package.ASSIGNMENT__TO:
            setTo((Expression) newValue);
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
        case Bpmn2Package.ASSIGNMENT__FROM:
            setFrom((Expression) null);
            return;
        case Bpmn2Package.ASSIGNMENT__TO:
            setTo((Expression) null);
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
        case Bpmn2Package.ASSIGNMENT__FROM:
            return from != null;
        case Bpmn2Package.ASSIGNMENT__TO:
            return to != null;
        }
        return super.eIsSet(featureID);
    }

} //AssignmentImpl
