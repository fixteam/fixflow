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
package org.eclipse.dd.di;

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
 *   <li>{@link org.eclipse.dd.di.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DocumentRoot#getDiagramElement <em>Diagram Element</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DocumentRoot#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DocumentRoot#getEdge <em>Edge</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DocumentRoot#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DocumentRoot#getLabeledEdge <em>Labeled Edge</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DocumentRoot#getLabeledShape <em>Labeled Shape</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DocumentRoot#getNode <em>Node</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DocumentRoot#getPlane <em>Plane</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DocumentRoot#getShape <em>Shape</em>}</li>
 *   <li>{@link org.eclipse.dd.di.DocumentRoot#getStyle <em>Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.dd.di.DiPackage#getDocumentRoot()
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
     * @see org.eclipse.dd.di.DiPackage#getDocumentRoot_Mixed()
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
     * @see org.eclipse.dd.di.DiPackage#getDocumentRoot_XMLNSPrefixMap()
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
     * @see org.eclipse.dd.di.DiPackage#getDocumentRoot_XSISchemaLocation()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
     *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
     * @generated
     */
    Map<String, String> getXSISchemaLocation();

    /**
     * Returns the value of the '<em><b>Diagram Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Diagram Element</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Diagram Element</em>' containment reference.
     * @see #setDiagramElement(DiagramElement)
     * @see org.eclipse.dd.di.DiPackage#getDocumentRoot_DiagramElement()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='DiagramElement' namespace='http://www.omg.org/spec/DD/20100524/DI'"
     * @generated
     */
    DiagramElement getDiagramElement();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.DocumentRoot#getDiagramElement <em>Diagram Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Diagram Element</em>' containment reference.
     * @see #getDiagramElement()
     * @generated
     */
    void setDiagramElement(DiagramElement value);

    /**
     * Returns the value of the '<em><b>Diagram</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Diagram</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Diagram</em>' containment reference.
     * @see #setDiagram(Diagram)
     * @see org.eclipse.dd.di.DiPackage#getDocumentRoot_Diagram()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='Diagram' namespace='http://www.omg.org/spec/DD/20100524/DI'"
     * @generated
     */
    Diagram getDiagram();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.DocumentRoot#getDiagram <em>Diagram</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Diagram</em>' containment reference.
     * @see #getDiagram()
     * @generated
     */
    void setDiagram(Diagram value);

    /**
     * Returns the value of the '<em><b>Edge</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Edge</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Edge</em>' containment reference.
     * @see #setEdge(Edge)
     * @see org.eclipse.dd.di.DiPackage#getDocumentRoot_Edge()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='Edge' namespace='http://www.omg.org/spec/DD/20100524/DI'"
     * @generated
     */
    Edge getEdge();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.DocumentRoot#getEdge <em>Edge</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Edge</em>' containment reference.
     * @see #getEdge()
     * @generated
     */
    void setEdge(Edge value);

    /**
     * Returns the value of the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' containment reference.
     * @see #setLabel(Label)
     * @see org.eclipse.dd.di.DiPackage#getDocumentRoot_Label()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='Label' namespace='http://www.omg.org/spec/DD/20100524/DI'"
     * @generated
     */
    Label getLabel();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.DocumentRoot#getLabel <em>Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' containment reference.
     * @see #getLabel()
     * @generated
     */
    void setLabel(Label value);

    /**
     * Returns the value of the '<em><b>Labeled Edge</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Labeled Edge</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Labeled Edge</em>' containment reference.
     * @see #setLabeledEdge(LabeledEdge)
     * @see org.eclipse.dd.di.DiPackage#getDocumentRoot_LabeledEdge()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='LabeledEdge' namespace='http://www.omg.org/spec/DD/20100524/DI'"
     * @generated
     */
    LabeledEdge getLabeledEdge();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.DocumentRoot#getLabeledEdge <em>Labeled Edge</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Labeled Edge</em>' containment reference.
     * @see #getLabeledEdge()
     * @generated
     */
    void setLabeledEdge(LabeledEdge value);

    /**
     * Returns the value of the '<em><b>Labeled Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Labeled Shape</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Labeled Shape</em>' containment reference.
     * @see #setLabeledShape(LabeledShape)
     * @see org.eclipse.dd.di.DiPackage#getDocumentRoot_LabeledShape()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='LabeledShape' namespace='http://www.omg.org/spec/DD/20100524/DI'"
     * @generated
     */
    LabeledShape getLabeledShape();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.DocumentRoot#getLabeledShape <em>Labeled Shape</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Labeled Shape</em>' containment reference.
     * @see #getLabeledShape()
     * @generated
     */
    void setLabeledShape(LabeledShape value);

    /**
     * Returns the value of the '<em><b>Node</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node</em>' containment reference.
     * @see #setNode(Node)
     * @see org.eclipse.dd.di.DiPackage#getDocumentRoot_Node()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='Node' namespace='http://www.omg.org/spec/DD/20100524/DI'"
     * @generated
     */
    Node getNode();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.DocumentRoot#getNode <em>Node</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Node</em>' containment reference.
     * @see #getNode()
     * @generated
     */
    void setNode(Node value);

    /**
     * Returns the value of the '<em><b>Plane</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Plane</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Plane</em>' containment reference.
     * @see #setPlane(Plane)
     * @see org.eclipse.dd.di.DiPackage#getDocumentRoot_Plane()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='Plane' namespace='http://www.omg.org/spec/DD/20100524/DI'"
     * @generated
     */
    Plane getPlane();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.DocumentRoot#getPlane <em>Plane</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Plane</em>' containment reference.
     * @see #getPlane()
     * @generated
     */
    void setPlane(Plane value);

    /**
     * Returns the value of the '<em><b>Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Shape</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Shape</em>' containment reference.
     * @see #setShape(Shape)
     * @see org.eclipse.dd.di.DiPackage#getDocumentRoot_Shape()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='Shape' namespace='http://www.omg.org/spec/DD/20100524/DI'"
     * @generated
     */
    Shape getShape();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.DocumentRoot#getShape <em>Shape</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Shape</em>' containment reference.
     * @see #getShape()
     * @generated
     */
    void setShape(Shape value);

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(Style)
     * @see org.eclipse.dd.di.DiPackage#getDocumentRoot_Style()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='Style' namespace='http://www.omg.org/spec/DD/20100524/DI'"
     * @generated
     */
    Style getStyle();

    /**
     * Sets the value of the '{@link org.eclipse.dd.di.DocumentRoot#getStyle <em>Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(Style value);

} // DocumentRoot
