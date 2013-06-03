package com.founder.fix.fixflow.expand.quartz.jdbcjobstore;

import java.sql.Connection;

import org.quartz.JobPersistenceException;
import org.quartz.impl.jdbcjobstore.JobStoreTX;

import com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class JobStoreFix extends JobStoreTX {

	@Override
	protected Connection getConnection() throws JobPersistenceException {

		if (Context.getProcessEngineConfiguration() == null) {
			return super.getConnection();
		} else {
			QuartzConfig quartzConfig = Context.getProcessEngineConfiguration()
					.getQuartzConfig();
			if (quartzConfig == null) {
				return null;
			}
			if (StringUtil.getBoolean(quartzConfig.getIsDefaultConfig())) {
				if (Context.getDbConnection() == null) {
					return super.getConnection();
				} else {
					return Context.getDbConnection();
				}
			} else {
				if (Context.getDbConnection(quartzConfig.getDataBaseId()) == null) {
					return super.getConnection();
				} else {
					return Context.getDbConnection(quartzConfig
							.getIsDefaultConfig());
				}
			}
		}

	}

	@Override
	protected void closeConnection(Connection arg0) {

		if (Context.getProcessEngineConfiguration() == null) {
			super.closeConnection(arg0);

		} else {
			QuartzConfig quartzConfig = Context.getProcessEngineConfiguration()
					.getQuartzConfig();
			if (quartzConfig == null) {
				return;
			}
			if (StringUtil.getBoolean(quartzConfig.getIsDefaultConfig())) {
				if (Context.getDbConnection() == null) {
					super.closeConnection(arg0);
				}
			} else {
				if (Context.getDbConnection(quartzConfig.getDataBaseId()) == null) {
					super.closeConnection(arg0);
				}
			}
		}

	}

	@Override
	protected void commitConnection(Connection arg0)
			throws JobPersistenceException {

		if (Context.getProcessEngineConfiguration() == null) {
			super.commitConnection(arg0);
		} else {

			QuartzConfig quartzConfig = Context.getProcessEngineConfiguration()
					.getQuartzConfig();
			if (quartzConfig == null) {
				return;
			}
			if (StringUtil.getBoolean(quartzConfig.getIsDefaultConfig())) {
				if (Context.getDbConnection() == null) {
					super.commitConnection(arg0);
				}
			} else {
				if (Context.getDbConnection(quartzConfig.getDataBaseId()) == null) {
					super.commitConnection(arg0);
				}
			}
		}

	}

	@Override
	protected void rollbackConnection(Connection arg0) {

		if (Context.getProcessEngineConfiguration() == null) {
			super.rollbackConnection(arg0);
		} else {
			QuartzConfig quartzConfig = Context.getProcessEngineConfiguration()
					.getQuartzConfig();
			if (quartzConfig == null) {
				return;
			}
			if (StringUtil.getBoolean(quartzConfig.getIsDefaultConfig())) {
				if (Context.getDbConnection() == null) {
					super.rollbackConnection(arg0);
				}
			} else {
				if (Context.getDbConnection(quartzConfig.getDataBaseId()) == null) {
					super.rollbackConnection(arg0);
				}
			}
		}

	}

}
