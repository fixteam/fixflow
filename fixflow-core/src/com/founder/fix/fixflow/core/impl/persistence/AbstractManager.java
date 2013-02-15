package com.founder.fix.fixflow.core.impl.persistence;

import com.founder.fix.fixflow.core.impl.db.DbSqlSession;
import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

/**
 * @author kenshin
 */
public abstract class AbstractManager {

	public void insert(String insertStatement, PersistentObject persistentObject) {
		getDbSqlSession().insert(insertStatement, persistentObject);
	}

	public void delete(String deleteStatement, PersistentObject persistentObject) {
		getDbSqlSession().delete(deleteStatement, persistentObject.getId());
	}

	protected DbSqlSession getDbSqlSession() {

		return commandContext.getDbSqlSession();
	}

	protected CommandContext commandContext;

	public CommandContext getCommandContext() {
		return commandContext;
	}

	public void setCommandContext(CommandContext commandContext) {
		this.commandContext = commandContext;
	}

}
