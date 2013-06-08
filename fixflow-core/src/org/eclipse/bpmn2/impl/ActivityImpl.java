/**
 * <copyright>
 * 
 * Copyright (c) 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Reiner Hille-Doering (SAP AG) - initial API and implementation and/or initial documentation
 * 
 * </copyright>
 */
package org.eclipse.bpmn2.impl;

import java.util.Collection;
import java.util.Date;

import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BoundaryEvent;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;
import org.eclipse.bpmn2.StandardLoopCharacteristics;

import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.LoopCharacteristics;

import org.eclipse.bpmn2.Property;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.founder.fix.bpmn2extensions.fixflow.SkipStrategy;
import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.util.EMFExtensionUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Activity</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getIoSpecification <em>Io
 * Specification</em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getBoundaryEventRefs <em>
 * Boundary Event Refs</em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getProperties <em>Properties
 * </em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getDataInputAssociations <em>
 * Data Input Associations</em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getDataOutputAssociations <em>
 * Data Output Associations</em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getResources <em>Resources
 * </em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getLoopCharacteristics <em>
 * Loop Characteristics</em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getCompletionQuantity <em>
 * Completion Quantity</em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getDefault <em>Default</em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#isIsForCompensation <em>Is For
 * Compensation</em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getStartQuantity <em>Start
 * Quantity</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ActivityImpl extends FlowNodeImpl implements Activity {
	/**
	 * The cached value of the '{@link #getIoSpecification()
	 * <em>Io Specification</em>}' containment reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getIoSpecification()
	 * @generated
	 * @ordered
	 */
	protected InputOutputSpecification ioSpecification;

	/**
	 * The cached value of the '{@link #getBoundaryEventRefs()
	 * <em>Boundary Event Refs</em>}' reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getBoundaryEventRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<BoundaryEvent> boundaryEventRefs;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> properties;

	/**
	 * The cached value of the '{@link #getDataInputAssociations()
	 * <em>Data Input Associations</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDataInputAssociations()
	 * @generated
	 * @ordered
	 */
	protected EList<DataInputAssociation> dataInputAssociations;

	/**
	 * The cached value of the '{@link #getDataOutputAssociations()
	 * <em>Data Output Associations</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDataOutputAssociations()
	 * @generated
	 * @ordered
	 */
	protected EList<DataOutputAssociation> dataOutputAssociations;

	/**
	 * The cached value of the '{@link #getResources() <em>Resources</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResources()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceRole> resources;

	/**
	 * The cached value of the '{@link #getLoopCharacteristics()
	 * <em>Loop Characteristics</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLoopCharacteristics()
	 * @generated
	 * @ordered
	 */
	protected LoopCharacteristics loopCharacteristics;

	/**
	 * The default value of the '{@link #getCompletionQuantity()
	 * <em>Completion Quantity</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCompletionQuantity()
	 * @generated
	 * @ordered
	 */
	protected static final int COMPLETION_QUANTITY_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getCompletionQuantity()
	 * <em>Completion Quantity</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCompletionQuantity()
	 * @generated
	 * @ordered
	 */
	protected int completionQuantity = COMPLETION_QUANTITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDefault() <em>Default</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDefault()
	 * @generated
	 * @ordered
	 */
	protected SequenceFlow default_;

	/**
	 * The default value of the '{@link #isIsForCompensation()
	 * <em>Is For Compensation</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isIsForCompensation()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_FOR_COMPENSATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsForCompensation()
	 * <em>Is For Compensation</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isIsForCompensation()
	 * @generated
	 * @ordered
	 */
	protected boolean isForCompensation = IS_FOR_COMPENSATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartQuantity()
	 * <em>Start Quantity</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStartQuantity()
	 * @generated
	 * @ordered
	 */
	protected static final int START_QUANTITY_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getStartQuantity()
	 * <em>Start Quantity</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStartQuantity()
	 * @generated
	 * @ordered
	 */
	protected int startQuantity = START_QUANTITY_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bpmn2Package.Literals.ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public InputOutputSpecification getIoSpecification() {
		return ioSpecification;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetIoSpecification(InputOutputSpecification newIoSpecification, NotificationChain msgs) {
		InputOutputSpecification oldIoSpecification = ioSpecification;
		ioSpecification = newIoSpecification;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__IO_SPECIFICATION,
					oldIoSpecification, newIoSpecification);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIoSpecification(InputOutputSpecification newIoSpecification) {
		if (newIoSpecification != ioSpecification) {
			NotificationChain msgs = null;
			if (ioSpecification != null)
				msgs = ((InternalEObject) ioSpecification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Bpmn2Package.ACTIVITY__IO_SPECIFICATION,
						null, msgs);
			if (newIoSpecification != null)
				msgs = ((InternalEObject) newIoSpecification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Bpmn2Package.ACTIVITY__IO_SPECIFICATION,
						null, msgs);
			msgs = basicSetIoSpecification(newIoSpecification, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__IO_SPECIFICATION, newIoSpecification, newIoSpecification));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public List<BoundaryEvent> getBoundaryEventRefs() {
		if (boundaryEventRefs == null) {
			boundaryEventRefs = new EObjectWithInverseEList<BoundaryEvent>(BoundaryEvent.class, this, Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS,
					Bpmn2Package.BOUNDARY_EVENT__ATTACHED_TO_REF);
		}
		return boundaryEventRefs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public List<Property> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<Property>(Property.class, this, Bpmn2Package.ACTIVITY__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public List<DataInputAssociation> getDataInputAssociations() {
		if (dataInputAssociations == null) {
			dataInputAssociations = new EObjectContainmentEList<DataInputAssociation>(DataInputAssociation.class, this,
					Bpmn2Package.ACTIVITY__DATA_INPUT_ASSOCIATIONS);
		}
		return dataInputAssociations;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public List<DataOutputAssociation> getDataOutputAssociations() {
		if (dataOutputAssociations == null) {
			dataOutputAssociations = new EObjectContainmentEList<DataOutputAssociation>(DataOutputAssociation.class, this,
					Bpmn2Package.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS);
		}
		return dataOutputAssociations;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public List<ResourceRole> getResources() {
		if (resources == null) {
			resources = new EObjectContainmentEList<ResourceRole>(ResourceRole.class, this, Bpmn2Package.ACTIVITY__RESOURCES);
		}
		return resources;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public LoopCharacteristics getLoopCharacteristics() {
		return loopCharacteristics;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetLoopCharacteristics(LoopCharacteristics newLoopCharacteristics, NotificationChain msgs) {
		LoopCharacteristics oldLoopCharacteristics = loopCharacteristics;
		loopCharacteristics = newLoopCharacteristics;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS,
					oldLoopCharacteristics, newLoopCharacteristics);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLoopCharacteristics(LoopCharacteristics newLoopCharacteristics) {
		if (newLoopCharacteristics != loopCharacteristics) {
			NotificationChain msgs = null;
			if (loopCharacteristics != null)
				msgs = ((InternalEObject) loopCharacteristics).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS, null, msgs);
			if (newLoopCharacteristics != null)
				msgs = ((InternalEObject) newLoopCharacteristics).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS, null, msgs);
			msgs = basicSetLoopCharacteristics(newLoopCharacteristics, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS, newLoopCharacteristics,
					newLoopCharacteristics));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getCompletionQuantity() {
		return completionQuantity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setCompletionQuantity(int newCompletionQuantity) {
		int oldCompletionQuantity = completionQuantity;
		completionQuantity = newCompletionQuantity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY, oldCompletionQuantity,
					completionQuantity));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public SequenceFlow getDefault() {
		return default_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDefault(SequenceFlow newDefault) {
		SequenceFlow oldDefault = default_;
		default_ = newDefault;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__DEFAULT, oldDefault, default_));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isIsForCompensation() {
		return isForCompensation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsForCompensation(boolean newIsForCompensation) {
		boolean oldIsForCompensation = isForCompensation;
		isForCompensation = newIsForCompensation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION, oldIsForCompensation, isForCompensation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getStartQuantity() {
		return startQuantity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setStartQuantity(int newStartQuantity) {
		int oldStartQuantity = startQuantity;
		startQuantity = newStartQuantity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__START_QUANTITY, oldStartQuantity, startQuantity));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getBoundaryEventRefs()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Bpmn2Package.ACTIVITY__IO_SPECIFICATION:
			return basicSetIoSpecification(null, msgs);
		case Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS:
			return ((InternalEList<?>) getBoundaryEventRefs()).basicRemove(otherEnd, msgs);
		case Bpmn2Package.ACTIVITY__PROPERTIES:
			return ((InternalEList<?>) getProperties()).basicRemove(otherEnd, msgs);
		case Bpmn2Package.ACTIVITY__DATA_INPUT_ASSOCIATIONS:
			return ((InternalEList<?>) getDataInputAssociations()).basicRemove(otherEnd, msgs);
		case Bpmn2Package.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS:
			return ((InternalEList<?>) getDataOutputAssociations()).basicRemove(otherEnd, msgs);
		case Bpmn2Package.ACTIVITY__RESOURCES:
			return ((InternalEList<?>) getResources()).basicRemove(otherEnd, msgs);
		case Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS:
			return basicSetLoopCharacteristics(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Bpmn2Package.ACTIVITY__IO_SPECIFICATION:
			return getIoSpecification();
		case Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS:
			return getBoundaryEventRefs();
		case Bpmn2Package.ACTIVITY__PROPERTIES:
			return getProperties();
		case Bpmn2Package.ACTIVITY__DATA_INPUT_ASSOCIATIONS:
			return getDataInputAssociations();
		case Bpmn2Package.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS:
			return getDataOutputAssociations();
		case Bpmn2Package.ACTIVITY__RESOURCES:
			return getResources();
		case Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS:
			return getLoopCharacteristics();
		case Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY:
			return getCompletionQuantity();
		case Bpmn2Package.ACTIVITY__DEFAULT:
			return getDefault();
		case Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION:
			return isIsForCompensation();
		case Bpmn2Package.ACTIVITY__START_QUANTITY:
			return getStartQuantity();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case Bpmn2Package.ACTIVITY__IO_SPECIFICATION:
			setIoSpecification((InputOutputSpecification) newValue);
			return;
		case Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS:
			getBoundaryEventRefs().clear();
			getBoundaryEventRefs().addAll((Collection<? extends BoundaryEvent>) newValue);
			return;
		case Bpmn2Package.ACTIVITY__PROPERTIES:
			getProperties().clear();
			getProperties().addAll((Collection<? extends Property>) newValue);
			return;
		case Bpmn2Package.ACTIVITY__DATA_INPUT_ASSOCIATIONS:
			getDataInputAssociations().clear();
			getDataInputAssociations().addAll((Collection<? extends DataInputAssociation>) newValue);
			return;
		case Bpmn2Package.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS:
			getDataOutputAssociations().clear();
			getDataOutputAssociations().addAll((Collection<? extends DataOutputAssociation>) newValue);
			return;
		case Bpmn2Package.ACTIVITY__RESOURCES:
			getResources().clear();
			getResources().addAll((Collection<? extends ResourceRole>) newValue);
			return;
		case Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS:
			setLoopCharacteristics((LoopCharacteristics) newValue);
			return;
		case Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY:
			setCompletionQuantity((Integer) newValue);
			return;
		case Bpmn2Package.ACTIVITY__DEFAULT:
			setDefault((SequenceFlow) newValue);
			return;
		case Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION:
			setIsForCompensation((Boolean) newValue);
			return;
		case Bpmn2Package.ACTIVITY__START_QUANTITY:
			setStartQuantity((Integer) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case Bpmn2Package.ACTIVITY__IO_SPECIFICATION:
			setIoSpecification((InputOutputSpecification) null);
			return;
		case Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS:
			getBoundaryEventRefs().clear();
			return;
		case Bpmn2Package.ACTIVITY__PROPERTIES:
			getProperties().clear();
			return;
		case Bpmn2Package.ACTIVITY__DATA_INPUT_ASSOCIATIONS:
			getDataInputAssociations().clear();
			return;
		case Bpmn2Package.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS:
			getDataOutputAssociations().clear();
			return;
		case Bpmn2Package.ACTIVITY__RESOURCES:
			getResources().clear();
			return;
		case Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS:
			setLoopCharacteristics((LoopCharacteristics) null);
			return;
		case Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY:
			setCompletionQuantity(COMPLETION_QUANTITY_EDEFAULT);
			return;
		case Bpmn2Package.ACTIVITY__DEFAULT:
			setDefault((SequenceFlow) null);
			return;
		case Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION:
			setIsForCompensation(IS_FOR_COMPENSATION_EDEFAULT);
			return;
		case Bpmn2Package.ACTIVITY__START_QUANTITY:
			setStartQuantity(START_QUANTITY_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case Bpmn2Package.ACTIVITY__IO_SPECIFICATION:
			return ioSpecification != null;
		case Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS:
			return boundaryEventRefs != null && !boundaryEventRefs.isEmpty();
		case Bpmn2Package.ACTIVITY__PROPERTIES:
			return properties != null && !properties.isEmpty();
		case Bpmn2Package.ACTIVITY__DATA_INPUT_ASSOCIATIONS:
			return dataInputAssociations != null && !dataInputAssociations.isEmpty();
		case Bpmn2Package.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS:
			return dataOutputAssociations != null && !dataOutputAssociations.isEmpty();
		case Bpmn2Package.ACTIVITY__RESOURCES:
			return resources != null && !resources.isEmpty();
		case Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS:
			return loopCharacteristics != null;
		case Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY:
			return completionQuantity != COMPLETION_QUANTITY_EDEFAULT;
		case Bpmn2Package.ACTIVITY__DEFAULT:
			return default_ != null;
		case Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION:
			return isForCompensation != IS_FOR_COMPENSATION_EDEFAULT;
		case Bpmn2Package.ACTIVITY__START_QUANTITY:
			return startQuantity != START_QUANTITY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (completionQuantity: ");
		result.append(completionQuantity);
		result.append(", isForCompensation: ");
		result.append(isForCompensation);
		result.append(", startQuantity: ");
		result.append(startQuantity);
		result.append(')');
		return result.toString();
	}

	// 非BPMN2.0

	/**
	 * 获取节点的跳过策略
	 * 
	 * @return
	 */
	public SkipStrategy getSkipStrategy() {
		SkipStrategy skipStrategy = EMFExtensionUtil.getSkipStrategy(this);
		return skipStrategy;
	}

	/**
	 * 
	 * @param executionContext
	 * @throws Exception
	 */
	public void enter(ExecutionContext executionContext) {
		TokenEntity token = executionContext.getToken();

		// 把令牌的所在节点设置为当前节点
		token.setFlowNode(this);

		// 触发节点进入事件
		// fireEvent(BaseElementEvent.EVENTTYPE_NODE_ENTER, executionContext);

		// 设置令牌进入节点的时间
		token.setNodeEnterTime(new Date());

		// 移除执行内容对象的线条关联
		executionContext.setSequenceFlow(null);

		executionContext.setGroupID(null);

		executionContext.setSequenceFlowSource(null);

		SkipStrategy skipStrategy = getSkipStrategy();

		// entryList.get(0);
		if (skipStrategy != null) {

			boolean isEnable = skipStrategy.isIsEnable();

			// 判断是否启用
			if (isEnable) {

				boolean valueObj = false;

				if (skipStrategy.getExpression() != null) {
					String expressionValue = skipStrategy.getExpression().getValue();
					if (expressionValue != null && !expressionValue.equals("")) {
						try {
							valueObj = StringUtil.getBoolean(ExpressionMgmt.execute(expressionValue, executionContext));

						} catch (Exception e) {

							throw new FixFlowException("节点 " + this.getId() + " " + this.getName() + " 的跳过策略出错请检查流程配置！", e);
						}
					}
				}

				if (valueObj) {

					// 直接跳过
					boolean isCreateSkipProcess = skipStrategy.isIsCreateSkipProcess();

					if (isCreateSkipProcess) {
						
						
						executionContext.setSkipStrategy(skipStrategy);
						skipExecute(executionContext);
					} else {

					}

					super.leave(executionContext);
					return;
				}

			}

		}
		
		
		eventExecute(executionContext);
		
		

	}

	private void forkedTokenEnter(ExecutionContext executionContext,boolean isMultiple) {
		TokenEntity token = executionContext.getToken();

		// 把令牌的所在节点设置为当前节点
		token.setFlowNode(this);

		// 触发节点进入事件
		fireEvent(BaseElementEvent.EVENTTYPE_NODE_ENTER, executionContext);

		// 设置令牌进入节点的时间
		token.setNodeEnterTime(new Date());

		// 移除执行内容对象的线条关联
		executionContext.setSequenceFlow(null);

		executionContext.setSequenceFlowSource(null);

		if(isMultiple){
			execute(executionContext);
		}else{
			loopExecute(executionContext);
		}
		
		/*
		if (this instanceof Activity && ((Activity) this).getLoopCharacteristics() != null) {

			loopExecute(executionContext);

		} else {

			
		}*/
	}

	protected void eventExecute(ExecutionContext executionContext) {

		// 触发节点进入事件
		fireEvent(BaseElementEvent.EVENTTYPE_NODE_ENTER, executionContext);

		if (this.getBoundaryEventRefs().size() > 0) {
			List<BoundaryEvent> boundaryEvents = this.getBoundaryEventRefs();
			TokenEntity tokenEntity = executionContext.getToken();

			String nodeTokenId = this.getId();
			// 创建分支令牌并添加到集合中
			TokenEntity nodeToken = this.createForkedToken(tokenEntity, nodeTokenId).token;
			ExecutionContext nodeChildExecutionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(nodeToken);

			this.forkedTokenEnter(nodeChildExecutionContext,false);

			// 遍历边界事件
			for (BoundaryEvent boundaryEvent : boundaryEvents) {
				// 获取边界事件名称
				// String tokenId = boundaryEvent.getId();

				// TokenEntity childToken =this.createForkedToken(tokenEntity,
				// tokenId).token;

				// 创建执行内容对象并将里边的令牌赋值为新的分支令牌

				// ExecutionContext childExecutionContext =
				// ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(childToken);

				// 这里的逻辑是这样的，当token进入节点发现有 BoundaryEvent 则 创建一个子令牌
				boundaryEvent.execute(executionContext);
			}

			// execute(executionContext);

		} else {
			loopExecute(executionContext);
		}

	}

	protected void skipExecute(ExecutionContext executionContext) {
		// debugLog.debug("节点 "+getId()+" 执行跳过处理!");
	}

	@SuppressWarnings("rawtypes")
	protected void loopExecute(ExecutionContext executionContext) {
		Activity activity = (Activity) this;
		LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();

		if (loopCharacteristics instanceof MultiInstanceLoopCharacteristics) {
			// 并行多实例处理

			// 解决多实例处理退回BUG
			List<FeatureMap.Entry> dataOutputentryList = EMFExtensionUtil.getExtensionElements(loopCharacteristics, "loopDataOutputCollection");

			if (dataOutputentryList == null || dataOutputentryList.size() == 0) {

			} else {
				dataOutputentryList.get(0);
				// loopDataOutputCollection outputDataItem completionCondition
				FeatureMap.Entry dataOutputexpressionEntry = EMFExtensionUtil.getExtensionElementsInEntry(dataOutputentryList.get(0), "expression")
						.get(0);
				String dataOutputexpressionValue = EMFExtensionUtil.getExtensionElementValue(dataOutputexpressionEntry);

				if (dataOutputexpressionValue != null && !dataOutputexpressionValue.equals("")) {

					Object valueObj = ExpressionMgmt.execute(dataOutputexpressionValue, executionContext);

					if (valueObj != null) {

						if (valueObj instanceof Collection) {

							// 如果计算结果为集合时清空数据
							((Collection) valueObj).clear();

						} else {
							throw new FixFlowException("表达式计算结果错误，结果不是一个集合。");
						}
					}
				}
			}

			List<FeatureMap.Entry> entryList = EMFExtensionUtil.getExtensionElements(loopCharacteristics, "LoopDataInputCollection");
			// entryList.get(0);

			FeatureMap.Entry expressionEntry = EMFExtensionUtil.getExtensionElementsInEntry(entryList.get(0), "expression").get(0);
			String expressionValue = EMFExtensionUtil.getExtensionElementValue(expressionEntry);

			if (expressionValue != null && !expressionValue.equals("")) {

				String groupID = GuidUtil.CreateGuid();

				executionContext.setGroupID(groupID);

				Object valueObj = ExpressionMgmt.execute(expressionValue, executionContext);

				if (valueObj != null) {

					if (valueObj instanceof Collection) {
						Collection<?> valueObjCollection = (Collection<?>) valueObj;

						if (valueObjCollection.size() == 0) {
							throw new FixFlowException("多实例输入集合为0,请重新检查输入。");
						}

						for (Object object : valueObjCollection) {

							MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) loopCharacteristics;

							FeatureMap.Entry expressionEntryTemp = EMFExtensionUtil.getExtensionElements(
									multiInstanceLoopCharacteristics.getInputDataItem(), "expression").get(0);

							String expressionValueTemp = EMFExtensionUtil.getExtensionElementValue(expressionEntryTemp);

							ExpressionMgmt.setVariable(expressionValueTemp, object, executionContext);

							this.forkedTokenEnter(executionContext,true);
						}
					} else {
						if (valueObj instanceof String[]) {
							String[] valueObjString = (String[]) valueObj;
							for (int i = 0; i < valueObjString.length; i++) {

								MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) loopCharacteristics;

								FeatureMap.Entry expressionEntryTemp = EMFExtensionUtil.getExtensionElements(
										multiInstanceLoopCharacteristics.getInputDataItem(), "expression").get(0);

								String expressionValueTemp = EMFExtensionUtil.getExtensionElementValue(expressionEntryTemp);

								ExpressionMgmt.setVariable(expressionValueTemp, valueObjString[i], executionContext);

								this.forkedTokenEnter(executionContext,true);

							}
						} else {
							if (valueObj != null && !valueObj.equals("")) {
								String[] valueObjString = valueObj.toString().split(",");

								if (valueObjString.length > 0) {
									for (int i = 0; i < valueObjString.length; i++) {

										MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) loopCharacteristics;

										FeatureMap.Entry expressionEntryTemp = EMFExtensionUtil.getExtensionElements(
												multiInstanceLoopCharacteristics.getInputDataItem(), "expression").get(0);

										String expressionValueTemp = EMFExtensionUtil.getExtensionElementValue(expressionEntryTemp);

										ExpressionMgmt.setVariable(expressionValueTemp, valueObjString[i], executionContext);

										this.forkedTokenEnter(executionContext,true);

									}
								} else {
									throw new FixFlowException("输入数据集格式不满足.");
								}

							} else {
								throw new FixFlowException("输入数据集格式不满足.");
							}
						}
					}

				} else {
					throw new FixFlowException("表达式返回值不是数据集合!");
				}

			} else {
				throw new FixFlowException("并行多实例的输入数据集不能为空!");
			}

		} else {
			//当发现不是多实例的情况下继续节点的执行,以后添加了串行多实例要在这里加判断
			if(loopCharacteristics instanceof StandardLoopCharacteristics){
					//串行多实例执行
				execute(executionContext);
			}else{
				//无多实例执行
				execute(executionContext);
				
			}

		}

	}

	@SuppressWarnings({ "unchecked" })
	public void leave(ExecutionContext executionContext) {

		// 用于并行、串行会签的处理.

		if (this instanceof Activity && ((Activity) this).getLoopCharacteristics() != null) {
			Activity activity = (Activity) this;
			LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();

			if (loopCharacteristics instanceof MultiInstanceLoopCharacteristics) {
				// 并行多实例处理

				List<FeatureMap.Entry> entryList = EMFExtensionUtil.getExtensionElements(loopCharacteristics, "loopDataOutputCollection");

				if (entryList != null && entryList.size() > 0) {
					entryList.get(0);
					// loopDataOutputCollection outputDataItem
					// completionCondition
					FeatureMap.Entry expressionEntry = EMFExtensionUtil.getExtensionElementsInEntry(entryList.get(0), "expression").get(0);
					String expressionValue = EMFExtensionUtil.getExtensionElementValue(expressionEntry);

					if (expressionValue != null && !expressionValue.equals("")) {

						Object valueObj = ExpressionMgmt.execute(expressionValue, executionContext);

						if (valueObj != null) {

							if (valueObj instanceof Collection) {
								MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) loopCharacteristics;

								FeatureMap.Entry expressionEntryTemp = EMFExtensionUtil.getExtensionElements(
										multiInstanceLoopCharacteristics.getOutputDataItem(), "expression").get(0);

								String expressionValueTemp = EMFExtensionUtil.getExtensionElementValue(expressionEntryTemp);

								@SuppressWarnings("rawtypes")
								Collection collection = (Collection) valueObj;
								collection.add(ExpressionMgmt.execute(expressionValueTemp, executionContext));

							}
						}
					}
				} else {

				}

				MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) loopCharacteristics;

				FormalExpression formalExpression = (FormalExpression) multiInstanceLoopCharacteristics.getCompletionCondition();
				if (formalExpression == null || formalExpression.getBody() == null || formalExpression.getBody().equals("")) {

					throw new FixFlowException("多实例完成表达式不能为空.");
				} else {
					String evalue = formalExpression.getBody();
					if (StringUtil.getBoolean(ExpressionMgmt.execute(evalue, executionContext))) {
						super.leave(executionContext);
					}
				}

			} else {
				throw new FixFlowException("串行处理没有实现！");
			}

		}
		// 正常处理
		else {

			super.leave(executionContext);

		}

	}

	/**
	 * 离开节点的时候需要清理的数据. 每个子类需要自己实现.
	 */
	public void leaveClearData(ExecutionContext executionContext) {
		TokenEntity tokenEntity = executionContext.getToken();

		if (this.getBoundaryEventRefs().size() > 0) {

			String parentTokenId = tokenEntity.getParent().getId();
			try {
				Scheduler scheduler = Context.getProcessEngineConfiguration().getSchedulerFactory().getScheduler();
				scheduler.deleteJob(JobKey.jobKey(tokenEntity.getParent().getId(), "FixTimeOutTask_" + parentTokenId));

			} catch (SchedulerException e) {
				e.printStackTrace();
				throw new FixFlowException("流程在离开节点 " + this.getId() + " 的时候发生错误! 错误信息: " + e.toString(), e);
			}

		}

	}

	public void boundaryEventExecute() {

	}

} // ActivityImpl
