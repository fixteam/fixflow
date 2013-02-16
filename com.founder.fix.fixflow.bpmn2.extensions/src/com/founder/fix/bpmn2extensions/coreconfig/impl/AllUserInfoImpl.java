/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo;
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>All User Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AllUserInfoImpl#getIsEnabled <em>Is Enabled</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AllUserInfoImpl#getUserIdField <em>User Id Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AllUserInfoImpl#getUserNameField <em>User Name Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AllUserInfoImpl#getSqlText <em>Sql Text</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AllUserInfoImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AllUserInfoImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.AllUserInfoImpl#getDataSource <em>Data Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AllUserInfoImpl extends EObjectImpl implements AllUserInfo {
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
	 * The default value of the '{@link #getUserIdField() <em>User Id Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserIdField()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_ID_FIELD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUserIdField() <em>User Id Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserIdField()
	 * @generated
	 * @ordered
	 */
	protected String userIdField = USER_ID_FIELD_EDEFAULT;

	/**
	 * The default value of the '{@link #getUserNameField() <em>User Name Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserNameField()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_NAME_FIELD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUserNameField() <em>User Name Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserNameField()
	 * @generated
	 * @ordered
	 */
	protected String userNameField = USER_NAME_FIELD_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AllUserInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.ALL_USER_INFO;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.ALL_USER_INFO__IS_ENABLED, oldIsEnabled, isEnabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUserIdField() {
		return userIdField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserIdField(String newUserIdField) {
		String oldUserIdField = userIdField;
		userIdField = newUserIdField;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.ALL_USER_INFO__USER_ID_FIELD, oldUserIdField, userIdField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUserNameField() {
		return userNameField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserNameField(String newUserNameField) {
		String oldUserNameField = userNameField;
		userNameField = newUserNameField;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.ALL_USER_INFO__USER_NAME_FIELD, oldUserNameField, userNameField));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.ALL_USER_INFO__SQL_TEXT, oldSqlText, sqlText));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.ALL_USER_INFO__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.ALL_USER_INFO__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.ALL_USER_INFO__DATA_SOURCE, oldDataSource, dataSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CoreconfigPackage.ALL_USER_INFO__IS_ENABLED:
				return getIsEnabled();
			case CoreconfigPackage.ALL_USER_INFO__USER_ID_FIELD:
				return getUserIdField();
			case CoreconfigPackage.ALL_USER_INFO__USER_NAME_FIELD:
				return getUserNameField();
			case CoreconfigPackage.ALL_USER_INFO__SQL_TEXT:
				return getSqlText();
			case CoreconfigPackage.ALL_USER_INFO__ID:
				return getId();
			case CoreconfigPackage.ALL_USER_INFO__NAME:
				return getName();
			case CoreconfigPackage.ALL_USER_INFO__DATA_SOURCE:
				return getDataSource();
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
			case CoreconfigPackage.ALL_USER_INFO__IS_ENABLED:
				setIsEnabled((String)newValue);
				return;
			case CoreconfigPackage.ALL_USER_INFO__USER_ID_FIELD:
				setUserIdField((String)newValue);
				return;
			case CoreconfigPackage.ALL_USER_INFO__USER_NAME_FIELD:
				setUserNameField((String)newValue);
				return;
			case CoreconfigPackage.ALL_USER_INFO__SQL_TEXT:
				setSqlText((String)newValue);
				return;
			case CoreconfigPackage.ALL_USER_INFO__ID:
				setId((String)newValue);
				return;
			case CoreconfigPackage.ALL_USER_INFO__NAME:
				setName((String)newValue);
				return;
			case CoreconfigPackage.ALL_USER_INFO__DATA_SOURCE:
				setDataSource((String)newValue);
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
			case CoreconfigPackage.ALL_USER_INFO__IS_ENABLED:
				setIsEnabled(IS_ENABLED_EDEFAULT);
				return;
			case CoreconfigPackage.ALL_USER_INFO__USER_ID_FIELD:
				setUserIdField(USER_ID_FIELD_EDEFAULT);
				return;
			case CoreconfigPackage.ALL_USER_INFO__USER_NAME_FIELD:
				setUserNameField(USER_NAME_FIELD_EDEFAULT);
				return;
			case CoreconfigPackage.ALL_USER_INFO__SQL_TEXT:
				setSqlText(SQL_TEXT_EDEFAULT);
				return;
			case CoreconfigPackage.ALL_USER_INFO__ID:
				setId(ID_EDEFAULT);
				return;
			case CoreconfigPackage.ALL_USER_INFO__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CoreconfigPackage.ALL_USER_INFO__DATA_SOURCE:
				setDataSource(DATA_SOURCE_EDEFAULT);
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
			case CoreconfigPackage.ALL_USER_INFO__IS_ENABLED:
				return IS_ENABLED_EDEFAULT == null ? isEnabled != null : !IS_ENABLED_EDEFAULT.equals(isEnabled);
			case CoreconfigPackage.ALL_USER_INFO__USER_ID_FIELD:
				return USER_ID_FIELD_EDEFAULT == null ? userIdField != null : !USER_ID_FIELD_EDEFAULT.equals(userIdField);
			case CoreconfigPackage.ALL_USER_INFO__USER_NAME_FIELD:
				return USER_NAME_FIELD_EDEFAULT == null ? userNameField != null : !USER_NAME_FIELD_EDEFAULT.equals(userNameField);
			case CoreconfigPackage.ALL_USER_INFO__SQL_TEXT:
				return SQL_TEXT_EDEFAULT == null ? sqlText != null : !SQL_TEXT_EDEFAULT.equals(sqlText);
			case CoreconfigPackage.ALL_USER_INFO__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case CoreconfigPackage.ALL_USER_INFO__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CoreconfigPackage.ALL_USER_INFO__DATA_SOURCE:
				return DATA_SOURCE_EDEFAULT == null ? dataSource != null : !DATA_SOURCE_EDEFAULT.equals(dataSource);
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
		result.append(" (isEnabled: ");
		result.append(isEnabled);
		result.append(", userIdField: ");
		result.append(userIdField);
		result.append(", userNameField: ");
		result.append(userNameField);
		result.append(", sqlText: ");
		result.append(sqlText);
		result.append(", id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", dataSource: ");
		result.append(dataSource);
		result.append(')');
		return result.toString();
	}

} //AllUserInfoImpl
