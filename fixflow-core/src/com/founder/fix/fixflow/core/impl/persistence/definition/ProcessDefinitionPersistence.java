/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl.persistence.definition;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.di.BpmnDiPackage;
import org.eclipse.bpmn2.impl.BaseElementImpl;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.dd.dc.DcPackage;
import org.eclipse.dd.di.DiPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs;
import com.founder.fix.bpmn2extensions.fixflow.DataVariable;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.ProcessDefinitionQueryImpl;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.DataVariableBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.DefinitionsBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.connector.ConnectorInstanceBehavior;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableMgmtDefinition;
import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.event.BaseElementEventImpl;
import com.founder.fix.fixflow.core.impl.persistence.deployer.DeploymentCache;
import com.founder.fix.fixflow.core.impl.util.EMFExtensionUtil;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.objkey.ProcessInstanceObjKey;

public class ProcessDefinitionPersistence {

	public Connection connection;
	protected SqlCommand sqlCommand;
	Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();

	public ProcessDefinitionPersistence(Connection connection) {
		this.connection = connection;
		// 初始化数据库操作类
		sqlCommand = new SqlCommand(connection);
	}

	public void insertProcessDefinition(PersistentObject persistentObject) {
		Map<String, Object> resourceMap = persistentObject.getPersistentState();
		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();
		objectParam.put("PROCESS_ID", resourceMap.get("processDefinitionId"));
		objectParam.put("PROCESS_NAME", resourceMap.get("processDefinitionName"));
		objectParam.put("PROCESS_KEY", resourceMap.get("processDefinitionKey"));
		objectParam.put("CATEGORY", resourceMap.get("category"));
		objectParam.put("VERSION", resourceMap.get("version"));
		objectParam.put("RESOURCE_NAME", resourceMap.get("resourceName"));
		objectParam.put("DEPLOYMENT_ID", resourceMap.get("deploymentId"));
		objectParam.put("DIAGRAM_RESOURCE_NAME", resourceMap.get("diagramResourceName"));
		objectParam.put("RESOURCE_ID", resourceMap.get("resourceId"));
		// 执行插入语句
		sqlCommand.insert("FIXFLOW_DEF_PROCESSDEFINITION", objectParam);
	}

