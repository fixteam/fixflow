/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig;

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
 * An implementation of the model object '<em><b>Data Base Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseConfigImpl#getDataBase <em>Data Base</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseConfigImpl#getSelected <em>Selected</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseConfigImpl#getMode <em>Mode</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataBaseConfigImpl extends EObjectImpl implements DataBaseConfig {
	/**
	 * The cached value of the '{@link #getDataBase() <em>Data Base</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataBase()
	 * @generated
	 * @ordered
	 */
	protected EList<DataBase> dataBase;

	/**
	 * The default value of the '{@link #getSelected() <em>Selected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelected()
	 * @generated
	 * @ordered
	 */
	protected static final String SELECTED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSelected() <em>Selected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelected()
	 * @generated
	 * @ordered
	 */
	protected String selected = SELECTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected static final String MODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected String mode = MODE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataBaseConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.DATA_BASE_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataBase> getDataBase() {
		if (dataBase == null) {
			dataBase = new EObjectContainmentEList<DataBase>(DataBase.class, this, CoreconfigPackage.DATA_BASE_CONFIG__DATA_BASE);
		}
		return dataBase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSelected() {
		return selected;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelected(String newSelected) {
		String oldSelected = selected;
		selected = newSelected;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DATA_BASE_CONFIG__SELECTED, oldSelected, selected));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMode(String newMode) {
		String oldMode = mode;
		mode = newMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DATA_BASE_CONFIG__MODE, oldMode, mode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CoreconfigPackage.DATA_BASE_CONFIG__DATA_BASE:
				return ((InternalEList<?>)getDataBase()).basicRemove(otherEnd, msgs);
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
			case CoreconfigPackage.DATA_BASE_CONFIG__DATA_BASE:
				return getDataBase();
			case CoreconfigPackage.DATA_BASE_CONFIG__SELECTED:
				return getSelected();
			case CoreconfigPackage.DATA_BASE_CONFIG__MODE:
				return getMode();
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
			case CoreconfigPackage.DATA_BASE_CONFIG__DATA_BASE:
				getDataBase().clear();
				getDataBase().addAll((Collection<? extends DataBase>)newValue);
				return;
			case CoreconfigPackage.DATA_BASE_CONFIG__SELECTED:
				setSelected((String)newValue);
				return;
			case CoreconfigPackage.DATA_BASE_CONFIG__MODE:
				setMode((String)newValue);
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
			case CoreconfigPackage.DATA_BASE_CONFIG__DATA_BASE:
				getDataBase().clear();
				return;
			case CoreconfigPackage.DATA_BASE_CONFIG__SELECTED:
				setSelected(SELECTED_EDEFAULT);
				return;
			case CoreconfigPackage.DATA_BASE_CONFIG__MODE:
				setMode(MODE_EDEFAULT);
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
			case CoreconfigPackage.DATA_BASE_CONFIG__DATA_BASE:
				return dataBase != null && !dataBase.isEmpty();
			case CoreconfigPackage.DATA_BASE_CONFIG__SELECTED:
				return SELECTED_EDEFAULT == null ? selected != null : !SELECTED_EDEFAULT.equals(selected);
			case CoreconfigPackage.DATA_BASE_CONFIG__MODE:
				return MODE_EDEFAULT == null ? mode != null : !MODE_EDEFAULT.equals(mode);
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
		result.append(" (selected: ");
		result.append(selected);
		result.append(", mode: ");
		result.append(mode);
		result.append(')');
		return result.toString();
	}

} //DataBaseConfigImpl
