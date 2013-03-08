package com.founder.fix.fixflow.core.impl.persistence;

import com.founder.fix.fixflow.core.impl.job.JobEntity;

public class JobManager extends AbstractManager {
	
	
	public void deleteIdentityLinkById(String id) {
		getDbSqlSession().delete("deleteJob", id);
	}

	public void saveJob(JobEntity jobEntity) {
		String saveStatement = "saveJob";
		getDbSqlSession().save(saveStatement, jobEntity);
	}
}
