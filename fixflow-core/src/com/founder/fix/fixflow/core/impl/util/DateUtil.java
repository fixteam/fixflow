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
package com.founder.fix.fixflow.core.impl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.internationalization.FixFlowResources;

/**
 * 时间操作Util类
 */
public class DateUtil {
	
	private final static SimpleDateFormat dfYyyy = new SimpleDateFormat("yyyy");
    private final static SimpleDateFormat dfMM = new SimpleDateFormat("MM");
    private final static SimpleDateFormat dfDd = new SimpleDateFormat("dd");
    private final static SimpleDateFormat dfMmmYyyy = new SimpleDateFormat("MMM yyyy");
    private final static SimpleDateFormat dfDdMMMYYYY = new SimpleDateFormat("dd MMM yyyy");
    private final static SimpleDateFormat dfMmDdYYYY = new SimpleDateFormat("MM/dd/yyyy");
    private final static SimpleDateFormat dfyyyyMMddHHMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final static SimpleDateFormat dfyyyyMMddHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat yyyyMmDd = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat hhmm = new SimpleDateFormat("HH:mm"); // 24 Hours
    private final static SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat yyyymm = new SimpleDateFormat("yyyyMM");
    private final static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
    private final static SimpleDateFormat yyyyMMddHmmss = new SimpleDateFormat("yyyyMMddHmmss");
    private final static SimpleDateFormat yyyyMMddmmss = new SimpleDateFormat("yyyyMMddmmss");
    
    private final static String twentyFourHourRegExp = "^(([0-1][0-9])|(2[0-3])):([0-5][0-9])$";
    private final static SimpleDateFormat dfyyyyMMddHHMMSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    
    @SuppressWarnings("unused")
	private final static String timeFormat = "^(\\d{1,4})(-|\\/)(\\d{1,2})(-|\\/)(\\d{1,2})$";
    
    @SuppressWarnings("unused")
	private static boolean compare(String one,String sybmol,String two) throws ParseException{
    	boolean result = false;
    	Date date1 = yyyyMmDd.parse(one);
    	Date date2 = yyyyMmDd.parse(two);
    	if(">".equals(sybmol)){
    		result = date1.compareTo(date2)>0;
    	}else if("<".equals(sybmol)){
    		result = date1.compareTo(date2)<0;
    	}else if("=".equals(sybmol) || "==".equals(sybmol)){
    		result = date1.compareTo(date2)==0;
    	}else if(">=".equals(sybmol)){
    		result = date1.compareTo(date2)>=0;
    	}else if("<=".equals(sybmol)){
    		result = date1.compareTo(date2)<=0;
    	}
    	
    	return result;
    }
    

    /**
	 * add by jinxinzhang
	 * 
	 * @param pattern
	 * @return
	 */
	private static SimpleDateFormat getDateParser(String pattern) {
		return new SimpleDateFormat(pattern);
	}
	
	public static String parseGMTTime(Long time,String format){
		Date date = new Date();
		date.setTime(time);
		SimpleDateFormat dformat = getDateParser(format);
		
		return dformat.format(date);
	}
	
	/**
	 * 当前时间格式化(yyyyMMddHHmmssSSS)
	 * @return 时间
	 */
	public static String curDateTimeStr23() {
		Date date = new Date();
		//return getDateParser("yyyy-MM-dd HH:mm:ss:SSS").format(date);
		return dfyyyyMMddHHMMSSSSS.format(date);
	}

	/**
	 * 当前时间格式化(yyyy-MM-dd HH:mm:ss)
	 * @return 时间
	 */
	public static String curDateTimeStr19() {
		Date date = new Date();
		//return getDateParser("yyyy-MM-dd HH:mm:ss").format(date);
		return dfyyyyMMddHHMMSS.format(date);
	}
	
	/**
	 * 当前时间格式化(yyyy-MM-dd HH:mm:ss)
	 * @return 时间
	 */
	public static String curDateTimeYYYYMMDDHHMMSS() {
		Date date = new Date();
		//return getDateParser("yyyy-MM-dd HH:mm:ss").format(date);
		return yyyyMMddHHmmss.format(date);
	}
	
