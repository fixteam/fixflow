/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo;
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.DesignerOrgConfig;
import com.founder.fix.bpmn2extensions.coreconfig.GroupInfo;

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
 * An implementation of the model object '<em><b>Designer Org Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DesignerOrgConfigImpl#getAllUserInfo <em>All User Info</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DesignerOrgConfigImpl#getGroupInfo <em>Group Info</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DesignerOrgConfigImpl#getDataBaseId <em>Data Base Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DesignerOrgConfigImpl extends EObjectImpl implements DesignerOrgConfig {
	/**
	 * The cached value of the '{@link #getAllUserInfo() <em>All User Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllUserInfo()
	 * @generated
	 * @ordered
	 */
	protected AllUserInfo allUserInfo;

	/**
	 * The cached value of the '{@link #getGroupInfo() <em>Group Info</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupInfo()
	 * @generated
	 * @ordered
	 */
	protected EList<GroupInfo> groupInfo;

	/**
	 * The default value of the '{@link #getDataBaseId() <em>Data Base Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataBaseId()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_BASE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataBaseId() <em>Data Base Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataBaseId()
	 * @generated
	 * @ordered
	 */
	protected String dataBaseId = DATA_BASE_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DesignerOrgConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.DESIGNER_ORG_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllUserInfo getAllUserInfo() {
		return allUserInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAllUserInfo(AllUserInfo newAllUserInfo, NotificationChain msgs) {
		AllUserInfo oldAllUserInfo = allUserInfo;
		allUserInfo = newAllUserInfo;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DESIGNER_ORG_CONFIG__ALL_USER_INFO, oldAllUserInfo, newAllUserInfo);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllUserInfo(AllUserInfo newAllUserInfo) {
		if (newAllUserInfo != allUserInfo) {
			NotificationChain msgs = null;
			if (allUserInfo != null)
				msgs = ((InternalEObject)allUserInfo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.DESIGNER_ORG_CONFIG__ALL_USER_INFO, null, msgs);
			if (newAllUserInfo != null)
				msgs = ((InternalEObject)newAllUserInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.DESIGNER_ORG_CONFIG__ALL_USER_INFO, null, msgs);
			msgs = basicSetAllUserInfo(newAllUserInfo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DESIGNER_ORG_CONFIG__ALL_USER_INFO, newAllUserInfo, newAllUserInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GroupInfo> getGroupInfo() {
		if (groupInfo == null) {
			groupInfo = new EObjectContainmentEList<GroupInfo>(GroupInfo.class, this, CoreconfigPackage.DESIGNER_ORG_CONFIG__GROUP_INFO);
		}
		return groupInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataBaseId() {
		return dataBaseId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataBaseId(String newDataBaseId) {
		String oldDataBaseId = dataBaseId;
		dataBaseId = newDataBaseId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DESIGNER_ORG_CONFIG__DATA_BASE_ID, oldDataBaseId, dataBaseId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__ALL_USER_INFO:
				return basicSetAllUserInfo(null, msgs);
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__GROUP_INFO:
				return ((InternalEList<?>)getGroupInfo()).basicRemove(otherEnd, msgs);
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
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__ALL_USER_INFO:
				return getAllUserInfo();
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__GROUP_INFO:
				return getGroupInfo();
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__DATA_BASE_ID:
				return getDataBaseId();
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
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__ALL_USER_INFO:
				setAllUserInfo((AllUserInfo)newValue);
				return;
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__GROUP_INFO:
				getGroupInfo().clear();
				getGroupInfo().addAll((Collection<? extends GroupInfo>)newValue);
				return;
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__DATA_BASE_ID:
				setDataBaseId((String)newValue);
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
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__ALL_USER_INFO:
				setAllUserInfo((AllUserInfo)null);
				return;
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__GROUP_INFO:
				getGroupInfo().clear();
				return;
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__DATA_BASE_ID:
				setDataBaseId(DATA_BASE_ID_EDEFAULT);
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
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__ALL_USER_INFO:
				return allUserInfo != null;
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__GROUP_INFO:
				return groupInfo != null && !groupInfo.isEmpty();
			case CoreconfigPackage.DESIGNER_ORG_CONFIG__DATA_BASE_ID:
				return DATA_BASE_ID_EDEFAULT == null ? dataBaseId != null : !DATA_BASE_ID_EDEFAULT.equals(dataBaseId);
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
		result.append(" (dataBaseId: ");
		result.append(dataBaseId);
		result.append(')');
		return result.toString();
	}

} //DesignerOrgConfigImpl
