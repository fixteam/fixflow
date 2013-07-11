package com.founder.fix.fixflow.expand.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig;
import com.founder.fix.fixflow.core.ConnectionManagement;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.exception.FixFlowDbException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.db.FixConnectionResult;


public class GeneralConnectionResultImpl implements FixConnectionResult {

	protected String dbId;

	protected Connection connection;

	public GeneralConnectionResultImpl(String dbId) {
		this.dbId = dbId;
	}

	public GeneralConnectionResultImpl(String dbId, Connection connection) {
		this.dbId = dbId;
		this.connection = connection;
	}

	public GeneralConnectionResultImpl(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {

		if (this.connection == null) {
			DataBase selectedDatabase = null;
			FixFlowConfig fixFlowConfig = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration()
					.getFixFlowConfig();
			for (DataBase dataBase : fixFlowConfig.getDataBaseConfig().getDataBase()) {
				if (dataBase.getId().equals(this.dbId)) {
					selectedDatabase = dataBase;
					ConnectionManagement.defaultDataBaseId = selectedDatabase.getId();
				}
			}

			DataBase dataBase = selectedDatabase;
			Connection connection = null;
			String driver = dataBase.getDriverClassName();
			String url = dataBase.getUrl();
			String user = dataBase.getUsername();
			String password = dataBase.getPassword();

			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
			} catch (Exception e) {
				e.printStackTrace();
				throw new FixFlowException("数据库链接创建失败!", e);
			}// com.mysql.jdbc.Driver

			this.connection = connection;
			if (this.connection == null) {
				throw new FixFlowDbException("ID为" + this.dbId + "的数据库连接创建失败!");
			}

			return this.connection;
		} else {
			return this.connection;

		}

	}

	public void colseConnection() {
		try {
			if (!this.connection.isClosed()) {
				this.connection.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FixFlowDbException(e.getMessage(), e);
		}
	}

	public void commitConnection() {
		try {
			this.connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FixFlowDbException(e.getMessage(), e);
		}
	}

	public void rollBackConnection() {
		try {
			this.connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FixFlowDbException(e.getMessage(), e);
		}
	}

	public void openConnection() {
		// TODO Auto-generated method stub

	}

	public String getDataBaseId() {
		// TODO Auto-generated method stub
		return this.dbId;
	}

}
