/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.DBType;
import com.founder.fix.bpmn2extensions.coreconfig.DataBase;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Base</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl#getDriverClassName <em>Driver Class Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl#getUsername <em>Username</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl#getMaxActive <em>Max Active</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl#getMaxIdle <em>Max Idle</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl#getMaxWait <em>Max Wait</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl#getPaginationImpl <em>Pagination Impl</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.DataBaseImpl#getDbtype <em>Dbtype</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataBaseImpl extends EObjectImpl implements DataBase {
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
	 * The default value of the '{@link #getDriverClassName() <em>Driver Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDriverClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String DRIVER_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDriverClassName() <em>Driver Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDriverClassName()
	 * @generated
	 * @ordered
	 */
	protected String driverClassName = DRIVER_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected String url = URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getUsername() <em>Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsername()
	 * @generated
	 * @ordered
	 */
	protected static final String USERNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUsername() <em>Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsername()
	 * @generated
	 * @ordered
	 */
	protected String username = USERNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected String password = PASSWORD_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxActive() <em>Max Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxActive()
	 * @generated
	 * @ordered
	 */
	protected static final String MAX_ACTIVE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxActive() <em>Max Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxActive()
	 * @generated
	 * @ordered
	 */
	protected String maxActive = MAX_ACTIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxIdle() <em>Max Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxIdle()
	 * @generated
	 * @ordered
	 */
	protected static final String MAX_IDLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxIdle() <em>Max Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxIdle()
	 * @generated
	 * @ordered
	 */
	protected String maxIdle = MAX_IDLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxWait() <em>Max Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxWait()
	 * @generated
	 * @ordered
	 */
	protected static final String MAX_WAIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxWait() <em>Max Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxWait()
	 * @generated
	 * @ordered
	 */
	protected String maxWait = MAX_WAIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getPaginationImpl() <em>Pagination Impl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaginationImpl()
	 * @generated
	 * @ordered
	 */
	protected static final String PAGINATION_IMPL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPaginationImpl() <em>Pagination Impl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaginationImpl()
	 * @generated
	 * @ordered
	 */
	protected String paginationImpl = PAGINATION_IMPL_EDEFAULT;

	/**
	 * The default value of the '{@link #getDbtype() <em>Dbtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDbtype()
	 * @generated
	 * @ordered
	 */
	protected static final DBType DBTYPE_EDEFAULT = DBType.OTHER;

	/**
	 * The cached value of the '{@link #getDbtype() <em>Dbtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDbtype()
	 * @generated
	 * @ordered
	 */
	protected DBType dbtype = DBTYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataBaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.DATA_BASE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DATA_BASE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DATA_BASE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDriverClassName() {
		return driverClassName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDriverClassName(String newDriverClassName) {
		String oldDriverClassName = driverClassName;
		driverClassName = newDriverClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DATA_BASE__DRIVER_CLASS_NAME, oldDriverClassName, driverClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrl(String newUrl) {
		String oldUrl = url;
		url = newUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DATA_BASE__URL, oldUrl, url));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsername(String newUsername) {
		String oldUsername = username;
		username = newUsername;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DATA_BASE__USERNAME, oldUsername, username));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPassword(String newPassword) {
		String oldPassword = password;
		password = newPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DATA_BASE__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMaxActive() {
		return maxActive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxActive(String newMaxActive) {
		String oldMaxActive = maxActive;
		maxActive = newMaxActive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DATA_BASE__MAX_ACTIVE, oldMaxActive, maxActive));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMaxIdle() {
		return maxIdle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxIdle(String newMaxIdle) {
		String oldMaxIdle = maxIdle;
		maxIdle = newMaxIdle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DATA_BASE__MAX_IDLE, oldMaxIdle, maxIdle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMaxWait() {
		return maxWait;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxWait(String newMaxWait) {
		String oldMaxWait = maxWait;
		maxWait = newMaxWait;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DATA_BASE__MAX_WAIT, oldMaxWait, maxWait));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPaginationImpl() {
		return paginationImpl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPaginationImpl(String newPaginationImpl) {
		String oldPaginationImpl = paginationImpl;
		paginationImpl = newPaginationImpl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DATA_BASE__PAGINATION_IMPL, oldPaginationImpl, paginationImpl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DBType getDbtype() {
		return dbtype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDbtype(DBType newDbtype) {
		DBType oldDbtype = dbtype;
		dbtype = newDbtype == null ? DBTYPE_EDEFAULT : newDbtype;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.DATA_BASE__DBTYPE, oldDbtype, dbtype));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CoreconfigPackage.DATA_BASE__ID:
				return getId();
			case CoreconfigPackage.DATA_BASE__NAME:
				return getName();
			case CoreconfigPackage.DATA_BASE__DRIVER_CLASS_NAME:
				return getDriverClassName();
			case CoreconfigPackage.DATA_BASE__URL:
				return getUrl();
			case CoreconfigPackage.DATA_BASE__USERNAME:
				return getUsername();
			case CoreconfigPackage.DATA_BASE__PASSWORD:
				return getPassword();
			case CoreconfigPackage.DATA_BASE__MAX_ACTIVE:
				return getMaxActive();
			case CoreconfigPackage.DATA_BASE__MAX_IDLE:
				return getMaxIdle();
			case CoreconfigPackage.DATA_BASE__MAX_WAIT:
				return getMaxWait();
			case CoreconfigPackage.DATA_BASE__PAGINATION_IMPL:
				return getPaginationImpl();
			case CoreconfigPackage.DATA_BASE__DBTYPE:
				return getDbtype();
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
			case CoreconfigPackage.DATA_BASE__ID:
				setId((String)newValue);
				return;
			case CoreconfigPackage.DATA_BASE__NAME:
				setName((String)newValue);
				return;
			case CoreconfigPackage.DATA_BASE__DRIVER_CLASS_NAME:
				setDriverClassName((String)newValue);
				return;
			case CoreconfigPackage.DATA_BASE__URL:
				setUrl((String)newValue);
				return;
			case CoreconfigPackage.DATA_BASE__USERNAME:
				setUsername((String)newValue);
				return;
			case CoreconfigPackage.DATA_BASE__PASSWORD:
				setPassword((String)newValue);
				return;
			case CoreconfigPackage.DATA_BASE__MAX_ACTIVE:
				setMaxActive((String)newValue);
				return;
			case CoreconfigPackage.DATA_BASE__MAX_IDLE:
				setMaxIdle((String)newValue);
				return;
			case CoreconfigPackage.DATA_BASE__MAX_WAIT:
				setMaxWait((String)newValue);
				return;
			case CoreconfigPackage.DATA_BASE__PAGINATION_IMPL:
				setPaginationImpl((String)newValue);
				return;
			case CoreconfigPackage.DATA_BASE__DBTYPE:
				setDbtype((DBType)newValue);
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
			case CoreconfigPackage.DATA_BASE__ID:
				setId(ID_EDEFAULT);
				return;
			case CoreconfigPackage.DATA_BASE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CoreconfigPackage.DATA_BASE__DRIVER_CLASS_NAME:
				setDriverClassName(DRIVER_CLASS_NAME_EDEFAULT);
				return;
			case CoreconfigPackage.DATA_BASE__URL:
				setUrl(URL_EDEFAULT);
				return;
			case CoreconfigPackage.DATA_BASE__USERNAME:
				setUsername(USERNAME_EDEFAULT);
				return;
			case CoreconfigPackage.DATA_BASE__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
				return;
			case CoreconfigPackage.DATA_BASE__MAX_ACTIVE:
				setMaxActive(MAX_ACTIVE_EDEFAULT);
				return;
			case CoreconfigPackage.DATA_BASE__MAX_IDLE:
				setMaxIdle(MAX_IDLE_EDEFAULT);
				return;
			case CoreconfigPackage.DATA_BASE__MAX_WAIT:
				setMaxWait(MAX_WAIT_EDEFAULT);
				return;
			case CoreconfigPackage.DATA_BASE__PAGINATION_IMPL:
				setPaginationImpl(PAGINATION_IMPL_EDEFAULT);
				return;
			case CoreconfigPackage.DATA_BASE__DBTYPE:
				setDbtype(DBTYPE_EDEFAULT);
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
			case CoreconfigPackage.DATA_BASE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case CoreconfigPackage.DATA_BASE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CoreconfigPackage.DATA_BASE__DRIVER_CLASS_NAME:
				return DRIVER_CLASS_NAME_EDEFAULT == null ? driverClassName != null : !DRIVER_CLASS_NAME_EDEFAULT.equals(driverClassName);
			case CoreconfigPackage.DATA_BASE__URL:
				return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
			case CoreconfigPackage.DATA_BASE__USERNAME:
				return USERNAME_EDEFAULT == null ? username != null : !USERNAME_EDEFAULT.equals(username);
			case CoreconfigPackage.DATA_BASE__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
			case CoreconfigPackage.DATA_BASE__MAX_ACTIVE:
				return MAX_ACTIVE_EDEFAULT == null ? maxActive != null : !MAX_ACTIVE_EDEFAULT.equals(maxActive);
			case CoreconfigPackage.DATA_BASE__MAX_IDLE:
				return MAX_IDLE_EDEFAULT == null ? maxIdle != null : !MAX_IDLE_EDEFAULT.equals(maxIdle);
			case CoreconfigPackage.DATA_BASE__MAX_WAIT:
				return MAX_WAIT_EDEFAULT == null ? maxWait != null : !MAX_WAIT_EDEFAULT.equals(maxWait);
			case CoreconfigPackage.DATA_BASE__PAGINATION_IMPL:
				return PAGINATION_IMPL_EDEFAULT == null ? paginationImpl != null : !PAGINATION_IMPL_EDEFAULT.equals(paginationImpl);
			case CoreconfigPackage.DATA_BASE__DBTYPE:
				return dbtype != DBTYPE_EDEFAULT;
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
		result.append(", driverClassName: ");
		result.append(driverClassName);
		result.append(", url: ");
		result.append(url);
		result.append(", username: ");
		result.append(username);
		result.append(", password: ");
		result.append(password);
		result.append(", maxActive: ");
		result.append(maxActive);
		result.append(", maxIdle: ");
		result.append(maxIdle);
		result.append(", maxWait: ");
		result.append(maxWait);
		result.append(", paginationImpl: ");
		result.append(paginationImpl);
		result.append(", dbtype: ");
		result.append(dbtype);
		result.append(')');
		return result.toString();
	}

} //DataBaseImpl
