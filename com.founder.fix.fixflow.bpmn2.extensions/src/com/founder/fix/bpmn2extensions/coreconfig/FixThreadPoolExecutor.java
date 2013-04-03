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
 * A representation of the model object '<em><b>Fix Thread Pool Executor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor#getThreadPoolKey <em>Thread Pool Key</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor#getThreadPoolName <em>Thread Pool Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor#getCorePoolSize <em>Core Pool Size</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor#getMaximumPoolSize <em>Maximum Pool Size</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor#getKeepAliveTime <em>Keep Alive Time</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor#getTimeUnit <em>Time Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixThreadPoolExecutor()
 * @model extendedMetaData="name='fixThreadPoolExecutor'"
 * @generated
 */
public interface FixThreadPoolExecutor extends EObject {
	/**
	 * Returns the value of the '<em><b>Thread Pool Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Thread Pool Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Thread Pool Key</em>' attribute.
	 * @see #setThreadPoolKey(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixThreadPoolExecutor_ThreadPoolKey()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getThreadPoolKey();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor#getThreadPoolKey <em>Thread Pool Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Thread Pool Key</em>' attribute.
	 * @see #getThreadPoolKey()
	 * @generated
	 */
	void setThreadPoolKey(String value);

	/**
	 * Returns the value of the '<em><b>Thread Pool Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Thread Pool Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Thread Pool Name</em>' attribute.
	 * @see #setThreadPoolName(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixThreadPoolExecutor_ThreadPoolName()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getThreadPoolName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor#getThreadPoolName <em>Thread Pool Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Thread Pool Name</em>' attribute.
	 * @see #getThreadPoolName()
	 * @generated
	 */
	void setThreadPoolName(String value);

	/**
	 * Returns the value of the '<em><b>Core Pool Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Core Pool Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Core Pool Size</em>' attribute.
	 * @see #setCorePoolSize(int)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixThreadPoolExecutor_CorePoolSize()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	int getCorePoolSize();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor#getCorePoolSize <em>Core Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Core Pool Size</em>' attribute.
	 * @see #getCorePoolSize()
	 * @generated
	 */
	void setCorePoolSize(int value);

	/**
	 * Returns the value of the '<em><b>Maximum Pool Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Pool Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Pool Size</em>' attribute.
	 * @see #setMaximumPoolSize(int)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixThreadPoolExecutor_MaximumPoolSize()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	int getMaximumPoolSize();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor#getMaximumPoolSize <em>Maximum Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Pool Size</em>' attribute.
	 * @see #getMaximumPoolSize()
	 * @generated
	 */
	void setMaximumPoolSize(int value);

	/**
	 * Returns the value of the '<em><b>Keep Alive Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keep Alive Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Keep Alive Time</em>' attribute.
	 * @see #setKeepAliveTime(long)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixThreadPoolExecutor_KeepAliveTime()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	long getKeepAliveTime();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor#getKeepAliveTime <em>Keep Alive Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Keep Alive Time</em>' attribute.
	 * @see #getKeepAliveTime()
	 * @generated
	 */
	void setKeepAliveTime(long value);

	/**
	 * Returns the value of the '<em><b>Time Unit</b></em>' attribute.
	 * The literals are from the enumeration {@link com.founder.fix.bpmn2extensions.coreconfig.TimeUnitType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Unit</em>' attribute.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TimeUnitType
	 * @see #setTimeUnit(TimeUnitType)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getFixThreadPoolExecutor_TimeUnit()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	TimeUnitType getTimeUnit();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor#getTimeUnit <em>Time Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Unit</em>' attribute.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.TimeUnitType
	 * @see #getTimeUnit()
	 * @generated
	 */
	void setTimeUnit(TimeUnitType value);

} // FixThreadPoolExecutor
