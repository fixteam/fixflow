/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy;
import com.founder.fix.bpmn2extensions.coreconfig.AssignPolicyConfig;
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;

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
 * An implementation of the model object '<em><b>Assign Policy Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AssignPolicyConfigImpl#getAssignPolicy <em>Assign Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssignPolicyConfigImpl extends EObjectImpl implements AssignPolicyConfig {
	/**
	 * The cached value of the '{@link #getAssignPolicy() <em>Assign Policy</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignPolicy()
	 * @generated
	 * @ordered
	 */
	protected EList<AssignPolicy> assignPolicy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssignPolicyConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.ASSIGN_POLICY_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssignPolicy> getAssignPolicy() {
		if (assignPolicy == null) {
			assignPolicy = new EObjectContainmentEList<AssignPolicy>(AssignPolicy.class, this, CoreconfigPackage.ASSIGN_POLICY_CONFIG__ASSIGN_POLICY);
		}
		return assignPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CoreconfigPackage.ASSIGN_POLICY_CONFIG__ASSIGN_POLICY:
				return ((InternalEList<?>)getAssignPolicy()).basicRemove(otherEnd, msgs);
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
			case CoreconfigPackage.ASSIGN_POLICY_CONFIG__ASSIGN_POLICY:
				return getAssignPolicy();
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
			case CoreconfigPackage.ASSIGN_POLICY_CONFIG__ASSIGN_POLICY:
				getAssignPolicy().clear();
				getAssignPolicy().addAll((Collection<? extends AssignPolicy>)newValue);
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
			case CoreconfigPackage.ASSIGN_POLICY_CONFIG__ASSIGN_POLICY:
				getAssignPolicy().clear();
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
			case CoreconfigPackage.ASSIGN_POLICY_CONFIG__ASSIGN_POLICY:
				return assignPolicy != null && !assignPolicy.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AssignPolicyConfigImpl
