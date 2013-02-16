/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quartz Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.QuartzConfigImpl#getIsEnable <em>Is Enable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.QuartzConfigImpl#getIsDefaultConfig <em>Is Default Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.QuartzConfigImpl#getDataBaseId <em>Data Base Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QuartzConfigImpl extends EObjectImpl implements QuartzConfig {
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
	 * The default value of the '{@link #getIsDefaultConfig() <em>Is Default Config</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDefaultConfig()
	 * @generated
	 * @ordered
	 */
	protected static final String IS_DEFAULT_CONFIG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsDefaultConfig() <em>Is Default Config</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDefaultConfig()
	 * @generated
	 * @ordered
	 */
	protected String isDefaultConfig = IS_DEFAULT_CONFIG_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataBaseId() <em>Data Base Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataBaseId()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_BASE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataBaseId() <em>Data Base Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataBaseId()
	 * @generated
	 * @ordered
	 */
	protected String dataBaseId = DATA_BASE_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QuartzConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.QUARTZ_CONFIG;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.QUARTZ_CONFIG__IS_ENABLE, oldIsEnable, isEnable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIsDefaultConfig() {
		return isDefaultConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsDefaultConfig(String newIsDefaultConfig) {
		String oldIsDefaultConfig = isDefaultConfig;
		isDefaultConfig = newIsDefaultConfig;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.QUARTZ_CONFIG__IS_DEFAULT_CONFIG, oldIsDefaultConfig, isDefaultConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataBaseId() {
		return dataBaseId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataBaseId(String newDataBaseId) {
		String oldDataBaseId = dataBaseId;
		dataBaseId = newDataBaseId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.QUARTZ_CONFIG__DATA_BASE_ID, oldDataBaseId, dataBaseId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CoreconfigPackage.QUARTZ_CONFIG__IS_ENABLE:
				return getIsEnable();
			case CoreconfigPackage.QUARTZ_CONFIG__IS_DEFAULT_CONFIG:
				return getIsDefaultConfig();
			case CoreconfigPackage.QUARTZ_CONFIG__DATA_BASE_ID:
				return getDataBaseId();
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
			case CoreconfigPackage.QUARTZ_CONFIG__IS_ENABLE:
				setIsEnable((String)newValue);
				return;
			case CoreconfigPackage.QUARTZ_CONFIG__IS_DEFAULT_CONFIG:
				setIsDefaultConfig((String)newValue);
				return;
			case CoreconfigPackage.QUARTZ_CONFIG__DATA_BASE_ID:
				setDataBaseId((String)newValue);
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
			case CoreconfigPackage.QUARTZ_CONFIG__IS_ENABLE:
				setIsEnable(IS_ENABLE_EDEFAULT);
				return;
			case CoreconfigPackage.QUARTZ_CONFIG__IS_DEFAULT_CONFIG:
				setIsDefaultConfig(IS_DEFAULT_CONFIG_EDEFAULT);
				return;
			case CoreconfigPackage.QUARTZ_CONFIG__DATA_BASE_ID:
				setDataBaseId(DATA_BASE_ID_EDEFAULT);
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
			case CoreconfigPackage.QUARTZ_CONFIG__IS_ENABLE:
				return IS_ENABLE_EDEFAULT == null ? isEnable != null : !IS_ENABLE_EDEFAULT.equals(isEnable);
			case CoreconfigPackage.QUARTZ_CONFIG__IS_DEFAULT_CONFIG:
				return IS_DEFAULT_CONFIG_EDEFAULT == null ? isDefaultConfig != null : !IS_DEFAULT_CONFIG_EDEFAULT.equals(isDefaultConfig);
			case CoreconfigPackage.QUARTZ_CONFIG__DATA_BASE_ID:
				return DATA_BASE_ID_EDEFAULT == null ? dataBaseId != null : !DATA_BASE_ID_EDEFAULT.equals(dataBaseId);
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
		result.append(" (isEnable: ");
		result.append(isEnable);
		result.append(", isDefaultConfig: ");
		result.append(isDefaultConfig);
		result.append(", dataBaseId: ");
		result.append(dataBaseId);
		result.append(')');
		return result.toString();
	}

} //QuartzConfigImpl
