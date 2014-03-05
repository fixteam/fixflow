/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author shao
 */
package com.founder.fix.fixflow.shell;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.founder.fix.fixflow.core.db.pagination.Pagination;

public class DBConnection {
	private DataSource dataSource;
	
	private Connection connection;
	
	private Pagination pageination;
	
	public void close() throws SQLException{
		if (connection != null && connection.isClosed()==false){
			if(connection.getAutoCommit()==false){
				connection.commit();
			}
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
	}
	
	public void closeAndRockBack() throws SQLException{
		if (connection != null && connection.isClosed()==false){
			if(connection.getAutoCommit()==false){
				connection.rollback();
			}
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection){
		this.connection = connection;
		pageination = initDialet(connection);
	}
	
	public static Pagination initDialet(Connection connection){
		Pagination pageination = null;
		try{
			String dbms = connection.getMetaData().getDatabaseProductName();
			if ( dbms == null || dbms.equals("") ){
				pageination = (Pagination)Class.forName("com.founder.fix.fixflow.expand.database.pagination.OraclePaginationImpl").newInstance();
			}else if ( dbms.equals("Oracle") ){
				pageination = (Pagination)(Pagination)Class.forName("com.founder.fix.fixflow.expand.database.pagination.OraclePaginationImpl").newInstance();
			}else if ( dbms.equals("Microsoft SQL Server")){
				pageination = (Pagination)Class.forName("com.founder.fix.fixflow.expand.database.pagination.SqlServerPaginationImpl").newInstance();
			}else if ( dbms.indexOf("DB2")!=-1){
				pageination = (Pagination)Class.forName("com.founder.fix.fixflow.core.impl.db.pagination.DB2PaginationImpl").newInstance();
			}else if ( dbms.equals("MySQL")){
				pageination = (Pagination)Class.forName("com.founder.fix.fixflow.expand.database.pagination.MySqlPaginationImpl").newInstance();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return pageination;
	}

	public Pagination getPageination() {
		return pageination;
	}

	public void setPageination(Pagination pageination) {
		this.pageination = pageination;
	}
	
	
}
