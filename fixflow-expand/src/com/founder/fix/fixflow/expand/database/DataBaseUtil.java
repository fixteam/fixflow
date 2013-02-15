package com.founder.fix.fixflow.expand.database;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;

public class DataBaseUtil {
	
	public static Object getSequenceValue(String sequenceName){
		
		
		SqlCommand sqlCommand=new SqlCommand(Context.getDbConnection());
		Object returnValueObject=sqlCommand.queryForValue("SELECT "+sequenceName+".nextval FROM DUAL");
		
		
		return returnValueObject;
		
	}

}
