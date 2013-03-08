/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor;
import com.founder.fix.bpmn2extensions.coreconfig.TimeUnitType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fix Thread Pool Executor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixThreadPoolExecutorImpl#getThreadPoolKey <em>Thread Pool Key</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixThreadPoolExecutorImpl#getThreadPoolName <em>Thread Pool Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixThreadPoolExecutorImpl#getCorePoolSize <em>Core Pool Size</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixThreadPoolExecutorImpl#getMaximumPoolSize <em>Maximum Pool Size</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixThreadPoolExecutorImpl#getKeepAliveTime <em>Keep Alive Time</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixThreadPoolExecutorImpl#getTimeUnit <em>Time Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FixThreadPoolExecutorImpl extends EObjectImpl implements FixThreadPoolExecutor {
	/**
	 * The default value of the '{@link #getThreadPoolKey() <em>Thread Pool Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThreadPoolKey()
	 * @generated
	 * @ordered
	 */
	protected static final String THREAD_POOL_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getThreadPoolKey() <em>Thread Pool Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThreadPoolKey()
	 * @generated
	 * @ordered
	 */
	protected String threadPoolKey = THREAD_POOL_KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getThreadPoolName() <em>Thread Pool Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThreadPoolName()
	 * @generated
	 * @ordered
	 */
	protected static final String THREAD_POOL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getThreadPoolName() <em>Thread Pool Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThreadPoolName()
	 * @generated
	 * @ordered
	 */
	protected String threadPoolName = THREAD_POOL_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCorePoolSize() <em>Core Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorePoolSize()
	 * @generated
	 * @ordered
	 */
	protected static final int CORE_POOL_SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCorePoolSize() <em>Core Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorePoolSize()
	 * @generated
	 * @ordered
	 */
	protected int corePoolSize = CORE_POOL_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumPoolSize() <em>Maximum Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPoolSize()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_POOL_SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumPoolSize() <em>Maximum Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPoolSize()
	 * @generated
	 * @ordered
	 */
	protected int maximumPoolSize = MAXIMUM_POOL_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getKeepAliveTime() <em>Keep Alive Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeepAliveTime()
	 * @generated
	 * @ordered
	 */
	protected static final long KEEP_ALIVE_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getKeepAliveTime() <em>Keep Alive Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeepAliveTime()
	 * @generated
	 * @ordered
	 */
	protected long keepAliveTime = KEEP_ALIVE_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeUnit() <em>Time Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeUnit()
	 * @generated
	 * @ordered
	 */
	protected static final TimeUnitType TIME_UNIT_EDEFAULT = TimeUnitType.DAYS;

	/**
	 * The cached value of the '{@link #getTimeUnit() <em>Time Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeUnit()
	 * @generated
	 * @ordered
	 */
	protected TimeUnitType timeUnit = TIME_UNIT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FixThreadPoolExecutorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.FIX_THREAD_POOL_EXECUTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getThreadPoolKey() {
		return threadPoolKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThreadPoolKey(String newThreadPoolKey) {
		String oldThreadPoolKey = threadPoolKey;
		threadPoolKey = newThreadPoolKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__THREAD_POOL_KEY, oldThreadPoolKey, threadPoolKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getThreadPoolName() {
		return threadPoolName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThreadPoolName(String newThreadPoolName) {
		String oldThreadPoolName = threadPoolName;
		threadPoolName = newThreadPoolName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__THREAD_POOL_NAME, oldThreadPoolName, threadPoolName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCorePoolSize() {
		return corePoolSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCorePoolSize(int newCorePoolSize) {
		int oldCorePoolSize = corePoolSize;
		corePoolSize = newCorePoolSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__CORE_POOL_SIZE, oldCorePoolSize, corePoolSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumPoolSize(int newMaximumPoolSize) {
		int oldMaximumPoolSize = maximumPoolSize;
		maximumPoolSize = newMaximumPoolSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__MAXIMUM_POOL_SIZE, oldMaximumPoolSize, maximumPoolSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getKeepAliveTime() {
		return keepAliveTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKeepAliveTime(long newKeepAliveTime) {
		long oldKeepAliveTime = keepAliveTime;
		keepAliveTime = newKeepAliveTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__KEEP_ALIVE_TIME, oldKeepAliveTime, keepAliveTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeUnitType getTimeUnit() {
		return timeUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeUnit(TimeUnitType newTimeUnit) {
		TimeUnitType oldTimeUnit = timeUnit;
		timeUnit = newTimeUnit == null ? TIME_UNIT_EDEFAULT : newTimeUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__TIME_UNIT, oldTimeUnit, timeUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__THREAD_POOL_KEY:
				return getThreadPoolKey();
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__THREAD_POOL_NAME:
				return getThreadPoolName();
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__CORE_POOL_SIZE:
				return getCorePoolSize();
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__MAXIMUM_POOL_SIZE:
				return getMaximumPoolSize();
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__KEEP_ALIVE_TIME:
				return getKeepAliveTime();
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__TIME_UNIT:
				return getTimeUnit();
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
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__THREAD_POOL_KEY:
				setThreadPoolKey((String)newValue);
				return;
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__THREAD_POOL_NAME:
				setThreadPoolName((String)newValue);
				return;
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__CORE_POOL_SIZE:
				setCorePoolSize((Integer)newValue);
				return;
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__MAXIMUM_POOL_SIZE:
				setMaximumPoolSize((Integer)newValue);
				return;
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__KEEP_ALIVE_TIME:
				setKeepAliveTime((Long)newValue);
				return;
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__TIME_UNIT:
				setTimeUnit((TimeUnitType)newValue);
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
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__THREAD_POOL_KEY:
				setThreadPoolKey(THREAD_POOL_KEY_EDEFAULT);
				return;
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__THREAD_POOL_NAME:
				setThreadPoolName(THREAD_POOL_NAME_EDEFAULT);
				return;
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__CORE_POOL_SIZE:
				setCorePoolSize(CORE_POOL_SIZE_EDEFAULT);
				return;
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__MAXIMUM_POOL_SIZE:
				setMaximumPoolSize(MAXIMUM_POOL_SIZE_EDEFAULT);
				return;
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__KEEP_ALIVE_TIME:
				setKeepAliveTime(KEEP_ALIVE_TIME_EDEFAULT);
				return;
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__TIME_UNIT:
				setTimeUnit(TIME_UNIT_EDEFAULT);
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
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__THREAD_POOL_KEY:
				return THREAD_POOL_KEY_EDEFAULT == null ? threadPoolKey != null : !THREAD_POOL_KEY_EDEFAULT.equals(threadPoolKey);
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__THREAD_POOL_NAME:
				return THREAD_POOL_NAME_EDEFAULT == null ? threadPoolName != null : !THREAD_POOL_NAME_EDEFAULT.equals(threadPoolName);
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__CORE_POOL_SIZE:
				return corePoolSize != CORE_POOL_SIZE_EDEFAULT;
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__MAXIMUM_POOL_SIZE:
				return maximumPoolSize != MAXIMUM_POOL_SIZE_EDEFAULT;
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__KEEP_ALIVE_TIME:
				return keepAliveTime != KEEP_ALIVE_TIME_EDEFAULT;
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR__TIME_UNIT:
				return timeUnit != TIME_UNIT_EDEFAULT;
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
		result.append(" (threadPoolKey: ");
		result.append(threadPoolKey);
		result.append(", threadPoolName: ");
		result.append(threadPoolName);
		result.append(", corePoolSize: ");
		result.append(corePoolSize);
		result.append(", maximumPoolSize: ");
		result.append(maximumPoolSize);
		result.append(", keepAliveTime: ");
		result.append(keepAliveTime);
		result.append(", timeUnit: ");
		result.append(timeUnit);
		result.append(')');
		return result.toString();
	}

} //FixThreadPoolExecutorImpl
