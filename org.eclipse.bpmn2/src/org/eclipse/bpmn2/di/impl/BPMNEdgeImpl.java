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

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.di.BPMNLabel;
import org.eclipse.bpmn2.di.BpmnDiPackage;
import org.eclipse.bpmn2.di.MessageVisibleKind;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.dd.di.impl.LabeledEdgeImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BPMN Edge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.di.impl.BPMNEdgeImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.impl.BPMNEdgeImpl#getBpmnElement <em>Bpmn Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.impl.BPMNEdgeImpl#getMessageVisibleKind <em>Message Visible Kind</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.impl.BPMNEdgeImpl#getSourceElement <em>Source Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.impl.BPMNEdgeImpl#getTargetElement <em>Target Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BPMNEdgeImpl extends LabeledEdgeImpl implements BPMNEdge {
    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected BPMNLabel label;

    /**
     * The cached value of the '{@link #getBpmnElement() <em>Bpmn Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBpmnElement()
     * @generated
     * @ordered
     */
    protected BaseElement bpmnElement;

    /**
     * The default value of the '{@link #getMessageVisibleKind() <em>Message Visible Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessageVisibleKind()
     * @generated
     * @ordered
     */
    protected static final MessageVisibleKind MESSAGE_VISIBLE_KIND_EDEFAULT = MessageVisibleKind.INITIATING;

    /**
     * The cached value of the '{@link #getMessageVisibleKind() <em>Message Visible Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessageVisibleKind()
     * @generated
     * @ordered
     */
    protected MessageVisibleKind messageVisibleKind = MESSAGE_VISIBLE_KIND_EDEFAULT;

    /**
     * The cached value of the '{@link #getSourceElement() <em>Source Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceElement()
     * @generated
     * @ordered
     */
    protected DiagramElement sourceElement;

    /**
     * The cached value of the '{@link #getTargetElement() <em>Target Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetElement()
     * @generated
     * @ordered
     */
    protected DiagramElement targetElement;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BPMNEdgeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BpmnDiPackage.Literals.BPMN_EDGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNLabel getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLabel(BPMNLabel newLabel, NotificationChain msgs) {
        BPMNLabel oldLabel = label;
        label = newLabel;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    BpmnDiPackage.BPMN_EDGE__LABEL, oldLabel, newLabel);
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
    public void setLabel(BPMNLabel newLabel) {
        if (newLabel != label) {
            NotificationChain msgs = null;
            if (label != null)
                msgs = ((InternalEObject) label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - BpmnDiPackage.BPMN_EDGE__LABEL, null, msgs);
            if (newLabel != null)
                msgs = ((InternalEObject) newLabel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - BpmnDiPackage.BPMN_EDGE__LABEL, null, msgs);
            msgs = basicSetLabel(newLabel, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmnDiPackage.BPMN_EDGE__LABEL,
                    newLabel, newLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BaseElement getBpmnElement() {
        if (bpmnElement != null && bpmnElement.eIsProxy()) {
            InternalEObject oldBpmnElement = (InternalEObject) bpmnElement;
            bpmnElement = (BaseElement) eResolveProxy(oldBpmnElement);
            if (bpmnElement != oldBpmnElement) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            BpmnDiPackage.BPMN_EDGE__BPMN_ELEMENT, oldBpmnElement, bpmnElement));
            }
        }
        return bpmnElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BaseElement basicGetBpmnElement() {
        return bpmnElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBpmnElement(BaseElement newBpmnElement) {
        BaseElement oldBpmnElement = bpmnElement;
        bpmnElement = newBpmnElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    BpmnDiPackage.BPMN_EDGE__BPMN_ELEMENT, oldBpmnElement, bpmnElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MessageVisibleKind getMessageVisibleKind() {
        return messageVisibleKind;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMessageVisibleKind(MessageVisibleKind newMessageVisibleKind) {
        MessageVisibleKind oldMessageVisibleKind = messageVisibleKind;
        messageVisibleKind = newMessageVisibleKind == null ? MESSAGE_VISIBLE_KIND_EDEFAULT
                : newMessageVisibleKind;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    BpmnDiPackage.BPMN_EDGE__MESSAGE_VISIBLE_KIND, oldMessageVisibleKind,
                    messageVisibleKind));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DiagramElement getSourceElement() {
        if (sourceElement != null && sourceElement.eIsProxy()) {
            InternalEObject oldSourceElement = (InternalEObject) sourceElement;
            sourceElement = (DiagramElement) eResolveProxy(oldSourceElement);
            if (sourceElement != oldSourceElement) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            BpmnDiPackage.BPMN_EDGE__SOURCE_ELEMENT, oldSourceElement,
                            sourceElement));
            }
        }
        return sourceElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DiagramElement basicGetSourceElement() {
        return sourceElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceElement(DiagramElement newSourceElement) {
        DiagramElement oldSourceElement = sourceElement;
        sourceElement = newSourceElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    BpmnDiPackage.BPMN_EDGE__SOURCE_ELEMENT, oldSourceElement, sourceElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DiagramElement getTargetElement() {
        if (targetElement != null && targetElement.eIsProxy()) {
            InternalEObject oldTargetElement = (InternalEObject) targetElement;
            targetElement = (DiagramElement) eResolveProxy(oldTargetElement);
            if (targetElement != oldTargetElement) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            BpmnDiPackage.BPMN_EDGE__TARGET_ELEMENT, oldTargetElement,
                            targetElement));
            }
        }
        return targetElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DiagramElement basicGetTargetElement() {
        return targetElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetElement(DiagramElement newTargetElement) {
        DiagramElement oldTargetElement = targetElement;
        targetElement = newTargetElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    BpmnDiPackage.BPMN_EDGE__TARGET_ELEMENT, oldTargetElement, targetElement));
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
        case BpmnDiPackage.BPMN_EDGE__LABEL:
            return basicSetLabel(null, msgs);
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
        case BpmnDiPackage.BPMN_EDGE__LABEL:
            return getLabel();
        case BpmnDiPackage.BPMN_EDGE__BPMN_ELEMENT:
            if (resolve)
                return getBpmnElement();
            return basicGetBpmnElement();
        case BpmnDiPackage.BPMN_EDGE__MESSAGE_VISIBLE_KIND:
            return getMessageVisibleKind();
        case BpmnDiPackage.BPMN_EDGE__SOURCE_ELEMENT:
            if (resolve)
                return getSourceElement();
            return basicGetSourceElement();
        case BpmnDiPackage.BPMN_EDGE__TARGET_ELEMENT:
            if (resolve)
                return getTargetElement();
            return basicGetTargetElement();
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
        case BpmnDiPackage.BPMN_EDGE__LABEL:
            setLabel((BPMNLabel) newValue);
            return;
        case BpmnDiPackage.BPMN_EDGE__BPMN_ELEMENT:
            setBpmnElement((BaseElement) newValue);
            return;
        case BpmnDiPackage.BPMN_EDGE__MESSAGE_VISIBLE_KIND:
            setMessageVisibleKind((MessageVisibleKind) newValue);
            return;
        case BpmnDiPackage.BPMN_EDGE__SOURCE_ELEMENT:
            setSourceElement((DiagramElement) newValue);
            return;
        case BpmnDiPackage.BPMN_EDGE__TARGET_ELEMENT:
            setTargetElement((DiagramElement) newValue);
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
        case BpmnDiPackage.BPMN_EDGE__LABEL:
            setLabel((BPMNLabel) null);
            return;
        case BpmnDiPackage.BPMN_EDGE__BPMN_ELEMENT:
            setBpmnElement((BaseElement) null);
            return;
        case BpmnDiPackage.BPMN_EDGE__MESSAGE_VISIBLE_KIND:
            setMessageVisibleKind(MESSAGE_VISIBLE_KIND_EDEFAULT);
            return;
        case BpmnDiPackage.BPMN_EDGE__SOURCE_ELEMENT:
            setSourceElement((DiagramElement) null);
            return;
        case BpmnDiPackage.BPMN_EDGE__TARGET_ELEMENT:
            setTargetElement((DiagramElement) null);
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
        case BpmnDiPackage.BPMN_EDGE__LABEL:
            return label != null;
        case BpmnDiPackage.BPMN_EDGE__BPMN_ELEMENT:
            return bpmnElement != null;
        case BpmnDiPackage.BPMN_EDGE__MESSAGE_VISIBLE_KIND:
            return messageVisibleKind != MESSAGE_VISIBLE_KIND_EDEFAULT;
        case BpmnDiPackage.BPMN_EDGE__SOURCE_ELEMENT:
            return sourceElement != null;
        case BpmnDiPackage.BPMN_EDGE__TARGET_ELEMENT:
            return targetElement != null;
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
        result.append(" (messageVisibleKind: ");
        result.append(messageVisibleKind);
        result.append(')');
        return result.toString();
    }

} //BPMNEdgeImpl
