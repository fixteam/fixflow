
package org.quartz.impl.jdbcjobstore;

import java.sql.Connection;

import org.quartz.JobPersistenceException;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;


public class JobStoreFix extends JobStoreTX {

	@Override
	protected void closeConnection(Connection arg0) {
		// TODO 自动生成的方法存根
		boolean isQuartzTransactionAuto=Context.isQuartzTransactionAuto();
		if(!isQuartzTransactionAuto){
			Context.setQuartzCloseConnection(arg0);
		}else{
			super.closeConnection(arg0);
		}
		
		
		
	}

	@Override
	protected void commitConnection(Connection arg0) throws JobPersistenceException {
		// TODO 自动生成的方法存根
		boolean isQuartzTransactionAuto=Context.isQuartzTransactionAuto();
		if(!isQuartzTransactionAuto){
			Context.setQuartzCommitConnection(arg0);
		}
		else{
			super.commitConnection(arg0);
		}
		
	}

	@Override
	protected void rollbackConnection(Connection arg0) {
		// TODO 自动生成的方法存根
		boolean isQuartzTransactionAuto=Context.isQuartzTransactionAuto();
		if(!isQuartzTransactionAuto){
			Context.setQuartzRollbackConnection(arg0);
			throw new FixFlowException("quartz error");
		}
		else{
			super.rollbackConnection(arg0);
		}
	}
   
}
