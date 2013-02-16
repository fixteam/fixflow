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

import org.eclipse.bpmn2.Artifact;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.SubChoreography;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sub Choreography</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.SubChoreographyImpl#getLaneSets <em>Lane Sets</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.SubChoreographyImpl#getFlowElements <em>Flow Elements</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.SubChoreographyImpl#getArtifacts <em>Artifacts</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SubChoreographyImpl extends ChoreographyActivityImpl implements SubChoreography {
    /**
     * The cached value of the '{@link #getLaneSets() <em>Lane Sets</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLaneSets()
     * @generated
     * @ordered
     */
    protected EList<LaneSet> laneSets;

    /**
     * The cached value of the '{@link #getFlowElements() <em>Flow Elements</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFlowElements()
     * @generated
     * @ordered
     */
    protected EList<FlowElement> flowElements;

    /**
     * The cached value of the '{@link #getArtifacts() <em>Artifacts</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArtifacts()
     * @generated
     * @ordered
     */
    protected EList<Artifact> artifacts;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SubChoreographyImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.SUB_CHOREOGRAPHY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<LaneSet> getLaneSets() {
        if (laneSets == null) {
            laneSets = new EObjectContainmentEList<LaneSet>(LaneSet.class, this,
                    Bpmn2Package.SUB_CHOREOGRAPHY__LANE_SETS);
        }
        return laneSets;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<FlowElement> getFlowElements() {
        if (flowElements == null) {
            flowElements = new EObjectContainmentEList<FlowElement>(FlowElement.class, this,
                    Bpmn2Package.SUB_CHOREOGRAPHY__FLOW_ELEMENTS);
        }
        return flowElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<Artifact> getArtifacts() {
        if (artifacts == null) {
            artifacts = new EObjectContainmentEList<Artifact>(Artifact.class, this,
                    Bpmn2Package.SUB_CHOREOGRAPHY__ARTIFACTS);
        }
        return artifacts;
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
        case Bpmn2Package.SUB_CHOREOGRAPHY__LANE_SETS:
            return ((InternalEList<?>) getLaneSets()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.SUB_CHOREOGRAPHY__FLOW_ELEMENTS:
            return ((InternalEList<?>) getFlowElements()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.SUB_CHOREOGRAPHY__ARTIFACTS:
            return ((InternalEList<?>) getArtifacts()).basicRemove(otherEnd, msgs);
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
        case Bpmn2Package.SUB_CHOREOGRAPHY__LANE_SETS:
            return getLaneSets();
        case Bpmn2Package.SUB_CHOREOGRAPHY__FLOW_ELEMENTS:
            return getFlowElements();
        case Bpmn2Package.SUB_CHOREOGRAPHY__ARTIFACTS:
            return getArtifacts();
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
        case Bpmn2Package.SUB_CHOREOGRAPHY__LANE_SETS:
            getLaneSets().clear();
            getLaneSets().addAll((Collection<? extends LaneSet>) newValue);
            return;
        case Bpmn2Package.SUB_CHOREOGRAPHY__FLOW_ELEMENTS:
            getFlowElements().clear();
            getFlowElements().addAll((Collection<? extends FlowElement>) newValue);
            return;
        case Bpmn2Package.SUB_CHOREOGRAPHY__ARTIFACTS:
            getArtifacts().clear();
            getArtifacts().addAll((Collection<? extends Artifact>) newValue);
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
        case Bpmn2Package.SUB_CHOREOGRAPHY__LANE_SETS:
            getLaneSets().clear();
            return;
        case Bpmn2Package.SUB_CHOREOGRAPHY__FLOW_ELEMENTS:
            getFlowElements().clear();
            return;
        case Bpmn2Package.SUB_CHOREOGRAPHY__ARTIFACTS:
            getArtifacts().clear();
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
        case Bpmn2Package.SUB_CHOREOGRAPHY__LANE_SETS:
            return laneSets != null && !laneSets.isEmpty();
        case Bpmn2Package.SUB_CHOREOGRAPHY__FLOW_ELEMENTS:
            return flowElements != null && !flowElements.isEmpty();
        case Bpmn2Package.SUB_CHOREOGRAPHY__ARTIFACTS:
            return artifacts != null && !artifacts.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == FlowElementsContainer.class) {
            switch (derivedFeatureID) {
            case Bpmn2Package.SUB_CHOREOGRAPHY__LANE_SETS:
                return Bpmn2Package.FLOW_ELEMENTS_CONTAINER__LANE_SETS;
            case Bpmn2Package.SUB_CHOREOGRAPHY__FLOW_ELEMENTS:
                return Bpmn2Package.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == FlowElementsContainer.class) {
            switch (baseFeatureID) {
            case Bpmn2Package.FLOW_ELEMENTS_CONTAINER__LANE_SETS:
                return Bpmn2Package.SUB_CHOREOGRAPHY__LANE_SETS;
            case Bpmn2Package.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS:
                return Bpmn2Package.SUB_CHOREOGRAPHY__FLOW_ELEMENTS;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} //SubChoreographyImpl
