/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow.util;

import com.founder.fix.bpmn2extensions.fixflow.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage
 * @generated
 */
public class FixFlowAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FixFlowPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FixFlowAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = FixFlowPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FixFlowSwitch<Adapter> modelSwitch =
		new FixFlowSwitch<Adapter>() {
			@Override
			public Adapter caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			@Override
			public Adapter caseExpression(Expression object) {
				return createExpressionAdapter();
			}
			@Override
			public Adapter caseTaskSubject(TaskSubject object) {
				return createTaskSubjectAdapter();
			}
			@Override
			public Adapter caseTaskCommand(TaskCommand object) {
				return createTaskCommandAdapter();
			}
			@Override
			public Adapter caseResourceFilter(ResourceFilter object) {
				return createResourceFilterAdapter();
			}
			@Override
			public Adapter caseDataVariable(DataVariable object) {
				return createDataVariableAdapter();
			}
			@Override
			public Adapter caseDocumentation(Documentation object) {
				return createDocumentationAdapter();
			}
			@Override
			public Adapter caseConnectorInstance(ConnectorInstance object) {
				return createConnectorInstanceAdapter();
			}
			@Override
			public Adapter caseConnectorParameterInputs(ConnectorParameterInputs object) {
				return createConnectorParameterInputsAdapter();
			}
			@Override
			public Adapter caseConnectorParameterOutputs(ConnectorParameterOutputs object) {
				return createConnectorParameterOutputsAdapter();
			}
			@Override
			public Adapter caseLoopDataInputCollection(LoopDataInputCollection object) {
				return createLoopDataInputCollectionAdapter();
			}
			@Override
			public Adapter caseLoopDataOutputCollection(LoopDataOutputCollection object) {
				return createLoopDataOutputCollectionAdapter();
			}
			@Override
			public Adapter caseConnectorParameterOutputsDef(ConnectorParameterOutputsDef object) {
				return createConnectorParameterOutputsDefAdapter();
			}
			@Override
			public Adapter caseFormUri(FormUri object) {
				return createFormUriAdapter();
			}
			@Override
			public Adapter caseLoopMaximum(LoopMaximum object) {
				return createLoopMaximumAdapter();
			}
			@Override
			public Adapter caseSkipStrategy(SkipStrategy object) {
				return createSkipStrategyAdapter();
			}
			@Override
			public Adapter caseMessageObj(MessageObj object) {
				return createMessageObjAdapter();
			}
			@Override
			public Adapter caseFilterConditions(FilterConditions object) {
				return createFilterConditionsAdapter();
			}
			@Override
			public Adapter caseReceiveMessage(ReceiveMessage object) {
				return createReceiveMessageAdapter();
			}
			@Override
			public Adapter caseTokenVariable(TokenVariable object) {
				return createTokenVariableAdapter();
			}
			@Override
			public Adapter caseProcessInstanceVariable(ProcessInstanceVariable object) {
				return createProcessInstanceVariableAdapter();
			}
			@Override
			public Adapter caseExpectedExecutionTime(ExpectedExecutionTime object) {
				return createExpectedExecutionTimeAdapter();
			}
			@Override
			public Adapter caseDataSourceToSubProcessMapping(DataSourceToSubProcessMapping object) {
				return createDataSourceToSubProcessMappingAdapter();
			}
			@Override
			public Adapter caseSubProcessToDataSourceMapping(SubProcessToDataSourceMapping object) {
				return createSubProcessToDataSourceMappingAdapter();
			}
			@Override
			public Adapter caseDataVariableMapping(DataVariableMapping object) {
				return createDataVariableMappingAdapter();
			}
			@Override
			public Adapter caseFormUriView(FormUriView object) {
				return createFormUriViewAdapter();
			}
			@Override
			public Adapter caseTaskPriority(TaskPriority object) {
				return createTaskPriorityAdapter();
			}
			@Override
			public Adapter caseAssignPolicyType(AssignPolicyType object) {
				return createAssignPolicyTypeAdapter();
			}
			@Override
			public Adapter caseSkipAssignee(SkipAssignee object) {
				return createSkipAssigneeAdapter();
			}
			@Override
			public Adapter caseSkipComment(SkipComment object) {
				return createSkipCommentAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.TaskSubject <em>Task Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskSubject
	 * @generated
	 */
	public Adapter createTaskSubjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand <em>Task Command</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskCommand
	 * @generated
	 */
	public Adapter createTaskCommandAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.ResourceFilter <em>Resource Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ResourceFilter
	 * @generated
	 */
	public Adapter createResourceFilterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariable <em>Data Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataVariable
	 * @generated
	 */
	public Adapter createDataVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.Documentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.Documentation
	 * @generated
	 */
	public Adapter createDocumentationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance <em>Connector Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance
	 * @generated
	 */
	public Adapter createConnectorInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs <em>Connector Parameter Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs
	 * @generated
	 */
	public Adapter createConnectorParameterInputsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs <em>Connector Parameter Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs
	 * @generated
	 */
	public Adapter createConnectorParameterOutputsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.LoopDataInputCollection <em>Loop Data Input Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.LoopDataInputCollection
	 * @generated
	 */
	public Adapter createLoopDataInputCollectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.LoopDataOutputCollection <em>Loop Data Output Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.LoopDataOutputCollection
	 * @generated
	 */
	public Adapter createLoopDataOutputCollectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef <em>Connector Parameter Outputs Def</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef
	 * @generated
	 */
	public Adapter createConnectorParameterOutputsDefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.FormUri <em>Form Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FormUri
	 * @generated
	 */
	public Adapter createFormUriAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.LoopMaximum <em>Loop Maximum</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.LoopMaximum
	 * @generated
	 */
	public Adapter createLoopMaximumAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.SkipStrategy <em>Skip Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SkipStrategy
	 * @generated
	 */
	public Adapter createSkipStrategyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.MessageObj <em>Message Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.MessageObj
	 * @generated
	 */
	public Adapter createMessageObjAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.FilterConditions <em>Filter Conditions</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FilterConditions
	 * @generated
	 */
	public Adapter createFilterConditionsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage <em>Receive Message</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage
	 * @generated
	 */
	public Adapter createReceiveMessageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.TokenVariable <em>Token Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TokenVariable
	 * @generated
	 */
	public Adapter createTokenVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.ProcessInstanceVariable <em>Process Instance Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ProcessInstanceVariable
	 * @generated
	 */
	public Adapter createProcessInstanceVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime <em>Expected Execution Time</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime
	 * @generated
	 */
	public Adapter createExpectedExecutionTimeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.DataSourceToSubProcessMapping <em>Data Source To Sub Process Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataSourceToSubProcessMapping
	 * @generated
	 */
	public Adapter createDataSourceToSubProcessMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.SubProcessToDataSourceMapping <em>Sub Process To Data Source Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SubProcessToDataSourceMapping
	 * @generated
	 */
	public Adapter createSubProcessToDataSourceMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping <em>Data Variable Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping
	 * @generated
	 */
	public Adapter createDataVariableMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.FormUriView <em>Form Uri View</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.FormUriView
	 * @generated
	 */
	public Adapter createFormUriViewAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.TaskPriority <em>Task Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.TaskPriority
	 * @generated
	 */
	public Adapter createTaskPriorityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType <em>Assign Policy Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType
	 * @generated
	 */
	public Adapter createAssignPolicyTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.SkipAssignee <em>Skip Assignee</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SkipAssignee
	 * @generated
	 */
	public Adapter createSkipAssigneeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.fixflow.SkipComment <em>Skip Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.fixflow.SkipComment
	 * @generated
	 */
	public Adapter createSkipCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //FixFlowAdapterFactory
