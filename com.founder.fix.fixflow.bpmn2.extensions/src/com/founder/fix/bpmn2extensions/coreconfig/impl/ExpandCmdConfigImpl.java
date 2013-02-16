/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandCmdConfig;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expand Cmd Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandCmdConfigImpl#getExpandCmd <em>Expand Cmd</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExpandCmdConfigImpl extends EObjectImpl implements ExpandCmdConfig {
	/**
	 * The cached value of the '{@link #getExpandCmd() <em>Expand Cmd</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpandCmd()
	 * @generated
	 * @ordered
	 */
	protected EList<ExpandCmd> expandCmd;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpandCmdConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.EXPAND_CMD_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExpandCmd> getExpandCmd() {
		if (expandCmd == null) {
			expandCmd = new EObjectContainmentEList<ExpandCmd>(ExpandCmd.class, this, CoreconfigPackage.EXPAND_CMD_CONFIG__EXPAND_CMD);
		}
		return expandCmd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CoreconfigPackage.EXPAND_CMD_CONFIG__EXPAND_CMD:
				return ((InternalEList<?>)getExpandCmd()).basicRemove(otherEnd, msgs);
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
			case CoreconfigPackage.EXPAND_CMD_CONFIG__EXPAND_CMD:
				return getExpandCmd();
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
			case CoreconfigPackage.EXPAND_CMD_CONFIG__EXPAND_CMD:
				getExpandCmd().clear();
				getExpandCmd().addAll((Collection<? extends ExpandCmd>)newValue);
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
			case CoreconfigPackage.EXPAND_CMD_CONFIG__EXPAND_CMD:
				getExpandCmd().clear();
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
			case CoreconfigPackage.EXPAND_CMD_CONFIG__EXPAND_CMD:
				return expandCmd != null && !expandCmd.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExpandCmdConfigImpl
