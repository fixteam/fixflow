/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigFactory
 * @model kind="package"
 * @generated
 */
public interface CoreconfigPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "coreconfig";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.founderfix.com/coreconfig";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "coreconfig";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CoreconfigPackage eINSTANCE = com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl <em>Fix Flow Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getFixFlowConfig()
	 * @generated
	 */
	int FIX_FLOW_CONFIG = 0;

	/**
	 * The feature id for the '<em><b>Data Base Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__DATA_BASE_CONFIG = 0;

	/**
	 * The feature id for the '<em><b>Group Definition Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__GROUP_DEFINITION_CONFIG = 1;

	/**
	 * The feature id for the '<em><b>Task Command Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__TASK_COMMAND_CONFIG = 2;

	/**
	 * The feature id for the '<em><b>Designer Org Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG = 3;

	/**
	 * The feature id for the '<em><b>Sys Mail Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__SYS_MAIL_CONFIG = 4;

	/**
	 * The feature id for the '<em><b>Expand Class Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__EXPAND_CLASS_CONFIG = 5;

	/**
	 * The feature id for the '<em><b>Event Subscription Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG = 6;

	/**
	 * The feature id for the '<em><b>Quartz Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__QUARTZ_CONFIG = 7;

	/**
	 * The feature id for the '<em><b>Script Language Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__SCRIPT_LANGUAGE_CONFIG = 8;

	/**
	 * The feature id for the '<em><b>Internationalization Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__INTERNATIONALIZATION_CONFIG = 9;

	/**
	 * The feature id for the '<em><b>Pigeonhole Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG = 10;

	/**
	 * The feature id for the '<em><b>Expand Cmd Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__EXPAND_CMD_CONFIG = 11;

	/**
	 * The feature id for the '<em><b>Priority Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__PRIORITY_CONFIG = 12;

	/**
	 * The feature id for the '<em><b>Assign Policy Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG__ASSIGN_POLICY_CONFIG = 13;

	/**
	 * The number of structural features of the '<em>Fix Flow Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_CONFIG_FEATURE_COUNT = 14;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseConfigImpl <em>Data Base Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getDataBaseConfig()
	 * @generated
	 */
	int DATA_BASE_CONFIG = 1;

	/**
	 * The feature id for the '<em><b>Data Base</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE_CONFIG__DATA_BASE = 0;

	/**
	 * The feature id for the '<em><b>Selected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE_CONFIG__SELECTED = 1;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE_CONFIG__MODE = 2;

	/**
	 * The number of structural features of the '<em>Data Base Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE_CONFIG_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupDefinitionConfigImpl <em>Group Definition Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.GroupDefinitionConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getGroupDefinitionConfig()
	 * @generated
	 */
	int GROUP_DEFINITION_CONFIG = 2;

	/**
	 * The feature id for the '<em><b>Group Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_DEFINITION_CONFIG__GROUP_DEFINITION = 0;

	/**
	 * The number of structural features of the '<em>Group Definition Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_DEFINITION_CONFIG_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl <em>Data Base</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getDataBase()
	 * @generated
	 */
	int DATA_BASE = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Driver Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE__DRIVER_CLASS_NAME = 2;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE__URL = 3;

	/**
	 * The feature id for the '<em><b>Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE__USERNAME = 4;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE__PASSWORD = 5;

	/**
	 * The feature id for the '<em><b>Max Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE__MAX_ACTIVE = 6;

	/**
	 * The feature id for the '<em><b>Max Idle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE__MAX_IDLE = 7;

	/**
	 * The feature id for the '<em><b>Max Wait</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE__MAX_WAIT = 8;

	/**
	 * The feature id for the '<em><b>Pagination Impl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE__PAGINATION_IMPL = 9;

	/**
	 * The feature id for the '<em><b>Dbtype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE__DBTYPE = 10;

	/**
	 * The number of structural features of the '<em>Data Base</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BASE_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupDefinitionImpl <em>Group Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.GroupDefinitionImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getGroupDefinition()
	 * @generated
	 */
	int GROUP_DEFINITION = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_DEFINITION__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_DEFINITION__NAME = 1;

	/**
	 * The feature id for the '<em><b>Group Definition Impl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_DEFINITION__GROUP_DEFINITION_IMPL = 2;

	/**
	 * The number of structural features of the '<em>Group Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_DEFINITION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.TaskCommandConfigImpl <em>Task Command Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.TaskCommandConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getTaskCommandConfig()
	 * @generated
	 */
	int TASK_COMMAND_CONFIG = 5;

	/**
	 * The feature id for the '<em><b>Task Command Def</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND_CONFIG__TASK_COMMAND_DEF = 0;

	/**
	 * The number of structural features of the '<em>Task Command Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND_CONFIG_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.TaskCommandDefImpl <em>Task Command Def</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.TaskCommandDefImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getTaskCommandDef()
	 * @generated
	 */
	int TASK_COMMAND_DEF = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND_DEF__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND_DEF__NAME = 1;

	/**
	 * The feature id for the '<em><b>Command</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND_DEF__COMMAND = 2;

	/**
	 * The feature id for the '<em><b>Cmd</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND_DEF__CMD = 3;

	/**
	 * The feature id for the '<em><b>Is Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND_DEF__IS_ENABLED = 4;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND_DEF__TYPE = 5;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND_DEF__FILTER = 6;

	/**
	 * The number of structural features of the '<em>Task Command Def</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_COMMAND_DEF_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DesignerOrgConfigImpl <em>Designer Org Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.DesignerOrgConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getDesignerOrgConfig()
	 * @generated
	 */
	int DESIGNER_ORG_CONFIG = 7;

	/**
	 * The feature id for the '<em><b>All User Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESIGNER_ORG_CONFIG__ALL_USER_INFO = 0;

	/**
	 * The feature id for the '<em><b>Group Info</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESIGNER_ORG_CONFIG__GROUP_INFO = 1;

	/**
	 * The number of structural features of the '<em>Designer Org Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESIGNER_ORG_CONFIG_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AllUserInfoImpl <em>All User Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.AllUserInfoImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getAllUserInfo()
	 * @generated
	 */
	int ALL_USER_INFO = 8;

	/**
	 * The feature id for the '<em><b>Is Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_USER_INFO__IS_ENABLED = 0;

	/**
	 * The feature id for the '<em><b>User Id Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_USER_INFO__USER_ID_FIELD = 1;

	/**
	 * The feature id for the '<em><b>User Name Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_USER_INFO__USER_NAME_FIELD = 2;

	/**
	 * The feature id for the '<em><b>Sql Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_USER_INFO__SQL_TEXT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_USER_INFO__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_USER_INFO__NAME = 5;

	/**
	 * The feature id for the '<em><b>Data Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_USER_INFO__DATA_SOURCE = 6;

	/**
	 * The number of structural features of the '<em>All User Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_USER_INFO_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl <em>Group Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getGroupInfo()
	 * @generated
	 */
	int GROUP_INFO = 9;

	/**
	 * The feature id for the '<em><b>Group Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_INFO__GROUP_ID = 0;

	/**
	 * The feature id for the '<em><b>Group Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_INFO__GROUP_NAME = 1;

	/**
	 * The feature id for the '<em><b>Is Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_INFO__IS_ENABLED = 2;

	/**
	 * The feature id for the '<em><b>Is Display User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_INFO__IS_DISPLAY_USER = 3;

	/**
	 * The feature id for the '<em><b>Group Id Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_INFO__GROUP_ID_FIELD = 4;

	/**
	 * The feature id for the '<em><b>Group Name Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_INFO__GROUP_NAME_FIELD = 5;

	/**
	 * The feature id for the '<em><b>Sup Group Id Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_INFO__SUP_GROUP_ID_FIELD = 6;

	/**
	 * The feature id for the '<em><b>Sql Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_INFO__SQL_TEXT = 7;

	/**
	 * The feature id for the '<em><b>User Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_INFO__USER_INFO = 8;

	/**
	 * The feature id for the '<em><b>Data Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_INFO__DATA_SOURCE = 9;

	/**
	 * The feature id for the '<em><b>Group Definition Impl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_INFO__GROUP_DEFINITION_IMPL = 10;

	/**
	 * The number of structural features of the '<em>Group Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_INFO_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.UserInfoImpl <em>User Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.UserInfoImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getUserInfo()
	 * @generated
	 */
	int USER_INFO = 10;

	/**
	 * The feature id for the '<em><b>User Id Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INFO__USER_ID_FIELD = 0;

	/**
	 * The feature id for the '<em><b>User Name Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INFO__USER_NAME_FIELD = 1;

	/**
	 * The feature id for the '<em><b>Group Id Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INFO__GROUP_ID_FIELD = 2;

	/**
	 * The feature id for the '<em><b>Sql Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INFO__SQL_TEXT = 3;

	/**
	 * The feature id for the '<em><b>Data Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INFO__DATA_SOURCE = 4;

	/**
	 * The number of structural features of the '<em>User Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INFO_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.SysMailConfigImpl <em>Sys Mail Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.SysMailConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getSysMailConfig()
	 * @generated
	 */
	int SYS_MAIL_CONFIG = 11;

	/**
	 * The feature id for the '<em><b>Selected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYS_MAIL_CONFIG__SELECTED = 0;

	/**
	 * The feature id for the '<em><b>Mail Info</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYS_MAIL_CONFIG__MAIL_INFO = 1;

	/**
	 * The number of structural features of the '<em>Sys Mail Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYS_MAIL_CONFIG_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.MailInfoImpl <em>Mail Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.MailInfoImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getMailInfo()
	 * @generated
	 */
	int MAIL_INFO = 12;

	/**
	 * The feature id for the '<em><b>Mail Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_INFO__MAIL_ADDRESS = 0;

	/**
	 * The feature id for the '<em><b>Mail Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_INFO__MAIL_NAME = 1;

	/**
	 * The feature id for the '<em><b>Smtp Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_INFO__SMTP_HOST = 2;

	/**
	 * The feature id for the '<em><b>Smtp Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_INFO__SMTP_PORT = 3;

	/**
	 * The feature id for the '<em><b>User Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_INFO__USER_NAME = 4;

	/**
	 * The feature id for the '<em><b>Pass Word</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_INFO__PASS_WORD = 5;

	/**
	 * The number of structural features of the '<em>Mail Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_INFO_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandClassConfigImpl <em>Expand Class Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandClassConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getExpandClassConfig()
	 * @generated
	 */
	int EXPAND_CLASS_CONFIG = 13;

	/**
	 * The feature id for the '<em><b>Expand Class</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CLASS_CONFIG__EXPAND_CLASS = 0;

	/**
	 * The number of structural features of the '<em>Expand Class Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CLASS_CONFIG_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandClassImpl <em>Expand Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandClassImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getExpandClass()
	 * @generated
	 */
	int EXPAND_CLASS = 14;

	/**
	 * The feature id for the '<em><b>Class Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CLASS__CLASS_ID = 0;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CLASS__CLASS_NAME = 1;

	/**
	 * The feature id for the '<em><b>Class Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CLASS__CLASS_INTERFACE = 2;

	/**
	 * The feature id for the '<em><b>Class Impl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CLASS__CLASS_IMPL = 3;

	/**
	 * The feature id for the '<em><b>Remarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CLASS__REMARKS = 4;

	/**
	 * The number of structural features of the '<em>Expand Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CLASS_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.EventSubscriptionConfigImpl <em>Event Subscription Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.EventSubscriptionConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getEventSubscriptionConfig()
	 * @generated
	 */
	int EVENT_SUBSCRIPTION_CONFIG = 15;

	/**
	 * The feature id for the '<em><b>Server Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SUBSCRIPTION_CONFIG__SERVER_ADDRESS = 0;

	/**
	 * The feature id for the '<em><b>Server Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SUBSCRIPTION_CONFIG__SERVER_PORT = 1;

	/**
	 * The feature id for the '<em><b>Message Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SUBSCRIPTION_CONFIG__MESSAGE_INFO = 2;

	/**
	 * The feature id for the '<em><b>Signal Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SUBSCRIPTION_CONFIG__SIGNAL_INFO = 3;

	/**
	 * The feature id for the '<em><b>Is Enable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SUBSCRIPTION_CONFIG__IS_ENABLE = 4;

	/**
	 * The number of structural features of the '<em>Event Subscription Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_SUBSCRIPTION_CONFIG_FEATURE_COUNT = 5;


	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.QuartzConfigImpl <em>Quartz Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.QuartzConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getQuartzConfig()
	 * @generated
	 */
	int QUARTZ_CONFIG = 16;

	/**
	 * The feature id for the '<em><b>Is Enable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUARTZ_CONFIG__IS_ENABLE = 0;

	/**
	 * The feature id for the '<em><b>Is Default Config</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUARTZ_CONFIG__IS_DEFAULT_CONFIG = 1;

	/**
	 * The feature id for the '<em><b>Data Base Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUARTZ_CONFIG__DATA_BASE_ID = 2;

	/**
	 * The number of structural features of the '<em>Quartz Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUARTZ_CONFIG_FEATURE_COUNT = 3;


	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ScriptLanguageConfigImpl <em>Script Language Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.ScriptLanguageConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getScriptLanguageConfig()
	 * @generated
	 */
	int SCRIPT_LANGUAGE_CONFIG = 17;

	/**
	 * The feature id for the '<em><b>Selected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_LANGUAGE_CONFIG__SELECTED = 0;

	/**
	 * The feature id for the '<em><b>Script Language</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_LANGUAGE_CONFIG__SCRIPT_LANGUAGE = 1;

	/**
	 * The number of structural features of the '<em>Script Language Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_LANGUAGE_CONFIG_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ScriptLanguageImpl <em>Script Language</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.ScriptLanguageImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getScriptLanguage()
	 * @generated
	 */
	int SCRIPT_LANGUAGE = 18;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_LANGUAGE__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_LANGUAGE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Class Impl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_LANGUAGE__CLASS_IMPL = 2;

	/**
	 * The number of structural features of the '<em>Script Language</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_LANGUAGE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.InternationalizationConfigImpl <em>Internationalization Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.InternationalizationConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getInternationalizationConfig()
	 * @generated
	 */
	int INTERNATIONALIZATION_CONFIG = 19;

	/**
	 * The feature id for the '<em><b>Is Enable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNATIONALIZATION_CONFIG__IS_ENABLE = 0;

	/**
	 * The feature id for the '<em><b>Folder Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNATIONALIZATION_CONFIG__FOLDER_PATH = 1;

	/**
	 * The number of structural features of the '<em>Internationalization Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNATIONALIZATION_CONFIG_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PigeonholeConfigImpl <em>Pigeonhole Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.PigeonholeConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getPigeonholeConfig()
	 * @generated
	 */
	int PIGEONHOLE_CONFIG = 20;

	/**
	 * The feature id for the '<em><b>Server Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIGEONHOLE_CONFIG__SERVER_PATH = 0;

	/**
	 * The feature id for the '<em><b>Pdf Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIGEONHOLE_CONFIG__PDF_PATH = 1;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIGEONHOLE_CONFIG__TIME = 2;

	/**
	 * The feature id for the '<em><b>Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIGEONHOLE_CONFIG__FREQUENCY = 3;

	/**
	 * The feature id for the '<em><b>Week</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIGEONHOLE_CONFIG__WEEK = 4;

	/**
	 * The feature id for the '<em><b>Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIGEONHOLE_CONFIG__MONTH = 5;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIGEONHOLE_CONFIG__CODE = 6;

	/**
	 * The feature id for the '<em><b>Is Enable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIGEONHOLE_CONFIG__IS_ENABLE = 7;

	/**
	 * The number of structural features of the '<em>Pigeonhole Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIGEONHOLE_CONFIG_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandCmdConfigImpl <em>Expand Cmd Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandCmdConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getExpandCmdConfig()
	 * @generated
	 */
	int EXPAND_CMD_CONFIG = 21;

	/**
	 * The feature id for the '<em><b>Expand Cmd</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CMD_CONFIG__EXPAND_CMD = 0;

	/**
	 * The number of structural features of the '<em>Expand Cmd Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CMD_CONFIG_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandCmdImpl <em>Expand Cmd</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandCmdImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getExpandCmd()
	 * @generated
	 */
	int EXPAND_CMD = 22;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CMD__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CMD__NAME = 1;

	/**
	 * The feature id for the '<em><b>Cmd</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CMD__CMD = 2;

	/**
	 * The number of structural features of the '<em>Expand Cmd</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_CMD_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PriorityConfigImpl <em>Priority Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.PriorityConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getPriorityConfig()
	 * @generated
	 */
	int PRIORITY_CONFIG = 23;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORITY_CONFIG__PRIORITY = 0;

	/**
	 * The number of structural features of the '<em>Priority Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORITY_CONFIG_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PriorityImpl <em>Priority</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.PriorityImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getPriority()
	 * @generated
	 */
	int PRIORITY = 24;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORITY__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORITY__NAME = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORITY__VALUE = 2;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORITY__COLOR = 3;

	/**
	 * The feature id for the '<em><b>Remark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORITY__REMARK = 4;

	/**
	 * The number of structural features of the '<em>Priority</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORITY_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AssignPolicyConfigImpl <em>Assign Policy Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.AssignPolicyConfigImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getAssignPolicyConfig()
	 * @generated
	 */
	int ASSIGN_POLICY_CONFIG = 25;

	/**
	 * The feature id for the '<em><b>Assign Policy</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN_POLICY_CONFIG__ASSIGN_POLICY = 0;

	/**
	 * The number of structural features of the '<em>Assign Policy Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN_POLICY_CONFIG_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AssignPolicyImpl <em>Assign Policy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.AssignPolicyImpl
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getAssignPolicy()
	 * @generated
	 */
	int ASSIGN_POLICY = 26;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN_POLICY__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN_POLICY__NAME = 1;

	/**
	 * The feature id for the '<em><b>Class Impl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN_POLICY__CLASS_IMPL = 2;

	/**
	 * The feature id for the '<em><b>Remarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN_POLICY__REMARKS = 3;

	/**
	 * The number of structural features of the '<em>Assign Policy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN_POLICY_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.coreconfig.DBType <em>DB Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DBType
	 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getDBType()
	 * @generated
	 */
	int DB_TYPE = 27;


	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig <em>Fix Flow Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fix Flow Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig
	 * @generated
	 */
	EClass getFixFlowConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getDataBaseConfig <em>Data Base Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Base Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getDataBaseConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_DataBaseConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getGroupDefinitionConfig <em>Group Definition Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Group Definition Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getGroupDefinitionConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_GroupDefinitionConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getTaskCommandConfig <em>Task Command Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Task Command Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getTaskCommandConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_TaskCommandConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getDesignerOrgConfig <em>Designer Org Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Designer Org Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getDesignerOrgConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_DesignerOrgConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getSysMailConfig <em>Sys Mail Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sys Mail Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getSysMailConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_SysMailConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getExpandClassConfig <em>Expand Class Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expand Class Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getExpandClassConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_ExpandClassConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getEventSubscriptionConfig <em>Event Subscription Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event Subscription Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getEventSubscriptionConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_EventSubscriptionConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getQuartzConfig <em>Quartz Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Quartz Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getQuartzConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_QuartzConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getScriptLanguageConfig <em>Script Language Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script Language Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getScriptLanguageConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_ScriptLanguageConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getInternationalizationConfig <em>Internationalization Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Internationalization Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getInternationalizationConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_InternationalizationConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getPigeonholeConfig <em>Pigeonhole Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pigeonhole Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getPigeonholeConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_PigeonholeConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getExpandCmdConfig <em>Expand Cmd Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expand Cmd Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getExpandCmdConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_ExpandCmdConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getPriorityConfig <em>Priority Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Priority Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getPriorityConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_PriorityConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getAssignPolicyConfig <em>Assign Policy Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Assign Policy Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getAssignPolicyConfig()
	 * @see #getFixFlowConfig()
	 * @generated
	 */
	EReference getFixFlowConfig_AssignPolicyConfig();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig <em>Data Base Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Base Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig
	 * @generated
	 */
	EClass getDataBaseConfig();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig#getDataBase <em>Data Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Base</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig#getDataBase()
	 * @see #getDataBaseConfig()
	 * @generated
	 */
	EReference getDataBaseConfig_DataBase();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig#getSelected <em>Selected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Selected</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig#getSelected()
	 * @see #getDataBaseConfig()
	 * @generated
	 */
	EAttribute getDataBaseConfig_Selected();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig#getMode()
	 * @see #getDataBaseConfig()
	 * @generated
	 */
	EAttribute getDataBaseConfig_Mode();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupDefinitionConfig <em>Group Definition Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group Definition Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupDefinitionConfig
	 * @generated
	 */
	EClass getGroupDefinitionConfig();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupDefinitionConfig#getGroupDefinition <em>Group Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Group Definition</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupDefinitionConfig#getGroupDefinition()
	 * @see #getGroupDefinitionConfig()
	 * @generated
	 */
	EReference getGroupDefinitionConfig_GroupDefinition();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase <em>Data Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Base</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBase
	 * @generated
	 */
	EClass getDataBase();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBase#getId()
	 * @see #getDataBase()
	 * @generated
	 */
	EAttribute getDataBase_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBase#getName()
	 * @see #getDataBase()
	 * @generated
	 */
	EAttribute getDataBase_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getDriverClassName <em>Driver Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Driver Class Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBase#getDriverClassName()
	 * @see #getDataBase()
	 * @generated
	 */
	EAttribute getDataBase_DriverClassName();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBase#getUrl()
	 * @see #getDataBase()
	 * @generated
	 */
	EAttribute getDataBase_Url();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getUsername <em>Username</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Username</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBase#getUsername()
	 * @see #getDataBase()
	 * @generated
	 */
	EAttribute getDataBase_Username();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBase#getPassword()
	 * @see #getDataBase()
	 * @generated
	 */
	EAttribute getDataBase_Password();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getMaxActive <em>Max Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Active</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBase#getMaxActive()
	 * @see #getDataBase()
	 * @generated
	 */
	EAttribute getDataBase_MaxActive();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getMaxIdle <em>Max Idle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Idle</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBase#getMaxIdle()
	 * @see #getDataBase()
	 * @generated
	 */
	EAttribute getDataBase_MaxIdle();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getMaxWait <em>Max Wait</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Wait</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBase#getMaxWait()
	 * @see #getDataBase()
	 * @generated
	 */
	EAttribute getDataBase_MaxWait();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getPaginationImpl <em>Pagination Impl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pagination Impl</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBase#getPaginationImpl()
	 * @see #getDataBase()
	 * @generated
	 */
	EAttribute getDataBase_PaginationImpl();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getDbtype <em>Dbtype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dbtype</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBase#getDbtype()
	 * @see #getDataBase()
	 * @generated
	 */
	EAttribute getDataBase_Dbtype();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition <em>Group Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group Definition</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition
	 * @generated
	 */
	EClass getGroupDefinition();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition#getId()
	 * @see #getGroupDefinition()
	 * @generated
	 */
	EAttribute getGroupDefinition_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition#getName()
	 * @see #getGroupDefinition()
	 * @generated
	 */
	EAttribute getGroupDefinition_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition#getGroupDefinitionImpl <em>Group Definition Impl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Definition Impl</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition#getGroupDefinitionImpl()
	 * @see #getGroupDefinition()
	 * @generated
	 */
	EAttribute getGroupDefinition_GroupDefinitionImpl();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig <em>Task Command Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Command Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig
	 * @generated
	 */
	EClass getTaskCommandConfig();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig#getTaskCommandDef <em>Task Command Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Task Command Def</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig#getTaskCommandDef()
	 * @see #getTaskCommandConfig()
	 * @generated
	 */
	EReference getTaskCommandConfig_TaskCommandDef();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef <em>Task Command Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Command Def</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef
	 * @generated
	 */
	EClass getTaskCommandDef();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getId()
	 * @see #getTaskCommandDef()
	 * @generated
	 */
	EAttribute getTaskCommandDef_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getName()
	 * @see #getTaskCommandDef()
	 * @generated
	 */
	EAttribute getTaskCommandDef_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getCommand <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Command</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getCommand()
	 * @see #getTaskCommandDef()
	 * @generated
	 */
	EAttribute getTaskCommandDef_Command();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getCmd <em>Cmd</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cmd</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getCmd()
	 * @see #getTaskCommandDef()
	 * @generated
	 */
	EAttribute getTaskCommandDef_Cmd();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getIsEnabled <em>Is Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Enabled</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getIsEnabled()
	 * @see #getTaskCommandDef()
	 * @generated
	 */
	EAttribute getTaskCommandDef_IsEnabled();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getType()
	 * @see #getTaskCommandDef()
	 * @generated
	 */
	EAttribute getTaskCommandDef_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getFilter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getFilter()
	 * @see #getTaskCommandDef()
	 * @generated
	 */
	EAttribute getTaskCommandDef_Filter();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig <em>Designer Org Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Designer Org Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig
	 * @generated
	 */
	EClass getDesignerOrgConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig#getAllUserInfo <em>All User Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>All User Info</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig#getAllUserInfo()
	 * @see #getDesignerOrgConfig()
	 * @generated
	 */
	EReference getDesignerOrgConfig_AllUserInfo();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig#getGroupInfo <em>Group Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Group Info</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig#getGroupInfo()
	 * @see #getDesignerOrgConfig()
	 * @generated
	 */
	EReference getDesignerOrgConfig_GroupInfo();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo <em>All User Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>All User Info</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo
	 * @generated
	 */
	EClass getAllUserInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getIsEnabled <em>Is Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Enabled</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getIsEnabled()
	 * @see #getAllUserInfo()
	 * @generated
	 */
	EAttribute getAllUserInfo_IsEnabled();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getUserIdField <em>User Id Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Id Field</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getUserIdField()
	 * @see #getAllUserInfo()
	 * @generated
	 */
	EAttribute getAllUserInfo_UserIdField();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getUserNameField <em>User Name Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Name Field</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getUserNameField()
	 * @see #getAllUserInfo()
	 * @generated
	 */
	EAttribute getAllUserInfo_UserNameField();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getSqlText <em>Sql Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sql Text</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getSqlText()
	 * @see #getAllUserInfo()
	 * @generated
	 */
	EAttribute getAllUserInfo_SqlText();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getId()
	 * @see #getAllUserInfo()
	 * @generated
	 */
	EAttribute getAllUserInfo_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getName()
	 * @see #getAllUserInfo()
	 * @generated
	 */
	EAttribute getAllUserInfo_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getDataSource <em>Data Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Source</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo#getDataSource()
	 * @see #getAllUserInfo()
	 * @generated
	 */
	EAttribute getAllUserInfo_DataSource();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo <em>Group Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group Info</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupInfo
	 * @generated
	 */
	EClass getGroupInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupId <em>Group Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupId()
	 * @see #getGroupInfo()
	 * @generated
	 */
	EAttribute getGroupInfo_GroupId();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupName <em>Group Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupName()
	 * @see #getGroupInfo()
	 * @generated
	 */
	EAttribute getGroupInfo_GroupName();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getIsEnabled <em>Is Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Enabled</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getIsEnabled()
	 * @see #getGroupInfo()
	 * @generated
	 */
	EAttribute getGroupInfo_IsEnabled();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getIsDisplayUser <em>Is Display User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Display User</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getIsDisplayUser()
	 * @see #getGroupInfo()
	 * @generated
	 */
	EAttribute getGroupInfo_IsDisplayUser();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupIdField <em>Group Id Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Id Field</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupIdField()
	 * @see #getGroupInfo()
	 * @generated
	 */
	EAttribute getGroupInfo_GroupIdField();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupNameField <em>Group Name Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Name Field</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupNameField()
	 * @see #getGroupInfo()
	 * @generated
	 */
	EAttribute getGroupInfo_GroupNameField();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getSupGroupIdField <em>Sup Group Id Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sup Group Id Field</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getSupGroupIdField()
	 * @see #getGroupInfo()
	 * @generated
	 */
	EAttribute getGroupInfo_SupGroupIdField();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getSqlText <em>Sql Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sql Text</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getSqlText()
	 * @see #getGroupInfo()
	 * @generated
	 */
	EAttribute getGroupInfo_SqlText();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getUserInfo <em>User Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>User Info</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getUserInfo()
	 * @see #getGroupInfo()
	 * @generated
	 */
	EReference getGroupInfo_UserInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getDataSource <em>Data Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Source</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getDataSource()
	 * @see #getGroupInfo()
	 * @generated
	 */
	EAttribute getGroupInfo_DataSource();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupDefinitionImpl <em>Group Definition Impl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Definition Impl</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupInfo#getGroupDefinitionImpl()
	 * @see #getGroupInfo()
	 * @generated
	 */
	EAttribute getGroupInfo_GroupDefinitionImpl();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo <em>User Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Info</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.UserInfo
	 * @generated
	 */
	EClass getUserInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getUserIdField <em>User Id Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Id Field</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getUserIdField()
	 * @see #getUserInfo()
	 * @generated
	 */
	EAttribute getUserInfo_UserIdField();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getUserNameField <em>User Name Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Name Field</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getUserNameField()
	 * @see #getUserInfo()
	 * @generated
	 */
	EAttribute getUserInfo_UserNameField();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getGroupIdField <em>Group Id Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Id Field</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getGroupIdField()
	 * @see #getUserInfo()
	 * @generated
	 */
	EAttribute getUserInfo_GroupIdField();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getSqlText <em>Sql Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sql Text</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getSqlText()
	 * @see #getUserInfo()
	 * @generated
	 */
	EAttribute getUserInfo_SqlText();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getDataSource <em>Data Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Source</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.UserInfo#getDataSource()
	 * @see #getUserInfo()
	 * @generated
	 */
	EAttribute getUserInfo_DataSource();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig <em>Sys Mail Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sys Mail Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig
	 * @generated
	 */
	EClass getSysMailConfig();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig#getSelected <em>Selected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Selected</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig#getSelected()
	 * @see #getSysMailConfig()
	 * @generated
	 */
	EAttribute getSysMailConfig_Selected();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig#getMailInfo <em>Mail Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mail Info</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig#getMailInfo()
	 * @see #getSysMailConfig()
	 * @generated
	 */
	EReference getSysMailConfig_MailInfo();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo <em>Mail Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mail Info</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.MailInfo
	 * @generated
	 */
	EClass getMailInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getMailAddress <em>Mail Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mail Address</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getMailAddress()
	 * @see #getMailInfo()
	 * @generated
	 */
	EAttribute getMailInfo_MailAddress();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getMailName <em>Mail Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mail Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getMailName()
	 * @see #getMailInfo()
	 * @generated
	 */
	EAttribute getMailInfo_MailName();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getSmtpHost <em>Smtp Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Smtp Host</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getSmtpHost()
	 * @see #getMailInfo()
	 * @generated
	 */
	EAttribute getMailInfo_SmtpHost();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getSmtpPort <em>Smtp Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Smtp Port</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getSmtpPort()
	 * @see #getMailInfo()
	 * @generated
	 */
	EAttribute getMailInfo_SmtpPort();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getUserName <em>User Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getUserName()
	 * @see #getMailInfo()
	 * @generated
	 */
	EAttribute getMailInfo_UserName();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getPassWord <em>Pass Word</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pass Word</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getPassWord()
	 * @see #getMailInfo()
	 * @generated
	 */
	EAttribute getMailInfo_PassWord();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClassConfig <em>Expand Class Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expand Class Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandClassConfig
	 * @generated
	 */
	EClass getExpandClassConfig();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClassConfig#getExpandClass <em>Expand Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expand Class</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandClassConfig#getExpandClass()
	 * @see #getExpandClassConfig()
	 * @generated
	 */
	EReference getExpandClassConfig_ExpandClass();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass <em>Expand Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expand Class</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandClass
	 * @generated
	 */
	EClass getExpandClass();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassId <em>Class Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassId()
	 * @see #getExpandClass()
	 * @generated
	 */
	EAttribute getExpandClass_ClassId();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassName()
	 * @see #getExpandClass()
	 * @generated
	 */
	EAttribute getExpandClass_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassInterface <em>Class Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Interface</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassInterface()
	 * @see #getExpandClass()
	 * @generated
	 */
	EAttribute getExpandClass_ClassInterface();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassImpl <em>Class Impl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Impl</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassImpl()
	 * @see #getExpandClass()
	 * @generated
	 */
	EAttribute getExpandClass_ClassImpl();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getRemarks <em>Remarks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remarks</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getRemarks()
	 * @see #getExpandClass()
	 * @generated
	 */
	EAttribute getExpandClass_Remarks();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig <em>Event Subscription Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Subscription Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig
	 * @generated
	 */
	EClass getEventSubscriptionConfig();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getServerAddress <em>Server Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server Address</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getServerAddress()
	 * @see #getEventSubscriptionConfig()
	 * @generated
	 */
	EAttribute getEventSubscriptionConfig_ServerAddress();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getServerPort <em>Server Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server Port</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getServerPort()
	 * @see #getEventSubscriptionConfig()
	 * @generated
	 */
	EAttribute getEventSubscriptionConfig_ServerPort();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getMessageInfo <em>Message Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Info</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getMessageInfo()
	 * @see #getEventSubscriptionConfig()
	 * @generated
	 */
	EAttribute getEventSubscriptionConfig_MessageInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getSignalInfo <em>Signal Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signal Info</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getSignalInfo()
	 * @see #getEventSubscriptionConfig()
	 * @generated
	 */
	EAttribute getEventSubscriptionConfig_SignalInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getIsEnable <em>Is Enable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Enable</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getIsEnable()
	 * @see #getEventSubscriptionConfig()
	 * @generated
	 */
	EAttribute getEventSubscriptionConfig_IsEnable();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig <em>Quartz Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quartz Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig
	 * @generated
	 */
	EClass getQuartzConfig();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig#getIsEnable <em>Is Enable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Enable</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig#getIsEnable()
	 * @see #getQuartzConfig()
	 * @generated
	 */
	EAttribute getQuartzConfig_IsEnable();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig#getIsDefaultConfig <em>Is Default Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Default Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig#getIsDefaultConfig()
	 * @see #getQuartzConfig()
	 * @generated
	 */
	EAttribute getQuartzConfig_IsDefaultConfig();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig#getDataBaseId <em>Data Base Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Base Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig#getDataBaseId()
	 * @see #getQuartzConfig()
	 * @generated
	 */
	EAttribute getQuartzConfig_DataBaseId();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig <em>Script Language Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script Language Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig
	 * @generated
	 */
	EClass getScriptLanguageConfig();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig#getSelected <em>Selected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Selected</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig#getSelected()
	 * @see #getScriptLanguageConfig()
	 * @generated
	 */
	EAttribute getScriptLanguageConfig_Selected();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig#getScriptLanguage <em>Script Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Script Language</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig#getScriptLanguage()
	 * @see #getScriptLanguageConfig()
	 * @generated
	 */
	EReference getScriptLanguageConfig_ScriptLanguage();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage <em>Script Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script Language</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage
	 * @generated
	 */
	EClass getScriptLanguage();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage#getId()
	 * @see #getScriptLanguage()
	 * @generated
	 */
	EAttribute getScriptLanguage_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage#getName()
	 * @see #getScriptLanguage()
	 * @generated
	 */
	EAttribute getScriptLanguage_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage#getClassImpl <em>Class Impl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Impl</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage#getClassImpl()
	 * @see #getScriptLanguage()
	 * @generated
	 */
	EAttribute getScriptLanguage_ClassImpl();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig <em>Internationalization Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Internationalization Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig
	 * @generated
	 */
	EClass getInternationalizationConfig();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig#getIsEnable <em>Is Enable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Enable</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig#getIsEnable()
	 * @see #getInternationalizationConfig()
	 * @generated
	 */
	EAttribute getInternationalizationConfig_IsEnable();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig#getFolderPath <em>Folder Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Folder Path</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig#getFolderPath()
	 * @see #getInternationalizationConfig()
	 * @generated
	 */
	EAttribute getInternationalizationConfig_FolderPath();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig <em>Pigeonhole Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pigeonhole Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig
	 * @generated
	 */
	EClass getPigeonholeConfig();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getServerPath <em>Server Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server Path</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getServerPath()
	 * @see #getPigeonholeConfig()
	 * @generated
	 */
	EAttribute getPigeonholeConfig_ServerPath();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getPdfPath <em>Pdf Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pdf Path</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getPdfPath()
	 * @see #getPigeonholeConfig()
	 * @generated
	 */
	EAttribute getPigeonholeConfig_PdfPath();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getTime()
	 * @see #getPigeonholeConfig()
	 * @generated
	 */
	EAttribute getPigeonholeConfig_Time();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getFrequency <em>Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frequency</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getFrequency()
	 * @see #getPigeonholeConfig()
	 * @generated
	 */
	EAttribute getPigeonholeConfig_Frequency();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getWeek <em>Week</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Week</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getWeek()
	 * @see #getPigeonholeConfig()
	 * @generated
	 */
	EAttribute getPigeonholeConfig_Week();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getMonth <em>Month</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Month</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getMonth()
	 * @see #getPigeonholeConfig()
	 * @generated
	 */
	EAttribute getPigeonholeConfig_Month();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getCode()
	 * @see #getPigeonholeConfig()
	 * @generated
	 */
	EAttribute getPigeonholeConfig_Code();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getIsEnable <em>Is Enable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Enable</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig#getIsEnable()
	 * @see #getPigeonholeConfig()
	 * @generated
	 */
	EAttribute getPigeonholeConfig_IsEnable();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmdConfig <em>Expand Cmd Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expand Cmd Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandCmdConfig
	 * @generated
	 */
	EClass getExpandCmdConfig();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmdConfig#getExpandCmd <em>Expand Cmd</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expand Cmd</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandCmdConfig#getExpandCmd()
	 * @see #getExpandCmdConfig()
	 * @generated
	 */
	EReference getExpandCmdConfig_ExpandCmd();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd <em>Expand Cmd</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expand Cmd</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd
	 * @generated
	 */
	EClass getExpandCmd();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd#getId()
	 * @see #getExpandCmd()
	 * @generated
	 */
	EAttribute getExpandCmd_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd#getName()
	 * @see #getExpandCmd()
	 * @generated
	 */
	EAttribute getExpandCmd_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd#getCmd <em>Cmd</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cmd</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd#getCmd()
	 * @see #getExpandCmd()
	 * @generated
	 */
	EAttribute getExpandCmd_Cmd();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.PriorityConfig <em>Priority Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Priority Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.PriorityConfig
	 * @generated
	 */
	EClass getPriorityConfig();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.coreconfig.PriorityConfig#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Priority</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.PriorityConfig#getPriority()
	 * @see #getPriorityConfig()
	 * @generated
	 */
	EReference getPriorityConfig_Priority();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.Priority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Priority</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.Priority
	 * @generated
	 */
	EClass getPriority();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.Priority#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.Priority#getId()
	 * @see #getPriority()
	 * @generated
	 */
	EAttribute getPriority_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.Priority#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.Priority#getName()
	 * @see #getPriority()
	 * @generated
	 */
	EAttribute getPriority_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.Priority#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.Priority#getValue()
	 * @see #getPriority()
	 * @generated
	 */
	EAttribute getPriority_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.Priority#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.Priority#getColor()
	 * @see #getPriority()
	 * @generated
	 */
	EAttribute getPriority_Color();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.Priority#getRemark <em>Remark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remark</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.Priority#getRemark()
	 * @see #getPriority()
	 * @generated
	 */
	EAttribute getPriority_Remark();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.AssignPolicyConfig <em>Assign Policy Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assign Policy Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AssignPolicyConfig
	 * @generated
	 */
	EClass getAssignPolicyConfig();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.coreconfig.AssignPolicyConfig#getAssignPolicy <em>Assign Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Assign Policy</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AssignPolicyConfig#getAssignPolicy()
	 * @see #getAssignPolicyConfig()
	 * @generated
	 */
	EReference getAssignPolicyConfig_AssignPolicy();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy <em>Assign Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assign Policy</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy
	 * @generated
	 */
	EClass getAssignPolicy();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy#getId()
	 * @see #getAssignPolicy()
	 * @generated
	 */
	EAttribute getAssignPolicy_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy#getName()
	 * @see #getAssignPolicy()
	 * @generated
	 */
	EAttribute getAssignPolicy_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy#getClassImpl <em>Class Impl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Impl</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy#getClassImpl()
	 * @see #getAssignPolicy()
	 * @generated
	 */
	EAttribute getAssignPolicy_ClassImpl();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy#getRemarks <em>Remarks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remarks</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy#getRemarks()
	 * @see #getAssignPolicy()
	 * @generated
	 */
	EAttribute getAssignPolicy_Remarks();

	/**
	 * Returns the meta object for enum '{@link com.founder.fix.bpmn2extensions.coreconfig.DBType <em>DB Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DB Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DBType
	 * @generated
	 */
	EEnum getDBType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CoreconfigFactory getCoreconfigFactory();

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
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl <em>Fix Flow Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getFixFlowConfig()
		 * @generated
		 */
		EClass FIX_FLOW_CONFIG = eINSTANCE.getFixFlowConfig();

		/**
		 * The meta object literal for the '<em><b>Data Base Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__DATA_BASE_CONFIG = eINSTANCE.getFixFlowConfig_DataBaseConfig();

		/**
		 * The meta object literal for the '<em><b>Group Definition Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__GROUP_DEFINITION_CONFIG = eINSTANCE.getFixFlowConfig_GroupDefinitionConfig();

		/**
		 * The meta object literal for the '<em><b>Task Command Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__TASK_COMMAND_CONFIG = eINSTANCE.getFixFlowConfig_TaskCommandConfig();

		/**
		 * The meta object literal for the '<em><b>Designer Org Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG = eINSTANCE.getFixFlowConfig_DesignerOrgConfig();

		/**
		 * The meta object literal for the '<em><b>Sys Mail Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__SYS_MAIL_CONFIG = eINSTANCE.getFixFlowConfig_SysMailConfig();

		/**
		 * The meta object literal for the '<em><b>Expand Class Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__EXPAND_CLASS_CONFIG = eINSTANCE.getFixFlowConfig_ExpandClassConfig();

		/**
		 * The meta object literal for the '<em><b>Event Subscription Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG = eINSTANCE.getFixFlowConfig_EventSubscriptionConfig();

		/**
		 * The meta object literal for the '<em><b>Quartz Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__QUARTZ_CONFIG = eINSTANCE.getFixFlowConfig_QuartzConfig();

		/**
		 * The meta object literal for the '<em><b>Script Language Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__SCRIPT_LANGUAGE_CONFIG = eINSTANCE.getFixFlowConfig_ScriptLanguageConfig();

		/**
		 * The meta object literal for the '<em><b>Internationalization Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__INTERNATIONALIZATION_CONFIG = eINSTANCE.getFixFlowConfig_InternationalizationConfig();

		/**
		 * The meta object literal for the '<em><b>Pigeonhole Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG = eINSTANCE.getFixFlowConfig_PigeonholeConfig();

		/**
		 * The meta object literal for the '<em><b>Expand Cmd Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__EXPAND_CMD_CONFIG = eINSTANCE.getFixFlowConfig_ExpandCmdConfig();

		/**
		 * The meta object literal for the '<em><b>Priority Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__PRIORITY_CONFIG = eINSTANCE.getFixFlowConfig_PriorityConfig();

		/**
		 * The meta object literal for the '<em><b>Assign Policy Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_CONFIG__ASSIGN_POLICY_CONFIG = eINSTANCE.getFixFlowConfig_AssignPolicyConfig();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseConfigImpl <em>Data Base Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getDataBaseConfig()
		 * @generated
		 */
		EClass DATA_BASE_CONFIG = eINSTANCE.getDataBaseConfig();

		/**
		 * The meta object literal for the '<em><b>Data Base</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_BASE_CONFIG__DATA_BASE = eINSTANCE.getDataBaseConfig_DataBase();

		/**
		 * The meta object literal for the '<em><b>Selected</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BASE_CONFIG__SELECTED = eINSTANCE.getDataBaseConfig_Selected();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BASE_CONFIG__MODE = eINSTANCE.getDataBaseConfig_Mode();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupDefinitionConfigImpl <em>Group Definition Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.GroupDefinitionConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getGroupDefinitionConfig()
		 * @generated
		 */
		EClass GROUP_DEFINITION_CONFIG = eINSTANCE.getGroupDefinitionConfig();

		/**
		 * The meta object literal for the '<em><b>Group Definition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GROUP_DEFINITION_CONFIG__GROUP_DEFINITION = eINSTANCE.getGroupDefinitionConfig_GroupDefinition();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl <em>Data Base</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getDataBase()
		 * @generated
		 */
		EClass DATA_BASE = eINSTANCE.getDataBase();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BASE__ID = eINSTANCE.getDataBase_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BASE__NAME = eINSTANCE.getDataBase_Name();

		/**
		 * The meta object literal for the '<em><b>Driver Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BASE__DRIVER_CLASS_NAME = eINSTANCE.getDataBase_DriverClassName();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BASE__URL = eINSTANCE.getDataBase_Url();

		/**
		 * The meta object literal for the '<em><b>Username</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BASE__USERNAME = eINSTANCE.getDataBase_Username();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BASE__PASSWORD = eINSTANCE.getDataBase_Password();

		/**
		 * The meta object literal for the '<em><b>Max Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BASE__MAX_ACTIVE = eINSTANCE.getDataBase_MaxActive();

		/**
		 * The meta object literal for the '<em><b>Max Idle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BASE__MAX_IDLE = eINSTANCE.getDataBase_MaxIdle();

		/**
		 * The meta object literal for the '<em><b>Max Wait</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BASE__MAX_WAIT = eINSTANCE.getDataBase_MaxWait();

		/**
		 * The meta object literal for the '<em><b>Pagination Impl</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BASE__PAGINATION_IMPL = eINSTANCE.getDataBase_PaginationImpl();

		/**
		 * The meta object literal for the '<em><b>Dbtype</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BASE__DBTYPE = eINSTANCE.getDataBase_Dbtype();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupDefinitionImpl <em>Group Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.GroupDefinitionImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getGroupDefinition()
		 * @generated
		 */
		EClass GROUP_DEFINITION = eINSTANCE.getGroupDefinition();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_DEFINITION__ID = eINSTANCE.getGroupDefinition_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_DEFINITION__NAME = eINSTANCE.getGroupDefinition_Name();

		/**
		 * The meta object literal for the '<em><b>Group Definition Impl</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_DEFINITION__GROUP_DEFINITION_IMPL = eINSTANCE.getGroupDefinition_GroupDefinitionImpl();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.TaskCommandConfigImpl <em>Task Command Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.TaskCommandConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getTaskCommandConfig()
		 * @generated
		 */
		EClass TASK_COMMAND_CONFIG = eINSTANCE.getTaskCommandConfig();

		/**
		 * The meta object literal for the '<em><b>Task Command Def</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_COMMAND_CONFIG__TASK_COMMAND_DEF = eINSTANCE.getTaskCommandConfig_TaskCommandDef();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.TaskCommandDefImpl <em>Task Command Def</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.TaskCommandDefImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getTaskCommandDef()
		 * @generated
		 */
		EClass TASK_COMMAND_DEF = eINSTANCE.getTaskCommandDef();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_COMMAND_DEF__ID = eINSTANCE.getTaskCommandDef_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_COMMAND_DEF__NAME = eINSTANCE.getTaskCommandDef_Name();

		/**
		 * The meta object literal for the '<em><b>Command</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_COMMAND_DEF__COMMAND = eINSTANCE.getTaskCommandDef_Command();

		/**
		 * The meta object literal for the '<em><b>Cmd</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_COMMAND_DEF__CMD = eINSTANCE.getTaskCommandDef_Cmd();

		/**
		 * The meta object literal for the '<em><b>Is Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_COMMAND_DEF__IS_ENABLED = eINSTANCE.getTaskCommandDef_IsEnabled();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_COMMAND_DEF__TYPE = eINSTANCE.getTaskCommandDef_Type();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_COMMAND_DEF__FILTER = eINSTANCE.getTaskCommandDef_Filter();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DesignerOrgConfigImpl <em>Designer Org Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.DesignerOrgConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getDesignerOrgConfig()
		 * @generated
		 */
		EClass DESIGNER_ORG_CONFIG = eINSTANCE.getDesignerOrgConfig();

		/**
		 * The meta object literal for the '<em><b>All User Info</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DESIGNER_ORG_CONFIG__ALL_USER_INFO = eINSTANCE.getDesignerOrgConfig_AllUserInfo();

		/**
		 * The meta object literal for the '<em><b>Group Info</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DESIGNER_ORG_CONFIG__GROUP_INFO = eINSTANCE.getDesignerOrgConfig_GroupInfo();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AllUserInfoImpl <em>All User Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.AllUserInfoImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getAllUserInfo()
		 * @generated
		 */
		EClass ALL_USER_INFO = eINSTANCE.getAllUserInfo();

		/**
		 * The meta object literal for the '<em><b>Is Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALL_USER_INFO__IS_ENABLED = eINSTANCE.getAllUserInfo_IsEnabled();

		/**
		 * The meta object literal for the '<em><b>User Id Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALL_USER_INFO__USER_ID_FIELD = eINSTANCE.getAllUserInfo_UserIdField();

		/**
		 * The meta object literal for the '<em><b>User Name Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALL_USER_INFO__USER_NAME_FIELD = eINSTANCE.getAllUserInfo_UserNameField();

		/**
		 * The meta object literal for the '<em><b>Sql Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALL_USER_INFO__SQL_TEXT = eINSTANCE.getAllUserInfo_SqlText();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALL_USER_INFO__ID = eINSTANCE.getAllUserInfo_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALL_USER_INFO__NAME = eINSTANCE.getAllUserInfo_Name();

		/**
		 * The meta object literal for the '<em><b>Data Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALL_USER_INFO__DATA_SOURCE = eINSTANCE.getAllUserInfo_DataSource();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl <em>Group Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getGroupInfo()
		 * @generated
		 */
		EClass GROUP_INFO = eINSTANCE.getGroupInfo();

		/**
		 * The meta object literal for the '<em><b>Group Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_INFO__GROUP_ID = eINSTANCE.getGroupInfo_GroupId();

		/**
		 * The meta object literal for the '<em><b>Group Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_INFO__GROUP_NAME = eINSTANCE.getGroupInfo_GroupName();

		/**
		 * The meta object literal for the '<em><b>Is Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_INFO__IS_ENABLED = eINSTANCE.getGroupInfo_IsEnabled();

		/**
		 * The meta object literal for the '<em><b>Is Display User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_INFO__IS_DISPLAY_USER = eINSTANCE.getGroupInfo_IsDisplayUser();

		/**
		 * The meta object literal for the '<em><b>Group Id Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_INFO__GROUP_ID_FIELD = eINSTANCE.getGroupInfo_GroupIdField();

		/**
		 * The meta object literal for the '<em><b>Group Name Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_INFO__GROUP_NAME_FIELD = eINSTANCE.getGroupInfo_GroupNameField();

		/**
		 * The meta object literal for the '<em><b>Sup Group Id Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_INFO__SUP_GROUP_ID_FIELD = eINSTANCE.getGroupInfo_SupGroupIdField();

		/**
		 * The meta object literal for the '<em><b>Sql Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_INFO__SQL_TEXT = eINSTANCE.getGroupInfo_SqlText();

		/**
		 * The meta object literal for the '<em><b>User Info</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GROUP_INFO__USER_INFO = eINSTANCE.getGroupInfo_UserInfo();

		/**
		 * The meta object literal for the '<em><b>Data Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_INFO__DATA_SOURCE = eINSTANCE.getGroupInfo_DataSource();

		/**
		 * The meta object literal for the '<em><b>Group Definition Impl</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_INFO__GROUP_DEFINITION_IMPL = eINSTANCE.getGroupInfo_GroupDefinitionImpl();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.UserInfoImpl <em>User Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.UserInfoImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getUserInfo()
		 * @generated
		 */
		EClass USER_INFO = eINSTANCE.getUserInfo();

		/**
		 * The meta object literal for the '<em><b>User Id Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_INFO__USER_ID_FIELD = eINSTANCE.getUserInfo_UserIdField();

		/**
		 * The meta object literal for the '<em><b>User Name Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_INFO__USER_NAME_FIELD = eINSTANCE.getUserInfo_UserNameField();

		/**
		 * The meta object literal for the '<em><b>Group Id Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_INFO__GROUP_ID_FIELD = eINSTANCE.getUserInfo_GroupIdField();

		/**
		 * The meta object literal for the '<em><b>Sql Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_INFO__SQL_TEXT = eINSTANCE.getUserInfo_SqlText();

		/**
		 * The meta object literal for the '<em><b>Data Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_INFO__DATA_SOURCE = eINSTANCE.getUserInfo_DataSource();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.SysMailConfigImpl <em>Sys Mail Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.SysMailConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getSysMailConfig()
		 * @generated
		 */
		EClass SYS_MAIL_CONFIG = eINSTANCE.getSysMailConfig();

		/**
		 * The meta object literal for the '<em><b>Selected</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYS_MAIL_CONFIG__SELECTED = eINSTANCE.getSysMailConfig_Selected();

		/**
		 * The meta object literal for the '<em><b>Mail Info</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYS_MAIL_CONFIG__MAIL_INFO = eINSTANCE.getSysMailConfig_MailInfo();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.MailInfoImpl <em>Mail Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.MailInfoImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getMailInfo()
		 * @generated
		 */
		EClass MAIL_INFO = eINSTANCE.getMailInfo();

		/**
		 * The meta object literal for the '<em><b>Mail Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_INFO__MAIL_ADDRESS = eINSTANCE.getMailInfo_MailAddress();

		/**
		 * The meta object literal for the '<em><b>Mail Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_INFO__MAIL_NAME = eINSTANCE.getMailInfo_MailName();

		/**
		 * The meta object literal for the '<em><b>Smtp Host</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_INFO__SMTP_HOST = eINSTANCE.getMailInfo_SmtpHost();

		/**
		 * The meta object literal for the '<em><b>Smtp Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_INFO__SMTP_PORT = eINSTANCE.getMailInfo_SmtpPort();

		/**
		 * The meta object literal for the '<em><b>User Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_INFO__USER_NAME = eINSTANCE.getMailInfo_UserName();

		/**
		 * The meta object literal for the '<em><b>Pass Word</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_INFO__PASS_WORD = eINSTANCE.getMailInfo_PassWord();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandClassConfigImpl <em>Expand Class Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandClassConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getExpandClassConfig()
		 * @generated
		 */
		EClass EXPAND_CLASS_CONFIG = eINSTANCE.getExpandClassConfig();

		/**
		 * The meta object literal for the '<em><b>Expand Class</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPAND_CLASS_CONFIG__EXPAND_CLASS = eINSTANCE.getExpandClassConfig_ExpandClass();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandClassImpl <em>Expand Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandClassImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getExpandClass()
		 * @generated
		 */
		EClass EXPAND_CLASS = eINSTANCE.getExpandClass();

		/**
		 * The meta object literal for the '<em><b>Class Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPAND_CLASS__CLASS_ID = eINSTANCE.getExpandClass_ClassId();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPAND_CLASS__CLASS_NAME = eINSTANCE.getExpandClass_ClassName();

		/**
		 * The meta object literal for the '<em><b>Class Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPAND_CLASS__CLASS_INTERFACE = eINSTANCE.getExpandClass_ClassInterface();

		/**
		 * The meta object literal for the '<em><b>Class Impl</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPAND_CLASS__CLASS_IMPL = eINSTANCE.getExpandClass_ClassImpl();

		/**
		 * The meta object literal for the '<em><b>Remarks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPAND_CLASS__REMARKS = eINSTANCE.getExpandClass_Remarks();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.EventSubscriptionConfigImpl <em>Event Subscription Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.EventSubscriptionConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getEventSubscriptionConfig()
		 * @generated
		 */
		EClass EVENT_SUBSCRIPTION_CONFIG = eINSTANCE.getEventSubscriptionConfig();

		/**
		 * The meta object literal for the '<em><b>Server Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_SUBSCRIPTION_CONFIG__SERVER_ADDRESS = eINSTANCE.getEventSubscriptionConfig_ServerAddress();

		/**
		 * The meta object literal for the '<em><b>Server Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_SUBSCRIPTION_CONFIG__SERVER_PORT = eINSTANCE.getEventSubscriptionConfig_ServerPort();

		/**
		 * The meta object literal for the '<em><b>Message Info</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_SUBSCRIPTION_CONFIG__MESSAGE_INFO = eINSTANCE.getEventSubscriptionConfig_MessageInfo();

		/**
		 * The meta object literal for the '<em><b>Signal Info</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_SUBSCRIPTION_CONFIG__SIGNAL_INFO = eINSTANCE.getEventSubscriptionConfig_SignalInfo();

		/**
		 * The meta object literal for the '<em><b>Is Enable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_SUBSCRIPTION_CONFIG__IS_ENABLE = eINSTANCE.getEventSubscriptionConfig_IsEnable();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.QuartzConfigImpl <em>Quartz Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.QuartzConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getQuartzConfig()
		 * @generated
		 */
		EClass QUARTZ_CONFIG = eINSTANCE.getQuartzConfig();

		/**
		 * The meta object literal for the '<em><b>Is Enable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUARTZ_CONFIG__IS_ENABLE = eINSTANCE.getQuartzConfig_IsEnable();

		/**
		 * The meta object literal for the '<em><b>Is Default Config</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUARTZ_CONFIG__IS_DEFAULT_CONFIG = eINSTANCE.getQuartzConfig_IsDefaultConfig();

		/**
		 * The meta object literal for the '<em><b>Data Base Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUARTZ_CONFIG__DATA_BASE_ID = eINSTANCE.getQuartzConfig_DataBaseId();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ScriptLanguageConfigImpl <em>Script Language Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.ScriptLanguageConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getScriptLanguageConfig()
		 * @generated
		 */
		EClass SCRIPT_LANGUAGE_CONFIG = eINSTANCE.getScriptLanguageConfig();

		/**
		 * The meta object literal for the '<em><b>Selected</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_LANGUAGE_CONFIG__SELECTED = eINSTANCE.getScriptLanguageConfig_Selected();

		/**
		 * The meta object literal for the '<em><b>Script Language</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCRIPT_LANGUAGE_CONFIG__SCRIPT_LANGUAGE = eINSTANCE.getScriptLanguageConfig_ScriptLanguage();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ScriptLanguageImpl <em>Script Language</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.ScriptLanguageImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getScriptLanguage()
		 * @generated
		 */
		EClass SCRIPT_LANGUAGE = eINSTANCE.getScriptLanguage();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_LANGUAGE__ID = eINSTANCE.getScriptLanguage_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_LANGUAGE__NAME = eINSTANCE.getScriptLanguage_Name();

		/**
		 * The meta object literal for the '<em><b>Class Impl</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_LANGUAGE__CLASS_IMPL = eINSTANCE.getScriptLanguage_ClassImpl();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.InternationalizationConfigImpl <em>Internationalization Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.InternationalizationConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getInternationalizationConfig()
		 * @generated
		 */
		EClass INTERNATIONALIZATION_CONFIG = eINSTANCE.getInternationalizationConfig();

		/**
		 * The meta object literal for the '<em><b>Is Enable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERNATIONALIZATION_CONFIG__IS_ENABLE = eINSTANCE.getInternationalizationConfig_IsEnable();

		/**
		 * The meta object literal for the '<em><b>Folder Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERNATIONALIZATION_CONFIG__FOLDER_PATH = eINSTANCE.getInternationalizationConfig_FolderPath();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PigeonholeConfigImpl <em>Pigeonhole Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.PigeonholeConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getPigeonholeConfig()
		 * @generated
		 */
		EClass PIGEONHOLE_CONFIG = eINSTANCE.getPigeonholeConfig();

		/**
		 * The meta object literal for the '<em><b>Server Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIGEONHOLE_CONFIG__SERVER_PATH = eINSTANCE.getPigeonholeConfig_ServerPath();

		/**
		 * The meta object literal for the '<em><b>Pdf Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIGEONHOLE_CONFIG__PDF_PATH = eINSTANCE.getPigeonholeConfig_PdfPath();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIGEONHOLE_CONFIG__TIME = eINSTANCE.getPigeonholeConfig_Time();

		/**
		 * The meta object literal for the '<em><b>Frequency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIGEONHOLE_CONFIG__FREQUENCY = eINSTANCE.getPigeonholeConfig_Frequency();

		/**
		 * The meta object literal for the '<em><b>Week</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIGEONHOLE_CONFIG__WEEK = eINSTANCE.getPigeonholeConfig_Week();

		/**
		 * The meta object literal for the '<em><b>Month</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIGEONHOLE_CONFIG__MONTH = eINSTANCE.getPigeonholeConfig_Month();

		/**
		 * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIGEONHOLE_CONFIG__CODE = eINSTANCE.getPigeonholeConfig_Code();

		/**
		 * The meta object literal for the '<em><b>Is Enable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIGEONHOLE_CONFIG__IS_ENABLE = eINSTANCE.getPigeonholeConfig_IsEnable();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandCmdConfigImpl <em>Expand Cmd Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandCmdConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getExpandCmdConfig()
		 * @generated
		 */
		EClass EXPAND_CMD_CONFIG = eINSTANCE.getExpandCmdConfig();

		/**
		 * The meta object literal for the '<em><b>Expand Cmd</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPAND_CMD_CONFIG__EXPAND_CMD = eINSTANCE.getExpandCmdConfig_ExpandCmd();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandCmdImpl <em>Expand Cmd</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandCmdImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getExpandCmd()
		 * @generated
		 */
		EClass EXPAND_CMD = eINSTANCE.getExpandCmd();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPAND_CMD__ID = eINSTANCE.getExpandCmd_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPAND_CMD__NAME = eINSTANCE.getExpandCmd_Name();

		/**
		 * The meta object literal for the '<em><b>Cmd</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPAND_CMD__CMD = eINSTANCE.getExpandCmd_Cmd();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PriorityConfigImpl <em>Priority Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.PriorityConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getPriorityConfig()
		 * @generated
		 */
		EClass PRIORITY_CONFIG = eINSTANCE.getPriorityConfig();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIORITY_CONFIG__PRIORITY = eINSTANCE.getPriorityConfig_Priority();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PriorityImpl <em>Priority</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.PriorityImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getPriority()
		 * @generated
		 */
		EClass PRIORITY = eINSTANCE.getPriority();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIORITY__ID = eINSTANCE.getPriority_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIORITY__NAME = eINSTANCE.getPriority_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIORITY__VALUE = eINSTANCE.getPriority_Value();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIORITY__COLOR = eINSTANCE.getPriority_Color();

		/**
		 * The meta object literal for the '<em><b>Remark</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIORITY__REMARK = eINSTANCE.getPriority_Remark();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AssignPolicyConfigImpl <em>Assign Policy Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.AssignPolicyConfigImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getAssignPolicyConfig()
		 * @generated
		 */
		EClass ASSIGN_POLICY_CONFIG = eINSTANCE.getAssignPolicyConfig();

		/**
		 * The meta object literal for the '<em><b>Assign Policy</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGN_POLICY_CONFIG__ASSIGN_POLICY = eINSTANCE.getAssignPolicyConfig_AssignPolicy();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AssignPolicyImpl <em>Assign Policy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.AssignPolicyImpl
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getAssignPolicy()
		 * @generated
		 */
		EClass ASSIGN_POLICY = eINSTANCE.getAssignPolicy();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSIGN_POLICY__ID = eINSTANCE.getAssignPolicy_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSIGN_POLICY__NAME = eINSTANCE.getAssignPolicy_Name();

		/**
		 * The meta object literal for the '<em><b>Class Impl</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSIGN_POLICY__CLASS_IMPL = eINSTANCE.getAssignPolicy_ClassImpl();

		/**
		 * The meta object literal for the '<em><b>Remarks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSIGN_POLICY__REMARKS = eINSTANCE.getAssignPolicy_Remarks();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.coreconfig.DBType <em>DB Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.coreconfig.DBType
		 * @see com.founder.fix.bpmn2extensions.coreconfig.impl.CoreconfigPackageImpl#getDBType()
		 * @generated
		 */
		EEnum DB_TYPE = eINSTANCE.getDBType();

	}

} //CoreconfigPackage
