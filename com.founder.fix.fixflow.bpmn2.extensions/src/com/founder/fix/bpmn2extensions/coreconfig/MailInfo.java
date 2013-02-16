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
 * A representation of the model object '<em><b>Mail Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getMailAddress <em>Mail Address</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getMailName <em>Mail Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getSmtpHost <em>Smtp Host</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getSmtpPort <em>Smtp Port</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getUserName <em>User Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getPassWord <em>Pass Word</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getMailInfo()
 * @model
 * @generated
 */
public interface MailInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Mail Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mail Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mail Address</em>' attribute.
	 * @see #setMailAddress(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getMailInfo_MailAddress()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getMailAddress();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getMailAddress <em>Mail Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mail Address</em>' attribute.
	 * @see #getMailAddress()
	 * @generated
	 */
	void setMailAddress(String value);

	/**
	 * Returns the value of the '<em><b>Mail Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mail Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mail Name</em>' attribute.
	 * @see #setMailName(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getMailInfo_MailName()
	 * @model required="true"
	 *        extendedMetaData="name='mailName' kind='attribute'"
	 * @generated
	 */
	String getMailName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getMailName <em>Mail Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mail Name</em>' attribute.
	 * @see #getMailName()
	 * @generated
	 */
	void setMailName(String value);

	/**
	 * Returns the value of the '<em><b>Smtp Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 邮件发送服务器地址
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Smtp Host</em>' attribute.
	 * @see #setSmtpHost(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getMailInfo_SmtpHost()
	 * @model required="true"
	 *        extendedMetaData="name='smtpHost' kind='attribute'"
	 * @generated
	 */
	String getSmtpHost();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getSmtpHost <em>Smtp Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Smtp Host</em>' attribute.
	 * @see #getSmtpHost()
	 * @generated
	 */
	void setSmtpHost(String value);

	/**
	 * Returns the value of the '<em><b>Smtp Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 发件服务器端口默认 25
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Smtp Port</em>' attribute.
	 * @see #setSmtpPort(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getMailInfo_SmtpPort()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getSmtpPort();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getSmtpPort <em>Smtp Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Smtp Port</em>' attribute.
	 * @see #getSmtpPort()
	 * @generated
	 */
	void setSmtpPort(String value);

	/**
	 * Returns the value of the '<em><b>User Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 用户名
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>User Name</em>' attribute.
	 * @see #setUserName(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getMailInfo_UserName()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getUserName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getUserName <em>User Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Name</em>' attribute.
	 * @see #getUserName()
	 * @generated
	 */
	void setUserName(String value);

	/**
	 * Returns the value of the '<em><b>Pass Word</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 密码
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pass Word</em>' attribute.
	 * @see #setPassWord(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getMailInfo_PassWord()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPassWord();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.MailInfo#getPassWord <em>Pass Word</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pass Word</em>' attribute.
	 * @see #getPassWord()
	 * @generated
	 */
	void setPassWord(String value);

} // MailInfo
