/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.util;

import com.founder.fix.bpmn2extensions.coreconfig.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage
 * @generated
 */
public class CoreconfigAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CoreconfigPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreconfigAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = CoreconfigPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CoreconfigSwitch<Adapter> modelSwitch =
		new CoreconfigSwitch<Adapter>() {
			@Override
			public Adapter caseFixFlowConfig(FixFlowConfig object) {
				return createFixFlowConfigAdapter();
			}
			@Override
			public Adapter caseDataBaseConfig(DataBaseConfig object) {
				return createDataBaseConfigAdapter();
			}
			@Override
			public Adapter caseGroupDefinitionConfig(GroupDefinitionConfig object) {
				return createGroupDefinitionConfigAdapter();
			}
			@Override
			public Adapter caseDataBase(DataBase object) {
				return createDataBaseAdapter();
			}
			@Override
			public Adapter caseGroupDefinition(GroupDefinition object) {
				return createGroupDefinitionAdapter();
			}
			@Override
			public Adapter caseTaskCommandConfig(TaskCommandConfig object) {
				return createTaskCommandConfigAdapter();
			}
			@Override
			public Adapter caseTaskCommandDef(TaskCommandDef object) {
				return createTaskCommandDefAdapter();
			}
			@Override
			public Adapter caseDesignerOrgConfig(DesignerOrgConfig object) {
				return createDesignerOrgConfigAdapter();
			}
			@Override
			public Adapter caseAllUserInfo(AllUserInfo object) {
				return createAllUserInfoAdapter();
			}
			@Override
			public Adapter caseGroupInfo(GroupInfo object) {
				return createGroupInfoAdapter();
			}
			@Override
			public Adapter caseUserInfo(UserInfo object) {
				return createUserInfoAdapter();
			}
			@Override
			public Adapter caseSysMailConfig(SysMailConfig object) {
				return createSysMailConfigAdapter();
			}
			@Override
			public Adapter caseMailInfo(MailInfo object) {
				return createMailInfoAdapter();
			}
			@Override
			public Adapter caseExpandClassConfig(ExpandClassConfig object) {
				return createExpandClassConfigAdapter();
			}
			@Override
			public Adapter caseExpandClass(ExpandClass object) {
				return createExpandClassAdapter();
			}
			@Override
			public Adapter caseEventSubscriptionConfig(EventSubscriptionConfig object) {
				return createEventSubscriptionConfigAdapter();
			}
			@Override
			public Adapter caseQuartzConfig(QuartzConfig object) {
				return createQuartzConfigAdapter();
			}
			@Override
			public Adapter caseScriptLanguageConfig(ScriptLanguageConfig object) {
				return createScriptLanguageConfigAdapter();
			}
			@Override
			public Adapter caseScriptLanguage(ScriptLanguage object) {
				return createScriptLanguageAdapter();
			}
			@Override
			public Adapter caseInternationalizationConfig(InternationalizationConfig object) {
				return createInternationalizationConfigAdapter();
			}
			@Override
			public Adapter casePigeonholeConfig(PigeonholeConfig object) {
				return createPigeonholeConfigAdapter();
			}
			@Override
			public Adapter caseExpandCmdConfig(ExpandCmdConfig object) {
				return createExpandCmdConfigAdapter();
			}
			@Override
			public Adapter caseExpandCmd(ExpandCmd object) {
				return createExpandCmdAdapter();
			}
			@Override
			public Adapter casePriorityConfig(PriorityConfig object) {
				return createPriorityConfigAdapter();
			}
			@Override
			public Adapter casePriority(Priority object) {
				return createPriorityAdapter();
			}
			@Override
			public Adapter caseAssignPolicyConfig(AssignPolicyConfig object) {
				return createAssignPolicyConfigAdapter();
			}
			@Override
			public Adapter caseAssignPolicy(AssignPolicy object) {
				return createAssignPolicyAdapter();
			}
			@Override
			public Adapter caseFixThreadPoolExecutorConfig(FixThreadPoolExecutorConfig object) {
				return createFixThreadPoolExecutorConfigAdapter();
			}
			@Override
			public Adapter caseFixThreadPoolExecutor(FixThreadPoolExecutor object) {
				return createFixThreadPoolExecutorAdapter();
			}
			@Override
			public Adapter caseTaskTypeConfig(TaskTypeConfig object) {
				return createTaskTypeConfigAdapter();
			}
			@Override
			public Adapter caseTaskType(TaskType object) {
				return createTaskTypeAdapter();
			}
			@Override
			public Adapter caseConnectionManagementConfig(ConnectionManagementConfig object) {
				return createConnectionManagementConfigAdapter();
			}
			@Override
			public Adapter caseConnectionManagementInstanceConfig(ConnectionManagementInstanceConfig object) {
				return createConnectionManagementInstanceConfigAdapter();
			}
			@Override
			public Adapter caseImportDataVariableConfig(ImportDataVariableConfig object) {
				return createImportDataVariableConfigAdapter();
			}
			@Override
			public Adapter caseImportDataVariable(ImportDataVariable object) {
				return createImportDataVariableAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig <em>Fix Flow Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig
	 * @generated
	 */
	public Adapter createFixFlowConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig <em>Data Base Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig
	 * @generated
	 */
	public Adapter createDataBaseConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupDefinitionConfig <em>Group Definition Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupDefinitionConfig
	 * @generated
	 */
	public Adapter createGroupDefinitionConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase <em>Data Base</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DataBase
	 * @generated
	 */
	public Adapter createDataBaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition <em>Group Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition
	 * @generated
	 */
	public Adapter createGroupDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig <em>Task Command Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig
	 * @generated
	 */
	public Adapter createTaskCommandConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef <em>Task Command Def</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef
	 * @generated
	 */
	public Adapter createTaskCommandDefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig <em>Designer Org Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig
	 * @generated
	 */
	public Adapter createDesignerOrgConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo <em>All User Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo
	 * @generated
	 */
	public Adapter createAllUserInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.GroupInfo <em>Group Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.GroupInfo
	 * @generated
	 */
	public Adapter createGroupInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.UserInfo <em>User Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.UserInfo
	 * @generated
	 */
	public Adapter createUserInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig <em>Sys Mail Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig
	 * @generated
	 */
	public Adapter createSysMailConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo <em>Mail Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.MailInfo
	 * @generated
	 */
	public Adapter createMailInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClassConfig <em>Expand Class Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandClassConfig
	 * @generated
	 */
	public Adapter createExpandClassConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass <em>Expand Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandClass
	 * @generated
	 */
	public Adapter createExpandClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig <em>Event Subscription Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig
	 * @generated
	 */
	public Adapter createEventSubscriptionConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig <em>Quartz Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig
	 * @generated
	 */
	public Adapter createQuartzConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig <em>Script Language Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig
	 * @generated
	 */
	public Adapter createScriptLanguageConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage <em>Script Language</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage
	 * @generated
	 */
	public Adapter createScriptLanguageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig <em>Internationalization Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig
	 * @generated
	 */
	public Adapter createInternationalizationConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig <em>Pigeonhole Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig
	 * @generated
	 */
	public Adapter createPigeonholeConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmdConfig <em>Expand Cmd Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandCmdConfig
	 * @generated
	 */
	public Adapter createExpandCmdConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd <em>Expand Cmd</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd
	 * @generated
	 */
	public Adapter createExpandCmdAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.PriorityConfig <em>Priority Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.PriorityConfig
	 * @generated
	 */
	public Adapter createPriorityConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.Priority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.Priority
	 * @generated
	 */
	public Adapter createPriorityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.AssignPolicyConfig <em>Assign Policy Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AssignPolicyConfig
	 * @generated
	 */
	public Adapter createAssignPolicyConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy <em>Assign Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy
	 * @generated
	 */
	public Adapter createAssignPolicyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutorConfig <em>Fix Thread Pool Executor Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutorConfig
	 * @generated
	 */
	public Adapter createFixThreadPoolExecutorConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor <em>Fix Thread Pool Executor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor
	 * @generated
	 */
	public Adapter createFixThreadPoolExecutorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskTypeConfig <em>Task Type Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskTypeConfig
	 * @generated
	 */
	public Adapter createTaskTypeConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskType <em>Task Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TaskType
	 * @generated
	 */
	public Adapter createTaskTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.ConnectionManagementConfig <em>Connection Management Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ConnectionManagementConfig
	 * @generated
	 */
	public Adapter createConnectionManagementConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.ConnectionManagementInstanceConfig <em>Connection Management Instance Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ConnectionManagementInstanceConfig
	 * @generated
	 */
	public Adapter createConnectionManagementInstanceConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.ImportDataVariableConfig <em>Import Data Variable Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ImportDataVariableConfig
	 * @generated
	 */
	public Adapter createImportDataVariableConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.coreconfig.ImportDataVariable <em>Import Data Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.ImportDataVariable
	 * @generated
	 */
	public Adapter createImportDataVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //CoreconfigAdapterFactory
