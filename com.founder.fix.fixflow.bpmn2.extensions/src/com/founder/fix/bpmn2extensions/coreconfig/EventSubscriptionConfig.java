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
 * A representation of the model object '<em><b>Event Subscription Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getServerAddress <em>Server Address</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getServerPort <em>Server Port</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getMessageInfo <em>Message Info</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getSignalInfo <em>Signal Info</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getIsEnable <em>Is Enable</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getEventSubscriptionConfig()
 * @model extendedMetaData="name='eventSubscriptionConfig'"
 * @generated
 */
public interface EventSubscriptionConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Server Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server Address</em>' attribute.
	 * @see #setServerAddress(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getEventSubscriptionConfig_ServerAddress()
	 * @model
	 * @generated
	 */
	String getServerAddress();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getServerAddress <em>Server Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server Address</em>' attribute.
	 * @see #getServerAddress()
	 * @generated
	 */
	void setServerAddress(String value);

	/**
	 * Returns the value of the '<em><b>Server Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server Port</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server Port</em>' attribute.
	 * @see #setServerPort(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getEventSubscriptionConfig_ServerPort()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getServerPort();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getServerPort <em>Server Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server Port</em>' attribute.
	 * @see #getServerPort()
	 * @generated
	 */
	void setServerPort(String value);

	/**
	 * Returns the value of the '<em><b>Message Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Info</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Info</em>' attribute.
	 * @see #setMessageInfo(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getEventSubscriptionConfig_MessageInfo()
	 * @model extendedMetaData="name='messageInfo' kind='attribute'"
	 * @generated
	 */
	String getMessageInfo();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getMessageInfo <em>Message Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Info</em>' attribute.
	 * @see #getMessageInfo()
	 * @generated
	 */
	void setMessageInfo(String value);

	/**
	 * Returns the value of the '<em><b>Signal Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signal Info</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signal Info</em>' attribute.
	 * @see #setSignalInfo(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getEventSubscriptionConfig_SignalInfo()
	 * @model extendedMetaData="name='signalInfo' kind='attribute'"
	 * @generated
	 */
	String getSignalInfo();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getSignalInfo <em>Signal Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signal Info</em>' attribute.
	 * @see #getSignalInfo()
	 * @generated
	 */
	void setSignalInfo(String value);

	/**
	 * Returns the value of the '<em><b>Is Enable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Enable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Enable</em>' attribute.
	 * @see #setIsEnable(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getEventSubscriptionConfig_IsEnable()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIsEnable();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig#getIsEnable <em>Is Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Enable</em>' attribute.
	 * @see #getIsEnable()
	 * @generated
	 */
	void setIsEnable(String value);

} // EventSubscriptionConfig
