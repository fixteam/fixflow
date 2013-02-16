/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.GroupInfo;
import com.founder.fix.bpmn2extensions.coreconfig.UserInfo;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl#getGroupId <em>Group Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl#getGroupName <em>Group Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl#getIsEnabled <em>Is Enabled</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl#getIsDisplayUser <em>Is Display User</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl#getGroupIdField <em>Group Id Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl#getGroupNameField <em>Group Name Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl#getSupGroupIdField <em>Sup Group Id Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl#getSqlText <em>Sql Text</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl#getUserInfo <em>User Info</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.GroupInfoImpl#getGroupDefinitionImpl <em>Group Definition Impl</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupInfoImpl extends EObjectImpl implements GroupInfo {
	/**
	 * The default value of the '{@link #getGroupId() <em>Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroupId() <em>Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected String groupId = GROUP_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroupName() <em>Group Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupName()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroupName() <em>Group Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupName()
	 * @generated
	 * @ordered
	 */
	protected String groupName = GROUP_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsEnabled() <em>Is Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final String IS_ENABLED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsEnabled() <em>Is Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsEnabled()
	 * @generated
	 * @ordered
	 */
	protected String isEnabled = IS_ENABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsDisplayUser() <em>Is Display User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDisplayUser()
	 * @generated
	 * @ordered
	 */
	protected static final String IS_DISPLAY_USER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsDisplayUser() <em>Is Display User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDisplayUser()
	 * @generated
	 * @ordered
	 */
	protected String isDisplayUser = IS_DISPLAY_USER_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroupIdField() <em>Group Id Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupIdField()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_ID_FIELD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroupIdField() <em>Group Id Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupIdField()
	 * @generated
	 * @ordered
	 */
	protected String groupIdField = GROUP_ID_FIELD_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroupNameField() <em>Group Name Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupNameField()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_NAME_FIELD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroupNameField() <em>Group Name Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupNameField()
	 * @generated
	 * @ordered
	 */
	protected String groupNameField = GROUP_NAME_FIELD_EDEFAULT;

	/**
	 * The default value of the '{@link #getSupGroupIdField() <em>Sup Group Id Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupGroupIdField()
	 * @generated
	 * @ordered
	 */
	protected static final String SUP_GROUP_ID_FIELD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSupGroupIdField() <em>Sup Group Id Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupGroupIdField()
	 * @generated
	 * @ordered
	 */
	protected String supGroupIdField = SUP_GROUP_ID_FIELD_EDEFAULT;

	/**
	 * The default value of the '{@link #getSqlText() <em>Sql Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlText()
	 * @generated
	 * @ordered
	 */
	protected static final String SQL_TEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSqlText() <em>Sql Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlText()
	 * @generated
	 * @ordered
	 */
	protected String sqlText = SQL_TEXT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getUserInfo() <em>User Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserInfo()
	 * @generated
	 * @ordered
	 */
	protected UserInfo userInfo;

	/**
	 * The default value of the '{@link #getDataSource() <em>Data Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataSource()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataSource() <em>Data Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataSource()
	 * @generated
	 * @ordered
	 */
	protected String dataSource = DATA_SOURCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroupDefinitionImpl() <em>Group Definition Impl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupDefinitionImpl()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_DEFINITION_IMPL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroupDefinitionImpl() <em>Group Definition Impl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupDefinitionImpl()
	 * @generated
	 * @ordered
	 */
	protected String groupDefinitionImpl = GROUP_DEFINITION_IMPL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GroupInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.GROUP_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupId(String newGroupId) {
		String oldGroupId = groupId;
		groupId = newGroupId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.GROUP_INFO__GROUP_ID, oldGroupId, groupId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupName(String newGroupName) {
		String oldGroupName = groupName;
		groupName = newGroupName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.GROUP_INFO__GROUP_NAME, oldGroupName, groupName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIsEnabled() {
		return isEnabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEnabled(String newIsEnabled) {
		String oldIsEnabled = isEnabled;
		isEnabled = newIsEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.GROUP_INFO__IS_ENABLED, oldIsEnabled, isEnabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIsDisplayUser() {
		return isDisplayUser;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsDisplayUser(String newIsDisplayUser) {
		String oldIsDisplayUser = isDisplayUser;
		isDisplayUser = newIsDisplayUser;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.GROUP_INFO__IS_DISPLAY_USER, oldIsDisplayUser, isDisplayUser));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroupIdField() {
		return groupIdField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupIdField(String newGroupIdField) {
		String oldGroupIdField = groupIdField;
		groupIdField = newGroupIdField;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.GROUP_INFO__GROUP_ID_FIELD, oldGroupIdField, groupIdField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroupNameField() {
		return groupNameField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupNameField(String newGroupNameField) {
		String oldGroupNameField = groupNameField;
		groupNameField = newGroupNameField;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.GROUP_INFO__GROUP_NAME_FIELD, oldGroupNameField, groupNameField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSupGroupIdField() {
		return supGroupIdField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSupGroupIdField(String newSupGroupIdField) {
		String oldSupGroupIdField = supGroupIdField;
		supGroupIdField = newSupGroupIdField;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.GROUP_INFO__SUP_GROUP_ID_FIELD, oldSupGroupIdField, supGroupIdField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSqlText() {
		return sqlText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSqlText(String newSqlText) {
		String oldSqlText = sqlText;
		sqlText = newSqlText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.GROUP_INFO__SQL_TEXT, oldSqlText, sqlText));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUserInfo(UserInfo newUserInfo, NotificationChain msgs) {
		UserInfo oldUserInfo = userInfo;
		userInfo = newUserInfo;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CoreconfigPackage.GROUP_INFO__USER_INFO, oldUserInfo, newUserInfo);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserInfo(UserInfo newUserInfo) {
		if (newUserInfo != userInfo) {
			NotificationChain msgs = null;
			if (userInfo != null)
				msgs = ((InternalEObject)userInfo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.GROUP_INFO__USER_INFO, null, msgs);
			if (newUserInfo != null)
				msgs = ((InternalEObject)newUserInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CoreconfigPackage.GROUP_INFO__USER_INFO, null, msgs);
			msgs = basicSetUserInfo(newUserInfo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.GROUP_INFO__USER_INFO, newUserInfo, newUserInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataSource(String newDataSource) {
		String oldDataSource = dataSource;
		dataSource = newDataSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.GROUP_INFO__DATA_SOURCE, oldDataSource, dataSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroupDefinitionImpl() {
		return groupDefinitionImpl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupDefinitionImpl(String newGroupDefinitionImpl) {
		String oldGroupDefinitionImpl = groupDefinitionImpl;
		groupDefinitionImpl = newGroupDefinitionImpl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.GROUP_INFO__GROUP_DEFINITION_IMPL, oldGroupDefinitionImpl, groupDefinitionImpl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CoreconfigPackage.GROUP_INFO__USER_INFO:
				return basicSetUserInfo(null, msgs);
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
			case CoreconfigPackage.GROUP_INFO__GROUP_ID:
				return getGroupId();
			case CoreconfigPackage.GROUP_INFO__GROUP_NAME:
				return getGroupName();
			case CoreconfigPackage.GROUP_INFO__IS_ENABLED:
				return getIsEnabled();
			case CoreconfigPackage.GROUP_INFO__IS_DISPLAY_USER:
				return getIsDisplayUser();
			case CoreconfigPackage.GROUP_INFO__GROUP_ID_FIELD:
				return getGroupIdField();
			case CoreconfigPackage.GROUP_INFO__GROUP_NAME_FIELD:
				return getGroupNameField();
			case CoreconfigPackage.GROUP_INFO__SUP_GROUP_ID_FIELD:
				return getSupGroupIdField();
			case CoreconfigPackage.GROUP_INFO__SQL_TEXT:
				return getSqlText();
			case CoreconfigPackage.GROUP_INFO__USER_INFO:
				return getUserInfo();
			case CoreconfigPackage.GROUP_INFO__DATA_SOURCE:
				return getDataSource();
			case CoreconfigPackage.GROUP_INFO__GROUP_DEFINITION_IMPL:
				return getGroupDefinitionImpl();
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
			case CoreconfigPackage.GROUP_INFO__GROUP_ID:
				setGroupId((String)newValue);
				return;
			case CoreconfigPackage.GROUP_INFO__GROUP_NAME:
				setGroupName((String)newValue);
				return;
			case CoreconfigPackage.GROUP_INFO__IS_ENABLED:
				setIsEnabled((String)newValue);
				return;
			case CoreconfigPackage.GROUP_INFO__IS_DISPLAY_USER:
				setIsDisplayUser((String)newValue);
				return;
			case CoreconfigPackage.GROUP_INFO__GROUP_ID_FIELD:
				setGroupIdField((String)newValue);
				return;
			case CoreconfigPackage.GROUP_INFO__GROUP_NAME_FIELD:
				setGroupNameField((String)newValue);
				return;
			case CoreconfigPackage.GROUP_INFO__SUP_GROUP_ID_FIELD:
				setSupGroupIdField((String)newValue);
				return;
			case CoreconfigPackage.GROUP_INFO__SQL_TEXT:
				setSqlText((String)newValue);
				return;
			case CoreconfigPackage.GROUP_INFO__USER_INFO:
				setUserInfo((UserInfo)newValue);
				return;
			case CoreconfigPackage.GROUP_INFO__DATA_SOURCE:
				setDataSource((String)newValue);
				return;
			case CoreconfigPackage.GROUP_INFO__GROUP_DEFINITION_IMPL:
				setGroupDefinitionImpl((String)newValue);
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
			case CoreconfigPackage.GROUP_INFO__GROUP_ID:
				setGroupId(GROUP_ID_EDEFAULT);
				return;
			case CoreconfigPackage.GROUP_INFO__GROUP_NAME:
				setGroupName(GROUP_NAME_EDEFAULT);
				return;
			case CoreconfigPackage.GROUP_INFO__IS_ENABLED:
				setIsEnabled(IS_ENABLED_EDEFAULT);
				return;
			case CoreconfigPackage.GROUP_INFO__IS_DISPLAY_USER:
				setIsDisplayUser(IS_DISPLAY_USER_EDEFAULT);
				return;
			case CoreconfigPackage.GROUP_INFO__GROUP_ID_FIELD:
				setGroupIdField(GROUP_ID_FIELD_EDEFAULT);
				return;
			case CoreconfigPackage.GROUP_INFO__GROUP_NAME_FIELD:
				setGroupNameField(GROUP_NAME_FIELD_EDEFAULT);
				return;
			case CoreconfigPackage.GROUP_INFO__SUP_GROUP_ID_FIELD:
				setSupGroupIdField(SUP_GROUP_ID_FIELD_EDEFAULT);
				return;
			case CoreconfigPackage.GROUP_INFO__SQL_TEXT:
				setSqlText(SQL_TEXT_EDEFAULT);
				return;
			case CoreconfigPackage.GROUP_INFO__USER_INFO:
				setUserInfo((UserInfo)null);
				return;
			case CoreconfigPackage.GROUP_INFO__DATA_SOURCE:
				setDataSource(DATA_SOURCE_EDEFAULT);
				return;
			case CoreconfigPackage.GROUP_INFO__GROUP_DEFINITION_IMPL:
				setGroupDefinitionImpl(GROUP_DEFINITION_IMPL_EDEFAULT);
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
			case CoreconfigPackage.GROUP_INFO__GROUP_ID:
				return GROUP_ID_EDEFAULT == null ? groupId != null : !GROUP_ID_EDEFAULT.equals(groupId);
			case CoreconfigPackage.GROUP_INFO__GROUP_NAME:
				return GROUP_NAME_EDEFAULT == null ? groupName != null : !GROUP_NAME_EDEFAULT.equals(groupName);
			case CoreconfigPackage.GROUP_INFO__IS_ENABLED:
				return IS_ENABLED_EDEFAULT == null ? isEnabled != null : !IS_ENABLED_EDEFAULT.equals(isEnabled);
			case CoreconfigPackage.GROUP_INFO__IS_DISPLAY_USER:
				return IS_DISPLAY_USER_EDEFAULT == null ? isDisplayUser != null : !IS_DISPLAY_USER_EDEFAULT.equals(isDisplayUser);
			case CoreconfigPackage.GROUP_INFO__GROUP_ID_FIELD:
				return GROUP_ID_FIELD_EDEFAULT == null ? groupIdField != null : !GROUP_ID_FIELD_EDEFAULT.equals(groupIdField);
			case CoreconfigPackage.GROUP_INFO__GROUP_NAME_FIELD:
				return GROUP_NAME_FIELD_EDEFAULT == null ? groupNameField != null : !GROUP_NAME_FIELD_EDEFAULT.equals(groupNameField);
			case CoreconfigPackage.GROUP_INFO__SUP_GROUP_ID_FIELD:
				return SUP_GROUP_ID_FIELD_EDEFAULT == null ? supGroupIdField != null : !SUP_GROUP_ID_FIELD_EDEFAULT.equals(supGroupIdField);
			case CoreconfigPackage.GROUP_INFO__SQL_TEXT:
				return SQL_TEXT_EDEFAULT == null ? sqlText != null : !SQL_TEXT_EDEFAULT.equals(sqlText);
			case CoreconfigPackage.GROUP_INFO__USER_INFO:
				return userInfo != null;
			case CoreconfigPackage.GROUP_INFO__DATA_SOURCE:
				return DATA_SOURCE_EDEFAULT == null ? dataSource != null : !DATA_SOURCE_EDEFAULT.equals(dataSource);
			case CoreconfigPackage.GROUP_INFO__GROUP_DEFINITION_IMPL:
				return GROUP_DEFINITION_IMPL_EDEFAULT == null ? groupDefinitionImpl != null : !GROUP_DEFINITION_IMPL_EDEFAULT.equals(groupDefinitionImpl);
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
		result.append(" (groupId: ");
		result.append(groupId);
		result.append(", groupName: ");
		result.append(groupName);
		result.append(", isEnabled: ");
		result.append(isEnabled);
		result.append(", isDisplayUser: ");
		result.append(isDisplayUser);
		result.append(", groupIdField: ");
		result.append(groupIdField);
		result.append(", groupNameField: ");
		result.append(groupNameField);
		result.append(", supGroupIdField: ");
		result.append(supGroupIdField);
		result.append(", sqlText: ");
		result.append(sqlText);
		result.append(", dataSource: ");
		result.append(dataSource);
		result.append(", groupDefinitionImpl: ");
		result.append(groupDefinitionImpl);
		result.append(')');
		return result.toString();
	}

} //GroupInfoImpl
