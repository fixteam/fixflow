package com.founder.fix.fixflow.core.worktime;

import java.util.Date;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public interface WorkTime {
	
	/**
	 * 判断时间是不是工作时间
	 * @param nowDate 时间
	 * @return
	 */
	public boolean isWorkDate(Date nowDate);
	
	public Date  getWorkTime (Date newDate,int days,int hours,int minutes, int seconds,ExecutionContext executionContext);

}
