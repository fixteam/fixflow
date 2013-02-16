/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig;

import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
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
 * An implementation of the model object '<em><b>Task Command Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.TaskCommandConfigImpl#getTaskCommandDef <em>Task Command Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskCommandConfigImpl extends EObjectImpl implements TaskCommandConfig {
	/**
	 * The cached value of the '{@link #getTaskCommandDef() <em>Task Command Def</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaskCommandDef()
	 * @generated
	 * @ordered
	 */
	protected EList<TaskCommandDef> taskCommandDef;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskCommandConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.TASK_COMMAND_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TaskCommandDef> getTaskCommandDef() {
		if (taskCommandDef == null) {
			taskCommandDef = new EObjectContainmentEList<TaskCommandDef>(TaskCommandDef.class, this, CoreconfigPackage.TASK_COMMAND_CONFIG__TASK_COMMAND_DEF);
		}
		return taskCommandDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CoreconfigPackage.TASK_COMMAND_CONFIG__TASK_COMMAND_DEF:
				return ((InternalEList<?>)getTaskCommandDef()).basicRemove(otherEnd, msgs);
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
			case CoreconfigPackage.TASK_COMMAND_CONFIG__TASK_COMMAND_DEF:
				return getTaskCommandDef();
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
			case CoreconfigPackage.TASK_COMMAND_CONFIG__TASK_COMMAND_DEF:
				getTaskCommandDef().clear();
				getTaskCommandDef().addAll((Collection<? extends TaskCommandDef>)newValue);
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
			case CoreconfigPackage.TASK_COMMAND_CONFIG__TASK_COMMAND_DEF:
				getTaskCommandDef().clear();
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
			case CoreconfigPackage.TASK_COMMAND_CONFIG__TASK_COMMAND_DEF:
				return taskCommandDef != null && !taskCommandDef.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TaskCommandConfigImpl
