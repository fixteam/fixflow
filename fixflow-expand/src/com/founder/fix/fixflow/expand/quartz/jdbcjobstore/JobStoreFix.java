
package com.founder.fix.fixflow.expand.quartz.jdbcjobstore;

import java.sql.Connection;

import org.quartz.JobPersistenceException;
import org.quartz.impl.jdbcjobstore.JobStoreTX;


import com.founder.fix.fixflow.core.impl.Context;


public class JobStoreFix extends JobStoreTX {
	
	@Override
	protected Connection getConnection() throws JobPersistenceException{
		
		if(Context.getDbConnection()==null){
			return super.getConnection();
		}
		else{
			return Context.getDbConnection();
		}
		
	}

	@Override
	protected void closeConnection(Connection arg0) {
		
		
		if(Context.getDbConnection()==null){
			super.closeConnection(arg0);
		}
		
		/*
		// TODO 自动生成的方法存根
		boolean isQuartzTransactionAuto=Context.isQuartzTransactionAuto();
		if(!isQuartzTransactionAuto){
			Context.setQuartzCloseConnection(arg0);
		}else{
			super.closeConnection(arg0);
		}*/
		
		
		
	}

	@Override
	protected void commitConnection(Connection arg0) throws JobPersistenceException {
		

		if(Context.getDbConnection()==null){
			super.commitConnection(arg0);
		}
		/*
		
		// TODO 自动生成的方法存根
		boolean isQuartzTransactionAuto=Context.isQuartzTransactionAuto();
		if(!isQuartzTransactionAuto){
			Context.setQuartzCommitConnection(arg0);
		}
		else{
			super.commitConnection(arg0);
		}*/
		
	}

	@Override
	protected void rollbackConnection(Connection arg0) {
		
		
		
		if(Context.getDbConnection()==null){
			super.rollbackConnection(arg0);
		}
		/*
		// TODO 自动生成的方法存根
		boolean isQuartzTransactionAuto=Context.isQuartzTransactionAuto();
		if(!isQuartzTransactionAuto){
			Context.setQuartzRollbackConnection(arg0);
			throw new FixFlowException("quartz error");
		}
		else{
			super.rollbackConnection(arg0);
		}*/
	}
   
}
