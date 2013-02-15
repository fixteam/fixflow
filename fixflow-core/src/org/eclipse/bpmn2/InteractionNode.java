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
package org.eclipse.bpmn2;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interaction Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.InteractionNode#getIncomingConversationLinks <em>Incoming Conversation Links</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.InteractionNode#getOutgoingConversationLinks <em>Outgoing Conversation Links</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getInteractionNode()
 * @model extendedMetaData="abstract='true'"
 * @generated
 */
public interface InteractionNode extends EObject {
    /**
     * Returns the value of the '<em><b>Incoming Conversation Links</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.ConversationLink}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Incoming Conversation Links</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Incoming Conversation Links</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getInteractionNode_IncomingConversationLinks()
     * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
     * @generated
     */
    List<ConversationLink> getIncomingConversationLinks();

    /**
     * Returns the value of the '<em><b>Outgoing Conversation Links</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.ConversationLink}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Outgoing Conversation Links</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outgoing Conversation Links</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getInteractionNode_OutgoingConversationLinks()
     * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
     * @generated
     */
    List<ConversationLink> getOutgoingConversationLinks();

} // InteractionNode
