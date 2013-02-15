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
package org.eclipse.bpmn2.di;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.bpmn2.di.BpmnDiPackage
 * @generated
 */
public interface BpmnDiFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    BpmnDiFactory eINSTANCE = org.eclipse.bpmn2.di.impl.BpmnDiFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
    DocumentRoot createDocumentRoot();

    /**
     * Returns a new object of class '<em>BPMN Diagram</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>BPMN Diagram</em>'.
     * @generated
     */
    BPMNDiagram createBPMNDiagram();

    /**
     * Returns a new object of class '<em>BPMN Edge</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>BPMN Edge</em>'.
     * @generated
     */
    BPMNEdge createBPMNEdge();

    /**
     * Returns a new object of class '<em>BPMN Label</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>BPMN Label</em>'.
     * @generated
     */
    BPMNLabel createBPMNLabel();

    /**
     * Returns a new object of class '<em>BPMN Label Style</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>BPMN Label Style</em>'.
     * @generated
     */
    BPMNLabelStyle createBPMNLabelStyle();

    /**
     * Returns a new object of class '<em>BPMN Plane</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>BPMN Plane</em>'.
     * @generated
     */
    BPMNPlane createBPMNPlane();

    /**
     * Returns a new object of class '<em>BPMN Shape</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>BPMN Shape</em>'.
     * @generated
     */
    BPMNShape createBPMNShape();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    BpmnDiPackage getBpmnDiPackage();

} //BpmnDiFactory
