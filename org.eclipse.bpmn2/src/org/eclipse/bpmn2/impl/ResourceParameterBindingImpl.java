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

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.ResourceParameter;
import org.eclipse.bpmn2.ResourceParameterBinding;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Parameter Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.ResourceParameterBindingImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ResourceParameterBindingImpl#getParameterRef <em>Parameter Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceParameterBindingImpl extends BaseElementImpl implements
        ResourceParameterBinding {
    /**
     * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExpression()
     * @generated
     * @ordered
     */
    protected Expression expression;

    /**
     * The cached value of the '{@link #getParameterRef() <em>Parameter Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterRef()
     * @generated
     * @ordered
     */
    protected ResourceParameter parameterRef;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResourceParameterBindingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.RESOURCE_PARAMETER_BINDING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
        Expression oldExpression = expression;
        expression = newExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.RESOURCE_PARAMETER_BINDING__EXPRESSION, oldExpression,
                    newExpression);
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
    public void setExpression(Expression newExpression) {
        if (newExpression != expression) {
            NotificationChain msgs = null;
            if (expression != null)
                msgs = ((InternalEObject) expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - Bpmn2Package.RESOURCE_PARAMETER_BINDING__EXPRESSION, null, msgs);
            if (newExpression != null)
                msgs = ((InternalEObject) newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - Bpmn2Package.RESOURCE_PARAMETER_BINDING__EXPRESSION, null, msgs);
            msgs = basicSetExpression(newExpression, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.RESOURCE_PARAMETER_BINDING__EXPRESSION, newExpression,
                    newExpression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceParameter getParameterRef() {
        if (parameterRef != null && parameterRef.eIsProxy()) {
            InternalEObject oldParameterRef = (InternalEObject) parameterRef;
            parameterRef = (ResourceParameter) eResolveProxy(oldParameterRef);
            if (parameterRef != oldParameterRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.RESOURCE_PARAMETER_BINDING__PARAMETER_REF,
                            oldParameterRef, parameterRef));
            }
        }
        return parameterRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceParameter basicGetParameterRef() {
        return parameterRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParameterRef(ResourceParameter newParameterRef) {
        ResourceParameter oldParameterRef = parameterRef;
        parameterRef = newParameterRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.RESOURCE_PARAMETER_BINDING__PARAMETER_REF, oldParameterRef,
                    parameterRef));
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
        case Bpmn2Package.RESOURCE_PARAMETER_BINDING__EXPRESSION:
            return basicSetExpression(null, msgs);
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
        case Bpmn2Package.RESOURCE_PARAMETER_BINDING__EXPRESSION:
            return getExpression();
        case Bpmn2Package.RESOURCE_PARAMETER_BINDING__PARAMETER_REF:
            if (resolve)
                return getParameterRef();
            return basicGetParameterRef();
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
        case Bpmn2Package.RESOURCE_PARAMETER_BINDING__EXPRESSION:
            setExpression((Expression) newValue);
            return;
        case Bpmn2Package.RESOURCE_PARAMETER_BINDING__PARAMETER_REF:
            setParameterRef((ResourceParameter) newValue);
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
        case Bpmn2Package.RESOURCE_PARAMETER_BINDING__EXPRESSION:
            setExpression((Expression) null);
            return;
        case Bpmn2Package.RESOURCE_PARAMETER_BINDING__PARAMETER_REF:
            setParameterRef((ResourceParameter) null);
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
        case Bpmn2Package.RESOURCE_PARAMETER_BINDING__EXPRESSION:
            return expression != null;
        case Bpmn2Package.RESOURCE_PARAMETER_BINDING__PARAMETER_REF:
            return parameterRef != null;
        }
        return super.eIsSet(featureID);
    }

} //ResourceParameterBindingImpl
