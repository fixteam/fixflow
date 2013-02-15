package com.founder.fix.fixflow.core.worktime;

import java.util.Date;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public interface WorkTime {
	
	public Date  getWorkTime (Date newDate,int days,int hours,int minutes, int seconds,ExecutionContext executionContext);

}
