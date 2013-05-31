/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

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
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getTaskSubject <em>Task Subject</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getTaskCommand <em>Task Command</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResourceType <em>Resource Type</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResourceFilter <em>Resource Filter</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getDataVariable <em>Data Variable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getVersion <em>Version</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getDbid <em>Dbid</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCategory <em>Category</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getConnectorInstance <em>Connector Instance</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLoopDataInputCollection <em>Loop Data Input Collection</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLoopDataOutputCollection <em>Loop Data Output Collection</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getFormUri <em>Form Uri</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLoopMaximum <em>Loop Maximum</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getSubTask <em>Sub Task</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getScriptName <em>Script Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResultVariable <em>Result Variable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementId <em>Callable Element Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementName <em>Callable Element Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementVersion <em>Callable Element Version</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementVersionName <em>Callable Element Version Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getIncludeExclusion <em>Include Exclusion</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResourceRange <em>Resource Range</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getAssignAction <em>Assign Action</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getErrorCode <em>Error Code</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getSkipStrategy <em>Skip Strategy</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#isIsContainsSub <em>Is Contains Sub</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getVerification <em>Verification</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getMessageObj <em>Message Obj</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getReceiveMessage <em>Receive Message</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getExpectedExecutionTime <em>Expected Execution Time</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getDataSourceToSubProcessMapping <em>Data Source To Sub Process Mapping</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getSubProcessToDataSourceMapping <em>Sub Process To Data Source Mapping</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementBizKey <em>Callable Element Biz Key</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementBizKeyName <em>Callable Element Biz Key Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getIsAsync <em>Is Async</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getFormUriView <em>Form Uri View</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getOrderId <em>Order Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getTaskPriority <em>Task Priority</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getAssignPolicyType <em>Assign Policy Type</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#isIsPersistence <em>Is Persistence</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getTaskType <em>Task Type</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getValidationLevel <em>Validation Level</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLanguageType <em>Language Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot()
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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_Mixed()
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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Task Subject</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 				任务的主题
	 * 			
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Task Subject</em>' containment reference.
	 * @see #setTaskSubject(TaskSubject)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_TaskSubject()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='taskSubject' namespace='##targetNamespace'"
	 * @generated
	 */
	TaskSubject getTaskSubject();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getTaskSubject <em>Task Subject</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task Subject</em>' containment reference.
	 * @see #getTaskSubject()
	 * @generated
	 */
	void setTaskSubject(TaskSubject value);

	/**
	 * Returns the value of the '<em><b>Task Command</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task Command</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task Command</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_TaskCommand()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' name='taskCommand'"
	 * @generated
	 */
	EList<TaskCommand> getTaskCommand();

	/**
	 * Returns the value of the '<em><b>Resource Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Type</em>' attribute.
	 * @see #setResourceType(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_ResourceType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getResourceType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResourceType <em>Resource Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Type</em>' attribute.
	 * @see #getResourceType()
	 * @generated
	 */
	void setResourceType(String value);

	/**
	 * Returns the value of the '<em><b>Resource Filter</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.fixflow.ResourceFilter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Filter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Filter</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_ResourceFilter()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<ResourceFilter> getResourceFilter();

	/**
	 * Returns the value of the '<em><b>Data Variable</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.fixflow.DataVariable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Variable</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Variable</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_DataVariable()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' name='DataVariable'"
	 * @generated
	 */
	EList<DataVariable> getDataVariable();

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(int)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	int getVersion();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(int value);

	/**
	 * Returns the value of the '<em><b>Dbid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dbid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dbid</em>' attribute.
	 * @see #setDbid(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_Dbid()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getDbid();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getDbid <em>Dbid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dbid</em>' attribute.
	 * @see #getDbid()
	 * @generated
	 */
	void setDbid(String value);

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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_Category()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getCategory();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCategory <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Category</em>' attribute.
	 * @see #getCategory()
	 * @generated
	 */
	void setCategory(String value);

	/**
	 * Returns the value of the '<em><b>Connector Instance</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector Instance</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Instance</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_ConnectorInstance()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<ConnectorInstance> getConnectorInstance();

	/**
	 * Returns the value of the '<em><b>Loop Data Input Collection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Data Input Collection</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Data Input Collection</em>' containment reference.
	 * @see #setLoopDataInputCollection(LoopDataInputCollection)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_LoopDataInputCollection()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	LoopDataInputCollection getLoopDataInputCollection();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLoopDataInputCollection <em>Loop Data Input Collection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Data Input Collection</em>' containment reference.
	 * @see #getLoopDataInputCollection()
	 * @generated
	 */
	void setLoopDataInputCollection(LoopDataInputCollection value);

	/**
	 * Returns the value of the '<em><b>Loop Data Output Collection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Data Output Collection</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Data Output Collection</em>' containment reference.
	 * @see #setLoopDataOutputCollection(LoopDataOutputCollection)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_LoopDataOutputCollection()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	LoopDataOutputCollection getLoopDataOutputCollection();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLoopDataOutputCollection <em>Loop Data Output Collection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Data Output Collection</em>' containment reference.
	 * @see #getLoopDataOutputCollection()
	 * @generated
	 */
	void setLoopDataOutputCollection(LoopDataOutputCollection value);

	/**
	 * Returns the value of the '<em><b>Form Uri</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Form Uri</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Form Uri</em>' containment reference.
	 * @see #setFormUri(FormUri)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_FormUri()
	 * @model containment="true"
	 *        extendedMetaData="name='formUri' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	FormUri getFormUri();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getFormUri <em>Form Uri</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Form Uri</em>' containment reference.
	 * @see #getFormUri()
	 * @generated
	 */
	void setFormUri(FormUri value);

	/**
	 * Returns the value of the '<em><b>Loop Maximum</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Maximum</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Maximum</em>' containment reference.
	 * @see #setLoopMaximum(LoopMaximum)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_LoopMaximum()
	 * @model containment="true"
	 *        extendedMetaData="name='loopMaximum' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	LoopMaximum getLoopMaximum();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLoopMaximum <em>Loop Maximum</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Maximum</em>' containment reference.
	 * @see #getLoopMaximum()
	 * @generated
	 */
	void setLoopMaximum(LoopMaximum value);

	/**
	 * Returns the value of the '<em><b>Sub Task</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Task</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Task</em>' attribute.
	 * @see #setSubTask(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_SubTask()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getSubTask();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getSubTask <em>Sub Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Task</em>' attribute.
	 * @see #getSubTask()
	 * @generated
	 */
	void setSubTask(String value);

	/**
	 * Returns the value of the '<em><b>Script Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script Name</em>' attribute.
	 * @see #setScriptName(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_ScriptName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getScriptName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getScriptName <em>Script Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script Name</em>' attribute.
	 * @see #getScriptName()
	 * @generated
	 */
	void setScriptName(String value);

	/**
	 * Returns the value of the '<em><b>Result Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Variable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Variable</em>' attribute.
	 * @see #setResultVariable(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_ResultVariable()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' name='resultVariable' kind='attribute'"
	 * @generated
	 */
	String getResultVariable();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResultVariable <em>Result Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Variable</em>' attribute.
	 * @see #getResultVariable()
	 * @generated
	 */
	void setResultVariable(String value);

	/**
	 * Returns the value of the '<em><b>Callable Element Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callable Element Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callable Element Id</em>' attribute.
	 * @see #setCallableElementId(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_CallableElementId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getCallableElementId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementId <em>Callable Element Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callable Element Id</em>' attribute.
	 * @see #getCallableElementId()
	 * @generated
	 */
	void setCallableElementId(String value);

	/**
	 * Returns the value of the '<em><b>Callable Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callable Element Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callable Element Name</em>' attribute.
	 * @see #setCallableElementName(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_CallableElementName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getCallableElementName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementName <em>Callable Element Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callable Element Name</em>' attribute.
	 * @see #getCallableElementName()
	 * @generated
	 */
	void setCallableElementName(String value);

	/**
	 * Returns the value of the '<em><b>Callable Element Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callable Element Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callable Element Version</em>' attribute.
	 * @see #setCallableElementVersion(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_CallableElementVersion()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getCallableElementVersion();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementVersion <em>Callable Element Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callable Element Version</em>' attribute.
	 * @see #getCallableElementVersion()
	 * @generated
	 */
	void setCallableElementVersion(String value);

	/**
	 * Returns the value of the '<em><b>Callable Element Version Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callable Element Version Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callable Element Version Name</em>' attribute.
	 * @see #setCallableElementVersionName(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_CallableElementVersionName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getCallableElementVersionName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementVersionName <em>Callable Element Version Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callable Element Version Name</em>' attribute.
	 * @see #getCallableElementVersionName()
	 * @generated
	 */
	void setCallableElementVersionName(String value);

	/**
	 * Returns the value of the '<em><b>Include Exclusion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Include Exclusion</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Include Exclusion</em>' attribute.
	 * @see #setIncludeExclusion(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_IncludeExclusion()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute' name='includeExclusion'"
	 * @generated
	 */
	String getIncludeExclusion();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getIncludeExclusion <em>Include Exclusion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Include Exclusion</em>' attribute.
	 * @see #getIncludeExclusion()
	 * @generated
	 */
	void setIncludeExclusion(String value);

	/**
	 * Returns the value of the '<em><b>Resource Range</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Range</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Range</em>' attribute.
	 * @see #setResourceRange(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_ResourceRange()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getResourceRange();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResourceRange <em>Resource Range</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Range</em>' attribute.
	 * @see #getResourceRange()
	 * @generated
	 */
	void setResourceRange(String value);

	/**
	 * Returns the value of the '<em><b>Assign Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assign Action</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assign Action</em>' attribute.
	 * @see #setAssignAction(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_AssignAction()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getAssignAction();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getAssignAction <em>Assign Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assign Action</em>' attribute.
	 * @see #getAssignAction()
	 * @generated
	 */
	void setAssignAction(String value);

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
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_ErrorCode()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' name='errorCode' kind='attribute'"
	 * @generated
	 */
	String getErrorCode();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getErrorCode <em>Error Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Code</em>' attribute.
	 * @see #getErrorCode()
	 * @generated
	 */
	void setErrorCode(String value);

	/**
	 * Returns the value of the '<em><b>Skip Strategy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skip Strategy</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skip Strategy</em>' containment reference.
	 * @see #setSkipStrategy(SkipStrategy)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_SkipStrategy()
	 * @model containment="true"
	 *        extendedMetaData="name='skipStrategy' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	SkipStrategy getSkipStrategy();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getSkipStrategy <em>Skip Strategy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skip Strategy</em>' containment reference.
	 * @see #getSkipStrategy()
	 * @generated
	 */
	void setSkipStrategy(SkipStrategy value);

	/**
	 * Returns the value of the '<em><b>Is Contains Sub</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 是否包含子集
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Contains Sub</em>' attribute.
	 * @see #setIsContainsSub(boolean)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_IsContainsSub()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	boolean isIsContainsSub();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#isIsContainsSub <em>Is Contains Sub</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Contains Sub</em>' attribute.
	 * @see #isIsContainsSub()
	 * @generated
	 */
	void setIsContainsSub(boolean value);

	/**
	 * Returns the value of the '<em><b>Verification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Verification</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Verification</em>' attribute.
	 * @see #setVerification(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_Verification()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getVerification();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getVerification <em>Verification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Verification</em>' attribute.
	 * @see #getVerification()
	 * @generated
	 */
	void setVerification(String value);

	/**
	 * Returns the value of the '<em><b>Message Obj</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.fixflow.MessageObj}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Obj</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Obj</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_MessageObj()
	 * @model containment="true"
	 *        extendedMetaData="name='messageObj' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<MessageObj> getMessageObj();

	/**
	 * Returns the value of the '<em><b>Receive Message</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Receive Message</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Receive Message</em>' containment reference.
	 * @see #setReceiveMessage(ReceiveMessage)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_ReceiveMessage()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	ReceiveMessage getReceiveMessage();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getReceiveMessage <em>Receive Message</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Receive Message</em>' containment reference.
	 * @see #getReceiveMessage()
	 * @generated
	 */
	void setReceiveMessage(ReceiveMessage value);

	/**
	 * Returns the value of the '<em><b>Expected Execution Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expected Execution Time</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expected Execution Time</em>' containment reference.
	 * @see #setExpectedExecutionTime(ExpectedExecutionTime)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_ExpectedExecutionTime()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' name='expectedExecutionTime'"
	 * @generated
	 */
	ExpectedExecutionTime getExpectedExecutionTime();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getExpectedExecutionTime <em>Expected Execution Time</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expected Execution Time</em>' containment reference.
	 * @see #getExpectedExecutionTime()
	 * @generated
	 */
	void setExpectedExecutionTime(ExpectedExecutionTime value);

	/**
	 * Returns the value of the '<em><b>Data Source To Sub Process Mapping</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Source To Sub Process Mapping</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Source To Sub Process Mapping</em>' containment reference.
	 * @see #setDataSourceToSubProcessMapping(DataSourceToSubProcessMapping)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_DataSourceToSubProcessMapping()
	 * @model containment="true"
	 *        extendedMetaData="name='dataSourceToSubProcessMapping' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	DataSourceToSubProcessMapping getDataSourceToSubProcessMapping();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getDataSourceToSubProcessMapping <em>Data Source To Sub Process Mapping</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Source To Sub Process Mapping</em>' containment reference.
	 * @see #getDataSourceToSubProcessMapping()
	 * @generated
	 */
	void setDataSourceToSubProcessMapping(DataSourceToSubProcessMapping value);

	/**
	 * Returns the value of the '<em><b>Sub Process To Data Source Mapping</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Process To Data Source Mapping</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Process To Data Source Mapping</em>' containment reference.
	 * @see #setSubProcessToDataSourceMapping(SubProcessToDataSourceMapping)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_SubProcessToDataSourceMapping()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' name='subProcessToDataSourceMapping' kind='element'"
	 * @generated
	 */
	SubProcessToDataSourceMapping getSubProcessToDataSourceMapping();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getSubProcessToDataSourceMapping <em>Sub Process To Data Source Mapping</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Process To Data Source Mapping</em>' containment reference.
	 * @see #getSubProcessToDataSourceMapping()
	 * @generated
	 */
	void setSubProcessToDataSourceMapping(SubProcessToDataSourceMapping value);

	/**
	 * Returns the value of the '<em><b>Callable Element Biz Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callable Element Biz Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callable Element Biz Key</em>' attribute.
	 * @see #setCallableElementBizKey(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_CallableElementBizKey()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getCallableElementBizKey();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementBizKey <em>Callable Element Biz Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callable Element Biz Key</em>' attribute.
	 * @see #getCallableElementBizKey()
	 * @generated
	 */
	void setCallableElementBizKey(String value);

	/**
	 * Returns the value of the '<em><b>Callable Element Biz Key Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callable Element Biz Key Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callable Element Biz Key Name</em>' attribute.
	 * @see #setCallableElementBizKeyName(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_CallableElementBizKeyName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getCallableElementBizKeyName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementBizKeyName <em>Callable Element Biz Key Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callable Element Biz Key Name</em>' attribute.
	 * @see #getCallableElementBizKeyName()
	 * @generated
	 */
	void setCallableElementBizKeyName(String value);

	/**
	 * Returns the value of the '<em><b>Is Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Async</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Async</em>' attribute.
	 * @see #setIsAsync(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_IsAsync()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' namespace='##targetNamespace'"
	 * @generated
	 */
	String getIsAsync();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getIsAsync <em>Is Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Async</em>' attribute.
	 * @see #getIsAsync()
	 * @generated
	 */
	void setIsAsync(String value);

	/**
	 * Returns the value of the '<em><b>Form Uri View</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Form Uri View</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Form Uri View</em>' containment reference.
	 * @see #setFormUriView(FormUriView)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_FormUriView()
	 * @model containment="true"
	 *        extendedMetaData="name='formUriView' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	FormUriView getFormUriView();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getFormUriView <em>Form Uri View</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Form Uri View</em>' containment reference.
	 * @see #getFormUriView()
	 * @generated
	 */
	void setFormUriView(FormUriView value);

	/**
	 * Returns the value of the '<em><b>Order Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Order Id</em>' attribute.
	 * @see #setOrderId(int)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_OrderId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	int getOrderId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getOrderId <em>Order Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Order Id</em>' attribute.
	 * @see #getOrderId()
	 * @generated
	 */
	void setOrderId(int value);

	/**
	 * Returns the value of the '<em><b>Task Priority</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task Priority</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task Priority</em>' containment reference.
	 * @see #setTaskPriority(TaskPriority)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_TaskPriority()
	 * @model containment="true"
	 *        extendedMetaData="name='taskPriority' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	TaskPriority getTaskPriority();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getTaskPriority <em>Task Priority</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task Priority</em>' containment reference.
	 * @see #getTaskPriority()
	 * @generated
	 */
	void setTaskPriority(TaskPriority value);

	/**
	 * Returns the value of the '<em><b>Assign Policy Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assign Policy Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assign Policy Type</em>' containment reference.
	 * @see #setAssignPolicyType(AssignPolicyType)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_AssignPolicyType()
	 * @model containment="true"
	 *        extendedMetaData="name='assignPolicyType' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	AssignPolicyType getAssignPolicyType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getAssignPolicyType <em>Assign Policy Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assign Policy Type</em>' containment reference.
	 * @see #getAssignPolicyType()
	 * @generated
	 */
	void setAssignPolicyType(AssignPolicyType value);

	/**
	 * Returns the value of the '<em><b>Is Persistence</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 是否在启动流程的时候持久化
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Persistence</em>' attribute.
	 * @see #setIsPersistence(boolean)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_IsPersistence()
	 * @model default="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	boolean isIsPersistence();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#isIsPersistence <em>Is Persistence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Persistence</em>' attribute.
	 * @see #isIsPersistence()
	 * @generated
	 */
	void setIsPersistence(boolean value);

	/**
	 * Returns the value of the '<em><b>Task Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task Type</em>' attribute.
	 * @see #setTaskType(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_TaskType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='attribute'"
	 * @generated
	 */
	String getTaskType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getTaskType <em>Task Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task Type</em>' attribute.
	 * @see #getTaskType()
	 * @generated
	 */
	void setTaskType(String value);

	/**
	 * Returns the value of the '<em><b>Validation Level</b></em>' attribute.
	 * The literals are from the enumeration {@link com.founder.fix.bpmn2extensions.fixflow.ProcessValidationLevel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 流程验证级别
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Validation Level</em>' attribute.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ProcessValidationLevel
	 * @see #setValidationLevel(ProcessValidationLevel)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_ValidationLevel()
	 * @model extendedMetaData="kind='attribute' namespace='##targetNamespace'"
	 * @generated
	 */
	ProcessValidationLevel getValidationLevel();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getValidationLevel <em>Validation Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Validation Level</em>' attribute.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ProcessValidationLevel
	 * @see #getValidationLevel()
	 * @generated
	 */
	void setValidationLevel(ProcessValidationLevel value);

	/**
	 * Returns the value of the '<em><b>Language Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Language Type</em>' attribute.
	 * @see #setLanguageType(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDocumentRoot_LanguageType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' namespace='##targetNamespace'"
	 * @generated
	 */
	String getLanguageType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLanguageType <em>Language Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language Type</em>' attribute.
	 * @see #getLanguageType()
	 * @generated
	 */
	void setLanguageType(String value);

} // DocumentRoot
