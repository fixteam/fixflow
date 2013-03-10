package com.founder.fix.fixflow.core.job;

import java.util.Date;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.job.JobDetailEntity;

/**
 * 定时任务接口
 * @author kenshin
 *
 */
public interface Job extends PersistentObject {
	
	/**
	 * 未注册
	 */
	public static int  JOB_NO_REG=0;
	
	/**
	 * 执行中
	 */
	public static int  JOB_OPERATION=1;
	
	/**
	 * 已经完成
	 */
	public static int  JOB_Complete=2;
	
	/**
	 * 执行失败
	 */
	public static int  JOB_FAIL=3;
	
	

	public Map<String, JobDetailEntity> getJobDetailEntityMap();

	public void addJobDetailEntity(String bizKey, String bizValue, String bizObj);

	public JobDetailEntity getJobDetailEntity(String bizKey);

	public Class<? extends AbstractJob> getJobClassName();

	public void setJobClassName(Class<? extends AbstractJob> jobClassName);

	public int getJobState();

	public void setJobState(int jobState);

	public boolean isDelete();

	public void setDelete(boolean isDelete);

	public String getJobName();

	public void setJobName(String jobName);

	public String getQuarztType();

	public void setQuarztType(String quarztType);

	public Date getQuarztTime();

	public void setQuarztTime(Date quarztTime);

	public String getQuarztCron();

	public void setQuarztCron(String quarztCron);

	public String getId();

	public Map<String, Object> getPersistentDbMap();

}
