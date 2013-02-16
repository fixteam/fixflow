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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Script Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.ScriptTask#getScript <em>Script</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ScriptTask#getScriptFormat <em>Script Format</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getScriptTask()
 * @model extendedMetaData="name='tScriptTask' kind='elementOnly'"
 * @generated
 */
public interface ScriptTask extends Task {
    /**
     * Returns the value of the '<em><b>Script</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Script</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Script</em>' attribute.
     * @see #setScript(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getScriptTask_Script()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='element' name='script' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    String getScript();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ScriptTask#getScript <em>Script</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Script</em>' attribute.
     * @see #getScript()
     * @generated
     */
    void setScript(String value);

    /**
     * Returns the value of the '<em><b>Script Format</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Script Format</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Script Format</em>' attribute.
     * @see #setScriptFormat(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getScriptTask_ScriptFormat()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='scriptFormat'"
     * @generated
     */
    String getScriptFormat();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ScriptTask#getScriptFormat <em>Script Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Script Format</em>' attribute.
     * @see #getScriptFormat()
     * @generated
     */
    void setScriptFormat(String value);

} // ScriptTask
