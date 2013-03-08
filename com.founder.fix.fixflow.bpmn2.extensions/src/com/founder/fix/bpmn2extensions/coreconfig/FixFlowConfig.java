/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fix Flow Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getDataBaseConfig <em>Data Base Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getGroupDefinitionConfig <em>Group Definition Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getTaskCommandConfig <em>Task Command Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getDesignerOrgConfig <em>Designer Org Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getSysMailConfig <em>Sys Mail Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getExpandClassConfig <em>Expand Class Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getEventSubscriptionConfig <em>Event Subscription Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getQuartzConfig <em>Quartz Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getScriptLanguageConfig <em>Script Language Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getInternationalizationConfig <em>Internationalization Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getPigeonholeConfig <em>Pigeonhole Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getExpandCmdConfig <em>Expand Cmd Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getPriorityConfig <em>Priority Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getAssignPolicyConfig <em>Assign Policy Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getFixThreadPoolExecutorConfig <em>Fix Thread Pool Executor Config</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig()
 * @model extendedMetaData="name='fixFlowConfig'"
 * @generated
 */
public interface FixFlowConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Data Base Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Base Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Base Config</em>' containment reference.
	 * @see #setDataBaseConfig(DataBaseConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_DataBaseConfig()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	DataBaseConfig getDataBaseConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getDataBaseConfig <em>Data Base Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Base Config</em>' containment reference.
	 * @see #getDataBaseConfig()
	 * @generated
	 */
	void setDataBaseConfig(DataBaseConfig value);

	/**
	 * Returns the value of the '<em><b>Group Definition Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Definition Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Definition Config</em>' containment reference.
	 * @see #setGroupDefinitionConfig(GroupDefinitionConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_GroupDefinitionConfig()
	 * @model containment="true" required="true"
	 *        extendedMetaData="name='groupDefinitionConfig' kind='element'"
	 * @generated
	 */
	GroupDefinitionConfig getGroupDefinitionConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getGroupDefinitionConfig <em>Group Definition Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Definition Config</em>' containment reference.
	 * @see #getGroupDefinitionConfig()
	 * @generated
	 */
	void setGroupDefinitionConfig(GroupDefinitionConfig value);

	/**
	 * Returns the value of the '<em><b>Task Command Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task Command Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task Command Config</em>' containment reference.
	 * @see #setTaskCommandConfig(TaskCommandConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_TaskCommandConfig()
	 * @model containment="true" required="true"
	 *        extendedMetaData="name='taskCommandConfig' kind='element'"
	 * @generated
	 */
	TaskCommandConfig getTaskCommandConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getTaskCommandConfig <em>Task Command Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task Command Config</em>' containment reference.
	 * @see #getTaskCommandConfig()
	 * @generated
	 */
	void setTaskCommandConfig(TaskCommandConfig value);

	/**
	 * Returns the value of the '<em><b>Designer Org Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Designer Org Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Designer Org Config</em>' containment reference.
	 * @see #setDesignerOrgConfig(DesignerOrgConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_DesignerOrgConfig()
	 * @model containment="true" required="true"
	 *        extendedMetaData="name='designerOrgConfig' kind='element'"
	 * @generated
	 */
	DesignerOrgConfig getDesignerOrgConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getDesignerOrgConfig <em>Designer Org Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Designer Org Config</em>' containment reference.
	 * @see #getDesignerOrgConfig()
	 * @generated
	 */
	void setDesignerOrgConfig(DesignerOrgConfig value);

	/**
	 * Returns the value of the '<em><b>Sys Mail Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sys Mail Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sys Mail Config</em>' containment reference.
	 * @see #setSysMailConfig(SysMailConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_SysMailConfig()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	SysMailConfig getSysMailConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getSysMailConfig <em>Sys Mail Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sys Mail Config</em>' containment reference.
	 * @see #getSysMailConfig()
	 * @generated
	 */
	void setSysMailConfig(SysMailConfig value);

	/**
	 * Returns the value of the '<em><b>Expand Class Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expand Class Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expand Class Config</em>' containment reference.
	 * @see #setExpandClassConfig(ExpandClassConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_ExpandClassConfig()
	 * @model containment="true"
	 *        extendedMetaData="name='expandClassConfig' kind='element'"
	 * @generated
	 */
	ExpandClassConfig getExpandClassConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getExpandClassConfig <em>Expand Class Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expand Class Config</em>' containment reference.
	 * @see #getExpandClassConfig()
	 * @generated
	 */
	void setExpandClassConfig(ExpandClassConfig value);

	/**
	 * Returns the value of the '<em><b>Event Subscription Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Subscription Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Subscription Config</em>' containment reference.
	 * @see #setEventSubscriptionConfig(EventSubscriptionConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_EventSubscriptionConfig()
	 * @model containment="true" required="true"
	 *        extendedMetaData="name='eventSubscriptionConfig' kind='element'"
	 * @generated
	 */
	EventSubscriptionConfig getEventSubscriptionConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getEventSubscriptionConfig <em>Event Subscription Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Subscription Config</em>' containment reference.
	 * @see #getEventSubscriptionConfig()
	 * @generated
	 */
	void setEventSubscriptionConfig(EventSubscriptionConfig value);

	/**
	 * Returns the value of the '<em><b>Quartz Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quartz Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quartz Config</em>' containment reference.
	 * @see #setQuartzConfig(QuartzConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_QuartzConfig()
	 * @model containment="true" required="true"
	 *        extendedMetaData="name='quartzConfig' kind='element'"
	 * @generated
	 */
	QuartzConfig getQuartzConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getQuartzConfig <em>Quartz Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quartz Config</em>' containment reference.
	 * @see #getQuartzConfig()
	 * @generated
	 */
	void setQuartzConfig(QuartzConfig value);

	/**
	 * Returns the value of the '<em><b>Script Language Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script Language Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script Language Config</em>' containment reference.
	 * @see #setScriptLanguageConfig(ScriptLanguageConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_ScriptLanguageConfig()
	 * @model containment="true" required="true"
	 *        extendedMetaData="name='scriptLanguageConfig' kind='element'"
	 * @generated
	 */
	ScriptLanguageConfig getScriptLanguageConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getScriptLanguageConfig <em>Script Language Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script Language Config</em>' containment reference.
	 * @see #getScriptLanguageConfig()
	 * @generated
	 */
	void setScriptLanguageConfig(ScriptLanguageConfig value);

	/**
	 * Returns the value of the '<em><b>Internationalization Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Internationalization Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internationalization Config</em>' containment reference.
	 * @see #setInternationalizationConfig(InternationalizationConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_InternationalizationConfig()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='internationalizationConfig'"
	 * @generated
	 */
	InternationalizationConfig getInternationalizationConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getInternationalizationConfig <em>Internationalization Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Internationalization Config</em>' containment reference.
	 * @see #getInternationalizationConfig()
	 * @generated
	 */
	void setInternationalizationConfig(InternationalizationConfig value);

	/**
	 * Returns the value of the '<em><b>Pigeonhole Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pigeonhole Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pigeonhole Config</em>' containment reference.
	 * @see #setPigeonholeConfig(PigeonholeConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_PigeonholeConfig()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	PigeonholeConfig getPigeonholeConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getPigeonholeConfig <em>Pigeonhole Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pigeonhole Config</em>' containment reference.
	 * @see #getPigeonholeConfig()
	 * @generated
	 */
	void setPigeonholeConfig(PigeonholeConfig value);

	/**
	 * Returns the value of the '<em><b>Expand Cmd Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expand Cmd Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expand Cmd Config</em>' containment reference.
	 * @see #setExpandCmdConfig(ExpandCmdConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_ExpandCmdConfig()
	 * @model containment="true" required="true"
	 *        extendedMetaData="name='expandCmdConfig' kind='element'"
	 * @generated
	 */
	ExpandCmdConfig getExpandCmdConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getExpandCmdConfig <em>Expand Cmd Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expand Cmd Config</em>' containment reference.
	 * @see #getExpandCmdConfig()
	 * @generated
	 */
	void setExpandCmdConfig(ExpandCmdConfig value);

	/**
	 * Returns the value of the '<em><b>Priority Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority Config</em>' containment reference.
	 * @see #setPriorityConfig(PriorityConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_PriorityConfig()
	 * @model containment="true"
	 *        extendedMetaData="name='priorityConfig' kind='element'"
	 * @generated
	 */
	PriorityConfig getPriorityConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getPriorityConfig <em>Priority Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority Config</em>' containment reference.
	 * @see #getPriorityConfig()
	 * @generated
	 */
	void setPriorityConfig(PriorityConfig value);

	/**
	 * Returns the value of the '<em><b>Assign Policy Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assign Policy Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assign Policy Config</em>' containment reference.
	 * @see #setAssignPolicyConfig(AssignPolicyConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_AssignPolicyConfig()
	 * @model containment="true"
	 *        extendedMetaData="name='assignPolicyConfig' kind='element'"
	 * @generated
	 */
	AssignPolicyConfig getAssignPolicyConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getAssignPolicyConfig <em>Assign Policy Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assign Policy Config</em>' containment reference.
	 * @see #getAssignPolicyConfig()
	 * @generated
	 */
	void setAssignPolicyConfig(AssignPolicyConfig value);

	/**
	 * Returns the value of the '<em><b>Fix Thread Pool Executor Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fix Thread Pool Executor Config</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fix Thread Pool Executor Config</em>' containment reference.
	 * @see #setFixThreadPoolExecutorConfig(FixThreadPoolExecutorConfig)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixFlowConfig_FixThreadPoolExecutorConfig()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='fixThreadPoolExecutorConfig'"
	 * @generated
	 */
	FixThreadPoolExecutorConfig getFixThreadPoolExecutorConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig#getFixThreadPoolExecutorConfig <em>Fix Thread Pool Executor Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fix Thread Pool Executor Config</em>' containment reference.
	 * @see #getFixThreadPoolExecutorConfig()
	 * @generated
	 */
	void setFixThreadPoolExecutorConfig(FixThreadPoolExecutorConfig value);

} // FixFlowConfig
