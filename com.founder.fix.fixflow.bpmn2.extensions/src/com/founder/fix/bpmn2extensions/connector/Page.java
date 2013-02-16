/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.connector;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Page#getPageId <em>Page Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Page#getPageTitle <em>Page Title</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Page#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Page#getPageNote <em>Page Note</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.Page#getInputParameter <em>Input Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getPage()
 * @model
 * @generated
 */
public interface Page extends EObject {
	/**
	 * Returns the value of the '<em><b>Page Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Page Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Page Id</em>' attribute.
	 * @see #setPageId(String)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getPage_PageId()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPageId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Page#getPageId <em>Page Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Id</em>' attribute.
	 * @see #getPageId()
	 * @generated
	 */
	void setPageId(String value);

	/**
	 * Returns the value of the '<em><b>Page Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Page Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Page Title</em>' attribute.
	 * @see #setPageTitle(String)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getPage_PageTitle()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPageTitle();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Page#getPageTitle <em>Page Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Title</em>' attribute.
	 * @see #getPageTitle()
	 * @generated
	 */
	void setPageTitle(String value);

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
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getPage_Documentation()
	 * @model containment="true"
	 * @generated
	 */
	Documentation getDocumentation();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Page#getDocumentation <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' containment reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(Documentation value);

	/**
	 * Returns the value of the '<em><b>Page Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Page Note</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Page Note</em>' attribute.
	 * @see #setPageNote(String)
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getPage_PageNote()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPageNote();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.connector.Page#getPageNote <em>Page Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Note</em>' attribute.
	 * @see #getPageNote()
	 * @generated
	 */
	void setPageNote(String value);

	/**
	 * Returns the value of the '<em><b>Input Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.connector.InputParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Parameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Parameter</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.connector.ConnectorPackage#getPage_InputParameter()
	 * @model containment="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	EList<InputParameter> getInputParameter();

} // Page
