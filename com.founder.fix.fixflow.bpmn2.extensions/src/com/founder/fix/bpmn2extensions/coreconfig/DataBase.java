/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Base</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getId <em>Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getName <em>Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getDriverClassName <em>Driver Class Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getUrl <em>Url</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getUsername <em>Username</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getPassword <em>Password</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getMaxActive <em>Max Active</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getMaxIdle <em>Max Idle</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getMaxWait <em>Max Wait</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getPaginationImpl <em>Pagination Impl</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getDbtype <em>Dbtype</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBase()
 * @model extendedMetaData="name='dataBase'"
 * @generated
 */
public interface DataBase extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBase_Id()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBase_Name()
	 * @model required="true"
	 *        extendedMetaData="name='name' kind='attribute'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Driver Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Driver Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Driver Class Name</em>' attribute.
	 * @see #setDriverClassName(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBase_DriverClassName()
	 * @model required="true"
	 *        extendedMetaData="name='driverClassName' kind='attribute'"
	 * @generated
	 */
	String getDriverClassName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getDriverClassName <em>Driver Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Driver Class Name</em>' attribute.
	 * @see #getDriverClassName()
	 * @generated
	 */
	void setDriverClassName(String value);

	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBase_Url()
	 * @model required="true"
	 *        extendedMetaData="name='url' kind='attribute'"
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Username</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Username</em>' attribute.
	 * @see #setUsername(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBase_Username()
	 * @model required="true"
	 *        extendedMetaData="name='username' kind='attribute'"
	 * @generated
	 */
	String getUsername();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getUsername <em>Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Username</em>' attribute.
	 * @see #getUsername()
	 * @generated
	 */
	void setUsername(String value);

	/**
	 * Returns the value of the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Password</em>' attribute.
	 * @see #setPassword(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBase_Password()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute' name='password'"
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

	/**
	 * Returns the value of the '<em><b>Max Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Active</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Active</em>' attribute.
	 * @see #setMaxActive(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBase_MaxActive()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getMaxActive();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getMaxActive <em>Max Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Active</em>' attribute.
	 * @see #getMaxActive()
	 * @generated
	 */
	void setMaxActive(String value);

	/**
	 * Returns the value of the '<em><b>Max Idle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Idle</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Idle</em>' attribute.
	 * @see #setMaxIdle(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBase_MaxIdle()
	 * @model required="true"
	 *        extendedMetaData="name='maxIdle' kind='attribute'"
	 * @generated
	 */
	String getMaxIdle();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getMaxIdle <em>Max Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Idle</em>' attribute.
	 * @see #getMaxIdle()
	 * @generated
	 */
	void setMaxIdle(String value);

	/**
	 * Returns the value of the '<em><b>Max Wait</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Wait</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Wait</em>' attribute.
	 * @see #setMaxWait(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBase_MaxWait()
	 * @model required="true"
	 *        extendedMetaData="name='maxWait' kind='attribute'"
	 * @generated
	 */
	String getMaxWait();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getMaxWait <em>Max Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Wait</em>' attribute.
	 * @see #getMaxWait()
	 * @generated
	 */
	void setMaxWait(String value);

	/**
	 * Returns the value of the '<em><b>Pagination Impl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pagination Impl</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pagination Impl</em>' attribute.
	 * @see #setPaginationImpl(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBase_PaginationImpl()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPaginationImpl();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getPaginationImpl <em>Pagination Impl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pagination Impl</em>' attribute.
	 * @see #getPaginationImpl()
	 * @generated
	 */
	void setPaginationImpl(String value);

	/**
	 * Returns the value of the '<em><b>Dbtype</b></em>' attribute.
	 * The literals are from the enumeration {@link com.founder.fix.bpmn2extensions.coreconfig.DBType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dbtype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dbtype</em>' attribute.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DBType
	 * @see #setDbtype(DBType)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBase_Dbtype()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	DBType getDbtype();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBase#getDbtype <em>Dbtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dbtype</em>' attribute.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.DBType
	 * @see #getDbtype()
	 * @generated
	 */
	void setDbtype(DBType value);

} // DataBase
