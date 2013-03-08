package com.founder.fix.fixflow.core.impl.persistence.instance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.runtime.IdentityLinkQueryImpl;
import com.founder.fix.fixflow.core.impl.task.IdentityLinkEntity;


public class IdentityLinkPersistence {
	
	
	protected Connection connection;
	protected SqlCommand sqlCommand;

	public IdentityLinkPersistence(Connection connection) {
		this.connection = connection;
		sqlCommand = new SqlCommand(connection);
	}
	
	
	
	private String selectIdentityLinkByQueryCriteriaSql(String sqlString,IdentityLinkQueryImpl identityLinkQuery, Page page,List<Object> objectParamWhere)
	{
		sqlString = sqlString+" FROM FIXFLOW_RUN_TASKIDENTITYLINK  ";



		sqlString = sqlString + " WHERE 1=1";

		if (identityLinkQuery.getId() != null) {
			sqlString = sqlString + " and ID=? ";
			objectParamWhere.add(identityLinkQuery.getId());
		}
		
		if (identityLinkQuery.getType() != null) {
			sqlString = sqlString + " and TYPE=? ";
			objectParamWhere.add(identityLinkQuery.getType().toString());
		}
		if (identityLinkQuery.getUserId() != null) {
			sqlString = sqlString + " and USER_ID=? ";
			objectParamWhere.add(identityLinkQuery.getUserId());
		}
		if (identityLinkQuery.getGroupId() != null) {
			sqlString = sqlString + " and GROUP_ID=? ";
			objectParamWhere.add(identityLinkQuery.getGroupId());
		}
		
		if (identityLinkQuery.getGroupType() != null) {
			sqlString = sqlString + " and GROUP_TYPE=? ";
			objectParamWhere.add(identityLinkQuery.getGroupType());
		}
		
		if (identityLinkQuery.getIncludeExclusion()!= null) {
			sqlString = sqlString + " and INCLUDE_EXCLUSION=? ";
			objectParamWhere.add(identityLinkQuery.getIncludeExclusion().toString());
		}
		
		if (identityLinkQuery.getTaskId() != null) {
			sqlString = sqlString + " and TASKINSTANCE_ID=? ";
			objectParamWhere.add(identityLinkQuery.getTaskId());
		}
		

		


		
		
		return sqlString;
	}
	
	
	public List<IdentityLinkEntity> selectIdentityLinksByTask(String taskId)
	{
		String sqlString="SELECT * FROM FIXFLOW_RUN_TASKIDENTITYLINK WHERE TASKINSTANCE_ID=?";
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(taskId);
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlString, objectParamWhere);

		List<IdentityLinkEntity> identityLinkPersistenceToList = new ArrayList<IdentityLinkEntity>();

		for (Map<String, Object> dataMap : dataObj) {
			
			IdentityLinkEntity identityLinkPersistenceTo=new IdentityLinkEntity(dataMap);

			identityLinkPersistenceToList.add(identityLinkPersistenceTo);
		}
		
		return identityLinkPersistenceToList;
	}
	
	public List<IdentityLinkEntity> selectIdentityLinkByQueryCriteria(IdentityLinkQueryImpl identityLinkQuery, Page page) {

		String sqlString="select "+Context.getProcessEngineConfiguration().getDbConfig().getDbSqlMap().get("topOrderBy")+" * ";
		List<Object> objectParamWhere = new ArrayList<Object>();
		sqlString=selectIdentityLinkByQueryCriteriaSql(sqlString,identityLinkQuery,page,objectParamWhere);
		
		
		if (identityLinkQuery.getOrderBy() != null) {

			sqlString = sqlString + " order by "+identityLinkQuery.getOrderBy().toString();
		}
		
		if(page!=null)
		{
			Pagination pagination=Context.getProcessEngineConfiguration().getDbConfig().getPagination();
			sqlString=pagination.getPaginationSql(sqlString, page.getFirstResult(), page.getMaxResults(), "*");
		}
	
		
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlString, objectParamWhere);

		List<IdentityLinkEntity> identityLinkPersistenceToList = new ArrayList<IdentityLinkEntity>();

		for (Map<String, Object> dataMap : dataObj) {
			
			IdentityLinkEntity identityLinkPersistenceTo=new IdentityLinkEntity(dataMap);

			identityLinkPersistenceToList.add(identityLinkPersistenceTo);
		}
		
		return identityLinkPersistenceToList;
		//select distinct T.* from
	}

	public long selectIdentityLinkCountByQueryCriteria(IdentityLinkQueryImpl identityLinkQuery) {
		// select count(distinct T.ID_)
		String sqlString="select count(*) ";
		List<Object> objectParamWhere = new ArrayList<Object>();
		sqlString=selectIdentityLinkByQueryCriteriaSql(sqlString,identityLinkQuery,null,objectParamWhere);
		Object returnObj=sqlCommand.queryForValue(sqlString, objectParamWhere);
		return Integer.parseInt(returnObj.toString());
	}

	
	
	
	
	
	
	
	public void deleteIdentityLinkByProcessInstanceId(String processInstanceId){
		
		Object[] objectParamWhere = { processInstanceId };
		//String sqlString="DELETE FROM FIXFLOW_RUN_TOKEN WHERE PROCESSINSTANCE_ID=?";
		
		sqlCommand.delete("FIXFLOW_RUN_TASKIDENTITYLINK", " TASKINSTANCE_ID IN (SELECT TASKINSTANCE_ID FROM FIXFLOW_RUN_TAKSINSTANECE WHERE PROCESSINSTANCE_ID=?)",objectParamWhere);
		
	}
	
	
	public void insertIdentityLink(IdentityLinkEntity identityLink) {

		
		Map<String, Object> objectParam=identityLink.getPersistentDbMap();
		// 执行插入语句
		sqlCommand.insert("FIXFLOW_RUN_TASKIDENTITYLINK", objectParam);

	}

	public void updateIdentityLink(IdentityLinkEntity identityLink) {

		Map<String, Object> objectParam=identityLink.getPersistentDbMap();

		// 构建Where查询参数
		Object[] objectParamWhere = { identityLink.getId() };

		// 执行插入语句
		sqlCommand.update("FIXFLOW_RUN_TASKIDENTITYLINK", objectParam, " ID=?", objectParamWhere);

	}
	
	public void saveIdentityLink(IdentityLinkEntity identityLink){
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(identityLink.getId());

		// 设置查询字符串
		String sqlText = "SELECT COUNT(1) FROM FIXFLOW_RUN_TASKIDENTITYLINK WHERE ID=?";
		// 执行查询Sql语句,判断身份链接是否存在于数据库中.
		int rowNum = Integer.parseInt(sqlCommand.queryForValue(sqlText, objectParamWhere).toString());

		if (rowNum == 0) {
			
			insertIdentityLink(identityLink);
		} else {
			updateIdentityLink(identityLink);
		}
	}

	

}
