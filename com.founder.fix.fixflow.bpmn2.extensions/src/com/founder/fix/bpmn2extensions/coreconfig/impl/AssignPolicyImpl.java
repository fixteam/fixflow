/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy;
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assign Policy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AssignPolicyImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AssignPolicyImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AssignPolicyImpl#getClassImpl <em>Class Impl</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AssignPolicyImpl#getRemarks <em>Remarks</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssignPolicyImpl extends EObjectImpl implements AssignPolicy {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassImpl() <em>Class Impl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassImpl()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_IMPL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassImpl() <em>Class Impl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassImpl()
	 * @generated
	 * @ordered
	 */
	protected String classImpl = CLASS_IMPL_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemarks() <em>Remarks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemarks()
	 * @generated
	 * @ordered
	 */
	protected static final String REMARKS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemarks() <em>Remarks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemarks()
	 * @generated
	 * @ordered
	 */
	protected String remarks = REMARKS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssignPolicyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.ASSIGN_POLICY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.ASSIGN_POLICY__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.ASSIGN_POLICY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassImpl() {
		return classImpl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassImpl(String newClassImpl) {
		String oldClassImpl = classImpl;
		classImpl = newClassImpl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.ASSIGN_POLICY__CLASS_IMPL, oldClassImpl, classImpl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemarks(String newRemarks) {
		String oldRemarks = remarks;
		remarks = newRemarks;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.ASSIGN_POLICY__REMARKS, oldRemarks, remarks));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CoreconfigPackage.ASSIGN_POLICY__ID:
				return getId();
			case CoreconfigPackage.ASSIGN_POLICY__NAME:
				return getName();
			case CoreconfigPackage.ASSIGN_POLICY__CLASS_IMPL:
				return getClassImpl();
			case CoreconfigPackage.ASSIGN_POLICY__REMARKS:
				return getRemarks();
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
			case CoreconfigPackage.ASSIGN_POLICY__ID:
				setId((String)newValue);
				return;
			case CoreconfigPackage.ASSIGN_POLICY__NAME:
				setName((String)newValue);
				return;
			case CoreconfigPackage.ASSIGN_POLICY__CLASS_IMPL:
				setClassImpl((String)newValue);
				return;
			case CoreconfigPackage.ASSIGN_POLICY__REMARKS:
				setRemarks((String)newValue);
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
			case CoreconfigPackage.ASSIGN_POLICY__ID:
				setId(ID_EDEFAULT);
				return;
			case CoreconfigPackage.ASSIGN_POLICY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CoreconfigPackage.ASSIGN_POLICY__CLASS_IMPL:
				setClassImpl(CLASS_IMPL_EDEFAULT);
				return;
			case CoreconfigPackage.ASSIGN_POLICY__REMARKS:
				setRemarks(REMARKS_EDEFAULT);
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
			case CoreconfigPackage.ASSIGN_POLICY__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case CoreconfigPackage.ASSIGN_POLICY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CoreconfigPackage.ASSIGN_POLICY__CLASS_IMPL:
				return CLASS_IMPL_EDEFAULT == null ? classImpl != null : !CLASS_IMPL_EDEFAULT.equals(classImpl);
			case CoreconfigPackage.ASSIGN_POLICY__REMARKS:
				return REMARKS_EDEFAULT == null ? remarks != null : !REMARKS_EDEFAULT.equals(remarks);
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
		result.append(" (id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", classImpl: ");
		result.append(classImpl);
		result.append(", remarks: ");
		result.append(remarks);
		result.append(')');
		return result.toString();
	}

} //AssignPolicyImpl
