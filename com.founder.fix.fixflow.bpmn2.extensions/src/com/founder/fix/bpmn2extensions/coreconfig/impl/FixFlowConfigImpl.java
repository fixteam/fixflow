/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.AssignPolicyConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ConnectionManagementConfig;
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig;
import com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig;
import com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandClassConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandCmdConfig;
import com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig;
import com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutorConfig;
import com.founder.fix.bpmn2extensions.coreconfig.GroupDefinitionConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ImportDataVariableConfig;
import com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig;
import com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig;
import com.founder.fix.bpmn2extensions.coreconfig.PriorityConfig;
import com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig;
import com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig;
import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig;

import com.founder.fix.bpmn2extensions.coreconfig.TaskTypeConfig;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fix Flow Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getDataBaseConfig <em>Data Base Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getGroupDefinitionConfig <em>Group Definition Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getTaskCommandConfig <em>Task Command Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getDesignerOrgConfig <em>Designer Org Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getSysMailConfig <em>Sys Mail Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getExpandClassConfig <em>Expand Class Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getEventSubscriptionConfig <em>Event Subscription Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getQuartzConfig <em>Quartz Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getScriptLanguageConfig <em>Script Language Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getInternationalizationConfig <em>Internationalization Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getPigeonholeConfig <em>Pigeonhole Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getExpandCmdConfig <em>Expand Cmd Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getPriorityConfig <em>Priority Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getAssignPolicyConfig <em>Assign Policy Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getFixThreadPoolExecutorConfig <em>Fix Thread Pool Executor Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getTaskTypeConfig <em>Task Type Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getConnectionManagementConfig <em>Connection Management Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getImportDataVariableConfig <em>Import Data Variable Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixFlowConfigImpl#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FixFlowConfigImpl extends EObjectImpl implements FixFlowConfig {
	/**
	 * The cached value of the '{@link #getDataBaseConfig() <em>Data Base Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataBaseConfig()
	 * @generated
	 * @ordered
	 */
	protected DataBaseConfig dataBaseConfig;

	/**
	 * The cached value of the '{@link #getGroupDefinitionConfig() <em>Group Definition Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupDefinitionConfig()
	 * @generated
	 * @ordered
	 */
	protected GroupDefinitionConfig groupDefinitionConfig;

	/**
	 * The cached value of the '{@link #getTaskCommandConfig() <em>Task Command Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaskCommandConfig()
	 * @generated
	 * @ordered
	 */
	protected TaskCommandConfig taskCommandConfig;

	/**
	 * The cached value of the '{@link #getDesignerOrgConfig() <em>Designer Org Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDesignerOrgConfig()
	 * @generated
	 * @ordered
	 */
	protected DesignerOrgConfig designerOrgConfig;

	/**
	 * The cached value of the '{@link #getSysMailConfig() <em>Sys Mail Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSysMailConfig()
	 * @generated
	 * @ordered
	 */
	protected SysMailConfig sysMailConfig;

	/**
	 * The cached value of the '{@link #getExpandClassConfig() <em>Expand Class Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpandClassConfig()
	 * @generated
	 * @ordered
	 */
	protected ExpandClassConfig expandClassConfig;

	/**
	 * The cached value of the '{@link #getEventSubscriptionConfig() <em>Event Subscription Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventSubscriptionConfig()
	 * @generated
	 * @ordered
	 */
	protected EventSubscriptionConfig eventSubscriptionConfig;

	/**
	 * The cached value of the '{@link #getQuartzConfig() <em>Quartz Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuartzConfig()
	 * @generated
	 * @ordered
	 */
	protected QuartzConfig quartzConfig;

	/**
	 * The cached value of the '{@link #getScriptLanguageConfig() <em>Script Language Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScriptLanguageConfig()
	 * @generated
	 * @ordered
	 */
	protected ScriptLanguageConfig scriptLanguageConfig;

	/**
	 * The cached value of the '{@link #getInternationalizationConfig() <em>Internationalization Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternationalizationConfig()
	 * @generated
	 * @ordered
	 */
	protected InternationalizationConfig internationalizationConfig;

	/**
	 * The cached value of the '{@link #getPigeonholeConfig() <em>Pigeonhole Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPigeonholeConfig()
	 * @generated
	 * @ordered
	 */
	protected PigeonholeConfig pigeonholeConfig;

	/**
	 * The cached value of the '{@link #getExpandCmdConfig() <em>Expand Cmd Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpandCmdConfig()
	 * @generated
	 * @ordered
	 */
	protected ExpandCmdConfig expandCmdConfig;

	/**
	 * The cached value of the '{@link #getPriorityConfig() <em>Priority Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriorityConfig()
	 * @generated
	 * @ordered
	 */
	protected PriorityConfig priorityConfig;

	/**
	 * The cached value of the '{@link #getAssignPolicyConfig() <em>Assign Policy Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignPolicyConfig()
	 * @generated
	 * @ordered
	 */
	protected AssignPolicyConfig assignPolicyConfig;

	/**
	 * The cached value of the '{@link #getFixThreadPoolExecutorConfig() <em>Fix Thread Pool Executor Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFixThreadPoolExecutorConfig()
	 * @generated
	 * @ordered
	 */
	protected FixThreadPoolExecutorConfig fixThreadPoolExecutorConfig;

	/**
	 * The cached value of the '{@link #getTaskTypeConfig() <em>Task Type Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaskTypeConfig()
	 * @generated
	 * @ordered
	 */
	protected TaskTypeConfig taskTypeConfig;

	/**
	 * The cached value of the '{@link #getConnectionManagementConfig() <em>Connection Management Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionManagementConfig()
	 * @generated
	 * @ordered
	 */
	protected ConnectionManagementConfig connectionManagementConfig;

	/**
	 * The cached value of the '{@link #getImportDataVariableConfig() <em>Import Data Variable Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportDataVariableConfig()
	 * @generated
	 * @ordered
	 */
	protected ImportDataVariableConfig importDataVariableConfig;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FixFlowConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.FIX_FLOW_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataBaseConfig getDataBaseConfig() {
		return dataBaseConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataBaseConfig(DataBaseConfig newDataBaseConfig, NotificationChain msgs) {
		DataBaseConfig oldDataBaseConfig = dataBaseConfig;
		dataBaseConfig = newDataBaseConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__DATA_BASE_CONFIG, oldDataBaseConfig, newDataBaseConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataBaseConfig(DataBaseConfig newDataBaseConfig) {
		if (newDataBaseConfig != dataBaseConfig) {
			NotificationChain msgs = null;
			if (dataBaseConfig != null)
				msgs = ((InternalEObject)dataBaseConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__DATA_BASE_CONFIG, null, msgs);
			if (newDataBaseConfig != null)
				msgs = ((InternalEObject)newDataBaseConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__DATA_BASE_CONFIG, null, msgs);
			msgs = basicSetDataBaseConfig(newDataBaseConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__DATA_BASE_CONFIG, newDataBaseConfig, newDataBaseConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GroupDefinitionConfig getGroupDefinitionConfig() {
		return groupDefinitionConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroupDefinitionConfig(GroupDefinitionConfig newGroupDefinitionConfig, NotificationChain msgs) {
		GroupDefinitionConfig oldGroupDefinitionConfig = groupDefinitionConfig;
		groupDefinitionConfig = newGroupDefinitionConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__GROUP_DEFINITION_CONFIG, oldGroupDefinitionConfig, newGroupDefinitionConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupDefinitionConfig(GroupDefinitionConfig newGroupDefinitionConfig) {
		if (newGroupDefinitionConfig != groupDefinitionConfig) {
			NotificationChain msgs = null;
			if (groupDefinitionConfig != null)
				msgs = ((InternalEObject)groupDefinitionConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__GROUP_DEFINITION_CONFIG, null, msgs);
			if (newGroupDefinitionConfig != null)
				msgs = ((InternalEObject)newGroupDefinitionConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__GROUP_DEFINITION_CONFIG, null, msgs);
			msgs = basicSetGroupDefinitionConfig(newGroupDefinitionConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__GROUP_DEFINITION_CONFIG, newGroupDefinitionConfig, newGroupDefinitionConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskCommandConfig getTaskCommandConfig() {
		return taskCommandConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTaskCommandConfig(TaskCommandConfig newTaskCommandConfig, NotificationChain msgs) {
		TaskCommandConfig oldTaskCommandConfig = taskCommandConfig;
		taskCommandConfig = newTaskCommandConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__TASK_COMMAND_CONFIG, oldTaskCommandConfig, newTaskCommandConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTaskCommandConfig(TaskCommandConfig newTaskCommandConfig) {
		if (newTaskCommandConfig != taskCommandConfig) {
			NotificationChain msgs = null;
			if (taskCommandConfig != null)
				msgs = ((InternalEObject)taskCommandConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__TASK_COMMAND_CONFIG, null, msgs);
			if (newTaskCommandConfig != null)
				msgs = ((InternalEObject)newTaskCommandConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__TASK_COMMAND_CONFIG, null, msgs);
			msgs = basicSetTaskCommandConfig(newTaskCommandConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__TASK_COMMAND_CONFIG, newTaskCommandConfig, newTaskCommandConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DesignerOrgConfig getDesignerOrgConfig() {
		return designerOrgConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDesignerOrgConfig(DesignerOrgConfig newDesignerOrgConfig, NotificationChain msgs) {
		DesignerOrgConfig oldDesignerOrgConfig = designerOrgConfig;
		designerOrgConfig = newDesignerOrgConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG, oldDesignerOrgConfig, newDesignerOrgConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDesignerOrgConfig(DesignerOrgConfig newDesignerOrgConfig) {
		if (newDesignerOrgConfig != designerOrgConfig) {
			NotificationChain msgs = null;
			if (designerOrgConfig != null)
				msgs = ((InternalEObject)designerOrgConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG, null, msgs);
			if (newDesignerOrgConfig != null)
				msgs = ((InternalEObject)newDesignerOrgConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG, null, msgs);
			msgs = basicSetDesignerOrgConfig(newDesignerOrgConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG, newDesignerOrgConfig, newDesignerOrgConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SysMailConfig getSysMailConfig() {
		return sysMailConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSysMailConfig(SysMailConfig newSysMailConfig, NotificationChain msgs) {
		SysMailConfig oldSysMailConfig = sysMailConfig;
		sysMailConfig = newSysMailConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__SYS_MAIL_CONFIG, oldSysMailConfig, newSysMailConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSysMailConfig(SysMailConfig newSysMailConfig) {
		if (newSysMailConfig != sysMailConfig) {
			NotificationChain msgs = null;
			if (sysMailConfig != null)
				msgs = ((InternalEObject)sysMailConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__SYS_MAIL_CONFIG, null, msgs);
			if (newSysMailConfig != null)
				msgs = ((InternalEObject)newSysMailConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__SYS_MAIL_CONFIG, null, msgs);
			msgs = basicSetSysMailConfig(newSysMailConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__SYS_MAIL_CONFIG, newSysMailConfig, newSysMailConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpandClassConfig getExpandClassConfig() {
		return expandClassConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpandClassConfig(ExpandClassConfig newExpandClassConfig, NotificationChain msgs) {
		ExpandClassConfig oldExpandClassConfig = expandClassConfig;
		expandClassConfig = newExpandClassConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CLASS_CONFIG, oldExpandClassConfig, newExpandClassConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpandClassConfig(ExpandClassConfig newExpandClassConfig) {
		if (newExpandClassConfig != expandClassConfig) {
			NotificationChain msgs = null;
			if (expandClassConfig != null)
				msgs = ((InternalEObject)expandClassConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CLASS_CONFIG, null, msgs);
			if (newExpandClassConfig != null)
				msgs = ((InternalEObject)newExpandClassConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CLASS_CONFIG, null, msgs);
			msgs = basicSetExpandClassConfig(newExpandClassConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CLASS_CONFIG, newExpandClassConfig, newExpandClassConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventSubscriptionConfig getEventSubscriptionConfig() {
		return eventSubscriptionConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEventSubscriptionConfig(EventSubscriptionConfig newEventSubscriptionConfig, NotificationChain msgs) {
		EventSubscriptionConfig oldEventSubscriptionConfig = eventSubscriptionConfig;
		eventSubscriptionConfig = newEventSubscriptionConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG, oldEventSubscriptionConfig, newEventSubscriptionConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventSubscriptionConfig(EventSubscriptionConfig newEventSubscriptionConfig) {
		if (newEventSubscriptionConfig != eventSubscriptionConfig) {
			NotificationChain msgs = null;
			if (eventSubscriptionConfig != null)
				msgs = ((InternalEObject)eventSubscriptionConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG, null, msgs);
			if (newEventSubscriptionConfig != null)
				msgs = ((InternalEObject)newEventSubscriptionConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG, null, msgs);
			msgs = basicSetEventSubscriptionConfig(newEventSubscriptionConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG, newEventSubscriptionConfig, newEventSubscriptionConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QuartzConfig getQuartzConfig() {
		return quartzConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQuartzConfig(QuartzConfig newQuartzConfig, NotificationChain msgs) {
		QuartzConfig oldQuartzConfig = quartzConfig;
		quartzConfig = newQuartzConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__QUARTZ_CONFIG, oldQuartzConfig, newQuartzConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuartzConfig(QuartzConfig newQuartzConfig) {
		if (newQuartzConfig != quartzConfig) {
			NotificationChain msgs = null;
			if (quartzConfig != null)
				msgs = ((InternalEObject)quartzConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__QUARTZ_CONFIG, null, msgs);
			if (newQuartzConfig != null)
				msgs = ((InternalEObject)newQuartzConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__QUARTZ_CONFIG, null, msgs);
			msgs = basicSetQuartzConfig(newQuartzConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__QUARTZ_CONFIG, newQuartzConfig, newQuartzConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptLanguageConfig getScriptLanguageConfig() {
		return scriptLanguageConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScriptLanguageConfig(ScriptLanguageConfig newScriptLanguageConfig, NotificationChain msgs) {
		ScriptLanguageConfig oldScriptLanguageConfig = scriptLanguageConfig;
		scriptLanguageConfig = newScriptLanguageConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__SCRIPT_LANGUAGE_CONFIG, oldScriptLanguageConfig, newScriptLanguageConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScriptLanguageConfig(ScriptLanguageConfig newScriptLanguageConfig) {
		if (newScriptLanguageConfig != scriptLanguageConfig) {
			NotificationChain msgs = null;
			if (scriptLanguageConfig != null)
				msgs = ((InternalEObject)scriptLanguageConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__SCRIPT_LANGUAGE_CONFIG, null, msgs);
			if (newScriptLanguageConfig != null)
				msgs = ((InternalEObject)newScriptLanguageConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__SCRIPT_LANGUAGE_CONFIG, null, msgs);
			msgs = basicSetScriptLanguageConfig(newScriptLanguageConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__SCRIPT_LANGUAGE_CONFIG, newScriptLanguageConfig, newScriptLanguageConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InternationalizationConfig getInternationalizationConfig() {
		return internationalizationConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInternationalizationConfig(InternationalizationConfig newInternationalizationConfig, NotificationChain msgs) {
		InternationalizationConfig oldInternationalizationConfig = internationalizationConfig;
		internationalizationConfig = newInternationalizationConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__INTERNATIONALIZATION_CONFIG, oldInternationalizationConfig, newInternationalizationConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInternationalizationConfig(InternationalizationConfig newInternationalizationConfig) {
		if (newInternationalizationConfig != internationalizationConfig) {
			NotificationChain msgs = null;
			if (internationalizationConfig != null)
				msgs = ((InternalEObject)internationalizationConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__INTERNATIONALIZATION_CONFIG, null, msgs);
			if (newInternationalizationConfig != null)
				msgs = ((InternalEObject)newInternationalizationConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__INTERNATIONALIZATION_CONFIG, null, msgs);
			msgs = basicSetInternationalizationConfig(newInternationalizationConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__INTERNATIONALIZATION_CONFIG, newInternationalizationConfig, newInternationalizationConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PigeonholeConfig getPigeonholeConfig() {
		return pigeonholeConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPigeonholeConfig(PigeonholeConfig newPigeonholeConfig, NotificationChain msgs) {
		PigeonholeConfig oldPigeonholeConfig = pigeonholeConfig;
		pigeonholeConfig = newPigeonholeConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG, oldPigeonholeConfig, newPigeonholeConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPigeonholeConfig(PigeonholeConfig newPigeonholeConfig) {
		if (newPigeonholeConfig != pigeonholeConfig) {
			NotificationChain msgs = null;
			if (pigeonholeConfig != null)
				msgs = ((InternalEObject)pigeonholeConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG, null, msgs);
			if (newPigeonholeConfig != null)
				msgs = ((InternalEObject)newPigeonholeConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG, null, msgs);
			msgs = basicSetPigeonholeConfig(newPigeonholeConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG, newPigeonholeConfig, newPigeonholeConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpandCmdConfig getExpandCmdConfig() {
		return expandCmdConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpandCmdConfig(ExpandCmdConfig newExpandCmdConfig, NotificationChain msgs) {
		ExpandCmdConfig oldExpandCmdConfig = expandCmdConfig;
		expandCmdConfig = newExpandCmdConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CMD_CONFIG, oldExpandCmdConfig, newExpandCmdConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpandCmdConfig(ExpandCmdConfig newExpandCmdConfig) {
		if (newExpandCmdConfig != expandCmdConfig) {
			NotificationChain msgs = null;
			if (expandCmdConfig != null)
				msgs = ((InternalEObject)expandCmdConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CMD_CONFIG, null, msgs);
			if (newExpandCmdConfig != null)
				msgs = ((InternalEObject)newExpandCmdConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CMD_CONFIG, null, msgs);
			msgs = basicSetExpandCmdConfig(newExpandCmdConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CMD_CONFIG, newExpandCmdConfig, newExpandCmdConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PriorityConfig getPriorityConfig() {
		return priorityConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPriorityConfig(PriorityConfig newPriorityConfig, NotificationChain msgs) {
		PriorityConfig oldPriorityConfig = priorityConfig;
		priorityConfig = newPriorityConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__PRIORITY_CONFIG, oldPriorityConfig, newPriorityConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriorityConfig(PriorityConfig newPriorityConfig) {
		if (newPriorityConfig != priorityConfig) {
			NotificationChain msgs = null;
			if (priorityConfig != null)
				msgs = ((InternalEObject)priorityConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__PRIORITY_CONFIG, null, msgs);
			if (newPriorityConfig != null)
				msgs = ((InternalEObject)newPriorityConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__PRIORITY_CONFIG, null, msgs);
			msgs = basicSetPriorityConfig(newPriorityConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__PRIORITY_CONFIG, newPriorityConfig, newPriorityConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignPolicyConfig getAssignPolicyConfig() {
		return assignPolicyConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssignPolicyConfig(AssignPolicyConfig newAssignPolicyConfig, NotificationChain msgs) {
		AssignPolicyConfig oldAssignPolicyConfig = assignPolicyConfig;
		assignPolicyConfig = newAssignPolicyConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__ASSIGN_POLICY_CONFIG, oldAssignPolicyConfig, newAssignPolicyConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssignPolicyConfig(AssignPolicyConfig newAssignPolicyConfig) {
		if (newAssignPolicyConfig != assignPolicyConfig) {
			NotificationChain msgs = null;
			if (assignPolicyConfig != null)
				msgs = ((InternalEObject)assignPolicyConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__ASSIGN_POLICY_CONFIG, null, msgs);
			if (newAssignPolicyConfig != null)
				msgs = ((InternalEObject)newAssignPolicyConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__ASSIGN_POLICY_CONFIG, null, msgs);
			msgs = basicSetAssignPolicyConfig(newAssignPolicyConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__ASSIGN_POLICY_CONFIG, newAssignPolicyConfig, newAssignPolicyConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FixThreadPoolExecutorConfig getFixThreadPoolExecutorConfig() {
		return fixThreadPoolExecutorConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFixThreadPoolExecutorConfig(FixThreadPoolExecutorConfig newFixThreadPoolExecutorConfig, NotificationChain msgs) {
		FixThreadPoolExecutorConfig oldFixThreadPoolExecutorConfig = fixThreadPoolExecutorConfig;
		fixThreadPoolExecutorConfig = newFixThreadPoolExecutorConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__FIX_THREAD_POOL_EXECUTOR_CONFIG, oldFixThreadPoolExecutorConfig, newFixThreadPoolExecutorConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFixThreadPoolExecutorConfig(FixThreadPoolExecutorConfig newFixThreadPoolExecutorConfig) {
		if (newFixThreadPoolExecutorConfig != fixThreadPoolExecutorConfig) {
			NotificationChain msgs = null;
			if (fixThreadPoolExecutorConfig != null)
				msgs = ((InternalEObject)fixThreadPoolExecutorConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__FIX_THREAD_POOL_EXECUTOR_CONFIG, null, msgs);
			if (newFixThreadPoolExecutorConfig != null)
				msgs = ((InternalEObject)newFixThreadPoolExecutorConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__FIX_THREAD_POOL_EXECUTOR_CONFIG, null, msgs);
			msgs = basicSetFixThreadPoolExecutorConfig(newFixThreadPoolExecutorConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__FIX_THREAD_POOL_EXECUTOR_CONFIG, newFixThreadPoolExecutorConfig, newFixThreadPoolExecutorConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskTypeConfig getTaskTypeConfig() {
		return taskTypeConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTaskTypeConfig(TaskTypeConfig newTaskTypeConfig, NotificationChain msgs) {
		TaskTypeConfig oldTaskTypeConfig = taskTypeConfig;
		taskTypeConfig = newTaskTypeConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__TASK_TYPE_CONFIG, oldTaskTypeConfig, newTaskTypeConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTaskTypeConfig(TaskTypeConfig newTaskTypeConfig) {
		if (newTaskTypeConfig != taskTypeConfig) {
			NotificationChain msgs = null;
			if (taskTypeConfig != null)
				msgs = ((InternalEObject)taskTypeConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__TASK_TYPE_CONFIG, null, msgs);
			if (newTaskTypeConfig != null)
				msgs = ((InternalEObject)newTaskTypeConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__TASK_TYPE_CONFIG, null, msgs);
			msgs = basicSetTaskTypeConfig(newTaskTypeConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__TASK_TYPE_CONFIG, newTaskTypeConfig, newTaskTypeConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionManagementConfig getConnectionManagementConfig() {
		return connectionManagementConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConnectionManagementConfig(ConnectionManagementConfig newConnectionManagementConfig, NotificationChain msgs) {
		ConnectionManagementConfig oldConnectionManagementConfig = connectionManagementConfig;
		connectionManagementConfig = newConnectionManagementConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__CONNECTION_MANAGEMENT_CONFIG, oldConnectionManagementConfig, newConnectionManagementConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectionManagementConfig(ConnectionManagementConfig newConnectionManagementConfig) {
		if (newConnectionManagementConfig != connectionManagementConfig) {
			NotificationChain msgs = null;
			if (connectionManagementConfig != null)
				msgs = ((InternalEObject)connectionManagementConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__CONNECTION_MANAGEMENT_CONFIG, null, msgs);
			if (newConnectionManagementConfig != null)
				msgs = ((InternalEObject)newConnectionManagementConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__CONNECTION_MANAGEMENT_CONFIG, null, msgs);
			msgs = basicSetConnectionManagementConfig(newConnectionManagementConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__CONNECTION_MANAGEMENT_CONFIG, newConnectionManagementConfig, newConnectionManagementConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportDataVariableConfig getImportDataVariableConfig() {
		return importDataVariableConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImportDataVariableConfig(ImportDataVariableConfig newImportDataVariableConfig, NotificationChain msgs) {
		ImportDataVariableConfig oldImportDataVariableConfig = importDataVariableConfig;
		importDataVariableConfig = newImportDataVariableConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__IMPORT_DATA_VARIABLE_CONFIG, oldImportDataVariableConfig, newImportDataVariableConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImportDataVariableConfig(ImportDataVariableConfig newImportDataVariableConfig) {
		if (newImportDataVariableConfig != importDataVariableConfig) {
			NotificationChain msgs = null;
			if (importDataVariableConfig != null)
				msgs = ((InternalEObject)importDataVariableConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__IMPORT_DATA_VARIABLE_CONFIG, null, msgs);
			if (newImportDataVariableConfig != null)
				msgs = ((InternalEObject)newImportDataVariableConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.FIX_FLOW_CONFIG__IMPORT_DATA_VARIABLE_CONFIG, null, msgs);
			msgs = basicSetImportDataVariableConfig(newImportDataVariableConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__IMPORT_DATA_VARIABLE_CONFIG, newImportDataVariableConfig, newImportDataVariableConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_FLOW_CONFIG__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CoreconfigPackage.FIX_FLOW_CONFIG__DATA_BASE_CONFIG:
				return basicSetDataBaseConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__GROUP_DEFINITION_CONFIG:
				return basicSetGroupDefinitionConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__TASK_COMMAND_CONFIG:
				return basicSetTaskCommandConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG:
				return basicSetDesignerOrgConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__SYS_MAIL_CONFIG:
				return basicSetSysMailConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CLASS_CONFIG:
				return basicSetExpandClassConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG:
				return basicSetEventSubscriptionConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__QUARTZ_CONFIG:
				return basicSetQuartzConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__SCRIPT_LANGUAGE_CONFIG:
				return basicSetScriptLanguageConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__INTERNATIONALIZATION_CONFIG:
				return basicSetInternationalizationConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG:
				return basicSetPigeonholeConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CMD_CONFIG:
				return basicSetExpandCmdConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__PRIORITY_CONFIG:
				return basicSetPriorityConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__ASSIGN_POLICY_CONFIG:
				return basicSetAssignPolicyConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__FIX_THREAD_POOL_EXECUTOR_CONFIG:
				return basicSetFixThreadPoolExecutorConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__TASK_TYPE_CONFIG:
				return basicSetTaskTypeConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__CONNECTION_MANAGEMENT_CONFIG:
				return basicSetConnectionManagementConfig(null, msgs);
			case CoreconfigPackage.FIX_FLOW_CONFIG__IMPORT_DATA_VARIABLE_CONFIG:
				return basicSetImportDataVariableConfig(null, msgs);
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
			case CoreconfigPackage.FIX_FLOW_CONFIG__DATA_BASE_CONFIG:
				return getDataBaseConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__GROUP_DEFINITION_CONFIG:
				return getGroupDefinitionConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__TASK_COMMAND_CONFIG:
				return getTaskCommandConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG:
				return getDesignerOrgConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__SYS_MAIL_CONFIG:
				return getSysMailConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CLASS_CONFIG:
				return getExpandClassConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG:
				return getEventSubscriptionConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__QUARTZ_CONFIG:
				return getQuartzConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__SCRIPT_LANGUAGE_CONFIG:
				return getScriptLanguageConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__INTERNATIONALIZATION_CONFIG:
				return getInternationalizationConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG:
				return getPigeonholeConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CMD_CONFIG:
				return getExpandCmdConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__PRIORITY_CONFIG:
				return getPriorityConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__ASSIGN_POLICY_CONFIG:
				return getAssignPolicyConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__FIX_THREAD_POOL_EXECUTOR_CONFIG:
				return getFixThreadPoolExecutorConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__TASK_TYPE_CONFIG:
				return getTaskTypeConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__CONNECTION_MANAGEMENT_CONFIG:
				return getConnectionManagementConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__IMPORT_DATA_VARIABLE_CONFIG:
				return getImportDataVariableConfig();
			case CoreconfigPackage.FIX_FLOW_CONFIG__VERSION:
				return getVersion();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CoreconfigPackage.FIX_FLOW_CONFIG__DATA_BASE_CONFIG:
				setDataBaseConfig((DataBaseConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__GROUP_DEFINITION_CONFIG:
				setGroupDefinitionConfig((GroupDefinitionConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__TASK_COMMAND_CONFIG:
				setTaskCommandConfig((TaskCommandConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG:
				setDesignerOrgConfig((DesignerOrgConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__SYS_MAIL_CONFIG:
				setSysMailConfig((SysMailConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CLASS_CONFIG:
				setExpandClassConfig((ExpandClassConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG:
				setEventSubscriptionConfig((EventSubscriptionConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__QUARTZ_CONFIG:
				setQuartzConfig((QuartzConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__SCRIPT_LANGUAGE_CONFIG:
				setScriptLanguageConfig((ScriptLanguageConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__INTERNATIONALIZATION_CONFIG:
				setInternationalizationConfig((InternationalizationConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG:
				setPigeonholeConfig((PigeonholeConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CMD_CONFIG:
				setExpandCmdConfig((ExpandCmdConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__PRIORITY_CONFIG:
				setPriorityConfig((PriorityConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__ASSIGN_POLICY_CONFIG:
				setAssignPolicyConfig((AssignPolicyConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__FIX_THREAD_POOL_EXECUTOR_CONFIG:
				setFixThreadPoolExecutorConfig((FixThreadPoolExecutorConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__TASK_TYPE_CONFIG:
				setTaskTypeConfig((TaskTypeConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__CONNECTION_MANAGEMENT_CONFIG:
				setConnectionManagementConfig((ConnectionManagementConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__IMPORT_DATA_VARIABLE_CONFIG:
				setImportDataVariableConfig((ImportDataVariableConfig)newValue);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__VERSION:
				setVersion((String)newValue);
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
			case CoreconfigPackage.FIX_FLOW_CONFIG__DATA_BASE_CONFIG:
				setDataBaseConfig((DataBaseConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__GROUP_DEFINITION_CONFIG:
				setGroupDefinitionConfig((GroupDefinitionConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__TASK_COMMAND_CONFIG:
				setTaskCommandConfig((TaskCommandConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG:
				setDesignerOrgConfig((DesignerOrgConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__SYS_MAIL_CONFIG:
				setSysMailConfig((SysMailConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CLASS_CONFIG:
				setExpandClassConfig((ExpandClassConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG:
				setEventSubscriptionConfig((EventSubscriptionConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__QUARTZ_CONFIG:
				setQuartzConfig((QuartzConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__SCRIPT_LANGUAGE_CONFIG:
				setScriptLanguageConfig((ScriptLanguageConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__INTERNATIONALIZATION_CONFIG:
				setInternationalizationConfig((InternationalizationConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG:
				setPigeonholeConfig((PigeonholeConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CMD_CONFIG:
				setExpandCmdConfig((ExpandCmdConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__PRIORITY_CONFIG:
				setPriorityConfig((PriorityConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__ASSIGN_POLICY_CONFIG:
				setAssignPolicyConfig((AssignPolicyConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__FIX_THREAD_POOL_EXECUTOR_CONFIG:
				setFixThreadPoolExecutorConfig((FixThreadPoolExecutorConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__TASK_TYPE_CONFIG:
				setTaskTypeConfig((TaskTypeConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__CONNECTION_MANAGEMENT_CONFIG:
				setConnectionManagementConfig((ConnectionManagementConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__IMPORT_DATA_VARIABLE_CONFIG:
				setImportDataVariableConfig((ImportDataVariableConfig)null);
				return;
			case CoreconfigPackage.FIX_FLOW_CONFIG__VERSION:
				setVersion(VERSION_EDEFAULT);
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
			case CoreconfigPackage.FIX_FLOW_CONFIG__DATA_BASE_CONFIG:
				return dataBaseConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__GROUP_DEFINITION_CONFIG:
				return groupDefinitionConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__TASK_COMMAND_CONFIG:
				return taskCommandConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG:
				return designerOrgConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__SYS_MAIL_CONFIG:
				return sysMailConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CLASS_CONFIG:
				return expandClassConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG:
				return eventSubscriptionConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__QUARTZ_CONFIG:
				return quartzConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__SCRIPT_LANGUAGE_CONFIG:
				return scriptLanguageConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__INTERNATIONALIZATION_CONFIG:
				return internationalizationConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG:
				return pigeonholeConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__EXPAND_CMD_CONFIG:
				return expandCmdConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__PRIORITY_CONFIG:
				return priorityConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__ASSIGN_POLICY_CONFIG:
				return assignPolicyConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__FIX_THREAD_POOL_EXECUTOR_CONFIG:
				return fixThreadPoolExecutorConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__TASK_TYPE_CONFIG:
				return taskTypeConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__CONNECTION_MANAGEMENT_CONFIG:
				return connectionManagementConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__IMPORT_DATA_VARIABLE_CONFIG:
				return importDataVariableConfig != null;
			case CoreconfigPackage.FIX_FLOW_CONFIG__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
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
		result.append(" (version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //FixFlowConfigImpl
