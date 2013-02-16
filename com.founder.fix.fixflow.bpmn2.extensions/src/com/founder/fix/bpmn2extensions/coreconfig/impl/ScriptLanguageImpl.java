/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Script Language</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ScriptLanguageImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ScriptLanguageImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.ScriptLanguageImpl#getClassImpl <em>Class Impl</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScriptLanguageImpl extends EObjectImpl implements ScriptLanguage {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScriptLanguageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.SCRIPT_LANGUAGE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.SCRIPT_LANGUAGE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.SCRIPT_LANGUAGE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.SCRIPT_LANGUAGE__CLASS_IMPL, oldClassImpl, classImpl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CoreconfigPackage.SCRIPT_LANGUAGE__ID:
				return getId();
			case CoreconfigPackage.SCRIPT_LANGUAGE__NAME:
				return getName();
			case CoreconfigPackage.SCRIPT_LANGUAGE__CLASS_IMPL:
				return getClassImpl();
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
			case CoreconfigPackage.SCRIPT_LANGUAGE__ID:
				setId((String)newValue);
				return;
			case CoreconfigPackage.SCRIPT_LANGUAGE__NAME:
				setName((String)newValue);
				return;
			case CoreconfigPackage.SCRIPT_LANGUAGE__CLASS_IMPL:
				setClassImpl((String)newValue);
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
			case CoreconfigPackage.SCRIPT_LANGUAGE__ID:
				setId(ID_EDEFAULT);
				return;
			case CoreconfigPackage.SCRIPT_LANGUAGE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CoreconfigPackage.SCRIPT_LANGUAGE__CLASS_IMPL:
				setClassImpl(CLASS_IMPL_EDEFAULT);
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
			case CoreconfigPackage.SCRIPT_LANGUAGE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case CoreconfigPackage.SCRIPT_LANGUAGE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CoreconfigPackage.SCRIPT_LANGUAGE__CLASS_IMPL:
				return CLASS_IMPL_EDEFAULT == null ? classImpl != null : !CLASS_IMPL_EDEFAULT.equals(classImpl);
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
		result.append(')');
		return result.toString();
	}

} //ScriptLanguageImpl
