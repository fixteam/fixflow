/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutor;
import com.founder.fix.bpmn2extensions.coreconfig.FixThreadPoolExecutorConfig;

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
 * An implementation of the model object '<em><b>Fix Thread Pool Executor Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.FixThreadPoolExecutorConfigImpl#getFixThreadPoolExecutor <em>Fix Thread Pool Executor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FixThreadPoolExecutorConfigImpl extends EObjectImpl implements FixThreadPoolExecutorConfig {
	/**
	 * The cached value of the '{@link #getFixThreadPoolExecutor() <em>Fix Thread Pool Executor</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFixThreadPoolExecutor()
	 * @generated
	 * @ordered
	 */
	protected EList<FixThreadPoolExecutor> fixThreadPoolExecutor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FixThreadPoolExecutorConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.FIX_THREAD_POOL_EXECUTOR_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FixThreadPoolExecutor> getFixThreadPoolExecutor() {
		if (fixThreadPoolExecutor == null) {
			fixThreadPoolExecutor = new EObjectContainmentEList<FixThreadPoolExecutor>(FixThreadPoolExecutor.class, this, CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR_CONFIG__FIX_THREAD_POOL_EXECUTOR);
		}
		return fixThreadPoolExecutor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR_CONFIG__FIX_THREAD_POOL_EXECUTOR:
				return ((InternalEList<?>)getFixThreadPoolExecutor()).basicRemove(otherEnd, msgs);
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
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR_CONFIG__FIX_THREAD_POOL_EXECUTOR:
				return getFixThreadPoolExecutor();
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
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR_CONFIG__FIX_THREAD_POOL_EXECUTOR:
				getFixThreadPoolExecutor().clear();
				getFixThreadPoolExecutor().addAll((Collection<? extends FixThreadPoolExecutor>)newValue);
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
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR_CONFIG__FIX_THREAD_POOL_EXECUTOR:
				getFixThreadPoolExecutor().clear();
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
			case CoreconfigPackage.FIX_THREAD_POOL_EXECUTOR_CONFIG__FIX_THREAD_POOL_EXECUTOR:
				return fixThreadPoolExecutor != null && !fixThreadPoolExecutor.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //FixThreadPoolExecutorConfigImpl
