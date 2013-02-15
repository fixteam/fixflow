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
