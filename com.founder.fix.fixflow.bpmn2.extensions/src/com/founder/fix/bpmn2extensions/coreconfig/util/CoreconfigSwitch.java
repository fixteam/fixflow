/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.util;

import com.founder.fix.bpmn2extensions.coreconfig.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage
 * @generated
 */
public class CoreconfigSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CoreconfigPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreconfigSwitch() {
		if (modelPackage == null) {
			modelPackage = CoreconfigPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case CoreconfigPackage.FIX_FLOW_CONFIG: {
				FixFlowConfig fixFlowConfig = (FixFlowConfig)theEObject;
				T result = caseFixFlowConfig(fixFlowConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.DATA_BASE_CONFIG: {
				DataBaseConfig dataBaseConfig = (DataBaseConfig)theEObject;
				T result = caseDataBaseConfig(dataBaseConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.GROUP_DEFINITION_CONFIG: {
				GroupDefinitionConfig groupDefinitionConfig = (GroupDefinitionConfig)theEObject;
				T result = caseGroupDefinitionConfig(groupDefinitionConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.DATA_BASE: {
				DataBase dataBase = (DataBase)theEObject;
				T result = caseDataBase(dataBase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.GROUP_DEFINITION: {
				GroupDefinition groupDefinition = (GroupDefinition)theEObject;
				T result = caseGroupDefinition(groupDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.TASK_COMMAND_CONFIG: {
				TaskCommandConfig taskCommandConfig = (TaskCommandConfig)theEObject;
				T result = caseTaskCommandConfig(taskCommandConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.TASK_COMMAND_DEF: {
				TaskCommandDef taskCommandDef = (TaskCommandDef)theEObject;
				T result = caseTaskCommandDef(taskCommandDef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.DESIGNER_ORG_CONFIG: {
				DesignerOrgConfig designerOrgConfig = (DesignerOrgConfig)theEObject;
				T result = caseDesignerOrgConfig(designerOrgConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.ALL_USER_INFO: {
				AllUserInfo allUserInfo = (AllUserInfo)theEObject;
				T result = caseAllUserInfo(allUserInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.GROUP_INFO: {
				GroupInfo groupInfo = (GroupInfo)theEObject;
				T result = caseGroupInfo(groupInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.USER_INFO: {
				UserInfo userInfo = (UserInfo)theEObject;
				T result = caseUserInfo(userInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.SYS_MAIL_CONFIG: {
				SysMailConfig sysMailConfig = (SysMailConfig)theEObject;
				T result = caseSysMailConfig(sysMailConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.MAIL_INFO: {
				MailInfo mailInfo = (MailInfo)theEObject;
				T result = caseMailInfo(mailInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.EXPAND_CLASS_CONFIG: {
				ExpandClassConfig expandClassConfig = (ExpandClassConfig)theEObject;
				T result = caseExpandClassConfig(expandClassConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.EXPAND_CLASS: {
				ExpandClass expandClass = (ExpandClass)theEObject;
				T result = caseExpandClass(expandClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG: {
				EventSubscriptionConfig eventSubscriptionConfig = (EventSubscriptionConfig)theEObject;
				T result = caseEventSubscriptionConfig(eventSubscriptionConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.QUARTZ_CONFIG: {
				QuartzConfig quartzConfig = (QuartzConfig)theEObject;
				T result = caseQuartzConfig(quartzConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.SCRIPT_LANGUAGE_CONFIG: {
				ScriptLanguageConfig scriptLanguageConfig = (ScriptLanguageConfig)theEObject;
				T result = caseScriptLanguageConfig(scriptLanguageConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.SCRIPT_LANGUAGE: {
				ScriptLanguage scriptLanguage = (ScriptLanguage)theEObject;
				T result = caseScriptLanguage(scriptLanguage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.INTERNATIONALIZATION_CONFIG: {
				InternationalizationConfig internationalizationConfig = (InternationalizationConfig)theEObject;
				T result = caseInternationalizationConfig(internationalizationConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.PIGEONHOLE_CONFIG: {
				PigeonholeConfig pigeonholeConfig = (PigeonholeConfig)theEObject;
				T result = casePigeonholeConfig(pigeonholeConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.EXPAND_CMD_CONFIG: {
				ExpandCmdConfig expandCmdConfig = (ExpandCmdConfig)theEObject;
				T result = caseExpandCmdConfig(expandCmdConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.EXPAND_CMD: {
				ExpandCmd expandCmd = (ExpandCmd)theEObject;
				T result = caseExpandCmd(expandCmd);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.PRIORITY_CONFIG: {
				PriorityConfig priorityConfig = (PriorityConfig)theEObject;
				T result = casePriorityConfig(priorityConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.PRIORITY: {
				Priority priority = (Priority)theEObject;
				T result = casePriority(priority);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.ASSIGN_POLICY_CONFIG: {
				AssignPolicyConfig assignPolicyConfig = (AssignPolicyConfig)theEObject;
				T result = caseAssignPolicyConfig(assignPolicyConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.ASSIGN_POLICY: {
				AssignPolicy assignPolicy = (AssignPolicy)theEObject;
				T result = caseAssignPolicy(assignPolicy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR_CONFIG: {
				FixThreadPoolExecutorConfig fixThreadPoolExecutorConfig = (FixThreadPoolExecutorConfig)theEObject;
				T result = caseFixThreadPoolExecutorConfig(fixThreadPoolExecutorConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR: {
				FixThreadPoolExecutor fixThreadPoolExecutor = (FixThreadPoolExecutor)theEObject;
				T result = caseFixThreadPoolExecutor(fixThreadPoolExecutor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.TASK_TYPE_CONFIG: {
				TaskTypeConfig taskTypeConfig = (TaskTypeConfig)theEObject;
				T result = caseTaskTypeConfig(taskTypeConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.TASK_TYPE: {
				TaskType taskType = (TaskType)theEObject;
				T result = caseTaskType(taskType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.CONNECTION_MANAGEMENT_CONFIG: {
				ConnectionManagementConfig connectionManagementConfig = (ConnectionManagementConfig)theEObject;
				T result = caseConnectionManagementConfig(connectionManagementConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.CONNECTION_MANAGEMENT: {
				ConnectionManagement connectionManagement = (ConnectionManagement)theEObject;
				T result = caseConnectionManagement(connectionManagement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.IMPORT_DATA_VARIABLE_CONFIG: {
				ImportDataVariableConfig importDataVariableConfig = (ImportDataVariableConfig)theEObject;
				T result = caseImportDataVariableConfig(importDataVariableConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CoreconfigPackage.IMPORT_DATA_VARIABLE: {
				ImportDataVariable importDataVariable = (ImportDataVariable)theEObject;
				T result = caseImportDataVariable(importDataVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fix Flow Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fix Flow Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFixFlowConfig(FixFlowConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Base Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Base Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataBaseConfig(DataBaseConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Group Definition Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Group Definition Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGroupDefinitionConfig(GroupDefinitionConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Base</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Base</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataBase(DataBase object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Group Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Group Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGroupDefinition(GroupDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Task Command Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Task Command Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTaskCommandConfig(TaskCommandConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Task Command Def</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Task Command Def</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTaskCommandDef(TaskCommandDef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Designer Org Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Designer Org Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDesignerOrgConfig(DesignerOrgConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>All User Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>All User Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAllUserInfo(AllUserInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Group Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Group Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGroupInfo(GroupInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInfo(UserInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sys Mail Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sys Mail Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSysMailConfig(SysMailConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mail Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mail Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMailInfo(MailInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expand Class Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expand Class Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpandClassConfig(ExpandClassConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expand Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expand Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpandClass(ExpandClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event Subscription Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Subscription Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEventSubscriptionConfig(EventSubscriptionConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Quartz Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quartz Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQuartzConfig(QuartzConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Script Language Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Script Language Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScriptLanguageConfig(ScriptLanguageConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Script Language</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Script Language</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScriptLanguage(ScriptLanguage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Internationalization Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Internationalization Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInternationalizationConfig(InternationalizationConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pigeonhole Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pigeonhole Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePigeonholeConfig(PigeonholeConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expand Cmd Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expand Cmd Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpandCmdConfig(ExpandCmdConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expand Cmd</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expand Cmd</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpandCmd(ExpandCmd object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Priority Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Priority Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePriorityConfig(PriorityConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Priority</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Priority</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePriority(Priority object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assign Policy Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assign Policy Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssignPolicyConfig(AssignPolicyConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assign Policy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assign Policy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssignPolicy(AssignPolicy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fix Thread Pool Executor Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fix Thread Pool Executor Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFixThreadPoolExecutorConfig(FixThreadPoolExecutorConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fix Thread Pool Executor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fix Thread Pool Executor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFixThreadPoolExecutor(FixThreadPoolExecutor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Task Type Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Task Type Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTaskTypeConfig(TaskTypeConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Task Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Task Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTaskType(TaskType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection Management Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection Management Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectionManagementConfig(ConnectionManagementConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection Management</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection Management</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectionManagement(ConnectionManagement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Import Data Variable Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Import Data Variable Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImportDataVariableConfig(ImportDataVariableConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Import Data Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Import Data Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImportDataVariable(ImportDataVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //CoreconfigSwitch
