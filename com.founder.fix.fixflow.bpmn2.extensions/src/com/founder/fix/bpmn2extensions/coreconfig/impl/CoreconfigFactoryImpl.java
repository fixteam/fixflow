/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CoreconfigFactoryImpl extends EFactoryImpl implements CoreconfigFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CoreconfigFactory init() {
		try {
			CoreconfigFactory theCoreconfigFactory = (CoreconfigFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.founderfix.com/coreconfig"); 
			if (theCoreconfigFactory != null) {
				return theCoreconfigFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CoreconfigFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreconfigFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CoreconfigPackage.FIX_FLOW_CONFIG: return createFixFlowConfig();
			case CoreconfigPackage.DATA_BASE_CONFIG: return createDataBaseConfig();
			case CoreconfigPackage.GROUP_DEFINITION_CONFIG: return createGroupDefinitionConfig();
			case CoreconfigPackage.DATA_BASE: return createDataBase();
			case CoreconfigPackage.GROUP_DEFINITION: return createGroupDefinition();
			case CoreconfigPackage.TASK_COMMAND_CONFIG: return createTaskCommandConfig();
			case CoreconfigPackage.TASK_COMMAND_DEF: return createTaskCommandDef();
			case CoreconfigPackage.DESIGNER_ORG_CONFIG: return createDesignerOrgConfig();
			case CoreconfigPackage.ALL_USER_INFO: return createAllUserInfo();
			case CoreconfigPackage.GROUP_INFO: return createGroupInfo();
			case CoreconfigPackage.USER_INFO: return createUserInfo();
			case CoreconfigPackage.SYS_MAIL_CONFIG: return createSysMailConfig();
			case CoreconfigPackage.MAIL_INFO: return createMailInfo();
			case CoreconfigPackage.EXPAND_CLASS_CONFIG: return createExpandClassConfig();
			case CoreconfigPackage.EXPAND_CLASS: return createExpandClass();
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG: return createEventSubscriptionConfig();
			case CoreconfigPackage.QUARTZ_CONFIG: return createQuartzConfig();
			case CoreconfigPackage.SCRIPT_LANGUAGE_CONFIG: return createScriptLanguageConfig();
			case CoreconfigPackage.SCRIPT_LANGUAGE: return createScriptLanguage();
			case CoreconfigPackage.INTERNATIONALIZATION_CONFIG: return createInternationalizationConfig();
			case CoreconfigPackage.PIGEONHOLE_CONFIG: return createPigeonholeConfig();
			case CoreconfigPackage.EXPAND_CMD_CONFIG: return createExpandCmdConfig();
			case CoreconfigPackage.EXPAND_CMD: return createExpandCmd();
			case CoreconfigPackage.PRIORITY_CONFIG: return createPriorityConfig();
			case CoreconfigPackage.PRIORITY: return createPriority();
			case CoreconfigPackage.ASSIGN_POLICY_CONFIG: return createAssignPolicyConfig();
			case CoreconfigPackage.ASSIGN_POLICY: return createAssignPolicy();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case CoreconfigPackage.DB_TYPE:
				return createDBTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case CoreconfigPackage.DB_TYPE:
				return convertDBTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FixFlowConfig createFixFlowConfig() {
		FixFlowConfigImpl fixFlowConfig = new FixFlowConfigImpl();
		return fixFlowConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataBaseConfig createDataBaseConfig() {
		DataBaseConfigImpl dataBaseConfig = new DataBaseConfigImpl();
		return dataBaseConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GroupDefinitionConfig createGroupDefinitionConfig() {
		GroupDefinitionConfigImpl groupDefinitionConfig = new GroupDefinitionConfigImpl();
		return groupDefinitionConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataBase createDataBase() {
		DataBaseImpl dataBase = new DataBaseImpl();
		return dataBase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GroupDefinition createGroupDefinition() {
		GroupDefinitionImpl groupDefinition = new GroupDefinitionImpl();
		return groupDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskCommandConfig createTaskCommandConfig() {
		TaskCommandConfigImpl taskCommandConfig = new TaskCommandConfigImpl();
		return taskCommandConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskCommandDef createTaskCommandDef() {
		TaskCommandDefImpl taskCommandDef = new TaskCommandDefImpl();
		return taskCommandDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DesignerOrgConfig createDesignerOrgConfig() {
		DesignerOrgConfigImpl designerOrgConfig = new DesignerOrgConfigImpl();
		return designerOrgConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllUserInfo createAllUserInfo() {
		AllUserInfoImpl allUserInfo = new AllUserInfoImpl();
		return allUserInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GroupInfo createGroupInfo() {
		GroupInfoImpl groupInfo = new GroupInfoImpl();
		return groupInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInfo createUserInfo() {
		UserInfoImpl userInfo = new UserInfoImpl();
		return userInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SysMailConfig createSysMailConfig() {
		SysMailConfigImpl sysMailConfig = new SysMailConfigImpl();
		return sysMailConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MailInfo createMailInfo() {
		MailInfoImpl mailInfo = new MailInfoImpl();
		return mailInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpandClassConfig createExpandClassConfig() {
		ExpandClassConfigImpl expandClassConfig = new ExpandClassConfigImpl();
		return expandClassConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpandClass createExpandClass() {
		ExpandClassImpl expandClass = new ExpandClassImpl();
		return expandClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventSubscriptionConfig createEventSubscriptionConfig() {
		EventSubscriptionConfigImpl eventSubscriptionConfig = new EventSubscriptionConfigImpl();
		return eventSubscriptionConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QuartzConfig createQuartzConfig() {
		QuartzConfigImpl quartzConfig = new QuartzConfigImpl();
		return quartzConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptLanguageConfig createScriptLanguageConfig() {
		ScriptLanguageConfigImpl scriptLanguageConfig = new ScriptLanguageConfigImpl();
		return scriptLanguageConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptLanguage createScriptLanguage() {
		ScriptLanguageImpl scriptLanguage = new ScriptLanguageImpl();
		return scriptLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InternationalizationConfig createInternationalizationConfig() {
		InternationalizationConfigImpl internationalizationConfig = new InternationalizationConfigImpl();
		return internationalizationConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PigeonholeConfig createPigeonholeConfig() {
		PigeonholeConfigImpl pigeonholeConfig = new PigeonholeConfigImpl();
		return pigeonholeConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpandCmdConfig createExpandCmdConfig() {
		ExpandCmdConfigImpl expandCmdConfig = new ExpandCmdConfigImpl();
		return expandCmdConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpandCmd createExpandCmd() {
		ExpandCmdImpl expandCmd = new ExpandCmdImpl();
		return expandCmd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PriorityConfig createPriorityConfig() {
		PriorityConfigImpl priorityConfig = new PriorityConfigImpl();
		return priorityConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Priority createPriority() {
		PriorityImpl priority = new PriorityImpl();
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignPolicyConfig createAssignPolicyConfig() {
		AssignPolicyConfigImpl assignPolicyConfig = new AssignPolicyConfigImpl();
		return assignPolicyConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignPolicy createAssignPolicy() {
		AssignPolicyImpl assignPolicy = new AssignPolicyImpl();
		return assignPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DBType createDBTypeFromString(EDataType eDataType, String initialValue) {
		DBType result = DBType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDBTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreconfigPackage getCoreconfigPackage() {
		return (CoreconfigPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CoreconfigPackage getPackage() {
		return CoreconfigPackage.eINSTANCE;
	}

} //CoreconfigFactoryImpl
