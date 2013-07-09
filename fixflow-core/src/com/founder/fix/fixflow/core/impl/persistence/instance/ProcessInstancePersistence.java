package com.founder.fix.fixflow.core.impl.persistence.instance;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.UserTask;

import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.context.ContextInstanceImpl;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableInstance;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableMgmtInstance;
import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.persistence.definition.ProcessDefinitionPersistence;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceQueryImpl;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.IdentityLinkEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.task.TaskMgmtInstanceImpl;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.variable.VariableFlowTypeEntity;
import com.founder.fix.fixflow.core.impl.variable.VariableTransferEntity;
import com.founder.fix.fixflow.core.task.TaskMgmtInstance;
import com.founder.fix.fixflow.core.variable.VariableFlowType;

public class ProcessInstancePersistence {

	Connection connection;

	ProcessDefinitionBehavior processDefinition;

	ProcessInstanceEntity processInstance;

	java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 基础运行库库操作对象
	 */
	SqlCommand sqlCommand = null;

	public ProcessInstancePersistence(Connection connection) {
		this.connection = connection;
		// 初始化数据库操作类
		sqlCommand = new SqlCommand(connection);
	}
	


	public ProcessInstanceEntity getProcessInstance(String processInstanceId, ProcessDefinitionBehavior processDefinition) {

		
		String sqlText = "SELECT * FROM FIXFLOW_RUN_PROCESSINSTANECE WHERE PROCESSINSTANCE_ID=?";

		// 构建查询参数

		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(processInstanceId);

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);
		
		if(processDefinition==null){
			String processId=StringUtil.getString(dataObj.get(0).get("PROCESSDEFINITION_ID"));
			ProcessDefinitionPersistence processDefinitionPersistence=new ProcessDefinitionPersistence(connection);
			processDefinition=processDefinitionPersistence.selectProcessDefinitionById(processId);
			
		}
		
		this.processDefinition = (ProcessDefinitionBehavior) processDefinition;

		this.processInstance = new ProcessInstanceEntity(dataObj.get(0));
		this.processInstance.setProcessDefinition(processDefinition);
		
		
		
		/*this.processInstance.setId(StringUtil.getString(dataObj.get(0).get("PROCESSINSTANCE_ID")));
		this.processInstance.setStartTime(StringUtil.getDate(dataObj.get(0).get("START_TIME")));
		this.processInstance.setInitiator(StringUtil.getString(dataObj.get(0).get("INITIATOR")));
		this.processInstance.setStartAuthor(StringUtil.getString(dataObj.get(0).get("START_AUTHOR")));
		
		this.processInstance.setBizKey(StringUtil.getString(dataObj.get(0).get("BIZ_KEY")));
		this.processInstance.setSuspendedWithoutCascade(Boolean.getBoolean(StringUtil.getString(dataObj.get(0).get("ISSUSPENDED"))));
		this.processInstance.setParentProcessInstanceId(StringUtil.getString(dataObj.get(0).get("PARENT_INSTANCE_ID")));
		this.processInstance.setParentProcessInstanceTokenId(StringUtil.getString(dataObj.get(0).get("PARENT_INSTANCE_TOKEN_ID")));
		this.processInstance.setSubject(StringUtil.getString(dataObj.get(0).get("SUBJECT")));*/

		TaskMgmtInstance taskMgmtInstance = ProcessObjectFactory.FACTORYINSTANCE.createTaskMgmtInstance();

		
		processInstance.setTaskMgmtInstance(taskMgmtInstance);

		DataVariableMgmtInstance dataVariableMgmtInstance = new DataVariableMgmtInstance();
		dataVariableMgmtInstance.setProcessInstance(processInstance);

		ContextInstanceImpl contextInstanceImpl = new ContextInstanceImpl(this.processInstance);

		processInstance.setContextInstance(contextInstanceImpl);

		readToken();
		
		readFreeToken();

		// readRunningTrack();

		// readTaskInstance();

		// readContextInstance();

