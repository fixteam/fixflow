package com.founder.fix.fixflow.bpa.test;

import static org.junit.Assert.assertEquals;



import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import com.founder.fix.fixflow.bpa.calendar.BusinessCalendar.BusinessCalendar;



public class BusinessCalendarTest {
	static BusinessCalendar businessCalendar = new BusinessCalendar();
	static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	@Test
	public void testminDiff(){
		//同天
//		float minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-6 2:00:00"),formatter.parseDateTime("2013-11-6 9:00:00"));
//		assertEquals(0,minDiff,0.1);
//	
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-6 9:00:00"),formatter.parseDateTime("2013-11-6 9:45:00"));
//		assertEquals(45,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-6 8:00:00"),formatter.parseDateTime("2013-11-6 9:45:00"));
//		assertEquals(45,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-6 11:00:00"),formatter.parseDateTime("2013-11-6 12:00:00"));
//		assertEquals(30,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-6 11:00:00"),formatter.parseDateTime("2013-11-6 13:00:00"));
//		assertEquals(60,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-6 12:00:00"),formatter.parseDateTime("2013-11-6 12:15:00"));
//		assertEquals(0,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-6 9:00:00"),formatter.parseDateTime("2013-11-6 18:00:00"));
//		assertEquals(8*60,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-6 12:00:00"),formatter.parseDateTime("2013-11-6 14:30:00"));
//		assertEquals(120,minDiff,0.1);
//
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-6 14:00:00"),formatter.parseDateTime("2013-11-6 14:30:00"));
//		assertEquals(30,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-6 14:00:00"),formatter.parseDateTime("2013-11-6 18:00:00"));
//		assertEquals(4*60,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-6 14:00:00"),formatter.parseDateTime("2013-11-6 19:00:00"));
//		assertEquals(4*60,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-6 2:00:00"),formatter.parseDateTime("2013-11-6 19:00:00"));
//		assertEquals(8*60,minDiff,0.1);
//		
//		//不同天		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-30 16:00:00"),formatter.parseDateTime("2013-12-01 8:00:00"));
//		assertEquals(0,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-12-31 16:00:00"),formatter.parseDateTime("2014-01-01 17:00:00"));
//		assertEquals(9*60,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-12-31 16:00:00"),formatter.parseDateTime("2014-01-02 17:00:00"));
//		assertEquals(17*60,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-12-31 12:00:00"),formatter.parseDateTime("2014-01-02 8:00:00"));
//		assertEquals(13.5*60,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-12-31 12:00:00"),formatter.parseDateTime("2014-01-01 12:00:00"));
//		assertEquals(8*60,minDiff,0.1);
//		
//
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-12-31 12:00:00"),formatter.parseDateTime("2014-01-01 13:00:00"));
//		assertEquals(8.5*60,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-12-31 12:00:00"),formatter.parseDateTime("2014-01-02 13:00:00"));
//		assertEquals(16.5*60,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-30 9:00:00"),formatter.parseDateTime("2013-12-02 10:00:00"));
//		assertEquals(1*60,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-30 9:00:00"),formatter.parseDateTime("2013-12-08 10:00:00"));
//		assertEquals(5*8*60,minDiff,0.1);
//		
//		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-12-4 12:00:00"),formatter.parseDateTime("2013-12-08 10:00:00"));
//		assertEquals(21.5*60,minDiff,0.1);
		

//		int weeks = businessCalendar.numOfWeekends(formatter.parseDateTime("2013-10-30 1:00:00"),formatter.parseDateTime("2013-11-5 13:00:00"));
//		assertEquals(1,weeks);
//		
//		weeks = businessCalendar.numOfWeekends(formatter.parseDateTime("2013-10-30 1:00:00"),formatter.parseDateTime("2013-10-31 13:00:00"));
//		assertEquals(0,weeks);
//		
//		weeks = businessCalendar.numOfWeekends(formatter.parseDateTime("2013-10-30 1:00:00"),formatter.parseDateTime("2013-11-15 13:00:00"));
//		assertEquals(2,weeks);
//		
//		weeks = businessCalendar.numOfWeekends(formatter.parseDateTime("2013-10-30 1:00:00"),formatter.parseDateTime("2013-11-11 13:00:00"));
//		assertEquals(2,weeks);
//		
//		weeks = businessCalendar.numOfWeekends(formatter.parseDateTime("2013-10-30 1:00:00"),formatter.parseDateTime("2013-11-13 00:00:00"));
//		assertEquals(2,weeks);
//		
//		weeks = businessCalendar.numOfWeekends(formatter.parseDateTime("2013-10-30 1:00:00"),formatter.parseDateTime("2013-11-18 13:00:00"));
//		assertEquals(3,weeks);
		
		
		float minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-06 09:00:00"),formatter.parseDateTime("2013-11-06 17:00:00"));
		assertEquals(6*60,minDiff,0.1);
		
		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-06 10:00:00"),formatter.parseDateTime("2013-11-07 11:00:00"));
		assertEquals(8*60,minDiff,0.1);
		
		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-08 15:00:00"),formatter.parseDateTime("2013-11-10 14:00:00"));
		assertEquals(3*60,minDiff,0.1);
		
		
		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-08 15:00:00"),formatter.parseDateTime("2013-11-11 14:00:00"));
		assertEquals(6*60,minDiff,0.1);
		
		minDiff  = businessCalendar.minDiff(formatter.parseDateTime("2013-11-08 15:00:00"),formatter.parseDateTime("2013-11-18 14:00:00"));
		assertEquals(41*60,minDiff,0.1);
	}

	
}
