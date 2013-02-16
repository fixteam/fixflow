/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow.impl;

import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.SkipAssignee;
import com.founder.fix.bpmn2extensions.fixflow.SkipComment;
import com.founder.fix.bpmn2extensions.fixflow.SkipStrategy;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Skip Strategy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.SkipStrategyImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.SkipStrategyImpl#isIsCreateSkipProcess <em>Is Create Skip Process</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.SkipStrategyImpl#getSkipAssignee <em>Skip Assignee</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.SkipStrategyImpl#getSkipComment <em>Skip Comment</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.SkipStrategyImpl#isIsEnable <em>Is Enable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SkipStrategyImpl extends EObjectImpl implements SkipStrategy {
	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression expression;

	/**
	 * The default value of the '{@link #isIsCreateSkipProcess() <em>Is Create Skip Process</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCreateSkipProcess()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_CREATE_SKIP_PROCESS_EDEFAULT = true;
	/**
	 * The cached value of the '{@link #isIsCreateSkipProcess() <em>Is Create Skip Process</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCreateSkipProcess()
	 * @generated
	 * @ordered
	 */
	protected boolean isCreateSkipProcess = IS_CREATE_SKIP_PROCESS_EDEFAULT;
	/**
	 * The cached value of the '{@link #getSkipAssignee() <em>Skip Assignee</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkipAssignee()
	 * @generated
	 * @ordered
	 */
	protected SkipAssignee skipAssignee;

	/**
	 * The cached value of the '{@link #getSkipComment() <em>Skip Comment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkipComment()
	 * @generated
	 * @ordered
	 */
	protected SkipComment skipComment;

	/**
	 * The default value of the '{@link #isIsEnable() <em>Is Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEnable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ENABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsEnable() <em>Is Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEnable()
	 * @generated
	 * @ordered
	 */
	protected boolean isEnable = IS_ENABLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SkipStrategyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FixFlowPackage.Literals.SKIP_STRATEGY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
		Expression oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.SKIP_STRATEGY__EXPRESSION, oldExpression, newExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		if (newExpression != expression) {
			NotificationChain msgs = null;
			if (expression != null)
				msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.SKIP_STRATEGY__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.SKIP_STRATEGY__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.SKIP_STRATEGY__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsCreateSkipProcess() {
		return isCreateSkipProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsCreateSkipProcess(boolean newIsCreateSkipProcess) {
		boolean oldIsCreateSkipProcess = isCreateSkipProcess;
		isCreateSkipProcess = newIsCreateSkipProcess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.SKIP_STRATEGY__IS_CREATE_SKIP_PROCESS, oldIsCreateSkipProcess, isCreateSkipProcess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SkipAssignee getSkipAssignee() {
		return skipAssignee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSkipAssignee(SkipAssignee newSkipAssignee, NotificationChain msgs) {
		SkipAssignee oldSkipAssignee = skipAssignee;
		skipAssignee = newSkipAssignee;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.SKIP_STRATEGY__SKIP_ASSIGNEE, oldSkipAssignee, newSkipAssignee);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSkipAssignee(SkipAssignee newSkipAssignee) {
		if (newSkipAssignee != skipAssignee) {
			NotificationChain msgs = null;
			if (skipAssignee != null)
				msgs = ((InternalEObject)skipAssignee).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.SKIP_STRATEGY__SKIP_ASSIGNEE, null, msgs);
			if (newSkipAssignee != null)
				msgs = ((InternalEObject)newSkipAssignee).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.SKIP_STRATEGY__SKIP_ASSIGNEE, null, msgs);
			msgs = basicSetSkipAssignee(newSkipAssignee, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.SKIP_STRATEGY__SKIP_ASSIGNEE, newSkipAssignee, newSkipAssignee));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SkipComment getSkipComment() {
		return skipComment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSkipComment(SkipComment newSkipComment, NotificationChain msgs) {
		SkipComment oldSkipComment = skipComment;
		skipComment = newSkipComment;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.SKIP_STRATEGY__SKIP_COMMENT, oldSkipComment, newSkipComment);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSkipComment(SkipComment newSkipComment) {
		if (newSkipComment != skipComment) {
			NotificationChain msgs = null;
			if (skipComment != null)
				msgs = ((InternalEObject)skipComment).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.SKIP_STRATEGY__SKIP_COMMENT, null, msgs);
			if (newSkipComment != null)
				msgs = ((InternalEObject)newSkipComment).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.SKIP_STRATEGY__SKIP_COMMENT, null, msgs);
			msgs = basicSetSkipComment(newSkipComment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.SKIP_STRATEGY__SKIP_COMMENT, newSkipComment, newSkipComment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsEnable() {
		return isEnable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEnable(boolean newIsEnable) {
		boolean oldIsEnable = isEnable;
		isEnable = newIsEnable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.SKIP_STRATEGY__IS_ENABLE, oldIsEnable, isEnable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FixFlowPackage.SKIP_STRATEGY__EXPRESSION:
				return basicSetExpression(null, msgs);
			case FixFlowPackage.SKIP_STRATEGY__SKIP_ASSIGNEE:
				return basicSetSkipAssignee(null, msgs);
			case FixFlowPackage.SKIP_STRATEGY__SKIP_COMMENT:
				return basicSetSkipComment(null, msgs);
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
			case FixFlowPackage.SKIP_STRATEGY__EXPRESSION:
				return getExpression();
			case FixFlowPackage.SKIP_STRATEGY__IS_CREATE_SKIP_PROCESS:
				return isIsCreateSkipProcess();
			case FixFlowPackage.SKIP_STRATEGY__SKIP_ASSIGNEE:
				return getSkipAssignee();
			case FixFlowPackage.SKIP_STRATEGY__SKIP_COMMENT:
				return getSkipComment();
			case FixFlowPackage.SKIP_STRATEGY__IS_ENABLE:
				return isIsEnable();
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
			case FixFlowPackage.SKIP_STRATEGY__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case FixFlowPackage.SKIP_STRATEGY__IS_CREATE_SKIP_PROCESS:
				setIsCreateSkipProcess((Boolean)newValue);
				return;
			case FixFlowPackage.SKIP_STRATEGY__SKIP_ASSIGNEE:
				setSkipAssignee((SkipAssignee)newValue);
				return;
			case FixFlowPackage.SKIP_STRATEGY__SKIP_COMMENT:
				setSkipComment((SkipComment)newValue);
				return;
			case FixFlowPackage.SKIP_STRATEGY__IS_ENABLE:
				setIsEnable((Boolean)newValue);
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
			case FixFlowPackage.SKIP_STRATEGY__EXPRESSION:
				setExpression((Expression)null);
				return;
			case FixFlowPackage.SKIP_STRATEGY__IS_CREATE_SKIP_PROCESS:
				setIsCreateSkipProcess(IS_CREATE_SKIP_PROCESS_EDEFAULT);
				return;
			case FixFlowPackage.SKIP_STRATEGY__SKIP_ASSIGNEE:
				setSkipAssignee((SkipAssignee)null);
				return;
			case FixFlowPackage.SKIP_STRATEGY__SKIP_COMMENT:
				setSkipComment((SkipComment)null);
				return;
			case FixFlowPackage.SKIP_STRATEGY__IS_ENABLE:
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
			case FixFlowPackage.SKIP_STRATEGY__EXPRESSION:
				return expression != null;
			case FixFlowPackage.SKIP_STRATEGY__IS_CREATE_SKIP_PROCESS:
				return isCreateSkipProcess != IS_CREATE_SKIP_PROCESS_EDEFAULT;
			case FixFlowPackage.SKIP_STRATEGY__SKIP_ASSIGNEE:
				return skipAssignee != null;
			case FixFlowPackage.SKIP_STRATEGY__SKIP_COMMENT:
				return skipComment != null;
			case FixFlowPackage.SKIP_STRATEGY__IS_ENABLE:
				return isEnable != IS_ENABLE_EDEFAULT;
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
		result.append(" (isCreateSkipProcess: ");
		result.append(isCreateSkipProcess);
		result.append(", isEnable: ");
		result.append(isEnable);
		result.append(')');
		return result.toString();
	}

} //SkipStrategyImpl
