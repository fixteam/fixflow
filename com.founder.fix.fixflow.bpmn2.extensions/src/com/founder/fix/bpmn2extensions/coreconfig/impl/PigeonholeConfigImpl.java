/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig.impl;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pigeonhole Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PigeonholeConfigImpl#getServerPath <em>Server Path</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PigeonholeConfigImpl#getPdfPath <em>Pdf Path</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PigeonholeConfigImpl#getTime <em>Time</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PigeonholeConfigImpl#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PigeonholeConfigImpl#getWeek <em>Week</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PigeonholeConfigImpl#getMonth <em>Month</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PigeonholeConfigImpl#getCode <em>Code</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.impl.PigeonholeConfigImpl#getIsEnable <em>Is Enable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PigeonholeConfigImpl extends EObjectImpl implements PigeonholeConfig {
	/**
	 * The default value of the '{@link #getServerPath() <em>Server Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerPath()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVER_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServerPath() <em>Server Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerPath()
	 * @generated
	 * @ordered
	 */
	protected String serverPath = SERVER_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getPdfPath() <em>Pdf Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPdfPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PDF_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPdfPath() <em>Pdf Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPdfPath()
	 * @generated
	 * @ordered
	 */
	protected String pdfPath = PDF_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected static final String TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected String time = TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFrequency() <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrequency()
	 * @generated
	 * @ordered
	 */
	protected static final String FREQUENCY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFrequency() <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrequency()
	 * @generated
	 * @ordered
	 */
	protected String frequency = FREQUENCY_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeek() <em>Week</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeek()
	 * @generated
	 * @ordered
	 */
	protected static final String WEEK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWeek() <em>Week</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeek()
	 * @generated
	 * @ordered
	 */
	protected String week = WEEK_EDEFAULT;

	/**
	 * The default value of the '{@link #getMonth() <em>Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonth()
	 * @generated
	 * @ordered
	 */
	protected static final String MONTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMonth() <em>Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonth()
	 * @generated
	 * @ordered
	 */
	protected String month = MONTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected static final String CODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected String code = CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsEnable() <em>Is Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsEnable()
	 * @generated
	 * @ordered
	 */
	protected static final String IS_ENABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsEnable() <em>Is Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsEnable()
	 * @generated
	 * @ordered
	 */
	protected String isEnable = IS_ENABLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PigeonholeConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoreconfigPackage.Literals.PIGEONHOLE_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServerPath() {
		return serverPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServerPath(String newServerPath) {
		String oldServerPath = serverPath;
		serverPath = newServerPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.PIGEONHOLE_CONFIG__SERVER_PATH, oldServerPath, serverPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPdfPath() {
		return pdfPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPdfPath(String newPdfPath) {
		String oldPdfPath = pdfPath;
		pdfPath = newPdfPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.PIGEONHOLE_CONFIG__PDF_PATH, oldPdfPath, pdfPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTime() {
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTime(String newTime) {
		String oldTime = time;
		time = newTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.PIGEONHOLE_CONFIG__TIME, oldTime, time));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFrequency() {
		return frequency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrequency(String newFrequency) {
		String oldFrequency = frequency;
		frequency = newFrequency;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.PIGEONHOLE_CONFIG__FREQUENCY, oldFrequency, frequency));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWeek() {
		return week;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeek(String newWeek) {
		String oldWeek = week;
		week = newWeek;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.PIGEONHOLE_CONFIG__WEEK, oldWeek, week));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMonth(String newMonth) {
		String oldMonth = month;
		month = newMonth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.PIGEONHOLE_CONFIG__MONTH, oldMonth, month));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCode() {
		return code;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCode(String newCode) {
		String oldCode = code;
		code = newCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.PIGEONHOLE_CONFIG__CODE, oldCode, code));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIsEnable() {
		return isEnable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEnable(String newIsEnable) {
		String oldIsEnable = isEnable;
		isEnable = newIsEnable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoreconfigPackage.PIGEONHOLE_CONFIG__IS_ENABLE, oldIsEnable, isEnable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CoreconfigPackage.PIGEONHOLE_CONFIG__SERVER_PATH:
				return getServerPath();
			case CoreconfigPackage.PIGEONHOLE_CONFIG__PDF_PATH:
				return getPdfPath();
			case CoreconfigPackage.PIGEONHOLE_CONFIG__TIME:
				return getTime();
			case CoreconfigPackage.PIGEONHOLE_CONFIG__FREQUENCY:
				return getFrequency();
			case CoreconfigPackage.PIGEONHOLE_CONFIG__WEEK:
				return getWeek();
			case CoreconfigPackage.PIGEONHOLE_CONFIG__MONTH:
				return getMonth();
			case CoreconfigPackage.PIGEONHOLE_CONFIG__CODE:
				return getCode();
			case CoreconfigPackage.PIGEONHOLE_CONFIG__IS_ENABLE:
				return getIsEnable();
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
			case CoreconfigPackage.PIGEONHOLE_CONFIG__SERVER_PATH:
				setServerPath((String)newValue);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__PDF_PATH:
				setPdfPath((String)newValue);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__TIME:
				setTime((String)newValue);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__FREQUENCY:
				setFrequency((String)newValue);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__WEEK:
				setWeek((String)newValue);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__MONTH:
				setMonth((String)newValue);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__CODE:
				setCode((String)newValue);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__IS_ENABLE:
				setIsEnable((String)newValue);
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
			case CoreconfigPackage.PIGEONHOLE_CONFIG__SERVER_PATH:
				setServerPath(SERVER_PATH_EDEFAULT);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__PDF_PATH:
				setPdfPath(PDF_PATH_EDEFAULT);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__TIME:
				setTime(TIME_EDEFAULT);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__FREQUENCY:
				setFrequency(FREQUENCY_EDEFAULT);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__WEEK:
				setWeek(WEEK_EDEFAULT);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__MONTH:
				setMonth(MONTH_EDEFAULT);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__CODE:
				setCode(CODE_EDEFAULT);
				return;
			case CoreconfigPackage.PIGEONHOLE_CONFIG__IS_ENABLE:
				setIsEnable(IS_ENABLE_EDEFAULT);
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
			case CoreconfigPackage.PIGEONHOLE_CONFIG__SERVER_PATH:
				return SERVER_PATH_EDEFAULT == null ? serverPath != null : !SERVER_PATH_EDEFAULT.equals(serverPath);
			case CoreconfigPackage.PIGEONHOLE_CONFIG__PDF_PATH:
				return PDF_PATH_EDEFAULT == null ? pdfPath != null : !PDF_PATH_EDEFAULT.equals(pdfPath);
			case CoreconfigPackage.PIGEONHOLE_CONFIG__TIME:
				return TIME_EDEFAULT == null ? time != null : !TIME_EDEFAULT.equals(time);
			case CoreconfigPackage.PIGEONHOLE_CONFIG__FREQUENCY:
				return FREQUENCY_EDEFAULT == null ? frequency != null : !FREQUENCY_EDEFAULT.equals(frequency);
			case CoreconfigPackage.PIGEONHOLE_CONFIG__WEEK:
				return WEEK_EDEFAULT == null ? week != null : !WEEK_EDEFAULT.equals(week);
			case CoreconfigPackage.PIGEONHOLE_CONFIG__MONTH:
				return MONTH_EDEFAULT == null ? month != null : !MONTH_EDEFAULT.equals(month);
			case CoreconfigPackage.PIGEONHOLE_CONFIG__CODE:
				return CODE_EDEFAULT == null ? code != null : !CODE_EDEFAULT.equals(code);
			case CoreconfigPackage.PIGEONHOLE_CONFIG__IS_ENABLE:
				return IS_ENABLE_EDEFAULT == null ? isEnable != null : !IS_ENABLE_EDEFAULT.equals(isEnable);
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
		result.append(" (serverPath: ");
		result.append(serverPath);
		result.append(", pdfPath: ");
		result.append(pdfPath);
		result.append(", time: ");
		result.append(time);
		result.append(", frequency: ");
		result.append(frequency);
		result.append(", week: ");
		result.append(week);
		result.append(", month: ");
		result.append(month);
		result.append(", code: ");
		result.append(code);
		result.append(", isEnable: ");
		result.append(isEnable);
		result.append(')');
		return result.toString();
	}

} //PigeonholeConfigImpl
