/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo;
import com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy;
import com.founder.fix.bpmn2extensions.coreconfig.AssignPolicyConfig;
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigFactory;
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.DBType;
import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig;
import com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig;
import com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandClass;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandClassConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandCmdConfig;
import com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig;
import com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor;
import com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutorConfig;
import com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition;
import com.founder.fix.bpmn2extensions.coreconfig.GroupDefinitionConfig;
import com.founder.fix.bpmn2extensions.coreconfig.GroupInfo;
import com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig;
import com.founder.fix.bpmn2extensions.coreconfig.MailInfo;
import com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig;
import com.founder.fix.bpmn2extensions.coreconfig.Priority;
import com.founder.fix.bpmn2extensions.coreconfig.PriorityConfig;
import com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage;
import com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig;
import com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig;
import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig;
import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.bpmn2extensions.coreconfig.TaskType;
import com.founder.fix.bpmn2extensions.coreconfig.TaskTypeConfig;
import com.founder.fix.bpmn2extensions.coreconfig.TimeUnitType;
import com.founder.fix.bpmn2extensions.coreconfig.UserInfo;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CoreconfigPackageImpl extends EPackageImpl implements CoreconfigPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fixFlowConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataBaseConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass groupDefinitionConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataBaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass groupDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskCommandConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskCommandDefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass designerOrgConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass allUserInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass groupInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sysMailConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mailInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expandClassConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expandClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventSubscriptionConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass quartzConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scriptLanguageConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scriptLanguageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass internationalizationConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pigeonholeConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expandCmdConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expandCmdEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass priorityConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass priorityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assignPolicyConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assignPolicyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fixThreadPoolExecutorConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fixThreadPoolExecutorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskTypeConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dbTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum timeUnitTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CoreconfigPackageImpl() {
		super(eNS_URI, CoreconfigFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link CoreconfigPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CoreconfigPackage init() {
		if (isInited) return (CoreconfigPackage)EPackage.Registry.INSTANCE.getEPackage(CoreconfigPackage.eNS_URI);

		// Obtain or create and register package
		CoreconfigPackageImpl theCoreconfigPackage = (CoreconfigPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CoreconfigPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CoreconfigPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theCoreconfigPackage.createPackageContents();

		// Initialize created meta-data
		theCoreconfigPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCoreconfigPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CoreconfigPackage.eNS_URI, theCoreconfigPackage);
		return theCoreconfigPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFixFlowConfig() {
		return fixFlowConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_DataBaseConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_GroupDefinitionConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_TaskCommandConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_DesignerOrgConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_SysMailConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_ExpandClassConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_EventSubscriptionConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_QuartzConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_ScriptLanguageConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_InternationalizationConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_PigeonholeConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_ExpandCmdConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_PriorityConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_AssignPolicyConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_FixThreadPoolExecutorConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowConfig_TaskTypeConfig() {
		return (EReference)fixFlowConfigEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataBaseConfig() {
		return dataBaseConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataBaseConfig_DataBase() {
		return (EReference)dataBaseConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataBaseConfig_Selected() {
		return (EAttribute)dataBaseConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataBaseConfig_Mode() {
		return (EAttribute)dataBaseConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGroupDefinitionConfig() {
		return groupDefinitionConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGroupDefinitionConfig_GroupDefinition() {
		return (EReference)groupDefinitionConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataBase() {
		return dataBaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataBase_Id() {
		return (EAttribute)dataBaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataBase_Name() {
		return (EAttribute)dataBaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataBase_DriverClassName() {
		return (EAttribute)dataBaseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataBase_Url() {
		return (EAttribute)dataBaseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataBase_Username() {
		return (EAttribute)dataBaseEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataBase_Password() {
		return (EAttribute)dataBaseEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataBase_MaxActive() {
		return (EAttribute)dataBaseEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataBase_MaxIdle() {
		return (EAttribute)dataBaseEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataBase_MaxWait() {
		return (EAttribute)dataBaseEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataBase_PaginationImpl() {
		return (EAttribute)dataBaseEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataBase_Dbtype() {
		return (EAttribute)dataBaseEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGroupDefinition() {
		return groupDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupDefinition_Id() {
		return (EAttribute)groupDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupDefinition_Name() {
		return (EAttribute)groupDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupDefinition_GroupDefinitionImpl() {
		return (EAttribute)groupDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTaskCommandConfig() {
		return taskCommandConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskCommandConfig_TaskCommandDef() {
		return (EReference)taskCommandConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTaskCommandDef() {
		return taskCommandDefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskCommandDef_Id() {
		return (EAttribute)taskCommandDefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskCommandDef_Name() {
		return (EAttribute)taskCommandDefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskCommandDef_Command() {
		return (EAttribute)taskCommandDefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskCommandDef_Cmd() {
		return (EAttribute)taskCommandDefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskCommandDef_IsEnabled() {
		return (EAttribute)taskCommandDefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskCommandDef_Type() {
		return (EAttribute)taskCommandDefEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskCommandDef_Filter() {
		return (EAttribute)taskCommandDefEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskCommandDef_IsVerification() {
		return (EAttribute)taskCommandDefEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskCommandDef_IsSaveData() {
		return (EAttribute)taskCommandDefEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskCommandDef_IsSimulationRun() {
		return (EAttribute)taskCommandDefEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDesignerOrgConfig() {
		return designerOrgConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDesignerOrgConfig_AllUserInfo() {
		return (EReference)designerOrgConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDesignerOrgConfig_GroupInfo() {
		return (EReference)designerOrgConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAllUserInfo() {
		return allUserInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllUserInfo_IsEnabled() {
		return (EAttribute)allUserInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllUserInfo_UserIdField() {
		return (EAttribute)allUserInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllUserInfo_UserNameField() {
		return (EAttribute)allUserInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllUserInfo_SqlText() {
		return (EAttribute)allUserInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllUserInfo_Id() {
		return (EAttribute)allUserInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllUserInfo_Name() {
		return (EAttribute)allUserInfoEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllUserInfo_DataSource() {
		return (EAttribute)allUserInfoEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGroupInfo() {
		return groupInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupInfo_GroupId() {
		return (EAttribute)groupInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupInfo_GroupName() {
		return (EAttribute)groupInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupInfo_IsEnabled() {
		return (EAttribute)groupInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupInfo_IsDisplayUser() {
		return (EAttribute)groupInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupInfo_GroupIdField() {
		return (EAttribute)groupInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupInfo_GroupNameField() {
		return (EAttribute)groupInfoEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupInfo_SupGroupIdField() {
		return (EAttribute)groupInfoEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupInfo_SqlText() {
		return (EAttribute)groupInfoEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGroupInfo_UserInfo() {
		return (EReference)groupInfoEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupInfo_DataSource() {
		return (EAttribute)groupInfoEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupInfo_GroupDefinitionImpl() {
		return (EAttribute)groupInfoEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserInfo() {
		return userInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserInfo_UserIdField() {
		return (EAttribute)userInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserInfo_UserNameField() {
		return (EAttribute)userInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserInfo_GroupIdField() {
		return (EAttribute)userInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserInfo_SqlText() {
		return (EAttribute)userInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserInfo_DataSource() {
		return (EAttribute)userInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSysMailConfig() {
		return sysMailConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSysMailConfig_Selected() {
		return (EAttribute)sysMailConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSysMailConfig_MailInfo() {
		return (EReference)sysMailConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMailInfo() {
		return mailInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailInfo_MailAddress() {
		return (EAttribute)mailInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailInfo_MailName() {
		return (EAttribute)mailInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailInfo_SmtpHost() {
		return (EAttribute)mailInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailInfo_SmtpPort() {
		return (EAttribute)mailInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailInfo_UserName() {
		return (EAttribute)mailInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailInfo_PassWord() {
		return (EAttribute)mailInfoEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpandClassConfig() {
		return expandClassConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExpandClassConfig_ExpandClass() {
		return (EReference)expandClassConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpandClass() {
		return expandClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExpandClass_ClassId() {
		return (EAttribute)expandClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExpandClass_ClassName() {
		return (EAttribute)expandClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExpandClass_ClassInterface() {
		return (EAttribute)expandClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExpandClass_ClassImpl() {
		return (EAttribute)expandClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExpandClass_Remarks() {
		return (EAttribute)expandClassEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventSubscriptionConfig() {
		return eventSubscriptionConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventSubscriptionConfig_ServerAddress() {
		return (EAttribute)eventSubscriptionConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventSubscriptionConfig_ServerPort() {
		return (EAttribute)eventSubscriptionConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventSubscriptionConfig_MessageInfo() {
		return (EAttribute)eventSubscriptionConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventSubscriptionConfig_SignalInfo() {
		return (EAttribute)eventSubscriptionConfigEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventSubscriptionConfig_IsEnable() {
		return (EAttribute)eventSubscriptionConfigEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQuartzConfig() {
		return quartzConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuartzConfig_IsEnable() {
		return (EAttribute)quartzConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuartzConfig_IsDefaultConfig() {
		return (EAttribute)quartzConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuartzConfig_DataBaseId() {
		return (EAttribute)quartzConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScriptLanguageConfig() {
		return scriptLanguageConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScriptLanguageConfig_Selected() {
		return (EAttribute)scriptLanguageConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScriptLanguageConfig_ScriptLanguage() {
		return (EReference)scriptLanguageConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScriptLanguage() {
		return scriptLanguageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScriptLanguage_Id() {
		return (EAttribute)scriptLanguageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScriptLanguage_Name() {
		return (EAttribute)scriptLanguageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScriptLanguage_ClassImpl() {
		return (EAttribute)scriptLanguageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInternationalizationConfig() {
		return internationalizationConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInternationalizationConfig_IsEnable() {
		return (EAttribute)internationalizationConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInternationalizationConfig_FolderPath() {
		return (EAttribute)internationalizationConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPigeonholeConfig() {
		return pigeonholeConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPigeonholeConfig_ServerPath() {
		return (EAttribute)pigeonholeConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPigeonholeConfig_PdfPath() {
		return (EAttribute)pigeonholeConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPigeonholeConfig_Time() {
		return (EAttribute)pigeonholeConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPigeonholeConfig_Frequency() {
		return (EAttribute)pigeonholeConfigEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPigeonholeConfig_Week() {
		return (EAttribute)pigeonholeConfigEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPigeonholeConfig_Month() {
		return (EAttribute)pigeonholeConfigEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPigeonholeConfig_Code() {
		return (EAttribute)pigeonholeConfigEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPigeonholeConfig_IsEnable() {
		return (EAttribute)pigeonholeConfigEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpandCmdConfig() {
		return expandCmdConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExpandCmdConfig_ExpandCmd() {
		return (EReference)expandCmdConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpandCmd() {
		return expandCmdEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExpandCmd_Id() {
		return (EAttribute)expandCmdEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExpandCmd_Name() {
		return (EAttribute)expandCmdEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExpandCmd_Cmd() {
		return (EAttribute)expandCmdEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPriorityConfig() {
		return priorityConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPriorityConfig_Priority() {
		return (EReference)priorityConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPriority() {
		return priorityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPriority_Id() {
		return (EAttribute)priorityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPriority_Name() {
		return (EAttribute)priorityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPriority_Value() {
		return (EAttribute)priorityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPriority_Color() {
		return (EAttribute)priorityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPriority_Remark() {
		return (EAttribute)priorityEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssignPolicyConfig() {
		return assignPolicyConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssignPolicyConfig_AssignPolicy() {
		return (EReference)assignPolicyConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssignPolicy() {
		return assignPolicyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignPolicy_Id() {
		return (EAttribute)assignPolicyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignPolicy_Name() {
		return (EAttribute)assignPolicyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignPolicy_ClassImpl() {
		return (EAttribute)assignPolicyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignPolicy_Remarks() {
		return (EAttribute)assignPolicyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFixThreadPoolExecutorConfig() {
		return fixThreadPoolExecutorConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixThreadPoolExecutorConfig_FixThreadPoolExecutor() {
		return (EReference)fixThreadPoolExecutorConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFixThreadPoolExecutor() {
		return fixThreadPoolExecutorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFixThreadPoolExecutor_ThreadPoolKey() {
		return (EAttribute)fixThreadPoolExecutorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFixThreadPoolExecutor_ThreadPoolName() {
		return (EAttribute)fixThreadPoolExecutorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFixThreadPoolExecutor_CorePoolSize() {
		return (EAttribute)fixThreadPoolExecutorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFixThreadPoolExecutor_MaximumPoolSize() {
		return (EAttribute)fixThreadPoolExecutorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFixThreadPoolExecutor_KeepAliveTime() {
		return (EAttribute)fixThreadPoolExecutorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFixThreadPoolExecutor_TimeUnit() {
		return (EAttribute)fixThreadPoolExecutorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTaskTypeConfig() {
		return taskTypeConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskTypeConfig_TaskType() {
		return (EReference)taskTypeConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTaskType() {
		return taskTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskType_TypeId() {
		return (EAttribute)taskTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskType_TypeName() {
		return (EAttribute)taskTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDBType() {
		return dbTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTimeUnitType() {
		return timeUnitTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreconfigFactory getCoreconfigFactory() {
		return (CoreconfigFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		fixFlowConfigEClass = createEClass(FIX_FLOW_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__DATA_BASE_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__GROUP_DEFINITION_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__TASK_COMMAND_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__SYS_MAIL_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__EXPAND_CLASS_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__QUARTZ_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__SCRIPT_LANGUAGE_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__INTERNATIONALIZATION_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__EXPAND_CMD_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__PRIORITY_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__ASSIGN_POLICY_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__FIX_THREAD_POOL_EXECUTOR_CONFIG);
		createEReference(fixFlowConfigEClass, FIX_FLOW_CONFIG__TASK_TYPE_CONFIG);

		dataBaseConfigEClass = createEClass(DATA_BASE_CONFIG);
		createEReference(dataBaseConfigEClass, DATA_BASE_CONFIG__DATA_BASE);
		createEAttribute(dataBaseConfigEClass, DATA_BASE_CONFIG__SELECTED);
		createEAttribute(dataBaseConfigEClass, DATA_BASE_CONFIG__MODE);

		groupDefinitionConfigEClass = createEClass(GROUP_DEFINITION_CONFIG);
		createEReference(groupDefinitionConfigEClass, GROUP_DEFINITION_CONFIG__GROUP_DEFINITION);

		dataBaseEClass = createEClass(DATA_BASE);
		createEAttribute(dataBaseEClass, DATA_BASE__ID);
		createEAttribute(dataBaseEClass, DATA_BASE__NAME);
		createEAttribute(dataBaseEClass, DATA_BASE__DRIVER_CLASS_NAME);
		createEAttribute(dataBaseEClass, DATA_BASE__URL);
		createEAttribute(dataBaseEClass, DATA_BASE__USERNAME);
		createEAttribute(dataBaseEClass, DATA_BASE__PASSWORD);
		createEAttribute(dataBaseEClass, DATA_BASE__MAX_ACTIVE);
		createEAttribute(dataBaseEClass, DATA_BASE__MAX_IDLE);
		createEAttribute(dataBaseEClass, DATA_BASE__MAX_WAIT);
		createEAttribute(dataBaseEClass, DATA_BASE__PAGINATION_IMPL);
		createEAttribute(dataBaseEClass, DATA_BASE__DBTYPE);

		groupDefinitionEClass = createEClass(GROUP_DEFINITION);
		createEAttribute(groupDefinitionEClass, GROUP_DEFINITION__ID);
		createEAttribute(groupDefinitionEClass, GROUP_DEFINITION__NAME);
		createEAttribute(groupDefinitionEClass, GROUP_DEFINITION__GROUP_DEFINITION_IMPL);

		taskCommandConfigEClass = createEClass(TASK_COMMAND_CONFIG);
		createEReference(taskCommandConfigEClass, TASK_COMMAND_CONFIG__TASK_COMMAND_DEF);

		taskCommandDefEClass = createEClass(TASK_COMMAND_DEF);
		createEAttribute(taskCommandDefEClass, TASK_COMMAND_DEF__ID);
		createEAttribute(taskCommandDefEClass, TASK_COMMAND_DEF__NAME);
		createEAttribute(taskCommandDefEClass, TASK_COMMAND_DEF__COMMAND);
		createEAttribute(taskCommandDefEClass, TASK_COMMAND_DEF__CMD);
		createEAttribute(taskCommandDefEClass, TASK_COMMAND_DEF__IS_ENABLED);
		createEAttribute(taskCommandDefEClass, TASK_COMMAND_DEF__TYPE);
		createEAttribute(taskCommandDefEClass, TASK_COMMAND_DEF__FILTER);
		createEAttribute(taskCommandDefEClass, TASK_COMMAND_DEF__IS_VERIFICATION);
		createEAttribute(taskCommandDefEClass, TASK_COMMAND_DEF__IS_SAVE_DATA);
		createEAttribute(taskCommandDefEClass, TASK_COMMAND_DEF__IS_SIMULATION_RUN);

		designerOrgConfigEClass = createEClass(DESIGNER_ORG_CONFIG);
		createEReference(designerOrgConfigEClass, DESIGNER_ORG_CONFIG__ALL_USER_INFO);
		createEReference(designerOrgConfigEClass, DESIGNER_ORG_CONFIG__GROUP_INFO);

		allUserInfoEClass = createEClass(ALL_USER_INFO);
		createEAttribute(allUserInfoEClass, ALL_USER_INFO__IS_ENABLED);
		createEAttribute(allUserInfoEClass, ALL_USER_INFO__USER_ID_FIELD);
		createEAttribute(allUserInfoEClass, ALL_USER_INFO__USER_NAME_FIELD);
		createEAttribute(allUserInfoEClass, ALL_USER_INFO__SQL_TEXT);
		createEAttribute(allUserInfoEClass, ALL_USER_INFO__ID);
		createEAttribute(allUserInfoEClass, ALL_USER_INFO__NAME);
		createEAttribute(allUserInfoEClass, ALL_USER_INFO__DATA_SOURCE);

		groupInfoEClass = createEClass(GROUP_INFO);
		createEAttribute(groupInfoEClass, GROUP_INFO__GROUP_ID);
		createEAttribute(groupInfoEClass, GROUP_INFO__GROUP_NAME);
		createEAttribute(groupInfoEClass, GROUP_INFO__IS_ENABLED);
		createEAttribute(groupInfoEClass, GROUP_INFO__IS_DISPLAY_USER);
		createEAttribute(groupInfoEClass, GROUP_INFO__GROUP_ID_FIELD);
		createEAttribute(groupInfoEClass, GROUP_INFO__GROUP_NAME_FIELD);
		createEAttribute(groupInfoEClass, GROUP_INFO__SUP_GROUP_ID_FIELD);
		createEAttribute(groupInfoEClass, GROUP_INFO__SQL_TEXT);
		createEReference(groupInfoEClass, GROUP_INFO__USER_INFO);
		createEAttribute(groupInfoEClass, GROUP_INFO__DATA_SOURCE);
		createEAttribute(groupInfoEClass, GROUP_INFO__GROUP_DEFINITION_IMPL);

		userInfoEClass = createEClass(USER_INFO);
		createEAttribute(userInfoEClass, USER_INFO__USER_ID_FIELD);
		createEAttribute(userInfoEClass, USER_INFO__USER_NAME_FIELD);
		createEAttribute(userInfoEClass, USER_INFO__GROUP_ID_FIELD);
		createEAttribute(userInfoEClass, USER_INFO__SQL_TEXT);
		createEAttribute(userInfoEClass, USER_INFO__DATA_SOURCE);

		sysMailConfigEClass = createEClass(SYS_MAIL_CONFIG);
		createEAttribute(sysMailConfigEClass, SYS_MAIL_CONFIG__SELECTED);
		createEReference(sysMailConfigEClass, SYS_MAIL_CONFIG__MAIL_INFO);

		mailInfoEClass = createEClass(MAIL_INFO);
		createEAttribute(mailInfoEClass, MAIL_INFO__MAIL_ADDRESS);
		createEAttribute(mailInfoEClass, MAIL_INFO__MAIL_NAME);
		createEAttribute(mailInfoEClass, MAIL_INFO__SMTP_HOST);
		createEAttribute(mailInfoEClass, MAIL_INFO__SMTP_PORT);
		createEAttribute(mailInfoEClass, MAIL_INFO__USER_NAME);
		createEAttribute(mailInfoEClass, MAIL_INFO__PASS_WORD);

		expandClassConfigEClass = createEClass(EXPAND_CLASS_CONFIG);
		createEReference(expandClassConfigEClass, EXPAND_CLASS_CONFIG__EXPAND_CLASS);

		expandClassEClass = createEClass(EXPAND_CLASS);
		createEAttribute(expandClassEClass, EXPAND_CLASS__CLASS_ID);
		createEAttribute(expandClassEClass, EXPAND_CLASS__CLASS_NAME);
		createEAttribute(expandClassEClass, EXPAND_CLASS__CLASS_INTERFACE);
		createEAttribute(expandClassEClass, EXPAND_CLASS__CLASS_IMPL);
		createEAttribute(expandClassEClass, EXPAND_CLASS__REMARKS);

		eventSubscriptionConfigEClass = createEClass(EVENT_SUBSCRIPTION_CONFIG);
		createEAttribute(eventSubscriptionConfigEClass, EVENT_SUBSCRIPTION_CONFIG__SERVER_ADDRESS);
		createEAttribute(eventSubscriptionConfigEClass, EVENT_SUBSCRIPTION_CONFIG__SERVER_PORT);
		createEAttribute(eventSubscriptionConfigEClass, EVENT_SUBSCRIPTION_CONFIG__MESSAGE_INFO);
		createEAttribute(eventSubscriptionConfigEClass, EVENT_SUBSCRIPTION_CONFIG__SIGNAL_INFO);
		createEAttribute(eventSubscriptionConfigEClass, EVENT_SUBSCRIPTION_CONFIG__IS_ENABLE);

		quartzConfigEClass = createEClass(QUARTZ_CONFIG);
		createEAttribute(quartzConfigEClass, QUARTZ_CONFIG__IS_ENABLE);
		createEAttribute(quartzConfigEClass, QUARTZ_CONFIG__IS_DEFAULT_CONFIG);
		createEAttribute(quartzConfigEClass, QUARTZ_CONFIG__DATA_BASE_ID);

		scriptLanguageConfigEClass = createEClass(SCRIPT_LANGUAGE_CONFIG);
		createEAttribute(scriptLanguageConfigEClass, SCRIPT_LANGUAGE_CONFIG__SELECTED);
		createEReference(scriptLanguageConfigEClass, SCRIPT_LANGUAGE_CONFIG__SCRIPT_LANGUAGE);

		scriptLanguageEClass = createEClass(SCRIPT_LANGUAGE);
		createEAttribute(scriptLanguageEClass, SCRIPT_LANGUAGE__ID);
		createEAttribute(scriptLanguageEClass, SCRIPT_LANGUAGE__NAME);
		createEAttribute(scriptLanguageEClass, SCRIPT_LANGUAGE__CLASS_IMPL);

		internationalizationConfigEClass = createEClass(INTERNATIONALIZATION_CONFIG);
		createEAttribute(internationalizationConfigEClass, INTERNATIONALIZATION_CONFIG__IS_ENABLE);
		createEAttribute(internationalizationConfigEClass, INTERNATIONALIZATION_CONFIG__FOLDER_PATH);

		pigeonholeConfigEClass = createEClass(PIGEONHOLE_CONFIG);
		createEAttribute(pigeonholeConfigEClass, PIGEONHOLE_CONFIG__SERVER_PATH);
		createEAttribute(pigeonholeConfigEClass, PIGEONHOLE_CONFIG__PDF_PATH);
		createEAttribute(pigeonholeConfigEClass, PIGEONHOLE_CONFIG__TIME);
		createEAttribute(pigeonholeConfigEClass, PIGEONHOLE_CONFIG__FREQUENCY);
		createEAttribute(pigeonholeConfigEClass, PIGEONHOLE_CONFIG__WEEK);
		createEAttribute(pigeonholeConfigEClass, PIGEONHOLE_CONFIG__MONTH);
		createEAttribute(pigeonholeConfigEClass, PIGEONHOLE_CONFIG__CODE);
		createEAttribute(pigeonholeConfigEClass, PIGEONHOLE_CONFIG__IS_ENABLE);

		expandCmdConfigEClass = createEClass(EXPAND_CMD_CONFIG);
		createEReference(expandCmdConfigEClass, EXPAND_CMD_CONFIG__EXPAND_CMD);

		expandCmdEClass = createEClass(EXPAND_CMD);
		createEAttribute(expandCmdEClass, EXPAND_CMD__ID);
		createEAttribute(expandCmdEClass, EXPAND_CMD__NAME);
		createEAttribute(expandCmdEClass, EXPAND_CMD__CMD);

		priorityConfigEClass = createEClass(PRIORITY_CONFIG);
		createEReference(priorityConfigEClass, PRIORITY_CONFIG__PRIORITY);

		priorityEClass = createEClass(PRIORITY);
		createEAttribute(priorityEClass, PRIORITY__ID);
		createEAttribute(priorityEClass, PRIORITY__NAME);
		createEAttribute(priorityEClass, PRIORITY__VALUE);
		createEAttribute(priorityEClass, PRIORITY__COLOR);
		createEAttribute(priorityEClass, PRIORITY__REMARK);

		assignPolicyConfigEClass = createEClass(ASSIGN_POLICY_CONFIG);
		createEReference(assignPolicyConfigEClass, ASSIGN_POLICY_CONFIG__ASSIGN_POLICY);

		assignPolicyEClass = createEClass(ASSIGN_POLICY);
		createEAttribute(assignPolicyEClass, ASSIGN_POLICY__ID);
		createEAttribute(assignPolicyEClass, ASSIGN_POLICY__NAME);
		createEAttribute(assignPolicyEClass, ASSIGN_POLICY__CLASS_IMPL);
		createEAttribute(assignPolicyEClass, ASSIGN_POLICY__REMARKS);

		fixThreadPoolExecutorConfigEClass = createEClass(FIX_THREAD_POOL_EXECUTOR_CONFIG);
		createEReference(fixThreadPoolExecutorConfigEClass, FIX_THREAD_POOL_EXECUTOR_CONFIG__FIX_THREAD_POOL_EXECUTOR);

		fixThreadPoolExecutorEClass = createEClass(FIX_THREAD_POOL_EXECUTOR);
		createEAttribute(fixThreadPoolExecutorEClass, FIX_THREAD_POOL_EXECUTOR__THREAD_POOL_KEY);
		createEAttribute(fixThreadPoolExecutorEClass, FIX_THREAD_POOL_EXECUTOR__THREAD_POOL_NAME);
		createEAttribute(fixThreadPoolExecutorEClass, FIX_THREAD_POOL_EXECUTOR__CORE_POOL_SIZE);
		createEAttribute(fixThreadPoolExecutorEClass, FIX_THREAD_POOL_EXECUTOR__MAXIMUM_POOL_SIZE);
		createEAttribute(fixThreadPoolExecutorEClass, FIX_THREAD_POOL_EXECUTOR__KEEP_ALIVE_TIME);
		createEAttribute(fixThreadPoolExecutorEClass, FIX_THREAD_POOL_EXECUTOR__TIME_UNIT);

		taskTypeConfigEClass = createEClass(TASK_TYPE_CONFIG);
		createEReference(taskTypeConfigEClass, TASK_TYPE_CONFIG__TASK_TYPE);

		taskTypeEClass = createEClass(TASK_TYPE);
		createEAttribute(taskTypeEClass, TASK_TYPE__TYPE_ID);
		createEAttribute(taskTypeEClass, TASK_TYPE__TYPE_NAME);

		// Create enums
		dbTypeEEnum = createEEnum(DB_TYPE);
		timeUnitTypeEEnum = createEEnum(TIME_UNIT_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(fixFlowConfigEClass, FixFlowConfig.class, "FixFlowConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFixFlowConfig_DataBaseConfig(), this.getDataBaseConfig(), null, "dataBaseConfig", null, 1, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_GroupDefinitionConfig(), this.getGroupDefinitionConfig(), null, "groupDefinitionConfig", null, 1, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_TaskCommandConfig(), this.getTaskCommandConfig(), null, "taskCommandConfig", null, 1, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_DesignerOrgConfig(), this.getDesignerOrgConfig(), null, "designerOrgConfig", null, 1, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_SysMailConfig(), this.getSysMailConfig(), null, "sysMailConfig", null, 1, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_ExpandClassConfig(), this.getExpandClassConfig(), null, "expandClassConfig", null, 0, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_EventSubscriptionConfig(), this.getEventSubscriptionConfig(), null, "eventSubscriptionConfig", null, 1, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_QuartzConfig(), this.getQuartzConfig(), null, "quartzConfig", null, 1, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_ScriptLanguageConfig(), this.getScriptLanguageConfig(), null, "scriptLanguageConfig", null, 1, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_InternationalizationConfig(), this.getInternationalizationConfig(), null, "internationalizationConfig", null, 1, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_PigeonholeConfig(), this.getPigeonholeConfig(), null, "pigeonholeConfig", null, 1, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_ExpandCmdConfig(), this.getExpandCmdConfig(), null, "expandCmdConfig", null, 1, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_PriorityConfig(), this.getPriorityConfig(), null, "priorityConfig", null, 0, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_AssignPolicyConfig(), this.getAssignPolicyConfig(), null, "assignPolicyConfig", null, 0, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_FixThreadPoolExecutorConfig(), this.getFixThreadPoolExecutorConfig(), null, "fixThreadPoolExecutorConfig", null, 0, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFixFlowConfig_TaskTypeConfig(), this.getTaskTypeConfig(), null, "taskTypeConfig", null, 0, 1, FixFlowConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataBaseConfigEClass, DataBaseConfig.class, "DataBaseConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataBaseConfig_DataBase(), this.getDataBase(), null, "dataBase", null, 1, -1, DataBaseConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataBaseConfig_Selected(), ecorePackage.getEString(), "selected", null, 1, 1, DataBaseConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataBaseConfig_Mode(), ecorePackage.getEString(), "mode", null, 1, 1, DataBaseConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(groupDefinitionConfigEClass, GroupDefinitionConfig.class, "GroupDefinitionConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGroupDefinitionConfig_GroupDefinition(), this.getGroupDefinition(), null, "groupDefinition", null, 1, -1, GroupDefinitionConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataBaseEClass, DataBase.class, "DataBase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataBase_Id(), ecorePackage.getEString(), "id", null, 1, 1, DataBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataBase_Name(), ecorePackage.getEString(), "name", null, 1, 1, DataBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataBase_DriverClassName(), ecorePackage.getEString(), "driverClassName", null, 1, 1, DataBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataBase_Url(), ecorePackage.getEString(), "url", null, 1, 1, DataBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataBase_Username(), ecorePackage.getEString(), "username", null, 1, 1, DataBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataBase_Password(), ecorePackage.getEString(), "password", null, 1, 1, DataBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataBase_MaxActive(), ecorePackage.getEString(), "maxActive", null, 1, 1, DataBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataBase_MaxIdle(), ecorePackage.getEString(), "maxIdle", null, 1, 1, DataBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataBase_MaxWait(), ecorePackage.getEString(), "maxWait", null, 1, 1, DataBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataBase_PaginationImpl(), ecorePackage.getEString(), "paginationImpl", null, 1, 1, DataBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataBase_Dbtype(), this.getDBType(), "dbtype", null, 1, 1, DataBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(groupDefinitionEClass, GroupDefinition.class, "GroupDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGroupDefinition_Id(), ecorePackage.getEString(), "id", null, 1, 1, GroupDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroupDefinition_Name(), ecorePackage.getEString(), "name", null, 1, 1, GroupDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroupDefinition_GroupDefinitionImpl(), ecorePackage.getEString(), "groupDefinitionImpl", null, 1, 1, GroupDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(taskCommandConfigEClass, TaskCommandConfig.class, "TaskCommandConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTaskCommandConfig_TaskCommandDef(), this.getTaskCommandDef(), null, "taskCommandDef", null, 0, -1, TaskCommandConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(taskCommandDefEClass, TaskCommandDef.class, "TaskCommandDef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTaskCommandDef_Id(), ecorePackage.getEString(), "id", null, 1, 1, TaskCommandDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskCommandDef_Name(), ecorePackage.getEString(), "name", null, 1, 1, TaskCommandDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskCommandDef_Command(), ecorePackage.getEString(), "command", null, 1, 1, TaskCommandDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskCommandDef_Cmd(), ecorePackage.getEString(), "cmd", null, 1, 1, TaskCommandDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskCommandDef_IsEnabled(), ecorePackage.getEString(), "isEnabled", null, 1, 1, TaskCommandDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskCommandDef_Type(), ecorePackage.getEString(), "type", null, 1, 1, TaskCommandDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskCommandDef_Filter(), ecorePackage.getEString(), "filter", null, 0, 1, TaskCommandDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskCommandDef_IsVerification(), ecorePackage.getEString(), "isVerification", null, 0, 1, TaskCommandDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskCommandDef_IsSaveData(), ecorePackage.getEString(), "isSaveData", null, 0, 1, TaskCommandDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskCommandDef_IsSimulationRun(), ecorePackage.getEString(), "isSimulationRun", null, 0, 1, TaskCommandDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(designerOrgConfigEClass, DesignerOrgConfig.class, "DesignerOrgConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDesignerOrgConfig_AllUserInfo(), this.getAllUserInfo(), null, "allUserInfo", null, 0, 1, DesignerOrgConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDesignerOrgConfig_GroupInfo(), this.getGroupInfo(), null, "groupInfo", null, 0, -1, DesignerOrgConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(allUserInfoEClass, AllUserInfo.class, "AllUserInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAllUserInfo_IsEnabled(), ecorePackage.getEString(), "isEnabled", null, 1, 1, AllUserInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAllUserInfo_UserIdField(), ecorePackage.getEString(), "userIdField", null, 1, 1, AllUserInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAllUserInfo_UserNameField(), ecorePackage.getEString(), "userNameField", null, 1, 1, AllUserInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAllUserInfo_SqlText(), ecorePackage.getEString(), "sqlText", null, 1, 1, AllUserInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAllUserInfo_Id(), ecorePackage.getEString(), "id", null, 1, 1, AllUserInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAllUserInfo_Name(), ecorePackage.getEString(), "name", null, 1, 1, AllUserInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAllUserInfo_DataSource(), ecorePackage.getEString(), "dataSource", null, 1, 1, AllUserInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(groupInfoEClass, GroupInfo.class, "GroupInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGroupInfo_GroupId(), ecorePackage.getEString(), "groupId", null, 1, 1, GroupInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroupInfo_GroupName(), ecorePackage.getEString(), "groupName", null, 1, 1, GroupInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroupInfo_IsEnabled(), ecorePackage.getEString(), "isEnabled", null, 1, 1, GroupInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroupInfo_IsDisplayUser(), ecorePackage.getEString(), "isDisplayUser", null, 1, 1, GroupInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroupInfo_GroupIdField(), ecorePackage.getEString(), "groupIdField", null, 1, 1, GroupInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroupInfo_GroupNameField(), ecorePackage.getEString(), "groupNameField", null, 1, 1, GroupInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroupInfo_SupGroupIdField(), ecorePackage.getEString(), "supGroupIdField", null, 1, 1, GroupInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroupInfo_SqlText(), ecorePackage.getEString(), "sqlText", null, 1, 1, GroupInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGroupInfo_UserInfo(), this.getUserInfo(), null, "userInfo", null, 0, 1, GroupInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroupInfo_DataSource(), ecorePackage.getEString(), "dataSource", null, 1, 1, GroupInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroupInfo_GroupDefinitionImpl(), ecorePackage.getEString(), "groupDefinitionImpl", null, 1, 1, GroupInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userInfoEClass, UserInfo.class, "UserInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserInfo_UserIdField(), ecorePackage.getEString(), "userIdField", null, 1, 1, UserInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUserInfo_UserNameField(), ecorePackage.getEString(), "userNameField", null, 1, 1, UserInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUserInfo_GroupIdField(), ecorePackage.getEString(), "groupIdField", null, 1, 1, UserInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUserInfo_SqlText(), ecorePackage.getEString(), "sqlText", null, 1, 1, UserInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUserInfo_DataSource(), ecorePackage.getEString(), "dataSource", null, 1, 1, UserInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sysMailConfigEClass, SysMailConfig.class, "SysMailConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSysMailConfig_Selected(), ecorePackage.getEString(), "selected", null, 1, 1, SysMailConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSysMailConfig_MailInfo(), this.getMailInfo(), null, "mailInfo", null, 0, -1, SysMailConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mailInfoEClass, MailInfo.class, "MailInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMailInfo_MailAddress(), ecorePackage.getEString(), "mailAddress", null, 1, 1, MailInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailInfo_MailName(), ecorePackage.getEString(), "mailName", null, 1, 1, MailInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailInfo_SmtpHost(), ecorePackage.getEString(), "smtpHost", null, 1, 1, MailInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailInfo_SmtpPort(), ecorePackage.getEString(), "smtpPort", null, 1, 1, MailInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailInfo_UserName(), ecorePackage.getEString(), "userName", null, 1, 1, MailInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailInfo_PassWord(), ecorePackage.getEString(), "passWord", null, 1, 1, MailInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(expandClassConfigEClass, ExpandClassConfig.class, "ExpandClassConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExpandClassConfig_ExpandClass(), this.getExpandClass(), null, "expandClass", null, 0, -1, ExpandClassConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(expandClassEClass, ExpandClass.class, "ExpandClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExpandClass_ClassId(), ecorePackage.getEString(), "classId", null, 1, 1, ExpandClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExpandClass_ClassName(), ecorePackage.getEString(), "className", null, 1, 1, ExpandClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExpandClass_ClassInterface(), ecorePackage.getEString(), "classInterface", null, 1, 1, ExpandClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExpandClass_ClassImpl(), ecorePackage.getEString(), "classImpl", null, 1, 1, ExpandClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExpandClass_Remarks(), ecorePackage.getEString(), "remarks", null, 1, 1, ExpandClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventSubscriptionConfigEClass, EventSubscriptionConfig.class, "EventSubscriptionConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEventSubscriptionConfig_ServerAddress(), ecorePackage.getEString(), "serverAddress", null, 0, 1, EventSubscriptionConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventSubscriptionConfig_ServerPort(), ecorePackage.getEString(), "serverPort", null, 0, 1, EventSubscriptionConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventSubscriptionConfig_MessageInfo(), ecorePackage.getEString(), "messageInfo", null, 0, 1, EventSubscriptionConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventSubscriptionConfig_SignalInfo(), ecorePackage.getEString(), "signalInfo", null, 0, 1, EventSubscriptionConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventSubscriptionConfig_IsEnable(), ecorePackage.getEString(), "isEnable", null, 1, 1, EventSubscriptionConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(quartzConfigEClass, QuartzConfig.class, "QuartzConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getQuartzConfig_IsEnable(), ecorePackage.getEString(), "isEnable", null, 1, 1, QuartzConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQuartzConfig_IsDefaultConfig(), ecorePackage.getEString(), "isDefaultConfig", null, 1, 1, QuartzConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQuartzConfig_DataBaseId(), ecorePackage.getEString(), "dataBaseId", null, 0, 1, QuartzConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scriptLanguageConfigEClass, ScriptLanguageConfig.class, "ScriptLanguageConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScriptLanguageConfig_Selected(), ecorePackage.getEString(), "selected", null, 1, 1, ScriptLanguageConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScriptLanguageConfig_ScriptLanguage(), this.getScriptLanguage(), null, "scriptLanguage", null, 0, -1, ScriptLanguageConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scriptLanguageEClass, ScriptLanguage.class, "ScriptLanguage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScriptLanguage_Id(), ecorePackage.getEString(), "id", null, 1, 1, ScriptLanguage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptLanguage_Name(), ecorePackage.getEString(), "name", null, 0, 1, ScriptLanguage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptLanguage_ClassImpl(), ecorePackage.getEString(), "classImpl", null, 1, 1, ScriptLanguage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(internationalizationConfigEClass, InternationalizationConfig.class, "InternationalizationConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInternationalizationConfig_IsEnable(), ecorePackage.getEString(), "isEnable", null, 1, 1, InternationalizationConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInternationalizationConfig_FolderPath(), ecorePackage.getEString(), "folderPath", null, 1, 1, InternationalizationConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pigeonholeConfigEClass, PigeonholeConfig.class, "PigeonholeConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPigeonholeConfig_ServerPath(), ecorePackage.getEString(), "serverPath", null, 0, 1, PigeonholeConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPigeonholeConfig_PdfPath(), ecorePackage.getEString(), "pdfPath", null, 0, 1, PigeonholeConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPigeonholeConfig_Time(), ecorePackage.getEString(), "time", null, 0, 1, PigeonholeConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPigeonholeConfig_Frequency(), ecorePackage.getEString(), "frequency", null, 0, 1, PigeonholeConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPigeonholeConfig_Week(), ecorePackage.getEString(), "week", null, 0, 1, PigeonholeConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPigeonholeConfig_Month(), ecorePackage.getEString(), "month", null, 0, 1, PigeonholeConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPigeonholeConfig_Code(), ecorePackage.getEString(), "code", null, 0, 1, PigeonholeConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPigeonholeConfig_IsEnable(), ecorePackage.getEString(), "isEnable", null, 1, 1, PigeonholeConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(expandCmdConfigEClass, ExpandCmdConfig.class, "ExpandCmdConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExpandCmdConfig_ExpandCmd(), this.getExpandCmd(), null, "expandCmd", null, 0, -1, ExpandCmdConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(expandCmdEClass, ExpandCmd.class, "ExpandCmd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExpandCmd_Id(), ecorePackage.getEString(), "id", null, 1, 1, ExpandCmd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExpandCmd_Name(), ecorePackage.getEString(), "name", null, 1, 1, ExpandCmd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExpandCmd_Cmd(), ecorePackage.getEString(), "cmd", null, 1, 1, ExpandCmd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(priorityConfigEClass, PriorityConfig.class, "PriorityConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPriorityConfig_Priority(), this.getPriority(), null, "priority", null, 0, -1, PriorityConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(priorityEClass, Priority.class, "Priority", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPriority_Id(), ecorePackage.getEString(), "id", null, 1, 1, Priority.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPriority_Name(), ecorePackage.getEString(), "name", null, 1, 1, Priority.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPriority_Value(), ecorePackage.getEString(), "value", null, 1, 1, Priority.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPriority_Color(), ecorePackage.getEString(), "color", null, 0, 1, Priority.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPriority_Remark(), ecorePackage.getEString(), "remark", null, 0, 1, Priority.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(assignPolicyConfigEClass, AssignPolicyConfig.class, "AssignPolicyConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssignPolicyConfig_AssignPolicy(), this.getAssignPolicy(), null, "assignPolicy", null, 0, -1, AssignPolicyConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(assignPolicyEClass, AssignPolicy.class, "AssignPolicy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAssignPolicy_Id(), ecorePackage.getEString(), "id", null, 1, 1, AssignPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssignPolicy_Name(), ecorePackage.getEString(), "name", null, 1, 1, AssignPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssignPolicy_ClassImpl(), ecorePackage.getEString(), "classImpl", null, 1, 1, AssignPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssignPolicy_Remarks(), ecorePackage.getEString(), "remarks", null, 0, 1, AssignPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fixThreadPoolExecutorConfigEClass, FixThreadPoolExecutorConfig.class, "FixThreadPoolExecutorConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFixThreadPoolExecutorConfig_FixThreadPoolExecutor(), this.getFixThreadPoolExecutor(), null, "fixThreadPoolExecutor", null, 0, -1, FixThreadPoolExecutorConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fixThreadPoolExecutorEClass, FixThreadPoolExecutor.class, "FixThreadPoolExecutor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFixThreadPoolExecutor_ThreadPoolKey(), ecorePackage.getEString(), "threadPoolKey", null, 1, 1, FixThreadPoolExecutor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFixThreadPoolExecutor_ThreadPoolName(), ecorePackage.getEString(), "threadPoolName", null, 0, 1, FixThreadPoolExecutor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFixThreadPoolExecutor_CorePoolSize(), ecorePackage.getEInt(), "corePoolSize", null, 1, 1, FixThreadPoolExecutor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFixThreadPoolExecutor_MaximumPoolSize(), ecorePackage.getEInt(), "maximumPoolSize", null, 1, 1, FixThreadPoolExecutor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFixThreadPoolExecutor_KeepAliveTime(), ecorePackage.getELong(), "keepAliveTime", null, 1, 1, FixThreadPoolExecutor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFixThreadPoolExecutor_TimeUnit(), this.getTimeUnitType(), "timeUnit", null, 1, 1, FixThreadPoolExecutor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(taskTypeConfigEClass, TaskTypeConfig.class, "TaskTypeConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTaskTypeConfig_TaskType(), this.getTaskType(), null, "taskType", null, 0, -1, TaskTypeConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(taskTypeEClass, TaskType.class, "TaskType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTaskType_TypeId(), ecorePackage.getEString(), "typeId", null, 0, 1, TaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskType_TypeName(), ecorePackage.getEString(), "typeName", null, 0, 1, TaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(dbTypeEEnum, DBType.class, "DBType");
		addEEnumLiteral(dbTypeEEnum, DBType.OTHER);
		addEEnumLiteral(dbTypeEEnum, DBType.ORACLE);
		addEEnumLiteral(dbTypeEEnum, DBType.SQLSERVER);
		addEEnumLiteral(dbTypeEEnum, DBType.DB2);
		addEEnumLiteral(dbTypeEEnum, DBType.MYSQL);

		initEEnum(timeUnitTypeEEnum, TimeUnitType.class, "TimeUnitType");
		addEEnumLiteral(timeUnitTypeEEnum, TimeUnitType.DAYS);
		addEEnumLiteral(timeUnitTypeEEnum, TimeUnitType.HOURS);
		addEEnumLiteral(timeUnitTypeEEnum, TimeUnitType.MICROSECONDS);
		addEEnumLiteral(timeUnitTypeEEnum, TimeUnitType.MILLISECONDS);
		addEEnumLiteral(timeUnitTypeEEnum, TimeUnitType.MINUTES);
		addEEnumLiteral(timeUnitTypeEEnum, TimeUnitType.NANOSECONDS);
		addEEnumLiteral(timeUnitTypeEEnum, TimeUnitType.SECONDS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (fixFlowConfigEClass, 
		   source, 
		   new String[] {
			 "name", "fixFlowConfig"
		   });		
		addAnnotation
		  (getFixFlowConfig_DataBaseConfig(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getFixFlowConfig_GroupDefinitionConfig(), 
		   source, 
		   new String[] {
			 "name", "groupDefinitionConfig",
			 "kind", "element"
		   });		
		addAnnotation
		  (getFixFlowConfig_TaskCommandConfig(), 
		   source, 
		   new String[] {
			 "name", "taskCommandConfig",
			 "kind", "element"
		   });		
		addAnnotation
		  (getFixFlowConfig_DesignerOrgConfig(), 
		   source, 
		   new String[] {
			 "name", "designerOrgConfig",
			 "kind", "element"
		   });		
		addAnnotation
		  (getFixFlowConfig_SysMailConfig(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getFixFlowConfig_ExpandClassConfig(), 
		   source, 
		   new String[] {
			 "name", "expandClassConfig",
			 "kind", "element"
		   });		
		addAnnotation
		  (getFixFlowConfig_EventSubscriptionConfig(), 
		   source, 
		   new String[] {
			 "name", "eventSubscriptionConfig",
			 "kind", "element"
		   });		
		addAnnotation
		  (getFixFlowConfig_QuartzConfig(), 
		   source, 
		   new String[] {
			 "name", "quartzConfig",
			 "kind", "element"
		   });		
		addAnnotation
		  (getFixFlowConfig_ScriptLanguageConfig(), 
		   source, 
		   new String[] {
			 "name", "scriptLanguageConfig",
			 "kind", "element"
		   });		
		addAnnotation
		  (getFixFlowConfig_InternationalizationConfig(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "internationalizationConfig"
		   });		
		addAnnotation
		  (getFixFlowConfig_PigeonholeConfig(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getFixFlowConfig_ExpandCmdConfig(), 
		   source, 
		   new String[] {
			 "name", "expandCmdConfig",
			 "kind", "element"
		   });		
		addAnnotation
		  (getFixFlowConfig_PriorityConfig(), 
		   source, 
		   new String[] {
			 "name", "priorityConfig",
			 "kind", "element"
		   });		
		addAnnotation
		  (getFixFlowConfig_AssignPolicyConfig(), 
		   source, 
		   new String[] {
			 "name", "assignPolicyConfig",
			 "kind", "element"
		   });		
		addAnnotation
		  (getFixFlowConfig_FixThreadPoolExecutorConfig(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "fixThreadPoolExecutorConfig"
		   });		
		addAnnotation
		  (getFixFlowConfig_TaskTypeConfig(), 
		   source, 
		   new String[] {
			 "name", "taskTypeConfig",
			 "kind", "element"
		   });		
		addAnnotation
		  (dataBaseConfigEClass, 
		   source, 
		   new String[] {
			 "name", "dataBaseConfig"
		   });		
		addAnnotation
		  (getDataBaseConfig_DataBase(), 
		   source, 
		   new String[] {
			 "name", "dataBase",
			 "kind", "element"
		   });		
		addAnnotation
		  (getDataBaseConfig_Selected(), 
		   source, 
		   new String[] {
			 "name", "selected",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDataBaseConfig_Mode(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (groupDefinitionConfigEClass, 
		   source, 
		   new String[] {
			 "name", "groupDefinitionConfig"
		   });		
		addAnnotation
		  (getGroupDefinitionConfig_GroupDefinition(), 
		   source, 
		   new String[] {
			 "name", "groupDefinition",
			 "kind", "element"
		   });		
		addAnnotation
		  (dataBaseEClass, 
		   source, 
		   new String[] {
			 "name", "dataBase"
		   });		
		addAnnotation
		  (getDataBase_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDataBase_Name(), 
		   source, 
		   new String[] {
			 "name", "name",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDataBase_DriverClassName(), 
		   source, 
		   new String[] {
			 "name", "driverClassName",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDataBase_Url(), 
		   source, 
		   new String[] {
			 "name", "url",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDataBase_Username(), 
		   source, 
		   new String[] {
			 "name", "username",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDataBase_Password(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "password"
		   });		
		addAnnotation
		  (getDataBase_MaxActive(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDataBase_MaxIdle(), 
		   source, 
		   new String[] {
			 "name", "maxIdle",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDataBase_MaxWait(), 
		   source, 
		   new String[] {
			 "name", "maxWait",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDataBase_PaginationImpl(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDataBase_Dbtype(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (groupDefinitionEClass, 
		   source, 
		   new String[] {
			 "name", "groupDefinition"
		   });		
		addAnnotation
		  (getGroupDefinition_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getGroupDefinition_Name(), 
		   source, 
		   new String[] {
			 "name", "name",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getGroupDefinition_GroupDefinitionImpl(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (taskCommandConfigEClass, 
		   source, 
		   new String[] {
			 "name", "taskCommandConfig"
		   });		
		addAnnotation
		  (getTaskCommandConfig_TaskCommandDef(), 
		   source, 
		   new String[] {
			 "name", "taskCommandDef",
			 "kind", "element"
		   });		
		addAnnotation
		  (taskCommandDefEClass, 
		   source, 
		   new String[] {
			 "name", "taskCommandDef"
		   });		
		addAnnotation
		  (getTaskCommandDef_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getTaskCommandDef_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getTaskCommandDef_Command(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getTaskCommandDef_Cmd(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getTaskCommandDef_IsEnabled(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getTaskCommandDef_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getTaskCommandDef_Filter(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getTaskCommandDef_IsVerification(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getTaskCommandDef_IsSaveData(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getTaskCommandDef_IsSimulationRun(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (designerOrgConfigEClass, 
		   source, 
		   new String[] {
			 "name", "designerOrgConfig"
		   });		
		addAnnotation
		  (getDesignerOrgConfig_AllUserInfo(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getDesignerOrgConfig_GroupInfo(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (allUserInfoEClass, 
		   source, 
		   new String[] {
			 "name", "allUserInfo"
		   });		
		addAnnotation
		  (getAllUserInfo_IsEnabled(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getAllUserInfo_UserIdField(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getAllUserInfo_UserNameField(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getAllUserInfo_SqlText(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getAllUserInfo_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getAllUserInfo_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getAllUserInfo_DataSource(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (groupInfoEClass, 
		   source, 
		   new String[] {
			 "name", "groupInfo"
		   });		
		addAnnotation
		  (getGroupInfo_GroupId(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getGroupInfo_GroupName(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getGroupInfo_IsEnabled(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getGroupInfo_IsDisplayUser(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getGroupInfo_GroupIdField(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getGroupInfo_GroupNameField(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getGroupInfo_SupGroupIdField(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getGroupInfo_SqlText(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getGroupInfo_UserInfo(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getGroupInfo_DataSource(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getGroupInfo_GroupDefinitionImpl(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (userInfoEClass, 
		   source, 
		   new String[] {
			 "name", "userInfo"
		   });		
		addAnnotation
		  (getUserInfo_UserIdField(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getUserInfo_UserNameField(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getUserInfo_GroupIdField(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getUserInfo_SqlText(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getUserInfo_DataSource(), 
		   source, 
		   new String[] {
			 "name", "dataSource",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getSysMailConfig_Selected(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getSysMailConfig_MailInfo(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getMailInfo_MailAddress(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getMailInfo_MailName(), 
		   source, 
		   new String[] {
			 "name", "mailName",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getMailInfo_SmtpHost(), 
		   source, 
		   new String[] {
			 "name", "smtpHost",
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getMailInfo_SmtpPort(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getMailInfo_UserName(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getMailInfo_PassWord(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (expandClassConfigEClass, 
		   source, 
		   new String[] {
			 "name", "expandClassConfig"
		   });		
		addAnnotation
		  (getExpandClassConfig_ExpandClass(), 
		   source, 
		   new String[] {
			 "name", "expandClass",
			 "kind", "element"
		   });		
		addAnnotation
		  (expandClassEClass, 
		   source, 
		   new String[] {
			 "name", "expandClass"
		   });		
		addAnnotation
		  (getExpandClass_ClassId(), 
		   source, 
		   new String[] {
			 "name", "classId",
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getExpandClass_ClassName(), 
		   source, 
		   new String[] {
			 "name", "className",
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getExpandClass_ClassInterface(), 
		   source, 
		   new String[] {
			 "name", "classInterface",
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getExpandClass_ClassImpl(), 
		   source, 
		   new String[] {
			 "name", "classImpl",
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getExpandClass_Remarks(), 
		   source, 
		   new String[] {
			 "name", "remarks",
			 "kind", "attribute"
		   });			
		addAnnotation
		  (eventSubscriptionConfigEClass, 
		   source, 
		   new String[] {
			 "name", "eventSubscriptionConfig"
		   });		
		addAnnotation
		  (getEventSubscriptionConfig_ServerPort(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getEventSubscriptionConfig_MessageInfo(), 
		   source, 
		   new String[] {
			 "name", "messageInfo",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getEventSubscriptionConfig_SignalInfo(), 
		   source, 
		   new String[] {
			 "name", "signalInfo",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getEventSubscriptionConfig_IsEnable(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (quartzConfigEClass, 
		   source, 
		   new String[] {
			 "name", "quartzConfig"
		   });		
		addAnnotation
		  (getQuartzConfig_IsEnable(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getQuartzConfig_IsDefaultConfig(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getQuartzConfig_DataBaseId(), 
		   source, 
		   new String[] {
			 "name", "dataBaseId",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (scriptLanguageConfigEClass, 
		   source, 
		   new String[] {
			 "name", "scriptLanguageConfig"
		   });		
		addAnnotation
		  (getScriptLanguageConfig_Selected(), 
		   source, 
		   new String[] {
			 "name", "selected",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getScriptLanguageConfig_ScriptLanguage(), 
		   source, 
		   new String[] {
			 "name", "scriptLanguage",
			 "kind", "element"
		   });		
		addAnnotation
		  (scriptLanguageEClass, 
		   source, 
		   new String[] {
			 "name", "scriptLanguage"
		   });		
		addAnnotation
		  (getScriptLanguage_Id(), 
		   source, 
		   new String[] {
			 "name", "id",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getScriptLanguage_Name(), 
		   source, 
		   new String[] {
			 "name", "name",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getScriptLanguage_ClassImpl(), 
		   source, 
		   new String[] {
			 "name", "classImpl",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (internationalizationConfigEClass, 
		   source, 
		   new String[] {
			 "name", "internationalizationConfig"
		   });		
		addAnnotation
		  (getInternationalizationConfig_IsEnable(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getInternationalizationConfig_FolderPath(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (pigeonholeConfigEClass, 
		   source, 
		   new String[] {
			 "name", "pigeonholeConfig"
		   });		
		addAnnotation
		  (getPigeonholeConfig_ServerPath(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getPigeonholeConfig_PdfPath(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getPigeonholeConfig_Time(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getPigeonholeConfig_Frequency(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getPigeonholeConfig_Week(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getPigeonholeConfig_Month(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getPigeonholeConfig_Code(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getPigeonholeConfig_IsEnable(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (expandCmdConfigEClass, 
		   source, 
		   new String[] {
			 "name", "expandCmdConfig"
		   });		
		addAnnotation
		  (getExpandCmdConfig_ExpandCmd(), 
		   source, 
		   new String[] {
			 "name", "expandCmd",
			 "kind", "element"
		   });		
		addAnnotation
		  (expandCmdEClass, 
		   source, 
		   new String[] {
			 "name", "expandCmd"
		   });		
		addAnnotation
		  (getExpandCmd_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getExpandCmd_Name(), 
		   source, 
		   new String[] {
			 "name", "name",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getExpandCmd_Cmd(), 
		   source, 
		   new String[] {
			 "name", "cmd",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (priorityConfigEClass, 
		   source, 
		   new String[] {
			 "name", "priorityConfig"
		   });		
		addAnnotation
		  (getPriorityConfig_Priority(), 
		   source, 
		   new String[] {
			 "name", "priority",
			 "kind", "element"
		   });		
		addAnnotation
		  (priorityEClass, 
		   source, 
		   new String[] {
			 "name", "priority"
		   });		
		addAnnotation
		  (getPriority_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getPriority_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getPriority_Value(), 
		   source, 
		   new String[] {
			 "name", "value",
			 "kind", "attribute",
			 "namespace", ""
		   });			
		addAnnotation
		  (getPriority_Color(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getPriority_Remark(), 
		   source, 
		   new String[] {
			 "name", "remark",
			 "kind", "attribute"
		   });			
		addAnnotation
		  (assignPolicyConfigEClass, 
		   source, 
		   new String[] {
			 "name", "assignPolicyConfig"
		   });			
		addAnnotation
		  (getAssignPolicyConfig_AssignPolicy(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (assignPolicyEClass, 
		   source, 
		   new String[] {
			 "name", "assignPolicy"
		   });			
		addAnnotation
		  (getAssignPolicy_Id(), 
		   source, 
		   new String[] {
			 "name", "id",
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getAssignPolicy_Name(), 
		   source, 
		   new String[] {
			 "name", "name",
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getAssignPolicy_ClassImpl(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getAssignPolicy_Remarks(), 
		   source, 
		   new String[] {
			 "name", "remarks",
			 "kind", "attribute"
		   });			
		addAnnotation
		  (fixThreadPoolExecutorConfigEClass, 
		   source, 
		   new String[] {
			 "name", "fixThreadPoolExecutorConfig"
		   });		
		addAnnotation
		  (getFixThreadPoolExecutorConfig_FixThreadPoolExecutor(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (fixThreadPoolExecutorEClass, 
		   source, 
		   new String[] {
			 "name", "fixThreadPoolExecutor"
		   });		
		addAnnotation
		  (getFixThreadPoolExecutor_ThreadPoolKey(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getFixThreadPoolExecutor_ThreadPoolName(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getFixThreadPoolExecutor_CorePoolSize(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getFixThreadPoolExecutor_MaximumPoolSize(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getFixThreadPoolExecutor_KeepAliveTime(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getFixThreadPoolExecutor_TimeUnit(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (taskTypeConfigEClass, 
		   source, 
		   new String[] {
			 "name", "taskTypeConfig"
		   });		
		addAnnotation
		  (getTaskTypeConfig_TaskType(), 
		   source, 
		   new String[] {
			 "name", "taskType",
			 "kind", "element"
		   });		
		addAnnotation
		  (taskTypeEClass, 
		   source, 
		   new String[] {
			 "name", "taskType"
		   });			
		addAnnotation
		  (getTaskType_TypeId(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getTaskType_TypeName(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });	
	}

} //CoreconfigPackageImpl
