package com.founder.fix.fixflow.core.impl.persistence.instance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.job.JobDetailEntity;
import com.founder.fix.fixflow.core.impl.job.JobEntity;

public class JobPersistence {
	
	
	protected Connection connection;
	protected SqlCommand sqlCommand;

	public JobPersistence(Connection connection) {
		this.connection = connection;
		sqlCommand = new SqlCommand(connection);
	}
	
	
	public void deleteJob(String jobId){
		
		
		
		Object[] objectParamWhere = { jobId };

		sqlCommand.delete("FIXFLOW_JOB_INFO", " JOB_ID =?)",objectParamWhere);
		sqlCommand.delete("FIXFLOW_JOB_DETAIL", " JOB_ID =?)",objectParamWhere);
		
	}
	
	public void saveJob(JobEntity jobEntity){
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(jobEntity.getId());

		// 设置查询字符串
		String sqlText = "SELECT COUNT(1) FROM FIXFLOW_JOB_INFO WHERE JOB_ID=?";
		// 执行查询Sql语句,判断身份链接是否存在于数据库中.
		int rowNum = Integer.parseInt(sqlCommand.queryForValue(sqlText, objectParamWhere).toString());

		if (rowNum == 0) {
			
			insertJob(jobEntity);
			
			Map<String, JobDetailEntity>  jobDetailMap= jobEntity.getJobDetailEntityMap();
			for (String mapKey : jobDetailMap.keySet()) {
				insertJobDetail(jobDetailMap.get(mapKey));
			}
		} else {
			Object[] objectParamWhereDel = { jobEntity.getId() };
			sqlCommand.delete("FIXFLOW_JOB_DETAIL", " JOB_ID =?)",objectParamWhereDel);
			updateJob(jobEntity);
			
			Map<String, JobDetailEntity>  jobDetailMap= jobEntity.getJobDetailEntityMap();
			for (String mapKey : jobDetailMap.keySet()) {
				insertJobDetail(jobDetailMap.get(mapKey));
			}
		}
	}
	
	
	
	public void insertJobDetail(JobDetailEntity jobDetailEntity){
		Map<String, Object> objectParam=jobDetailEntity.getPersistentDbMap();
		// 执行插入语句
		sqlCommand.insert("FIXFLOW_JOB_DETAIL", objectParam);
	}
	
	


	public void insertJob(JobEntity jobEntity){
		Map<String, Object> objectParam=jobEntity.getPersistentDbMap();
		// 执行插入语句
		sqlCommand.insert("FIXFLOW_RUN_TASKIDENTITYLINK", objectParam);
	}
	
	public void updateJob(JobEntity jobEntity){
		Map<String, Object> objectParam=jobEntity.getPersistentDbMap();

		// 构建Where查询参数
		Object[] objectParamWhere = { jobEntity.getId() };

		// 执行插入语句
		sqlCommand.update("FIXFLOW_RUN_TASKIDENTITYLINK", objectParam, " JOB_ID=?", objectParamWhere);
	}

}
