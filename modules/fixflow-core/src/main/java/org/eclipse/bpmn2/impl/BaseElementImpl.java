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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;

import com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.connector.ConnectorInstanceBehavior;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.job.ConnectorTimeJob;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.util.EMFUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.QuartzUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.TaskInstance;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Base Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.bpmn2.impl.BaseElementImpl#getExtensionValues <em>
 * Extension Values</em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.BaseElementImpl#getDocumentation <em>
 * Documentation</em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.BaseElementImpl#getExtensionDefinitions
 * <em>Extension Definitions</em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.BaseElementImpl#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.BaseElementImpl#getAnyAttribute <em>Any
 * Attribute</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BaseElementImpl extends EObjectImpl implements BaseElement {
	/**
	 * The cached value of the '{@link #getExtensionValues()
	 * <em>Extension Values</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getExtensionValues()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtensionAttributeValue> extensionValues;

	/**
	 * The cached value of the '{@link #getDocumentation()
	 * <em>Documentation</em>}' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected EList<Documentation> documentation;

	/**
	 * The cached value of the '{@link #getExtensionDefinitions()
	 * <em>Extension Definitions</em>}' reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getExtensionDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtensionDefinition> extensionDefinitions;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAnyAttribute()
	 * <em>Any Attribute</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAnyAttribute()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap anyAttribute;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected BaseElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bpmn2Package.Literals.BASE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public List<ExtensionAttributeValue> getExtensionValues() {
		if (extensionValues == null) {
			extensionValues = new EObjectContainmentEList<ExtensionAttributeValue>(ExtensionAttributeValue.class, this,
					Bpmn2Package.BASE_ELEMENT__EXTENSION_VALUES);
		}
		return extensionValues;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public List<Documentation> getDocumentation() {
		if (documentation == null) {
			documentation = new EObjectContainmentEList<Documentation>(Documentation.class, this, Bpmn2Package.BASE_ELEMENT__DOCUMENTATION);
		}
		return documentation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public List<ExtensionDefinition> getExtensionDefinitions() {
		if (extensionDefinitions == null) {
			extensionDefinitions = new EObjectResolvingEList<ExtensionDefinition>(ExtensionDefinition.class, this,
					Bpmn2Package.BASE_ELEMENT__EXTENSION_DEFINITIONS);
		}
		return extensionDefinitions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.BASE_ELEMENT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FeatureMap getAnyAttribute() {
		if (anyAttribute == null) {
			anyAttribute = new BasicFeatureMap(this, Bpmn2Package.BASE_ELEMENT__ANY_ATTRIBUTE);
		}
		return anyAttribute;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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

	public void fireEvent(String eventType, ExecutionContext executionContext, TaskInstance taskInstance) {
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

	void executeConnectors(List<ConnectorInstanceBehavior> connectors, ExecutionContext executionContext, boolean isPropagated) {
		if (connectors != null) {

			for (ConnectorInstanceBehavior connector : connectors) {

				if ((!isPropagated)) {

					executeConnector(connector, executionContext);

				}

			}
		}
	}

	public void executeConnector(ConnectorInstanceBehavior connector, ExecutionContext executionContext) {
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
				
				if(connector.getSkipExpression()!=null&&!connector.getSkipExpression().equals("")){
					Object skipExpressionObj=ExpressionMgmt.execute(connector.getSkipExpression(), executionContext);
					if(StringUtil.getBoolean(skipExpressionObj)){
						return ;
					}
				}
				
				if (connector.isTimeExecute()) {
					// 定时器执行方式
					timeExecute(executionContext, connector);
				} else {
					// 直接执行方式
					connector.execute(executionContext);
				}

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

	@SuppressWarnings("unchecked")
	private boolean timeExecute(ExecutionContext executionContext, ConnectorInstanceBehavior connectorDefinition) {

		String expressionText = connectorDefinition.getTimeExpression();

		if (expressionText == null) {
			throw new FixFlowException("时间表达式不能为空");
		}
		
		List<Trigger> triggersList=new ArrayList<Trigger>();
		Object triggerListObj = ExpressionMgmt.execute(expressionText, executionContext);
		if(triggerListObj instanceof List){
			try {
				triggersList=(List<Trigger>)triggerListObj;
			} catch (Exception e) {
				throw new FixFlowException("定时连接器的触发器集合必须为List<Trigger>");
			}
			
		}
		else{
			if(triggerListObj instanceof Trigger){
				triggersList.add((Trigger)triggerListObj);
			}else{
				throw new FixFlowException("定时连接器的触发器集合必须为List<Trigger>");
			}
		}
	
		/*
		Date date = null;
		String expressionTemp = null;// "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发
		List<Object> timeObjects = new ArrayList<Object>();
		try {
			Object dateObj = ExpressionMgmt.execute(expressionText, executionContext);
			if (dateObj instanceof Date) {
				date = (Date) dateObj;
			} else {
				if (dateObj instanceof List) {
					timeObjects = (List<Object>) dateObj;
				} else {
					expressionTemp = StringUtil.getString(dateObj);
				}

			}

		} catch (Exception e) {
			throw new FixFlowException("超时表达式计算失败!原因是: " + e.toString(), e);
		}*/

		TokenEntity tokenEntity = executionContext.getToken();
		String processInstanceId = tokenEntity.getProcessInstance().getId();
		// VariableTransferEntity variableTransferEntity = new
		// VariableTransferEntity();
		// Map<String, Object>
		// transientVariableMap=tokenEntity.getProcessInstance().getContextInstance().getTransientVariableMap();
		// String guidString=GuidUtil.CreateGuid();
		// variableTransferEntity.addVariable(guidString, transientVariableMap);

		// if (processInstanceId != null && !processInstanceId.equals("")) {
		// VariableFlowTypeEntity variableFlowTypeEntity = new
		// VariableFlowTypeEntity(VariableFlowType.PROCESSINSTANCE,
		// processInstanceId);
		// variableTransferEntity.addVariableFlowType(variableFlowTypeEntity);
		// }

		// Context.getCommandContext().getVariableManager().saveVariable(variableTransferEntity);

		Scheduler scheduler = Context.getProcessEngineConfiguration().getScheduler();

		Map<JobDetail, List<Trigger>> jobList = new HashMap<JobDetail, List<Trigger>>();

		String processId= tokenEntity.getProcessInstance().getProcessDefinitionId();
		JobDetail job = QuartzUtil.createJobDetail(ConnectorTimeJob.class, GuidUtil.CreateGuid()+"_"+tokenEntity.getId(), tokenEntity.getId()+"_"+processInstanceId+"_"+processId);
		job.getJobDataMap().put("tokenId", tokenEntity.getId());
		
		
		// job.getJobDataMap().put("transientVariableId", guidString);
		job.getJobDataMap().put("processInstanceId", processInstanceId);
		job.getJobDataMap().put("nodeId", this.getId());
		job.getJobDataMap().put("processKey", tokenEntity.getProcessInstance().getProcessDefinitionKey());
		job.getJobDataMap().put("processId", tokenEntity.getProcessInstance().getProcessDefinitionId());
		job.getJobDataMap().put("processName", tokenEntity.getProcessInstance().getProcessDefinition().getName());
		job.getJobDataMap().put("bizKey", tokenEntity.getProcessInstance().getBizKey());
		job.getJobDataMap().put("jobType", "connectorJob");
		job.getJobDataMap().put("connectorId", connectorDefinition.getConnectorId());//连接器编号
		job.getJobDataMap().put("connectorInstanceId", connectorDefinition.getConnectorInstanceId());//连接器实例编号
		job.getJobDataMap().put("connectorInstanceName", connectorDefinition.getConnectorInstanceName());//连接器实例名称
		job.getJobDataMap().put("eventType", connectorDefinition.getEventType());//触发事件类型
		if(executionContext.getTaskInstance()!=null){
			job.getJobDataMap().put("taskId", executionContext.getTaskInstance().getId());
		}
		

		if(triggersList.size()==0){
			throw new FixFlowException("定时连接器的触发器集合不能为空");
		}
		
		jobList.put(job, triggersList);
		/*
		if (date == null) {

			if (expressionTemp != null && !expressionTemp.equals("")) {
				Trigger trigger = null;
				trigger = QuartzUtil.createCronTrigger(GuidUtil.CreateGuid(), processKey, expressionTemp);

				List<Trigger> triggers = new ArrayList<Trigger>();
				triggers.add(trigger);
				jobList.put(job, triggers);

			} else {

				if (timeObjects.size() > 0) {

					List<Trigger> triggers = new ArrayList<Trigger>();
					for (Object object : timeObjects) {
						if (object instanceof Date) {
							Trigger trigger = null;

							trigger = (SimpleTrigger) QuartzUtil.createSimpleTrigger(GuidUtil.CreateGuid(), processKey,
									StringUtil.getDate(object));

							triggers.add(trigger);

						}
						if (object instanceof String) {
							Trigger trigger = null;
							trigger = QuartzUtil.createCronTrigger(GuidUtil.CreateGuid(), processKey, StringUtil.getString(object));
							triggers.add(trigger);
						}
					}
					jobList.put(job, triggers);
				} else {
					throw new FixFlowBizException("定时连接器没有设置时间!");
				}

			}

		} else {

			Trigger trigger = null;

			trigger = (SimpleTrigger) QuartzUtil.createSimpleTrigger(GuidUtil.CreateGuid(), processKey, date);
			List<Trigger> triggers = new ArrayList<Trigger>();
			triggers.add(trigger);
			jobList.put(job, triggers);
		}*/

		// QuartzUtil.createCronTrigger(jobName, groupName, cronExpression);

		try {
			scheduler.scheduleJobs(jobList, true);// .scheduleJob(job, trigger);
		} catch (Exception e) {
			throw new FixFlowException("定时连接器启动失败!错误信息: " + e.toString(), e);
		}

		return false;
	}
	
	protected List<ConnectorInstance> connectorInstances;

	/**
	 * 获取对象的连接器
	 * @param baseElement
	 * @return
	 */
	public  List<ConnectorInstance> getConnectorInstances() {

		
		if(connectorInstances==null){
			
			
			this.connectorInstances=  EMFUtil.getExtensionElementList(ConnectorInstance.class,this,FixFlowPackage.Literals.DOCUMENT_ROOT__CONNECTOR_INSTANCE);
			
		}
		
		

		return this.connectorInstances;
	}

} // BaseElementImpl
