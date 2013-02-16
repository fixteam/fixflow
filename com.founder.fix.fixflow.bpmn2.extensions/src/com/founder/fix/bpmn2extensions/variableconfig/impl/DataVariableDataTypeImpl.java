/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.variableconfig.impl;

import com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef;
import com.founder.fix.bpmn2extensions.variableconfig.DataVariableDataType;
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
 * An implementation of the model object '<em><b>Data Variable Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableDataTypeImpl#getDataTypeDef <em>Data Type Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataVariableDataTypeImpl extends EObjectImpl implements DataVariableDataType {
	/**
	 * The cached value of the '{@link #getDataTypeDef() <em>Data Type Def</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataTypeDef()
	 * @generated
	 * @ordered
	 */
	protected EList<DataTypeDef> dataTypeDef;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataVariableDataTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VariableconfigPackage.Literals.DATA_VARIABLE_DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataTypeDef> getDataTypeDef() {
		if (dataTypeDef == null) {
			dataTypeDef = new EObjectContainmentEList<DataTypeDef>(DataTypeDef.class, this, VariableconfigPackage.DATA_VARIABLE_DATA_TYPE__DATA_TYPE_DEF);
		}
		return dataTypeDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VariableconfigPackage.DATA_VARIABLE_DATA_TYPE__DATA_TYPE_DEF:
				return ((InternalEList<?>)getDataTypeDef()).basicRemove(otherEnd, msgs);
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
			case VariableconfigPackage.DATA_VARIABLE_DATA_TYPE__DATA_TYPE_DEF:
				return getDataTypeDef();
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
			case VariableconfigPackage.DATA_VARIABLE_DATA_TYPE__DATA_TYPE_DEF:
				getDataTypeDef().clear();
				getDataTypeDef().addAll((Collection<? extends DataTypeDef>)newValue);
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
			case VariableconfigPackage.DATA_VARIABLE_DATA_TYPE__DATA_TYPE_DEF:
				getDataTypeDef().clear();
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
			case VariableconfigPackage.DATA_VARIABLE_DATA_TYPE__DATA_TYPE_DEF:
				return dataTypeDef != null && !dataTypeDef.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DataVariableDataTypeImpl
