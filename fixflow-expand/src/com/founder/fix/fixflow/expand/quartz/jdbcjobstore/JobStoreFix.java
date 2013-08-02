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

		if (Context.isQuartzTransactionAuto()) {
			return super.getConnection();
		} else {
			QuartzConfig quartzConfig = Context.getProcessEngineConfiguration().getQuartzConfig();
			if (quartzConfig == null) {
				return null;
			}

			if (StringUtil.getBoolean(quartzConfig.getIsDefaultConfig())) {

				return Context.getDbConnection();

			} else {

				return Context.getDbConnection(quartzConfig.getIsDefaultConfig());

			}
		}

	}

	@Override
	protected void closeConnection(Connection connection) {

		if (Context.isQuartzTransactionAuto()) {
			super.closeConnection(connection);

		} else {
			/*QuartzConfig quartzConfig = Context.getProcessEngineConfiguration().getQuartzConfig();
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
			}*/
		}

	}

	@Override
	protected void commitConnection(Connection connection) throws JobPersistenceException {

		if (Context.isQuartzTransactionAuto()) {
			super.commitConnection(connection);
		} else {

			/*QuartzConfig quartzConfig = Context.getProcessEngineConfiguration().getQuartzConfig();
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
			}*/
		}

	}

	@Override
	protected void rollbackConnection(Connection connection) {

		if (Context.isQuartzTransactionAuto()) {
			super.rollbackConnection(connection);
		} else {
			/*QuartzConfig quartzConfig = Context.getProcessEngineConfiguration().getQuartzConfig();
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
			}*/
		}

	}

}
