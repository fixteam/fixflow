/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow.impl;

import com.founder.fix.bpmn2extensions.fixflow.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FixFlowFactoryImpl extends EFactoryImpl implements FixFlowFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FixFlowFactory init() {
		try {
			FixFlowFactory theFixFlowFactory = (FixFlowFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.founderfix.com/fixflow"); 
			if (theFixFlowFactory != null) {
				return theFixFlowFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FixFlowFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FixFlowFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case FixFlowPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case FixFlowPackage.EXPRESSION: return createExpression();
			case FixFlowPackage.TASK_SUBJECT: return createTaskSubject();
			case FixFlowPackage.TASK_COMMAND: return createTaskCommand();
			case FixFlowPackage.RESOURCE_FILTER: return createResourceFilter();
			case FixFlowPackage.DATA_VARIABLE: return createDataVariable();
			case FixFlowPackage.DOCUMENTATION: return createDocumentation();
			case FixFlowPackage.CONNECTOR_INSTANCE: return createConnectorInstance();
			case FixFlowPackage.CONNECTOR_PARAMETER_INPUTS: return createConnectorParameterInputs();
			case FixFlowPackage.CONNECTOR_PARAMETER_OUTPUTS: return createConnectorParameterOutputs();
			case FixFlowPackage.LOOP_DATA_INPUT_COLLECTION: return createLoopDataInputCollection();
			case FixFlowPackage.LOOP_DATA_OUTPUT_COLLECTION: return createLoopDataOutputCollection();
			case FixFlowPackage.CONNECTOR_PARAMETER_OUTPUTS_DEF: return createConnectorParameterOutputsDef();
			case FixFlowPackage.FORM_URI: return createFormUri();
			case FixFlowPackage.LOOP_MAXIMUM: return createLoopMaximum();
			case FixFlowPackage.SKIP_STRATEGY: return createSkipStrategy();
			case FixFlowPackage.MESSAGE_OBJ: return createMessageObj();
			case FixFlowPackage.FILTER_CONDITIONS: return createFilterConditions();
			case FixFlowPackage.RECEIVE_MESSAGE: return createReceiveMessage();
			case FixFlowPackage.TOKEN_VARIABLE: return createTokenVariable();
			case FixFlowPackage.PROCESS_INSTANCE_VARIABLE: return createProcessInstanceVariable();
			case FixFlowPackage.EXPECTED_EXECUTION_TIME: return createExpectedExecutionTime();
			case FixFlowPackage.DATA_SOURCE_TO_SUB_PROCESS_MAPPING: return createDataSourceToSubProcessMapping();
			case FixFlowPackage.SUB_PROCESS_TO_DATA_SOURCE_MAPPING: return createSubProcessToDataSourceMapping();
			case FixFlowPackage.DATA_VARIABLE_MAPPING: return createDataVariableMapping();
			case FixFlowPackage.FORM_URI_VIEW: return createFormUriView();
			case FixFlowPackage.TASK_PRIORITY: return createTaskPriority();
			case FixFlowPackage.ASSIGN_POLICY_TYPE: return createAssignPolicyType();
			case FixFlowPackage.SKIP_ASSIGNEE: return createSkipAssignee();
			case FixFlowPackage.SKIP_COMMENT: return createSkipComment();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case FixFlowPackage.PROCESS_VALIDATION_LEVEL:
				return createProcessValidationLevelFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case FixFlowPackage.PROCESS_VALIDATION_LEVEL:
				return convertProcessValidationLevelToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression createExpression() {
		ExpressionImpl expression = new ExpressionImpl();
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskSubject createTaskSubject() {
		TaskSubjectImpl taskSubject = new TaskSubjectImpl();
		return taskSubject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskCommand createTaskCommand() {
		TaskCommandImpl taskCommand = new TaskCommandImpl();
		return taskCommand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceFilter createResourceFilter() {
		ResourceFilterImpl resourceFilter = new ResourceFilterImpl();
		return resourceFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariable createDataVariable() {
		DataVariableImpl dataVariable = new DataVariableImpl();
		return dataVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Documentation createDocumentation() {
		DocumentationImpl documentation = new DocumentationImpl();
		return documentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorInstance createConnectorInstance() {
		ConnectorInstanceImpl connectorInstance = new ConnectorInstanceImpl();
		return connectorInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorParameterInputs createConnectorParameterInputs() {
		ConnectorParameterInputsImpl connectorParameterInputs = new ConnectorParameterInputsImpl();
		return connectorParameterInputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorParameterOutputs createConnectorParameterOutputs() {
		ConnectorParameterOutputsImpl connectorParameterOutputs = new ConnectorParameterOutputsImpl();
		return connectorParameterOutputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoopDataInputCollection createLoopDataInputCollection() {
		LoopDataInputCollectionImpl loopDataInputCollection = new LoopDataInputCollectionImpl();
		return loopDataInputCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoopDataOutputCollection createLoopDataOutputCollection() {
		LoopDataOutputCollectionImpl loopDataOutputCollection = new LoopDataOutputCollectionImpl();
		return loopDataOutputCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorParameterOutputsDef createConnectorParameterOutputsDef() {
		ConnectorParameterOutputsDefImpl connectorParameterOutputsDef = new ConnectorParameterOutputsDefImpl();
		return connectorParameterOutputsDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormUri createFormUri() {
		FormUriImpl formUri = new FormUriImpl();
		return formUri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoopMaximum createLoopMaximum() {
		LoopMaximumImpl loopMaximum = new LoopMaximumImpl();
		return loopMaximum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SkipStrategy createSkipStrategy() {
		SkipStrategyImpl skipStrategy = new SkipStrategyImpl();
		return skipStrategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageObj createMessageObj() {
		MessageObjImpl messageObj = new MessageObjImpl();
		return messageObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterConditions createFilterConditions() {
		FilterConditionsImpl filterConditions = new FilterConditionsImpl();
		return filterConditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReceiveMessage createReceiveMessage() {
		ReceiveMessageImpl receiveMessage = new ReceiveMessageImpl();
		return receiveMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TokenVariable createTokenVariable() {
		TokenVariableImpl tokenVariable = new TokenVariableImpl();
		return tokenVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessInstanceVariable createProcessInstanceVariable() {
		ProcessInstanceVariableImpl processInstanceVariable = new ProcessInstanceVariableImpl();
		return processInstanceVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpectedExecutionTime createExpectedExecutionTime() {
		ExpectedExecutionTimeImpl expectedExecutionTime = new ExpectedExecutionTimeImpl();
		return expectedExecutionTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSourceToSubProcessMapping createDataSourceToSubProcessMapping() {
		DataSourceToSubProcessMappingImpl dataSourceToSubProcessMapping = new DataSourceToSubProcessMappingImpl();
		return dataSourceToSubProcessMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubProcessToDataSourceMapping createSubProcessToDataSourceMapping() {
		SubProcessToDataSourceMappingImpl subProcessToDataSourceMapping = new SubProcessToDataSourceMappingImpl();
		return subProcessToDataSourceMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariableMapping createDataVariableMapping() {
		DataVariableMappingImpl dataVariableMapping = new DataVariableMappingImpl();
		return dataVariableMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormUriView createFormUriView() {
		FormUriViewImpl formUriView = new FormUriViewImpl();
		return formUriView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskPriority createTaskPriority() {
		TaskPriorityImpl taskPriority = new TaskPriorityImpl();
		return taskPriority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignPolicyType createAssignPolicyType() {
		AssignPolicyTypeImpl assignPolicyType = new AssignPolicyTypeImpl();
		return assignPolicyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SkipAssignee createSkipAssignee() {
		SkipAssigneeImpl skipAssignee = new SkipAssigneeImpl();
		return skipAssignee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SkipComment createSkipComment() {
		SkipCommentImpl skipComment = new SkipCommentImpl();
		return skipComment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessValidationLevel createProcessValidationLevelFromString(EDataType eDataType, String initialValue) {
		ProcessValidationLevel result = ProcessValidationLevel.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertProcessValidationLevelToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FixFlowPackage getFixFlowPackage() {
		return (FixFlowPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static FixFlowPackage getPackage() {
		return FixFlowPackage.eINSTANCE;
	}

} //FixFlowFactoryImpl
