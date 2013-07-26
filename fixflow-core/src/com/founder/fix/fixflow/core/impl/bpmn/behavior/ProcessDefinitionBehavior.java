package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.impl.ProcessImpl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.FormUri;
import com.founder.fix.bpmn2extensions.fixflow.TaskSubject;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableMgmtDefinition;
import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.EMFUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class ProcessDefinitionBehavior extends ProcessImpl implements PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2351798875564943808L;

	/**
	 * '{@link #isIsExecutable() <em>Version</em>}' 字段的默认值. <!-- 开始-用户-文档 -->
	 * <!-- 结束-用户-文档 -->
	 * 
	 * @see #isIsExecutable()
	 * @generated
	 * @ordered
	 */
	protected static final int VERSION_DEFAULT = 1;

	/**
	 * 该值缓存 '{@link #getVersion() <em>Version</em>}' 字段. <!-- 开始-用户-文档 -->
	 * <p>
	 * 流程版本定义,默认值为 1. 每当 {@link #getVersion() <em>流程定义版本号 Version</em>} 增加 1,
	 * {@link #com.founder.fix.fixflow.core.Definitions.getVersion()
	 * <em>业务定义版本号 Version</em>} 也同时增加 1.
	 * </p>
	 * <!-- 结束-用户-文档 -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected int version = VERSION_DEFAULT;

	/**
	 * 返回 '<em><b>Version</b></em>' 字段. <!-- 开始-用户-文档 -->
	 * <p>
	 * 返回流程定义版本号
	 * </p>
	 * <!-- 结束-用户-文档 -->
	 * 
	 * @return 该值为 '<em>Version</em>' 字段.
	 * @see #setVersion(int)
	 * @generated
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * <!-- 开始-用户-文档 --> 设置流程定义本版号 <!-- 结束-用户-文档 -->
	 * 
	 * @param version
	 *            版本号
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * <!-- 开始-用户-文档 --> 增加流程版本号 <!-- 结束-用户-文档 -->
	 */
	public void addVersion() {
		this.version = this.version + 1;
	}

	/**
	 * <!-- 开始-用户-文档 --> 返回流程定义的启动节点 <!-- 结束-用户-文档 -->
	 * 
	 * @return 开始节点
	 */
	public FlowNode getStartElement() {
		FlowNode startElement;

		for (FlowElement flowElement : this.getFlowElements()) {
			if (flowElement instanceof StartEventBehavior) {
				startElement = (FlowNode) flowElement;
				StartEventBehavior startEventBehavior = (StartEventBehavior) flowElement;
				List<EventDefinition> eventDefinitions = startEventBehavior.getEventDefinitions();
				if (eventDefinitions.size() == 0) {
					return startElement;
				}
			}
		}
		return null;

	}

	/**
	 * 获取空开始事件
	 * 
	 * @return
	 */
	public StartEvent getNoneStartEvent() {
		StartEvent noneStartEvent;

		for (FlowElement flowElement : this.getFlowElements()) {
			if (flowElement instanceof StartEventBehavior) {
				noneStartEvent = (StartEventBehavior) flowElement;
				StartEventBehavior startEventBehavior = (StartEventBehavior) flowElement;
				List<EventDefinition> eventDefinitions = startEventBehavior.getEventDefinitions();
				if (eventDefinitions.size() == 0) {
					return noneStartEvent;
				}
			}
		}
		return null;

	}

	/**
	 * 获取定时开始事件
	 * 
	 * @return
	 */
	public StartEvent getTimeStartEvent(String nodeId) {
		StartEvent timeStartEvent;

		for (FlowElement flowElement : this.getFlowElements()) {
			if (flowElement instanceof StartEventBehavior) {
				timeStartEvent = (StartEventBehavior) flowElement;
				StartEventBehavior startEventBehavior = (StartEventBehavior) flowElement;
				List<EventDefinition> eventDefinitions = startEventBehavior.getEventDefinitions();
				for (EventDefinition eventDefinition : eventDefinitions) {
					if (eventDefinition instanceof TimerEventDefinition) {
						if (nodeId == null || nodeId.equals("")) {
							return timeStartEvent;
						} else {
							if (timeStartEvent.getId().equals(nodeId)) {
								return timeStartEvent;
							}
						}

					}
				}
			}
		}
		return null;

	}

	// protected Map<String, StartEvent> messageStartElementMap;

	/**
	 * <!-- 开始-用户-文档 --> 返回流程定义的启动节点 <!-- 结束-用户-文档 -->
	 * 
	 * @return 开始节点
	 */
	public StartEvent getMessageStartElement(String nodeId) {

		for (FlowElement flowElement : this.getFlowElements()) {
			if (flowElement instanceof StartEventBehavior && flowElement.getId().equals(nodeId)) {
				return (StartEventBehavior) flowElement;
			}
		}
		throw new FixFlowException("流程定义没有开始节点！");

	}

	public ProcessInstanceEntity createProcessInstance(String businessKey) throws Exception {
		return new ProcessInstanceEntity(this, businessKey);
	}

	/**
	 * 该值缓存 '{@link #getFlowElementsMap() <em>Flow Elements Map</em>}' 内容引用 Map.
	 * <!-- 开始-用户-文档 --> <!-- 结束-用户-文档 -->
	 * 
	 * @see #getRootElementsMap()
	 * @generated
	 * @ordered
	 */
	protected transient Map<String, FlowElement> flowElementsMap;

	/**
	 * 返回 '<em><b>FlowElementsMap</b></em>' 内容引用 Map. 该Map的内容类型
	 * {@link com.founder.fix.fixflow.bpmn.base.FlowElement}. <!-- 开始-用户-文档 -->
	 * <p>
	 * 返回流程定义(Process)中的所有根元素(FlowElement)
	 * </p>
	 * <!-- 结束-用户-文档 -->
	 * 
	 * @return 该值为 '<em>FlowElementsMap</em>' 内容引用 Map.
	 * @generated
	 */
	public Map<String, FlowElement> getFlowElementsMap() {

		Map<String, FlowElement> flowElementsMap_tmp;
		flowElementsMap_tmp = new HashMap<String, FlowElement>();
		if ((flowElements != null)) {

			List<FlowElement> flowElementsObj = EMFUtil.getAllFlowElement(this);

			for (FlowElement rootElementObj : flowElementsObj) {
				flowElementsMap_tmp.put(rootElementObj.getId(), rootElementObj);
			}
		}
		return flowElementsMap_tmp;

	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(final Class<T> class1) {
		ArrayList<T> l = new ArrayList<T>();

		TreeIterator<EObject> contents = this.eResource().getAllContents();
		for (; contents.hasNext();) {
			Object t = contents.next();
			if (class1.isInstance(t)) {
				l.add((T) t);
			}
		}
		return l;
	}

	/**
	 * 返回 '<em><b>FlowElement</b></em>' 元素. 该对象类型为
	 * {@link com.founder.fix.fixflow.bpmn.base.FlowElement}. <!-- 开始-用户-文档 -->
	 * <p>
	 * 根据元素编号(Id)返回流程定义(Process)中指定的元素(FlowElement)
	 * </p>
	 * <!-- 结束-用户-文档 -->
	 * 
	 * @param elementId
	 *            元素编号
	 * @return 该值为 '<em>FlowElementsMap</em>' 内容引用 Map.
	 * @generated
	 */
	public FlowElement getFlowElement(String flowElementId) {

		return EMFUtil.findFlowElement(flowElementId, this);

		// return getFlowElementsMap().get(flowElementId);
	}

	protected String resourceId;

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	protected String deploymentId;

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getDeploymentId() {
		return this.deploymentId;
	}

	public Map<String, Object> getPersistentState() {

		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("processDefinitionId", this.processDefinitionId);
		persistentState.put("processDefinitionName", this.name);
		persistentState.put("processDefinitionKey", this.id);
		persistentState.put("category", getCategory());
		persistentState.put("version", this.version);
		persistentState.put("resourceName", this.resourceName);
		persistentState.put("resourceId", this.resourceId);
		persistentState.put("deploymentId", this.deploymentId);
		// persistentState.put("startForm", this.getStartFormKey());

		return persistentState;
	}

	protected String resourceName;

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	protected String processDefinitionId;

	public String getProcessDefinitionKey() {
		return this.id;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.id = processDefinitionKey;

	}

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	protected String category;

	public String getCategory() {
		
		if(this.category==null){

			this.category=StringUtil.getString(this.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__CATEGORY));
		}
		return this.category;
	}



	public String getStartFormKey() {

		UserTaskBehavior userTaskBehavior = (UserTaskBehavior) getSubTask();
		String startFormKeyTemp = userTaskBehavior.getFormUri();

		if (startFormKeyTemp != null && !startFormKeyTemp.equals("")) {

			Object returnObject = ExpressionMgmt.execute(startFormKeyTemp, this);
			if (returnObject != null) {

				return returnObject.toString();
			}

		} else {

			String defaultFormUri = this.getDefaultFormUri();
			if (defaultFormUri != null && !defaultFormUri.equals("")) {

				return defaultFormUri;

			} else {
				throw new FixFlowException("流程没有提交表单,请检查流程配置!");
			}
		}

		return startFormKeyTemp;

	}

	protected String defaultFormUri;

	protected FormUri formUri;

	public FormUri getFormUriObj() {

		if (this.formUri == null) {
			this.formUri =EMFUtil.getExtensionElementOne(FormUri.class,this,FixFlowPackage.Literals.DOCUMENT_ROOT__FORM_URI);
		}
		return this.formUri;
	}

	public String getDefaultFormUri() {

		String expressionValue = "";
		if (getFormUriObj() != null && getFormUriObj().getExpression() != null) {
			expressionValue = getFormUriObj().getExpression().getValue();
		} else {
			return null;
		}

		if (expressionValue != null && !expressionValue.equals("")) {

			Object returnObject = ExpressionMgmt.execute(expressionValue, this);
			if (returnObject != null) {
				this.defaultFormUri = returnObject.toString();
				return defaultFormUri;
			} else {
				return null;
			}

		} else {
			return null;
		}

	}

	public UserTask getSubTask() {

		FlowNode flowNode = getStartElement().getOutgoing().get(0).getTargetRef();
		if (flowNode instanceof UserTask) {
			UserTaskBehavior userTask = (UserTaskBehavior) flowNode;
			return userTask;
		}
		/*
		 * String subTaskString = EMFExtensionUtil.getAnyAttributeValue(this,
		 * "subTask"); if (subTaskString != null && !subTaskString.equals("")) {
		 * BaseElement baseElement = definitions.getElement(subTaskString); if
		 * (baseElement != null) { subTask = (UserTask) baseElement; return
		 * subTask; } else { return null; } } else { return null; }
		 */
		return null;

	}

	public UserTask getSubTask(StartEvent startEvent) {

		if (startEvent == null) {
			return null;
		}

		FlowNode flowNode = startEvent.getOutgoing().get(0).getTargetRef();
		if (flowNode instanceof UserTask) {
			UserTaskBehavior userTask = (UserTaskBehavior) flowNode;
			return userTask;
		}
		throw new FixFlowException("流程启动节点后面没有提交节点! ");
		/*
		 * String subTask = EMFExtensionUtil.getAnyAttributeValue(this,
		 * "subTask"); if (subTask != null && !subTask.equals("")) { BaseElement
		 * baseElement = definitions.getElement(subTask); if (baseElement !=
		 * null) { this.subTask = (UserTask) baseElement; return this.subTask; }
		 * else { return null; } } else { return null; }
		 */

	}

	DefinitionsBehavior definitions;

	public void setDefinitions(DefinitionsBehavior definitions) {
		this.definitions = definitions;
	}

	public DefinitionsBehavior getDefinitions() {
		// TODO Auto-generated method stub
		return definitions;
	}

	protected TaskSubjectBehavior taskSubject;

	public TaskSubjectBehavior getTaskSubject() {

		if (this.taskSubject == null) {

			TaskSubject taskSubjectObj = EMFUtil.getExtensionElementOne(TaskSubject.class, this, FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_SUBJECT);

			if(taskSubjectObj!=null){
				this.taskSubject = new TaskSubjectBehavior(taskSubjectObj);
			}
			

		}

		return this.taskSubject;
	}

	public boolean verification = true;

	public boolean isVerification() {

		Object verificationObject = this.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__VERIFICATION);
		if (verificationObject == null || verificationObject.equals("")) {
			return true;
		} else {
			this.verification = StringUtil.getBoolean(verificationObject);

		}
		return this.verification;

	}

	protected DataVariableMgmtDefinition dataVariableMgmtDefinition;

	public DataVariableMgmtDefinition getDataVariableMgmtDefinition() {
		return dataVariableMgmtDefinition;
	}

	public void setDataVariableMgmtDefinition(DataVariableMgmtDefinition dataVariableMgmtDefinition) {
		this.dataVariableMgmtDefinition = dataVariableMgmtDefinition;
	}

}
