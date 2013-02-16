/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow.impl;

import com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance;
import com.founder.fix.bpmn2extensions.fixflow.DataSourceToSubProcessMapping;
import com.founder.fix.bpmn2extensions.fixflow.DataVariable;
import com.founder.fix.bpmn2extensions.fixflow.DocumentRoot;
import com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.FormUri;
import com.founder.fix.bpmn2extensions.fixflow.FormUriView;
import com.founder.fix.bpmn2extensions.fixflow.LoopDataInputCollection;
import com.founder.fix.bpmn2extensions.fixflow.LoopDataOutputCollection;
import com.founder.fix.bpmn2extensions.fixflow.LoopMaximum;
import com.founder.fix.bpmn2extensions.fixflow.MessageObj;
import com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage;
import com.founder.fix.bpmn2extensions.fixflow.ResourceFilter;
import com.founder.fix.bpmn2extensions.fixflow.SkipStrategy;
import com.founder.fix.bpmn2extensions.fixflow.SubProcessToDataSourceMapping;
import com.founder.fix.bpmn2extensions.fixflow.TaskCommand;
import com.founder.fix.bpmn2extensions.fixflow.TaskPriority;
import com.founder.fix.bpmn2extensions.fixflow.TaskSubject;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getTaskSubject <em>Task Subject</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getTaskCommand <em>Task Command</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getResourceType <em>Resource Type</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getResourceFilter <em>Resource Filter</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getDataVariable <em>Data Variable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getDbid <em>Dbid</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getConnectorInstance <em>Connector Instance</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getLoopDataInputCollection <em>Loop Data Input Collection</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getLoopDataOutputCollection <em>Loop Data Output Collection</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getFormUri <em>Form Uri</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getLoopMaximum <em>Loop Maximum</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getSubTask <em>Sub Task</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getScriptName <em>Script Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getResultVariable <em>Result Variable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getCallableElementId <em>Callable Element Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getCallableElementName <em>Callable Element Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getCallableElementVersion <em>Callable Element Version</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getCallableElementVersionName <em>Callable Element Version Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getIncludeExclusion <em>Include Exclusion</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getResourceRange <em>Resource Range</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getAssignAction <em>Assign Action</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getErrorCode <em>Error Code</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getSkipStrategy <em>Skip Strategy</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#isIsContainsSub <em>Is Contains Sub</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getVerification <em>Verification</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getMessageObj <em>Message Obj</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getReceiveMessage <em>Receive Message</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getExpectedExecutionTime <em>Expected Execution Time</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getDataSourceToSubProcessMapping <em>Data Source To Sub Process Mapping</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getSubProcessToDataSourceMapping <em>Sub Process To Data Source Mapping</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getCallableElementBizKey <em>Callable Element Biz Key</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getCallableElementBizKeyName <em>Callable Element Biz Key Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getIsAsync <em>Is Async</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getFormUriView <em>Form Uri View</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getOrderId <em>Order Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getTaskPriority <em>Task Priority</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#getAssignPolicyType <em>Assign Policy Type</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl#isIsPersistence <em>Is Persistence</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * The default value of the '{@link #getResourceType() <em>Resource Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceType()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceType() <em>Resource Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceType()
	 * @generated
	 * @ordered
	 */
	protected String resourceType = RESOURCE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final int VERSION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected int version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDbid() <em>Dbid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDbid()
	 * @generated
	 * @ordered
	 */
	protected static final String DBID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDbid() <em>Dbid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDbid()
	 * @generated
	 * @ordered
	 */
	protected String dbid = DBID_EDEFAULT;

	/**
	 * The default value of the '{@link #getCategory() <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected static final String CATEGORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected String category = CATEGORY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLoopDataInputCollection() <em>Loop Data Input Collection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoopDataInputCollection()
	 * @generated
	 * @ordered
	 */
	protected LoopDataInputCollection loopDataInputCollection;

	/**
	 * The cached value of the '{@link #getLoopDataOutputCollection() <em>Loop Data Output Collection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoopDataOutputCollection()
	 * @generated
	 * @ordered
	 */
	protected LoopDataOutputCollection loopDataOutputCollection;

	/**
	 * The cached value of the '{@link #getFormUri() <em>Form Uri</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormUri()
	 * @generated
	 * @ordered
	 */
	protected FormUri formUri;

	/**
	 * The cached value of the '{@link #getLoopMaximum() <em>Loop Maximum</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoopMaximum()
	 * @generated
	 * @ordered
	 */
	protected LoopMaximum loopMaximum;

	/**
	 * The default value of the '{@link #getSubTask() <em>Sub Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubTask()
	 * @generated
	 * @ordered
	 */
	protected static final String SUB_TASK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubTask() <em>Sub Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubTask()
	 * @generated
	 * @ordered
	 */
	protected String subTask = SUB_TASK_EDEFAULT;

	/**
	 * The default value of the '{@link #getScriptName() <em>Script Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScriptName()
	 * @generated
	 * @ordered
	 */
	protected static final String SCRIPT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScriptName() <em>Script Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScriptName()
	 * @generated
	 * @ordered
	 */
	protected String scriptName = SCRIPT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getResultVariable() <em>Result Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultVariable()
	 * @generated
	 * @ordered
	 */
	protected static final String RESULT_VARIABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResultVariable() <em>Result Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultVariable()
	 * @generated
	 * @ordered
	 */
	protected String resultVariable = RESULT_VARIABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCallableElementId() <em>Callable Element Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableElementId()
	 * @generated
	 * @ordered
	 */
	protected static final String CALLABLE_ELEMENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCallableElementId() <em>Callable Element Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableElementId()
	 * @generated
	 * @ordered
	 */
	protected String callableElementId = CALLABLE_ELEMENT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getCallableElementName() <em>Callable Element Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableElementName()
	 * @generated
	 * @ordered
	 */
	protected static final String CALLABLE_ELEMENT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCallableElementName() <em>Callable Element Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableElementName()
	 * @generated
	 * @ordered
	 */
	protected String callableElementName = CALLABLE_ELEMENT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCallableElementVersion() <em>Callable Element Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableElementVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String CALLABLE_ELEMENT_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCallableElementVersion() <em>Callable Element Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableElementVersion()
	 * @generated
	 * @ordered
	 */
	protected String callableElementVersion = CALLABLE_ELEMENT_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getCallableElementVersionName() <em>Callable Element Version Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableElementVersionName()
	 * @generated
	 * @ordered
	 */
	protected static final String CALLABLE_ELEMENT_VERSION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCallableElementVersionName() <em>Callable Element Version Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableElementVersionName()
	 * @generated
	 * @ordered
	 */
	protected String callableElementVersionName = CALLABLE_ELEMENT_VERSION_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getIncludeExclusion() <em>Include Exclusion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludeExclusion()
	 * @generated
	 * @ordered
	 */
	protected static final String INCLUDE_EXCLUSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIncludeExclusion() <em>Include Exclusion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludeExclusion()
	 * @generated
	 * @ordered
	 */
	protected String includeExclusion = INCLUDE_EXCLUSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getResourceRange() <em>Resource Range</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceRange()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_RANGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceRange() <em>Resource Range</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceRange()
	 * @generated
	 * @ordered
	 */
	protected String resourceRange = RESOURCE_RANGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getAssignAction() <em>Assign Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignAction()
	 * @generated
	 * @ordered
	 */
	protected static final String ASSIGN_ACTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAssignAction() <em>Assign Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignAction()
	 * @generated
	 * @ordered
	 */
	protected String assignAction = ASSIGN_ACTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getErrorCode() <em>Error Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorCode()
	 * @generated
	 * @ordered
	 */
	protected static final String ERROR_CODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getErrorCode() <em>Error Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorCode()
	 * @generated
	 * @ordered
	 */
	protected String errorCode = ERROR_CODE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSkipStrategy() <em>Skip Strategy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkipStrategy()
	 * @generated
	 * @ordered
	 */
	protected SkipStrategy skipStrategy;

	/**
	 * The default value of the '{@link #isIsContainsSub() <em>Is Contains Sub</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsContainsSub()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_CONTAINS_SUB_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsContainsSub() <em>Is Contains Sub</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsContainsSub()
	 * @generated
	 * @ordered
	 */
	protected boolean isContainsSub = IS_CONTAINS_SUB_EDEFAULT;

	/**
	 * The default value of the '{@link #getVerification() <em>Verification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerification()
	 * @generated
	 * @ordered
	 */
	protected static final String VERIFICATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVerification() <em>Verification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerification()
	 * @generated
	 * @ordered
	 */
	protected String verification = VERIFICATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMessageObj() <em>Message Obj</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageObj()
	 * @generated
	 * @ordered
	 */
	protected EList<MessageObj> messageObj;

	/**
	 * The cached value of the '{@link #getReceiveMessage() <em>Receive Message</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReceiveMessage()
	 * @generated
	 * @ordered
	 */
	protected ReceiveMessage receiveMessage;

	/**
	 * The cached value of the '{@link #getExpectedExecutionTime() <em>Expected Execution Time</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpectedExecutionTime()
	 * @generated
	 * @ordered
	 */
	protected ExpectedExecutionTime expectedExecutionTime;

	/**
	 * The cached value of the '{@link #getDataSourceToSubProcessMapping() <em>Data Source To Sub Process Mapping</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataSourceToSubProcessMapping()
	 * @generated
	 * @ordered
	 */
	protected DataSourceToSubProcessMapping dataSourceToSubProcessMapping;

	/**
	 * The cached value of the '{@link #getSubProcessToDataSourceMapping() <em>Sub Process To Data Source Mapping</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubProcessToDataSourceMapping()
	 * @generated
	 * @ordered
	 */
	protected SubProcessToDataSourceMapping subProcessToDataSourceMapping;

	/**
	 * The default value of the '{@link #getCallableElementBizKey() <em>Callable Element Biz Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableElementBizKey()
	 * @generated
	 * @ordered
	 */
	protected static final String CALLABLE_ELEMENT_BIZ_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCallableElementBizKey() <em>Callable Element Biz Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableElementBizKey()
	 * @generated
	 * @ordered
	 */
	protected String callableElementBizKey = CALLABLE_ELEMENT_BIZ_KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getCallableElementBizKeyName() <em>Callable Element Biz Key Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableElementBizKeyName()
	 * @generated
	 * @ordered
	 */
	protected static final String CALLABLE_ELEMENT_BIZ_KEY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCallableElementBizKeyName() <em>Callable Element Biz Key Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableElementBizKeyName()
	 * @generated
	 * @ordered
	 */
	protected String callableElementBizKeyName = CALLABLE_ELEMENT_BIZ_KEY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsAsync() <em>Is Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsAsync()
	 * @generated
	 * @ordered
	 */
	protected static final String IS_ASYNC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsAsync() <em>Is Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsAsync()
	 * @generated
	 * @ordered
	 */
	protected String isAsync = IS_ASYNC_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFormUriView() <em>Form Uri View</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormUriView()
	 * @generated
	 * @ordered
	 */
	protected FormUriView formUriView;

	/**
	 * The default value of the '{@link #getOrderId() <em>Order Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderId()
	 * @generated
	 * @ordered
	 */
	protected static final int ORDER_ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getOrderId() <em>Order Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderId()
	 * @generated
	 * @ordered
	 */
	protected int orderId = ORDER_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTaskPriority() <em>Task Priority</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaskPriority()
	 * @generated
	 * @ordered
	 */
	protected TaskPriority taskPriority;

	/**
	 * The cached value of the '{@link #getAssignPolicyType() <em>Assign Policy Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignPolicyType()
	 * @generated
	 * @ordered
	 */
	protected AssignPolicyType assignPolicyType;

	/**
	 * The default value of the '{@link #isIsPersistence() <em>Is Persistence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsPersistence()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_PERSISTENCE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isIsPersistence() <em>Is Persistence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsPersistence()
	 * @generated
	 * @ordered
	 */
	protected boolean isPersistence = IS_PERSISTENCE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FixFlowPackage.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, FixFlowPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, FixFlowPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, FixFlowPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskSubject getTaskSubject() {
		return (TaskSubject)getMixed().get(FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_SUBJECT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTaskSubject(TaskSubject newTaskSubject, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_SUBJECT, newTaskSubject, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTaskSubject(TaskSubject newTaskSubject) {
		((FeatureMap.Internal)getMixed()).set(FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_SUBJECT, newTaskSubject);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TaskCommand> getTaskCommand() {
		return getMixed().list(FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceType(String newResourceType) {
		String oldResourceType = resourceType;
		resourceType = newResourceType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__RESOURCE_TYPE, oldResourceType, resourceType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceFilter> getResourceFilter() {
		return getMixed().list(FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_FILTER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataVariable> getDataVariable() {
		return getMixed().list(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(int newVersion) {
		int oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDbid() {
		return dbid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDbid(String newDbid) {
		String oldDbid = dbid;
		dbid = newDbid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__DBID, oldDbid, dbid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCategory(String newCategory) {
		String oldCategory = category;
		category = newCategory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__CATEGORY, oldCategory, category));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConnectorInstance> getConnectorInstance() {
		return getMixed().list(FixFlowPackage.Literals.DOCUMENT_ROOT__CONNECTOR_INSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoopDataInputCollection getLoopDataInputCollection() {
		return loopDataInputCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLoopDataInputCollection(LoopDataInputCollection newLoopDataInputCollection, NotificationChain msgs) {
		LoopDataInputCollection oldLoopDataInputCollection = loopDataInputCollection;
		loopDataInputCollection = newLoopDataInputCollection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION, oldLoopDataInputCollection, newLoopDataInputCollection);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoopDataInputCollection(LoopDataInputCollection newLoopDataInputCollection) {
		if (newLoopDataInputCollection != loopDataInputCollection) {
			NotificationChain msgs = null;
			if (loopDataInputCollection != null)
				msgs = ((InternalEObject)loopDataInputCollection).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION, null, msgs);
			if (newLoopDataInputCollection != null)
				msgs = ((InternalEObject)newLoopDataInputCollection).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION, null, msgs);
			msgs = basicSetLoopDataInputCollection(newLoopDataInputCollection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION, newLoopDataInputCollection, newLoopDataInputCollection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoopDataOutputCollection getLoopDataOutputCollection() {
		return loopDataOutputCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLoopDataOutputCollection(LoopDataOutputCollection newLoopDataOutputCollection, NotificationChain msgs) {
		LoopDataOutputCollection oldLoopDataOutputCollection = loopDataOutputCollection;
		loopDataOutputCollection = newLoopDataOutputCollection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION, oldLoopDataOutputCollection, newLoopDataOutputCollection);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoopDataOutputCollection(LoopDataOutputCollection newLoopDataOutputCollection) {
		if (newLoopDataOutputCollection != loopDataOutputCollection) {
			NotificationChain msgs = null;
			if (loopDataOutputCollection != null)
				msgs = ((InternalEObject)loopDataOutputCollection).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION, null, msgs);
			if (newLoopDataOutputCollection != null)
				msgs = ((InternalEObject)newLoopDataOutputCollection).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION, null, msgs);
			msgs = basicSetLoopDataOutputCollection(newLoopDataOutputCollection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION, newLoopDataOutputCollection, newLoopDataOutputCollection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormUri getFormUri() {
		return formUri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFormUri(FormUri newFormUri, NotificationChain msgs) {
		FormUri oldFormUri = formUri;
		formUri = newFormUri;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__FORM_URI, oldFormUri, newFormUri);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormUri(FormUri newFormUri) {
		if (newFormUri != formUri) {
			NotificationChain msgs = null;
			if (formUri != null)
				msgs = ((InternalEObject)formUri).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__FORM_URI, null, msgs);
			if (newFormUri != null)
				msgs = ((InternalEObject)newFormUri).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__FORM_URI, null, msgs);
			msgs = basicSetFormUri(newFormUri, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__FORM_URI, newFormUri, newFormUri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoopMaximum getLoopMaximum() {
		return loopMaximum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLoopMaximum(LoopMaximum newLoopMaximum, NotificationChain msgs) {
		LoopMaximum oldLoopMaximum = loopMaximum;
		loopMaximum = newLoopMaximum;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__LOOP_MAXIMUM, oldLoopMaximum, newLoopMaximum);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoopMaximum(LoopMaximum newLoopMaximum) {
		if (newLoopMaximum != loopMaximum) {
			NotificationChain msgs = null;
			if (loopMaximum != null)
				msgs = ((InternalEObject)loopMaximum).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__LOOP_MAXIMUM, null, msgs);
			if (newLoopMaximum != null)
				msgs = ((InternalEObject)newLoopMaximum).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__LOOP_MAXIMUM, null, msgs);
			msgs = basicSetLoopMaximum(newLoopMaximum, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__LOOP_MAXIMUM, newLoopMaximum, newLoopMaximum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubTask() {
		return subTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubTask(String newSubTask) {
		String oldSubTask = subTask;
		subTask = newSubTask;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__SUB_TASK, oldSubTask, subTask));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getScriptName() {
		return scriptName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScriptName(String newScriptName) {
		String oldScriptName = scriptName;
		scriptName = newScriptName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__SCRIPT_NAME, oldScriptName, scriptName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResultVariable() {
		return resultVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultVariable(String newResultVariable) {
		String oldResultVariable = resultVariable;
		resultVariable = newResultVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__RESULT_VARIABLE, oldResultVariable, resultVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCallableElementId() {
		return callableElementId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallableElementId(String newCallableElementId) {
		String oldCallableElementId = callableElementId;
		callableElementId = newCallableElementId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_ID, oldCallableElementId, callableElementId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCallableElementName() {
		return callableElementName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallableElementName(String newCallableElementName) {
		String oldCallableElementName = callableElementName;
		callableElementName = newCallableElementName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_NAME, oldCallableElementName, callableElementName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCallableElementVersion() {
		return callableElementVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallableElementVersion(String newCallableElementVersion) {
		String oldCallableElementVersion = callableElementVersion;
		callableElementVersion = newCallableElementVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION, oldCallableElementVersion, callableElementVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCallableElementVersionName() {
		return callableElementVersionName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallableElementVersionName(String newCallableElementVersionName) {
		String oldCallableElementVersionName = callableElementVersionName;
		callableElementVersionName = newCallableElementVersionName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION_NAME, oldCallableElementVersionName, callableElementVersionName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIncludeExclusion() {
		return includeExclusion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncludeExclusion(String newIncludeExclusion) {
		String oldIncludeExclusion = includeExclusion;
		includeExclusion = newIncludeExclusion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__INCLUDE_EXCLUSION, oldIncludeExclusion, includeExclusion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResourceRange() {
		return resourceRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceRange(String newResourceRange) {
		String oldResourceRange = resourceRange;
		resourceRange = newResourceRange;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__RESOURCE_RANGE, oldResourceRange, resourceRange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAssignAction() {
		return assignAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssignAction(String newAssignAction) {
		String oldAssignAction = assignAction;
		assignAction = newAssignAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__ASSIGN_ACTION, oldAssignAction, assignAction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setErrorCode(String newErrorCode) {
		String oldErrorCode = errorCode;
		errorCode = newErrorCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__ERROR_CODE, oldErrorCode, errorCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SkipStrategy getSkipStrategy() {
		return skipStrategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSkipStrategy(SkipStrategy newSkipStrategy, NotificationChain msgs) {
		SkipStrategy oldSkipStrategy = skipStrategy;
		skipStrategy = newSkipStrategy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__SKIP_STRATEGY, oldSkipStrategy, newSkipStrategy);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSkipStrategy(SkipStrategy newSkipStrategy) {
		if (newSkipStrategy != skipStrategy) {
			NotificationChain msgs = null;
			if (skipStrategy != null)
				msgs = ((InternalEObject)skipStrategy).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__SKIP_STRATEGY, null, msgs);
			if (newSkipStrategy != null)
				msgs = ((InternalEObject)newSkipStrategy).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__SKIP_STRATEGY, null, msgs);
			msgs = basicSetSkipStrategy(newSkipStrategy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__SKIP_STRATEGY, newSkipStrategy, newSkipStrategy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsContainsSub() {
		return isContainsSub;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsContainsSub(boolean newIsContainsSub) {
		boolean oldIsContainsSub = isContainsSub;
		isContainsSub = newIsContainsSub;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__IS_CONTAINS_SUB, oldIsContainsSub, isContainsSub));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVerification() {
		return verification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVerification(String newVerification) {
		String oldVerification = verification;
		verification = newVerification;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__VERIFICATION, oldVerification, verification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MessageObj> getMessageObj() {
		if (messageObj == null) {
			messageObj = new EObjectContainmentEList<MessageObj>(MessageObj.class, this, FixFlowPackage.DOCUMENT_ROOT__MESSAGE_OBJ);
		}
		return messageObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReceiveMessage getReceiveMessage() {
		return receiveMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReceiveMessage(ReceiveMessage newReceiveMessage, NotificationChain msgs) {
		ReceiveMessage oldReceiveMessage = receiveMessage;
		receiveMessage = newReceiveMessage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__RECEIVE_MESSAGE, oldReceiveMessage, newReceiveMessage);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReceiveMessage(ReceiveMessage newReceiveMessage) {
		if (newReceiveMessage != receiveMessage) {
			NotificationChain msgs = null;
			if (receiveMessage != null)
				msgs = ((InternalEObject)receiveMessage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__RECEIVE_MESSAGE, null, msgs);
			if (newReceiveMessage != null)
				msgs = ((InternalEObject)newReceiveMessage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__RECEIVE_MESSAGE, null, msgs);
			msgs = basicSetReceiveMessage(newReceiveMessage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__RECEIVE_MESSAGE, newReceiveMessage, newReceiveMessage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpectedExecutionTime getExpectedExecutionTime() {
		return expectedExecutionTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpectedExecutionTime(ExpectedExecutionTime newExpectedExecutionTime, NotificationChain msgs) {
		ExpectedExecutionTime oldExpectedExecutionTime = expectedExecutionTime;
		expectedExecutionTime = newExpectedExecutionTime;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__EXPECTED_EXECUTION_TIME, oldExpectedExecutionTime, newExpectedExecutionTime);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpectedExecutionTime(ExpectedExecutionTime newExpectedExecutionTime) {
		if (newExpectedExecutionTime != expectedExecutionTime) {
			NotificationChain msgs = null;
			if (expectedExecutionTime != null)
				msgs = ((InternalEObject)expectedExecutionTime).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__EXPECTED_EXECUTION_TIME, null, msgs);
			if (newExpectedExecutionTime != null)
				msgs = ((InternalEObject)newExpectedExecutionTime).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__EXPECTED_EXECUTION_TIME, null, msgs);
			msgs = basicSetExpectedExecutionTime(newExpectedExecutionTime, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__EXPECTED_EXECUTION_TIME, newExpectedExecutionTime, newExpectedExecutionTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSourceToSubProcessMapping getDataSourceToSubProcessMapping() {
		return dataSourceToSubProcessMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataSourceToSubProcessMapping(DataSourceToSubProcessMapping newDataSourceToSubProcessMapping, NotificationChain msgs) {
		DataSourceToSubProcessMapping oldDataSourceToSubProcessMapping = dataSourceToSubProcessMapping;
		dataSourceToSubProcessMapping = newDataSourceToSubProcessMapping;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING, oldDataSourceToSubProcessMapping, newDataSourceToSubProcessMapping);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataSourceToSubProcessMapping(DataSourceToSubProcessMapping newDataSourceToSubProcessMapping) {
		if (newDataSourceToSubProcessMapping != dataSourceToSubProcessMapping) {
			NotificationChain msgs = null;
			if (dataSourceToSubProcessMapping != null)
				msgs = ((InternalEObject)dataSourceToSubProcessMapping).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING, null, msgs);
			if (newDataSourceToSubProcessMapping != null)
				msgs = ((InternalEObject)newDataSourceToSubProcessMapping).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING, null, msgs);
			msgs = basicSetDataSourceToSubProcessMapping(newDataSourceToSubProcessMapping, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING, newDataSourceToSubProcessMapping, newDataSourceToSubProcessMapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubProcessToDataSourceMapping getSubProcessToDataSourceMapping() {
		return subProcessToDataSourceMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSubProcessToDataSourceMapping(SubProcessToDataSourceMapping newSubProcessToDataSourceMapping, NotificationChain msgs) {
		SubProcessToDataSourceMapping oldSubProcessToDataSourceMapping = subProcessToDataSourceMapping;
		subProcessToDataSourceMapping = newSubProcessToDataSourceMapping;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING, oldSubProcessToDataSourceMapping, newSubProcessToDataSourceMapping);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubProcessToDataSourceMapping(SubProcessToDataSourceMapping newSubProcessToDataSourceMapping) {
		if (newSubProcessToDataSourceMapping != subProcessToDataSourceMapping) {
			NotificationChain msgs = null;
			if (subProcessToDataSourceMapping != null)
				msgs = ((InternalEObject)subProcessToDataSourceMapping).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING, null, msgs);
			if (newSubProcessToDataSourceMapping != null)
				msgs = ((InternalEObject)newSubProcessToDataSourceMapping).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING, null, msgs);
			msgs = basicSetSubProcessToDataSourceMapping(newSubProcessToDataSourceMapping, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING, newSubProcessToDataSourceMapping, newSubProcessToDataSourceMapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCallableElementBizKey() {
		return callableElementBizKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallableElementBizKey(String newCallableElementBizKey) {
		String oldCallableElementBizKey = callableElementBizKey;
		callableElementBizKey = newCallableElementBizKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY, oldCallableElementBizKey, callableElementBizKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCallableElementBizKeyName() {
		return callableElementBizKeyName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallableElementBizKeyName(String newCallableElementBizKeyName) {
		String oldCallableElementBizKeyName = callableElementBizKeyName;
		callableElementBizKeyName = newCallableElementBizKeyName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY_NAME, oldCallableElementBizKeyName, callableElementBizKeyName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIsAsync() {
		return isAsync;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsAsync(String newIsAsync) {
		String oldIsAsync = isAsync;
		isAsync = newIsAsync;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__IS_ASYNC, oldIsAsync, isAsync));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormUriView getFormUriView() {
		return formUriView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFormUriView(FormUriView newFormUriView, NotificationChain msgs) {
		FormUriView oldFormUriView = formUriView;
		formUriView = newFormUriView;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__FORM_URI_VIEW, oldFormUriView, newFormUriView);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormUriView(FormUriView newFormUriView) {
		if (newFormUriView != formUriView) {
			NotificationChain msgs = null;
			if (formUriView != null)
				msgs = ((InternalEObject)formUriView).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__FORM_URI_VIEW, null, msgs);
			if (newFormUriView != null)
				msgs = ((InternalEObject)newFormUriView).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__FORM_URI_VIEW, null, msgs);
			msgs = basicSetFormUriView(newFormUriView, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__FORM_URI_VIEW, newFormUriView, newFormUriView));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderId(int newOrderId) {
		int oldOrderId = orderId;
		orderId = newOrderId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__ORDER_ID, oldOrderId, orderId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskPriority getTaskPriority() {
		return taskPriority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTaskPriority(TaskPriority newTaskPriority, NotificationChain msgs) {
		TaskPriority oldTaskPriority = taskPriority;
		taskPriority = newTaskPriority;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__TASK_PRIORITY, oldTaskPriority, newTaskPriority);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTaskPriority(TaskPriority newTaskPriority) {
		if (newTaskPriority != taskPriority) {
			NotificationChain msgs = null;
			if (taskPriority != null)
				msgs = ((InternalEObject)taskPriority).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__TASK_PRIORITY, null, msgs);
			if (newTaskPriority != null)
				msgs = ((InternalEObject)newTaskPriority).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__TASK_PRIORITY, null, msgs);
			msgs = basicSetTaskPriority(newTaskPriority, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__TASK_PRIORITY, newTaskPriority, newTaskPriority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignPolicyType getAssignPolicyType() {
		return assignPolicyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssignPolicyType(AssignPolicyType newAssignPolicyType, NotificationChain msgs) {
		AssignPolicyType oldAssignPolicyType = assignPolicyType;
		assignPolicyType = newAssignPolicyType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE, oldAssignPolicyType, newAssignPolicyType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssignPolicyType(AssignPolicyType newAssignPolicyType) {
		if (newAssignPolicyType != assignPolicyType) {
			NotificationChain msgs = null;
			if (assignPolicyType != null)
				msgs = ((InternalEObject)assignPolicyType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE, null, msgs);
			if (newAssignPolicyType != null)
				msgs = ((InternalEObject)newAssignPolicyType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE, null, msgs);
			msgs = basicSetAssignPolicyType(newAssignPolicyType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE, newAssignPolicyType, newAssignPolicyType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsPersistence() {
		return isPersistence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsPersistence(boolean newIsPersistence) {
		boolean oldIsPersistence = isPersistence;
		isPersistence = newIsPersistence;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.DOCUMENT_ROOT__IS_PERSISTENCE, oldIsPersistence, isPersistence));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FixFlowPackage.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__TASK_SUBJECT:
				return basicSetTaskSubject(null, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__TASK_COMMAND:
				return ((InternalEList<?>)getTaskCommand()).basicRemove(otherEnd, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__RESOURCE_FILTER:
				return ((InternalEList<?>)getResourceFilter()).basicRemove(otherEnd, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__DATA_VARIABLE:
				return ((InternalEList<?>)getDataVariable()).basicRemove(otherEnd, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__CONNECTOR_INSTANCE:
				return ((InternalEList<?>)getConnectorInstance()).basicRemove(otherEnd, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION:
				return basicSetLoopDataInputCollection(null, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION:
				return basicSetLoopDataOutputCollection(null, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__FORM_URI:
				return basicSetFormUri(null, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_MAXIMUM:
				return basicSetLoopMaximum(null, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__SKIP_STRATEGY:
				return basicSetSkipStrategy(null, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__MESSAGE_OBJ:
				return ((InternalEList<?>)getMessageObj()).basicRemove(otherEnd, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__RECEIVE_MESSAGE:
				return basicSetReceiveMessage(null, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__EXPECTED_EXECUTION_TIME:
				return basicSetExpectedExecutionTime(null, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING:
				return basicSetDataSourceToSubProcessMapping(null, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING:
				return basicSetSubProcessToDataSourceMapping(null, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__FORM_URI_VIEW:
				return basicSetFormUriView(null, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__TASK_PRIORITY:
				return basicSetTaskPriority(null, msgs);
			case FixFlowPackage.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE:
				return basicSetAssignPolicyType(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FixFlowPackage.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case FixFlowPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case FixFlowPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case FixFlowPackage.DOCUMENT_ROOT__TASK_SUBJECT:
				return getTaskSubject();
			case FixFlowPackage.DOCUMENT_ROOT__TASK_COMMAND:
				return getTaskCommand();
			case FixFlowPackage.DOCUMENT_ROOT__RESOURCE_TYPE:
				return getResourceType();
			case FixFlowPackage.DOCUMENT_ROOT__RESOURCE_FILTER:
				return getResourceFilter();
			case FixFlowPackage.DOCUMENT_ROOT__DATA_VARIABLE:
				return getDataVariable();
			case FixFlowPackage.DOCUMENT_ROOT__VERSION:
				return getVersion();
			case FixFlowPackage.DOCUMENT_ROOT__DBID:
				return getDbid();
			case FixFlowPackage.DOCUMENT_ROOT__CATEGORY:
				return getCategory();
			case FixFlowPackage.DOCUMENT_ROOT__CONNECTOR_INSTANCE:
				return getConnectorInstance();
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION:
				return getLoopDataInputCollection();
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION:
				return getLoopDataOutputCollection();
			case FixFlowPackage.DOCUMENT_ROOT__FORM_URI:
				return getFormUri();
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_MAXIMUM:
				return getLoopMaximum();
			case FixFlowPackage.DOCUMENT_ROOT__SUB_TASK:
				return getSubTask();
			case FixFlowPackage.DOCUMENT_ROOT__SCRIPT_NAME:
				return getScriptName();
			case FixFlowPackage.DOCUMENT_ROOT__RESULT_VARIABLE:
				return getResultVariable();
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_ID:
				return getCallableElementId();
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_NAME:
				return getCallableElementName();
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION:
				return getCallableElementVersion();
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION_NAME:
				return getCallableElementVersionName();
			case FixFlowPackage.DOCUMENT_ROOT__INCLUDE_EXCLUSION:
				return getIncludeExclusion();
			case FixFlowPackage.DOCUMENT_ROOT__RESOURCE_RANGE:
				return getResourceRange();
			case FixFlowPackage.DOCUMENT_ROOT__ASSIGN_ACTION:
				return getAssignAction();
			case FixFlowPackage.DOCUMENT_ROOT__ERROR_CODE:
				return getErrorCode();
			case FixFlowPackage.DOCUMENT_ROOT__SKIP_STRATEGY:
				return getSkipStrategy();
			case FixFlowPackage.DOCUMENT_ROOT__IS_CONTAINS_SUB:
				return isIsContainsSub();
			case FixFlowPackage.DOCUMENT_ROOT__VERIFICATION:
				return getVerification();
			case FixFlowPackage.DOCUMENT_ROOT__MESSAGE_OBJ:
				return getMessageObj();
			case FixFlowPackage.DOCUMENT_ROOT__RECEIVE_MESSAGE:
				return getReceiveMessage();
			case FixFlowPackage.DOCUMENT_ROOT__EXPECTED_EXECUTION_TIME:
				return getExpectedExecutionTime();
			case FixFlowPackage.DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING:
				return getDataSourceToSubProcessMapping();
			case FixFlowPackage.DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING:
				return getSubProcessToDataSourceMapping();
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY:
				return getCallableElementBizKey();
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY_NAME:
				return getCallableElementBizKeyName();
			case FixFlowPackage.DOCUMENT_ROOT__IS_ASYNC:
				return getIsAsync();
			case FixFlowPackage.DOCUMENT_ROOT__FORM_URI_VIEW:
				return getFormUriView();
			case FixFlowPackage.DOCUMENT_ROOT__ORDER_ID:
				return getOrderId();
			case FixFlowPackage.DOCUMENT_ROOT__TASK_PRIORITY:
				return getTaskPriority();
			case FixFlowPackage.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE:
				return getAssignPolicyType();
			case FixFlowPackage.DOCUMENT_ROOT__IS_PERSISTENCE:
				return isIsPersistence();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FixFlowPackage.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__TASK_SUBJECT:
				setTaskSubject((TaskSubject)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__TASK_COMMAND:
				getTaskCommand().clear();
				getTaskCommand().addAll((Collection<? extends TaskCommand>)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__RESOURCE_TYPE:
				setResourceType((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__RESOURCE_FILTER:
				getResourceFilter().clear();
				getResourceFilter().addAll((Collection<? extends ResourceFilter>)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__DATA_VARIABLE:
				getDataVariable().clear();
				getDataVariable().addAll((Collection<? extends DataVariable>)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__VERSION:
				setVersion((Integer)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__DBID:
				setDbid((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CATEGORY:
				setCategory((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CONNECTOR_INSTANCE:
				getConnectorInstance().clear();
				getConnectorInstance().addAll((Collection<? extends ConnectorInstance>)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION:
				setLoopDataInputCollection((LoopDataInputCollection)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION:
				setLoopDataOutputCollection((LoopDataOutputCollection)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__FORM_URI:
				setFormUri((FormUri)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_MAXIMUM:
				setLoopMaximum((LoopMaximum)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__SUB_TASK:
				setSubTask((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__SCRIPT_NAME:
				setScriptName((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__RESULT_VARIABLE:
				setResultVariable((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_ID:
				setCallableElementId((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_NAME:
				setCallableElementName((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION:
				setCallableElementVersion((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION_NAME:
				setCallableElementVersionName((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__INCLUDE_EXCLUSION:
				setIncludeExclusion((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__RESOURCE_RANGE:
				setResourceRange((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__ASSIGN_ACTION:
				setAssignAction((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__ERROR_CODE:
				setErrorCode((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__SKIP_STRATEGY:
				setSkipStrategy((SkipStrategy)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__IS_CONTAINS_SUB:
				setIsContainsSub((Boolean)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__VERIFICATION:
				setVerification((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__MESSAGE_OBJ:
				getMessageObj().clear();
				getMessageObj().addAll((Collection<? extends MessageObj>)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__RECEIVE_MESSAGE:
				setReceiveMessage((ReceiveMessage)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__EXPECTED_EXECUTION_TIME:
				setExpectedExecutionTime((ExpectedExecutionTime)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING:
				setDataSourceToSubProcessMapping((DataSourceToSubProcessMapping)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING:
				setSubProcessToDataSourceMapping((SubProcessToDataSourceMapping)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY:
				setCallableElementBizKey((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY_NAME:
				setCallableElementBizKeyName((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__IS_ASYNC:
				setIsAsync((String)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__FORM_URI_VIEW:
				setFormUriView((FormUriView)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__ORDER_ID:
				setOrderId((Integer)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__TASK_PRIORITY:
				setTaskPriority((TaskPriority)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE:
				setAssignPolicyType((AssignPolicyType)newValue);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__IS_PERSISTENCE:
				setIsPersistence((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case FixFlowPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case FixFlowPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case FixFlowPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case FixFlowPackage.DOCUMENT_ROOT__TASK_SUBJECT:
				setTaskSubject((TaskSubject)null);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__TASK_COMMAND:
				getTaskCommand().clear();
				return;
			case FixFlowPackage.DOCUMENT_ROOT__RESOURCE_TYPE:
				setResourceType(RESOURCE_TYPE_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__RESOURCE_FILTER:
				getResourceFilter().clear();
				return;
			case FixFlowPackage.DOCUMENT_ROOT__DATA_VARIABLE:
				getDataVariable().clear();
				return;
			case FixFlowPackage.DOCUMENT_ROOT__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__DBID:
				setDbid(DBID_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CATEGORY:
				setCategory(CATEGORY_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CONNECTOR_INSTANCE:
				getConnectorInstance().clear();
				return;
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION:
				setLoopDataInputCollection((LoopDataInputCollection)null);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION:
				setLoopDataOutputCollection((LoopDataOutputCollection)null);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__FORM_URI:
				setFormUri((FormUri)null);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_MAXIMUM:
				setLoopMaximum((LoopMaximum)null);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__SUB_TASK:
				setSubTask(SUB_TASK_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__SCRIPT_NAME:
				setScriptName(SCRIPT_NAME_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__RESULT_VARIABLE:
				setResultVariable(RESULT_VARIABLE_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_ID:
				setCallableElementId(CALLABLE_ELEMENT_ID_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_NAME:
				setCallableElementName(CALLABLE_ELEMENT_NAME_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION:
				setCallableElementVersion(CALLABLE_ELEMENT_VERSION_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION_NAME:
				setCallableElementVersionName(CALLABLE_ELEMENT_VERSION_NAME_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__INCLUDE_EXCLUSION:
				setIncludeExclusion(INCLUDE_EXCLUSION_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__RESOURCE_RANGE:
				setResourceRange(RESOURCE_RANGE_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__ASSIGN_ACTION:
				setAssignAction(ASSIGN_ACTION_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__ERROR_CODE:
				setErrorCode(ERROR_CODE_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__SKIP_STRATEGY:
				setSkipStrategy((SkipStrategy)null);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__IS_CONTAINS_SUB:
				setIsContainsSub(IS_CONTAINS_SUB_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__VERIFICATION:
				setVerification(VERIFICATION_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__MESSAGE_OBJ:
				getMessageObj().clear();
				return;
			case FixFlowPackage.DOCUMENT_ROOT__RECEIVE_MESSAGE:
				setReceiveMessage((ReceiveMessage)null);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__EXPECTED_EXECUTION_TIME:
				setExpectedExecutionTime((ExpectedExecutionTime)null);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING:
				setDataSourceToSubProcessMapping((DataSourceToSubProcessMapping)null);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING:
				setSubProcessToDataSourceMapping((SubProcessToDataSourceMapping)null);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY:
				setCallableElementBizKey(CALLABLE_ELEMENT_BIZ_KEY_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY_NAME:
				setCallableElementBizKeyName(CALLABLE_ELEMENT_BIZ_KEY_NAME_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__IS_ASYNC:
				setIsAsync(IS_ASYNC_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__FORM_URI_VIEW:
				setFormUriView((FormUriView)null);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__ORDER_ID:
				setOrderId(ORDER_ID_EDEFAULT);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__TASK_PRIORITY:
				setTaskPriority((TaskPriority)null);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE:
				setAssignPolicyType((AssignPolicyType)null);
				return;
			case FixFlowPackage.DOCUMENT_ROOT__IS_PERSISTENCE:
				setIsPersistence(IS_PERSISTENCE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case FixFlowPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case FixFlowPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case FixFlowPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case FixFlowPackage.DOCUMENT_ROOT__TASK_SUBJECT:
				return getTaskSubject() != null;
			case FixFlowPackage.DOCUMENT_ROOT__TASK_COMMAND:
				return !getTaskCommand().isEmpty();
			case FixFlowPackage.DOCUMENT_ROOT__RESOURCE_TYPE:
				return RESOURCE_TYPE_EDEFAULT == null ? resourceType != null : !RESOURCE_TYPE_EDEFAULT.equals(resourceType);
			case FixFlowPackage.DOCUMENT_ROOT__RESOURCE_FILTER:
				return !getResourceFilter().isEmpty();
			case FixFlowPackage.DOCUMENT_ROOT__DATA_VARIABLE:
				return !getDataVariable().isEmpty();
			case FixFlowPackage.DOCUMENT_ROOT__VERSION:
				return version != VERSION_EDEFAULT;
			case FixFlowPackage.DOCUMENT_ROOT__DBID:
				return DBID_EDEFAULT == null ? dbid != null : !DBID_EDEFAULT.equals(dbid);
			case FixFlowPackage.DOCUMENT_ROOT__CATEGORY:
				return CATEGORY_EDEFAULT == null ? category != null : !CATEGORY_EDEFAULT.equals(category);
			case FixFlowPackage.DOCUMENT_ROOT__CONNECTOR_INSTANCE:
				return !getConnectorInstance().isEmpty();
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION:
				return loopDataInputCollection != null;
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION:
				return loopDataOutputCollection != null;
			case FixFlowPackage.DOCUMENT_ROOT__FORM_URI:
				return formUri != null;
			case FixFlowPackage.DOCUMENT_ROOT__LOOP_MAXIMUM:
				return loopMaximum != null;
			case FixFlowPackage.DOCUMENT_ROOT__SUB_TASK:
				return SUB_TASK_EDEFAULT == null ? subTask != null : !SUB_TASK_EDEFAULT.equals(subTask);
			case FixFlowPackage.DOCUMENT_ROOT__SCRIPT_NAME:
				return SCRIPT_NAME_EDEFAULT == null ? scriptName != null : !SCRIPT_NAME_EDEFAULT.equals(scriptName);
			case FixFlowPackage.DOCUMENT_ROOT__RESULT_VARIABLE:
				return RESULT_VARIABLE_EDEFAULT == null ? resultVariable != null : !RESULT_VARIABLE_EDEFAULT.equals(resultVariable);
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_ID:
				return CALLABLE_ELEMENT_ID_EDEFAULT == null ? callableElementId != null : !CALLABLE_ELEMENT_ID_EDEFAULT.equals(callableElementId);
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_NAME:
				return CALLABLE_ELEMENT_NAME_EDEFAULT == null ? callableElementName != null : !CALLABLE_ELEMENT_NAME_EDEFAULT.equals(callableElementName);
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION:
				return CALLABLE_ELEMENT_VERSION_EDEFAULT == null ? callableElementVersion != null : !CALLABLE_ELEMENT_VERSION_EDEFAULT.equals(callableElementVersion);
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION_NAME:
				return CALLABLE_ELEMENT_VERSION_NAME_EDEFAULT == null ? callableElementVersionName != null : !CALLABLE_ELEMENT_VERSION_NAME_EDEFAULT.equals(callableElementVersionName);
			case FixFlowPackage.DOCUMENT_ROOT__INCLUDE_EXCLUSION:
				return INCLUDE_EXCLUSION_EDEFAULT == null ? includeExclusion != null : !INCLUDE_EXCLUSION_EDEFAULT.equals(includeExclusion);
			case FixFlowPackage.DOCUMENT_ROOT__RESOURCE_RANGE:
				return RESOURCE_RANGE_EDEFAULT == null ? resourceRange != null : !RESOURCE_RANGE_EDEFAULT.equals(resourceRange);
			case FixFlowPackage.DOCUMENT_ROOT__ASSIGN_ACTION:
				return ASSIGN_ACTION_EDEFAULT == null ? assignAction != null : !ASSIGN_ACTION_EDEFAULT.equals(assignAction);
			case FixFlowPackage.DOCUMENT_ROOT__ERROR_CODE:
				return ERROR_CODE_EDEFAULT == null ? errorCode != null : !ERROR_CODE_EDEFAULT.equals(errorCode);
			case FixFlowPackage.DOCUMENT_ROOT__SKIP_STRATEGY:
				return skipStrategy != null;
			case FixFlowPackage.DOCUMENT_ROOT__IS_CONTAINS_SUB:
				return isContainsSub != IS_CONTAINS_SUB_EDEFAULT;
			case FixFlowPackage.DOCUMENT_ROOT__VERIFICATION:
				return VERIFICATION_EDEFAULT == null ? verification != null : !VERIFICATION_EDEFAULT.equals(verification);
			case FixFlowPackage.DOCUMENT_ROOT__MESSAGE_OBJ:
				return messageObj != null && !messageObj.isEmpty();
			case FixFlowPackage.DOCUMENT_ROOT__RECEIVE_MESSAGE:
				return receiveMessage != null;
			case FixFlowPackage.DOCUMENT_ROOT__EXPECTED_EXECUTION_TIME:
				return expectedExecutionTime != null;
			case FixFlowPackage.DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING:
				return dataSourceToSubProcessMapping != null;
			case FixFlowPackage.DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING:
				return subProcessToDataSourceMapping != null;
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY:
				return CALLABLE_ELEMENT_BIZ_KEY_EDEFAULT == null ? callableElementBizKey != null : !CALLABLE_ELEMENT_BIZ_KEY_EDEFAULT.equals(callableElementBizKey);
			case FixFlowPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY_NAME:
				return CALLABLE_ELEMENT_BIZ_KEY_NAME_EDEFAULT == null ? callableElementBizKeyName != null : !CALLABLE_ELEMENT_BIZ_KEY_NAME_EDEFAULT.equals(callableElementBizKeyName);
			case FixFlowPackage.DOCUMENT_ROOT__IS_ASYNC:
				return IS_ASYNC_EDEFAULT == null ? isAsync != null : !IS_ASYNC_EDEFAULT.equals(isAsync);
			case FixFlowPackage.DOCUMENT_ROOT__FORM_URI_VIEW:
				return formUriView != null;
			case FixFlowPackage.DOCUMENT_ROOT__ORDER_ID:
				return orderId != ORDER_ID_EDEFAULT;
			case FixFlowPackage.DOCUMENT_ROOT__TASK_PRIORITY:
				return taskPriority != null;
			case FixFlowPackage.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE:
				return assignPolicyType != null;
			case FixFlowPackage.DOCUMENT_ROOT__IS_PERSISTENCE:
				return isPersistence != IS_PERSISTENCE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(", resourceType: ");
		result.append(resourceType);
		result.append(", version: ");
		result.append(version);
		result.append(", dbid: ");
		result.append(dbid);
		result.append(", category: ");
		result.append(category);
		result.append(", subTask: ");
		result.append(subTask);
		result.append(", scriptName: ");
		result.append(scriptName);
		result.append(", resultVariable: ");
		result.append(resultVariable);
		result.append(", callableElementId: ");
		result.append(callableElementId);
		result.append(", callableElementName: ");
		result.append(callableElementName);
		result.append(", callableElementVersion: ");
		result.append(callableElementVersion);
		result.append(", callableElementVersionName: ");
		result.append(callableElementVersionName);
		result.append(", includeExclusion: ");
		result.append(includeExclusion);
		result.append(", resourceRange: ");
		result.append(resourceRange);
		result.append(", assignAction: ");
		result.append(assignAction);
		result.append(", errorCode: ");
		result.append(errorCode);
		result.append(", isContainsSub: ");
		result.append(isContainsSub);
		result.append(", verification: ");
		result.append(verification);
		result.append(", callableElementBizKey: ");
		result.append(callableElementBizKey);
		result.append(", callableElementBizKeyName: ");
		result.append(callableElementBizKeyName);
		result.append(", isAsync: ");
		result.append(isAsync);
		result.append(", orderId: ");
		result.append(orderId);
		result.append(", isPersistence: ");
		result.append(isPersistence);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
