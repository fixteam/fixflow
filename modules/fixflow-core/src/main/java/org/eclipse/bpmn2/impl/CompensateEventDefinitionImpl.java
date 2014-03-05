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
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.CompensateEventDefinition;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compensate Event Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.CompensateEventDefinitionImpl#getActivityRef <em>Activity Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.CompensateEventDefinitionImpl#isWaitForCompletion <em>Wait For Completion</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompensateEventDefinitionImpl extends EventDefinitionImpl implements
        CompensateEventDefinition {
    /**
     * The cached value of the '{@link #getActivityRef() <em>Activity Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActivityRef()
     * @generated
     * @ordered
     */
    protected Activity activityRef;

    /**
     * The default value of the '{@link #isWaitForCompletion() <em>Wait For Completion</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isWaitForCompletion()
     * @generated
     * @ordered
     */
    protected static final boolean WAIT_FOR_COMPLETION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isWaitForCompletion() <em>Wait For Completion</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isWaitForCompletion()
     * @generated
     * @ordered
     */
    protected boolean waitForCompletion = WAIT_FOR_COMPLETION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CompensateEventDefinitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.COMPENSATE_EVENT_DEFINITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Activity getActivityRef() {
        if (activityRef != null && activityRef.eIsProxy()) {
            InternalEObject oldActivityRef = (InternalEObject) activityRef;
            activityRef = (Activity) eResolveProxy(oldActivityRef);
            if (activityRef != oldActivityRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.COMPENSATE_EVENT_DEFINITION__ACTIVITY_REF, oldActivityRef,
                            activityRef));
            }
        }
        return activityRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Activity basicGetActivityRef() {
        return activityRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setActivityRef(Activity newActivityRef) {
        Activity oldActivityRef = activityRef;
        activityRef = newActivityRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.COMPENSATE_EVENT_DEFINITION__ACTIVITY_REF, oldActivityRef,
                    activityRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isWaitForCompletion() {
        return waitForCompletion;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWaitForCompletion(boolean newWaitForCompletion) {
        boolean oldWaitForCompletion = waitForCompletion;
        waitForCompletion = newWaitForCompletion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.COMPENSATE_EVENT_DEFINITION__WAIT_FOR_COMPLETION,
                    oldWaitForCompletion, waitForCompletion));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Bpmn2Package.COMPENSATE_EVENT_DEFINITION__ACTIVITY_REF:
            if (resolve)
                return getActivityRef();
            return basicGetActivityRef();
        case Bpmn2Package.COMPENSATE_EVENT_DEFINITION__WAIT_FOR_COMPLETION:
            return isWaitForCompletion();
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
        case Bpmn2Package.COMPENSATE_EVENT_DEFINITION__ACTIVITY_REF:
            setActivityRef((Activity) newValue);
            return;
        case Bpmn2Package.COMPENSATE_EVENT_DEFINITION__WAIT_FOR_COMPLETION:
            setWaitForCompletion((Boolean) newValue);
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
        case Bpmn2Package.COMPENSATE_EVENT_DEFINITION__ACTIVITY_REF:
            setActivityRef((Activity) null);
            return;
        case Bpmn2Package.COMPENSATE_EVENT_DEFINITION__WAIT_FOR_COMPLETION:
            setWaitForCompletion(WAIT_FOR_COMPLETION_EDEFAULT);
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
        case Bpmn2Package.COMPENSATE_EVENT_DEFINITION__ACTIVITY_REF:
            return activityRef != null;
        case Bpmn2Package.COMPENSATE_EVENT_DEFINITION__WAIT_FOR_COMPLETION:
            return waitForCompletion != WAIT_FOR_COMPLETION_EDEFAULT;
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
        result.append(" (waitForCompletion: ");
        result.append(waitForCompletion);
        result.append(')');
        return result.toString();
    }

} //CompensateEventDefinitionImpl
