/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.connector;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Connector#getConnectorId <em>Connector Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Connector#getIcon <em>Icon</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Connector#getClassName <em>Class Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Connector#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Connector#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Connector#getConnectorName <em>Connector Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Connector#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Connector#getPages <em>Pages</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Connector#getConnectorNote <em>Connector Note</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Connector#getCategory <em>Category</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getConnector()
 * @model extendedMetaData="name='connector'"
 * @generated
 */
public interface Connector extends EObject {
	/**
	 * Returns the value of the '<em><b>Connector Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Id</em>' attribute.
	 * @see #setConnectorId(String)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getConnector_ConnectorId()
	 * @model extendedMetaData="kind='attribute' namespace=''"
	 * @generated
	 */
	String getConnectorId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Connector#getConnectorId <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Id</em>' attribute.
	 * @see #getConnectorId()
	 * @generated
	 */
	void setConnectorId(String value);

	/**
	 * Returns the value of the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icon</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icon</em>' attribute.
	 * @see #setIcon(String)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getConnector_Icon()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIcon();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Connector#getIcon <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icon</em>' attribute.
	 * @see #getIcon()
	 * @generated
	 */
	void setIcon(String value);

	/**
	 * Returns the value of the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Name</em>' attribute.
	 * @see #setClassName(String)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getConnector_ClassName()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Connector#getClassName <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
	void setClassName(String value);

	/**
	 * Returns the value of the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Name</em>' attribute.
	 * @see #setPackageName(String)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getConnector_PackageName()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPackageName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Connector#getPackageName <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Name</em>' attribute.
	 * @see #getPackageName()
	 * @generated
	 */
	void setPackageName(String value);

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' containment reference.
	 * @see #setDocumentation(Documentation)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getConnector_Documentation()
	 * @model containment="true"
	 * @generated
	 */
	Documentation getDocumentation();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Connector#getDocumentation <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' containment reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(Documentation value);

	/**
	 * Returns the value of the '<em><b>Connector Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Name</em>' attribute.
	 * @see #setConnectorName(String)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getConnector_ConnectorName()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getConnectorName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Connector#getConnectorName <em>Connector Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Name</em>' attribute.
	 * @see #getConnectorName()
	 * @generated
	 */
	void setConnectorName(String value);

	/**
	 * Returns the value of the '<em><b>Outputs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outputs</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outputs</em>' containment reference.
	 * @see #setOutputs(Outputs)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getConnector_Outputs()
	 * @model containment="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	Outputs getOutputs();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Connector#getOutputs <em>Outputs</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outputs</em>' containment reference.
	 * @see #getOutputs()
	 * @generated
	 */
	void setOutputs(Outputs value);

	/**
	 * Returns the value of the '<em><b>Pages</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pages</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pages</em>' containment reference.
	 * @see #setPages(Pages)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getConnector_Pages()
	 * @model containment="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	Pages getPages();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Connector#getPages <em>Pages</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pages</em>' containment reference.
	 * @see #getPages()
	 * @generated
	 */
	void setPages(Pages value);

	/**
	 * Returns the value of the '<em><b>Connector Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector Note</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Note</em>' attribute.
	 * @see #setConnectorNote(String)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getConnector_ConnectorNote()
	 * @model extendedMetaData="name='connectorNote' kind='attribute'"
	 * @generated
	 */
	String getConnectorNote();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Connector#getConnectorNote <em>Connector Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Note</em>' attribute.
	 * @see #getConnectorNote()
	 * @generated
	 */
	void setConnectorNote(String value);

	/**
	 * Returns the value of the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Category</em>' attribute.
	 * @see #setCategory(String)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getConnector_Category()
	 * @model extendedMetaData="name='category' namespace='' kind='attribute'"
	 * @generated
	 */
	String getCategory();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Connector#getCategory <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Category</em>' attribute.
	 * @see #getCategory()
	 * @generated
	 */
	void setCategory(String value);

} // Connector
