package com.founder.fix.fixflow.core.impl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.founder.fix.fixflow.core.exception.FixFlowException;


public class DateUtil {
	
	public static Date stringToDate(String dateString,String formatString){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

		
	    long days = mss / (24*3600);  
	    long hours = (mss % (60 * 60 * 24)) / ( 60 * 60);  
	    long minutes = (mss % (60 * 60)) / (60);  
	    long seconds = (mss % ( 60));  
	    return days + " 天 " + hours + " 小时 " + minutes + " 分 "  
	            + seconds + " 秒 ";  
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