	public ProcessDefinitionBehavior selectLatestProcessDefinitionByKey(String processDefinitionKey) {
		String sqlText = "select * " + "from FIXFLOW_DEF_PROCESSDEFINITION " + "where PROCESS_KEY = ? and "
				+ "VERSION = (select max(VERSION) from FIXFLOW_DEF_PROCESSDEFINITION where PROCESS_KEY = ?)";
		// 构建查询参数
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(processDefinitionKey);
		objectParamWhere.add(processDefinitionKey);
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);
		if (dataObj == null || dataObj.size() == 0) {
			return null;
		}
		Map<String, Object> dataMap = dataObj.get(0);
		ProcessDefinitionBehavior processDefinitionBehavior = getProcessDefinition(dataMap);
		return processDefinitionBehavior;
	}

	public ProcessDefinitionBehavior selectProcessDefinitionById(String processDefinitionId) {
		DeploymentCache deploymentCache = Context.getProcessEngineConfiguration().getDeploymentCache();
		ProcessDefinitionBehavior processDefinition = deploymentCache.getProcessDefinitionCache().get(processDefinitionId);
		if (processDefinition == null) {
			String sqlText = "select * " + "from FIXFLOW_DEF_PROCESSDEFINITION " + "where PROCESS_ID = ?";
			// 构建查询参数
			List<Object> objectParamWhere = new ArrayList<Object>();
			objectParamWhere.add(processDefinitionId);
			List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);
			if (dataObj.size() == 0) {
				throw new FixFlowException("流程定义 " + processDefinitionId + " 未查询到!");
			}
			Map<String, Object> dataMap = dataObj.get(0);
			ProcessDefinitionBehavior processDefinitionBehavior = getProcessDefinition(dataMap);
			return processDefinitionBehavior;
		}
		return processDefinition;
	}

	public String selectProcessDefinitionsByQueryCriteria(String selectProcessDefinitionsByQueryCriteriaSql, ProcessDefinitionQueryImpl processDefinitionQuery,
			List<Object> objectParamWhere) {
		selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " from FIXFLOW_DEF_PROCESSDEFINITION PD ";
		selectProcessDefinitionsByQueryCriteriaSql += " left join FIXFLOW_DEF_DEPLOYMENT DP ON PD.DEPLOYMENT_ID = DP.ID ";
		// 自定义扩展查询
		if (processDefinitionQuery.getQueryExpandTo() != null && processDefinitionQuery.getQueryExpandTo().getLeftJoinSql() != null
				&& !processDefinitionQuery.getQueryExpandTo().getLeftJoinSql().equals("")) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql
					+ processDefinitionQuery.getQueryExpandTo().getLeftJoinSql();
		}
		selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " WHERE 1=1";
		// 自定义扩展查询
		if (processDefinitionQuery.getQueryExpandTo() != null && processDefinitionQuery.getQueryExpandTo().getWhereSql() != null
				&& !processDefinitionQuery.getQueryExpandTo().getWhereSql().equals("")) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " and "
					+ processDefinitionQuery.getQueryExpandTo().getWhereSql();
			if (processDefinitionQuery.getQueryExpandTo().getWhereSqlObj() != null && processDefinitionQuery.getQueryExpandTo().getWhereSqlObj().size() > 0) {
				objectParamWhere.addAll(processDefinitionQuery.getQueryExpandTo().getWhereSqlObj());
			}
		}
		if (processDefinitionQuery.getId() != null) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " and PD.PROCESS_ID=? ";
			objectParamWhere.add(processDefinitionQuery.getId());
		}
		if (processDefinitionQuery.getKey() != null) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " and PD.PROCESS_KEY=? ";
			objectParamWhere.add(processDefinitionQuery.getKey());
		}
		if (processDefinitionQuery.getKeyLike() != null) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " and PD.PROCESS_KEY like '%"
					+ processDefinitionQuery.getKeyLike() + "%' ";
		}
		if (processDefinitionQuery.isLatest()) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql
					+ " and PD.VERSION = (select max(VERSION) from FIXFLOW_DEF_PROCESSDEFINITION where PROCESS_KEY = PD.PROCESS_KEY)";
		}
		if (processDefinitionQuery.getCategory() != null) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " and PD.CATEGORY = ?";
			objectParamWhere.add(processDefinitionQuery.getCategory());
		}
		if (processDefinitionQuery.getCategoryLike() != null) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " and PD.CATEGORY like '%"
					+ processDefinitionQuery.getCategoryLike() + "%'";
		}
		if (processDefinitionQuery.getName() != null) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " and PD.PROCESS_NAME =?";
			objectParamWhere.add(processDefinitionQuery.getName());
		}
		if (processDefinitionQuery.getNameLike() != null) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " and PD.PROCESS_NAME like '%"
					+ processDefinitionQuery.getNameLike() + "%'";
		}
		if (processDefinitionQuery.getVersion() != null) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " and PD.VERSION =?";
			objectParamWhere.add(processDefinitionQuery.getVersion());
		}
		if (processDefinitionQuery.getDeploymentId() != null) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " and PD.DEPLOYMENT_ID =?";
			objectParamWhere.add(processDefinitionQuery.getDeploymentId());
		}

		return selectProcessDefinitionsByQueryCriteriaSql;
	}

	public List<ProcessDefinitionBehavior> selectProcessDefinitionsByQueryCriteria(ProcessDefinitionQueryImpl processDefinitionQuery, Page page) {
		List<Object> objectParamWhere = new ArrayList<Object>();
		String selectProcessDefinitionsByQueryCriteriaSql = " select " + Context.getProcessEngineConfiguration().getDbConfig().getDbSqlMap().get("topOrderBy") + " PD.* ,DP.*";
		if (processDefinitionQuery.getQueryExpandTo() != null && processDefinitionQuery.getQueryExpandTo().getFieldSql() != null
				&& !processDefinitionQuery.getQueryExpandTo().getFieldSql().equals("")) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " , "
					+ processDefinitionQuery.getQueryExpandTo().getFieldSql();
		}
		selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteria(selectProcessDefinitionsByQueryCriteriaSql,
				processDefinitionQuery, objectParamWhere);
		if (processDefinitionQuery.getOrderBy() != null) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " order by "
					+ processDefinitionQuery.getOrderBy().toString();
		}
		String orderByString = "";
		if (processDefinitionQuery.getOrderBy() != null && page != null) {
			String orderBySql = processDefinitionQuery.getOrderBy();
			String orderBySqlFin = "";
			if (orderBySql.indexOf(",") >= 0) {
				String[] orderBySqlTemp = orderBySql.split(",");
				for (String orderByObj : orderBySqlTemp) {
					if (orderBySqlFin.equals("")) {
						orderBySqlFin = orderBySqlFin + orderByObj.substring(orderByObj.indexOf(".") + 1, orderByObj.length());
					} else {
						orderBySqlFin = orderBySqlFin + "," + orderByObj.substring(orderByObj.indexOf(".") + 1, orderByObj.length());
					}
				}
				orderByString = orderByString + " order by " + orderBySqlFin;
			} else {
				orderByString = orderByString + " order by " + processDefinitionQuery.getOrderBy().toString().substring(3);
			}
		}
		if (page != null) {
			selectProcessDefinitionsByQueryCriteriaSql = pagination.getPaginationSql(selectProcessDefinitionsByQueryCriteriaSql, page.getFirstResult(),
					page.getMaxResults(), "*", orderByString);
		}
		if (processDefinitionQuery.getOrderBy() != null && page != null) {
			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + orderByString;
		}
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(selectProcessDefinitionsByQueryCriteriaSql, objectParamWhere);
		List<ProcessDefinitionBehavior> processDefinitionList = new ArrayList<ProcessDefinitionBehavior>();
		for (Map<String, Object> dataMap : dataObj) {
			ProcessDefinitionBehavior processDefinition = getProcessDefinition(dataMap);
			processDefinitionList.add(processDefinition);
		}
		return processDefinitionList;
	}

	public long selectProcessDefinitionsCountByQueryCriteria(ProcessDefinitionQueryImpl processDefinitionQuery) {
		List<Object> objectParamWhere = new ArrayList<Object>();
		String selectProcessDefinitionsByQueryCriteriaSql = " select count(*)";
		selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteria(selectProcessDefinitionsByQueryCriteriaSql,
				processDefinitionQuery, objectParamWhere);
		Object returnObj = sqlCommand.queryForValue(selectProcessDefinitionsByQueryCriteriaSql, objectParamWhere);
		return Integer.parseInt(returnObj.toString());
	}

	private ResourceSet getResourceSet() {
		ResourceSet resourceSet = new ResourceSetImpl();
		(EPackage.Registry.INSTANCE).put("http://www.omg.org/spec/BPMN/20100524/MODEL", Bpmn2Package.eINSTANCE);
		(EPackage.Registry.INSTANCE).put("http://www.founderfix.com/fixflow", FixFlowPackage.eINSTANCE);
		(EPackage.Registry.INSTANCE).put("http://www.omg.org/spec/DD/20100524/DI", DiPackage.eINSTANCE);
		(EPackage.Registry.INSTANCE).put("http://www.omg.org/spec/DD/20100524/DC", DcPackage.eINSTANCE);
		(EPackage.Registry.INSTANCE).put("http://www.omg.org/spec/BPMN/20100524/DI", BpmnDiPackage.eINSTANCE);
		FixFlowPackage.eINSTANCE.eClass();
		FixFlowPackage xxxPackage = FixFlowPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.put(xxxPackage.getNsURI(), xxxPackage);
		Bpmn2ResourceFactoryImpl ddd = new Bpmn2ResourceFactoryImpl();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fixflow", ddd);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("bpmn", ddd);
		resourceSet.getPackageRegistry().put(xxxPackage.getNsURI(), xxxPackage);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("bpmn", ddd);
		return resourceSet;
	}

	private ProcessDefinitionBehavior getProcessDefinition(Map<String, Object> processDataMap) {
		String processId = StringUtil.getString(processDataMap.get("PROCESS_ID"));
		String deploymentId = StringUtil.getString(processDataMap.get("DEPLOYMENT_ID"));
		String resourceName = StringUtil.getString(processDataMap.get("RESOURCE_NAME"));
		String processKey = StringUtil.getString(processDataMap.get("PROCESS_KEY"));
		DeploymentCache deploymentCache = Context.getProcessEngineConfiguration().getDeploymentCache();
		ProcessDefinitionBehavior processDefinition = deploymentCache.getProcessDefinitionCache().get(processId);
		if (processDefinition == null) {
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
				ResourceSet resourceSet = getResourceSet();
				String filePath = this.getClass().getClassLoader().getResource("com/founder/fix/fixflow/expand/config/fixflowfile.bpmn").toString();
				Resource ddddResource = null;
				if (!filePath.startsWith("jar")) {
					try {
						filePath = java.net.URLDecoder.decode(ReflectUtil.getResource("com/founder/fix/fixflow/expand/config/fixflowfile.bpmn").getFile(),
								"utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
						throw new FixFlowException("流程定义文件加载失败！", e);
					}
					ddddResource = resourceSet.createResource(URI.createFileURI(filePath));
				} else {
					ddddResource = resourceSet.createResource(URI.createURI(filePath));
				}
				try {
					ddddResource.load(new ByteArrayInputStream(bytes), null);
				} catch (UnsupportedEncodingException e) {
					throw new FixFlowException("定义文件加载失败!", e);
				} catch (IOException e) {
					throw new FixFlowException("定义文件加载失败!", e);
				}
				DefinitionsBehavior definitions = (DefinitionsBehavior) ddddResource.getContents().get(0).eContents().get(0);
				definitions.setProcessId(processId);

				for (RootElement rootElement : definitions.getRootElements()) {
					if (rootElement instanceof ProcessDefinitionBehavior) {
						ProcessDefinitionBehavior processObj = (ProcessDefinitionBehavior) rootElement;
						if (processObj.getProcessDefinitionKey().equals(processKey)) {
							processDefinition = (ProcessDefinitionBehavior) rootElement;
							break;
						}
					}
				}
				processDefinition.setDefinitions(definitions);
				// 加载事件定义.
				loadEvent(processDefinition);
				// 加载数据变量
				loadVariable(processDefinition);
				// 设置FlowNode元素的子流程
				loadSubProcess(processDefinition);
				processDefinition.persistentInit(processDataMap);
				deploymentCache.addProcessDefinition(processDefinition);
				return processDefinition;

			}
			return null;
		} else {
			return processDefinition;
		}
	}

	private void setSubProcess(SubProcess subProcess) {
		for (FlowElement flowElement : subProcess.getFlowElements()) {
			if (flowElement instanceof FlowNode) {
				FlowNode flowNode = (FlowNode) flowElement;
				flowNode.setSubProcess(subProcess);
				if (flowElement instanceof SubProcess) {
					setSubProcess(subProcess);
				}
			}
		}
	}

	private void loadSubProcess(ProcessDefinitionBehavior process) {
		for (FlowElement flowElement : process.getFlowElements()) {
			if (flowElement instanceof SubProcess) {
				setSubProcess((SubProcess) flowElement);
			}
		}
	}

	private void loadVariable(ProcessDefinitionBehavior process) {
		process.setDataVariableMgmtDefinition(new DataVariableMgmtDefinition(process));
		addVariable(process, process, true);
		for (FlowElement flowElement : process.getFlowElements()) {
			if (flowElement instanceof SubProcess) {
				addVariable(flowElement, process, false);
				addSubProcessElement((SubProcess) flowElement, process);
			} else {
				addVariable(flowElement, process, false);
			}
		}
	}

	private void addVariable(BaseElement baseElement, ProcessDefinitionBehavior process, boolean isPubilc) {
		List<DataVariable> dataVariables = EMFExtensionUtil.getDataVariables(baseElement);
		if (dataVariables == null) {
			return;
		}
		for (DataVariable dataVariable : dataVariables) {
			process.getDataVariableMgmtDefinition().addDataVariableBehavior(new DataVariableBehavior(dataVariable, baseElement.getId(), isPubilc));
		}
	}

	private void addSubProcessElement(SubProcess subProcess, ProcessDefinitionBehavior process) {
		for (FlowElement flowElementSub : subProcess.getFlowElements()) {
			if (flowElementSub instanceof SubProcess) {
				addVariable(subProcess, process, false);
				addSubProcessElement((SubProcess) flowElementSub, process);
			} else {
				addVariable(flowElementSub, process, false);
			}
		}
	}

	private ProcessDefinitionBehavior loadEvent(ProcessDefinitionBehavior processDefinitionBehavior) {
		for (FlowElement flowElement : processDefinitionBehavior.getFlowElements()) {
			if (flowElement instanceof Activity) {
				loadEventObj(flowElement);
			}
		}
		loadEventObj(processDefinitionBehavior);
		return processDefinitionBehavior;
	}

	private void loadEventObj(BaseElement baseElement) {
		BaseElementImpl baseElementImpl = (BaseElementImpl) baseElement;
		if (baseElement instanceof SubProcess) {
			SubProcess subProcess = (SubProcess) baseElement;
			List<FlowElement> flowElements = subProcess.getFlowElements();
			for (FlowElement flowElement : flowElements) {
				if (flowElement instanceof Activity) {
					loadEventObj(flowElement);
				}
			}
		}
		List<ConnectorInstance> connectorInstances = baseElementImpl.getConnectorInstances();
		if (connectorInstances != null) {
			for (ConnectorInstance connectorInstance : connectorInstances) {
				String packageNamesString = connectorInstance.getPackageName();
				String classNameString = connectorInstance.getClassName();
				String eventTypeString = connectorInstance.getEventType();
				String connectorIdString = connectorInstance.getConnectorId();
				String connectorInstanceIdString = connectorInstance.getConnectorInstanceId();
				String connectorInstanceNameString = connectorInstance.getConnectorInstanceName();
				String errorHandlingString = connectorInstance.getErrorHandling();
				String errorCodeString = connectorInstance.getErrorCode();
				boolean isTimeExecute = connectorInstance.isIsTimeExecute();
				String documentationString = connectorInstance.getDocumentation().getValue();
				String skipExpression = null;
				if (connectorInstance.getSkipComment() != null) {
					skipExpression = connectorInstance.getSkipComment().getExpression().getValue();
				}
				String timeExpression = null;
				if (connectorInstance.getTimeExpression() != null) {
					timeExpression = connectorInstance.getTimeExpression().getExpression().getValue();
				}
				ConnectorInstanceBehavior connectorInstanceBehavior = new ConnectorInstanceBehavior();
				connectorInstanceBehavior.setConnectorId(connectorIdString);
				connectorInstanceBehavior.setConnectorInstanceId(connectorInstanceIdString);
				connectorInstanceBehavior.setClassName(classNameString);
				connectorInstanceBehavior.setConnectorInstanceName(connectorInstanceNameString);
				connectorInstanceBehavior.setDocumentation(documentationString);
				connectorInstanceBehavior.setErrorCode(errorCodeString);
				connectorInstanceBehavior.setErrorHandling(errorHandlingString);
				connectorInstanceBehavior.setEventType(eventTypeString);
				connectorInstanceBehavior.setPackageName(packageNamesString);
				connectorInstanceBehavior.setSkipExpression(skipExpression);
				if (isTimeExecute) {
					connectorInstanceBehavior.setTimeExecute(true);
					connectorInstanceBehavior.setTimeExpression(timeExpression);
				} else {
					connectorInstanceBehavior.setTimeExecute(false);
				}
				if (baseElementImpl.getEvents().get(eventTypeString) == null) {
					BaseElementEventImpl flowNodeEventImpl = new BaseElementEventImpl(eventTypeString);
					flowNodeEventImpl.addConnector(connectorInstanceBehavior);
					baseElementImpl.addEvent(flowNodeEventImpl);
				} else {
					baseElementImpl.getEvents().get(eventTypeString).addConnector(connectorInstanceBehavior);
				}
				List<ConnectorParameterInputs> connectorParameterInputs = connectorInstance.getConnectorParameterInputs();
				connectorInstanceBehavior.getConnectorParameterInputs().clear();
				connectorInstanceBehavior.getConnectorParameterInputs().addAll(connectorParameterInputs);
				List<ConnectorParameterOutputs> connectorParameterOutputs = connectorInstance.getConnectorParameterOutputs();
				connectorInstanceBehavior.getConnectorParameterOutputs().clear();
				connectorInstanceBehavior.getConnectorParameterOutputs().addAll(connectorParameterOutputs);
			}
		}
	}

	public void deleteProcessDefinitionsByDeploymentId(String deploymentId) {
		// 构建Where查询参数
		Object[] objectParamWhere = { deploymentId };
		sqlCommand.delete("FIXFLOW_DEF_PROCESSDEFINITION", " DEPLOYMENT_ID=?", objectParamWhere);
	}

	public List<Map<String, Object>> selectProcessDefinitionGroupKey() {
		String sqlTextString = "select * from (select max(VERSION) as VERSION,PROCESS_KEY from FIXFLOW_DEF_PROCESSDEFINITION GROUP BY PROCESS_KEY  ) t1 "+
	" left join FIXFLOW_DEF_PROCESSDEFINITION t2 on "+
 "t1.VERSION=t2.VERSION and t1.PROCESS_KEY=t2.PROCESS_KEY";
		List<Map<String, Object>> listMap = sqlCommand.queryForList(sqlTextString);
		return listMap;
	}

	@SuppressWarnings("unchecked")
	public ProcessDefinitionBehavior selectLatestProcessDefinitionByKeyAndVersion(Object parameter) {
		Map<String, Object> parameters = (Map<String, Object>) parameter;
		String processDefinitionKey = StringUtil.getString(parameters.get("processDefinitionKey"));
		int processDefinitionVersion = StringUtil.getInt(parameters.get("processDefinitionVersion"));
		String sqlText = "select * " + "from FIXFLOW_DEF_PROCESSDEFINITION " + "where PROCESS_KEY = ? AND VERSION=? ";
		// 构建查询参数
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(processDefinitionKey);
		objectParamWhere.add(processDefinitionVersion);
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);
		Map<String, Object> dataMap = dataObj.get(0);
		ProcessDefinitionBehavior processDefinition = getProcessDefinition(dataMap);
		return processDefinition;
	}

	@SuppressWarnings("unchecked")
	public Object selectProcessDefinitionByDeploymentAndKey(Object parameter) {
		Map<String, String> strmap = (Map<String, String>) parameter;
		String deploymentIdTemp = strmap.get("deploymentId");
		String processKeyTemp = strmap.get("processDefinitionKey");
		String sqlText = "select * " + "from FIXFLOW_DEF_PROCESSDEFINITION " + "where PROCESS_KEY = ? AND DEPLOYMENT_ID=? ";
		// 构建查询参数
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(processKeyTemp);
		objectParamWhere.add(deploymentIdTemp);
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);
		Map<String, Object> dataMap = dataObj.get(0);
		ProcessDefinitionBehavior processDefinition = getProcessDefinition(dataMap);

		return processDefinition;
	}

	public List<Map<String, Object>> findUserSubmitProcess(String userId, int number) {
		String sqlTextString = "select processdefinition_key from (" + "select p.processdefinition_key, max(p.start_time) start_time, p.initiator"
				+ " from "+ProcessInstanceObjKey.ProcessInstanceTableName()+" p" + " group by p.processdefinition_key, p.initiator" + " having p.initiator = ? "
				+ ")t order by start_time desc";
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(userId);
		
		
		sqlTextString = pagination.getPaginationSql(sqlTextString, 1,1+number, " processdefinition_key ", "");
		
		
		
		sqlTextString = "SELECT * FROM (SELECT PROCESS_KEY,MAX(PROCESS_NAME) AS PROCESS_NAME,MAX(CATEGORY) AS CATEGORY ,MAX(RESOURCE_NAME) AS RESOURCE_NAME,MAX(RESOURCE_ID) AS RESOURCE_ID,"+
		"MAX(DEPLOYMENT_ID) AS  DEPLOYMENT_ID,MAX(DIAGRAM_RESOURCE_NAME) AS DIAGRAM_RESOURCE_NAME ,MAX(PROCESS_ID) AS PROCESS_ID "
				+ "FROM FIXFLOW_DEF_PROCESSDEFINITION GROUP BY PROCESS_KEY) WHERE PROCESS_KEY IN ("+sqlTextString+")";
		
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlTextString, objectParamWhere);
		return dataObj;
	}

}
