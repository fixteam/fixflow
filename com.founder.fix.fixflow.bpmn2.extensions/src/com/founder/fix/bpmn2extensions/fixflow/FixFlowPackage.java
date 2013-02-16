/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow;

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
 * <!-- begin-model-doc -->
 * 此XML Schema定义的BPMN 2.0的扩展元素是Founder公司拓展的属性。
 * 		
 * <!-- end-model-doc -->
 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory
 * @model kind="package"
 * @generated
 */
public interface FixFlowPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "fixflow";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.founderfix.com/fixflow";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "fixflow";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FixFlowPackage eINSTANCE = com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getDocumentRoot()
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
	 * The feature id for the '<em><b>Task Subject</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TASK_SUBJECT = 3;

	/**
	 * The feature id for the '<em><b>Task Command</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TASK_COMMAND = 4;

	/**
	 * The feature id for the '<em><b>Resource Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RESOURCE_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Resource Filter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RESOURCE_FILTER = 6;

	/**
	 * The feature id for the '<em><b>Data Variable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_VARIABLE = 7;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VERSION = 8;

	/**
	 * The feature id for the '<em><b>Dbid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DBID = 9;

	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CATEGORY = 10;

	/**
	 * The feature id for the '<em><b>Connector Instance</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONNECTOR_INSTANCE = 11;

	/**
	 * The feature id for the '<em><b>Loop Data Input Collection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION = 12;

	/**
	 * The feature id for the '<em><b>Loop Data Output Collection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION = 13;

	/**
	 * The feature id for the '<em><b>Form Uri</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FORM_URI = 14;

	/**
	 * The feature id for the '<em><b>Loop Maximum</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LOOP_MAXIMUM = 15;

	/**
	 * The feature id for the '<em><b>Sub Task</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SUB_TASK = 16;

	/**
	 * The feature id for the '<em><b>Script Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SCRIPT_NAME = 17;

	/**
	 * The feature id for the '<em><b>Result Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RESULT_VARIABLE = 18;

	/**
	 * The feature id for the '<em><b>Callable Element Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CALLABLE_ELEMENT_ID = 19;

	/**
	 * The feature id for the '<em><b>Callable Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CALLABLE_ELEMENT_NAME = 20;

	/**
	 * The feature id for the '<em><b>Callable Element Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION = 21;

	/**
	 * The feature id for the '<em><b>Callable Element Version Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION_NAME = 22;

	/**
	 * The feature id for the '<em><b>Include Exclusion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__INCLUDE_EXCLUSION = 23;

	/**
	 * The feature id for the '<em><b>Resource Range</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RESOURCE_RANGE = 24;

	/**
	 * The feature id for the '<em><b>Assign Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ASSIGN_ACTION = 25;

	/**
	 * The feature id for the '<em><b>Error Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ERROR_CODE = 26;

	/**
	 * The feature id for the '<em><b>Skip Strategy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SKIP_STRATEGY = 27;

	/**
	 * The feature id for the '<em><b>Is Contains Sub</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IS_CONTAINS_SUB = 28;

	/**
	 * The feature id for the '<em><b>Verification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VERIFICATION = 29;

	/**
	 * The feature id for the '<em><b>Message Obj</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MESSAGE_OBJ = 30;

	/**
	 * The feature id for the '<em><b>Receive Message</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RECEIVE_MESSAGE = 31;

	/**
	 * The feature id for the '<em><b>Expected Execution Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXPECTED_EXECUTION_TIME = 32;

	/**
	 * The feature id for the '<em><b>Data Source To Sub Process Mapping</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING = 33;

	/**
	 * The feature id for the '<em><b>Sub Process To Data Source Mapping</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING = 34;

	/**
	 * The feature id for the '<em><b>Callable Element Biz Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY = 35;

	/**
	 * The feature id for the '<em><b>Callable Element Biz Key Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY_NAME = 36;

	/**
	 * The feature id for the '<em><b>Is Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IS_ASYNC = 37;

	/**
	 * The feature id for the '<em><b>Form Uri View</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FORM_URI_VIEW = 38;

	/**
	 * The feature id for the '<em><b>Order Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ORDER_ID = 39;

	/**
	 * The feature id for the '<em><b>Task Priority</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TASK_PRIORITY = 40;

	/**
	 * The feature id for the '<em><b>Assign Policy Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ASSIGN_POLICY_TYPE = 41;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 42;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ExpressionImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__ID = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__NAME = 2;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskSubjectImpl <em>Task Subject</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.TaskSubjectImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getTaskSubject()
	 * @generated
	 */
	int TASK_SUBJECT = 2;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_SUBJECT__EXPRESSION = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_SUBJECT__ID = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_SUBJECT__NAME = 2;

	/**
	 * The number of structural features of the '<em>Task Subject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_SUBJECT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskCommandImpl <em>Task Command</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.TaskCommandImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getTaskCommand()
	 * @generated
	 */
	int TASK_COMMAND = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND__NAME = 1;

	/**
	 * The feature id for the '<em><b>Command Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND__COMMAND_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND__EXPRESSION = 3;

	/**
	 * The feature id for the '<em><b>Order Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND__ORDER_ID = 4;

	/**
	 * The number of structural features of the '<em>Task Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ResourceFilterImpl <em>Resource Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ResourceFilterImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getResourceFilter()
	 * @generated
	 */
	int RESOURCE_FILTER = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FILTER__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FILTER__NAME = 1;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FILTER__EXPRESSION = 2;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FILTER__DOCUMENTATION = 3;

	/**
	 * The number of structural features of the '<em>Resource Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FILTER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.DataVariableImpl <em>Data Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.DataVariableImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getDataVariable()
	 * @generated
	 */
	int DATA_VARIABLE = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE__ID = 0;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE__DATA_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Is List</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE__IS_LIST = 2;

	/**
	 * The feature id for the '<em><b>Is Persistence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE__IS_PERSISTENCE = 3;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE__EXPRESSION = 4;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE__DOCUMENTATION = 5;

	/**
	 * The number of structural features of the '<em>Data Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentationImpl <em>Documentation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.DocumentationImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getDocumentation()
	 * @generated
	 */
	int DOCUMENTATION = 6;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION__ID = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION__NAME = 2;

	/**
	 * The number of structural features of the '<em>Documentation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl <em>Connector Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getConnectorInstance()
	 * @generated
	 */
	int CONNECTOR_INSTANCE = 7;

	/**
	 * The feature id for the '<em><b>Connector Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_INSTANCE__CONNECTOR_ID = 0;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_INSTANCE__PACKAGE_NAME = 1;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_INSTANCE__CLASS_NAME = 2;

	/**
	 * The feature id for the '<em><b>Connector Instance Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_ID = 3;

	/**
	 * The feature id for the '<em><b>Connector Instance Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_NAME = 4;

	/**
	 * The feature id for the '<em><b>Event Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_INSTANCE__EVENT_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_INSTANCE__DOCUMENTATION = 6;

	/**
	 * The feature id for the '<em><b>Error Handling</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_INSTANCE__ERROR_HANDLING = 7;

	/**
	 * The feature id for the '<em><b>Error Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_INSTANCE__ERROR_CODE = 8;

	/**
	 * The feature id for the '<em><b>Connector Parameter Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_INPUTS = 9;

	/**
	 * The feature id for the '<em><b>Connector Parameter Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS = 10;

	/**
	 * The feature id for the '<em><b>Connector Parameter Outputs Def</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS_DEF = 11;

	/**
	 * The number of structural features of the '<em>Connector Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_INSTANCE_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorParameterInputsImpl <em>Connector Parameter Inputs</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorParameterInputsImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getConnectorParameterInputs()
	 * @generated
	 */
	int CONNECTOR_PARAMETER_INPUTS = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PARAMETER_INPUTS__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PARAMETER_INPUTS__NAME = 1;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PARAMETER_INPUTS__DATA_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PARAMETER_INPUTS__EXPRESSION = 3;

	/**
	 * The number of structural features of the '<em>Connector Parameter Inputs</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PARAMETER_INPUTS_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorParameterOutputsImpl <em>Connector Parameter Outputs</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorParameterOutputsImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getConnectorParameterOutputs()
	 * @generated
	 */
	int CONNECTOR_PARAMETER_OUTPUTS = 9;

	/**
	 * The feature id for the '<em><b>Variable Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PARAMETER_OUTPUTS__VARIABLE_TARGET = 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PARAMETER_OUTPUTS__EXPRESSION = 1;

	/**
	 * The number of structural features of the '<em>Connector Parameter Outputs</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PARAMETER_OUTPUTS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.LoopDataInputCollectionImpl <em>Loop Data Input Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.LoopDataInputCollectionImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getLoopDataInputCollection()
	 * @generated
	 */
	int LOOP_DATA_INPUT_COLLECTION = 10;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_DATA_INPUT_COLLECTION__EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Loop Data Input Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_DATA_INPUT_COLLECTION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.LoopDataOutputCollectionImpl <em>Loop Data Output Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.LoopDataOutputCollectionImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getLoopDataOutputCollection()
	 * @generated
	 */
	int LOOP_DATA_OUTPUT_COLLECTION = 11;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_DATA_OUTPUT_COLLECTION__EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Loop Data Output Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_DATA_OUTPUT_COLLECTION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorParameterOutputsDefImpl <em>Connector Parameter Outputs Def</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorParameterOutputsDefImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getConnectorParameterOutputsDef()
	 * @generated
	 */
	int CONNECTOR_PARAMETER_OUTPUTS_DEF = 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PARAMETER_OUTPUTS_DEF__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PARAMETER_OUTPUTS_DEF__NAME = 1;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PARAMETER_OUTPUTS_DEF__DATA_TYPE = 2;

	/**
	 * The number of structural features of the '<em>Connector Parameter Outputs Def</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PARAMETER_OUTPUTS_DEF_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.FormUriImpl <em>Form Uri</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FormUriImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getFormUri()
	 * @generated
	 */
	int FORM_URI = 13;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_URI__EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Form Uri</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_URI_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.LoopMaximumImpl <em>Loop Maximum</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.LoopMaximumImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getLoopMaximum()
	 * @generated
	 */
	int LOOP_MAXIMUM = 14;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_MAXIMUM__EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Loop Maximum</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_MAXIMUM_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.SkipStrategyImpl <em>Skip Strategy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.SkipStrategyImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getSkipStrategy()
	 * @generated
	 */
	int SKIP_STRATEGY = 15;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKIP_STRATEGY__EXPRESSION = 0;

	/**
	 * The feature id for the '<em><b>Is Create Skip Process</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKIP_STRATEGY__IS_CREATE_SKIP_PROCESS = 1;

	/**
	 * The feature id for the '<em><b>Skip Assignee</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKIP_STRATEGY__SKIP_ASSIGNEE = 2;

	/**
	 * The feature id for the '<em><b>Skip Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKIP_STRATEGY__SKIP_COMMENT = 3;

	/**
	 * The feature id for the '<em><b>Is Enable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKIP_STRATEGY__IS_ENABLE = 4;

	/**
	 * The number of structural features of the '<em>Skip Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKIP_STRATEGY_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.MessageObjImpl <em>Message Obj</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.MessageObjImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getMessageObj()
	 * @generated
	 */
	int MESSAGE_OBJ = 16;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_OBJ__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_OBJ__NAME = 1;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_OBJ__DOCUMENTATION = 2;

	/**
	 * The feature id for the '<em><b>Data Variable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_OBJ__DATA_VARIABLE = 3;

	/**
	 * The feature id for the '<em><b>Target Process</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_OBJ__TARGET_PROCESS = 4;

	/**
	 * The feature id for the '<em><b>Target Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_OBJ__TARGET_NODE = 5;

	/**
	 * The feature id for the '<em><b>Process Instance Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_OBJ__PROCESS_INSTANCE_VARIABLE = 6;

	/**
	 * The feature id for the '<em><b>Token Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_OBJ__TOKEN_VARIABLE = 7;

	/**
	 * The feature id for the '<em><b>Message Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_OBJ__MESSAGE_TYPE = 8;

	/**
	 * The number of structural features of the '<em>Message Obj</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_OBJ_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.FilterConditionsImpl <em>Filter Conditions</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FilterConditionsImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getFilterConditions()
	 * @generated
	 */
	int FILTER_CONDITIONS = 17;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_CONDITIONS__EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Filter Conditions</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_CONDITIONS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ReceiveMessageImpl <em>Receive Message</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ReceiveMessageImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getReceiveMessage()
	 * @generated
	 */
	int RECEIVE_MESSAGE = 18;

	/**
	 * The feature id for the '<em><b>Message Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE__MESSAGE_ID = 0;

	/**
	 * The feature id for the '<em><b>Filter Conditions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE__FILTER_CONDITIONS = 1;

	/**
	 * The feature id for the '<em><b>Process Instance Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE__PROCESS_INSTANCE_VARIABLE = 2;

	/**
	 * The feature id for the '<em><b>Token Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE__TOKEN_VARIABLE = 3;

	/**
	 * The number of structural features of the '<em>Receive Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.TokenVariableImpl <em>Token Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.TokenVariableImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getTokenVariable()
	 * @generated
	 */
	int TOKEN_VARIABLE = 19;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN_VARIABLE__EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Token Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN_VARIABLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ProcessInstanceVariableImpl <em>Process Instance Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ProcessInstanceVariableImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getProcessInstanceVariable()
	 * @generated
	 */
	int PROCESS_INSTANCE_VARIABLE = 20;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_INSTANCE_VARIABLE__EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Process Instance Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_INSTANCE_VARIABLE_FEATURE_COUNT = 1;


	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ExpectedExecutionTimeImpl <em>Expected Execution Time</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ExpectedExecutionTimeImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getExpectedExecutionTime()
	 * @generated
	 */
	int EXPECTED_EXECUTION_TIME = 21;

	/**
	 * The feature id for the '<em><b>Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_EXECUTION_TIME__DAY = 0;

	/**
	 * The feature id for the '<em><b>Hour</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_EXECUTION_TIME__HOUR = 1;

	/**
	 * The feature id for the '<em><b>Minute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_EXECUTION_TIME__MINUTE = 2;

	/**
	 * The feature id for the '<em><b>Second</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_EXECUTION_TIME__SECOND = 3;

	/**
	 * The number of structural features of the '<em>Expected Execution Time</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_EXECUTION_TIME_FEATURE_COUNT = 4;


	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.DataSourceToSubProcessMappingImpl <em>Data Source To Sub Process Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.DataSourceToSubProcessMappingImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getDataSourceToSubProcessMapping()
	 * @generated
	 */
	int DATA_SOURCE_TO_SUB_PROCESS_MAPPING = 22;

	/**
	 * The feature id for the '<em><b>Data Variable Mapping</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SOURCE_TO_SUB_PROCESS_MAPPING__DATA_VARIABLE_MAPPING = 0;

	/**
	 * The number of structural features of the '<em>Data Source To Sub Process Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SOURCE_TO_SUB_PROCESS_MAPPING_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.SubProcessToDataSourceMappingImpl <em>Sub Process To Data Source Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.SubProcessToDataSourceMappingImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getSubProcessToDataSourceMapping()
	 * @generated
	 */
	int SUB_PROCESS_TO_DATA_SOURCE_MAPPING = 23;

	/**
	 * The feature id for the '<em><b>Data Variable Mapping</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TO_DATA_SOURCE_MAPPING__DATA_VARIABLE_MAPPING = 0;

	/**
	 * The number of structural features of the '<em>Sub Process To Data Source Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TO_DATA_SOURCE_MAPPING_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.DataVariableMappingImpl <em>Data Variable Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.DataVariableMappingImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getDataVariableMapping()
	 * @generated
	 */
	int DATA_VARIABLE_MAPPING = 24;

	/**
	 * The feature id for the '<em><b>Data Source Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_MAPPING__DATA_SOURCE_ID = 0;

	/**
	 * The feature id for the '<em><b>Sub Proces Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_MAPPING__SUB_PROCES_ID = 1;

	/**
	 * The number of structural features of the '<em>Data Variable Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_MAPPING_FEATURE_COUNT = 2;


	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.FormUriViewImpl <em>Form Uri View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FormUriViewImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getFormUriView()
	 * @generated
	 */
	int FORM_URI_VIEW = 25;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_URI_VIEW__EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Form Uri View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_URI_VIEW_FEATURE_COUNT = 1;


	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskPriorityImpl <em>Task Priority</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.TaskPriorityImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getTaskPriority()
	 * @generated
	 */
	int TASK_PRIORITY = 26;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_PRIORITY__EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Task Priority</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_PRIORITY_FEATURE_COUNT = 1;


	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.AssignPolicyTypeImpl <em>Assign Policy Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.AssignPolicyTypeImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getAssignPolicyType()
	 * @generated
	 */
	int ASSIGN_POLICY_TYPE = 27;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN_POLICY_TYPE__ID = 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN_POLICY_TYPE__EXPRESSION = 1;

	/**
	 * The number of structural features of the '<em>Assign Policy Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN_POLICY_TYPE_FEATURE_COUNT = 2;


	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.SkipAssigneeImpl <em>Skip Assignee</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.SkipAssigneeImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getSkipAssignee()
	 * @generated
	 */
	int SKIP_ASSIGNEE = 28;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKIP_ASSIGNEE__EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Skip Assignee</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKIP_ASSIGNEE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.SkipCommentImpl <em>Skip Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.SkipCommentImpl
	 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getSkipComment()
	 * @generated
	 */
	int SKIP_COMMENT = 29;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKIP_COMMENT__EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Skip Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKIP_COMMENT_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getTaskSubject <em>Task Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Task Subject</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getTaskSubject()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TaskSubject();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getTaskCommand <em>Task Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Task Command</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getTaskCommand()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TaskCommand();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResourceType <em>Resource Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResourceType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_ResourceType();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResourceFilter <em>Resource Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Filter</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResourceFilter()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ResourceFilter();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getDataVariable <em>Data Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Variable</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getDataVariable()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataVariable();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getVersion()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Version();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getDbid <em>Dbid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dbid</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getDbid()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Dbid();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCategory <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Category</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCategory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Category();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getConnectorInstance <em>Connector Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connector Instance</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getConnectorInstance()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ConnectorInstance();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLoopDataInputCollection <em>Loop Data Input Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Loop Data Input Collection</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLoopDataInputCollection()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_LoopDataInputCollection();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLoopDataOutputCollection <em>Loop Data Output Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Loop Data Output Collection</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLoopDataOutputCollection()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_LoopDataOutputCollection();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getFormUri <em>Form Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Form Uri</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getFormUri()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FormUri();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLoopMaximum <em>Loop Maximum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Loop Maximum</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getLoopMaximum()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_LoopMaximum();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getSubTask <em>Sub Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sub Task</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getSubTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_SubTask();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getScriptName <em>Script Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Script Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getScriptName()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_ScriptName();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResultVariable <em>Result Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result Variable</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResultVariable()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_ResultVariable();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementId <em>Callable Element Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Callable Element Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementId()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_CallableElementId();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementName <em>Callable Element Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Callable Element Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementName()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_CallableElementName();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementVersion <em>Callable Element Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Callable Element Version</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementVersion()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_CallableElementVersion();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementVersionName <em>Callable Element Version Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Callable Element Version Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementVersionName()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_CallableElementVersionName();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getIncludeExclusion <em>Include Exclusion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Include Exclusion</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getIncludeExclusion()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_IncludeExclusion();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResourceRange <em>Resource Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Range</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getResourceRange()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_ResourceRange();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getAssignAction <em>Assign Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Assign Action</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getAssignAction()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_AssignAction();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getErrorCode <em>Error Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error Code</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getErrorCode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_ErrorCode();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getSkipStrategy <em>Skip Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Skip Strategy</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getSkipStrategy()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SkipStrategy();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#isIsContainsSub <em>Is Contains Sub</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Contains Sub</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#isIsContainsSub()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_IsContainsSub();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getVerification <em>Verification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Verification</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getVerification()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Verification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getMessageObj <em>Message Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Obj</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getMessageObj()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MessageObj();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getReceiveMessage <em>Receive Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Receive Message</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getReceiveMessage()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ReceiveMessage();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getExpectedExecutionTime <em>Expected Execution Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expected Execution Time</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getExpectedExecutionTime()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExpectedExecutionTime();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getDataSourceToSubProcessMapping <em>Data Source To Sub Process Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Source To Sub Process Mapping</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getDataSourceToSubProcessMapping()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataSourceToSubProcessMapping();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getSubProcessToDataSourceMapping <em>Sub Process To Data Source Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sub Process To Data Source Mapping</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getSubProcessToDataSourceMapping()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SubProcessToDataSourceMapping();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementBizKey <em>Callable Element Biz Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Callable Element Biz Key</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementBizKey()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_CallableElementBizKey();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementBizKeyName <em>Callable Element Biz Key Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Callable Element Biz Key Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getCallableElementBizKeyName()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_CallableElementBizKeyName();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getIsAsync <em>Is Async</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Async</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getIsAsync()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_IsAsync();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getFormUriView <em>Form Uri View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Form Uri View</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getFormUriView()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FormUriView();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getOrderId <em>Order Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Order Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getOrderId()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_OrderId();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getTaskPriority <em>Task Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Task Priority</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getTaskPriority()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TaskPriority();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getAssignPolicyType <em>Assign Policy Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Assign Policy Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot#getAssignPolicyType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_AssignPolicyType();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.Expression#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.Expression#getValue()
	 * @see #getExpression()
	 * @generated
	 */
	EAttribute getExpression_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.Expression#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.Expression#getId()
	 * @see #getExpression()
	 * @generated
	 */
	EAttribute getExpression_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.Expression#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.Expression#getName()
	 * @see #getExpression()
	 * @generated
	 */
	EAttribute getExpression_Name();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.TaskSubject <em>Task Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Subject</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskSubject
	 * @generated
	 */
	EClass getTaskSubject();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.TaskSubject#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskSubject#getExpression()
	 * @see #getTaskSubject()
	 * @generated
	 */
	EReference getTaskSubject_Expression();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.TaskSubject#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskSubject#getId()
	 * @see #getTaskSubject()
	 * @generated
	 */
	EAttribute getTaskSubject_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.TaskSubject#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskSubject#getName()
	 * @see #getTaskSubject()
	 * @generated
	 */
	EAttribute getTaskSubject_Name();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand <em>Task Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Command</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskCommand
	 * @generated
	 */
	EClass getTaskCommand();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getId()
	 * @see #getTaskCommand()
	 * @generated
	 */
	EAttribute getTaskCommand_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getName()
	 * @see #getTaskCommand()
	 * @generated
	 */
	EAttribute getTaskCommand_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getCommandType <em>Command Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Command Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getCommandType()
	 * @see #getTaskCommand()
	 * @generated
	 */
	EAttribute getTaskCommand_CommandType();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getExpression()
	 * @see #getTaskCommand()
	 * @generated
	 */
	EReference getTaskCommand_Expression();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getOrderId <em>Order Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Order Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getOrderId()
	 * @see #getTaskCommand()
	 * @generated
	 */
	EAttribute getTaskCommand_OrderId();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.ResourceFilter <em>Resource Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Filter</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ResourceFilter
	 * @generated
	 */
	EClass getResourceFilter();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ResourceFilter#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ResourceFilter#getId()
	 * @see #getResourceFilter()
	 * @generated
	 */
	EAttribute getResourceFilter_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ResourceFilter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ResourceFilter#getName()
	 * @see #getResourceFilter()
	 * @generated
	 */
	EAttribute getResourceFilter_Name();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.ResourceFilter#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ResourceFilter#getExpression()
	 * @see #getResourceFilter()
	 * @generated
	 */
	EReference getResourceFilter_Expression();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.fixflow.ResourceFilter#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Documentation</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ResourceFilter#getDocumentation()
	 * @see #getResourceFilter()
	 * @generated
	 */
	EReference getResourceFilter_Documentation();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable <em>Data Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Variable</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataVariable
	 * @generated
	 */
	EClass getDataVariable();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataVariable#getId()
	 * @see #getDataVariable()
	 * @generated
	 */
	EAttribute getDataVariable_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataVariable#getDataType()
	 * @see #getDataVariable()
	 * @generated
	 */
	EAttribute getDataVariable_DataType();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#isIsList <em>Is List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is List</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataVariable#isIsList()
	 * @see #getDataVariable()
	 * @generated
	 */
	EAttribute getDataVariable_IsList();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#isIsPersistence <em>Is Persistence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Persistence</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataVariable#isIsPersistence()
	 * @see #getDataVariable()
	 * @generated
	 */
	EAttribute getDataVariable_IsPersistence();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataVariable#getExpression()
	 * @see #getDataVariable()
	 * @generated
	 */
	EReference getDataVariable_Expression();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Documentation</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataVariable#getDocumentation()
	 * @see #getDataVariable()
	 * @generated
	 */
	EReference getDataVariable_Documentation();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.Documentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documentation</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.Documentation
	 * @generated
	 */
	EClass getDocumentation();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.Documentation#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.Documentation#getValue()
	 * @see #getDocumentation()
	 * @generated
	 */
	EAttribute getDocumentation_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.Documentation#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.Documentation#getId()
	 * @see #getDocumentation()
	 * @generated
	 */
	EAttribute getDocumentation_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.Documentation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.Documentation#getName()
	 * @see #getDocumentation()
	 * @generated
	 */
	EAttribute getDocumentation_Name();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance <em>Connector Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Instance</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance
	 * @generated
	 */
	EClass getConnectorInstance();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorId <em>Connector Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connector Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorId()
	 * @see #getConnectorInstance()
	 * @generated
	 */
	EAttribute getConnectorInstance_ConnectorId();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getPackageName <em>Package Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getPackageName()
	 * @see #getConnectorInstance()
	 * @generated
	 */
	EAttribute getConnectorInstance_PackageName();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getClassName()
	 * @see #getConnectorInstance()
	 * @generated
	 */
	EAttribute getConnectorInstance_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorInstanceId <em>Connector Instance Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connector Instance Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorInstanceId()
	 * @see #getConnectorInstance()
	 * @generated
	 */
	EAttribute getConnectorInstance_ConnectorInstanceId();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorInstanceName <em>Connector Instance Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connector Instance Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorInstanceName()
	 * @see #getConnectorInstance()
	 * @generated
	 */
	EAttribute getConnectorInstance_ConnectorInstanceName();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getEventType <em>Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Event Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getEventType()
	 * @see #getConnectorInstance()
	 * @generated
	 */
	EAttribute getConnectorInstance_EventType();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Documentation</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getDocumentation()
	 * @see #getConnectorInstance()
	 * @generated
	 */
	EReference getConnectorInstance_Documentation();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getErrorHandling <em>Error Handling</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error Handling</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getErrorHandling()
	 * @see #getConnectorInstance()
	 * @generated
	 */
	EAttribute getConnectorInstance_ErrorHandling();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getErrorCode <em>Error Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error Code</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getErrorCode()
	 * @see #getConnectorInstance()
	 * @generated
	 */
	EAttribute getConnectorInstance_ErrorCode();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorParameterInputs <em>Connector Parameter Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connector Parameter Inputs</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorParameterInputs()
	 * @see #getConnectorInstance()
	 * @generated
	 */
	EReference getConnectorInstance_ConnectorParameterInputs();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorParameterOutputs <em>Connector Parameter Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connector Parameter Outputs</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorParameterOutputs()
	 * @see #getConnectorInstance()
	 * @generated
	 */
	EReference getConnectorInstance_ConnectorParameterOutputs();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorParameterOutputsDef <em>Connector Parameter Outputs Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connector Parameter Outputs Def</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance#getConnectorParameterOutputsDef()
	 * @see #getConnectorInstance()
	 * @generated
	 */
	EReference getConnectorInstance_ConnectorParameterOutputsDef();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs <em>Connector Parameter Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Parameter Inputs</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs
	 * @generated
	 */
	EClass getConnectorParameterInputs();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs#getId()
	 * @see #getConnectorParameterInputs()
	 * @generated
	 */
	EAttribute getConnectorParameterInputs_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs#getName()
	 * @see #getConnectorParameterInputs()
	 * @generated
	 */
	EAttribute getConnectorParameterInputs_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs#getDataType()
	 * @see #getConnectorParameterInputs()
	 * @generated
	 */
	EAttribute getConnectorParameterInputs_DataType();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs#getExpression()
	 * @see #getConnectorParameterInputs()
	 * @generated
	 */
	EReference getConnectorParameterInputs_Expression();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs <em>Connector Parameter Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Parameter Outputs</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs
	 * @generated
	 */
	EClass getConnectorParameterOutputs();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs#getVariableTarget <em>Variable Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variable Target</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs#getVariableTarget()
	 * @see #getConnectorParameterOutputs()
	 * @generated
	 */
	EAttribute getConnectorParameterOutputs_VariableTarget();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs#getExpression()
	 * @see #getConnectorParameterOutputs()
	 * @generated
	 */
	EReference getConnectorParameterOutputs_Expression();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.LoopDataInputCollection <em>Loop Data Input Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Loop Data Input Collection</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.LoopDataInputCollection
	 * @generated
	 */
	EClass getLoopDataInputCollection();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.LoopDataInputCollection#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.LoopDataInputCollection#getExpression()
	 * @see #getLoopDataInputCollection()
	 * @generated
	 */
	EReference getLoopDataInputCollection_Expression();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.LoopDataOutputCollection <em>Loop Data Output Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Loop Data Output Collection</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.LoopDataOutputCollection
	 * @generated
	 */
	EClass getLoopDataOutputCollection();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.LoopDataOutputCollection#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.LoopDataOutputCollection#getExpression()
	 * @see #getLoopDataOutputCollection()
	 * @generated
	 */
	EReference getLoopDataOutputCollection_Expression();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef <em>Connector Parameter Outputs Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Parameter Outputs Def</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef
	 * @generated
	 */
	EClass getConnectorParameterOutputsDef();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef#getId()
	 * @see #getConnectorParameterOutputsDef()
	 * @generated
	 */
	EAttribute getConnectorParameterOutputsDef_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef#getName()
	 * @see #getConnectorParameterOutputsDef()
	 * @generated
	 */
	EAttribute getConnectorParameterOutputsDef_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef#getDataType()
	 * @see #getConnectorParameterOutputsDef()
	 * @generated
	 */
	EAttribute getConnectorParameterOutputsDef_DataType();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.FormUri <em>Form Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Uri</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FormUri
	 * @generated
	 */
	EClass getFormUri();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.FormUri#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FormUri#getExpression()
	 * @see #getFormUri()
	 * @generated
	 */
	EReference getFormUri_Expression();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.LoopMaximum <em>Loop Maximum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Loop Maximum</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.LoopMaximum
	 * @generated
	 */
	EClass getLoopMaximum();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.LoopMaximum#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.LoopMaximum#getExpression()
	 * @see #getLoopMaximum()
	 * @generated
	 */
	EReference getLoopMaximum_Expression();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy <em>Skip Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Skip Strategy</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SkipStrategy
	 * @generated
	 */
	EClass getSkipStrategy();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#getExpression()
	 * @see #getSkipStrategy()
	 * @generated
	 */
	EReference getSkipStrategy_Expression();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#isIsCreateSkipProcess <em>Is Create Skip Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Create Skip Process</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#isIsCreateSkipProcess()
	 * @see #getSkipStrategy()
	 * @generated
	 */
	EAttribute getSkipStrategy_IsCreateSkipProcess();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#getSkipAssignee <em>Skip Assignee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Skip Assignee</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#getSkipAssignee()
	 * @see #getSkipStrategy()
	 * @generated
	 */
	EReference getSkipStrategy_SkipAssignee();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#getSkipComment <em>Skip Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Skip Comment</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#getSkipComment()
	 * @see #getSkipStrategy()
	 * @generated
	 */
	EReference getSkipStrategy_SkipComment();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#isIsEnable <em>Is Enable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Enable</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SkipStrategy#isIsEnable()
	 * @see #getSkipStrategy()
	 * @generated
	 */
	EAttribute getSkipStrategy_IsEnable();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj <em>Message Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Obj</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.MessageObj
	 * @generated
	 */
	EClass getMessageObj();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.MessageObj#getId()
	 * @see #getMessageObj()
	 * @generated
	 */
	EAttribute getMessageObj_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.MessageObj#getName()
	 * @see #getMessageObj()
	 * @generated
	 */
	EAttribute getMessageObj_Name();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Documentation</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.MessageObj#getDocumentation()
	 * @see #getMessageObj()
	 * @generated
	 */
	EReference getMessageObj_Documentation();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getDataVariable <em>Data Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Variable</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.MessageObj#getDataVariable()
	 * @see #getMessageObj()
	 * @generated
	 */
	EReference getMessageObj_DataVariable();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getTargetProcess <em>Target Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Process</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.MessageObj#getTargetProcess()
	 * @see #getMessageObj()
	 * @generated
	 */
	EAttribute getMessageObj_TargetProcess();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getTargetNode <em>Target Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Node</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.MessageObj#getTargetNode()
	 * @see #getMessageObj()
	 * @generated
	 */
	EAttribute getMessageObj_TargetNode();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getProcessInstanceVariable <em>Process Instance Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Process Instance Variable</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.MessageObj#getProcessInstanceVariable()
	 * @see #getMessageObj()
	 * @generated
	 */
	EReference getMessageObj_ProcessInstanceVariable();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getTokenVariable <em>Token Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Token Variable</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.MessageObj#getTokenVariable()
	 * @see #getMessageObj()
	 * @generated
	 */
	EReference getMessageObj_TokenVariable();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj#getMessageType <em>Message Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.MessageObj#getMessageType()
	 * @see #getMessageObj()
	 * @generated
	 */
	EAttribute getMessageObj_MessageType();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.FilterConditions <em>Filter Conditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter Conditions</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FilterConditions
	 * @generated
	 */
	EClass getFilterConditions();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.FilterConditions#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FilterConditions#getExpression()
	 * @see #getFilterConditions()
	 * @generated
	 */
	EReference getFilterConditions_Expression();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage <em>Receive Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Receive Message</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage
	 * @generated
	 */
	EClass getReceiveMessage();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage#getMessageId <em>Message Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage#getMessageId()
	 * @see #getReceiveMessage()
	 * @generated
	 */
	EAttribute getReceiveMessage_MessageId();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage#getFilterConditions <em>Filter Conditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Filter Conditions</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage#getFilterConditions()
	 * @see #getReceiveMessage()
	 * @generated
	 */
	EReference getReceiveMessage_FilterConditions();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage#getProcessInstanceVariable <em>Process Instance Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Process Instance Variable</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage#getProcessInstanceVariable()
	 * @see #getReceiveMessage()
	 * @generated
	 */
	EReference getReceiveMessage_ProcessInstanceVariable();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage#getTokenVariable <em>Token Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Token Variable</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage#getTokenVariable()
	 * @see #getReceiveMessage()
	 * @generated
	 */
	EReference getReceiveMessage_TokenVariable();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.TokenVariable <em>Token Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Token Variable</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TokenVariable
	 * @generated
	 */
	EClass getTokenVariable();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.TokenVariable#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TokenVariable#getExpression()
	 * @see #getTokenVariable()
	 * @generated
	 */
	EReference getTokenVariable_Expression();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.ProcessInstanceVariable <em>Process Instance Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Instance Variable</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ProcessInstanceVariable
	 * @generated
	 */
	EClass getProcessInstanceVariable();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.ProcessInstanceVariable#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ProcessInstanceVariable#getExpression()
	 * @see #getProcessInstanceVariable()
	 * @generated
	 */
	EReference getProcessInstanceVariable_Expression();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime <em>Expected Execution Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expected Execution Time</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime
	 * @generated
	 */
	EClass getExpectedExecutionTime();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime#getDay <em>Day</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Day</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime#getDay()
	 * @see #getExpectedExecutionTime()
	 * @generated
	 */
	EAttribute getExpectedExecutionTime_Day();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime#getHour <em>Hour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hour</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime#getHour()
	 * @see #getExpectedExecutionTime()
	 * @generated
	 */
	EAttribute getExpectedExecutionTime_Hour();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime#getMinute <em>Minute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minute</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime#getMinute()
	 * @see #getExpectedExecutionTime()
	 * @generated
	 */
	EAttribute getExpectedExecutionTime_Minute();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime#getSecond <em>Second</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Second</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime#getSecond()
	 * @see #getExpectedExecutionTime()
	 * @generated
	 */
	EAttribute getExpectedExecutionTime_Second();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.DataSourceToSubProcessMapping <em>Data Source To Sub Process Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Source To Sub Process Mapping</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataSourceToSubProcessMapping
	 * @generated
	 */
	EClass getDataSourceToSubProcessMapping();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.fixflow.DataSourceToSubProcessMapping#getDataVariableMapping <em>Data Variable Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Variable Mapping</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataSourceToSubProcessMapping#getDataVariableMapping()
	 * @see #getDataSourceToSubProcessMapping()
	 * @generated
	 */
	EReference getDataSourceToSubProcessMapping_DataVariableMapping();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.SubProcessToDataSourceMapping <em>Sub Process To Data Source Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sub Process To Data Source Mapping</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SubProcessToDataSourceMapping
	 * @generated
	 */
	EClass getSubProcessToDataSourceMapping();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.fixflow.SubProcessToDataSourceMapping#getDataVariableMapping <em>Data Variable Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Variable Mapping</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SubProcessToDataSourceMapping#getDataVariableMapping()
	 * @see #getSubProcessToDataSourceMapping()
	 * @generated
	 */
	EReference getSubProcessToDataSourceMapping_DataVariableMapping();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping <em>Data Variable Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Variable Mapping</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping
	 * @generated
	 */
	EClass getDataVariableMapping();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping#getDataSourceId <em>Data Source Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Source Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping#getDataSourceId()
	 * @see #getDataVariableMapping()
	 * @generated
	 */
	EAttribute getDataVariableMapping_DataSourceId();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping#getSubProcesId <em>Sub Proces Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sub Proces Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping#getSubProcesId()
	 * @see #getDataVariableMapping()
	 * @generated
	 */
	EAttribute getDataVariableMapping_SubProcesId();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.FormUriView <em>Form Uri View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Uri View</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FormUriView
	 * @generated
	 */
	EClass getFormUriView();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.FormUriView#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FormUriView#getExpression()
	 * @see #getFormUriView()
	 * @generated
	 */
	EReference getFormUriView_Expression();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.TaskPriority <em>Task Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Priority</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskPriority
	 * @generated
	 */
	EClass getTaskPriority();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.TaskPriority#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskPriority#getExpression()
	 * @see #getTaskPriority()
	 * @generated
	 */
	EReference getTaskPriority_Expression();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType <em>Assign Policy Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assign Policy Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType
	 * @generated
	 */
	EClass getAssignPolicyType();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType#getId()
	 * @see #getAssignPolicyType()
	 * @generated
	 */
	EAttribute getAssignPolicyType_Id();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType#getExpression()
	 * @see #getAssignPolicyType()
	 * @generated
	 */
	EReference getAssignPolicyType_Expression();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.SkipAssignee <em>Skip Assignee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Skip Assignee</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SkipAssignee
	 * @generated
	 */
	EClass getSkipAssignee();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.SkipAssignee#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SkipAssignee#getExpression()
	 * @see #getSkipAssignee()
	 * @generated
	 */
	EReference getSkipAssignee_Expression();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.fixflow.SkipComment <em>Skip Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Skip Comment</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SkipComment
	 * @generated
	 */
	EClass getSkipComment();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.fixflow.SkipComment#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SkipComment#getExpression()
	 * @see #getSkipComment()
	 * @generated
	 */
	EReference getSkipComment_Expression();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FixFlowFactory getFixFlowFactory();

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
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.DocumentRootImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getDocumentRoot()
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
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Task Subject</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TASK_SUBJECT = eINSTANCE.getDocumentRoot_TaskSubject();

		/**
		 * The meta object literal for the '<em><b>Task Command</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TASK_COMMAND = eINSTANCE.getDocumentRoot_TaskCommand();

		/**
		 * The meta object literal for the '<em><b>Resource Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__RESOURCE_TYPE = eINSTANCE.getDocumentRoot_ResourceType();

		/**
		 * The meta object literal for the '<em><b>Resource Filter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RESOURCE_FILTER = eINSTANCE.getDocumentRoot_ResourceFilter();

		/**
		 * The meta object literal for the '<em><b>Data Variable</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_VARIABLE = eINSTANCE.getDocumentRoot_DataVariable();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__VERSION = eINSTANCE.getDocumentRoot_Version();

		/**
		 * The meta object literal for the '<em><b>Dbid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__DBID = eINSTANCE.getDocumentRoot_Dbid();

		/**
		 * The meta object literal for the '<em><b>Category</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__CATEGORY = eINSTANCE.getDocumentRoot_Category();

		/**
		 * The meta object literal for the '<em><b>Connector Instance</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONNECTOR_INSTANCE = eINSTANCE.getDocumentRoot_ConnectorInstance();

		/**
		 * The meta object literal for the '<em><b>Loop Data Input Collection</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION = eINSTANCE.getDocumentRoot_LoopDataInputCollection();

		/**
		 * The meta object literal for the '<em><b>Loop Data Output Collection</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION = eINSTANCE.getDocumentRoot_LoopDataOutputCollection();

		/**
		 * The meta object literal for the '<em><b>Form Uri</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FORM_URI = eINSTANCE.getDocumentRoot_FormUri();

		/**
		 * The meta object literal for the '<em><b>Loop Maximum</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__LOOP_MAXIMUM = eINSTANCE.getDocumentRoot_LoopMaximum();

		/**
		 * The meta object literal for the '<em><b>Sub Task</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__SUB_TASK = eINSTANCE.getDocumentRoot_SubTask();

		/**
		 * The meta object literal for the '<em><b>Script Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__SCRIPT_NAME = eINSTANCE.getDocumentRoot_ScriptName();

		/**
		 * The meta object literal for the '<em><b>Result Variable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__RESULT_VARIABLE = eINSTANCE.getDocumentRoot_ResultVariable();

		/**
		 * The meta object literal for the '<em><b>Callable Element Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__CALLABLE_ELEMENT_ID = eINSTANCE.getDocumentRoot_CallableElementId();

		/**
		 * The meta object literal for the '<em><b>Callable Element Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__CALLABLE_ELEMENT_NAME = eINSTANCE.getDocumentRoot_CallableElementName();

		/**
		 * The meta object literal for the '<em><b>Callable Element Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION = eINSTANCE.getDocumentRoot_CallableElementVersion();

		/**
		 * The meta object literal for the '<em><b>Callable Element Version Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION_NAME = eINSTANCE.getDocumentRoot_CallableElementVersionName();

		/**
		 * The meta object literal for the '<em><b>Include Exclusion</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__INCLUDE_EXCLUSION = eINSTANCE.getDocumentRoot_IncludeExclusion();

		/**
		 * The meta object literal for the '<em><b>Resource Range</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__RESOURCE_RANGE = eINSTANCE.getDocumentRoot_ResourceRange();

		/**
		 * The meta object literal for the '<em><b>Assign Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__ASSIGN_ACTION = eINSTANCE.getDocumentRoot_AssignAction();

		/**
		 * The meta object literal for the '<em><b>Error Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__ERROR_CODE = eINSTANCE.getDocumentRoot_ErrorCode();

		/**
		 * The meta object literal for the '<em><b>Skip Strategy</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SKIP_STRATEGY = eINSTANCE.getDocumentRoot_SkipStrategy();

		/**
		 * The meta object literal for the '<em><b>Is Contains Sub</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__IS_CONTAINS_SUB = eINSTANCE.getDocumentRoot_IsContainsSub();

		/**
		 * The meta object literal for the '<em><b>Verification</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__VERIFICATION = eINSTANCE.getDocumentRoot_Verification();

		/**
		 * The meta object literal for the '<em><b>Message Obj</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MESSAGE_OBJ = eINSTANCE.getDocumentRoot_MessageObj();

		/**
		 * The meta object literal for the '<em><b>Receive Message</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RECEIVE_MESSAGE = eINSTANCE.getDocumentRoot_ReceiveMessage();

		/**
		 * The meta object literal for the '<em><b>Expected Execution Time</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXPECTED_EXECUTION_TIME = eINSTANCE.getDocumentRoot_ExpectedExecutionTime();

		/**
		 * The meta object literal for the '<em><b>Data Source To Sub Process Mapping</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING = eINSTANCE.getDocumentRoot_DataSourceToSubProcessMapping();

		/**
		 * The meta object literal for the '<em><b>Sub Process To Data Source Mapping</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING = eINSTANCE.getDocumentRoot_SubProcessToDataSourceMapping();

		/**
		 * The meta object literal for the '<em><b>Callable Element Biz Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY = eINSTANCE.getDocumentRoot_CallableElementBizKey();

		/**
		 * The meta object literal for the '<em><b>Callable Element Biz Key Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY_NAME = eINSTANCE.getDocumentRoot_CallableElementBizKeyName();

		/**
		 * The meta object literal for the '<em><b>Is Async</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__IS_ASYNC = eINSTANCE.getDocumentRoot_IsAsync();

		/**
		 * The meta object literal for the '<em><b>Form Uri View</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FORM_URI_VIEW = eINSTANCE.getDocumentRoot_FormUriView();

		/**
		 * The meta object literal for the '<em><b>Order Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__ORDER_ID = eINSTANCE.getDocumentRoot_OrderId();

		/**
		 * The meta object literal for the '<em><b>Task Priority</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TASK_PRIORITY = eINSTANCE.getDocumentRoot_TaskPriority();

		/**
		 * The meta object literal for the '<em><b>Assign Policy Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ASSIGN_POLICY_TYPE = eINSTANCE.getDocumentRoot_AssignPolicyType();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ExpressionImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPRESSION__VALUE = eINSTANCE.getExpression_Value();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPRESSION__ID = eINSTANCE.getExpression_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPRESSION__NAME = eINSTANCE.getExpression_Name();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskSubjectImpl <em>Task Subject</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.TaskSubjectImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getTaskSubject()
		 * @generated
		 */
		EClass TASK_SUBJECT = eINSTANCE.getTaskSubject();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_SUBJECT__EXPRESSION = eINSTANCE.getTaskSubject_Expression();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_SUBJECT__ID = eINSTANCE.getTaskSubject_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_SUBJECT__NAME = eINSTANCE.getTaskSubject_Name();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskCommandImpl <em>Task Command</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.TaskCommandImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getTaskCommand()
		 * @generated
		 */
		EClass TASK_COMMAND = eINSTANCE.getTaskCommand();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_COMMAND__ID = eINSTANCE.getTaskCommand_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_COMMAND__NAME = eINSTANCE.getTaskCommand_Name();

		/**
		 * The meta object literal for the '<em><b>Command Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_COMMAND__COMMAND_TYPE = eINSTANCE.getTaskCommand_CommandType();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_COMMAND__EXPRESSION = eINSTANCE.getTaskCommand_Expression();

		/**
		 * The meta object literal for the '<em><b>Order Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_COMMAND__ORDER_ID = eINSTANCE.getTaskCommand_OrderId();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ResourceFilterImpl <em>Resource Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ResourceFilterImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getResourceFilter()
		 * @generated
		 */
		EClass RESOURCE_FILTER = eINSTANCE.getResourceFilter();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_FILTER__ID = eINSTANCE.getResourceFilter_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_FILTER__NAME = eINSTANCE.getResourceFilter_Name();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_FILTER__EXPRESSION = eINSTANCE.getResourceFilter_Expression();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_FILTER__DOCUMENTATION = eINSTANCE.getResourceFilter_Documentation();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.DataVariableImpl <em>Data Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.DataVariableImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getDataVariable()
		 * @generated
		 */
		EClass DATA_VARIABLE = eINSTANCE.getDataVariable();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_VARIABLE__ID = eINSTANCE.getDataVariable_Id();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_VARIABLE__DATA_TYPE = eINSTANCE.getDataVariable_DataType();

		/**
		 * The meta object literal for the '<em><b>Is List</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_VARIABLE__IS_LIST = eINSTANCE.getDataVariable_IsList();

		/**
		 * The meta object literal for the '<em><b>Is Persistence</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_VARIABLE__IS_PERSISTENCE = eINSTANCE.getDataVariable_IsPersistence();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_VARIABLE__EXPRESSION = eINSTANCE.getDataVariable_Expression();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_VARIABLE__DOCUMENTATION = eINSTANCE.getDataVariable_Documentation();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.DocumentationImpl <em>Documentation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.DocumentationImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getDocumentation()
		 * @generated
		 */
		EClass DOCUMENTATION = eINSTANCE.getDocumentation();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENTATION__VALUE = eINSTANCE.getDocumentation_Value();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENTATION__ID = eINSTANCE.getDocumentation_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENTATION__NAME = eINSTANCE.getDocumentation_Name();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl <em>Connector Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getConnectorInstance()
		 * @generated
		 */
		EClass CONNECTOR_INSTANCE = eINSTANCE.getConnectorInstance();

		/**
		 * The meta object literal for the '<em><b>Connector Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_INSTANCE__CONNECTOR_ID = eINSTANCE.getConnectorInstance_ConnectorId();

		/**
		 * The meta object literal for the '<em><b>Package Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_INSTANCE__PACKAGE_NAME = eINSTANCE.getConnectorInstance_PackageName();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_INSTANCE__CLASS_NAME = eINSTANCE.getConnectorInstance_ClassName();

		/**
		 * The meta object literal for the '<em><b>Connector Instance Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_ID = eINSTANCE.getConnectorInstance_ConnectorInstanceId();

		/**
		 * The meta object literal for the '<em><b>Connector Instance Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_NAME = eINSTANCE.getConnectorInstance_ConnectorInstanceName();

		/**
		 * The meta object literal for the '<em><b>Event Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_INSTANCE__EVENT_TYPE = eINSTANCE.getConnectorInstance_EventType();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_INSTANCE__DOCUMENTATION = eINSTANCE.getConnectorInstance_Documentation();

		/**
		 * The meta object literal for the '<em><b>Error Handling</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_INSTANCE__ERROR_HANDLING = eINSTANCE.getConnectorInstance_ErrorHandling();

		/**
		 * The meta object literal for the '<em><b>Error Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_INSTANCE__ERROR_CODE = eINSTANCE.getConnectorInstance_ErrorCode();

		/**
		 * The meta object literal for the '<em><b>Connector Parameter Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_INPUTS = eINSTANCE.getConnectorInstance_ConnectorParameterInputs();

		/**
		 * The meta object literal for the '<em><b>Connector Parameter Outputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS = eINSTANCE.getConnectorInstance_ConnectorParameterOutputs();

		/**
		 * The meta object literal for the '<em><b>Connector Parameter Outputs Def</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS_DEF = eINSTANCE.getConnectorInstance_ConnectorParameterOutputsDef();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorParameterInputsImpl <em>Connector Parameter Inputs</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorParameterInputsImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getConnectorParameterInputs()
		 * @generated
		 */
		EClass CONNECTOR_PARAMETER_INPUTS = eINSTANCE.getConnectorParameterInputs();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PARAMETER_INPUTS__ID = eINSTANCE.getConnectorParameterInputs_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PARAMETER_INPUTS__NAME = eINSTANCE.getConnectorParameterInputs_Name();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PARAMETER_INPUTS__DATA_TYPE = eINSTANCE.getConnectorParameterInputs_DataType();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_PARAMETER_INPUTS__EXPRESSION = eINSTANCE.getConnectorParameterInputs_Expression();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorParameterOutputsImpl <em>Connector Parameter Outputs</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorParameterOutputsImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getConnectorParameterOutputs()
		 * @generated
		 */
		EClass CONNECTOR_PARAMETER_OUTPUTS = eINSTANCE.getConnectorParameterOutputs();

		/**
		 * The meta object literal for the '<em><b>Variable Target</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PARAMETER_OUTPUTS__VARIABLE_TARGET = eINSTANCE.getConnectorParameterOutputs_VariableTarget();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_PARAMETER_OUTPUTS__EXPRESSION = eINSTANCE.getConnectorParameterOutputs_Expression();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.LoopDataInputCollectionImpl <em>Loop Data Input Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.LoopDataInputCollectionImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getLoopDataInputCollection()
		 * @generated
		 */
		EClass LOOP_DATA_INPUT_COLLECTION = eINSTANCE.getLoopDataInputCollection();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOP_DATA_INPUT_COLLECTION__EXPRESSION = eINSTANCE.getLoopDataInputCollection_Expression();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.LoopDataOutputCollectionImpl <em>Loop Data Output Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.LoopDataOutputCollectionImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getLoopDataOutputCollection()
		 * @generated
		 */
		EClass LOOP_DATA_OUTPUT_COLLECTION = eINSTANCE.getLoopDataOutputCollection();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOP_DATA_OUTPUT_COLLECTION__EXPRESSION = eINSTANCE.getLoopDataOutputCollection_Expression();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorParameterOutputsDefImpl <em>Connector Parameter Outputs Def</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorParameterOutputsDefImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getConnectorParameterOutputsDef()
		 * @generated
		 */
		EClass CONNECTOR_PARAMETER_OUTPUTS_DEF = eINSTANCE.getConnectorParameterOutputsDef();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PARAMETER_OUTPUTS_DEF__ID = eINSTANCE.getConnectorParameterOutputsDef_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PARAMETER_OUTPUTS_DEF__NAME = eINSTANCE.getConnectorParameterOutputsDef_Name();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PARAMETER_OUTPUTS_DEF__DATA_TYPE = eINSTANCE.getConnectorParameterOutputsDef_DataType();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.FormUriImpl <em>Form Uri</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FormUriImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getFormUri()
		 * @generated
		 */
		EClass FORM_URI = eINSTANCE.getFormUri();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORM_URI__EXPRESSION = eINSTANCE.getFormUri_Expression();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.LoopMaximumImpl <em>Loop Maximum</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.LoopMaximumImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getLoopMaximum()
		 * @generated
		 */
		EClass LOOP_MAXIMUM = eINSTANCE.getLoopMaximum();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOP_MAXIMUM__EXPRESSION = eINSTANCE.getLoopMaximum_Expression();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.SkipStrategyImpl <em>Skip Strategy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.SkipStrategyImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getSkipStrategy()
		 * @generated
		 */
		EClass SKIP_STRATEGY = eINSTANCE.getSkipStrategy();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SKIP_STRATEGY__EXPRESSION = eINSTANCE.getSkipStrategy_Expression();

		/**
		 * The meta object literal for the '<em><b>Is Create Skip Process</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SKIP_STRATEGY__IS_CREATE_SKIP_PROCESS = eINSTANCE.getSkipStrategy_IsCreateSkipProcess();

		/**
		 * The meta object literal for the '<em><b>Skip Assignee</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SKIP_STRATEGY__SKIP_ASSIGNEE = eINSTANCE.getSkipStrategy_SkipAssignee();

		/**
		 * The meta object literal for the '<em><b>Skip Comment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SKIP_STRATEGY__SKIP_COMMENT = eINSTANCE.getSkipStrategy_SkipComment();

		/**
		 * The meta object literal for the '<em><b>Is Enable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SKIP_STRATEGY__IS_ENABLE = eINSTANCE.getSkipStrategy_IsEnable();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.MessageObjImpl <em>Message Obj</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.MessageObjImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getMessageObj()
		 * @generated
		 */
		EClass MESSAGE_OBJ = eINSTANCE.getMessageObj();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_OBJ__ID = eINSTANCE.getMessageObj_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_OBJ__NAME = eINSTANCE.getMessageObj_Name();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_OBJ__DOCUMENTATION = eINSTANCE.getMessageObj_Documentation();

		/**
		 * The meta object literal for the '<em><b>Data Variable</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_OBJ__DATA_VARIABLE = eINSTANCE.getMessageObj_DataVariable();

		/**
		 * The meta object literal for the '<em><b>Target Process</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_OBJ__TARGET_PROCESS = eINSTANCE.getMessageObj_TargetProcess();

		/**
		 * The meta object literal for the '<em><b>Target Node</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_OBJ__TARGET_NODE = eINSTANCE.getMessageObj_TargetNode();

		/**
		 * The meta object literal for the '<em><b>Process Instance Variable</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_OBJ__PROCESS_INSTANCE_VARIABLE = eINSTANCE.getMessageObj_ProcessInstanceVariable();

		/**
		 * The meta object literal for the '<em><b>Token Variable</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_OBJ__TOKEN_VARIABLE = eINSTANCE.getMessageObj_TokenVariable();

		/**
		 * The meta object literal for the '<em><b>Message Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_OBJ__MESSAGE_TYPE = eINSTANCE.getMessageObj_MessageType();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.FilterConditionsImpl <em>Filter Conditions</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FilterConditionsImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getFilterConditions()
		 * @generated
		 */
		EClass FILTER_CONDITIONS = eINSTANCE.getFilterConditions();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_CONDITIONS__EXPRESSION = eINSTANCE.getFilterConditions_Expression();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ReceiveMessageImpl <em>Receive Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ReceiveMessageImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getReceiveMessage()
		 * @generated
		 */
		EClass RECEIVE_MESSAGE = eINSTANCE.getReceiveMessage();

		/**
		 * The meta object literal for the '<em><b>Message Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECEIVE_MESSAGE__MESSAGE_ID = eINSTANCE.getReceiveMessage_MessageId();

		/**
		 * The meta object literal for the '<em><b>Filter Conditions</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECEIVE_MESSAGE__FILTER_CONDITIONS = eINSTANCE.getReceiveMessage_FilterConditions();

		/**
		 * The meta object literal for the '<em><b>Process Instance Variable</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECEIVE_MESSAGE__PROCESS_INSTANCE_VARIABLE = eINSTANCE.getReceiveMessage_ProcessInstanceVariable();

		/**
		 * The meta object literal for the '<em><b>Token Variable</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECEIVE_MESSAGE__TOKEN_VARIABLE = eINSTANCE.getReceiveMessage_TokenVariable();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.TokenVariableImpl <em>Token Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.TokenVariableImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getTokenVariable()
		 * @generated
		 */
		EClass TOKEN_VARIABLE = eINSTANCE.getTokenVariable();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOKEN_VARIABLE__EXPRESSION = eINSTANCE.getTokenVariable_Expression();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ProcessInstanceVariableImpl <em>Process Instance Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ProcessInstanceVariableImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getProcessInstanceVariable()
		 * @generated
		 */
		EClass PROCESS_INSTANCE_VARIABLE = eINSTANCE.getProcessInstanceVariable();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_INSTANCE_VARIABLE__EXPRESSION = eINSTANCE.getProcessInstanceVariable_Expression();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.ExpectedExecutionTimeImpl <em>Expected Execution Time</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.ExpectedExecutionTimeImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getExpectedExecutionTime()
		 * @generated
		 */
		EClass EXPECTED_EXECUTION_TIME = eINSTANCE.getExpectedExecutionTime();

		/**
		 * The meta object literal for the '<em><b>Day</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPECTED_EXECUTION_TIME__DAY = eINSTANCE.getExpectedExecutionTime_Day();

		/**
		 * The meta object literal for the '<em><b>Hour</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPECTED_EXECUTION_TIME__HOUR = eINSTANCE.getExpectedExecutionTime_Hour();

		/**
		 * The meta object literal for the '<em><b>Minute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPECTED_EXECUTION_TIME__MINUTE = eINSTANCE.getExpectedExecutionTime_Minute();

		/**
		 * The meta object literal for the '<em><b>Second</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPECTED_EXECUTION_TIME__SECOND = eINSTANCE.getExpectedExecutionTime_Second();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.DataSourceToSubProcessMappingImpl <em>Data Source To Sub Process Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.DataSourceToSubProcessMappingImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getDataSourceToSubProcessMapping()
		 * @generated
		 */
		EClass DATA_SOURCE_TO_SUB_PROCESS_MAPPING = eINSTANCE.getDataSourceToSubProcessMapping();

		/**
		 * The meta object literal for the '<em><b>Data Variable Mapping</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_SOURCE_TO_SUB_PROCESS_MAPPING__DATA_VARIABLE_MAPPING = eINSTANCE.getDataSourceToSubProcessMapping_DataVariableMapping();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.SubProcessToDataSourceMappingImpl <em>Sub Process To Data Source Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.SubProcessToDataSourceMappingImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getSubProcessToDataSourceMapping()
		 * @generated
		 */
		EClass SUB_PROCESS_TO_DATA_SOURCE_MAPPING = eINSTANCE.getSubProcessToDataSourceMapping();

		/**
		 * The meta object literal for the '<em><b>Data Variable Mapping</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_PROCESS_TO_DATA_SOURCE_MAPPING__DATA_VARIABLE_MAPPING = eINSTANCE.getSubProcessToDataSourceMapping_DataVariableMapping();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.DataVariableMappingImpl <em>Data Variable Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.DataVariableMappingImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getDataVariableMapping()
		 * @generated
		 */
		EClass DATA_VARIABLE_MAPPING = eINSTANCE.getDataVariableMapping();

		/**
		 * The meta object literal for the '<em><b>Data Source Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_VARIABLE_MAPPING__DATA_SOURCE_ID = eINSTANCE.getDataVariableMapping_DataSourceId();

		/**
		 * The meta object literal for the '<em><b>Sub Proces Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_VARIABLE_MAPPING__SUB_PROCES_ID = eINSTANCE.getDataVariableMapping_SubProcesId();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.FormUriViewImpl <em>Form Uri View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FormUriViewImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getFormUriView()
		 * @generated
		 */
		EClass FORM_URI_VIEW = eINSTANCE.getFormUriView();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORM_URI_VIEW__EXPRESSION = eINSTANCE.getFormUriView_Expression();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskPriorityImpl <em>Task Priority</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.TaskPriorityImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getTaskPriority()
		 * @generated
		 */
		EClass TASK_PRIORITY = eINSTANCE.getTaskPriority();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_PRIORITY__EXPRESSION = eINSTANCE.getTaskPriority_Expression();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.AssignPolicyTypeImpl <em>Assign Policy Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.AssignPolicyTypeImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getAssignPolicyType()
		 * @generated
		 */
		EClass ASSIGN_POLICY_TYPE = eINSTANCE.getAssignPolicyType();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSIGN_POLICY_TYPE__ID = eINSTANCE.getAssignPolicyType_Id();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGN_POLICY_TYPE__EXPRESSION = eINSTANCE.getAssignPolicyType_Expression();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.SkipAssigneeImpl <em>Skip Assignee</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.SkipAssigneeImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getSkipAssignee()
		 * @generated
		 */
		EClass SKIP_ASSIGNEE = eINSTANCE.getSkipAssignee();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SKIP_ASSIGNEE__EXPRESSION = eINSTANCE.getSkipAssignee_Expression();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.fixflow.impl.SkipCommentImpl <em>Skip Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.SkipCommentImpl
		 * @see com.founder.fix.bpmn2extensions.fixflow.impl.FixFlowPackageImpl#getSkipComment()
		 * @generated
		 */
		EClass SKIP_COMMENT = eINSTANCE.getSkipComment();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SKIP_COMMENT__EXPRESSION = eINSTANCE.getSkipComment_Expression();

	}

} //FixFlowPackage
