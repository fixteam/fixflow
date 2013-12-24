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
package com.founder.fix.fixflow.core.impl.persistence;


import java.util.List;

import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.runtime.IdentityLinkQueryImpl;
import com.founder.fix.fixflow.core.impl.task.IdentityLinkEntity;
import com.founder.fix.fixflow.core.task.IdentityLink;

public class IdentityLinkManager extends AbstractManager {

	public void deleteIdentityLink(IdentityLinkEntity identityLink) {
		//getDbSqlSession().delete("deleteIdentityLink", identityLink.getId());
		
	}
	
	public void deleteIdentityLinkById(String id) {
		//getDbSqlSession().delete("deleteIdentityLink", id);
	}

	@SuppressWarnings("unchecked")
	public List<IdentityLinkEntity> findIdentityLinksByTaskId(String taskId) {
		return getDbSqlSession().selectList("selectIdentityLinksByTask", taskId);
	}


	public void deleteIdentityLinksByTaskId(String taskId) {
//		List<IdentityLinkEntity> identityLinks = findIdentityLinksByTaskId(taskId);
//		for (IdentityLinkEntity identityLink : identityLinks) {
//			deleteIdentityLink(identityLink);
//		}
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
		/* 5.1版本修改
		String saveStatement = "saveIdentityLink";
		getDbSqlSession().save(saveStatement, identityLink);
		*/
		
		IdentityLink tmpIdentityLink = selectIdentifyLinkById(identityLink.getId());
		if(tmpIdentityLink == null){
			insert("insertIdentityLink", identityLink);
		}else{
			update("updateIdentityLink", identityLink);
		}
	}
	
	/****新增方法*****/
	
	public IdentityLink selectIdentifyLinkById(String id){
		return (IdentityLink)getMappingSqlSession().selectOne("selectIdentityLinkById", id);
	}
}
