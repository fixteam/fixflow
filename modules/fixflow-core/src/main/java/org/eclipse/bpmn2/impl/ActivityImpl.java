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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.ExtensionAttributeValue;
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
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.util.InternalEList;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.LoopDataInputCollection;
import com.founder.fix.bpmn2extensions.fixflow.LoopDataOutputCollection;
import com.founder.fix.bpmn2extensions.fixflow.SkipStrategy;
import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.exception.FixFlowExpressionException;
import com.founder.fix.fixflow.core.exception.FixFlowScheduleException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.util.EMFUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.internationalization.ExceptionCode;
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
	
	private static Logger LOG = LoggerFactory.getLogger(ActivityImpl.class);
	
	
	protected SkipStrategy skipStrategy;

	/**
	 * 获取节点的跳过策略
	 * 
	 * @return
	 */
	public SkipStrategy getSkipStrategy() {
		

		
		if(skipStrategy==null){

			this.skipStrategy  =EMFUtil.getExtensionElementOne(SkipStrategy.class,this,FixFlowPackage.Literals.DOCUMENT_ROOT__SKIP_STRATEGY);
	
		}
		
		
		return skipStrategy;
	}

	/**
	 * 重载的节点进去事件
	 * @param executionContext 流程上下文
	 */
	public void enter(ExecutionContext executionContext) {
		
		//事件顺序如下,先判断跳过策略,再判断边界事件,
		//再执行多实例,最后执行节点进入事件
		TokenEntity token = executionContext.getToken();
		
		LOG.debug("进入节点: {}({}),令牌号: {}({}).", this.getName(),this.getId(),token.getName(),token.getId());

		// 把令牌的所在节点设置为当前节点
		token.setFlowNode(this);

		// 这里先不触发事件,在每个多实例进入的时候再触发。
		// fireEvent(BaseElementEvent.EVENTTYPE_NODE_ENTER, executionContext);

		// 设置令牌进入节点的时间
		token.setNodeEnterTime(new Date());

		// 移除临时执行内容对象
		executionContext.clearExecutionContextData();


		//获取跳过策略设置
		SkipStrategy skipStrategy = getSkipStrategy();


		if (skipStrategy != null) {

			boolean isEnable = skipStrategy.isIsEnable();

			// 判断是否启用
			if (isEnable) {
				//启用跳过策略处理的情况
				
				LOG.debug("节点: {}({}) 启用跳过策略.", this.getName(),this.getId());
				
				boolean valueObj = false;
				
				//处理跳过策略表达式
				if (skipStrategy.getExpression() != null) {
					
					String expressionValue = skipStrategy.getExpression().getValue();
					
					if (expressionValue != null && !expressionValue.equals("")) {
						try {
							
							LOG.debug("节点: {}({}) 跳过策略开始直接,跳过策略表达式内容为:\n {}", this.getName(),this.getId(),expressionValue);
							
							//执行验证表达式
							valueObj = StringUtil.getBoolean(ExpressionMgmt.execute(expressionValue, executionContext));
							
							LOG.debug("节点: {}({}) 跳过策略直接结束,结果为 '{}'.", this.getName(),this.getId(),valueObj);
							
						} catch (Exception e) {
							
							LOG.error("节点: "+this.getName()+"("+this.getId()+") 跳过策略执行出错,错误信息: 【"+e.getMessage()+"】.", e);
							
							throw new FixFlowExpressionException(ExceptionCode.EXPRESSION_EXCEPTION_SKIPSTRATEGY,this.getId(),this.getName(),expressionValue,e);
							
						}
					}
				}

				if (valueObj) {

					// 直接跳过
					boolean isCreateSkipProcess = skipStrategy.isIsCreateSkipProcess();

					if (isCreateSkipProcess) {
							
						executionContext.setSkipStrategy(skipStrategy);
						
						skipExecute(executionContext);
						
					}
					
					LOG.debug("跳过策略生效,跳过节点: {}({}).", this.getName(),this.getId());
					//节点离开
					super.leave(executionContext);
					
					return;
				}
				else{
					LOG.debug("节点: {}({}),跳过策略未生效.", this.getName(),this.getId());
				}

			}

		}
		
		//跳过策略未生效,继续执行节点进入
		eventExecute(executionContext);
		
		

	}
	
	private void tokenEnter(ExecutionContext executionContext) {
		

		
		fireEvent(BaseElementEvent.EVENTTYPE_NODE_ENTER, executionContext);

		execute(executionContext);

	}
	

	private void forkedTokenEnter(ExecutionContext executionContext) {
		TokenEntity token = executionContext.getToken();

		// 把令牌的所在节点设置为当前节点
		token.setFlowNode(this);

		//这里先不触发事件,在每个多实例进入的时候再触发
		//fireEvent(BaseElementEvent.EVENTTYPE_NODE_ENTER, executionContext);

		// 设置令牌进入节点的时间
		token.setNodeEnterTime(new Date());

		// 移除执行内容对象的线条关联
		executionContext.setSequenceFlow(null);

		executionContext.setSequenceFlowSource(null);

		
		loopExecute(executionContext);
		
		
		/*
		if (this instanceof Activity && ((Activity) this).getLoopCharacteristics() != null) {

			loopExecute(executionContext);

		} else {

			
		}*/
	}

	protected void eventExecute(ExecutionContext executionContext) {

		//验证是否含有边界事件
		if (this.getBoundaryEventRefs().size() > 0) {
			
			/*
		    边界事件的处理逻辑是这样的,
			如果有边界事件,主令牌创建一个儿子,进入到当前节点,
			主令牌自己去执行边界事件的执行方法,
			当定时到的时候如果是中断边界,则直接杀掉主令牌所有的儿子,将主令牌推下去
			如果是非中断,则主令牌再生成个儿子给边界事件继续向下走,最后需要并行合并网关将他们收回。
			*/
			
			TokenEntity tokenEntity = executionContext.getToken();

			
			
			
			List<BoundaryEvent> boundaryEvents = this.getBoundaryEventRefs();
			
			LOG.debug("节点: {}({}) 含有 {} 个边界事件,令牌号: {}({}).", this.getName(),this.getId(),boundaryEvents.size(),tokenEntity.getName(),tokenEntity.getId());

			String nodeTokenId = this.getId();
			// 创建分支令牌并添加到集合中
			TokenEntity nodeToken = this.createForkedToken(tokenEntity, nodeTokenId).token;
			
			LOG.debug("主令牌创建子令牌,子令牌: {}({}).",tokenEntity.getName(),tokenEntity.getId());
			//创建一个子令牌
			ExecutionContext nodeChildExecutionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(nodeToken);

			LOG.debug("子令牌: {}({}),进入节点.",tokenEntity.getName(),tokenEntity.getId());
			//将子流程放入节点
			this.forkedTokenEnter(nodeChildExecutionContext);

			// 遍历边界事件
			for (BoundaryEvent boundaryEvent : boundaryEvents) {

				LOG.debug("主令牌: {}({}),触发边界事件: {} 执行.",tokenEntity.getName(),tokenEntity.getId(),boundaryEvent.getId());
				//主令牌执行边界事件里的事件定义
				boundaryEvent.execute(executionContext);
				
			}

		} else {
			//没有边界事件,则执行多实例执行
			loopExecute(executionContext);
		}

	}

	protected void skipExecute(ExecutionContext executionContext) {
		// debugLog.debug("节点 "+getId()+" 执行跳过处理!");
	}

	protected void loopExecute(ExecutionContext executionContext) {
		
		Activity activity = (Activity) this;
		
		TokenEntity token=executionContext.getToken();
		
		//获取 Activity 的多实例信息
		LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();
		
		//判断事都是并行多实例
		if (loopCharacteristics instanceof MultiInstanceLoopCharacteristics) {
			
			// 并行多实例处理
			LOG.debug("节点: {}({}) 含有并行多实例,将进入多实例'进入'阶段处理,令牌号: {}({}).",activity.getName(),activity.getId(),token.getName(),token.getId());
			
			// 输出数据集
			String loopDataOutputCollectionExpressionValue = getLoopDataOutputCollectionExpression();
			
			// 多实例输入数据集
			String loopDataInputCollectionExpressionValue = getLoopDataInputCollectionExpression();
			
			// 多实例输入项
			String inputDataItemExpressionValue = getInputDataItemExpression();
			
			// 多实例输出项
			String outputDataItemExpressionValue = getOutputDataItemExpression();
			
			//完成条件
			String completionConditionExpressionValue=getCompletionConditionExpression();
			
			
			//打印日志信息
			LOG.debug("\n多实例配置信息: \n 【输入数据集】: \n{}",loopDataInputCollectionExpressionValue);
			
			LOG.debug("\n【输入项编号】: \n{}",inputDataItemExpressionValue);
			
			LOG.debug("\n【输出项编号】: \n{}",outputDataItemExpressionValue);	
			
			LOG.debug("\n【输出数据集】: \n{}",outputDataItemExpressionValue);	
			
			LOG.debug("\n【完成条件】: \n{}",completionConditionExpressionValue);	
			
		
			
			// 解决多实例处理退回BUG
			// 在进入多实例的第一次先清空多实例输出集合,以防历史数据影响。
			if (loopDataOutputCollectionExpressionValue != null && !loopDataOutputCollectionExpressionValue.equals("")) {

				Object valueObj = ExpressionMgmt.execute(loopDataOutputCollectionExpressionValue, executionContext);

				if (valueObj != null) {

					if (valueObj instanceof Collection) {
						
						LOG.debug("清空多实例输出集合");
						
						// 如果计算结果为集合时清空数据
						((Collection<?>) valueObj).clear();

					} else {
						
						LOG.error("多实例输出集合不是一个集合");
						
						throw new FixFlowExpressionException(ExceptionCode.EXPRESSION_EXCEPTION_LOOPDATAOUTPUTCOLLECTION_COLLECTIONCHECK,this.getId(),this.getName(),loopDataOutputCollectionExpressionValue);

					}
				}
			}
			

			
			//开始触发多实例循环

			if (loopDataInputCollectionExpressionValue != null && !loopDataInputCollectionExpressionValue.equals("")) {
				
				//生成一个唯一组号,用户多实例任务组的标识
				String groupID = GuidUtil.CreateGuid();
				
				//给流程上下文设置唯一组号,好在多实例每次进入节点的时候将唯一组号传递给每个任务创建方法。
				executionContext.setGroupID(groupID);
				
				LOG.debug("为多实例生成唯一组号: '{}'", groupID);
				
				//执行多实例 输入数据集 解释
				Object valueObj =null;
				try {
					
					valueObj = ExpressionMgmt.execute(loopDataInputCollectionExpressionValue, executionContext);

				} catch (Exception e) {
					
					LOG.error("多实例输入数据集解释出错,错误信息: "+e.getMessage(), e);
					
					throw new FixFlowExpressionException(ExceptionCode.EXPRESSION_EXCEPTION_LOOPDATAOUTPUTCOLLECTION_COLLECTIONCHECK,this.getId(),this.getName(),loopDataInputCollectionExpressionValue,e);

				}
				
				
				if (valueObj != null) {
					
					//如果会签输入数据集是一个集合
					
					if (valueObj instanceof Collection) {
						
						Collection<?> valueObjCollection = (Collection<?>) valueObj;

						if (valueObjCollection.size() == 0) {
							
							LOG.error("多实例输入集合的个数为 0,请重新检查多实例输入集合配置.");
							
							throw new FixFlowExpressionException(ExceptionCode.EXPRESSION_EXCEPTION_LOOPDATAINPUTCOLLECTIONEMPTY,this.getId(),this.getName(),loopDataInputCollectionExpressionValue);
							
						}
						//定义计数器
						int i=1;
						//循环执行 将令牌多次放入节点
						for (Object object : valueObjCollection) {
							
							
							
							LOG.debug("多实例循环第 '{}' 次开始执行,循环值为: '{}'",i,StringUtil.getString(object));

							try {
								//将循环的每个变量赋值给输入数据项
								ExpressionMgmt.setVariable(inputDataItemExpressionValue, object, executionContext);
								
							} catch (Exception e) {
								
								LOG.error("多实例循环第 '"+i+"' 次执行出错,错误信息: "+e.getMessage(),e);
								
								throw new FixFlowExpressionException(ExceptionCode.EXPRESSIONEXCEPTION_COLLECTIONININPUTDATAITEM,this.getId(),this.getName(),inputDataItemExpressionValue,e);
								
								
							}
							
							
							//执行令牌进入节点方法
							this.tokenEnter(executionContext);
							
							//循环计数器自加1
							i=i+1;
							
						}
						
					//如果会签输入数据集不是集合
					} else {
						
						if (valueObj instanceof String[]) {
							
							//如果是个字符串数组
							String[] valueObjString = (String[]) valueObj;
							
							//循环执行 将令牌多次放入节点
							for (int i = 0; i < valueObjString.length; i++) {
								
								LOG.debug("多实例循环第 '{}' 次开始执行,循环值为: '{}'",i,valueObjString[i]);

								try {
									//将循环的每个变量赋值给输入数据项
									ExpressionMgmt.setVariable(inputDataItemExpressionValue, valueObjString[i],executionContext);
									
								} catch (Exception e) {
									
									LOG.error("多实例循环第 '"+i+"' 次执行出错,错误信息: "+e.getMessage(),e);
									
									throw new FixFlowExpressionException(ExceptionCode.EXPRESSIONEXCEPTION_COLLECTIONININPUTDATAITEM,this.getId(),this.getName(),inputDataItemExpressionValue,e);
									
									
								}
								
								//执行令牌进入节点方法
								this.tokenEnter(executionContext);

							}
							
							
							
						} else {
							
							//如果是一个逗号分割的字符串
							
							if (valueObj != null && !valueObj.equals("")) {
								
								//将字符串转换为字符串数组
								
								String[] valueObjString = valueObj.toString().split(",");
								
								//如果大于0再处理
								if (valueObjString.length > 0) {
									for (int i = 0; i < valueObjString.length; i++) {
										
										LOG.debug("多实例循环第 '{}' 次开始执行,循环值为: '{}'", i, valueObjString[i]);
										try {
											// 将循环的每个变量赋值给输入数据项
											ExpressionMgmt.setVariable(inputDataItemExpressionValue, valueObjString[i],executionContext);
										} catch (Exception e) {

											LOG.error("多实例循环第 '" + i + "' 次执行出错,错误信息: " + e.getMessage(), e);

											throw new FixFlowExpressionException(
													ExceptionCode.EXPRESSIONEXCEPTION_COLLECTIONININPUTDATAITEM,
													this.getId(), this.getName(), inputDataItemExpressionValue, e);

										}
										
										//执行节点进入
										this.tokenEnter(executionContext);

									}
								} else {
									//不认识的格式
									LOG.error("多实例输出集合不是一个集合");
									
									throw new FixFlowExpressionException(ExceptionCode.EXPRESSION_EXCEPTION_LOOPDATAOUTPUTCOLLECTION_COLLECTIONCHECK,this.getId(),this.getName(),loopDataOutputCollectionExpressionValue);

								}

							} else {
								//不认识的格式
								
								LOG.error("多实例输出集合不是一个集合");
								
								throw new FixFlowExpressionException(ExceptionCode.EXPRESSION_EXCEPTION_LOOPDATAOUTPUTCOLLECTION_COLLECTIONCHECK,this.getId(),this.getName(),loopDataOutputCollectionExpressionValue);

							}
						}
					}

				} else {
					
					LOG.error("多实例输出集合不是一个集合");
					
					throw new FixFlowExpressionException(ExceptionCode.EXPRESSION_EXCEPTION_LOOPDATAOUTPUTCOLLECTION_COLLECTIONCHECK,this.getId(),this.getName(),loopDataOutputCollectionExpressionValue);

				}

			} else {
				
				LOG.error("多实例输出集合不是一个集合");
				
				throw new FixFlowExpressionException(ExceptionCode.EXPRESSION_EXCEPTION_LOOPDATAOUTPUTCOLLECTION_COLLECTIONCHECK,this.getId(),this.getName(),loopDataOutputCollectionExpressionValue);

			}

		} else {
			// 当发现不是多实例的情况下继续节点的执行,以后添加了串行多实例要在这里加判断
			if (loopCharacteristics instanceof StandardLoopCharacteristics) {
				// 串行多实例执行
				tokenEnter(executionContext);
			} else {
				tokenEnter(executionContext);

			}

		}

	}

	@SuppressWarnings({ "unchecked" })
	public void leave(ExecutionContext executionContext) {

		// 用于并行、串行会签的处理.
		
		//在离开节点的时候判断多实例
		if (this instanceof Activity && ((Activity) this).getLoopCharacteristics() != null) {
			
			Activity activity = (Activity) this;
			
			LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();
			
			// 如果是并行多实例
			if (loopCharacteristics instanceof MultiInstanceLoopCharacteristics) {
				// 并行多实例处理
				
				//MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) loopCharacteristics;

				
				TokenEntity token=executionContext.getToken();
				
				LOG.debug("节点: {}({}) 含有并行多实例,将进入多实例'离开'阶段处理,令牌号: {}({}).",activity.getName(),activity.getId(),token.getName(),token.getId());
				
				
				
				// 输出数据集
				String loopDataOutputCollectionExpressionValue = getLoopDataOutputCollectionExpression();
				
				// 多实例输入数据集
				String loopDataInputCollectionExpressionValue = getLoopDataInputCollectionExpression();
				
				// 多实例输入项
				String inputDataItemExpressionValue = getInputDataItemExpression();
				
				// 多实例输出项
				String outputDataItemExpressionValue = getOutputDataItemExpression();
				
				//完成条件
				String completionConditionExpressionValue=getCompletionConditionExpression();
				
				//打印日志信息
				LOG.debug("\n多实例配置信息: \n 【输入数据集】: \n{}",loopDataInputCollectionExpressionValue);
				
				LOG.debug("\n【输入项编号】: \n{}",inputDataItemExpressionValue);
				
				LOG.debug("\n【输出项编号】: \n{}",outputDataItemExpressionValue);	
				
				LOG.debug("\n【输出数据集】: \n{}",outputDataItemExpressionValue);	
				
				LOG.debug("\n【完成条件】: \n{}",completionConditionExpressionValue);	
				

				if (loopDataOutputCollectionExpressionValue != null && !loopDataOutputCollectionExpressionValue.equals("")) {

					Object valueObj = ExpressionMgmt.execute(loopDataOutputCollectionExpressionValue, executionContext);

					if (valueObj != null) {

						if (valueObj instanceof Collection) {

							String expressionValueTemp = getOutputDataItemExpression();

							@SuppressWarnings("rawtypes")
							Collection collection = (Collection) valueObj;
							collection.add(ExpressionMgmt.execute(expressionValueTemp, executionContext));

						} else {

						}
					} else {

					}
				}

				
				
				if (completionConditionExpressionValue == null || completionConditionExpressionValue.equals("")) {

					LOG.error("节点: "+activity.getName()+"("+activity.getId()+") 多实例完成条件为空.");
					
					throw new FixFlowExpressionException(ExceptionCode.EXPRESSIONEXCEPTION_CONDITIONEXPRESSIONEMPTY,this.getId(),this.getName(),"");
					
				} else {
					
					boolean isCompletion=false;
					try {
						
						isCompletion=StringUtil.getBoolean(ExpressionMgmt.execute(completionConditionExpressionValue, executionContext));
						
					} catch (Exception e) {
						
						LOG.error("节点: "+activity.getName()+"("+activity.getId()+") 多实例完成条件计算出错.",e);
						throw new FixFlowExpressionException(ExceptionCode.EXPRESSIONEXCEPTION_CONDITIONEXPRESSIONERROR,this.getId(),this.getName(),"");
						
					}
					
					if (isCompletion){
						
						LOG.debug("节点: {}({}) 多实例完成条件验证通过,令牌号: {}({}).",activity.getName(),activity.getId(),token.getName(),token.getId());
						
						
						super.leave(executionContext);
						
					}else{
						//不做处理
						LOG.debug("节点: {}({}) 多实例完成条件验证不通过,令牌将继续停留在当前借点,令牌号: {}({}).",activity.getName(),activity.getId(),token.getName(),token.getId());
					}
				}

			} else {
				//这里还没实现。。。。囧
				//异常方式也暂时不改了
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
				//Scheduler scheduler = Context.getProcessEngineConfiguration().getSchedulerFactory().getScheduler();
				//scheduler.deleteJob(JobKey.jobKey(tokenEntity.getParent().getId(), "FixTimeOutTask_" + parentTokenId));
				if(StringUtil.getBoolean(Context.getProcessEngineConfiguration().getQuartzConfig().getIsEnable())){
					Scheduler scheduler = Context.getProcessEngineConfiguration().getSchedulerFactory().getScheduler();
					Set<JobKey> jobKeys=new HashSet<JobKey>();
					jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupContains(parentTokenId));
					if(jobKeys.size()>0){
						List<JobKey> jobKeysList=new ArrayList<JobKey>();
						jobKeysList.addAll(jobKeys);
						scheduler.deleteJobs(jobKeysList);
					}
				}
				

			} catch (Exception e) {
				
				LOG.error("节点: "+this.getName()+"("+this.getId()+") 在离开时,清理定时任务数据出错,错误信息: "+e.getMessage(),e);
				
				throw new FixFlowScheduleException(ExceptionCode.QUARZTEXCEPTION_NODELEAVECLEANQUARTZ,this.getId(),this.getName(),e);
				
			}

		}
		
		
		try {
			
			if (StringUtil.getBoolean(Context.getProcessEngineConfiguration().getQuartzConfig().getIsEnable())) {
				Scheduler scheduler = Context.getProcessEngineConfiguration().getSchedulerFactory().getScheduler();
				Set<JobKey> jobKeys = new HashSet<JobKey>();
				jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupContains(tokenEntity.getId()));
				if (jobKeys.size() > 0) {
					List<JobKey> jobKeysList = new ArrayList<JobKey>();
					jobKeysList.addAll(jobKeys);
					scheduler.deleteJobs(jobKeysList);
				}
			}
			
		} catch (Exception e) {
			
			LOG.error("节点: "+this.getName()+"("+this.getId()+") 在离开时,清理定时任务数据出错,错误信息: "+e.getMessage(),e);
			
			throw new FixFlowScheduleException(ExceptionCode.QUARZTEXCEPTION_NODELEAVECLEANQUARTZ,this.getId(),this.getName(),e);
			
			
		}
		
		
		super.leaveClearData(executionContext);
		
	
	}

	public void boundaryEventExecute() {

	}
	
	
	
	public String loopDataInputCollectionExpression;
	
	public String inputDataItemExpression;
	
	public String loopDataOutputCollectionExpression;
	
	public String outputDataItemExpression;
	
	public String completionConditionExpression;
	
	
	public String getLoopDataInputCollectionExpression() {
		
		
		if(this.loopDataInputCollectionExpression==null){
			
			LoopDataInputCollection loopDataInputCollection  =EMFUtil.getExtensionElementOne(LoopDataInputCollection.class,loopCharacteristics,FixFlowPackage.Literals.DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION);
			
			if(loopDataInputCollection!=null){
				this.loopDataInputCollectionExpression=loopDataInputCollection.getExpression()!=null?loopDataInputCollection.getExpression().getValue():null;
				

			}
		}
		
		return loopDataInputCollectionExpression;
		
		
	}

	public String getInputDataItemExpression() {
		
		
		
		if(this.inputDataItemExpression==null){
			MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics=(MultiInstanceLoopCharacteristics)loopCharacteristics;
			DataInput dataInput =multiInstanceLoopCharacteristics.getInputDataItem();
			Expression expression=getExtensionExpression(dataInput);
			if(expression!=null){
				this.inputDataItemExpression=expression.getValue();
			}

		}
		return inputDataItemExpression;
	}

	public String getLoopDataOutputCollectionExpression() {
		
		
		
		
		if(this.loopDataOutputCollectionExpression==null){
			
				LoopDataOutputCollection loopDataOutputCollection  =EMFUtil.getExtensionElementOne(LoopDataOutputCollection.class,loopCharacteristics,FixFlowPackage.Literals.DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION);
				if(loopDataOutputCollection!=null){
					this.loopDataOutputCollectionExpression=loopDataOutputCollection.getExpression()!=null?loopDataOutputCollection.getExpression().getValue():null;
					
				}
				
			
		}
		
		return loopDataOutputCollectionExpression;
	}

	public String getOutputDataItemExpression() {
		
		if(this.outputDataItemExpression==null){
			MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics=(MultiInstanceLoopCharacteristics)loopCharacteristics;
			DataOutput dataOutput =multiInstanceLoopCharacteristics.getOutputDataItem();
			Expression expression=getExtensionExpression(dataOutput);
			if(expression!=null){
				this.outputDataItemExpression=expression.getValue();
			}

		}
		return outputDataItemExpression;
	
	}

	public String getCompletionConditionExpression() {

		if (this.completionConditionExpression == null) {
			MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) loopCharacteristics;

			FormalExpression completionConditionExpression = (FormalExpression) multiInstanceLoopCharacteristics
					.getCompletionCondition();
			if (completionConditionExpression != null) {
				String evalue = completionConditionExpression.getBody();
				this.completionConditionExpression = evalue;
			}

		}

		return this.completionConditionExpression;
	}

	private Expression getExtensionExpression(BaseElement baseElement) {

		if (baseElement.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof Expression) {
						Expression expression= (Expression) entry.getValue();
						return expression;

					}

				}

			}

		}
		return null;

	}

} // ActivityImpl
