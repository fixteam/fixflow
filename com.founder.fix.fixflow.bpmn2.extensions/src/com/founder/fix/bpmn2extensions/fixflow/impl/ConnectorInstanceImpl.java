/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow.impl;

import com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef;
import com.founder.fix.bpmn2extensions.fixflow.Documentation;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connector Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl#getConnectorId <em>Connector Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl#getConnectorInstanceId <em>Connector Instance Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl#getConnectorInstanceName <em>Connector Instance Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl#getEventType <em>Event Type</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl#getErrorHandling <em>Error Handling</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl#getErrorCode <em>Error Code</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl#getConnectorParameterInputs <em>Connector Parameter Inputs</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl#getConnectorParameterOutputs <em>Connector Parameter Outputs</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ConnectorInstanceImpl#getConnectorParameterOutputsDef <em>Connector Parameter Outputs Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectorInstanceImpl extends EObjectImpl implements ConnectorInstance {
	/**
	 * The default value of the '{@link #getConnectorId() <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorId()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTOR_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectorId() <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorId()
	 * @generated
	 * @ordered
	 */
	protected String connectorId = CONNECTOR_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected String packageName = PACKAGE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected String className = CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectorInstanceId() <em>Connector Instance Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorInstanceId()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTOR_INSTANCE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectorInstanceId() <em>Connector Instance Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorInstanceId()
	 * @generated
	 * @ordered
	 */
	protected String connectorInstanceId = CONNECTOR_INSTANCE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectorInstanceName() <em>Connector Instance Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorInstanceName()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTOR_INSTANCE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectorInstanceName() <em>Connector Instance Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorInstanceName()
	 * @generated
	 * @ordered
	 */
	protected String connectorInstanceName = CONNECTOR_INSTANCE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEventType() <em>Event Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventType()
	 * @generated
	 * @ordered
	 */
	protected static final String EVENT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEventType() <em>Event Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventType()
	 * @generated
	 * @ordered
	 */
	protected String eventType = EVENT_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected Documentation documentation;

	/**
	 * The default value of the '{@link #getErrorHandling() <em>Error Handling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorHandling()
	 * @generated
	 * @ordered
	 */
	protected static final String ERROR_HANDLING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getErrorHandling() <em>Error Handling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorHandling()
	 * @generated
	 * @ordered
	 */
	protected String errorHandling = ERROR_HANDLING_EDEFAULT;

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
	 * The cached value of the '{@link #getConnectorParameterInputs() <em>Connector Parameter Inputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorParameterInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<ConnectorParameterInputs> connectorParameterInputs;

	/**
	 * The cached value of the '{@link #getConnectorParameterOutputs() <em>Connector Parameter Outputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorParameterOutputs()
	 * @generated
	 * @ordered
	 */
	protected EList<ConnectorParameterOutputs> connectorParameterOutputs;

	/**
	 * The cached value of the '{@link #getConnectorParameterOutputsDef() <em>Connector Parameter Outputs Def</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorParameterOutputsDef()
	 * @generated
	 * @ordered
	 */
	protected EList<ConnectorParameterOutputsDef> connectorParameterOutputsDef;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectorInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FixFlowPackage.Literals.CONNECTOR_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnectorId() {
		return connectorId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectorId(String newConnectorId) {
		String oldConnectorId = connectorId;
		connectorId = newConnectorId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_ID, oldConnectorId, connectorId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageName(String newPackageName) {
		String oldPackageName = packageName;
		packageName = newPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.CONNECTOR_INSTANCE__PACKAGE_NAME, oldPackageName, packageName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassName(String newClassName) {
		String oldClassName = className;
		className = newClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.CONNECTOR_INSTANCE__CLASS_NAME, oldClassName, className));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnectorInstanceId() {
		return connectorInstanceId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectorInstanceId(String newConnectorInstanceId) {
		String oldConnectorInstanceId = connectorInstanceId;
		connectorInstanceId = newConnectorInstanceId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_ID, oldConnectorInstanceId, connectorInstanceId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnectorInstanceName() {
		return connectorInstanceName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectorInstanceName(String newConnectorInstanceName) {
		String oldConnectorInstanceName = connectorInstanceName;
		connectorInstanceName = newConnectorInstanceName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_NAME, oldConnectorInstanceName, connectorInstanceName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventType(String newEventType) {
		String oldEventType = eventType;
		eventType = newEventType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.CONNECTOR_INSTANCE__EVENT_TYPE, oldEventType, eventType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Documentation getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDocumentation(Documentation newDocumentation, NotificationChain msgs) {
		Documentation oldDocumentation = documentation;
		documentation = newDocumentation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.CONNECTOR_INSTANCE__DOCUMENTATION, oldDocumentation, newDocumentation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDocumentation(Documentation newDocumentation) {
		if (newDocumentation != documentation) {
			NotificationChain msgs = null;
			if (documentation != null)
				msgs = ((InternalEObject)documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.CONNECTOR_INSTANCE__DOCUMENTATION, null, msgs);
			if (newDocumentation != null)
				msgs = ((InternalEObject)newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.CONNECTOR_INSTANCE__DOCUMENTATION, null, msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.CONNECTOR_INSTANCE__DOCUMENTATION, newDocumentation, newDocumentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getErrorHandling() {
		return errorHandling;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setErrorHandling(String newErrorHandling) {
		String oldErrorHandling = errorHandling;
		errorHandling = newErrorHandling;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.CONNECTOR_INSTANCE__ERROR_HANDLING, oldErrorHandling, errorHandling));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.CONNECTOR_INSTANCE__ERROR_CODE, oldErrorCode, errorCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConnectorParameterInputs> getConnectorParameterInputs() {
		if (connectorParameterInputs == null) {
			connectorParameterInputs = new EObjectContainmentEList<ConnectorParameterInputs>(ConnectorParameterInputs.class, this, FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_INPUTS);
		}
		return connectorParameterInputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConnectorParameterOutputs> getConnectorParameterOutputs() {
		if (connectorParameterOutputs == null) {
			connectorParameterOutputs = new EObjectContainmentEList<ConnectorParameterOutputs>(ConnectorParameterOutputs.class, this, FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS);
		}
		return connectorParameterOutputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConnectorParameterOutputsDef> getConnectorParameterOutputsDef() {
		if (connectorParameterOutputsDef == null) {
			connectorParameterOutputsDef = new EObjectContainmentEList<ConnectorParameterOutputsDef>(ConnectorParameterOutputsDef.class, this, FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS_DEF);
		}
		return connectorParameterOutputsDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FixFlowPackage.CONNECTOR_INSTANCE__DOCUMENTATION:
				return basicSetDocumentation(null, msgs);
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_INPUTS:
				return ((InternalEList<?>)getConnectorParameterInputs()).basicRemove(otherEnd, msgs);
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS:
				return ((InternalEList<?>)getConnectorParameterOutputs()).basicRemove(otherEnd, msgs);
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS_DEF:
				return ((InternalEList<?>)getConnectorParameterOutputsDef()).basicRemove(otherEnd, msgs);
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
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_ID:
				return getConnectorId();
			case FixFlowPackage.CONNECTOR_INSTANCE__PACKAGE_NAME:
				return getPackageName();
			case FixFlowPackage.CONNECTOR_INSTANCE__CLASS_NAME:
				return getClassName();
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_ID:
				return getConnectorInstanceId();
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_NAME:
				return getConnectorInstanceName();
			case FixFlowPackage.CONNECTOR_INSTANCE__EVENT_TYPE:
				return getEventType();
			case FixFlowPackage.CONNECTOR_INSTANCE__DOCUMENTATION:
				return getDocumentation();
			case FixFlowPackage.CONNECTOR_INSTANCE__ERROR_HANDLING:
				return getErrorHandling();
			case FixFlowPackage.CONNECTOR_INSTANCE__ERROR_CODE:
				return getErrorCode();
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_INPUTS:
				return getConnectorParameterInputs();
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS:
				return getConnectorParameterOutputs();
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS_DEF:
				return getConnectorParameterOutputsDef();
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
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_ID:
				setConnectorId((String)newValue);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__PACKAGE_NAME:
				setPackageName((String)newValue);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_ID:
				setConnectorInstanceId((String)newValue);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_NAME:
				setConnectorInstanceName((String)newValue);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__EVENT_TYPE:
				setEventType((String)newValue);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__DOCUMENTATION:
				setDocumentation((Documentation)newValue);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__ERROR_HANDLING:
				setErrorHandling((String)newValue);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__ERROR_CODE:
				setErrorCode((String)newValue);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_INPUTS:
				getConnectorParameterInputs().clear();
				getConnectorParameterInputs().addAll((Collection<? extends ConnectorParameterInputs>)newValue);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS:
				getConnectorParameterOutputs().clear();
				getConnectorParameterOutputs().addAll((Collection<? extends ConnectorParameterOutputs>)newValue);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS_DEF:
				getConnectorParameterOutputsDef().clear();
				getConnectorParameterOutputsDef().addAll((Collection<? extends ConnectorParameterOutputsDef>)newValue);
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
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_ID:
				setConnectorId(CONNECTOR_ID_EDEFAULT);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__PACKAGE_NAME:
				setPackageName(PACKAGE_NAME_EDEFAULT);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_ID:
				setConnectorInstanceId(CONNECTOR_INSTANCE_ID_EDEFAULT);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_NAME:
				setConnectorInstanceName(CONNECTOR_INSTANCE_NAME_EDEFAULT);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__EVENT_TYPE:
				setEventType(EVENT_TYPE_EDEFAULT);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__DOCUMENTATION:
				setDocumentation((Documentation)null);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__ERROR_HANDLING:
				setErrorHandling(ERROR_HANDLING_EDEFAULT);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__ERROR_CODE:
				setErrorCode(ERROR_CODE_EDEFAULT);
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_INPUTS:
				getConnectorParameterInputs().clear();
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS:
				getConnectorParameterOutputs().clear();
				return;
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS_DEF:
				getConnectorParameterOutputsDef().clear();
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
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_ID:
				return CONNECTOR_ID_EDEFAULT == null ? connectorId != null : !CONNECTOR_ID_EDEFAULT.equals(connectorId);
			case FixFlowPackage.CONNECTOR_INSTANCE__PACKAGE_NAME:
				return PACKAGE_NAME_EDEFAULT == null ? packageName != null : !PACKAGE_NAME_EDEFAULT.equals(packageName);
			case FixFlowPackage.CONNECTOR_INSTANCE__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_ID:
				return CONNECTOR_INSTANCE_ID_EDEFAULT == null ? connectorInstanceId != null : !CONNECTOR_INSTANCE_ID_EDEFAULT.equals(connectorInstanceId);
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_INSTANCE_NAME:
				return CONNECTOR_INSTANCE_NAME_EDEFAULT == null ? connectorInstanceName != null : !CONNECTOR_INSTANCE_NAME_EDEFAULT.equals(connectorInstanceName);
			case FixFlowPackage.CONNECTOR_INSTANCE__EVENT_TYPE:
				return EVENT_TYPE_EDEFAULT == null ? eventType != null : !EVENT_TYPE_EDEFAULT.equals(eventType);
			case FixFlowPackage.CONNECTOR_INSTANCE__DOCUMENTATION:
				return documentation != null;
			case FixFlowPackage.CONNECTOR_INSTANCE__ERROR_HANDLING:
				return ERROR_HANDLING_EDEFAULT == null ? errorHandling != null : !ERROR_HANDLING_EDEFAULT.equals(errorHandling);
			case FixFlowPackage.CONNECTOR_INSTANCE__ERROR_CODE:
				return ERROR_CODE_EDEFAULT == null ? errorCode != null : !ERROR_CODE_EDEFAULT.equals(errorCode);
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_INPUTS:
				return connectorParameterInputs != null && !connectorParameterInputs.isEmpty();
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS:
				return connectorParameterOutputs != null && !connectorParameterOutputs.isEmpty();
			case FixFlowPackage.CONNECTOR_INSTANCE__CONNECTOR_PARAMETER_OUTPUTS_DEF:
				return connectorParameterOutputsDef != null && !connectorParameterOutputsDef.isEmpty();
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
		result.append(" (connectorId: ");
		result.append(connectorId);
		result.append(", packageName: ");
		result.append(packageName);
		result.append(", className: ");
		result.append(className);
		result.append(", connectorInstanceId: ");
		result.append(connectorInstanceId);
		result.append(", connectorInstanceName: ");
		result.append(connectorInstanceName);
		result.append(", eventType: ");
		result.append(eventType);
		result.append(", errorHandling: ");
		result.append(errorHandling);
		result.append(", errorCode: ");
		result.append(errorCode);
		result.append(')');
		return result.toString();
	}

} //ConnectorInstanceImpl
