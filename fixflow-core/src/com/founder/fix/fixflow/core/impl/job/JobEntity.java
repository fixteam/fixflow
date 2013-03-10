package com.founder.fix.fixflow.core.impl.job;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;



import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.exception.FixFlowClassLoadingException;
import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.job.AbstractJob;
import com.founder.fix.fixflow.core.job.Job;
import com.founder.fix.fixflow.core.objkey.JobObjKey;

/**
 * 定时任务类
 * @author kenshin 
 *
 */
public class JobEntity extends AbstractPersistentObject implements Job{


	/**
	 * 
	 */
	private static final long serialVersionUID = -934877366500337782L;
	
	
	
	

	
	
	protected String id;
	
	protected String jobName;
	
	protected String bizType;
	
	
	protected String bizValue;
	
	protected String quarztType;
	
	protected Date quarztTime;
	
	protected String quarztCron;
	
	protected int jobState=0;

	protected boolean isDelete=false; 
	
	protected  Class<? extends AbstractJob> jobClassName;
	

	protected Map<String, JobDetailEntity> jobDetailEntityMap=new HashMap<String, JobDetailEntity>();

	public 	JobEntity(){
		
		this.id=GuidUtil.CreateGuid();
	}
	
	public Map<String, JobDetailEntity> getJobDetailEntityMap() {
		return jobDetailEntityMap;
	}

	public void addJobDetailEntity(String bizKey,String bizValue,String bizObj){
		
		if(bizKey==null||bizKey.equals("")){
			
			throw new FixFlowBizException("bizKey不能为空!");
			
		}

		JobDetailEntity jobDetailEntity=new JobDetailEntity();
		jobDetailEntity.setId(this.id);
		jobDetailEntity.setBizKey(bizKey);
		jobDetailEntity.setBizValue(bizValue);
		jobDetailEntity.setBizObj(bizObj);
		jobDetailEntityMap.put(bizKey, jobDetailEntity);
		
	}
	
	public JobDetailEntity getJobDetailEntity(String bizKey){
		
		JobDetailEntity jobDetailEntity=jobDetailEntityMap.get(bizKey);
		
		return jobDetailEntity;
	}
	
	
	/*
	private void addJobDetailEntity(JobDetailEntity jobDetailEntity){
		
		if(jobDetailEntity==null){
			throw new FixFlowBizException("jobDetailEntity不能为空!");
		}
		if(jobDetailEntity.getId()==null||jobDetailEntity.getId().equals("")){
			throw new FixFlowBizException("jobDetailEntityId不能为空!");
		}
		if(jobDetailEntity.getBizKey()==null||jobDetailEntity.getBizKey().equals("")){
			throw new FixFlowBizException("bizKey不能为空!");
		}
		if(jobDetailEntity.getId()!=this.id){
			throw new FixFlowBizException("jobEntityId和jobDetailEntityId不相等!");
		}
		jobDetailEntityMap.put(jobDetailEntity.getBizKey(), jobDetailEntity);
	}*/
	
	public Class<? extends AbstractJob> getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(Class<? extends AbstractJob> jobClassName) {
		this.jobClassName = jobClassName;
	}

	public int getJobState() {
		return jobState;
	}

