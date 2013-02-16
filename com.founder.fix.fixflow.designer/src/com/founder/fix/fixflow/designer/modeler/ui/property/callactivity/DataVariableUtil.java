package com.founder.fix.fixflow.designer.modeler.ui.property.callactivity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.founder.fix.bpmn2extensions.fixflow.DataSourceToSubProcessMapping;
import com.founder.fix.bpmn2extensions.fixflow.DataVariable;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.SubProcessToDataSourceMapping;
import com.founder.fix.fixflow.designer.database.SqlCommand;
import com.founder.fix.fixflow.designer.exception.FixFlowException;
import com.founder.fix.fixflow.designer.util.FixFlowConfigUtil;
import com.founder.fix.fixflow.designer.util.StringUtil;

public class DataVariableUtil {

	/**
	 * 获取子流程数据变量
	 * 
	 * @param callActivity
	 * @return
	 * @throws Exception
	 */
	public static List<DataVariable> getSubProcessDataVariable(CallActivity callActivity) throws Exception {

		List<DataVariable> dataVariables = new ArrayList<DataVariable>();

		Object processKey = callActivity.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_ID);
		Object processVersion = callActivity.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION);

		if (processKey != null && !processKey.equals("")) {

			Connection connection = FixFlowConfigUtil.createConnectionWithCommit();
			SqlCommand sqlCommand = new SqlCommand(connection);

			String processKeyNow=null;
			int processVersionNow=0;
			if(processKey.toString().indexOf("\"")>=0){
				processKeyNow=processKey.toString().replace("\"", "");
			}
			else{
				processKeyNow=processKey.toString();
			}
			
			if(processVersion.toString().indexOf("\"")>=0){
				processVersionNow=StringUtil.getInt(processVersion.toString().replace("\"", ""));
			}else{
				processVersionNow=StringUtil.getInt(processVersion.toString());
			}
			
		
			Process process = selectProcessDefinitionById(processKeyNow,processVersionNow, sqlCommand);

			List<DataVariable> dataVariablesTemp = getProcessDataVariable(process);
			dataVariables.addAll(dataVariablesTemp);
		}

		return dataVariables;

	}

	// selectProcessDefinitionById

	private static Process selectProcessDefinitionById(String processKey, int version, SqlCommand sqlCommand) throws Exception {

		String sqlText = "select * " + "from FIXFLOW_DEF_PROCESSDEFINITION " + "where PROCESS_KEY = ? AND VERSION=?";

		// 构建查询参数

		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(processKey);
		objectParamWhere.add(version);

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);
		if (dataObj.size() == 0) {
			throw new FixFlowException("流程定义 " + processKey + " 未查询到!");
		}
		Map<String, Object> dataMap = dataObj.get(0);

		String processId = StringUtil.getString(dataMap.get("PROCESS_ID"));
		String deploymentId = StringUtil.getString(dataMap.get("DEPLOYMENT_ID"));
		String resourceName = StringUtil.getString(dataMap.get("RESOURCE_NAME"));
		String processKeyObj = StringUtil.getString(dataMap.get("PROCESS_KEY"));
		// String startFormKey =
		// StringUtil.getString(dataMap.get("START_FORM_KEY"));

		Process processDefinition = getProcessDefinition(deploymentId, resourceName, processKeyObj, processId, sqlCommand);

		return processDefinition;

	}

	/**
	 * 得到数据变量所存放的路径
	 * 
	 * @return
	 */
	private static String getConfigPath() {
		return ResourcesPlugin.getWorkspace().getRoot().getProject("fixflow-expand").getLocation().toString()
				+ "/src/com/founder/fix/fixflow/expand/config/";
	}

	/**
	 * 得到数据变量XML所存放的路径
	 * 
	 * @return
	 */
	private static String getFixflowfilePath() {
		return getConfigPath() + "fixflowfile.bpmn";
	}

	private static Process getProcessDefinition(String deploymentId, String resourceName, String processKey, String processId,
			SqlCommand sqlCommand) throws Exception {

		String sqlText = "SELECT BYTES FROM FIXFLOW_DEF_BYTEARRAY WHERE NAME=? and DEPLOYMENT_ID=?";

		// 构建查询参数

		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(resourceName);
		objectParamWhere.add(deploymentId);

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);
		Map<String, Object> dataMap = dataObj.get(0);

		Object bytesObject = dataMap.get("BYTES");
		if (bytesObject != null) {
			byte[] bytes = (byte[]) bytesObject;

			Bpmn2ResourceFactoryImpl ddd = new Bpmn2ResourceFactoryImpl();
			Resource ddddResource = ddd.createResource(URI.createFileURI(getFixflowfilePath()));

			try {
				ddddResource.load(new ByteArrayInputStream(bytes), null);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				throw new FixFlowException("定义文件加载失败!", e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new FixFlowException("定义文件加载失败!", e);
			}

			Definitions definitions = (Definitions) ddddResource.getContents().get(0).eContents().get(0);

			Process process = null;
			for (RootElement rootElement : definitions.getRootElements()) {
				if (rootElement instanceof Process) {
					Process processObj = (Process) rootElement;
					if (processObj.getId().equals(processKey)) {
						process = (Process) rootElement;
						break;
					}

				}
			}

			return process;

		}
		return null;

	}

	/**
	 * 获取主流程数据变量
	 * 
	 * @param process
	 * @return
	 */
	public static List<DataVariable> getProcessDataVariable(Process process) {

		BaseElement baseElement = process;
		List<DataVariable> dataVariables = new ArrayList<DataVariable>();

		if (baseElement.getExtensionValues().size() > 0) {
			for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
				FeatureMap extensionElements = extensionAttributeValue.getValue();
				Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, true);
				if (objectElement != null) {
					@SuppressWarnings("unchecked")
					List<DataVariable> dataVariableList = (List<DataVariable>) objectElement;
					for (DataVariable dataVariable : dataVariableList) {
						dataVariables.add(dataVariable);
					}
				}
			}
		}

		return dataVariables;
	}

	/**
	 * 获取主流程到子流程的映射
	 * 
	 * @param taskC
	 */
	public static DataSourceToSubProcessMapping getDataSourceToSubProcessMapping(final CallActivity callActivity) {

	

				if (callActivity.getExtensionValues().size() > 0) {

					for (ExtensionAttributeValue extensionAttributeValue : callActivity.getExtensionValues()) {

						FeatureMap extensionElements = extensionAttributeValue.getValue();
						Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING, true);
						if (objectElement != null) {
							return (DataSourceToSubProcessMapping)objectElement;
						}
						else{
							DataSourceToSubProcessMapping dataSourceToSubProcessMapping=FixFlowFactory.eINSTANCE.createDataSourceToSubProcessMapping();
							FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING, dataSourceToSubProcessMapping);
							extensionAttributeValue.getValue().add(extensionElementEntry);
							return dataSourceToSubProcessMapping;
						}

					}
				} else {
				
					DataSourceToSubProcessMapping dataSourceToSubProcessMapping=FixFlowFactory.eINSTANCE.createDataSourceToSubProcessMapping();
					
					ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
					callActivity.getExtensionValues().add(extensionElement);
					FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING, dataSourceToSubProcessMapping);
					extensionElement.getValue().add(extensionElementEntry);
					
					return dataSourceToSubProcessMapping;
				}
				
				
				return null;

	}
	
	
	
	/**
	 * 获取子流程到主流程的映射
	 * 
	 * @param taskC
	 */
	public static SubProcessToDataSourceMapping getSubProcessToDataSourceMapping(final CallActivity callActivity) {


				if (callActivity.getExtensionValues().size() > 0) {

					for (ExtensionAttributeValue extensionAttributeValue : callActivity.getExtensionValues()) {

						FeatureMap extensionElements = extensionAttributeValue.getValue();
						Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING, true);
						if (objectElement != null) {
							return (SubProcessToDataSourceMapping)objectElement;
						}
						else{
							SubProcessToDataSourceMapping dataSourceToSubProcessMapping=FixFlowFactory.eINSTANCE.createSubProcessToDataSourceMapping();
							FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING, dataSourceToSubProcessMapping);
							extensionAttributeValue.getValue().add(extensionElementEntry);
							return dataSourceToSubProcessMapping;
						}

					}
				} else {
				
					SubProcessToDataSourceMapping subProcessToDataSourceMapping=FixFlowFactory.eINSTANCE.createSubProcessToDataSourceMapping();
					
					ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
					callActivity.getExtensionValues().add(extensionElement);
					FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING, subProcessToDataSourceMapping);
					extensionElement.getValue().add(extensionElementEntry);
					
					return subProcessToDataSourceMapping;
				}
				
				
				return null;

	}

}
