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

import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.task.CommentQueryTo;

public class CommentManager extends AbstractManager {

	public void delete(PersistentObject persistentObject) {

		super.delete("deleteCommentsByTaskId", persistentObject);
	}

	public void insert(PersistentObject persistentObject) {

		super.insert("insertComment", persistentObject);
	}

	@SuppressWarnings("unchecked")
	public List<CommentQueryTo> findCommentsByTaskId(String taskId) {

		return getDbSqlSession().selectList("selectCommentsByTaskId", taskId);
	}

	public void deleteCommentsByTaskId(String taskId) {

		getDbSqlSession().delete("deleteCommentsByTaskId", taskId);
	}

	@SuppressWarnings("unchecked")
	public List<CommentQueryTo> findCommentsByProcessInstanceId(
			String processInstanceId) {

		return getDbSqlSession().selectList(
				"selectCommentsByProcessInstanceId", processInstanceId);
	}

}
