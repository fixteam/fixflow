package com.founder.fix.fixflow.designer.util;

import java.util.Calendar;

public class TimeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。   
		cal.add(Calendar.MINUTE, +1);//取当前日期的前一天.   
		  
		cal.getTime();
		
		
		
		System.out.print(cal.getTime());
	}

}
