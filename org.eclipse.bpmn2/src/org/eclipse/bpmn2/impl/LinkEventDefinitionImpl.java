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
import org.eclipse.bpmn2.LinkEventDefinition;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link Event Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.LinkEventDefinitionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.LinkEventDefinitionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.LinkEventDefinitionImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkEventDefinitionImpl extends EventDefinitionImpl implements LinkEventDefinition {
    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected EList<LinkEventDefinition> source;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected LinkEventDefinition target;

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
    protected LinkEventDefinitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.LINK_EVENT_DEFINITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<LinkEventDefinition> getSource() {
        if (source == null) {
            source = new EObjectWithInverseResolvingEList<LinkEventDefinition>(
                    LinkEventDefinition.class, this, Bpmn2Package.LINK_EVENT_DEFINITION__SOURCE,
                    Bpmn2Package.LINK_EVENT_DEFINITION__TARGET);
        }
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LinkEventDefinition getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = (LinkEventDefinition) eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.LINK_EVENT_DEFINITION__TARGET, oldTarget, target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LinkEventDefinition basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTarget(LinkEventDefinition newTarget, NotificationChain msgs) {
        LinkEventDefinition oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.LINK_EVENT_DEFINITION__TARGET, oldTarget, newTarget);
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
    public void setTarget(LinkEventDefinition newTarget) {
        if (newTarget != target) {
            NotificationChain msgs = null;
            if (target != null)
                msgs = ((InternalEObject) target)
                        .eInverseRemove(this, Bpmn2Package.LINK_EVENT_DEFINITION__SOURCE,
                                LinkEventDefinition.class, msgs);
            if (newTarget != null)
                msgs = ((InternalEObject) newTarget)
                        .eInverseAdd(this, Bpmn2Package.LINK_EVENT_DEFINITION__SOURCE,
                                LinkEventDefinition.class, msgs);
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.LINK_EVENT_DEFINITION__TARGET, newTarget, newTarget));
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
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.LINK_EVENT_DEFINITION__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
            NotificationChain msgs) {
        switch (featureID) {
        case Bpmn2Package.LINK_EVENT_DEFINITION__SOURCE:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getSource()).basicAdd(
                    otherEnd, msgs);
        case Bpmn2Package.LINK_EVENT_DEFINITION__TARGET:
            if (target != null)
                msgs = ((InternalEObject) target)
                        .eInverseRemove(this, Bpmn2Package.LINK_EVENT_DEFINITION__SOURCE,
                                LinkEventDefinition.class, msgs);
            return basicSetTarget((LinkEventDefinition) otherEnd, msgs);
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
        case Bpmn2Package.LINK_EVENT_DEFINITION__SOURCE:
            return ((InternalEList<?>) getSource()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.LINK_EVENT_DEFINITION__TARGET:
            return basicSetTarget(null, msgs);
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
        case Bpmn2Package.LINK_EVENT_DEFINITION__SOURCE:
            return getSource();
        case Bpmn2Package.LINK_EVENT_DEFINITION__TARGET:
            if (resolve)
                return getTarget();
            return basicGetTarget();
        case Bpmn2Package.LINK_EVENT_DEFINITION__NAME:
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
        case Bpmn2Package.LINK_EVENT_DEFINITION__SOURCE:
            getSource().clear();
            getSource().addAll((Collection<? extends LinkEventDefinition>) newValue);
            return;
        case Bpmn2Package.LINK_EVENT_DEFINITION__TARGET:
            setTarget((LinkEventDefinition) newValue);
            return;
        case Bpmn2Package.LINK_EVENT_DEFINITION__NAME:
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
        case Bpmn2Package.LINK_EVENT_DEFINITION__SOURCE:
            getSource().clear();
            return;
        case Bpmn2Package.LINK_EVENT_DEFINITION__TARGET:
            setTarget((LinkEventDefinition) null);
            return;
        case Bpmn2Package.LINK_EVENT_DEFINITION__NAME:
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
        case Bpmn2Package.LINK_EVENT_DEFINITION__SOURCE:
            return source != null && !source.isEmpty();
        case Bpmn2Package.LINK_EVENT_DEFINITION__TARGET:
            return target != null;
        case Bpmn2Package.LINK_EVENT_DEFINITION__NAME:
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

} //LinkEventDefinitionImpl
