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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.dd.di.DiFactory
 * @model kind="package"
 * @generated
 */
public interface DiPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "di";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.omg.org/spec/DD/20100524/DI-XMI";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "di";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    DiPackage eINSTANCE = org.eclipse.dd.di.impl.DiPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.dd.di.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.dd.di.impl.DocumentRootImpl
     * @see org.eclipse.dd.di.impl.DiPackageImpl#getDocumentRoot()
     * @generated
     */
    int DOCUMENT_ROOT = 0;

    /**
     * The feature id for the '<em><b>Mixed</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__MIXED = 0;

    /**
     * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

    /**
     * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

    /**
     * The feature id for the '<em><b>Diagram Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__DIAGRAM_ELEMENT = 3;

    /**
     * The feature id for the '<em><b>Diagram</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__DIAGRAM = 4;

    /**
     * The feature id for the '<em><b>Edge</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__EDGE = 5;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LABEL = 6;

    /**
     * The feature id for the '<em><b>Labeled Edge</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LABELED_EDGE = 7;

    /**
     * The feature id for the '<em><b>Labeled Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LABELED_SHAPE = 8;

    /**
     * The feature id for the '<em><b>Node</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__NODE = 9;

    /**
     * The feature id for the '<em><b>Plane</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__PLANE = 10;

    /**
     * The feature id for the '<em><b>Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__SHAPE = 11;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STYLE = 12;

    /**
     * The number of structural features of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT_FEATURE_COUNT = 13;

    /**
     * The meta object id for the '{@link org.eclipse.dd.di.impl.DiagramImpl <em>Diagram</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.dd.di.impl.DiagramImpl
     * @see org.eclipse.dd.di.impl.DiPackageImpl#getDiagram()
     * @generated
     */
    int DIAGRAM = 1;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__DOCUMENTATION = 0;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__OWNED_STYLE = 1;

    /**
     * The feature id for the '<em><b>Root Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__ROOT_ELEMENT = 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__ID = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__NAME = 4;

    /**
     * The feature id for the '<em><b>Resolution</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__RESOLUTION = 5;

    /**
     * The number of structural features of the '<em>Diagram</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '{@link org.eclipse.dd.di.impl.DiagramElementImpl <em>Diagram Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.dd.di.impl.DiagramElementImpl
     * @see org.eclipse.dd.di.impl.DiPackageImpl#getDiagramElement()
     * @generated
     */
    int DIAGRAM_ELEMENT = 2;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT__OWNING_DIAGRAM = 0;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT__OWNING_ELEMENT = 1;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT__OWNED_ELEMENT = 2;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT__MODEL_ELEMENT = 3;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT__STYLE = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT__ID = 5;

    /**
     * The number of structural features of the '<em>Diagram Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '{@link org.eclipse.dd.di.impl.EdgeImpl <em>Edge</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.dd.di.impl.EdgeImpl
     * @see org.eclipse.dd.di.impl.DiPackageImpl#getEdge()
     * @generated
     */
    int EDGE = 3;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__OWNING_DIAGRAM = DIAGRAM_ELEMENT__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__OWNING_ELEMENT = DIAGRAM_ELEMENT__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__OWNED_ELEMENT = DIAGRAM_ELEMENT__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__MODEL_ELEMENT = DIAGRAM_ELEMENT__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__STYLE = DIAGRAM_ELEMENT__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__ID = DIAGRAM_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__SOURCE = DIAGRAM_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__TARGET = DIAGRAM_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Waypoint</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__WAYPOINT = DIAGRAM_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_FEATURE_COUNT = DIAGRAM_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.dd.di.impl.NodeImpl <em>Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.dd.di.impl.NodeImpl
     * @see org.eclipse.dd.di.impl.DiPackageImpl#getNode()
     * @generated
     */
    int NODE = 7;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__OWNING_DIAGRAM = DIAGRAM_ELEMENT__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__OWNING_ELEMENT = DIAGRAM_ELEMENT__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__OWNED_ELEMENT = DIAGRAM_ELEMENT__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__MODEL_ELEMENT = DIAGRAM_ELEMENT__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__STYLE = DIAGRAM_ELEMENT__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__ID = DIAGRAM_ELEMENT__ID;

    /**
     * The number of structural features of the '<em>Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_FEATURE_COUNT = DIAGRAM_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.dd.di.impl.LabelImpl <em>Label</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.dd.di.impl.LabelImpl
     * @see org.eclipse.dd.di.impl.DiPackageImpl#getLabel()
     * @generated
     */
    int LABEL = 4;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__OWNING_DIAGRAM = NODE__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__OWNING_ELEMENT = NODE__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__OWNED_ELEMENT = NODE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__MODEL_ELEMENT = NODE__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__STYLE = NODE__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__ID = NODE__ID;

    /**
     * The feature id for the '<em><b>Bounds</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__BOUNDS = NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Label</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.dd.di.impl.LabeledEdgeImpl <em>Labeled Edge</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.dd.di.impl.LabeledEdgeImpl
     * @see org.eclipse.dd.di.impl.DiPackageImpl#getLabeledEdge()
     * @generated
     */
    int LABELED_EDGE = 5;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__OWNING_DIAGRAM = EDGE__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__OWNING_ELEMENT = EDGE__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__OWNED_ELEMENT = EDGE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__MODEL_ELEMENT = EDGE__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__STYLE = EDGE__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__ID = EDGE__ID;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__SOURCE = EDGE__SOURCE;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__TARGET = EDGE__TARGET;

    /**
     * The feature id for the '<em><b>Waypoint</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__WAYPOINT = EDGE__WAYPOINT;

    /**
     * The feature id for the '<em><b>Owned Label</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__OWNED_LABEL = EDGE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Labeled Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE_FEATURE_COUNT = EDGE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.dd.di.impl.ShapeImpl <em>Shape</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.dd.di.impl.ShapeImpl
     * @see org.eclipse.dd.di.impl.DiPackageImpl#getShape()
     * @generated
     */
    int SHAPE = 9;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__OWNING_DIAGRAM = NODE__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__OWNING_ELEMENT = NODE__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__OWNED_ELEMENT = NODE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__MODEL_ELEMENT = NODE__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__STYLE = NODE__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__ID = NODE__ID;

    /**
     * The feature id for the '<em><b>Bounds</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__BOUNDS = NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Shape</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.dd.di.impl.LabeledShapeImpl <em>Labeled Shape</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.dd.di.impl.LabeledShapeImpl
     * @see org.eclipse.dd.di.impl.DiPackageImpl#getLabeledShape()
     * @generated
     */
    int LABELED_SHAPE = 6;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__OWNING_DIAGRAM = SHAPE__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__OWNING_ELEMENT = SHAPE__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__OWNED_ELEMENT = SHAPE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__MODEL_ELEMENT = SHAPE__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__STYLE = SHAPE__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__ID = SHAPE__ID;

    /**
     * The feature id for the '<em><b>Bounds</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__BOUNDS = SHAPE__BOUNDS;

    /**
     * The feature id for the '<em><b>Owned Label</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__OWNED_LABEL = SHAPE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Labeled Shape</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE_FEATURE_COUNT = SHAPE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.dd.di.impl.PlaneImpl <em>Plane</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.dd.di.impl.PlaneImpl
     * @see org.eclipse.dd.di.impl.DiPackageImpl#getPlane()
     * @generated
     */
    int PLANE = 8;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__OWNING_DIAGRAM = NODE__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__OWNING_ELEMENT = NODE__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__OWNED_ELEMENT = NODE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__MODEL_ELEMENT = NODE__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__STYLE = NODE__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__ID = NODE__ID;

    /**
     * The feature id for the '<em><b>Plane Element</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__PLANE_ELEMENT = NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Plane</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.dd.di.impl.StyleImpl <em>Style</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.dd.di.impl.StyleImpl
     * @see org.eclipse.dd.di.impl.DiPackageImpl#getStyle()
     * @generated
     */
    int STYLE = 10;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLE__ID = 0;

    /**
     * The number of structural features of the '<em>Style</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLE_FEATURE_COUNT = 1;

    /**
     * Returns the meta object for class '{@link org.eclipse.dd.di.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Document Root</em>'.
     * @see org.eclipse.dd.di.DocumentRoot
     * @generated
     */
    EClass getDocumentRoot();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.dd.di.DocumentRoot#getMixed <em>Mixed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Mixed</em>'.
     * @see org.eclipse.dd.di.DocumentRoot#getMixed()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_Mixed();

    /**
     * Returns the meta object for the map '{@link org.eclipse.dd.di.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
     * @see org.eclipse.dd.di.DocumentRoot#getXMLNSPrefixMap()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_XMLNSPrefixMap();

    /**
     * Returns the meta object for the map '{@link org.eclipse.dd.di.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XSI Schema Location</em>'.
     * @see org.eclipse.dd.di.DocumentRoot#getXSISchemaLocation()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_XSISchemaLocation();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.dd.di.DocumentRoot#getDiagramElement <em>Diagram Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Diagram Element</em>'.
     * @see org.eclipse.dd.di.DocumentRoot#getDiagramElement()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_DiagramElement();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.dd.di.DocumentRoot#getDiagram <em>Diagram</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Diagram</em>'.
     * @see org.eclipse.dd.di.DocumentRoot#getDiagram()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Diagram();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.dd.di.DocumentRoot#getEdge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Edge</em>'.
     * @see org.eclipse.dd.di.DocumentRoot#getEdge()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Edge();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.dd.di.DocumentRoot#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Label</em>'.
     * @see org.eclipse.dd.di.DocumentRoot#getLabel()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Label();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.dd.di.DocumentRoot#getLabeledEdge <em>Labeled Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Labeled Edge</em>'.
     * @see org.eclipse.dd.di.DocumentRoot#getLabeledEdge()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_LabeledEdge();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.dd.di.DocumentRoot#getLabeledShape <em>Labeled Shape</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Labeled Shape</em>'.
     * @see org.eclipse.dd.di.DocumentRoot#getLabeledShape()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_LabeledShape();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.dd.di.DocumentRoot#getNode <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Node</em>'.
     * @see org.eclipse.dd.di.DocumentRoot#getNode()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Node();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.dd.di.DocumentRoot#getPlane <em>Plane</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Plane</em>'.
     * @see org.eclipse.dd.di.DocumentRoot#getPlane()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Plane();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.dd.di.DocumentRoot#getShape <em>Shape</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Shape</em>'.
     * @see org.eclipse.dd.di.DocumentRoot#getShape()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Shape();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.dd.di.DocumentRoot#getStyle <em>Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.dd.di.DocumentRoot#getStyle()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Style();

    /**
     * Returns the meta object for class '{@link org.eclipse.dd.di.Diagram <em>Diagram</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Diagram</em>'.
     * @see org.eclipse.dd.di.Diagram
     * @generated
     */
    EClass getDiagram();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.dd.di.Diagram#getDocumentation <em>Documentation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Documentation</em>'.
     * @see org.eclipse.dd.di.Diagram#getDocumentation()
     * @see #getDiagram()
     * @generated
     */
    EAttribute getDiagram_Documentation();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.dd.di.Diagram#getOwnedStyle <em>Owned Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Owned Style</em>'.
     * @see org.eclipse.dd.di.Diagram#getOwnedStyle()
     * @see #getDiagram()
     * @generated
     */
    EReference getDiagram_OwnedStyle();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.dd.di.Diagram#getRootElement <em>Root Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Root Element</em>'.
     * @see org.eclipse.dd.di.Diagram#getRootElement()
     * @see #getDiagram()
     * @generated
     */
    EReference getDiagram_RootElement();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.dd.di.Diagram#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.dd.di.Diagram#getId()
     * @see #getDiagram()
     * @generated
     */
    EAttribute getDiagram_Id();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.dd.di.Diagram#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.dd.di.Diagram#getName()
     * @see #getDiagram()
     * @generated
     */
    EAttribute getDiagram_Name();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.dd.di.Diagram#getResolution <em>Resolution</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Resolution</em>'.
     * @see org.eclipse.dd.di.Diagram#getResolution()
     * @see #getDiagram()
     * @generated
     */
    EAttribute getDiagram_Resolution();

    /**
     * Returns the meta object for class '{@link org.eclipse.dd.di.DiagramElement <em>Diagram Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Diagram Element</em>'.
     * @see org.eclipse.dd.di.DiagramElement
     * @generated
     */
    EClass getDiagramElement();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.dd.di.DiagramElement#getOwningDiagram <em>Owning Diagram</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Owning Diagram</em>'.
     * @see org.eclipse.dd.di.DiagramElement#getOwningDiagram()
     * @see #getDiagramElement()
     * @generated
     */
    EReference getDiagramElement_OwningDiagram();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.dd.di.DiagramElement#getOwningElement <em>Owning Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Owning Element</em>'.
     * @see org.eclipse.dd.di.DiagramElement#getOwningElement()
     * @see #getDiagramElement()
     * @generated
     */
    EReference getDiagramElement_OwningElement();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.dd.di.DiagramElement#getOwnedElement <em>Owned Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Owned Element</em>'.
     * @see org.eclipse.dd.di.DiagramElement#getOwnedElement()
     * @see #getDiagramElement()
     * @generated
     */
    EReference getDiagramElement_OwnedElement();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.dd.di.DiagramElement#getModelElement <em>Model Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Model Element</em>'.
     * @see org.eclipse.dd.di.DiagramElement#getModelElement()
     * @see #getDiagramElement()
     * @generated
     */
    EReference getDiagramElement_ModelElement();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.dd.di.DiagramElement#getStyle <em>Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Style</em>'.
     * @see org.eclipse.dd.di.DiagramElement#getStyle()
     * @see #getDiagramElement()
     * @generated
     */
    EReference getDiagramElement_Style();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.dd.di.DiagramElement#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.dd.di.DiagramElement#getId()
     * @see #getDiagramElement()
     * @generated
     */
    EAttribute getDiagramElement_Id();

    /**
     * Returns the meta object for class '{@link org.eclipse.dd.di.Edge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Edge</em>'.
     * @see org.eclipse.dd.di.Edge
     * @generated
     */
    EClass getEdge();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.dd.di.Edge#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see org.eclipse.dd.di.Edge#getSource()
     * @see #getEdge()
     * @generated
     */
    EReference getEdge_Source();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.dd.di.Edge#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.eclipse.dd.di.Edge#getTarget()
     * @see #getEdge()
     * @generated
     */
    EReference getEdge_Target();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.dd.di.Edge#getWaypoint <em>Waypoint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Waypoint</em>'.
     * @see org.eclipse.dd.di.Edge#getWaypoint()
     * @see #getEdge()
     * @generated
     */
    EReference getEdge_Waypoint();

    /**
     * Returns the meta object for class '{@link org.eclipse.dd.di.Label <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Label</em>'.
     * @see org.eclipse.dd.di.Label
     * @generated
     */
    EClass getLabel();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.dd.di.Label#getBounds <em>Bounds</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Bounds</em>'.
     * @see org.eclipse.dd.di.Label#getBounds()
     * @see #getLabel()
     * @generated
     */
    EReference getLabel_Bounds();

    /**
     * Returns the meta object for class '{@link org.eclipse.dd.di.LabeledEdge <em>Labeled Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Labeled Edge</em>'.
     * @see org.eclipse.dd.di.LabeledEdge
     * @generated
     */
    EClass getLabeledEdge();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.dd.di.LabeledEdge#getOwnedLabel <em>Owned Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Owned Label</em>'.
     * @see org.eclipse.dd.di.LabeledEdge#getOwnedLabel()
     * @see #getLabeledEdge()
     * @generated
     */
    EReference getLabeledEdge_OwnedLabel();

    /**
     * Returns the meta object for class '{@link org.eclipse.dd.di.LabeledShape <em>Labeled Shape</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Labeled Shape</em>'.
     * @see org.eclipse.dd.di.LabeledShape
     * @generated
     */
    EClass getLabeledShape();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.dd.di.LabeledShape#getOwnedLabel <em>Owned Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Owned Label</em>'.
     * @see org.eclipse.dd.di.LabeledShape#getOwnedLabel()
     * @see #getLabeledShape()
     * @generated
     */
    EReference getLabeledShape_OwnedLabel();

    /**
     * Returns the meta object for class '{@link org.eclipse.dd.di.Node <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Node</em>'.
     * @see org.eclipse.dd.di.Node
     * @generated
     */
    EClass getNode();

    /**
     * Returns the meta object for class '{@link org.eclipse.dd.di.Plane <em>Plane</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Plane</em>'.
     * @see org.eclipse.dd.di.Plane
     * @generated
     */
    EClass getPlane();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.dd.di.Plane#getPlaneElement <em>Plane Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Plane Element</em>'.
     * @see org.eclipse.dd.di.Plane#getPlaneElement()
     * @see #getPlane()
     * @generated
     */
    EReference getPlane_PlaneElement();

    /**
     * Returns the meta object for class '{@link org.eclipse.dd.di.Shape <em>Shape</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Shape</em>'.
     * @see org.eclipse.dd.di.Shape
     * @generated
     */
    EClass getShape();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.dd.di.Shape#getBounds <em>Bounds</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Bounds</em>'.
     * @see org.eclipse.dd.di.Shape#getBounds()
     * @see #getShape()
     * @generated
     */
    EReference getShape_Bounds();

    /**
     * Returns the meta object for class '{@link org.eclipse.dd.di.Style <em>Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Style</em>'.
     * @see org.eclipse.dd.di.Style
     * @generated
     */
    EClass getStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.dd.di.Style#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.dd.di.Style#getId()
     * @see #getStyle()
     * @generated
     */
    EAttribute getStyle_Id();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    DiFactory getDiFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.eclipse.dd.di.impl.DocumentRootImpl <em>Document Root</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.dd.di.impl.DocumentRootImpl
         * @see org.eclipse.dd.di.impl.DiPackageImpl#getDocumentRoot()
         * @generated
         */
        EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

        /**
         * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

        /**
         * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

        /**
         * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE
                .getDocumentRoot_XSISchemaLocation();

        /**
         * The meta object literal for the '<em><b>Diagram Element</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__DIAGRAM_ELEMENT = eINSTANCE.getDocumentRoot_DiagramElement();

        /**
         * The meta object literal for the '<em><b>Diagram</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__DIAGRAM = eINSTANCE.getDocumentRoot_Diagram();

        /**
         * The meta object literal for the '<em><b>Edge</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__EDGE = eINSTANCE.getDocumentRoot_Edge();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LABEL = eINSTANCE.getDocumentRoot_Label();

        /**
         * The meta object literal for the '<em><b>Labeled Edge</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LABELED_EDGE = eINSTANCE.getDocumentRoot_LabeledEdge();

        /**
         * The meta object literal for the '<em><b>Labeled Shape</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LABELED_SHAPE = eINSTANCE.getDocumentRoot_LabeledShape();

        /**
         * The meta object literal for the '<em><b>Node</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__NODE = eINSTANCE.getDocumentRoot_Node();

        /**
         * The meta object literal for the '<em><b>Plane</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__PLANE = eINSTANCE.getDocumentRoot_Plane();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__SHAPE = eINSTANCE.getDocumentRoot_Shape();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__STYLE = eINSTANCE.getDocumentRoot_Style();

        /**
         * The meta object literal for the '{@link org.eclipse.dd.di.impl.DiagramImpl <em>Diagram</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.dd.di.impl.DiagramImpl
         * @see org.eclipse.dd.di.impl.DiPackageImpl#getDiagram()
         * @generated
         */
        EClass DIAGRAM = eINSTANCE.getDiagram();

        /**
         * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM__DOCUMENTATION = eINSTANCE.getDiagram_Documentation();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM__OWNED_STYLE = eINSTANCE.getDiagram_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Root Element</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM__ROOT_ELEMENT = eINSTANCE.getDiagram_RootElement();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM__ID = eINSTANCE.getDiagram_Id();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM__NAME = eINSTANCE.getDiagram_Name();

        /**
         * The meta object literal for the '<em><b>Resolution</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM__RESOLUTION = eINSTANCE.getDiagram_Resolution();

        /**
         * The meta object literal for the '{@link org.eclipse.dd.di.impl.DiagramElementImpl <em>Diagram Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.dd.di.impl.DiagramElementImpl
         * @see org.eclipse.dd.di.impl.DiPackageImpl#getDiagramElement()
         * @generated
         */
        EClass DIAGRAM_ELEMENT = eINSTANCE.getDiagramElement();

        /**
         * The meta object literal for the '<em><b>Owning Diagram</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM_ELEMENT__OWNING_DIAGRAM = eINSTANCE.getDiagramElement_OwningDiagram();

        /**
         * The meta object literal for the '<em><b>Owning Element</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM_ELEMENT__OWNING_ELEMENT = eINSTANCE.getDiagramElement_OwningElement();

        /**
         * The meta object literal for the '<em><b>Owned Element</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM_ELEMENT__OWNED_ELEMENT = eINSTANCE.getDiagramElement_OwnedElement();

        /**
         * The meta object literal for the '<em><b>Model Element</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM_ELEMENT__MODEL_ELEMENT = eINSTANCE.getDiagramElement_ModelElement();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM_ELEMENT__STYLE = eINSTANCE.getDiagramElement_Style();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT__ID = eINSTANCE.getDiagramElement_Id();

        /**
         * The meta object literal for the '{@link org.eclipse.dd.di.impl.EdgeImpl <em>Edge</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.dd.di.impl.EdgeImpl
         * @see org.eclipse.dd.di.impl.DiPackageImpl#getEdge()
         * @generated
         */
        EClass EDGE = eINSTANCE.getEdge();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE__SOURCE = eINSTANCE.getEdge_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE__TARGET = eINSTANCE.getEdge_Target();

        /**
         * The meta object literal for the '<em><b>Waypoint</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE__WAYPOINT = eINSTANCE.getEdge_Waypoint();

        /**
         * The meta object literal for the '{@link org.eclipse.dd.di.impl.LabelImpl <em>Label</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.dd.di.impl.LabelImpl
         * @see org.eclipse.dd.di.impl.DiPackageImpl#getLabel()
         * @generated
         */
        EClass LABEL = eINSTANCE.getLabel();

        /**
         * The meta object literal for the '<em><b>Bounds</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABEL__BOUNDS = eINSTANCE.getLabel_Bounds();

        /**
         * The meta object literal for the '{@link org.eclipse.dd.di.impl.LabeledEdgeImpl <em>Labeled Edge</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.dd.di.impl.LabeledEdgeImpl
         * @see org.eclipse.dd.di.impl.DiPackageImpl#getLabeledEdge()
         * @generated
         */
        EClass LABELED_EDGE = eINSTANCE.getLabeledEdge();

        /**
         * The meta object literal for the '<em><b>Owned Label</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABELED_EDGE__OWNED_LABEL = eINSTANCE.getLabeledEdge_OwnedLabel();

        /**
         * The meta object literal for the '{@link org.eclipse.dd.di.impl.LabeledShapeImpl <em>Labeled Shape</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.dd.di.impl.LabeledShapeImpl
         * @see org.eclipse.dd.di.impl.DiPackageImpl#getLabeledShape()
         * @generated
         */
        EClass LABELED_SHAPE = eINSTANCE.getLabeledShape();

        /**
         * The meta object literal for the '<em><b>Owned Label</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABELED_SHAPE__OWNED_LABEL = eINSTANCE.getLabeledShape_OwnedLabel();

        /**
         * The meta object literal for the '{@link org.eclipse.dd.di.impl.NodeImpl <em>Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.dd.di.impl.NodeImpl
         * @see org.eclipse.dd.di.impl.DiPackageImpl#getNode()
         * @generated
         */
        EClass NODE = eINSTANCE.getNode();

        /**
         * The meta object literal for the '{@link org.eclipse.dd.di.impl.PlaneImpl <em>Plane</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.dd.di.impl.PlaneImpl
         * @see org.eclipse.dd.di.impl.DiPackageImpl#getPlane()
         * @generated
         */
        EClass PLANE = eINSTANCE.getPlane();

        /**
         * The meta object literal for the '<em><b>Plane Element</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PLANE__PLANE_ELEMENT = eINSTANCE.getPlane_PlaneElement();

        /**
         * The meta object literal for the '{@link org.eclipse.dd.di.impl.ShapeImpl <em>Shape</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.dd.di.impl.ShapeImpl
         * @see org.eclipse.dd.di.impl.DiPackageImpl#getShape()
         * @generated
         */
        EClass SHAPE = eINSTANCE.getShape();

        /**
         * The meta object literal for the '<em><b>Bounds</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SHAPE__BOUNDS = eINSTANCE.getShape_Bounds();

        /**
         * The meta object literal for the '{@link org.eclipse.dd.di.impl.StyleImpl <em>Style</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.dd.di.impl.StyleImpl
         * @see org.eclipse.dd.di.impl.DiPackageImpl#getStyle()
         * @generated
         */
        EClass STYLE = eINSTANCE.getStyle();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute STYLE__ID = eINSTANCE.getStyle_Id();

    }

} //DiPackage
