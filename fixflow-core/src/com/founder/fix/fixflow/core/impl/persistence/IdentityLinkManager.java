package com.founder.fix.fixflow.core.impl.persistence;


import java.util.List;

import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.runtime.IdentityLinkQueryImpl;
import com.founder.fix.fixflow.core.impl.task.IdentityLinkEntity;
import com.founder.fix.fixflow.core.task.IdentityLink;

public class IdentityLinkManager extends AbstractManager {

	public void deleteIdentityLink(IdentityLinkEntity identityLink) {
		getDbSqlSession().delete("deleteIdentityLink", identityLink.getId());
	}
	
	public void deleteIdentityLinkById(String id) {
		getDbSqlSession().delete("deleteIdentityLink", id);
	}

	@SuppressWarnings("unchecked")
	public List<IdentityLinkEntity> findIdentityLinksByTaskId(String taskId) {
		return getDbSqlSession().selectList("selectIdentityLinksByTask", taskId);
	}


	public void deleteIdentityLinksByTaskId(String taskId) {
		List<IdentityLinkEntity> identityLinks = findIdentityLinksByTaskId(taskId);
		for (IdentityLinkEntity identityLink : identityLinks) {
			deleteIdentityLink(identityLink);
		}
	}

	public long findIdentityLinkCountByQueryCriteria(IdentityLinkQueryImpl identityLinkQueryImpl) {
		return (Long)getDbSqlSession().selectOne("selectIdentityLinkCountByQueryCriteria", identityLinkQueryImpl);
	}

	@SuppressWarnings("unchecked")
	public List<IdentityLinkEntity> findIdentityLinkByQueryCriteria(IdentityLinkQueryImpl identityLinkQueryImpl, Page page) {
		String query = "selectIdentityLinkByQueryCriteria";
		return getDbSqlSession().selectList(query, identityLinkQueryImpl, page);
	}

	
	public void saveIdentityLink(IdentityLink identityLink) {
		String saveStatement = "saveIdentityLink";
		getDbSqlSession().save(saveStatement, identityLink);
	}
}
