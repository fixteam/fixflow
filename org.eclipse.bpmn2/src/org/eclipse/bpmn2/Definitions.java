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

import org.eclipse.bpmn2.di.BPMNDiagram;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Definitions</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.Definitions#getImports <em>Imports</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Definitions#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Definitions#getRootElements <em>Root Elements</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Definitions#getDiagrams <em>Diagrams</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Definitions#getRelationships <em>Relationships</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Definitions#getExporter <em>Exporter</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Definitions#getExporterVersion <em>Exporter Version</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Definitions#getExpressionLanguage <em>Expression Language</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Definitions#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Definitions#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Definitions#getTypeLanguage <em>Type Language</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getDefinitions()
 * @model extendedMetaData="name='tDefinitions' kind='elementOnly'"
 * @generated
 */
public interface Definitions extends BaseElement {
    /**
     * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.Import}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Imports</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDefinitions_Imports()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='import' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<Import> getImports();

    /**
     * Returns the value of the '<em><b>Extensions</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.Extension}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extensions</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extensions</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDefinitions_Extensions()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='extension' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<Extension> getExtensions();

    /**
     * Returns the value of the '<em><b>Root Elements</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.RootElement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Root Elements</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Root Elements</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDefinitions_RootElements()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='rootElement' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' group='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    List<RootElement> getRootElements();

    /**
     * Returns the value of the '<em><b>Diagrams</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.di.BPMNDiagram}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Diagrams</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Diagrams</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDefinitions_Diagrams()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='BPMNDiagram' namespace='http://www.omg.org/spec/BPMN/20100524/DI'"
     * @generated
     */
    List<BPMNDiagram> getDiagrams();

    /**
     * Returns the value of the '<em><b>Relationships</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.Relationship}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Relationships</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Relationships</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDefinitions_Relationships()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='relationship' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<Relationship> getRelationships();

    /**
     * Returns the value of the '<em><b>Exporter</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Exporter</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Exporter</em>' attribute.
     * @see #setExporter(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDefinitions_Exporter()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='exporter'"
     * @generated
     */
    String getExporter();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Definitions#getExporter <em>Exporter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Exporter</em>' attribute.
     * @see #getExporter()
     * @generated
     */
    void setExporter(String value);

    /**
     * Returns the value of the '<em><b>Exporter Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Exporter Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Exporter Version</em>' attribute.
     * @see #setExporterVersion(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDefinitions_ExporterVersion()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='exporterVersion'"
     * @generated
     */
    String getExporterVersion();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Definitions#getExporterVersion <em>Exporter Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Exporter Version</em>' attribute.
     * @see #getExporterVersion()
     * @generated
     */
    void setExporterVersion(String value);

    /**
     * Returns the value of the '<em><b>Expression Language</b></em>' attribute.
     * The default value is <code>"http://www.w3.org/1999/XPath"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expression Language</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression Language</em>' attribute.
     * @see #setExpressionLanguage(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDefinitions_ExpressionLanguage()
     * @model default="http://www.w3.org/1999/XPath" ordered="false"
     *        extendedMetaData="kind='attribute' name='expressionLanguage'"
     * @generated
     */
    String getExpressionLanguage();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Definitions#getExpressionLanguage <em>Expression Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression Language</em>' attribute.
     * @see #getExpressionLanguage()
     * @generated
     */
    void setExpressionLanguage(String value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDefinitions_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Definitions#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Target Namespace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Namespace</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Namespace</em>' attribute.
     * @see #setTargetNamespace(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDefinitions_TargetNamespace()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='targetNamespace'"
     * @generated
     */
    String getTargetNamespace();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Definitions#getTargetNamespace <em>Target Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Namespace</em>' attribute.
     * @see #getTargetNamespace()
     * @generated
     */
    void setTargetNamespace(String value);

    /**
     * Returns the value of the '<em><b>Type Language</b></em>' attribute.
     * The default value is <code>"http://www.w3.org/2001/XMLSchema"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type Language</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type Language</em>' attribute.
     * @see #setTypeLanguage(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDefinitions_TypeLanguage()
     * @model default="http://www.w3.org/2001/XMLSchema" ordered="false"
     *        extendedMetaData="kind='attribute' name='typeLanguage'"
     * @generated
     */
    String getTypeLanguage();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Definitions#getTypeLanguage <em>Type Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type Language</em>' attribute.
     * @see #getTypeLanguage()
     * @generated
     */
    void setTypeLanguage(String value);

} // Definitions
