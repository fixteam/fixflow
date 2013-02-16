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

import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.ResourceAssignmentExpression;
import org.eclipse.bpmn2.ResourceParameterBinding;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.ResourceRoleImpl#getResourceRef <em>Resource Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ResourceRoleImpl#getResourceParameterBindings <em>Resource Parameter Bindings</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ResourceRoleImpl#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ResourceRoleImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceRoleImpl extends BaseElementImpl implements ResourceRole {
    /**
     * The cached value of the '{@link #getResourceRef() <em>Resource Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResourceRef()
     * @generated
     * @ordered
     */
    protected Resource resourceRef;

    /**
     * The cached value of the '{@link #getResourceParameterBindings() <em>Resource Parameter Bindings</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResourceParameterBindings()
     * @generated
     * @ordered
     */
    protected EList<ResourceParameterBinding> resourceParameterBindings;

    /**
     * The cached value of the '{@link #getResourceAssignmentExpression() <em>Resource Assignment Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResourceAssignmentExpression()
     * @generated
     * @ordered
     */
    protected ResourceAssignmentExpression resourceAssignmentExpression;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResourceRoleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.RESOURCE_ROLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Resource getResourceRef() {
        if (resourceRef != null && resourceRef.eIsProxy()) {
            InternalEObject oldResourceRef = (InternalEObject) resourceRef;
            resourceRef = (Resource) eResolveProxy(oldResourceRef);
            if (resourceRef != oldResourceRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.RESOURCE_ROLE__RESOURCE_REF, oldResourceRef, resourceRef));
            }
        }
        return resourceRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Resource basicGetResourceRef() {
        return resourceRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResourceRef(Resource newResourceRef) {
        Resource oldResourceRef = resourceRef;
        resourceRef = newResourceRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.RESOURCE_ROLE__RESOURCE_REF, oldResourceRef, resourceRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<ResourceParameterBinding> getResourceParameterBindings() {
        if (resourceParameterBindings == null) {
            resourceParameterBindings = new EObjectContainmentEList<ResourceParameterBinding>(
                    ResourceParameterBinding.class, this,
                    Bpmn2Package.RESOURCE_ROLE__RESOURCE_PARAMETER_BINDINGS);
        }
        return resourceParameterBindings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceAssignmentExpression getResourceAssignmentExpression() {
        return resourceAssignmentExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetResourceAssignmentExpression(
            ResourceAssignmentExpression newResourceAssignmentExpression, NotificationChain msgs) {
        ResourceAssignmentExpression oldResourceAssignmentExpression = resourceAssignmentExpression;
        resourceAssignmentExpression = newResourceAssignmentExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.RESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION,
                    oldResourceAssignmentExpression, newResourceAssignmentExpression);
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
    public void setResourceAssignmentExpression(
            ResourceAssignmentExpression newResourceAssignmentExpression) {
        if (newResourceAssignmentExpression != resourceAssignmentExpression) {
            NotificationChain msgs = null;
            if (resourceAssignmentExpression != null)
                msgs = ((InternalEObject) resourceAssignmentExpression).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE
                                - Bpmn2Package.RESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION, null,
                        msgs);
            if (newResourceAssignmentExpression != null)
                msgs = ((InternalEObject) newResourceAssignmentExpression).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE
                                - Bpmn2Package.RESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION, null,
                        msgs);
            msgs = basicSetResourceAssignmentExpression(newResourceAssignmentExpression, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.RESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION,
                    newResourceAssignmentExpression, newResourceAssignmentExpression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.RESOURCE_ROLE__NAME,
                    oldName, name));
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
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_PARAMETER_BINDINGS:
            return ((InternalEList<?>) getResourceParameterBindings()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION:
            return basicSetResourceAssignmentExpression(null, msgs);
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
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_REF:
            if (resolve)
                return getResourceRef();
            return basicGetResourceRef();
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_PARAMETER_BINDINGS:
            return getResourceParameterBindings();
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION:
            return getResourceAssignmentExpression();
        case Bpmn2Package.RESOURCE_ROLE__NAME:
            return getName();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_REF:
            setResourceRef((Resource) newValue);
            return;
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_PARAMETER_BINDINGS:
            getResourceParameterBindings().clear();
            getResourceParameterBindings().addAll(
                    (Collection<? extends ResourceParameterBinding>) newValue);
            return;
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION:
            setResourceAssignmentExpression((ResourceAssignmentExpression) newValue);
            return;
        case Bpmn2Package.RESOURCE_ROLE__NAME:
            setName((String) newValue);
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
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_REF:
            setResourceRef((Resource) null);
            return;
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_PARAMETER_BINDINGS:
            getResourceParameterBindings().clear();
            return;
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION:
            setResourceAssignmentExpression((ResourceAssignmentExpression) null);
            return;
        case Bpmn2Package.RESOURCE_ROLE__NAME:
            setName(NAME_EDEFAULT);
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
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_REF:
            return resourceRef != null;
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_PARAMETER_BINDINGS:
            return resourceParameterBindings != null && !resourceParameterBindings.isEmpty();
        case Bpmn2Package.RESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION:
            return resourceAssignmentExpression != null;
        case Bpmn2Package.RESOURCE_ROLE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
        result.append(" (name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //ResourceRoleImpl
