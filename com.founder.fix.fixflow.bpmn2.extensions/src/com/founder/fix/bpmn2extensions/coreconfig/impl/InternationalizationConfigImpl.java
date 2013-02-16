/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Internationalization Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.InternationalizationConfigImpl#getIsEnable <em>Is Enable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.InternationalizationConfigImpl#getFolderPath <em>Folder Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InternationalizationConfigImpl extends EObjectImpl implements InternationalizationConfig {
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
	 * The default value of the '{@link #getFolderPath() <em>Folder Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFolderPath()
	 * @generated
	 * @ordered
	 */
	protected static final String FOLDER_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFolderPath() <em>Folder Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFolderPath()
	 * @generated
	 * @ordered
	 */
	protected String folderPath = FOLDER_PATH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InternationalizationConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.INTERNATIONALIZATION_CONFIG;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.INTERNATIONALIZATION_CONFIG__IS_ENABLE, oldIsEnable, isEnable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFolderPath() {
		return folderPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFolderPath(String newFolderPath) {
		String oldFolderPath = folderPath;
		folderPath = newFolderPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.INTERNATIONALIZATION_CONFIG__FOLDER_PATH, oldFolderPath, folderPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CoreconfigPackage.INTERNATIONALIZATION_CONFIG__IS_ENABLE:
				return getIsEnable();
			case CoreconfigPackage.INTERNATIONALIZATION_CONFIG__FOLDER_PATH:
				return getFolderPath();
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
			case CoreconfigPackage.INTERNATIONALIZATION_CONFIG__IS_ENABLE:
				setIsEnable((String)newValue);
				return;
			case CoreconfigPackage.INTERNATIONALIZATION_CONFIG__FOLDER_PATH:
				setFolderPath((String)newValue);
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
			case CoreconfigPackage.INTERNATIONALIZATION_CONFIG__IS_ENABLE:
				setIsEnable(IS_ENABLE_EDEFAULT);
				return;
			case CoreconfigPackage.INTERNATIONALIZATION_CONFIG__FOLDER_PATH:
				setFolderPath(FOLDER_PATH_EDEFAULT);
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
			case CoreconfigPackage.INTERNATIONALIZATION_CONFIG__IS_ENABLE:
				return IS_ENABLE_EDEFAULT == null ? isEnable != null : !IS_ENABLE_EDEFAULT.equals(isEnable);
			case CoreconfigPackage.INTERNATIONALIZATION_CONFIG__FOLDER_PATH:
				return FOLDER_PATH_EDEFAULT == null ? folderPath != null : !FOLDER_PATH_EDEFAULT.equals(folderPath);
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
		result.append(", folderPath: ");
		result.append(folderPath);
		result.append(')');
		return result.toString();
	}

} //InternationalizationConfigImpl
