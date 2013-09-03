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
package com.founder.fix.fixflow.expand.worktime;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.worktime.WorkTime;

public class WorkTimeImpl implements WorkTime {
	private int hours;
	private int minutes;
	private int seconds;
	
	private int returnHours;
	private int returnMinutes;
	private int returnSeconds;
	private String returnDateStr;
	private String nextWorkDate;

	public boolean isWorkDate(Date nowDate) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 重写方法
	 */
	public Date getWorkTime(Date newDate, int days, int hours, int minutes,
			int seconds, ExecutionContext executionContext) {
		SqlCommand sqlCommand = null;
		try {
			// 直接操作 sqlcommand 里边已经包含 connection了
			sqlCommand = executionContext.getSqlCommand();
		} catch (Exception e) {
			try {
				sqlCommand = new SqlCommand(Context.getDbConnection());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if(newDate == null) {
			return newDate;
		}
		
		//将传递的时间换算成‘秒’
		int updateTimeSeconds = hours * 3600 + minutes * 60 + seconds;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		
		//搜索出工作日
		String sql = "select commom_date from FIXFLOW_WORKDATE_DETAIL" +
				" group by commom_date order by commom_date";
		List<Map<String, Object>> workDays = sqlCommand.queryForList(sql);
		if(workDays == null || workDays.size() == 0) {
			return newDate;
		}
		
		//计算日期
		operWorkDate(sqlCommand, newDate, days, workDays, updateTimeSeconds);
		
		if(returnDateStr == null || returnDateStr.length() == 0) {
			returnDateStr = nextWorkDate + " "
					+ (returnHours < 10 ? ("0" + returnHours) : returnHours) + ":"
					+ (returnMinutes < 10 ? ("0" + returnMinutes) : returnMinutes) + ":"
					+ (returnSeconds < 10 ? ("0" + returnSeconds) : returnSeconds);
		}
//		System.out.println(returnDateStr);
		return putTimeStr19ToDate(returnDateStr);
	}
	
	/**
	 * 计算日期
	 * @param sqlCommand
	 * @param newDate
	 * @param nextdays
	 * @param workDays
	 * @param updateTimeSeconds
	 */
	private void operWorkDate(SqlCommand sqlCommand, Date newDate, int nextdays,
			List<Map<String, Object>> workDays,	int updateTimeSeconds) {
		nextWorkDate = "";	//满足条件的下一次工作日(因为是递归方法，所以最后的值即为得到的日期值)
		boolean isWorkDate = false;
		boolean overWholeYear = false;
		
		for (int i = 0; i < workDays.size(); i++) {
			Map<String, Object> map = (Map<String, Object>) workDays.get(i);
			String date = ((String) map.get("COMMOM_DATE")).replaceAll("-", "");
			if(date.equals(putDateToTimeStr8(newDate))) {
				isWorkDate = true;	//找到工作日
				if(nextdays > 0) {
					//根据传入的时间找到下一个工作日
					try {
						nextWorkDate = ((String) ((Map<String, Object>) 
								workDays.get(i + nextdays)).get("COMMOM_DATE"));
					} catch (Exception e) {
						//异常说明日期已经跨年
						nextWorkDate = putDateToTimeStr10(dateAfterNDays(
								putTimeStr19ToDate(((String) map.get("COMMOM_DATE"))
										+ " 00:00:00"), nextdays));
						returnDateStr = nextWorkDate + " " + this.hours + ":" + this.minutes + ":" + this.seconds;
						
						isWorkDate = false;	
						overWholeYear = true;
						break;
					}
				}
				else {
					nextWorkDate = date;
				}
				break;
			}
		}
		
		//传入的日期是工作日期
		if(isWorkDate) {
			String sql = "select * from FIXFLOW_WORKDATE_DETAIL where commom_date = " +
					"'" + nextWorkDate + "' order by begin_date";
			List<Map<String, Object>> workDay = sqlCommand.queryForList(sql);
			if(workDay == null || workDay.size() == 0) {
				return;
			}
			
			Map<String, Object> firstRow = (Map<String, Object>) workDay.get(0);
			int firstRowMinutes = ((BigDecimal) firstRow.get("MINUTES")).intValue();	//第一上班时间
			
			int secondRowMinutes = 0;
			Map<String, Object> secondRow = null;
			if(workDay.size() > 1) {
				secondRow = (Map<String, Object>) workDay.get(1);
				secondRowMinutes = ((BigDecimal) secondRow.get("MINUTES")).intValue();	//第二上班时间
			}
			
			if(updateTimeSeconds <= firstRowMinutes) {	//如果在区间内则直接加上
				String startTime = (String) firstRow.get("BEGIN_DATE");
				String endTime = (String) firstRow.get("END_DATE");
				String[] startTimeArray = startTime.split(":");
				String[] endTimeArray = endTime.split(":");
				
				//差值具体时间
				int diffHour = updateTimeSeconds / 3600;
				updateTimeSeconds = updateTimeSeconds % 3600;
				int diffMinute = updateTimeSeconds / 60;
				updateTimeSeconds = updateTimeSeconds % 60;
				int diffSecond = updateTimeSeconds % 60;
				
				returnHours = Integer.parseInt(startTimeArray[0]) + diffHour;
				returnMinutes = Integer.parseInt(startTimeArray[1]) + diffMinute;
				returnSeconds = Integer.parseInt(startTimeArray[2]) + diffSecond;
				
				if(returnSeconds > 59) {
					returnSeconds = returnSeconds - 60;
					returnMinutes += 1;
				}
				if(returnMinutes > 59) {
					returnMinutes = returnMinutes - 60;
					returnHours += 1; 
				}
				if(returnHours > Integer.parseInt(endTimeArray[0])) {
					int seconds = (returnHours - Integer.parseInt(endTimeArray[0])) * 3600 
							+ returnMinutes * 60 + returnSeconds;
					operWorkDate(sqlCommand, newDate, nextdays + 1, workDays, seconds);
				}
			} 
			else {
				//超过的部分需要求差值
				int diffNum = updateTimeSeconds - firstRowMinutes;
				//如果差值在区间内
				if(diffNum <= secondRowMinutes) {
					String startTime = (String) secondRow.get("BEGIN_DATE");
					String endTime = (String) secondRow.get("END_DATE");
					String[] startTimeArray = startTime.split(":");
					String[] endTimeArray = endTime.split(":");
					
					//差值具体时间
					int diffHour = diffNum / 3600;
					diffNum = diffNum % 3600;
					int diffMinute = diffNum / 60;
					diffNum = diffNum % 60;
					int diffSecond = diffNum % 60;
					
					returnHours = Integer.parseInt(startTimeArray[0]) + diffHour;
					returnMinutes = Integer.parseInt(startTimeArray[1]) + diffMinute;
					returnSeconds = Integer.parseInt(startTimeArray[2]) + diffSecond;
					
					if(returnSeconds > 59) {
						returnSeconds = returnSeconds - 60;
						returnMinutes += 1;
					}
					if(returnMinutes > 59) {
						returnMinutes = returnMinutes - 60;
						returnHours += 1; 
					}
					if(returnHours > Integer.parseInt(endTimeArray[0])) {
						int seconds = (returnHours - Integer.parseInt(endTimeArray[0])) * 3600 
								+ returnMinutes * 60 + returnSeconds;
						operWorkDate(sqlCommand, newDate, nextdays + 1, workDays, seconds);
					}
				}
				else {
					//如果差值依然超过下午的时间需要将时间记录到下一天，递归调用
					operWorkDate(sqlCommand, newDate, nextdays + 1, workDays, diffNum - secondRowMinutes);
				}
			}
		}
		//传入的日期不是工作日期
		else {
			if(!overWholeYear) {
				operWorkDate(sqlCommand, newDate, nextdays + 1, workDays, updateTimeSeconds);
			}
		}
	}
	
	public static void main(String[] args) {
		WorkTimeImpl impl = new WorkTimeImpl();
		impl.getWorkTime(impl.putTimeStr14ToDate("20120102000000"), 20, 23, 59, 59, null);
	}
	
	/**
	 * 格式化指定日期(yyyy-MM-dd)
	 * @param date
	 * @return 日期
	 */
	private String putDateToTimeStr8(Date date){
		//return getDateParser("yyyy-MM-dd").format(date);
		SimpleDateFormat yyyyMmDd = new SimpleDateFormat("yyyyMMdd");
		return yyyyMmDd.format(date);
	}
	
	/**
	 * 格式化指定日期(yyyy-MM-dd)
	 * @param date
	 * @return 日期
	 */
	private String putDateToTimeStr10(Date date){
		//return getDateParser("yyyy-MM-dd").format(date);
		SimpleDateFormat yyyyMmDd = new SimpleDateFormat("yyyy-MM-dd");
		return yyyyMmDd.format(date);
	}
	
	/**
     * 获得指定日期相距指定天数的日期
     * @param dt 指定日期
     * @param n 天数
     * @return 日期
     */
    private Date dateAfterNDays(Date dt, int n){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }
    
    /**
	 * 获得日期对象(yyyy-MM-dd HH:mm:ss)
	 * @param dateTimeStr19
	 * @return 日期
	 */
	private Date putTimeStr19ToDate(String dateTimeStr19) {
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		SimpleDateFormat dfyyyyMMddHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
	private Date putTimeStr14ToDate(String dateTimeStr) {
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			switch(dateTimeStr.length()){
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
	
	/*
	private Connection createConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@172.29.128.91:1521:orcl";
		String user = "idbase";
		String password = "idbase";
		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);
		return connection;
	}*/

	
}
