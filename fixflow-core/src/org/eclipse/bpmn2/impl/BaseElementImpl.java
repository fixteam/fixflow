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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.ExtensionDefinition;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.connector.ConnectorDefinition;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.TaskInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Base Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.BaseElementImpl#getExtensionValues <em>Extension Values</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.BaseElementImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.BaseElementImpl#getExtensionDefinitions <em>Extension Definitions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.BaseElementImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.BaseElementImpl#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BaseElementImpl extends EObjectImpl implements BaseElement {
    /**
     * The cached value of the '{@link #getExtensionValues() <em>Extension Values</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtensionValues()
     * @generated
     * @ordered
     */
    protected EList<ExtensionAttributeValue> extensionValues;

    /**
     * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected EList<Documentation> documentation;

    /**
     * The cached value of the '{@link #getExtensionDefinitions() <em>Extension Definitions</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtensionDefinitions()
     * @generated
     * @ordered
     */
    protected EList<ExtensionDefinition> extensionDefinitions;

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
     * The cached value of the '{@link #getAnyAttribute() <em>Any Attribute</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAnyAttribute()
     * @generated
     * @ordered
     */
    protected FeatureMap anyAttribute;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BaseElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.BASE_ELEMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<ExtensionAttributeValue> getExtensionValues() {
        if (extensionValues == null) {
            extensionValues = new EObjectContainmentEList<ExtensionAttributeValue>(
                    ExtensionAttributeValue.class, this,
                    Bpmn2Package.BASE_ELEMENT__EXTENSION_VALUES);
        }
        return extensionValues;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<Documentation> getDocumentation() {
        if (documentation == null) {
            documentation = new EObjectContainmentEList<Documentation>(Documentation.class, this,
                    Bpmn2Package.BASE_ELEMENT__DOCUMENTATION);
        }
        return documentation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<ExtensionDefinition> getExtensionDefinitions() {
        if (extensionDefinitions == null) {
            extensionDefinitions = new EObjectResolvingEList<ExtensionDefinition>(
                    ExtensionDefinition.class, this,
                    Bpmn2Package.BASE_ELEMENT__EXTENSION_DEFINITIONS);
        }
        return extensionDefinitions;
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
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.BASE_ELEMENT__ID,
                    oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getAnyAttribute() {
        if (anyAttribute == null) {
            anyAttribute = new BasicFeatureMap(this, Bpmn2Package.BASE_ELEMENT__ANY_ATTRIBUTE);
        }
        return anyAttribute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
            NotificationChain msgs) {
        switch (featureID) {
        case Bpmn2Package.BASE_ELEMENT__EXTENSION_VALUES:
            return ((InternalEList<?>) getExtensionValues()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.BASE_ELEMENT__DOCUMENTATION:
            return ((InternalEList<?>) getDocumentation()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.BASE_ELEMENT__ANY_ATTRIBUTE:
            return ((InternalEList<?>) getAnyAttribute()).basicRemove(otherEnd, msgs);
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
        case Bpmn2Package.BASE_ELEMENT__EXTENSION_VALUES:
            return getExtensionValues();
        case Bpmn2Package.BASE_ELEMENT__DOCUMENTATION:
            return getDocumentation();
        case Bpmn2Package.BASE_ELEMENT__EXTENSION_DEFINITIONS:
            return getExtensionDefinitions();
        case Bpmn2Package.BASE_ELEMENT__ID:
            return getId();
        case Bpmn2Package.BASE_ELEMENT__ANY_ATTRIBUTE:
            if (coreType)
                return getAnyAttribute();
            return ((FeatureMap.Internal) getAnyAttribute()).getWrapper();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case Bpmn2Package.BASE_ELEMENT__EXTENSION_VALUES:
            getExtensionValues().clear();
            getExtensionValues().addAll((Collection<? extends ExtensionAttributeValue>) newValue);
            return;
        case Bpmn2Package.BASE_ELEMENT__DOCUMENTATION:
            getDocumentation().clear();
            getDocumentation().addAll((Collection<? extends Documentation>) newValue);
            return;
        case Bpmn2Package.BASE_ELEMENT__EXTENSION_DEFINITIONS:
            getExtensionDefinitions().clear();
            getExtensionDefinitions().addAll((Collection<? extends ExtensionDefinition>) newValue);
            return;
        case Bpmn2Package.BASE_ELEMENT__ID:
            setId((String) newValue);
            return;
        case Bpmn2Package.BASE_ELEMENT__ANY_ATTRIBUTE:
            ((FeatureMap.Internal) getAnyAttribute()).set(newValue);
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
        case Bpmn2Package.BASE_ELEMENT__EXTENSION_VALUES:
            getExtensionValues().clear();
            return;
        case Bpmn2Package.BASE_ELEMENT__DOCUMENTATION:
            getDocumentation().clear();
            return;
        case Bpmn2Package.BASE_ELEMENT__EXTENSION_DEFINITIONS:
            getExtensionDefinitions().clear();
            return;
        case Bpmn2Package.BASE_ELEMENT__ID:
            setId(ID_EDEFAULT);
            return;
        case Bpmn2Package.BASE_ELEMENT__ANY_ATTRIBUTE:
            getAnyAttribute().clear();
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
        case Bpmn2Package.BASE_ELEMENT__EXTENSION_VALUES:
            return extensionValues != null && !extensionValues.isEmpty();
        case Bpmn2Package.BASE_ELEMENT__DOCUMENTATION:
            return documentation != null && !documentation.isEmpty();
        case Bpmn2Package.BASE_ELEMENT__EXTENSION_DEFINITIONS:
            return extensionDefinitions != null && !extensionDefinitions.isEmpty();
        case Bpmn2Package.BASE_ELEMENT__ID:
            return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
        case Bpmn2Package.BASE_ELEMENT__ANY_ATTRIBUTE:
            return anyAttribute != null && !anyAttribute.isEmpty();
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (id: ");
        result.append(id);
        result.append(", anyAttribute: ");
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
protected Map<String, BaseElementEvent> events = null;
	
	/* 节点事件 */

	public Map<String, BaseElementEvent> getEvents() {

		if (this.events == null) {
			this.events = new HashMap<String, BaseElementEvent>();
		}

		return events;
	}

	public boolean hasEvents() {
		return ((events != null) && (events.size() > 0));
	}

	public BaseElementEvent getEvent(String eventType) {
		BaseElementEvent event = null;
		if (events != null) {
			event = (BaseElementEvent) events.get(eventType);
		}
		return event;
	}

	public boolean hasEvent(String eventType) {
		boolean hasEvent = false;
		if (events != null) {
			hasEvent = events.containsKey(eventType);
		}
		return hasEvent;
	}


	
	public void fireEvent(String eventType, ExecutionContext executionContext) {
		// Token token = executionContext.getToken();

		// log.debug( "event '"+eventType+"' on '"+this+"' for '"+token+"'" );

		try {
			executionContext.setEventSource(this);
			fireAndPropagateEvent(eventType, executionContext);
		} finally {
			executionContext.setEventSource(null);
		}
	}
	
	
	public void fireEvent(String eventType, ExecutionContext executionContext,TaskInstance taskInstance) {
		// Token token = executionContext.getToken();

		// log.debug( "event '"+eventType+"' on '"+this+"' for '"+token+"'" );

		try {
			executionContext.setTaskInstance(taskInstance);
			executionContext.setEventSource(this);
			fireAndPropagateEvent(eventType, executionContext);
		} finally {
			executionContext.setEventSource(null);
			executionContext.setTaskInstance(null);
		}
	}
	
	


	public void fireAndPropagateEvent(String eventType, ExecutionContext executionContext) {
		// calculate if the event was fired on this element or if it was a
		// propagated event
		boolean isPropagated = false;// (this.getId().equals(executionContext.getEventSource().getId()));

		// execute static actions
		BaseElementEvent event = getEvent(eventType);
		if (event != null) {
			// update the context
			executionContext.setBaseElementEvent(event);
			// execute the static actions specified in the process definition
			executeConnectors(event.getConnectors(), executionContext, isPropagated);
		}

		// execute the runtime actions
		// List<ActionHandler> runtimeActions =
		// getRuntimeActionsForEvent(executionContext, eventType);
		// executeActions(runtimeActions, executionContext, isPropagated);

		// remove the event from the context
		executionContext.setBaseElementEvent(null);

		// propagate the event to the parent element
		// GraphElement parent = getParent();
		// if (parent != null) {
		// parent.fireAndPropagateEvent(eventType, executionContext);
		// }
	}

	void executeConnectors(List<ConnectorDefinition> connectors, ExecutionContext executionContext, boolean isPropagated) {
		if (connectors != null) {

			for (ConnectorDefinition connector : connectors) {

				if ((!isPropagated)) {

					executeConnector(connector, executionContext);

				}

			}
		}
	}

	public void executeConnector(ConnectorDefinition connector, ExecutionContext executionContext) {
		TokenEntity token = executionContext.getToken();

		// create action log
		// ActionLog actionLog = new ActionLog(action);
		// token.startCompositeLog(actionLog);

		try {
			// update the execution context
			executionContext.setConnector(connector);

			// execute the action
			// log.debug("executing action '"+action+"'");
			try {
				token.lock();

				connector.execute(executionContext);

			} finally {
				token.unlock();
			}

		} catch (Exception exception) {
			// NOTE that Error's are not caught because that might halt the JVM
			// and mask the original Error
			// log.error("action threw exception: "+exception.getMessage(),
			// exception);

			throw new FixFlowException(exception.getMessage(), exception);

			// log the action exception
			// actionLog.setException(exception);

			// if an exception handler is available
			// raiseException(exception, executionContext);

		} finally {
			executionContext.setConnector(null);
			// token.endCompositeLog();
		}
	}

	public BaseElementEvent addEvent(BaseElementEvent event) {
		if (event == null)
			throw new FixFlowException("不能将一个null的事件添加到flowNode里");
		if (event.getEventType() == null)
			throw new FixFlowException("不能将一个事件类型为空的事件添加到flowNode里");
		if (events == null)
			events = new HashMap<String, BaseElementEvent>();
		events.put(event.getEventType(), event);
		event.setBaseElement(this);
		return event;
	}

	public BaseElementEvent removeEvent(BaseElementEvent event) {
		BaseElementEvent removedEvent = null;
		if (event == null)
			throw new FixFlowException("不能将一个null的事件从flowNode里移除");
		if (event.getEventType() == null)
			throw new FixFlowException("不能将一个事件类型为空的事件从flowNode里移除");
		if (events != null) {
			removedEvent = (BaseElementEvent) events.remove(event.getEventType());
			if (removedEvent != null) {
				event.setBaseElement(null);
			}
		}
		return removedEvent;
	}
	
	
    
    
    
    
    
    
    
    

} //BaseElementImpl
