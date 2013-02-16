/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandClass;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expand Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandClassImpl#getClassId <em>Class Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandClassImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandClassImpl#getClassInterface <em>Class Interface</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandClassImpl#getClassImpl <em>Class Impl</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ExpandClassImpl#getRemarks <em>Remarks</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExpandClassImpl extends EObjectImpl implements ExpandClass {
	/**
	 * The default value of the '{@link #getClassId() <em>Class Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassId()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassId() <em>Class Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassId()
	 * @generated
	 * @ordered
	 */
	protected String classId = CLASS_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected String className = CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassInterface() <em>Class Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassInterface()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassInterface() <em>Class Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassInterface()
	 * @generated
	 * @ordered
	 */
	protected String classInterface = CLASS_INTERFACE_EDEFAULT;

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
	protected ExpandClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.EXPAND_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassId() {
		return classId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassId(String newClassId) {
		String oldClassId = classId;
		classId = newClassId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.EXPAND_CLASS__CLASS_ID, oldClassId, classId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassName(String newClassName) {
		String oldClassName = className;
		className = newClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.EXPAND_CLASS__CLASS_NAME, oldClassName, className));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassInterface() {
		return classInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassInterface(String newClassInterface) {
		String oldClassInterface = classInterface;
		classInterface = newClassInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.EXPAND_CLASS__CLASS_INTERFACE, oldClassInterface, classInterface));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.EXPAND_CLASS__CLASS_IMPL, oldClassImpl, classImpl));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.EXPAND_CLASS__REMARKS, oldRemarks, remarks));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CoreconfigPackage.EXPAND_CLASS__CLASS_ID:
				return getClassId();
			case CoreconfigPackage.EXPAND_CLASS__CLASS_NAME:
				return getClassName();
			case CoreconfigPackage.EXPAND_CLASS__CLASS_INTERFACE:
				return getClassInterface();
			case CoreconfigPackage.EXPAND_CLASS__CLASS_IMPL:
				return getClassImpl();
			case CoreconfigPackage.EXPAND_CLASS__REMARKS:
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
			case CoreconfigPackage.EXPAND_CLASS__CLASS_ID:
				setClassId((String)newValue);
				return;
			case CoreconfigPackage.EXPAND_CLASS__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case CoreconfigPackage.EXPAND_CLASS__CLASS_INTERFACE:
				setClassInterface((String)newValue);
				return;
			case CoreconfigPackage.EXPAND_CLASS__CLASS_IMPL:
				setClassImpl((String)newValue);
				return;
			case CoreconfigPackage.EXPAND_CLASS__REMARKS:
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
			case CoreconfigPackage.EXPAND_CLASS__CLASS_ID:
				setClassId(CLASS_ID_EDEFAULT);
				return;
			case CoreconfigPackage.EXPAND_CLASS__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case CoreconfigPackage.EXPAND_CLASS__CLASS_INTERFACE:
				setClassInterface(CLASS_INTERFACE_EDEFAULT);
				return;
			case CoreconfigPackage.EXPAND_CLASS__CLASS_IMPL:
				setClassImpl(CLASS_IMPL_EDEFAULT);
				return;
			case CoreconfigPackage.EXPAND_CLASS__REMARKS:
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
			case CoreconfigPackage.EXPAND_CLASS__CLASS_ID:
				return CLASS_ID_EDEFAULT == null ? classId != null : !CLASS_ID_EDEFAULT.equals(classId);
			case CoreconfigPackage.EXPAND_CLASS__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case CoreconfigPackage.EXPAND_CLASS__CLASS_INTERFACE:
				return CLASS_INTERFACE_EDEFAULT == null ? classInterface != null : !CLASS_INTERFACE_EDEFAULT.equals(classInterface);
			case CoreconfigPackage.EXPAND_CLASS__CLASS_IMPL:
				return CLASS_IMPL_EDEFAULT == null ? classImpl != null : !CLASS_IMPL_EDEFAULT.equals(classImpl);
			case CoreconfigPackage.EXPAND_CLASS__REMARKS:
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
		result.append(" (classId: ");
		result.append(classId);
		result.append(", className: ");
		result.append(className);
		result.append(", classInterface: ");
		result.append(classInterface);
		result.append(", classImpl: ");
		result.append(classImpl);
		result.append(", remarks: ");
		result.append(remarks);
		result.append(')');
		return result.toString();
	}

} //ExpandClassImpl