	/**
	 * 获得日期对象(yyyy-MM-dd HH:mm:ss)
	 * @param dateTimeStr19
	 * @return 日期
	 */
	public static Date putTimeStr19ToDate(String dateTimeStr19) {
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = dfyyyyMMddHHMMSS.parse(dateTimeStr19);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获得日期对象
	 * @param dateTimeStr
	 * @return 日期
	 */
	public static Date putTimeStr14ToDate(String dateTimeStr) {
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			switch(dateTimeStr.length()){
				case 12:
					date = yyyyMMddmmss.parse(dateTimeStr);
					break;
				case 13:;
					date = yyyyMMddHmmss.parse(dateTimeStr);
					break;
				case 14:;
					date = yyyyMMddHHmmss.parse(dateTimeStr);
					break;
				default:break;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	/**格式化指定日期(yyyy-MM-dd HH:mm)
	 * @param date
	 * @return
	 */
	public static String putDateToTimeStr16(Date date){
		return dfyyyyMMddHHMM.format(date);
	}
	
	
	/**
	 * 格式化指定日期(yyyy-MM-dd HH:mm:ss)
	 * @param date
	 * @return 日期
	 */
	public static String putDateToTimeStr19(Date date){
		//return getDateParser("yyyy-MM-dd HH:mm:ss").format(date);
		return dfyyyyMMddHHMMSS.format(date);
	}
	
	/**
	 * 格式化指定日期(HH:mm)
	 * @param date
	 * @return 日期
	 */
	public static String putDateToTimeHhMm(Date date){
		return hhmm.format(date);
	}
	
	/**
	 * 格式化指定日期(yyyy-MM-dd)
	 * @param date
	 * @return 日期
	 */
	public static String putDateToTimeStr10(Date date){
		//return getDateParser("yyyy-MM-dd").format(date);
		return yyyyMmDd.format(date);
	}
    
    /**
     * 格式化指定日期(yyyyMM)
     * @param date
     * @return 日期
     */
    public static String putDateToYmStr(Date date) {
        String dateStr = null;
        if(date != null)
            dateStr = yyyymm.format(date);
        return dateStr;
    }
	
	/**
	 * 格式化指定日期(yyyyMMdd)
	 * @param date
	 * @return 日期
	 */
    public static String putDateToYmdStr(Date date)
	{
	    String dateStr = null;
	    if(date != null)
	        dateStr = yyyymmdd.format(date);
	    return dateStr;
	}
    
	/**
	 * 获得当前时间
	 * @return 时间
	 */
	public static Date currentDate()
	{
		return new Date();
	}
	
    /**
     * 获得当前日期的年份
     * @return 年份
     */
    public static Integer currentYear()
    {
    	return Integer.valueOf(dfYyyy.format(currentDate()));
    }
    
    /**
     * @param topupSeq
     * @return 年
     */
    public static Integer currentTopupYear(String topupSeq)
    {
    	if("3".equals(topupSeq) || "4".equals(topupSeq))
    		return currentYear() - 1;
    	else
    		return currentYear();
    }
    
    /**
     * 获得当前日期的月份
     * @return 月份
     */
    public static Integer currentMonth (){
        return Integer.valueOf(dfMM.format(currentDate()));
    }
    
    /**
     * 获得当前日期的天
     * @return 天
     */
    public static Integer currentNumDate(){
        return Integer.valueOf(dfDd.format(currentDate()));
    }
    
    /**
     * 获得当前日期的上月的日期
     * @return 日期
     */
    public static Date preMonthDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,-1);
        return cal.getTime();
    }
    
    /**
     * 获得当前日期的上月的日期(MMM yyyy)
     * @return 日期
     */
    public static String preMonth(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,-1);
        return String.valueOf(dfMmmYyyy.format(cal.getTime()));
    }

