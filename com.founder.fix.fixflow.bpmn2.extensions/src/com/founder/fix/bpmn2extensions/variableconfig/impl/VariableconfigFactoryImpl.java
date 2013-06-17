/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.variableconfig.impl;

import com.founder.fix.bpmn2extensions.variableconfig.*;

import org.eclipse.emf.ecore.EClass;
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
public class VariableconfigFactoryImpl extends EFactoryImpl implements VariableconfigFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VariableconfigFactory init() {
		try {
			VariableconfigFactory theVariableconfigFactory = (VariableconfigFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.founderfix.com/variableconfig"); 
			if (theVariableconfigFactory != null) {
				return theVariableconfigFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new VariableconfigFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableconfigFactoryImpl() {
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
			case VariableconfigPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case VariableconfigPackage.DATA_VARIABLE_CONFIG: return createDataVariableConfig();
			case VariableconfigPackage.DATA_VARIABLE_TYPE: return createDataVariableType();
			case VariableconfigPackage.TYPE: return createType();
			case VariableconfigPackage.DATA_VARIABLE_DATA_TYPE: return createDataVariableDataType();
			case VariableconfigPackage.DATA_TYPE_DEF: return createDataTypeDef();
			case VariableconfigPackage.FIX_FLOW_DATA_VARIABLE: return createFixFlowDataVariable();
			case VariableconfigPackage.DATA_VARIABLE_DEF: return createDataVariableDef();
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE_CONFIG: return createDataVariableBizTypeConfig();
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE: return createDataVariableBizType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
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
	public DataVariableConfig createDataVariableConfig() {
		DataVariableConfigImpl dataVariableConfig = new DataVariableConfigImpl();
		return dataVariableConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariableType createDataVariableType() {
		DataVariableTypeImpl dataVariableType = new DataVariableTypeImpl();
		return dataVariableType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type createType() {
		TypeImpl type = new TypeImpl();
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariableDataType createDataVariableDataType() {
		DataVariableDataTypeImpl dataVariableDataType = new DataVariableDataTypeImpl();
		return dataVariableDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTypeDef createDataTypeDef() {
		DataTypeDefImpl dataTypeDef = new DataTypeDefImpl();
		return dataTypeDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FixFlowDataVariable createFixFlowDataVariable() {
		FixFlowDataVariableImpl fixFlowDataVariable = new FixFlowDataVariableImpl();
		return fixFlowDataVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariableDef createDataVariableDef() {
		DataVariableDefImpl dataVariableDef = new DataVariableDefImpl();
		return dataVariableDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariableBizTypeConfig createDataVariableBizTypeConfig() {
		DataVariableBizTypeConfigImpl dataVariableBizTypeConfig = new DataVariableBizTypeConfigImpl();
		return dataVariableBizTypeConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariableBizType createDataVariableBizType() {
		DataVariableBizTypeImpl dataVariableBizType = new DataVariableBizTypeImpl();
		return dataVariableBizType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableconfigPackage getVariableconfigPackage() {
		return (VariableconfigPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static VariableconfigPackage getPackage() {
		return VariableconfigPackage.eINSTANCE;
	}

} //VariableconfigFactoryImpl
