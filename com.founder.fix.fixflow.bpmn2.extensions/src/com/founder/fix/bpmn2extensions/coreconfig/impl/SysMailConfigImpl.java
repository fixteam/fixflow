/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.MailInfo;
import com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sys Mail Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.SysMailConfigImpl#getSelected <em>Selected</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.SysMailConfigImpl#getMailInfo <em>Mail Info</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SysMailConfigImpl extends EObjectImpl implements SysMailConfig {
	/**
	 * The default value of the '{@link #getSelected() <em>Selected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelected()
	 * @generated
	 * @ordered
	 */
	protected static final String SELECTED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSelected() <em>Selected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelected()
	 * @generated
	 * @ordered
	 */
	protected String selected = SELECTED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMailInfo() <em>Mail Info</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMailInfo()
	 * @generated
	 * @ordered
	 */
	protected EList<MailInfo> mailInfo;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SysMailConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.SYS_MAIL_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSelected() {
		return selected;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelected(String newSelected) {
		String oldSelected = selected;
		selected = newSelected;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.SYS_MAIL_CONFIG__SELECTED, oldSelected, selected));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MailInfo> getMailInfo() {
		if (mailInfo == null) {
			mailInfo = new EObjectContainmentEList<MailInfo>(MailInfo.class, this, CoreconfigPackage.SYS_MAIL_CONFIG__MAIL_INFO);
		}
		return mailInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CoreconfigPackage.SYS_MAIL_CONFIG__MAIL_INFO:
				return ((InternalEList<?>)getMailInfo()).basicRemove(otherEnd, msgs);
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
			case CoreconfigPackage.SYS_MAIL_CONFIG__SELECTED:
				return getSelected();
			case CoreconfigPackage.SYS_MAIL_CONFIG__MAIL_INFO:
				return getMailInfo();
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
			case CoreconfigPackage.SYS_MAIL_CONFIG__SELECTED:
				setSelected((String)newValue);
				return;
			case CoreconfigPackage.SYS_MAIL_CONFIG__MAIL_INFO:
				getMailInfo().clear();
				getMailInfo().addAll((Collection<? extends MailInfo>)newValue);
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
			case CoreconfigPackage.SYS_MAIL_CONFIG__SELECTED:
				setSelected(SELECTED_EDEFAULT);
				return;
			case CoreconfigPackage.SYS_MAIL_CONFIG__MAIL_INFO:
				getMailInfo().clear();
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
			case CoreconfigPackage.SYS_MAIL_CONFIG__SELECTED:
				return SELECTED_EDEFAULT == null ? selected != null : !SELECTED_EDEFAULT.equals(selected);
			case CoreconfigPackage.SYS_MAIL_CONFIG__MAIL_INFO:
				return mailInfo != null && !mailInfo.isEmpty();
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
		result.append(" (selected: ");
		result.append(selected);
		result.append(')');
		return result.toString();
	}

} //SysMailConfigImpl
