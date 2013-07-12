/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connector Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorId <em>Connector Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getClassName <em>Class Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorInstanceId <em>Connector Instance Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorInstanceName <em>Connector Instance Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getEventType <em>Event Type</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getErrorHandling <em>Error Handling</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getErrorCode <em>Error Code</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorParameterInputs <em>Connector Parameter Inputs</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorParameterOutputs <em>Connector Parameter Outputs</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorParameterOutputsDef <em>Connector Parameter Outputs Def</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getSkipComment <em>Skip Comment</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getTimeExpression <em>Time Expression</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#isIsTimeExecute <em>Is Time Execute</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getTimeSkipExpression <em>Time Skip Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance()
 * @model
 * @generated
 */
public interface ConnectorInstance extends EObject {
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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_ConnectorId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getConnectorId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorId <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Id</em>' attribute.
	 * @see #getConnectorId()
	 * @generated
	 */
	void setConnectorId(String value);

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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_PackageName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPackageName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getPackageName <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Name</em>' attribute.
	 * @see #getPackageName()
	 * @generated
	 */
	void setPackageName(String value);

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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_ClassName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getClassName <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
	void setClassName(String value);

	/**
	 * Returns the value of the '<em><b>Connector Instance Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector Instance Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Instance Id</em>' attribute.
	 * @see #setConnectorInstanceId(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_ConnectorInstanceId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getConnectorInstanceId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorInstanceId <em>Connector Instance Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Instance Id</em>' attribute.
	 * @see #getConnectorInstanceId()
	 * @generated
	 */
	void setConnectorInstanceId(String value);

	/**
	 * Returns the value of the '<em><b>Connector Instance Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector Instance Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Instance Name</em>' attribute.
	 * @see #setConnectorInstanceName(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_ConnectorInstanceName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getConnectorInstanceName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorInstanceName <em>Connector Instance Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Instance Name</em>' attribute.
	 * @see #getConnectorInstanceName()
	 * @generated
	 */
	void setConnectorInstanceName(String value);

	/**
	 * Returns the value of the '<em><b>Event Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Type</em>' attribute.
	 * @see #setEventType(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_EventType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getEventType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getEventType <em>Event Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Type</em>' attribute.
	 * @see #getEventType()
	 * @generated
	 */
	void setEventType(String value);

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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_Documentation()
	 * @model containment="true"
	 * @generated
	 */
	Documentation getDocumentation();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getDocumentation <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' containment reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(Documentation value);

	/**
	 * Returns the value of the '<em><b>Error Handling</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error Handling</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Handling</em>' attribute.
	 * @see #setErrorHandling(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_ErrorHandling()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getErrorHandling();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getErrorHandling <em>Error Handling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Handling</em>' attribute.
	 * @see #getErrorHandling()
	 * @generated
	 */
	void setErrorHandling(String value);

	/**
	 * Returns the value of the '<em><b>Error Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Code</em>' attribute.
	 * @see #setErrorCode(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_ErrorCode()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getErrorCode();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getErrorCode <em>Error Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Code</em>' attribute.
	 * @see #getErrorCode()
	 * @generated
	 */
	void setErrorCode(String value);

	/**
	 * Returns the value of the '<em><b>Connector Parameter Inputs</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector Parameter Inputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Parameter Inputs</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_ConnectorParameterInputs()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<ConnectorParameterInputs> getConnectorParameterInputs();

	/**
	 * Returns the value of the '<em><b>Connector Parameter Outputs</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector Parameter Outputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Parameter Outputs</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_ConnectorParameterOutputs()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' name='connectorParameterOutputs' kind='element'"
	 * @generated
	 */
	EList<ConnectorParameterOutputs> getConnectorParameterOutputs();

	/**
	 * Returns the value of the '<em><b>Connector Parameter Outputs Def</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector Parameter Outputs Def</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Parameter Outputs Def</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_ConnectorParameterOutputsDef()
	 * @model containment="true"
	 *        extendedMetaData="name='connectorParameterOutputsDef' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<ConnectorParameterOutputsDef> getConnectorParameterOutputsDef();

	/**
	 * Returns the value of the '<em><b>Skip Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skip Comment</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skip Comment</em>' containment reference.
	 * @see #setSkipComment(SkipComment)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_SkipComment()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' name='skipComment' kind='element'"
	 * @generated
	 */
	SkipComment getSkipComment();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getSkipComment <em>Skip Comment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skip Comment</em>' containment reference.
	 * @see #getSkipComment()
	 * @generated
	 */
	void setSkipComment(SkipComment value);

	/**
	 * Returns the value of the '<em><b>Time Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Expression</em>' containment reference.
	 * @see #setTimeExpression(TimeExpression)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_TimeExpression()
	 * @model containment="true"
	 *        extendedMetaData="name='timeExpression' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	TimeExpression getTimeExpression();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getTimeExpression <em>Time Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Expression</em>' containment reference.
	 * @see #getTimeExpression()
	 * @generated
	 */
	void setTimeExpression(TimeExpression value);

	/**
	 * Returns the value of the '<em><b>Is Time Execute</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Time Execute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Time Execute</em>' attribute.
	 * @see #setIsTimeExecute(boolean)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_IsTimeExecute()
	 * @model default="false" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	boolean isIsTimeExecute();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#isIsTimeExecute <em>Is Time Execute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Time Execute</em>' attribute.
	 * @see #isIsTimeExecute()
	 * @generated
	 */
	void setIsTimeExecute(boolean value);

	/**
	 * Returns the value of the '<em><b>Time Skip Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Skip Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Skip Expression</em>' containment reference.
	 * @see #setTimeSkipExpression(TimeSkipExpression)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getConnectorInstance_TimeSkipExpression()
	 * @model containment="true"
	 *        extendedMetaData="name='timeSkipExpression' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	TimeSkipExpression getTimeSkipExpression();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getTimeSkipExpression <em>Time Skip Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Skip Expression</em>' containment reference.
	 * @see #getTimeSkipExpression()
	 * @generated
	 */
	void setTimeSkipExpression(TimeSkipExpression value);

} // ConnectorInstance
