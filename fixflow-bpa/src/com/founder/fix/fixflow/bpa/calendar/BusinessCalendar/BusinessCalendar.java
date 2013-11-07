package com.founder.fix.fixflow.bpa.calendar.BusinessCalendar;


import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Weeks;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class BusinessCalendar {

	//private final static long MILLISECOND_OF_DAY = 1000 * 60 * 60 * 24;
	private final static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	
	private final static DateTime workingTimeStartAM = formatter.parseDateTime("2013-01-01 09:00:00");	
	private final static DateTime workingTimeEndAM = formatter.parseDateTime("2013-01-01 11:30:00");
	private final static DateTime workingTimeStartPM = formatter.parseDateTime("2013-01-01 13:30:00");
	private final static DateTime workingTimeEndPM = formatter.parseDateTime("2013-01-01 18:00:00");
	
	
	public float minDiff (DateTime dateStart , DateTime dateEnd) {
		float res = 0;
		
		//如果开始日期是周末移到下周一
		if(dateStart.getDayOfWeek()==6){
			dateStart = dateStart.plusDays(2);
			dateStart = new DateTime(dateStart.getYear(), dateStart.getMonthOfYear(), dateStart.getDayOfMonth(),
									workingTimeStartAM.getHourOfDay(), workingTimeStartAM.getMinuteOfHour(),workingTimeStartAM.getSecondOfMinute() );
		}
		if(dateStart.getDayOfWeek()==7){
			dateStart = dateStart.plusDays(1);
			dateStart = new DateTime(dateStart.getYear(), dateStart.getMonthOfYear(), dateStart.getDayOfMonth(),
									workingTimeStartAM.getHourOfDay(), workingTimeStartAM.getMinuteOfHour(),workingTimeStartAM.getSecondOfMinute() );
		}
		//如果结束日期是周末移到上周五
		if (dateEnd.getDayOfWeek()==6) {
			dateEnd = dateEnd.minusDays(1);
			dateEnd = new DateTime(dateEnd.getYear(), dateEnd.getMonthOfYear(), dateEnd.getDayOfMonth(),
					workingTimeEndPM.getHourOfDay(), workingTimeEndPM.getMinuteOfHour(),workingTimeEndPM.getSecondOfMinute() );	
		}
		if (dateEnd.getDayOfWeek()==7) {
			dateEnd = dateEnd.minusDays(2);
			dateEnd = new DateTime(dateEnd.getYear(), dateEnd.getMonthOfYear(), dateEnd.getDayOfMonth(),
					workingTimeEndPM.getHourOfDay(), workingTimeEndPM.getMinuteOfHour(),workingTimeEndPM.getSecondOfMinute() );	
		}
		

		//System.out.println(dateEnd.toString());
		if (dateStart.isAfter(dateEnd))
			return 0;
		
		int minDiff = Minutes.minutesBetween(dateStart, dateEnd).getMinutes();		
		int dayDiff = minDiff/(60*24) - numOfWeekends(dateStart, dateEnd)*2;		
		
		int hourStart = dateStart.getHourOfDay();	
		int minStart = dateStart.getMinuteOfHour();
		int secStart = dateStart.getSecondOfMinute();		
		DateTime timeStart =  formatter.parseDateTime("2013-01-01 "+hourStart+":"+minStart+":"+secStart);
		
		int hourEnd = dateEnd.getHourOfDay();	
		int minEnd = dateEnd.getMinuteOfHour();
		int secEnd = dateEnd.getSecondOfMinute();	
		DateTime timeEnd = formatter.parseDateTime("2013-01-01 "+hourEnd+":"+minEnd+":"+secEnd);
		
		if (dateStart.getYear() < dateEnd.getYear() || 
		(dateStart.getYear() == dateEnd.getYear()&&dateStart.getDayOfYear()<dateEnd.getDayOfYear()) ){
			
			int lunchTime =  Minutes.minutesBetween(workingTimeEndAM,workingTimeStartPM).getMinutes();		
			int workingTime = Minutes.minutesBetween(workingTimeStartAM,workingTimeEndPM).getMinutes()-lunchTime;
			
			float hoursOfFirstDay = (float)minDiffSameDay(timeStart, workingTimeEndPM);
			float hoursOfSecDay =  (float)minDiffSameDay(workingTimeStartAM, timeEnd);
			if (( hoursOfFirstDay + hoursOfSecDay ) >= workingTime)
				dayDiff--;
			res = hoursOfFirstDay + hoursOfSecDay + workingTime*dayDiff;
			
			return res;	
		} 
		else {			
			return (float)minDiffSameDay( timeStart, timeEnd);		
		}
	}  
	
	
//计算两个时间都在同一天内的时间差	
	public int minDiffSameDay(DateTime timeStart, DateTime timeEnd) {
		
		if (timeStart.isBefore(workingTimeStartAM))
			timeStart = workingTimeStartAM;
		else if ((timeStart.isAfter(workingTimeEndAM)||timeStart.isEqual(workingTimeEndAM))
		&&timeStart.isBefore(workingTimeStartPM))
			timeStart = workingTimeStartPM;
		else if (timeStart.isAfter(workingTimeEndPM)||timeStart.isEqual(workingTimeEndPM))
			return 0;
		
		if (timeEnd.isBefore(workingTimeStartAM) || timeEnd.isEqual(workingTimeStartAM)) 
			return 0;
		else if (timeEnd.isAfter(workingTimeEndAM)
		&&(timeEnd.isBefore(workingTimeStartPM)||timeEnd.isEqual(workingTimeStartPM)))
			timeEnd =  workingTimeEndAM;
		else if (timeEnd.isAfter(workingTimeEndPM)) 
			timeEnd = workingTimeEndPM;
		
		int res = Minutes.minutesBetween(timeStart,timeEnd).getMinutes();
		if(timeStart.isBefore(workingTimeEndAM)&&timeEnd.isAfter(workingTimeStartPM))
			res -= Minutes.minutesBetween(workingTimeEndAM,workingTimeStartPM).getMinutes();
		
		return res<0 ? 0:res;		
	}
	
	public int numOfWeekends(DateTime dateStart, DateTime dateEnd) {
		dateStart= new DateTime(dateStart.getYear(), dateStart.getMonthOfYear(), dateStart.getDayOfMonth(), 9, 0, 0 );
		dateEnd= new DateTime(dateEnd.getYear(), dateEnd.getMonthOfYear(), dateEnd.getDayOfMonth(), 9, 0, 0 );		
		int numOfWeekends = Weeks.weeksBetween(dateStart, dateEnd).getWeeks();
		
		if(dateStart.getDayOfWeek()>dateEnd.getDayOfWeek())
			numOfWeekends++;
		
		return numOfWeekends;		
	}
}
