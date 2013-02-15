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

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boundary Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.BoundaryEventImpl#getAttachedToRef <em>Attached To Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.BoundaryEventImpl#isCancelActivity <em>Cancel Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BoundaryEventImpl extends CatchEventImpl implements BoundaryEvent {
    /**
     * The cached value of the '{@link #getAttachedToRef() <em>Attached To Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttachedToRef()
     * @generated
     * @ordered
     */
    protected Activity attachedToRef;

    /**
     * The default value of the '{@link #isCancelActivity() <em>Cancel Activity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCancelActivity()
     * @generated
     * @ordered
     */
    protected static final boolean CANCEL_ACTIVITY_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isCancelActivity() <em>Cancel Activity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCancelActivity()
     * @generated
     * @ordered
     */
    protected boolean cancelActivity = CANCEL_ACTIVITY_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BoundaryEventImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.BOUNDARY_EVENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Activity getAttachedToRef() {
        return attachedToRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAttachedToRef(Activity newAttachedToRef, NotificationChain msgs) {
        Activity oldAttachedToRef = attachedToRef;
        attachedToRef = newAttachedToRef;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.BOUNDARY_EVENT__ATTACHED_TO_REF, oldAttachedToRef,
                    newAttachedToRef);
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
    public void setAttachedToRef(Activity newAttachedToRef) {
        if (newAttachedToRef != attachedToRef) {
            NotificationChain msgs = null;
            if (attachedToRef != null)
                msgs = ((InternalEObject) attachedToRef).eInverseRemove(this,
                        Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS, Activity.class, msgs);
            if (newAttachedToRef != null)
                msgs = ((InternalEObject) newAttachedToRef).eInverseAdd(this,
                        Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS, Activity.class, msgs);
            msgs = basicSetAttachedToRef(newAttachedToRef, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.BOUNDARY_EVENT__ATTACHED_TO_REF, newAttachedToRef,
                    newAttachedToRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isCancelActivity() {
        return cancelActivity;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCancelActivity(boolean newCancelActivity) {
        boolean oldCancelActivity = cancelActivity;
        cancelActivity = newCancelActivity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.BOUNDARY_EVENT__CANCEL_ACTIVITY, oldCancelActivity, cancelActivity));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
            NotificationChain msgs) {
        switch (featureID) {
        case Bpmn2Package.BOUNDARY_EVENT__ATTACHED_TO_REF:
            if (attachedToRef != null)
                msgs = ((InternalEObject) attachedToRef).eInverseRemove(this,
                        Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS, Activity.class, msgs);
            return basicSetAttachedToRef((Activity) otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
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
        case Bpmn2Package.BOUNDARY_EVENT__ATTACHED_TO_REF:
            return basicSetAttachedToRef(null, msgs);
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
        case Bpmn2Package.BOUNDARY_EVENT__ATTACHED_TO_REF:
            return getAttachedToRef();
        case Bpmn2Package.BOUNDARY_EVENT__CANCEL_ACTIVITY:
            return isCancelActivity();
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
        case Bpmn2Package.BOUNDARY_EVENT__ATTACHED_TO_REF:
            setAttachedToRef((Activity) newValue);
            return;
        case Bpmn2Package.BOUNDARY_EVENT__CANCEL_ACTIVITY:
            setCancelActivity((Boolean) newValue);
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
        case Bpmn2Package.BOUNDARY_EVENT__ATTACHED_TO_REF:
            setAttachedToRef((Activity) null);
            return;
        case Bpmn2Package.BOUNDARY_EVENT__CANCEL_ACTIVITY:
            setCancelActivity(CANCEL_ACTIVITY_EDEFAULT);
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
        case Bpmn2Package.BOUNDARY_EVENT__ATTACHED_TO_REF:
            return attachedToRef != null;
        case Bpmn2Package.BOUNDARY_EVENT__CANCEL_ACTIVITY:
            return cancelActivity != CANCEL_ACTIVITY_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (cancelActivity: ");
        result.append(cancelActivity);
        result.append(')');
        return result.toString();
    }

} //BoundaryEventImpl
