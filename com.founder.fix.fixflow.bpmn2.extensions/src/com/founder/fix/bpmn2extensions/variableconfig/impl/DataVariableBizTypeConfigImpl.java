/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.variableconfig.impl;

import com.founder.fix.bpmn2extensions.variableconfig.DataVariableBizType;
import com.founder.fix.bpmn2extensions.variableconfig.DataVariableBizTypeConfig;
import com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage;

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
 * An implementation of the model object '<em><b>Data Variable Biz Type Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableBizTypeConfigImpl#getDataVariableBizType <em>Data Variable Biz Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataVariableBizTypeConfigImpl extends EObjectImpl implements DataVariableBizTypeConfig {
	/**
	 * The cached value of the '{@link #getDataVariableBizType() <em>Data Variable Biz Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataVariableBizType()
	 * @generated
	 * @ordered
	 */
	protected EList<DataVariableBizType> dataVariableBizType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataVariableBizTypeConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VariableconfigPackage.Literals.DATA_VARIABLE_BIZ_TYPE_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataVariableBizType> getDataVariableBizType() {
		if (dataVariableBizType == null) {
			dataVariableBizType = new EObjectContainmentEList<DataVariableBizType>(DataVariableBizType.class, this, VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE_CONFIG__DATA_VARIABLE_BIZ_TYPE);
		}
		return dataVariableBizType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE_CONFIG__DATA_VARIABLE_BIZ_TYPE:
				return ((InternalEList<?>)getDataVariableBizType()).basicRemove(otherEnd, msgs);
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
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE_CONFIG__DATA_VARIABLE_BIZ_TYPE:
				return getDataVariableBizType();
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
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE_CONFIG__DATA_VARIABLE_BIZ_TYPE:
				getDataVariableBizType().clear();
				getDataVariableBizType().addAll((Collection<? extends DataVariableBizType>)newValue);
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
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE_CONFIG__DATA_VARIABLE_BIZ_TYPE:
				getDataVariableBizType().clear();
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
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE_CONFIG__DATA_VARIABLE_BIZ_TYPE:
				return dataVariableBizType != null && !dataVariableBizType.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DataVariableBizTypeConfigImpl
