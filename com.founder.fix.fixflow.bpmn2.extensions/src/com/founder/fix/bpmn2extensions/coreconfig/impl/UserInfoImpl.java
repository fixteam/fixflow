/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.UserInfo;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.UserInfoImpl#getUserIdField <em>User Id Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.UserInfoImpl#getUserNameField <em>User Name Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.UserInfoImpl#getGroupIdField <em>Group Id Field</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.UserInfoImpl#getSqlText <em>Sql Text</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.UserInfoImpl#getDataSource <em>Data Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserInfoImpl extends EObjectImpl implements UserInfo {
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
	protected UserInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.USER_INFO;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.USER_INFO__USER_ID_FIELD, oldUserIdField, userIdField));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.USER_INFO__USER_NAME_FIELD, oldUserNameField, userNameField));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.USER_INFO__GROUP_ID_FIELD, oldGroupIdField, groupIdField));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.USER_INFO__SQL_TEXT, oldSqlText, sqlText));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.USER_INFO__DATA_SOURCE, oldDataSource, dataSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CoreconfigPackage.USER_INFO__USER_ID_FIELD:
				return getUserIdField();
			case CoreconfigPackage.USER_INFO__USER_NAME_FIELD:
				return getUserNameField();
			case CoreconfigPackage.USER_INFO__GROUP_ID_FIELD:
				return getGroupIdField();
			case CoreconfigPackage.USER_INFO__SQL_TEXT:
				return getSqlText();
			case CoreconfigPackage.USER_INFO__DATA_SOURCE:
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
			case CoreconfigPackage.USER_INFO__USER_ID_FIELD:
				setUserIdField((String)newValue);
				return;
			case CoreconfigPackage.USER_INFO__USER_NAME_FIELD:
				setUserNameField((String)newValue);
				return;
			case CoreconfigPackage.USER_INFO__GROUP_ID_FIELD:
				setGroupIdField((String)newValue);
				return;
			case CoreconfigPackage.USER_INFO__SQL_TEXT:
				setSqlText((String)newValue);
				return;
			case CoreconfigPackage.USER_INFO__DATA_SOURCE:
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
			case CoreconfigPackage.USER_INFO__USER_ID_FIELD:
				setUserIdField(USER_ID_FIELD_EDEFAULT);
				return;
			case CoreconfigPackage.USER_INFO__USER_NAME_FIELD:
				setUserNameField(USER_NAME_FIELD_EDEFAULT);
				return;
			case CoreconfigPackage.USER_INFO__GROUP_ID_FIELD:
				setGroupIdField(GROUP_ID_FIELD_EDEFAULT);
				return;
			case CoreconfigPackage.USER_INFO__SQL_TEXT:
				setSqlText(SQL_TEXT_EDEFAULT);
				return;
			case CoreconfigPackage.USER_INFO__DATA_SOURCE:
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
			case CoreconfigPackage.USER_INFO__USER_ID_FIELD:
				return USER_ID_FIELD_EDEFAULT == null ? userIdField != null : !USER_ID_FIELD_EDEFAULT.equals(userIdField);
			case CoreconfigPackage.USER_INFO__USER_NAME_FIELD:
				return USER_NAME_FIELD_EDEFAULT == null ? userNameField != null : !USER_NAME_FIELD_EDEFAULT.equals(userNameField);
			case CoreconfigPackage.USER_INFO__GROUP_ID_FIELD:
				return GROUP_ID_FIELD_EDEFAULT == null ? groupIdField != null : !GROUP_ID_FIELD_EDEFAULT.equals(groupIdField);
			case CoreconfigPackage.USER_INFO__SQL_TEXT:
				return SQL_TEXT_EDEFAULT == null ? sqlText != null : !SQL_TEXT_EDEFAULT.equals(sqlText);
			case CoreconfigPackage.USER_INFO__DATA_SOURCE:
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
		result.append(" (userIdField: ");
		result.append(userIdField);
		result.append(", userNameField: ");
		result.append(userNameField);
		result.append(", groupIdField: ");
		result.append(groupIdField);
		result.append(", sqlText: ");
		result.append(sqlText);
		result.append(", dataSource: ");
		result.append(dataSource);
		result.append(')');
		return result.toString();
	}

} //UserInfoImpl
