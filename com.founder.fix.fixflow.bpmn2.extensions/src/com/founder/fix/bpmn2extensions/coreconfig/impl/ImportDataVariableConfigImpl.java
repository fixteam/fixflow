/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.ImportDataVariable;
import com.founder.fix.bpmn2extensions.coreconfig.ImportDataVariableConfig;

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
 * An implementation of the model object '<em><b>Import Data Variable Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ImportDataVariableConfigImpl#getImportDataVariable <em>Import Data Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImportDataVariableConfigImpl extends EObjectImpl implements ImportDataVariableConfig {
	/**
	 * The cached value of the '{@link #getImportDataVariable() <em>Import Data Variable</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportDataVariable()
	 * @generated
	 * @ordered
	 */
	protected EList<ImportDataVariable> importDataVariable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImportDataVariableConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.IMPORT_DATA_VARIABLE_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ImportDataVariable> getImportDataVariable() {
		if (importDataVariable == null) {
			importDataVariable = new EObjectContainmentEList<ImportDataVariable>(ImportDataVariable.class, this, CoreconfigPackage.IMPORT_DATA_VARIABLE_CONFIG__IMPORT_DATA_VARIABLE);
		}
		return importDataVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CoreconfigPackage.IMPORT_DATA_VARIABLE_CONFIG__IMPORT_DATA_VARIABLE:
				return ((InternalEList<?>)getImportDataVariable()).basicRemove(otherEnd, msgs);
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
			case CoreconfigPackage.IMPORT_DATA_VARIABLE_CONFIG__IMPORT_DATA_VARIABLE:
				return getImportDataVariable();
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
			case CoreconfigPackage.IMPORT_DATA_VARIABLE_CONFIG__IMPORT_DATA_VARIABLE:
				getImportDataVariable().clear();
				getImportDataVariable().addAll((Collection<? extends ImportDataVariable>)newValue);
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
			case CoreconfigPackage.IMPORT_DATA_VARIABLE_CONFIG__IMPORT_DATA_VARIABLE:
				getImportDataVariable().clear();
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
			case CoreconfigPackage.IMPORT_DATA_VARIABLE_CONFIG__IMPORT_DATA_VARIABLE:
				return importDataVariable != null && !importDataVariable.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ImportDataVariableConfigImpl
