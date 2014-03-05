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

import java.util.List;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.ConversationLink;
import org.eclipse.bpmn2.InteractionNode;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interaction Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.InteractionNodeImpl#getIncomingConversationLinks <em>Incoming Conversation Links</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.InteractionNodeImpl#getOutgoingConversationLinks <em>Outgoing Conversation Links</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InteractionNodeImpl extends EObjectImpl implements InteractionNode {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InteractionNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.INTERACTION_NODE;
    }

    /**
     * <!-- begin-user-doc -->
     * Virtual opposite of {@link ConversationLink#getTargetRef()}.
     * For general information about virtual opposites see {@link CategoryValueImpl#getCategorizedFlowElements()}
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public List<ConversationLink> getIncomingConversationLinks() {
        if (eResource() instanceof Bpmn2Resource) {
            return ((Bpmn2Resource) eResource()).getOppositeReferenceAdapter().getOppositeList(
                    ConversationLink.class, this,
                    Bpmn2Package.Literals.CONVERSATION_LINK__TARGET_REF);
        }
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * Virtual opposite of {@link ConversationLink#getSourceRef()}.
     * For general information about virtual opposites see {@link CategoryValueImpl#getCategorizedFlowElements()}
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public List<ConversationLink> getOutgoingConversationLinks() {
        if (eResource() instanceof Bpmn2Resource) {
            return ((Bpmn2Resource) eResource()).getOppositeReferenceAdapter().getOppositeList(
                    ConversationLink.class, this,
                    Bpmn2Package.Literals.CONVERSATION_LINK__SOURCE_REF);
        }
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Bpmn2Package.INTERACTION_NODE__INCOMING_CONVERSATION_LINKS:
            return getIncomingConversationLinks();
        case Bpmn2Package.INTERACTION_NODE__OUTGOING_CONVERSATION_LINKS:
            return getOutgoingConversationLinks();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case Bpmn2Package.INTERACTION_NODE__INCOMING_CONVERSATION_LINKS:
            return !getIncomingConversationLinks().isEmpty();
        case Bpmn2Package.INTERACTION_NODE__OUTGOING_CONVERSATION_LINKS:
            return !getOutgoingConversationLinks().isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //InteractionNodeImpl
