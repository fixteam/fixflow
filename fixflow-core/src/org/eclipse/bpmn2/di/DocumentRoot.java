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

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.di.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.DocumentRoot#getBPMNDiagram <em>BPMN Diagram</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.DocumentRoot#getBPMNEdge <em>BPMN Edge</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.DocumentRoot#getBPMNLabel <em>BPMN Label</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.DocumentRoot#getBPMNLabelStyle <em>BPMN Label Style</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.DocumentRoot#getBPMNPlane <em>BPMN Plane</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.DocumentRoot#getBPMNShape <em>BPMN Shape</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.di.BpmnDiPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
    /**
     * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mixed</em>' attribute list.
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getDocumentRoot_Mixed()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='elementWildcard' name=':mixed'"
     * @generated
     */
    FeatureMap getMixed();

    /**
     * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>XMLNS Prefix Map</em>' map.
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getDocumentRoot_XMLNSPrefixMap()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
     *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
     * @generated
     */
    Map<String, String> getXMLNSPrefixMap();

    /**
     * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>XSI Schema Location</em>' map.
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getDocumentRoot_XSISchemaLocation()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
     *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
     * @generated
     */
    Map<String, String> getXSISchemaLocation();

    /**
     * Returns the value of the '<em><b>BPMN Diagram</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>BPMN Diagram</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Diagram</em>' containment reference.
     * @see #setBPMNDiagram(BPMNDiagram)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getDocumentRoot_BPMNDiagram()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='BPMNDiagram' namespace='http://www.omg.org/spec/BPMN/20100524/DI'"
     * @generated
     */
    BPMNDiagram getBPMNDiagram();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.DocumentRoot#getBPMNDiagram <em>BPMN Diagram</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Diagram</em>' containment reference.
     * @see #getBPMNDiagram()
     * @generated
     */
    void setBPMNDiagram(BPMNDiagram value);

    /**
     * Returns the value of the '<em><b>BPMN Edge</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>BPMN Edge</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Edge</em>' containment reference.
     * @see #setBPMNEdge(BPMNEdge)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getDocumentRoot_BPMNEdge()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='BPMNEdge' namespace='http://www.omg.org/spec/BPMN/20100524/DI' affiliation='http://www.omg.org/spec/DD/20100524/DI#DiagramElement'"
     * @generated
     */
    BPMNEdge getBPMNEdge();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.DocumentRoot#getBPMNEdge <em>BPMN Edge</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Edge</em>' containment reference.
     * @see #getBPMNEdge()
     * @generated
     */
    void setBPMNEdge(BPMNEdge value);

    /**
     * Returns the value of the '<em><b>BPMN Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>BPMN Label</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Label</em>' containment reference.
     * @see #setBPMNLabel(BPMNLabel)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getDocumentRoot_BPMNLabel()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='BPMNLabel' namespace='http://www.omg.org/spec/BPMN/20100524/DI'"
     * @generated
     */
    BPMNLabel getBPMNLabel();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.DocumentRoot#getBPMNLabel <em>BPMN Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Label</em>' containment reference.
     * @see #getBPMNLabel()
     * @generated
     */
    void setBPMNLabel(BPMNLabel value);

    /**
     * Returns the value of the '<em><b>BPMN Label Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>BPMN Label Style</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Label Style</em>' containment reference.
     * @see #setBPMNLabelStyle(BPMNLabelStyle)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getDocumentRoot_BPMNLabelStyle()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='BPMNLabelStyle' namespace='http://www.omg.org/spec/BPMN/20100524/DI'"
     * @generated
     */
    BPMNLabelStyle getBPMNLabelStyle();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.DocumentRoot#getBPMNLabelStyle <em>BPMN Label Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Label Style</em>' containment reference.
     * @see #getBPMNLabelStyle()
     * @generated
     */
    void setBPMNLabelStyle(BPMNLabelStyle value);

    /**
     * Returns the value of the '<em><b>BPMN Plane</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>BPMN Plane</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Plane</em>' containment reference.
     * @see #setBPMNPlane(BPMNPlane)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getDocumentRoot_BPMNPlane()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='BPMNPlane' namespace='http://www.omg.org/spec/BPMN/20100524/DI'"
     * @generated
     */
    BPMNPlane getBPMNPlane();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.DocumentRoot#getBPMNPlane <em>BPMN Plane</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Plane</em>' containment reference.
     * @see #getBPMNPlane()
     * @generated
     */
    void setBPMNPlane(BPMNPlane value);

    /**
     * Returns the value of the '<em><b>BPMN Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>BPMN Shape</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Shape</em>' containment reference.
     * @see #setBPMNShape(BPMNShape)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getDocumentRoot_BPMNShape()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='BPMNShape' namespace='http://www.omg.org/spec/BPMN/20100524/DI' affiliation='http://www.omg.org/spec/DD/20100524/DI#DiagramElement'"
     * @generated
     */
    BPMNShape getBPMNShape();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.DocumentRoot#getBPMNShape <em>BPMN Shape</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Shape</em>' containment reference.
     * @see #getBPMNShape()
     * @generated
     */
    void setBPMNShape(BPMNShape value);

} // DocumentRoot