    /**
     * 获得当前日期的上月首日日期(dd MMM yyyy)
     * @return 日期
     */
    public static String firstDayOfLastMonth(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DATE, 1);
        return String.valueOf(dfDdMMMYYYY.format(cal.getTime()));
    }

    /**
     * 获得当前日期的上月末日日期(dd MMM yyyy)
     * @return 日期
     */
    public static String lastDayOfLastMonth(){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.DATE,1);
        cal.add(Calendar.DATE,-1);
        return String.valueOf(dfDdMMMYYYY.format(cal.getTime()));
    }

    /**
     * 获得当前日期(dd MMM yyyy)
     * @return 日期
     */
    public static String letterDate(){
        return String.valueOf(dfDdMMMYYYY.format(currentDate()));
    }

    /**
     * 获得指定日期(dd MMM yyyy)
     * @param dt
     * @return 日期
     */
    public static String letterDate(Date dt){
        if(dt != null)
            return String.valueOf(dfDdMMMYYYY.format(dt));
        else
            return "";
    }

    /**
     * 获得指定日期(dd MMM yyyy)的日期类
     * @param ddMmmYyyy
     * @return 日期
     */
    public static Date getDateDdMmmYyyy(String ddMmmYyyy){
        try {
            return dfDdMMMYYYY.parse(ddMmmYyyy);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获得指定的日期(MMM yyyy)
     * @param dt
     * @return 日期
     */
    public static String dateMmmYyyy(Date dt){
        if(dt != null)
            return String.valueOf(dfMmmYyyy.format(dt));
        else
            return "";
    }
    
    /**
     * 获得指定的日期(yyyy-MM-dd)
     * @param dt
     * @return 日期
     */
    public static String dateYyyyMmDd(Date dt){
        if(dt != null)
            return String.valueOf(yyyyMmDd.format(dt));
        else
            return "";
    }
    
    /**
     * 获得指定的日期(yyyy-MM-dd HH:mm:ss)
     * @param dt
     * @return 日期
     */
    public static String dateYyyyMmDdHhMmSs(Date dt){
        if(dt != null)
            return String.valueOf(dfyyyyMMddHHMMSS.format(dt));
        else
            return "";
    }

     /**
      * 获得指定的日期(MM/dd/yyyy)
     * @param dt
     * @return 日期
     */
    public static String dateMmDdYyyy(Date dt){
        if(dt != null)
            return String.valueOf(dfMmDdYYYY.format(dt));
        else
            return "";
    }
    // Maybe need to change the algorithm, depends on the requirment
    /**
     * 根据指定的日期计算年龄
     * @param dob
     * @return 年龄
     */
    public static int age(Date dob){
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.setTime(dob);
        to.setTime(new Date());
        int birthYYYY = from.get( Calendar.YEAR );
        int curYYYY = to.get( Calendar.YEAR );

        int ageInYears = curYYYY - birthYYYY;

        if (ageInYears < 0)
            ageInYears = 0;

        return ageInYears;
    }
    
    /**
     * 计算2个日期相距的天数
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 天数
     */
    public static int dayBetweenTwoDates(Date beginDate, Date endDate){
        int days;
        int pnMark = 1;
        if(endDate!=null && beginDate != null)
        {
        	
            Calendar bCalendar = Calendar.getInstance();
            Calendar eCalendar = Calendar.getInstance();
            if(beginDate.after(endDate))
        	{
            	pnMark = -1;
            	bCalendar.setTime(endDate);
	            eCalendar.setTime(beginDate);
        	}else
        	{
	            bCalendar.setTime(beginDate);
	            eCalendar.setTime(endDate);
        	}
            int dayBegin = bCalendar.get(Calendar.DAY_OF_YEAR);
            int dayEnd = eCalendar.get(Calendar.DAY_OF_YEAR);
            days = dayEnd - dayBegin;
            int endYear = eCalendar.get(Calendar.YEAR);
            if(bCalendar.get(Calendar.YEAR) != endYear){
                bCalendar = (Calendar)bCalendar.clone();
            }
            while(bCalendar.get(Calendar.YEAR) != endYear)
            {
                days += bCalendar.getActualMaximum(Calendar.DAY_OF_YEAR);
                bCalendar.add(Calendar.YEAR, 1);
            }
        }else
            days = 0;

        return days*pnMark;
    }
    
    /**
     * 计算2个日期相距的时间差（差的形式为：XX天XX小时XX分XX秒）
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return XX天XX小时XX分XX秒
     */
    public static String timeBetweenTwoDates(Date beginDate, Date endDate){
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// java.util.Date now = df.parse("2004-03-26 13:31:40");
		// java.util.Date date=df.parse("2004-01-02 11:30:24");
		long l = endDate.getTime() - beginDate.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return day + "天" + hour + "小时"+min+"分"+s+"秒";
    }

    /**
     * 获得指定日期相距指定天数的日期
     * @param dt 指定日期
     * @param n 天数
     * @return 日期
     */
    public static Date dateAfterNDays(Date dt, int n){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }
    
    /**
     * 获得指定日期相距指定月数的日期
     * @param dt 指定日期
     * @param n 月数
     * @return 日期
     */
    public static Date dateAfterNMonths(Date dt, int n){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }
    
    /**
     * 获得指定日期相距指定天数的日期
     * @param dt 指定日期
     * @param n 年数
     * @return 日期
     */
    public static Date dateAfterNYears(Date dt, int n){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.YEAR, n);
        return cal.getTime();
    }

//    public static void main(String[] args){
//        System.out.println(getDateDdMmmYyyy("12 Dec 2007"));
//        System.out.println(dateYyyyMmDdHhMmSs(new Date()));
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        try {
//			Date d1=yyyyMmDd.parse("2004-07-09");
//			Date d2=yyyyMmDd.parse("2007-07-01");
//			System.out.println(dayBetweenTwoDates(d1,d2));
//			Calendar bCalendar = Calendar.getInstance();
//			bCalendar.setTime(d1);
//			System.out.println(bCalendar.getActualMaximum(Calendar.DAY_OF_YEAR));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//    }
    
    /**
     * 对当前日期使用指定格式格式化
     * @param formart 指定格式
     * @return 日期
     */
    public static String getDateFormatStr(String formart) {
		return new SimpleDateFormat(formart).format(new java.util.Date());
	}
    
    /**
     * 计算2个日期相距的天数
     * @param startDay 起始日期
     * @param endDay 结束日期
     * @return 天数
     */
    public static int getIntervalDays(Date startDay,Date endDay){
    	if(startDay.after(endDay)){
    		Date cal = startDay;
    		startDay = endDay;
    		endDay = cal;
    	}
       
    	long startl = startDay.getTime();
    	long endl = endDay.getTime();
    	long ei = endl - startl  ;
    	return (int)(ei/(1000*60*60*24));
    }
    
    /**
     * 获得当前的日期(yyyy-MM-dd)
     * @return 日期
     */
    public static String getCurrentDateStr()
    {
    	Date now=new Date();   
        //SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
        //return df.format(now);
    	return yyyyMmDd.format(now);
    }
    
    /**
     * 获得当前日期之后指定天数的日期(yyyy-MM-dd)
     * @param days 指定天数
     * @return 日期
     */
    public static String getBookInfoDate(int days)
    {
    	Date now=new Date();   
        //SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
        //return df.format(new Date(now.getTime() - (long)days * 24 * 60 * 60 * 1000));
        return yyyyMmDd.format(new Date(now.getTime() - (long)days * 24 * 60 * 60 * 1000));
    }
    
    /**
     * check if the string is one valid time, it should be hh:mm
     * @param timeStr
     * @return 结果
     */
    public static boolean isValidTimeHHMM(String timeStr){
    	if(StringUtil.isNotEmpty(timeStr)){
	    	Pattern p = Pattern.compile(twentyFourHourRegExp);
	    	Matcher m = p.matcher(timeStr);
	    	return m.matches();
    	}else
    		return false;
    }
    

    //added by peisongquan 2010-02-05
    
    /**
     * get format string Current Year
     * @return 日期
     */
    public static String getFormatCurYearAsYYYY(){
    	return dfYyyy.format(currentDate());
    }
    
    /**
     * get format string Previous Year
     * @return 日期
     */
    public static String getFormatPreYearAsYYYY(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR,-1);
        return dfYyyy.format(cal.getTime());
    }
	
	/**
	 * get format string current month "YYYYMM" 
	 * @return 日期
	 */
    public static String getFormatCurMonthAsYYYYMM(){
        //Calendar.getInstance().getTime()
    	return yyyymm.format(currentDate());
    }
    
    /**
     * get format string previous month "YYYYMM"
     * @return 日期
     */
    public static String getFormatPreMonthAsYYYYMM(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,-1);
        return yyyymm.format(cal.getTime());
    }
    
     /**
      * 获得指定年月的最后一天
     * @param year 指定年份
     * @param month 指定月份
     * @return 天
     */
    public static int getLastDayOfMonth(int year, int month) {
		
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8    
	            || month == 10 || month == 12) {    
	        return 31;    
		}    
		if (month == 4 || month == 6 || month == 9 || month == 11) {    
	        return 30;    
		}    
		if (month == 2) {    
	        if (isLeapYear(year)) {    
	            return 29;    
	        } else {    
	            return 28;    
	        }    
		}    
		return 0;    
	}

	/**   
	 * 判断指定年份是否是闰年
	 * @param year 指定年份
	 * @return true:是闰年 false:不是闰年
	 */    
	public static boolean isLeapYear(int year) {    
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);    
	} 
	
     /**
      * 获得指定日期所在季度的起始月份或结束月份
     * @param date 指定日期
     * @param firstOrLast true:起始月份 false:结束月份
     * @return 日期
     */
    public static String getMonthOfSeason(Date date,boolean firstOrLast){    	 
    	 SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    	 SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    	 String yearStr = yearFormat.format(date);
    	 String monthStr = monthFormat.format(date);
         int month = Integer.parseInt(monthStr);

         String array[][] = {{"01","02","03"},{"04","05","06"},{"07","08","09"},{"10","11","12"}};
         
         int season = 1; 
        	 
         if(month>=1&&month<=3){    
             season = 1;    
         }    
         if(month>=4&&month<=6){    
             season = 2;    
         }    
         if(month>=7&&month<=9){    
             season = 3;    
         }    
         if(month>=10&&month<=12){    
             season = 4;    
         }
         
         String start_month = array[season-1][0];         
         String end_month = array[season-1][2]; 
         
         if(firstOrLast){
        	 return yearStr+start_month;
         }else{
        	 return yearStr+end_month;
         }         
     } 
	
	
    
    public static void main(String args[]){
//    	boolean str;
//		try {
//			str = compare("2009-09-09","<","2009-09-08");
//			System.out.println(str);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
    	
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date endDate = df.parse("2004-03-26 13:31:40");
			java.util.Date beginDate = df.parse("2004-01-02 11:30:24");
	    	System.out.println(timeBetweenTwoDates(beginDate, endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    }
	
	public static Date stringToDate(String dateString,String formatString){
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		Date date=null;
		try {
			date = sdf.parse(StringUtil.getString(dateString));
		} catch (ParseException e) {
			throw new FixFlowException("日期 "+dateString+" 格式转换出错!",e);
		}
		return date;
	}
	
	/** 
	 *  
	 * @param 要转换的毫秒数 
	 * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式 
	 * @author fy.zhang 
	 */  
	public static String formatDuring(long mss) {  
		//传过来的是毫秒，转换成秒级     by ych  2013-07-23
		mss /= 1000;
	    long days = mss / (24*3600);  
	    long hours = (mss % (60 * 60 * 24)) / ( 60 * 60);  
	    long minutes = (mss % (60 * 60)) / (60);  
	    long seconds = (mss % ( 60)); 
	    
	    
	    Boolean booleanTemp = StringUtil.getBoolean(Context.getProcessEngineConfiguration().getInternationalizationConfig().getIsEnable());

		// 用户名称国际化处理
		if (booleanTemp) {

			FixFlowResources fixFlowResources = Context.getProcessEngineConfiguration().getFixFlowResources();

			String dayString = fixFlowResources.getResourceName(FixFlowResources.SystemResource, "FixFlow_Date_day_Name");
			
			String hourString = fixFlowResources.getResourceName(FixFlowResources.SystemResource, "FixFlow_Date_hour_Name");
			
			String minuteString = fixFlowResources.getResourceName(FixFlowResources.SystemResource, "FixFlow_Date_minute_Name");
			
			String secondString = fixFlowResources.getResourceName(FixFlowResources.SystemResource, "FixFlow_Date_second_Name");
			
			dayString=StringUtil.decodeNull(dayString, "天");
			hourString=StringUtil.decodeNull(hourString, "小时");
			minuteString=StringUtil.decodeNull(minuteString, "分");
			secondString=StringUtil.decodeNull(secondString, "秒");
			
			 return days + dayString+" " + hours + hourString+" " + minutes + minuteString+" "  
	            + seconds + secondString;  
			
		}
	    
	    return days + "天 " + hours + "小时 " + minutes + "分 "  
	            + seconds + "秒 ";
	}  
	/** 
	 *  
	 * @param begin 时间段的开始 
	 * @param end   时间段的结束 
	 * @return  输入的两个Date类型数据之间的时间间格用* days * hours * minutes * seconds的格式展示 
	 * @author fy.zhang 
	 */  
	public static String formatDuring(Date begin, Date end) { 
	    return formatDuring(end.getTime() - begin.getTime());  
	}  

}
