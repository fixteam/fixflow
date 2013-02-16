/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition;
import com.founder.fix.bpmn2extensions.coreconfig.GroupDefinitionConfig;

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
 * An implementation of the model object '<em><b>Group Definition Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupDefinitionConfigImpl#getGroupDefinition <em>Group Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupDefinitionConfigImpl extends EObjectImpl implements GroupDefinitionConfig {
	/**
	 * The cached value of the '{@link #getGroupDefinition() <em>Group Definition</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupDefinition()
	 * @generated
	 * @ordered
	 */
	protected EList<GroupDefinition> groupDefinition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GroupDefinitionConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.GROUP_DEFINITION_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GroupDefinition> getGroupDefinition() {
		if (groupDefinition == null) {
			groupDefinition = new EObjectContainmentEList<GroupDefinition>(GroupDefinition.class, this, CoreconfigPackage.GROUP_DEFINITION_CONFIG__GROUP_DEFINITION);
		}
		return groupDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CoreconfigPackage.GROUP_DEFINITION_CONFIG__GROUP_DEFINITION:
				return ((InternalEList<?>)getGroupDefinition()).basicRemove(otherEnd, msgs);
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
			case CoreconfigPackage.GROUP_DEFINITION_CONFIG__GROUP_DEFINITION:
				return getGroupDefinition();
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
			case CoreconfigPackage.GROUP_DEFINITION_CONFIG__GROUP_DEFINITION:
				getGroupDefinition().clear();
				getGroupDefinition().addAll((Collection<? extends GroupDefinition>)newValue);
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
			case CoreconfigPackage.GROUP_DEFINITION_CONFIG__GROUP_DEFINITION:
				getGroupDefinition().clear();
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
			case CoreconfigPackage.GROUP_DEFINITION_CONFIG__GROUP_DEFINITION:
				return groupDefinition != null && !groupDefinition.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GroupDefinitionConfigImpl
