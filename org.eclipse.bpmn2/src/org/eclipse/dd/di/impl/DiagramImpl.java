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
package org.eclipse.dd.di.impl;

import java.util.List;

import org.eclipse.dd.di.DiPackage;
import org.eclipse.dd.di.Diagram;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.dd.di.Style;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.dd.di.impl.DiagramImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.dd.di.impl.DiagramImpl#getOwnedStyle <em>Owned Style</em>}</li>
 *   <li>{@link org.eclipse.dd.di.impl.DiagramImpl#getRootElement <em>Root Element</em>}</li>
 *   <li>{@link org.eclipse.dd.di.impl.DiagramImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.dd.di.impl.DiagramImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.dd.di.impl.DiagramImpl#getResolution <em>Resolution</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DiagramImpl extends EObjectImpl implements Diagram {
    /**
     * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENTATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected String documentation = DOCUMENTATION_EDEFAULT;

    /**
     * The cached value of the '{@link #getOwnedStyle() <em>Owned Style</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOwnedStyle()
     * @generated
     * @ordered
     */
    protected EList<Style> ownedStyle;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ID_EDEFAULT;

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
     * The default value of the '{@link #getResolution() <em>Resolution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResolution()
     * @generated
     * @ordered
     */
    protected static final float RESOLUTION_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getResolution() <em>Resolution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResolution()
     * @generated
     * @ordered
     */
    protected float resolution = RESOLUTION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DiagramImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiPackage.Literals.DIAGRAM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDocumentation() {
        return documentation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDocumentation(String newDocumentation) {
        String oldDocumentation = documentation;
        documentation = newDocumentation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.DIAGRAM__DOCUMENTATION,
                    oldDocumentation, documentation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<Style> getOwnedStyle() {
        if (ownedStyle == null) {
            ownedStyle = new EObjectResolvingEList<Style>(Style.class, this,
                    DiPackage.DIAGRAM__OWNED_STYLE);
        }
        return ownedStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * Subclasses need to provide an implementation.
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public DiagramElement getRootElement() {
        // Diagram is abstract and rootElement is derived, subclasses have to provide an implementation 
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.DIAGRAM__ID, oldId, id));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.DIAGRAM__NAME, oldName,
                    name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getResolution() {
        return resolution;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResolution(float newResolution) {
        float oldResolution = resolution;
        resolution = newResolution;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.DIAGRAM__RESOLUTION,
                    oldResolution, resolution));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DiPackage.DIAGRAM__DOCUMENTATION:
            return getDocumentation();
        case DiPackage.DIAGRAM__OWNED_STYLE:
            return getOwnedStyle();
        case DiPackage.DIAGRAM__ROOT_ELEMENT:
            return getRootElement();
        case DiPackage.DIAGRAM__ID:
            return getId();
        case DiPackage.DIAGRAM__NAME:
            return getName();
        case DiPackage.DIAGRAM__RESOLUTION:
            return getResolution();
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
        case DiPackage.DIAGRAM__DOCUMENTATION:
            setDocumentation((String) newValue);
            return;
        case DiPackage.DIAGRAM__ID:
            setId((String) newValue);
            return;
        case DiPackage.DIAGRAM__NAME:
            setName((String) newValue);
            return;
        case DiPackage.DIAGRAM__RESOLUTION:
            setResolution((Float) newValue);
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
        case DiPackage.DIAGRAM__DOCUMENTATION:
            setDocumentation(DOCUMENTATION_EDEFAULT);
            return;
        case DiPackage.DIAGRAM__ID:
            setId(ID_EDEFAULT);
            return;
        case DiPackage.DIAGRAM__NAME:
            setName(NAME_EDEFAULT);
            return;
        case DiPackage.DIAGRAM__RESOLUTION:
            setResolution(RESOLUTION_EDEFAULT);
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
        case DiPackage.DIAGRAM__DOCUMENTATION:
            return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT
                    .equals(documentation);
        case DiPackage.DIAGRAM__OWNED_STYLE:
            return ownedStyle != null && !ownedStyle.isEmpty();
        case DiPackage.DIAGRAM__ROOT_ELEMENT:
            return getRootElement() != null;
        case DiPackage.DIAGRAM__ID:
            return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
        case DiPackage.DIAGRAM__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case DiPackage.DIAGRAM__RESOLUTION:
            return resolution != RESOLUTION_EDEFAULT;
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
        result.append(" (documentation: ");
        result.append(documentation);
        result.append(", id: ");
        result.append(id);
        result.append(", name: ");
        result.append(name);
        result.append(", resolution: ");
        result.append(resolution);
        result.append(')');
        return result.toString();
    }

} //DiagramImpl
