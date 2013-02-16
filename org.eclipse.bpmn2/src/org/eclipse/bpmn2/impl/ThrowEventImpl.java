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
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.InputSet;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Throw Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.ThrowEventImpl#getDataInputs <em>Data Inputs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ThrowEventImpl#getDataInputAssociation <em>Data Input Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ThrowEventImpl#getInputSet <em>Input Set</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ThrowEventImpl#getEventDefinitions <em>Event Definitions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ThrowEventImpl#getEventDefinitionRefs <em>Event Definition Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ThrowEventImpl extends EventImpl implements ThrowEvent {
    /**
     * The cached value of the '{@link #getDataInputs() <em>Data Inputs</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataInputs()
     * @generated
     * @ordered
     */
    protected EList<DataInput> dataInputs;

    /**
     * The cached value of the '{@link #getDataInputAssociation() <em>Data Input Association</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataInputAssociation()
     * @generated
     * @ordered
     */
    protected EList<DataInputAssociation> dataInputAssociation;

    /**
     * The cached value of the '{@link #getInputSet() <em>Input Set</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInputSet()
     * @generated
     * @ordered
     */
    protected InputSet inputSet;

    /**
     * The cached value of the '{@link #getEventDefinitions() <em>Event Definitions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEventDefinitions()
     * @generated
     * @ordered
     */
    protected EList<EventDefinition> eventDefinitions;

    /**
     * The cached value of the '{@link #getEventDefinitionRefs() <em>Event Definition Refs</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEventDefinitionRefs()
     * @generated
     * @ordered
     */
    protected EList<EventDefinition> eventDefinitionRefs;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ThrowEventImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.THROW_EVENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<DataInput> getDataInputs() {
        if (dataInputs == null) {
            dataInputs = new EObjectContainmentEList<DataInput>(DataInput.class, this,
                    Bpmn2Package.THROW_EVENT__DATA_INPUTS);
        }
        return dataInputs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<DataInputAssociation> getDataInputAssociation() {
        if (dataInputAssociation == null) {
            dataInputAssociation = new EObjectContainmentEList<DataInputAssociation>(
                    DataInputAssociation.class, this,
                    Bpmn2Package.THROW_EVENT__DATA_INPUT_ASSOCIATION);
        }
        return dataInputAssociation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputSet getInputSet() {
        return inputSet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInputSet(InputSet newInputSet, NotificationChain msgs) {
        InputSet oldInputSet = inputSet;
        inputSet = newInputSet;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.THROW_EVENT__INPUT_SET, oldInputSet, newInputSet);
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
    public void setInputSet(InputSet newInputSet) {
        if (newInputSet != inputSet) {
            NotificationChain msgs = null;
            if (inputSet != null)
                msgs = ((InternalEObject) inputSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - Bpmn2Package.THROW_EVENT__INPUT_SET, null, msgs);
            if (newInputSet != null)
                msgs = ((InternalEObject) newInputSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - Bpmn2Package.THROW_EVENT__INPUT_SET, null, msgs);
            msgs = basicSetInputSet(newInputSet, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.THROW_EVENT__INPUT_SET, newInputSet, newInputSet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<EventDefinition> getEventDefinitions() {
        if (eventDefinitions == null) {
            eventDefinitions = new EObjectContainmentEList<EventDefinition>(EventDefinition.class,
                    this, Bpmn2Package.THROW_EVENT__EVENT_DEFINITIONS);
        }
        return eventDefinitions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<EventDefinition> getEventDefinitionRefs() {
        if (eventDefinitionRefs == null) {
            eventDefinitionRefs = new EObjectResolvingEList<EventDefinition>(EventDefinition.class,
                    this, Bpmn2Package.THROW_EVENT__EVENT_DEFINITION_REFS);
        }
        return eventDefinitionRefs;
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
        case Bpmn2Package.THROW_EVENT__DATA_INPUTS:
            return ((InternalEList<?>) getDataInputs()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.THROW_EVENT__DATA_INPUT_ASSOCIATION:
            return ((InternalEList<?>) getDataInputAssociation()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.THROW_EVENT__INPUT_SET:
            return basicSetInputSet(null, msgs);
        case Bpmn2Package.THROW_EVENT__EVENT_DEFINITIONS:
            return ((InternalEList<?>) getEventDefinitions()).basicRemove(otherEnd, msgs);
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
        case Bpmn2Package.THROW_EVENT__DATA_INPUTS:
            return getDataInputs();
        case Bpmn2Package.THROW_EVENT__DATA_INPUT_ASSOCIATION:
            return getDataInputAssociation();
        case Bpmn2Package.THROW_EVENT__INPUT_SET:
            return getInputSet();
        case Bpmn2Package.THROW_EVENT__EVENT_DEFINITIONS:
            return getEventDefinitions();
        case Bpmn2Package.THROW_EVENT__EVENT_DEFINITION_REFS:
            return getEventDefinitionRefs();
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
        case Bpmn2Package.THROW_EVENT__DATA_INPUTS:
            getDataInputs().clear();
            getDataInputs().addAll((Collection<? extends DataInput>) newValue);
            return;
        case Bpmn2Package.THROW_EVENT__DATA_INPUT_ASSOCIATION:
            getDataInputAssociation().clear();
            getDataInputAssociation().addAll((Collection<? extends DataInputAssociation>) newValue);
            return;
        case Bpmn2Package.THROW_EVENT__INPUT_SET:
            setInputSet((InputSet) newValue);
            return;
        case Bpmn2Package.THROW_EVENT__EVENT_DEFINITIONS:
            getEventDefinitions().clear();
            getEventDefinitions().addAll((Collection<? extends EventDefinition>) newValue);
            return;
        case Bpmn2Package.THROW_EVENT__EVENT_DEFINITION_REFS:
            getEventDefinitionRefs().clear();
            getEventDefinitionRefs().addAll((Collection<? extends EventDefinition>) newValue);
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
        case Bpmn2Package.THROW_EVENT__DATA_INPUTS:
            getDataInputs().clear();
            return;
        case Bpmn2Package.THROW_EVENT__DATA_INPUT_ASSOCIATION:
            getDataInputAssociation().clear();
            return;
        case Bpmn2Package.THROW_EVENT__INPUT_SET:
            setInputSet((InputSet) null);
            return;
        case Bpmn2Package.THROW_EVENT__EVENT_DEFINITIONS:
            getEventDefinitions().clear();
            return;
        case Bpmn2Package.THROW_EVENT__EVENT_DEFINITION_REFS:
            getEventDefinitionRefs().clear();
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
        case Bpmn2Package.THROW_EVENT__DATA_INPUTS:
            return dataInputs != null && !dataInputs.isEmpty();
        case Bpmn2Package.THROW_EVENT__DATA_INPUT_ASSOCIATION:
            return dataInputAssociation != null && !dataInputAssociation.isEmpty();
        case Bpmn2Package.THROW_EVENT__INPUT_SET:
            return inputSet != null;
        case Bpmn2Package.THROW_EVENT__EVENT_DEFINITIONS:
            return eventDefinitions != null && !eventDefinitions.isEmpty();
        case Bpmn2Package.THROW_EVENT__EVENT_DEFINITION_REFS:
            return eventDefinitionRefs != null && !eventDefinitionRefs.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //ThrowEventImpl