	public void setJobState(int jobState) {
		this.jobState = jobState;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getQuarztType() {
		return quarztType;
	}

	public void setQuarztType(String quarztType) {
		this.quarztType = quarztType;
	}

	public Date getQuarztTime() {
		return quarztTime;
	}

	public void setQuarztTime(Date quarztTime) {
		this.quarztTime = quarztTime;
	}

	public String getQuarztCron() {
		return quarztCron;
	}

	public void setQuarztCron(String quarztCron) {
		this.quarztCron = quarztCron;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	public JobEntity(Map<String, Object> entityMap) {
		persistentInit(entityMap);
	}

	public String getId() {
		// TODO 自动生成的方法存根
		return this.id;
	}

	public Map<String, Object> getPersistentState() {

		Map<String, Object> objectParam = new HashMap<String, Object>();

		objectParam.put(JobObjKey.Id().FullKey(), this.getId());

		objectParam.put(JobObjKey.JobName().FullKey(), this.jobName);

		objectParam.put(JobObjKey.BizType().FullKey(), this.bizType);

		objectParam.put(JobObjKey.BizValue().FullKey(), this.bizValue);
		
		objectParam.put(JobObjKey.QuarztType().FullKey(), this.quarztType);
		
		
		objectParam.put(JobObjKey.QuarztTime().FullKey(), this.quarztTime);
		
		objectParam.put(JobObjKey.QuarztCron().FullKey(), this.quarztCron);
		
		objectParam.put(JobObjKey.JobState().FullKey(), this.jobState);
		
		objectParam.put(JobObjKey.IsDelete().FullKey(), this.quarztCron);
		
		objectParam.put(JobObjKey.JobClassName().FullKey(), this.jobClassName.getName());

		return objectParam;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void persistentInit(Map<String, Object> entityMap) {
	
		for (String dataKey : entityMap.keySet()) {

			if (dataKey.equals(JobObjKey.Id().DataBaseKey())) {
				this.id = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(JobObjKey.JobName().DataBaseKey())) {
				this.jobName=StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(JobObjKey.BizType().DataBaseKey())) {
				this.bizType=StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(JobObjKey.BizValue().DataBaseKey())) {
				this.bizValue=StringUtil.getString(entityMap.get(dataKey));
				continue;
			}	
			if (dataKey.equals(JobObjKey.QuarztTime().DataBaseKey())) {
				this.quarztTime=StringUtil.getDate(entityMap.get(dataKey));
				continue;
			}	
			if (dataKey.equals(JobObjKey.QuarztType().DataBaseKey())) {
				this.quarztType=StringUtil.getString(entityMap.get(dataKey));
				continue;
			}	
			if (dataKey.equals(JobObjKey.QuarztCron().DataBaseKey())) {
				this.quarztCron=StringUtil.getString(entityMap.get(dataKey));
				continue;
			}	
			
			if (dataKey.equals(JobObjKey.JobState().DataBaseKey())) {
				this.jobState=StringUtil.getInt(entityMap.get(dataKey));
				continue;
			}
			
			if (dataKey.equals(JobObjKey.IsDelete().DataBaseKey())) {
				this.isDelete=StringUtil.getBoolean(entityMap.get(dataKey));
				continue;
			}
			
			if (dataKey.equals(JobObjKey.JobClassName().DataBaseKey())) {
				
				String classNameString=StringUtil.getString(entityMap.get(dataKey));
				try {
					this.jobClassName=(Class<? extends AbstractJob>)Class.forName(classNameString);
				} catch (ClassNotFoundException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					throw new FixFlowClassLoadingException(classNameString, e);
				}
				
				continue;
			}
			
		}
	}

	@Override
	public Map<String, Object> getPersistentDbMap() {

		Map<String, Object> objectParam = new HashMap<String, Object>();

		objectParam.put(JobObjKey.Id().DataBaseKey(), this.getId());

		objectParam.put(JobObjKey.JobName().DataBaseKey(), this.jobName);

		objectParam.put(JobObjKey.BizType().DataBaseKey(), this.bizType);

		objectParam.put(JobObjKey.BizValue().DataBaseKey(), this.bizValue);
		
		objectParam.put(JobObjKey.QuarztType().DataBaseKey(), this.quarztType);
		
		
		objectParam.put(JobObjKey.QuarztTime().DataBaseKey(), this.quarztTime);
		
		objectParam.put(JobObjKey.QuarztCron().DataBaseKey(), this.quarztCron);

		
		objectParam.put(JobObjKey.JobState().DataBaseKey(), this.jobState);
		
		objectParam.put(JobObjKey.IsDelete().DataBaseKey(), StringUtil.getString(this.isDelete));
		
		objectParam.put(JobObjKey.JobClassName().DataBaseKey(), this.jobClassName.getName());

		return objectParam;
	}


	
}
