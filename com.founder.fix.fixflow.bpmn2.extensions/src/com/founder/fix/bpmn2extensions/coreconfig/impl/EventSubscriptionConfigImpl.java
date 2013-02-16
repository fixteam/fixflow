/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Subscription Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.EventSubscriptionConfigImpl#getServerAddress <em>Server Address</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.EventSubscriptionConfigImpl#getServerPort <em>Server Port</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.EventSubscriptionConfigImpl#getMessageInfo <em>Message Info</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.EventSubscriptionConfigImpl#getSignalInfo <em>Signal Info</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.EventSubscriptionConfigImpl#getIsEnable <em>Is Enable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventSubscriptionConfigImpl extends EObjectImpl implements EventSubscriptionConfig {
	/**
	 * The default value of the '{@link #getServerAddress() <em>Server Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVER_ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServerAddress() <em>Server Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerAddress()
	 * @generated
	 * @ordered
	 */
	protected String serverAddress = SERVER_ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getServerPort() <em>Server Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerPort()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVER_PORT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServerPort() <em>Server Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerPort()
	 * @generated
	 * @ordered
	 */
	protected String serverPort = SERVER_PORT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMessageInfo() <em>Message Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageInfo()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_INFO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageInfo() <em>Message Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageInfo()
	 * @generated
	 * @ordered
	 */
	protected String messageInfo = MESSAGE_INFO_EDEFAULT;

	/**
	 * The default value of the '{@link #getSignalInfo() <em>Signal Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignalInfo()
	 * @generated
	 * @ordered
	 */
	protected static final String SIGNAL_INFO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSignalInfo() <em>Signal Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignalInfo()
	 * @generated
	 * @ordered
	 */
	protected String signalInfo = SIGNAL_INFO_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsEnable() <em>Is Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsEnable()
	 * @generated
	 * @ordered
	 */
	protected static final String IS_ENABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsEnable() <em>Is Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsEnable()
	 * @generated
	 * @ordered
	 */
	protected String isEnable = IS_ENABLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventSubscriptionConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.EVENT_SUBSCRIPTION_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServerAddress() {
		return serverAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServerAddress(String newServerAddress) {
		String oldServerAddress = serverAddress;
		serverAddress = newServerAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SERVER_ADDRESS, oldServerAddress, serverAddress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServerPort() {
		return serverPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServerPort(String newServerPort) {
		String oldServerPort = serverPort;
		serverPort = newServerPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SERVER_PORT, oldServerPort, serverPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMessageInfo() {
		return messageInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessageInfo(String newMessageInfo) {
		String oldMessageInfo = messageInfo;
		messageInfo = newMessageInfo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__MESSAGE_INFO, oldMessageInfo, messageInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSignalInfo() {
		return signalInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignalInfo(String newSignalInfo) {
		String oldSignalInfo = signalInfo;
		signalInfo = newSignalInfo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SIGNAL_INFO, oldSignalInfo, signalInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIsEnable() {
		return isEnable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEnable(String newIsEnable) {
		String oldIsEnable = isEnable;
		isEnable = newIsEnable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__IS_ENABLE, oldIsEnable, isEnable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SERVER_ADDRESS:
				return getServerAddress();
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SERVER_PORT:
				return getServerPort();
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__MESSAGE_INFO:
				return getMessageInfo();
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SIGNAL_INFO:
				return getSignalInfo();
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__IS_ENABLE:
				return getIsEnable();
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
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SERVER_ADDRESS:
				setServerAddress((String)newValue);
				return;
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SERVER_PORT:
				setServerPort((String)newValue);
				return;
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__MESSAGE_INFO:
				setMessageInfo((String)newValue);
				return;
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SIGNAL_INFO:
				setSignalInfo((String)newValue);
				return;
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__IS_ENABLE:
				setIsEnable((String)newValue);
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
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SERVER_ADDRESS:
				setServerAddress(SERVER_ADDRESS_EDEFAULT);
				return;
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SERVER_PORT:
				setServerPort(SERVER_PORT_EDEFAULT);
				return;
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__MESSAGE_INFO:
				setMessageInfo(MESSAGE_INFO_EDEFAULT);
				return;
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SIGNAL_INFO:
				setSignalInfo(SIGNAL_INFO_EDEFAULT);
				return;
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__IS_ENABLE:
				setIsEnable(IS_ENABLE_EDEFAULT);
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
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SERVER_ADDRESS:
				return SERVER_ADDRESS_EDEFAULT == null ? serverAddress != null : !SERVER_ADDRESS_EDEFAULT.equals(serverAddress);
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SERVER_PORT:
				return SERVER_PORT_EDEFAULT == null ? serverPort != null : !SERVER_PORT_EDEFAULT.equals(serverPort);
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__MESSAGE_INFO:
				return MESSAGE_INFO_EDEFAULT == null ? messageInfo != null : !MESSAGE_INFO_EDEFAULT.equals(messageInfo);
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__SIGNAL_INFO:
				return SIGNAL_INFO_EDEFAULT == null ? signalInfo != null : !SIGNAL_INFO_EDEFAULT.equals(signalInfo);
			case CoreconfigPackage.EVENT_SUBSCRIPTION_CONFIG__IS_ENABLE:
				return IS_ENABLE_EDEFAULT == null ? isEnable != null : !IS_ENABLE_EDEFAULT.equals(isEnable);
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
		result.append(" (serverAddress: ");
		result.append(serverAddress);
		result.append(", serverPort: ");
		result.append(serverPort);
		result.append(", messageInfo: ");
		result.append(messageInfo);
		result.append(", signalInfo: ");
		result.append(signalInfo);
		result.append(", isEnable: ");
		result.append(isEnable);
		result.append(')');
		return result.toString();
	}

} //EventSubscriptionConfigImpl