		return this.processInstance;

	}

	void readToken() {

		String sqlText = "SELECT * FROM FIXFLOW_RUN_TOKEN WHERE PROCESSINSTANCE_ID=? and PARENT_ID is null AND  FREETOKEN='false'";

		// 构建查询参数

		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(this.processInstance.getId());

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);

		// dbgr.closeConn();
		TokenEntity token = new TokenEntity();
		token.setId(dataObj.get(0).get("TOKEN_ID").toString());
		token.setProcessInstance(this.processInstance);

		// getBPMNElementMap

		token.setFlowNode((FlowNode) this.processDefinition.getDefinitions().getElement(dataObj.get(0).get("NODE_ID").toString()));
		token.setName(StringUtil.getString(dataObj.get(0).get("NAME")));
		token.setStartTime(StringUtil.getDate(dataObj.get(0).get("START_TIME")));
		token.setSubProcessRootTokenWithoutCascade(StringUtil.getBoolean(dataObj.get(0).get("ISSUBPROCESSROOTTOKEN")));
		token.setNodeEnterTime(StringUtil.getDate(dataObj.get(0).get("NODEENTER_TIME")));
		
		token.setEndTime(StringUtil.getDate(dataObj.get(0).get("END_TIME")));

		token.setAbleToReactivateParent(dataObj.get(0).get("ISABLETOREACTIVATEPARENT").toString().equals("true"));

		token.setSuspended(dataObj.get(0).get("ISSUSPENDED").toString().equals("true"));

		token.setLocked(dataObj.get(0).get("TOKEN_LOCK").toString().equals("true"));
		
		token.setFreeToken(false);
		
		

		// ISABLETOREACTIVATEPARENT
		readTaskInstance(token);
		// token.addChild(token)

		readTokenChildren(token);

		this.processInstance.setRootToken(token);
		this.processInstance.addTokenList(token);
	}
	
	void readFreeToken() {

		String sqlText = "SELECT * FROM FIXFLOW_RUN_TOKEN WHERE PROCESSINSTANCE_ID=? and FREETOKEN='true'";

		// 构建查询参数

		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(this.processInstance.getId());

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);

		
		for (Map<String, Object> map : dataObj) {
			// dbgr.closeConn();
			TokenEntity token = new TokenEntity();
			token.setId(map.get("TOKEN_ID").toString());
			token.setProcessInstance(this.processInstance);

			// getBPMNElementMap

			token.setFlowNode((FlowNode) this.processDefinition.getDefinitions().getElement(map.get("NODE_ID").toString()));
			token.setName(StringUtil.getString(map.get("NAME")));
			token.setStartTime(StringUtil.getDate(map.get("START_TIME")));
			token.setSubProcessRootTokenWithoutCascade(StringUtil.getBoolean(map.get("ISSUBPROCESSROOTTOKEN")));
			token.setNodeEnterTime(StringUtil.getDate(map.get("NODEENTER_TIME")));

			token.setEndTime(StringUtil.getDate(map.get("END_TIME")));

			token.setAbleToReactivateParent(StringUtil.getBoolean(map.get("ISABLETOREACTIVATEPARENT")));

			token.setSuspended(StringUtil.getBoolean(map.get("ISSUSPENDED")));

			token.setLocked(map.get("TOKEN_LOCK").toString().equals("true"));
			
			token.setFreeToken(true);
			
			if(StringUtil.getString(map.get("PARENT_FREETOKEN_ID"))!=null){
				TokenEntity ptoken=this.processInstance.getTokenMap().get(StringUtil.getString(map.get("PARENT_FREETOKEN_ID")));
				token.setParentFreeToken(ptoken);
				ptoken.getFreeChildren().put(token.getId(), token);
			}

			// ISABLETOREACTIVATEPARENT
			readTaskInstance(token);
			// token.addChild(token)

			readTokenChildren(token);

			//this.processInstance.setRootToken(token);
			this.processInstance.addTokenList(token);
		}
		
	}

	void readTokenChildren(TokenEntity tokenParent) {

		String sqlText = "SELECT * FROM FIXFLOW_RUN_TOKEN WHERE PARENT_ID=? AND PROCESSINSTANCE_ID=? AND END_TIME IS NULL";

		// 构建查询参数
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(tokenParent.getId());
		objectParamWhere.add(this.processInstance.getId());

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);

		for (Map<String, Object> mapObj : dataObj) {

			TokenEntity token = new TokenEntity();
			token.setId(mapObj.get("TOKEN_ID").toString());
			token.setProcessInstance(this.processInstance);
			token.setFlowNode((FlowNode) this.processDefinition.getDefinitions().getElement(mapObj.get("NODE_ID").toString()));
			token.setName(StringUtil.getString(mapObj.get("NAME")));
			token.setStartTime(StringUtil.getDate(dataObj.get(0).get("START_TIME")));
			token.setSubProcessRootTokenWithoutCascade(StringUtil.getBoolean(dataObj.get(0).get("ISSUBPROCESSROOTTOKEN")));
			token.setNodeEnterTime(StringUtil.getDate(dataObj.get(0).get("NODEENTER_TIME")));

			token.setEndTime(StringUtil.getDate(dataObj.get(0).get("END_TIME")));

			token.setAbleToReactivateParent(mapObj.get("ISABLETOREACTIVATEPARENT").toString().equals("true"));

			token.setSuspended(mapObj.get("ISSUSPENDED").toString().equals("true"));

			token.setLocked(mapObj.get("TOKEN_LOCK").toString().equals("true"));

			tokenParent.addChild(token);
			// ISABLETOREACTIVATEPARENT
			token.setParent(tokenParent);

			this.readTaskInstance(token);

			this.processInstance.addTokenList(token);

			// 构建查询参数
			List<Object> objectParamWhereTemp = new ArrayList<Object>();
			objectParamWhereTemp.add(tokenParent.getId());
			// 设置查询字符串
			String sqlTextTemp = "SELECT COUNT(1) FROM FIXFLOW_RUN_TOKEN WHERE PARENT_ID=?";
			// 执行查询流程是Sql语句,判断流程实例是否存在于数据库中.
			int rowNum = Integer.parseInt(sqlCommand.queryForValue(sqlTextTemp, objectParamWhereTemp).toString());

			if (rowNum > 0) {
				readTokenChildren(token);
			}

			// readTokenChildren(token);
		}

		// workFlowInstance.setRootToken(token);

	}

	void readTaskInstance(TokenEntity token) {

		TaskMgmtInstanceImpl taskMgmtInstance = (TaskMgmtInstanceImpl) this.processInstance.getTaskMgmtInstance();

		if (!(token.getFlowNode() instanceof UserTask)) {
			return;
		}

		String sqlText = "SELECT * FROM FIXFLOW_RUN_TAKSINSTANECE WHERE TOKEN_ID=? AND END_TIME is null";

		// 构建查询参数
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(token.getId());

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);

		if (dataObj.size() <= 0) {
			return;
		}

		for (Map<String, Object> entityMap : dataObj) {
			
			TaskInstancePersistence taskInstancePersistence=new TaskInstancePersistence(connection);
			TaskInstanceEntity taskInstanceEntity =taskInstancePersistence.getTaskInstanceEntity(entityMap);		
			taskInstanceEntity.setToken(token);
			taskInstanceEntity.setTaskDefinition(((UserTaskBehavior) token.getFlowNode()).getTaskDefinition());
			taskMgmtInstance.addTaskInstanceEntity(taskInstanceEntity);

		}

	}

	public void saveProcessInstance(PersistentObject persistentObject) {

		ProcessInstanceEntity processInstance = (ProcessInstanceEntity) persistentObject;

		// 获取流程实例编号
		String processInstanceId = processInstance.getId();
		// 构建查询参数
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(processInstanceId);
		// 设置查询字符串
		String sqlText = "SELECT count(1) FROM FIXFLOW_RUN_PROCESSINSTANECE WHERE PROCESSINSTANCE_ID=?";
		// 执行查询流程是Sql语句,判断流程实例是否存在于数据库中.
		int rowNum = Integer.parseInt(sqlCommand.queryForValue(sqlText, objectParamWhere).toString());

		if (rowNum == 0) {
			// 数据库不存在这条流程实例,则执行创建新实例的方法.

			// 添加新的流程实例
			addProcessInstance(processInstance);
			// 添加新的令牌
			addToken(processInstance.getRootToken());

		} else {
			// 当数据库中已经存在这个流程实例

			// 更新流程实例
			updateProcessInstance(processInstance);
			// 更新令牌
			updateToken(processInstance.getRootToken());

		}
		saveFreeToken(processInstance);
		

		// 存储任务实例
		saveTaskInstance(processInstance);
		// 存储环流程环境变量
		saveVariableInstance(processInstance);

	}

	

	void addProcessInstance(ProcessInstanceEntity processInstance) {


		 Map<String, Object> objectParam=processInstance.getPersistentDbMap();

		
		// 执行插入语句
		sqlCommand.insert("FIXFLOW_RUN_PROCESSINSTANECE", objectParam);

	}

	void updateProcessInstance(ProcessInstanceEntity processInstance) {

		Map<String, Object> objectParam=processInstance.getPersistentDbMap();

		// 构建Where查询参数
		Object[] objectParamWhere = { processInstance.getId() };
		// 执行插入语句
		sqlCommand.update("FIXFLOW_RUN_PROCESSINSTANECE", objectParam, " PROCESSINSTANCE_ID=?", objectParamWhere);

	}
	
	
	void saveFreeToken(ProcessInstanceEntity processInstance){
		
		for (TokenEntity token : processInstance.getTokenList()) {
			if(token.isFreeToken()){
				List<Object> tcObjectParamWhere = new ArrayList<Object>();
				tcObjectParamWhere.add(token.getId());

				// 设置查询字符串
				String sqlText = "SELECT COUNT(1) FROM FIXFLOW_RUN_TOKEN WHERE TOKEN_ID=?";
				// 执行查询Sql语句,判断子令牌是否存在于数据库中.
				int tcRowNum = Integer.parseInt(sqlCommand.queryForValue(sqlText, tcObjectParamWhere).toString());

				if (tcRowNum > 0) {
					this.updateToken(token);
				} else {
					this.addToken(token);
				}
			}
		}
		
	}

	void addToken(TokenEntity token) {

		Map<String, Object> objectParam=token.getPersistentDbMap();

		// 执行插入语句
		sqlCommand.insert("FIXFLOW_RUN_TOKEN", objectParam);

		if (token.getChildren() != null) {
			for (String tokenKey : token.getChildren().keySet()) {
				TokenEntity tokenChildren = token.getChildren().get(tokenKey);
				List<Object> tcObjectParamWhere = new ArrayList<Object>();
				tcObjectParamWhere.add(tokenChildren.getId());

				// 设置查询字符串
				String sqlText = "SELECT COUNT(1) FROM FIXFLOW_RUN_TOKEN WHERE TOKEN_ID=?";
				// 执行查询Sql语句,判断子令牌是否存在于数据库中.
				int tcRowNum = Integer.parseInt(sqlCommand.queryForValue(sqlText, tcObjectParamWhere).toString());

				if (tcRowNum > 0) {
					this.updateToken(tokenChildren);
				} else {
					this.addToken(tokenChildren);
				}
			}
		}

		
		
		
	}

	void updateToken(TokenEntity token) {

		Map<String, Object> objectParam=token.getPersistentDbMap();


		// 构建Where查询参数
		Object[] objectParamWhere = { token.getId() };

		// 执行插入语句
		sqlCommand.update("FIXFLOW_RUN_TOKEN", objectParam, " TOKEN_ID=?", objectParamWhere);

		if (token.getChildren() != null) {
			for (String tokenKey : token.getChildren().keySet()) {
				TokenEntity tokenChildren = token.getChildren().get(tokenKey);
				List<Object> tcObjectParamWhere = new ArrayList<Object>();
				tcObjectParamWhere.add(tokenChildren.getId());

				// 设置查询字符串
				String sqlText = "SELECT COUNT(1) FROM FIXFLOW_RUN_TOKEN WHERE TOKEN_ID=?";
				// 执行查询Sql语句,判断子令牌是否存在于数据库中.
				int tcRowNum = Integer.parseInt(sqlCommand.queryForValue(sqlText, tcObjectParamWhere).toString());

				if (tcRowNum > 0) {
					this.updateToken(tokenChildren);
				} else {
					this.addToken(tokenChildren);
				}
			}
		}
	

	}

	void saveTaskInstance(ProcessInstanceEntity processInstance) {

		if (processInstance.getTaskMgmtInstance().getTaskInstanceEntitys() != null) {

			for (TaskInstanceEntity taskInstance : processInstance.getTaskMgmtInstance().getTaskInstanceEntitys()) {

				TaskInstancePersistence taskInstancePersistence=new TaskInstancePersistence(connection);
				taskInstancePersistence.saveTaskInstance(taskInstance);

				saveIdentityLink(taskInstance);
			}

		}
	}

	void saveIdentityLink(TaskInstanceEntity taskInstance) {
		IdentityLinkPersistence identityLinkPersistence=new IdentityLinkPersistence(connection);
		for (IdentityLinkEntity identityLink : taskInstance.getTaskIdentityLinkEntitys()) {

			identityLinkPersistence.saveIdentityLink(identityLink);
		}
	}

	

	/**
	 * 持久化流程变量
	 * @param processInstance
	 */
	void saveVariableInstance(ProcessInstanceEntity processInstance) {

		String processInstanceId=processInstance.getId();
		VariableTransferEntity variableTransferEntity = new VariableTransferEntity();
		
		
		
		for (DataVariableInstance dataVariableInstance : processInstance.getDataVariableMgmtInstance().getDataVariableInstances()) {
			if (dataVariableInstance.isPersistence()) {
				
				variableTransferEntity.addVariable(dataVariableInstance.getId(), dataVariableInstance.getExpressionValue());
				
			}
		}

		if (processInstanceId != null && !processInstanceId.equals("")) {
			VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.PROCESSINSTANCE, processInstanceId);
			variableTransferEntity.addVariableFlowType(variableFlowTypeEntity);
		}
		
		VariablePersistence variablePersistence=new VariablePersistence(connection);
		variablePersistence.saveVariable(variableTransferEntity);
	}

	private String selectProcessInstanceByQueryCriteriaSql(String sqlString, ProcessInstanceQueryImpl processInstanceQuery, Page page, List<Object> objectParamWhere) {

		sqlString = sqlString + " FROM FIXFLOW_RUN_PROCESSINSTANECE E ";
		
		//自定义扩展查询
		if(processInstanceQuery.getQueryExpandTo()!=null&&processInstanceQuery.getQueryExpandTo().getLeftJoinSql()!=null&&!processInstanceQuery.getQueryExpandTo().getLeftJoinSql().equals("")){
			sqlString=sqlString+processInstanceQuery.getQueryExpandTo().getLeftJoinSql();
		}
		
		
	

		sqlString = sqlString + " WHERE 1=1";
		
		
		//自定义扩展查询
		if(processInstanceQuery.getQueryExpandTo()!=null&&processInstanceQuery.getQueryExpandTo().getWhereSql()!=null&&!processInstanceQuery.getQueryExpandTo().getWhereSql().equals("")){
			sqlString=sqlString+" and "+processInstanceQuery.getQueryExpandTo().getWhereSql();
			if(processInstanceQuery.getQueryExpandTo().getWhereSqlObj()!=null&&processInstanceQuery.getQueryExpandTo().getWhereSqlObj().size()>0){
				objectParamWhere.add(processInstanceQuery.getQueryExpandTo().getWhereSqlObj());
			}
		}

		if (processInstanceQuery.getBusinessKey() != null) {
			sqlString = sqlString + " and E.BIZ_KEY=? ";
			objectParamWhere.add(processInstanceQuery.getBusinessKey());
		}
		if (processInstanceQuery.getIsPigeonhole() != null) {
			sqlString = sqlString + " and (E.ISPIGEONHOLE=? or E.ISPIGEONHOLE IS NULL) ";
			objectParamWhere.add(processInstanceQuery.getIsPigeonhole());
		}
		
		if (processInstanceQuery.getIsEnd() != null) {
			if(StringUtil.getBoolean(processInstanceQuery.getIsEnd())){
				sqlString = sqlString + " AND E.END_TIME IS NOT NULL ";
				//objectParamWhere.add(processInstanceQuery.getIsPigeonhole());
			}
			else{
				sqlString = sqlString + " AND E.END_TIME IS NULL ";
				//objectParamWhere.add(processInstanceQuery.getIsEnd());
			}
			
		}

		if (processInstanceQuery.getProcessInstanceId() != null) {
			sqlString = sqlString + " and E.PROCESSINSTANCE_ID=? ";
			objectParamWhere.add(processInstanceQuery.getProcessInstanceId());
		}
		
		
		
		
		if (processInstanceQuery.getProcessInstanceId() != null) {
			if(processInstanceQuery.isContainsSubProcess()){
				
				
				//这个地方需要用到递归去寻找所有的子流程
				
				List<Object> dataList=new ArrayList<Object>();
				
				dataList.add(processInstanceQuery.getProcessInstanceId());
				StringBuffer  processInstanceIdList=new StringBuffer();
	
				List<Map<String, Object>> dataListMaps=sqlCommand.queryForList("SELECT * FROM FIXFLOW_RUN_PROCESSINSTANECE WHERE PARENT_INSTANCE_ID=?", dataList);
				processInstanceIdList.append("'"+processInstanceQuery.getProcessInstanceId()+"'");
				if(dataListMaps.size()>0){
					
					
					
					getSubProcessId(processInstanceQuery.getProcessInstanceId(),processInstanceIdList);
				}
				
				sqlString = sqlString + " and E.PROCESSINSTANCE_ID in ("+processInstanceIdList.toString()+") ";
				
			}
			else{
				
				sqlString = sqlString + " and E.PROCESSINSTANCE_ID=? ";
					objectParamWhere.add(processInstanceQuery.getProcessInstanceId());
				
			}
		}

		if (processInstanceQuery.getProcessDefinitionId() != null) {
			sqlString = sqlString + " and E.PROCESSDEFINITION_ID=? ";
			objectParamWhere.add(processInstanceQuery.getProcessDefinitionId());
		}

		if (processInstanceQuery.getProcessDefinitionKey() != null) {
			sqlString = sqlString + " and E.PROCESSDEFINITION_KEY=? ";
			objectParamWhere.add(processInstanceQuery.getProcessDefinitionKey());
		}
		
		
		if(processInstanceQuery.getProcessDefinitionKeyList().size()>0){
			
			
			List<String> processDefinitionKeyList = processInstanceQuery.getProcessDefinitionKeyList();

			sqlString = sqlString + " and E.PROCESSDEFINITION_KEY IN (";

			for (int i = 0; i < processDefinitionKeyList.size(); i++) {
				if (i == processDefinitionKeyList.size() - 1) {
					String processDefinitionKey = processDefinitionKeyList.get(i);
					sqlString = sqlString + " ? ) ";
					objectParamWhere.add(processDefinitionKey.toString());
				} else {
					String processDefinitionKey = processDefinitionKeyList.get(i);
					sqlString = sqlString + " ?, ";
					objectParamWhere.add(processDefinitionKey.toString());
				}

			}
		
		}

		if (processInstanceQuery.getInitiator() != null) {
			sqlString = sqlString + " and E.INITIATOR=? ";
			objectParamWhere.add(processInstanceQuery.getInitiator());
		}
		
		if (processInstanceQuery.getSubject() != null) {
			sqlString = sqlString + " and E.SUBJECT=?";
			objectParamWhere.add(processInstanceQuery.getSubject());
		}
		
		if (processInstanceQuery.getSubjectLike() != null) {
			sqlString = sqlString + " and E.SUBJECT LIKE '%"+processInstanceQuery.getSubjectLike()+"%' ";
		}
		
		if (processInstanceQuery.getInitiatorLike() != null) {
			sqlString = sqlString + " and E.INITIATOR LIKE '%"+processInstanceQuery.getInitiatorLike()+"%' ";
		}
		
		
		
		
		if (processInstanceQuery.getStartTime() != null) {

			sqlString = sqlString + " and  E.START_TIME=? ";
			objectParamWhere.add(processInstanceQuery.getStartTime());
		}
		
		if (processInstanceQuery.getIsSuspended() != null) {

			sqlString = sqlString + " and  E.ISSUSPENDED=? ";
			objectParamWhere.add(processInstanceQuery.getIsSuspended());
		}
		
		if (processInstanceQuery.getStartTimeBefore() != null) {

			sqlString = sqlString + " and  E.START_TIME>=? ";
			objectParamWhere.add(processInstanceQuery.getStartTimeBefore());
		}

		if (processInstanceQuery.getStartTimeAfter() != null) {

			sqlString = sqlString + " and  E.START_TIME<=? ";
			objectParamWhere.add(processInstanceQuery.getStartTimeAfter());
		}
		
		
		
		if(processInstanceQuery.getTaskParticipants() !=null ){
			
			
			sqlString = sqlString + " and E.PROCESSINSTANCE_ID in (SELECT distinct(F.PROCESSINSTANCE_ID) FROM FIXFLOW_RUN_TAKSINSTANECE F WHERE F.ASSIGNEE=? and F.END_TIME is not null) ";
			objectParamWhere.add(processInstanceQuery.getTaskParticipants());
		}

		return sqlString;
	}
	
	private void getSubProcessId(String processInstanceId,StringBuffer processInstanceIdList){
		//这个地方需要用到递归去寻找所有的子流程
		List<Object> dataList=new ArrayList<Object>();
		
		
		dataList.add(processInstanceId);
		
		List<Map<String, Object>> dataListMaps=sqlCommand.queryForList("SELECT * FROM FIXFLOW_RUN_PROCESSINSTANECE WHERE PARENT_INSTANCE_ID=?", dataList);
		if(dataListMaps.size()>0){
			
			for (Map<String, Object> map : dataListMaps) {
				processInstanceIdList.append(",'"+StringUtil.getString(map.get("PROCESSINSTANCE_ID"))+"'");
				getSubProcessId(StringUtil.getString(map.get("PROCESSINSTANCE_ID")),processInstanceIdList);
			}

		}

	}

	public List<ProcessInstanceEntity> selectProcessInstanceByQueryCriteria(ProcessInstanceQueryImpl processInstanceQuery, Page page) {

		String sqlString = " select " + Context.getProcessEngineConfiguration().getDbConfig().getDbSqlMap().get("topOrderBy") + " E.* ";
		
		if(processInstanceQuery.getQueryExpandTo()!=null&&processInstanceQuery.getQueryExpandTo().getFieldSql()!=null&&!processInstanceQuery.getQueryExpandTo().getFieldSql().equals("")){
			sqlString=sqlString+" , "+processInstanceQuery.getQueryExpandTo().getFieldSql();
		}
		
		
		List<Object> objectParamWhere = new ArrayList<Object>();
		sqlString = selectProcessInstanceByQueryCriteriaSql(sqlString, processInstanceQuery, page, objectParamWhere);

		if (processInstanceQuery.getOrderBy() != null) {

			sqlString = sqlString + " order by " + processInstanceQuery.getOrderBy().toString();
		}

		if (page != null) {
			Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();
			sqlString = pagination.getPaginationSql(sqlString, page.getFirstResult(), page.getMaxResults(), "*");
		}
		
		if (processInstanceQuery.getOrderBy() != null&&page != null) {
			
			
			String orderBySql=processInstanceQuery.getOrderBy();
			String orderBySqlFin="";
			if(orderBySql.indexOf(",")>=0){
				
				String[] orderBySqlTemp=orderBySql.split(",");
				for (String orderByObj : orderBySqlTemp) {
					if(orderBySqlFin.equals("")){
						orderBySqlFin=orderBySqlFin+orderByObj.substring(orderByObj.indexOf(".")+1,orderByObj.length());
					}
					else{
						orderBySqlFin=orderBySqlFin+","+orderByObj.substring(orderByObj.indexOf(".")+1,orderByObj.length());
					}
					
				}
				sqlString = sqlString + " order by " + orderBySqlFin;
				
			}else{
				sqlString = sqlString + " order by " + processInstanceQuery.getOrderBy().toString().substring(2);
			}
			
			 
			//sqlString = sqlString + " order by " + processInstanceQuery.getOrderBy().toString().substring(2);
		}

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlString, objectParamWhere);

		List<ProcessInstanceEntity> processInstancePersistenceToList = new ArrayList<ProcessInstanceEntity>();

		for (Map<String, Object> dataMap : dataObj) {

			ProcessInstanceEntity processInstancePersistenceTo = new ProcessInstanceEntity(dataMap);

			processInstancePersistenceToList.add(processInstancePersistenceTo);
		}

		return processInstancePersistenceToList;

	}

	public long selectProcessInstanceCountByQueryCriteria(ProcessInstanceQueryImpl processInstanceQuery) {
		String sqlString = " select count(*) ";
		List<Object> objectParamWhere = new ArrayList<Object>();
		sqlString = selectProcessInstanceByQueryCriteriaSql(sqlString, processInstanceQuery, null, objectParamWhere);
		Object returnObj = sqlCommand.queryForValue(sqlString, objectParamWhere);
		return Integer.parseInt(returnObj.toString());
	}


	public List<Map<String,Object>> selectProcessPerformance(String startTime, String endTime,String processKey, Page page) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		Date startdate = null;
		Date enddate = null;
		long diff = 0;
		int days = 0;
		try {
			startdate=sdf.parse(startTime);
			enddate = sdf.parse(endTime);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(enddate);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			enddate = calendar.getTime();
			
			diff = enddate.getTime() -startdate.getTime();  
		    days = (int) (diff / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("select process_key,sum(sumwf) as SUMWF,sum(swf) as SWF,sum(ewf) as EWF,sum(wfcount) as WFCOUNT,sum(avgdate) as AVGDATE from ( " +
				"SELECT b.process_key,count(a.processinstance_id) as sumwf,0 as swf,0 as ewf,0 as wfcount,0 as avgdate FROM FIXFLOW_RUN_PROCESSINSTANECE a,fixflow_def_processdefinition b  " +
				"where a.processdefinition_id=b.process_id " +
				" and a.start_time>=? and a.start_time<=? " +
				" and b.process_key=? " +
				" group by b.process_key " +
				" union " +
				" select b.process_key , 0,count(a.processinstance_id) as swf,0,0,0 from fixflow_run_processinstanece a, fixflow_def_processdefinition b " +
				" where end_time is null and a.processdefinition_id=b.process_id" +
				" and a.start_time>=? and a.start_time<=? " +
				" and b.process_key=? " +
				" group by b.process_key " +
				" union " +
				" select b.process_key , 0,0,count(a.processinstance_id) as ewf,0,0 from fixflow_run_processinstanece a, fixflow_def_processdefinition b " +
				" where end_time is not null and a.processdefinition_id=b.process_id " +
				" and a.start_time>=? and a.start_time<=? " +
				" and b.process_key=? " +
				" group by b.process_key " +
				" union " +
				" select b.process_key , 0,0,0,round(count(a.processinstance_id)/(?),2) as wfcount,0 from fixflow_run_processinstanece a, fixflow_def_processdefinition b " +
				" where a.processdefinition_id=b.process_id " +
				" and a.start_time>=? and a.start_time<=? " +
				" group by b.process_key " +
				" union " +
				" select b.process_key,0,0,0,0,round(avg("+pagination.getLocalismSql("processperformance", null)+"),2) from fixflow_run_processinstanece a, fixflow_def_processdefinition b " +
				" where a.processdefinition_id=b.process_id " +
				" and a.start_time>=? and a.start_time<=? " +
				" group by b.process_key " +
				" )t group by process_key )w");
		
		
		List<Object> objectParamWhere = new ArrayList<Object>();
		
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(processKey);
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(processKey);
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(processKey);
		objectParamWhere.add(days);
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(processKey);
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(processKey);
		
		//增加分页信息
		String finalsql = "";
		finalsql = pagination.getPaginationSql(stringBuffer.toString(), page.getFirstResult(), page.getMaxResults(), "*");

		SqlCommand sqlCommand=new SqlCommand(Context.getDbConnection());
		return sqlCommand.queryForList(finalsql,objectParamWhere);
	}
	
	public List<Map<String,Object>> selectProcessPerformance(String startTime, String endTime, Page page) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		Date startdate = null;
		Date enddate = null;
		long diff = 0;
		int days = 0;
		try {
			startdate=sdf.parse(startTime);
			enddate = sdf.parse(endTime);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(enddate);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			enddate = calendar.getTime();
			
			diff = enddate.getTime() -startdate.getTime();  
		    days = (int) (diff / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("select w.pk as PK,w.sumwf as SUMWF,w.swf as SWF,w.ewf as EWF,(w.ewf/w.sumwf) as PER,w.wfcount as WFCOUNT,w.avgdate as AVGDATE from(" +
				"select process_key as pk,sum(sumwf) as sumwf,sum(swf) as swf,sum(ewf) as ewf,sum(wfcount) as wfcount,sum(avgdate) as avgdate from ( " +
				"SELECT b.process_key,count(a.processinstance_id) as sumwf,0 as swf,0 as ewf,0 as wfcount,0 as avgdate FROM FIXFLOW_RUN_PROCESSINSTANECE a,fixflow_def_processdefinition b  " +
				"where a.processdefinition_id=b.process_id " +
				" and a.start_time>=? and a.start_time<=? " +
				" group by b.process_key " +
				" union " +
				" select b.process_key , 0,count(a.processinstance_id) as swf,0,0,0 from fixflow_run_processinstanece a, fixflow_def_processdefinition b " +
				" where end_time is null and a.processdefinition_id=b.process_id" +
				" and a.start_time>=? and a.start_time<=? " +
				" group by b.process_key " +
				" union " +
				" select b.process_key , 0,0,count(a.processinstance_id) as ewf,0,0 from fixflow_run_processinstanece a, fixflow_def_processdefinition b " +
				" where end_time is not null and a.processdefinition_id=b.process_id " +
				" and a.start_time>=? and a.start_time<=? " +
				" group by b.process_key " +
				" union " +
				" select b.process_key , 0,0,0,round(count(a.processinstance_id)/(?),2) as wfcount,0 from fixflow_run_processinstanece a, fixflow_def_processdefinition b " +
				" where a.processdefinition_id=b.process_id " +
				" and a.start_time>=? and a.start_time<=? " +
				" group by b.process_key " +
				" union " +
				" select b.process_key,0,0,0,0,round(avg("+pagination.getLocalismSql("processperformance", null)+"),2) from fixflow_run_processinstanece a, fixflow_def_processdefinition b " +
				" where a.processdefinition_id=b.process_id " +
				" and a.start_time>=? and a.start_time<=? " +
				" group by b.process_key " +
				" )t group by process_key )w");
		
		
		List<Object> objectParamWhere = new ArrayList<Object>();
		
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(days);
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		
		//增加分页信息
		String finalsql = "";
		finalsql = pagination.getPaginationSql(stringBuffer.toString(), page.getFirstResult(), page.getMaxResults(), "*");

		SqlCommand sqlCommand=new SqlCommand(Context.getDbConnection());
		return sqlCommand.queryForList(finalsql,objectParamWhere);
	}
	
	public Object selectProcessPerformance2(String startTime, String endTime) {
 		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		Date startdate = null;
		Date enddate = null;
		long diff = 0;
		int days = 0;
		try {
			startdate=sdf.parse(startTime);
			enddate = sdf.parse(endTime);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(enddate);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			enddate = calendar.getTime();
			
			diff = enddate.getTime() -startdate.getTime();  
		    days = (int) (diff / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		StringBuffer stringBuffer = new StringBuffer();
		Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();
		stringBuffer.append("select count(*) as COUNT from(" +
				"select process_key,sum(sumwf) as sumwf,sum(swf) as swf,sum(ewf) as ewf,sum(wfcount) as wfcount,sum(avgdate) as avgdate from ( " +
				"SELECT b.process_key,count(a.processinstance_id) as sumwf,0 as swf,0 as ewf,0 as wfcount,0 as avgdate FROM FIXFLOW_RUN_PROCESSINSTANECE a,fixflow_def_processdefinition b  " +
				"where a.processdefinition_id=b.process_id " +
				" and a.start_time>=? and a.start_time<=? " +
				" group by b.process_key " +
				" union " +
				" select b.process_key , 0,count(a.processinstance_id) as swf,0,0,0 from fixflow_run_processinstanece a, fixflow_def_processdefinition b " +
				" where end_time is null and a.processdefinition_id=b.process_id" +
				" and a.start_time>=? and a.start_time<=? " +
				" group by b.process_key " +
				" union " +
				" select b.process_key , 0,0,count(a.processinstance_id) as ewf,0,0 from fixflow_run_processinstanece a, fixflow_def_processdefinition b " +
				" where end_time is not null and a.processdefinition_id=b.process_id " +
				" and a.start_time>=? and a.start_time<=? " +
				" group by b.process_key " +
				" union " +
				" select b.process_key , 0,0,0,round(count(a.processinstance_id)/(?),2) as wfcount,0 from fixflow_run_processinstanece a, fixflow_def_processdefinition b " +
				" where a.processdefinition_id=b.process_id " +
				" and a.start_time>=? and a.start_time<=? " +
				" group by b.process_key " +
				" union " +
				" select b.process_key,0,0,0,0,round(avg("+pagination.getLocalismSql("processperformance", null)+"),2) from fixflow_run_processinstanece a, fixflow_def_processdefinition b " +
				" where a.processdefinition_id=b.process_id " +
				" and a.start_time>=? and a.start_time<=? " +
				" group by b.process_key " +
				" )t group by process_key )w");
		
		
		List<Object> objectParamWhere = new ArrayList<Object>();
		
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(days);
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		
		SqlCommand sqlCommand=new SqlCommand(Context.getDbConnection());
		return sqlCommand.queryForValue(stringBuffer.toString(),objectParamWhere);
	}

	@SuppressWarnings("deprecation")
	public List<Map<String,Object>> selectProcessPerformance(String[] processKey, String startTime, String endTime) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		Date startdate = null;
		Date enddate = null;
		long diff = 0;
		int days = 0;
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Calendar cal3 = Calendar.getInstance();
		try {
			startdate=sdf.parse(startTime);
			enddate = sdf.parse(endTime);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(enddate);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			enddate = calendar.getTime();
			
			diff = enddate.getTime() -startdate.getTime();  
		    days = (int) (diff / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		List<Object> objectParamWhere = new ArrayList<Object>();
		StringBuffer stringBuffer = new StringBuffer();
		
		for(int i=0;i<days;i++) {
			if(i>0) {
				stringBuffer.append(" union all ");
			}
		
		cal3.setTime(startdate);
		cal3.add(Calendar.DAY_OF_YEAR, i);
		stringBuffer.append(
				"SELECT b.process_key as PROCESS_KEY,count(a.processinstance_id) as SUMWF,'" + sdf.format(cal3.getTime()) + "' as WFDATE FROM FIXFLOW_RUN_PROCESSINSTANECE a,fixflow_def_processdefinition b  " +
				" where a.processdefinition_id=b.process_id " +
				" and b.process_key in(");
		
		for(int j=0;j<processKey.length;j++) {
			if(j==0) {
				stringBuffer.append("?");
			}else {
				stringBuffer.append(",?");
			}
			objectParamWhere.add(processKey[j]);
		}
		
		stringBuffer.append(")");
		
		
			stringBuffer.append(" and a.start_time>=? and a.start_time<=?");
			startdate.setHours(0);
			startdate.setMinutes(0);
			startdate.setSeconds(0);
			cal.setTime(startdate);
			cal.add(Calendar.DAY_OF_YEAR, i);
			objectParamWhere.add(cal.getTime());
			cal2.setTime(startdate);
			cal2.add(Calendar.DAY_OF_YEAR, i+1);
			objectParamWhere.add(cal2.getTime());
		
		
		stringBuffer.append(" group by b.process_key");
		
		}
		

		SqlCommand sqlCommand=new SqlCommand(Context.getDbConnection());
		return sqlCommand.queryForList(stringBuffer.toString(),objectParamWhere);
	}
	
	public List<Map<String,Object>> selectProcessPerformance(String startTime, String endTime) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		Date startdate = null;
		Date enddate = null;
		try {
			startdate=sdf.parse(startTime);
			enddate = sdf.parse(endTime);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(enddate);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			enddate = calendar.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(
				"SELECT b.process_key as PROCESS_KEY,count(a.processinstance_id) as SUMWF FROM FIXFLOW_RUN_PROCESSINSTANECE a,fixflow_def_processdefinition b" +
				" where a.processdefinition_id=b.process_id " +
				" and a.start_time>=? and a.start_time<=? " +
				" group by b.process_key ");
		
		
		List<Object> objectParamWhere = new ArrayList<Object>();
		
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		
		SqlCommand sqlCommand=new SqlCommand(Context.getDbConnection());
		return sqlCommand.queryForList(stringBuffer.toString(),objectParamWhere);
	}
	
	public List<Map<String,Object>> selectProcessPerformanceTask(String[] pid, String startTime, String endTime) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		Date startdate = null;
		Date enddate = null;
		try {
			startdate=sdf.parse(startTime);
			enddate = sdf.parse(endTime);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(enddate);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			enddate = calendar.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		List<Object> objectParamWhere = new ArrayList<Object>();
		Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(
				"select ASSIGNEE, round("+pagination.getLocalismSql("datediffconvert", null)+",2) as AVGTIME FROM FIXFLOW_RUN_TAKSINSTANECE " +
				" where end_time is not null and TASKTYPE='FIXFLOWTASK'" +
				" and create_time>=? and create_time<=? " +
				" and assignee in(");
		
			objectParamWhere.add(startdate);
			objectParamWhere.add(enddate);
				
			for(int j=0;j<pid.length;j++) {
				if(j==0) {
					stringBuffer.append("?");
				}else {
					stringBuffer.append(",?");
				}
				objectParamWhere.add(pid[j]);
			}
			
			stringBuffer.append(")");
			stringBuffer.append(" group by ASSIGNEE ");
		
		SqlCommand sqlCommand=new SqlCommand(Context.getDbConnection());
		return sqlCommand.queryForList(stringBuffer.toString(),objectParamWhere);
	}
	
	public List<Map<String,Object>> selectProcessPerformance(String startTime, String endTime, String pid) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		Date startdate = null;
		Date enddate = null;
		try {
			startdate=sdf.parse(startTime);
			enddate = sdf.parse(endTime);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(enddate);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			enddate = calendar.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(
				"SELECT COMMAND_TYPE,count(COMMAND_TYPE) as TIMES FROM FIXFLOW_RUN_TAKSINSTANECE " +
				" where end_time is not null " +
				" and create_time>=? and create_time<=? " +
				" and assignee=? " +
				" group by ASSIGNEE,COMMAND_TYPE");
		
		
		List<Object> objectParamWhere = new ArrayList<Object>();
		
		objectParamWhere.add(startdate);
		objectParamWhere.add(enddate);
		objectParamWhere.add(pid);
		
		SqlCommand sqlCommand=new SqlCommand(Context.getDbConnection());
		return sqlCommand.queryForList(stringBuffer.toString(),objectParamWhere);
	}
	
	/*
	public ProcessInstancePersistenceTo selectProcessInstanceByMyTaskEnd(String userId, Page page){
		
		
		String sqlString = " select " + Context.getProcessEngineConfiguration().getDbConfig().getDbSqlMap().get("topOrderBy") + " E.* ";
		List<Object> objectParamWhere = new ArrayList<Object>();
		sqlString = selectProcessInstanceByMyTaskEndSql(sqlString,userId , page);

		if (processInstanceQuery.getOrderBy() != null) {

			sqlString = sqlString + " order by " + processInstanceQuery.getOrderBy().toString();
		}

		if (page != null) {
			Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();
			sqlString = pagination.getPaginationSql(sqlString, page.getFirstResult(), page.getMaxResults(), "*");
		}

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlString, objectParamWhere);

		List<ProcessInstancePersistenceTo> processInstancePersistenceToList = new ArrayList<ProcessInstancePersistenceTo>();

		for (Map<String, Object> dataMap : dataObj) {

			ProcessInstancePersistenceTo processInstancePersistenceTo = getProcessInstancePersistenceTo(dataMap);

			processInstancePersistenceToList.add(processInstancePersistenceTo);
		}

		return processInstancePersistenceToList;
	}
	
	
	
	private String selectProcessInstanceByMyTaskEndSql(String sqlString,
			String userId, Page page) {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}
	*/

	

	public ProcessInstanceEntity getProcessInstance(String processDefinitionKey, String businessKey) {
		// TODO Auto-generated method stub
		
		String sqlString = " SELECT * FROM FIXFLOW_RUN_PROCESSINSTANECE WHERE PROCESSDEFINITION_KEY=?  AND BIZ_KEY=? ";
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(processDefinitionKey);
		objectParamWhere.add(businessKey);
		

		
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlString, objectParamWhere);
		if(dataObj.size()==0){
			return null;
		}
		Map<String, Object> dataMap = dataObj.get(0);
		
		return new ProcessInstanceEntity(dataMap);

		
	}
	
	
	public void deleteProcessInstanceByProcessInstanceId(String processInstanceId){
		
		Object[] objectParamWhere = { processInstanceId };
		//String sqlString="DELETE FROM FIXFLOW_RUN_TOKEN WHERE PROCESSINSTANCE_ID=?";
		
		sqlCommand.delete("FIXFLOW_RUN_PROCESSINSTANECE", " PROCESSINSTANCE_ID=?",objectParamWhere);
		
	}
	
	
	

}
