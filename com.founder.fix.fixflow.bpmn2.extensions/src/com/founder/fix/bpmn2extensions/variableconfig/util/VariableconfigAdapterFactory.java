/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.variableconfig.util;

import com.founder.fix.bpmn2extensions.variableconfig.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage
 * @generated
 */
public class VariableconfigAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static VariableconfigPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableconfigAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = VariableconfigPackage.eINSTANCE;
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
	protected VariableconfigSwitch<Adapter> modelSwitch =
		new VariableconfigSwitch<Adapter>() {
			@Override
			public Adapter caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			@Override
			public Adapter caseDataVariableConfig(DataVariableConfig object) {
				return createDataVariableConfigAdapter();
			}
			@Override
			public Adapter caseDataVariableType(DataVariableType object) {
				return createDataVariableTypeAdapter();
			}
			@Override
			public Adapter caseType(Type object) {
				return createTypeAdapter();
			}
			@Override
			public Adapter caseDataVariableDataType(DataVariableDataType object) {
				return createDataVariableDataTypeAdapter();
			}
			@Override
			public Adapter caseDataTypeDef(DataTypeDef object) {
				return createDataTypeDefAdapter();
			}
			@Override
			public Adapter caseFixFlowDataVariable(FixFlowDataVariable object) {
				return createFixFlowDataVariableAdapter();
			}
			@Override
			public Adapter caseDataVariableDef(DataVariableDef object) {
				return createDataVariableDefAdapter();
			}
			@Override
			public Adapter caseDataVariableBizTypeConfig(DataVariableBizTypeConfig object) {
				return createDataVariableBizTypeConfigAdapter();
			}
			@Override
			public Adapter caseDataVariableBizType(DataVariableBizType object) {
				return createDataVariableBizTypeAdapter();
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
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.variableconfig.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig <em>Data Variable Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig
	 * @generated
	 */
	public Adapter createDataVariableConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableType <em>Data Variable Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableType
	 * @generated
	 */
	public Adapter createDataVariableTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.variableconfig.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.Type
	 * @generated
	 */
	public Adapter createTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableDataType <em>Data Variable Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableDataType
	 * @generated
	 */
	public Adapter createDataVariableDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef <em>Data Type Def</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef
	 * @generated
	 */
	public Adapter createDataTypeDefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable <em>Fix Flow Data Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable
	 * @generated
	 */
	public Adapter createFixFlowDataVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef <em>Data Variable Def</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef
	 * @generated
	 */
	public Adapter createDataVariableDefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableBizTypeConfig <em>Data Variable Biz Type Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableBizTypeConfig
	 * @generated
	 */
	public Adapter createDataVariableBizTypeConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableBizType <em>Data Variable Biz Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableBizType
	 * @generated
	 */
	public Adapter createDataVariableBizTypeAdapter() {
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

} //VariableconfigAdapterFactory
